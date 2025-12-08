package dev.dixmk.minepreggo.network.capability;

import java.util.Optional;

import org.jetbrains.annotations.Nullable;

import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.network.packet.SyncFemalePlayerDataS2CPacket;
import dev.dixmk.minepreggo.world.pregnancy.FemaleEntityImpl;
import dev.dixmk.minepreggo.world.pregnancy.PostPregnancy;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.PacketDistributor;

public class FemalePlayerImpl extends FemaleEntityImpl implements IFemalePlayer {

	private PlayerPregnancySystemHolder pregnancySystemHolder = new PlayerPregnancySystemHolder();
	private PlayerPregnancyEffectsHolder pregnancyEffectsHolder = new PlayerPregnancyEffectsHolder();
	
	public boolean isPregnancySystemInitialized() {
		return pregnancySystemHolder.isInitialized();
	}
	
	public boolean isPregnancyEfectsInitialized() {
		return pregnancyEffectsHolder.isInitialized();
	}
	
	@Override
	public PlayerPregnancySystemImpl getPregnancySystem() {
		return pregnancySystemHolder.getValue();
	}
	
	@Override
	public PlayerPregnancyEffectsImpl getPregnancyEffects() {
		return pregnancyEffectsHolder.getValue();
	}
	
	@Override
	public CompoundTag serializeNBT() {
		CompoundTag nbt = super.serializeNBT();	
		if (pregnancySystemHolder.isInitialized()) {
			nbt.put("PlayerPregnancySystem", pregnancySystemHolder.serializeNBT());
		}	
		if (pregnancyEffectsHolder.isInitialized()) {
			nbt.put("PlayerPregnancyEffects", pregnancyEffectsHolder.serializeNBT());
		}
		return nbt;
	}
	
	@Override
	public void deserializeNBT(CompoundTag nbt) {
		super.deserializeNBT(nbt);		
		if (nbt.contains("PlayerPregnancySystem", Tag.TAG_COMPOUND)) {
			pregnancySystemHolder.deserializeNBT(nbt.getCompound("PlayerPregnancySystem"));
		}	
		if (nbt.contains("PlayerPregnancyEffects", Tag.TAG_COMPOUND)) {
			pregnancyEffectsHolder.deserializeNBT(nbt.getCompound("PlayerPregnancyEffects"));
		}
	}
	
	public void resetPregnancy() {
		pregnancySystemHolder = new PlayerPregnancySystemHolder();
		pregnancyEffectsHolder = new PlayerPregnancyEffectsHolder();	
	}
	
	public ClientData createClientData() {
		return new ClientData(this.pregnant, this.postPregnancy.orElse(null), this.fertility);
	}
	
	public void sync(ServerPlayer serverPlayer) {
		MinepreggoModPacketHandler.INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> serverPlayer),
				new SyncFemalePlayerDataS2CPacket(serverPlayer.getUUID(), createClientData()));
	}
	
	@OnlyIn(Dist.CLIENT)
	public void updateFromServer(ClientData data) {
		this.pregnant = data.pregnant;
		this.postPregnancy = Optional.ofNullable(data.postPregnancy);
		this.fertility = data.fertility;
	}
	
	public static record ClientData(boolean pregnant, @Nullable PostPregnancy postPregnancy, float fertility) {}
}
