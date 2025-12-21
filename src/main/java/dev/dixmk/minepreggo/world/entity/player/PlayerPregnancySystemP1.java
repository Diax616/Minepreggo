package dev.dixmk.minepreggo.world.entity.player;

import java.util.EnumSet;
import java.util.Set;

import javax.annotation.Nonnegative;

import org.checkerframework.checker.nullness.qual.NonNull;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.init.MinepreggoModMobEffects;
import dev.dixmk.minepreggo.network.chat.MessageHelper;
import dev.dixmk.minepreggo.world.pregnancy.PostPregnancy;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPain;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySymptom;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;

public class PlayerPregnancySystemP1 extends PlayerPregnancySystemP0 {
	private int pregnancyPainTicks = 0;
	private int pregnancysymptonsTicks = 0;
	
	protected @Nonnegative int totalTicksOfCraving = MinepreggoModConfig.getTotalTicksOfCravingP1();
	protected @Nonnegative float morningSicknessProb = PregnancySystemHelper.LOW_MORNING_SICKNESS_PROBABILITY;

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
			
		if (!hasAllPregnancySymptoms()) {
			if (pregnancysymptonsTicks > 40) {
				tryInitPregnancySymptom();
				pregnancysymptonsTicks = 0;
			}
			else {
				++pregnancysymptonsTicks;
			}
		}
		if (!pregnancySystem.getPregnancySymptoms().isEmpty()) {
			evaluatePregnancySymptoms();
		}
		
		evaluatePregnancyNeeds();
		
		super.evaluatePregnancySystem();
	}
	
	@Override
	protected void evaluatePregnancyNeeds() {	
		evaluateCravingTimer();
	}
	
	protected void evaluateCravingTimer() {   				
		if (pregnancyEffects.getCraving() < PregnancySystemHelper.MAX_CRAVING_LEVEL) {
	        if (pregnancyEffects.getCravingTimer() >= totalTicksOfCraving) {
	        	pregnancyEffects.incrementCraving();
	        	pregnancyEffects.resetCravingTimer();
	        	pregnancyEffects.sync(pregnantEntity);
	        	
	        	MinepreggoMod.LOGGER.debug("Player {} craving level increased to: {}", 
	        			pregnantEntity.getGameProfile().getName(), pregnancyEffects.getCraving());
	        }
	        else {
	        	pregnancyEffects.incrementCravingTimer();
	        }
		}	
	}
	
	@Override
	protected void evaluateMiscarriage(ServerLevel serverLevel) {		
		if (pregnancySystem.getPregnancyPainTimer() > PregnancySystemHelper.TOTAL_TICKS_MISCARRIAGE) {
			pregnancySystem.resetPregnancyPainTimer();
			pregnancySystem.clearPregnancyPain();	
			
			femaleData.tryActivatePostPregnancyPhase(PostPregnancy.MISCARRIAGE);
				
			femaleData.resetPregnancy();
			femaleData.resetPregnancyOnClient(pregnantEntity);
			removePregnancy();
			
			femaleData.sync(pregnantEntity);
		} else {
			
    		for (ServerPlayer otherPlayer : serverLevel.getServer().getPlayerList().getPlayers()) {
    		    if (pregnantEntity.distanceToSqr(otherPlayer) <= 512.0) { 
    				serverLevel.sendParticles(otherPlayer, ParticleTypes.FALLING_DRIPSTONE_LAVA, true, pregnantEntity.getX(), (pregnantEntity.getY() + pregnantEntity.getBbHeight() * 0.35), pregnantEntity.getZ(),
    						1, 0, 1, 0, 0.02);
    		    }
    		}
			
			pregnancySystem.incrementPregnancyPainTimer();
		}
	}
	
	@Override
	protected void evaluatePregnancySymptoms() {			
		if (pregnancySystem.getPregnancySymptoms().contains(PregnancySymptom.CRAVING) && pregnancyEffects.getCraving() <= PregnancySystemHelper.DESACTIVATE_CRAVING_SYMPTOM) {	
			pregnancySystem.removePregnancySymptom(PregnancySymptom.CRAVING);
			pregnancyEffects.clearTypeOfCravingBySpecies();
			pregnantEntity.removeEffect(MinepreggoModMobEffects.CRAVING.get());
			pregnancySystem.sync(pregnantEntity);
			pregnancyEffects.sync(pregnantEntity);	
			MinepreggoMod.LOGGER.debug("Player {} pregnancy symptom cleared: {}",
					pregnantEntity.getGameProfile().getName(), PregnancySymptom.CRAVING.name());
		}
	}
	
	@Override
	protected void evaluatePregnancyPains() {
		if (pregnancySystem.getPregnancyPain() == PregnancyPain.MORNING_SICKNESS) {
			if (pregnancySystem.getPregnancyPainTimer() >= PregnancySystemHelper.TOTAL_TICKS_MORNING_SICKNESS) {
				pregnancySystem.clearPregnancyPain();
				pregnancySystem.resetPregnancyPainTimer();
				pregnancySystem.sync(pregnantEntity);
				
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
		return pregnancySystem.getPregnancySymptoms().containsAll(validPregnancySymptoms);
	}
	
	@Override
	protected boolean tryInitRandomPregnancyPain() {
		if (randomSource.nextFloat() < morningSicknessProb) {
			pregnancySystem.setPregnancyPain(PregnancyPain.MORNING_SICKNESS);
			pregnancySystem.sync(pregnantEntity);
			
			MinepreggoMod.LOGGER.debug("Player {} has developed pregnancy pain: {}",
					pregnantEntity.getGameProfile().getName(), PregnancyPain.MORNING_SICKNESS.name());

			return true;
		}	
		return false;
	}
	
	@Override
	protected boolean tryInitPregnancySymptom() {
		if (pregnancyEffects.getCraving() >= PregnancySystemHelper.MAX_CRAVING_LEVEL
				&& !pregnancySystem.getPregnancySymptoms().contains(PregnancySymptom.CRAVING)) {
			pregnancySystem.addPregnancySymptom(PregnancySymptom.CRAVING);
			pregnancyEffects.setTypeOfCraving(PregnancySystemHelper.getRandomCraving(randomSource));
			pregnantEntity.addEffect(new MobEffectInstance(MinepreggoModMobEffects.CRAVING.get(), -1, 0, true, true));
			pregnancySystem.sync(pregnantEntity);
			pregnancyEffects.sync(pregnantEntity);
			
			MinepreggoMod.LOGGER.debug("Player {} has developed pregnancy symptom: {}, all pregnancy symptoms: {}",
					pregnantEntity.getGameProfile().getName(), PregnancySymptom.CRAVING, pregnancySystem.getPregnancySymptoms());
			return true;
		}
		return false;
	}
	
	@Override
	protected void startMiscarriage() {
		MessageHelper.sendTo(pregnantEntity, Component.translatable("chat.minepreggo.player.miscarriage.message.init", pregnantEntity.getDisplayName().getString()));
		pregnancySystem.resetPregnancyPainTimer();
		pregnancySystem.setPregnancyPain(PregnancyPain.MISCARRIAGE);
		pregnantEntity.addEffect(new MobEffectInstance(MinepreggoModMobEffects.MISCARRIAGE.get(), PregnancySystemHelper.TOTAL_TICKS_MISCARRIAGE, 0, false, false, true));
		pregnancySystem.sync(pregnantEntity);
		MinepreggoMod.LOGGER.debug("Miscarriage just started");
	}
	
	// TODO: Redesign the way of resetting pregnancy data after birth
	@Override
	protected void initPostMiscarriage() {
    	MessageHelper.sendTo(pregnantEntity, Component.translatable("chat.minepreggo.player.miscarriage.message.post", pregnantEntity.getDisplayName().getString()));
    	// tryActivatePostPregnancyPhase only works if isPregnant flag is true
    	femaleData.tryActivatePostPregnancyPhase(PostPregnancy.MISCARRIAGE);
		femaleData.sync(pregnantEntity);
		
		// resetPregnancy creates new instance of pregnancy system, so it has to be called after tryActivatePostPregnancyPhase, because it changes isPregnant flag to false and tryActivatePostPregnancyPhase does nothing if it's false
		femaleData.resetPregnancy();
		femaleData.resetPregnancyOnClient(pregnantEntity);
		MinepreggoMod.LOGGER.debug("Player {} has entered postmiscarriage phase.", pregnantEntity.getGameProfile().getName());
	}
}
