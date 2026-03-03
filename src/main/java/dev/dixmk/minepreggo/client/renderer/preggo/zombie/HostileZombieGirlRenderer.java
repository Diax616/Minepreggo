package dev.dixmk.minepreggo.client.renderer.preggo.zombie;

import dev.dixmk.minepreggo.client.model.entity.preggo.zombie.HostileZombieGirlModel;
import dev.dixmk.minepreggo.client.model.geom.MinepreggoModelLayers;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.HostileZombieGirl;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HostileZombieGirlRenderer extends AbstractHostileZombieGirlRenderer<HostileZombieGirl, HostileZombieGirlModel> {

	public HostileZombieGirlRenderer(EntityRendererProvider.Context context) {
		this(context, MinepreggoModelLayers.ZOMBIE_GIRL, MinepreggoModelLayers.ZOMBIE_GIRL_INNER_ARMOR, MinepreggoModelLayers.ZOMBIE_GIRL_OUTER_ARMOR);
	}
	
	public HostileZombieGirlRenderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter) {
		super(context, new HostileZombieGirlModel(context.bakeLayer(main)), new HostileZombieGirlModel(context.bakeLayer(inner)), new HostileZombieGirlModel(context.bakeLayer(outter)));
	}

	@Override
	public ResourceLocation getTextureLocation(HostileZombieGirl entity) {
		return ZOMBIE_GIRL_LOCATION;
	}
}
