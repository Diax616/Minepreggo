package dev.dixmk.minepreggo.client.model.entity.preggo.zombie;

import dev.dixmk.minepreggo.client.animation.preggo.BellyInflation;
import dev.dixmk.minepreggo.client.animation.preggo.FetalMovementIntensity;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirlP3;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TamableZombieGirlP3Model extends AbstractTamablePregnantZombieGirlModel<TamableZombieGirlP3> {
	
	public TamableZombieGirlP3Model(ModelPart root) {
		super(root, new ZombieGirlAnimator.TamablePregnantZombieGirlAnimator<>(root, BellyInflation.MEDIUM, FetalMovementIntensity.P3));
	}
}
