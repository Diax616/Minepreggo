package dev.dixmk.minepreggo.client.model.entity.preggo.creeper;

import dev.dixmk.minepreggo.client.animation.preggo.BellyInflation;
import dev.dixmk.minepreggo.client.animation.preggo.FetalMovementIntensity;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamablePregnantMonsterCreeperGirl;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TamablePregnantMonsterCreeperGirlModel {

	@OnlyIn(Dist.CLIENT)
	public static class TamableMonsterCreeperGirlP0Model extends AbstractTamablePregnantMonsterCreeperGirlModel<TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP0> {	
		public TamableMonsterCreeperGirlP0Model(ModelPart root) {
			super(root, PregnancyPhase.P0, true, BellyInflation.LOW, null);
		}
	}

	@OnlyIn(Dist.CLIENT)
	public static class TamableMonsterCreeperGirlP1Model extends AbstractTamablePregnantMonsterCreeperGirlModel<TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP1> {	
		public TamableMonsterCreeperGirlP1Model(ModelPart root) {
			super(root, PregnancyPhase.P1, true, BellyInflation.LOW, null);
		}
	}

	@OnlyIn(Dist.CLIENT)
	public static class TamableMonsterCreeperGirlP2Model extends AbstractTamablePregnantMonsterCreeperGirlModel<TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP2> {
		public TamableMonsterCreeperGirlP2Model(ModelPart root) {
			super(root, PregnancyPhase.P2, true, BellyInflation.LOW, null);
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class TamableMonsterCreeperGirlP3Model extends AbstractTamablePregnantMonsterCreeperGirlModel<TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP3> {	
		public TamableMonsterCreeperGirlP3Model(ModelPart root) {
			super(root, PregnancyPhase.P3, false, BellyInflation.MEDIUM, FetalMovementIntensity.P3);
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class TamableMonsterCreeperGirlP4Model extends AbstractTamablePregnantMonsterCreeperGirlModel<TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP4> {
		public TamableMonsterCreeperGirlP4Model(ModelPart root) {
			super(root, PregnancyPhase.P4, false, BellyInflation.MEDIUM, FetalMovementIntensity.P4);
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class TamableMonsterCreeperGirlP5Model extends AbstractTamablePregnantMonsterCreeperGirlModel<TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP5> {	
		public TamableMonsterCreeperGirlP5Model(ModelPart root) {
			super(root, PregnancyPhase.P5, false, BellyInflation.MEDIUM, FetalMovementIntensity.P5);
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class TamableMonsterCreeperGirlP6Model extends AbstractTamablePregnantMonsterCreeperGirlModel<TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP6> {	
		public TamableMonsterCreeperGirlP6Model(ModelPart root) {
			super(root, PregnancyPhase.P6, false, BellyInflation.MEDIUM, FetalMovementIntensity.P6);
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class TamableMonsterCreeperGirlP7Model extends AbstractTamablePregnantMonsterCreeperGirlModel<TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP7> {
		public TamableMonsterCreeperGirlP7Model(ModelPart root) {
			super(root, PregnancyPhase.P7, false, BellyInflation.HIGH, FetalMovementIntensity.P7);
		} 
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class TamableMonsterCreeperGirlP8Model extends AbstractTamablePregnantMonsterCreeperGirlModel<TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP8> {
		public TamableMonsterCreeperGirlP8Model(ModelPart root) {
			super(root, PregnancyPhase.P8, false, BellyInflation.HIGH, FetalMovementIntensity.P8);
		} 
	}	
}
