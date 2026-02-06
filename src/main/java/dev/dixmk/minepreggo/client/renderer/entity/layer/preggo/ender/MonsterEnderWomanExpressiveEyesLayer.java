package dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.ender;

import com.mojang.blaze3d.vertex.PoseStack;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.client.model.entity.preggo.ender.AbstractTamableMonsterEnderWomanModel;
import dev.dixmk.minepreggo.utils.MinepreggoHelper;
import dev.dixmk.minepreggo.world.entity.preggo.ender.AbstractTamableEnderWoman;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.effect.MobEffects;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MonsterEnderWomanExpressiveEyesLayer
	<E extends AbstractTamableEnderWoman, M extends AbstractTamableMonsterEnderWomanModel<E>> extends RenderLayer<E, M> {

	protected static final RenderType ANGRY_ENDER_EYES_1 = RenderType.eyes(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/ender/monster/expressions/monster_ender_woman_eye_angry1.png"));
	protected static final RenderType ANGRY_ENDER_EYES_2 = RenderType.eyes(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/ender/monster/expressions/monster_ender_woman_eye_angry2.png"));
	protected static final RenderType SAD_ENDER_EYES_1 = RenderType.eyes(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/ender/monster/expressions/monster_ender_woman_eye_sad1.png"));
	protected static final RenderType SAD_ENDER_EYES_2 = RenderType.eyes(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/ender/monster/expressions/monster_ender_woman_eye_sad2.png"));
	protected static final RenderType SURPRISED_ENDER_EYES = RenderType.eyes(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/ender/monster/expressions/monster_ender_woman_eye_surprised.png"));
	protected static final RenderType PAIN_ENDER_EYES = RenderType.eyes(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/ender/monster/expressions/monster_ender_woman_eye_pain.png"));
	
	public MonsterEnderWomanExpressiveEyesLayer(RenderLayerParent<E, M> p_116981_) {
		super(p_116981_);
	}

	public RenderType renderType(E enderWoman) {
		final var tamableData = enderWoman.getTamableData();	
		if (enderWoman.hasEffect(MobEffects.CONFUSION)) {
			return SAD_ENDER_EYES_1;
		}
		else if (tamableData.isWaiting()) {
			return SAD_ENDER_EYES_2;
		}
		else if (tamableData.isAngry() || (tamableData.isSavage() && enderWoman.isTame())) {
			return ANGRY_ENDER_EYES_1;
		}
		return MonsterEnderWomanClientHelper.DEFAULT_ENDER_EYES;
	}

	@Override
	public void render(PoseStack p_117349_, MultiBufferSource p_117350_, int p_117351_, E p_117352_, float p_117353_,
			float p_117354_, float p_117355_, float p_117356_, float p_117357_, float p_117358_) {
		this.getParentModel().renderToBuffer(p_117349_, p_117350_.getBuffer(renderType(p_117352_)), 15728640, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
	}
}
