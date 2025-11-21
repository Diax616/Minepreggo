package dev.dixmk.minepreggo.world.entity.preggo;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import org.jetbrains.annotations.Nullable;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.network.capability.IPregnancyEffectsHandler;
import dev.dixmk.minepreggo.network.capability.IPregnancySystemHandler;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobSystem.Result;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;

public abstract class PreggoMobPregnancySystemP3 <E extends PreggoMob
	& ITamablePreggoMob & IPregnancySystemHandler & IPregnancyEffectsHandler> extends PreggoMobPregnancySystemP2<E> {

	protected @Nonnegative int totalTicksOfBellyRubs = MinepreggoModConfig.getTotalTicksOfBellyRubsP3();

	protected PreggoMobPregnancySystemP3(@Nonnull E preggoMob) {
		super(preggoMob);
		totalTicksOfCraving = MinepreggoModConfig.getTotalTicksOfCravingP3();
		totalTicksOfMilking = MinepreggoModConfig.getTotalTicksOfMilkingP3();
	}
	
	@Override
	protected void evaluatePregnancyNeeds() {	
		super.evaluatePregnancyNeeds();
		evaluateBellyRubsTimer();
	}
	
	protected void evaluateBellyRubsTimer() {
		if (pregnantEntity.getBellyRubs() < PregnancySystemHelper.MAX_BELLY_RUBBING_LEVEL) {
	        if (pregnantEntity.getBellyRubsTimer() >= totalTicksOfBellyRubs) {
	        	pregnantEntity.incrementBellyRubs();
	        	pregnantEntity.resetBellyRubsTimer();
	        }
	        else {
	        	pregnantEntity.incrementBellyRubsTimer();
	        }
		}	
	}
	
	@Override
	protected boolean tryInitPregnancySymptom() {
		if (super.tryInitPregnancySymptom()) {
			return true;
		}
		if (pregnantEntity.getBellyRubs() >= PregnancySystemHelper.ACTIVATE_BELLY_RUBS_SYMPTOM) {
	    	pregnantEntity.setPregnancySymptom(PregnancySymptom.BELLY_RUBS);
	    	return true;		
		}
		return false;
	}
	
	@Override
	protected boolean tryInitRandomPregnancyPain() {
	    if (randomSource.nextFloat() < PregnancySystemHelper.HIGH_MORNING_SICKNESS_PROBABILITY) {
	        pregnantEntity.setPregnancyPain(PregnancyPain.MORNING_SICKNESS);
	        pregnantEntity.resetPregnancyPainTimer();
	        return true;
	    }
		else if (randomSource.nextFloat() < PregnancySystemHelper.LOW_PREGNANCY_PAIN_PROBABILITY) {
			pregnantEntity.setPregnancyPain(PregnancyPain.FETAL_MOVEMENT);
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
				|| (pain == PregnancyPain.FETAL_MOVEMENT && pregnantEntity.getPregnancyPainTimer() >= PregnancySystemHelper.TOTAL_TICKS_KICKING_P3)) {
			pregnantEntity.clearPregnancyPain();
			pregnantEntity.resetPregnancyPainTimer();
		}
		else {
			pregnantEntity.incrementPregnancyPainTimer();
		}
	}
	
	@Override
	public boolean canBeAngry() {
		return super.canBeAngry() || pregnantEntity.getBellyRubs() >= PregnancySystemHelper.MAX_BELLY_RUBBING_LEVEL;
	}
	
	@Nullable
	@Override
	protected Result evaluateBellyRubs(Level level, Player source) {	
		if (!level.isClientSide) {
			level.playSound(null, BlockPos.containing(pregnantEntity.getX(), pregnantEntity.getY(), pregnantEntity.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "belly_touch")), SoundSource.NEUTRAL, 0.75F, 1);
		}
		
		var currentBellyRubs = pregnantEntity.getBellyRubs();
	
		if (currentBellyRubs > 0) {					
			currentBellyRubs = Math.max(0, currentBellyRubs - PregnancySystemHelper.BELLY_RUBBING_VALUE);			
			pregnantEntity.setBellyRubs(currentBellyRubs);
						
			if (!level.isClientSide && pregnantEntity.getPregnancySymptom() == PregnancySymptom.BELLY_RUBS
					&& currentBellyRubs <= PregnancySystemHelper.DESACTIVATE_FULL_BELLY_RUBS_STAGE) {									
				pregnantEntity.clearPregnancySymptom();							
			}	
			
			return Result.SUCCESS;
		}		
		
		return Result.ANGRY;
	}
}
