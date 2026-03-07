package dev.dixmk.minepreggo.world.entity.player;

import java.util.Optional;

import org.apache.commons.lang3.tuple.ImmutablePair;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.init.MinepreggoMobEffects;
import dev.dixmk.minepreggo.init.MinepreggoSounds;
import dev.dixmk.minepreggo.network.capability.FemalePlayerImpl;
import dev.dixmk.minepreggo.network.capability.PlayerDataImpl;
import dev.dixmk.minepreggo.network.capability.PlayerPregnancyDataImpl;
import dev.dixmk.minepreggo.world.entity.BellyPartManager;
import dev.dixmk.minepreggo.world.entity.LivingEntityHelper;
import dev.dixmk.minepreggo.world.entity.preggo.Species;
import dev.dixmk.minepreggo.world.pregnancy.AbstractPregnancySystem;
import dev.dixmk.minepreggo.world.pregnancy.IBreedable;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPain;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPainInstance;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySymptom;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;

public abstract class AbstractPlayerPregnancySystem extends AbstractPregnancySystem<ServerPlayer> {	
	protected PlayerDataImpl playerData = null;
	protected FemalePlayerImpl femaleData = null;
	protected PlayerPregnancyDataImpl pregnancySystem = null;
	private final boolean dataLoaded;
	
	protected AbstractPlayerPregnancySystem(ServerPlayer pregnantEntity) {
		super(pregnantEntity);	
		pregnantEntity.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap -> {			
			this.playerData = cap;				
			cap.getFemaleData().ifPresent(f -> {
				this.femaleData = f;
				this.pregnancySystem = f.getPregnancyData();
			});	
		});			
		this.dataLoaded = this.playerData != null && this.pregnancySystem != null;
	}

	public boolean isPlayerValid(ServerPlayer currentPlayer) {
	    if (this.pregnantEntity == null || currentPlayer == null) {
	        return false;
	    }
	    
	    if (this.pregnantEntity.isRemoved() || currentPlayer.isRemoved()) {
	        return false;
	    }	   
	    
	    if (!this.pregnantEntity.getUUID().equals(currentPlayer.getUUID())) {
	        return false;
	    }

	    // Check if the player is connected
	    return this.pregnantEntity.connection != null &&
	    	       this.pregnantEntity.connection.connection != null &&
	    	       this.pregnantEntity.connection.connection.isConnected();
	}
	
	@Override
	public final void onServerTick() {			
		if (pregnantEntity.level().isClientSide) {
			return;
		}
		if (!this.isDataLoaded()) {
			MinepreggoMod.LOGGER.warn("PlayerPregnancySystem is not valid for player: {}. Aborting onServerTick. playerData: {}, femaleData: {}, pregnancySystem: {}",
					pregnantEntity.getGameProfile().getName(), this.playerData != null, this.femaleData != null, this.pregnancySystem != null);		
			return;
		}	
		evaluatePregnancySystem();
	}
	
	protected boolean isDataLoaded() {
		return this.dataLoaded;
	}
	
	@Override
	protected void evaluatePregnancySystem() {
		evaluatePregnancyTimer();
		
		if (canAdvanceNextPregnancyPhase() && !hasToGiveBirth()){
			advanceToNextPregnancyPhase();
		}	
	}
	
	@Override
	protected void evaluatePregnancyTimer() {
		if (this.pregnantEntity.hasEffect(MinepreggoMobEffects.ETERNAL_PREGNANCY.get())) {
			return;
		}
		
		if (pregnancySystem.getPregnancyTimer() > MinepreggoModConfig.SERVER.getTotalTicksByPregnancyDay()) {
			pregnancySystem.resetPregnancyTimer();
			pregnancySystem.incrementDaysPassed();
			pregnancySystem.reduceDaysToGiveBirth();
			MinepreggoMod.LOGGER.debug("Pregnancy day advanced to {} for player {}", 
					pregnancySystem.getDaysPassed(), pregnantEntity.getGameProfile().getName());
		} else {
			pregnancySystem.incrementPregnancyTimer();
		}	
	}
	
	@Override
	public boolean canAdvanceNextPregnancyPhase() {
		return pregnancySystem.getDaysPassed() >= pregnancySystem.getDaysByCurrentStage();
	}

	@Override
	protected void advanceToNextPregnancyPhase() {		
		final var previousStage = pregnancySystem.getCurrentPregnancyPhase();
		final var phases = PregnancyPhase.values();	
		final var next = phases[Math.min(previousStage.ordinal() + 1, phases.length - 1)];
		
		PlayerHelper.updateJigglePhysics(pregnantEntity, playerData.getSkinType(), next);
		
		pregnancySystem.setCurrentPregnancyPhase(next);
		pregnancySystem.resetPregnancyTimer();
		pregnancySystem.resetDaysPassed();
		pregnancySystem.syncState(pregnantEntity);		
			
		pregnantEntity.removeEffect(PlayerHelper.getPregnancyEffects(previousStage));
		pregnantEntity.addEffect(new MobEffectInstance(PlayerHelper.getPregnancyEffects(next), -1, 0, false, false, true));
		
		if (next.compareTo(PregnancyPhase.P0) > 0) {		
			var chestplate = pregnantEntity.getItemBySlot(EquipmentSlot.CHEST);
			var legginds = pregnantEntity.getItemBySlot(EquipmentSlot.LEGS);

			if (!chestplate.isEmpty()
					&& (!PregnancySystemHelper.canUseChestplate(chestplate.getItem(), next) || !PlayerHelper.canUseChestPlateInLactation(pregnantEntity, chestplate.getItem()))) {			
				PlayerHelper.removeAndDropItemStackFromEquipmentSlot(pregnantEntity, EquipmentSlot.CHEST);
			}
			
			if (!legginds.isEmpty()
					&& !PregnancySystemHelper.canUseLegging(legginds.getItem(), next)) {
				PlayerHelper.removeAndDropItemStackFromEquipmentSlot(pregnantEntity, EquipmentSlot.LEGS);
			}
		}
		
		if (MinepreggoModConfig.SERVER.isBellyColisionsForPlayersEnable()
				&& next.compareTo(PregnancyPhase.P4) >= 0) {
			BellyPartManager.getInstance().create(pregnantEntity, next);
		}
		
		MinepreggoMod.LOGGER.debug("Player {} advanced to next pregnancy phase: {}",
				pregnantEntity.getGameProfile().getName(), next);	
	}
	
	protected void removePregnancy() {	
		pregnantEntity.getActiveEffects().forEach(effect -> {
			var e = effect.getEffect();
			if (PregnancySystemHelper.isPregnancyEffect(e)) {
				pregnantEntity.removeEffect(e);
			}
		});	
		
		pregnantEntity.removeEffect(MinepreggoMobEffects.ETERNAL_PREGNANCY.get());
		pregnantEntity.removeEffect(MinepreggoMobEffects.ZERO_GRAVITY_BELLY.get());	
		 
		MinepreggoMod.LOGGER.debug("Pregnancy removed for player {}", pregnantEntity.getGameProfile().getName());
	}
	
	@Override
	public boolean initCommonPregnancyPain(PregnancyPain pain, int duration, int severity) {
		if (pain.type != PregnancyPain.Type.COMMON) {
			return false;
		}	
		
		boolean flag = false;
	
		if (pain == PregnancyPain.MORNING_SICKNESS) {
			pregnantEntity.addEffect(new MobEffectInstance(MinepreggoMobEffects.MORNING_SICKNESS.get(), duration, severity, false, false, true));
			flag = true;
		}
		else if (pain == PregnancyPain.FETAL_MOVEMENT) {
			LivingEntityHelper.playSoundNearTo(pregnantEntity, MinepreggoSounds.getRandomPregnancyPain(pregnantEntity.getRandom()));		
			pregnantEntity.addEffect(new MobEffectInstance(MinepreggoMobEffects.FETAL_MOVEMENT.get(), duration, severity, false, false, true));
			PlayerHelper.removeAndDropItemStackFromEquipmentSlot(pregnantEntity, EquipmentSlot.CHEST);	
			flag = true;
		}
		else if (pain == PregnancyPain.CONTRACTION) {
			LivingEntityHelper.playSoundNearTo(pregnantEntity, MinepreggoSounds.PLAYER_CONTRACTION.get());			
			pregnantEntity.addEffect(new MobEffectInstance(MinepreggoMobEffects.CONTRACTION.get(), duration, severity, false, false, true));
			PlayerHelper.removeAndDropItemStackFromEquipmentSlot(pregnantEntity, EquipmentSlot.CHEST);
			flag = true;
		}
			
		if (flag) {
			pregnancySystem.setPregnancyPain(new PregnancyPainInstance(pain, duration, severity));
			pregnancySystem.syncState(pregnantEntity);
			MinepreggoMod.LOGGER.debug("Player {} has developed pregnancy pain: {}", pregnantEntity.getGameProfile().getName(), pain);
			return true;
		}
		
		return false;
	}

	@Override
	public boolean initPregnancySymptom(PregnancySymptom symptom) {	
		if (this.pregnancySystem.getPregnancySymptoms().contains(symptom)) {
			return false;
		}
		
		boolean flag = false;
		RandomSource randomSource = pregnantEntity.getRandom();
		if (symptom == PregnancySymptom.CRAVING) {
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
			pregnantEntity.addEffect(new MobEffectInstance(MinepreggoMobEffects.CRAVING.get(), -1, 0, false, false, true));
			pregnancySystem.syncEffect(pregnantEntity);
			flag = true;
		}
		else if (symptom == PregnancySymptom.MILKING) { 
			pregnantEntity.addEffect(new MobEffectInstance(MinepreggoMobEffects.LACTATION.get(), -1, 0, false, false, true));
			flag = true;
		}
		else if (symptom == PregnancySymptom.BELLY_RUBS) {
			pregnantEntity.addEffect(new MobEffectInstance(MinepreggoMobEffects.BELLY_RUBS.get(), -1, 0, false, false, true));
			flag = true;
		}
		else if (symptom == PregnancySymptom.HORNY) {
			pregnantEntity.addEffect(new MobEffectInstance(MinepreggoMobEffects.HORNY.get(), -1, 0, false, false, true));
			femaleData.setSexualAppetite(IBreedable.MAX_SEXUAL_APPETIVE);	
			flag = true;
		}
		
		if (flag) {
			pregnancySystem.getPregnancySymptoms().add(symptom);
			pregnancySystem.syncState(pregnantEntity);
			MinepreggoMod.LOGGER.debug("Player {} has developed pregnancy symptom: {}, all pregnancy symptom: {}",
					pregnantEntity.getGameProfile().getName(), symptom, pregnancySystem.getPregnancySymptoms());
			return true;
		}

		return false;
	}
	
	public void refreshPregnancySymtoms() {
		pregnancySystem.getPregnancySymptoms().forEach(symptom -> {					
			boolean flag = false;		
			if (symptom == PregnancySymptom.CRAVING && pregnancySystem.getCraving() <= PregnancySystemHelper.DESACTIVATE_CRAVING_SYMPTOM) {
				pregnancySystem.clearTypeOfCravingBySpecies();
				pregnantEntity.removeEffect(MinepreggoMobEffects.CRAVING.get());
				flag = true;
			}
			else if (symptom == PregnancySymptom.MILKING && pregnancySystem.getMilking() <= PregnancySystemHelper.DESACTIVATE_MILKING_SYMPTOM) {
				pregnantEntity.removeEffect(MinepreggoMobEffects.LACTATION.get());
				flag = true;
			}
			else if (symptom == PregnancySymptom.BELLY_RUBS && pregnancySystem.getBellyRubs() <= PregnancySystemHelper.DESACTIVATEL_BELLY_RUBS_SYMPTOM) {
				pregnantEntity.removeEffect(MinepreggoMobEffects.BELLY_RUBS.get());
				flag = true;
			}
					
			if (flag) {			
				pregnancySystem.getPregnancySymptoms().remove(symptom);				
				pregnancySystem.syncState(pregnantEntity);
				pregnancySystem.syncEffect(pregnantEntity);
				MinepreggoMod.LOGGER.debug("Player {} pregnancy symptom cleared: {}", pregnantEntity.getGameProfile().getName(), symptom);
			}
		});	
	}
	
	public boolean tryInitMiscarriage() {
		var instance = pregnancySystem.getPregnancyPain();
		if (instance == null || (instance.getPain().type != PregnancyPain.Type.MISBIRTH && instance.getPain().type != PregnancyPain.Type.LABOR)) {
			this.startMiscarriage();
			return true;
		}
		return false;
	}
}
