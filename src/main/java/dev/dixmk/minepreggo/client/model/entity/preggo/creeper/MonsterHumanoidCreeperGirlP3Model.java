package dev.dixmk.minepreggo.client.model.entity.preggo.creeper;
import dev.dixmk.minepreggo.client.animation.preggo.BellyInflation;
import dev.dixmk.minepreggo.client.animation.preggo.FetalMovementIntensity;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.MonsterHumanoidCreeperGirlP3;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MonsterHumanoidCreeperGirlP3Model extends AbstractHumanoidMonsterPregnantCreeperGirlModel<MonsterHumanoidCreeperGirlP3> {
	public MonsterHumanoidCreeperGirlP3Model(ModelPart root) {
		super(root, new HumanoidCreeperGirlAnimator.MonsterPregnantHumanoidCreeperGirlAnimator<>(root, BellyInflation.LOW, FetalMovementIntensity.P3), PregnancyPhase.P3, false);
	}
}