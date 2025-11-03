package dev.dixmk.minepreggo.client.renderer.preggo.creeper;

import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractMonsterCreeperGirlModel;
import dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.creeper.HumanoidMonsterCreeperGirlExpressionLayer;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractMonsterHumanoidCreeperGirl;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractMonsterCreeperGirlRenderer 
	<E extends AbstractMonsterHumanoidCreeperGirl, M extends AbstractMonsterCreeperGirlModel<E>> extends AbstractHumanoidCreeperGirlRenderer<E, M> {

	protected AbstractMonsterCreeperGirlRenderer(Context context, M main, M inner, M outter, M layer, boolean facialExpresion) {
		super(context, main, inner, outter, layer);
		if (facialExpresion) this.addFacialExpresions();
	}

	protected AbstractMonsterCreeperGirlRenderer(Context context, M main, M inner, M outter, M layer) {
		this(context, main, inner, outter, layer, true);
	}
	
	protected void addFacialExpresions() {
		this.addLayer(new HumanoidMonsterCreeperGirlExpressionLayer<>(this));
	}
}
