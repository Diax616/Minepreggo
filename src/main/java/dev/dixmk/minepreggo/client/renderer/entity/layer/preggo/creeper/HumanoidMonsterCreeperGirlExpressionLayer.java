package dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.creeper;

import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractHumanoidMonsterCreeperGirlModel;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractMonsterHumanoidCreeperGirl;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HumanoidMonsterCreeperGirlExpressionLayer 
	<E extends AbstractMonsterHumanoidCreeperGirl, M extends AbstractHumanoidMonsterCreeperGirlModel<E>> extends AbstractHumanoidCreeperGirlExpressionFacialLayer<E, M> {

	public HumanoidMonsterCreeperGirlExpressionLayer(RenderLayerParent<E, M> p_117346_) {
		super(p_117346_);
	}

	public RenderType renderType(E creeperGirl) {	
		return HOSTIL;
	}
}

