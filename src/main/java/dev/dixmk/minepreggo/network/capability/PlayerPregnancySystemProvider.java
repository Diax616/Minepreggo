package dev.dixmk.minepreggo.network.capability;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import net.minecraft.core.Direction;
import net.minecraft.nbt.Tag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class PlayerPregnancySystemProvider implements ICapabilitySerializable<Tag> {

	private final PlayerPregnancySystemImpl pregnancySystem = new PlayerPregnancySystemImpl();
	private final LazyOptional<PlayerPregnancySystemImpl> instance = LazyOptional.of(() -> pregnancySystem);
	
	@Override
	public <T> @NotNull LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == MinepreggoCapabilities.PLAYER_PREGNANCY_SYSTEM) {
            return instance.cast();
        }
        return LazyOptional.empty();	
	}

	@Override
	public Tag serializeNBT() {
		return this.pregnancySystem.serializeNBT();
	}

	@Override
	public void deserializeNBT(Tag nbt) {
		this.pregnancySystem.deserializeNBT(nbt);
	}
}
