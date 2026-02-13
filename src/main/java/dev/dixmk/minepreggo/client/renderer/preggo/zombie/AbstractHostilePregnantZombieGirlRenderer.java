package dev.dixmk.minepreggo.client.renderer.preggo.zombie;

import dev.dixmk.minepreggo.client.model.entity.preggo.zombie.AbstractHostilePregnantZombieGirlModel;
import dev.dixmk.minepreggo.client.renderer.entity.layer.ExpressiveFaceLayer;
import dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.zombie.HostilePregnantZombieGirlExpressionLayer;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.AbstractHostilePregnantZombieGirl;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractHostilePregnantZombieGirlRenderer
	<E extends AbstractHostilePregnantZombieGirl, M extends AbstractHostilePregnantZombieGirlModel<E>> extends AbstractHostileZombieGirlRenderer<E, M> {

	protected AbstractHostilePregnantZombieGirlRenderer(Context context, M main, M inner, M outter) {
		super(context, main, inner, outter);
	}
	
	@Override
	protected ExpressiveFaceLayer<E, M> createExpressiveFaceLayer() {
		return new HostilePregnantZombieGirlExpressionLayer<>(this);
	}
}