package dev.dixmk.minepreggo.network.capability;

import org.checkerframework.checker.nullness.qual.NonNull;

import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.network.packet.s2c.SyncEnderPowerDataS2CPacket;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.network.PacketDistributor;

public class EnderPowerDataImpl implements IEnderPowerData, INBTSerializable<Tag> {
	private int enderPowerTimer = 0;
	private int enderPowerLevel = 0;
	
	@Override
	public int getEnderPowerTimer() {
		return enderPowerTimer;
	}

	@Override
	public void setEnderPowerTimer(int timer) {
		this.enderPowerTimer = Math.max(0, timer);
	}

	@Override
	public void incrementEnderPowerTimer() {
		this.enderPowerTimer++;
	}

	@Override
	public void resetEnderPowerTimer() {
		this.enderPowerTimer = 0;
	}

	@Override
	public int getEnderPowerLevel() {
		return enderPowerLevel;
	}

	@Override
	public void setEnderPowerLevel(int level) {
		this.enderPowerLevel = Mth.clamp(level, 0, MAX_ENDER_POWER_LEVEL);
	}

	@Override
	public void incrementEnderPowerLevel(int amount) {
		setEnderPowerLevel(this.enderPowerLevel + amount);	
	}

	@Override
	public void decrementEnderPowerLevel(int amount) {
		setEnderPowerLevel(this.enderPowerLevel - Math.max(0, amount));	
	}
	
	@Override
	public @NonNull Tag serializeNBT() {
		CompoundTag nbt = new CompoundTag();
		nbt.putInt("enderPowerTimer", enderPowerTimer);
		nbt.putInt("enderPowerLevel", enderPowerLevel);
		return nbt;	
	}
	
	@Override
	public void deserializeNBT(@NonNull Tag tag) {
		CompoundTag nbt = (CompoundTag) tag;
		this.enderPowerTimer = nbt.getInt("enderPowerTimer");
		this.enderPowerLevel = nbt.getInt("enderPowerLevel");
	}
	
	public void sync(ServerPlayer serverPlayer) {
		MinepreggoModPacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> serverPlayer),
				new SyncEnderPowerDataS2CPacket(serverPlayer.getUUID(), this.enderPowerLevel));
	}

	@Override
	public void reset() {
		this.enderPowerTimer = 0;
		this.enderPowerLevel = 0;	
	}
}
