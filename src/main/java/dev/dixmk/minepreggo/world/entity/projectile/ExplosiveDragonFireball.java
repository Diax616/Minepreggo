package dev.dixmk.minepreggo.world.entity.projectile;

import java.util.List;

import dev.dixmk.minepreggo.init.MinepreggoModItems;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class ExplosiveDragonFireball extends AbstractHurtingProjectile implements ItemSupplier {
   public static final ItemStack PROJECTILE_ITEM = new ItemStack(MinepreggoModItems.EXPLOSIVE_DRAGON_FIREBALL.get());

   private int explosionPower = 2;
   
   public static final float SPLASH_RANGE = 3.0F;
   private static final float CLOUD_RADIUS = 2.0F;
   private static final int CLOUD_DURATION = 200;
   
   private static final float DIRECT_DAMAGE = 9.0F;

   public ExplosiveDragonFireball(EntityType<? extends ExplosiveDragonFireball> entityType, Level level) {
      super(entityType, level);
   }

   public ExplosiveDragonFireball(Level level, LivingEntity shooter, double deltaX, double deltaY, double deltaZ, int explosionPower) {
      super(EntityType.DRAGON_FIREBALL, shooter, deltaX, deltaY, deltaZ, level);
      this.explosionPower = explosionPower;
   }

   @Override
   protected void onHit(HitResult hitResult) {
      super.onHit(hitResult);  
      if (this.level().isClientSide) {
    	  return;
      }
   
      if (hitResult.getType() != HitResult.Type.ENTITY || !this.ownedBy(((EntityHitResult)hitResult).getEntity())) {
          
          boolean mobGriefing = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.level(), this.getOwner());
          this.level().explode(this, this.getX(), this.getY(), this.getZ(), this.explosionPower, mobGriefing, Level.ExplosionInteraction.MOB);
          
          List<LivingEntity> nearbyEntities = this.level().getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(SPLASH_RANGE, 2.0D, SPLASH_RANGE));
          
          AreaEffectCloud effectCloud = new AreaEffectCloud(this.level(), this.getX(), this.getY(), this.getZ());
          
          if (this.getOwner() instanceof LivingEntity owner) {
             effectCloud.setOwner(owner);
          }
          
          effectCloud.setParticle(ParticleTypes.DRAGON_BREATH);
          effectCloud.setRadius(CLOUD_RADIUS);
          effectCloud.setDuration(CLOUD_DURATION);
          effectCloud.setRadiusPerTick((7.0F - effectCloud.getRadius()) / effectCloud.getDuration());
          
          effectCloud.addEffect(new MobEffectInstance(MobEffects.HARM, 1, 1));
          effectCloud.addEffect(new MobEffectInstance(MobEffects.WITHER, 200, 0));
          
          if (!nearbyEntities.isEmpty()) {
             for(LivingEntity entity : nearbyEntities) {
                double distanceSq = this.distanceToSqr(entity);
                if (distanceSq < 16.0D) {
                   effectCloud.setPos(entity.getX(), entity.getY(), entity.getZ());
                   break;
                }
             }
          }
          
          this.level().levelEvent(2006, this.blockPosition(), this.isSilent() ? -1 : 1);
          
          this.level().addFreshEntity(effectCloud);
         
          this.discard();
      }
   }

   @Override
   protected void onHitEntity(EntityHitResult entityHitResult) {
      super.onHitEntity(entityHitResult);
      
      if (!this.level().isClientSide) {
         Entity hitEntity = entityHitResult.getEntity();
         Entity owner = this.getOwner();
         
         DamageSource damageSource = this.damageSources().mobProjectile(this, owner instanceof LivingEntity livingOwner ? livingOwner : null);
         hitEntity.hurt(damageSource, DIRECT_DAMAGE);
         
         if (owner instanceof LivingEntity livingOwner) {
            this.doEnchantDamageEffects(livingOwner, hitEntity);
         }
         
         hitEntity.setSecondsOnFire(5);
      }
   }

   @Override
   public boolean isPickable() {
      return false;
   }

   @Override
   public boolean hurt(DamageSource damageSource, float amount) {
      return false;
   }

   @Override
   protected ParticleOptions getTrailParticle() {
      return this.random.nextBoolean() ? ParticleTypes.DRAGON_BREATH : ParticleTypes.FLAME;
   }

   @Override
   protected boolean shouldBurn() {
      return false;
   }
   
   @Override
   public void addAdditionalSaveData(CompoundTag tag) {
      super.addAdditionalSaveData(tag);
      tag.putByte("ExplosionPower", (byte)this.explosionPower);
   }

   @Override
   public void readAdditionalSaveData(CompoundTag tag) {
      super.readAdditionalSaveData(tag);
      if (tag.contains("ExplosionPower", 99)) {
         this.explosionPower = tag.getByte("ExplosionPower");
      }
   }

   @Override
   public ItemStack getItem() {
	   return PROJECTILE_ITEM;
   }
}