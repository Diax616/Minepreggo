package dev.dixmk.minepreggo.world.entity.preggo;

import javax.annotation.Nonnull;

import org.jetbrains.annotations.Nullable;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.network.capability.IPregnancyEffectsHandler;
import dev.dixmk.minepreggo.network.capability.IPregnancySystemHandler;
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
	& ITamablePreggoMob & IPregnancySystemHandler & IPregnancyEffectsHandler> extends PregnancySystemP2<E> {

	protected PregnancySystemP3(@Nonnull E preggoMob) {
		super(preggoMob);
	}
	
	protected void evaluateBellyRubsTimer(final int totalTicksOfBellyRubs) {
		if (preggoMob.getBellyRubs() < PregnancySystemConstants.MAX_BELLY_RUBBING_LEVEL) {
	        if (preggoMob.getBellyRubsTimer() >= totalTicksOfBellyRubs) {
	        	preggoMob.incrementBellyRubs();
	        	preggoMob.resetBellyRubsTimer();
	        }
	        else {
	        	preggoMob.incrementBellyRubsTimer();
	        }
		}	
	}
	
	
	@Override
	protected boolean tryInitPregnancySymptom() {
		if (super.tryInitPregnancySymptom()) {
			return true;
		}
		if (preggoMob.getBellyRubs() >= PregnancySystemConstants.ACTIVATE_BELLY_RUBS_SYMPTOM) {
	    	preggoMob.setPregnancySymptom(PregnancySymptom.BELLY_RUBS);
	    	return true;		
		}
		return false;
	}
	
	@Override
	protected boolean tryInitRandomPregnancyPain() {
	    if (randomSource.nextFloat() < PregnancySystemConstants.HIGH_MORNING_SICKNESS_PROBABILITY) {
	        preggoMob.setPregnancyPain(PregnancyPain.MORNING_SICKNESS);
	        preggoMob.resetPregnancyPainTimer();
	        return true;
	    }
		else if (randomSource.nextFloat() < PregnancySystemConstants.LOW_PREGNANCY_PAIN_PROBABILITY) {
			preggoMob.setPregnancyPain(PregnancyPain.KICKING);
			preggoMob.resetPregnancyPainTimer();
			PreggoMobHelper.removeAndDropItemStackFromEquipmentSlot(preggoMob, EquipmentSlot.CHEST);				
			return true;
		}     
	    return false;
	}
	
	
	@Override
	protected void evaluatePregnancyPains() {
		final var pain = preggoMob.getPregnancyPain();
	
		if ((pain == PregnancyPain.MORNING_SICKNESS && preggoMob.getPregnancyPainTimer() >= PregnancySystemConstants.TOTAL_TICKS_MORNING_SICKNESS)
				|| (pain == PregnancyPain.KICKING && preggoMob.getPregnancyPainTimer() >= PregnancySystemConstants.TOTAL_TICKS_KICKING_P3)) {
			preggoMob.clearPregnancyPain();
			preggoMob.resetPregnancyPainTimer();
		}
		else {
			preggoMob.incrementPregnancyPainTimer();
		}
	}
	
	@Override
	public boolean canBeAngry() {
		return super.canBeAngry() || preggoMob.getBellyRubs() >= PregnancySystemConstants.MAX_BELLY_RUBBING_LEVEL;
	}
	
	public boolean canOwnerRubBelly(Player source) {
		return source.isShiftKeyDown()
				&& source.getMainHandItem().isEmpty() 
				&& source.getDirection() == preggoMob.getDirection().getOpposite()
				&& preggoMob.getItemBySlot(EquipmentSlot.CHEST).isEmpty();
	}
	
	@Nullable
	protected Result evaluateBellyRubs(Level level, Player source) {	
	
		if (!canOwnerRubBelly(source)) {
			return null;
		}
			
		if (!level.isClientSide) {
			level.playSound(null, BlockPos.containing(preggoMob.getX(), preggoMob.getY(), preggoMob.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "belly_touch")), SoundSource.NEUTRAL, 0.75F, 1);
		}
		
		var currentBellyRubs = preggoMob.getBellyRubs();
	
		if (currentBellyRubs > 0) {					
			currentBellyRubs = Math.max(0, currentBellyRubs - PregnancySystemConstants.BELLY_RUBBING_VALUE);			
			preggoMob.setBellyRubs(currentBellyRubs);
						
			if (!level.isClientSide && preggoMob.getPregnancySymptom() == PregnancySymptom.BELLY_RUBS
					&& currentBellyRubs <= PregnancySystemConstants.DESACTIVATE_FULL_BELLY_RUBS_STAGE) {									
				preggoMob.clearPregnancySymptom();							
			}	
			
			return Result.SUCCESS;
		}		
		
		return Result.ANGRY;
	}
	
	
	
	
	@Override
	public void onServerTick() {
		final var level = preggoMob.level();	
		if (level.isClientSide()) {			
			return;
		}
	
		if (isMiscarriageActive()) {
			if (level instanceof ServerLevel serverLevel) {
				evaluateMiscarriage(serverLevel, preggoMob.getX(), preggoMob.getY(), preggoMob.getZ(), PregnancySystemConstants.TOTAL_TICKS_MISCARRIAGE);
			}		
			return;
		}
		
		evaluatePregnancyTimer();
		if (canAdvanceNextPregnancyPhase()) {
			advanceToNextPregnancyPhase();
			preggoMob.discard();
			return;
		}
		
		evaluateCravingTimer(MinepreggoModConfig.getTotalTicksOfCravingP3());
		evaluateMilkingTimer(MinepreggoModConfig.getTotalTicksOfMilkingP3());
		evaluateBellyRubsTimer(MinepreggoModConfig.getTotalTicksOfBellyRubsP3());
		
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
			
		evaluateAngry(level, preggoMob.getX(), preggoMob.getY(), preggoMob.getZ(), PregnancySystemConstants.LOW_ANGER_PROBABILITY);		
	}
	
	@Override
	public InteractionResult onRightClick(Player source) {		
		if (!preggoMob.isOwnedBy(source) || preggoMob.isIncapacitated()) {
			return InteractionResult.FAIL;
		}				
		var level = preggoMob.level();

		Result result = evaluateCraving(level, source);
		
		if (result == null) {			
			result =  evaluateMilking(level, source);
		}
		
		if (result == null) {
			result =  evaluateBellyRubs(level, source);
		}
		
		if (result == null) {
			return InteractionResult.PASS;
		}
		
	
		if (level instanceof ServerLevel serverLevel) {
			PreggoMobSystem.spawnParticles(preggoMob, serverLevel, result);
		}
		return InteractionResult.sidedSuccess(level.isClientSide);		
	}
	
}
