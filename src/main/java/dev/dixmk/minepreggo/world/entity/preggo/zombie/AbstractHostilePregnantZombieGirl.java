package dev.dixmk.minepreggo.world.entity.preggo.zombie;

import javax.annotation.Nullable;

import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.init.MinepreggoDamageSources;
import dev.dixmk.minepreggo.init.MinepreggoSounds;
import dev.dixmk.minepreggo.world.entity.BellyPartFactory;
import dev.dixmk.minepreggo.world.entity.BellyPartManager;
import dev.dixmk.minepreggo.world.entity.LivingEntityHelper;
import dev.dixmk.minepreggo.world.entity.preggo.IHostilePreggoMobPregnancyData;
import dev.dixmk.minepreggo.world.entity.preggo.IHostilePregnantPreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.HostilePregnantPreggoMobDataImpl;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;
import dev.dixmk.minepreggo.world.item.ItemHelper;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
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
import net.minecraft.world.level.ServerLevelAccessor;

public abstract class AbstractHostilePregnantZombieGirl extends AbstractHostileZombieGirl implements IHostilePregnantPreggoMob {

	private static final HostilePregnantPreggoMobDataImpl.DataAccessor<AbstractHostilePregnantZombieGirl> DATA_ACCESOR = new HostilePregnantPreggoMobDataImpl.DataAccessor<>(AbstractHostilePregnantZombieGirl.class);
	private final IHostilePreggoMobPregnancyData pregnancyData;
	
	protected AbstractHostilePregnantZombieGirl(EntityType<? extends AbstractHostileZombieGirl> entityType, Level level, PregnancyPhase currentPregnancyStage) {
		super(entityType, level);
		pregnancyData = new HostilePregnantPreggoMobDataImpl<>(DATA_ACCESOR, this, currentPregnancyStage);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		DATA_ACCESOR.defineSynchedData(this);
	}
	
	@Override
	public IHostilePreggoMobPregnancyData getPregnancyData() {
		return this.pregnancyData;
	}
	
	@Override
	public void addAdditionalSaveData(CompoundTag compoundTag) {
		super.addAdditionalSaveData(compoundTag);
		compoundTag.put("HostilePregnantZombieGirlData", pregnancyData.serializeNBT());
	}
	
	@Override
	public void readAdditionalSaveData(CompoundTag compoundTag) {
		super.readAdditionalSaveData(compoundTag);	
		if (compoundTag.contains("HostilePregnantZombieGirlData")) {
			pregnancyData.deserializeNBT(compoundTag.getCompound("HostilePregnantZombieGirlData"));
		}
	}
	
	@Override
	public void die(DamageSource source) {
		super.die(source);		
		if (!this.level().isClientSide) {
			if (source.is(MinepreggoDamageSources.BELLY_BURST)) {
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
			LivingEntityHelper.playSoundNearTo(this, MinepreggoSounds.getRandomStomachGrowls(random));
		}
		return result;
	}
	
	@Override
	public SoundEvent getDeathSound() {
		return MinepreggoSounds.PREGNANT_PREGGO_MOB_DEATH.get();
	}
	
	@Override
	protected boolean canReplaceCurrentItem(ItemStack candidate, ItemStack existing) {	
		if (!PregnancySystemHelper.canUseChestplate(candidate.getItem(), this.pregnancyData.getCurrentPregnancyPhase())
					|| !PregnancySystemHelper.canUseLegging(candidate.getItem(), this.pregnancyData.getCurrentPregnancyPhase())) {
			return false;
		}	
		return super.canReplaceCurrentItem(candidate, existing);
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
		}.setAlertOthers(AbstractHostileZombieGirl.class));	
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
	
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData, @Nullable CompoundTag dataTag) {
		SpawnGroupData spawnData = super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData, dataTag);
		ItemStack chestplate = this.getItemBySlot(EquipmentSlot.CHEST);
		ItemStack legging = this.getItemBySlot(EquipmentSlot.LEGS);
		PregnancyPhase currentPregnancyPhase = pregnancyData.getCurrentPregnancyPhase();
		if (!chestplate.isEmpty() && !PregnancySystemHelper.canUseChestplate(chestplate.getItem(), currentPregnancyPhase)) {
			ItemStack maternityChestplate = ItemHelper.createMaternityChestplateByVanillaChestplate(chestplate, currentPregnancyPhase);
			ItemHelper.copyEnchantments(chestplate, maternityChestplate);
			this.setItemSlot(EquipmentSlot.CHEST, maternityChestplate);
		}
		if (!legging.isEmpty() && !PregnancySystemHelper.canUseLegging(legging.getItem(), currentPregnancyPhase)) {
			ItemStack maternityLegging = ItemHelper.createKneeBraceByVanillaLeggings(legging);
			ItemHelper.copyEnchantments(legging, maternityLegging);
			this.setItemSlot(EquipmentSlot.LEGS, maternityLegging);
		}
		this.getPregnancyData().init();
		PregnancySystemHelper.applyGravityModifier(this, currentPregnancyPhase);
		PregnancySystemHelper.applyKnockbackResistanceModifier(this, currentPregnancyPhase);
		return spawnData;
	}
}
