package dev.dixmk.minepreggo.client.renderer.preggo.creeper;

import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractHumanoidCreeperGirlModel;
import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.TamableCreeperGirlP7Model;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableCreeperGirlP7;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TamableCreeperGirlP7Renderer extends AbstractTamablePregnantCreeperGirlRenderer<TamableCreeperGirlP7, TamableCreeperGirlP7Model> {
	
	public TamableCreeperGirlP7Renderer(EntityRendererProvider.Context context) {
		this(context, AbstractHumanoidCreeperGirlModel.LAYER_LOCATION_P7, AbstractHumanoidCreeperGirlModel.LAYER_INNER_ARMOR_LOCATION, AbstractHumanoidCreeperGirlModel.LAYER_OUTER_ARMOR_LOCATION, AbstractHumanoidCreeperGirlModel.LAYER_ENERGY_ARMOR_P7_LOCATION);
	}
	
	public TamableCreeperGirlP7Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter, ModelLayerLocation armor) {
		super(context, new TamableCreeperGirlP7Model(context.bakeLayer(main)), new TamableCreeperGirlP7Model(context.bakeLayer(inner)), new TamableCreeperGirlP7Model(context.bakeLayer(outter)), new TamableCreeperGirlP7Model(context.bakeLayer(armor)));
	}

	@Override
	public ResourceLocation getTextureLocation(TamableCreeperGirlP7 p_115812_) {
		return AbstractHumanoidCreeperGirlRenderer.CREEPER_GIRL_P7_LOCATION;
	}

}
