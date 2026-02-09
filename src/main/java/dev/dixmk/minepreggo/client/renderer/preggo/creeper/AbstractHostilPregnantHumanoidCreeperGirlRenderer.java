package dev.dixmk.minepreggo.client.renderer.preggo.creeper;

import javax.annotation.Nullable;

import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractHostilPregnantHumanoidCreeperGirlModel;
import dev.dixmk.minepreggo.client.renderer.entity.layer.ExpressiveFaceLayer;
import dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.creeper.HostilPregnantHumanoidCreeperGirlExpressionLayer;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractHostilPregnantHumanoidCreeperGirl;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractHostilPregnantHumanoidCreeperGirlRenderer 
	<E extends AbstractHostilPregnantHumanoidCreeperGirl, M extends AbstractHostilPregnantHumanoidCreeperGirlModel<E>> extends AbstractHumanoidCreeperGirlRenderer<E, M> {

	protected AbstractHostilPregnantHumanoidCreeperGirlRenderer(Context context, M main, M inner, M outter, M layer) {
		super(context, main, inner, outter, layer);
	}
	
	@Override
	protected @Nullable ExpressiveFaceLayer<E, M> createExpressiveFaceLayer() {
		return new HostilPregnantHumanoidCreeperGirlExpressionLayer<>(this);
	}
}
