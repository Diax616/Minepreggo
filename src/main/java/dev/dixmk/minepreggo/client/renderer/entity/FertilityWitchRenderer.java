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

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.world.entity.monster.FertilityWitch;

@OnlyIn(Dist.CLIENT)
public class FertilityWitchRenderer extends MobRenderer<FertilityWitch, WitchModel<FertilityWitch>> {
	private static final ResourceLocation WITCH_LOCATION = ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/fertility_witch.png");

	public FertilityWitchRenderer(EntityRendererProvider.Context context) {
		super(context, new WitchModel<>(context.bakeLayer(ModelLayers.WITCH)), 0.5F);
		this.addLayer(new WitchItemLayer<>(this, context.getItemInHandRenderer()));
	}

	@Override
	public void render(FertilityWitch p_116412_, float p_116413_, float p_116414_, PoseStack p_116415_, MultiBufferSource p_116416_, int p_116417_) {
		this.model.setHoldingItem(!p_116412_.getMainHandItem().isEmpty());
		super.render(p_116412_, p_116413_, p_116414_, p_116415_, p_116416_, p_116417_);
	}
	
	@Override
	public ResourceLocation getTextureLocation(FertilityWitch entity) {
		return WITCH_LOCATION;
	}
	
	@Override
	protected void scale(FertilityWitch p_116419_, PoseStack p_116420_, float p_116421_) {
		p_116420_.scale(0.9375F, 0.9375F, 0.9375F);
	}
}
