package dev.dixmk.minepreggo.world.entity.preggo;

import javax.annotation.Nonnull;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;

public abstract class AbstractPregnancySystem<E extends LivingEntity> {

	protected final E pregnantEntity;
	protected final RandomSource randomSource;
	
	protected AbstractPregnancySystem(@Nonnull E pregnantEntity) {
		this.pregnantEntity = pregnantEntity;
		this.randomSource = pregnantEntity.getRandom();	
	}
	
	protected abstract void evaluatePregnancySystem();
	
	protected abstract void evaluatePregnancyEffects();
	
	protected abstract void evaluatePregnancyTimer();
	
	protected abstract void evaluateMiscarriage(ServerLevel serverLevel);
	
	public abstract void onServerTick();
	
	protected abstract void evaluatePregnancySymptoms();
	
	protected abstract void evaluatePregnancyPains();
	
	public abstract boolean isMiscarriageActive();
	
	public abstract boolean hasPregnancyPain();
	
	public abstract boolean canAdvanceNextPregnancyPhase();
	
	public abstract boolean hasPregnancySymptom();
	
	protected abstract void advanceToNextPregnancyPhase();
	
	protected abstract void initPostMiscarriage();
	
	protected abstract void initPostPartum();
	
	protected abstract boolean tryInitRandomPregnancyPain();
	
	protected abstract boolean tryInitPregnancySymptom();
	
	protected abstract boolean hasToGiveBirth();
	
	protected abstract boolean isInLabor();
	
	protected abstract void startLabor();
	
	protected abstract void evaluateBirth(ServerLevel serverLevel);
}

