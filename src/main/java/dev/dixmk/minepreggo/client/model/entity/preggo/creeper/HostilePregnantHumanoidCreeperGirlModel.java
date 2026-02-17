package dev.dixmk.minepreggo.client.model.entity.preggo.creeper;

import dev.dixmk.minepreggo.client.animation.preggo.BellyInflation;
import dev.dixmk.minepreggo.client.animation.preggo.FetalMovementIntensity;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.HostilePregnantHumanoidCreeperGirl;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HostilePregnantHumanoidCreeperGirlModel {

	@OnlyIn(Dist.CLIENT)
	public static class MonsterHumanoidCreeperGirlP3Model extends AbstractHostilePregnantHumanoidCreeperGirlModel<HostilePregnantHumanoidCreeperGirl.MonsterHumanoidCreeperGirlP3> {
		public MonsterHumanoidCreeperGirlP3Model(ModelPart root) {
			super(root, new HumanoidCreeperGirlAnimator.MonsterPregnantHumanoidCreeperGirlAnimator<>(root, BellyInflation.LOW, FetalMovementIntensity.P3), PregnancyPhase.P3, false);
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class MonsterHumanoidCreeperGirlP5Model extends AbstractHostilePregnantHumanoidCreeperGirlModel<HostilePregnantHumanoidCreeperGirl.MonsterHumanoidCreeperGirlP5> {
		public MonsterHumanoidCreeperGirlP5Model(ModelPart root) {
			super(root, new HumanoidCreeperGirlAnimator.MonsterPregnantHumanoidCreeperGirlAnimator<>(root, BellyInflation.MEDIUM, FetalMovementIntensity.P5), PregnancyPhase.P5, false);
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class MonsterHumanoidCreeperGirlP7Model extends AbstractHostilePregnantHumanoidCreeperGirlModel<HostilePregnantHumanoidCreeperGirl.MonsterHumanoidCreeperGirlP7> {
		public MonsterHumanoidCreeperGirlP7Model(ModelPart root) {
			super(root, new HumanoidCreeperGirlAnimator.MonsterPregnantHumanoidCreeperGirlAnimator<>(root, BellyInflation.HIGH, FetalMovementIntensity.P7), PregnancyPhase.P7, false);
		}
	}
	
}
