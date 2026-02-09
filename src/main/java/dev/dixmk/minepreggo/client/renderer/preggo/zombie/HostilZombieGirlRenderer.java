package dev.dixmk.minepreggo.client.renderer.preggo.zombie;

import dev.dixmk.minepreggo.client.model.entity.preggo.zombie.HostilZombieGirlModel;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.HostilZombieGirl;

import dev.dixmk.minepreggo.client.model.entity.preggo.zombie.AbstractZombieGirlModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HostilZombieGirlRenderer extends AbstractHostilZombieGirlRenderer<HostilZombieGirl, HostilZombieGirlModel> {

	public HostilZombieGirlRenderer(EntityRendererProvider.Context context) {
		this(context, AbstractZombieGirlModel.LAYER_LOCATION, AbstractZombieGirlModel.LAYER_INNER_ARMOR_LOCATION, AbstractZombieGirlModel.LAYER_OUTER_ARMOR_LOCATION);
	}
	
	public HostilZombieGirlRenderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter) {
		super(context, new HostilZombieGirlModel(context.bakeLayer(main)), new HostilZombieGirlModel(context.bakeLayer(inner)), new HostilZombieGirlModel(context.bakeLayer(outter)));
	}

	@Override
	public ResourceLocation getTextureLocation(HostilZombieGirl p_115812_) {
		return ZOMBIE_GIRL_LOCATION;
	}
}
