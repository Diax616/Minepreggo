package dev.dixmk.minepreggo.client.model.entity.preggo.zombie;

import dev.dixmk.minepreggo.client.animation.preggo.BellyInflation;
import dev.dixmk.minepreggo.client.animation.preggo.FetalMovementIntensity;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.HostilPregnantZombieGirl;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HostilPregnantZombieGirlModel {

	@OnlyIn(Dist.CLIENT)
	public static class MonsterZombieGirlP3Model extends AbstractHostilPregnantZombieGirlModel<HostilPregnantZombieGirl.MonsterZombieGirlP3> {
		public MonsterZombieGirlP3Model(ModelPart root) {
			super(root, new ZombieGirlAnimator.MonsterPregnantZombieGirlAnimator<>(root, BellyInflation.MEDIUM, FetalMovementIntensity.P3), PregnancyPhase.P3, false);
		}
	}

	@OnlyIn(Dist.CLIENT)
	public static class MonsterZombieGirlP5Model extends AbstractHostilPregnantZombieGirlModel<HostilPregnantZombieGirl.MonsterZombieGirlP5> {
		public MonsterZombieGirlP5Model(ModelPart root) {
			super(root, new ZombieGirlAnimator.MonsterPregnantZombieGirlAnimator<>(root, BellyInflation.MEDIUM, FetalMovementIntensity.P5), PregnancyPhase.P5, false);
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class MonsterZombieGirlP7Model extends AbstractHostilPregnantZombieGirlModel<HostilPregnantZombieGirl.MonsterZombieGirlP7> {
		public MonsterZombieGirlP7Model(ModelPart root) {
			super(root, new ZombieGirlAnimator.MonsterPregnantZombieGirlAnimator<>(root, BellyInflation.HIGH, FetalMovementIntensity.P7), PregnancyPhase.P3, false);
		}
	}
}
