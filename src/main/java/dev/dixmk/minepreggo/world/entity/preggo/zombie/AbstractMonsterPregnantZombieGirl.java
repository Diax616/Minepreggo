package dev.dixmk.minepreggo.world.entity.preggo.zombie;

import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.init.MinepreggoModDamageSources;
import dev.dixmk.minepreggo.init.MinepreggoModSounds;
import dev.dixmk.minepreggo.world.entity.BellyPartFactory;
import dev.dixmk.minepreggo.world.entity.BellyPartManager;
import dev.dixmk.minepreggo.world.entity.player.PlayerHelper;
import dev.dixmk.minepreggo.world.entity.preggo.IMonsterPreggoMobPregnancyData;
import dev.dixmk.minepreggo.world.entity.preggo.IMonsterPregnantPreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.MonsterPregnantPreggoMobDataImpl;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.FleeSunGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.MoveThroughVillageGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RestrictSunGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public abstract class AbstractMonsterPregnantZombieGirl extends AbstractMonsterZombieGirl implements IMonsterPregnantPreggoMob {

	private static final MonsterPregnantPreggoMobDataImpl.DataAccessor<AbstractMonsterPregnantZombieGirl> DATA_ACCESOR = new MonsterPregnantPreggoMobDataImpl.DataAccessor<>(AbstractMonsterPregnantZombieGirl.class);
	private final IMonsterPreggoMobPregnancyData pregnancyData;
	
	protected AbstractMonsterPregnantZombieGirl(EntityType<? extends AbstractMonsterZombieGirl> p_21803_, Level p_21804_, PregnancyPhase currentPregnancyStage) {
		super(p_21803_, p_21804_);
		pregnancyData = new MonsterPregnantPreggoMobDataImpl<>(DATA_ACCESOR, this, currentPregnancyStage);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		DATA_ACCESOR.defineSynchedData(this);
	}
	
	@Override
	public IMonsterPreggoMobPregnancyData getPregnancyData() {
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
			boolean bellyBurst = source.is(MinepreggoModDamageSources.BELLY_BURST);
			if (bellyBurst) {
				PregnancySystemHelper.deathByBellyBurst(this, (ServerLevel) this.level());
			}
			PreggoMobHelper.spawnBabyAndFetusZombies(this);
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
    	  if (timer > 120) {
    		  this.pregnancyData.setPregnancyPainTimer(0);
    		  this.pregnancyData.setPregnancyPain(false);
    	  }
    	  else {
        	  this.pregnancyData.setPregnancyPainTimer(timer + 1);
    	  }
      }  
      
      if (MinepreggoModConfig.isBellyColisionsEnable() && pregnancyData.getCurrentPregnancyPhase().compareTo(PregnancyPhase.P5) >= 0) {
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
			PlayerHelper.playSoundNearTo(this, MinepreggoModSounds.getRandomStomachGrowls(random));
		}
		return result;
	}
	
	@Override
	public SoundEvent getDeathSound() {
		return MinepreggoModSounds.PREGNANT_PREGGO_MOB_DEATH.get();
	}
	
	@Override
	protected boolean canReplaceCurrentItem(ItemStack p_21428_, ItemStack p_21429_) {	
		if (!PregnancySystemHelper.canUseChestplate(p_21428_.getItem(), this.pregnancyData.getCurrentPregnancyPhase())
					|| !PregnancySystemHelper.canUseLegging(p_21428_.getItem(), this.pregnancyData.getCurrentPregnancyPhase())) {
			return false;
		}	
		return super.canReplaceCurrentItem(p_21428_, p_21429_);
	}
	
	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.2, false) {
			@Override
			public boolean canUse() {
				return super.canUse() && !pregnancyData.isIncapacitated();		
			}			
			@Override
			public boolean canContinueToUse() {
				return super.canContinueToUse() && !pregnancyData.isIncapacitated();			   
			}
		});	
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this) {
			@Override
			public boolean canUse() {
				return super.canUse() && !pregnancyData.isIncapacitated();		
			}			
			@Override
			public boolean canContinueToUse() {
				return super.canContinueToUse() && !pregnancyData.isIncapacitated();			   
			}
		}.setAlertOthers(AbstractMonsterZombieGirl.class));	
		this.goalSelector.addGoal(2, new RestrictSunGoal(this) {
			@Override
			public boolean canUse() {
				return super.canUse() && !pregnancyData.isIncapacitated();		
			}		
			@Override
			public boolean canContinueToUse() {
				return super.canContinueToUse() && !pregnancyData.isIncapacitated();			   
			}
		});
		this.goalSelector.addGoal(2, new FleeSunGoal(this, 1.1D) {
			@Override
			public boolean canUse() {
				return super.canUse() && !pregnancyData.isIncapacitated();		
			}
			@Override
			public boolean canContinueToUse() {
				return super.canContinueToUse() && !pregnancyData.isIncapacitated();			   
			}
		});			
	    this.goalSelector.addGoal(6, new MoveThroughVillageGoal(this, 1.0D, true, 4, () -> false) {
			@Override
			public boolean canUse() {
				return super.canUse() && !pregnancyData.isIncapacitated();		
			}
			@Override
			public boolean canContinueToUse() {
				return super.canContinueToUse() && !pregnancyData.isIncapacitated();			   
			}
	    });
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, false, false) {
			@Override
			public boolean canUse() {
				return super.canUse() && !pregnancyData.isIncapacitated();		
			}
			@Override
			public boolean canContinueToUse() {
				return super.canContinueToUse() && !pregnancyData.isIncapacitated();			   
			}
		});
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, false, false) {
			@Override
			public boolean canUse() {
				return super.canUse() && !pregnancyData.isIncapacitated();		
			}
			@Override
			public boolean canContinueToUse() {
				return super.canContinueToUse() && !pregnancyData.isIncapacitated();			   
			}
		});
		this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, IronGolem.class, false, false) {
			@Override
			public boolean canUse() {
				return super.canUse() && !pregnancyData.isIncapacitated();		
			}
			@Override
			public boolean canContinueToUse() {
				return super.canContinueToUse() && !pregnancyData.isIncapacitated();			   
			}
		});
		this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, Turtle.class, 10, true, false, Turtle.BABY_ON_LAND_SELECTOR) {
			@Override
			public boolean canUse() {
				return super.canUse() && !pregnancyData.isIncapacitated();		
			}
			@Override
			public boolean canContinueToUse() {
				return super.canContinueToUse() && !pregnancyData.isIncapacitated();			   
			}
		});	
		this.goalSelector.addGoal(7, new AbstractZombieGirl.ZombieGirlAttackTurtleEggGoal(this, 1.0, 3) {
			@Override
			public boolean canUse() {
				return super.canUse() && !pregnancyData.isIncapacitated();		
			}
			@Override
			public boolean canContinueToUse() {
				return super.canContinueToUse() && !pregnancyData.isIncapacitated();			   
			}
		});
		this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8F) {
			@Override
			public boolean canUse() {
				return super.canUse() && !pregnancyData.isIncapacitated();		
			}
		});
		this.goalSelector.addGoal(9, new WaterAvoidingRandomStrollGoal(this, 1.0) {
			@Override
			public boolean canUse() {
				return super.canUse() && !pregnancyData.isIncapacitated();		
			}
			@Override
			public boolean canContinueToUse() {
				return super.canContinueToUse() && !pregnancyData.isIncapacitated();			   
			}
		});
		this.goalSelector.addGoal(10, new RandomLookAroundGoal(this) {
			@Override
			public boolean canUse() {
				return super.canUse() && !pregnancyData.isIncapacitated();		
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
