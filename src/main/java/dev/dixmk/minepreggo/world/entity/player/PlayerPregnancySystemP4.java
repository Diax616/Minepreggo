package dev.dixmk.minepreggo.world.entity.player;

import java.util.ArrayList;

import javax.annotation.Nonnegative;

import org.checkerframework.checker.nullness.qual.NonNull;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.init.MinepreggoModMobEffects;
import dev.dixmk.minepreggo.init.MinepreggoModSounds;
import dev.dixmk.minepreggo.network.chat.MessageHelper;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;
import dev.dixmk.minepreggo.world.pregnancy.AbstractPregnancySystem;
import dev.dixmk.minepreggo.world.pregnancy.IBreedable;
import dev.dixmk.minepreggo.world.pregnancy.PostPregnancy;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPain;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySymptom;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;

public class PlayerPregnancySystemP4 extends PlayerPregnancySystemP3 {
	
	protected @Nonnegative int totalTicksOfHorny = MinepreggoModConfig.getTotalTicksOfHornyP4();
	protected @Nonnegative int totalTicksOfBirth = PregnancySystemHelper.TOTAL_TICKS_BIRTH_P4;
	protected @Nonnegative int totalTicksOfPreBirth = PregnancySystemHelper.TOTAL_TICKS_PREBIRTH_P4;
	protected @Nonnegative float contractionProb = PregnancySystemHelper.HIGH_PREGNANCY_PAIN_PROBABILITY;
	
	private int pushCoolDown = 0;
	protected float pushProb = 0.35f;
	
	public PlayerPregnancySystemP4(@NonNull ServerPlayer player) {
		super(player);
		addNewValidPregnancySymptoms(PregnancySymptom.HORNY);
	}
	
	@Override
	protected void initPregnancyTimers() {
		totalTicksOfCraving = MinepreggoModConfig.getTotalTicksOfCravingP4();
		totalTicksOfMilking = MinepreggoModConfig.getTotalTicksOfMilkingP4();
		totalTicksOfBellyRubs = MinepreggoModConfig.getTotalTicksOfBellyRubsP4();
		fetalMovementProb = PregnancySystemHelper.HIGH_PREGNANCY_PAIN_PROBABILITY;
		totalTicksOfFetalMovement = PregnancySystemHelper.TOTAL_TICKS_KICKING_P4;	
		pregnancyExhaustion = 1.03f;
	}
	
	@Override
	protected void evaluatePregnancyNeeds() {	
		super.evaluatePregnancyNeeds();
		evaluateHornyTimer();
	}
	
	@Override
	protected void evaluatePregnancySystem() {
		if (isInLabor()) {		
			if (pregnantEntity.level() instanceof ServerLevel serverLevel) {
				evaluateBirth(serverLevel);
			}		
			return;
		}
		
		if (isWaterBroken()) {
			if (pregnantEntity.level() instanceof ServerLevel serverLevel) {
				evaluateWaterBreaking(serverLevel);
			}
			return;
		}
				
		if (canAdvanceNextPregnancyPhase() && hasToGiveBirth()) {			
			if (pregnancySystem.getWomb().isWombOverloaded()) {
				if (pregnantEntity.isAlive()) {
					if (pregnantEntity.getAbilities().instabuild || pregnantEntity.isSpectator()) {
						return;
					}
					PregnancySystemHelper.tornWomb(pregnantEntity);
				}
				else {
					MinepreggoMod.LOGGER.debug("Player {} is dead", pregnantEntity.getGameProfile().getName());
				}
			}
			else {
				breakWater();
			}

			return;
		}
		
		super.evaluatePregnancySystem();
	}
	
	protected void evaluateHornyTimer() {   				
		if (pregnancySystem.getHorny() < PregnancySystemHelper.MAX_HORNY_LEVEL) {
	        if (pregnancySystem.getHornyTimer() >= totalTicksOfHorny) {
	        	pregnancySystem.incrementHorny();
	        	pregnancySystem.resetHornyTimer();
	        	pregnancySystem.syncEffect(pregnantEntity);
	        	
	        	MinepreggoMod.LOGGER.debug("Player {} horny level increased to: {}", 
	        			pregnantEntity.getGameProfile().getName(), pregnancySystem.getHorny());
	        }
	        else {
	        	pregnancySystem.incrementHornyTimer();
	        }
		}	
	}
	
	@Override
	protected boolean tryInitRandomPregnancyPain() {
		if (super.tryInitRandomPregnancyPain()) {
			return true;
		}	
			
		if (hasToGiveBirth() && !pregnantEntity.hasEffect(MinepreggoModMobEffects.ETERNAL_PREGNANCY.get())) {
			float newContractionProb = contractionProb;
			
			if (this.isMovingRidingSaddledHorse()) {
				newContractionProb *= (pregnancySystem.getCurrentPregnancyPhase().ordinal() + 4);
			}
			
			if (randomSource.nextFloat() < newContractionProb) {
				PlayerHelper.playSoundNearTo(pregnantEntity, MinepreggoModSounds.PLAYER_CONTRACTION.get());			
				pregnancySystem.setPregnancyPain(PregnancyPain.CONTRACTION);
				pregnantEntity.addEffect(new MobEffectInstance(MinepreggoModMobEffects.CONTRACTION.get(), totalTicksOfFetalMovement, 0, false, false, true));
				pregnancySystem.syncState(pregnantEntity);	
				PlayerHelper.removeAndDropItemStackFromEquipmentSlot(pregnantEntity, EquipmentSlot.CHEST);
				MinepreggoMod.LOGGER.debug("Player {} has developed pregnancy pain: {}",
						pregnantEntity.getGameProfile().getName(), pregnancySystem.getPregnancyPain());
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	protected boolean tryInitPregnancySymptom() {
		if (super.tryInitPregnancySymptom()) {
			return true;
		} 	
		if (pregnancySystem.getHorny() >= PregnancySystemHelper.MAX_HORNY_LEVEL
				&& !pregnancySystem.getPregnancySymptoms().containsPregnancySymptom(PregnancySymptom.HORNY)) {
			
			pregnancySystem.getPregnancySymptoms().addPregnancySymptom(PregnancySymptom.HORNY);
			pregnantEntity.addEffect(new MobEffectInstance(MinepreggoModMobEffects.HORNY.get(), -1, 0, false, false, true));
			femaleData.setSexualAppetite(IBreedable.MAX_SEXUAL_APPETIVE);
			
			pregnancySystem.syncState(pregnantEntity);

			MinepreggoMod.LOGGER.debug("Player {} has developed pregnancy symptom: {}, all pregnancy symptom: {}",
					pregnantEntity.getGameProfile().getName(), PregnancySymptom.HORNY, pregnancySystem.getPregnancySymptoms());
			return true;
		}

		return false;
	}
	

	@Override
	protected void startLabor() {
		tryHurt();
		PlayerHelper.playSoundNearTo(pregnantEntity, MinepreggoModSounds.getRandomPregnancyPain(randomSource));
		MessageHelper.sendTo(pregnantEntity, Component.translatable("chat.minepreggo.player.birth.message.pre", pregnantEntity.getDisplayName().getString()), true);
		pregnancySystem.setPregnancyPain(PregnancyPain.PREBIRTH);
		pregnancySystem.resetPregnancyPainTimer();
		pregnantEntity.addEffect(new MobEffectInstance(MinepreggoModMobEffects.PREBIRTH.get(), totalTicksOfPreBirth, 0, false, false, true));
		pregnancySystem.syncState(pregnantEntity);
		MinepreggoMod.LOGGER.debug("Player {} has started labor.", pregnantEntity.getGameProfile().getName());
	}
	
	@Override
	protected void evaluateBirth(ServerLevel serverLevel) {		
		final var pain = pregnancySystem.getPregnancyPain();
		
		if (pain == PregnancyPain.PREBIRTH) {		
			if (pregnancySystem.getPregnancyPainTimer() >= totalTicksOfPreBirth) {
				pregnancySystem.setPregnancyPain(PregnancyPain.BIRTH);
				pregnantEntity.removeEffect(MinepreggoModMobEffects.PREBIRTH.get());
				tryHurt();
				pregnantEntity.addEffect(new MobEffectInstance(MinepreggoModMobEffects.BIRTH.get(), totalTicksOfBirth, 0, false, false, true));
				PlayerHelper.playSoundNearTo(pregnantEntity, MinepreggoModSounds.PLAYER_BIRTH.get());
				pregnancySystem.resetPregnancyPainTimer();
				PlayerHelper.removeAndDropItemStackFromEquipmentSlot(pregnantEntity, EquipmentSlot.CHEST);
				PlayerHelper.removeAndDropItemStackFromEquipmentSlot(pregnantEntity, EquipmentSlot.LEGS);
				PlayerHelper.removeAndDropItemStackFromEquipmentSlot(pregnantEntity, EquipmentSlot.MAINHAND);
				PlayerHelper.removeAndDropItemStackFromEquipmentSlot(pregnantEntity, EquipmentSlot.OFFHAND);
				MessageHelper.sendTo(pregnantEntity, Component.translatable("chat.minepreggo.player.birth.message.pre", pregnantEntity.getDisplayName().getString()));
			}	
			else {
				pregnancySystem.incrementPregnancyPainTimer();
	    		AbstractPregnancySystem.spawnParticulesForWaterBreaking(serverLevel, pregnantEntity);
			}
		}
		else if (pain == PregnancyPain.BIRTH) {
			if (pregnancySystem.getPregnancyPainTimer() >= totalTicksOfBirth) {			
				
	        	final var aliveBabiesItemStacks = new ArrayList<>(PregnancySystemHelper.getAliveBabies(pregnancySystem.getWomb()));   	
	       		
	        	MinepreggoMod.LOGGER.debug("Player {} is giving birth to {} babies.", pregnantEntity.getDisplayName().getString(), aliveBabiesItemStacks.size());
	        	
	        	if (aliveBabiesItemStacks.isEmpty()) {
					MinepreggoMod.LOGGER.error("Failed to get baby item for pregnancy system {} birth.", pregnancySystem.getCurrentPregnancyPhase());
				}
	        	
	        	// TODO: Babies itemstacks are only removed if player's hands are empty. It should handle stacking unless itemstack is a baby item.
	        	aliveBabiesItemStacks.removeIf(baby -> {
	        		if (pregnantEntity.getItemInHand(InteractionHand.MAIN_HAND).isEmpty()) {        			
	        			PlayerHelper.replaceAndDropItemstackInHand(pregnantEntity, InteractionHand.MAIN_HAND, baby);
	            		return true;
	        		}
	        		else if (pregnantEntity.getItemInHand(InteractionHand.OFF_HAND).isEmpty()) {
	        			PlayerHelper.replaceAndDropItemstackInHand(pregnantEntity, InteractionHand.OFF_HAND, baby);
	            		return true;
	        		}
	        		return false;
	        	});
	        	
	        	if (!aliveBabiesItemStacks.isEmpty()) {	              	
		        	aliveBabiesItemStacks.forEach(baby -> {
		        		if(!pregnantEntity.getInventory().add(baby)) 
		        			PreggoMobHelper.dropItemStack(pregnantEntity, baby);
		        	}); 						
	        	}
	        	        	
				initPostPartum();		
				MinepreggoMod.LOGGER.debug("Player {} has given birth.", pregnantEntity.getDisplayName().getString());	
			}	
			else {
				pregnancySystem.incrementPregnancyPainTimer();
				tryPlayPushSound();
				tryHurtByCooldown();
			}
		}
	}
	
	@Override
	protected void evaluateWaterBreaking(ServerLevel serverLevel) {
		if (pregnancySystem.getPregnancyPainTimer() >= PregnancySystemHelper.TOTAL_TICKS_WATER_BREAKING) {
			startLabor();
			pregnantEntity.removeEffect(MinepreggoModMobEffects.WATER_BREAKING.get());
		}
		else {
			pregnancySystem.incrementPregnancyPainTimer();
    		AbstractPregnancySystem.spawnParticulesForWaterBreaking(serverLevel, pregnantEntity);
		}
	}
	
	@Override
	protected void breakWater() {	
		tryHurt();
		PlayerHelper.playSoundNearTo(pregnantEntity, MinepreggoModSounds.getRandomPregnancyPain(randomSource));
		MessageHelper.sendTo(pregnantEntity, Component.translatable("chat.minepreggo.player.birth.message.start", pregnantEntity.getDisplayName().getString()), true);
		pregnancySystem.resetPregnancyPainTimer();
		pregnancySystem.setPregnancyPain(PregnancyPain.WATER_BREAKING);
		pregnantEntity.addEffect(new MobEffectInstance(MinepreggoModMobEffects.WATER_BREAKING.get(), PregnancySystemHelper.TOTAL_TICKS_WATER_BREAKING, 0, false, false, true));
		pregnancySystem.syncState(pregnantEntity);
		MinepreggoMod.LOGGER.debug("Player {} water has broken.", pregnantEntity.getGameProfile().getName());
	}
	
	@Override
	protected boolean hasToGiveBirth() {
		return pregnancySystem.getLastPregnancyStage() == pregnancySystem.getCurrentPregnancyPhase();
	}
	
	@Override
	protected boolean isInLabor() {
		final var pain = pregnancySystem.getPregnancyPain();
		return pain == PregnancyPain.PREBIRTH || pain == PregnancyPain.BIRTH;
 	}
	
	@Override
	protected boolean isWaterBroken() {
		return pregnancySystem.getPregnancyPain() == PregnancyPain.WATER_BREAKING;
 	}
	
	@Override
	protected void evaluatePregnancyPains() {
		super.evaluatePregnancyPains();
		if (pregnancySystem.getPregnancyPain() == PregnancyPain.CONTRACTION) {
			if (pregnancySystem.getPregnancyPainTimer() >= totalTicksOfFetalMovement) {
				pregnantEntity.removeEffect(MinepreggoModMobEffects.CONTRACTION.get());
				pregnancySystem.clearPregnancyPain();
				pregnancySystem.resetPregnancyPainTimer();
				pregnancySystem.syncState(pregnantEntity);
				
				MinepreggoMod.LOGGER.debug("Player {} pregnancy pain cleared: {}",
						pregnantEntity.getGameProfile().getName(), PregnancyPain.CONTRACTION.name());
			} else {
				pregnancySystem.incrementPregnancyPainTimer();
				tryHurtByCooldown();
			}
		}
	}
	
	// TODO: Redesign the way of resetting pregnancy data after birth
	@Override
	protected void initPostPartum() {
    	MessageHelper.sendTo(pregnantEntity, Component.translatable("chat.minepreggo.player.birth.message.post", Integer.toString(pregnancySystem.getWomb().getNumOfBabies())));	
    	
		PlayerHelper.updateJigglePhysics(pregnantEntity, playerData.getSkinType(), null);
    	
    	// tryActivatePostPregnancyPhase only works if isPregnant flag is true
    	femaleData.tryActivatePostPregnancyPhase(PostPregnancy.PARTUM);
		femaleData.sync(pregnantEntity);
		
		// resetPregnancy creates new instance of pregnancy system, so it has to be called after tryActivatePostPregnancyPhase, because it changes isPregnant flag to false and tryActivatePostPregnancyPhase does nothing if it's false
		femaleData.resetPregnancy();
		femaleData.resetPregnancyOnClient(pregnantEntity);
		
		pregnantEntity.addEffect(new MobEffectInstance(MinepreggoModMobEffects.MATERNITY.get(), MinepreggoModConfig.getTotalTicksOfPostPregnancyPhase(), 0, false, false, true));
		pregnantEntity.addEffect(new MobEffectInstance(MobEffects.LUCK, 12000, 0, false, true, true));
		pregnantEntity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 3600, 0, false, false, true));
		
		removePregnancy();
		
		PregnancySystemHelper.applyPostPregnancyNerf(pregnantEntity);
		MinepreggoMod.LOGGER.debug("Player {} has entered postpartum phase.", pregnantEntity.getGameProfile().getName());
	}
	
	protected boolean tryPlayPushSound() {
		if (pushCoolDown < 60) {
			++pushCoolDown;
			return false;
		}
		pushCoolDown = 0;	
		if (randomSource.nextFloat() < pushProb) {
			PlayerHelper.playSoundNearTo(pregnantEntity, MinepreggoModSounds.getRandomPlayerPush(randomSource));	
			return true;
		}	
		return false;
	}
}
