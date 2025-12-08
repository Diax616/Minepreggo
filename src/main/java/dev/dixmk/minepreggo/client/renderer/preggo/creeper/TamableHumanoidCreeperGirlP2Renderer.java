package dev.dixmk.minepreggo.client.renderer.preggo.creeper;

import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractHumanoidCreeperGirlModel;
import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.TamableHumanoidCreeperGirlP2Model;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableHumanoidCreeperGirlP2;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TamableHumanoidCreeperGirlP2Renderer extends AbstractTamableHumanoidPregnantCreeperGirlRenderer<TamableHumanoidCreeperGirlP2, TamableHumanoidCreeperGirlP2Model> {
	
	public TamableHumanoidCreeperGirlP2Renderer(EntityRendererProvider.Context context) {
		this(context, AbstractHumanoidCreeperGirlModel.LAYER_LOCATION_P2, AbstractHumanoidCreeperGirlModel.LAYER_INNER_ARMOR_LOCATION, AbstractHumanoidCreeperGirlModel.LAYER_OUTER_ARMOR_LOCATION, AbstractHumanoidCreeperGirlModel.LAYER_ENERGY_ARMOR_P2_LOCATION);
	}
	
	public TamableHumanoidCreeperGirlP2Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter, ModelLayerLocation armor) {
		super(context, new TamableHumanoidCreeperGirlP2Model(context.bakeLayer(main)), new TamableHumanoidCreeperGirlP2Model(context.bakeLayer(inner)), new TamableHumanoidCreeperGirlP2Model(context.bakeLayer(outter)), new TamableHumanoidCreeperGirlP2Model(context.bakeLayer(armor)));
	}

	@Override
	public ResourceLocation getTextureLocation(TamableHumanoidCreeperGirlP2 p_115812_) {
		return CREEPER_GIRL_P2_LOCATION;
	}

}
