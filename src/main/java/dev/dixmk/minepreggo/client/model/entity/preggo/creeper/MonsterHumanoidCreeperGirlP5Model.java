package dev.dixmk.minepreggo.client.model.entity.preggo.creeper;

import dev.dixmk.minepreggo.client.animation.preggo.BellyInflation;
import dev.dixmk.minepreggo.client.animation.preggo.FetalMovementIntensity;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.MonsterHumanoidCreeperGirlP5;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MonsterHumanoidCreeperGirlP5Model extends AbstractHumanoidMonsterPregnantCreeperGirlModel<MonsterHumanoidCreeperGirlP5> {
	public MonsterHumanoidCreeperGirlP5Model(ModelPart root) {
		super(root, new HumanoidCreeperGirlAnimator.MonsterPregnantHumanoidCreeperGirlAnimator<>(root, BellyInflation.MEDIUM, FetalMovementIntensity.P5));
	}
}