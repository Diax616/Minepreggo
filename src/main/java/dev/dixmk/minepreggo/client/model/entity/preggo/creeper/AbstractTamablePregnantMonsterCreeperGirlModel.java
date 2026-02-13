package dev.dixmk.minepreggo.client.model.entity.preggo.creeper;

import java.util.UUID;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.commons.lang3.tuple.ImmutablePair;

import dev.dixmk.minepreggo.client.animation.player.BellyAnimationManager;
import dev.dixmk.minepreggo.client.animation.preggo.BellyInflation;
import dev.dixmk.minepreggo.client.animation.preggo.FetalMovementIntensity;
import dev.dixmk.minepreggo.client.jiggle.EntityJiggleDataFactory;
import dev.dixmk.minepreggo.client.jiggle.EntityJiggleDataFactory.JigglePositionConfig;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractTamablePregnantCreeperGirl;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPain;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySymptom;
import dev.dixmk.minepreggo.client.animation.preggo.MonsterCreeperGirlAnimation;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.AnimationState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractTamablePregnantMonsterCreeperGirlModel 
	<E extends AbstractTamablePregnantCreeperGirl> extends AbstractTamableMonsterCreeperGirlModel<E> {
	protected float milkingBoobsXScale = 1.15F;
	protected float milkingBoobsYScale = 1.05F;
	protected float milkingBoobsZScale = 1.25F;
	protected float milkingBoobsYPos = -0.42F;
	private final BellyInflation bellyInflation;
	private final @Nullable FetalMovementIntensity fetalMovementIntensity;
		
	protected AbstractTamablePregnantMonsterCreeperGirlModel(ModelPart root, @Nonnull PregnancyPhase phase, boolean simpleBellyJiggle, BellyInflation bellyInflation, @Nullable FetalMovementIntensity fetalMovementIntensity) {
		super(root, ImmutablePair.of(phase, simpleBellyJiggle));
		this.bellyInflation = bellyInflation;
		this.fetalMovementIntensity = fetalMovementIntensity;
		this.belly.visible = true;
	}
	
	@Override
	protected JigglePositionConfig createJiggleConfig() {
		return EntityJiggleDataFactory.JigglePositionConfig.boobsAndBelly(this.boobs.y, this.belly.y);
	}
	
	@Override
	public void setupAnim(E creeperGirl, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setupAnim(creeperGirl, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		if (creeperGirl.getPregnancyData().getSyncedPregnancySymptoms().containsPregnancySymptom(PregnancySymptom.MILKING)) {
			this.boobs.y += milkingBoobsYPos;
			this.boobs.xScale = milkingBoobsXScale;
			this.boobs.zScale = milkingBoobsYScale;
			this.boobs.yScale = milkingBoobsZScale;			
		} 
	}
	
	@Override
	protected void animate(E creeperGirl, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		if (fetalMovementIntensity != null && creeperGirl.getPregnancyData().getPregnancyPain() ==  PregnancyPain.FETAL_MOVEMENT) {
			this.animate(creeperGirl.bellyAnimationState, fetalMovementIntensity.animation, ageInTicks);		    
		}
		else {
			this.animate(creeperGirl.bellyAnimationState, bellyInflation.animation, ageInTicks);		    
		}
	    	    	
		UUID preggoMobId = creeperGirl.getUUID();       
		if (BellyAnimationManager.getInstance().isAnimating(preggoMobId)) {
			AnimationState state = BellyAnimationManager.getInstance().getAnimationState(preggoMobId);
			AnimationDefinition animation = BellyAnimationManager.getInstance().getCurrentAnimation(preggoMobId);
		        
			if (state != null && animation != null) {
				this.animate(state, animation, ageInTicks);
			}
		} 
		
		final var pregnancyData = creeperGirl.getPregnancyData();
		final var pregnancyPain = pregnancyData.getPregnancyPain();	
		if (pregnancyPain == PregnancyPain.MORNING_SICKNESS) {
			this.animate(creeperGirl.loopAnimationState, MonsterCreeperGirlAnimation.MORNING_SICKNESS, ageInTicks);										
		}
		else if (pregnancyPain == PregnancyPain.MISCARRIAGE) {
			this.animate(creeperGirl.loopAnimationState, MonsterCreeperGirlAnimation.MISCARRIAGE, ageInTicks);						
		}
		else if (pregnancyPain == PregnancyPain.PREBIRTH) {
			this.animate(creeperGirl.loopAnimationState, MonsterCreeperGirlAnimation.PREBIRTH, ageInTicks);						
		}
		else if (pregnancyPain == PregnancyPain.BIRTH) {
			this.animate(creeperGirl.loopAnimationState, MonsterCreeperGirlAnimation.BIRTH, ageInTicks);						
		}
		else if (pregnancyPain == PregnancyPain.CONTRACTION) {
			this.animate(creeperGirl.loopAnimationState, MonsterCreeperGirlAnimation.CONTRACTION, ageInTicks);						
		}
		
		super.animate(creeperGirl, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
	}
}
