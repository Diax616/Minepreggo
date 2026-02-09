package dev.dixmk.minepreggo.client.renderer.preggo.zombie;

import dev.dixmk.minepreggo.client.model.entity.preggo.zombie.AbstractHostilZombieGirlModel;
import dev.dixmk.minepreggo.client.renderer.entity.layer.ExpressiveFaceLayer;
import dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.zombie.HostilZombieGirlExpressionLayer;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.AbstractHostilZombieGirl;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractHostilZombieGirlRenderer
	<E extends AbstractHostilZombieGirl, M extends AbstractHostilZombieGirlModel<E>> extends AbstractZombieGirlRenderer<E, M> {

	protected AbstractHostilZombieGirlRenderer(Context context, M main, M inner, M outter) {
		super(context, main, inner, outter);
	}
	
	@Override
	protected ExpressiveFaceLayer<E, M> createExpressiveFaceLayer() {
		return new HostilZombieGirlExpressionLayer<>(this);
	}
}