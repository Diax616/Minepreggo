package dev.dixmk.minepreggo.client.renderer.entity.layer;

import javax.annotation.Nullable;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class ExpressiveFaceLayer 
	<E extends LivingEntity, M extends EntityModel<E> & HeadedModel> extends RenderLayer<E, M> {

	protected ExpressiveFaceLayer(RenderLayerParent<E, M> p_117346_) {
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
		var whiteOverlay = getWhiteOverlayProgress(p_117352_, p_117353_);
		model.getHead().render(
				p_117349_, 
				p_117350_.getBuffer(face), 
				p_117351_, 
				LivingEntityRenderer.getOverlayCoords(p_117352_, whiteOverlay),
				1.0F, 1.0F, 1.0F, whiteOverlay
			);
	}
	
	public abstract @Nullable RenderType renderType(E entity);
	
	protected float getWhiteOverlayProgress(E entity, float partialTicks) {
		return 0.0F;
	}
}
