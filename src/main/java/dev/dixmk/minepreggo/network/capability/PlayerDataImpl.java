package dev.dixmk.minepreggo.network.capability;

import org.checkerframework.checker.nullness.qual.NonNull;

import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.network.packet.SyncPlayerDataS2CPacket;
import dev.dixmk.minepreggo.world.pregnancy.Gender;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.network.PacketDistributor;

public class PlayerDataImpl implements IPlayerData {
	private boolean customSkin = true;
	private boolean showMainMenu = true; 
	private boolean cinematic = false;
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

	@Override
	public boolean isCinamatic() {
		return this.cinematic;
	}

	@Override
	public void setCinematic(boolean value) {
		this.cinematic = value;
	}
    
	@NonNull
	public Tag serializeNBT() {
		CompoundTag nbt = new CompoundTag();
		nbt.putBoolean("DataCustomSkin", customSkin);
		nbt.putBoolean("DataShowMainMenu", showMainMenu);			
		nbt.putString(Gender.NBT_KEY, gender.name());	
		
		if (isFemale()) {			
	        this.femalePlayerData.resolve().ifPresent(data -> data.serializeNBT(nbt));		
		}
		else if (isMale()) {
	        this.malePlayerData.resolve().ifPresent(data -> data.serializeNBT(nbt));
		}		

		return nbt;
	}
	
	public void deserializeNBT(@NonNull Tag tag) {
		CompoundTag nbt = (CompoundTag) tag;
		customSkin = nbt.getBoolean("DataCustomSkin");
		showMainMenu = nbt.getBoolean("DataShowMainMenu");
		
	    if (nbt.contains(Gender.NBT_KEY, Tag.TAG_STRING)) {
            Gender loadedGender = Gender.valueOf(nbt.getString(Gender.NBT_KEY));
            setGender(loadedGender);
            
            if (loadedGender == Gender.FEMALE) {
                femalePlayerData.ifPresent(data -> 
                    data.deserializeNBT(nbt)
                );
            } else if (loadedGender == Gender.MALE) {
                malePlayerData.ifPresent(data -> 
                    data.deserializeNBT(nbt)
                );
            }
	    } 			
	}
	
	public void sync(ServerPlayer serverPlayer) {
		MinepreggoModPacketHandler.INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> serverPlayer),
				new SyncPlayerDataS2CPacket(serverPlayer.getUUID(), this.gender,  this.customSkin));
	}
	
	public void syncAllClientData(ServerPlayer serverPlayer) {
		sync(serverPlayer);			
		getFemaleData().ifPresent(cap -> {
			cap.sync(serverPlayer);
			cap.getPregnancySystem().sync(serverPlayer);
			cap.getPregnancyEffects().sync(serverPlayer);
		});
	}

	public void syncNeededClientData(ServerPlayer serverPlayer) {
		sync(serverPlayer);			
		getFemaleData().ifPresent(cap -> {
			cap.sync(serverPlayer);
			cap.getPregnancySystem().sync(serverPlayer);
		});
	}
}
