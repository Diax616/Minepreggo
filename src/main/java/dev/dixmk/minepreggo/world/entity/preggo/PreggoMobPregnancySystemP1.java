package dev.dixmk.minepreggo.world.entity.preggo;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Set;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import org.jetbrains.annotations.Nullable;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.network.chat.MessageHelper;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobSystem.Result;
import dev.dixmk.minepreggo.world.item.ICravingItem;
import dev.dixmk.minepreggo.world.pregnancy.AbstractPregnancySystem;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPain;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySymptom;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import dev.dixmk.minepreggo.world.pregnancy.SyncedSetPregnancySymptom;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public abstract class PreggoMobPregnancySystemP1
	<E extends PreggoMob & ITamablePregnantPreggoMob> extends PreggoMobPregnancySystemP0<E> {

	private int pregnancyPainTicks = 0;
	private int pregnancysymptonsTicks = 0;
	
	private final Set<PregnancySymptom> validPregnancySymptoms = EnumSet.noneOf(PregnancySymptom.class);

	protected @Nonnegative int totalTicksOfCraving = MinepreggoModConfig.getTotalTicksOfCravingP1();
	protected @Nonnegative float morningSicknessProb = PregnancySystemHelper.LOW_MORNING_SICKNESS_PROBABILITY;

	protected PreggoMobPregnancySystemP1(@Nonnull E preggoMob) {
		super(preggoMob);
		initPregnancyTimers();
		addNewValidPregnancySymptom(PregnancySymptom.CRAVING);
	}
	
	protected void initPregnancyTimers() {

	}
	
	protected void addNewValidPregnancySymptom(PregnancySymptom newPregnancySymptom) {
		this.validPregnancySymptoms.add(newPregnancySymptom);
	}
	
	// It has to be executed on server side
	@Override
	protected void evaluatePregnancySystem() {
		if (isMiscarriageActive()) {
			if (pregnantEntity.level() instanceof ServerLevel serverLevel) {
				evaluateMiscarriage(serverLevel);
			}		
			return;
		}
		
		if (!hasPregnancyPain()) {
			if (pregnancyPainTicks > 20) {
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
		
		if (!hasAllPregnancySymptoms()) {
			if (pregnancysymptonsTicks > 20) {
				tryInitPregnancySymptom();
				pregnancysymptonsTicks = 0;
			}
			else {
				++pregnancysymptonsTicks;
			}
		}
		if (!pregnantEntity.getPregnancyData().getSyncedPregnancySymptoms().isEmpty()) {
			evaluatePregnancySymptoms();
		}
		
		evaluatePregnancyNeeds();
		
		super.evaluatePregnancySystem();
	}
	
	@Override
	protected void evaluatePregnancyNeeds() {	
		evaluateCravingTimer();	
	}
	
	@Override
	public InteractionResult onRightClick(Player source) {		
		var retval = super.onRightClick(source);	
		if (retval != InteractionResult.PASS) {
			return retval;
		}	
					
		var level = pregnantEntity.level();
					
		final Result result;	
		if (!source.getMainHandItem().isEmpty() && (result = evaluateCraving(level, source)) != null) {			
			if (!level.isClientSide) {
				PreggoMobSystem.spawnParticles(pregnantEntity, result);
			}
			return InteractionResult.sidedSuccess(level.isClientSide);
		}
		
		return InteractionResult.PASS;
	}
	
	@Override
	public boolean isRightClickValid(Player source) {
		return super.isRightClickValid(source) && !pregnantEntity.getPregnancyData().isIncapacitated();
	}

	
	@Override
	protected void evaluateMiscarriage(ServerLevel serverLevel) {	    
		final var pregnancyData = pregnantEntity.getPregnancyData();
		
		if (pregnancyData.getPregnancyPainTimer() < PregnancySystemHelper.TOTAL_TICKS_MISCARRIAGE) {
        	pregnancyData.setPregnancyPainTimer(pregnancyData.getPregnancyPainTimer() + 1);	        		        	
        	AbstractPregnancySystem.spawnParticulesForMiscarriage(serverLevel, pregnantEntity);
        } else {      	
        	final var deadBabiesItemStacks = new ArrayList<>(PregnancySystemHelper.getDeadBabies(pregnancyData.getWomb()));   	
       		
        	MinepreggoMod.LOGGER.debug("Miscarriage delivering {} dead babies: id={}, class={}",
					deadBabiesItemStacks.size(), pregnantEntity.getId(), pregnantEntity.getClass().getSimpleName());
        	
        	if (deadBabiesItemStacks.isEmpty()) {
				MinepreggoMod.LOGGER.error("Failed to get dead baby item for miscarriage event. mobId={}, mobClass={}",
						pregnantEntity.getId(), pregnantEntity.getClass().getSimpleName());
			}
        	
        	// TODO: Babies itemstacks are only removed if player's hands are empty. It should handle stacking unless itemstack is a baby item.
        	deadBabiesItemStacks.removeIf(baby -> {
        		if (pregnantEntity.getItemInHand(InteractionHand.MAIN_HAND).isEmpty()) {
        			PreggoMobHelper.replaceAndDropItemstackInHand(pregnantEntity, InteractionHand.MAIN_HAND, baby);
            		return true;
        		}
        		else if (pregnantEntity.getItemInHand(InteractionHand.OFF_HAND).isEmpty()) {
        			PreggoMobHelper.replaceAndDropItemstackInHand(pregnantEntity, InteractionHand.OFF_HAND, baby);
            		return true;
        		}
        		return false;
        	});
        	    	
        	if (!deadBabiesItemStacks.isEmpty()) {
            	deadBabiesItemStacks.forEach(baby -> PreggoMobHelper.storeItemInSpecificRangeOrDrop(pregnantEntity, baby, ITamablePreggoMob.FOOD_INVENTORY_SLOT + 1, pregnantEntity.getInventorySize() - 1)); 	
        	}
	    	
        	initPostMiscarriage();	 
        	MessageHelper.sendTo(MessageHelper.asServerPlayer((Player) pregnantEntity.getOwner()), Component.translatable("chat.minepreggo.preggo_mob.miscarriage.message.post", pregnantEntity.getSimpleName()));
        	pregnantEntity.discard();
        	MinepreggoMod.LOGGER.debug("Miscarriage completed: id={}, class={}", pregnantEntity.getId(), pregnantEntity.getClass().getSimpleName());
        }	
	}
	
	protected void evaluateCravingTimer() {   				
		final var pregnancyData = pregnantEntity.getPregnancyData();

		if (pregnancyData.getCraving() < PregnancySystemHelper.MAX_CRAVING_LEVEL) {
	        if (pregnancyData.getCravingTimer() >= totalTicksOfCraving) {
	        	pregnancyData.incrementCraving();
	        	pregnancyData.resetCravingTimer();
	        }
	        else {
	        	pregnancyData.incrementCravingTimer();
	        }
		}	
	}


	@Override
	protected void evaluatePregnancyPains() {
		final var pregnancyData = pregnantEntity.getPregnancyData();

		if (pregnancyData.getPregnancyPain() == PregnancyPain.MORNING_SICKNESS) {
			if (pregnancyData.getPregnancyPainTimer() >= PregnancySystemHelper.TOTAL_TICKS_MORNING_SICKNESS) {
				pregnancyData.clearPregnancyPain();
				pregnancyData.resetPregnancyPainTimer();
			} else {
				pregnancyData.incrementPregnancyPainTimer();
			}
		}
	}
	
	@Override
	protected void evaluatePregnancySymptoms() {
		final var pregnancyData = pregnantEntity.getPregnancyData();
		SyncedSetPregnancySymptom pregnancySymptoms = pregnancyData.getSyncedPregnancySymptoms();	
		if (pregnancySymptoms.containsPregnancySymptom(PregnancySymptom.CRAVING)
				&& pregnancyData.getCraving() <= PregnancySystemHelper.DESACTIVATE_CRAVING_SYMPTOM) {
			pregnancySymptoms.removePregnancySymptom(PregnancySymptom.CRAVING);
			pregnancyData.clearTypeOfCraving();
		}
	}
	
	@Override
	public void evaluateOnSuccessfulHurt(DamageSource damagesource) {	
		final var pregnancyData = pregnantEntity.getPregnancyData();

		if (pregnancyData.getPregnancyPain() == PregnancyPain.MISCARRIAGE) return;

		PregnancySystemHelper.calculatePregnancyDamage(pregnantEntity, pregnancyData.getCurrentPregnancyPhase(), damagesource).ifPresent(damage -> {
			pregnancyData.reducePregnancyHealth(damage);
			var currentPregnancyHealth = pregnancyData.getPregnancyHealth();

			if (currentPregnancyHealth <= 0) {
				startMiscarriage();
			}	
			else if (currentPregnancyHealth < 40) {
				MessageHelper.sendTo(MessageHelper.asServerPlayer((Player) pregnantEntity.getOwner()), Component.translatable("chat.minepreggo.preggo_mob.miscarriage.message.warning", pregnantEntity.getSimpleName()));
			}		
		});
	}
	
	@Override
	public boolean hasPregnancyPain() {
	    return pregnantEntity.getPregnancyData().getPregnancyPain() != null;
	}
	
	@Override
	public boolean hasAllPregnancySymptoms() {
		return pregnantEntity.getPregnancyData().getSyncedPregnancySymptoms().containsAllPregnancySymptoms(validPregnancySymptoms);
	}
	
	@Override
	public boolean isMiscarriageActive() {
	    return pregnantEntity.getPregnancyData().getPregnancyPain() == PregnancyPain.MISCARRIAGE;
	}
	
	@Override
	protected void startMiscarriage() {
		tryHurt();
		final var pregnancyData = pregnantEntity.getPregnancyData();
		pregnancyData.setPregnancyPain(PregnancyPain.MISCARRIAGE);
		pregnancyData.resetPregnancyPainTimer();
		MinepreggoMod.LOGGER.debug("Miscarriage just started");
		MessageHelper.sendTo(MessageHelper.asServerPlayer((Player) pregnantEntity.getOwner()), Component.translatable("chat.minepreggo.preggo_mob.miscarriage.message.init", pregnantEntity.getSimpleName()));
	}
	
	@Override
	protected boolean tryInitRandomPregnancyPain() {
		final var pregnancyData = pregnantEntity.getPregnancyData();
	    if (randomSource.nextFloat() < morningSicknessProb) {
	    	pregnancyData.setPregnancyPain(PregnancyPain.MORNING_SICKNESS);
	    	pregnancyData.resetPregnancyPainTimer();
	        return true;
	    }
	    return false;
	}
	
	@Override
	protected boolean tryInitPregnancySymptom() {
		final var pregnancyData = pregnantEntity.getPregnancyData();
		SyncedSetPregnancySymptom pregnancySymptoms = pregnancyData.getSyncedPregnancySymptoms();			
		if (pregnancyData.getCraving() >= PregnancySystemHelper.ACTIVATE_CRAVING_SYMPTOM
				&& !pregnancySymptoms.containsPregnancySymptom(PregnancySymptom.CRAVING)) {
			pregnancySymptoms.addPregnancySymptom(PregnancySymptom.CRAVING);
			pregnancyData.setTypeOfCraving(PregnancySystemHelper.getRandomCraving(randomSource));
	    	
			MinepreggoMod.LOGGER.debug("PreggomMob {} has developed pregnancy symptom: {}, all pregnancy symptoms: {}",
					pregnantEntity.getSimpleName(), PregnancySymptom.CRAVING.name(), pregnancySymptoms.toSet());
	    	return true;		
		}
	    return false;
	}
	
	@Nullable
	protected Result evaluateCraving(Level level, Player source) {
		final var pregnancyData = pregnantEntity.getPregnancyData();
		if (!pregnancyData.getSyncedPregnancySymptoms().containsPregnancySymptom(PregnancySymptom.CRAVING)) {
			return null;
		}
		
	    var mainHandItem = source.getMainHandItem();
	    var currentCraving = pregnancyData.getCraving();
	    
	    if (currentCraving > PregnancySystemHelper.DESACTIVATE_CRAVING_SYMPTOM && pregnantEntity.isFood(mainHandItem)) {    	
	    	
	    	if (!pregnancyData.isValidCraving(mainHandItem.getItem()) || !(mainHandItem.getItem() instanceof ICravingItem)) {
	    		return Result.ANGRY;
	    	}
	    	    	
            pregnantEntity.playSound(SoundEvents.GENERIC_EAT, 0.8F, 0.8F + pregnantEntity.getRandom().nextFloat() * 0.3F);

	    	if (!level.isClientSide) {
				mainHandItem.shrink(1);
	            if (mainHandItem.isEmpty()) {
	            	source.setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
	            }        
	            source.getInventory().setChanged();    		  		
	            pregnancyData.setCraving(currentCraving - ((ICravingItem)mainHandItem.getItem()).getGratification());    
	    	}
            
            return Result.SUCCESS; 
	    }
	    
	    return null;
	}
}