package dev.dixmk.minepreggo.client.renderer.preggo.creeper;

import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.HostileHumanoidCreeperGirlModel;
import dev.dixmk.minepreggo.client.model.geom.MinepreggoModelLayers;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.HostileHumanoidCreeperGirl;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MonsterHumanoidCreeperGirlRenderer extends AbstractHostileHumanoidCreeperGirlRenderer<HostileHumanoidCreeperGirl, HostileHumanoidCreeperGirlModel> {
	
	public MonsterHumanoidCreeperGirlRenderer(EntityRendererProvider.Context context) {
		this(context, MinepreggoModelLayers.HUMANOID_CREEPER_GIRL, MinepreggoModelLayers.HUMANOID_CREEPER_GIRL_INNER_ARMOR, MinepreggoModelLayers.HUMANOID_CREEPER_GIRL_OUTER_ARMOR, MinepreggoModelLayers.HUMANOID_CREEPER_GIRL_ENERGY_ARMOR);
	}
	
	public MonsterHumanoidCreeperGirlRenderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter, ModelLayerLocation armor) {
		super(context, new HostileHumanoidCreeperGirlModel(context.bakeLayer(main)), new HostileHumanoidCreeperGirlModel(context.bakeLayer(inner)), new HostileHumanoidCreeperGirlModel(context.bakeLayer(outter)), new HostileHumanoidCreeperGirlModel(context.bakeLayer(armor)));
	}

	@Override
	public ResourceLocation getTextureLocation(HostileHumanoidCreeperGirl entity) {
		return CREEPER_GIRL_LOCATION;
	}
}
