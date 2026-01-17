package dev.dixmk.minepreggo.network.capability;

import dev.dixmk.minepreggo.MinepreggoMod;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.Lazy;

public class PlayerPregnancyDataHolder implements INBTSerializable<CompoundTag> {
    private CompoundTag savedData;   
    private Lazy<PlayerPregnancyDataImpl> lazyValue;
    private boolean isInitialized = false;
	
	public PlayerPregnancyDataHolder() {
		this.savedData = new CompoundTag();
		this.lazyValue = createLazy();
	}
	
	private Lazy<PlayerPregnancyDataImpl> createLazy() {
		return Lazy.of(() -> {
			MinepreggoMod.LOGGER.debug("PlayerPregnancySystemImpl was initialized");
			isInitialized = true;
			PlayerPregnancyDataImpl system = new PlayerPregnancyDataImpl();
			if (savedData.contains(PlayerPregnancyDataImpl.NBT_KEY, Tag.TAG_COMPOUND)) {
				system.deserializeNBT(savedData);
			}
			return system;
		});
	}
	
	public PlayerPregnancyDataImpl getValue() {
		return lazyValue.get();
	}
	
	public boolean isInitialized() {
		return isInitialized;
	}
	
	public void reset() {
		this.isInitialized = false;
		this.savedData = new CompoundTag();
		this.lazyValue = createLazy();
	}
	
	@Override
	public CompoundTag serializeNBT() {
        CompoundTag tag;
        if (isInitialized) {
            tag = lazyValue.get().serializeNBT();
        } else if (savedData.contains(PlayerPregnancyDataImpl.NBT_KEY)) {
            tag = savedData.copy();
        } else {
            tag = new CompoundTag();
        }
        tag.putBoolean("PregnancySystemInitialized", isInitialized);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        this.savedData = nbt.copy();
        this.isInitialized = nbt.getBoolean("PregnancySystemInitialized");
        this.lazyValue = createLazy();
    }
}