package dev.dixmk.minepreggo.world.entity.player;

import javax.annotation.Nonnegative;

import org.checkerframework.checker.nullness.qual.NonNull;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.init.MinepreggoAdvancements;
import dev.dixmk.minepreggo.init.MinepreggoMobEffects;
import dev.dixmk.minepreggo.init.MinepreggoSounds;
import dev.dixmk.minepreggo.network.chat.MessageHelper;
import dev.dixmk.minepreggo.world.entity.EntityHelper;
import dev.dixmk.minepreggo.world.entity.LivingEntityHelper;
import dev.dixmk.minepreggo.world.item.BabyItem;
import dev.dixmk.minepreggo.world.pregnancy.BabyData;
import dev.dixmk.minepreggo.world.pregnancy.BirthData;
import dev.dixmk.minepreggo.world.pregnancy.PostPregnancy;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPain;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPainInstance;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySymptom;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import dev.dixmk.minepreggo.world.pregnancy.Womb;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class PlayerPregnancySystemP4 extends PlayerPregnancySystemP3 {
	protected @Nonnegative int totalTicksOfHorny = MinepreggoModConfig.SERVER.getTotalTicksOfHornyP4();
	protected @Nonnegative int totalTicksOfBirth = PregnancySystemHelper.TOTAL_TICKS_BIRTH_P4;
	protected @Nonnegative int totalTicksOfPreBirth = PregnancySystemHelper.TOTAL_TICKS_PREBIRTH_P4;
	protected @Nonnegative float contractionProb = PregnancySystemHelper.HIGH_PREGNANCY_PAIN_PROBABILITY;	
	private int pushCoolDown = 0;
	protected float pushProb = 0.5f;
	private boolean wasWombOverloadMessageSent = false;
	
	public PlayerPregnancySystemP4(@NonNull ServerPlayer player) {
		super(player);
		addNewValidPregnancySymptoms(PregnancySymptom.HORNY);
	}
	
	@Override
	protected void initPregnancyTimers() {
		totalTicksOfCraving = MinepreggoModConfig.SERVER.getTotalTicksOfCravingP4();
		totalTicksOfMilking = MinepreggoModConfig.SERVER.getTotalTicksOfMilkingP4();
		totalTicksOfBellyRubs = MinepreggoModConfig.SERVER.getTotalTicksOfBellyRubsP4();
		fetalMovementProb = PregnancySystemHelper.HIGH_PREGNANCY_PAIN_PROBABILITY;
		totalTicksOfFetalMovement = PregnancySystemHelper.TOTAL_TICKS_KICKING_P4;	
		pregnancyExhaustion = 0.04f;
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
					if (PlayerHelper.isInvencible(pregnantEntity)) {	
						if (!wasWombOverloadMessageSent) {
							MessageHelper.sendTo(pregnantEntity, Component.translatable("chat.minepreggo.player.pregnancy.message.womb_is_overloaded.creative", pregnantEntity.getDisplayName().getString()), true);
							wasWombOverloadMessageSent = true;
						}
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
			
		if (hasToGiveBirth() && !pregnantEntity.hasEffect(MinepreggoMobEffects.ETERNAL_PREGNANCY.get())) {
			float newContractionProb = contractionProb;
			
			if (this.isMovingRidingSaddledHorse()) {
				newContractionProb *= (pregnancySystem.getCurrentPregnancyPhase().ordinal() + 4);
			}
			
			if (pregnantEntity.getRandom().nextFloat() < newContractionProb) {
				this.initCommonPregnancyPain(PregnancyPain.CONTRACTION, totalTicksOfFetalMovement, 0);
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
				&& !pregnancySystem.getPregnancySymptoms().contains(PregnancySymptom.HORNY)) {
			this.initPregnancySymptom(PregnancySymptom.HORNY);
			return true;
		}

		return false;
	}
	
	@Override
	protected void startLabor() {
		tryHurt();
		LivingEntityHelper.playSoundNearTo(pregnantEntity, MinepreggoSounds.getRandomPregnancyPain(pregnantEntity.getRandom()));
		MessageHelper.sendTo(pregnantEntity, Component.translatable("chat.minepreggo.player.birth.message.pre", pregnantEntity.getDisplayName().getString()), true);
		pregnancySystem.setPregnancyPain(new PregnancyPainInstance(PregnancyPain.PREBIRTH, totalTicksOfPreBirth, 0));	
		pregnantEntity.addEffect(new MobEffectInstance(MinepreggoMobEffects.PREBIRTH.get(), totalTicksOfPreBirth, 0, false, false, true));
		pregnancySystem.syncState(pregnantEntity);
		MinepreggoMod.LOGGER.debug("Player {} has started labor.", pregnantEntity.getGameProfile().getName());
	}
	
	@Override
	protected void evaluateBirth(ServerLevel serverLevel) {		
		final var instance = pregnancySystem.getPregnancyPain();
		if (instance == null) {
			MinepreggoMod.LOGGER.warn("Pregnancy pain instance is null for player {} during birth evaluation.", pregnantEntity.getGameProfile().getName());
			return;
		}
		
		if (instance.getPain() == PregnancyPain.PREBIRTH) {		
			if (instance.isExpired()) {
				tryHurt();
				int totalTicksForBirth = pregnancySystem.getWomb().stream()
						.mapToInt(PregnancySystemHelper::calculateBirthDuration)
						.sum() + 200;

				pregnancySystem.setPregnancyPain(new PregnancyPainInstance(PregnancyPain.BIRTH, totalTicksForBirth, 0));
				pregnantEntity.removeEffect(MinepreggoMobEffects.PREBIRTH.get());
				pregnantEntity.addEffect(new MobEffectInstance(MinepreggoMobEffects.BIRTH.get(), totalTicksForBirth, 0, false, false, true));
				LivingEntityHelper.playSoundNearTo(pregnantEntity, MinepreggoSounds.PLAYER_BIRTH_1.get());
				PlayerHelper.removeAndDropItemStackFromEquipmentSlot(pregnantEntity, EquipmentSlot.CHEST);
				PlayerHelper.removeAndDropItemStackFromEquipmentSlot(pregnantEntity, EquipmentSlot.LEGS);
				PlayerHelper.removeAndDropItemStackFromEquipmentSlot(pregnantEntity, EquipmentSlot.MAINHAND);
				PlayerHelper.removeAndDropItemStackFromEquipmentSlot(pregnantEntity, EquipmentSlot.OFFHAND);
				Womb womb = pregnancySystem.getWomb();
				womb.refreshOriginalNumOfBabies();
				BabyData babyData = womb.removeBaby();
				pregnancySystem.setBirthData(new BirthData(babyData, PregnancySystemHelper.calculateBirthDuration(babyData)));	
				MessageHelper.sendTo(pregnantEntity, Component.translatable("chat.minepreggo.player.birth.message.pre", pregnantEntity.getDisplayName().getString()));
			}	
			else {
				instance.tick();
				spawnParticulesForWaterBreaking(serverLevel);
			}
		}
		else if (instance.getPain() == PregnancyPain.BIRTH) {
			if (instance.isExpired()) {							
	        	MinepreggoMod.LOGGER.debug("Succesful births for player {}: {}", pregnantEntity.getDisplayName().getString(), playerData.getPlayerStatistic().getSuccessfulBirths());
	        	initPostPartum();		
			}	
			else {
				instance.tick();
				BirthData birthData = pregnancySystem.getBirthData();
				if (birthData != null) { 
					birthData.tick();				
					if (birthData.isBirthComplete()) {
						BabyData babyData = birthData.getBabyData();
						Item babyItem = PregnancySystemHelper.getAliveBabyItem(babyData.typeOfSpecies, babyData.typeOfCreature);
						if (babyItem != null) {		
							tryHurt();
							LivingEntityHelper.playSoundNearTo(pregnantEntity, MinepreggoSounds.getRandomPlayerBirth(pregnantEntity.getRandom()));
							LivingEntityHelper.playSoundNearTo(pregnantEntity, MinepreggoSounds.SPLASH.get(), 0.3f);
							ItemStack babyItemStack = BabyItem.createBabyItemStack(babyData.motherId, babyData.fatherId.orElse(null), babyItem);
							playerData.getPlayerStatistic().addSuccessfulBirth(babyData.typeOfSpecies);
							EntityHelper.spawnItemOn(pregnantEntity, babyItemStack);			
							BabyData nextBabyData = pregnancySystem.getWomb().removeBaby();
							if (nextBabyData != null) {
								pregnancySystem.setBirthData(new BirthData(nextBabyData, PregnancySystemHelper.calculateBirthDuration(nextBabyData)));
							}
							else {
								pregnancySystem.setBirthData(null);
							}
						}
					}
					else {
						tryPlayPushSound();
					}
				}
			}
		}
	}
	
	@Override
	protected void evaluateWaterBreaking(ServerLevel serverLevel) {
		final var instance = pregnancySystem.getPregnancyPain();
		if (instance == null) {
			MinepreggoMod.LOGGER.warn("Pregnancy pain instance is null for player {} during water breaking evaluation.", pregnantEntity.getGameProfile().getName());
			return;
		}
		
		if (instance.isExpired()) {
			startLabor();
			pregnantEntity.removeEffect(MinepreggoMobEffects.WATER_BREAKING.get());
		}
		else {
			instance.tick();
			spawnParticulesForWaterBreaking(serverLevel);
		}
	}
	
	@Override
	protected void breakWater() {	
		tryHurt();
		LivingEntityHelper.playSoundNearTo(pregnantEntity, MinepreggoSounds.getRandomPregnancyPain(pregnantEntity.getRandom()));
		MessageHelper.sendTo(pregnantEntity, Component.translatable("chat.minepreggo.player.birth.message.start", pregnantEntity.getDisplayName().getString()), true);
		pregnancySystem.setPregnancyPain(new PregnancyPainInstance(PregnancyPain.WATER_BREAKING, PregnancySystemHelper.TOTAL_TICKS_WATER_BREAKING, 0));
		pregnantEntity.addEffect(new MobEffectInstance(MinepreggoMobEffects.WATER_BREAKING.get(), PregnancySystemHelper.TOTAL_TICKS_WATER_BREAKING, 0, false, false, true));
		pregnancySystem.syncState(pregnantEntity);
		MinepreggoMod.LOGGER.debug("Player {} water has broken.", pregnantEntity.getGameProfile().getName());
	}
	
	@Override
	protected boolean hasToGiveBirth() {
		return pregnancySystem.getLastPregnancyStage() == pregnancySystem.getCurrentPregnancyPhase();
	}
	
	@Override
	protected boolean isInLabor() {
		final var instance = pregnancySystem.getPregnancyPain();
		return instance != null && (instance.getPain() == PregnancyPain.PREBIRTH || instance.getPain() == PregnancyPain.BIRTH);
 	}
	
	@Override
	protected boolean isWaterBroken() {
		final var instance = pregnancySystem.getPregnancyPain();
		return instance != null && instance.getPain() == PregnancyPain.WATER_BREAKING;
 	}
	
	// TODO: Redesign the way of resetting pregnancy data after birth
	@Override
	protected void initPostPartum() {
		MinepreggoMod.LOGGER.debug("Player {} has entered postpartum phase.", pregnantEntity.getGameProfile().getName());

		MinepreggoAdvancements.GIVE_BIRTH_TRIGGER.trigger(pregnantEntity);

		MessageHelper.sendTo(pregnantEntity, Component.translatable("chat.minepreggo.player.birth.message.post", Integer.toString(pregnancySystem.getWomb().getOriginalNumOfBabies())));	
    	
		PlayerHelper.updateJigglePhysics(pregnantEntity, playerData.getSkinType(), null);
    	
    	// tryActivatePostPregnancyPhase only works if isPregnant flag is true
    	femaleData.tryActivatePostPregnancyPhase(PostPregnancy.PARTUM);
		femaleData.sync(pregnantEntity);
		
		// resetPregnancy creates new instance of pregnancy system, so it has to be called after tryActivatePostPregnancyPhase, because it changes isPregnant flag to false and tryActivatePostPregnancyPhase does nothing if it's false
		femaleData.resetPregnancy();
		femaleData.resetPregnancyOnClient(pregnantEntity);
		
		pregnantEntity.addEffect(new MobEffectInstance(MinepreggoMobEffects.MATERNITY.get(), MinepreggoModConfig.SERVER.getTotalTicksOfPostPregnancyPhase(), 0, false, false, true));
		pregnantEntity.addEffect(new MobEffectInstance(MobEffects.LUCK, 12000, 0, false, true, true));
		pregnantEntity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 3600, 0, false, false, true));
				
		PregnancySystemHelper.applyPostPregnancyNerf(pregnantEntity);
		tryHurt();
		
		removePregnancy();		
	}
	
	protected boolean tryPlayPushSound() {
		if (pushCoolDown < 60) {
			++pushCoolDown;
			return false;
		}
		pushCoolDown = 0;	
		if (pregnantEntity.getRandom().nextFloat() < pushProb) {
			LivingEntityHelper.playSoundNearTo(pregnantEntity, MinepreggoSounds.getRandomPlayerPush(pregnantEntity.getRandom()));	
			return true;
		}	
		return false;
	}
}
