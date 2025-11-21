package dev.dixmk.minepreggo.network.capability;

import java.util.Optional;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.Nullable;

import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.network.packet.SyncFemalePlayerDataS2CPacket;
import dev.dixmk.minepreggo.world.entity.preggo.PostPregnancy;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.PacketDistributor;

public class FemalePlayerImpl extends FemaleEntityImpl {

	private final PlayerPregnancySystemImpl pregnancySystem = new PlayerPregnancySystemImpl();
	private final PlayerPregnancyEffectsImpl pregnancyEffects = new PlayerPregnancyEffectsImpl();
	
	public PlayerPregnancySystemImpl getPregnancySystem() {
		return pregnancySystem;
	}
	
	public PlayerPregnancyEffectsImpl getPregnancyEffects() {
		return pregnancyEffects;
	}
	
	@Override
	public void serializeNBT(@NonNull Tag tag) {
		super.serializeNBT(tag);
		CompoundTag nbt = (CompoundTag) tag;
		pregnancySystem.serializeNBT(nbt);
		pregnancyEffects.serializeNBT(nbt);
	}
	
	@Override
	public void deserializeNBT(@NonNull Tag tag) {
		super.deserializeNBT(tag);
		pregnancySystem.deserializeNBT(tag);
		pregnancyEffects.deserializeNBT(tag);
	}
	
	public void copyFrom(@NonNull FemalePlayerImpl target) {
	    CompoundTag nbt = new CompoundTag();
	    target.serializeNBT(nbt);
	    this.deserializeNBT(nbt);
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
