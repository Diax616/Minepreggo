package dev.dixmk.minepreggo.client.renderer.preggo.ender;

import dev.dixmk.minepreggo.client.model.entity.preggo.ender.TamablePregnantMonsterEnderWomanModel;
import dev.dixmk.minepreggo.client.model.geom.MinepreggoModelLayers;
import dev.dixmk.minepreggo.world.entity.preggo.ender.TamablePregnantMonsterEnderWoman;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TamablePregnantMonsterEnderWomanRenderer  {

	private TamablePregnantMonsterEnderWomanRenderer() {}
	
	@OnlyIn(Dist.CLIENT)
	public static class TamableMonsterEnderWomanP0Renderer extends AbstractTamablePregnantMonsterEnderWomanRenderer<TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP0, TamablePregnantMonsterEnderWomanModel.TamableMonsterEnderWomanP0Model> {

		public TamableMonsterEnderWomanP0Renderer(EntityRendererProvider.Context context) {
			this(context, MinepreggoModelLayers.PREGNANT_MONSTER_ENDER_WOMAN_P0, MinepreggoModelLayers.MONSTER_ENDER_WOMAN_INNER_ARMOR, MinepreggoModelLayers.MONSTER_ENDER_WOMAN_OUTER_ARMOR);
		}
		
		public TamableMonsterEnderWomanP0Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter) {
			super(context, new TamablePregnantMonsterEnderWomanModel.TamableMonsterEnderWomanP0Model(context.bakeLayer(main)), new TamablePregnantMonsterEnderWomanModel.TamableMonsterEnderWomanP0Model(context.bakeLayer(inner)), new TamablePregnantMonsterEnderWomanModel.TamableMonsterEnderWomanP0Model(context.bakeLayer(outter)));
		}
		
		@Override
		public ResourceLocation getTextureLocation(TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP0 entity) {
			return MONSTER_PREGNANT_ENDER_WOMAN_P0_TEXTURE;
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class TamableMonsterEnderWomanP1Renderer extends AbstractTamablePregnantMonsterEnderWomanRenderer<TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP1, TamablePregnantMonsterEnderWomanModel.TamableMonsterEnderWomanP1Model> {

		public TamableMonsterEnderWomanP1Renderer(EntityRendererProvider.Context context) {
			this(context, MinepreggoModelLayers.PREGNANT_MONSTER_ENDER_WOMAN_P1, MinepreggoModelLayers.MONSTER_ENDER_WOMAN_INNER_ARMOR, MinepreggoModelLayers.MONSTER_ENDER_WOMAN_OUTER_ARMOR);
		}
		
		public TamableMonsterEnderWomanP1Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter) {
			super(context, new TamablePregnantMonsterEnderWomanModel.TamableMonsterEnderWomanP1Model(context.bakeLayer(main)), new TamablePregnantMonsterEnderWomanModel.TamableMonsterEnderWomanP1Model(context.bakeLayer(inner)), new TamablePregnantMonsterEnderWomanModel.TamableMonsterEnderWomanP1Model(context.bakeLayer(outter)));
		}
		
		@Override
		public ResourceLocation getTextureLocation(TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP1 entity) {
			return MONSTER_PREGNANT_ENDER_WOMAN_P1_TEXTURE;
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class TamableMonsterEnderWomanP2Renderer extends AbstractTamablePregnantMonsterEnderWomanRenderer<TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP2, TamablePregnantMonsterEnderWomanModel.TamableMonsterEnderWomanP2Model> {

		public TamableMonsterEnderWomanP2Renderer(EntityRendererProvider.Context context) {
			this(context, MinepreggoModelLayers.PREGNANT_MONSTER_ENDER_WOMAN_P2, MinepreggoModelLayers.MONSTER_ENDER_WOMAN_INNER_ARMOR, MinepreggoModelLayers.MONSTER_ENDER_WOMAN_OUTER_ARMOR);
		}
		
		public TamableMonsterEnderWomanP2Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter) {
			super(context, new TamablePregnantMonsterEnderWomanModel.TamableMonsterEnderWomanP2Model(context.bakeLayer(main)), new TamablePregnantMonsterEnderWomanModel.TamableMonsterEnderWomanP2Model(context.bakeLayer(inner)), new TamablePregnantMonsterEnderWomanModel.TamableMonsterEnderWomanP2Model(context.bakeLayer(outter)));
		}
		
		@Override
		public ResourceLocation getTextureLocation(TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP2 entity) {
			return MONSTER_PREGNANT_ENDER_WOMAN_P2_TEXTURE;
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class TamableMonsterEnderWomanP3Renderer extends AbstractTamablePregnantMonsterEnderWomanRenderer<TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP3, TamablePregnantMonsterEnderWomanModel.TamableMonsterEnderWomanP3Model> {

		public TamableMonsterEnderWomanP3Renderer(EntityRendererProvider.Context context) {
			this(context, MinepreggoModelLayers.PREGNANT_MONSTER_ENDER_WOMAN_P3, MinepreggoModelLayers.MONSTER_ENDER_WOMAN_INNER_ARMOR, MinepreggoModelLayers.MONSTER_ENDER_WOMAN_OUTER_ARMOR);
		}
		
		public TamableMonsterEnderWomanP3Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter) {
			super(context, new TamablePregnantMonsterEnderWomanModel.TamableMonsterEnderWomanP3Model(context.bakeLayer(main)), new TamablePregnantMonsterEnderWomanModel.TamableMonsterEnderWomanP3Model(context.bakeLayer(inner)), new TamablePregnantMonsterEnderWomanModel.TamableMonsterEnderWomanP3Model(context.bakeLayer(outter)));
		}
		
		@Override
		public ResourceLocation getTextureLocation(TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP3 entity) {
			return MONSTER_PREGNANT_ENDER_WOMAN_P3_TEXTURE;
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class TamableMonsterEnderWomanP4Renderer extends AbstractTamablePregnantMonsterEnderWomanRenderer<TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP4, TamablePregnantMonsterEnderWomanModel.TamableMonsterEnderWomanP4Model> {

		public TamableMonsterEnderWomanP4Renderer(EntityRendererProvider.Context context) {
			this(context, MinepreggoModelLayers.PREGNANT_MONSTER_ENDER_WOMAN_P4, MinepreggoModelLayers.MONSTER_ENDER_WOMAN_INNER_ARMOR, MinepreggoModelLayers.MONSTER_ENDER_WOMAN_OUTER_ARMOR);
		}
		
		public TamableMonsterEnderWomanP4Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter) {
			super(context, new TamablePregnantMonsterEnderWomanModel.TamableMonsterEnderWomanP4Model(context.bakeLayer(main)), new TamablePregnantMonsterEnderWomanModel.TamableMonsterEnderWomanP4Model(context.bakeLayer(inner)), new TamablePregnantMonsterEnderWomanModel.TamableMonsterEnderWomanP4Model(context.bakeLayer(outter)));
		}
		
		@Override
		public ResourceLocation getTextureLocation(TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP4 entity) {
			return MONSTER_PREGNANT_ENDER_WOMAN_P4_TEXTURE;
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class TamableMonsterEnderWomanP5Renderer extends AbstractTamablePregnantMonsterEnderWomanRenderer<TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP5, TamablePregnantMonsterEnderWomanModel.TamableMonsterEnderWomanP5Model> {

		public TamableMonsterEnderWomanP5Renderer(EntityRendererProvider.Context context) {
			this(context, MinepreggoModelLayers.PREGNANT_MONSTER_ENDER_WOMAN_P5, MinepreggoModelLayers.MONSTER_ENDER_WOMAN_INNER_ARMOR, MinepreggoModelLayers.MONSTER_ENDER_WOMAN_OUTER_ARMOR);
		}
		
		public TamableMonsterEnderWomanP5Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter) {
			super(context, new TamablePregnantMonsterEnderWomanModel.TamableMonsterEnderWomanP5Model(context.bakeLayer(main)), new TamablePregnantMonsterEnderWomanModel.TamableMonsterEnderWomanP5Model(context.bakeLayer(inner)), new TamablePregnantMonsterEnderWomanModel.TamableMonsterEnderWomanP5Model(context.bakeLayer(outter)));
		}
		
		@Override
		public ResourceLocation getTextureLocation(TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP5 entity) {
			return MONSTER_PREGNANT_ENDER_WOMAN_P5_TEXTURE;
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class TamableMonsterEnderWomanP6Renderer extends AbstractTamablePregnantMonsterEnderWomanRenderer<TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP6, TamablePregnantMonsterEnderWomanModel.TamableMonsterEnderWomanP6Model> {

		public TamableMonsterEnderWomanP6Renderer(EntityRendererProvider.Context context) {
			this(context, MinepreggoModelLayers.PREGNANT_MONSTER_ENDER_WOMAN_P6, MinepreggoModelLayers.MONSTER_ENDER_WOMAN_INNER_ARMOR, MinepreggoModelLayers.MONSTER_ENDER_WOMAN_OUTER_ARMOR);
		}
		
		public TamableMonsterEnderWomanP6Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter) {
			super(context, new TamablePregnantMonsterEnderWomanModel.TamableMonsterEnderWomanP6Model(context.bakeLayer(main)), new TamablePregnantMonsterEnderWomanModel.TamableMonsterEnderWomanP6Model(context.bakeLayer(inner)), new TamablePregnantMonsterEnderWomanModel.TamableMonsterEnderWomanP6Model(context.bakeLayer(outter)));
		}
		
		@Override
		public ResourceLocation getTextureLocation(TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP6 entity) {
			return MONSTER_PREGNANT_ENDER_WOMAN_P6_TEXTURE;
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class TamableMonsterEnderWomanP7Renderer extends AbstractTamablePregnantMonsterEnderWomanRenderer<TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP7, TamablePregnantMonsterEnderWomanModel.TamableMonsterEnderWomanP7Model> {

		public TamableMonsterEnderWomanP7Renderer(EntityRendererProvider.Context context) {
			this(context, MinepreggoModelLayers.PREGNANT_MONSTER_ENDER_WOMAN_P7, MinepreggoModelLayers.MONSTER_ENDER_WOMAN_INNER_ARMOR, MinepreggoModelLayers.MONSTER_ENDER_WOMAN_OUTER_ARMOR);
		}
		
		public TamableMonsterEnderWomanP7Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter) {
			super(context, new TamablePregnantMonsterEnderWomanModel.TamableMonsterEnderWomanP7Model(context.bakeLayer(main)), new TamablePregnantMonsterEnderWomanModel.TamableMonsterEnderWomanP7Model(context.bakeLayer(inner)), new TamablePregnantMonsterEnderWomanModel.TamableMonsterEnderWomanP7Model(context.bakeLayer(outter)));
		}
		
		@Override
		public ResourceLocation getTextureLocation(TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP7 entity) {
			return MONSTER_PREGNANT_ENDER_WOMAN_P7_TEXTURE;
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class TamableMonsterEnderWomanP8Renderer extends AbstractTamablePregnantMonsterEnderWomanRenderer<TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP8, TamablePregnantMonsterEnderWomanModel.TamableMonsterEnderWomanP8Model> {

		public TamableMonsterEnderWomanP8Renderer(EntityRendererProvider.Context context) {
			this(context, MinepreggoModelLayers.PREGNANT_MONSTER_ENDER_WOMAN_P8, MinepreggoModelLayers.MONSTER_ENDER_WOMAN_INNER_ARMOR, MinepreggoModelLayers.MONSTER_ENDER_WOMAN_OUTER_ARMOR);
		}
		
		public TamableMonsterEnderWomanP8Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter) {
			super(context, new TamablePregnantMonsterEnderWomanModel.TamableMonsterEnderWomanP8Model(context.bakeLayer(main)), new TamablePregnantMonsterEnderWomanModel.TamableMonsterEnderWomanP8Model(context.bakeLayer(inner)), new TamablePregnantMonsterEnderWomanModel.TamableMonsterEnderWomanP8Model(context.bakeLayer(outter)));
		}
		
		@Override
		public ResourceLocation getTextureLocation(TamablePregnantMonsterEnderWoman.TamableMonsterEnderWomanP8 entity) {
			return MONSTER_PREGNANT_ENDER_WOMAN_P8_TEXTURE;
		}
	}
}
