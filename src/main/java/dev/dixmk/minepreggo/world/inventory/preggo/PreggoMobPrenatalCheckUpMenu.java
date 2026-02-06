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
import dev.dixmk.minepreggo.world.entity.preggo.ITamablePregnantPreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
import dev.dixmk.minepreggo.world.item.checkup.PrenatalCheckups;
import dev.dixmk.minepreggo.world.item.checkup.PrenatalCheckups.PrenatalCheckup;
import dev.dixmk.minepreggo.world.item.checkup.PrenatalCheckups.PrenatalCheckupData;
import dev.dixmk.minepreggo.world.pregnancy.PrenatalCheckupHelper;
import io.netty.buffer.Unpooled;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.network.NetworkHooks;

public class PreggoMobPrenatalCheckUpMenu extends AbstractPrenatalCheckUpMenu<PreggoMob, ScientificIllager> {

	private Optional<ITamablePregnantPreggoMob> pregnancySystem;
	
	public PreggoMobPrenatalCheckUpMenu(int id, Inventory inv, FriendlyByteBuf buffer) {
		super(MinepreggoModMenus.PREGGO_MOB_PRENATAL_CHECKUP_MENU.get(), id, inv, buffer);	
	}

	@Override
	protected void readBuffer(FriendlyByteBuf buffer) {
		PreggoMob s = null;	
		ITamablePregnantPreggoMob ps = null;
		ScientificIllager t = null;
		Vector3i p = null;
		
		if (buffer != null) {	
			var pos = buffer.readBlockPos();			
			p = new Vector3i(pos.getX(), pos.getY(), pos.getZ());
			
			this.pos = Optional.ofNullable(p);
			
			if (level.getEntity(buffer.readVarInt()) instanceof PreggoMob preggoMob)  {
				s = preggoMob;		
				if (preggoMob instanceof ITamablePregnantPreggoMob pregSystem) {
					ps = pregSystem;
					this.motherPregnancyPhase = pregSystem.getPregnancyData().getCurrentPregnancyPhase();
				}	
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
		this.pos = Optional.ofNullable(p);
		this.pregnancySystem = Optional.ofNullable(ps);
		
		this.valid = this.source.isPresent() && this.pregnancySystem.isPresent() && this.target.isPresent();	
				
		
		if (!valid) {
			MinepreggoMod.LOGGER.error("Target={} or Source={} or PregnancySystem={} was null",
					this.source.isPresent(), this.target.isPresent(), this.pregnancySystem.isPresent());
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
	protected PrenatalCheckups createTradesForThisSession() {
		if (source.isEmpty() || pregnancySystem.isEmpty() || target.isEmpty()) {
			MinepreggoMod.LOGGER.error("Source or PregnancySystem was empty when creating trades");
			return null;
		}
			
		final var ps = pregnancySystem.get().getPregnancyData();
		
		String playerName = source.get().getSimpleNameOrCustom();
		LocalDateTime date = LocalDateTime.now();
		String autor = target.get().getName().getString();
		
		Supplier<ItemStack> f1 = () -> PrenatalCheckupHelper.createPrenatalCheckUpResult(
				new PrenatalCheckupHelper.PrenatalCheckUpInfo(playerName, emeraldForRegularCheckUp, date),
				PrenatalCheckupHelper.createRegularPrenatalCheckUpData(ps), autor);

		Supplier<ItemStack> f2 = () -> PrenatalCheckupHelper.createPrenatalCheckUpResult(
				new PrenatalCheckupHelper.PrenatalCheckUpInfo(playerName, emeraldForUltrasoundScan, date),
				PrenatalCheckupHelper.createUltrasoundScanPrenatalCheckUpData(source.get().getTypeOfSpecies(), ps), autor);
	
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

	@Override
	protected void onSuccessful() {		
		this.source.ifPresent(s -> {
			if (this.level.isClientSide) {
				this.level.playLocalSound(s.getX(), s.getY(), s.getZ(), SoundEvents.PILLAGER_CELEBRATE, SoundSource.AMBIENT, 1, 1, false);
			}
		});
	}
	
	public static void showPrenatalCheckUpMenu(@NonNull ServerPlayer serverPlayer, PreggoMob preggoMob, ScientificIllager scientificIllager) {						
		final var pos = preggoMob.blockPosition();
		final var preggoMobId = preggoMob.getId();
		final var scientificIllagerId = scientificIllager.getId();
		final var costs = scientificIllager.getPrenatalCheckupCosts();
		
		NetworkHooks.openScreen(serverPlayer,new MenuProvider() {
            @Override
            public Component getDisplayName() {
                return Component.literal("CreeperGirlMainGUI");
            }

            @Override
            public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
                FriendlyByteBuf packetBuffer = new FriendlyByteBuf(Unpooled.buffer());
                packetBuffer.writeBlockPos(pos);
                packetBuffer.writeVarInt(preggoMobId);
                packetBuffer.writeVarInt(scientificIllagerId);
                packetBuffer.writeInt(costs.getCost(PrenatalCheckup.REGULAR));
                packetBuffer.writeInt(costs.getCost(PrenatalCheckup.ULTRASOUND_SCAN));
                packetBuffer.writeInt(costs.getCost(PrenatalCheckup.PATERNITY_TEST));	 
                return new PreggoMobPrenatalCheckUpMenu(id, inventory, packetBuffer);
            }
        }, buf -> {
        	buf.writeBlockPos(pos);
			buf.writeVarInt(preggoMobId);
			buf.writeVarInt(scientificIllagerId);
			buf.writeInt(costs.getCost(PrenatalCheckup.REGULAR));
			buf.writeInt(costs.getCost(PrenatalCheckup.ULTRASOUND_SCAN));
			buf.writeInt(costs.getCost(PrenatalCheckup.PATERNITY_TEST));
		});	  				
	}
}
