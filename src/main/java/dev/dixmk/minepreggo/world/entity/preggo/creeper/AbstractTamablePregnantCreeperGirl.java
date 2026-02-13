package dev.dixmk.minepreggo.world.entity.preggo.creeper;

import java.util.Set;

import javax.annotation.Nonnull;

import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.client.animation.player.BellyAnimationManager;
import dev.dixmk.minepreggo.client.animation.preggo.BellyAnimation;
import dev.dixmk.minepreggo.init.MinepreggoModDamageSources;
import dev.dixmk.minepreggo.world.entity.BellyPartFactory;
import dev.dixmk.minepreggo.world.entity.BellyPartManager;
import dev.dixmk.minepreggo.world.entity.LivingEntityHelper;
import dev.dixmk.minepreggo.world.entity.ai.goal.GoalHelper;
import dev.dixmk.minepreggo.world.entity.ai.goal.PregnantPreggoMobFollowOwnerGoal;
import dev.dixmk.minepreggo.world.entity.ai.goal.PregnantPreggoMobOwnerHurtByTargetGoal;
import dev.dixmk.minepreggo.world.entity.ai.goal.PregnantPreggoMobOwnerHurtTargetGoal;
import dev.dixmk.minepreggo.world.entity.preggo.Creature;
import dev.dixmk.minepreggo.world.entity.preggo.IPreggoMobPregnancySystem;
import dev.dixmk.minepreggo.world.entity.preggo.ITamablePregnantPreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.ITamablePregnantPreggoMobData;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;
import dev.dixmk.minepreggo.world.entity.preggo.TamablePregnantPreggoMobDataImpl;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public abstract class AbstractTamablePregnantCreeperGirl extends AbstractTamableCreeperGirl implements ITamablePregnantPreggoMob {
	
    private static final TamablePregnantPreggoMobDataImpl.DataAccessor<AbstractTamablePregnantCreeperGirl> DATA_HOLDER = new TamablePregnantPreggoMobDataImpl.DataAccessor<>(AbstractTamablePregnantCreeperGirl.class);

	protected final ITamablePregnantPreggoMobData pregnancyData;
	
	protected final IPreggoMobPregnancySystem pregnancySystem;

	public final AnimationState bellyAnimationState = new AnimationState();
	
	protected AbstractTamablePregnantCreeperGirl(EntityType<? extends AbstractTamableCreeperGirl> p_21803_, Level p_21804_, Creature typeOfCreature, PregnancyPhase currentPregnancyStage) {
		super(p_21803_, p_21804_, typeOfCreature);
		this.pregnancyData = new TamablePregnantPreggoMobDataImpl<>(DATA_HOLDER, this, currentPregnancyStage);		
		this.pregnancySystem = createPregnancySystem();
		setExplosionData(updateExplosionByPregnancyPhase(currentPregnancyStage));
	}
	
	protected abstract ExplosionData updateExplosionByPregnancyPhase(PregnancyPhase pregnancyPhase);
	
	protected abstract @Nonnull IPreggoMobPregnancySystem createPregnancySystem();
	
	@Override
	public ITamablePregnantPreggoMobData getPregnancyData() {
		return pregnancyData;
	}
	
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
		if (compoundTag.contains("PregnantTamableData")) {
			this.pregnancyData.deserializeNBT(compoundTag.getCompound("PregnantTamableData"));
		}
	}
	
	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(1, new AbstractCreeperGirl.SwellGoal<>(this) {
			@Override
			public boolean canUse() {				
				return super.canUse() 
				&& canExplode()
				&& !pregnancyData.isIncapacitated();								
			}
		});
		
		this.goalSelector.addGoal(2, new FloatGoal(this) {
			@Override
			public boolean canUse() {
				return super.canUse() 
				&& !getPregnancyData().isIncapacitated();
			}
		});
		
		this.targetSelector.addGoal(2, new HurtByTargetGoal(this) {
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
			
		this.goalSelector.addGoal(5, new MeleeAttackGoal(this, 1.2D, false){
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
		
		this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0D) {
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
				
		this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(this, Player.class, false, false) {
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
		
		this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Player.class, 6F) {
			@Override
			public boolean canUse() {
				return super.canUse() 
				&& !LivingEntityHelper.hasValidTarget(mob)
				&& !getTamableData().isWaiting()
				&& !getPregnancyData().isIncapacitated();
				
			}

			@Override
			public boolean canContinueToUse() {
				return super.canContinueToUse() 
				&& !LivingEntityHelper.isTargetStillValid(mob)
				&& !getPregnancyData().isIncapacitated();
			}
		});			

		this.goalSelector.addGoal(10, new RandomLookAroundGoal(this) {
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
	}
	
	@Override
	protected void reassessTameGoals() {
		if (this.isTame()) {
			GoalHelper.addGoalWithReplacement(this, 3, new PregnantPreggoMobOwnerHurtByTargetGoal<>(this));
			GoalHelper.addGoalWithReplacement(this, 3, new PregnantPreggoMobOwnerHurtTargetGoal<>(this), true);
			GoalHelper.addGoalWithReplacement(this, 6, new PregnantPreggoMobFollowOwnerGoal<>(this, 1.2D, 6F, 2F, false));	
		}
		else {
			GoalHelper.removeGoalByClass(this.goalSelector, Set.of(PregnantPreggoMobOwnerHurtByTargetGoal.class, PregnantPreggoMobFollowOwnerGoal.class));
			GoalHelper.removeGoalByClass(this.targetSelector, PregnantPreggoMobOwnerHurtTargetGoal.class);
		}
	}
	
	@Override
	public boolean hasCustomHeadAnimation() {
		return super.hasCustomHeadAnimation() || this.pregnancyData.getPregnancyPain() != null;
	}
	
	@Override
	public void die(DamageSource source) {
		super.die(source);		
		if (!this.level().isClientSide) {
			if (source.is(MinepreggoModDamageSources.BELLY_BURST)) {
				PregnancySystemHelper.deathByBellyBurst(this, (ServerLevel) this.level());
			}
			PreggoMobHelper.spawnBabyAndFetus(this);
		}
	}
	
	@Override
	public boolean hurt(DamageSource damagesource, float amount) {
		boolean result = super.hurt(damagesource, amount);	
		
		if (!this.level().isClientSide && result) {
			pregnancySystem.evaluateOnSuccessfulHurt(damagesource);
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
	public boolean causeFallDamage(float pFallDistance, float pMultiplier, DamageSource pSource) {
		PregnancyPhase currentPregnancyPhase = this.pregnancyData.getCurrentPregnancyPhase();
		if (currentPregnancyPhase.compareTo(PregnancyPhase.P3) >= 0) {
			return super.causeFallDamage(pFallDistance, pMultiplier * PregnancySystemHelper.calculateExtraFallDamageMultiplier(currentPregnancyPhase), pSource);
		}
		return super.causeFallDamage(pFallDistance, pMultiplier, pSource);
	}
}

