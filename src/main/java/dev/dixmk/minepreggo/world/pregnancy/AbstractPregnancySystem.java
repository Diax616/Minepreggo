package dev.dixmk.minepreggo.world.pregnancy;

import javax.annotation.Nonnull;

import dev.dixmk.minepreggo.init.MinepreggoModSounds;
import dev.dixmk.minepreggo.world.entity.LivingEntityHelper;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.horse.AbstractHorse;

public abstract class AbstractPregnancySystem<E extends LivingEntity> implements IPregnancySystem {

	protected E pregnantEntity;
	protected final RandomSource randomSource;
	
	private int stomachGrowlCoolDown = 0;
	protected float stomachGrowlProb = 0.01f;
	
	private int pregnancyPainCoolDown = 0;
	protected float pregnancyHurtProb = 0.1f;
	
	protected AbstractPregnancySystem(@Nonnull E pregnantEntity) {
		this.pregnantEntity = pregnantEntity;
		this.randomSource = pregnantEntity.getRandom();	
	}
	
	protected abstract void evaluatePregnancySystem();
	
	protected abstract void evaluatePregnancyPains();
	
	protected abstract void evaluatePregnancyTimer();
	
	protected abstract void evaluateMiscarriage(ServerLevel serverLevel);
	
	protected abstract void evaluatePregnancySymptoms();
	
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
	
	protected abstract void startMiscarriage();
	
	public static void spawnParticulesForWaterBreaking(ServerLevel serverLevel, LivingEntity target) {	
		spawnParticulesForWaterBreaking(serverLevel, target, target.getBbHeight() * 0.35);
	}
	
	public static void spawnParticulesForWaterBreaking(ServerLevel serverLevel, LivingEntity target, double extraYOffset) {	
		for (ServerPlayer player : serverLevel.getServer().getPlayerList().getPlayers()) {
		    if (player.distanceToSqr(target) <= 256.0) { // 16 blocks
				serverLevel.sendParticles(
						player,
						ParticleTypes.FALLING_DRIPSTONE_WATER,
						true,
						target.getX(), (target.getY() + extraYOffset), target.getZ(),
						1,
						0, 0, 0,
						0.02);
		    }
		}
	}
	
	public static void spawnParticulesForMiscarriage(ServerLevel serverLevel, LivingEntity target) {	
		spawnParticulesForMiscarriage(serverLevel, target, target.getBbHeight() * 0.35);
	}
	
	public static void spawnParticulesForMiscarriage(ServerLevel serverLevel, LivingEntity target, double extraYOffset) {	
		for (ServerPlayer player : serverLevel.getServer().getPlayerList().getPlayers()) {
		    if (player.distanceToSqr(target) <= 256.0) { // 16 blocks
				serverLevel.sendParticles(
						player,
						ParticleTypes.FALLING_DRIPSTONE_LAVA,
						true,
						target.getX(), (target.getY() + extraYOffset), target.getZ(),
						1,
						0, 0, 0,
						0.02);
		    }
		}
	}
	
	protected boolean tryPlayStomachGrowlsSound() {	
		if (stomachGrowlCoolDown < 20) {
			++stomachGrowlCoolDown;
			return false;
		}
		stomachGrowlCoolDown = 0;	
		if (randomSource.nextFloat() < stomachGrowlProb) {
			LivingEntityHelper.playSoundNearTo(pregnantEntity, MinepreggoModSounds.getRandomStomachGrowls(randomSource), 0.2f);	
			return true;
		}	
		return false;
	}
	
	protected boolean tryHurt() {	
		if (pregnantEntity.getHealth() > 1.5f) {
			PregnancySystemHelper.applyDamageByPregnancyPain(pregnantEntity, 1);
			return true;
		}
		return false;
	}
	
	protected boolean tryHurtByCooldown(int coolDownTicks) {	
		if (pregnantEntity.getHealth() < 1.5f) {
			return false;
		}
		
		if (pregnancyPainCoolDown < coolDownTicks) {
			++pregnancyPainCoolDown;
			return false;
		}
		pregnancyPainCoolDown = 0;	
		if (randomSource.nextFloat() < pregnancyHurtProb) {
			PregnancySystemHelper.applyDamageByPregnancyPain(pregnantEntity, 1);
			return true;
		}	
		return false;
	}
	
	protected boolean tryHurtByCooldown() {	
		return tryHurtByCooldown(60);
	}
	
	protected boolean isMovingRidingSaddledHorse() {
		return this.pregnantEntity.isPassenger()
		&& this.pregnantEntity.getVehicle() instanceof AbstractHorse horse
		&& horse.isSaddled()
		&& (pregnantEntity.xxa != 0 || pregnantEntity.zza != 0);
	}
}