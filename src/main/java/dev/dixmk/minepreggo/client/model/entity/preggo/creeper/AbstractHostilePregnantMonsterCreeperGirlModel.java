package dev.dixmk.minepreggo.client.model.entity.preggo.creeper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import dev.dixmk.minepreggo.client.animation.preggo.BellyInflation;
import dev.dixmk.minepreggo.client.animation.preggo.FetalMovementIntensity;
import dev.dixmk.minepreggo.client.animation.preggo.MonsterCreeperGirlAnimation;
import dev.dixmk.minepreggo.client.jiggle.EntityJiggleDataFactory;
import dev.dixmk.minepreggo.client.jiggle.EntityJiggleDataFactory.JigglePositionConfig;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractHostilePregnantCreeperGirl;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractHostilePregnantMonsterCreeperGirlModel
	<E extends AbstractHostilePregnantCreeperGirl> extends AbstractHostileMonsterCreeperGirlModel<E> {
	private final BellyInflation bellyInflation;
	private final @Nullable FetalMovementIntensity fetalMovementIntensity;
		
	protected AbstractHostilePregnantMonsterCreeperGirlModel(ModelPart root, @Nonnull PregnancyPhase phase, boolean simpleBellyJiggle, BellyInflation bellyInflation, @Nullable FetalMovementIntensity fetalMovementIntensity) {
		super(root, phase, simpleBellyJiggle);	
		this.belly.visible = true;
		this.bellyInflation = bellyInflation;
		this.fetalMovementIntensity = fetalMovementIntensity;
	}
	
	@Override
	protected JigglePositionConfig createJiggleConfig() {
		return EntityJiggleDataFactory.JigglePositionConfig.boobsAndBelly(this.boobs.y, this.belly.y);
	}
	
	@Override
	protected void animate(E creeperGirl, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {		
		if (fetalMovementIntensity != null && creeperGirl.getPregnancyData().isIncapacitated()) {
			this.animate(creeperGirl.loopAnimationState, fetalMovementIntensity.animation, ageInTicks);		    
		}
		else {
			this.animate(creeperGirl.loopAnimationState, bellyInflation.animation, ageInTicks);		    
		}
		
		if (creeperGirl.getPregnancyData().isIncapacitated()) {
			this.animate(creeperGirl.loopAnimationState, MonsterCreeperGirlAnimation.CONTRACTION, ageInTicks);	
		}
		
		super.animate(creeperGirl, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
	}
}
