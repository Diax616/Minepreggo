package dev.dixmk.minepreggo.network.capability;

import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.Nullable;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.network.packet.SyncPregnancySystemS2CPacket;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancyPain;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancyPhase;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancySymptom;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancySystemHelper;
import dev.dixmk.minepreggo.world.entity.preggo.Species;
import dev.dixmk.minepreggo.world.entity.preggo.Womb;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraftforge.network.PacketDistributor;

public class PlayerPregnancySystemImpl implements IPlayerPregnancySystemHandler {	
	private int daysToGiveBirth = 0;
	private int daysPassed = 0;
	private int pregnancyHealth = 0;
	private int pregnancyTimer = 0;
	private int pregnancyPainTimer = 0;
	private int numOfJumps = 0;
	private int sprintingTimer = 0;
	
	private Optional<PregnancyPhase> currentPregnancyPhase = Optional.empty();
	private Optional<PregnancyPhase> lastPregnancyPhase = Optional.empty();
	private Optional<PregnancyPain> currentPregnancyPain = Optional.empty();
	private Map<PregnancyPhase, @NonNull Integer> daysByPregnancyPhase = new EnumMap<>(PregnancyPhase.class);
	private byte pregnancySymptomsBitMask = (byte) 0;
	private Womb babiesInsideWomb = Womb.empty();
	
	
	private Set<PregnancySymptom> cachePregnancySymptoms = null;
	
	@Override
	public int getDaysByCurrentStage() {	
		if (currentPregnancyPhase.isPresent() && daysByPregnancyPhase.containsKey(currentPregnancyPhase.get())) {
			return daysByPregnancyPhase.get(currentPregnancyPhase.get());
		}
		MinepreggoMod.LOGGER.error("Could not get total days by pregnancy phase, current pregnancy phase: {}, map: {}", 
				currentPregnancyPhase.isPresent(), daysByPregnancyPhase.isEmpty());
		
		return 0;
	}

	@Override
	public boolean setDaysByStage(int days, PregnancyPhase phase) {
		if (!this.daysByPregnancyPhase.isEmpty()) {	
			daysByPregnancyPhase.put(phase, days);	
			return true;
		}
		return false;
	}
	
	@Override
	public void setDaysByStage(Map<PregnancyPhase, Integer> map) {
		this.daysByPregnancyPhase = map;
	}
	
	@Override
	public Map<PregnancyPhase, Integer> getDaysByStageMapping() {
		return this.daysByPregnancyPhase;
	}
	
	@Override
	public int getPregnancyHealth() {
		return this.pregnancyHealth;
	}

	@Override
	public void setPregnancyHealth(int health) {
		this.pregnancyHealth = Mth.clamp(health, 0, PregnancySystemHelper.MAX_PREGNANCY_HEALTH);
	}

	@Override
	public int getDaysPassed() {
		return this.daysPassed;
	}

	@Override
	public void setDaysPassed(int days) {
		this.daysPassed = Math.max(days, 0);
	}

	@Override
	public int getTotalDaysOfPregnancy() {
		return daysByPregnancyPhase.values().stream()
				.mapToInt(Integer::intValue)
				.sum();
	}
	
	@Override
	public int getDaysToGiveBirth() {
		return this.daysToGiveBirth;
	}

	@Override
	public void setDaysToGiveBirth(int days) {
		this.daysToGiveBirth = Math.max(days, 0);
	}

	@Override
	public void incrementDaysPassed() {
		this.daysPassed++;
	}

	@Override
	public void reduceDaysToGiveBirth() {
		setDaysToGiveBirth(daysToGiveBirth - 1);
	}
	
	@Override
	public int getPregnancyTimer() {
		return this.pregnancyTimer;
	}

	@Override
	public void setPregnancyTimer(int ticks) {
		this.pregnancyTimer = Math.max(ticks, 0);
	}
	
	@Override
	public void incrementPregnancyTimer() {
		++this.pregnancyTimer;
	}
	
	@Override
	public @Nullable PregnancyPhase getLastPregnancyStage() {
		return this.lastPregnancyPhase.orElse(null);
	}

	@Override
	public void setLastPregnancyStage(@Nullable PregnancyPhase stage) {
		this.lastPregnancyPhase = Optional.ofNullable(stage);
	}

	@Override
	public @Nullable PregnancyPhase getCurrentPregnancyStage() {
		return this.currentPregnancyPhase.orElse(null);
	}

	@Override
	public void setCurrentPregnancyStage(@Nullable PregnancyPhase stage) {
		this.currentPregnancyPhase = Optional.ofNullable(stage);
	}

	@Override
	@Nullable
	public PregnancyPain getPregnancyPain() {
		return this.currentPregnancyPain.orElse(null);
	}

	@Override
	public void setPregnancyPain(@Nullable PregnancyPain pain) {
		this.currentPregnancyPain = Optional.ofNullable(pain);
	}

	@Override
	public void resetPregnancyTimer() {
		this.pregnancyTimer = 0;	
	}

	@Override
	public int getPregnancyPainTimer() {
		return this.pregnancyPainTimer;
	}

	@Override
	public void setPregnancyPainTimer(@NonNegative int ticks) {
		this.pregnancyPainTimer = Math.max(ticks, 0);
	}

	@Override
	public void incrementPregnancyPainTimer() {
		++this.pregnancyPainTimer;
	}

	@Override
	public void resetPregnancyPainTimer() {
		this.pregnancyPainTimer = 0;
	}

	@Override
	public void resetDaysPassed() {
		this.daysPassed = 0;	
	}

	@Override
	public void reducePregnancyHealth(int amount) {
		setPregnancyHealth(pregnancyHealth - amount);
	}

	@Override
	public void resetPregnancyHealth() {
		pregnancyHealth = 0;
	}

	@Override
	public void clearPregnancyPain() {
		currentPregnancyPain = Optional.empty();	
	}

	@Override
	public boolean isIncapacitated() {
		return currentPregnancyPain.isPresent();
	}	

	@Override
	public int getNumOfBabiesByType(Species babyType) {
		return (int) this.babiesInsideWomb.stream()
				.filter(babyData -> babyData.typeOfSpecies == babyType)
				.count();
	}
	
	@Override
	public Set<Species> getTypesOfBabies() {
		return this.babiesInsideWomb.stream()
				.map(baby -> baby.typeOfSpecies)
				.collect(Collectors.toUnmodifiableSet());
	}
	
	@Override
	public void setBabiesInsideWomb(@NonNull Womb babiesInsideWomb) {
		this.babiesInsideWomb = babiesInsideWomb;
	}
	
	@Override
	public Womb getBabiesInsideWomb() {
		return this.babiesInsideWomb;
	}
	
	@Override
	public Set<PregnancySymptom> getPregnancySymptoms() {
		if (cachePregnancySymptoms == null) {
			cachePregnancySymptoms = PregnancySymptom.fromBitMask(this.pregnancySymptomsBitMask);
		}	
		return cachePregnancySymptoms;
	}

	@Override
	public boolean addPregnancySymptom(PregnancySymptom symptom) {	
		if ((this.pregnancySymptomsBitMask & symptom.flag) != 0) {
			return false;
		}	
		this.pregnancySymptomsBitMask |= symptom.flag;
		this.cachePregnancySymptoms = null;
		return true;
	}

	@Override
	public void setPregnancySymptoms(Set<PregnancySymptom> symptoms) {
		this.pregnancySymptomsBitMask = PregnancySymptom.toBitMask(symptoms);
		this.cachePregnancySymptoms = null;
	}

	@Override
	public boolean removePregnancySymptom(PregnancySymptom symptom) {	
		if ((this.pregnancySymptomsBitMask & symptom.flag) != 0) {
			this.pregnancySymptomsBitMask &= ~symptom.flag;
			this.cachePregnancySymptoms = null;
			return true;
		}	
		return false;
	}

	@Override
	public void clearPregnancySymptoms() {
		this.pregnancySymptomsBitMask = (byte) 0;
	}
	
	@NonNull
	public Tag serializeNBT(@NonNull Tag tag) {
		CompoundTag nbt = (CompoundTag) tag;
		nbt.putInt("DataDaysToGiveBirth", daysToGiveBirth);
		nbt.putInt("DataDaysPassed", daysPassed);
		nbt.putInt("DataPregnancyHealth", pregnancyHealth);
		nbt.putInt("DataPregnancyTimer", pregnancyTimer);
		
		currentPregnancyPhase.ifPresent(phase -> nbt.putString(PregnancyPhase.CURRENT_PHASE_NBT_KEY, phase.name()));	
		lastPregnancyPhase.ifPresent(phase -> nbt.putString(PregnancyPhase.LAST_PHASE_NBT_KEY, phase.name()));
	
		if (this.pregnancySymptomsBitMask != (byte) 0) {
			nbt.putByte(PregnancySymptom.NBT_KEY, this.pregnancySymptomsBitMask);
		}
	
		currentPregnancyPain.ifPresent(pain -> nbt.putString(PregnancyPain.NBT_KEY, pain.name()));
	
		if(!babiesInsideWomb.isEmpty()) {
			nbt.put("DataBabies", Womb.serializeNBT(this.babiesInsideWomb));
		}
		if (!daysByPregnancyPhase.isEmpty()) {
			nbt.put("DaysByPhase", PregnancySystemHelper.serializePregnancyPhaseMap(this.daysByPregnancyPhase));
		}

		return nbt;
	}

	public void deserializeNBT(@NonNull Tag tag) {
		CompoundTag nbt = (CompoundTag) tag;
		daysToGiveBirth = nbt.getInt("DataDaysToGiveBirth");
		daysPassed = nbt.getInt("DataDaysPassed");
		pregnancyHealth = nbt.getInt("DataPregnancyHealth");
		pregnancyTimer = nbt.getInt("DataPregnancyTimer");
			
	    if (nbt.contains(PregnancyPhase.CURRENT_PHASE_NBT_KEY, Tag.TAG_STRING)) {
	        String name = nbt.getString(PregnancyPhase.CURRENT_PHASE_NBT_KEY);
	        setCurrentPregnancyStage(PregnancyPhase.valueOf(name));
	    }
	    if (nbt.contains(PregnancyPhase.LAST_PHASE_NBT_KEY, Tag.TAG_STRING)) {
	        String name = nbt.getString(PregnancyPhase.LAST_PHASE_NBT_KEY);
	        setLastPregnancyStage(PregnancyPhase.valueOf(name));
	    }
	    if (nbt.contains(PregnancyPain.NBT_KEY, Tag.TAG_STRING)) {
	        String name = nbt.getString(PregnancyPain.NBT_KEY);
	        PregnancyPain pain = PregnancyPain.valueOf(name);
	        setPregnancyPain(pain);
	    }   
	    
	    if (nbt.contains(PregnancySymptom.NBT_KEY, Tag.TAG_BYTE)) {
	    	this.pregnancySymptomsBitMask = nbt.getByte(PregnancySymptom.NBT_KEY);
	    } 	 
	    
	    if (nbt.contains("DataBabies", Tag.TAG_LIST)) {
	    	Womb.deserializeNBT(nbt.getList("DataBabies", Tag.TAG_COMPOUND), babiesInsideWomb);
	    }  	
	    
	    if (nbt.contains("DaysByPhase", Tag.TAG_LIST)) {
	    	PregnancySystemHelper.deserializePregnancyPhaseMap(nbt.getList("DaysByPhase", Tag.TAG_COMPOUND), daysByPregnancyPhase);
	    }  
	}

	public void copyFrom(@NonNull PlayerPregnancySystemImpl newData) {		
		// this is not a COPY, it is a transference
		this.babiesInsideWomb = newData.babiesInsideWomb;
		this.daysByPregnancyPhase = newData.daysByPregnancyPhase;
		
		this.currentPregnancyPain = Optional.ofNullable(newData.currentPregnancyPain.orElse(null));
		this.currentPregnancyPhase = Optional.ofNullable(newData.currentPregnancyPhase.orElse(null));
		
		this.pregnancySymptomsBitMask = newData.pregnancySymptomsBitMask;
		
		this.lastPregnancyPhase = Optional.ofNullable(newData.lastPregnancyPhase.orElse(null));
		this.daysPassed = newData.daysPassed;
		this.daysToGiveBirth = newData.daysToGiveBirth;
		this.numOfJumps = newData.numOfJumps;
		this.pregnancyHealth = newData.pregnancyHealth;
		this.pregnancyPainTimer = newData.pregnancyPainTimer;
		this.pregnancyTimer = newData.pregnancyTimer;
		this.sprintingTimer = newData.sprintingTimer;
	}
	
	@NonNull
	public ClientData createClientData() {
		return new ClientData(
				this.currentPregnancyPhase.orElse(null),
				this.currentPregnancyPain.orElse(null),
				this.pregnancySymptomsBitMask);
	}
	
	public PregnancySystemHelper.PrenatalRegularCheckUpData createRegularCheckUpData() {
		return new PregnancySystemHelper.PrenatalRegularCheckUpData(
				this.currentPregnancyPhase.orElse(PregnancyPhase.P0),
				this.lastPregnancyPhase.orElse(PregnancyPhase.P4),
				this.pregnancyHealth,
				this.babiesInsideWomb.getNumOfBabies(),
				this.daysPassed,
				PregnancySystemHelper.calculateDaysToNextPhase(this),
				this.daysToGiveBirth);	
	}
	
	public PregnancySystemHelper.PrenatalUltrasoundScanData createUltrasoundScanData() {
		return new PregnancySystemHelper.PrenatalUltrasoundScanData(
				Species.HUMAN,
				this.babiesInsideWomb);	
	}
	
	public void sync(ServerPlayer serverPlayer) {
		MinepreggoModPacketHandler.INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> serverPlayer), 
				new SyncPregnancySystemS2CPacket(serverPlayer.getUUID(), createClientData()));
	}
	
	public static record ClientData(@Nullable PregnancyPhase currentPregnancyPhase, @Nullable PregnancyPain pregnancyPain, byte pregnancySymptoms) {
		public void encode(FriendlyByteBuf buffer) {
			buffer.writeBoolean(currentPregnancyPhase != null);
			if (currentPregnancyPhase != null) {
				buffer.writeEnum(currentPregnancyPhase);
			}	
			buffer.writeBoolean(pregnancyPain != null);
			if (pregnancyPain != null) {
				buffer.writeEnum(pregnancyPain);
			}	
			buffer.writeByte(pregnancySymptoms);
		}	
		
		public static ClientData decode(FriendlyByteBuf buffer) {
			byte pregnancySymptom;
			PregnancyPain pregnancyPain = null;
			PregnancyPhase currentPregnancyPhase = null;		
			if (buffer.readBoolean()) {
				currentPregnancyPhase = buffer.readEnum(PregnancyPhase.class);
			}
			if (buffer.readBoolean()) {
				pregnancyPain = buffer.readEnum(PregnancyPain.class);
			}	
			pregnancySymptom = buffer.readByte();
			return new ClientData(currentPregnancyPhase, pregnancyPain, pregnancySymptom);
		}
	}
}
