package dev.dixmk.minepreggo.client.renderer.preggo.creeper;

import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractMonsterCreeperGirlModel;
import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.TamablePregnantMonsterCreeperGirlModel;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamablePregnantMonsterCreeperGirl;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TamablePregnantMonsterCreeperGirlRenderer {

	@OnlyIn(Dist.CLIENT)
	public static class TamablePregnantCreeperGirlP0Renderer extends AbstractTamablePregnantMonsterCreeperGirlRenderer<TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP0, TamablePregnantMonsterCreeperGirlModel.TamableMonsterCreeperGirlP0Model> {
		
		public TamablePregnantCreeperGirlP0Renderer(EntityRendererProvider.Context context) {
			this(context, AbstractMonsterCreeperGirlModel.LAYER_LOCATION_P0, AbstractMonsterCreeperGirlModel.LAYER_ENERGY_ARMOR_P0_LOCATION, AbstractMonsterCreeperGirlModel.LAYER_OUTER_ARMOR_LOCATION);
		}
		
		protected TamablePregnantCreeperGirlP0Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation armor, ModelLayerLocation outter) {
			super(context, new TamablePregnantMonsterCreeperGirlModel.TamableMonsterCreeperGirlP0Model(context.bakeLayer(main)), new TamablePregnantMonsterCreeperGirlModel.TamableMonsterCreeperGirlP0Model(context.bakeLayer(armor)), new TamablePregnantMonsterCreeperGirlModel.TamableMonsterCreeperGirlP0Model(context.bakeLayer(outter)));
		}

		@Override
		public ResourceLocation getTextureLocation(TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP0 p_115812_) {
			return MONSTER_CREEPER_GIRL_P0_LOCATION;
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class TamablePregnantCreeperGirlP1Renderer extends AbstractTamablePregnantMonsterCreeperGirlRenderer<TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP1, TamablePregnantMonsterCreeperGirlModel.TamableMonsterCreeperGirlP1Model> {
		
		public TamablePregnantCreeperGirlP1Renderer(EntityRendererProvider.Context context) {
			this(context, AbstractMonsterCreeperGirlModel.LAYER_LOCATION_P1, AbstractMonsterCreeperGirlModel.LAYER_ENERGY_ARMOR_P1_LOCATION, AbstractMonsterCreeperGirlModel.LAYER_OUTER_ARMOR_LOCATION);
		}
		
		protected TamablePregnantCreeperGirlP1Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation armor, ModelLayerLocation outter) {
			super(context, new TamablePregnantMonsterCreeperGirlModel.TamableMonsterCreeperGirlP1Model(context.bakeLayer(main)), new TamablePregnantMonsterCreeperGirlModel.TamableMonsterCreeperGirlP1Model(context.bakeLayer(armor)), new TamablePregnantMonsterCreeperGirlModel.TamableMonsterCreeperGirlP1Model(context.bakeLayer(outter)));
		}

		@Override
		public ResourceLocation getTextureLocation(TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP1 p_115812_) {
			return MONSTER_CREEPER_GIRL_P1_LOCATION;
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class TamablePregnantCreeperGirlP2Renderer extends AbstractTamablePregnantMonsterCreeperGirlRenderer<TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP2, TamablePregnantMonsterCreeperGirlModel.TamableMonsterCreeperGirlP2Model> {
		
		public TamablePregnantCreeperGirlP2Renderer(EntityRendererProvider.Context context) {
			this(context, AbstractMonsterCreeperGirlModel.LAYER_LOCATION_P2, AbstractMonsterCreeperGirlModel.LAYER_ENERGY_ARMOR_P2_LOCATION, AbstractMonsterCreeperGirlModel.LAYER_OUTER_ARMOR_LOCATION);
		}
		
		protected TamablePregnantCreeperGirlP2Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation armor, ModelLayerLocation outter) {
			super(context, new TamablePregnantMonsterCreeperGirlModel.TamableMonsterCreeperGirlP2Model(context.bakeLayer(main)), new TamablePregnantMonsterCreeperGirlModel.TamableMonsterCreeperGirlP2Model(context.bakeLayer(armor)), new TamablePregnantMonsterCreeperGirlModel.TamableMonsterCreeperGirlP2Model(context.bakeLayer(outter)));
		}

		@Override
		public ResourceLocation getTextureLocation(TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP2 p_115812_) {
			return MONSTER_CREEPER_GIRL_P2_LOCATION;
		}

	}
	
	@OnlyIn(Dist.CLIENT)
	public static class TamablePregnantCreeperGirlP3Renderer extends AbstractTamablePregnantMonsterCreeperGirlRenderer<TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP3, TamablePregnantMonsterCreeperGirlModel.TamableMonsterCreeperGirlP3Model> {
		
		public TamablePregnantCreeperGirlP3Renderer(EntityRendererProvider.Context context) {
			this(context, AbstractMonsterCreeperGirlModel.LAYER_LOCATION_P3, AbstractMonsterCreeperGirlModel.LAYER_ENERGY_ARMOR_P3_LOCATION, AbstractMonsterCreeperGirlModel.LAYER_OUTER_ARMOR_LOCATION);
		}
		
		protected TamablePregnantCreeperGirlP3Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation armor, ModelLayerLocation outter) {
			super(context, new TamablePregnantMonsterCreeperGirlModel.TamableMonsterCreeperGirlP3Model(context.bakeLayer(main)), new TamablePregnantMonsterCreeperGirlModel.TamableMonsterCreeperGirlP3Model(context.bakeLayer(armor)), new TamablePregnantMonsterCreeperGirlModel.TamableMonsterCreeperGirlP3Model(context.bakeLayer(outter)));
		}

		@Override
		public ResourceLocation getTextureLocation(TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP3 p_115812_) {
			return MONSTER_CREEPER_GIRL_P3_LOCATION;
		}

	}
	
	@OnlyIn(Dist.CLIENT)
	public static class TamablePregnantCreeperGirlP4Renderer extends AbstractTamablePregnantMonsterCreeperGirlRenderer<TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP4, TamablePregnantMonsterCreeperGirlModel.TamableMonsterCreeperGirlP4Model> {
		
		public TamablePregnantCreeperGirlP4Renderer(EntityRendererProvider.Context context) {
			this(context, AbstractMonsterCreeperGirlModel.LAYER_LOCATION_P4, AbstractMonsterCreeperGirlModel.LAYER_ENERGY_ARMOR_P4_LOCATION, AbstractMonsterCreeperGirlModel.LAYER_OUTER_ARMOR_LOCATION);
		}
		
		protected TamablePregnantCreeperGirlP4Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation armor, ModelLayerLocation outter) {
			super(context, new TamablePregnantMonsterCreeperGirlModel.TamableMonsterCreeperGirlP4Model(context.bakeLayer(main)), new TamablePregnantMonsterCreeperGirlModel.TamableMonsterCreeperGirlP4Model(context.bakeLayer(armor)), new TamablePregnantMonsterCreeperGirlModel.TamableMonsterCreeperGirlP4Model(context.bakeLayer(outter)));
		}

		@Override
		public ResourceLocation getTextureLocation(TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP4 entity) {
			return MONSTER_CREEPER_GIRL_P4_LOCATION;
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class TamablePregnantCreeperGirlP5Renderer extends AbstractTamablePregnantMonsterCreeperGirlRenderer<TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP5, TamablePregnantMonsterCreeperGirlModel.TamableMonsterCreeperGirlP5Model> {
		
		public TamablePregnantCreeperGirlP5Renderer(EntityRendererProvider.Context context) {
			this(context, AbstractMonsterCreeperGirlModel.LAYER_LOCATION_P5, AbstractMonsterCreeperGirlModel.LAYER_ENERGY_ARMOR_P5_LOCATION, AbstractMonsterCreeperGirlModel.LAYER_OUTER_ARMOR_LOCATION);
		}
		
		protected TamablePregnantCreeperGirlP5Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation armor, ModelLayerLocation outter) {
			super(context, new TamablePregnantMonsterCreeperGirlModel.TamableMonsterCreeperGirlP5Model(context.bakeLayer(main)), new TamablePregnantMonsterCreeperGirlModel.TamableMonsterCreeperGirlP5Model(context.bakeLayer(armor)), new TamablePregnantMonsterCreeperGirlModel.TamableMonsterCreeperGirlP5Model(context.bakeLayer(outter)));
		}

		@Override
		public ResourceLocation getTextureLocation(TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP5 entity) {
			return MONSTER_CREEPER_GIRL_P5_LOCATION;
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class TamablePregnantCreeperGirlP6Renderer extends AbstractTamablePregnantMonsterCreeperGirlRenderer<TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP6, TamablePregnantMonsterCreeperGirlModel.TamableMonsterCreeperGirlP6Model> {
		
		public TamablePregnantCreeperGirlP6Renderer(EntityRendererProvider.Context context) {
			this(context, AbstractMonsterCreeperGirlModel.LAYER_LOCATION_P6, AbstractMonsterCreeperGirlModel.LAYER_ENERGY_ARMOR_P6_LOCATION, AbstractMonsterCreeperGirlModel.LAYER_OUTER_ARMOR_LOCATION);
		}
		
		protected TamablePregnantCreeperGirlP6Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation armor, ModelLayerLocation outter) {
			super(context, new TamablePregnantMonsterCreeperGirlModel.TamableMonsterCreeperGirlP6Model(context.bakeLayer(main)), new TamablePregnantMonsterCreeperGirlModel.TamableMonsterCreeperGirlP6Model(context.bakeLayer(armor)), new TamablePregnantMonsterCreeperGirlModel.TamableMonsterCreeperGirlP6Model(context.bakeLayer(outter)));
		}

		@Override
		public ResourceLocation getTextureLocation(TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP6 entity) {
			return MONSTER_CREEPER_GIRL_P6_LOCATION;
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class TamablePregnantCreeperGirlP7Renderer extends AbstractTamablePregnantMonsterCreeperGirlRenderer<TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP7, TamablePregnantMonsterCreeperGirlModel.TamableMonsterCreeperGirlP7Model> {
		
		public TamablePregnantCreeperGirlP7Renderer(EntityRendererProvider.Context context) {
			this(context, AbstractMonsterCreeperGirlModel.LAYER_LOCATION_P7, AbstractMonsterCreeperGirlModel.LAYER_ENERGY_ARMOR_P7_LOCATION, AbstractMonsterCreeperGirlModel.LAYER_OUTER_ARMOR_LOCATION);
		}
		
		protected TamablePregnantCreeperGirlP7Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation armor, ModelLayerLocation outter) {
			super(context, new TamablePregnantMonsterCreeperGirlModel.TamableMonsterCreeperGirlP7Model(context.bakeLayer(main)), new TamablePregnantMonsterCreeperGirlModel.TamableMonsterCreeperGirlP7Model(context.bakeLayer(armor)), new TamablePregnantMonsterCreeperGirlModel.TamableMonsterCreeperGirlP7Model(context.bakeLayer(outter)));
		}

		@Override
		public ResourceLocation getTextureLocation(TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP7 entity) {
			return MONSTER_CREEPER_GIRL_P7_LOCATION;
		}

	}
	
	@OnlyIn(Dist.CLIENT)
	public static class TamablePregnantCreeperGirlP8Renderer extends AbstractTamablePregnantMonsterCreeperGirlRenderer<TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP8, TamablePregnantMonsterCreeperGirlModel.TamableMonsterCreeperGirlP8Model> {
		
		public TamablePregnantCreeperGirlP8Renderer(EntityRendererProvider.Context context) {
			this(context, AbstractMonsterCreeperGirlModel.LAYER_LOCATION_P8, AbstractMonsterCreeperGirlModel.LAYER_ENERGY_ARMOR_P8_LOCATION, AbstractMonsterCreeperGirlModel.LAYER_OUTER_ARMOR_LOCATION);
		}
		
		protected TamablePregnantCreeperGirlP8Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation armor, ModelLayerLocation outter) {
			super(context, new TamablePregnantMonsterCreeperGirlModel.TamableMonsterCreeperGirlP8Model(context.bakeLayer(main)), new TamablePregnantMonsterCreeperGirlModel.TamableMonsterCreeperGirlP8Model(context.bakeLayer(armor)), new TamablePregnantMonsterCreeperGirlModel.TamableMonsterCreeperGirlP8Model(context.bakeLayer(outter)));
		}
		
		@Override
		public ResourceLocation getTextureLocation(TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP8 entity) {
			return MONSTER_CREEPER_GIRL_P8_LOCATION;
		}
	}
}
