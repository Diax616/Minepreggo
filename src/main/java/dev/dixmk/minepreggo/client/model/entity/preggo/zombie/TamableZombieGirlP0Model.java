package dev.dixmk.minepreggo.client.model.entity.preggo.zombie;

import dev.dixmk.minepreggo.client.animation.preggo.BellyInflation;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirlP0;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TamableZombieGirlP0Model extends AbstractTamablePregnantZombieGirlModel<TamableZombieGirlP0> {
	
	public TamableZombieGirlP0Model(ModelPart root) {
		super(root, new ZombieGirlAnimator.TamablePregnantZombieGirlAnimator<>(root, BellyInflation.LOW, null), PregnancyPhase.P0, true);
	}
}

