package dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.ender;

import javax.annotation.Nullable;

import com.mojang.blaze3d.vertex.PoseStack;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.client.model.entity.preggo.ender.AbstractTamableMonsterEnderWomanModel;
import dev.dixmk.minepreggo.utils.MinepreggoHelper;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobFace;
import dev.dixmk.minepreggo.world.entity.preggo.ender.AbstractTamableEnderWoman;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.effect.MobEffects;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MonsterEnderWomanExpressionLayer 
	<E extends AbstractTamableEnderWoman, M extends AbstractTamableMonsterEnderWomanModel<E>> extends RenderLayer<E, M> {

	protected static final RenderType ANGRY_MASK = RenderType.entityCutoutNoCull(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/ender/monster/expressions/monster_ender_woman_face_angry.png"));
	protected static final RenderType SAD_MASK = RenderType.entityCutoutNoCull(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/ender/monster/expressions/monster_ender_woman_face_sad.png"));
	protected static final RenderType SUPRISED_MASK = RenderType.entityCutoutNoCull(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/ender/monster/expressions/monster_ender_woman_face_surprised.png"));
	protected static final RenderType BLUSHED_MASK = RenderType.entityCutoutNoCull(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/ender/monster/expressions/monster_ender_woman_face_blushed.png"));
	
	public MonsterEnderWomanExpressionLayer(RenderLayerParent<E, M> p_117346_) {
		super(p_117346_);
	}

	@Override
	public void render(PoseStack p_117349_, MultiBufferSource p_117350_, int p_117351_, E p_117352_, float p_117353_,
			float p_117354_, float p_117355_, float p_117356_, float p_117357_, float p_117358_) {

		if (p_117352_.isInvisible())
			return;
		
		final var face = renderType(p_117352_);
		
		if (face == null)
			return;

		var model = this.getParentModel();	
		model.head.render(
				p_117349_, 
				p_117350_.getBuffer(face), 
				p_117351_, 
				LivingEntityRenderer.getOverlayCoords(p_117352_, 0.0F),
				1.0F, 1.0F, 1.0F, 1.0F
			);
	}
	
	public @Nullable RenderType renderType(E enderWoman) {
		final var tamableData = enderWoman.getTamableData();
		if (tamableData.isAngry() || (tamableData.isSavage() && enderWoman.isTame())) {
			return ANGRY_MASK;
		}
		else if (enderWoman.hasEffect(MobEffects.CONFUSION) || tamableData.isWaiting()) {
			return SAD_MASK;
		}
		else if (tamableData.getFaceState() == PreggoMobFace.BLUSHED) {
			return BLUSHED_MASK;
		}
		return null;
	}
}
