package dev.dixmk.minepreggo.client.renderer.entity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.WitchItemLayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.WitchModel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import com.mojang.blaze3d.vertex.PoseStack;

import dev.dixmk.minepreggo.utils.MinepreggoHelper;
import dev.dixmk.minepreggo.world.entity.monster.FertilityWitch;

@OnlyIn(Dist.CLIENT)
public class FertilityWitchRenderer extends MobRenderer<FertilityWitch, WitchModel<FertilityWitch>> {
	private static final ResourceLocation WITCH_LOCATION = MinepreggoHelper.fromThisNamespaceAndPath("textures/entity/fertility_witch.png");

	public FertilityWitchRenderer(EntityRendererProvider.Context context) {
		super(context, new WitchModel<>(context.bakeLayer(ModelLayers.WITCH)), 0.5F);
		this.addLayer(new WitchItemLayer<>(this, context.getItemInHandRenderer()));
	}

	@Override
	public void render(FertilityWitch entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {		
		this.model.setHoldingItem(!entity.getMainHandItem().isEmpty());
		super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
	}
	
	@Override
	public ResourceLocation getTextureLocation(FertilityWitch entity) {
		return WITCH_LOCATION;
	}
	
	@Override
	protected void scale(FertilityWitch entity, PoseStack poseStack, float partialTick) {
		poseStack.scale(0.9375F, 0.9375F, 0.9375F);
	}
}
