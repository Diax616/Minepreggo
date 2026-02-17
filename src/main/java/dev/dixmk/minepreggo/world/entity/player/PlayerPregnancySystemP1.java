package dev.dixmk.minepreggo.world.entity.player;

import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.annotation.Nonnegative;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.checkerframework.checker.nullness.qual.NonNull;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.init.MinepreggoModMobEffects;
import dev.dixmk.minepreggo.network.chat.MessageHelper;
import dev.dixmk.minepreggo.world.entity.EntityHelper;
import dev.dixmk.minepreggo.world.entity.preggo.Species;
import dev.dixmk.minepreggo.world.pregnancy.AbstractPregnancySystem;
import dev.dixmk.minepreggo.world.pregnancy.PostPregnancy;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPain;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySymptom;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemStack;

public class PlayerPregnancySystemP1 extends PlayerPregnancySystemP0 {
	private int pregnancyPainCooldown = 0;
	private int pregnancysymptonsCooldown = 0;
	
	protected @Nonnegative int totalTicksOfCraving = MinepreggoModConfig.SERVER.getTotalTicksOfCravingP1();
	protected @Nonnegative float morningSicknessProb = PregnancySystemHelper.LOW_MORNING_SICKNESS_PROBABILITY;
	protected @Nonnegative float pregnancyExhaustion = 0.01f;
	
	private Set<PregnancySymptom> validPregnancySymptoms = EnumSet.noneOf(PregnancySymptom.class);
	
	public PlayerPregnancySystemP1(@NonNull ServerPlayer player) {
		super(player);
		initPregnancyTimers();
		addNewValidPregnancySymptoms(PregnancySymptom.CRAVING);
	}
	
	protected void addNewValidPregnancySymptoms(PregnancySymptom newPregnancySymptom) {
		this.validPregnancySymptoms.add(newPregnancySymptom);
	}
	
	protected void initPregnancyTimers() {

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
			if (pregnancyPainCooldown > 20) {
				tryInitRandomPregnancyPain();
				pregnancyPainCooldown = 0;
			}
			else {
				++pregnancyPainCooldown;
			}
		}
		else {
			evaluatePregnancyPains();
		}
			
		if (!hasAllPregnancySymptoms()) {
			if (pregnancysymptonsCooldown > 20) {
				tryInitPregnancySymptom();
				pregnancysymptonsCooldown = 0;
			}
			else {
				++pregnancysymptonsCooldown;
			}
		}
		if (!pregnancySystem.getPregnancySymptoms().isEmpty()) {
			evaluatePregnancySymptoms();
		}
		
		evaluatePregnancyNeeds();
		evaluateRandomWeakness();
		
		pregnantEntity.causeFoodExhaustion(pregnancyExhaustion);
		super.evaluatePregnancySystem();
	}
	
	@Override
	protected void evaluatePregnancyNeeds() {	
		evaluateCravingTimer();
	}
	
	protected void evaluateCravingTimer() {   				
		if (pregnancySystem.getCraving() < PregnancySystemHelper.MAX_CRAVING_LEVEL) {
	        if (pregnancySystem.getCravingTimer() >= totalTicksOfCraving) {
	        	pregnancySystem.incrementCraving();
	        	pregnancySystem.resetCravingTimer();
	        	pregnancySystem.syncEffect(pregnantEntity);
	        	
	        	MinepreggoMod.LOGGER.debug("Player {} craving level increased to: {}", 
	        			pregnantEntity.getGameProfile().getName(), pregnancySystem.getCraving());
	        }
	        else {
	        	pregnancySystem.incrementCravingTimer();
	        }
		}	
	}
	
	// TODO: Redesign the way of resetting pregnancy data after miscarriage
	@Override
	protected void evaluateMiscarriage(ServerLevel serverLevel) {		
		if (pregnancySystem.getPregnancyPainTimer() > PregnancySystemHelper.TOTAL_TICKS_MISCARRIAGE) {				
       		
			final List<ItemStack> deadBabiesItemStacks = PregnancySystemHelper.getDeadBabies(pregnancySystem.getWomb());
        	Iterator<ItemStack> iterator = deadBabiesItemStacks.iterator();
			
        	MinepreggoMod.LOGGER.debug("Miscarriage delivering {} dead babies: id={}, class={}",
					deadBabiesItemStacks.size(), pregnantEntity.getId(), pregnantEntity.getClass().getSimpleName());
        	
        	if (deadBabiesItemStacks.isEmpty()) {
				MinepreggoMod.LOGGER.error("Failed to get dead baby item for miscarriage event. mobId={}, mobClass={}",
						pregnantEntity.getId(), pregnantEntity.getClass().getSimpleName());
			}
        		
        	// TODO: Babies itemstacks are only removed if player's hands are empty. It should handle stacking unless itemstack is a baby item.
    		if (pregnantEntity.getItemInHand(InteractionHand.MAIN_HAND).isEmpty() && iterator.hasNext()) {        			
    			PlayerHelper.replaceAndDropItemstackInHand(pregnantEntity, InteractionHand.MAIN_HAND, iterator.next());
    		}
    		if (pregnantEntity.getItemInHand(InteractionHand.OFF_HAND).isEmpty() && iterator.hasNext()) {
    			PlayerHelper.replaceAndDropItemstackInHand(pregnantEntity, InteractionHand.OFF_HAND, iterator.next());
    		}
        	
    		while (iterator.hasNext()) {
				ItemStack baby = iterator.next();
				if(!pregnantEntity.getInventory().add(baby)) {
					EntityHelper.dropItemStack(pregnantEntity, baby);
				}
			}
        			
			initPostMiscarriage();		
			MinepreggoMod.LOGGER.debug("Player {} miscarriage has ended.", pregnantEntity.getGameProfile().getName());
		} else {		
    		AbstractPregnancySystem.spawnParticulesForMiscarriage(serverLevel, pregnantEntity);	
			pregnancySystem.incrementPregnancyPainTimer();
		}
	}
	
	@Override
	protected void evaluatePregnancySymptoms() {
		if (pregnancySystem.getPregnancySymptoms().containsPregnancySymptom(PregnancySymptom.CRAVING) && pregnancySystem.getCraving() <= PregnancySystemHelper.DESACTIVATE_CRAVING_SYMPTOM) {	
			pregnancySystem.getPregnancySymptoms().removePregnancySymptom(PregnancySymptom.CRAVING);
			pregnancySystem.clearTypeOfCravingBySpecies();
			pregnantEntity.removeEffect(MinepreggoModMobEffects.CRAVING.get());
			pregnancySystem.syncState(pregnantEntity);
			pregnancySystem.syncEffect(pregnantEntity);	
			MinepreggoMod.LOGGER.debug("Player {} pregnancy symptom cleared: {}",
					pregnantEntity.getGameProfile().getName(), PregnancySymptom.CRAVING.name());
		}
	}
	
	@Override
	protected void evaluatePregnancyPains() {
		if (pregnancySystem.getPregnancyPain() == PregnancyPain.MORNING_SICKNESS) {
			if (pregnancySystem.getPregnancyPainTimer() >= PregnancySystemHelper.TOTAL_TICKS_MORNING_SICKNESS) {
				pregnantEntity.removeEffect(MinepreggoModMobEffects.MORNING_SICKNESS.get());
				pregnancySystem.clearPregnancyPain();
				pregnancySystem.resetPregnancyPainTimer();
				pregnancySystem.syncState(pregnantEntity);
				
				MinepreggoMod.LOGGER.debug("Player {} pregnancy pain cleared: {}",
						pregnantEntity.getGameProfile().getName(), PregnancyPain.MORNING_SICKNESS.name());
			} else {
				pregnancySystem.incrementPregnancyPainTimer();
			}
		}
	}
	
	@Override
	public boolean isMiscarriageActive() {
		return pregnancySystem.getPregnancyPain() == PregnancyPain.MISCARRIAGE;	
	}
	
	@Override
	public boolean hasPregnancyPain() {
		return pregnancySystem.getPregnancyPain() != null;
	}
	
	@Override
	public boolean hasAllPregnancySymptoms() {
		return pregnancySystem.getPregnancySymptoms().containsAllPregnancySymptoms(validPregnancySymptoms);
	}
	
	@Override
	protected boolean tryInitRandomPregnancyPain() {
		if (randomSource.nextFloat() < morningSicknessProb) {
			pregnancySystem.setPregnancyPain(PregnancyPain.MORNING_SICKNESS);
			pregnantEntity.addEffect(new MobEffectInstance(MinepreggoModMobEffects.MORNING_SICKNESS.get(), PregnancySystemHelper.TOTAL_TICKS_MORNING_SICKNESS, 0, false, false, true));
			pregnancySystem.syncState(pregnantEntity);
			
			MinepreggoMod.LOGGER.debug("Player {} has developed pregnancy pain: {}",
					pregnantEntity.getGameProfile().getName(), PregnancyPain.MORNING_SICKNESS.name());

			return true;
		}	
		return false;
	}
	
	@Override
	protected boolean tryInitPregnancySymptom() {
		if (pregnancySystem.getCraving() >= PregnancySystemHelper.MAX_CRAVING_LEVEL
				&& !pregnancySystem.getPregnancySymptoms().containsPregnancySymptom(PregnancySymptom.CRAVING)) {
			pregnancySystem.getPregnancySymptoms().addPregnancySymptom(PregnancySymptom.CRAVING);
			
			if (pregnancySystem.getCurrentPregnancyPhase().compareTo(PregnancyPhase.P3) >= 0 && randomSource.nextDouble() < 0.3f) {
				var fatherSpecies = femaleData.getPrePregnancyData().map(data -> data.typeOfSpeciesOfFather());
				if (!fatherSpecies.isEmpty() && !PlayerHelper.isValidCravingBySpecies(fatherSpecies.get())) {
					fatherSpecies = Optional.of(Species.HUMAN);
				}
				else if (fatherSpecies.isEmpty()) {
					fatherSpecies = Optional.of(Species.HUMAN);
					MinepreggoMod.LOGGER.warn("Player {} pregnancy craving by species couldn't be set because father species is empty, setting it to HUMAN by default.", pregnantEntity.getGameProfile().getName());
				}	
				pregnancySystem.setTypeOfCravingBySpecies(ImmutablePair.of(PregnancySystemHelper.getRandomCraving(randomSource), fatherSpecies.get()));	
			}
			else {
				pregnancySystem.setTypeOfCraving(PregnancySystemHelper.getRandomCraving(randomSource));	
			}
			
			MinepreggoMod.LOGGER.debug("Player {} craving set to: {}", pregnantEntity.getGameProfile().getName(), pregnancySystem.getTypeOfCraving());
			
			pregnantEntity.addEffect(new MobEffectInstance(MinepreggoModMobEffects.CRAVING.get(), -1, 0, false, false, true));
			pregnancySystem.syncState(pregnantEntity);
			pregnancySystem.syncEffect(pregnantEntity);
			MinepreggoMod.LOGGER.debug("Player {} has developed pregnancy symptom: {}, all pregnancy symptoms: {}",
					pregnantEntity.getGameProfile().getName(), PregnancySymptom.CRAVING, pregnancySystem.getPregnancySymptoms());
			return true;
		}
		return false;
	}
	
	@Override
	protected void startMiscarriage() {
		tryHurt();
		MessageHelper.sendTo(pregnantEntity, Component.translatable("chat.minepreggo.player.miscarriage.message.init", pregnantEntity.getDisplayName().getString()));
		pregnancySystem.resetPregnancyPainTimer();
		pregnancySystem.setPregnancyPain(PregnancyPain.MISCARRIAGE);
		pregnantEntity.addEffect(new MobEffectInstance(MinepreggoModMobEffects.MISCARRIAGE.get(), PregnancySystemHelper.TOTAL_TICKS_MISCARRIAGE, 0, false, false, true));
		pregnancySystem.syncState(pregnantEntity);
		MinepreggoMod.LOGGER.debug("Miscarriage just started");
	}
	
	// TODO: Redesign the way of resetting pregnancy data after birth
	@Override
	protected void initPostMiscarriage() {
    	MessageHelper.sendTo(pregnantEntity, Component.translatable("chat.minepreggo.player.miscarriage.message.post", pregnantEntity.getDisplayName().getString()));
		pregnancySystem.resetPregnancyPainTimer();
		pregnancySystem.clearPregnancyPain();
    	
		PlayerHelper.updateJigglePhysics(pregnantEntity, playerData.getSkinType(), null);
		
    	// tryActivatePostPregnancyPhase only works if isPregnant flag is true
    	femaleData.tryActivatePostPregnancyPhase(PostPregnancy.MISCARRIAGE);
		femaleData.sync(pregnantEntity);
		
		// resetPregnancy creates new instance of pregnancy system, so it has to be called after tryActivatePostPregnancyPhase, because it changes isPregnant flag to false and tryActivatePostPregnancyPhase does nothing if it's false
		femaleData.resetPregnancy();
		femaleData.resetPregnancyOnClient(pregnantEntity);
	
		pregnantEntity.addEffect(new MobEffectInstance(MinepreggoModMobEffects.DEPRESSION.get(), MinepreggoModConfig.SERVER.getTotalTicksOfPostPregnancyPhase(), 0, false, false, true));
		pregnantEntity.addEffect(new MobEffectInstance(MobEffects.UNLUCK, 12000, 0, false, true, true));
		pregnantEntity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 4800, 0, false, false, true));
			
		PregnancySystemHelper.applyPostPregnancyNerf(pregnantEntity);
	
		removePregnancy();
							
		MinepreggoMod.LOGGER.debug("Player {} has entered postmiscarriage phase.", pregnantEntity.getGameProfile().getName());
	}
}
