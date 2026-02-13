package dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.creeper;

import com.mojang.blaze3d.vertex.PoseStack;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractHumanoidCreeperGirlModel;
import dev.dixmk.minepreggo.client.renderer.entity.layer.ExpressiveFaceLayer;
import dev.dixmk.minepreggo.client.renderer.preggo.creeper.CreeperGirlClientHelper;
import dev.dixmk.minepreggo.utils.MinepreggoHelper;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractCreeperGirl;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractHumanoidCreeperGirlExpressionLayer
	<E extends AbstractCreeperGirl, M extends AbstractHumanoidCreeperGirlModel<E>> extends ExpressiveFaceLayer<E, M> {

	protected static final RenderType HOSTIL = RenderType.entityCutoutNoCull(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/humanoid/expressions/humanoid_creeper_girl_face_hostil.png"));
	protected static final RenderType HOSTIL_PAIN = RenderType.entityTranslucent(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/humanoid/expressions/humanoid_creeper_girl_face_hostil_pain.png"));

	protected static final RenderType ANGRY1 = RenderType.entityCutoutNoCull(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/humanoid/expressions/humanoid_creeper_girl_face_angry1.png"));
	protected static final RenderType ANGRY2 = RenderType.entityCutoutNoCull(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/humanoid/expressions/humanoid_creeper_girl_face_angry2.png"));
	protected static final RenderType HORNY1 = RenderType.entityCutoutNoCull(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/humanoid/expressions/humanoid_creeper_girl_face_horny1.png"));	
	protected static final RenderType PAIN1 = RenderType.entityCutoutNoCull(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/humanoid/expressions/humanoid_creeper_girl_face_pain1.png"));
	protected static final RenderType PAIN3 = RenderType.entityCutoutNoCull(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/humanoid/expressions/humanoid_creeper_girl_face_pain3.png"));
	protected static final RenderType PAIN2 = RenderType.entityCutoutNoCull(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/humanoid/expressions/humanoid_creeper_girl_face_pain2.png"));
	protected static final RenderType SAD1 = RenderType.entityCutoutNoCull(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/humanoid/expressions/humanoid_creeper_girl_face_sad1.png"));
	protected static final RenderType SURPRISED1 = RenderType.entityCutoutNoCull(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/humanoid/expressions/humanoid_creeper_girl_face_surprised1.png"));
	protected static final RenderType SAD2 = RenderType.entityCutoutNoCull(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/humanoid/expressions/humanoid_creeper_girl_face_sad2.png"));
	protected static final RenderType SAD3 = RenderType.entityCutoutNoCull(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/humanoid/expressions/humanoid_creeper_girl_face_sad3.png"));
	protected static final RenderType PAIN4 = RenderType.entityCutoutNoCull(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/humanoid/expressions/humanoid_creeper_girl_face_pain4.png"));
	protected static final RenderType HORNY2 = RenderType.entityCutoutNoCull(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/humanoid/expressions/humanoid_creeper_girl_face_horny2.png"));
	
	protected static final RenderType POST_PARTUM = RenderType.entityCutoutNoCull(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/humanoid/expressions/humanoid_creeper_girl_face_post_partum.png"));
	protected static final RenderType POST_MISCARRIAGE = RenderType.entityCutoutNoCull(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/humanoid/expressions/humanoid_creeper_girl_face_post_miscarriage.png"));

	
	protected AbstractHumanoidCreeperGirlExpressionLayer(RenderLayerParent<E, M> p_117346_) {
		super(p_117346_);
	}
	
	@Override
	public void render(PoseStack poseStack, MultiBufferSource p_117350_, int p_117351_, E p_117352_, float p_117353_,
			float p_117354_, float p_117355_, float p_117356_, float p_117357_, float p_117358_) {		
		
		if (p_117352_.isInvisible())
			return;
	
		var model = this.getParentModel();
		if (model.young) {
			final var face = this.renderType(p_117352_);
			if (face == null)
				return;
			
			var whiteOverlay = CreeperGirlClientHelper.getWhiteOverlayProgress(p_117352_, p_117353_);
			model.setAllInvisibleLessHead();				
			model.renderToBuffer(
					poseStack, 
					p_117350_.getBuffer(face), 
					p_117351_, 
					LivingEntityRenderer.getOverlayCoords(p_117352_, whiteOverlay),
					1.0F, 1.0F, 1.0F, whiteOverlay
				);
			model.setAllVisible(true);
		}
		else {
			super.render(poseStack, p_117350_, p_117351_, p_117352_, p_117353_, p_117354_, p_117355_, p_117356_, p_117357_, p_117358_);
		}
	}
	
	@Override
	protected float getWhiteOverlayProgress(E entity, float partialTicks) {
		return CreeperGirlClientHelper.getWhiteOverlayProgress(entity, partialTicks);
	}
}
