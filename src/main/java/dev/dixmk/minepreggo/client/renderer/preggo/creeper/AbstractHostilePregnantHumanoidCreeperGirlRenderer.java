package dev.dixmk.minepreggo.client.renderer.preggo.creeper;

import javax.annotation.Nullable;

import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractHostilePregnantHumanoidCreeperGirlModel;
import dev.dixmk.minepreggo.client.renderer.entity.layer.ExpressiveFaceLayer;
import dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.creeper.HostilePregnantHumanoidCreeperGirlExpressionLayer;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractHostilePregnantHumanoidCreeperGirl;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractHostilePregnantHumanoidCreeperGirlRenderer 
	<E extends AbstractHostilePregnantHumanoidCreeperGirl, M extends AbstractHostilePregnantHumanoidCreeperGirlModel<E>> extends AbstractHumanoidCreeperGirlRenderer<E, M> {

	protected AbstractHostilePregnantHumanoidCreeperGirlRenderer(Context context, M main, M inner, M outter, M layer) {
		super(context, main, inner, outter, layer);
	}
	
	@Override
	protected @Nullable ExpressiveFaceLayer<E, M> createExpressiveFaceLayer() {
		return new HostilePregnantHumanoidCreeperGirlExpressionLayer<>(this);
	}
}
