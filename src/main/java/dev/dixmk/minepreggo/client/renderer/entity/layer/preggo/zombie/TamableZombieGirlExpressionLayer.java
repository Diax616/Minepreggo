package dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.zombie;

import javax.annotation.Nullable;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.client.model.entity.preggo.zombie.AbstractTamableZombieGirlModel;
import dev.dixmk.minepreggo.utils.MinepreggoHelper;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobFace;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.AbstractTamableZombieGirl;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.world.effect.MobEffects;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TamableZombieGirlExpressionLayer 
	<E extends AbstractTamableZombieGirl, M extends AbstractTamableZombieGirlModel<E>> extends AbstractZombieGirlExpressionLayer<E, M> {

	protected static final RenderType SAD2 = RenderType.entityCutoutNoCull(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/zombie/expressions/zombie_girl_face_sad2.png"));
	protected static final RenderType SAD3 = RenderType.entityCutoutNoCull(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/zombie/expressions/zombie_girl_face_sad3.png"));
	protected static final RenderType PAIN4 = RenderType.entityCutoutNoCull(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/zombie/expressions/zombie_girl_face_pain4.png"));
	protected static final RenderType SURPRISED2 = RenderType.entityCutoutNoCull(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/zombie/expressions/zombie_girl_face_surprised2.png"));
	protected static final RenderType HORNY2 = RenderType.entityCutoutNoCull(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/zombie/expressions/zombie_girl_face_horny2.png"));

	protected static final RenderType POST_PARTUM = RenderType.entityCutoutNoCull(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/zombie/expressions/zombie_girl_face_post_partum.png"));
	protected static final RenderType POST_MISCARRIAGE = RenderType.entityCutoutNoCull(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/zombie/expressions/zombie_girl_face_post_miscarriage.png"));

	public TamableZombieGirlExpressionLayer(RenderLayerParent<E, M> p_117346_) {
		super(p_117346_);
	}
	
	@Override
	public @Nullable RenderType renderType(E zombieGirl) {		
		var tamableData = zombieGirl.getTamableData();
	
		if (zombieGirl.isOnFire()) {
			return SURPRISED2;
		}	
		else if (zombieGirl.hasEffect(MobEffects.CONFUSION)) {
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

		return null;
	}
}
