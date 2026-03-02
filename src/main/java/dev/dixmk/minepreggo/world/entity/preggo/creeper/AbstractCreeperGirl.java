package dev.dixmk.minepreggo.world.entity.preggo.creeper;

import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;

import java.util.Collection;
import java.util.EnumSet;

import javax.annotation.Nullable;

import dev.dixmk.minepreggo.init.MinepreggoModItems;
import dev.dixmk.minepreggo.utils.MinepreggoHelper;
import dev.dixmk.minepreggo.utils.TagHelper;
import dev.dixmk.minepreggo.world.entity.preggo.Creature;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.Species;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.PowerableMob;

public abstract class AbstractCreeperGirl extends PreggoMob implements PowerableMob {
	public static final ExplosionData DEFAULT_EXPLOSION_DATA = new ExplosionData(3, 1, 30);
	
	private static final EntityDataAccessor<Integer> DATA_SWELL_DIR = SynchedEntityData.defineId(AbstractCreeperGirl.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Boolean> DATA_IS_POWERED = SynchedEntityData.defineId(AbstractCreeperGirl.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Boolean> DATA_IS_IGNITED = SynchedEntityData.defineId(AbstractCreeperGirl.class, EntityDataSerializers.BOOLEAN);
	private int oldSwell;
	private int swell;
	private int droppedSkulls;
	private int maxSwell = DEFAULT_EXPLOSION_DATA.maxSwell();
	private int explosionRadius = DEFAULT_EXPLOSION_DATA.explosionRadius();
	private int explosionItensity = DEFAULT_EXPLOSION_DATA.explosionItensity();
	protected double maxDistance = 9D;
	private CombatMode combatMode = CombatMode.EXPLODE;
	
	protected AbstractCreeperGirl(EntityType<? extends PreggoMob> entity, Level level, Creature typeOfCreature) {
		super(entity, level, Species.CREEPER, typeOfCreature);   
		this.setRandomCombatMode();
	}
		
	protected void setExplosionData(ExplosionData explosionData) {
		this.explosionRadius = explosionData.explosionRadius();
		this.explosionItensity = explosionData.explosionItensity();
		this.maxSwell = explosionData.maxSwell();
	}
	
	public void setCombatMode(CombatMode value) {
		this.combatMode = value;
		if (value == CombatMode.EXPLODE)
			this.maxDistance = 3D;
	}
	
	public CombatMode getCombatMode() {
		return combatMode;
	}
	
	protected void setRandomCombatMode() {		
		if (this.level().isClientSide()) {
			return;
		}	
		final var p = this.getRandom().nextFloat();		
	    if (p < 0.4F) {    	
	    	this.setCombatMode(CombatMode.FIGHT_AND_EXPLODE);
	    }
	    else if (p < 0.9F) {
	    	this.setCombatMode(CombatMode.EXPLODE);
	    }
	    else {
	    	this.setCombatMode(CombatMode.DONT_EXPLODE);
	    }
	}
	
	@Override
	public boolean hasJigglePhysics() {
		return true;
	}
	
	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return !this.isTame();
	}
	
	@Override
	protected boolean shouldDespawnInPeaceful() {
		return !this.isTame();
	}
	
	@Override
	public SoundSource getSoundSource() {
		return SoundSource.HOSTILE;
	}
	
	@Override
	public boolean isFood(ItemStack stack) {
		return stack.is(TagHelper.CREEPER_FOOD);
	}
	
	@Override
	public boolean isFoodToTame(ItemStack stack) {
		return stack.is(MinepreggoModItems.ACTIVATED_GUNPOWDER.get());
	}
	
	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(DATA_SWELL_DIR, -1);
		this.entityData.define(DATA_IS_POWERED, false);
		this.entityData.define(DATA_IS_IGNITED, false);
	}
	
	@Override
	public void addAdditionalSaveData(CompoundTag compoundTag) {
		super.addAdditionalSaveData(compoundTag);
		compoundTag.putInt("DataSwell", this.entityData.get(DATA_SWELL_DIR));
		compoundTag.putBoolean("DataIsPowered", this.entityData.get(DATA_IS_POWERED));
		compoundTag.putBoolean("DataIsIgnited", this.entityData.get(DATA_IS_IGNITED));
		compoundTag.putString(CombatMode.NBT_KEY, this.combatMode.name());
	}
	
	@Override
	public void readAdditionalSaveData(CompoundTag compoundTag) {
		super.readAdditionalSaveData(compoundTag);	
		this.entityData.set(DATA_SWELL_DIR, compoundTag.getInt("DataSwell"));
		this.entityData.set(DATA_IS_POWERED, compoundTag.getBoolean("DataIsPowered"));
		this.entityData.set(DATA_IS_IGNITED, compoundTag.getBoolean("DataIsIgnited"));	
		this.setCombatMode(CombatMode.valueOf(compoundTag.getString(CombatMode.NBT_KEY)));
	}
	
	@Override
	protected boolean canReplaceCurrentItem(ItemStack candidate, ItemStack existing) {
		if (this.getTypeOfCreature() != Creature.HUMANOID) return false;
			
		if (!canReplaceArmorBasedInPregnancyPhase(candidate)) return false;
	
		return super.canReplaceCurrentItem(candidate, existing);
	}
	
	protected abstract boolean canReplaceArmorBasedInPregnancyPhase(ItemStack armor);
	
	public boolean canExplode() {
		return true;
	}
	
	public boolean isPowered() {
		return this.entityData.get(DATA_IS_POWERED);
	}

	protected void setPower(boolean power) {
		this.entityData.set(DATA_IS_POWERED, power);
	}
	
	public boolean isIgnited() {
		return this.entityData.get(DATA_IS_IGNITED);
	}
	
	public void ignite() {
		this.entityData.set(DATA_IS_IGNITED, true);
	}
	
	public int getSwellDir() {
		return this.entityData.get(DATA_SWELL_DIR);
	}
	
	public float getSwelling(float partialTick) {
		return Mth.lerp(partialTick, this.oldSwell, this.swell) / (this.maxSwell - 2);
	}
	
	public void setSwellDir(int value) {
		this.entityData.set(DATA_SWELL_DIR, value);
	}
	
	public boolean canDropMobsSkull() {
		return this.isPowered() && this.droppedSkulls < 1;
	}

	public void increaseDroppedSkulls() {
		++this.droppedSkulls;
	}
	
	@Override
	public int getMaxFallDistance() {
		return this.getTarget() == null ? 3 : 3 + (int)(this.getHealth() - 1.0F);
	}
	
	private void explodeCreeper() {
		if (!this.level().isClientSide) {
			float f = this.isPowered() ? explosionItensity * 2F : explosionItensity;
			this.dead = true;
			this.level().explode(this, this.getX(), this.getY(), this.getZ(), this.explosionRadius * f, Level.ExplosionInteraction.MOB);
			this.discard();
			this.spawnLingeringCloud();
		}
	}
	
	private void spawnLingeringCloud() {
		Collection<MobEffectInstance> collection = this.getActiveEffects();
		if (!collection.isEmpty()) {
			AreaEffectCloud areaeffectcloud = new AreaEffectCloud(this.level(), this.getX(), this.getY(), this.getZ());
			areaeffectcloud.setRadius(2.5F);
			areaeffectcloud.setRadiusOnUse(-0.5F);
			areaeffectcloud.setWaitTime(10);
			areaeffectcloud.setDuration(areaeffectcloud.getDuration() / 2);
			areaeffectcloud.setRadiusPerTick(-areaeffectcloud.getRadius() / (float)areaeffectcloud.getDuration());

			for(MobEffectInstance mobeffectinstance : collection) {
				areaeffectcloud.addEffect(new MobEffectInstance(mobeffectinstance));
			}

			this.level().addFreshEntity(areaeffectcloud);
		}

	}
		
	@Override
	public MobType getMobType() {
		return MobType.UNDEFINED;
	}

	@Override
	public void thunderHit(ServerLevel level, LightningBolt lightning) {
		super.thunderHit(level, lightning);
		this.setPower(true);
	}

	@Override
	public boolean causeFallDamage(float fallDistance, float multiplier, DamageSource damageSource) {
		boolean flag = super.causeFallDamage(fallDistance, multiplier, damageSource);
		this.swell += (int)(fallDistance * 1.5F);
		if (this.swell > this.maxSwell - 5) {
			this.swell = this.maxSwell - 5;
		}

		return flag;
	}
	
	@Override
	public void tick() {	
		if (this.isAlive()) {
			this.oldSwell = this.swell;
			if (this.isIgnited()) {
				this.setSwellDir(1);
			}

			int i = this.getSwellDir();
			if (i > 0 && this.swell == 0) {
				this.playSound(SoundEvents.CREEPER_PRIMED, 1.0F, 0.5F);
				this.gameEvent(GameEvent.PRIME_FUSE);
			}

			this.swell += i;
			if (this.swell < 0) {
				this.swell = 0;
			}

			if (this.swell >= this.maxSwell) {
				this.swell = this.maxSwell;
				this.explodeCreeper();
			}
		}	
		
		super.tick();
	}

	@Override
	protected ResourceLocation getDefaultLootTable() {
	    return MinepreggoHelper.fromThisNamespaceAndPath("entities/abstract_creeper_girl_loot");
	}

	@Override
	public InteractionResult mobInteract(Player sourceentity, InteractionHand hand) {		
		var retval = super.mobInteract(sourceentity, hand);
		
		if (retval == InteractionResult.SUCCESS || retval == InteractionResult.CONSUME) {
			return retval;
		}	
		
		ItemStack itemstack = sourceentity.getItemInHand(hand);
		if (itemstack.is(ItemTags.CREEPER_IGNITERS)) {
			SoundEvent soundevent = itemstack.is(Items.FIRE_CHARGE) ? SoundEvents.FIRECHARGE_USE : SoundEvents.FLINTANDSTEEL_USE;
			this.level().playSound(sourceentity, this.getX(), this.getY(), this.getZ(), soundevent, this.getSoundSource(), 1.0F, this.random.nextFloat() * 0.4F + 0.8F);
			if (!this.level().isClientSide) {
				this.ignite();
				if (!itemstack.isDamageableItem()) {
					itemstack.shrink(1);
				} else {
					itemstack.hurtAndBreak(1, sourceentity, entity -> entity.broadcastBreakEvent(hand));
				}
			}
			retval = InteractionResult.sidedSuccess(this.level().isClientSide());
		}	
		
		return retval;
	}
	
	
	@Override
	public boolean canHoldItem(ItemStack stack) {
		return !this.isPassenger() && super.canHoldItem(stack);
		
	}

	@Override
	public boolean wantsToPickUp(ItemStack stack) {
		return !stack.is(Items.GLOW_INK_SAC) && super.wantsToPickUp(stack);
	}
	
	@Override
	public SoundEvent getHurtSound(DamageSource ds) {
		return SoundEvents.GENERIC_HURT;
	}

	@Override
	public SoundEvent getDeathSound() {
		return SoundEvents.GENERIC_DEATH;
	}
	
	protected static class SwellGoal<T extends AbstractCreeperGirl> extends Goal {

		  private final T creeperGirl;

		   @Nullable
		   private LivingEntity target;

		   public SwellGoal(T creeperGirl) {
		      this.creeperGirl = creeperGirl;
		      this.setFlags(EnumSet.of(Goal.Flag.MOVE));
		   }
		   
           @Override
		   public boolean canUse() {
		      LivingEntity livingentity = this.creeperGirl.getTarget();
		      return this.creeperGirl.getSwellDir() > 0 || livingentity != null && this.creeperGirl.distanceToSqr(livingentity) < creeperGirl.maxDistance;
		   }

		   @Override
		   public void start() {
		      this.creeperGirl.getNavigation().stop();
		      this.target = this.creeperGirl.getTarget();
		   }

		   @Override
		   public void stop() {
		      this.target = null;
		   }

	 	   @Override
		   public boolean requiresUpdateEveryTick() {
		      return true;
		   }

           @Override
		   public void tick() {
		      if (this.target == null) {
		         this.creeperGirl.setSwellDir(-1);
		      } else if (this.creeperGirl.distanceToSqr(this.target) > 49.0D) {
		         this.creeperGirl.setSwellDir(-1);
		      } else if (!this.creeperGirl.getSensing().hasLineOfSight(this.target)) {
		         this.creeperGirl.setSwellDir(-1);
		      } else {
		         this.creeperGirl.setSwellDir(1);
		      }
		   }
	}
	
	public enum CombatMode {
		EXPLODE,
		DONT_EXPLODE,
		FIGHT_AND_EXPLODE;    
		
		public static final String NBT_KEY = "DataCombatMode";
	}	
	
	public static record ExplosionData (int explosionRadius, int explosionItensity, int maxSwell) {}
}