package dev.dixmk.minepreggo.world.entity.player;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;

public class PlayerPregnancySystemP0 extends AbstractPlayerPregnancySystem {
	public PlayerPregnancySystemP0(ServerPlayer player) {
		super(player);
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
	protected void evaluatePregnancyPains() {
		// This pregnancy phase does not support pregnancy pains yet	
	}
	
	@Override
	protected void evaluatePregnancySymptoms() {
		// This pregnancy phase does not support pregnancy symptoms yet			
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
	public boolean hasAllPregnancySymptoms() {
		return false;
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
		// This pregnancy phase does not support miscarriage yet	
	}

	@Override
	protected void evaluatePregnancyHealing() {
		// This pregnancy phase does not support pregnancy healing yet	
	}
	
	protected void evaluateRandomWeakness() {
		// This pregnancy phase does not support random weakness yet
	}
}
