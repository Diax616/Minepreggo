package dev.dixmk.minepreggo.network.capability;

import java.util.Optional;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.network.packet.s2c.SyncPlayerDataS2CPacket;
import dev.dixmk.minepreggo.world.entity.player.SkinType;
import dev.dixmk.minepreggo.world.pregnancy.AbstractBreedableEntity;
import dev.dixmk.minepreggo.world.pregnancy.Gender;
import dev.dixmk.minepreggo.world.pregnancy.PostPregnancy;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.network.PacketDistributor;

public class PlayerDataImpl implements IPlayerData {
	private boolean showMainMenu = true; 
	private boolean cinematic = false;
	
	private Gender gender = Gender.UNKNOWN;
	private SkinType skinType = SkinType.CUSTOM;
	
	private LazyOptional<FemalePlayerImpl> femalePlayerData = LazyOptional.empty();
	private LazyOptional<MalePlayerImpl> malePlayerData = LazyOptional.empty();
		
	@Override
	public SkinType getSkinType() {
		return skinType;
	}

	@Override
	public void setSKinType(SkinType skinType) {
		this.skinType = skinType;	
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

    public Optional<AbstractBreedableEntity> getBreedableData() {  
    	if (getGender() == Gender.FEMALE) {
    		var data = getFemaleData().resolve().orElse(null);  		
    		if (data == null) {
    			MinepreggoMod.LOGGER.error("Player is female, but female data is not PRESENT");
    		}		
    		return Optional.ofNullable(data);
    	}    	else if (getGender() == Gender.MALE) {
    		var data = getMaleData().resolve().orElse(null);  		
    		if (data == null) {
    			MinepreggoMod.LOGGER.error("Player is male, but male data is not PRESENT");
    		}	
    		return Optional.ofNullable(data);
    	}
    	

    	
    	
    	return Optional.empty();
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
		nbt.putString("DataSkinType", skinType.name());
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
		skinType = SkinType.valueOf(nbt.getString("DataSkinType"));
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
                		invalidate();
						throw new IllegalStateException("FemalePlayerImpl is not present in nbt");
                	}
                });
            } else if (loadedGender == Gender.MALE) {
            	malePlayerData.ifPresent(data -> {
                	if (nbt.contains("MalePlayerImpl", Tag.TAG_COMPOUND)) {
                    	data.deserializeNBT(nbt.getCompound("MalePlayerImpl"));            	
                	}
                	else {
                		invalidate();
                		throw new IllegalStateException("MalePlayerImpl is not present in nbt");
                	}
            	});
            }
	    } 			
	}
	
	public void invalidate() {
		skinType = SkinType.CUSTOM;
		showMainMenu = true; 
		cinematic = false;
		gender = Gender.UNKNOWN;
		invalidateGenderData();
	}
	
	public void sync(ServerPlayer serverPlayer) {
		MinepreggoModPacketHandler.INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> serverPlayer),
				new SyncPlayerDataS2CPacket(serverPlayer.getUUID(), this.gender,  this.skinType));
	}
	
	public void syncAllClientData(ServerPlayer serverPlayer) {
		sync(serverPlayer);			
		getFemaleData().ifPresent(cap -> {
			cap.sync(serverPlayer);		
			
			if (cap.isPregnant() && cap.isPregnancyDataInitialized()) {
				var pregnancyData = cap.getPregnancyData();
				pregnancyData.syncState(serverPlayer);
				pregnancyData.syncEffect(serverPlayer);
			}
			else if (cap.getPostPregnancyData().isPresent() && cap.getPostPregnancyData().get().getPostPregnancy() == PostPregnancy.PARTUM) {
				cap.syncLactation(serverPlayer);
			}
			
		});
	}
}