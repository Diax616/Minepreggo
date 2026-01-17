package dev.dixmk.minepreggo.client.renderer.preggo.creeper;

import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractHumanoidCreeperGirlModel;
import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.TamableHumanoidCreeperGirlP4Model;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobBody;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableHumanoidCreeperGirlP4;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TamableHumanoidCreeperGirlP4Renderer extends AbstractTamableHumanoidPregnantCreeperGirlRenderer<TamableHumanoidCreeperGirlP4, TamableHumanoidCreeperGirlP4Model> {
	
	public TamableHumanoidCreeperGirlP4Renderer(EntityRendererProvider.Context context) {
		this(context, AbstractHumanoidCreeperGirlModel.LAYER_LOCATION_P4, AbstractHumanoidCreeperGirlModel.LAYER_INNER_ARMOR_LOCATION, AbstractHumanoidCreeperGirlModel.LAYER_OUTER_ARMOR_LOCATION, AbstractHumanoidCreeperGirlModel.LAYER_ENERGY_ARMOR_P4_LOCATION);
	}
	
	public TamableHumanoidCreeperGirlP4Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter, ModelLayerLocation armor) {
		super(context, new TamableHumanoidCreeperGirlP4Model(context.bakeLayer(main)), new TamableHumanoidCreeperGirlP4Model(context.bakeLayer(inner)), new TamableHumanoidCreeperGirlP4Model(context.bakeLayer(outter)), new TamableHumanoidCreeperGirlP4Model(context.bakeLayer(armor)));
	}

	@Override
	public ResourceLocation getTextureLocation(TamableHumanoidCreeperGirlP4 entity) {
		if (entity.getTamableData().getBodyState() == PreggoMobBody.NAKED) {
			return CREEPER_GIRL_P4_LOCATION.getRight();
		}
		return CREEPER_GIRL_P4_LOCATION.getLeft();
	}
}
