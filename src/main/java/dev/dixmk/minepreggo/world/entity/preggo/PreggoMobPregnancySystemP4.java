package dev.dixmk.minepreggo.world.entity.preggo;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.network.capability.IPregnancyEffectsHandler;
import dev.dixmk.minepreggo.network.capability.IPregnancySystemHandler;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;

public abstract class PreggoMobPregnancySystemP4<E extends PreggoMob
	& ITamablePreggoMob & IPregnancySystemHandler & IPregnancyEffectsHandler> extends PreggoMobPregnancySystemP3<E> {
	
	protected @Nonnegative int totalTicksOfHorny = MinepreggoModConfig.getTotalTicksOfHornyP4();
	protected @Nonnegative int totalTicksOfBirth = PregnancySystemHelper.TOTAL_TICKS_BIRTH_P4;
	protected @Nonnegative int totalTicksOfPreBirth = PregnancySystemHelper.TOTAL_TICKS_PREBIRTH_P4;
	
	protected PreggoMobPregnancySystemP4(@Nonnull E preggoMob) {
		super(preggoMob);
		totalTicksOfCraving = MinepreggoModConfig.getTotalTicksOfCravingP4();
		totalTicksOfMilking = MinepreggoModConfig.getTotalTicksOfMilkingP4();
		totalTicksOfBellyRubs = MinepreggoModConfig.getTotalTicksOfBellyRubsP4();
	}
	
	@Override
	protected void evaluatePregnancyEffects() {	
		super.evaluatePregnancyEffects();
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
		
		if (canAdvanceNextPregnancyPhase() && hasToGiveBirth()) {
			startLabor();
			return;
		}
		
		super.evaluatePregnancySystem();
	}
	
	
	@Override
	protected void evaluateBirth(ServerLevel serverLevel) {		
		
		final var pain = pregnantEntity.getPregnancyPain();
		
		if (pain == PregnancyPain.PREBIRTH) {		
			if (pregnantEntity.getPregnancyPainTimer() >= totalTicksOfPreBirth) {
				pregnantEntity.setPregnancyPain(PregnancyPain.BIRTH);
	    		pregnantEntity.resetPregnancyPainTimer();   		
				PreggoMobHelper.removeAndDropItemStackFromEquipmentSlot(pregnantEntity, EquipmentSlot.CHEST);
				PreggoMobHelper.removeAndDropItemStackFromEquipmentSlot(pregnantEntity, EquipmentSlot.LEGS);
				PreggoMobHelper.removeAndDropItemStackFromEquipmentSlot(pregnantEntity, EquipmentSlot.MAINHAND);
				PreggoMobHelper.removeAndDropItemStackFromEquipmentSlot(pregnantEntity, EquipmentSlot.OFFHAND);
			}	
			else {
	    		pregnantEntity.incrementPregnancyTimer();
	    		for (ServerPlayer player : serverLevel.getServer().getPlayerList().getPlayers()) {
	    		    if (player.distanceToSqr(pregnantEntity) <= 512.0) { // 32 blocks
	    				serverLevel.sendParticles(player, ParticleTypes.FALLING_DRIPSTONE_WATER, true, pregnantEntity.getX(), (pregnantEntity.getY() + pregnantEntity.getBbHeight() * 0.35), pregnantEntity.getZ(),
	    						1, 0, 1, 0, 0.02);
	    		    }
	    		}
			}
		}
		else if (pain == PregnancyPain.BIRTH) {
			if (pregnantEntity.getPregnancyPainTimer() >= totalTicksOfBirth) {
				
				final var babyItem = PregnancySystemHelper.getAliveBabyItem(pregnantEntity.getDefaultTypeOfBaby());
				
				if (babyItem != null) {
					PreggoMobHelper.setItemstackOnOffHand(pregnantEntity, new ItemStack(babyItem, IBreedable.getOffspringsByMaxPregnancyStage(pregnantEntity.getLastPregnancyStage())));
				} else {
					MinepreggoMod.LOGGER.error("Failed to get baby item for pregnancy system {} birth.", pregnantEntity.getCurrentPregnancyStage());
				}
				
				initPostPartum();
	        	pregnantEntity.discard();
			}	
			else {
	    		pregnantEntity.incrementPregnancyTimer();
			}
		}
	}
	
	@Override
	protected void startLabor() {
		pregnantEntity.setPregnancyPain(PregnancyPain.PREBIRTH);
		pregnantEntity.resetPregnancyPainTimer();
		pregnantEntity.setPickUpItems(false);
		pregnantEntity.setBreakBlocks(false);
		MinepreggoMod.LOGGER.debug("PreggoMob {} has started labor.", pregnantEntity.getDisplayName().getString());	
	}
	
	@Override
	public boolean canBeAngry() {
		return super.canBeAngry() || pregnantEntity.getHorny() >= 20;	
	}
	
	@Override
	protected boolean tryInitPregnancySymptom() {
		if (super.tryInitPregnancySymptom()) {
			return true;
		}
		if (pregnantEntity.getHorny() >= PregnancySystemHelper.ACTIVATE_HORNY_SYMPTOM) {
	    	pregnantEntity.setPregnancySymptom(PregnancySymptom.HORNY);
	    	return true;		
		}
		return false;
	}
	
	@Override
	protected boolean tryInitRandomPregnancyPain() {
	    if (randomSource.nextFloat() < PregnancySystemHelper.LOW_MORNING_SICKNESS_PROBABILITY) {
	        pregnantEntity.setPregnancyPain(PregnancyPain.MORNING_SICKNESS);
	        pregnantEntity.resetPregnancyPainTimer();
	        return true;
	    }
		else if (randomSource.nextFloat() < PregnancySystemHelper.MEDIUM_PREGNANCY_PAIN_PROBABILITY) {
			
			if (hasToGiveBirth()) {
				pregnantEntity.setPregnancyPain(PregnancyPain.CONTRACTION);
			}		
			else {
				pregnantEntity.setPregnancyPain(PregnancyPain.KICKING);
			}

			pregnantEntity.resetPregnancyPainTimer();
			PreggoMobHelper.removeAndDropItemStackFromEquipmentSlot(pregnantEntity, EquipmentSlot.CHEST);					
			return true;
		}     
	    return false;
	}
	
	@Override
	protected void evaluatePregnancyPains() {
		final var pain = pregnantEntity.getPregnancyPain();
	
		if ((pain == PregnancyPain.MORNING_SICKNESS && pregnantEntity.getPregnancyPainTimer() >= PregnancySystemHelper.TOTAL_TICKS_MORNING_SICKNESS)
				|| (pain == PregnancyPain.KICKING && pregnantEntity.getPregnancyPainTimer() >= PregnancySystemHelper.TOTAL_TICKS_KICKING_P4)
				|| (pain == PregnancyPain.CONTRACTION && pregnantEntity.getPregnancyPainTimer() >= PregnancySystemHelper.TOTAL_TICKS_CONTRACTION_P4)) {
			pregnantEntity.clearPregnancyPain();
			pregnantEntity.resetPregnancyPainTimer();
		}
		else {
			pregnantEntity.incrementPregnancyPainTimer();
		}
	}
	
	protected void evaluateHornyTimer() {
		if (pregnantEntity.getHorny() < PregnancySystemHelper.MAX_HORNY_LEVEL) {
	        if (pregnantEntity.getHornyTimer() >= totalTicksOfHorny) {
	        	pregnantEntity.incrementHorny();
	        	pregnantEntity.resetHornyTimer();
	        }
	        else {
	        	pregnantEntity.incrementHornyTimer();
	        }
		}	
	}
}
