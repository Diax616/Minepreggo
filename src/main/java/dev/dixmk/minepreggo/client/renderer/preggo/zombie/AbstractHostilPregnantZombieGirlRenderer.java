package dev.dixmk.minepreggo.client.renderer.preggo.zombie;

import dev.dixmk.minepreggo.client.model.entity.preggo.zombie.AbstractHostilPregnantZombieGirlModel;
import dev.dixmk.minepreggo.client.renderer.entity.layer.ExpressiveFaceLayer;
import dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.zombie.HostilPregnantZombieGirlExpressionLayer;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.AbstractHostilPregnantZombieGirl;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractHostilPregnantZombieGirlRenderer
	<E extends AbstractHostilPregnantZombieGirl, M extends AbstractHostilPregnantZombieGirlModel<E>> extends AbstractHostilZombieGirlRenderer<E, M> {

	protected AbstractHostilPregnantZombieGirlRenderer(Context context, M main, M inner, M outter) {
		super(context, main, inner, outter);
	}
	
	@Override
	protected ExpressiveFaceLayer<E, M> createExpressiveFaceLayer() {
		return new HostilPregnantZombieGirlExpressionLayer<>(this);
	}
}