package dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.creeper;

import javax.annotation.Nullable;

import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractTamableHumanoidCreeperGirlModel;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobFace;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractTamableHumanoidCreeperGirl;
import dev.dixmk.minepreggo.world.pregnancy.PostPregnancy;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.world.effect.MobEffects;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HumanoidTamableCreeperGirlExpressionLayer
	<E extends AbstractTamableHumanoidCreeperGirl, M extends AbstractTamableHumanoidCreeperGirlModel<E>> extends AbstractHumanoidCreeperGirlExpressionFacialLayer<E, M> {
	
	
	public HumanoidTamableCreeperGirlExpressionLayer(RenderLayerParent<E, M> p_117346_) {
		super(p_117346_);
	}
	
	public @Nullable RenderType renderType(E creeperGirl) {	
		
		final var femaleData = creeperGirl.getGenderedData();
		final var post = femaleData.getPostPregnancyData().map(p -> p.getPostPregnancy()).orElse(null);
		final var tamableData = creeperGirl.getTamableData();
		
		if (post == PostPregnancy.MISCARRIAGE) {
			return POST_MISCARRIAGE;
		}
		else if (creeperGirl.hasEffect(MobEffects.CONFUSION)) {
			return PAIN4;
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
		else if (post == PostPregnancy.PARTUM) {
			return POST_PARTUM;
		}
		return null;
	}
}
