package dev.dixmk.minepreggo.world.entity;

import java.util.List;

import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.entity.IEntityAdditionalSpawnData;
import net.minecraftforge.network.NetworkHooks;

public class BellyPart extends Entity implements IEntityAdditionalSpawnData {
    private static final EntityDataAccessor<Integer> PARENT_ID = SynchedEntityData.defineId(BellyPart.class, EntityDataSerializers.INT);
    
    private float width = 0.1f;
    private float height = 0.1f;
    private float offsetX = 0.0f;
    private float offsetY = 0.0f;
    private float offsetZ = 0.0f;
    
    private LivingEntity parent;

    public BellyPart(EntityType<? extends BellyPart> type, Level level) {
        super(type, level);
        this.parent = null;
        this.noPhysics = false;
    }

    public BellyPart(LivingEntity parent, float width, float height, float offsetX, float offsetY, float offsetZ) {
        this(MinepreggoModEntities.BELLY_PART.get(), parent.level());
        this.parent = parent;
        this.getEntityData().set(PARENT_ID, parent.getId());
        this.width = width;
        this.height = height;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.offsetZ = offsetZ;
        this.refreshDimensions();
    }

    @Override
    protected void defineSynchedData() {
        this.getEntityData().define(PARENT_ID, -1);
    }
    
    protected void pushPart(LivingEntity currentParent) {
        List<BellyPart> nearbyBellyParts = this.level().getEntitiesOfClass(BellyPart.class, this.getBoundingBox().inflate(0.1), 
            other -> other != this && other.getParent() != null && other.getParent() != this.parent);
        
        for (BellyPart otherPart : nearbyBellyParts) {
            if (this.getBoundingBox().intersects(otherPart.getBoundingBox())) {
                LivingEntity otherParent = otherPart.getParent();
                if (otherParent != null) {
                    double dx = currentParent.getX() - otherParent.getX();
                    double dz = currentParent.getZ() - otherParent.getZ();
                    double distance = Math.sqrt(dx * dx + dz * dz);
                    
                    if (distance < 0.01) {
                        distance = 0.01;
                    }
                    
                    double pushStrength = 0.15;
                    double pushX = (dx / distance) * pushStrength;
                    double pushZ = (dz / distance) * pushStrength;
                    
                    currentParent.push(pushX, 0, pushZ);
                    otherParent.push(-pushX, 0, -pushZ);
                }
            }
        }
    }
     
    protected void pushEntiesOnTop() {
        List<Entity> entitiesOnTop = this.level().getEntities(this, this.getBoundingBox().move(0, 0.1, 0), 
            entity -> entity != this.parent && entity instanceof LivingEntity && 
                     entity.getY() >= this.getY() + (this.getBbHeight() * 0.5));
        
        for (Entity entity : entitiesOnTop) {
            if (entity instanceof LivingEntity living) {
                double dx = living.getX() - this.getX();
                double dz = living.getZ() - this.getZ();
                double distHorizontal = Math.sqrt(dx * dx + dz * dz);
                
                if (distHorizontal < 0.01) {
                    dx = (Math.random() - 0.5) * 0.4;
                    dz = (Math.random() - 0.5) * 0.4;
                } else {
                    dx = (dx / distHorizontal) * 0.25;
                    dz = (dz / distHorizontal) * 0.25;
                }
                
                living.push(dx, -0.2, dz);
                living.setOnGround(false);
                living.hurtMarked = true;
            }
        }
    }
    
    protected void pushCollidingEntities(LivingEntity currentParent) {
        List<LivingEntity> collidingEntities = this.level().getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(0.075), 
            entity -> !entity.getUUID().equals(currentParent.getUUID()));
        
        for (LivingEntity entity : collidingEntities) {
            double dx = entity.getX() - this.getX();
            double dz = entity.getZ() - this.getZ();
            double distance = Math.sqrt(dx * dx + dz * dz);     
  
            if (distance < 0.01) {
				distance = 0.01;
			}
            
            double pushX = (dx / distance) * 0.3;
            double pushZ = (dz / distance) * 0.3;
            
            entity.push(pushX, 0, pushZ);
            
            currentParent.push(-pushX * 0.5, 0, -pushZ * 0.5);   
        }
    }
    
    protected void pushParent(LivingEntity currentParent) {
        float yaw = (float) Math.toRadians(currentParent.getYRot());
        double rotX = -Math.sin(yaw) * this.offsetZ;
        double rotZ = Math.cos(yaw) * this.offsetZ;

        double targetX = currentParent.getX() + rotX + this.offsetX;
        double targetY = currentParent.getY() + this.offsetY;
        double targetZ = currentParent.getZ() + rotZ;
        
        Vec3 parentMotion = currentParent.getDeltaMovement();
        targetX += parentMotion.x;
        targetZ += parentMotion.z;
        
        Vec3 posBefore = this.position();
        Vec3 targetPos = new Vec3(targetX, targetY, targetZ);
        Vec3 movement = targetPos.subtract(posBefore).scale(0.9);
        
        this.move(MoverType.SELF, movement);
        
        this.setYRot(currentParent.getYRot());
        this.setXRot(currentParent.getXRot());

        Vec3 posAfter = this.position();
        Vec3 actualMovement = posAfter.subtract(posBefore);

        double deltaX = Math.abs(movement.x - actualMovement.x);
        double deltaY = Math.abs(movement.y - actualMovement.y);
        double deltaZ = Math.abs(movement.z - actualMovement.z);

        boolean collidedX = deltaX > 0.05 && Math.abs(movement.x) > 0.001;
        boolean collidedY = deltaY > 0.05 && Math.abs(movement.y) > 0.001;
        boolean collidedZ = deltaZ > 0.05 && Math.abs(movement.z) > 0.001;

        if (collidedX || collidedY || collidedZ) {
            parentMotion = currentParent.getDeltaMovement();
            
            if (collidedX || collidedZ) {
                double newMotionX = collidedX ? 0.0 : parentMotion.x;
                double newMotionZ = collidedZ ? 0.0 : parentMotion.z;
                
                currentParent.setDeltaMovement(newMotionX, parentMotion.y, newMotionZ);
                
                double pushX = 0;
                double pushZ = 0;
                
                if (collidedX) {
                    if (Math.abs(movement.x) > 0.001) {
                        pushX = -Math.signum(movement.x) * 0.15;
                    } else {
                        pushX = -deltaX * 0.3;
                    }
                }
                
                if (collidedZ) {
                    if (Math.abs(movement.z) > 0.001) {
                        pushZ = -Math.signum(movement.z) * 0.15;
                    } else {
                        pushZ = -deltaZ * 0.3;
                    }
                }

                currentParent.push(pushX, 0, pushZ);
            }
        }
    }
    
    @Override
    public void tick() {
        super.tick();
        LivingEntity currentParent = getParent();

        if (currentParent == null || !currentParent.isAlive()) {
            int parentId = this.getEntityData().get(PARENT_ID);
            if (parentId != -1) {
                if (this.level().getEntity(parentId) instanceof LivingEntity livingEntity) {
                    this.parent = livingEntity;
                    currentParent = livingEntity;
                } else {
                    this.discard();
                    return;
                }
            } else {
                this.discard();
                return;
            }
        }
        
        // TODO: It is probably that horses, pigs, or other rideable entities will be pushed incorrectly when the player is riding them while having a belly part.

        pushCollidingEntities(currentParent);
        
        pushPart(currentParent);
        
        pushEntiesOnTop();
            
        pushParent(currentParent);       
    }

    @Override
    public EntityDimensions getDimensions(Pose pose) {
        return EntityDimensions.scalable(this.width, this.height);
    }
    
    @Override
    public boolean isPushable() {
        return true;
    }
    
    @Override
    public boolean canBeCollidedWith() {
        return true;
    }

    @Override
    public boolean isPickable() {
        return true;
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        LivingEntity currentParent = getParent();
        if (currentParent != null && !this.isInvulnerableTo(source)) {
            return currentParent.hurt(source, amount);
        }
        return false;
    }

    @Override
    public void push(Entity entityIn) {
        LivingEntity currentParent = getParent();
        if (currentParent == null || entityIn.getUUID().equals(currentParent.getUUID())) return;

        super.push(entityIn);
        Vec3 pushVector = this.position().subtract(entityIn.position()).normalize().scale(0.1);
        currentParent.push(pushVector.x, pushVector.y, pushVector.z);
    }

    @Override
    public void push(double x, double y, double z) {
        super.push(x, y, z);
        LivingEntity currentParent = getParent();
        if (currentParent != null) {
            currentParent.push(x * 0.5, y * 0.5, z * 0.5);
        }
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {}

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {}

    @Override
    public boolean shouldBeSaved() {
        return false;
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public void writeSpawnData(FriendlyByteBuf buffer) {
        buffer.writeInt(this.getEntityData().get(PARENT_ID));
        buffer.writeFloat(this.width);
        buffer.writeFloat(this.height);
        buffer.writeFloat(this.offsetX);
        buffer.writeFloat(this.offsetY);
        buffer.writeFloat(this.offsetZ);
    }

    @Override
    public void readSpawnData(FriendlyByteBuf additionalData) {
        this.getEntityData().set(PARENT_ID, additionalData.readInt());
        this.width = additionalData.readFloat();
        this.height = additionalData.readFloat();
        this.offsetX = additionalData.readFloat();
        this.offsetY = additionalData.readFloat();
        this.offsetZ = additionalData.readFloat();
        if (this.level().getEntity(this.entityData.get(PARENT_ID)) instanceof LivingEntity livingEntity) {
            this.parent = livingEntity;
        }
        this.refreshDimensions();
    }

    public LivingEntity getParent() {
         if (this.parent == null || !this.parent.isAlive()) {
              int parentId = this.getEntityData().get(PARENT_ID);
              if (this.level().getEntity(parentId) instanceof LivingEntity livingEntity) {
                  this.parent = livingEntity;
              }
         }
         return this.parent;
    }
}