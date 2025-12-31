package dev.dixmk.minepreggo.network.capability;

import dev.dixmk.minepreggo.MinepreggoMod;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.Lazy;

public class PlayerPregnancyEffectsHolder implements INBTSerializable<CompoundTag> {
    private CompoundTag savedData;   
    private Lazy<PlayerPregnancyEffectsImpl> lazyValue;
    private boolean isInitialized = false;
	
	public PlayerPregnancyEffectsHolder() {
		this.savedData = new CompoundTag();
		this.lazyValue = createLazy();
	}
	
	private Lazy<PlayerPregnancyEffectsImpl> createLazy() {
		return Lazy.of(() -> {
			isInitialized = true;
			MinepreggoMod.LOGGER.debug("PlayerPregnancyEffectsImpl was initialized");
			PlayerPregnancyEffectsImpl effects = new PlayerPregnancyEffectsImpl();
			if (savedData.contains(PlayerPregnancyEffectsImpl.NBT_KEY, Tag.TAG_COMPOUND)) {
				effects.deserializeNBT(savedData);
			}
			return effects;
		});
	}
	
	public PlayerPregnancyEffectsImpl getValue() {
		return lazyValue.get();
	}
	
	public boolean isInitialized() {
		return isInitialized;
	}
	
	@Override
	public CompoundTag serializeNBT() {
		CompoundTag tag;
        if (isInitialized) {  	
            return lazyValue.get().serializeNBT();
        }   
        else if (savedData.contains(PlayerPregnancyEffectsImpl.NBT_KEY)) {
            return savedData;
        }   
        else {
            tag = new CompoundTag();
        }
        tag.putBoolean("PregnancySystemInitialized", isInitialized);
        return new CompoundTag();
	}
	
	@Override
	public void deserializeNBT(CompoundTag nbt) {
        this.savedData = nbt.copy();
        this.isInitialized = nbt.getBoolean("PregnancySystemInitialized");
        this.lazyValue = createLazy();		
	}
}