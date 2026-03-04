package dev.dixmk.minepreggo.client.renderer.preggo.zombie;

import javax.annotation.Nullable;

import dev.dixmk.minepreggo.client.model.entity.preggo.zombie.TamableZombieGirlModel;
import dev.dixmk.minepreggo.client.model.geom.MinepreggoModelLayers;
import dev.dixmk.minepreggo.client.renderer.entity.layers.preggo.zombie.TamableZombieGirlExpressionLayer;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirl;
import dev.dixmk.minepreggo.world.pregnancy.PostPregnancy;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TamableZombieGirlRenderer extends AbstractTamableZombieGirlRenderer<TamableZombieGirl, TamableZombieGirlModel> {

	public TamableZombieGirlRenderer(EntityRendererProvider.Context context) {
		this(context, MinepreggoModelLayers.ZOMBIE_GIRL, MinepreggoModelLayers.ZOMBIE_GIRL_INNER_ARMOR, MinepreggoModelLayers.ZOMBIE_GIRL_OUTER_ARMOR);
	}
	
	public TamableZombieGirlRenderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter) {
		super(context, new TamableZombieGirlModel(context.bakeLayer(main)), new TamableZombieGirlModel(context.bakeLayer(inner)), new TamableZombieGirlModel(context.bakeLayer(outter)));
	}
	
	@Override
	public ResourceLocation getTextureLocation(TamableZombieGirl entity) {
		return ZOMBIE_GIRL_LOCATION;
	}
		
	@Override
	protected TamableZombieGirlExpressionLayer<TamableZombieGirl, TamableZombieGirlModel> createExpressiveFaceLayer() {
		return new TamableZombieGirlExpressionLayer<>(this) {
			@Override
			public @Nullable RenderType renderType(TamableZombieGirl zombieGirl) {	
				PostPregnancy post = zombieGirl.getSyncedPostPregnancy().orElse(null);
				if (post == PostPregnancy.MISCARRIAGE) {
					return POST_MISCARRIAGE;
				}		
				var result = super.renderType(zombieGirl);
				if (result != null) {
					return result;
				}			
				else if (post == PostPregnancy.PARTUM) {
					return POST_PARTUM;
				}
				return null;
			}
		};
	}
}
