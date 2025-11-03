package dev.dixmk.minepreggo.client.renderer.preggo.zombie;

import dev.dixmk.minepreggo.client.model.entity.preggo.zombie.AbstractZombieGirlModel;
import dev.dixmk.minepreggo.client.model.entity.preggo.zombie.TamableZombieGirlP2Model;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirlP2;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT) 
public class TamableZombieGirlP2Renderer extends AbstractTamablePregnantZombieGirlRenderer<TamableZombieGirlP2, TamableZombieGirlP2Model> {

	public TamableZombieGirlP2Renderer(EntityRendererProvider.Context context) {
		this(context, AbstractZombieGirlModel.LAYER_LOCATION_P2, AbstractZombieGirlModel.LAYER_INNER_ARMOR_LOCATION, AbstractZombieGirlModel.LAYER_OUTER_ARMOR_LOCATION);
	}
	
	public TamableZombieGirlP2Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter) {
		super(context, new TamableZombieGirlP2Model(context.bakeLayer(main)), new TamableZombieGirlP2Model(context.bakeLayer(inner)), new TamableZombieGirlP2Model(context.bakeLayer(outter)));
	}

	@Override
	public ResourceLocation getTextureLocation(TamableZombieGirlP2 p_115812_) {
		return ZOMBIE_GIRL_P2_LOCATION;
	}
}
