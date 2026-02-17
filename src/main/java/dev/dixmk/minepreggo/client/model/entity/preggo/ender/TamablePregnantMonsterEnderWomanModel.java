package dev.dixmk.minepreggo.client.model.entity.preggo.ender;

import dev.dixmk.minepreggo.client.animation.preggo.BellyInflation;
import dev.dixmk.minepreggo.client.animation.preggo.FetalMovementIntensity;
import dev.dixmk.minepreggo.world.entity.preggo.ender.TamablePregnantMonsterEnderWoman;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TamablePregnantMonsterEnderWomanModel {

	@OnlyIn(Dist.CLIENT)
	public static class TamableMonsterEnderWomanP0Model extends AbstractTamablePregnantMonsterEnderWomanModel<TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP0> {	
		public TamableMonsterEnderWomanP0Model(ModelPart root) {
			super(root, new MonsterEnderWomanAnimator.TamablePregnantMonsterEnderWomanAnimator<>(root, BellyInflation.LOW, null), PregnancyPhase.P0, true);
		}
	}

	@OnlyIn(Dist.CLIENT)
	public static class TamableMonsterEnderWomanP1Model extends AbstractTamablePregnantMonsterEnderWomanModel<TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP1> {
		
		public TamableMonsterEnderWomanP1Model(ModelPart root) {
			super(root, new MonsterEnderWomanAnimator.TamablePregnantMonsterEnderWomanAnimator<>(root, BellyInflation.LOW, null), PregnancyPhase.P1, true);
		}
	}

	@OnlyIn(Dist.CLIENT)
	public static class TamableMonsterEnderWomanP2Model extends AbstractTamablePregnantMonsterEnderWomanModel<TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP2> {

		public TamableMonsterEnderWomanP2Model(ModelPart root) {
			super(root, new MonsterEnderWomanAnimator.TamablePregnantMonsterEnderWomanAnimator<>(root, BellyInflation.LOW, null), PregnancyPhase.P2, true);
		}	
	}

	@OnlyIn(Dist.CLIENT)
	public static class TamableMonsterEnderWomanP3Model extends AbstractTamablePregnantMonsterEnderWomanModel<TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP3> {
		
		public TamableMonsterEnderWomanP3Model(ModelPart root) {
			super(root, new MonsterEnderWomanAnimator.TamablePregnantMonsterEnderWomanAnimator<>(root, BellyInflation.MEDIUM, FetalMovementIntensity.P3), PregnancyPhase.P3, false);
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class TamableMonsterEnderWomanP4Model extends AbstractTamablePregnantMonsterEnderWomanModel<TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP4> {
		public TamableMonsterEnderWomanP4Model(ModelPart root) {
			super(root, new MonsterEnderWomanAnimator.TamablePregnantMonsterEnderWomanAnimator<>(root, BellyInflation.MEDIUM, FetalMovementIntensity.P4), PregnancyPhase.P4, false);
		}
	}

	@OnlyIn(Dist.CLIENT)
	public static class TamableMonsterEnderWomanP5Model extends AbstractTamablePregnantMonsterEnderWomanModel<TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP5> {
		
		public TamableMonsterEnderWomanP5Model(ModelPart root) {
			super(root, new MonsterEnderWomanAnimator.TamablePregnantMonsterEnderWomanAnimator<>(root, BellyInflation.MEDIUM, FetalMovementIntensity.P5), PregnancyPhase.P5, false);
		}
	}

	@OnlyIn(Dist.CLIENT)
	public static class TamableMonsterEnderWomanP6Model extends AbstractTamablePregnantMonsterEnderWomanModel<TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP6> {

		public TamableMonsterEnderWomanP6Model(ModelPart root) {
			super(root, new MonsterEnderWomanAnimator.TamablePregnantMonsterEnderWomanAnimator<>(root, BellyInflation.MEDIUM, FetalMovementIntensity.P6), PregnancyPhase.P6, false);	
		}
	}

	@OnlyIn(Dist.CLIENT)
	public static class TamableMonsterEnderWomanP7Model extends AbstractTamablePregnantMonsterEnderWomanModel<TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP7> {
		
		public TamableMonsterEnderWomanP7Model(ModelPart root) {
			super(root, new MonsterEnderWomanAnimator.TamablePregnantMonsterEnderWomanAnimator<>(root, BellyInflation.HIGH, FetalMovementIntensity.P7), PregnancyPhase.P7, false);
		}
	}

	@OnlyIn(Dist.CLIENT)
	public static class TamableMonsterEnderWomanP8Model extends AbstractTamablePregnantMonsterEnderWomanModel<TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP8> {
		
		public TamableMonsterEnderWomanP8Model(ModelPart root) {
			super(root, new MonsterEnderWomanAnimator.TamablePregnantMonsterEnderWomanAnimator<>(root, BellyInflation.HIGH, FetalMovementIntensity.P8), PregnancyPhase.P8, false);
		}
	}
}
