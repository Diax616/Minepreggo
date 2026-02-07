package dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.creeper;

import javax.annotation.Nullable;

import com.mojang.blaze3d.vertex.PoseStack;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractMonsterCreeperGirlModel;
import dev.dixmk.minepreggo.utils.MinepreggoHelper;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractCreeperGirl;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractMonsterCreeperGirlExpressionLayer 
	<E extends AbstractCreeperGirl, M extends AbstractMonsterCreeperGirlModel<E>> extends RenderLayer<E, M> {

	protected static final RenderType ANGRY1 = RenderType.entityCutoutNoCull(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/monster/expressions/monster_creeper_girl_face_angry1.png"));
	protected static final RenderType HORNY1 = RenderType.entityCutoutNoCull(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/monster/expressions/monster_creeper_girl_face_horny1.png"));
	protected static final RenderType PAIN1 = RenderType.entityCutoutNoCull(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/monster/expressions/monster_creeper_girl_face_pain1.png"));
	protected static final RenderType PAIN3 = RenderType.entityCutoutNoCull(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/monster/expressions/monster_creeper_girl_face_pain3.png"));
	protected static final RenderType PAIN2 = RenderType.entityCutoutNoCull(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/monster/expressions/monster_creeper_girl_face_pain2.png"));
	protected static final RenderType SAD1 = RenderType.entityCutoutNoCull(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/monster/expressions/monster_creeper_girl_face_sad1.png"));
	protected static final RenderType SURPRISED1 = RenderType.entityCutoutNoCull(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/monster/expressions/monster_creeper_girl_face_surprised1.png"));
	protected static final RenderType SAD2 = RenderType.entityCutoutNoCull(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/monster/expressions/monster_creeper_girl_face_sad2.png"));
	protected static final RenderType SAD3 = RenderType.entityCutoutNoCull(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/monster/expressions/monster_creeper_girl_face_sad3.png"));
	protected static final RenderType PAIN4 = RenderType.entityCutoutNoCull(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/monster/expressions/monster_creeper_girl_face_pain4.png"));
	protected static final RenderType HORNY2 = RenderType.entityCutoutNoCull(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/monster/expressions/monster_creeper_girl_face_horny2.png"));
	
	protected static final RenderType POST_PARTUM = RenderType.entityCutoutNoCull(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/monster/expressions/monster_creeper_girl_face_post_partum.png"));
	protected static final RenderType POST_MISCARRIAGE = RenderType.entityCutoutNoCull(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/monster/expressions/monster_creeper_girl_face_post_miscarriage.png"));
	
	protected AbstractMonsterCreeperGirlExpressionLayer(RenderLayerParent<E, M> p_117346_) {
		super(p_117346_);
	}

	@Override
	public void render(PoseStack poseStack, MultiBufferSource p_117350_, int p_117351_, E p_117352_, float p_117353_,
			float p_117354_, float p_117355_, float p_117356_, float p_117357_, float p_117358_) {		
		
		if (p_117352_.isInvisible())
			return;
		
		final var face = renderType(p_117352_);
		if (face != null) {	
			var model = this.getParentModel();	
			model.head.render(
					poseStack, 
					p_117350_.getBuffer(face), 
					p_117351_, 
					LivingEntityRenderer.getOverlayCoords(p_117352_, 0.0F),
					1.0F, 1.0F, 1.0F, 1.0F
				);
		}
	}
	
	public abstract @Nullable RenderType renderType(E creeperGirl);
}
