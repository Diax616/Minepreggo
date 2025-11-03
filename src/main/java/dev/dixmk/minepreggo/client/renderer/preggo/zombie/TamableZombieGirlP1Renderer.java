package dev.dixmk.minepreggo.client.renderer.preggo.zombie;

import dev.dixmk.minepreggo.client.model.entity.preggo.zombie.AbstractZombieGirlModel;
import dev.dixmk.minepreggo.client.model.entity.preggo.zombie.TamableZombieGirlP1Model;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirlP1;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT) 
public class TamableZombieGirlP1Renderer extends AbstractTamablePregnantZombieGirlRenderer<TamableZombieGirlP1, TamableZombieGirlP1Model> {

	public TamableZombieGirlP1Renderer(EntityRendererProvider.Context context) {
		this(context, AbstractZombieGirlModel.LAYER_LOCATION_P1, AbstractZombieGirlModel.LAYER_INNER_ARMOR_LOCATION, AbstractZombieGirlModel.LAYER_OUTER_ARMOR_LOCATION);
	}
	
	public TamableZombieGirlP1Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter) {
		super(context, new TamableZombieGirlP1Model(context.bakeLayer(main)), new TamableZombieGirlP1Model(context.bakeLayer(inner)), new TamableZombieGirlP1Model(context.bakeLayer(outter)));
	}

	@Override
	public ResourceLocation getTextureLocation(TamableZombieGirlP1 p_115812_) {
		return ZOMBIE_GIRL_P1_LOCATION;
	}
}
