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
	
	@Override
	public CompoundTag serializeNBT() {
        if (isInitialized) {  	
            return lazyValue.get().serializeNBT();
        }   
        else if (savedData.contains(PlayerPregnancySystemImpl.NBT_KEY)) {
            return savedData;
        }       
        return new CompoundTag();
	}
	
	@Override
	public void deserializeNBT(CompoundTag nbt) {
        this.savedData = nbt.copy();
        this.isInitialized = false;
        this.lazyValue = createLazy();		
	}
}
