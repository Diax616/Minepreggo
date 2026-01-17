package dev.dixmk.minepreggo.client.renderer.preggo.creeper;

import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractHumanoidCreeperGirlModel;
import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.TamableHumanoidCreeperGirlP8Model;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobBody;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableHumanoidCreeperGirlP8;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TamableHumanoidCreeperGirlP8Renderer extends AbstractTamableHumanoidPregnantCreeperGirlRenderer<TamableHumanoidCreeperGirlP8, TamableHumanoidCreeperGirlP8Model> {
	
	public TamableHumanoidCreeperGirlP8Renderer(EntityRendererProvider.Context context) {
		this(context, AbstractHumanoidCreeperGirlModel.LAYER_LOCATION_P8, AbstractHumanoidCreeperGirlModel.LAYER_INNER_ARMOR_LOCATION, AbstractHumanoidCreeperGirlModel.LAYER_OUTER_ARMOR_LOCATION, AbstractHumanoidCreeperGirlModel.LAYER_ENERGY_ARMOR_P8_LOCATION);
	}
	
	public TamableHumanoidCreeperGirlP8Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter, ModelLayerLocation armor) {
		super(context, new TamableHumanoidCreeperGirlP8Model(context.bakeLayer(main)), new TamableHumanoidCreeperGirlP8Model(context.bakeLayer(inner)), new TamableHumanoidCreeperGirlP8Model(context.bakeLayer(outter)), new TamableHumanoidCreeperGirlP8Model(context.bakeLayer(armor)));
	}
	@Override
	public ResourceLocation getTextureLocation(TamableHumanoidCreeperGirlP8 entity) {
		if (entity.getTamableData().getBodyState() == PreggoMobBody.NAKED) {
			return CREEPER_GIRL_P8_LOCATION.getRight();
		}
		return CREEPER_GIRL_P8_LOCATION.getLeft();
	}
}
