package dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.creeper;

import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractHostilPregnantHumanoidCreeperGirlModel;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractHostilPregnantHumanoidCreeperGirl;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HostilPregnantHumanoidCreeperGirlExpressionLayer 
	<E extends AbstractHostilPregnantHumanoidCreeperGirl, M extends AbstractHostilPregnantHumanoidCreeperGirlModel<E>> extends AbstractHumanoidCreeperGirlExpressionLayer<E, M> {

	public HostilPregnantHumanoidCreeperGirlExpressionLayer(RenderLayerParent<E, M> p_117346_) {
		super(p_117346_);
	}
	
	@Override
	public RenderType renderType(E creeperGirl) {		
		if (creeperGirl.getPregnancyData().isIncapacitated()) {
			return HOSTIL_PAIN;
		}	
		return HOSTIL; 
	}
}
