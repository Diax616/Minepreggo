package dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.creeper;

import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractHostilMonsterCreeperGirlModel;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractHostilCreeperGirl;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HostilMonsterCreeperGirlExpressiveFaceLayer 
	<E extends AbstractHostilCreeperGirl, M extends AbstractHostilMonsterCreeperGirlModel<E>> extends AbstractMonsterCreeperGirlExpressionLayer<E, M> {

	public HostilMonsterCreeperGirlExpressiveFaceLayer(RenderLayerParent<E, M> p_117346_) {
		super(p_117346_);
	}

	@Override
	public RenderType renderType(E entity) {
		return HOSTIL;
	}
}
