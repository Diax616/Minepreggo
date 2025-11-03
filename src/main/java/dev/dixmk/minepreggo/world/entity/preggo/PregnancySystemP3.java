package dev.dixmk.minepreggo.world.entity.preggo;

import javax.annotation.Nonnull;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobSystem.Result;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;

public abstract class PregnancySystemP3 <E extends PreggoMob
	& ITamablePreggoMob & IPregnancySystem & IPregnancyP3> extends PregnancySystemP2<E> {

	protected PregnancySystemP3(@Nonnull E preggoMob) {
		super(preggoMob);
	}
	
	protected void evaluateBellyRubsTimer(final int totalTicksOfBellyRubs) {
		if (preggoMob.getBellyRubs() < 20) {
	        if (preggoMob.getBellyRubsTimer() >= totalTicksOfBellyRubs) {
	        	preggoMob.setBellyRubs(preggoMob.getBellyRubs() + 1);
	        	preggoMob.setBellyRubsTimer(0);
	        }
	        else {
	        	preggoMob.setBellyRubsTimer(preggoMob.getBellyRubsTimer() + 1);
	        }
		}	
	}
	
	@Override
	protected void evaluatePregnancySymptoms() {		
		if (preggoMob.getPregnancySymptom() == PregnancySymptom.NONE) {		
			if (preggoMob.getCraving() >= PregnancySystemConstants.ACTIVATE_CRAVING_SYMPTOM) {		
				preggoMob.setPregnancySymptom(PregnancySymptom.CRAVING);	
			}
			else if (preggoMob.getMilking() >= PregnancySystemConstants.ACTIVATE_MILKING_SYMPTOM) {
				preggoMob.setPregnancySymptom(PregnancySymptom.MILKING);
			}
			else if (preggoMob.getBellyRubs() >= PregnancySystemConstants.ACTIVATE_BELLY_RUBS_SYMPTOM) {
				preggoMob.setPregnancySymptom(PregnancySymptom.BELLY_RUBS);
			}
		}
		else {
			if (preggoMob.getPregnancySymptom() == PregnancySymptom.CRAVING
					&& preggoMob.getTypeOfCraving() == Craving.NONE) {
				preggoMob.setTypeOfCraving(CravingFactory.getRandomCraving(randomSource));
			}
		}
	}
	
	@Override
	protected final void evaluatePregnancyPains() {
		
		final var pregnancyPain = preggoMob.getPregnancyPain();
		
		if (pregnancyPain == PregnancyPain.NONE) {				
			boolean flag = false;		
			if (randomSource.nextFloat() < PregnancySystemConstants.HIGH_MORNING_SICKNESS_PROBABILITY) {		
				preggoMob.setPregnancyPain(PregnancyPain.MORNING_SICKNESS);	
				flag = true;
			}
			else if (randomSource.nextFloat() < PregnancySystemConstants.LOW_PREGNANCY_PAIN_PROBABILITY) {
				preggoMob.setPregnancyPain(PregnancyPain.KICKING);
				PreggoMobHelper.removeAndDropItemStackFromEquipmentSlot(preggoMob, EquipmentSlot.CHEST);				
				flag = true;
			}	
			
			if (flag) {
				MinepreggoMod.LOGGER.debug("PREGNANCY PAIN ACTIVE: id={}, class={}, pregnancyPain={}",
						preggoMob.getId(), preggoMob.getClass().getSimpleName(), pregnancyPain);
			}	
		} 
		else {		
			final var pregnancyPainTimer = preggoMob.getPregnancyPainTimer();
			if ((pregnancyPain == PregnancyPain.MORNING_SICKNESS && pregnancyPainTimer >= PregnancySystemConstants.TOTAL_TICKS_MORNING_SICKNESS)
					|| (pregnancyPain == PregnancyPain.KICKING && pregnancyPainTimer >= PregnancySystemConstants.TOTAL_TICKS_KICKING_P3)) {
				preggoMob.setPregnancyPainTimer(0);
				preggoMob.setPregnancyPain(PregnancyPain.NONE);
			}			
			else {
				preggoMob.setPregnancyPainTimer(pregnancyPainTimer + 1);
			}
		}
	}
	
	
	@Override
	protected boolean canBeAngry() {
		return super.canBeAngry() || preggoMob.getBellyRubs() >= 20;
	}
	
	@Override
	public void evaluateOnTick() {		
		final var level = preggoMob.level();
		
		if (level.isClientSide()) {
			return;
		}
		
		if (evaluatePregnancyStageChange() == Result.SUCCESS) {
			return;
		}

		final var x =  preggoMob.getX();
		final var y = preggoMob.getY();
		final var z = preggoMob.getZ();
		
		if (level instanceof ServerLevel serverLevel
				&& evaluateMiscarriage(serverLevel, x, y, z, PregnancySystemConstants.TOTAL_TICKS_MISCARRIAGE) == Result.SUCCESS) {
			return; 
		}
		
		this.evaluatePregnancyTimer();
		this.evaluateCravingTimer(MinepreggoModConfig.getTotalTicksOfCravingP3());
		this.evaluateMilkingTimer(MinepreggoModConfig.getTotalTicksOfMilkingP3());
		this.evaluateBellyRubsTimer(MinepreggoModConfig.getTotalTicksOfBellyRubsP3());
		this.evaluateAngry(level, x, y, z, PregnancySystemConstants.MEDIUM_ANGER_PROBABILITY);	
		this.evaluatePregnancySymptoms();
		this.evaluatePregnancyPains();
	}
	

	@Override
	public InteractionResult evaluateRightClick(Player source) {		
		final var level = preggoMob.level();
		
		if (!preggoMob.isOwnedBy(source) || level.isClientSide() || !(level instanceof ServerLevel serverLevel)) {
			return InteractionResult.PASS;
		}				
		
		Result result;
		
		if ((result = evaluateCraving(serverLevel, source)) != Result.NOTHING
				|| (result = evaluateMilking(serverLevel, source)) != Result.NOTHING
				|| (result = evaluateBellyRubs(serverLevel, source)) != Result.NOTHING) {
			PreggoMobSystem.spawnParticles(preggoMob, serverLevel, result);
		}
		
		return PreggoMobSystem.onRightClickResult(result);
	}
	
	
	protected Result evaluateBellyRubs(Level level, Player source) {	
	
		if (!canOwnerRubBelly(source)) return Result.NOTHING;
						
		level.playSound(null, BlockPos.containing(preggoMob.getX(), preggoMob.getY(), preggoMob.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "belly_touch")), SoundSource.NEUTRAL, 0.75F, 1);
		var currentBellyRubs = preggoMob.getBellyRubs();
	
		if (currentBellyRubs > 0) {					
			currentBellyRubs = Math.max(0, currentBellyRubs - PregnancySystemConstants.BELLY_RUBBING_VALUE);			
			preggoMob.setBellyRubs(currentBellyRubs);
						
			if (preggoMob.getPregnancySymptom() == PregnancySymptom.BELLY_RUBS
					&& currentBellyRubs <= PregnancySystemConstants.DESACTIVATE_FULL_BELLY_RUBS_STAGE) {									
				preggoMob.setPregnancySymptom(PregnancySymptom.NONE);							
			}	
			
			return Result.SUCCESS;
		}		
		
		return Result.ANGRY;
	}
	
	protected boolean canOwnerRubBelly(Player source) {
		return source.getMainHandItem().isEmpty() 
				&& source.getDirection() == preggoMob.getDirection().getOpposite()
				&& preggoMob.getItemBySlot(EquipmentSlot.CHEST).isEmpty();
	}
}
