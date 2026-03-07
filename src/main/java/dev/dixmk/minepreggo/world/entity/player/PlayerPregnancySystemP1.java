package dev.dixmk.minepreggo.world.entity.player;

import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Nonnegative;

import org.checkerframework.checker.nullness.qual.NonNull;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.init.MinepreggoMobEffects;
import dev.dixmk.minepreggo.init.MinepreggoSounds;
import dev.dixmk.minepreggo.network.chat.MessageHelper;
import dev.dixmk.minepreggo.server.ServerParticleHelper;
import dev.dixmk.minepreggo.world.entity.EntityHelper;
import dev.dixmk.minepreggo.world.entity.LivingEntityHelper;
import dev.dixmk.minepreggo.world.pregnancy.PostPregnancy;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPain;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPainInstance;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySymptom;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import net.minecraft.core.particles.ParticleTypes;
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
		
		evaluatePregnancyHealing();
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
		final var instance = pregnancySystem.getPregnancyPain();
		if (instance == null) {
			MinepreggoMod.LOGGER.warn("Pregnancy pain instance is null for player {} during miscarriage evaluation.", pregnantEntity.getGameProfile().getName());
			return;
		}
		
		if (instance.isExpired()) {				    		
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
					EntityHelper.spawnItemOn(pregnantEntity, baby);
				}
			}
        			
			initPostMiscarriage();		
			MinepreggoMod.LOGGER.debug("Player {} miscarriage has ended.", pregnantEntity.getGameProfile().getName());
		} else {		
			tryHurtByCooldown();
			instance.tick();
			spawnParticulesForMiscarriage(serverLevel);
		}
	}
	
	@Override
	protected void evaluatePregnancySymptoms() {
		if (pregnancySystem.getPregnancySymptoms().contains(PregnancySymptom.CRAVING) && pregnancySystem.getCraving() <= PregnancySystemHelper.DESACTIVATE_CRAVING_SYMPTOM) {	
			pregnancySystem.getPregnancySymptoms().remove(PregnancySymptom.CRAVING);
			pregnancySystem.clearTypeOfCravingBySpecies();
			pregnantEntity.removeEffect(MinepreggoMobEffects.CRAVING.get());
			pregnancySystem.syncState(pregnantEntity);
			pregnancySystem.syncEffect(pregnantEntity);	
			MinepreggoMod.LOGGER.debug("Player {} pregnancy symptom cleared: {}",
					pregnantEntity.getGameProfile().getName(), PregnancySymptom.CRAVING.name());
		}
	}
	
	@Override
	protected void evaluatePregnancyPains() {
		var instance = pregnancySystem.getPregnancyPain();
		if (instance == null || instance.getPain().type != PregnancyPain.Type.COMMON) {
			return;
		}	
		
		if (instance.isExpired()) {
			MinepreggoMod.LOGGER.debug("Player {} pregnancy pain cleared: {}", pregnantEntity.getGameProfile().getName(), instance.getPain());		
			if (instance.getPain() == PregnancyPain.MORNING_SICKNESS) {
				pregnantEntity.removeEffect(MinepreggoMobEffects.MORNING_SICKNESS.get());
			}
			else if (instance.getPain() == PregnancyPain.FETAL_MOVEMENT) {
				pregnantEntity.removeEffect(MinepreggoMobEffects.FETAL_MOVEMENT.get());
			}
			else if (instance.getPain() == PregnancyPain.CONTRACTION) {
				pregnantEntity.removeEffect(MinepreggoMobEffects.CONTRACTION.get());
			}			
			pregnancySystem.clearPregnancyPain();
			pregnancySystem.syncState(pregnantEntity);
		} else {
			instance.tick();
			if (instance.getPain() == PregnancyPain.FETAL_MOVEMENT || instance.getPain() == PregnancyPain.CONTRACTION) {
				tryHurtByCooldown();
			}
		}
	}
	
	@Override
	public boolean isMiscarriageActive() {
		var instance = pregnancySystem.getPregnancyPain();
		return instance != null && instance.getPain() == PregnancyPain.MISCARRIAGE;	
	}
	
	@Override
	public boolean hasPregnancyPain() {
		return pregnancySystem.getPregnancyPain() != null;
	}
	
	@Override
	public boolean hasAllPregnancySymptoms() {
		return pregnancySystem.getPregnancySymptoms().containsAll(validPregnancySymptoms);
	}
	
	@Override
	protected boolean tryInitRandomPregnancyPain() {
		if (pregnantEntity.getRandom().nextFloat() < morningSicknessProb) {
			this.initCommonPregnancyPain(PregnancyPain.MORNING_SICKNESS, PregnancySystemHelper.TOTAL_TICKS_MORNING_SICKNESS, 0);
			return true;
		}	
		return false;
	}
	
	@Override
	protected boolean tryInitPregnancySymptom() {
		if (pregnancySystem.getCraving() >= PregnancySystemHelper.MAX_CRAVING_LEVEL
				&& !pregnancySystem.getPregnancySymptoms().contains(PregnancySymptom.CRAVING)) {
			this.initPregnancySymptom(PregnancySymptom.CRAVING);
			return true;
		}
		return false;
	}
	
	@Override
	protected void startMiscarriage() {
		tryHurt();
		LivingEntityHelper.playSoundNearTo(pregnantEntity, MinepreggoSounds.PLAYER_MISCARRIAGE.get(), 0.7f);
		MessageHelper.sendTo(pregnantEntity, Component.translatable("chat.minepreggo.player.miscarriage.message.start", pregnantEntity.getDisplayName().getString()));
		pregnancySystem.setPregnancyPain(new PregnancyPainInstance(PregnancyPain.MISCARRIAGE, PregnancySystemHelper.TOTAL_TICKS_MISCARRIAGE, 0));
		pregnantEntity.addEffect(new MobEffectInstance(MinepreggoMobEffects.MISCARRIAGE.get(), PregnancySystemHelper.TOTAL_TICKS_MISCARRIAGE, 0, false, false, true));
		pregnancySystem.syncState(pregnantEntity);
		MinepreggoMod.LOGGER.debug("Miscarriage just started");
	}
	
	// TODO: Redesign the way of resetting pregnancy data after birth
	@Override
	protected void initPostMiscarriage() {
		MinepreggoMod.LOGGER.debug("Player {} has entered postmiscarriage phase.", pregnantEntity.getGameProfile().getName());

		MessageHelper.sendTo(pregnantEntity, Component.translatable("chat.minepreggo.player.miscarriage.message.post", pregnantEntity.getDisplayName().getString()));
		pregnancySystem.clearPregnancyPain();
    	
		PlayerHelper.updateJigglePhysics(pregnantEntity, playerData.getSkinType(), null);
		
    	// tryActivatePostPregnancyPhase only works if isPregnant flag is true
    	femaleData.tryActivatePostPregnancyPhase(PostPregnancy.MISCARRIAGE);
		femaleData.sync(pregnantEntity);
		
		// resetPregnancy creates new instance of pregnancy system, so it has to be called after tryActivatePostPregnancyPhase, because it changes isPregnant flag to false and tryActivatePostPregnancyPhase does nothing if it's false
		femaleData.resetPregnancy();
		femaleData.resetPregnancyOnClient(pregnantEntity);
	
		pregnantEntity.addEffect(new MobEffectInstance(MinepreggoMobEffects.DEPRESSION.get(), MinepreggoModConfig.SERVER.getTotalTicksOfPostPregnancyPhase(), 0, false, false, true));
		pregnantEntity.addEffect(new MobEffectInstance(MobEffects.UNLUCK, 12000, 0, false, true, true));
		pregnantEntity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 4800, 0, false, false, true));
			
		PregnancySystemHelper.applyPostPregnancyNerf(pregnantEntity);
		tryHurt();
		
		removePregnancy();						
	}
	
	@Override
	protected void evaluatePregnancyHealing() {
		if (pregnancySystem.getPregnancyHealth() < PregnancySystemHelper.MAX_PREGNANCY_HEALTH) {
			if (pregnancySystem.getPregnancyHealthTimer() > MinepreggoModConfig.SERVER.getTotalTicksForPregnancyHealing()) {
				pregnancySystem.incrementPregnancyHealth(MinepreggoModConfig.SERVER.getPregnacyHealingAmount(pregnancySystem.getCurrentPregnancyPhase()));
				pregnancySystem.resetPregnancyHealthTimer();
				ServerParticleHelper.spawnParticlesAroundSelf(pregnantEntity, ParticleTypes.HAPPY_VILLAGER, 10);
				MinepreggoMod.LOGGER.debug("Player {} pregnancy health increased to: {}", pregnantEntity.getGameProfile().getName(), pregnancySystem.getPregnancyHealth());
			}
			else {
				pregnancySystem.incrementPregnancyHealthTimer();
			}
		}
	}
}
