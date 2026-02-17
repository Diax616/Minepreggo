package dev.dixmk.minepreggo.client.model.entity.preggo.creeper;

import dev.dixmk.minepreggo.client.animation.preggo.BellyInflation;
import dev.dixmk.minepreggo.client.animation.preggo.FetalMovementIntensity;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamablePregnantHumanoidCreeperGirl;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TamablePregnantHumanoidCreeperGirlModel {

	@OnlyIn(Dist.CLIENT)
	public static class TamableHumanoidCreeperGirlP0Model extends AbstractTamablePregnantHumanoidCreeperGirlModel<TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP0> {	
		public TamableHumanoidCreeperGirlP0Model(ModelPart root) {
			super(root, new HumanoidCreeperGirlAnimator.TamablePregnantHumanoidCreeperGirlAnimator<>(root, BellyInflation.LOW, null), PregnancyPhase.P0, true);
		}
	}

	@OnlyIn(Dist.CLIENT)
	public static class TamableHumanoidCreeperGirlP1Model extends AbstractTamablePregnantHumanoidCreeperGirlModel<TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP1> {	
		public TamableHumanoidCreeperGirlP1Model(ModelPart root) {
			super(root, new HumanoidCreeperGirlAnimator.TamablePregnantHumanoidCreeperGirlAnimator<>(root, BellyInflation.LOW, null), PregnancyPhase.P1, true);
		}
	}

	@OnlyIn(Dist.CLIENT)
	public static class TamableHumanoidCreeperGirlP2Model extends AbstractTamablePregnantHumanoidCreeperGirlModel<TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP2> {
		public TamableHumanoidCreeperGirlP2Model(ModelPart root) {
			super(root, new HumanoidCreeperGirlAnimator.TamablePregnantHumanoidCreeperGirlAnimator<>(root, BellyInflation.LOW, null), PregnancyPhase.P2, true);
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class TamableHumanoidCreeperGirlP3Model extends AbstractTamablePregnantHumanoidCreeperGirlModel<TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP3> {	
		public TamableHumanoidCreeperGirlP3Model(ModelPart root) {
			super(root, new HumanoidCreeperGirlAnimator.TamablePregnantHumanoidCreeperGirlAnimator<>(root, BellyInflation.MEDIUM, FetalMovementIntensity.P3), PregnancyPhase.P3, false);
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class TamableHumanoidCreeperGirlP4Model extends AbstractTamablePregnantHumanoidCreeperGirlModel<TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP4> {
		public TamableHumanoidCreeperGirlP4Model(ModelPart root) {
			super(root, new HumanoidCreeperGirlAnimator.TamablePregnantHumanoidCreeperGirlAnimator<>(root, BellyInflation.MEDIUM, FetalMovementIntensity.P4), PregnancyPhase.P4, false);
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class TamableHumanoidCreeperGirlP5Model extends AbstractTamablePregnantHumanoidCreeperGirlModel<TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP5> {	
		public TamableHumanoidCreeperGirlP5Model(ModelPart root) {
			super(root, new HumanoidCreeperGirlAnimator.TamablePregnantHumanoidCreeperGirlAnimator<>(root, BellyInflation.MEDIUM, FetalMovementIntensity.P5), PregnancyPhase.P5, false);
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class TamableHumanoidCreeperGirlP6Model extends AbstractTamablePregnantHumanoidCreeperGirlModel<TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP6> {	
		public TamableHumanoidCreeperGirlP6Model(ModelPart root) {
			super(root, new HumanoidCreeperGirlAnimator.TamablePregnantHumanoidCreeperGirlAnimator<>(root, BellyInflation.MEDIUM, FetalMovementIntensity.P6), PregnancyPhase.P6, false);
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class TamableHumanoidCreeperGirlP7Model extends AbstractTamablePregnantHumanoidCreeperGirlModel<TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP7> {
		public TamableHumanoidCreeperGirlP7Model(ModelPart root) {
			super(root, new HumanoidCreeperGirlAnimator.TamablePregnantHumanoidCreeperGirlAnimator<>(root, BellyInflation.HIGH, FetalMovementIntensity.P7), PregnancyPhase.P7, false);
		} 
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class TamableHumanoidCreeperGirlP8Model extends AbstractTamablePregnantHumanoidCreeperGirlModel<TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP8> {
		public TamableHumanoidCreeperGirlP8Model(ModelPart root) {
			super(root, new HumanoidCreeperGirlAnimator.TamablePregnantHumanoidCreeperGirlAnimator<>(root, BellyInflation.HIGH, FetalMovementIntensity.P8), PregnancyPhase.P8, false);
		} 
	}
}
