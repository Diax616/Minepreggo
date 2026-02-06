package dev.dixmk.minepreggo.client.renderer.preggo.creeper;

import javax.annotation.Nullable;

import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractHumanoidCreeperGirlModel;
import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.TamableHumanoidCreeperGirlModel;
import dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.creeper.TamableHumanoidCreeperGirlExpressionLayer;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableHumanoidCreeperGirl;
import dev.dixmk.minepreggo.world.pregnancy.PostPregnancy;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TamableHumanoidCreeperGirlRenderer extends AbstractTamableHumanoidCreeperGirlRenderer<TamableHumanoidCreeperGirl, TamableHumanoidCreeperGirlModel> {
	
	public TamableHumanoidCreeperGirlRenderer(EntityRendererProvider.Context context) {
		this(context, AbstractHumanoidCreeperGirlModel.LAYER_LOCATION, AbstractHumanoidCreeperGirlModel.LAYER_INNER_ARMOR_LOCATION, AbstractHumanoidCreeperGirlModel.LAYER_OUTER_ARMOR_LOCATION, AbstractHumanoidCreeperGirlModel.LAYER_ENERGY_ARMOR_LOCATION);
	}
	
	public TamableHumanoidCreeperGirlRenderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter, ModelLayerLocation armor) {
		super(context, new TamableHumanoidCreeperGirlModel(context.bakeLayer(main)), new TamableHumanoidCreeperGirlModel(context.bakeLayer(inner)), new TamableHumanoidCreeperGirlModel(context.bakeLayer(outter)), new TamableHumanoidCreeperGirlModel(context.bakeLayer(armor)));
	}

	@Override
	public ResourceLocation getTextureLocation(TamableHumanoidCreeperGirl p_115812_) {
		return CREEPER_GIRL_LOCATION;
	}
	
	@Override
	protected void addFacialExpresions() {
		this.addLayer(new TamableHumanoidCreeperGirlExpressionLayer<>(this) {
			@Override
			public @Nullable RenderType renderType(TamableHumanoidCreeperGirl creeperGirl) {	
				var result = super.renderType(creeperGirl);
				if (result != null) {
					return result;
				}
				PostPregnancy post = creeperGirl.getSyncedPostPregnancy().orElse(null);
				if (post == PostPregnancy.MISCARRIAGE) {
					return POST_MISCARRIAGE;
				}
				else if (post == PostPregnancy.PARTUM) {
					return POST_PARTUM;
				}
				return null;
			}
		});
	}
}

