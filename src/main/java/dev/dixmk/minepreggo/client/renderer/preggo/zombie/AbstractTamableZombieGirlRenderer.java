package dev.dixmk.minepreggo.client.renderer.preggo.zombie;

import dev.dixmk.minepreggo.client.model.entity.preggo.zombie.AbstractTamableZombieGirlModel;
import dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.zombie.TamableZombieGirlExpressionLayer;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.AbstractTamableZombieGirl;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractTamableZombieGirlRenderer
	<E extends AbstractTamableZombieGirl, M extends AbstractTamableZombieGirlModel<E>> extends AbstractZombieGirlRenderer<E, M> {

	protected AbstractTamableZombieGirlRenderer(Context context, M main, M inner, M outter) {
		super(context, main, inner, outter);
		this.addLayer(this.createExpressionLayer());
	}

	protected TamableZombieGirlExpressionLayer<E, M> createExpressionLayer() {
		return new TamableZombieGirlExpressionLayer<>(this);
	}
}
