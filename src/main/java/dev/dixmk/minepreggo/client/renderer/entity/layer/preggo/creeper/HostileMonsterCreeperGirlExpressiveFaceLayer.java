package dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.creeper;

import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractHostileMonsterCreeperGirlModel;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractHostileCreeperGirl;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HostileMonsterCreeperGirlExpressiveFaceLayer 
	<E extends AbstractHostileCreeperGirl, M extends AbstractHostileMonsterCreeperGirlModel<E>> extends AbstractMonsterCreeperGirlExpressionLayer<E, M> {

	public HostileMonsterCreeperGirlExpressiveFaceLayer(RenderLayerParent<E, M> p_117346_) {
		super(p_117346_);
	}

	@Override
	public RenderType renderType(E entity) {
		return HOSTIL;
	}
}
