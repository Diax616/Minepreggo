package dev.dixmk.minepreggo.world.inventory.preggo;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.joml.Vector3i;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.init.MinepreggoModAdvancements;
import dev.dixmk.minepreggo.init.MinepreggoModMenus;
import dev.dixmk.minepreggo.network.packet.s2c.PlaySoundPacketS2C;
import dev.dixmk.minepreggo.world.entity.monster.ScientificIllager;
import dev.dixmk.minepreggo.world.item.checkup.PrenatalCheckups;
import dev.dixmk.minepreggo.world.item.checkup.PrenatalCheckups.PrenatalCheckup;
import dev.dixmk.minepreggo.world.item.checkup.PrenatalCheckups.PrenatalCheckupData;
import dev.dixmk.minepreggo.world.pregnancy.PrenatalCheckupCostHolder.PrenatalCheckupCost;
import dev.dixmk.minepreggo.world.pregnancy.PrenatalCheckupHelper;
import io.netty.buffer.Unpooled;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
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
import net.minecraftforge.network.PacketDistributor;

public abstract class PlayerPrenatalCheckUpMenu<T extends Mob> extends AbstractPrenatalCheckUpMenu<Player, T> {
	
	protected PlayerPrenatalCheckUpMenu(MenuType<?> menu, int id, Inventory inv, FriendlyByteBuf buffer) {
		super(menu, id, inv, buffer);
	}	
		
	@Override
	protected void onSuccessful(PrenatalCheckup prenatalCheckup) {		
		if (this.source.orElse(null) instanceof ServerPlayer serverPlayer) {
			serverPlayer.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap -> cap.getPlayerStatistic().addPrenatalCheckupDoneMyself(prenatalCheckup));
			MinepreggoModAdvancements.PRENATAL_CHECKUP_TRIGGER.trigger(serverPlayer);
		}
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
		
		final var pregnancySystem = femaleData.get().getPregnancyData();	
		String playerName = player.getName().getString();
		LocalDateTime date = LocalDateTime.now();
		String autor = target.get().getName().getString();
		
		Supplier<ItemStack> f1 = () -> PrenatalCheckupHelper.createPrenatalCheckUpResult(
				new PrenatalCheckupHelper.PrenatalCheckUpInfo(playerName, emeraldForRegularCheckUp, date),
				pregnancySystem.createRegularCheckUpData(),
				autor);

		Supplier<ItemStack> f2 = () -> PrenatalCheckupHelper.createPrenatalCheckUpResult(
				new PrenatalCheckupHelper.PrenatalCheckUpInfo(playerName, emeraldForUltrasoundScan, date),
				pregnancySystem.createUltrasoundScanData(),
				autor);
	
		UUID motherId = source.get().getUUID();
		
		final var malePlayers =	level.getEntitiesOfClass(Player.class, new AABB(player.blockPosition()).inflate(16), p -> {
			final var c = p.getCapability(MinepreggoCapabilities.PLAYER_DATA).resolve();
			return !(c.isEmpty() || c.get().isFemale());
		}).stream()
		  .map(p -> new PrenatalCheckupHelper.PrenatalPaternityTestData(p.getId(), p.getName().getString(), p.getUUID().equals(motherId)))
		  .toList();
		
		Supplier<ItemStack> f3 = () -> PrenatalCheckupHelper.createPrenatalCheckUpResult(
				new PrenatalCheckupHelper.PrenatalCheckUpInfo(playerName, emeraldForPaternityTest, date),
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
		protected void onSuccessful(PrenatalCheckup prenatalCheckup) {		
			super.onSuccessful(prenatalCheckup);
			if (this.source.orElse(null) instanceof ServerPlayer serverPlayer) {
				MinepreggoModPacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> serverPlayer), new PlaySoundPacketS2C(SoundEvents.VILLAGER_YES, player.blockPosition(), 0.75f, 1.0f));
			}
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
			
			this.source.ifPresent(p -> 
				p.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap -> 
					cap.getFemaleData().ifPresent(femaleData -> 
						this.motherPregnancyPhase =	femaleData.getPregnancyData().getCurrentPregnancyPhase()
					)
				)
			);
					
			if (!valid) {
				MinepreggoMod.LOGGER.error("Target={} or Source={} was null",
						this.source.isPresent(), this.target.isPresent());
				
			}
		}

		@Override
		public void removed(Player playerIn) {
			super.removed(playerIn);
			if (!level.isClientSide) {
				this.target.ifPresent(t -> t.setTradingPlayer(null));
			}
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
		protected void onSuccessful(PrenatalCheckup prenatalCheckup) {		
			super.onSuccessful(prenatalCheckup);
			if (this.source.orElse(null) instanceof ServerPlayer serverPlayer) {
				MinepreggoModPacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> serverPlayer), new PlaySoundPacketS2C(SoundEvents.VINDICATOR_CELEBRATE, player.blockPosition(), 0.75f, 1.0f));
			}
		}
		
		@Override
		public void removed(Player playerIn) {
			super.removed(playerIn);
			if (!level.isClientSide) {
				this.target.ifPresent(t -> t.setTradingPlayer(null));
			}
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
				
			this.source.ifPresent(p -> 
				p.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap -> 
					cap.getFemaleData().ifPresent(femaleData -> 
						this.motherPregnancyPhase =	femaleData.getPregnancyData().getCurrentPregnancyPhase()
					)
				)
			);	
			
			this.valid = this.source.isPresent() && this.target.isPresent();		
			
			if (!valid) {
				MinepreggoMod.LOGGER.error("Target={} or Source={} was null",
						this.source.isPresent(), this.target.isPresent());
			}
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