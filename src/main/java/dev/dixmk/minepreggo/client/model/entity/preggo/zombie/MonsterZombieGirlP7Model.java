package dev.dixmk.minepreggo.client.model.entity.preggo.zombie;

import dev.dixmk.minepreggo.client.animation.preggo.BellyInflation;
import dev.dixmk.minepreggo.client.animation.preggo.FetalMovementIntensity;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.MonsterZombieGirlP7;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MonsterZombieGirlP7Model extends AbstractMonsterPregnantZombieGirlModel<MonsterZombieGirlP7> {
	public MonsterZombieGirlP7Model(ModelPart root) {
		super(root, new ZombieGirlAnimator.MonsterPregnantZombieGirlAnimator<>(root, BellyInflation.HIGH, FetalMovementIntensity.P7));
	}
}
