package dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.creeper;

import javax.annotation.Nullable;

import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractTamableHumanoidCreeperGirlModel;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobFace;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractTamableCreeperGirl;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.world.effect.MobEffects;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TamableHumanoidCreeperGirlExpressionLayer
	<E extends AbstractTamableCreeperGirl, M extends AbstractTamableHumanoidCreeperGirlModel<E>> extends AbstractHumanoidCreeperGirlExpressionLayer<E, M> {
	
	public TamableHumanoidCreeperGirlExpressionLayer(RenderLayerParent<E, M> p_117346_) {
		super(p_117346_);
	}
	
	public @Nullable RenderType renderType(E creeperGirl) {			
		final var tamableData = creeperGirl.getTamableData();		
		if (creeperGirl.hasEffect(MobEffects.CONFUSION)) {
			return PAIN4;
		}
		else if (tamableData.getFaceState() == PreggoMobFace.BLUSHED) {
			return HORNY2;
		}
		else if (tamableData.isAngry()) {
			return ANGRY1;
		}
		else if (tamableData.isWaiting()) {
			return SAD2;
		}
		else if (tamableData.isSavage()) {
			if (creeperGirl.isTame()) {
				return ANGRY1;
			}
			return SAD3;
		}

		return null;
	}
}
