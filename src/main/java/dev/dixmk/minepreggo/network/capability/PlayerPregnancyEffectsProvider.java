package dev.dixmk.minepreggo.network.capability;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import net.minecraft.core.Direction;
import net.minecraft.nbt.Tag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class PlayerPregnancyEffectsProvider implements ICapabilitySerializable<Tag> {
	private final PlayerPregnancyEffectsImpl pregnancyEffects = new PlayerPregnancyEffectsImpl();
	private final LazyOptional<PlayerPregnancyEffectsImpl> instance = LazyOptional.of(() -> pregnancyEffects);
	
	@Override
	public <T> @NotNull LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == MinepreggoCapabilities.PLAYER_PREGNANCY_EFFECTS) {
            return instance.cast();
        }
        return LazyOptional.empty();	
	}

	@Override
	public Tag serializeNBT() {
		return this.pregnancyEffects.serializeNBT();
	}

	@Override
	public void deserializeNBT(Tag nbt) {
		this.pregnancyEffects.deserializeNBT(nbt);
	}
}
