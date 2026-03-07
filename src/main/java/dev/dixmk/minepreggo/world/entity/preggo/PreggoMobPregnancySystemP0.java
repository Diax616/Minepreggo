package dev.dixmk.minepreggo.world.entity.preggo;

import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobSystem.Result;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public abstract class PreggoMobPregnancySystemP0
	<E extends PreggoMob & ITamablePregnantPreggoMob> extends AbstractPreggoMobPregnancySystem<E> {
	
	protected PreggoMobPregnancySystemP0(E pregnantEntity) {
		super(pregnantEntity);
	}

	protected void evaluatePregnancyPains() {	
		// This pregnancy phase does not support pregnancy pains yet
	}
	
	protected void evaluatePregnancySymptoms() {
		// This pregnancy phase does not support pregnancy symptoms yet			
	}
	
	protected void evaluatePregnancyNeeds() {
		// This pregnancy phase does not support pregnancy needs yet
	}

	public boolean isMiscarriageActive() {
	    return false;
	}

	
	public boolean hasPregnancyPain() {
	    return false;
	}
	
	public boolean hasAllPregnancySymptoms() {
	    return false;
	}
	
	@Override
	protected void evaluateMiscarriage(ServerLevel serverLevel) {
		// This pregnancy phase does not support water breaking yet
		
	}
	
	@Override
	protected void initPostMiscarriage() {
		// This pregnancy phase does not support miscarriage yet		
	}

	@Override
	protected boolean tryInitRandomPregnancyPain() {
		return false;
	}

	@Override
	protected boolean tryInitPregnancySymptom() {
		return false;
	}

	// ON HURT	
	public void evaluateOnSuccessfulHurt(DamageSource damagesource) {	
		
	}
	
	// RIGHT CLICK	
	public InteractionResult onRightClick(Player source) {	
		if (!this.isRightClickValid(source)) {
			return InteractionResult.FAIL;
		}				
		var level = pregnantEntity.level();

		// Belly rubs has priority over other right click actions
		if (PregnancySystemHelper.canTouchBelly(source, pregnantEntity)) {		
			if (level instanceof ServerLevel serverLevel && !serverLevel.isClientSide) {		
				PreggoMobSystem.spawnParticles(pregnantEntity, evaluateBellyRubs(serverLevel, source));
			}	
			
			return InteractionResult.sidedSuccess(level.isClientSide);
		}
		
		return InteractionResult.PASS;
	}

	protected Result evaluateBellyRubs(Level level, Player source) {
		// In this pregnancy phase, the belly is not large enough to do some action
		return Result.FAIL;
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
	protected void evaluateBirth(ServerLevel serverLevel) {	
		// This pregnancy phase does not support birth yet
	}
	
	@Override
	protected void startLabor() {
		// This pregnancy phase does not support birth yet
	}
	
	@Override
	protected void initPostPartum() {
		// This pregnancy phase does not support birth yet
	}
	
	@Override
	protected void startMiscarriage() {
		// This pregnancy phase does not support birth yet	
	}
	
	@Override
	protected void evaluatePregnancyHealing() {
		// This pregnancy phase does not support pregnancy healing yet	
	}
}
