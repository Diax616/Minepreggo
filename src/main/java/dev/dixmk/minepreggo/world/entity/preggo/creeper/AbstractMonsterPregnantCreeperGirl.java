package dev.dixmk.minepreggo.world.entity.preggo.creeper;

import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.init.MinepreggoModDamageSources;
import dev.dixmk.minepreggo.init.MinepreggoModSounds;
import dev.dixmk.minepreggo.world.entity.BellyPartFactory;
import dev.dixmk.minepreggo.world.entity.BellyPartManager;
import dev.dixmk.minepreggo.world.entity.player.PlayerHelper;
import dev.dixmk.minepreggo.world.entity.preggo.Creature;
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
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.animal.Ocelot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public abstract class AbstractMonsterPregnantCreeperGirl extends AbstractMonsterCreeperGirl implements IMonsterPregnantPreggoMob {

	private static final MonsterPregnantPreggoMobDataImpl.DataAccessor<AbstractMonsterPregnantCreeperGirl> DATA_ACCESOR = new MonsterPregnantPreggoMobDataImpl.DataAccessor<>(AbstractMonsterPregnantCreeperGirl.class);
	private final IMonsterPreggoMobPregnancyData pregnancyDataImpl;
		
	protected AbstractMonsterPregnantCreeperGirl(EntityType<? extends AbstractMonsterCreeperGirl> p_21803_, Level p_21804_, Creature typeOfCreature, PregnancyPhase currentPregnancyStage) {
		super(p_21803_, p_21804_, typeOfCreature);
		pregnancyDataImpl = new MonsterPregnantPreggoMobDataImpl<>(DATA_ACCESOR, this, currentPregnancyStage);
		this.setExplosionByCurrentPregnancyStage();	
	}
	
	@Override
	public IMonsterPreggoMobPregnancyData getPregnancyData() {
		return pregnancyDataImpl;
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		DATA_ACCESOR.defineSynchedData(this);
	}
	
	@Override
	public void addAdditionalSaveData(CompoundTag compoundTag) {
		super.addAdditionalSaveData(compoundTag);
		compoundTag.put("MonsterPregnantPreggoMobData", pregnancyDataImpl.serializeNBT());
	}
	
	@Override
	public void readAdditionalSaveData(CompoundTag compoundTag) {
		super.readAdditionalSaveData(compoundTag);	
		if (compoundTag.contains("MonsterPregnantPreggoMobData")) {
			pregnancyDataImpl.deserializeNBT(compoundTag.getCompound("MonsterPregnantPreggoMobData"));
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
			PreggoMobHelper.spawnBabyAndFetusCreepers(this);
		}
	}
	
	@Override
	public SoundEvent getDeathSound() {
		return MinepreggoModSounds.PREGNANT_PREGGO_MOB_DEATH.get();
	}
	
	protected void setExplosionByCurrentPregnancyStage() {	
		final var currentPregnancyStage = pregnancyDataImpl.getCurrentPregnancyPhase();
		
		if (currentPregnancyStage == PregnancyPhase.P2
				|| currentPregnancyStage == PregnancyPhase.P3) {
			++this.explosionRadius;
		}
		else if (currentPregnancyStage == PregnancyPhase.P4
				|| currentPregnancyStage == PregnancyPhase.P5
				|| currentPregnancyStage == PregnancyPhase.P6) {
			++this.explosionItensity;
			++this.explosionRadius;
		}
		else if (currentPregnancyStage == PregnancyPhase.P7
				|| currentPregnancyStage == PregnancyPhase.P8) {
			this.explosionItensity += 2;
			this.explosionRadius += 2;
		}
	}
	
	@Override
	public boolean hurt(DamageSource damagesource, float amount) {	
		var result = super.hurt(damagesource, amount);
		if (!this.level().isClientSide
				&& result
				&& !this.pregnancyDataImpl.isIncapacitated()
				&& this.getRandom().nextFloat() < pregnancyDataImpl.getPregnancyPainProbability()) {		
			this.pregnancyDataImpl.setPregnancyPain(true);	
			PlayerHelper.playSoundNearTo(this, MinepreggoModSounds.getRandomStomachGrowls(random));
		}
		return result;
	}
	
	@Override
	public boolean hasCustomHeadAnimation() {
		return pregnancyDataImpl.isIncapacitated();
	}
	
	@Override
   	public void tick() {
      super.tick();
         
      if (this.level().isClientSide) {
    	  return;
      }

      if (pregnancyDataImpl.isIncapacitated()) {   	  
    	  final var timer = pregnancyDataImpl.getPregnancyPainTimer();
    	  if (timer > 120) {
    		  pregnancyDataImpl.setPregnancyPainTimer(0);
    		  pregnancyDataImpl.setPregnancyPain(false);
    	  }
    	  else {
    		  pregnancyDataImpl.setPregnancyPainTimer(timer + 1);
    	  }
      }  
      
      if (MinepreggoModConfig.SERVER.isBellyColisionsForPreggoMobsEnable() && pregnancyDataImpl.getCurrentPregnancyPhase().compareTo(PregnancyPhase.P4) >= 0) {
    	  BellyPartManager.getInstance().onServerTick(this, () -> BellyPartFactory.createHumanoidBellyPart(this, pregnancyDataImpl.getCurrentPregnancyPhase()));
      }
	}
	
	@Override
	public boolean causeFallDamage(float pFallDistance, float pMultiplier, DamageSource pSource) {
		PregnancyPhase currentPregnancyPhase = this.pregnancyDataImpl.getCurrentPregnancyPhase();
		if (currentPregnancyPhase.compareTo(PregnancyPhase.P3) >= 0) {
			return super.causeFallDamage(pFallDistance, pMultiplier * PregnancySystemHelper.calculateExtraFallDamageMultiplier(currentPregnancyPhase), pSource);
		}
		return super.causeFallDamage(pFallDistance, pMultiplier, pSource);
	}
	
	@Override
	protected void registerGoals() {
		this.addBehaviourGoals();
		this.goalSelector.addGoal(3, new MeleeAttackGoal(this, 1.2, false) {
			@Override
			public boolean canUse() {
				return super.canUse() && !pregnancyDataImpl.isIncapacitated();		
			}
			@Override
			public boolean canContinueToUse() {
				return super.canContinueToUse() && !pregnancyDataImpl.isIncapacitated();			   
			}
		});
		this.targetSelector.addGoal(2, new HurtByTargetGoal(this) {
			@Override
			public boolean canUse() {
				return super.canUse() && !pregnancyDataImpl.isIncapacitated();		
			}
			@Override
			public boolean canContinueToUse() {
				return super.canContinueToUse() && !pregnancyDataImpl.isIncapacitated();			   
			}
		});

		this.goalSelector.addGoal(3, new FloatGoal(this) {
			@Override
			public boolean canUse() {
				return super.canUse() 
					&& !pregnancyDataImpl.isIncapacitated();		
			}
		});
		this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0) {
			@Override
			public boolean canUse() {
				return super.canUse() && !pregnancyDataImpl.isIncapacitated();		
			}
			@Override
			public boolean canContinueToUse() {
				return super.canContinueToUse() && !pregnancyDataImpl.isIncapacitated();			   
			}
		});
		this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8F) {
			@Override
			public boolean canUse() {
				return super.canUse() && !pregnancyDataImpl.isIncapacitated();		
			}
		});
		this.goalSelector.addGoal(8, new RandomLookAroundGoal(this) {
			@Override
			public boolean canUse() {
				return super.canUse() && !pregnancyDataImpl.isIncapacitated();		
			}
		});
	}
	
	@Override
	protected void addBehaviourGoals() {
		this.goalSelector.addGoal(1, new AbstractCreeperGirl.SwellGoal<>(this) {		
			@Override
			public boolean canUse() {												
				return super.canUse() 
					&& canExplode()
					&& !pregnancyDataImpl.isIncapacitated();
			}
		});
		this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Player.class, true) {
			@Override
			public boolean canUse() {
				return super.canUse() && !pregnancyDataImpl.isIncapacitated();		
			}
			@Override
			public boolean canContinueToUse() {
				return super.canContinueToUse() && !pregnancyDataImpl.isIncapacitated();			   
			}
		});
		this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, Ocelot.class, 6F, 1, 1.2) {
			@Override
			public boolean canUse() {
				return super.canUse() && !pregnancyDataImpl.isIncapacitated();		
			}
			@Override
			public boolean canContinueToUse() {
				return super.canContinueToUse() && !pregnancyDataImpl.isIncapacitated();			   
			}
		});
		this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, Cat.class, 6F, 1, 1.2) {
			@Override
			public boolean canUse() {
				return super.canUse() && !pregnancyDataImpl.isIncapacitated();		
			}
			@Override
			public boolean canContinueToUse() {
				return super.canContinueToUse() && !pregnancyDataImpl.isIncapacitated();			   
			}
		});	
	}
}