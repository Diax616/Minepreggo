package dev.dixmk.minepreggo.world.entity.preggo.ender;

import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.init.MinepreggoModDamageSources;
import dev.dixmk.minepreggo.init.MinepreggoModSounds;
import dev.dixmk.minepreggo.world.entity.BellyPartFactory;
import dev.dixmk.minepreggo.world.entity.BellyPartManager;
import dev.dixmk.minepreggo.world.entity.LivingEntityHelper;
import dev.dixmk.minepreggo.world.entity.preggo.IHostilPreggoMobPregnancyData;
import dev.dixmk.minepreggo.world.entity.preggo.IHostilPregnantPreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;
import dev.dixmk.minepreggo.world.entity.preggo.HostilPregnantPreggoMobDataImpl;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.monster.Endermite;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public abstract class AbstractHostilPregnantEnderWoman extends AbstractHostilMonsterEnderWoman implements IHostilPregnantPreggoMob {

	private static final HostilPregnantPreggoMobDataImpl.DataAccessor<AbstractHostilPregnantEnderWoman> DATA_ACCESOR = new HostilPregnantPreggoMobDataImpl.DataAccessor<>(AbstractHostilPregnantEnderWoman.class);
	private final IHostilPreggoMobPregnancyData pregnancyData;
	
	protected AbstractHostilPregnantEnderWoman(EntityType<? extends AbstractHostilMonsterEnderWoman> p_32485_, Level p_32486_, PregnancyPhase currentPregnancyStage) {
		super(p_32485_, p_32486_);
		pregnancyData = new HostilPregnantPreggoMobDataImpl<>(DATA_ACCESOR, this, currentPregnancyStage);
	}
	
	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		DATA_ACCESOR.defineSynchedData(this);
	}
	
	@Override
	public IHostilPreggoMobPregnancyData getPregnancyData() {
		return this.pregnancyData;
	}
	
	@Override
	public void addAdditionalSaveData(CompoundTag compoundTag) {
		super.addAdditionalSaveData(compoundTag);
		compoundTag.put("MonsterPregnantPreggoMobData", pregnancyData.serializeNBT());
	}
	
	@Override
	public void readAdditionalSaveData(CompoundTag compoundTag) {
		super.readAdditionalSaveData(compoundTag);	
		if (compoundTag.contains("MonsterPregnantPreggoMobData")) {
			pregnancyData.deserializeNBT(compoundTag.getCompound("MonsterPregnantPreggoMobData"));
		}
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
	public boolean hasCustomHeadAnimation() {
		return this.pregnancyData.isIncapacitated();
	}
	
	@Override
   	public void tick() {
      super.tick();
      
      if (this.level().isClientSide) {
    	  return;
      }

      if (this.pregnancyData.isIncapacitated()) {   	  
    	  final var timer = this.pregnancyData.getPregnancyPainTimer();
    	  if (timer > pregnancyData.getIncapacitatedCooldown()) {
    		  this.pregnancyData.setPregnancyPainTimer(0);
    		  this.pregnancyData.setPregnancyPain(false);
    	  }
    	  else {
        	  this.pregnancyData.setPregnancyPainTimer(timer + 1);
    	  }
      }  
      
      if (MinepreggoModConfig.SERVER.isBellyColisionsForPreggoMobsEnable() && pregnancyData.getCurrentPregnancyPhase().compareTo(PregnancyPhase.P4) >= 0) {
    	  BellyPartManager.getInstance().onServerTick(this, () -> BellyPartFactory.createHumanoidBellyPart(this, pregnancyData.getCurrentPregnancyPhase()));
      }
	}
	
	@Override
	public boolean hurt(DamageSource damagesource, float amount) {	
		var result = super.hurt(damagesource, amount);
		if (!this.level().isClientSide
				&& result
				&& !this.pregnancyData.isIncapacitated()
				&& this.getRandom().nextFloat() < pregnancyData.getPregnancyPainProbability()) {		
			this.pregnancyData.setPregnancyPain(true);	
			LivingEntityHelper.playSoundNearTo(this, MinepreggoModSounds.getRandomStomachGrowls(random));
		}
		return result;
	}
	
	@Override
	protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new AbstractEnderWoman.EnderWomanFreezeWhenLookedAt(this) {
        	@Override
			public boolean canUse() {
				return super.canUse() && !getPregnancyData().isIncapacitated();
			}
			@Override
			public boolean canContinueToUse() {
				return super.canContinueToUse() && !getPregnancyData().isIncapacitated();
			}
        });       
		this.goalSelector.addGoal(10, new AbstractEnderWoman.EnderWomanLeaveBlockGoal(this) {
        	@Override
			public boolean canUse() {
				return super.canUse() && !getPregnancyData().isIncapacitated();
			}
        });
        this.goalSelector.addGoal(11, new AbstractEnderWoman.EnderWomanTakeBlockGoal(this) {
        	@Override
			public boolean canUse() {
				return super.canUse() && !getPregnancyData().isIncapacitated();
			}
        });
        this.targetSelector.addGoal(1, new AbstractEnderWoman.EnderWomanLookForPlayerGoal(this, this::isAngryAt) {
        	@Override
			public boolean canUse() {
				return super.canUse() && !getPregnancyData().isIncapacitated();
			}
			@Override
			public boolean canContinueToUse() {
				return super.canContinueToUse() && !getPregnancyData().isIncapacitated();
			}
        });
        this.targetSelector.addGoal(4, new ResetUniversalAngerTargetGoal<>(this, false));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Endermite.class, true, false) {
        	@Override
			public boolean canUse() {
				return super.canUse() && !getPregnancyData().isIncapacitated();
			}
			@Override
			public boolean canContinueToUse() {
				return super.canContinueToUse() && !getPregnancyData().isIncapacitated();
			}
        });
		this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, false) {
        	@Override
			public boolean canUse() {
				return super.canUse() && !getPregnancyData().isIncapacitated();
			}
			@Override
			public boolean canContinueToUse() {
				return super.canContinueToUse() && !getPregnancyData().isIncapacitated();
			}
        });
		this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0D, 0.0F) {
        	@Override
			public boolean canUse() {
				return super.canUse() && !getPregnancyData().isIncapacitated();
			}
			@Override
			public boolean canContinueToUse() {
				return super.canContinueToUse() && !getPregnancyData().isIncapacitated();
			}
        });
		this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F) {
        	@Override
			public boolean canUse() {
				return super.canUse() && !getPregnancyData().isIncapacitated();
			}
        });
		this.goalSelector.addGoal(8, new RandomLookAroundGoal(this) {
        	@Override
			public boolean canUse() {
				return super.canUse() && !getPregnancyData().isIncapacitated();
			}
        });
		this.targetSelector.addGoal(2, new HurtByTargetGoal(this) {
        	@Override
			public boolean canUse() {
				return super.canUse() && !getPregnancyData().isIncapacitated();
			}
			@Override
			public boolean canContinueToUse() {
				return super.canContinueToUse() && !getPregnancyData().isIncapacitated();
			}
        });
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