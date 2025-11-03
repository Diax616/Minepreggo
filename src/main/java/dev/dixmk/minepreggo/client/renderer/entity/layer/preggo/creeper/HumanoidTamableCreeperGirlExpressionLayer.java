package dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.creeper;

import java.util.Optional;

import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractTamableCreeperGirlModel;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobState;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractTamableHumanoidCreeperGirl;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.world.effect.MobEffects;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HumanoidTamableCreeperGirlExpressionLayer
	<E extends AbstractTamableHumanoidCreeperGirl<?>, M extends AbstractTamableCreeperGirlModel<E>> extends AbstractHumanoidCreeperGirlExpressionFacialLayer<E, M> {
	
	
	public HumanoidTamableCreeperGirlExpressionLayer(RenderLayerParent<E, M> p_117346_) {
		super(p_117346_);
	}
	
	public Optional<RenderType> renderType(E creeperGirl) {	
		if (creeperGirl.hasEffect(MobEffects.CONFUSION)) {
			return Optional.of(PAIN4);
		}
		else if (creeperGirl.getState() == PreggoMobState.BLUSHED) {
			return Optional.of(HORNY2);
		}
		else if (creeperGirl.isWaiting()) {
			return Optional.of(SAD2);
		}
		else if (creeperGirl.isSavage()) {
			return Optional.of(SAD3);
		}
		else {
			return Optional.empty();
		}
	}
}
