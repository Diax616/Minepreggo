package dev.dixmk.minepreggo.world.entity.preggo.zombie;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.utils.MathHelper;
import dev.dixmk.minepreggo.world.entity.preggo.ISimplePregnancy;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancyStage;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancySystemConstants;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
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
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;

public abstract class AbstractMonsterPregnantZombieGirl extends AbstractMonsterZombieGirl implements ISimplePregnancy {

	private static final EntityDataAccessor<Boolean> DATA_HAS_PREGNANCY_PAIN = SynchedEntityData.defineId(AbstractMonsterPregnantZombieGirl.class, EntityDataSerializers.BOOLEAN);
	private int pregnancyPainTimer = 0;
	private PregnancyStage currentPregnanctStage;
	private PregnancyStage maxPregnanctStage;
	private int totalDaysPassed;
	
	protected AbstractMonsterPregnantZombieGirl(EntityType<? extends PreggoMob> p_21803_, Level p_21804_, PregnancyStage currentPregnancyStage, PregnancyStage maxPregnancyStage) {
		super(p_21803_, p_21804_);
		this.currentPregnanctStage = currentPregnancyStage;
		this.maxPregnanctStage = maxPregnancyStage;
		this.totalDaysPassed = ISimplePregnancy.getRandomTotalDaysPassed(currentPregnancyStage, maxPregnancyStage, this.getRandom());
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(DATA_HAS_PREGNANCY_PAIN, false);
	}
	
	@Override
	public void addAdditionalSaveData(CompoundTag compoundTag) {
		super.addAdditionalSaveData(compoundTag);
		compoundTag.putBoolean("DataHasPregnancyPain", this.getEntityData().get(DATA_HAS_PREGNANCY_PAIN));
	}
	
	@Override
	public void readAdditionalSaveData(CompoundTag compoundTag) {
		super.readAdditionalSaveData(compoundTag);	
		this.entityData.set(DATA_HAS_PREGNANCY_PAIN, compoundTag.getBoolean("DataHasPregnancyPain"));
	}
	
	@Override
	public void die(DamageSource source) {
		super.die(source);			
		PreggoMobHelper.spawnBabyAndFetusZombies(this);
	}
	
	@Override
	public SoundEvent getDeathSound() {
		return ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "preggo_death"));
	}
	
	
	@Override
	public boolean hasCustomHeadAnimation() {
		return this.hasPregnancyPain();
	}
	
	@Override
   	public void tick() {
      super.tick();
      
      if (this.level().isClientSide()) {
    	  return;
      }

      if (this.hasPregnancyPain()) {   	  
    	  final var timer = this.getPregnancyPainTimer();
    	  if (timer > 100) {
    		  this.setPregnancyPainTimer(0);
    		  this.setPregnancyPain(false);
    	  }
    	  else {
        	  this.setPregnancyPainTimer(timer + 1);
    	  }
      }  
	}
	
	@Override
	public boolean hurt(DamageSource damagesource, float amount) {	
		if (super.hurt(damagesource, amount) && !this.hasPregnancyPain() && !this.level().isClientSide()) {		
			final var p = MathHelper.sigmoid(0.1F, 0.4F, 0.1F, Mth.clamp(this.getTotalDaysPassed() /(float) PregnancySystemConstants.TOTAL_PREGNANCY_DAYS , 0, 1), 0.6F);		
			if (this.getRandom().nextFloat() < p) {
				this.setPregnancyPain(true);
			}			
			return true;
		}
		return false;
	}
	
	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.2, false) {
			@Override
			public boolean canUse() {
				return super.canUse() && !hasPregnancyPain();		
			}			
			@Override
			public boolean canContinueToUse() {
				return super.canContinueToUse() && !hasPregnancyPain();			   
			}
		});	
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this) {
			@Override
			public boolean canUse() {
				return super.canUse() && !hasPregnancyPain();		
			}			
			@Override
			public boolean canContinueToUse() {
				return super.canContinueToUse() && !hasPregnancyPain();			   
			}
		}.setAlertOthers(AbstractMonsterZombieGirl.class));	
		this.goalSelector.addGoal(2, new RestrictSunGoal(this) {
			@Override
			public boolean canUse() {
				return super.canUse() && !hasPregnancyPain();		
			}		
			@Override
			public boolean canContinueToUse() {
				return super.canContinueToUse() && !hasPregnancyPain();			   
			}
		});
		this.goalSelector.addGoal(2, new FleeSunGoal(this, 1.1D) {
			@Override
			public boolean canUse() {
				return super.canUse() && !hasPregnancyPain();		
			}
			@Override
			public boolean canContinueToUse() {
				return super.canContinueToUse() && !hasPregnancyPain();			   
			}
		});			
	    this.goalSelector.addGoal(6, new MoveThroughVillageGoal(this, 1.0D, true, 4, () -> false) {
			@Override
			public boolean canUse() {
				return super.canUse() && !hasPregnancyPain();		
			}
			@Override
			public boolean canContinueToUse() {
				return super.canContinueToUse() && !hasPregnancyPain();			   
			}
	    });
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, false, false) {
			@Override
			public boolean canUse() {
				return super.canUse() && !hasPregnancyPain();		
			}
			@Override
			public boolean canContinueToUse() {
				return super.canContinueToUse() && !hasPregnancyPain();			   
			}
		});
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, false, false) {
			@Override
			public boolean canUse() {
				return super.canUse() && !hasPregnancyPain();		
			}
			@Override
			public boolean canContinueToUse() {
				return super.canContinueToUse() && !hasPregnancyPain();			   
			}
		});
		this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, IronGolem.class, false, false) {
			@Override
			public boolean canUse() {
				return super.canUse() && !hasPregnancyPain();		
			}
			@Override
			public boolean canContinueToUse() {
				return super.canContinueToUse() && !hasPregnancyPain();			   
			}
		});
		this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, Turtle.class, 10, true, false, Turtle.BABY_ON_LAND_SELECTOR) {
			@Override
			public boolean canUse() {
				return super.canUse() && !hasPregnancyPain();		
			}
			@Override
			public boolean canContinueToUse() {
				return super.canContinueToUse() && !hasPregnancyPain();			   
			}
		});	
		this.goalSelector.addGoal(7, new AbstractZombieGirl.ZombieGirlAttackTurtleEggGoal(this, 1.0, 3) {
			@Override
			public boolean canUse() {
				return super.canUse() && !hasPregnancyPain();		
			}
			@Override
			public boolean canContinueToUse() {
				return super.canContinueToUse() && !hasPregnancyPain();			   
			}
		});
		this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8F) {
			@Override
			public boolean canUse() {
				return super.canUse() && !hasPregnancyPain();		
			}
		});
		this.goalSelector.addGoal(9, new WaterAvoidingRandomStrollGoal(this, 1.0) {
			@Override
			public boolean canUse() {
				return super.canUse() && !hasPregnancyPain();		
			}
			@Override
			public boolean canContinueToUse() {
				return super.canContinueToUse() && !hasPregnancyPain();			   
			}
		});
		this.goalSelector.addGoal(10, new RandomLookAroundGoal(this) {
			@Override
			public boolean canUse() {
				return super.canUse() && !hasPregnancyPain();		
			}
		});
	}
	
	@Override
	public boolean hasPregnancyPain() {
		return this.entityData.get(DATA_HAS_PREGNANCY_PAIN);
	}
	
	@Override
	public void setPregnancyPain(boolean value) {
		this.entityData.set(DATA_HAS_PREGNANCY_PAIN, value);
	}
	
	@Override
	public int getPregnancyPainTimer() {
		return this.pregnancyPainTimer;
	}

	@Override
	public void setPregnancyPainTimer(int tick) {
		this.pregnancyPainTimer = tick;
	}
	
	@Override
	public PregnancyStage getCurrentPregnancyStage() {
		return currentPregnanctStage;
	}

	@Override
	public PregnancyStage getLastPregnancyStage() {
		return maxPregnanctStage;
	}

	@Override
	public int getTotalDaysPassed() {
		return totalDaysPassed;
	}
}
