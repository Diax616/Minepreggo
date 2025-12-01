package dev.dixmk.minepreggo.world.entity.player;

import javax.annotation.Nonnegative;

import org.checkerframework.checker.nullness.qual.NonNull;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.init.MinepreggoModMobEffects;
import dev.dixmk.minepreggo.world.pregnancy.AbstractPregnancySystem;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPain;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySymptom;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;

public class PlayerPregnancySystemP4 extends PlayerPregnancySystemP3 {
	
	protected @Nonnegative int totalTicksOfHorny = MinepreggoModConfig.getTotalTicksOfHornyP4();
	protected @Nonnegative int totalTicksOfBirth = PregnancySystemHelper.TOTAL_TICKS_BIRTH_P4;
	protected @Nonnegative int totalTicksOfPreBirth = PregnancySystemHelper.TOTAL_TICKS_PREBIRTH_P4;

	public PlayerPregnancySystemP4(@NonNull ServerPlayer player) {
		super(player);
		addNewValidPregnancySymptoms(PregnancySymptom.HORNY);
	}
	
	@Override
	protected void initPregnancySymptomsTimers() {
		totalTicksOfCraving = MinepreggoModConfig.getTotalTicksOfCravingP4();
		totalTicksOfMilking = MinepreggoModConfig.getTotalTicksOfMilkingP4();
		totalTicksOfBellyRubs = MinepreggoModConfig.getTotalTicksOfBellyRubsP4();
	}
	
	@Override
	protected void evaluatePregnancyNeeds() {	
		super.evaluatePregnancyNeeds();
		evaluateHornyTimer();
	}
	
	@Override
	protected void evaluatePregnancySystem() {
		if (isInLabor()) {		
			if (pregnantEntity.level() instanceof ServerLevel serverLevel) {
				evaluateBirth(serverLevel);
			}		
			return;
		}
		
		if (isWaterBroken()) {
			if (pregnantEntity.level() instanceof ServerLevel serverLevel) {
				evaluateWaterBreaking(serverLevel);
			}
			return;
		}
		
		if (canAdvanceNextPregnancyPhase() && hasToGiveBirth()) {
			breakWater();
			return;
		}
		
		super.evaluatePregnancySystem();
	}
	
	protected void evaluateHornyTimer() {   				
		if (pregnancyEffects.getHorny() < PregnancySystemHelper.MAX_HORNY_LEVEL) {
	        if (pregnancyEffects.getHornyTimer() >= totalTicksOfHorny) {
	        	pregnancyEffects.incrementHorny();
	        	pregnancyEffects.resetHornyTimer();
	        	pregnancyEffects.sync(pregnantEntity);
	        	
	        	MinepreggoMod.LOGGER.debug("Player {} horny level increased to: {}", 
	        			pregnantEntity.getGameProfile().getName(), pregnancyEffects.getBellyRubs());
	        }
	        else {
	        	pregnancyEffects.incrementHornyTimer();
	        }
		}	
	}
	
	@Override
	protected boolean tryInitRandomPregnancyPain() {
		boolean flag = false;
		
		if (randomSource.nextFloat() < PregnancySystemHelper.MEDIUM_PREGNANCY_PAIN_PROBABILITY) {	
			if (hasToGiveBirth()) {
				pregnancySystem.setPregnancyPain(PregnancyPain.CONTRACTION);
			}
			else {
				pregnancySystem.setPregnancyPain(PregnancyPain.FETAL_MOVEMENT);
			}						
			flag = true;
		}
		else if (randomSource.nextFloat() < PregnancySystemHelper.LOW_MORNING_SICKNESS_PROBABILITY) {
			pregnancySystem.setPregnancyPain(PregnancyPain.MORNING_SICKNESS);					
			flag = true;
		}	
	
		if (flag) {
			pregnancySystem.sync(pregnantEntity);
			MinepreggoMod.LOGGER.debug("Player {} has developed pregnancy pain: {}",
					pregnantEntity.getGameProfile().getName(), pregnancySystem.getPregnancyPain());
		}
		
		return flag;
	}
	
	@Override
	protected boolean tryInitPregnancySymptom() {
		if (super.tryInitPregnancySymptom()) {
			return true;
		} 	
		if (pregnancyEffects.getHorny() >= PregnancySystemHelper.MAX_HORNY_LEVEL
				&& !pregnancySystem.getPregnancySymptoms().contains(PregnancySymptom.HORNY)) {
			pregnancySystem.addPregnancySymptom(PregnancySymptom.HORNY);
			pregnantEntity.addEffect(new MobEffectInstance(MinepreggoModMobEffects.HORNY.get(), -1, 0, true, true));
			pregnancySystem.sync(pregnantEntity);
			pregnancyEffects.sync(pregnantEntity);	
			MinepreggoMod.LOGGER.debug("Player {} has developed pregnancy symptom: {}",
					pregnantEntity.getGameProfile().getName(), PregnancySymptom.HORNY);
			return true;
		}

		return false;
	}
	

	@Override
	protected void startLabor() {
		pregnancySystem.setPregnancyPain(PregnancyPain.PREBIRTH);
		pregnancySystem.resetPregnancyPainTimer();
		pregnancySystem.sync(pregnantEntity);
		MinepreggoMod.LOGGER.debug("Player {} has started labor.", pregnantEntity.getGameProfile().getName());
	}
	
	@Override
	protected void startMiscarriage() {

	}
	
	@Override
	protected void evaluateBirth(ServerLevel serverLevel) {		
		

	}
	
	@Override
	protected void initPostPartum() {
		
		
	}
	
	@Override
	protected void evaluateWaterBreaking(ServerLevel serverLevel) {
		if (pregnancySystem.getPregnancyPainTimer() >= PregnancySystemHelper.TOTAL_TICKS_WATER_BREAKING) {
			startLabor();
		}
		else {
			pregnancySystem.incrementPregnancyPainTimer();
    		AbstractPregnancySystem.spawnParticulesForWaterBreaking(serverLevel, pregnantEntity);
		}
	}
	
	@Override
	protected void breakWater() {
		pregnancySystem.resetPregnancyPainTimer();
		pregnancySystem.setPregnancyPain(PregnancyPain.WATER_BREAKING);
		pregnancySystem.sync(pregnantEntity);
	}
	
	@Override
	protected boolean hasToGiveBirth() {
		return pregnancySystem.getLastPregnancyStage() == pregnancySystem.getCurrentPregnancyStage();
	}
	
	@Override
	protected boolean isInLabor() {
		final var pain = pregnancySystem.getPregnancyPain();
		return pain == PregnancyPain.PREBIRTH || pain == PregnancyPain.BIRTH;
 	}
	
	@Override
	protected boolean isWaterBroken() {
		return pregnancySystem.getPregnancyPain() == PregnancyPain.WATER_BREAKING;
 	}
}
