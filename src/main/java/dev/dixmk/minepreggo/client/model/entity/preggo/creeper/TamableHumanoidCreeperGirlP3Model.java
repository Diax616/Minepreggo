package dev.dixmk.minepreggo.client.model.entity.preggo.creeper;

import dev.dixmk.minepreggo.client.animation.preggo.BellyInflation;
import dev.dixmk.minepreggo.client.animation.preggo.FetalMovementIntensity;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableHumanoidCreeperGirlP3;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TamableHumanoidCreeperGirlP3Model extends AbstractTamableHumanoidPregnantCreeperGirlModel<TamableHumanoidCreeperGirlP3> {
	
	public TamableHumanoidCreeperGirlP3Model(ModelPart root) {
		super(root, new HumanoidCreeperGirlAnimator.TamablePregnantHumanoidCreeperGirlAnimator<>(root, BellyInflation.MEDIUM, FetalMovementIntensity.P3));
	}
}
