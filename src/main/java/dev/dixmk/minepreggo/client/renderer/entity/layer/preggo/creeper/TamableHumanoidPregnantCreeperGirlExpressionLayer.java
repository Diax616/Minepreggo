package dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.creeper;

import javax.annotation.Nullable;

import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractTamablePregnantHumanoidCreeperGirlModel;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractTamablePregnantCreeperGirl;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TamableHumanoidPregnantCreeperGirlExpressionLayer 
	<E extends AbstractTamablePregnantCreeperGirl, M extends AbstractTamablePregnantHumanoidCreeperGirlModel<E>> extends TamableHumanoidCreeperGirlExpressionLayer<E, M> {
	
	public TamableHumanoidPregnantCreeperGirlExpressionLayer(RenderLayerParent<E, M> p_117346_) {
		super(p_117346_);
	}
	
	@Override
	public @Nullable RenderType renderType(E creeperGirl) {		
		final var pregnancyData =  creeperGirl.getPregnancyData();
		final var pain = pregnancyData.getPregnancyPain();
			
		if (pain != null) {
			switch (pain) {
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
	
		if (!pregnancyData.getSyncedPregnancySymptoms().isEmpty()) {
			return SAD1;
		}

		return super.renderType(creeperGirl);
	}
}
