package dev.dixmk.minepreggo.network.capability;

import dev.dixmk.minepreggo.MinepreggoMod;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.Lazy;

public class PlayerPregnancySystemHolder implements INBTSerializable<CompoundTag> {
    private CompoundTag savedData;   
    private Lazy<PlayerPregnancySystemImpl> lazyValue;
    private boolean isInitialized = false;
	
	public PlayerPregnancySystemHolder() {
		this.savedData = new CompoundTag();
		this.lazyValue = createLazy();
	}
	
	private Lazy<PlayerPregnancySystemImpl> createLazy() {
		return Lazy.of(() -> {
			MinepreggoMod.LOGGER.debug("PlayerPregnancySystemImpl was initialized");
			isInitialized = true;
			PlayerPregnancySystemImpl system = new PlayerPregnancySystemImpl();
			if (savedData.contains(PlayerPregnancySystemImpl.NBT_KEY, Tag.TAG_COMPOUND)) {
				system.deserializeNBT(savedData);
			}
			return system;
		});
	}
	
	public PlayerPregnancySystemImpl getValue() {
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
        } else if (savedData.contains(PlayerPregnancySystemImpl.NBT_KEY)) {
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