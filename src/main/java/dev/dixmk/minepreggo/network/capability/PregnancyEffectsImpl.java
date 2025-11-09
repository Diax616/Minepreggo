package dev.dixmk.minepreggo.network.capability;

import java.util.Optional;

import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.Nullable;

import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.network.packet.SyncPregnancyEffectsS2CPacket;
import dev.dixmk.minepreggo.world.entity.preggo.Craving;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancySystemConstants;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.item.Item;

import net.minecraftforge.network.PacketDistributor;

public class PregnancyEffectsImpl implements IPregnancyEffectsHandler {
	// Server Data
	private int cravingTimer = 0;
	private int milkingTimer = 0;
	private int bellyRubsTimer = 0;
	private int hornyTimer = 0;
	
	// Client Data
	private int craving = 0;
	private int milking = 0;
	private int bellyRubs = 0;
	private int horny = 0;
	private Optional<Craving> typeOfCraving = Optional.empty();
	
	@Override
	@Nullable
	public Craving getTypeOfCraving() {	
		if (typeOfCraving.isPresent()) {
			return typeOfCraving.get();
		}	
		return null;
	}
	
	@Override
	public void setTypeOfCraving(@Nullable Craving craving) {
		this.typeOfCraving = Optional.ofNullable(craving);
	}
	
	@Override
	public boolean isValidCraving(Item item) {
		return false;
	}
	
	@Override
	public int getCraving() {
		return this.craving;
	}
	
	@Override
	public void setCraving(@NonNegative int craving) {
		this.craving = Mth.clamp(craving, 0, PregnancySystemConstants.MAX_CRAVING_LEVEL);
	}
	
	@Override
	public void incrementCraving() {
		++this.craving;
	}
	
	@Override
	public int getCravingTimer() {
		return this.cravingTimer;
	}
	
	@Override
	public void setCravingTimer(int timer) {
		this.cravingTimer = Math.max(0, timer);
	}
	
	@Override
	public void incrementCravingTimer() {
		++this.cravingTimer;
	}
	
	@Override
	public int getMilking() {
		return this.milking;
	}
	
	@Override
	public void setMilking(@NonNegative int milking) {
		this.milking = Mth.clamp(milking, 0, PregnancySystemConstants.MAX_MILKING_LEVEL);
	}
	
	@Override
	public void incrementMilking() {
		++this.milking;		
	}
	
	@Override
	public int getMilkingTimer() {
		return this.milkingTimer;
	}
	
	@Override
	public void setMilkingTimer(int timer) {
		this.milkingTimer = Math.max(0, timer);
		
	}
	
	@Override
	public void incrementMilkingTimer() {
		++this.milkingTimer;
	}
	
	@Override
	public int getBellyRubs() {
		return this.bellyRubs;
	}
	
	@Override
	public void setBellyRubs(int bellyRubs) {
		this.bellyRubs = Mth.clamp(bellyRubs, 0, PregnancySystemConstants.MAX_BELLY_RUBBING_LEVEL);
	}
	
	@Override
	public void incrementBellyRubs() {
		++this.bellyRubs;
	}
	
	@Override
	public int getBellyRubsTimer() {
		return this.bellyRubsTimer;
	}
	
	@Override
	public void setBellyRubsTimer(int timer) {
		this.bellyRubsTimer = Math.max(0, timer);
	}
	
	@Override
	public void incrementBellyRubsTimer() {
		++this.bellyRubsTimer;
	}
	
	@Override
	public int getHorny() {
		return this.horny;
	}
	
	@Override
	public void setHorny(int horny) {
		this.horny = Mth.clamp(horny, 0, PregnancySystemConstants.MAX_HORNY_LEVEL);
		
	}
	
	@Override
	public void incrementHorny() {
		++this.horny;
	}
	
	@Override
	public int getHornyTimer() {
		return this.hornyTimer;
	}
	
	@Override
	public void setHornyTimer(int timer) {
		this.hornyTimer = Math.max(0, timer);
		
	}
	
	@Override
	public void incrementHornyTimer() {
		++this.hornyTimer;	
	}
	
	@Override
	public void clearTypeOfCraving() {
		this.typeOfCraving = null;	
	}

	@Override
	public void decrementCraving(int amount) {
		setCraving(craving - amount);
	}

	@Override
	public void resetCravingTimer() {
		this.cravingTimer = 0;
	}

	@Override
	public void decrementMilking(int amount) {
		setMilking(this.milking - amount);
	}

	@Override
	public void resetMilkingTimer() {
		this.milkingTimer = 0;
	}

	@Override
	public void decrementBellyRubs(int amount) {
		setBellyRubs(this.bellyRubs - amount);
	}

	@Override
	public void resetBellyRubsTimer() {
		this.bellyRubsTimer = 0;
	}

	@Override
	public void resetHornyTimer() {
		this.hornyTimer = 0;
	}
	
	@NonNull
	public Tag serializeNBT() {
		CompoundTag nbt = new CompoundTag();
		nbt.putInt("DataCraving", craving);
		nbt.putInt("DataCravingTimer", cravingTimer);
		nbt.putInt("DataMilking", milking);
		nbt.putInt("DataMilkingTimer", milkingTimer);
		nbt.putInt("DataBellyRubs", bellyRubs);
		nbt.putInt("DataBellyRubsTimer", bellyRubsTimer);
		nbt.putInt("DataHorny", horny);
		nbt.putInt("DataHornyTimer", hornyTimer);
	
	    if (nbt.contains(Craving.NBT_KEY, Tag.TAG_STRING)) {
	        String name = nbt.getString(Craving.NBT_KEY);
        	Craving t = Craving.valueOf(name);
        	setTypeOfCraving(t);
	    } else {
	        setTypeOfCraving(null);
	    }
	    
		return nbt;
	}
	
	public void deserializeNBT(@NonNull Tag tag) {
		CompoundTag nbt = (CompoundTag) tag;
		craving = nbt.getInt("DataCraving");
		cravingTimer = nbt.getInt("DataCravingTimer");
		milking = nbt.getInt("DataMilking");
		milkingTimer = nbt.getInt("DataMilkingTimer");
		bellyRubs = nbt.getInt("DataBellyRubs");
		bellyRubsTimer = nbt.getInt("DataBellyRubsTimer");
		horny = nbt.getInt("DataHorny");
		hornyTimer = nbt.getInt("DataHornyTimer");
		if (typeOfCraving.isPresent()) {
			nbt.putString(Craving.NBT_KEY, typeOfCraving.get().name());
		}
	}
	
	public void copyFrom(@NonNull PregnancyEffectsImpl newData) {
		this.bellyRubs = newData.bellyRubs;
		this.bellyRubsTimer = newData.bellyRubsTimer;
		this.craving = newData.craving;
		this.cravingTimer = newData.cravingTimer;
		this.horny = newData.horny;
		this.hornyTimer = newData.hornyTimer;
		this.milking = newData.milking;
		this.milkingTimer = newData.milkingTimer;
		this.typeOfCraving = newData.typeOfCraving;
	}
	
	public void sync(ServerPlayer serverPlayer) {
		MinepreggoModPacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> serverPlayer), 
				new SyncPregnancyEffectsS2CPacket(serverPlayer.getUUID(), this.craving, this.milking, this.bellyRubs, this.horny, getTypeOfCraving()));
	}
}
