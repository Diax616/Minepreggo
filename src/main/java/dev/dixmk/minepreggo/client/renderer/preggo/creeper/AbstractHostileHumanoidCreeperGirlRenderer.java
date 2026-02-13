package dev.dixmk.minepreggo.client.renderer.preggo.creeper;

import javax.annotation.Nullable;

import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractHostileHumanoidCreeperGirlModel;
import dev.dixmk.minepreggo.client.renderer.entity.layer.ExpressiveFaceLayer;
import dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.creeper.HostileHumanoidCreeperGirlExpressionLayer;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractHostileHumanoidCreeperGirl;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractHostileHumanoidCreeperGirlRenderer 
	<E extends AbstractHostileHumanoidCreeperGirl, M extends AbstractHostileHumanoidCreeperGirlModel<E>> extends AbstractHumanoidCreeperGirlRenderer<E, M> {

	protected AbstractHostileHumanoidCreeperGirlRenderer(Context context, M main, M inner, M outter, M layer) {
		super(context, main, inner, outter, layer);
	}

	@Override
	protected @Nullable ExpressiveFaceLayer<E, M> createExpressiveFaceLayer() {
		return new HostileHumanoidCreeperGirlExpressionLayer<>(this);
	}
}
