package dev.dixmk.minepreggo.world.entity.preggo.creeper;

import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.gameevent.GameEvent;

import java.util.Collection;
import java.util.EnumSet;

import javax.annotation.Nullable;

import dev.dixmk.minepreggo.init.MinepreggoItems;
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
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.PowerableMob;
import net.minecraft.world.entity.SpawnGroupData;

public abstract class AbstractCreeperGirl extends PreggoMob implements PowerableMob {
	public static final ExplosionData DEFAULT_EXPLOSION_DATA = new ExplosionData(3, 1, 30);
	
	private static final EntityDataAccessor<Integer> DATA_SWELL_DIR = SynchedEntityData.defineId(AbstractCreeperGirl.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Boolean> DATA_IS_POWERED = SynchedEntityData.defineId(AbstractCreeperGirl.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Boolean> DATA_IS_IGNITED = SynchedEntityData.defineId(AbstractCreeperGirl.class, EntityDataSerializers.BOOLEAN);
	private int oldSwell;
	private int swell;
	private int droppedSkulls;
	private ExplosionData explosionData = new ExplosionData(DEFAULT_EXPLOSION_DATA);
	
	protected double maxDistance = 9D;
	private CombatMode combatMode = CombatMode.EXPLODE;
	
	protected AbstractCreeperGirl(EntityType<? extends PreggoMob> entity, Level level, Creature typeOfCreature) {
		super(entity, level, Species.CREEPER, typeOfCreature);   
	}
		
	protected void setExplosionData(ExplosionData explosionData) {
		this.explosionData = explosionData;
	}
	
	public ExplosionData getExplosionData() {
		return explosionData;
	}
	
	public void setCombatMode(CombatMode value) {
		this.combatMode = value;
		if (value == CombatMode.EXPLODE)
			this.maxDistance = 3D;
	}
	
	public CombatMode getCombatMode() {
		return combatMode;
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
		return stack.is(MinepreggoItems.ACTIVATED_GUNPOWDER.get());
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
		return Mth.lerp(partialTick, this.oldSwell, this.swell) / (this.explosionData.maxSwell - 2);
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
			float f = this.isPowered() ? this.explosionData.explosionItensity * 2F : this.explosionData.explosionItensity;
			this.dead = true;
			this.level().explode(this, this.getX(), this.getY(), this.getZ(), this.explosionData.explosionRadius * f, Level.ExplosionInteraction.MOB);
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
		if (this.swell > this.explosionData.maxSwell - 5) {
			this.swell = this.explosionData.maxSwell - 5;
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

			if (this.swell >= this.explosionData.maxSwell) {
				this.swell = this.explosionData.maxSwell;
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
	
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData, @Nullable CompoundTag dataTag) {
		SpawnGroupData spawnData = super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData, dataTag);		
	    final float p = this.getRandom().nextFloat();
	    final float specialMultiplier = difficulty.getSpecialMultiplier(); // 0.0 (Easy) -> 1.0 (Hard)

	    // Base thresholds scale with difficulty:
	    // DONT_EXPLODE: starts at 20% on Easy, drops to ~5% on Hard
	    // FIGHT_AND_EXPLODE: starts at 15% on Easy, rises to ~45% on Hard
	    // EXPLODE: fills the rest

	    final float dontExplodeChance = Mth.lerp(specialMultiplier, 0.20F, 0.05F);
	    final float fightAndExplodeChance = Mth.lerp(specialMultiplier, 0.15F, 0.45F);

	    // Cumulative thresholds
	    final float thresholdDontExplode = dontExplodeChance;
	    final float thresholdFightAndExplode = dontExplodeChance + fightAndExplodeChance;
	    // EXPLODE fills the rest: from thresholdFightAndExplode to 1.0

	    if (p < thresholdDontExplode) {
	        this.setCombatMode(CombatMode.DONT_EXPLODE);
	    } else if (p < thresholdFightAndExplode) {
	        this.setCombatMode(CombatMode.FIGHT_AND_EXPLODE);
	    } else {
	        this.setCombatMode(CombatMode.EXPLODE);
	    }
		
		return spawnData;
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
	
	public static class ExplosionData {
		private int explosionRadius;
		private int explosionItensity;
		private int maxSwell;
		
		public ExplosionData(int explosionRadius, int explosionItensity, int maxSwell) {
			this.explosionRadius = explosionRadius;
			this.explosionItensity = explosionItensity;
			this.maxSwell = maxSwell;
		}
		
		public ExplosionData(ExplosionData other) {
			this.explosionRadius = other.explosionRadius;
			this.explosionItensity = other.explosionItensity;
			this.maxSwell = other.maxSwell;
		}
		
		public int getExplosionRadius() {
			return explosionRadius;
		}
		
		public int getExplosionItensity() {
			return explosionItensity;
		}
		
		public int getMaxSwell() {
			return maxSwell;
		}
	}
}