package dev.dixmk.minepreggo.client.renderer.preggo.ender;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import dev.dixmk.minepreggo.client.model.entity.preggo.ender.AbstractEnderWomanModel;
import dev.dixmk.minepreggo.client.model.entity.preggo.ender.TamableMonsterEnderWomanModel;
import dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.ender.MonsterEnderWomanExpressionLayer;
import dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.ender.MonsterEnderWomanExpressiveEyesLayer;
import dev.dixmk.minepreggo.world.entity.preggo.ender.TamableMonsterEnderWoman;
import dev.dixmk.minepreggo.world.pregnancy.PostPregnancy;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TamableMonsterEnderWomanRenderer extends AbstractTamableMonsterEnderWomanRenderer<TamableMonsterEnderWoman, TamableMonsterEnderWomanModel> {
	
	public TamableMonsterEnderWomanRenderer(EntityRendererProvider.Context context) {
		this(context, AbstractEnderWomanModel.LAYER_LOCATION, AbstractEnderWomanModel.LAYER_INNER_ARMOR_LOCATION, AbstractEnderWomanModel.LAYER_OUTER_ARMOR_LOCATION);
	}
	
	public TamableMonsterEnderWomanRenderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter) {
		super(context, new TamableMonsterEnderWomanModel(context.bakeLayer(main)), new TamableMonsterEnderWomanModel(context.bakeLayer(inner)), new TamableMonsterEnderWomanModel(context.bakeLayer(outter)));
	}
	
	@Override
	public ResourceLocation getTextureLocation(TamableMonsterEnderWoman p_115812_) {
		return MONSTER_ENDER_GIRL_LOCATION;
	}	
	
	@Override
	protected MonsterEnderWomanExpressionLayer<TamableMonsterEnderWoman, TamableMonsterEnderWomanModel> createExpressiveFaceLayer() {
		return new MonsterEnderWomanExpressionLayer<>(this) {
			@Override
			public @Nullable RenderType renderType(TamableMonsterEnderWoman enderWoman) {	
				PostPregnancy post = enderWoman.getSyncedPostPregnancy().orElse(null);
				if (post == PostPregnancy.MISCARRIAGE) {
					return VERY_SAD_MASK;
				}		
				return super.renderType(enderWoman);
			}
		};
	}
	
	@Override
	protected MonsterEnderWomanExpressiveEyesLayer<TamableMonsterEnderWoman, TamableMonsterEnderWomanModel> createExpressiveEyesLayer() {
		return new MonsterEnderWomanExpressiveEyesLayer<>(this) {
			@Override
			public @Nonnull RenderType renderType(TamableMonsterEnderWoman enderWoman) {	
				PostPregnancy post = enderWoman.getSyncedPostPregnancy().orElse(null);
				if (post == PostPregnancy.MISCARRIAGE) {
					return SAD_ENDER_EYES_3;
				}		
				return super.renderType(enderWoman);
			}
		};
	}
}
