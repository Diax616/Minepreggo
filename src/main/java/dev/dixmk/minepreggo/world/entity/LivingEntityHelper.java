package dev.dixmk.minepreggo.world.entity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import javax.annotation.Nonnegative;

import org.checkerframework.checker.nullness.qual.NonNull;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class LivingEntityHelper {

	private LivingEntityHelper() {}
	
	
	public static void copyMobEffects(LivingEntity from, LivingEntity to) {
		from.getActiveEffects().forEach(effect -> to.addEffect(new MobEffectInstance(effect)));
	}
	
	public static List<MobEffect> removeEffects(LivingEntity entity, Predicate<MobEffect> predicate) {
		List<MobEffect> effectsToRemove = new ArrayList<>();
    	for (MobEffectInstance effectInstance : entity.getActiveEffects()) {
            MobEffect effect = effectInstance.getEffect();
            if (predicate.test(effect)) {
                effectsToRemove.add(effect);
            }
        }
    	return effectsToRemove;
	}
	
	public static void playSoundNearTo(LivingEntity entity, SoundEvent sound) {
		playSoundNearTo(entity, sound, 0.7f, entity.getVoicePitch());
	}
	
	public static void playSoundNearTo(LivingEntity entity, SoundEvent soundEvent, float volume) {
		playSoundNearTo(entity, soundEvent, volume, entity.getVoicePitch());
	}
	
	public static void playSoundNearTo(LivingEntity entity, SoundEvent soundEvent, float volume, float pitch) {
	    if (entity.level() instanceof ServerLevel serverLevel && !serverLevel.isClientSide) {
	        serverLevel.playSound(null, entity.getX(), entity.getY(), entity.getZ(), 
	                             soundEvent, entity.getSoundSource(), volume, pitch);
	    }
	}
	
	public static void copyRotation(@NonNull LivingEntity source, @NonNull LivingEntity target) {		
		target.setYRot(source.getYRot());
		target.setYBodyRot(source.getYRot());
		target.setYHeadRot(source.getYRot());
		target.setXRot(source.getXRot());
	}
	
	public static void copyHealth(LivingEntity source, LivingEntity target) {
		target.setHealth(Math.min(target.getMaxHealth(), source.getHealth()));
	}
	
	public static void transferAttackTarget(@NonNull Mob source, @NonNull Mob target) {
		var entfound = source.level().getEntitiesOfClass(LivingEntity.class, new AABB(source.blockPosition()).inflate(16)).stream()
				.sorted(Comparator.comparingDouble(entcnd -> entcnd.distanceToSqr(source)))
				.toList();
			
		entfound.forEach(ent -> {
			if (ent instanceof Mob mob && mob.getTarget() != null && mob.getTarget().getUUID().equals(source.getUUID())) {
				mob.setTarget(target);
			}
			if (source.getTarget() != null && source.getTarget().getUUID().equals(ent.getUUID())) {
				target.setTarget(ent);
			}
		});
	}
	
	public static boolean hasValidTarget(Mob entity) {
	    LivingEntity target = entity.getTarget();
	    return target != null && target.isAlive() && entity.canAttack(target);
	}
	
	public static boolean isTargetStillValid(Mob entity) {
	    LivingEntity target = entity.getTarget();
	    return target != null && target.isAlive();
	}
	
	public static void transferSlots(@NonNull Mob source, @NonNull Mob target) {
		for (EquipmentSlot slot : EquipmentSlot.values()) {
			target.setItemSlot(slot, source.getItemBySlot(slot).copy());
			source.setItemSlot(slot, ItemStack.EMPTY);
		}
	}
	
	// TODO: Rework this method to evaluate only hostile mobs that can target the source entities, ITamablePreggoMob are hostile by default
    public static boolean areHostileMobsNearby(Level level, LivingEntity source1, LivingEntity source2, @Nonnegative double detectionRadius) {
        double radiusSquared = detectionRadius * detectionRadius;

        return level.getEntitiesOfClass(Mob.class,
        		source1.getBoundingBox().inflate(detectionRadius))
                .stream()
                .anyMatch(mob -> {
                    if (mob.isDeadOrDying()) return false;
                    if (mob.distanceToSqr(source1) > radiusSquared || mob.distanceToSqr(source2) > radiusSquared) return false;
                    
                    var target = mob.getTarget();
                    if (target == null) return false;
                    return target.getId() == source1.getId() || target.getId() == source2.getId();
                });
    }
    
    public static void randomTeleport(LivingEntity entity, SoundEvent soundEvent, double minDistance, double maxDistance) {	
    	var level = entity.level();
    	if (!entity.level().isClientSide) {
    		double d0 = entity.getX();
    		double d1 = entity.getY();
    		double d2 = entity.getZ();

    		for(int i = 0; i < 16; ++i) {
    			double randomDistanceX = minDistance + entity.getRandom().nextDouble() * (maxDistance - minDistance);
    			double randomDistanceZ = minDistance + entity.getRandom().nextDouble() * (maxDistance - minDistance);
    			
    			randomDistanceX *= (entity.getRandom().nextBoolean() ? 1 : -1);
    			randomDistanceZ *= (entity.getRandom().nextBoolean() ? 1 : -1);
    			
    			double d3 = entity.getX() + randomDistanceX;
    			double d4 = Mth.clamp(entity.getY() + (entity.getRandom().nextInt(16) - 8), level.getMinBuildHeight(), (level.getMinBuildHeight() + ((ServerLevel)level).getLogicalHeight() - 1));
    			double d5 = entity.getZ() + randomDistanceZ;
    			
    			if (entity.isPassenger()) {
    				entity.stopRiding();
    			}
    			Vec3 vec3 = entity.position();
    			level.gameEvent(GameEvent.TELEPORT, vec3, GameEvent.Context.of(entity));
    			if (entity.randomTeleport(d3, d4, d5, true)) {
    				level.playSound(null, d0, d1, d2, soundEvent, SoundSource.PLAYERS, 1.0F, 1.0F);
    				level.playSound(null, d3, d4, d5, soundEvent, SoundSource.PLAYERS, 1.0F, 1.0F);
    				break;
    			}
    		}			
    	}
    }

    public static boolean isEyeAboveEntity(LivingEntity source, LivingEntity target, float factor) {
        double targetMidpointY = target.getY() + (target.getBbHeight() * factor);
        return source.getEyeY() > targetMidpointY;
    }
}
