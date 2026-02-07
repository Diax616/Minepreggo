package dev.dixmk.minepreggo.client.renderer.preggo.ender;

import dev.dixmk.minepreggo.client.model.entity.preggo.ender.AbstractEnderWomanModel;
import dev.dixmk.minepreggo.client.model.entity.preggo.ender.TamablePregnantMonsterEnderWomanModel;
import dev.dixmk.minepreggo.world.entity.preggo.ender.TamablePregnantMonsterEnderWoman;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TamablePregnantMonsterEnderWomanRenderer  {

	@OnlyIn(Dist.CLIENT)
	public static class TamableMonsterEnderWomanP0Renderer extends AbstractTamablePregnantMonsterEnderWomanRenderer<TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP0, TamablePregnantMonsterEnderWomanModel.TamableMonsterEnderWomanP0Model> {

		public TamableMonsterEnderWomanP0Renderer(EntityRendererProvider.Context context) {
			this(context, AbstractEnderWomanModel.LAYER_LOCATION_P0, AbstractEnderWomanModel.LAYER_INNER_ARMOR_LOCATION, AbstractEnderWomanModel.LAYER_OUTER_ARMOR_LOCATION);
		}
		
		public TamableMonsterEnderWomanP0Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter) {
			super(context, new TamablePregnantMonsterEnderWomanModel.TamableMonsterEnderWomanP0Model(context.bakeLayer(main)), new TamablePregnantMonsterEnderWomanModel.TamableMonsterEnderWomanP0Model(context.bakeLayer(inner)), new TamablePregnantMonsterEnderWomanModel.TamableMonsterEnderWomanP0Model(context.bakeLayer(outter)));
		}
		
		@Override
		public ResourceLocation getTextureLocation(TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP0 p_115812_) {
			return MONSTER_PREGNANT_ENDER_WOMAN_P0_TEXTURE;
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class TamableMonsterEnderWomanP1Renderer extends AbstractTamablePregnantMonsterEnderWomanRenderer<TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP1, TamablePregnantMonsterEnderWomanModel.TamableMonsterEnderWomanP1Model> {

		public TamableMonsterEnderWomanP1Renderer(EntityRendererProvider.Context context) {
			this(context, AbstractEnderWomanModel.LAYER_LOCATION_P1, AbstractEnderWomanModel.LAYER_INNER_ARMOR_LOCATION, AbstractEnderWomanModel.LAYER_OUTER_ARMOR_LOCATION);
		}
		
		public TamableMonsterEnderWomanP1Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter) {
			super(context, new TamablePregnantMonsterEnderWomanModel.TamableMonsterEnderWomanP1Model(context.bakeLayer(main)), new TamablePregnantMonsterEnderWomanModel.TamableMonsterEnderWomanP1Model(context.bakeLayer(inner)), new TamablePregnantMonsterEnderWomanModel.TamableMonsterEnderWomanP1Model(context.bakeLayer(outter)));
		}
		
		@Override
		public ResourceLocation getTextureLocation(TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP1 p_115812_) {
			return MONSTER_PREGNANT_ENDER_WOMAN_P1_TEXTURE;
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class TamableMonsterEnderWomanP2Renderer extends AbstractTamablePregnantMonsterEnderWomanRenderer<TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP2, TamablePregnantMonsterEnderWomanModel.TamableMonsterEnderWomanP2Model> {

		public TamableMonsterEnderWomanP2Renderer(EntityRendererProvider.Context context) {
			this(context, AbstractEnderWomanModel.LAYER_LOCATION_P2, AbstractEnderWomanModel.LAYER_INNER_ARMOR_LOCATION, AbstractEnderWomanModel.LAYER_OUTER_ARMOR_LOCATION);
		}
		
		public TamableMonsterEnderWomanP2Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter) {
			super(context, new TamablePregnantMonsterEnderWomanModel.TamableMonsterEnderWomanP2Model(context.bakeLayer(main)), new TamablePregnantMonsterEnderWomanModel.TamableMonsterEnderWomanP2Model(context.bakeLayer(inner)), new TamablePregnantMonsterEnderWomanModel.TamableMonsterEnderWomanP2Model(context.bakeLayer(outter)));
		}
		
		@Override
		public ResourceLocation getTextureLocation(TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP2 p_115812_) {
			return MONSTER_PREGNANT_ENDER_WOMAN_P2_TEXTURE;
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class TamableMonsterEnderWomanP3Renderer extends AbstractTamablePregnantMonsterEnderWomanRenderer<TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP3, TamablePregnantMonsterEnderWomanModel.TamableMonsterEnderWomanP3Model> {

		public TamableMonsterEnderWomanP3Renderer(EntityRendererProvider.Context context) {
			this(context, AbstractEnderWomanModel.LAYER_LOCATION_P3, AbstractEnderWomanModel.LAYER_INNER_ARMOR_LOCATION, AbstractEnderWomanModel.LAYER_OUTER_ARMOR_LOCATION);
		}
		
		public TamableMonsterEnderWomanP3Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter) {
			super(context, new TamablePregnantMonsterEnderWomanModel.TamableMonsterEnderWomanP3Model(context.bakeLayer(main)), new TamablePregnantMonsterEnderWomanModel.TamableMonsterEnderWomanP3Model(context.bakeLayer(inner)), new TamablePregnantMonsterEnderWomanModel.TamableMonsterEnderWomanP3Model(context.bakeLayer(outter)));
		}
		
		@Override
		public ResourceLocation getTextureLocation(TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP3 p_115812_) {
			return MONSTER_PREGNANT_ENDER_WOMAN_P3_TEXTURE;
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class TamableMonsterEnderWomanP4Renderer extends AbstractTamablePregnantMonsterEnderWomanRenderer<TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP4, TamablePregnantMonsterEnderWomanModel.TamableMonsterEnderWomanP4Model> {

		public TamableMonsterEnderWomanP4Renderer(EntityRendererProvider.Context context) {
			this(context, AbstractEnderWomanModel.LAYER_LOCATION_P4, AbstractEnderWomanModel.LAYER_INNER_ARMOR_LOCATION, AbstractEnderWomanModel.LAYER_OUTER_ARMOR_LOCATION);
		}
		
		public TamableMonsterEnderWomanP4Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter) {
			super(context, new TamablePregnantMonsterEnderWomanModel.TamableMonsterEnderWomanP4Model(context.bakeLayer(main)), new TamablePregnantMonsterEnderWomanModel.TamableMonsterEnderWomanP4Model(context.bakeLayer(inner)), new TamablePregnantMonsterEnderWomanModel.TamableMonsterEnderWomanP4Model(context.bakeLayer(outter)));
		}
		
		@Override
		public ResourceLocation getTextureLocation(TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP4 p_115812_) {
			return MONSTER_PREGNANT_ENDER_WOMAN_P4_TEXTURE;
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class TamableMonsterEnderWomanP5Renderer extends AbstractTamablePregnantMonsterEnderWomanRenderer<TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP5, TamablePregnantMonsterEnderWomanModel.TamableMonsterEnderWomanP5Model> {

		public TamableMonsterEnderWomanP5Renderer(EntityRendererProvider.Context context) {
			this(context, AbstractEnderWomanModel.LAYER_LOCATION_P5, AbstractEnderWomanModel.LAYER_INNER_ARMOR_LOCATION, AbstractEnderWomanModel.LAYER_OUTER_ARMOR_LOCATION);
		}
		
		public TamableMonsterEnderWomanP5Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter) {
			super(context, new TamablePregnantMonsterEnderWomanModel.TamableMonsterEnderWomanP5Model(context.bakeLayer(main)), new TamablePregnantMonsterEnderWomanModel.TamableMonsterEnderWomanP5Model(context.bakeLayer(inner)), new TamablePregnantMonsterEnderWomanModel.TamableMonsterEnderWomanP5Model(context.bakeLayer(outter)));
		}
		
		@Override
		public ResourceLocation getTextureLocation(TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP5 p_115812_) {
			return MONSTER_PREGNANT_ENDER_WOMAN_P5_TEXTURE;
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class TamableMonsterEnderWomanP6Renderer extends AbstractTamablePregnantMonsterEnderWomanRenderer<TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP6, TamablePregnantMonsterEnderWomanModel.TamableMonsterEnderWomanP6Model> {

		public TamableMonsterEnderWomanP6Renderer(EntityRendererProvider.Context context) {
			this(context, AbstractEnderWomanModel.LAYER_LOCATION_P6, AbstractEnderWomanModel.LAYER_INNER_ARMOR_LOCATION, AbstractEnderWomanModel.LAYER_OUTER_ARMOR_LOCATION);
		}
		
		public TamableMonsterEnderWomanP6Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter) {
			super(context, new TamablePregnantMonsterEnderWomanModel.TamableMonsterEnderWomanP6Model(context.bakeLayer(main)), new TamablePregnantMonsterEnderWomanModel.TamableMonsterEnderWomanP6Model(context.bakeLayer(inner)), new TamablePregnantMonsterEnderWomanModel.TamableMonsterEnderWomanP6Model(context.bakeLayer(outter)));
		}
		
		@Override
		public ResourceLocation getTextureLocation(TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP6 p_115812_) {
			return MONSTER_PREGNANT_ENDER_WOMAN_P6_TEXTURE;
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class TamableMonsterEnderWomanP7Renderer extends AbstractTamablePregnantMonsterEnderWomanRenderer<TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP7, TamablePregnantMonsterEnderWomanModel.TamableMonsterEnderWomanP7Model> {

		public TamableMonsterEnderWomanP7Renderer(EntityRendererProvider.Context context) {
			this(context, AbstractEnderWomanModel.LAYER_LOCATION_P7, AbstractEnderWomanModel.LAYER_INNER_ARMOR_LOCATION, AbstractEnderWomanModel.LAYER_OUTER_ARMOR_LOCATION);
		}
		
		public TamableMonsterEnderWomanP7Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter) {
			super(context, new TamablePregnantMonsterEnderWomanModel.TamableMonsterEnderWomanP7Model(context.bakeLayer(main)), new TamablePregnantMonsterEnderWomanModel.TamableMonsterEnderWomanP7Model(context.bakeLayer(inner)), new TamablePregnantMonsterEnderWomanModel.TamableMonsterEnderWomanP7Model(context.bakeLayer(outter)));
		}
		
		@Override
		public ResourceLocation getTextureLocation(TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP7 p_115812_) {
			return MONSTER_PREGNANT_ENDER_WOMAN_P7_TEXTURE;
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class TamableMonsterEnderWomanP8Renderer extends AbstractTamablePregnantMonsterEnderWomanRenderer<TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP8, TamablePregnantMonsterEnderWomanModel.TamableMonsterEnderWomanP8Model> {

		public TamableMonsterEnderWomanP8Renderer(EntityRendererProvider.Context context) {
			this(context, AbstractEnderWomanModel.LAYER_LOCATION_P8, AbstractEnderWomanModel.LAYER_INNER_ARMOR_LOCATION, AbstractEnderWomanModel.LAYER_OUTER_ARMOR_LOCATION);
		}
		
		public TamableMonsterEnderWomanP8Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter) {
			super(context, new TamablePregnantMonsterEnderWomanModel.TamableMonsterEnderWomanP8Model(context.bakeLayer(main)), new TamablePregnantMonsterEnderWomanModel.TamableMonsterEnderWomanP8Model(context.bakeLayer(inner)), new TamablePregnantMonsterEnderWomanModel.TamableMonsterEnderWomanP8Model(context.bakeLayer(outter)));
		}
		
		@Override
		public ResourceLocation getTextureLocation(TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP8 p_115812_) {
			return MONSTER_PREGNANT_ENDER_WOMAN_P8_TEXTURE;
		}
	}
}
