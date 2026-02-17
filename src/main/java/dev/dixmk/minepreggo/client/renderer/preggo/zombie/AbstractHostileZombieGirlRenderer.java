package dev.dixmk.minepreggo.client.renderer.preggo.zombie;

import dev.dixmk.minepreggo.client.model.entity.preggo.zombie.AbstractHostileZombieGirlModel;
import dev.dixmk.minepreggo.client.renderer.entity.layer.ExpressiveFaceLayer;
import dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.zombie.HostileZombieGirlExpressionLayer;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.AbstractHostileZombieGirl;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractHostileZombieGirlRenderer
	<E extends AbstractHostileZombieGirl, M extends AbstractHostileZombieGirlModel<E>> extends AbstractZombieGirlRenderer<E, M> {

	protected AbstractHostileZombieGirlRenderer(Context context, M main, M inner, M outter) {
		super(context, main, inner, outter);
	}
	
	@Override
	protected ExpressiveFaceLayer<E, M> createExpressiveFaceLayer() {
		return new HostileZombieGirlExpressionLayer<>(this);
	}
}