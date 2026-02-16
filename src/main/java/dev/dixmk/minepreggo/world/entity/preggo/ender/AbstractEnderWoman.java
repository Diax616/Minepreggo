package dev.dixmk.minepreggo.world.entity.preggo.ender;

import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;
import javax.annotation.Nullable;

import org.checkerframework.checker.nullness.qual.NonNull;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.event.entity.living.EnderWomanAngerEvent;
import dev.dixmk.minepreggo.init.MinepreggoModItems;
import dev.dixmk.minepreggo.utils.MinepreggoHelper;
import dev.dixmk.minepreggo.utils.TagHelper;
import dev.dixmk.minepreggo.world.entity.LivingEntityHelper;
import dev.dixmk.minepreggo.world.entity.preggo.Creature;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.Species;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.monster.Endermite;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.MinecraftForge;

public abstract class AbstractEnderWoman extends PreggoMob implements NeutralMob {
	protected static final UUID SPEED_MODIFIER_ATTACKING_UUID = UUID.fromString("020E0DFB-87AE-4653-9556-831010E291A0");
	protected static final AttributeModifier SPEED_MODIFIER_ATTACKING = new AttributeModifier(SPEED_MODIFIER_ATTACKING_UUID, "Attacking speed boost", 0.15D, AttributeModifier.Operation.ADDITION);
    
	protected static final int DELAY_BETWEEN_CREEPY_STARE_SOUND = 400;
	protected static final int MIN_DEAGGRESSION_TIME = 600;
	private static final EntityDataAccessor<Optional<BlockState>> DATA_CARRY_STATE = SynchedEntityData.defineId(AbstractEnderWoman.class, EntityDataSerializers.OPTIONAL_BLOCK_STATE);
	private static final EntityDataAccessor<Boolean> DATA_CREEPY = SynchedEntityData.defineId(AbstractEnderWoman.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Boolean> DATA_STARED_AT = SynchedEntityData.defineId(AbstractEnderWoman.class, EntityDataSerializers.BOOLEAN);
	protected int lastStareSound = Integer.MIN_VALUE;
	protected int targetChangeTime;
	protected static final UniformInt PERSISTENT_ANGER_TIME = TimeUtil.rangeOfSeconds(20, 39);
	protected int remainingPersistentAngerTime;
	
    @Nullable
    private UUID persistentAngerTarget;

    protected AbstractEnderWoman(EntityType< ? extends TamableAnimal> p_32485_, Level p_32486_, Creature typeOfCreature) {
        super(p_32485_, p_32486_, Species.ENDER, typeOfCreature);
        this.setMaxUpStep(1.0F);
        this.setPathfindingMalus(BlockPathTypes.WATER, -1.0F);  
    }
			
	@Override
	public @NonNull Species getTypeOfSpecies() {
		return Species.ENDER;
	}
    
	@Override
	public boolean hasJigglePhysics() {
		return true;
	}
	
	@Override
	public boolean removeWhenFarAway(double p_27598_) {
		return !this.isTame() && !this.wasTamed();
	}
	
	@Override
	protected boolean shouldDespawnInPeaceful() {
		return !this.isTame() && !this.wasTamed();
	}
	
	@Override
	public boolean isFoodToTame(ItemStack stack) {
		return stack.is(MinepreggoModItems.REFINED_CHORUS_SHARDS.get()) || stack.is(MinepreggoModItems.ENDER_SLIME_JELLY.get());
	}
	
	@Override
	public boolean isFood(ItemStack stack) {
		return stack.is(TagHelper.ENDER_FOOD);
	}

    @Override
    public void setTarget(@Nullable LivingEntity p_32537_) {
        AttributeInstance attributeinstance = this.getAttribute(Attributes.MOVEMENT_SPEED);
        if (p_32537_ == null) {
            this.targetChangeTime = 0;
            this.entityData.set(DATA_CREEPY, false);
            this.entityData.set(DATA_STARED_AT, false);
            attributeinstance.removeModifier(SPEED_MODIFIER_ATTACKING);
        }
        else {
            this.targetChangeTime = this.tickCount;
            this.entityData.set(DATA_CREEPY, true);
            if (!attributeinstance.hasModifier(SPEED_MODIFIER_ATTACKING)) {
                attributeinstance.addTransientModifier(SPEED_MODIFIER_ATTACKING);
            }
        }

        super.setTarget(p_32537_); //Forge: Moved down to allow event handlers to write data manager values.
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_CARRY_STATE, Optional.empty());
        this.entityData.define(DATA_CREEPY, false);
        this.entityData.define(DATA_STARED_AT, false);
    }
    
    public void startPersistentAngerTimer() {
        this.setRemainingPersistentAngerTime(PERSISTENT_ANGER_TIME.sample(this.random));
    }

    public void setRemainingPersistentAngerTime(int p_32515_) {
        this.remainingPersistentAngerTime = p_32515_;
    }

    public int getRemainingPersistentAngerTime() {
        return this.remainingPersistentAngerTime;
    }

    public void setPersistentAngerTarget(@Nullable UUID p_32509_) {
        this.persistentAngerTarget = p_32509_;
    }

    @Nullable
    public UUID getPersistentAngerTarget() {
        return this.persistentAngerTarget;
    }

    public void playStareSound() {
        if (this.tickCount >= this.lastStareSound + 400) {
            this.lastStareSound = this.tickCount;
            if (!this.isSilent()) {
                this.level().playLocalSound(this.getX(), this.getEyeY(), this.getZ(), SoundEvents.ENDERMAN_STARE, this.getSoundSource(), 2.5F, 1.0F, false);
            }
        }

    }

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor< ? > p_32513_) {
        if (DATA_CREEPY.equals(p_32513_) && this.hasBeenStaredAt() && this.level().isClientSide) {
            this.playStareSound();
        }

        super.onSyncedDataUpdated(p_32513_);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag p_32520_) {
        super.addAdditionalSaveData(p_32520_);
        BlockState blockstate = this.getCarriedBlock();
        if (blockstate != null) {
            p_32520_.put("carriedBlockState", NbtUtils.writeBlockState(blockstate));
        }

        this.addPersistentAngerSaveData(p_32520_);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag p_32511_) {
        super.readAdditionalSaveData(p_32511_);
        BlockState blockstate = null;
        if (p_32511_.contains("carriedBlockState", 10)) {
            blockstate = NbtUtils.readBlockState(this.level().holderLookup(Registries.BLOCK), p_32511_.getCompound("carriedBlockState"));
            if (blockstate.isAir()) {
                blockstate = null;
            }
        }

        this.setCarriedBlock(blockstate);
        this.readPersistentAngerSaveData(this.level(), p_32511_);
    }

    boolean isLookingAtMe(Player p_32535_) {
        ItemStack itemstack = p_32535_.getInventory().armor.get(3);
        if (AbstractEnderWoman.shouldSuppressEnderManAnger(this, p_32535_, itemstack)) {
            return false;
        }
        else {
            Vec3 vec3 = p_32535_.getViewVector(1.0F).normalize();
            Vec3 vec31 = new Vec3(this.getX() - p_32535_.getX(), this.getEyeY() - p_32535_.getEyeY(), this.getZ() - p_32535_.getZ());
            double d0 = vec31.length();
            vec31 = vec31.normalize();
            double d1 = vec3.dot(vec31);
            return d1 > 1.0D - 0.025D / d0 ? p_32535_.hasLineOfSight(this) : false;
        }
    }

    @Override
    protected float getStandingEyeHeight(Pose p_32517_, EntityDimensions p_32518_) {
        return 2.55F;
    }

	@Override
	public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
		return null;
	}
    
    @Override
    public void aiStep() {
        if (this.level().isClientSide) {
            for (int i = 0; i < 2; ++i) {
                this.level().addParticle(ParticleTypes.PORTAL, this.getRandomX(0.5D), this.getRandomY() - 0.25D, this.getRandomZ(0.5D), (this.random.nextDouble() - 0.5D) * 2.0D, -this.random.nextDouble(), (this.random.nextDouble() - 0.5D) * 2.0D);
            }
        }

        this.jumping = false;
        if (!this.level().isClientSide) {
            this.updatePersistentAnger((ServerLevel)this.level(), true);
        }

        super.aiStep();
    }

    @Override
    public boolean isSensitiveToWater() {
    	if (this.isInWater()) {
    		return true;
    	}
    	else if (!this.getItemBySlot(EquipmentSlot.HEAD).isEmpty()) {
    		return false;
    	}
        return true;
    }

    protected boolean shouldRandomlyTeleport() {
    	return true;
    }
      
    @SuppressWarnings("deprecation")
	@Override
    protected void customServerAiStep() {
        if (this.level().isDay() && this.tickCount >= this.targetChangeTime + 600) {
            float f = this.getLightLevelDependentMagicValue();
            if (f > 0.5F && this.level().canSeeSky(this.blockPosition()) && this.random.nextFloat() * 30.0F < (f - 0.4F) * 2.0F) {
                this.setTarget((LivingEntity)null);
                
                if (shouldRandomlyTeleport()) {
                    this.teleport();
                }           
            }
        }

        super.customServerAiStep();
    }

    protected boolean teleport() {
        if (!this.level().isClientSide() && this.isAlive()) {
            double d0 = this.getX() + (this.random.nextDouble() - 0.5D) * 64.0D;
            double d1 = this.getY() + (this.random.nextInt(64) - 32);
            double d2 = this.getZ() + (this.random.nextDouble() - 0.5D) * 64.0D;
            return this.teleport(d0, d1, d2);
        }
        else {
            return false;
        }
    }

    boolean teleportTowards(Entity p_32501_) {
        Vec3 vec3 = new Vec3(this.getX() - p_32501_.getX(), this.getY(0.5D) - p_32501_.getEyeY(), this.getZ() - p_32501_.getZ());
        vec3 = vec3.normalize();
        double d0 = 16.0D;
        double d1 = this.getX() + (this.random.nextDouble() - 0.5D) * 8.0D - vec3.x * d0;
        double d2 = this.getY() + (double)(this.random.nextInt(16) - 8) - vec3.y * d0;
        double d3 = this.getZ() + (this.random.nextDouble() - 0.5D) * 8.0D - vec3.z * d0;
        return this.teleport(d1, d2, d3);
    }

    boolean teleportNear(Entity target) {
        Vec3 targetPos = target.position();
        
        double angle = this.random.nextDouble() * Math.PI * 2.0D;
        double distance = this.random.nextDouble() * 2.0D;
        
        double d1 = targetPos.x + Math.cos(angle) * distance;
        double d2 = targetPos.y;
        double d3 = targetPos.z + Math.sin(angle) * distance;
        
        return this.teleport(d1, d2, d3);
    }
    
    @SuppressWarnings("deprecation")
    protected boolean teleport(double p_32544_, double p_32545_, double p_32546_) {
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos(p_32544_, p_32545_, p_32546_);

        while (blockpos$mutableblockpos.getY() > this.level().getMinBuildHeight() && !this.level().getBlockState(blockpos$mutableblockpos).blocksMotion()) {
            blockpos$mutableblockpos.move(Direction.DOWN);
        }

        BlockState blockstate = this.level().getBlockState(blockpos$mutableblockpos);
		boolean flag = blockstate.blocksMotion();
        boolean flag1 = blockstate.getFluidState().is(FluidTags.WATER);
        if (flag && !flag1) {
            net.minecraftforge.event.entity.EntityTeleportEvent.EnderEntity event = net.minecraftforge.event.ForgeEventFactory.onEnderTeleport(this, p_32544_, p_32545_, p_32546_);
            if (event.isCanceled()) return false;
            Vec3 vec3 = this.position();
            boolean flag2 = this.randomTeleport(event.getTargetX(), event.getTargetY(), event.getTargetZ(), true);
            if (flag2) {
                this.level().gameEvent(GameEvent.TELEPORT, vec3, GameEvent.Context.of(this));
                if (!this.isSilent()) {
                    this.level().playSound((Player)null, this.xo, this.yo, this.zo, SoundEvents.ENDERMAN_TELEPORT, this.getSoundSource(), 1.0F, 1.0F);
                    this.playSound(SoundEvents.ENDERMAN_TELEPORT, 1.0F, 1.0F);
                }
            }

            return flag2;
        }
        else {
            return false;
        }
    }
    
    @Override
    protected SoundEvent getAmbientSound() {
        return this.isCreepy() ? SoundEvents.ENDERMAN_SCREAM : SoundEvents.ENDERMAN_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource p_32527_) {
        return SoundEvents.ENDERMAN_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENDERMAN_DEATH;
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource p_32497_, int p_32498_, boolean p_32499_) {
        super.dropCustomDeathLoot(p_32497_, p_32498_, p_32499_);
        BlockState blockstate = this.getCarriedBlock();
        if (blockstate != null) {
            ItemStack itemstack = new ItemStack(Items.DIAMOND_AXE);
            itemstack.enchant(Enchantments.SILK_TOUCH, 1);
            LootParams.Builder lootparams$builder = (new LootParams.Builder((ServerLevel)this.level())).withParameter(LootContextParams.ORIGIN, this.position()).withParameter(LootContextParams.TOOL, itemstack).withOptionalParameter(LootContextParams.THIS_ENTITY, this);

            for (ItemStack itemstack1 : blockstate.getDrops(lootparams$builder)) {
                this.spawnAtLocation(itemstack1);
            }
        }

    }

    public void setCarriedBlock(@Nullable BlockState p_32522_) {
        this.entityData.set(DATA_CARRY_STATE, Optional.ofNullable(p_32522_));
    }

    @Nullable
    public BlockState getCarriedBlock() {
        return this.entityData.get(DATA_CARRY_STATE).orElse(null);
    }

    public boolean isCarring() {
    	return this.getCarriedBlock() != null;
    }
    
    protected boolean cannotTeleportByDamage(DamageSource damageSource) {
    	return false;
	}
    
    @Override
    public boolean hurt(DamageSource p_32494_, float p_32495_) {
        if (this.isInvulnerableTo(p_32494_)) {
            return false;
        }
        else if (this.cannotTeleportByDamage(p_32494_)) {
        	return super.hurt(p_32494_, p_32495_);
        }
        else {
            boolean flag = p_32494_.getDirectEntity() instanceof ThrownPotion;
            if (!p_32494_.is(DamageTypeTags.IS_PROJECTILE) && !flag) {
                boolean flag2 = super.hurt(p_32494_, p_32495_);
                if (!this.level().isClientSide() && !(p_32494_.getEntity() instanceof LivingEntity) && this.random.nextInt(10) != 0) {
                    this.teleport();
                }

                return flag2;
            }
            else {
                boolean flag1 = flag && this.hurtWithCleanWater(p_32494_, (ThrownPotion)p_32494_.getDirectEntity(), p_32495_);

                for (int i = 0; i < 64; ++i) {
                    if (this.teleport()) {
                        return true;
                    }
                }

                return flag1;
            }
        }
    }

    private boolean hurtWithCleanWater(DamageSource p_186273_, ThrownPotion p_186274_, float p_186275_) {
        ItemStack itemstack = p_186274_.getItem();
        Potion potion = PotionUtils.getPotion(itemstack);
        List<MobEffectInstance> list = PotionUtils.getMobEffects(itemstack);
        boolean flag = potion == Potions.WATER && list.isEmpty();
        return flag ? super.hurt(p_186273_, p_186275_) : false;
    }

    public boolean isCreepy() {
        return this.entityData.get(DATA_CREEPY);
    }

    public boolean hasBeenStaredAt() {
        return this.entityData.get(DATA_STARED_AT);
    }

    public void setBeingStaredAt() {
        this.entityData.set(DATA_STARED_AT, true);
    }

    @Override
    public boolean requiresCustomPersistence() {
        return super.requiresCustomPersistence() || this.getCarriedBlock() != null;
    }

	@Override
	protected ResourceLocation getDefaultLootTable() {
	    return MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "entities/abstract_ender_girl_loot");
	}
    
    public static boolean isEnderMask(ItemStack imItemStack, Player player, AbstractEnderWoman abstractEnderGirl) {
        return imItemStack.getItem() == Blocks.CARVED_PUMPKIN.asItem();
    }
      
    public static boolean shouldSuppressEnderManAnger(AbstractEnderWoman abstractEnderGirl, Player player, ItemStack mask) {
        return isEnderMask(mask, player, abstractEnderGirl) || MinecraftForge.EVENT_BUS.post(new EnderWomanAngerEvent(abstractEnderGirl, player)); 
    }
    
  
    protected void addBehaviourGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new AbstractEnderWoman.EnderWomanFreezeWhenLookedAt(this));       
		this.goalSelector.addGoal(10, new AbstractEnderWoman.EnderWomanLeaveBlockGoal(this));
        this.goalSelector.addGoal(11, new AbstractEnderWoman.EnderWomanTakeBlockGoal(this));
        this.targetSelector.addGoal(1, new AbstractEnderWoman.EnderWomanLookForPlayerGoal(this, this::isAngryAt));
        this.targetSelector.addGoal(4, new ResetUniversalAngerTargetGoal<>(this, false));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Endermite.class, true, false));
    }
    
    protected static class EnderWomanFreezeWhenLookedAt extends Goal {
        private final AbstractEnderWoman abstractEnderGirl;
        @Nullable
            private LivingEntity target;

        public EnderWomanFreezeWhenLookedAt(AbstractEnderWoman p_32550_) {
            this.abstractEnderGirl = p_32550_;
            this.setFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE));
        }

        public boolean canUse() {
            this.target = this.abstractEnderGirl.getTarget();
            if (!(this.target instanceof Player)) {
                return false;
            }
            else {
                double d0 = this.target.distanceToSqr(this.abstractEnderGirl);
                return d0 > 256.0D ? false : this.abstractEnderGirl.isLookingAtMe((Player)this.target);
            }
        }

        public void start() {
            this.abstractEnderGirl.getNavigation().stop();
        }

        public void tick() {
            this.abstractEnderGirl.getLookControl().setLookAt(this.target.getX(), this.target.getEyeY(), this.target.getZ());
        }
    }
    
    protected static class EnderWomanLookForPlayerGoal extends NearestAttackableTargetGoal<Player> {
        protected final AbstractEnderWoman abstractEnderWoman;
        @Nullable protected Player pendingTarget;
        private int aggroTime;
        private int teleportTime;
        private final TargetingConditions startAggroTargetConditions;
        private final TargetingConditions continueAggroTargetConditions = TargetingConditions.forCombat().ignoreLineOfSight();
        private final Predicate<LivingEntity> isAngerInducing;

        public EnderWomanLookForPlayerGoal(AbstractEnderWoman p_32573_, @Nullable Predicate<LivingEntity> p_32574_) {
            super(p_32573_, Player.class, 10, false, false, p_32574_);
            this.abstractEnderWoman = p_32573_;
            this.isAngerInducing = (p_269940_) -> {
                return (p_32573_.isLookingAtMe((Player)p_269940_) || p_32573_.isAngryAt(p_269940_)) && !p_32573_.hasIndirectPassenger(p_269940_);
            };
            this.startAggroTargetConditions = TargetingConditions.forCombat().range(this.getFollowDistance()).selector(this.isAngerInducing);
        }

        @Override
        public boolean canUse() {
            this.pendingTarget = this.abstractEnderWoman.level().getNearestPlayer(this.startAggroTargetConditions, this.abstractEnderWoman);
            return this.pendingTarget != null;
        }

        @Override
        public void start() {
            this.aggroTime = this.adjustedTickDelay(5);
            this.teleportTime = 0;
            this.abstractEnderWoman.setBeingStaredAt();
        }

        @Override
        public void stop() {
            this.pendingTarget = null;
            super.stop();
        }

        @Override
        public boolean canContinueToUse() {
            if (this.pendingTarget != null) {
                if (!this.isAngerInducing.test(this.pendingTarget)) {
                    return false;
                }
                else {
                    this.abstractEnderWoman.lookAt(this.pendingTarget, 10.0F, 10.0F);
                    return true;
                }
            }
            else {
                if (this.target != null) {
                    if (this.abstractEnderWoman.hasIndirectPassenger(this.target)) {
                        return false;
                    }

                    if (this.continueAggroTargetConditions.test(this.abstractEnderWoman, this.target)) {
                        return true;
                    }
                }

                return super.canContinueToUse();
            }
        }

        @Override
        public void tick() {
            if (this.abstractEnderWoman.getTarget() == null) {
                super.setTarget((LivingEntity)null);
            }

            if (this.pendingTarget != null) {
                if (--this.aggroTime <= 0) {
                    this.target = this.pendingTarget;
                    this.pendingTarget = null;
                    super.start();
                }
            }
            else {
                if (this.target != null && !this.abstractEnderWoman.isPassenger()) {
                    if (this.abstractEnderWoman.isLookingAtMe((Player)this.target)) {
                        if (this.target.distanceToSqr(this.abstractEnderWoman) < 16.0D) {
                            this.abstractEnderWoman.teleport();
                        }

                        this.teleportTime = 0;
                    }
                    else if (this.target.distanceToSqr(this.abstractEnderWoman) > 256.0D && this.teleportTime++ >= this.adjustedTickDelay(30) && this.abstractEnderWoman.teleportTowards(this.target)) {
                        this.teleportTime = 0;
                    }
                }

                super.tick();
            }

        }
    }
    
    protected static class EnderWomanTeleportToTargetGoal extends Goal {	
        private final AbstractEnderWoman enderWoman;
        private final float maxDistance;
        private final float minDistance;
        private int teleportTime;      
        private LivingEntity target;

        public EnderWomanTeleportToTargetGoal(AbstractEnderWoman enderWoman, float maxDistance, float minDistance) throws IllegalArgumentException {
            this.enderWoman = enderWoman;
            if (minDistance > maxDistance) {
				throw new IllegalArgumentException("Minimum distance must be less than maximum distance");          
            }
            this.maxDistance = maxDistance;
            this.minDistance = minDistance;
        }

        @Override
        public boolean canUse() {
            this.target = this.enderWoman.getTarget();
            if (this.target == null || !this.target.isAlive()) {
                return false;
            }              
            double distance = this.enderWoman.distanceToSqr(this.target);            
            return distance > this.minDistance && distance < this.maxDistance;
        }

        @Override
        public void start() {
            this.teleportTime = 0;
        }
        
        @Override
        public void tick() {
            if (this.target != null && !this.enderWoman.isPassenger()) {                    	
            	if (this.target.distanceToSqr(this.enderWoman) > this.minDistance
                		&& this.teleportTime++ >= this.adjustedTickDelay(10)
                		&& this.enderWoman.teleportNear(this.target)) {
                    this.teleportTime = 0;
                }      
                super.tick();
            }
        }
    }
    
    protected static class EnderWomanLeaveBlockGoal extends Goal {
        protected final AbstractEnderWoman abstractEnderWoman;

        public EnderWomanLeaveBlockGoal(AbstractEnderWoman p_32556_) {
            this.abstractEnderWoman = p_32556_;
        }

        @Override
        public boolean canUse() {
            if (this.abstractEnderWoman.getCarriedBlock() == null) {
                return false;
            }
            else if (!net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.abstractEnderWoman.level(), this.abstractEnderWoman)) {
                return false;
            }
            else {
                return this.abstractEnderWoman.getRandom().nextInt(reducedTickDelay(2000)) == 0;
            }
        }

        @Override
        public void tick() {
            RandomSource randomsource = this.abstractEnderWoman.getRandom();
            Level level = this.abstractEnderWoman.level();
            int i = Mth.floor(this.abstractEnderWoman.getX() - 1.0D + randomsource.nextDouble() * 2.0D);
            int j = Mth.floor(this.abstractEnderWoman.getY() + randomsource.nextDouble() * 2.0D);
            int k = Mth.floor(this.abstractEnderWoman.getZ() - 1.0D + randomsource.nextDouble() * 2.0D);
            BlockPos blockpos = new BlockPos(i, j, k);
            BlockState blockstate = level.getBlockState(blockpos);
            BlockPos blockpos1 = blockpos.below();
            BlockState blockstate1 = level.getBlockState(blockpos1);
            BlockState blockstate2 = this.abstractEnderWoman.getCarriedBlock();
            if (blockstate2 != null) {
                blockstate2 = Block.updateFromNeighbourShapes(blockstate2, this.abstractEnderWoman.level(), blockpos);
                if (this.canPlaceBlock(level, blockpos, blockstate2, blockstate, blockstate1, blockpos1) && !net.minecraftforge.event.ForgeEventFactory.onBlockPlace(abstractEnderWoman, net.minecraftforge.common.util.BlockSnapshot.create(level.dimension(), level, blockpos1), net.minecraft.core.Direction.UP)) {
                    level.setBlock(blockpos, blockstate2, 3);
                    level.gameEvent(GameEvent.BLOCK_PLACE, blockpos, GameEvent.Context.of(this.abstractEnderWoman, blockstate2));
                    this.abstractEnderWoman.setCarriedBlock((BlockState)null);
                }

            }
        }

        private boolean canPlaceBlock(Level p_32559_, BlockPos p_32560_, BlockState p_32561_, BlockState p_32562_, BlockState p_32563_, BlockPos p_32564_) {
            return !p_32559_.isClientSide() && p_32562_.isAir() && !p_32563_.isAir() && !p_32563_.is(Blocks.BEDROCK) && !p_32563_.is(net.minecraftforge.common.Tags.Blocks.ENDERMAN_PLACE_ON_BLACKLIST) && p_32563_.isCollisionShapeFullBlock(p_32559_, p_32564_) && p_32561_.canSurvive(p_32559_, p_32560_) && p_32559_.getEntities(this.abstractEnderWoman, AABB.unitCubeFromLowerCorner(Vec3.atLowerCornerOf(p_32560_))).isEmpty();
        }
    }

    protected static class EnderWomanTakeBlockGoal extends Goal {
    	protected final AbstractEnderWoman abstractEnderWoman;

        public EnderWomanTakeBlockGoal(AbstractEnderWoman p_32585_) {
            this.abstractEnderWoman = p_32585_;
        }
        
        @Override
        public boolean canUse() {
            if (this.abstractEnderWoman.getCarriedBlock() != null) {
                return false;
            }
            else if (!net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.abstractEnderWoman.level(), this.abstractEnderWoman)) {
                return false;
            }
            else {
                return this.abstractEnderWoman.getRandom().nextInt(reducedTickDelay(20)) == 0;
            }
        }

        @Override
        public void tick() {
            RandomSource randomsource = this.abstractEnderWoman.getRandom();
            Level level = this.abstractEnderWoman.level();
            int i = Mth.floor(this.abstractEnderWoman.getX() - 2.0D + randomsource.nextDouble() * 4.0D);
            int j = Mth.floor(this.abstractEnderWoman.getY() + randomsource.nextDouble() * 3.0D);
            int k = Mth.floor(this.abstractEnderWoman.getZ() - 2.0D + randomsource.nextDouble() * 4.0D);
            BlockPos blockpos = new BlockPos(i, j, k);
            BlockState blockstate = level.getBlockState(blockpos);
            Vec3 vec3 = new Vec3((double)this.abstractEnderWoman.getBlockX() + 0.5D, (double)j + 0.5D, (double)this.abstractEnderWoman.getBlockZ() + 0.5D);
            Vec3 vec31 = new Vec3((double)i + 0.5D, (double)j + 0.5D, (double)k + 0.5D);
            BlockHitResult blockhitresult = level.clip(new ClipContext(vec3, vec31, ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, this.abstractEnderWoman));
            boolean flag = !level.isClientSide() && blockhitresult.getBlockPos().equals(blockpos);
            if (blockstate.is(BlockTags.ENDERMAN_HOLDABLE) && flag) {
                level.removeBlock(blockpos, false);
                level.gameEvent(GameEvent.BLOCK_DESTROY, blockpos, GameEvent.Context.of(this.abstractEnderWoman, blockstate));
                this.abstractEnderWoman.setCarriedBlock(blockstate.getBlock().defaultBlockState());
            }

        }
    }
    
    protected static class EnderWomanTeleportToOwnerGoal extends Goal {
        private final AbstractEnderWoman enderWoman;
        private final float maxDistance;
        private final float minDistance;
        private int teleportTime;      
        private LivingEntity target;
        
        public EnderWomanTeleportToOwnerGoal(AbstractEnderWoman enderWoman, float maxDistance, float minDistance) throws IllegalArgumentException {
            this.enderWoman = enderWoman;
            if (minDistance > maxDistance) {
				throw new IllegalArgumentException("Minimum distance must be less than maximum distance");          
            }
            this.maxDistance = maxDistance;
            this.minDistance = minDistance;
        }
        
        @Override
        public boolean canUse() {
            this.target = this.enderWoman.getOwner();
            if (this.target == null || !this.target.isAlive()) {
                return false;
            }              
            
            if (this.enderWoman.isAggressive() || 
            		LivingEntityHelper.hasValidTarget(this.enderWoman) ||
            		LivingEntityHelper.isTargetStillValid(this.enderWoman)) {
				return false;
			}
            
            double distance = this.enderWoman.distanceToSqr(this.target);            
            return distance > this.minDistance && distance < this.maxDistance;
        }

        @Override
        public void start() {
            this.teleportTime = 0;
        }
        
        @Override
        public void tick() {
            if (this.target != null && !this.enderWoman.isPassenger()) {                    	
            	if (this.target.distanceToSqr(this.enderWoman) > this.minDistance
                		&& this.teleportTime++ >= this.adjustedTickDelay(10)
                		&& this.enderWoman.teleportNear(this.target)) {
                    this.teleportTime = 0;
                }      
                super.tick();
            }
        }
    }

    public static boolean forceDropCarriedBlock(AbstractEnderWoman enderWoman) {
        BlockState carriedBlock = enderWoman.getCarriedBlock();
        if (carriedBlock == null) {
            return false;
        }
        
        Level level = enderWoman.level();
        if (!net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(level, enderWoman)) {
            return false;
        }
        
        RandomSource random = enderWoman.getRandom();
        int i = Mth.floor(enderWoman.getX() - 1.0D + random.nextDouble() * 2.0D);
        int j = Mth.floor(enderWoman.getY() + random.nextDouble() * 2.0D);
        int k = Mth.floor(enderWoman.getZ() - 1.0D + random.nextDouble() * 2.0D);
        BlockPos placePos = new BlockPos(i, j, k);
        
        BlockState blockstate = level.getBlockState(placePos);
        BlockPos blockpos1 = placePos.below();
        BlockState blockstate1 = level.getBlockState(blockpos1);
        BlockState blockstate2 = Block.updateFromNeighbourShapes(carriedBlock, level, placePos);
        
        if (canPlaceBlockStatic(level, placePos, blockstate2, blockstate, blockstate1, blockpos1, enderWoman) && !net.minecraftforge.event.ForgeEventFactory.onBlockPlace(enderWoman, 
                    net.minecraftforge.common.util.BlockSnapshot.create(level.dimension(), level, blockpos1), 
                    net.minecraft.core.Direction.UP)) {
            level.setBlock(placePos, blockstate2, 3);
            level.gameEvent(GameEvent.BLOCK_PLACE, placePos, GameEvent.Context.of(enderWoman, blockstate2));
            enderWoman.setCarriedBlock(null);
            return true;
        }
        
        
        return false;
    }
    
    private static boolean canPlaceBlockStatic(Level level, BlockPos placePos, BlockState blockToPlace, 
            BlockState currentState, BlockState belowState, BlockPos belowPos, AbstractEnderWoman enderWoman) {
        return !level.isClientSide() 
            && currentState.isAir() 
            && !belowState.isAir() 
            && !belowState.is(Blocks.BEDROCK) 
            && !belowState.is(net.minecraftforge.common.Tags.Blocks.ENDERMAN_PLACE_ON_BLACKLIST) 
            && belowState.isCollisionShapeFullBlock(level, belowPos) 
            && blockToPlace.canSurvive(level, placePos) 
            && level.getEntities(enderWoman, AABB.unitCubeFromLowerCorner(Vec3.atLowerCornerOf(placePos))).isEmpty();
    }
}
