package dev.dixmk.minepreggo.client.renderer.preggo.creeper;

import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.TamablePregnantMonsterCreeperGirlModel;
import dev.dixmk.minepreggo.client.model.geom.MinepreggoModelLayers;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamablePregnantMonsterCreeperGirl;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TamablePregnantMonsterCreeperGirlRenderer {
	
	private TamablePregnantMonsterCreeperGirlRenderer() {}

	@OnlyIn(Dist.CLIENT)
	public static class TamablePregnantCreeperGirlP0Renderer extends AbstractTamablePregnantMonsterCreeperGirlRenderer<TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP0, TamablePregnantMonsterCreeperGirlModel.TamableMonsterCreeperGirlP0Model> {
		
		public TamablePregnantCreeperGirlP0Renderer(EntityRendererProvider.Context context) {
			this(context, MinepreggoModelLayers.PREGNANT_MONSTER_CREEPER_GIRL_P0, MinepreggoModelLayers.PREGNANT_MONSTER_CREEPER_GIRL_ENERGY_ARMOR_P0, MinepreggoModelLayers.MONSTER_CREEPER_GIRL_OUTER_ARMOR);
		}
		
		protected TamablePregnantCreeperGirlP0Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation armor, ModelLayerLocation outter) {
			super(context, new TamablePregnantMonsterCreeperGirlModel.TamableMonsterCreeperGirlP0Model(context.bakeLayer(main)), new TamablePregnantMonsterCreeperGirlModel.TamableMonsterCreeperGirlP0Model(context.bakeLayer(armor)), new TamablePregnantMonsterCreeperGirlModel.TamableMonsterCreeperGirlP0Model(context.bakeLayer(outter)));
		}

		@Override
		public ResourceLocation getTextureLocation(TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP0 entity) {
			return MONSTER_CREEPER_GIRL_P0_LOCATION;
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class TamablePregnantCreeperGirlP1Renderer extends AbstractTamablePregnantMonsterCreeperGirlRenderer<TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP1, TamablePregnantMonsterCreeperGirlModel.TamableMonsterCreeperGirlP1Model> {
		
		public TamablePregnantCreeperGirlP1Renderer(EntityRendererProvider.Context context) {
			this(context, MinepreggoModelLayers.PREGNANT_MONSTER_CREEPER_GIRL_P1, MinepreggoModelLayers.PREGNANT_MONSTER_CREEPER_GIRL_ENERGY_ARMOR_P1, MinepreggoModelLayers.MONSTER_CREEPER_GIRL_OUTER_ARMOR);
		}
		
		protected TamablePregnantCreeperGirlP1Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation armor, ModelLayerLocation outter) {
			super(context, new TamablePregnantMonsterCreeperGirlModel.TamableMonsterCreeperGirlP1Model(context.bakeLayer(main)), new TamablePregnantMonsterCreeperGirlModel.TamableMonsterCreeperGirlP1Model(context.bakeLayer(armor)), new TamablePregnantMonsterCreeperGirlModel.TamableMonsterCreeperGirlP1Model(context.bakeLayer(outter)));
		}

		@Override
		public ResourceLocation getTextureLocation(TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP1 entity) {
			return MONSTER_CREEPER_GIRL_P1_LOCATION;
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class TamablePregnantCreeperGirlP2Renderer extends AbstractTamablePregnantMonsterCreeperGirlRenderer<TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP2, TamablePregnantMonsterCreeperGirlModel.TamableMonsterCreeperGirlP2Model> {
		
		public TamablePregnantCreeperGirlP2Renderer(EntityRendererProvider.Context context) {
			this(context, MinepreggoModelLayers.PREGNANT_MONSTER_CREEPER_GIRL_P2, MinepreggoModelLayers.PREGNANT_MONSTER_CREEPER_GIRL_ENERGY_ARMOR_P2, MinepreggoModelLayers.MONSTER_CREEPER_GIRL_OUTER_ARMOR);
		}
		
		protected TamablePregnantCreeperGirlP2Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation armor, ModelLayerLocation outter) {
			super(context, new TamablePregnantMonsterCreeperGirlModel.TamableMonsterCreeperGirlP2Model(context.bakeLayer(main)), new TamablePregnantMonsterCreeperGirlModel.TamableMonsterCreeperGirlP2Model(context.bakeLayer(armor)), new TamablePregnantMonsterCreeperGirlModel.TamableMonsterCreeperGirlP2Model(context.bakeLayer(outter)));
		}

		@Override
		public ResourceLocation getTextureLocation(TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP2 entity) {
			return MONSTER_CREEPER_GIRL_P2_LOCATION;
		}

	}
	
	@OnlyIn(Dist.CLIENT)
	public static class TamablePregnantCreeperGirlP3Renderer extends AbstractTamablePregnantMonsterCreeperGirlRenderer<TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP3, TamablePregnantMonsterCreeperGirlModel.TamableMonsterCreeperGirlP3Model> {
		
		public TamablePregnantCreeperGirlP3Renderer(EntityRendererProvider.Context context) {
			this(context, MinepreggoModelLayers.PREGNANT_MONSTER_CREEPER_GIRL_P3, MinepreggoModelLayers.PREGNANT_MONSTER_CREEPER_GIRL_ENERGY_ARMOR_P3, MinepreggoModelLayers.MONSTER_CREEPER_GIRL_OUTER_ARMOR);
		}
		
		protected TamablePregnantCreeperGirlP3Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation armor, ModelLayerLocation outter) {
			super(context, new TamablePregnantMonsterCreeperGirlModel.TamableMonsterCreeperGirlP3Model(context.bakeLayer(main)), new TamablePregnantMonsterCreeperGirlModel.TamableMonsterCreeperGirlP3Model(context.bakeLayer(armor)), new TamablePregnantMonsterCreeperGirlModel.TamableMonsterCreeperGirlP3Model(context.bakeLayer(outter)));
		}

		@Override
		public ResourceLocation getTextureLocation(TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP3 entity) {
			return MONSTER_CREEPER_GIRL_P3_LOCATION;
		}

	}
	
	@OnlyIn(Dist.CLIENT)
	public static class TamablePregnantCreeperGirlP4Renderer extends AbstractTamablePregnantMonsterCreeperGirlRenderer<TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP4, TamablePregnantMonsterCreeperGirlModel.TamableMonsterCreeperGirlP4Model> {
		
		public TamablePregnantCreeperGirlP4Renderer(EntityRendererProvider.Context context) {
			this(context, MinepreggoModelLayers.PREGNANT_MONSTER_CREEPER_GIRL_P4, MinepreggoModelLayers.PREGNANT_MONSTER_CREEPER_GIRL_ENERGY_ARMOR_P4, MinepreggoModelLayers.MONSTER_CREEPER_GIRL_OUTER_ARMOR);
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
			this(context, MinepreggoModelLayers.PREGNANT_MONSTER_CREEPER_GIRL_P5, MinepreggoModelLayers.PREGNANT_MONSTER_CREEPER_GIRL_ENERGY_ARMOR_P5, MinepreggoModelLayers.MONSTER_CREEPER_GIRL_OUTER_ARMOR);
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
			this(context, MinepreggoModelLayers.PREGNANT_MONSTER_CREEPER_GIRL_P6, MinepreggoModelLayers.PREGNANT_MONSTER_CREEPER_GIRL_ENERGY_ARMOR_P6, MinepreggoModelLayers.MONSTER_CREEPER_GIRL_OUTER_ARMOR);
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
			this(context, MinepreggoModelLayers.PREGNANT_MONSTER_CREEPER_GIRL_P7, MinepreggoModelLayers.PREGNANT_MONSTER_CREEPER_GIRL_ENERGY_ARMOR_P7, MinepreggoModelLayers.MONSTER_CREEPER_GIRL_OUTER_ARMOR);
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
			this(context, MinepreggoModelLayers.PREGNANT_MONSTER_CREEPER_GIRL_P8, MinepreggoModelLayers.PREGNANT_MONSTER_CREEPER_GIRL_ENERGY_ARMOR_P8, MinepreggoModelLayers.MONSTER_CREEPER_GIRL_OUTER_ARMOR);
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
