package dev.dixmk.minepreggo.client.renderer.entity;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.IllagerRenderer;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.IllagerModel;

import com.mojang.blaze3d.vertex.PoseStack;

import dev.dixmk.minepreggo.utils.MinepreggoHelper;
import dev.dixmk.minepreggo.world.entity.monster.ScientificIllager;

@OnlyIn(Dist.CLIENT)
public class ScientificIllagerRenderer extends IllagerRenderer<ScientificIllager> {
	
	private static final ResourceLocation SCIENTIFIC_ILLAGER = MinepreggoHelper.fromThisNamespaceAndPath("textures/entity/illager/scientific_illager.png");
	
	public ScientificIllagerRenderer(EntityRendererProvider.Context context) {		
		super(context, new IllagerModel<>(context.bakeLayer(ModelLayers.VINDICATOR)), 0.5F);
					
		this.addLayer(new ItemInHandLayer<ScientificIllager, IllagerModel<ScientificIllager>>(this, context.getItemInHandRenderer()) {			
			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, ScientificIllager entity, float limbSwing, float limbSwingAmount, float partialTick, float ageInTicks, float netHeadYaw, float headPitch) {
				if (entity.isAggressive()) {
					super.render(poseStack, bufferSource, packedLight, entity, limbSwing, limbSwingAmount, partialTick, ageInTicks, netHeadYaw, headPitch);
				}
			}
		});		
	}

	@Override
	public ResourceLocation getTextureLocation(ScientificIllager entity) {
		return SCIENTIFIC_ILLAGER;
	}
}
