package dev.dixmk.minepreggo.client.model.entity.preggo.zombie;

import dev.dixmk.minepreggo.client.animation.preggo.BellyInflation;
import dev.dixmk.minepreggo.client.animation.preggo.FetalMovementIntensity;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.MonsterZombieGirlP5;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MonsterZombieGirlP5Model extends AbstractMonsterPregnantZombieGirlModel<MonsterZombieGirlP5> {
	public MonsterZombieGirlP5Model(ModelPart root) {
		super(root, new ZombieGirlAnimator.MonsterPregnantZombieGirlAnimator<>(root, BellyInflation.MEDIUM, FetalMovementIntensity.P5), PregnancyPhase.P5, false);
	}
}