package dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.ender;

import javax.annotation.Nullable;

import dev.dixmk.minepreggo.client.model.entity.preggo.ender.AbstractTamableMonsterEnderWomanModel;
import dev.dixmk.minepreggo.client.renderer.entity.layer.ExpressiveFaceLayer;
import dev.dixmk.minepreggo.utils.MinepreggoHelper;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobFace;
import dev.dixmk.minepreggo.world.entity.preggo.ender.AbstractTamableEnderWoman;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.world.effect.MobEffects;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TamableMonsterEnderWomanExpressionLayer 
	<E extends AbstractTamableEnderWoman, M extends AbstractTamableMonsterEnderWomanModel<E>> extends ExpressiveFaceLayer<E, M> {

	protected static final RenderType ANGRY_MASK = RenderType.entityCutoutNoCull(MinepreggoHelper.fromThisNamespaceAndPath("textures/entity/preggo/ender/monster/expressions/monster_ender_woman_face_angry.png"));
	protected static final RenderType ANGRY_AND_BLUSHED_MASK = RenderType.entityCutoutNoCull(MinepreggoHelper.fromThisNamespaceAndPath("textures/entity/preggo/ender/monster/expressions/monster_ender_woman_face_angry_and_blushed.png"));
	protected static final RenderType SAD_MASK = RenderType.entityCutoutNoCull(MinepreggoHelper.fromThisNamespaceAndPath("textures/entity/preggo/ender/monster/expressions/monster_ender_woman_face_sad.png"));
	protected static final RenderType VERY_SAD_MASK = RenderType.entityCutoutNoCull(MinepreggoHelper.fromThisNamespaceAndPath("textures/entity/preggo/ender/monster/expressions/monster_ender_woman_face_very_sad.png"));
	protected static final RenderType SUPRISED_MASK = RenderType.entityCutoutNoCull(MinepreggoHelper.fromThisNamespaceAndPath("textures/entity/preggo/ender/monster/expressions/monster_ender_woman_face_surprised.png"));
	protected static final RenderType BLUSHED_MASK = RenderType.entityCutoutNoCull(MinepreggoHelper.fromThisNamespaceAndPath("textures/entity/preggo/ender/monster/expressions/monster_ender_woman_face_blushed.png"));
	protected static final RenderType PAIN_MASK = RenderType.entityCutoutNoCull(MinepreggoHelper.fromThisNamespaceAndPath("textures/entity/preggo/ender/monster/expressions/monster_ender_woman_face_pain.png"));

	public TamableMonsterEnderWomanExpressionLayer(RenderLayerParent<E, M> renderer) {
		super(renderer);
	}
	
	public @Nullable RenderType renderType(E enderWoman) {
		final var tamableData = enderWoman.getTamableData();	
		if (enderWoman.hasEffect(MobEffects.CONFUSION)) {
			return SAD_MASK;
		}
		if (tamableData.isSavage() && enderWoman.isTame()) {		
			return ANGRY_MASK;
		}	
		else if (tamableData.isAngry()) {
			return ANGRY_AND_BLUSHED_MASK;
		}
		else if (tamableData.getFaceState() == PreggoMobFace.BLUSHED) {
			return BLUSHED_MASK;
		}
		else if(tamableData.isWaiting()) {
			return SAD_MASK;
		}
		return null;
	}
}
