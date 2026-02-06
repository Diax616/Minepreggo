package dev.dixmk.minepreggo.client.renderer.preggo.creeper;

import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractHumanoidMonsterPregnantCreeperGirlModel;
import dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.creeper.MonsterHumanoidPregnantCreeperGirlExpressionLayer;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractMonsterPregnantHumanoidCreeperGirl;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractMonsterHumanoidPregnantCreeperGirlRenderer 
	<E extends AbstractMonsterPregnantHumanoidCreeperGirl, M extends AbstractHumanoidMonsterPregnantCreeperGirlModel<E>> extends AbstractHumanoidCreeperGirlRenderer<E, M> {

	protected AbstractMonsterHumanoidPregnantCreeperGirlRenderer(Context context, M main, M inner, M outter, M layer, boolean facialExpresion) {
		super(context, main, inner, outter, layer);
		if (facialExpresion) this.addFacialExpresions();
	}
	
	protected AbstractMonsterHumanoidPregnantCreeperGirlRenderer(Context context, M main, M inner, M outter, M layer) {
		this(context, main, inner, outter, layer, true);
	}
	
	protected void addFacialExpresions() {
		this.addLayer(new MonsterHumanoidPregnantCreeperGirlExpressionLayer<>(this));
	}
}
