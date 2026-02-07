package dev.dixmk.minepreggo.client.renderer.preggo.creeper;

import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractTamablePregnantMonsterCreeperGirlModel;
import dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.creeper.TamableMonsterCreeperGirlExpressionLayer;
import dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.creeper.TamablePregnantMonsterCreeperGirlExpressionLayer;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractTamablePregnantCreeperGirl;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractTamablePregnantMonsterCreeperGirlRenderer 
	<E extends AbstractTamablePregnantCreeperGirl, M extends AbstractTamablePregnantMonsterCreeperGirlModel<E>> extends AbstractTamableMonsterCreeperGirlRenderer<E, M> {

	protected AbstractTamablePregnantMonsterCreeperGirlRenderer(Context context, M main, M eneryArmor, M outterArmor) {
		super(context, main, eneryArmor, outterArmor);
	}
	
	@Override
	protected TamableMonsterCreeperGirlExpressionLayer<E, M> createExpressionLayer() {
		return new TamablePregnantMonsterCreeperGirlExpressionLayer<>(this);
	}
}
