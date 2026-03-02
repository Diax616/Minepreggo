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

	protected ExpressiveFaceLayer(RenderLayerParent<E, M> renderer) {
		super(renderer);
	}

	@Override
	public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, E entity, float limbSwing, float limbSwingAmount, float partialTick, float ageInTicks, float netHeadYaw, float headPitch) {
		if (entity.isInvisible())
			return;
		
		final var face = renderType(entity);
		
		if (face == null)
			return;

		var model = this.getParentModel();	
		var whiteOverlay = getWhiteOverlayProgress(entity, partialTick);
		model.getHead().render(
				poseStack, 
				bufferSource.getBuffer(face), 
				packedLight, 
				LivingEntityRenderer.getOverlayCoords(entity, whiteOverlay),
				1.0F, 1.0F, 1.0F, whiteOverlay
			);
	}
	
	public abstract @Nullable RenderType renderType(E entity);
	
	protected float getWhiteOverlayProgress(E entity, float partialTicks) {
		return 0.0F;
	}
}
