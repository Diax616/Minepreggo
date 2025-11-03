package dev.dixmk.minepreggo.network.capability;

import org.checkerframework.checker.nullness.qual.NonNull;

import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.network.packet.SyncPlayerDataS2CPacket;
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
	private int pregnancyInitializerTimer = 0;
		
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
	public float getFertilityPercentage() {
		return this.fertility;
	}

	@Override
	public void setFertilityPercentage(float percentage) {
		this.fertility = Mth.clamp(percentage, 0, 1F);
	}

	@Override
	public void incrementFertilityPercentage() {
		this.fertility = Math.min(this.fertility + 0.05F, 1F);
	}

	@Override
	public void reduceFertilityPercentage() {
		this.fertility = Math.max(this.fertility - 0.05F, 0F);	
	}

	@Override
	public void incrementFertilityTimer() {
		++this.pregnancyInitializerTimer;
	}

	@Override
	public void startPregnancy() {

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
	
	@NonNull
	public Tag serializeNBT() {
		CompoundTag nbt = new CompoundTag();
		nbt.putInt("DataGender", gender.ordinal());
		nbt.putInt("DataPregnancyInitializerTimer", pregnancyInitializerTimer);
		nbt.putFloat("DataFertility", fertility);
		nbt.putBoolean("DataCustomSkin", customSkin);
		nbt.putBoolean("DataPregnant", pregnant);
		nbt.putBoolean("DataShowMainMenu", showMainMenu);
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
