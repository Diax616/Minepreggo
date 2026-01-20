package dev.dixmk.minepreggo.client.model.entity.preggo.zombie;

import dev.dixmk.minepreggo.client.animation.preggo.BellyInflation;
import dev.dixmk.minepreggo.client.animation.preggo.FetalMovementIntensity;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirlP8;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TamableZombieGirlP8Model extends AbstractTamablePregnantZombieGirlModel<TamableZombieGirlP8> {
	
	public TamableZombieGirlP8Model(ModelPart root) {
		super(root, new ZombieGirlAnimator.TamablePregnantZombieGirlAnimator<>(root, BellyInflation.HIGH, FetalMovementIntensity.P8), PregnancyPhase.P8, false);
	}
}
