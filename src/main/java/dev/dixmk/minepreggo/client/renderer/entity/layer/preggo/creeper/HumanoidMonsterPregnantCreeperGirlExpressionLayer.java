package dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.creeper;

import java.util.Optional;

import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractMonsterPregnantCreeperGirlModel;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractMonsterPregnantHumanoidCreeperGirl;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HumanoidMonsterPregnantCreeperGirlExpressionLayer 
	<E extends AbstractMonsterPregnantHumanoidCreeperGirl, M extends AbstractMonsterPregnantCreeperGirlModel<E>> extends AbstractHumanoidCreeperGirlExpressionFacialLayer<E, M> {

	public HumanoidMonsterPregnantCreeperGirlExpressionLayer(RenderLayerParent<E, M> p_117346_) {
		super(p_117346_);
	}
	
	@Override
	public Optional<RenderType> renderType(E creeperGirl) {		
		if (creeperGirl.hasPregnancyPain()) {
			return Optional.of(HOSTIL_PAIN);
		}	
		return Optional.of(HOSTIL); 
	}
}
