package dev.dixmk.minepreggo.network.capability;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class PlayerDataProvider implements ICapabilitySerializable<Tag> {
	private final PlayerDataImpl playerData = new PlayerDataImpl();
	private final LazyOptional<PlayerDataImpl> instance = LazyOptional.of(() -> playerData);
	
	@Override
	public <T> @NotNull LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == MinepreggoCapabilities.PLAYER_DATA) {
            return instance.cast();
        }
        return LazyOptional.empty();
	}

	@Override
	public Tag serializeNBT() {
		return playerData.serializeNBT();
	}

	@Override
	public void deserializeNBT(Tag nbt) {
		playerData.deserializeNBT((CompoundTag) nbt);
	}
}
