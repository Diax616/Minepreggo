package dev.dixmk.minepreggo.client.model.entity.preggo.zombie;

import dev.dixmk.minepreggo.client.animation.preggo.BellyInflation;
import dev.dixmk.minepreggo.client.animation.preggo.FetalMovementIntensity;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamablePregnantZombieGirl;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TamablePregnantZombieGirlModel {

	@OnlyIn(Dist.CLIENT)
	public static class TamableZombieGirlP0Model extends AbstractTamablePregnantZombieGirlModel<TamablePregnantZombieGirl.TamableZombieGirlP0> {	
		public TamableZombieGirlP0Model(ModelPart root) {
			super(root, new ZombieGirlAnimator.TamablePregnantZombieGirlAnimator<>(root, BellyInflation.LOW, null), PregnancyPhase.P0, true);
		}
	}

	@OnlyIn(Dist.CLIENT)
	public static class TamableZombieGirlP1Model extends AbstractTamablePregnantZombieGirlModel<TamablePregnantZombieGirl.TamableZombieGirlP1> {
		
		public TamableZombieGirlP1Model(ModelPart root) {
			super(root, new ZombieGirlAnimator.TamablePregnantZombieGirlAnimator<>(root, BellyInflation.LOW, null), PregnancyPhase.P1, true);
		}
	}

	@OnlyIn(Dist.CLIENT)
	public static class TamableZombieGirlP2Model extends AbstractTamablePregnantZombieGirlModel<TamablePregnantZombieGirl.TamableZombieGirlP2> {

		public TamableZombieGirlP2Model(ModelPart root) {
			super(root, new ZombieGirlAnimator.TamablePregnantZombieGirlAnimator<>(root, BellyInflation.LOW, null), PregnancyPhase.P2, true);
		}	
	}

	@OnlyIn(Dist.CLIENT)
	public static class TamableZombieGirlP3Model extends AbstractTamablePregnantZombieGirlModel<TamablePregnantZombieGirl.TamableZombieGirlP3> {
		
		public TamableZombieGirlP3Model(ModelPart root) {
			super(root, new ZombieGirlAnimator.TamablePregnantZombieGirlAnimator<>(root, BellyInflation.MEDIUM, FetalMovementIntensity.P3), PregnancyPhase.P3, false);
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class TamableZombieGirlP4Model extends AbstractTamablePregnantZombieGirlModel<TamablePregnantZombieGirl.TamableZombieGirlP4> {
		public TamableZombieGirlP4Model(ModelPart root) {
			super(root, new ZombieGirlAnimator.TamablePregnantZombieGirlAnimator<>(root, BellyInflation.MEDIUM, FetalMovementIntensity.P4), PregnancyPhase.P4, false);
		}
	}

	@OnlyIn(Dist.CLIENT)
	public static class TamableZombieGirlP5Model extends AbstractTamablePregnantZombieGirlModel<TamablePregnantZombieGirl.TamableZombieGirlP5> {
		
		public TamableZombieGirlP5Model(ModelPart root) {
			super(root, new ZombieGirlAnimator.TamablePregnantZombieGirlAnimator<>(root, BellyInflation.MEDIUM, FetalMovementIntensity.P5), PregnancyPhase.P5, false);
		}
	}

	@OnlyIn(Dist.CLIENT)
	public static class TamableZombieGirlP6Model extends AbstractTamablePregnantZombieGirlModel<TamablePregnantZombieGirl.TamableZombieGirlP6> {

		public TamableZombieGirlP6Model(ModelPart root) {
			super(root, new ZombieGirlAnimator.TamablePregnantZombieGirlAnimator<>(root, BellyInflation.MEDIUM, FetalMovementIntensity.P6), PregnancyPhase.P6, false);	
		}
	}

	@OnlyIn(Dist.CLIENT)
	public static class TamableZombieGirlP7Model extends AbstractTamablePregnantZombieGirlModel<TamablePregnantZombieGirl.TamableZombieGirlP7> {
		
		public TamableZombieGirlP7Model(ModelPart root) {
			super(root, new ZombieGirlAnimator.TamablePregnantZombieGirlAnimator<>(root, BellyInflation.HIGH, FetalMovementIntensity.P7), PregnancyPhase.P7, false);
		}
	}

	@OnlyIn(Dist.CLIENT)
	public static class TamableZombieGirlP8Model extends AbstractTamablePregnantZombieGirlModel<TamablePregnantZombieGirl.TamableZombieGirlP8> {
		
		public TamableZombieGirlP8Model(ModelPart root) {
			super(root, new ZombieGirlAnimator.TamablePregnantZombieGirlAnimator<>(root, BellyInflation.HIGH, FetalMovementIntensity.P8), PregnancyPhase.P8, false);
		}
	}
}
