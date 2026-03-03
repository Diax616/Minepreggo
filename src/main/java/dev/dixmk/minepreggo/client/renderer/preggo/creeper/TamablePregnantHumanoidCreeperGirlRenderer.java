package dev.dixmk.minepreggo.client.renderer.preggo.creeper;

import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.TamablePregnantHumanoidCreeperGirlModel;
import dev.dixmk.minepreggo.client.model.geom.MinepreggoModelLayers;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobBody;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamablePregnantHumanoidCreeperGirl;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TamablePregnantHumanoidCreeperGirlRenderer {

	private TamablePregnantHumanoidCreeperGirlRenderer() {}
	
	@OnlyIn(Dist.CLIENT)
	public static class TamableHumanoidCreeperGirlP0Renderer extends AbstractTamablePregnantHumanoidCreeperGirlRenderer<TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP0, TamablePregnantHumanoidCreeperGirlModel.TamableHumanoidCreeperGirlP0Model> {
		
		public TamableHumanoidCreeperGirlP0Renderer(EntityRendererProvider.Context context) {
			this(context, MinepreggoModelLayers.PREGNANT_HUMANOID_CREEPER_GIRL_P0, MinepreggoModelLayers.HUMANOID_CREEPER_GIRL_INNER_ARMOR, MinepreggoModelLayers.HUMANOID_CREEPER_GIRL_OUTER_ARMOR, MinepreggoModelLayers.PREGNANT_HUMANOID_CREEPER_GIRL_ENERGY_ARMOR_P0);
		}
		
		public TamableHumanoidCreeperGirlP0Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter, ModelLayerLocation armor) {
			super(context, new TamablePregnantHumanoidCreeperGirlModel.TamableHumanoidCreeperGirlP0Model(context.bakeLayer(main)), new TamablePregnantHumanoidCreeperGirlModel.TamableHumanoidCreeperGirlP0Model(context.bakeLayer(inner)), new TamablePregnantHumanoidCreeperGirlModel.TamableHumanoidCreeperGirlP0Model(context.bakeLayer(outter)), new TamablePregnantHumanoidCreeperGirlModel.TamableHumanoidCreeperGirlP0Model(context.bakeLayer(armor)));
		}

		@Override
		public ResourceLocation getTextureLocation(TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP0 entity) {
			return CREEPER_GIRL_P0_LOCATION;
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class TamableHumanoidCreeperGirlP1Renderer extends AbstractTamablePregnantHumanoidCreeperGirlRenderer<TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP1, TamablePregnantHumanoidCreeperGirlModel.TamableHumanoidCreeperGirlP1Model> {
		
		public TamableHumanoidCreeperGirlP1Renderer(EntityRendererProvider.Context context) {
			this(context, MinepreggoModelLayers.PREGNANT_HUMANOID_CREEPER_GIRL_P1, MinepreggoModelLayers.HUMANOID_CREEPER_GIRL_INNER_ARMOR, MinepreggoModelLayers.HUMANOID_CREEPER_GIRL_OUTER_ARMOR, MinepreggoModelLayers.PREGNANT_HUMANOID_CREEPER_GIRL_ENERGY_ARMOR_P1);
		}
		
		public TamableHumanoidCreeperGirlP1Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter, ModelLayerLocation armor) {
			super(context, new TamablePregnantHumanoidCreeperGirlModel.TamableHumanoidCreeperGirlP1Model(context.bakeLayer(main)), new TamablePregnantHumanoidCreeperGirlModel.TamableHumanoidCreeperGirlP1Model(context.bakeLayer(inner)), new TamablePregnantHumanoidCreeperGirlModel.TamableHumanoidCreeperGirlP1Model(context.bakeLayer(outter)), new TamablePregnantHumanoidCreeperGirlModel.TamableHumanoidCreeperGirlP1Model(context.bakeLayer(armor)));
		}

		@Override
		public ResourceLocation getTextureLocation(TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP1 entity) {
			return CREEPER_GIRL_P1_LOCATION;
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class TamableHumanoidCreeperGirlP2Renderer extends AbstractTamablePregnantHumanoidCreeperGirlRenderer<TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP2, TamablePregnantHumanoidCreeperGirlModel.TamableHumanoidCreeperGirlP2Model> {
		
		public TamableHumanoidCreeperGirlP2Renderer(EntityRendererProvider.Context context) {
			this(context, MinepreggoModelLayers.PREGNANT_HUMANOID_CREEPER_GIRL_P2, MinepreggoModelLayers.HUMANOID_CREEPER_GIRL_INNER_ARMOR, MinepreggoModelLayers.HUMANOID_CREEPER_GIRL_OUTER_ARMOR, MinepreggoModelLayers.PREGNANT_HUMANOID_CREEPER_GIRL_ENERGY_ARMOR_P2);
		}
		
		public TamableHumanoidCreeperGirlP2Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter, ModelLayerLocation armor) {
			super(context, new TamablePregnantHumanoidCreeperGirlModel.TamableHumanoidCreeperGirlP2Model(context.bakeLayer(main)), new TamablePregnantHumanoidCreeperGirlModel.TamableHumanoidCreeperGirlP2Model(context.bakeLayer(inner)), new TamablePregnantHumanoidCreeperGirlModel.TamableHumanoidCreeperGirlP2Model(context.bakeLayer(outter)), new TamablePregnantHumanoidCreeperGirlModel.TamableHumanoidCreeperGirlP2Model(context.bakeLayer(armor)));
		}

		@Override
		public ResourceLocation getTextureLocation(TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP2 entity) {
			return CREEPER_GIRL_P2_LOCATION;
		}

	}
	
	@OnlyIn(Dist.CLIENT)
	public static class TamableHumanoidCreeperGirlP3Renderer extends AbstractTamablePregnantHumanoidCreeperGirlRenderer<TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP3, TamablePregnantHumanoidCreeperGirlModel.TamableHumanoidCreeperGirlP3Model> {
		
		public TamableHumanoidCreeperGirlP3Renderer(EntityRendererProvider.Context context) {
			this(context, MinepreggoModelLayers.PREGNANT_HUMANOID_CREEPER_GIRL_P3, MinepreggoModelLayers.HUMANOID_CREEPER_GIRL_INNER_ARMOR, MinepreggoModelLayers.HUMANOID_CREEPER_GIRL_OUTER_ARMOR, MinepreggoModelLayers.PREGNANT_HUMANOID_CREEPER_GIRL_ENERGY_ARMOR_P3);
		}
		
		public TamableHumanoidCreeperGirlP3Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter, ModelLayerLocation armor) {
			super(context, new TamablePregnantHumanoidCreeperGirlModel.TamableHumanoidCreeperGirlP3Model(context.bakeLayer(main)), new TamablePregnantHumanoidCreeperGirlModel.TamableHumanoidCreeperGirlP3Model(context.bakeLayer(inner)), new TamablePregnantHumanoidCreeperGirlModel.TamableHumanoidCreeperGirlP3Model(context.bakeLayer(outter)), new TamablePregnantHumanoidCreeperGirlModel.TamableHumanoidCreeperGirlP3Model(context.bakeLayer(armor)));
		}

		@Override
		public ResourceLocation getTextureLocation(TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP3 entity) {
			return CREEPER_GIRL_P3_LOCATION;
		}

	}
	
	@OnlyIn(Dist.CLIENT)
	public static class TamableHumanoidCreeperGirlP4Renderer extends AbstractTamablePregnantHumanoidCreeperGirlRenderer<TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP4, TamablePregnantHumanoidCreeperGirlModel.TamableHumanoidCreeperGirlP4Model> {
		
		public TamableHumanoidCreeperGirlP4Renderer(EntityRendererProvider.Context context) {
			this(context, MinepreggoModelLayers.PREGNANT_HUMANOID_CREEPER_GIRL_P4, MinepreggoModelLayers.HUMANOID_CREEPER_GIRL_INNER_ARMOR, MinepreggoModelLayers.HUMANOID_CREEPER_GIRL_OUTER_ARMOR, MinepreggoModelLayers.PREGNANT_HUMANOID_CREEPER_GIRL_ENERGY_ARMOR_P4);
		}
		
		public TamableHumanoidCreeperGirlP4Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter, ModelLayerLocation armor) {
			super(context, new TamablePregnantHumanoidCreeperGirlModel.TamableHumanoidCreeperGirlP4Model(context.bakeLayer(main)), new TamablePregnantHumanoidCreeperGirlModel.TamableHumanoidCreeperGirlP4Model(context.bakeLayer(inner)), new TamablePregnantHumanoidCreeperGirlModel.TamableHumanoidCreeperGirlP4Model(context.bakeLayer(outter)), new TamablePregnantHumanoidCreeperGirlModel.TamableHumanoidCreeperGirlP4Model(context.bakeLayer(armor)));
		}

		@Override
		public ResourceLocation getTextureLocation(TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP4 entity) {
			if (entity.getTamableData().getBodyState() == PreggoMobBody.NAKED) {
				return CREEPER_GIRL_P4_LOCATION.getRight();
			}
			return CREEPER_GIRL_P4_LOCATION.getLeft();
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class TamableHumanoidCreeperGirlP5Renderer extends AbstractTamablePregnantHumanoidCreeperGirlRenderer<TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP5, TamablePregnantHumanoidCreeperGirlModel.TamableHumanoidCreeperGirlP5Model> {
		
		public TamableHumanoidCreeperGirlP5Renderer(EntityRendererProvider.Context context) {
			this(context, MinepreggoModelLayers.PREGNANT_HUMANOID_CREEPER_GIRL_P5, MinepreggoModelLayers.HUMANOID_CREEPER_GIRL_INNER_ARMOR, MinepreggoModelLayers.HUMANOID_CREEPER_GIRL_OUTER_ARMOR, MinepreggoModelLayers.PREGNANT_HUMANOID_CREEPER_GIRL_ENERGY_ARMOR_P5);
		}
		
		public TamableHumanoidCreeperGirlP5Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter, ModelLayerLocation armor) {
			super(context, new TamablePregnantHumanoidCreeperGirlModel.TamableHumanoidCreeperGirlP5Model(context.bakeLayer(main)), new TamablePregnantHumanoidCreeperGirlModel.TamableHumanoidCreeperGirlP5Model(context.bakeLayer(inner)), new TamablePregnantHumanoidCreeperGirlModel.TamableHumanoidCreeperGirlP5Model(context.bakeLayer(outter)), new TamablePregnantHumanoidCreeperGirlModel.TamableHumanoidCreeperGirlP5Model(context.bakeLayer(armor)));
		}

		@Override
		public ResourceLocation getTextureLocation(TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP5 entity) {
			if (entity.getTamableData().getBodyState() == PreggoMobBody.NAKED) {
				return CREEPER_GIRL_P5_LOCATION.getRight();
			}
			return CREEPER_GIRL_P5_LOCATION.getLeft();
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class TamableHumanoidCreeperGirlP6Renderer extends AbstractTamablePregnantHumanoidCreeperGirlRenderer<TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP6, TamablePregnantHumanoidCreeperGirlModel.TamableHumanoidCreeperGirlP6Model> {
		
		public TamableHumanoidCreeperGirlP6Renderer(EntityRendererProvider.Context context) {
			this(context, MinepreggoModelLayers.PREGNANT_HUMANOID_CREEPER_GIRL_P6, MinepreggoModelLayers.HUMANOID_CREEPER_GIRL_INNER_ARMOR, MinepreggoModelLayers.HUMANOID_CREEPER_GIRL_OUTER_ARMOR, MinepreggoModelLayers.PREGNANT_HUMANOID_CREEPER_GIRL_ENERGY_ARMOR_P6);
		}
		
		public TamableHumanoidCreeperGirlP6Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter, ModelLayerLocation armor) {
			super(context, new TamablePregnantHumanoidCreeperGirlModel.TamableHumanoidCreeperGirlP6Model(context.bakeLayer(main)), new TamablePregnantHumanoidCreeperGirlModel.TamableHumanoidCreeperGirlP6Model(context.bakeLayer(inner)), new TamablePregnantHumanoidCreeperGirlModel.TamableHumanoidCreeperGirlP6Model(context.bakeLayer(outter)), new TamablePregnantHumanoidCreeperGirlModel.TamableHumanoidCreeperGirlP6Model(context.bakeLayer(armor)));
		}

		@Override
		public ResourceLocation getTextureLocation(TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP6 entity) {
			if (entity.getTamableData().getBodyState() == PreggoMobBody.NAKED) {
				return CREEPER_GIRL_P6_LOCATION.getRight();
			}
			return CREEPER_GIRL_P6_LOCATION.getLeft();
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class TamableHumanoidCreeperGirlP7Renderer extends AbstractTamablePregnantHumanoidCreeperGirlRenderer<TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP7, TamablePregnantHumanoidCreeperGirlModel.TamableHumanoidCreeperGirlP7Model> {
		
		public TamableHumanoidCreeperGirlP7Renderer(EntityRendererProvider.Context context) {
			this(context, MinepreggoModelLayers.PREGNANT_HUMANOID_CREEPER_GIRL_P7, MinepreggoModelLayers.HUMANOID_CREEPER_GIRL_INNER_ARMOR, MinepreggoModelLayers.HUMANOID_CREEPER_GIRL_OUTER_ARMOR, MinepreggoModelLayers.PREGNANT_HUMANOID_CREEPER_GIRL_ENERGY_ARMOR_P7);
		}
		
		public TamableHumanoidCreeperGirlP7Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter, ModelLayerLocation armor) {
			super(context, new TamablePregnantHumanoidCreeperGirlModel.TamableHumanoidCreeperGirlP7Model(context.bakeLayer(main)), new TamablePregnantHumanoidCreeperGirlModel.TamableHumanoidCreeperGirlP7Model(context.bakeLayer(inner)), new TamablePregnantHumanoidCreeperGirlModel.TamableHumanoidCreeperGirlP7Model(context.bakeLayer(outter)), new TamablePregnantHumanoidCreeperGirlModel.TamableHumanoidCreeperGirlP7Model(context.bakeLayer(armor)));
		}

		@Override
		public ResourceLocation getTextureLocation(TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP7 entity) {
			if (entity.getTamableData().getBodyState() == PreggoMobBody.NAKED) {
				return CREEPER_GIRL_P7_LOCATION.getRight();
			}
			return CREEPER_GIRL_P7_LOCATION.getLeft();
		}

	}
	
	@OnlyIn(Dist.CLIENT)
	public static class TamableHumanoidCreeperGirlP8Renderer extends AbstractTamablePregnantHumanoidCreeperGirlRenderer<TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP8, TamablePregnantHumanoidCreeperGirlModel.TamableHumanoidCreeperGirlP8Model> {
		
		public TamableHumanoidCreeperGirlP8Renderer(EntityRendererProvider.Context context) {
			this(context, MinepreggoModelLayers.PREGNANT_HUMANOID_CREEPER_GIRL_P8, MinepreggoModelLayers.HUMANOID_CREEPER_GIRL_INNER_ARMOR, MinepreggoModelLayers.HUMANOID_CREEPER_GIRL_OUTER_ARMOR, MinepreggoModelLayers.PREGNANT_HUMANOID_CREEPER_GIRL_ENERGY_ARMOR_P8);
		}
		
		public TamableHumanoidCreeperGirlP8Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter, ModelLayerLocation armor) {
			super(context, new TamablePregnantHumanoidCreeperGirlModel.TamableHumanoidCreeperGirlP8Model(context.bakeLayer(main)), new TamablePregnantHumanoidCreeperGirlModel.TamableHumanoidCreeperGirlP8Model(context.bakeLayer(inner)), new TamablePregnantHumanoidCreeperGirlModel.TamableHumanoidCreeperGirlP8Model(context.bakeLayer(outter)), new TamablePregnantHumanoidCreeperGirlModel.TamableHumanoidCreeperGirlP8Model(context.bakeLayer(armor)));
		}
		@Override
		public ResourceLocation getTextureLocation(TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP8 entity) {
			if (entity.getTamableData().getBodyState() == PreggoMobBody.NAKED) {
				return CREEPER_GIRL_P8_LOCATION.getRight();
			}
			return CREEPER_GIRL_P8_LOCATION.getLeft();
		}
	}
	
}
