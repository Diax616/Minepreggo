package dev.dixmk.minepreggo.world.entity.preggo;

import dev.dixmk.minepreggo.init.MinepreggoModEntityDataSerializers;
import dev.dixmk.minepreggo.utils.MathHelper;
import dev.dixmk.minepreggo.world.pregnancy.MapPregnancyPhase;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;

public class HostilPregnantPreggoMobDataImpl<E extends PreggoMob> implements IHostilPreggoMobPregnancyData {
	
	private static final String NBT_KEY = "HostilPregnantPreggoMobData";
	
    public static class DataAccessor<E extends PreggoMob> {
    	private final EntityDataAccessor<Boolean> hasPregnancyPain;	
    	private final EntityDataAccessor<PregnancyPhase> currentPregnanctPhase;
		
		public DataAccessor(Class<E> entityClass) {
			this.hasPregnancyPain = SynchedEntityData.defineId(entityClass, EntityDataSerializers.BOOLEAN);
			this.currentPregnanctPhase = SynchedEntityData.defineId(entityClass, MinepreggoModEntityDataSerializers.PREGNANCY_STAGE);
		}
		
		public void defineSynchedData(E preggomob) {
			SynchedEntityData entityData = preggomob.getEntityData();
			entityData.define(currentPregnanctPhase, PregnancyPhase.P0);
			entityData.define(hasPregnancyPain, false);		
		} 	
		
		public EntityDataAccessor<Boolean> getDataHasPregnancyPain() {
			return hasPregnancyPain;
		}
		
		public EntityDataAccessor<PregnancyPhase> getCurrentPregnanctPhase() {
			return currentPregnanctPhase;
		}
    }
	
	private final DataAccessor<E> dataAccessor;
    private final E preggoMob;
	private int pregnancyPainTimer = 0;
	private int incapacitatedCooldown = 100;
	private PregnancyPhase maxPregnanctStage = PregnancyPhase.P4;
	private int totalDaysPassed = PregnancySystemHelper.DEFAULT_TOTAL_PREGNANCY_DAYS;
	private float pregnancyPainProbability = 0.1F;
	private int numOfBabies = 1;
	private boolean wasInitialized = false;
	
	public HostilPregnantPreggoMobDataImpl(DataAccessor<E> dataAccessor, E preggoMob, PregnancyPhase currentPregnancyStage) {
		this.dataAccessor = dataAccessor;
		this.preggoMob = preggoMob;
		this.preggoMob.getEntityData().set(this.dataAccessor.currentPregnanctPhase, currentPregnancyStage);
	}
	
	@Override
	public void init() {
		if (this.wasInitialized) {
			return;
		}
		
		var random = preggoMob.getRandom();
		this.maxPregnanctStage = PregnancySystemHelper.calculateRandomMinPhaseToGiveBirthFrom(this.preggoMob.getEntityData().get(this.dataAccessor.currentPregnanctPhase), random);
		this.totalDaysPassed = MapPregnancyPhase.calculateRandomTotalDaysElapsed(this.preggoMob.getEntityData().get(this.dataAccessor.currentPregnanctPhase), this.maxPregnanctStage, random);
		this.pregnancyPainProbability = MathHelper.sigmoid(0.05F, 0.15F, 0.1F, Mth.clamp(this.totalDaysPassed /(float) PregnancySystemHelper.DEFAULT_TOTAL_PREGNANCY_DAYS , 0, 1), 0.6F);
		this.incapacitatedCooldown = 60 + this.preggoMob.getEntityData().get(this.dataAccessor.currentPregnanctPhase).ordinal() * 20;
		
		var opt = PregnancySystemHelper.calculateRandomNumOfBabiesByMaxPregnancyPhase(this.maxPregnanctStage, random);
		
		if (opt.isEmpty()) {
			preggoMob.discard();
			throw new IllegalStateException("Failed to calculate number of babies for pregnancy phase: " + this.maxPregnanctStage.name());
		}
		
		this.numOfBabies = opt.getAsInt();
		
		this.wasInitialized = true;
	}
	
	@Override
	public boolean isIncapacitated() {
		return this.preggoMob.getEntityData().get(this.dataAccessor.hasPregnancyPain);
	}

	@Override
	public PregnancyPhase getCurrentPregnancyPhase() {
		return this.preggoMob.getEntityData().get(this.dataAccessor.currentPregnanctPhase);
	}
	
	@Override
	public void setCurrentPregnancyPhase(PregnancyPhase phase) throws IllegalArgumentException {
		if (phase.compareTo(this.maxPregnanctStage) > 0) {
			throw new IllegalArgumentException("Cannot set pregnancy phase to " + phase.name() + " because it exceeds the maximum pregnancy stage: " + this.maxPregnanctStage.name());
		}
		this.preggoMob.getEntityData().set(this.dataAccessor.currentPregnanctPhase, phase);
	}
	
	@Override
	public PregnancyPhase getLastPregnancyPhase() {
		return this.maxPregnanctStage;
	}

	@Override
	public int getTotalDaysElapsed() {
		return this.totalDaysPassed;
	}

	@Override
	public void setPregnancyPain(boolean value) {
		this.preggoMob.getEntityData().set(this.dataAccessor.getDataHasPregnancyPain(), value);
	}

	@Override
	public int getPregnancyPainTimer() {
		return this.pregnancyPainTimer;
	}

	@Override
	public void setPregnancyPainTimer(int tick) {
		this.pregnancyPainTimer = tick;
	}

	@Override
	public float getPregnancyPainProbability() {
		return this.pregnancyPainProbability;
	}

	@Override
	public CompoundTag serializeNBT() {
		CompoundTag nbt = new CompoundTag();
		CompoundTag wrapper = new CompoundTag();
		nbt.putBoolean("DataHasPregnancyPain", this.preggoMob.getEntityData().get(this.dataAccessor.hasPregnancyPain));
		nbt.putString("CurrentPregnancyPhase", this.preggoMob.getEntityData().get(this.dataAccessor.currentPregnanctPhase).name());
		nbt.putInt("PregnancyPainTimer", this.pregnancyPainTimer);
		nbt.putInt("TotalDaysPassed", this.totalDaysPassed);
		nbt.putFloat("PregnancyPainProbability", this.pregnancyPainProbability);
		nbt.putString("LastPregnancyPhase", this.maxPregnanctStage.name());
		nbt.putInt("NumOfBabies", this.numOfBabies);
		nbt.putInt("IncapacitatedCooldown", this.incapacitatedCooldown);
		nbt.putBoolean("WasInitialized", this.wasInitialized);
		wrapper.put(NBT_KEY, nbt);
		return wrapper;
	}

	@Override
	public void deserializeNBT(CompoundTag nbt) throws IllegalArgumentException {
		if (!nbt.contains(NBT_KEY, Tag.TAG_COMPOUND)) {
			throw new IllegalArgumentException("NBT data does not contain key: " + NBT_KEY);
		}
		CompoundTag dataTag = nbt.getCompound(NBT_KEY);
		this.preggoMob.getEntityData().set(this.dataAccessor.hasPregnancyPain, dataTag.getBoolean("DataHasPregnancyPain"));
		this.preggoMob.getEntityData().set(this.dataAccessor.currentPregnanctPhase, PregnancyPhase.valueOf(dataTag.getString("CurrentPregnancyPhase")));
		this.pregnancyPainTimer = dataTag.getInt("PregnancyPainTimer");
		this.totalDaysPassed = dataTag.getInt("TotalDaysPassed");
		this.pregnancyPainProbability = dataTag.getFloat("PregnancyPainProbability");
		this.maxPregnanctStage = PregnancyPhase.valueOf(dataTag.getString("LastPregnancyPhase"));
		this.numOfBabies = dataTag.getInt("NumOfBabies");
		this.incapacitatedCooldown = dataTag.getInt("IncapacitatedCooldown");
		this.wasInitialized = dataTag.getBoolean("WasInitialized");
	}

	@Override
	public int getNumOfBabies() {
		return this.numOfBabies;
	}

	@Override
	public void incrementNumOfBabies(int count) {
		this.numOfBabies += Math.max(0, count);
	}

	@Override
	public int getIncapacitatedCooldown() {
		return incapacitatedCooldown;
	}
}
