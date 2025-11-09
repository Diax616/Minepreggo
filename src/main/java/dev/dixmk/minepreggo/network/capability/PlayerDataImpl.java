package dev.dixmk.minepreggo.network.capability;

import java.util.Optional;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.Nullable;

import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.network.packet.SyncPlayerDataS2CPacket;
import dev.dixmk.minepreggo.world.entity.preggo.PostPregnancy;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraftforge.network.PacketDistributor;

public class PlayerDataImpl implements IPlayerData {
	// Client Data
	private Gender gender = Gender.FEMALE;
	private boolean customSkin = true;
	private boolean pregnant = false;
	private boolean showMainMenu = true;
	
	// Server Data
	private float fertility = 0;
	private int fertilityRateTimer = 0;
	private int pregnancyInitializerTimer = 0;
		
	Optional<PostPregnancy> postPregnancy = Optional.empty();
	
	
	@Override
	public void setGender(Gender gender) {
		this.gender = gender;
	}

	@Override
	public Gender getGender() {
		return this.gender;
	}

	@Override
	public boolean canGetPregnant() {
		return this.gender == Gender.FEMALE;
	}

	@Override
	public boolean isUsingCustomSkin() {
		return this.customSkin;
	}
	
	@Override
	public void setCustomSkin(boolean value) {
		this.customSkin = value;
	}
	
	@Override
	public boolean isPregnant() {
		return this.pregnant;
	}

	@Override
	public float getFertilityRate() {
		return this.fertility;
	}

	@Override
	public void setFertilityRate(float percentage) {
		this.fertility = Mth.clamp(percentage, 0, 1F);
	}

	@Override
	public void incrementFertilityRate(float amount) {
		this.fertility = Mth.clamp(this.fertility + amount, 0, 1F);
	}

	@Override
	public void incrementFertilityRateTimer() {
		++this.pregnancyInitializerTimer;
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
	public void setPregnant(boolean value) {
		this.pregnant = value;
	}
	
	@Override
	public int getPregnancyInitializerTimer() {
		return this.pregnancyInitializerTimer;
	}

	@Override
	public void setPregnancyInitializerTimer(int ticks) {
		this.pregnancyInitializerTimer = Math.max(0, ticks);
		
	}

	@Override
	public void incrementPregnancyInitializerTimer() {
		++this.pregnancyInitializerTimer;
		
	}

	@Override
	public int getFertilityRateTimer() {
		return this.fertilityRateTimer;
	}

	@Override
	public void setFertilityRateTimer(int ticks) {
		this.fertilityRateTimer = Math.max(0, ticks);
		
	}

	@Override
	public void impregnate() {
		this.pregnant = true;
	}

	@Override
	public @Nullable PostPregnancy getPostPregnancyPhase() {
		if (this.postPregnancy.isPresent()) {
			return this.postPregnancy.get();
		}
		return null;
	}

	@Override
	public boolean tryActivatePostPregnancyPhase(@NonNull PostPregnancy postPregnancy) {
		if (pregnant) {
			this.postPregnancy = Optional.of(postPregnancy);
			this.pregnant = false;
			return true;
		}
		return false;
	}
	
	
	@NonNull
	public Tag serializeNBT() {
		CompoundTag nbt = new CompoundTag();
		nbt.putInt("DataGender", gender.ordinal());
		nbt.putInt("DataPregnancyInitializerTimer", pregnancyInitializerTimer);
		nbt.putFloat("DataFertility", fertility);
		nbt.putBoolean("DataCustomSkin", customSkin);
		nbt.putBoolean("DataPregnant", pregnant);
		nbt.putBoolean("DataShowMainMenu", showMainMenu);
		
		if (postPregnancy.isPresent()) {
			nbt.putString(PostPregnancy.NBT_KEY, postPregnancy.get().name());
		}
		
		return nbt;
	}
	
	public void deserializeNBT(@NonNull Tag tag) {
		CompoundTag nbt = (CompoundTag) tag;
		pregnancyInitializerTimer = nbt.getInt("DataPregnancyInitializerTimer");
		gender = Gender.values()[nbt.getInt("DataGender")];
		fertility = nbt.getFloat("DataFertility");
		customSkin = nbt.getBoolean("DataCustomSkin");
		pregnant = nbt.getBoolean("DataPregnant");
		showMainMenu = nbt.getBoolean("DataShowMainMenu");
		
	    if (nbt.contains(PostPregnancy.NBT_KEY, Tag.TAG_STRING)) {
	        String name = nbt.getString(PostPregnancy.NBT_KEY);
	        postPregnancy = Optional.of(PostPregnancy.valueOf(name));
	    } else {
	    	postPregnancy = Optional.empty();
	    }
	}
	
	public void sync(ServerPlayer serverPlayer) {
		MinepreggoModPacketHandler.INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> serverPlayer),
				new SyncPlayerDataS2CPacket(serverPlayer.getUUID(), this.gender,  this.customSkin));
	}
	
	public void copyFrom(@NonNull PlayerDataImpl newData) {
		this.customSkin = newData.customSkin;
		this.pregnant = newData.pregnant;
		this.pregnancyInitializerTimer = newData.pregnancyInitializerTimer;
		this.fertility = newData.fertility;
		this.gender = newData.gender;
		this.showMainMenu = newData.showMainMenu;
	}
}
