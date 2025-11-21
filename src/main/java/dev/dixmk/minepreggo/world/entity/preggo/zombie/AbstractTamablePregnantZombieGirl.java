package dev.dixmk.minepreggo.world.entity.preggo.zombie;

import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;

import javax.annotation.Nonnull;

import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.Nullable;


import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.init.MinepreggoModEntityDataSerializers;
import dev.dixmk.minepreggo.network.capability.IPregnancyEffectsHandler;
import dev.dixmk.minepreggo.network.capability.IPregnancySystemHandler;
import dev.dixmk.minepreggo.world.entity.ai.goal.PreggoMobAIHelper;
import dev.dixmk.minepreggo.world.entity.preggo.Baby;
import dev.dixmk.minepreggo.world.entity.preggo.Craving;
import dev.dixmk.minepreggo.world.entity.preggo.IBreedable;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobPregnancySystemP0;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobSystem;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancyPain;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancyPhase;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancySymptom;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancySystemHelper;
import dev.dixmk.minepreggo.world.entity.preggo.Species;
import dev.dixmk.minepreggo.world.item.ItemHelper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;

public abstract class AbstractTamablePregnantZombieGirl<S extends PreggoMobSystem<?>, P extends PreggoMobPregnancySystemP0<?>> extends AbstractTamableZombieGirl<S> implements IPregnancySystemHandler, IPregnancyEffectsHandler {
	
	protected static final EntityDataAccessor<Integer> DATA_PREGNANCY_HEALTH = SynchedEntityData.defineId(AbstractTamablePregnantZombieGirl.class, EntityDataSerializers.INT);
	protected static final EntityDataAccessor<Integer> DATA_DAYS_PASSED = SynchedEntityData.defineId(AbstractTamablePregnantZombieGirl.class, EntityDataSerializers.INT);
	protected static final EntityDataAccessor<Integer> DATA_DAYS_TO_GIVE_BIRTH = SynchedEntityData.defineId(AbstractTamablePregnantZombieGirl.class, EntityDataSerializers.INT);
	
	protected static final EntityDataAccessor<Integer> DATA_CRAVING = SynchedEntityData.defineId(AbstractTamablePregnantZombieGirl.class, EntityDataSerializers.INT);
	protected static final EntityDataAccessor<Integer> DATA_MILKING = SynchedEntityData.defineId(AbstractTamablePregnantZombieGirl.class, EntityDataSerializers.INT);
	protected static final EntityDataAccessor<Integer> DATA_BELLY_RUBS = SynchedEntityData.defineId(AbstractTamablePregnantZombieGirl.class, EntityDataSerializers.INT);
	protected static final EntityDataAccessor<Integer> DATA_HORNY = SynchedEntityData.defineId(AbstractTamablePregnantZombieGirl.class, EntityDataSerializers.INT);
	protected static final EntityDataAccessor<Optional<PregnancySymptom>> DATA_PREGNANCY_SYMPTOM = SynchedEntityData.defineId(AbstractTamablePregnantZombieGirl.class, MinepreggoModEntityDataSerializers.OPTIONAL_PREGNANCY_SYMPTOM);
	protected static final EntityDataAccessor<Optional<PregnancyPain>> DATA_PREGNANCY_PAIN = SynchedEntityData.defineId(AbstractTamablePregnantZombieGirl.class, MinepreggoModEntityDataSerializers.OPTIONAL_PREGNANCY_PAIN);
	protected static final EntityDataAccessor<Optional<Craving>> DATA_CRAVING_CHOSEN = SynchedEntityData.defineId(AbstractTamablePregnantZombieGirl.class, MinepreggoModEntityDataSerializers.OPTIONAL_CRAVING);
	
	private int pregnancyTimer = 0;
	protected int cravingTimer = 0;
	protected int milkingTimer = 0;
	protected int bellyRubsTimer = 0;
	protected int hornyTimer = 0;
	protected int pregnancyPainTimer = 0;
	
	protected final PregnancyPhase currentPregnancyPhase;
	protected PregnancyPhase lastPregnancyPhase;
	
	protected final P pregnancySystem;
	
	private Map<PregnancyPhase, @NonNull Integer> daysByPregnancyPhase = new EnumMap<>(PregnancyPhase.class);

	
	
	protected AbstractTamablePregnantZombieGirl(EntityType<? extends AbstractTamablePregnantZombieGirl<?, ?>> p_21803_, Level p_21804_, PregnancyPhase currentPregnancyStage) {
		super(p_21803_, p_21804_);
		this.pregnancySystem = createPregnancySystem();
		this.currentPregnancyPhase = currentPregnancyStage;
		this.lastPregnancyPhase = PregnancyPhase.calculateMaxPregnancyStage(currentPregnancyStage);	
		PreggoMobHelper.initPregnancy(this, this.lastPregnancyPhase);
	}
		
	@Nonnull
	protected abstract P createPregnancySystem();
	
	private void tryReadOptional(CompoundTag compoundTag) {
	    if (compoundTag.contains(Craving.NBT_KEY, Tag.TAG_STRING)) {
	        String name = compoundTag.getString(Craving.NBT_KEY);
        	Craving craving = Craving.valueOf(name);
        	setTypeOfCraving(craving);
	    }	
	    if (compoundTag.contains(PregnancyPain.NBT_KEY, Tag.TAG_STRING)) {
	        String name = compoundTag.getString(PregnancyPain.NBT_KEY);
	        PregnancyPain pain = PregnancyPain.valueOf(name);
	        setPregnancyPain(pain);
	    }   
	    if (compoundTag.contains(PregnancySymptom.NBT_KEY, Tag.TAG_STRING)) {
	        String name = compoundTag.getString(PregnancySymptom.NBT_KEY);
			PregnancySymptom symptom = PregnancySymptom.valueOf(name);
			setPregnancySymptom(symptom);
	    }   
	}
	
	private void tryWriteOptional(CompoundTag compoundTag) {
		var craving = getTypeOfCraving();
		if (craving != null) {
			compoundTag.putString(Craving.NBT_KEY, craving.name());
		}
		var symptom = getPregnancySymptom();
		if (symptom != null) {
			compoundTag.putString(PregnancySymptom.NBT_KEY, symptom.name());
		}
		var pain = getPregnancyPain();
		if (pain != null) {
			compoundTag.putString(PregnancyPain.NBT_KEY, pain.name());
		}
	}
	
	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();	
		this.entityData.define(DATA_PREGNANCY_HEALTH, 100);
		this.entityData.define(DATA_DAYS_PASSED, 0);
		this.entityData.define(DATA_DAYS_TO_GIVE_BIRTH, 0);	
		this.entityData.define(DATA_CRAVING, 0);
		this.entityData.define(DATA_MILKING, 0);
		this.entityData.define(DATA_BELLY_RUBS, 0);
		this.entityData.define(DATA_HORNY, 0);
		this.entityData.define(DATA_PREGNANCY_SYMPTOM, Optional.empty());
		this.entityData.define(DATA_PREGNANCY_PAIN, Optional.empty());
		this.entityData.define(DATA_CRAVING_CHOSEN, Optional.empty());	
	}
	
	@Override
	public void addAdditionalSaveData(CompoundTag compoundTag) {
		super.addAdditionalSaveData(compoundTag);
		compoundTag.putInt("DataPregnancyHealth", this.entityData.get(DATA_PREGNANCY_HEALTH));
		compoundTag.putInt("DataDaysPassed", this.entityData.get(DATA_DAYS_PASSED));
		compoundTag.putInt("DataDaysToGiveBirth", this.entityData.get(DATA_DAYS_TO_GIVE_BIRTH));
		compoundTag.putInt("DataCraving", this.entityData.get(DATA_CRAVING));
		compoundTag.putInt("DataCravingTimer", this.cravingTimer);
		compoundTag.putInt("DataMilking", this.entityData.get(DATA_MILKING));
		compoundTag.putInt("DataMilkingTimer", this.milkingTimer);
		compoundTag.putInt("DataBellyRubs", this.entityData.get(DATA_BELLY_RUBS));
		compoundTag.putInt("DataBellyRubsTimer", this.bellyRubsTimer);
		compoundTag.putInt("DataHorny", this.entityData.get(DATA_HORNY));
		compoundTag.putInt("DataHornyTimer", this.hornyTimer);
		compoundTag.putInt("DataPregnancyTimer", pregnancyTimer);
		compoundTag.putInt("DataPregnancyPainTimer", this.pregnancyPainTimer);
		compoundTag.putInt("DataLastPregnancyPhase", this.lastPregnancyPhase.ordinal());
		tryWriteOptional(compoundTag);

		if (!daysByPregnancyPhase.isEmpty()) {
			compoundTag.put("DaysByPhase", PregnancySystemHelper.serializePregnancyPhaseMap(this.daysByPregnancyPhase));
		}
	}
	
	@Override
	public void readAdditionalSaveData(CompoundTag compoundTag) {
		super.readAdditionalSaveData(compoundTag);	
		this.entityData.set(DATA_PREGNANCY_HEALTH, compoundTag.getInt("DataPregnancyHealth"));
		this.entityData.set(DATA_DAYS_PASSED, compoundTag.getInt("DataDaysPassed"));
		this.entityData.set(DATA_DAYS_TO_GIVE_BIRTH, compoundTag.getInt("DataDaysToGiveBirth"));
		this.entityData.set(DATA_CRAVING, compoundTag.getInt("DataCraving"));
		this.cravingTimer = compoundTag.getInt("DataCravingTimer");
		this.entityData.set(DATA_MILKING, compoundTag.getInt("DataMilking"));
		this.milkingTimer = compoundTag.getInt("DataMilkingTimer");
		this.entityData.set(DATA_BELLY_RUBS, compoundTag.getInt("DataBellyRubs"));
		this.bellyRubsTimer = compoundTag.getInt("DataBellyRubsTimer");
		this.entityData.set(DATA_HORNY, compoundTag.getInt("DataHorny"));
		this.hornyTimer = compoundTag.getInt("DataHornyTimer");
		this.pregnancyTimer = compoundTag.getInt("DataPregnancyTimer");
		this.pregnancyPainTimer = compoundTag.getInt("DataPregnancyPainTimer");
		this.lastPregnancyPhase = PregnancyPhase.values()[compoundTag.getInt("DataLastPregnancyPhase")];
		tryReadOptional(compoundTag);

	    if (compoundTag.contains("DaysByPhase")) {
	    	PregnancySystemHelper.deserializePregnancyPhaseMap(compoundTag.getList("DaysByPhase", Tag.TAG_COMPOUND), daysByPregnancyPhase);
	    } 
	}
	
	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(8, new AbstractZombieGirl.ZombieGirlAttackTurtleEggGoal(this, 1.0D, 3){
			@Override
			public boolean canUse() {
				return super.canUse() 
				&& !isWaiting() && !isIncapacitated();
			}
		});	
		PreggoMobAIHelper.setTamablePregnantZombieGirlGoals(this);
	}
	
	@Override
	public SoundEvent getDeathSound() {
		return ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "preggo_death"));
	}
	
	@Override
	public boolean hasCustomHeadAnimation() {
		return super.hasCustomHeadAnimation() || this.getPregnancyPain() != null;
	}
	
	@Override
	public void die(DamageSource source) {
		super.die(source);			
		PreggoMobHelper.spawnBabyAndFetusZombies(this);
	}
	
	@Override
	public boolean hurt(DamageSource damagesource, float amount) {
		boolean result = super.hurt(damagesource, amount);	
		
		if (result) {
			pregnancySystem.evaluateOnSuccessfulHurt(damagesource);
		}
		
		return result;
	}
	
	@Override
   	public void aiStep() {
      super.aiStep();  
      if (this.isAlive()) {	  
          this.pregnancySystem.onServerTick();       
      }
	}
	
	@Override
	protected boolean canReplaceCurrentItem(ItemStack p_21428_, ItemStack p_21429_) {	
		if ((ItemHelper.isChest(p_21428_) && !PreggoMobHelper.canUseChestplate(p_21428_, this.getCurrentPregnancyStage()))
					|| (ItemHelper.isLegging(p_21428_) && !PreggoMobHelper.canUseLegging(p_21428_, this.getCurrentPregnancyStage()))) {
			return false;
		}	
		return super.canReplaceCurrentItem(p_21428_, p_21429_);
	}
	
	@Override
	public InteractionResult mobInteract(Player sourceentity, InteractionHand hand) {	
		var retval = this.pregnancySystem.onRightClick(sourceentity);
		if (retval.shouldAwardStats()) {
			return retval;
		}
		else {
			return super.mobInteract(sourceentity, hand);
		}
	}
	
	@Override
	public boolean doHurtTarget(Entity target) {		
		boolean result = super.doHurtTarget(target);	
		if (result && !this.isSavage() && target instanceof Player owner && this.isOwnedBy(owner) && this.isAngry()) {
			this.setTarget(null);		
		}
		return result;
	}
	
	@Override
	public int getDaysByStage() {
		if (daysByPregnancyPhase.containsKey(currentPregnancyPhase)) {
			return daysByPregnancyPhase.get(currentPregnancyPhase);
		}
		MinepreggoMod.LOGGER.error("Could not get total days by pregnancy phase, current pregnancy phase: {}, map: {}", 
				currentPregnancyPhase, daysByPregnancyPhase.isEmpty());
		return -1;
	}

	@Override
	public Map<PregnancyPhase, Integer> getDaysByStageMapping() {
		return this.daysByPregnancyPhase;
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
	public boolean setDaysByStage(Map<PregnancyPhase, Integer> map) {
		if (this.daysByPregnancyPhase.isEmpty()) {
			this.daysByPregnancyPhase = map;
			return true;
		}
		return false;
	}
	
	@Override
	public int getPregnancyHealth() {
		return this.entityData.get(DATA_PREGNANCY_HEALTH);
	}
	
	@Override
	public void setPregnancyHealth(int health) {
		this.entityData.set(DATA_PREGNANCY_HEALTH, Mth.clamp(health, 0, PregnancySystemHelper.MAX_PREGNANCY_HEALTH));
	}
	
	@Override
	public int getDaysPassed() {
		return this.entityData.get(DATA_DAYS_PASSED);
	}
	
	@Override
	public void setDaysPassed(int days) {
		this.entityData.set(DATA_DAYS_PASSED, Math.max(0, days));
	}
	
	@Override
	public int getDaysToGiveBirth() {
		return this.entityData.get(DATA_DAYS_TO_GIVE_BIRTH);
	}
	
	@Override
	public void setDaysToGiveBirth(int days) {
		this.entityData.set(DATA_DAYS_TO_GIVE_BIRTH, Math.max(0, days));
	}
	
	@Override
	@Nullable
	public PregnancySymptom getPregnancySymptom() {
		var symptom = this.entityData.get(DATA_PREGNANCY_SYMPTOM);
		return symptom.isPresent() ? symptom.get() : null;
	}
	
	@Override
	public void setPregnancySymptom(@Nullable PregnancySymptom symptom) {
		this.entityData.set(DATA_PREGNANCY_SYMPTOM, Optional.ofNullable(symptom));
	}
	
	@Override
	@Nullable
	public PregnancyPain getPregnancyPain() {
		var pain = this.entityData.get(DATA_PREGNANCY_PAIN);
		return pain.isPresent() ? pain.get() : null;	
	}
	
	@Override
	public void setPregnancyPain(@Nullable PregnancyPain symptom) {
		this.entityData.set(DATA_PREGNANCY_PAIN, Optional.ofNullable(symptom));
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
	public int getPregnancyPainTimer() {
		return this.pregnancyPainTimer;
	}
	
	@Override
	public void setPregnancyPainTimer(int ticks) {
		this.pregnancyPainTimer = ticks;
	}
	
	@Override
	public Baby getDefaultTypeOfBaby() {
		return Baby.ZOMBIE;
	}
	
	@Override
	public boolean isIncapacitated() {	
		return this.getPregnancyPain() != null;
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
	public void incrementPregnancyPainTimer() {
		this.pregnancyPainTimer++;	
	}

	@Override
	public void resetPregnancyPainTimer() {
		this.pregnancyPainTimer = 0;	
	}

	@Override
	public void setCurrentPregnancyStage(PregnancyPhase stage) {
		// Not needed here, each class has a fixed current pregnancy stage 
	}

	@Override
	public int getTotalNumOfBabies() {
		return IBreedable.getOffspringsByMaxPregnancyStage(lastPregnancyPhase);
	}

	@Override
	public int getMilking() {
		return this.entityData.get(DATA_MILKING);
	}

	@Override
	public void setMilking(@NonNegative int milking) {
		this.entityData.set(DATA_MILKING, Mth.clamp(milking, 0, PregnancySystemHelper.MAX_MILKING_LEVEL));
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
		return this.entityData.get(DATA_BELLY_RUBS);
	}

	@Override
	public void setBellyRubs(@NonNegative int bellyRubs) {
		this.entityData.set(DATA_BELLY_RUBS, Mth.clamp(bellyRubs, 0, PregnancySystemHelper.MAX_BELLY_RUBBING_LEVEL));
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
		return this.entityData.get(DATA_HORNY);
	}

	@Override
	public void setHorny(@NonNegative int horny) {
		this.entityData.set(DATA_HORNY, Mth.clamp(horny, 0, PregnancySystemHelper.MAX_HORNY_LEVEL));	
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
	public PregnancyPhase getCurrentPregnancyStage() {
		return this.currentPregnancyPhase;
	}

	@Override
	@Nullable
	public Craving getTypeOfCraving() {
		var craving = this.entityData.get(DATA_CRAVING_CHOSEN);
		return craving.isPresent() ? craving.get() : null;
	}

	@Override
	public void setTypeOfCraving(@Nullable Craving craving) {
		this.entityData.set(DATA_CRAVING_CHOSEN, Optional.ofNullable(craving));	
	}

	@Override
	public boolean isValidCraving(Item itemCraving) {	
		var craving = this.getTypeOfCraving();
		if (craving == null) return false;
			
		final var items = PregnancySystemHelper.getCravingItems(Species.ZOMBIE, craving);
		
		if (items == null) return false;
		
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
		return this.entityData.get(DATA_CRAVING);
	}

	@Override
	public void setCraving(int craving) {
		this.entityData.set(DATA_CRAVING, Mth.clamp(craving, 0, PregnancySystemHelper.MAX_CRAVING_LEVEL));
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
	public void clearPregnancySymptom() {
		setPregnancySymptom(null);
	}

	@Override
	public void clearPregnancyPain() {
		setPregnancyPain(null);
	}

	@Override
	public void resetCravingTimer() {
		setCravingTimer(0);	
	}
}
