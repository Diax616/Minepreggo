package dev.dixmk.minepreggo.client.renderer.preggo.creeper;

import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractHumanoidCreeperGirlModel;
import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.TamableCreeperGirlP6Model;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobBody;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableCreeperGirlP6;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TamableCreeperGirlP6Renderer extends AbstractTamablePregnantCreeperGirlRenderer<TamableCreeperGirlP6, TamableCreeperGirlP6Model> {
	
	public TamableCreeperGirlP6Renderer(EntityRendererProvider.Context context) {
		this(context, AbstractHumanoidCreeperGirlModel.LAYER_LOCATION_P6, AbstractHumanoidCreeperGirlModel.LAYER_INNER_ARMOR_LOCATION, AbstractHumanoidCreeperGirlModel.LAYER_OUTER_ARMOR_LOCATION, AbstractHumanoidCreeperGirlModel.LAYER_ENERGY_ARMOR_P6_LOCATION);
	}
	
	public TamableCreeperGirlP6Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter, ModelLayerLocation armor) {
		super(context, new TamableCreeperGirlP6Model(context.bakeLayer(main)), new TamableCreeperGirlP6Model(context.bakeLayer(inner)), new TamableCreeperGirlP6Model(context.bakeLayer(outter)), new TamableCreeperGirlP6Model(context.bakeLayer(armor)));
	}

	@Override
	public ResourceLocation getTextureLocation(TamableCreeperGirlP6 entity) {
		if (entity.getBodyState() == PreggoMobBody.NAKED) {
			return CREEPER_GIRL_P6_LOCATION.getRight();
		}
		return CREEPER_GIRL_P6_LOCATION.getLeft();
	}
}
