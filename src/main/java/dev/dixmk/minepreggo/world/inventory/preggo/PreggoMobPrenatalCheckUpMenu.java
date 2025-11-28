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
import dev.dixmk.minepreggo.network.capability.IPregnancySystemHandler;
import dev.dixmk.minepreggo.world.entity.monster.ScientificIllager;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancySystemHelper;
import dev.dixmk.minepreggo.world.item.checkup.PrenatalCheckups;
import dev.dixmk.minepreggo.world.item.checkup.PrenatalCheckups.PrenatalCheckupData;
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

	private Optional<IPregnancySystemHandler> pregnancySystem;
	
	public PreggoMobPrenatalCheckUpMenu(int id, Inventory inv, FriendlyByteBuf buffer) {
		super(MinepreggoModMenus.PREGGO_MOB_PRENATAL_CHECKUP_MENU.get(), id, inv, buffer);	
	}

	@Override
	protected void readBuffer(FriendlyByteBuf buffer) {
		PreggoMob s = null;	
		IPregnancySystemHandler ps = null;
		ScientificIllager t = null;
		Vector3i p = null;
		
		if (buffer != null) {	
			var pos = buffer.readBlockPos();			
			p = new Vector3i(pos.getX(), pos.getY(), pos.getZ());
			
			this.pos = Optional.ofNullable(p);
			
			if (level.getEntity(buffer.readVarInt()) instanceof PreggoMob preggoMob)  {
				s = preggoMob;		
				if (preggoMob instanceof IPregnancySystemHandler pregSystem) {
					ps = pregSystem;
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
	protected @NonNull PrenatalCheckups createTradesForThisSession() {
		if (source.isEmpty() || pregnancySystem.isEmpty() || target.isEmpty()) {
			MinepreggoMod.LOGGER.error("Source or PregnancySystem was empty when creating trades");
			return null;
		}
			
		final var ps = pregnancySystem.get();
		
		String playerName = source.get().getSimpleName();
		LocalDateTime date = LocalDateTime.now();
		String autor = target.get().getName().getString();
		
		Supplier<ItemStack> f1 = () -> PregnancySystemHelper.createPrenatalCheckUpResult(
				new PregnancySystemHelper.PrenatalCheckUpInfo(playerName, emeraldForRegularCheckUp, date),
				PreggoMobHelper.createRegularPrenatalCheckUpData(ps), autor);

		Supplier<ItemStack> f2 = () -> PregnancySystemHelper.createPrenatalCheckUpResult(
				new PregnancySystemHelper.PrenatalCheckUpInfo(playerName, emeraldForUltrasoundScan, date),
				PreggoMobHelper.createUltrasoundScanPrenatalCheckUpData(source.get().getTypeOfSpecies(), ps), autor);
	
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

	@Override
	protected void onSuccessful() {		
		this.source.ifPresent(s -> {
			if (this.level.isClientSide) {
				this.level.playLocalSound(s.getX(), s.getY(), s.getZ(), SoundEvents.PILLAGER_CELEBRATE, SoundSource.AMBIENT, 1, 1, false);
			}
		});
	}
	
	public static void showPrenatalCheckUpMenu(@NonNull ServerPlayer serverPlayer, PreggoMob preggoMob, ScientificIllager scientificIllager, int regularCheckUpCost, int ultrasoundScanCost, int paternityTestCost) {						
		final var pos = preggoMob.blockPosition();
		final var preggoMobId = preggoMob.getId();
		final var scientificIllagerId = scientificIllager.getId();
		
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
                packetBuffer.writeInt(regularCheckUpCost);
                packetBuffer.writeInt(ultrasoundScanCost);
                packetBuffer.writeInt(paternityTestCost);
                return new PreggoMobPrenatalCheckUpMenu(id, inventory, packetBuffer);
            }
        }, buf -> {
        	buf.writeBlockPos(pos);
			buf.writeVarInt(preggoMobId);
			buf.writeVarInt(scientificIllagerId);
			buf.writeInt(regularCheckUpCost);
			buf.writeInt(ultrasoundScanCost);
			buf.writeInt(paternityTestCost);
		});	  				
	}
}
