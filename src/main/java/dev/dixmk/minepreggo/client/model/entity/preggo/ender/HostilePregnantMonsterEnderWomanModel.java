package dev.dixmk.minepreggo.client.model.entity.preggo.ender;

import dev.dixmk.minepreggo.client.animation.preggo.BellyInflation;
import dev.dixmk.minepreggo.client.animation.preggo.FetalMovementIntensity;
import dev.dixmk.minepreggo.world.entity.preggo.ender.HostilePregnantMonsterEnderWoman;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HostilePregnantMonsterEnderWomanModel  {
	
	@OnlyIn(Dist.CLIENT)
	public static class MonsterEnderWomanP3 extends AbstractHostilePregnantMonsterEnderWomanModel<HostilePregnantMonsterEnderWoman.MonsterEnderWomanP3> {
		public MonsterEnderWomanP3(ModelPart root) {
			super(root, PregnancyPhase.P3, false, BellyInflation.LOW, FetalMovementIntensity.P3);
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class MonsterEnderWomanP5 extends AbstractHostilePregnantMonsterEnderWomanModel<HostilePregnantMonsterEnderWoman.MonsterEnderWomanP5> {
		public MonsterEnderWomanP5(ModelPart root) {
			super(root, PregnancyPhase.P5, false, BellyInflation.MEDIUM, FetalMovementIntensity.P5);
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class MonsterEnderWomanP7 extends AbstractHostilePregnantMonsterEnderWomanModel<HostilePregnantMonsterEnderWoman.MonsterEnderWomanP7> {
		public MonsterEnderWomanP7(ModelPart root) {
			super(root, PregnancyPhase.P7, false, BellyInflation.HIGH, FetalMovementIntensity.P7);
		}
	}
}
