package dev.dixmk.minepreggo.world.entity.preggo;

import javax.annotation.Nonnull;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
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
	
	protected abstract void evaluatePregnancyPains();
	
	protected abstract void evaluatePregnancySymptoms();
	
	protected abstract void evaluatePregnancyTimer();
	
	protected abstract void evaluateMiscarriage(ServerLevel serverLevel);
	
	public abstract void onServerTick();
	
	public abstract boolean isMiscarriageActive();
	
	public abstract boolean hasPregnancyPain();
	
	public abstract boolean canAdvanceNextPregnancyPhase();
	
	public abstract boolean hasAllPregnancySymptoms();
	
	protected abstract void advanceToNextPregnancyPhase();
	
	protected abstract void initPostMiscarriage();
	
	protected abstract void initPostPartum();
	
	protected abstract boolean tryInitRandomPregnancyPain();
	
	protected abstract boolean tryInitPregnancySymptom();
	
	protected abstract boolean hasToGiveBirth();
	
	protected abstract boolean isInLabor();
	
	protected abstract void startLabor();
	
	protected abstract boolean isWaterBroken();
	
	protected abstract void breakWater();
	
	protected abstract void evaluateWaterBreaking(ServerLevel serverLevel);
	
	protected abstract void evaluateBirth(ServerLevel serverLevel);
	
	protected abstract void evaluatePregnancyNeeds();
	
	
	public static void spawnParticulesForWaterBreaking(ServerLevel serverLevel, LivingEntity target) {	
		for (ServerPlayer player : serverLevel.getServer().getPlayerList().getPlayers()) {
		    if (player.distanceToSqr(target) <= 256.0) { // 16 blocks
				serverLevel.sendParticles(
						player,
						ParticleTypes.FALLING_DRIPSTONE_WATER,
						true,
						target.getX(), (target.getY() + target.getBbHeight() * 0.35), target.getZ(),
						1,
						0, 0, 0,
						0.02);
		    }
		}
	}
}

