package dev.dixmk.minepreggo.client.renderer.preggo.creeper;

import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractMonsterPregnantCreeperGirlModel;
import dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.creeper.HumanoidMonsterPregnantCreeperGirlExpressionLayer;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractMonsterPregnantHumanoidCreeperGirl;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractMonsterPregnantCreeperGirlRenderer 
	<E extends AbstractMonsterPregnantHumanoidCreeperGirl, M extends AbstractMonsterPregnantCreeperGirlModel<E>> extends AbstractHumanoidCreeperGirlRenderer<E, M> {

	protected AbstractMonsterPregnantCreeperGirlRenderer(Context context, M main, M inner, M outter, M layer, boolean facialExpresion) {
		super(context, main, inner, outter, layer);
		if (facialExpresion) this.addFacialExpresions();
	}
	
	protected AbstractMonsterPregnantCreeperGirlRenderer(Context context, M main, M inner, M outter, M layer) {
		this(context, main, inner, outter, layer, true);
	}
	
	protected void addFacialExpresions() {
		this.addLayer(new HumanoidMonsterPregnantCreeperGirlExpressionLayer<>(this));
	}
}
