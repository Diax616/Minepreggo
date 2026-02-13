package dev.dixmk.minepreggo.client.renderer.preggo.creeper;

import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractHumanoidCreeperGirlModel;
import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.HostileHumanoidCreeperGirlModel;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.HostileHumanoidCreeperGirl;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MonsterHumanoidCreeperGirlRenderer extends AbstractHostileHumanoidCreeperGirlRenderer<HostileHumanoidCreeperGirl, HostileHumanoidCreeperGirlModel> {
	
	public MonsterHumanoidCreeperGirlRenderer(EntityRendererProvider.Context context) {
		this(context, AbstractHumanoidCreeperGirlModel.LAYER_LOCATION, AbstractHumanoidCreeperGirlModel.LAYER_INNER_ARMOR_LOCATION, AbstractHumanoidCreeperGirlModel.LAYER_OUTER_ARMOR_LOCATION, AbstractHumanoidCreeperGirlModel.LAYER_ENERGY_ARMOR_LOCATION);
	}
	
	public MonsterHumanoidCreeperGirlRenderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter, ModelLayerLocation armor) {
		super(context, new HostileHumanoidCreeperGirlModel(context.bakeLayer(main)), new HostileHumanoidCreeperGirlModel(context.bakeLayer(inner)), new HostileHumanoidCreeperGirlModel(context.bakeLayer(outter)), new HostileHumanoidCreeperGirlModel(context.bakeLayer(armor)));
	}

	@Override
	public ResourceLocation getTextureLocation(HostileHumanoidCreeperGirl p_115812_) {
		return CREEPER_GIRL_LOCATION;
	}
}
