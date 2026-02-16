package dev.dixmk.minepreggo.network.capability;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import net.minecraft.core.Direction;
import net.minecraft.nbt.Tag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class EnderPowerDataProvider implements ICapabilitySerializable<Tag> {
	private final EnderPowerDataImpl enderPowerDataImpl = new EnderPowerDataImpl();
	private final LazyOptional<EnderPowerDataImpl> instance = LazyOptional.of(() -> enderPowerDataImpl);
	
	@Override
	public <T> @NotNull LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == MinepreggoCapabilities.ENDER_POWER_DATA) {
            return instance.cast();
        }
        return LazyOptional.empty();
	}

	@Override
	public Tag serializeNBT() {
		return enderPowerDataImpl.serializeNBT();
	}

	@Override
	public void deserializeNBT(Tag nbt) {
		enderPowerDataImpl.deserializeNBT(nbt);
	}
}
