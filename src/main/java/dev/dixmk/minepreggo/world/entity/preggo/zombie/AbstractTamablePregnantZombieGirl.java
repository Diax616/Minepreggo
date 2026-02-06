package dev.dixmk.minepreggo.world.entity.preggo.zombie;

import java.util.Set;

import javax.annotation.Nonnull;

import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.client.animation.player.BellyAnimationManager;
import dev.dixmk.minepreggo.client.animation.preggo.BellyAnimation;
import dev.dixmk.minepreggo.init.MinepreggoModDamageSources;
import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.init.MinepreggoModSounds;
import dev.dixmk.minepreggo.world.entity.BellyPartFactory;
import dev.dixmk.minepreggo.world.entity.BellyPartManager;
import dev.dixmk.minepreggo.world.entity.ai.goal.BreakBlocksToFollowOwnerGoal;
import dev.dixmk.minepreggo.world.entity.ai.goal.EatGoal;
import dev.dixmk.minepreggo.world.entity.ai.goal.GoalHelper;
import dev.dixmk.minepreggo.world.entity.ai.goal.PregnantPreggoMobFollowOwnerGoal;
import dev.dixmk.minepreggo.world.entity.ai.goal.PregnantPreggoMobOwnerHurtByTargetGoal;
import dev.dixmk.minepreggo.world.entity.ai.goal.PregnantPreggoMobOwnerHurtTargetGoal;
import dev.dixmk.minepreggo.world.entity.preggo.IPreggoMobPregnancySystem;
import dev.dixmk.minepreggo.world.entity.preggo.ITamablePregnantPreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.ITamablePregnantPreggoMobData;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;
import dev.dixmk.minepreggo.world.entity.preggo.TamablePregnantPreggoMobDataImpl;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.FleeSunGoal;
import net.minecraft.world.entity.ai.goal.RestrictSunGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public abstract class AbstractTamablePregnantZombieGirl extends AbstractTamableZombieGirl implements ITamablePregnantPreggoMob {

    private static final TamablePregnantPreggoMobDataImpl.DataAccessor<AbstractTamablePregnantZombieGirl> DATA_HOLDER = new TamablePregnantPreggoMobDataImpl.DataAccessor<>(AbstractTamablePregnantZombieGirl.class);

	protected final ITamablePregnantPreggoMobData pregnancyData;
	
	protected final IPreggoMobPregnancySystem pregnancySystem;
	
	public final AnimationState bellyAnimationState = new AnimationState();
	
	protected AbstractTamablePregnantZombieGirl(EntityType<? extends AbstractTamablePregnantZombieGirl> p_21803_, Level p_21804_, PregnancyPhase currentPregnancyStage) {
		super(p_21803_, p_21804_);
		this.pregnancyData = new TamablePregnantPreggoMobDataImpl<>(DATA_HOLDER, this, currentPregnancyStage);		
		this.pregnancySystem = createPregnancySystem();
	}
	
	protected abstract @Nonnull IPreggoMobPregnancySystem createPregnancySystem();
	
	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();		
		DATA_HOLDER.defineSynchedData(this);
	}
	
	@Override
	public void addAdditionalSaveData(CompoundTag compoundTag) {
		super.addAdditionalSaveData(compoundTag);
		compoundTag.put("PregnantTamableData", this.pregnancyData.serializeNBT());
	}
	
	@Override
	public void readAdditionalSaveData(CompoundTag compoundTag) {
		super.readAdditionalSaveData(compoundTag);	
		if (compoundTag.contains("PregnantTamableData", Tag.TAG_COMPOUND)) {
			this.pregnancyData.deserializeNBT(compoundTag.getCompound("PregnantTamableData"));
		}
	}
	
	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(8, new AbstractZombieGirl.ZombieGirlAttackTurtleEggGoal(this, 1.0D, 3){
			@Override
			public boolean canUse() {
				return super.canUse()
				&& !tamablePreggoMobData.isWaiting()
				&& !pregnancyData.isIncapacitated();
			}
		});	
		this.goalSelector.addGoal(1, new RestrictSunGoal(this) {
			@Override
			public boolean canUse() {
				return super.canUse() 
				&& !getPregnancyData().isIncapacitated();			
			}

			@Override
			public boolean canContinueToUse() {
				return super.canContinueToUse() 
				&& !getPregnancyData().isIncapacitated();	
			}
		});
		this.goalSelector.addGoal(1, new FleeSunGoal(this, 1.2D) {
			@Override
			public boolean canUse() {
				return super.canUse() 
				&& !getPregnancyData().isIncapacitated();			
			}

			@Override
			public boolean canContinueToUse() {
				return super.canContinueToUse() 
				&& !getPregnancyData().isIncapacitated();	
			}
		});
		this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, false, false) {
			@Override
			public boolean canUse() {
				return super.canUse() 
				&& (getTamableData().isSavage() || !isTame())
				&& !getPregnancyData().isIncapacitated();
			}

			@Override
			public boolean canContinueToUse() {
				return super.canContinueToUse() 
				&& !getPregnancyData().isIncapacitated();
			}
		});
		this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(this, IronGolem.class, false, false){
			@Override
			public boolean canUse() {
				return super.canUse() 
				&& !getPregnancyData().isIncapacitated()
				&& !getTamableData().isWaiting();
			}

			@Override
			public boolean canContinueToUse() {
				return super.canContinueToUse() 
				&& !getPregnancyData().isIncapacitated();
			}
		});
		this.targetSelector.addGoal(8, new NearestAttackableTargetGoal<>(this, Turtle.class, 10, true, false, Turtle.BABY_ON_LAND_SELECTOR){
			@Override
			public boolean canUse() {
				return super.canUse() 
				&& !getPregnancyData().isIncapacitated()
				&& !getTamableData().isWaiting();
			}

			@Override
			public boolean canContinueToUse() {
				return super.canContinueToUse() 
				&& !getPregnancyData().isIncapacitated();
			}
		});
	}
	
	@Override
	protected void reassessTameGoals() {
		if (this.isTame()) {		
			GoalHelper.addGoalWithReplacement(this, 3, new PregnantPreggoMobOwnerHurtByTargetGoal<>(this) {
				@Override
				public boolean canUse() {
					return super.canUse() 
					&& !isOnFire();				
				}

				@Override
				public boolean canContinueToUse() {
					return super.canContinueToUse()
					&& !isOnFire();	
				}
			});	
			GoalHelper.addGoalWithReplacement(this, 3, new PregnantPreggoMobOwnerHurtTargetGoal<>(this) {
				@Override
				public boolean canUse() {
					return super.canUse() 
					&& !isOnFire();				
				}

				@Override
				public boolean canContinueToUse() {
					return super.canContinueToUse()
					&& !isOnFire();	
				}
			});	
			GoalHelper.addGoalWithReplacement(this, 3, new PregnantPreggoMobOwnerHurtTargetGoal<>(this) {
				@Override
				public boolean canUse() {
					return super.canUse() 
					&& !isOnFire();				
				}

				@Override
				public boolean canContinueToUse() {
					return super.canContinueToUse()
					&& !isOnFire();	
				}
			}, true);
			GoalHelper.addGoalWithReplacement(this, 6, new PregnantPreggoMobFollowOwnerGoal<>(this, 1.2D, 7F, 2F, false) {
				@Override
				public boolean canUse() {
					return super.canUse() 
					&& !isOnFire();				
				}

				@Override
				public boolean canContinueToUse() {
					return super.canContinueToUse()
					&& !isOnFire();	
				}
			});
			GoalHelper.addGoalWithReplacement(this, 4, new BreakBlocksToFollowOwnerGoal<>(this, 2, 7) {
				@Override
				public boolean canUse() {
					return super.canUse() 
					&& !isOnFire()
					&& !getPregnancyData().isIncapacitated();
				}

				@Override
				public boolean canContinueToUse() {
					return super.canContinueToUse()
					&& !isOnFire()		
					&& !getPregnancyData().isIncapacitated();
				}
			}, true);	
			GoalHelper.addGoalWithReplacement(this, 6, new EatGoal<>(this, 0.6F, 30) {
				@Override
				public boolean canUse() {
					return super.canUse() 	
					&& !isOnFire()
					&& !getPregnancyData().isIncapacitated();
				}
				
				@Override
				public boolean canContinueToUse() {
					return super.canContinueToUse() 
					&& !isOnFire()
					&& !getPregnancyData().isIncapacitated();
				}
			});
		}
		else {
			GoalHelper.removeGoalByClass(this.goalSelector, Set.of(PregnantPreggoMobOwnerHurtByTargetGoal.class, PregnantPreggoMobFollowOwnerGoal.class, EatGoal.class));
			GoalHelper.removeGoalByClass(this.targetSelector, Set.of(PregnantPreggoMobOwnerHurtTargetGoal.class, BreakBlocksToFollowOwnerGoal.class));
		}
	}
	
	
	@Override
	public SoundEvent getDeathSound() {
		return MinepreggoModSounds.PREGNANT_PREGGO_MOB_DEATH.get();
	}
	
	@Override
	public boolean hasCustomHeadAnimation() {
		return super.hasCustomHeadAnimation() || this.pregnancyData.getPregnancyPain() != null;
	}
	
	@Override
	public void die(DamageSource source) {
		super.die(source);		
		if (!this.level().isClientSide) {
			boolean bellyBurst = source.is(MinepreggoModDamageSources.BELLY_BURST);
			if (bellyBurst) {
				PregnancySystemHelper.deathByBellyBurst(this, (ServerLevel) this.level());
			}
			PreggoMobHelper.spawnBabyAndFetusZombies(this, bellyBurst);
		}
	}
	
	@Override
	public boolean hurt(DamageSource damagesource, float amount) {
		boolean result = super.hurt(damagesource, amount);	
		
		if (result) {
			pregnancySystem.evaluateOnSuccessfulHurt(damagesource);
		}
		
		return result;
	}
	
	@Override
   	public void aiStep() {
      super.aiStep();  
      if (this.isAlive()) {	  
          this.pregnancySystem.onServerTick();       
      }
	}
	
	@Override
	public void tick() {
		super.tick();	
		if (this.level().isClientSide && !this.bellyAnimationState.isStarted()) {
			this.bellyAnimationState.start(this.tickCount);
		}	
		
		if (!this.level().isClientSide
				&& MinepreggoModConfig.SERVER.isBellyColisionsForPreggoMobsEnable()
				&& pregnancyData.getCurrentPregnancyPhase().compareTo(PregnancyPhase.P4) >= 0) {
			BellyPartManager.getInstance().onServerTick(this, () -> BellyPartFactory.createHumanoidBellyPart(this, pregnancyData.getCurrentPregnancyPhase()));
		}	
	}
	
	@Override
	protected boolean canReplaceCurrentItem(ItemStack p_21428_, ItemStack p_21429_) {	
		final var slot = LivingEntity.getEquipmentSlotForItem(p_21428_);				
		final var armor = p_21428_.getItem();
		
		if (slot == EquipmentSlot.CHEST) {
			return PregnancySystemHelper.canUseChestplate(armor, this.pregnancyData.getCurrentPregnancyPhase(), false) && PreggoMobHelper.canUseChestPlateInLactation(this, armor);
		}
		else if (slot == EquipmentSlot.LEGS) {
			return PregnancySystemHelper.canUseLegging(armor, this.pregnancyData.getCurrentPregnancyPhase());
		}	
		
		return super.canReplaceCurrentItem(p_21428_, p_21429_);
	}
	
	@Override
	public InteractionResult mobInteract(Player sourceentity, InteractionHand hand) {	
		var retval = this.pregnancySystem.onRightClick(sourceentity);
		if (retval.shouldAwardStats()) {
			return retval;
		}
		else {
			return super.mobInteract(sourceentity, hand);
		}
	}
	
	@Override
	public boolean doHurtTarget(Entity target) {		
		boolean result = super.doHurtTarget(target);	
		if (result && !this.tamablePreggoMobData.isSavage() && target instanceof Player owner && this.isOwnedBy(owner) && this.tamablePreggoMobData.isAngry()) {
			this.setTarget(null);		
		}
		return result;
	}
	
    @Override
    public void handleEntityEvent(byte id) {
        if (this.level().isClientSide) {
        	var animation = BellyAnimation.BELLY_SLAP_ANIMATION.get(id); 
            if (animation != null) {
            	BellyAnimationManager.getInstance().startAnimation(this, animation);
            	return;
            }
        }
        super.handleEntityEvent(id);
    }
	
	@Override
	public ITamablePregnantPreggoMobData getPregnancyData() {
		return this.pregnancyData;
	}
	
	@Override
	public boolean causeFallDamage(float pFallDistance, float pMultiplier, DamageSource pSource) {
		PregnancyPhase currentPregnancyPhase = this.pregnancyData.getCurrentPregnancyPhase();
		if (currentPregnancyPhase.compareTo(PregnancyPhase.P3) >= 0) {
			return super.causeFallDamage(pFallDistance, pMultiplier * PregnancySystemHelper.calculateExtraFallDamageMultiplier(currentPregnancyPhase), pSource);
		}
		return super.causeFallDamage(pFallDistance, pMultiplier, pSource);
	}
	
	public static EntityType<? extends AbstractTamablePregnantZombieGirl> getEntityType(PregnancyPhase phase) {	
		switch (phase) {
			case P0 -> MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P0.get();
			case P1 -> MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P1.get();
			case P2 -> MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P2.get();
			case P3 -> MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P3.get();
			case P4 -> MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P4.get();
			case P5 -> MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P5.get();
			case P6 -> MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P6.get();
			case P7 -> MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P7.get();
			case P8 -> MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P8.get();
		}
		throw new IllegalArgumentException("Unexpected value: " + phase);
	}
}
