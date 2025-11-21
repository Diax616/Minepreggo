package dev.dixmk.minepreggo.client.renderer.preggo.zombie;

import dev.dixmk.minepreggo.client.model.entity.preggo.zombie.AbstractZombieGirlModel;
import dev.dixmk.minepreggo.client.model.entity.preggo.zombie.TamableZombieGirlModel;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirl;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TamableZombieGirlRenderer extends AbstractTamableZombieGirlRenderer<TamableZombieGirl, TamableZombieGirlModel> {

	public TamableZombieGirlRenderer(EntityRendererProvider.Context context) {
		this(context, AbstractZombieGirlModel.LAYER_LOCATION, AbstractZombieGirlModel.LAYER_INNER_ARMOR_LOCATION, AbstractZombieGirlModel.LAYER_OUTER_ARMOR_LOCATION);
	}
	
	public TamableZombieGirlRenderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter) {
		super(context, new TamableZombieGirlModel(context.bakeLayer(main)), new TamableZombieGirlModel(context.bakeLayer(inner)), new TamableZombieGirlModel(context.bakeLayer(outter)));
	}
	
	@Override
	public ResourceLocation getTextureLocation(TamableZombieGirl p_115812_) {
		return ZOMBIE_GIRL_LOCATION;
	}
}
