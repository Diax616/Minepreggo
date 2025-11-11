package dev.dixmk.minepreggo.world.entity.preggo;

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

public abstract class PregnancySystemP4<E extends PreggoMob
	& ITamablePreggoMob & IPregnancySystemHandler & IPregnancyEffectsHandler> extends PregnancySystemP3<E> {
	
	protected PregnancySystemP4(@Nonnull E preggoMob) {
		super(preggoMob);
	}
	
	protected void evaluateBirth(ServerLevel serverLevel, final int totalTicksOfPrebirth, final int totalTicksOfBirth) {		
		
		final var pain = preggoMob.getPregnancyPain();
		
		if (pain == PregnancyPain.PREBIRTH) {		
			if (preggoMob.getPregnancyPainTimer() >= totalTicksOfPrebirth) {
				preggoMob.setPregnancyPain(PregnancyPain.BIRTH);
	    		preggoMob.resetPregnancyPainTimer();   		
				PreggoMobHelper.removeAndDropItemStackFromEquipmentSlot(preggoMob, EquipmentSlot.CHEST);
				PreggoMobHelper.removeAndDropItemStackFromEquipmentSlot(preggoMob, EquipmentSlot.MAINHAND);
				PreggoMobHelper.removeAndDropItemStackFromEquipmentSlot(preggoMob, EquipmentSlot.OFFHAND);
			}	
			else {
	    		preggoMob.incrementPregnancyTimer();
	    		for (ServerPlayer player : serverLevel.getServer().getPlayerList().getPlayers()) {
	    		    if (player.distanceToSqr(preggoMob) <= 512.0) { // 32 blocks
	    				serverLevel.sendParticles(player, ParticleTypes.FALLING_DRIPSTONE_WATER, true, preggoMob.getX(), (preggoMob.getY() + preggoMob.getBbHeight() * 0.35), preggoMob.getZ(),
	    						1, 0, 1, 0, 0.02);
	    		    }
	    		}
			}
		}
		else if (pain == PregnancyPain.BIRTH) {
			if (preggoMob.getPregnancyPainTimer() >= totalTicksOfBirth) {
				
				final var babyItem = PregnancySystemHelper.getAliveBabyItem(preggoMob.getDefaultTypeOfBaby());
				
				if (babyItem != null) {
				    PreggoMobHelper.setItemstackOnOffHand(preggoMob, new ItemStack(babyItem, IBreedable.getOffspringsByMaxPregnancyStage(preggoMob.getLastPregnancyStage())));
				} else {
					MinepreggoMod.LOGGER.error("Failed to get baby item for pregnancy system {} birth.", preggoMob.getCurrentPregnancyStage());
				}
				
				initPostBirth();
	        	preggoMob.discard();
			}	
			else {
	    		preggoMob.incrementPregnancyTimer();
			}
		}
	}
	
	protected final boolean hasToGiveBirth() {
		return preggoMob.getLastPregnancyStage() == preggoMob.getCurrentPregnancyStage();
	}
	
	protected boolean isInLabor() {
		final var pain = preggoMob.getPregnancyPain();
		return pain == PregnancyPain.PREBIRTH || pain == PregnancyPain.BIRTH;
 	}
	
	protected void startLabor() {
		preggoMob.setPregnancyPain(PregnancyPain.PREBIRTH);
		preggoMob.resetPregnancyPainTimer();
		preggoMob.setPickUpItems(false);
		preggoMob.setBreakBlocks(false);
	}
	
	@Override
	public boolean canBeAngry() {
		return super.canBeAngry() || preggoMob.getHorny() >= 20;	
	}
	
	@Override
	protected boolean tryInitPregnancySymptom() {
		if (super.tryInitPregnancySymptom()) {
			return true;
		}
		if (preggoMob.getHorny() >= PregnancySystemHelper.ACTIVATE_HORNY_SYMPTOM) {
	    	preggoMob.setPregnancySymptom(PregnancySymptom.HORNY);
	    	return true;		
		}
		return false;
	}
	
	@Override
	protected boolean tryInitRandomPregnancyPain() {
	    if (randomSource.nextFloat() < PregnancySystemHelper.LOW_MORNING_SICKNESS_PROBABILITY) {
	        preggoMob.setPregnancyPain(PregnancyPain.MORNING_SICKNESS);
	        preggoMob.resetPregnancyPainTimer();
	        return true;
	    }
		else if (randomSource.nextFloat() < PregnancySystemHelper.MEDIUM_PREGNANCY_PAIN_PROBABILITY) {
			
			if (hasToGiveBirth()) {
				preggoMob.setPregnancyPain(PregnancyPain.CONTRACTION);
			}		
			else {
				preggoMob.setPregnancyPain(PregnancyPain.KICKING);
			}

			preggoMob.resetPregnancyPainTimer();
			PreggoMobHelper.removeAndDropItemStackFromEquipmentSlot(preggoMob, EquipmentSlot.CHEST);					
			return true;
		}     
	    return false;
	}
	
	@Override
	protected void evaluatePregnancyPains() {
		final var pain = preggoMob.getPregnancyPain();
	
		if ((pain == PregnancyPain.MORNING_SICKNESS && preggoMob.getPregnancyPainTimer() >= PregnancySystemHelper.TOTAL_TICKS_MORNING_SICKNESS)
				|| (pain == PregnancyPain.KICKING && preggoMob.getPregnancyPainTimer() >= PregnancySystemHelper.TOTAL_TICKS_KICKING_P4)
				|| (pain == PregnancyPain.CONTRACTION && preggoMob.getPregnancyPainTimer() >= PregnancySystemHelper.TOTAL_TICKS_CONTRACTION_P4)) {
			preggoMob.clearPregnancyPain();
			preggoMob.resetPregnancyPainTimer();
		}
		else {
			preggoMob.incrementPregnancyPainTimer();
		}
	}
	
	protected void evaluateHornyTimer(final int totalTicksOfHorny) {
		if (preggoMob.getHorny() < PregnancySystemHelper.MAX_HORNY_LEVEL) {
	        if (preggoMob.getHornyTimer() >= totalTicksOfHorny) {
	        	preggoMob.incrementHorny();
	        	preggoMob.resetHornyTimer();
	        }
	        else {
	        	preggoMob.incrementHornyTimer();
	        }
		}	
	}

	@Override
	public void onServerTick() {
		final var level = preggoMob.level();	
		if (level.isClientSide()) {			
			return;
		}
		
		if (isInLabor()) {		
			if (level instanceof ServerLevel serverLevel) {
				evaluateBirth(serverLevel, PregnancySystemHelper.TOTAL_TICKS_PREBIRTH_P4, PregnancySystemHelper.TOTAL_TICKS_BIRTH_P4);
			}		
			return;
		}
		
		if (isMiscarriageActive()) {
			if (level instanceof ServerLevel serverLevel) {
				evaluateMiscarriage(serverLevel, preggoMob.getX(), preggoMob.getY(), preggoMob.getZ(), PregnancySystemHelper.TOTAL_TICKS_MISCARRIAGE);
			}		
			return;
		}
		
		evaluatePregnancyTimer();
		if (canAdvanceNextPregnancyPhase()) {			
			if (hasToGiveBirth()) {
				startLabor();
			}
			else {
				advanceToNextPregnancyPhase();
				preggoMob.discard();
			}
			return;
		}

		evaluateCravingTimer(MinepreggoModConfig.getTotalTicksOfCravingP4());
		evaluateMilkingTimer(MinepreggoModConfig.getTotalTicksOfMilkingP4());
		evaluateBellyRubsTimer(MinepreggoModConfig.getTotalTicksOfBellyRubsP4());
		evaluateHornyTimer(MinepreggoModConfig.getTotalTicksOfHornyP4());
		
		if (!hasPregnancyPain()) {
			tryInitRandomPregnancyPain();	
		}
		else {
			evaluatePregnancyPains();
		}
		
		if (!hasPregnancySymptom()) {
			tryInitPregnancySymptom();
		}
		else {
			evaluatePregnancySymptoms();
		}
			
		evaluateAngry(level, preggoMob.getX(), preggoMob.getY(), preggoMob.getZ(), PregnancySystemHelper.MEDIUM_ANGER_PROBABILITY);		
	}
	
	
	protected abstract void initPostBirth();
}
