package dev.dixmk.minepreggo.client.model.entity.preggo.creeper;

import dev.dixmk.minepreggo.client.animation.preggo.BellyInflation;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableHumanoidCreeperGirlP0;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TamableHumanoidCreeperGirlP0Model extends AbstractTamableHumanoidPregnantCreeperGirlModel<TamableHumanoidCreeperGirlP0> {
	
	public TamableHumanoidCreeperGirlP0Model(ModelPart root) {
		super(root, new HumanoidCreeperGirlAnimator.TamablePregnantHumanoidCreeperGirlAnimator<>(root, BellyInflation.LOW, null));
	}
}
