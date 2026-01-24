package dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.creeper;

import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractHumanoidMonsterPregnantCreeperGirlModel;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractMonsterPregnantHumanoidCreeperGirl;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HumanoidMonsterPregnantCreeperGirlExpressionLayer 
	<E extends AbstractMonsterPregnantHumanoidCreeperGirl, M extends AbstractHumanoidMonsterPregnantCreeperGirlModel<E>> extends AbstractHumanoidCreeperGirlExpressionFacialLayer<E, M> {

	public HumanoidMonsterPregnantCreeperGirlExpressionLayer(RenderLayerParent<E, M> p_117346_) {
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
