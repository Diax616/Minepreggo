package dev.dixmk.minepreggo.client.renderer.entity.layers.preggo.creeper;

import javax.annotation.Nullable;

import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractTamablePregnantHumanoidCreeperGirlModel;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractTamablePregnantCreeperGirl;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TamablePregnantHumanoidCreeperGirlExpressionLayer 
	<E extends AbstractTamablePregnantCreeperGirl, M extends AbstractTamablePregnantHumanoidCreeperGirlModel<E>> extends TamableHumanoidCreeperGirlExpressionLayer<E, M> {
	
	public TamablePregnantHumanoidCreeperGirlExpressionLayer(RenderLayerParent<E, M> renderer) {
		super(renderer);
	}
	
	@Override
	public @Nullable RenderType renderType(E creeperGirl) {		
		final var pregnancyData = creeperGirl.getPregnancyData();
		final var instance = pregnancyData.getPregnancyPain();
		
		if (instance != null) {
			switch (instance.getPain()) {
			case MORNING_SICKNESS: {		
				return PAIN4;
			}
			case FETAL_MOVEMENT, BIRTH: {		
				return PAIN1;
			}
			case CONTRACTION: {		
				return PAIN2;
			}
			case MISCARRIAGE, WATER_BREAKING: {		
				return SURPRISED1;
			}
			case PREBIRTH: {		
				return PAIN3;
			}
			default:
				break;
			}
		}
	
		if (pregnancyData.getSyncedPregnancySymptoms().getSyncedSymptoms() != 0) {
			return SAD1;
		}

		return super.renderType(creeperGirl);
	}
}
