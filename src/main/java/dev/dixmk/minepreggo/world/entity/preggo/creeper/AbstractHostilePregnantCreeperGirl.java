package dev.dixmk.minepreggo.world.entity.preggo.creeper;

import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.init.MinepreggoModDamageSources;
import dev.dixmk.minepreggo.init.MinepreggoModSounds;
import dev.dixmk.minepreggo.world.entity.BellyPartFactory;
import dev.dixmk.minepreggo.world.entity.BellyPartManager;
import dev.dixmk.minepreggo.world.entity.LivingEntityHelper;
import dev.dixmk.minepreggo.world.entity.preggo.Creature;
import dev.dixmk.minepreggo.world.entity.preggo.IHostilePreggoMobPregnancyData;
import dev.dixmk.minepreggo.world.entity.preggo.IHostilePregnantPreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.HostilePregnantPreggoMobDataImpl;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
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

public abstract class AbstractHostilePregnantCreeperGirl extends AbstractHostileCreeperGirl implements IHostilePregnantPreggoMob {

	private static final HostilePregnantPreggoMobDataImpl.DataAccessor<AbstractHostilePregnantCreeperGirl> DATA_ACCESOR = new HostilePregnantPreggoMobDataImpl.DataAccessor<>(AbstractHostilePregnantCreeperGirl.class);
	private final IHostilePreggoMobPregnancyData pregnancyDataImpl;
		
	protected AbstractHostilePregnantCreeperGirl(EntityType<? extends AbstractHostileCreeperGirl> entityType, Level level, Creature typeOfCreature, PregnancyPhase currentPregnancyStage) {
		super(entityType, level, typeOfCreature);
		pregnancyDataImpl = new HostilePregnantPreggoMobDataImpl<>(DATA_ACCESOR, this, currentPregnancyStage);
		setExplosionData(updateExplosionByPregnancyPhase(currentPregnancyStage));
	}
	
	protected abstract ExplosionData updateExplosionByPregnancyPhase(PregnancyPhase pregnancyPhase);
	
	@Override
	public IHostilePreggoMobPregnancyData getPregnancyData() {
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
		compoundTag.put("HostilePregnantCreeperGirlData", pregnancyDataImpl.serializeNBT());
	}
	
	@Override
	public void readAdditionalSaveData(CompoundTag compoundTag) {
		super.readAdditionalSaveData(compoundTag);	
		if (compoundTag.contains("HostilePregnantCreeperGirlData")) {
			pregnancyDataImpl.deserializeNBT(compoundTag.getCompound("HostilePregnantCreeperGirlData"));
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
	public boolean hurt(DamageSource damagesource, float amount) {	
		var result = super.hurt(damagesource, amount);
		if (!this.level().isClientSide
				&& result
				&& !this.pregnancyDataImpl.isIncapacitated()
				&& this.getRandom().nextFloat() < pregnancyDataImpl.getPregnancyPainProbability()) {		
			this.pregnancyDataImpl.setPregnancyPain(true);	
			LivingEntityHelper.playSoundNearTo(this, MinepreggoModSounds.getRandomStomachGrowls(random));
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
    	  if (timer > pregnancyDataImpl.getIncapacitatedCooldown()) {
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