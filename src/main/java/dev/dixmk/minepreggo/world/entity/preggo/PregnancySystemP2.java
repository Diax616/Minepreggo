package dev.dixmk.minepreggo.world.entity.preggo;

import javax.annotation.Nonnull;

import org.jetbrains.annotations.Nullable;

import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.network.capability.IPregnancyEffectsHandler;
import dev.dixmk.minepreggo.network.capability.IPregnancySystemHandler;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobSystem.Result;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;

public abstract class PregnancySystemP2<E extends PreggoMob
	& ITamablePreggoMob & IPregnancySystemHandler & IPregnancyEffectsHandler> extends PregnancySystemP1<E> {
	
	protected PregnancySystemP2(@Nonnull E preggoMob) {
		super(preggoMob);
	}
	
	protected void evaluateMilkingTimer(final int totalTicksOfMilking) {
		if (preggoMob.getMilking() < PregnancySystemConstants.MAX_MILKING_LEVEL) {
	        if (preggoMob.getMilkingTimer() >= totalTicksOfMilking) {
	        	preggoMob.incrementMilking();
	        	preggoMob.resetMilkingTimer();
	        }
	        else {
	        	preggoMob.incrementMilkingTimer();
	        }
		}	
	}

	@Override
	protected boolean tryInitPregnancySymptom() {
		if (super.tryInitPregnancySymptom()) {
			return true;
		}
		if (preggoMob.getMilking() >= PregnancySystemConstants.ACTIVATE_MILKING_SYMPTOM) {
	    	preggoMob.setPregnancySymptom(PregnancySymptom.MILKING);
	    	return true;		
		}
		return false;
	}
	
	@Override
	protected boolean tryInitRandomPregnancyPain() {
	    if (randomSource.nextFloat() < PregnancySystemConstants.MEDIUM_MORNING_SICKNESS_PROBABILITY) {
	        preggoMob.setPregnancyPain(PregnancyPain.MORNING_SICKNESS);
	        preggoMob.resetPregnancyPainTimer();
	        return true;
	    }
	    return false;
	}
	
	@Override
	public boolean canBeAngry() {
		return super.canBeAngry() || preggoMob.getMilking() >= PregnancySystemConstants.MAX_MILKING_LEVEL;
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
		
		evaluateCravingTimer(MinepreggoModConfig.getTotalTicksOfCravingP2());
		evaluateMilkingTimer(MinepreggoModConfig.getTotalTicksOfMilkingP2());
		
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
			return InteractionResult.PASS;
		}
		
	
		if (level instanceof ServerLevel serverLevel) {
			PreggoMobSystem.spawnParticles(preggoMob, serverLevel, result);
		}
		return InteractionResult.sidedSuccess(level.isClientSide);		
	}
	
	@Nullable
	public Result evaluateMilking(Level level, Player source) {
			
	    var mainHandItem = source.getMainHandItem().getItem();
	    var currentMilking = preggoMob.getMilking();
		
	    if (currentMilking >= PregnancySystemConstants.MILKING_VALUE
	    		&& mainHandItem == Items.GLASS_BOTTLE) {    	

            source.getInventory().clearOrCountMatchingItems(p -> mainHandItem == p.getItem(), 1, source.inventoryMenu.getCraftSlots());
            currentMilking = Math.max(0, currentMilking - PregnancySystemConstants.MILKING_VALUE);
            preggoMob.setMilking(currentMilking);
                
            if (!level.isClientSide) {
            	level.playSound(null, BlockPos.containing(preggoMob.getX(), preggoMob.getY(), preggoMob.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.withDefaultNamespace("entity.cow.milk")), SoundSource.NEUTRAL, 0.75f, 1);	
           
                if (preggoMob.getPregnancySymptom() == PregnancySymptom.MILKING
                		&& currentMilking <= PregnancySystemConstants.DESACTIVATE_MILKING_SYMPTOM) {
        	    	preggoMob.clearPregnancySymptom();
                } 
            }
         
            return Result.SUCCESS;   
	    }
		
	    return null;
	}
}
