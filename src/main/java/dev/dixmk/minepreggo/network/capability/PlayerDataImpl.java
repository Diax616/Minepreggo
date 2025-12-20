package dev.dixmk.minepreggo.network.capability;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.network.packet.SyncPlayerDataS2CPacket;
import dev.dixmk.minepreggo.world.pregnancy.AbstractBreedableEntity;
import dev.dixmk.minepreggo.world.pregnancy.Gender;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.network.PacketDistributor;

public class PlayerDataImpl implements IPlayerData {
	private boolean customSkin = true;
	private boolean showMainMenu = true; 
	
	// Sex Event
	private boolean cinematic = false;
	
	private String predefinedSkinName = null;		
	private Gender gender = Gender.UNKNOWN;
	
	private LazyOptional<FemalePlayerImpl> femalePlayerData = LazyOptional.empty();
	private LazyOptional<MalePlayerImpl> malePlayerData = LazyOptional.empty();

	@Override
	public boolean isUsingCustomSkin() {
		return this.customSkin;
	}
	
	@Override
	public void setCustomSkin(boolean value) {
		this.customSkin = value;
	}

	@Override
	public boolean canShowMainMenu() {
		return this.showMainMenu;
	}

	@Override
	public void setShowMainMenu(boolean value) {
		this.showMainMenu = value;
	}
	
	@Override
	public void setGender(Gender newGender) {
        if (this.gender != newGender) {
            invalidateGenderData();
            this.gender = newGender;
            initializeGenderData();
        }
	}

    private void initializeGenderData() {
        switch (this.gender) {
            case FEMALE:
                femalePlayerData = LazyOptional.of(FemalePlayerImpl::new);
                break;
            case MALE:
                malePlayerData = LazyOptional.of(MalePlayerImpl::new);
                break;
            default:
                break;
        }
    }

    private void invalidateGenderData() {
        femalePlayerData.invalidate();
        malePlayerData.invalidate();
        femalePlayerData = LazyOptional.empty();
        malePlayerData = LazyOptional.empty();
    }
	
	@Override
	public Gender getGender() {
		return this.gender;
	}

	@Override
	public boolean isFemale() {
		return this.gender == Gender.FEMALE;
	}

	@Override
	public boolean isMale() {
		return this.gender == Gender.MALE;
	}
	
    /**
     * Get the female player data if gender is FEMALE
     */
	
	@Override
    public LazyOptional<FemalePlayerImpl> getFemaleData() {
        return femalePlayerData;
    }

    /**
     * Get the male player data if gender is MALE
     */
    public LazyOptional<MalePlayerImpl> getMaleData() {
        return malePlayerData;
    }

    public AbstractBreedableEntity getBreedableData() {  
    	
    	AbstractBreedableEntity data = getFemaleData().resolve().orElse(null);
    		
    	if (data == null) {
    		return getMaleData().resolve().orElse(null);
    	}

    	return data;
    }
    
    
	@Override
	public boolean isCinamatic() {
		return this.cinematic;
	}

	@Override
	public void setCinematic(boolean value) {
		this.cinematic = value;
	}
    
	public CompoundTag serializeNBT() {
		CompoundTag nbt = new CompoundTag();
		nbt.putBoolean("DataCustomSkin", customSkin);
		nbt.putBoolean("DataShowMainMenu", showMainMenu);	
		nbt.putString(Gender.NBT_KEY, gender.name());	
	
		if (isFemale()) {			
	        this.femalePlayerData.resolve().ifPresentOrElse(data -> nbt.put("FemalePlayerImpl", data.serializeNBT()), () -> MinepreggoMod.LOGGER.error("Player is female, but female data is not PRESENT"));		
		}
		else if (isMale()) {
	        this.malePlayerData.resolve().ifPresentOrElse(data -> nbt.put("MalePlayerImpl", data.serializeNBT()), () -> MinepreggoMod.LOGGER.error("Player is male, but male data is not PRESENT"));
		}		

		return nbt;
	}
	
	public void deserializeNBT(CompoundTag nbt) {
		customSkin = nbt.getBoolean("DataCustomSkin");
		showMainMenu = nbt.getBoolean("DataShowMainMenu");		
		
	    if (nbt.contains(Gender.NBT_KEY, Tag.TAG_STRING)) {
            Gender loadedGender = Gender.valueOf(nbt.getString(Gender.NBT_KEY));
            setGender(loadedGender);
            
            if (loadedGender == Gender.FEMALE) {
                femalePlayerData.ifPresent(data -> {
                	if (nbt.contains("FemalePlayerImpl", Tag.TAG_COMPOUND)) {
                    	data.deserializeNBT(nbt.getCompound("FemalePlayerImpl"));
                	}
                	else {
                		MinepreggoMod.LOGGER.error("FemalePlayerImpl is not present in nbt");
                	}
                });
            } else if (loadedGender == Gender.MALE) {
            	malePlayerData.ifPresent(data -> {
                	if (nbt.contains("MalePlayerImpl", Tag.TAG_COMPOUND)) {
                    	data.deserializeNBT(nbt.getCompound("MalePlayerImpl"));
                	}
                	else {
                		MinepreggoMod.LOGGER.error("MalePlayerImpl is not present in nbt");
                	}
            	});
            }
	    } 			
	}
	
	public void invalidate() {
		customSkin = true;
		showMainMenu = true; 
		cinematic = false;
		gender = Gender.UNKNOWN;
		predefinedSkinName = null;
		invalidateGenderData();
	}
	
	public void sync(ServerPlayer serverPlayer) {
		MinepreggoModPacketHandler.INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> serverPlayer),
				new SyncPlayerDataS2CPacket(serverPlayer.getUUID(), this.gender,  this.customSkin));
	}
	
	public void syncAllClientData(ServerPlayer serverPlayer) {
		sync(serverPlayer);			
		getFemaleData().ifPresent(cap -> {
			cap.sync(serverPlayer);
			cap.syncLactation(serverPlayer);
			cap.getPregnancySystem().sync(serverPlayer);
			cap.getPregnancyEffects().sync(serverPlayer);
		});
	}
}
