package dev.dixmk.minepreggo.client.renderer.preggo.zombie;

import dev.dixmk.minepreggo.client.model.entity.preggo.zombie.AbstractZombieGirlModel;
import dev.dixmk.minepreggo.client.model.entity.preggo.zombie.TamableZombieGirlP3Model;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirlP3;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT) 
public class TamableZombieGirlP3Renderer extends AbstractTamablePregnantZombieGirlRenderer<TamableZombieGirlP3, TamableZombieGirlP3Model> {

	public TamableZombieGirlP3Renderer(EntityRendererProvider.Context context) {
		this(context, AbstractZombieGirlModel.LAYER_LOCATION_P3, AbstractZombieGirlModel.LAYER_INNER_ARMOR_LOCATION, AbstractZombieGirlModel.LAYER_OUTER_ARMOR_LOCATION);
	}
	
	public TamableZombieGirlP3Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter) {
		super(context, new TamableZombieGirlP3Model(context.bakeLayer(main)), new TamableZombieGirlP3Model(context.bakeLayer(inner)), new TamableZombieGirlP3Model(context.bakeLayer(outter)));
	}

	@Override
	public ResourceLocation getTextureLocation(TamableZombieGirlP3 p_115812_) {
		return ZOMBIE_GIRL_P3_LOCATION;
	}
}
