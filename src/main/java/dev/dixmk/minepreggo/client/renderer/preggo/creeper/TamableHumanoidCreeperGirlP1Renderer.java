package dev.dixmk.minepreggo.client.renderer.preggo.creeper;

import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractHumanoidCreeperGirlModel;
import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.TamableHumanoidCreeperGirlP1Model;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableHumanoidCreeperGirlP1;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TamableHumanoidCreeperGirlP1Renderer extends AbstractTamableHumanoidPregnantCreeperGirlRenderer<TamableHumanoidCreeperGirlP1, TamableHumanoidCreeperGirlP1Model> {
	
	public TamableHumanoidCreeperGirlP1Renderer(EntityRendererProvider.Context context) {
		this(context, AbstractHumanoidCreeperGirlModel.LAYER_LOCATION_P1, AbstractHumanoidCreeperGirlModel.LAYER_INNER_ARMOR_LOCATION, AbstractHumanoidCreeperGirlModel.LAYER_OUTER_ARMOR_LOCATION, AbstractHumanoidCreeperGirlModel.LAYER_ENERGY_ARMOR_P1_LOCATION);
	}
	
	public TamableHumanoidCreeperGirlP1Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter, ModelLayerLocation armor) {
		super(context, new TamableHumanoidCreeperGirlP1Model(context.bakeLayer(main)), new TamableHumanoidCreeperGirlP1Model(context.bakeLayer(inner)), new TamableHumanoidCreeperGirlP1Model(context.bakeLayer(outter)), new TamableHumanoidCreeperGirlP1Model(context.bakeLayer(armor)));
	}

	@Override
	public ResourceLocation getTextureLocation(TamableHumanoidCreeperGirlP1 p_115812_) {
		return CREEPER_GIRL_P1_LOCATION;
	}
}
