package dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.creeper;

import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractHostileHumanoidCreeperGirlModel;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractHostileHumanoidCreeperGirl;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HostileHumanoidCreeperGirlExpressionLayer 
	<E extends AbstractHostileHumanoidCreeperGirl, M extends AbstractHostileHumanoidCreeperGirlModel<E>> extends AbstractHumanoidCreeperGirlExpressionLayer<E, M> {

	public HostileHumanoidCreeperGirlExpressionLayer(RenderLayerParent<E, M> p_117346_) {
		super(p_117346_);
	}

	public RenderType renderType(E creeperGirl) {	
		return HOSTIL;
	}
}

