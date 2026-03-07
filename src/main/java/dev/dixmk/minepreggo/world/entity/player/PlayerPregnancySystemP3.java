package dev.dixmk.minepreggo.world.entity.player;

import javax.annotation.Nonnegative;

import org.checkerframework.checker.nullness.qual.NonNull;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.init.MinepreggoMobEffects;
import dev.dixmk.minepreggo.world.entity.preggo.Species;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPain;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySymptom;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public class PlayerPregnancySystemP3 extends PlayerPregnancySystemP2 {

	protected @Nonnegative int totalTicksOfBellyRubs = MinepreggoModConfig.SERVER.getTotalTicksOfBellyRubsP3();
	protected @Nonnegative float fetalMovementProb = PregnancySystemHelper.LOW_PREGNANCY_PAIN_PROBABILITY;
	protected @Nonnegative int totalTicksOfFetalMovement = PregnancySystemHelper.TOTAL_TICKS_KICKING_P3;
	private int randomWeaknessCooldown = 0;
	private final int randomWeaknessTotalTicks;
	private final Species pregnancyType;
	
	public PlayerPregnancySystemP3(@NonNull ServerPlayer player) {
		super(player);
		Species tempPregnancyType = PlayerHelper.addInterspeciesPregnancy(player);
		int tempRandomWeaknessTotalTicks = 3600;
		int phase = pregnancySystem.getCurrentPregnancyPhase().ordinal();
		pregnancyType = tempPregnancyType != null ? tempPregnancyType : Species.HUMAN;
		
		if (pregnancyType != null) {
			int extra = phase * 20;
			switch(pregnancyType) {
				case ZOMBIE:
					fetalMovementProb *= 1.4f;
					tempRandomWeaknessTotalTicks = 4200 - extra;
					pregnancyExhaustion *= 1.05;
					break;
				case CREEPER:
					fetalMovementProb *= 1.8f;
					tempRandomWeaknessTotalTicks = 4400 - extra;
					pregnancyExhaustion *= 1.15;
					break;
				case ENDER:
					fetalMovementProb *= 2.2f;
					tempRandomWeaknessTotalTicks = 4600 - extra;
					pregnancyExhaustion *= 1.25;
					break;
				case DRAGON:
					fetalMovementProb *= 2.6f;
					tempRandomWeaknessTotalTicks = 5200 - extra;
					pregnancyExhaustion *= 1.4;
					break;
				default:
					tempRandomWeaknessTotalTicks = 4800 - extra;
					break;
			}
		}	
		this.randomWeaknessTotalTicks = tempRandomWeaknessTotalTicks;
		addNewValidPregnancySymptoms(PregnancySymptom.BELLY_RUBS);		
	}

	@Override
	protected void initPregnancyTimers() {
		totalTicksOfCraving = MinepreggoModConfig.SERVER.getTotalTicksOfCravingP3();
		totalTicksOfMilking = MinepreggoModConfig.SERVER.getTotalTicksOfMilkingP3();
		morningSicknessProb = PregnancySystemHelper.HIGH_MORNING_SICKNESS_PROBABILITY;
		pregnancyExhaustion = 0.03f;
	}
	
	@Override
	protected void evaluatePregnancySystem() {
		super.evaluatePregnancySystem();
		tryPlayStomachGrowlsSound();
	}
	
	@Override
	protected void evaluatePregnancyNeeds() {	
		super.evaluatePregnancyNeeds();
		evaluateBellyRubsTimer();
	}
	
	@Override
	protected void evaluatePregnancySymptoms() {
		super.evaluatePregnancySymptoms();
		if (pregnancySystem.getPregnancySymptoms().contains(PregnancySymptom.BELLY_RUBS) && pregnancySystem.getBellyRubs() <= PregnancySystemHelper.DESACTIVATEL_BELLY_RUBS_SYMPTOM) {	
			pregnancySystem.getPregnancySymptoms().remove(PregnancySymptom.BELLY_RUBS);
			pregnantEntity.removeEffect(MinepreggoMobEffects.BELLY_RUBS.get());
			pregnancySystem.syncState(pregnantEntity);
			pregnancySystem.syncEffect(pregnantEntity);	
			MinepreggoMod.LOGGER.debug("Player {} pregnancy symptom cleared: {}",
					pregnantEntity.getGameProfile().getName(), PregnancySymptom.BELLY_RUBS.name());
		}
	}
	
	protected void evaluateBellyRubsTimer() {   				
		if (pregnancySystem.getBellyRubs() < PregnancySystemHelper.MAX_BELLY_RUBBING_LEVEL) {
	        if (pregnancySystem.getBellyRubsTimer() >= totalTicksOfBellyRubs) {
	        	pregnancySystem.incrementBellyRubs();
	        	pregnancySystem.resetBellyRubsTimer();
	        	pregnancySystem.syncEffect(pregnantEntity);
	        	
	        	MinepreggoMod.LOGGER.debug("Player {} belly rubs level increased to: {}", 
	        			pregnantEntity.getGameProfile().getName(), pregnancySystem.getBellyRubs());
	        }
	        else {
	        	pregnancySystem.incrementBellyRubsTimer();
	        }
		}	
	}
	
	@Override
	protected boolean tryInitRandomPregnancyPain() {
		if (super.tryInitRandomPregnancyPain()) {
			return true;
		}		
		
		float newFetalMovementProb = fetalMovementProb;
		
		if (this.pregnantEntity.hasEffect(MinepreggoMobEffects.ETERNAL_PREGNANCY.get())) {
			newFetalMovementProb *= 5f;
		}
		if (this.isMovingRidingSaddledHorse()) {		
			newFetalMovementProb *= (pregnancySystem.getCurrentPregnancyPhase().ordinal() + 2);
		}
		
		if (pregnantEntity.getRandom().nextFloat() < newFetalMovementProb) {
			this.initCommonPregnancyPain(PregnancyPain.FETAL_MOVEMENT, totalTicksOfFetalMovement, 0);
			return true;
		}	

		return false;
	}
	
	@Override
	protected boolean tryInitPregnancySymptom() {
		if (super.tryInitPregnancySymptom()) {
			return true;
		} 	
		if (pregnancySystem.getBellyRubs() >= PregnancySystemHelper.MAX_BELLY_RUBBING_LEVEL
				&& !pregnancySystem.getPregnancySymptoms().contains(PregnancySymptom.BELLY_RUBS)) {
			this.initPregnancySymptom(PregnancySymptom.BELLY_RUBS);
			return true;
		}

		return false;
	}
	
	@Override
	protected void evaluateRandomWeakness() {
		if (this.randomWeaknessCooldown < this.randomWeaknessTotalTicks) {
			++this.randomWeaknessCooldown;		
		}
		else if (this.pregnantEntity.getRandom().nextFloat() < 0.4) {
			this.randomWeaknessCooldown = 0;
			pregnantEntity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 1200, 0, false, true, true));
		}
	}
}
