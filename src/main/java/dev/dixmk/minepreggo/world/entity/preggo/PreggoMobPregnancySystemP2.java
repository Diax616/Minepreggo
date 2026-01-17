package dev.dixmk.minepreggo.world.entity.preggo;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import org.jetbrains.annotations.Nullable;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.init.MinepreggoModSounds;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobSystem.Result;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySymptom;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import dev.dixmk.minepreggo.world.pregnancy.SyncedSetPregnancySymptom;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.ItemHandlerHelper;
																																																																																																														
public abstract class PreggoMobPregnancySystemP2
	<E extends PreggoMob & ITamablePregnantPreggoMob> extends PreggoMobPregnancySystemP1<E> {
	
	protected @Nonnegative int totalTicksOfMilking = MinepreggoModConfig.getTotalTicksOfMilkingP2();

	protected PreggoMobPregnancySystemP2(@Nonnull E preggoMob) {
		super(preggoMob);
		addNewValidPregnancySymptom(PregnancySymptom.MILKING);
	}
	
	@Override
	protected void initPregnancyTimers() {
		this.totalTicksOfCraving = MinepreggoModConfig.getTotalTicksOfCravingP2();
		this.morningSicknessProb = PregnancySystemHelper.MEDIUM_MORNING_SICKNESS_PROBABILITY;
	}
	
	@Override
	protected void evaluatePregnancyNeeds() {	
		super.evaluatePregnancyNeeds();
		evaluateMilkingTimer();
	}
	
	@Override
	public InteractionResult onRightClick(Player source) {		
		var retval = super.onRightClick(source);	
		if (retval != InteractionResult.PASS) {
			return retval;
		}			
		
		var level = pregnantEntity.level();	
		final Result result;
		
		if (!source.getMainHandItem().isEmpty() && (result = evaluateMilking(level, source)) != null) {			
			if (!level.isClientSide) {
				PreggoMobSystem.spawnParticles(pregnantEntity, result);
			}
			return InteractionResult.sidedSuccess(level.isClientSide);	
		}
		
		return InteractionResult.PASS;
	}
	
	protected void evaluateMilkingTimer() {
		final var pregnancyData = pregnantEntity.getPregnancyData();
		if (pregnancyData.getMilking() < PregnancySystemHelper.MAX_MILKING_LEVEL) {
	        if (pregnancyData.getMilkingTimer() >= totalTicksOfMilking) {
	        	pregnancyData.incrementMilking();
	        	pregnancyData.resetMilkingTimer();
	        }
	        else {
	        	pregnancyData.incrementMilkingTimer();
	        }
		}	
	}

	@Override
	protected boolean tryInitPregnancySymptom() {
		if (super.tryInitPregnancySymptom()) {
			return true;
		}
		final var pregnancyData = pregnantEntity.getPregnancyData();
		if (pregnancyData.getMilking() >= PregnancySystemHelper.ACTIVATE_MILKING_SYMPTOM
				&& !pregnancyData.getSyncedPregnancySymptoms().containsPregnancySymptom(PregnancySymptom.MILKING)) {
	    	
			pregnancyData.getSyncedPregnancySymptoms().addPregnancySymptom(PregnancySymptom.MILKING);
		
			MinepreggoMod.LOGGER.debug("Player {} has developed pregnancy symptom: {}, all pregnancy symptoms: {}",
					pregnantEntity.getSimpleName(), PregnancySymptom.MILKING, pregnancyData.getSyncedPregnancySymptoms().toSet());
	    	
	    	return true;		
		}
		return false;
	}
	
	@Override
	protected void evaluatePregnancySymptoms() {
		super.evaluatePregnancySymptoms();
		final var pregnancyData = pregnantEntity.getPregnancyData();
		SyncedSetPregnancySymptom pregnancySymptoms = pregnancyData.getSyncedPregnancySymptoms();	
		if (pregnancySymptoms.containsPregnancySymptom(PregnancySymptom.MILKING)
				&& pregnancyData.getMilking() <= PregnancySystemHelper.DESACTIVATE_MILKING_SYMPTOM) {
			pregnancySymptoms.removePregnancySymptom(PregnancySymptom.MILKING);
		}
	}
	
	@Override
	public boolean canBeAngry() {
		return super.canBeAngry() || pregnantEntity.getPregnancyData().getMilking() >= PregnancySystemHelper.MAX_MILKING_LEVEL;
	}
	
	@Nullable
	public Result evaluateMilking(Level level, Player source) {
		final var pregnancyData = pregnantEntity.getPregnancyData();
	    var mainHandItem = source.getMainHandItem();
	    var currentMilking = pregnancyData.getMilking();
		
	    if (currentMilking < PregnancySystemHelper.MILKING_VALUE || mainHandItem.isEmpty() || mainHandItem.getItem() != Items.GLASS_BOTTLE) {   
	    	return null;
	    }
	    
        pregnantEntity.playSound(SoundEvents.COW_MILK, 0.8F, 0.8F + pregnantEntity.getRandom().nextFloat() * 0.3F);
	       
        if (!level.isClientSide) {    	
        	MinepreggoMod.LOGGER.debug("{} {}", mainHandItem, mainHandItem.getCount());
	
            currentMilking = Math.max(0, currentMilking - PregnancySystemHelper.MILKING_VALUE);
            pregnancyData.setMilking(currentMilking);               
            var milkItem = PregnancySystemHelper.getMilkItem(pregnantEntity.getTypeOfSpecies());
           
            if (milkItem != null) {
            	ItemHandlerHelper.giveItemToPlayer(source, new ItemStack(milkItem));
			} else {
				MinepreggoMod.LOGGER.warn("Milk item is null for species: {}", pregnantEntity.getTypeOfSpecies());
			}
        	
            mainHandItem.shrink(1);
            
            if (mainHandItem.isEmpty()) {
                source.setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
            }        
            source.getInventory().setChanged();
            
    		SyncedSetPregnancySymptom pregnancySymptoms = pregnancyData.getSyncedPregnancySymptoms();	

            if (pregnancySymptoms.containsPregnancySymptom(PregnancySymptom.MILKING)
            		&& currentMilking <= PregnancySystemHelper.DESACTIVATE_MILKING_SYMPTOM) {
            	pregnancySymptoms.removePregnancySymptom(PregnancySymptom.MILKING);
            } 
            
        	MinepreggoMod.LOGGER.debug("{} {}", mainHandItem, mainHandItem.getCount());
        }
     
        return Result.SUCCESS;   
	}
	
	@Override
	protected Result evaluateBellyRubs(Level level, Player source) {
		// In this pregnancy phase, the belly is not large enough to do some action
		pregnantEntity.playSound(MinepreggoModSounds.BELLY_TOUCH.get(), 0.7F, 0.8F + pregnantEntity.getRandom().nextFloat() * 0.3F);	
		if (!level.isClientSide) {
			PregnancySystemHelper.playSlappingBellyAnimation(source, pregnantEntity);
		}
		return Result.FAIL;
	}
}

