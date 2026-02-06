package dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.creeper;

import javax.annotation.Nullable;

import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractTamableHumanoidPregnantCreeperGirlModel;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobFace;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractTamablePregnantHumanoidCreeperGirl;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TamableHumanoidPregnantCreeperGirlExpressionLayer 
	<E extends AbstractTamablePregnantHumanoidCreeperGirl, M extends AbstractTamableHumanoidPregnantCreeperGirlModel<E>> extends AbstractHumanoidCreeperGirlExpressionFacialLayer<E, M> {
	
	public TamableHumanoidPregnantCreeperGirlExpressionLayer(RenderLayerParent<E, M> p_117346_) {
		super(p_117346_);
	}
	
	@Override
	public @Nullable RenderType renderType(E creeperGirl) {		
		
		final var pregnancyData =  creeperGirl.getPregnancyData();
		final var tamableData = creeperGirl.getTamableData();
		final var pain = pregnancyData.getPregnancyPain();
			
		if (pain != null) {
			switch (pain) {
			case MORNING_SICKNESS: {		
				return PAIN4;
			}
			case FETAL_MOVEMENT: {		
				return PAIN1;
			}
			case CONTRACTION: {		
				return PAIN2;
			}
			case MISCARRIAGE: {		
				return SURPRISED1;
			}
			case WATER_BREAKING: {		
				return SURPRISED1;
			}
			case PREBIRTH: {		
				return PAIN3;
			}
			case BIRTH: {		
				return PAIN1;
			}
			default:
				break;
			}
		}
	
		if (!pregnancyData.getSyncedPregnancySymptoms().isEmpty()) {
			return SAD1;
		}
		else if (tamableData.getFaceState() == PreggoMobFace.BLUSHED) {
			return HORNY2;
		}
		else if (tamableData.isWaiting()) {
			return SAD2;
		}
		else if (tamableData.isSavage()) {
			return SAD3;
		}

		return null;
	}
}
