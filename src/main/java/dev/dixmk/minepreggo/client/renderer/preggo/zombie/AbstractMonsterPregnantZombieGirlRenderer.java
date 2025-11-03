package dev.dixmk.minepreggo.client.renderer.preggo.zombie;

import dev.dixmk.minepreggo.client.model.entity.preggo.zombie.AbstractMonsterPregnantZombieGirlModel;
import dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.zombie.MonsterPregnantZombieGirlExpressionLayer;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.AbstractMonsterPregnantZombieGirl;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractMonsterPregnantZombieGirlRenderer
	<E extends AbstractMonsterPregnantZombieGirl, M extends AbstractMonsterPregnantZombieGirlModel<E>> extends AbstractMonsterZombieGirlRenderer<E, M> {

	protected AbstractMonsterPregnantZombieGirlRenderer(Context context, M main, M inner, M outter) {
		super(context, main, inner, outter);
	}

	protected AbstractMonsterPregnantZombieGirlRenderer(Context context, M main, M inner, M outter, boolean facialExpresion) {
		super(context, main, inner, outter, true);
	}
	
	@Override
	protected void addFacialExpresions() {
		this.addLayer(new MonsterPregnantZombieGirlExpressionLayer<>(this));
	}
}