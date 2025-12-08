package dev.dixmk.minepreggo.client.renderer.preggo.creeper;

import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractHumanoidCreeperGirlModel;
import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.TamableHumanoidCreeperGirlP3Model;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableHumanoidCreeperGirlP3;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TamableHumanoidCreeperGirlP3Renderer extends AbstractTamableHumanoidPregnantCreeperGirlRenderer<TamableHumanoidCreeperGirlP3, TamableHumanoidCreeperGirlP3Model> {
	
	public TamableHumanoidCreeperGirlP3Renderer(EntityRendererProvider.Context context) {
		this(context, AbstractHumanoidCreeperGirlModel.LAYER_LOCATION_P3, AbstractHumanoidCreeperGirlModel.LAYER_INNER_ARMOR_LOCATION, AbstractHumanoidCreeperGirlModel.LAYER_OUTER_ARMOR_LOCATION, AbstractHumanoidCreeperGirlModel.LAYER_ENERGY_ARMOR_P3_LOCATION);
	}
	
	public TamableHumanoidCreeperGirlP3Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter, ModelLayerLocation armor) {
		super(context, new TamableHumanoidCreeperGirlP3Model(context.bakeLayer(main)), new TamableHumanoidCreeperGirlP3Model(context.bakeLayer(inner)), new TamableHumanoidCreeperGirlP3Model(context.bakeLayer(outter)), new TamableHumanoidCreeperGirlP3Model(context.bakeLayer(armor)));
	}

	@Override
	public ResourceLocation getTextureLocation(TamableHumanoidCreeperGirlP3 p_115812_) {
		return CREEPER_GIRL_P3_LOCATION;
	}

}
