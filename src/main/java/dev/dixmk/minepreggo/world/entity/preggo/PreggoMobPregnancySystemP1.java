package dev.dixmk.minepreggo.world.entity.preggo;

import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import org.jetbrains.annotations.Nullable;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.network.chat.MessageHelper;
import dev.dixmk.minepreggo.server.ServerParticleHelper;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobSystem.Result;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractTamableCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.MonsterCreeperHelper;
import dev.dixmk.minepreggo.world.entity.preggo.ender.AbstractEnderWoman;
import dev.dixmk.minepreggo.world.item.ICravingItem;
import dev.dixmk.minepreggo.world.pregnancy.AbstractPregnancySystem;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPain;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySymptom;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import dev.dixmk.minepreggo.world.pregnancy.SyncedSetPregnancySymptom;
import net.minecraft.core.particles.ParticleTypes;
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

	protected @Nonnegative int totalTicksOfCraving = MinepreggoModConfig.SERVER.getTotalTicksOfCravingP1();
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
		
		evaluatePregnancyHealing();
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
					
		final Result result = evaluateCraving(level, source);	
		
		
		if (result != null) {			
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
			if (pregnantEntity instanceof AbstractEnderWoman) {
	    		AbstractPregnancySystem.spawnParticulesForWaterBreaking(serverLevel, pregnantEntity, pregnantEntity.getBbHeight() * 0.65);
			}
			else {
	    		AbstractPregnancySystem.spawnParticulesForWaterBreaking(serverLevel, pregnantEntity);
			}
        } else {      	
        	final List<ItemStack> deadBabiesItemStacks = PregnancySystemHelper.getDeadBabies(pregnancyData.getWomb());   	
       		
        	MinepreggoMod.LOGGER.debug("Miscarriage delivering {} dead babies: id={}, class={}",
					deadBabiesItemStacks.size(), pregnantEntity.getId(), pregnantEntity.getClass().getSimpleName());
        	
        	if (deadBabiesItemStacks.isEmpty()) {
				MinepreggoMod.LOGGER.error("Failed to get dead baby item for miscarriage event. mobId={}, mobClass={}",
						pregnantEntity.getId(), pregnantEntity.getClass().getSimpleName());
			}
        	
        	InventorySlotMapper slotMapper = pregnantEntity.getInventory().getSlotMapper();
        	Iterator<ItemStack> slotIterator = deadBabiesItemStacks.iterator();
        	       	
        	// TODO: Babies itemstacks are only removed if player's hands are empty. It should handle stacking unless itemstack is a baby item.
        	if (slotMapper.hasSlot(InventorySlot.MAINHAND) && slotIterator.hasNext()) {
        		PreggoMobHelper.replaceAndDropItemstackInHand(pregnantEntity, InteractionHand.MAIN_HAND, slotIterator.next());
        	}
        	if (slotMapper.hasSlot(InventorySlot.OFFHAND) && slotIterator.hasNext()) {
        		 PreggoMobHelper.replaceAndDropItemstackInHand(pregnantEntity, InteractionHand.OFF_HAND, slotIterator.next());	
        	}  	
        	if (pregnantEntity instanceof AbstractTamableCreeperGirl creeperGirl && creeperGirl.getTypeOfCreature() == Creature.MONSTER && slotIterator.hasNext()) {
        		MonsterCreeperHelper.replaceItemstackInMouth(creeperGirl, slotIterator.next());	
        	}
        	       	
        	while (slotIterator.hasNext()) {
        		PreggoMobHelper.storeItemInExtraSlotsOrDrop(pregnantEntity, slotIterator.next());
        	}
	    	
        	initPostMiscarriage();	 
        	MessageHelper.sendTo(MessageHelper.asServerPlayer((Player) pregnantEntity.getOwner()), Component.translatable("chat.minepreggo.preggo_mob.miscarriage.message.post", pregnantEntity.getSimpleNameOrCustom()));
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
	public void evaluateOnSuccessfulHurt(DamageSource damagesource) {	
		final var pregnancyData = pregnantEntity.getPregnancyData();

		if (pregnancyData.getPregnancyPain() == PregnancyPain.MISCARRIAGE) return;

		PregnancySystemHelper.calculatePregnancyDamage(pregnantEntity, pregnancyData.getCurrentPregnancyPhase(), damagesource).ifPresent(damage -> {
			pregnancyData.reducePregnancyHealth(damage);
			pregnancyData.resetPregnancyHealthTimer();
			var currentPregnancyHealth = pregnancyData.getPregnancyHealth();

			if (currentPregnancyHealth <= 0) {
				startMiscarriage();
			}	
			else if (currentPregnancyHealth < 40) {
				MessageHelper.sendTo(MessageHelper.asServerPlayer((Player) pregnantEntity.getOwner()), Component.translatable("chat.minepreggo.preggo_mob.miscarriage.message.warning", pregnantEntity.getSimpleNameOrCustom()));
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
		MessageHelper.sendTo(MessageHelper.asServerPlayer((Player) pregnantEntity.getOwner()), Component.translatable("chat.minepreggo.preggo_mob.miscarriage.message.init", pregnantEntity.getSimpleNameOrCustom()));
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
					pregnantEntity.getSimpleNameOrCustom(), PregnancySymptom.CRAVING.name(), pregnancySymptoms.toSet());
	    	return true;		
		}
	    return false;
	}
	
	@Nullable
	protected Result evaluateCraving(Level level, Player source) {		
		final var pregnancyData = pregnantEntity.getPregnancyData();
		SyncedSetPregnancySymptom pregnancySymptoms = pregnancyData.getSyncedPregnancySymptoms();			
		if (!pregnancySymptoms.containsPregnancySymptom(PregnancySymptom.CRAVING)) {
			MinepreggoMod.LOGGER.debug("No craving symptom active");
			return null;
		}
		
	    var mainHandItem = source.getMainHandItem();
   
	    if (pregnancyData.getCraving() > PregnancySystemHelper.DESACTIVATE_CRAVING_SYMPTOM && pregnantEntity.isFood(mainHandItem)) {    	
	    	    		    	
            pregnantEntity.playSound(SoundEvents.GENERIC_EAT, 0.8F, 0.8F + pregnantEntity.getRandom().nextFloat() * 0.3F);

	    	if (!level.isClientSide) {	  
	    		var item = mainHandItem.getItem();
		    	if (!pregnancyData.isValidCraving(item) || !(item instanceof ICravingItem)) {
		    		return Result.ANGRY;
		    	}
	    		
				mainHandItem.shrink(1);
	            if (mainHandItem.isEmpty()) {
	            	source.setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
	            }        
	            source.getInventory().setChanged();    		  		
	            pregnancyData.decrementCraving(((ICravingItem)item).getGratification());   
	            
	    		if (pregnancyData.getCraving() <= PregnancySystemHelper.DESACTIVATE_CRAVING_SYMPTOM) {
	    			pregnancySymptoms.removePregnancySymptom(PregnancySymptom.CRAVING);
	    			pregnancyData.clearTypeOfCraving();
	    		}
	    	}
            
            return Result.SUCCESS; 
	    }
	    
	    return null;
	}
	
	@Override
	protected void evaluatePregnancyHealing() {
		var pregnancySystem = pregnantEntity.getPregnancyData();
		if (pregnancySystem.getPregnancyHealth() < PregnancySystemHelper.MAX_PREGNANCY_HEALTH) {
			if (pregnancySystem.getPregnancyHealthTimer() > MinepreggoModConfig.SERVER.getTotalTicksForPregnancyHealing()) {
				pregnancySystem.incrementPregnancyHealth(MinepreggoModConfig.SERVER.getPregnacyHealingAmount(pregnancySystem.getCurrentPregnancyPhase()));
				pregnancySystem.resetPregnancyHealthTimer();
				MinepreggoMod.LOGGER.debug("Player {} pregnancy health increased to: {}", pregnantEntity.getSimpleNameOrCustom(), pregnancySystem.getPregnancyHealth());
				ServerParticleHelper.spawnParticlesAroundSelf(pregnantEntity, ParticleTypes.HAPPY_VILLAGER, 10);
			}
			else {
				pregnancySystem.incrementPregnancyHealthTimer();
			}
		}
	}
}