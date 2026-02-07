package dev.dixmk.minepreggo.client.renderer.preggo.creeper;

import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractHostilHumanoidCreeperGirlModel;
import dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.creeper.HostilHumanoidCreeperGirlExpressionLayer;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractHostilHumanoidCreeperGirl;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractHostilHumanoidCreeperGirlRenderer 
	<E extends AbstractHostilHumanoidCreeperGirl, M extends AbstractHostilHumanoidCreeperGirlModel<E>> extends AbstractHumanoidCreeperGirlRenderer<E, M> {

	protected AbstractHostilHumanoidCreeperGirlRenderer(Context context, M main, M inner, M outter, M layer, boolean facialExpresion) {
		super(context, main, inner, outter, layer);
		if (facialExpresion) this.addFacialExpresions();
	}

	protected AbstractHostilHumanoidCreeperGirlRenderer(Context context, M main, M inner, M outter, M layer) {
		this(context, main, inner, outter, layer, true);
	}
	
	protected void addFacialExpresions() {
		this.addLayer(new HostilHumanoidCreeperGirlExpressionLayer<>(this));
	}
}
