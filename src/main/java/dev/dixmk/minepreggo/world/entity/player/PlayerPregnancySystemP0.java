package dev.dixmk.minepreggo.world.entity.player;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.init.MinepreggoModMobEffects;
import dev.dixmk.minepreggo.network.capability.FemalePlayerImpl;
import dev.dixmk.minepreggo.network.capability.PlayerDataImpl;
import dev.dixmk.minepreggo.network.capability.PlayerPregnancyEffectsImpl;
import dev.dixmk.minepreggo.network.capability.PlayerPregnancySystemImpl;
import dev.dixmk.minepreggo.world.pregnancy.AbstractPregnancySystem;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;

public class PlayerPregnancySystemP0 extends AbstractPregnancySystem<ServerPlayer> {
	
	protected PlayerDataImpl playerData = null;
	protected FemalePlayerImpl femaleData = null;
	protected PlayerPregnancySystemImpl pregnancySystem = null;
	protected PlayerPregnancyEffectsImpl pregnancyEffects = null;
	
	private final boolean isValid;
	
	public PlayerPregnancySystemP0(ServerPlayer player) {
		super(player);
		
		player.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap -> {			
			this.playerData = cap;				
			cap.getFemaleData().ifPresent(f -> {
				this.femaleData = f;
				this.pregnancySystem = f.getPregnancySystem();
				this.pregnancyEffects = f.getPregnancyEffects();
			});	
		});	
		
		this.isValid = this.playerData != null && this.pregnancySystem != null && this.pregnancyEffects != null;
	}

	public boolean isPlayerValid(ServerPlayer currentPlayer) {
	    // Check if the stored player is the same instance and has a valid connection
	    return this.pregnantEntity == currentPlayer && 
	           this.pregnantEntity.connection != null && 
	           this.pregnantEntity.connection.connection.isConnected();
	}
	
	@Override
	public final void onServerTick() {			
		if (pregnantEntity.level().isClientSide) {
			return;
		}
		
		if (!isValid) {
			MinepreggoMod.LOGGER.warn("PlayerPregnancySystemP0 is not valid for player: {}. Aborting onServerTick. playerData: {}, femaleData: {}, pregnancySystem: {}, pregnancyEffects: {}",
					pregnantEntity.getGameProfile().getName(), this.playerData != null, this.femaleData != null, this.pregnancySystem != null, this.pregnancyEffects != null);
			
			
			return;
		}	

		evaluatePregnancySystem();
	}
	
	@Override
	protected void initPostMiscarriage() {
		// This pregnancy phase does not miscarriage yet
	}

	@Override
	protected void initPostPartum() {
		// This pregnancy phase does not support birth yet
	}
	
	@Override
	protected boolean hasToGiveBirth() {
		return false;
	}
	
	@Override
	protected boolean isInLabor() {
		return false;
 	}
	
	@Override
	protected void startLabor() {
		// This pregnancy phase does not support birth yet
	}
	
	@Override
	protected void evaluateBirth(ServerLevel serverLevel) {		
		// This pregnancy phase does not support birth yet
	}
	
	@Override
	protected boolean isWaterBroken() {
		return false;
 	}
	
	@Override
	protected void evaluateWaterBreaking(ServerLevel serverLevel) {
		// This pregnancy phase does not support water breaking yet
	}
	
	@Override
	protected void breakWater() {
		// This pregnancy phase does not support water breaking yet
	}

	@Override
	protected void evaluatePregnancySystem() {
		evaluatePregnancyTimer();
		
		if (canAdvanceNextPregnancyPhase() && !hasToGiveBirth()){
			advanceToNextPregnancyPhase();
		}	
	}

	@Override
	protected void evaluatePregnancyPains() {
		// This pregnancy phase does not support pregnancy pains yet	
	}

	@Override
	protected void evaluatePregnancySymptoms() {
		// This pregnancy phase does not support pregnancy symptoms yet			
	}

	@Override
	protected void evaluatePregnancyTimer() {
		if (this.pregnantEntity.hasEffect(MinepreggoModMobEffects.ETERNAL_PREGNANCY.get())) {
			return;
		}
		
		if (pregnancySystem.getPregnancyTimer() > MinepreggoModConfig.getTotalTicksByPregnancyDay()) {
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
	protected void evaluateMiscarriage(ServerLevel serverLevel) {
		// This pregnancy phase does not support miscarriage yet	
	}

	@Override
	public boolean isMiscarriageActive() {
		return false;
	}

	@Override
	public boolean hasPregnancyPain() {
		return false;
	}

	@Override
	public boolean canAdvanceNextPregnancyPhase() {
		return pregnancySystem.getDaysPassed() >= pregnancySystem.getDaysByCurrentStage();
	}

	@Override
	public boolean hasAllPregnancySymptoms() {
		return false;
	}

	@Override
	protected void advanceToNextPregnancyPhase() {		
		final var previousStage = pregnancySystem.getCurrentPregnancyStage();
		final var phases = PregnancyPhase.values();	
		final var next = phases[Math.min(previousStage.ordinal() + 1, phases.length - 1)];
		
		pregnantEntity.removeEffect(PlayerHelper.pregnancyEffects(pregnancySystem.getCurrentPregnancyStage()));
		pregnantEntity.addEffect(new MobEffectInstance(PlayerHelper.pregnancyEffects(next), -1, 0, false, false, true));
		
		pregnancySystem.setCurrentPregnancyStage(next);
		pregnancySystem.resetPregnancyTimer();
		pregnancySystem.resetDaysPassed();
		pregnancySystem.sync(pregnantEntity);
		
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
		
		MinepreggoMod.LOGGER.debug("Player {} advanced to next pregnancy phase: {}",
				pregnantEntity.getGameProfile().getName(), next);	
	}

	@Override
	protected boolean tryInitRandomPregnancyPain() {
		return false;
	}

	@Override
	protected boolean tryInitPregnancySymptom() {
		return false;
	}

	@Override
	protected void evaluatePregnancyNeeds() {
		// This pregnancy phase does not support pregnancy needs yet
	}

	@Override
	protected void startMiscarriage() {
		// This pregnancy phase does not miscarriage yet	
	}

	protected void removePregnancy() {		
		pregnantEntity.getActiveEffects().forEach(effect -> {
			var e = effect.getEffect();
			if (PregnancySystemHelper.isPregnancyEffect(e)) {
				pregnantEntity.removeEffect(e);
			}
		});
	}
}
