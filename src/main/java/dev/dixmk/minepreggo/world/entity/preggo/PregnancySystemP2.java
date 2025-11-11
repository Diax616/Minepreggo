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
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.registries.ForgeRegistries;

public abstract class PregnancySystemP2<E extends PreggoMob
	& ITamablePreggoMob & IPregnancySystemHandler & IPregnancyEffectsHandler> extends PregnancySystemP1<E> {
	
	protected PregnancySystemP2(@Nonnull E preggoMob) {
		super(preggoMob);
	}
	
	protected void evaluateMilkingTimer(final int totalTicksOfMilking) {
		if (preggoMob.getMilking() < PregnancySystemHelper.MAX_MILKING_LEVEL) {
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
		if (preggoMob.getMilking() >= PregnancySystemHelper.ACTIVATE_MILKING_SYMPTOM) {
	    	preggoMob.setPregnancySymptom(PregnancySymptom.MILKING);
	    	return true;		
		}
		return false;
	}
	
	@Override
	protected boolean tryInitRandomPregnancyPain() {
	    if (randomSource.nextFloat() < PregnancySystemHelper.MEDIUM_MORNING_SICKNESS_PROBABILITY) {
	        preggoMob.setPregnancyPain(PregnancyPain.MORNING_SICKNESS);
	        preggoMob.resetPregnancyPainTimer();
	        return true;
	    }
	    return false;
	}
	
	@Override
	public boolean canBeAngry() {
		return super.canBeAngry() || preggoMob.getMilking() >= PregnancySystemHelper.MAX_MILKING_LEVEL;
	}
	
	@Override
	public void onServerTick() {
		final var level = preggoMob.level();	
		if (level.isClientSide()) {			
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
			advanceToNextPregnancyPhase();
			preggoMob.discard();
			return;
		}
		
		evaluateCravingTimer(MinepreggoModConfig.getTotalTicksOfCravingP2());
		evaluateMilkingTimer(MinepreggoModConfig.getTotalTicksOfMilkingP2());
		
		if (!hasPregnancyPain()) {
			if (pregnancyPainTicks > 40) {
				tryInitRandomPregnancyPain();
				pregnancyPainTicks = 0;
			}
			else {
				++pregnancyPainTicks;
			}
		}
		else {
			evaluatePregnancyPains();
		}
		
		if (!hasPregnancySymptom()) {
			if (pregnancysymptonsTicks > 40) {
				tryInitPregnancySymptom();
				pregnancysymptonsTicks = 0;
			}
			else {
				++pregnancysymptonsTicks;
			}
		}
		else {
			evaluatePregnancySymptoms();
		}
			
		evaluateAngry(level, preggoMob.getX(), preggoMob.getY(), preggoMob.getZ(), PregnancySystemHelper.LOW_ANGER_PROBABILITY);		
	}
	
	@Override
	public InteractionResult onRightClick(Player source) {		
		if (!preggoMob.isOwnedBy(source) || preggoMob.isIncapacitated()) {
			return InteractionResult.FAIL;
		}				
		var level = preggoMob.level();

		// Belly rubs has priority over other right click actions
		if (canOwnerRubBelly(source) && level instanceof ServerLevel serverLevel) {
			PreggoMobSystem.spawnParticles(preggoMob, serverLevel, Result.FAIL);
			return InteractionResult.sidedSuccess(level.isClientSide);
		}
		
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
		
	    if (currentMilking >= PregnancySystemHelper.MILKING_VALUE
	    		&& mainHandItem == Items.GLASS_BOTTLE) {    	

            source.getInventory().clearOrCountMatchingItems(p -> mainHandItem == p.getItem(), 1, source.inventoryMenu.getCraftSlots());
            currentMilking = Math.max(0, currentMilking - PregnancySystemHelper.MILKING_VALUE);
            preggoMob.setMilking(currentMilking);               
            var milkItem = PregnancySystemHelper.getMilkItem(preggoMob.getSpecies());
           
            if (milkItem != null) {
            	ItemHandlerHelper.giveItemToPlayer(source, new ItemStack(milkItem));
			} else {
				MinepreggoMod.LOGGER.warn("Milk item is null for species: {}", preggoMob.getSpecies());
			}
            
            if (!level.isClientSide) {
            	level.playSound(null, BlockPos.containing(preggoMob.getX(), preggoMob.getY(), preggoMob.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.withDefaultNamespace("entity.cow.milk")), SoundSource.NEUTRAL, 0.75f, 1);	
         
                if (preggoMob.getPregnancySymptom() == PregnancySymptom.MILKING
                		&& currentMilking <= PregnancySystemHelper.DESACTIVATE_MILKING_SYMPTOM) {
        	    	preggoMob.clearPregnancySymptom();
                } 
            }
         
            return Result.SUCCESS;   
	    }
		
	    return null;
	}
}
