package dev.dixmk.minepreggo.client.renderer.entity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.IllagerRenderer;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.IllagerModel;

import com.mojang.blaze3d.vertex.PoseStack;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.world.entity.monster.ScientificIllager;

public class ScientificIllagerRenderer extends IllagerRenderer<ScientificIllager> {
	
	private static final ResourceLocation SCIENTIFIC_ILLAGER = ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/illager/scientific_illager.png");
	
	public ScientificIllagerRenderer(EntityRendererProvider.Context context) {		
		super(context, new IllagerModel<>(context.bakeLayer(ModelLayers.VINDICATOR)), 0.5F);
					
		this.addLayer(new ItemInHandLayer<ScientificIllager, IllagerModel<ScientificIllager>>(this, context.getItemInHandRenderer()) {			
			@Override
			public void render(PoseStack p_116352_, MultiBufferSource p_116353_, int p_116354_, ScientificIllager p_116355_, float p_116356_, float p_116357_, float p_116358_, float p_116359_, float p_116360_, float p_116361_) {
				if (p_116355_.isAggressive()) {
					super.render(p_116352_, p_116353_, p_116354_, p_116355_, p_116356_, p_116357_, p_116358_, p_116359_, p_116360_, p_116361_);
				}
			}
		});
		
	}

	@Override
	public ResourceLocation getTextureLocation(ScientificIllager entity) {
		return SCIENTIFIC_ILLAGER;
	}
}
