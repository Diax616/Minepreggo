package dev.dixmk.minepreggo.client.renderer.preggo.creeper;

import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractHumanoidCreeperGirlModel;
import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.TamableHumanoidCreeperGirlModel;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableHumanoidCreeperGirl;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TamableHumanoidCreeperGirlRenderer extends AbstractTamableHumanoidCreeperGirlRenderer<TamableHumanoidCreeperGirl, TamableHumanoidCreeperGirlModel> {
	
	public TamableHumanoidCreeperGirlRenderer(EntityRendererProvider.Context context) {
		this(context, AbstractHumanoidCreeperGirlModel.LAYER_LOCATION, AbstractHumanoidCreeperGirlModel.LAYER_INNER_ARMOR_LOCATION, AbstractHumanoidCreeperGirlModel.LAYER_OUTER_ARMOR_LOCATION, AbstractHumanoidCreeperGirlModel.LAYER_ENERGY_ARMOR_LOCATION);
	}
	
	public TamableHumanoidCreeperGirlRenderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter, ModelLayerLocation armor) {
		super(context, new TamableHumanoidCreeperGirlModel(context.bakeLayer(main)), new TamableHumanoidCreeperGirlModel(context.bakeLayer(inner)), new TamableHumanoidCreeperGirlModel(context.bakeLayer(outter)), new TamableHumanoidCreeperGirlModel(context.bakeLayer(armor)));
	}

	@Override
	public ResourceLocation getTextureLocation(TamableHumanoidCreeperGirl p_115812_) {
		return CREEPER_GIRL_LOCATION;
	}
}
