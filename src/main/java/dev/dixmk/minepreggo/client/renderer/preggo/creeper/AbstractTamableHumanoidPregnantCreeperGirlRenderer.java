package dev.dixmk.minepreggo.client.renderer.preggo.creeper;

import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractTamableHumanoidPregnantCreeperGirlModel;
import dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.creeper.HumanoidTamablePregnantCreeperGirlExpressionLayer;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractTamablePregnantHumanoidCreeperGirl;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractTamableHumanoidPregnantCreeperGirlRenderer 	
	<E extends AbstractTamablePregnantHumanoidCreeperGirl<?,?>, M extends AbstractTamableHumanoidPregnantCreeperGirlModel<E>> extends AbstractHumanoidCreeperGirlRenderer<E, M> {

	protected AbstractTamableHumanoidPregnantCreeperGirlRenderer(Context context, M main, M inner, M outter, M layer) {
		super(context, main, inner, outter, layer);
		this.addFacialExpresions();
	}

	
	protected void addFacialExpresions() {
		this.addLayer(new HumanoidTamablePregnantCreeperGirlExpressionLayer<>(this));
	}
}
