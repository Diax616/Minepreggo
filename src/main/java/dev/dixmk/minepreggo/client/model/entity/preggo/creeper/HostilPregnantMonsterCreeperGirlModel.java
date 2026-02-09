package dev.dixmk.minepreggo.client.model.entity.preggo.creeper;

import dev.dixmk.minepreggo.client.animation.preggo.BellyInflation;
import dev.dixmk.minepreggo.client.animation.preggo.FetalMovementIntensity;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.HostilPregnantMonsterCreeperGirl;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HostilPregnantMonsterCreeperGirlModel {

	@OnlyIn(Dist.CLIENT)
	public static class MonsterCreeperGirlP3 extends AbstractHostilPregnantMonsterCreeperGirlModel<HostilPregnantMonsterCreeperGirl.MonsterCreeperGirlP3> {
		public MonsterCreeperGirlP3(ModelPart root) {
			super(root, PregnancyPhase.P3, false, BellyInflation.LOW, FetalMovementIntensity.P3);
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class MonsterCreeperGirlP5 extends AbstractHostilPregnantMonsterCreeperGirlModel<HostilPregnantMonsterCreeperGirl.MonsterCreeperGirlP5> {
		public MonsterCreeperGirlP5(ModelPart root) {
			super(root, PregnancyPhase.P5, false, BellyInflation.MEDIUM, FetalMovementIntensity.P5);
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class MonsterCreeperGirlP7 extends AbstractHostilPregnantMonsterCreeperGirlModel<HostilPregnantMonsterCreeperGirl.MonsterCreeperGirlP7> {
		public MonsterCreeperGirlP7(ModelPart root) {
			super(root, PregnancyPhase.P7, false, BellyInflation.HIGH, FetalMovementIntensity.P7);
		}
	}
}
