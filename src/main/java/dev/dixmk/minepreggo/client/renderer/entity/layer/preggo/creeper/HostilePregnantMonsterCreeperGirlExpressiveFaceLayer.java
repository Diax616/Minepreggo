package dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.creeper;

import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractHostilePregnantMonsterCreeperGirlModel;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractHostilePregnantCreeperGirl;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HostilePregnantMonsterCreeperGirlExpressiveFaceLayer 
	<E extends AbstractHostilePregnantCreeperGirl, M extends AbstractHostilePregnantMonsterCreeperGirlModel<E>> extends HostileMonsterCreeperGirlExpressiveFaceLayer<E, M> {

	public HostilePregnantMonsterCreeperGirlExpressiveFaceLayer(RenderLayerParent<E, M> p_117346_) {
		super(p_117346_);
	}

	@Override
	public RenderType renderType(E creeperGirl) {		
		if (creeperGirl.getPregnancyData().isIncapacitated()) {
			return PAIN1;
		}	
		return super.renderType(creeperGirl); 
	}
}
