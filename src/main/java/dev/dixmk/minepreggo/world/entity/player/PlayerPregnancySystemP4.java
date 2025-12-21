package dev.dixmk.minepreggo.world.entity.player;

import java.util.ArrayList;

import javax.annotation.Nonnegative;

import org.checkerframework.checker.nullness.qual.NonNull;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.init.MinepreggoModMobEffects;
import dev.dixmk.minepreggo.network.chat.MessageHelper;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;
import dev.dixmk.minepreggo.world.pregnancy.AbstractPregnancySystem;
import dev.dixmk.minepreggo.world.pregnancy.PostPregnancy;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPain;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySymptom;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;

public class PlayerPregnancySystemP4 extends PlayerPregnancySystemP3 {
	
	protected @Nonnegative int totalTicksOfHorny = MinepreggoModConfig.getTotalTicksOfHornyP4();
	protected @Nonnegative int totalTicksOfBirth = PregnancySystemHelper.TOTAL_TICKS_BIRTH_P4;
	protected @Nonnegative int totalTicksOfPreBirth = PregnancySystemHelper.TOTAL_TICKS_PREBIRTH_P4;
	protected @Nonnegative float contractionProb = PregnancySystemHelper.HIGH_PREGNANCY_PAIN_PROBABILITY;

	public PlayerPregnancySystemP4(@NonNull ServerPlayer player) {
		super(player);
		addNewValidPregnancySymptoms(PregnancySymptom.HORNY);
	}
	
	@Override
	protected void initPregnancyTimers() {
		totalTicksOfCraving = MinepreggoModConfig.getTotalTicksOfCravingP4();
		totalTicksOfMilking = MinepreggoModConfig.getTotalTicksOfMilkingP4();
		totalTicksOfBellyRubs = MinepreggoModConfig.getTotalTicksOfBellyRubsP4();
		fetalMovementProb = PregnancySystemHelper.MEDIUM_PREGNANCY_PAIN_PROBABILITY;
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
			breakWater();
			return;
		}
		
		super.evaluatePregnancySystem();
	}
	
	protected void evaluateHornyTimer() {   				
		if (pregnancyEffects.getHorny() < PregnancySystemHelper.MAX_HORNY_LEVEL) {
	        if (pregnancyEffects.getHornyTimer() >= totalTicksOfHorny) {
	        	pregnancyEffects.incrementHorny();
	        	pregnancyEffects.resetHornyTimer();
	        	pregnancyEffects.sync(pregnantEntity);
	        	
	        	MinepreggoMod.LOGGER.debug("Player {} horny level increased to: {}", 
	        			pregnantEntity.getGameProfile().getName(), pregnancyEffects.getBellyRubs());
	        }
	        else {
	        	pregnancyEffects.incrementHornyTimer();
	        }
		}	
	}
	
	@Override
	protected boolean tryInitRandomPregnancyPain() {
		if (super.tryInitRandomPregnancyPain()) {
			return true;
		}	
			
		if (hasToGiveBirth() && randomSource.nextFloat() < contractionProb) {	
			pregnancySystem.setPregnancyPain(PregnancyPain.CONTRACTION);
			pregnancySystem.sync(pregnantEntity);
			
			PlayerHelper.removeAndDropItemStackFromEquipmentSlot(pregnantEntity, EquipmentSlot.CHEST);
			
			MinepreggoMod.LOGGER.debug("Player {} has developed pregnancy pain: {}",
					pregnantEntity.getGameProfile().getName(), pregnancySystem.getPregnancyPain());
			return true;
		}
		
		return false;
	}
	
	@Override
	protected boolean tryInitPregnancySymptom() {
		if (super.tryInitPregnancySymptom()) {
			return true;
		} 	
		if (pregnancyEffects.getHorny() >= PregnancySystemHelper.MAX_HORNY_LEVEL
				&& !pregnancySystem.getPregnancySymptoms().contains(PregnancySymptom.HORNY)) {
			pregnancySystem.addPregnancySymptom(PregnancySymptom.HORNY);
			pregnantEntity.addEffect(new MobEffectInstance(MinepreggoModMobEffects.HORNY.get(), -1, 0, true, true));
			pregnancySystem.sync(pregnantEntity);
			pregnancyEffects.sync(pregnantEntity);	
			MinepreggoMod.LOGGER.debug("Player {} has developed pregnancy symptom: {}",
					pregnantEntity.getGameProfile().getName(), PregnancySymptom.HORNY);
			return true;
		}

		return false;
	}
	

	@Override
	protected void startLabor() {
    	MessageHelper.sendTo(pregnantEntity, Component.translatable("chat.minepreggo.player.birth.message.warning", pregnantEntity.getDisplayName().getString()));
		pregnancySystem.setPregnancyPain(PregnancyPain.PREBIRTH);
		pregnancySystem.resetPregnancyPainTimer();
		pregnantEntity.addEffect(new MobEffectInstance(MinepreggoModMobEffects.PREBIRTH.get(), totalTicksOfPreBirth, 0, false, false, true));
		pregnancySystem.sync(pregnantEntity);
		MinepreggoMod.LOGGER.debug("Player {} has started labor.", pregnantEntity.getGameProfile().getName());
	}
	
	@Override
	protected void evaluateBirth(ServerLevel serverLevel) {		
		final var pain = pregnancySystem.getPregnancyPain();
		
		if (pain == PregnancyPain.PREBIRTH) {		
			if (pregnancySystem.getPregnancyPainTimer() >= totalTicksOfPreBirth) {
				pregnancySystem.setPregnancyPain(PregnancyPain.BIRTH);
				pregnantEntity.removeEffect(MinepreggoModMobEffects.PREBIRTH.get());
				pregnantEntity.addEffect(new MobEffectInstance(MinepreggoModMobEffects.BIRTH.get(), totalTicksOfBirth, 0, false, false, true));
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
					MinepreggoMod.LOGGER.error("Failed to get baby item for pregnancy system {} birth.", pregnancySystem.getCurrentPregnancyStage());
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
	        	        	
	        	MessageHelper.sendTo(pregnantEntity, Component.translatable("chat.minepreggo.player.birth.message.post", pregnantEntity.getDisplayName().getString()));
				initPostPartum();		
				MinepreggoMod.LOGGER.debug("Player {} has given birth.", pregnantEntity.getDisplayName().getString());	
			}	
			else {
				pregnancySystem.incrementPregnancyPainTimer();
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
    	MessageHelper.sendTo(pregnantEntity, Component.translatable("chat.minepreggo.player.birth.message.init", pregnantEntity.getDisplayName().getString()));
		pregnancySystem.resetPregnancyPainTimer();
		pregnancySystem.setPregnancyPain(PregnancyPain.WATER_BREAKING);
		pregnantEntity.addEffect(new MobEffectInstance(MinepreggoModMobEffects.WATER_BREAKING.get(), PregnancySystemHelper.TOTAL_TICKS_WATER_BREAKING, 0, false, false, true));
		pregnancySystem.sync(pregnantEntity);
		MinepreggoMod.LOGGER.debug("Player {} water has broken.", pregnantEntity.getGameProfile().getName());
	}
	
	@Override
	protected boolean hasToGiveBirth() {
		return pregnancySystem.getLastPregnancyStage() == pregnancySystem.getCurrentPregnancyStage();
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
	
	// TODO: Redesign the way of resetting pregnancy data after birth
	@Override
	protected void initPostPartum() {
    	MessageHelper.sendTo(pregnantEntity, Component.translatable("chat.minepreggo.player.birth.message.post", pregnantEntity.getDisplayName().getString()));	
    	
    	// tryActivatePostPregnancyPhase only works if isPregnant flag is true
    	femaleData.tryActivatePostPregnancyPhase(PostPregnancy.PARTUM);
		femaleData.sync(pregnantEntity);
		
		// resetPregnancy creates new instance of pregnancy system, so it has to be called after tryActivatePostPregnancyPhase, because it changes isPregnant flag to false and tryActivatePostPregnancyPhase does nothing if it's false
		femaleData.resetPregnancy();
		femaleData.resetPregnancyOnClient(pregnantEntity);
		
		removePregnancy();
		
		pregnantEntity.addEffect(new MobEffectInstance(MinepreggoModMobEffects.MATERNITY.get(), 32000, 0, false, false, true));
		MinepreggoMod.LOGGER.debug("Player {} has entered postpartum phase.", pregnantEntity.getGameProfile().getName());
	}
}
