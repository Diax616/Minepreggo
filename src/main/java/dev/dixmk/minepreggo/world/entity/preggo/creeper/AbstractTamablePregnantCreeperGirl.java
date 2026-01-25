package dev.dixmk.minepreggo.world.entity.preggo.creeper;

import javax.annotation.Nonnull;

import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.client.animation.player.BellyAnimationManager;
import dev.dixmk.minepreggo.client.animation.preggo.BellyAnimation;
import dev.dixmk.minepreggo.init.MinepreggoModDamageSources;
import dev.dixmk.minepreggo.init.MinepreggoModSounds;
import dev.dixmk.minepreggo.world.entity.BellyPartFactory;
import dev.dixmk.minepreggo.world.entity.BellyPartManager;
import dev.dixmk.minepreggo.world.entity.ai.goal.PreggoMobAIHelper;
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
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
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
	}
	
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
		PreggoMobAIHelper.setTamablePregnantCreeperGirlGoals(this);
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
			PreggoMobHelper.spawnBabyAndFetusCreepers(this, bellyBurst);
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
				&& MinepreggoModConfig.isBellyColisionsForPreggoMobsEnable()
				&& pregnancyData.getCurrentPregnancyPhase().compareTo(PregnancyPhase.P4) >= 0) {
			BellyPartManager.getInstance().onServerTick(this, () -> BellyPartFactory.createHumanoidBellyPart(this, pregnancyData.getCurrentPregnancyPhase()));
		}
	}
	
	@Override
	public SoundEvent getDeathSound() {
		return MinepreggoModSounds.PREGNANT_PREGGO_MOB_DEATH.get();
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
	public boolean causeFallDamage(float pFallDistance, float pMultiplier, DamageSource pSource) {
		PregnancyPhase currentPregnancyPhase = this.pregnancyData.getCurrentPregnancyPhase();
		if (currentPregnancyPhase.compareTo(PregnancyPhase.P3) >= 0) {
			return super.causeFallDamage(pFallDistance, pMultiplier * PregnancySystemHelper.calculateExtraFallDamageMultiplier(currentPregnancyPhase), pSource);
		}
		return super.causeFallDamage(pFallDistance, pMultiplier, pSource);
	}
}

