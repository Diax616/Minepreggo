package dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.creeper;

import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractHostilHumanoidCreeperGirlModel;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractHostilHumanoidCreeperGirl;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HostilHumanoidCreeperGirlExpressionLayer 
	<E extends AbstractHostilHumanoidCreeperGirl, M extends AbstractHostilHumanoidCreeperGirlModel<E>> extends AbstractHumanoidCreeperGirlExpressionLayer<E, M> {

	public HostilHumanoidCreeperGirlExpressionLayer(RenderLayerParent<E, M> p_117346_) {
		super(p_117346_);
	}

	public RenderType renderType(E creeperGirl) {	
		return HOSTIL;
	}
}

