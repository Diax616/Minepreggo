package dev.dixmk.minepreggo.client.renderer.preggo.zombie;

import dev.dixmk.minepreggo.client.model.entity.preggo.zombie.HostileZombieGirlModel;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.HostileZombieGirl;

import dev.dixmk.minepreggo.client.model.entity.preggo.zombie.AbstractZombieGirlModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HostileZombieGirlRenderer extends AbstractHostileZombieGirlRenderer<HostileZombieGirl, HostileZombieGirlModel> {

	public HostileZombieGirlRenderer(EntityRendererProvider.Context context) {
		this(context, AbstractZombieGirlModel.LAYER_LOCATION, AbstractZombieGirlModel.LAYER_INNER_ARMOR_LOCATION, AbstractZombieGirlModel.LAYER_OUTER_ARMOR_LOCATION);
	}
	
	public HostileZombieGirlRenderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter) {
		super(context, new HostileZombieGirlModel(context.bakeLayer(main)), new HostileZombieGirlModel(context.bakeLayer(inner)), new HostileZombieGirlModel(context.bakeLayer(outter)));
	}

	@Override
	public ResourceLocation getTextureLocation(HostileZombieGirl p_115812_) {
		return ZOMBIE_GIRL_LOCATION;
	}
}
