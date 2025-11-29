package dev.dixmk.minepreggo.world.inventory.preggo;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.joml.Vector3i;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.init.MinepreggoModMenus;
import dev.dixmk.minepreggo.world.entity.monster.ScientificIllager;
import dev.dixmk.minepreggo.world.item.checkup.PrenatalCheckups;
import dev.dixmk.minepreggo.world.item.checkup.PrenatalCheckups.PrenatalCheckup;
import dev.dixmk.minepreggo.world.item.checkup.PrenatalCheckups.PrenatalCheckupData;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import dev.dixmk.minepreggo.world.pregnancy.PrenatalCheckupCostHolder.PrenatalCheckupCost;
import io.netty.buffer.Unpooled;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.network.NetworkHooks;

public abstract class PlayerPrenatalCheckUpMenu<T extends Mob> extends AbstractPrenatalCheckUpMenu<Player, T> {
	
	protected PlayerPrenatalCheckUpMenu(MenuType<?> menu, int id, Inventory inv, FriendlyByteBuf buffer) {
		super(menu, id, inv, buffer);
	}	

	@Override
	protected void readBuffer(FriendlyByteBuf buffer) {
		Vector3i p = null;
		if (buffer != null) {
			var pos = buffer.readBlockPos();			
			p = new Vector3i(pos.getX(), pos.getY(), pos.getZ());
		}
		
		this.pos = Optional.ofNullable(p);	
	}
	
	protected PrenatalCheckups createTradesForThisSession() {
		if (source.isEmpty() || target.isEmpty()) {
			MinepreggoMod.LOGGER.error("Source player was empty when creating prenatal check-up trades");
			return null;
		}
		
		final var cap = source.get().getCapability(MinepreggoCapabilities.PLAYER_DATA).resolve();	
		
		if (cap.isEmpty()) {
			MinepreggoMod.LOGGER.error("Source player {} had no PLAYER_DATA capability when creating prenatal check-up trades", source.get().getName().getString());
			return null;
		}
		
		final var femaleData = cap.get().getFemaleData().resolve(); 
	
		if (femaleData.isEmpty() || !femaleData.get().isPregnant()) {
			MinepreggoMod.LOGGER.error("Source player {} was not pregnant when creating prenatal check-up trades", source.get().getName().getString());
			return null;
		}
		
		final var pregnancySystem = femaleData.get().getPregnancySystem();	
		String playerName = player.getName().getString();
		LocalDateTime date = LocalDateTime.now();
		String autor = target.get().getName().getString();
		
		Supplier<ItemStack> f1 = () -> PregnancySystemHelper.createPrenatalCheckUpResult(
				new PregnancySystemHelper.PrenatalCheckUpInfo(playerName, emeraldForRegularCheckUp, date),
				pregnancySystem.createRegularCheckUpData(),
				autor);

		Supplier<ItemStack> f2 = () -> PregnancySystemHelper.createPrenatalCheckUpResult(
				new PregnancySystemHelper.PrenatalCheckUpInfo(playerName, emeraldForUltrasoundScan, date),
				pregnancySystem.createUltrasoundScanData(),
				autor);
	
		UUID motherId = source.get().getUUID();
		
		final var malePlayers =	level.getEntitiesOfClass(Player.class, new AABB(player.blockPosition()).inflate(16), p -> {
			final var c = p.getCapability(MinepreggoCapabilities.PLAYER_DATA).resolve();
			return !(c.isEmpty() || c.get().isFemale());
		}).stream()
		  .map(p -> new PregnancySystemHelper.PrenatalPaternityTestData(p.getId(), p.getName().getString(), p.getUUID().equals(motherId)))
		  .toList();
		
		Supplier<ItemStack> f3 = () -> PregnancySystemHelper.createPrenatalCheckUpResult(
				new PregnancySystemHelper.PrenatalCheckUpInfo(playerName, emeraldForPaternityTest, date),
				malePlayers,
				autor);
				
		return PrenatalCheckups.from(
				new PrenatalCheckupData(emeraldForRegularCheckUp, f1),
				new PrenatalCheckupData(emeraldForUltrasoundScan, f2),
				new PrenatalCheckupData(emeraldForPaternityTest, f3));
	}
	

	public static class VillagerMenu extends PlayerPrenatalCheckUpMenu<Villager> {
		
		public VillagerMenu(int id, Inventory inv, FriendlyByteBuf buffer) {
			super(MinepreggoModMenus.PLAYER_PRENATAL_CHECKUP_BY_VILLAGER_MENU.get(), id, inv, buffer);	
		}
		
		@Override
		protected void readBuffer(FriendlyByteBuf buffer) {
			super.readBuffer(buffer);
			
			Player s = null;	
			Villager t = null;
			
			if (buffer != null) {
				if (level.getEntity(buffer.readVarInt()) instanceof Player pregnantPlayer)  {
					s = pregnantPlayer;					
				}	
			
				if (level.getEntity(buffer.readVarInt()) instanceof Villager villager) {
					t = villager;
				}	
				
				emeraldForRegularCheckUp = buffer.readInt();
				emeraldForUltrasoundScan = buffer.readInt();
				emeraldForPaternityTest = buffer.readInt();
			}
					
			this.target = Optional.ofNullable(t);
			this.source = Optional.ofNullable(s);
				
			this.valid = this.source.isPresent() && this.target.isPresent();		
			
			if (!valid) {
				MinepreggoMod.LOGGER.error("Target={} or Source={} was null",
						this.source.isPresent(), this.target.isPresent());
			}
		}

		@Override
		protected void onSuccessful() {		
			this.source.ifPresent(s -> {
				if (this.level.isClientSide) {
					this.level.playLocalSound(s.getX(), s.getY(), s.getZ(), SoundEvents.VILLAGER_CELEBRATE, SoundSource.AMBIENT, 1, 1, false);
				}
			});
		}
		
		public static void showPrenatalCheckUpMenu(@NonNull ServerPlayer serverPlayer, @NonNull Villager villager) {						
			final var pos = serverPlayer.blockPosition();
			final var playerId = serverPlayer.getId();
			final var villagerId = villager.getId();
			final var cap = villager.getCapability(MinepreggoCapabilities.VILLAGER_DATA).resolve();
			PrenatalCheckupCost costs = cap.isPresent() ? cap.get().getPrenatalCheckupCosts() : new PrenatalCheckupCost(3, 6);
	
			NetworkHooks.openScreen(serverPlayer,new MenuProvider() {
	            @Override
	            public Component getDisplayName() {
	                return Component.literal("CreeperGirlMainGUI");
	            }

	            @Override
	            public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
	                FriendlyByteBuf packetBuffer = new FriendlyByteBuf(Unpooled.buffer());
	                packetBuffer.writeBlockPos(pos);
	                packetBuffer.writeVarInt(playerId);
	                packetBuffer.writeVarInt(villagerId);
	                packetBuffer.writeInt(costs.getCost(PrenatalCheckup.REGULAR));
	                packetBuffer.writeInt(costs.getCost(PrenatalCheckup.ULTRASOUND_SCAN));
	                packetBuffer.writeInt(costs.getCost(PrenatalCheckup.PATERNITY_TEST));	
	                return new VillagerMenu(id, inventory, packetBuffer);
	            }
	        }, buf -> {
	        	buf.writeBlockPos(pos);
				buf.writeVarInt(playerId);
				buf.writeVarInt(villagerId);
				buf.writeInt(costs.getCost(PrenatalCheckup.REGULAR));
				buf.writeInt(costs.getCost(PrenatalCheckup.ULTRASOUND_SCAN));
				buf.writeInt(costs.getCost(PrenatalCheckup.PATERNITY_TEST));
			});	  				
		}

	}
	
	public static class IllagerMenu extends PlayerPrenatalCheckUpMenu<ScientificIllager> {
		
		public IllagerMenu(int id, Inventory inv, FriendlyByteBuf buffer) {
			super(MinepreggoModMenus.PLAYER_PRENATAL_CHECKUP_BY_ILLAGER_MENU.get(), id, inv, buffer);
		}
		
		@Override
		protected void readBuffer(FriendlyByteBuf buffer) {
			super.readBuffer(buffer);
			
			Player s = null;	
			ScientificIllager t = null;
			
			if (buffer != null) {
				if (level.getEntity(buffer.readVarInt()) instanceof Player pregnantPlayer)  {
					s = pregnantPlayer;					
				}	
			
				if (level.getEntity(buffer.readVarInt()) instanceof ScientificIllager scientificIllager) {
					t = scientificIllager;
				}	
				
				emeraldForRegularCheckUp = buffer.readInt();
				emeraldForUltrasoundScan = buffer.readInt();
				emeraldForPaternityTest = buffer.readInt();
			}
					
			this.target = Optional.ofNullable(t);
			this.source = Optional.ofNullable(s);
				
			this.valid = this.source.isPresent() && this.target.isPresent();		
			
			if (!valid) {
				MinepreggoMod.LOGGER.error("Target={} or Source={} was null",
						this.source.isPresent(), this.target.isPresent());
			}
		}

		@Override
		protected void onSuccessful() {		
			this.source.ifPresent(s -> {
				if (this.level.isClientSide) {
					this.level.playLocalSound(s.getX(), s.getY(), s.getZ(), SoundEvents.PILLAGER_CELEBRATE, SoundSource.AMBIENT, 1, 1, false);
				}
			});
		}
		
		public static void showPrenatalCheckUpMenu(@NonNull ServerPlayer serverPlayer, @NonNull ScientificIllager scientificIllager) {						
			final var pos = serverPlayer.blockPosition();
			final var playerId = serverPlayer.getId();
			final var scientificIllagerId = scientificIllager.getId();
			PrenatalCheckupCost costs = scientificIllager.getPrenatalCheckupCosts();
			
			NetworkHooks.openScreen(serverPlayer, new MenuProvider() {
	            @Override
	            public Component getDisplayName() {
	                return Component.literal("CreeperGirlMainGUI");
	            }

	            @Override
	            public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
	                FriendlyByteBuf packetBuffer = new FriendlyByteBuf(Unpooled.buffer());
	                packetBuffer.writeBlockPos(pos);
	                packetBuffer.writeVarInt(playerId);
	                packetBuffer.writeVarInt(scientificIllagerId);
	                packetBuffer.writeInt(costs.getCost(PrenatalCheckup.REGULAR));
	                packetBuffer.writeInt(costs.getCost(PrenatalCheckup.ULTRASOUND_SCAN));
	                packetBuffer.writeInt(costs.getCost(PrenatalCheckup.PATERNITY_TEST));	                	                
	                return new IllagerMenu(id, inventory, packetBuffer);
	            }
	        }, buf -> {
	        	buf.writeBlockPos(pos);
				buf.writeVarInt(playerId);
				buf.writeVarInt(scientificIllagerId);
				buf.writeInt(costs.getCost(PrenatalCheckup.REGULAR));
				buf.writeInt(costs.getCost(PrenatalCheckup.ULTRASOUND_SCAN));
				buf.writeInt(costs.getCost(PrenatalCheckup.PATERNITY_TEST));
			});	  				
		}
	}
}