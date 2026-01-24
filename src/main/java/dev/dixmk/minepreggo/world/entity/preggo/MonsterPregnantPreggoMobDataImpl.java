package dev.dixmk.minepreggo.world.entity.preggo;

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

public class MonsterPregnantPreggoMobDataImpl<E extends PreggoMob> implements IMonsterPreggoMobPregnancyData {
	
	private static final String NBT_KEY = "MonsterPregnantPreggoMobData";
	
    public static class DataAccessor<E extends PreggoMob> {
    	private final EntityDataAccessor<Boolean> dataHasPregnancyPain;	
		
		public DataAccessor(Class<E> entityClass) {
			this.dataHasPregnancyPain = SynchedEntityData.defineId(entityClass, EntityDataSerializers.BOOLEAN);
		}
		
		public void defineSynchedData(E preggomob) {
			SynchedEntityData entityData = preggomob.getEntityData();
			entityData.define(dataHasPregnancyPain, false);		
		} 	
		
		public EntityDataAccessor<Boolean> getDataHasPregnancyPain() {
			return dataHasPregnancyPain;
		}
    }
	
	private final DataAccessor<E> dataAccessor;
    private final E preggoMob;
	private int pregnancyPainTimer = 0;
	private final PregnancyPhase currentPregnanctStage;
	private PregnancyPhase maxPregnanctStage;
	private int totalDaysPassed;
	private float pregnancyPainProbability;
	private int numOfBabies;
	
	public MonsterPregnantPreggoMobDataImpl(DataAccessor<E> dataAccessor, E preggoMob, PregnancyPhase currentPregnancyStage) {
		this.dataAccessor = dataAccessor;
		this.preggoMob = preggoMob;
		this.currentPregnanctStage = currentPregnancyStage;
		var random = preggoMob.getRandom();
		this.maxPregnanctStage = PregnancySystemHelper.calculateRandomMinPhaseToGiveBirthFrom(currentPregnancyStage, random);
		this.totalDaysPassed = MapPregnancyPhase.calculateRandomTotalDaysElapsed(currentPregnancyStage, this.maxPregnanctStage, random);
		this.pregnancyPainProbability = MathHelper.sigmoid(0.05F, 0.1F, 0.1F, Mth.clamp(this.totalDaysPassed /(float) PregnancySystemHelper.DEFAULT_TOTAL_PREGNANCY_DAYS , 0, 1), 0.6F);
		
		var opt = PregnancySystemHelper.calculateRandomNumOfBabiesByMaxPregnancyPhase(this.maxPregnanctStage, random);
		
		if (opt.isEmpty()) {
			preggoMob.discard();
			throw new IllegalStateException("Failed to calculate number of babies for pregnancy phase: " + this.maxPregnanctStage.name());
		}
		
		this.numOfBabies = opt.getAsInt();
	}
	
	@Override
	public boolean isIncapacitated() {
		return this.preggoMob.getEntityData().get(this.dataAccessor.dataHasPregnancyPain);
	}

	@Override
	public PregnancyPhase getCurrentPregnancyPhase() {
		return this.currentPregnanctStage;
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
		nbt.putBoolean("DataHasPregnancyPain", this.preggoMob.getEntityData().get(this.dataAccessor.dataHasPregnancyPain));
		nbt.putInt("PregnancyPainTimer", this.pregnancyPainTimer);
		nbt.putInt("TotalDaysPassed", this.totalDaysPassed);
		nbt.putFloat("PregnancyPainProbability", this.pregnancyPainProbability);
		nbt.putString("LastPregnancyPhase", this.maxPregnanctStage.name());
		nbt.putInt("NumOfBabies", this.numOfBabies);
		wrapper.put(NBT_KEY, nbt);
		return wrapper;
	}

	@Override
	public void deserializeNBT(CompoundTag nbt) throws IllegalArgumentException {
		if (!nbt.contains(NBT_KEY, Tag.TAG_COMPOUND)) {
			throw new IllegalArgumentException("NBT data does not contain key: " + NBT_KEY);
		}
		CompoundTag dataTag = nbt.getCompound(NBT_KEY);
		this.preggoMob.getEntityData().set(this.dataAccessor.dataHasPregnancyPain, dataTag.getBoolean("DataHasPregnancyPain"));
		this.pregnancyPainTimer = dataTag.getInt("PregnancyPainTimer");
		this.totalDaysPassed = dataTag.getInt("TotalDaysPassed");
		this.pregnancyPainProbability = dataTag.getFloat("PregnancyPainProbability");
		this.maxPregnanctStage = PregnancyPhase.valueOf(dataTag.getString("LastPregnancyPhase"));
		this.numOfBabies = dataTag.getInt("NumOfBabies");
	}

	@Override
	public int getNumOfBabies() {
		return this.numOfBabies;
	}

	@Override
	public void incrementNumOfBabies(int count) {
		this.numOfBabies += Math.max(0, count);
	}
}
