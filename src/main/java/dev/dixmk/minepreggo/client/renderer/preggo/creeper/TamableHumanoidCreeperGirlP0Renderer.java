package dev.dixmk.minepreggo.client.renderer.preggo.creeper;

import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractHumanoidCreeperGirlModel;
import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.TamableHumanoidCreeperGirlP0Model;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableHumanoidCreeperGirlP0;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TamableHumanoidCreeperGirlP0Renderer extends AbstractTamableHumanoidPregnantCreeperGirlRenderer<TamableHumanoidCreeperGirlP0, TamableHumanoidCreeperGirlP0Model> {
	
	public TamableHumanoidCreeperGirlP0Renderer(EntityRendererProvider.Context context) {
		this(context, AbstractHumanoidCreeperGirlModel.LAYER_LOCATION_P0, AbstractHumanoidCreeperGirlModel.LAYER_INNER_ARMOR_LOCATION, AbstractHumanoidCreeperGirlModel.LAYER_OUTER_ARMOR_LOCATION, AbstractHumanoidCreeperGirlModel.LAYER_ENERGY_ARMOR_P0_LOCATION);
	}
	
	public TamableHumanoidCreeperGirlP0Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter, ModelLayerLocation armor) {
		super(context, new TamableHumanoidCreeperGirlP0Model(context.bakeLayer(main)), new TamableHumanoidCreeperGirlP0Model(context.bakeLayer(inner)), new TamableHumanoidCreeperGirlP0Model(context.bakeLayer(outter)), new TamableHumanoidCreeperGirlP0Model(context.bakeLayer(armor)));
	}

	@Override
	public ResourceLocation getTextureLocation(TamableHumanoidCreeperGirlP0 p_115812_) {
		return CREEPER_GIRL_P0_LOCATION;
	}
}
