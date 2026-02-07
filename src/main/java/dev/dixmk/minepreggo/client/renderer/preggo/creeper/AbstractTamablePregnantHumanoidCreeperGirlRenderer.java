package dev.dixmk.minepreggo.client.renderer.preggo.creeper;

import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractTamablePregnantHumanoidCreeperGirlModel;
import dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.creeper.TamableHumanoidCreeperGirlExpressionLayer;
import dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.creeper.TamableHumanoidPregnantCreeperGirlExpressionLayer;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractTamablePregnantCreeperGirl;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractTamablePregnantHumanoidCreeperGirlRenderer 	
	<E extends AbstractTamablePregnantCreeperGirl, M extends AbstractTamablePregnantHumanoidCreeperGirlModel<E>> extends AbstractTamableHumanoidCreeperGirlRenderer<E, M> {

	protected AbstractTamablePregnantHumanoidCreeperGirlRenderer(Context context, M main, M inner, M outter, M layer) {
		super(context, main, inner, outter, layer);
	}

	@Override
	protected TamableHumanoidCreeperGirlExpressionLayer<E, M> createExpressionLayer() {
		return new TamableHumanoidPregnantCreeperGirlExpressionLayer<>(this);
	}
}
