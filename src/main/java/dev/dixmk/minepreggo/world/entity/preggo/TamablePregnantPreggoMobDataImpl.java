package dev.dixmk.minepreggo.world.entity.preggo;

import java.util.Optional;

import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.Nullable;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.init.BirthClientData;
import dev.dixmk.minepreggo.init.MinepreggoEntityDataSerializers;
import dev.dixmk.minepreggo.world.pregnancy.BirthData;
import dev.dixmk.minepreggo.world.pregnancy.Craving;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhaseMap;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPainInstance;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import dev.dixmk.minepreggo.world.pregnancy.SyncedPregnancySymptomSet;
import dev.dixmk.minepreggo.world.pregnancy.Womb;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
import net.minecraft.world.item.Item;

public class TamablePregnantPreggoMobDataImpl<E extends PreggoMob & ITamablePregnantPreggoMob> implements ITamablePregnantPreggoMobData {

    public static class DataAccessor<E extends PreggoMob & ITamablePregnantPreggoMob> {
    	private final EntityDataAccessor<Integer> cravingData;
    	private final EntityDataAccessor<Integer> lactationData;
    	private final EntityDataAccessor<Integer> bellyRubsData;
    	private final EntityDataAccessor<Integer> hornyData;   	
    	private final EntityDataAccessor<Byte> pregnancySymptomsData;
    	private final EntityDataAccessor<Optional<PregnancyPainInstance>> pregnancyPainData;
    	private final EntityDataAccessor<Optional<Craving>> cravingChosenData;
    	private final EntityDataAccessor<Optional<BirthClientData>> birthClientData;
    	
        public DataAccessor(Class<E> entityClass) {
        	cravingData = SynchedEntityData.defineId(entityClass, EntityDataSerializers.INT);
        	lactationData = SynchedEntityData.defineId(entityClass, EntityDataSerializers.INT);
        	bellyRubsData = SynchedEntityData.defineId(entityClass, EntityDataSerializers.INT);
        	hornyData = SynchedEntityData.defineId(entityClass, EntityDataSerializers.INT);
        	pregnancySymptomsData = SynchedEntityData.defineId(entityClass, EntityDataSerializers.BYTE);
        	pregnancyPainData = SynchedEntityData.defineId(entityClass, MinepreggoEntityDataSerializers.OPTIONAL_PREGNANCY_PAIN_INSTANCE);
        	cravingChosenData = SynchedEntityData.defineId(entityClass, MinepreggoEntityDataSerializers.OPTIONAL_CRAVING);           
        	birthClientData = SynchedEntityData.defineId(entityClass, MinepreggoEntityDataSerializers.OPTIONAL_BIRTH_CLIENT_DATA);           
        }  
        
    	public void defineSynchedData(E preggomob) {
    		SynchedEntityData entityData = preggomob.getEntityData();
    		entityData.define(cravingData, 0);
    		entityData.define(lactationData, 0);
    		entityData.define(bellyRubsData, 0);
    		entityData.define(hornyData, 0);
    		entityData.define(pregnancySymptomsData, (byte) 0);
    		entityData.define(pregnancyPainData, Optional.empty());
    		entityData.define(cravingChosenData, Optional.empty());
    		entityData.define(birthClientData, Optional.empty());
    	}
    	
    	public EntityDataAccessor<Integer> getDataCraving() {
			return cravingData;
		}
    	
		public EntityDataAccessor<Integer> getDataLactation() {
			return lactationData;
		}
		
		public EntityDataAccessor<Integer> getDataBellyRubs() {
			return bellyRubsData;
		}
		
		public EntityDataAccessor<Integer> getDataHorny() {
			return hornyData;
		}
		
		public EntityDataAccessor<Byte> getDataPregnancySymptom() {
			return pregnancySymptomsData;
		}
		
		public EntityDataAccessor<Optional<PregnancyPainInstance>> getDataPregnancyPain() {
			return pregnancyPainData;
		}
		
		public EntityDataAccessor<Optional<Craving>> getDataCravingChosen() {
			return cravingChosenData;
		}
		
		public EntityDataAccessor<Optional<BirthClientData>> getDatabirth() {
			return birthClientData;
		}
    }
		
	private int pregnancyHealth = PregnancySystemHelper.MAX_PREGNANCY_HEALTH; 
	private int pregnancyHealthTimer = 0;
	private int daysPassed = 0;
	private int daysToGiveBirth = 0;	
	private int pregnancyTimer = 0;
	private int cravingTimer = 0;
	private int milkingTimer = 0;
	private int bellyRubsTimer = 0;
	private int hornyTimer = 0;
	private final PregnancyPhase currentPregnancyPhase;
	private PregnancyPhase lastPregnancyPhase;	
	private PregnancyPhaseMap daysByPregnancyPhase = null;
	private SyncedPregnancySymptomSet syncedPregnancySymptomsSet = null;
	private Womb babiesInsideWomb = null; 
	private Optional<BirthData> birthData = Optional.empty();
	private final DataAccessor<E> dataAccessor;
	private final E preggoMob;
	
	public TamablePregnantPreggoMobDataImpl(DataAccessor<E> dataAccessor, E preggoMob, PregnancyPhase currentPregnancyPhase) {	
		this.dataAccessor = dataAccessor;
		this.currentPregnancyPhase = currentPregnancyPhase;
		this.lastPregnancyPhase = PregnancySystemHelper.calculateMinPhaseToGiveBirth(currentPregnancyPhase);
		this.preggoMob = preggoMob;
		this.syncedPregnancySymptomsSet = new SyncedPregnancySymptomSet(preggoMob, this.dataAccessor.pregnancySymptomsData);
	}	
    	
	@Override
	public int getDaysByCurrentStage() {	
		if (daysByPregnancyPhase.containsPregnancyPhase(currentPregnancyPhase)) {
			return daysByPregnancyPhase.getDaysByPregnancyPhase(currentPregnancyPhase);
		}
		MinepreggoMod.LOGGER.error("Could not get total days by pregnancy phase, current pregnancy phase: {}, map: {}", 
				currentPregnancyPhase, daysByPregnancyPhase.isEmpty());	
		return 0;
	}

	@Override
	public PregnancyPhaseMap getMapPregnancyPhase() {
		return this.daysByPregnancyPhase;
	}
	
	@Override
	public boolean setDaysByStage(int days, PregnancyPhase phase) {
		return daysByPregnancyPhase.modifyDaysByPregnancyPhase(phase, days);
	}	
	@Override
	public void setMapPregnancyPhase(PregnancyPhaseMap map) {
		this.daysByPregnancyPhase = map;
	}
	
	@Override
	public int getTotalDaysOfPregnancy() {
		return daysByPregnancyPhase.getDaysValues().stream()
				.mapToInt(Integer::intValue)
				.sum();
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
	public void incrementPregnancyHealth(int amount) {
		setPregnancyHealth(this.pregnancyHealth + amount);	
	}
	
	@Override
	public int getDaysPassed() {
		return this.daysPassed;
	}
	
	@Override
	public void setDaysPassed(int days) {
		this.daysPassed = Math.max(0, days);
	}
	
	@Override
	public int getDaysToGiveBirth() {
		return this.daysToGiveBirth;
	}
	
	@Override
	public void setDaysToGiveBirth(int days) {
		this.daysToGiveBirth = Math.max(0, days);
	}
	
	@Override
	@Nullable
	public PregnancyPainInstance getPregnancyPain() {
		return this.preggoMob.getEntityData().get(this.dataAccessor.pregnancyPainData).orElse(null);	
	}
	
	@Override
	public void setPregnancyPain(@Nullable PregnancyPainInstance symptom) {
		this.preggoMob.getEntityData().set(this.dataAccessor.pregnancyPainData, Optional.ofNullable(symptom));
	}
	
	@Override
	public int getPregnancyTimer() {
	    return this.pregnancyTimer;
	}
	
	@Override
	public void setPregnancyTimer(int ticks) {
	    this.pregnancyTimer = ticks;
	}
	
	@Override
	public PregnancyPhase getLastPregnancyStage() {
		return this.lastPregnancyPhase;
	}
	
	@Override
	public void setLastPregnancyStage(PregnancyPhase stage) {
		this.lastPregnancyPhase = stage;
	}
	
	@Override
	public boolean isIncapacitated() {	
		final var pain = this.getPregnancyPain();
		return pain != null && pain.getPain().incapacitate;
	}
	
	@Override
	public void incrementPregnancyTimer() {
		this.pregnancyTimer++;	
	}

	@Override
	public void resetPregnancyTimer() {
		this.pregnancyTimer = 0;	
	}

	@Override
	public void setCurrentPregnancyPhase(PregnancyPhase stage) {
		// Not needed here, each class has a fixed current pregnancy stage 
	}

	@Override
	public void setWomb(@NonNull Womb babiesInsideWomb) {
		this.babiesInsideWomb = babiesInsideWomb;
	}
	
	@Override
	public Womb getWomb() {
		return this.babiesInsideWomb;
	}
	
	@Override
	public int getMilking() {
		return this.preggoMob.getEntityData().get(this.dataAccessor.lactationData);
	}

	@Override
	public void setMilking(@NonNegative int milking) {
		this.preggoMob.getEntityData().set(this.dataAccessor.lactationData, Mth.clamp(milking, 0, PregnancySystemHelper.MAX_MILKING_LEVEL));
	}

	@Override
	public void incrementMilking() {
		this.setMilking(this.getMilking() + 1);	
	}

	@Override
	public void decrementMilking(int amount) {
		this.setMilking(this.getMilking() - amount);	
	}

	@Override
	public int getMilkingTimer() {
		return this.milkingTimer;
	}

	@Override
	public void setMilkingTimer(@NonNegative int timer) {
		this.milkingTimer = Math.max(0, timer);
	}

	@Override
	public void incrementMilkingTimer() {
		this.milkingTimer++;
	}

	@Override
	public void resetMilkingTimer() {
		this.milkingTimer = 0;
	}

	@Override
	public int getBellyRubs() {
		return this.preggoMob.getEntityData().get(this.dataAccessor.bellyRubsData);
	}

	@Override
	public void setBellyRubs(@NonNegative int bellyRubs) {
		this.preggoMob.getEntityData().set(this.dataAccessor.bellyRubsData, Mth.clamp(bellyRubs, 0, PregnancySystemHelper.MAX_BELLY_RUBBING_LEVEL));
	}

	@Override
	public void incrementBellyRubs() {
		this.setBellyRubs(this.getBellyRubs() + 1);
	}

	@Override
	public void decrementBellyRubs(int amount) {
		this.setBellyRubs(this.getBellyRubs() - amount);
	}

	@Override
	public int getBellyRubsTimer() {
		return this.bellyRubsTimer;
	}

	@Override
	public void setBellyRubsTimer(@NonNegative int timer) {
		this.bellyRubsTimer = Math.max(0, timer);
	}

	@Override
	public void incrementBellyRubsTimer() {
		this.bellyRubsTimer++;
	}

	@Override
	public void resetBellyRubsTimer() {
		this.bellyRubsTimer = 0;	
	}

	@Override
	public int getHorny() {
		return this.preggoMob.getEntityData().get(this.dataAccessor.hornyData);
	}

	@Override
	public void setHorny(@NonNegative int horny) {
		this.preggoMob.getEntityData().set(this.dataAccessor.hornyData, Mth.clamp(horny, 0, PregnancySystemHelper.MAX_HORNY_LEVEL));	
	}

	@Override
	public void incrementHorny() {
		this.setHorny(this.getHorny() + 1);
	}

	@Override
	public int getHornyTimer() {
		return this.hornyTimer;
	}

	@Override
	public void setHornyTimer(@NonNegative int timer) {
		this.hornyTimer = Math.max(0, timer);	
	}

	@Override
	public void incrementHornyTimer() {
		this.hornyTimer++;
	}

	@Override
	public void resetHornyTimer() {
		this.hornyTimer = 0;	
	}
	
	@Override
	public PregnancyPhase getCurrentPregnancyPhase() {
		return this.currentPregnancyPhase;
	}

	@Override
	@Nullable
	public Craving getTypeOfCraving() {
		return this.preggoMob.getEntityData().get(this.dataAccessor.cravingChosenData).orElse(null);
	}

	@Override
	public void setTypeOfCraving(@Nullable Craving craving) {
		this.preggoMob.getEntityData().set(this.dataAccessor.cravingChosenData, Optional.ofNullable(craving));	
	}

	@Override
	public boolean isValidCraving(Item itemCraving) {	
		var craving = this.getTypeOfCraving();
		if (craving == null) return false;
			
		final var items = PregnancySystemHelper.getCravingItems(preggoMob.getTypeOfSpecies(), craving);
		
		if (items == null) {
			MinepreggoMod.LOGGER.warn("No craving items found for species: {} and craving: {}", 
					preggoMob.getTypeOfSpecies(), craving);
			return false;
		}

		for (final var item : items) {
			MinepreggoMod.LOGGER.debug("Checking craving item: {} against item: {}", item, itemCraving);
			if (item == itemCraving) {
				return true;
			}
		}
		
		return false;
	}

	@Override
	public int getCraving() {
		return this.preggoMob.getEntityData().get(this.dataAccessor.cravingData);
	}

	@Override
	public void setCraving(int craving) {
		this.preggoMob.getEntityData().set(this.dataAccessor.cravingData, Mth.clamp(craving, 0, PregnancySystemHelper.MAX_CRAVING_LEVEL));
	}

	@Override
	public int getCravingTimer() {
		return this.cravingTimer;
	}

	@Override
	public void setCravingTimer(@NonNegative int timer) {
		this.cravingTimer = Math.max(0, timer);	
	}
	
	@Override
	public void clearTypeOfCraving() {
		this.setTypeOfCraving(null);	
	}

	@Override
	public void incrementCraving() {
		this.setCraving(this.getCraving() + 1);
	}

	@Override
	public void decrementCraving(int amount) {
		this.setCraving(this.getCraving() - amount);
	}

	@Override
	public void incrementCravingTimer() {
		this.cravingTimer++;	
	}
	
	@Override
	public void reducePregnancyHealth(int amount) {
		setPregnancyHealth(getPregnancyHealth() - amount);
	}

	@Override
	public void resetPregnancyHealth() {
		setPregnancyHealth(0);
	}

	@Override
	public void incrementDaysPassed() {
		setDaysPassed(getDaysPassed() + 1);
	}

	@Override
	public void resetDaysPassed() {
		setDaysPassed(0);
	}

	@Override
	public void reduceDaysToGiveBirth() {
		setDaysToGiveBirth(getDaysToGiveBirth() - 1);
	}

	@Override
	public void clearPregnancyPain() {
		setPregnancyPain(null);
	}

	@Override
	public void resetCravingTimer() {
		setCravingTimer(0);	
	}
	
	@Override
	public SyncedPregnancySymptomSet getSyncedPregnancySymptoms() {
		return syncedPregnancySymptomsSet;
	}
	
	@Override
	public int getPregnancyHealthTimer() {
		return this.pregnancyHealthTimer;
	}

	@Override
	public void setPregnancyHealthTimer(int timer) {
		this.pregnancyHealthTimer = Math.max(0, timer);
	}

	@Override
	public void incrementPregnancyHealthTimer() {
		++this.pregnancyHealthTimer;
	}

	@Override
	public void resetPregnancyHealthTimer() {
		this.pregnancyHealthTimer = 0;
	}
	
	@Override
	public @Nullable BirthData getBirthData() {
		return this.birthData.orElse(null);
	}

	@Override
	public void setBirthData(@Nullable BirthData birthData) {
		if (birthData != null) {
			this.birthData = Optional.of(birthData);
			this.preggoMob.getEntityData().set(this.dataAccessor.birthClientData, Optional.of(new BirthClientData(birthData.getBabyData())));
		} else {
			this.birthData = Optional.empty();
			this.preggoMob.getEntityData().set(this.dataAccessor.birthClientData, Optional.empty());
		}
	}
	
	@Override
	public @Nullable BirthClientData getBirthClientData() {
		return this.preggoMob.getEntityData().get(this.dataAccessor.birthClientData).orElse(null);
	}
	
	@Override
	public CompoundTag serializeNBT() {
		CompoundTag compoundTag = new CompoundTag();
		SynchedEntityData syncedData = this.preggoMob.getEntityData();

		compoundTag.putInt("DataPregnancyHealth", this.pregnancyHealth);
		compoundTag.putInt("DataDaysPassed", this.daysPassed);
		compoundTag.putInt("DataDaysToGiveBirth", this.daysToGiveBirth);
		compoundTag.putInt("DataCraving", syncedData.get(this.dataAccessor.cravingData));
		compoundTag.putInt("DataCravingTimer", this.cravingTimer);
		compoundTag.putInt("DataMilking", syncedData.get(this.dataAccessor.lactationData));
		compoundTag.putInt("DataMilkingTimer", this.milkingTimer);
		compoundTag.putInt("DataBellyRubs", syncedData.get(this.dataAccessor.bellyRubsData));
		compoundTag.putInt("DataBellyRubsTimer", this.bellyRubsTimer);
		compoundTag.putInt("DataHorny", syncedData.get(this.dataAccessor.hornyData));
		compoundTag.putInt("DataHornyTimer", this.hornyTimer);
		compoundTag.putInt("DataPregnancyTimer", pregnancyTimer);
		compoundTag.putString(PregnancyPhase.LAST_PHASE_NBT_KEY, this.lastPregnancyPhase.name());

		var craving = getTypeOfCraving();
		if (craving != null) {
			compoundTag.putString(Craving.NBT_KEY, craving.name());
		}
		
		if (!syncedPregnancySymptomsSet.isEmpty()) {
			compoundTag.put("PregnancySymptomData", syncedPregnancySymptomsSet.serializeNBT());
		}
		
		var pain = getPregnancyPain();
		if (pain != null) {
			compoundTag.put("DataPregnancyPainInstance", pain.serializeNBT());
		}

		if (babiesInsideWomb != null && !babiesInsideWomb.isEmpty()) {
			compoundTag.put("DataBabies", this.babiesInsideWomb.toNBT());
		}
		if (daysByPregnancyPhase != null && !daysByPregnancyPhase.isEmpty()) {
			compoundTag.put("DaysByPhase", this.daysByPregnancyPhase.toNBT());
		}
		
		birthData.ifPresent(data -> compoundTag.put("DataBirth", data.serializeNBT()));
		
		return compoundTag;
	}


	@Override
	public void deserializeNBT(CompoundTag compoundTag) throws IllegalStateException {
		SynchedEntityData syncedData = this.preggoMob.getEntityData();
		this.pregnancyHealth = compoundTag.getInt("DataPregnancyHealth");
		this.daysPassed = compoundTag.getInt("DataDaysPassed");
		this.daysToGiveBirth = compoundTag.getInt("DataDaysToGiveBirth");
		syncedData.set(this.dataAccessor.cravingData, compoundTag.getInt("DataCraving"));
		this.cravingTimer = compoundTag.getInt("DataCravingTimer");
		syncedData.set(this.dataAccessor.lactationData, compoundTag.getInt("DataMilking"));
		this.milkingTimer = compoundTag.getInt("DataMilkingTimer");
		syncedData.set(this.dataAccessor.bellyRubsData, compoundTag.getInt("DataBellyRubs"));
		this.bellyRubsTimer = compoundTag.getInt("DataBellyRubsTimer");
		syncedData.set(this.dataAccessor.hornyData, compoundTag.getInt("DataHorny"));
		this.hornyTimer = compoundTag.getInt("DataHornyTimer");
		this.pregnancyTimer = compoundTag.getInt("DataPregnancyTimer");
		this.lastPregnancyPhase = PregnancyPhase.valueOf(compoundTag.getString(PregnancyPhase.LAST_PHASE_NBT_KEY));

	    if (compoundTag.contains(Craving.NBT_KEY, Tag.TAG_STRING)) {
        	setTypeOfCraving(Craving.valueOf(compoundTag.getString(Craving.NBT_KEY)));
	    }	
	    if (compoundTag.contains("DataPregnancyPainInstance", Tag.TAG_COMPOUND)) {
	        setPregnancyPain(new PregnancyPainInstance(compoundTag.getCompound("DataPregnancyPainInstance")));
	    }   
	    
	    if (compoundTag.contains("PregnancySymptomData", Tag.TAG_COMPOUND)) {
	    	this.syncedPregnancySymptomsSet.deserializeNBT(compoundTag.getCompound("PregnancySymptomData"));
	    }
 
	    if (compoundTag.contains("DataBabies", Tag.TAG_COMPOUND)) {
	    	this.babiesInsideWomb = Womb.fromNBT(compoundTag.getCompound("DataBabies"));
	    	if (this.babiesInsideWomb == null) {
	    		throw new IllegalStateException("Womb is not present in NBT of class: " + this.getClass().getSimpleName());
	    	}
	    }  	
	    
	    if (compoundTag.contains("DaysByPhase", Tag.TAG_COMPOUND)) {
	    	this.daysByPregnancyPhase = PregnancyPhaseMap.fromNBT(compoundTag.getCompound("DaysByPhase"));
	    	if (this.daysByPregnancyPhase == null) {
	    		throw new IllegalStateException("DaysByPregnancyPhase is not present in NBT of class: " + this.getClass().getSimpleName());
	    	}
	    } 	
	    
	    if (compoundTag.contains("DataBirth", Tag.TAG_COMPOUND)) {
	    	setBirthData(new BirthData(compoundTag.getCompound("DataBirth")));
	    }
	}
}
