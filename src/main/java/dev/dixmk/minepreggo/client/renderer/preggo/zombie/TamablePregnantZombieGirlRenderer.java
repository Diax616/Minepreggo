package dev.dixmk.minepreggo.client.renderer.preggo.zombie;

import dev.dixmk.minepreggo.client.model.entity.preggo.zombie.AbstractZombieGirlModel;
import dev.dixmk.minepreggo.client.model.entity.preggo.zombie.TamablePregnantZombieGirlModel;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobBody;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamablePregnantZombieGirl;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TamablePregnantZombieGirlRenderer {

	@OnlyIn(Dist.CLIENT)
	public static class TamableZombieGirlP0Renderer extends AbstractTamablePregnantZombieGirlRenderer<TamablePregnantZombieGirl.TamableZombieGirlP0, TamablePregnantZombieGirlModel.TamableZombieGirlP0Model> {

		public TamableZombieGirlP0Renderer(EntityRendererProvider.Context context) {
			this(context, AbstractZombieGirlModel.LAYER_LOCATION_P0, AbstractZombieGirlModel.LAYER_INNER_ARMOR_LOCATION, AbstractZombieGirlModel.LAYER_OUTER_ARMOR_LOCATION);
		}
		
		public TamableZombieGirlP0Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter) {
			super(context, new TamablePregnantZombieGirlModel.TamableZombieGirlP0Model(context.bakeLayer(main)), new TamablePregnantZombieGirlModel.TamableZombieGirlP0Model(context.bakeLayer(inner)), new TamablePregnantZombieGirlModel.TamableZombieGirlP0Model(context.bakeLayer(outter)));
		}
		
		@Override
		public ResourceLocation getTextureLocation(TamablePregnantZombieGirl.TamableZombieGirlP0 p_115812_) {
			return ZOMBIE_GIRL_P0_LOCATION;
		}
	}
	
	@OnlyIn(Dist.CLIENT) 
	public static class TamableZombieGirlP1Renderer extends AbstractTamablePregnantZombieGirlRenderer<TamablePregnantZombieGirl.TamableZombieGirlP1, TamablePregnantZombieGirlModel.TamableZombieGirlP1Model> {

		public TamableZombieGirlP1Renderer(EntityRendererProvider.Context context) {
			this(context, AbstractZombieGirlModel.LAYER_LOCATION_P1, AbstractZombieGirlModel.LAYER_INNER_ARMOR_LOCATION, AbstractZombieGirlModel.LAYER_OUTER_ARMOR_LOCATION);
		}
		
		public TamableZombieGirlP1Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter) {
			super(context, new TamablePregnantZombieGirlModel.TamableZombieGirlP1Model(context.bakeLayer(main)), new TamablePregnantZombieGirlModel.TamableZombieGirlP1Model(context.bakeLayer(inner)), new TamablePregnantZombieGirlModel.TamableZombieGirlP1Model(context.bakeLayer(outter)));
		}

		@Override
		public ResourceLocation getTextureLocation(TamablePregnantZombieGirl.TamableZombieGirlP1 p_115812_) {
			return ZOMBIE_GIRL_P1_LOCATION;
		}
	}
	
	@OnlyIn(Dist.CLIENT) 
	public static class TamableZombieGirlP2Renderer extends AbstractTamablePregnantZombieGirlRenderer<TamablePregnantZombieGirl.TamableZombieGirlP2, TamablePregnantZombieGirlModel.TamableZombieGirlP2Model> {

		public TamableZombieGirlP2Renderer(EntityRendererProvider.Context context) {
			this(context, AbstractZombieGirlModel.LAYER_LOCATION_P2, AbstractZombieGirlModel.LAYER_INNER_ARMOR_LOCATION, AbstractZombieGirlModel.LAYER_OUTER_ARMOR_LOCATION);
		}
		
		public TamableZombieGirlP2Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter) {
			super(context, new TamablePregnantZombieGirlModel.TamableZombieGirlP2Model(context.bakeLayer(main)), new TamablePregnantZombieGirlModel.TamableZombieGirlP2Model(context.bakeLayer(inner)), new TamablePregnantZombieGirlModel.TamableZombieGirlP2Model(context.bakeLayer(outter)));
		}

		@Override
		public ResourceLocation getTextureLocation(TamablePregnantZombieGirl.TamableZombieGirlP2 p_115812_) {
			return ZOMBIE_GIRL_P2_LOCATION;
		}
	}

	@OnlyIn(Dist.CLIENT) 
	public static class TamableZombieGirlP3Renderer extends AbstractTamablePregnantZombieGirlRenderer<TamablePregnantZombieGirl.TamableZombieGirlP3, TamablePregnantZombieGirlModel.TamableZombieGirlP3Model> {

		public TamableZombieGirlP3Renderer(EntityRendererProvider.Context context) {
			this(context, AbstractZombieGirlModel.LAYER_LOCATION_P3, AbstractZombieGirlModel.LAYER_INNER_ARMOR_LOCATION, AbstractZombieGirlModel.LAYER_OUTER_ARMOR_LOCATION);
		}
		
		public TamableZombieGirlP3Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter) {
			super(context, new TamablePregnantZombieGirlModel.TamableZombieGirlP3Model(context.bakeLayer(main)), new TamablePregnantZombieGirlModel.TamableZombieGirlP3Model(context.bakeLayer(inner)), new TamablePregnantZombieGirlModel.TamableZombieGirlP3Model(context.bakeLayer(outter)));
		}

		@Override
		public ResourceLocation getTextureLocation(TamablePregnantZombieGirl.TamableZombieGirlP3 p_115812_) {
			return ZOMBIE_GIRL_P3_LOCATION;
		}
	}
	
	@OnlyIn(Dist.CLIENT) 
	public static class TamableZombieGirlP4Renderer extends AbstractTamablePregnantZombieGirlRenderer<TamablePregnantZombieGirl.TamableZombieGirlP4, TamablePregnantZombieGirlModel.TamableZombieGirlP4Model> {

		public TamableZombieGirlP4Renderer(EntityRendererProvider.Context context) {
			this(context, AbstractZombieGirlModel.LAYER_LOCATION_P4, AbstractZombieGirlModel.LAYER_INNER_ARMOR_LOCATION, AbstractZombieGirlModel.LAYER_OUTER_ARMOR_LOCATION);
		}
		
		public TamableZombieGirlP4Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter) {
			super(context, new TamablePregnantZombieGirlModel.TamableZombieGirlP4Model(context.bakeLayer(main)), new TamablePregnantZombieGirlModel.TamableZombieGirlP4Model(context.bakeLayer(inner)), new TamablePregnantZombieGirlModel.TamableZombieGirlP4Model(context.bakeLayer(outter)));
		}

		@Override
		public ResourceLocation getTextureLocation(TamablePregnantZombieGirl.TamableZombieGirlP4 entity) {
			if (entity.getTamableData().getBodyState() == PreggoMobBody.NAKED) {
				return ZOMBIE_GIRL_P4_LOCATION.getRight();
			}
			return ZOMBIE_GIRL_P4_LOCATION.getLeft(); 
		}
	}
	
	@OnlyIn(Dist.CLIENT) 
	public static class TamableZombieGirlP5Renderer extends AbstractTamablePregnantZombieGirlRenderer<TamablePregnantZombieGirl.TamableZombieGirlP5, TamablePregnantZombieGirlModel.TamableZombieGirlP5Model> {

		public TamableZombieGirlP5Renderer(EntityRendererProvider.Context context) {
			this(context, AbstractZombieGirlModel.LAYER_LOCATION_P5, AbstractZombieGirlModel.LAYER_INNER_ARMOR_LOCATION, AbstractZombieGirlModel.LAYER_OUTER_ARMOR_LOCATION);
		}
		
		public TamableZombieGirlP5Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter) {
			super(context, new TamablePregnantZombieGirlModel.TamableZombieGirlP5Model(context.bakeLayer(main)), new TamablePregnantZombieGirlModel.TamableZombieGirlP5Model(context.bakeLayer(inner)), new TamablePregnantZombieGirlModel.TamableZombieGirlP5Model(context.bakeLayer(outter)));
		}

		@Override
		public ResourceLocation getTextureLocation(TamablePregnantZombieGirl.TamableZombieGirlP5 entity) {
			if (entity.getTamableData().getBodyState() == PreggoMobBody.NAKED) {
				return ZOMBIE_GIRL_P5_LOCATION.getRight();
			}
			return ZOMBIE_GIRL_P5_LOCATION.getLeft();  
		}
	}
	
	@OnlyIn(Dist.CLIENT) 
	public static class TamableZombieGirlP6Renderer extends AbstractTamablePregnantZombieGirlRenderer<TamablePregnantZombieGirl.TamableZombieGirlP6, TamablePregnantZombieGirlModel.TamableZombieGirlP6Model> {

		public TamableZombieGirlP6Renderer(EntityRendererProvider.Context context) {
			this(context, AbstractZombieGirlModel.LAYER_LOCATION_P6, AbstractZombieGirlModel.LAYER_INNER_ARMOR_LOCATION, AbstractZombieGirlModel.LAYER_OUTER_ARMOR_LOCATION);
		}
		
		public TamableZombieGirlP6Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter) {
			super(context, new TamablePregnantZombieGirlModel.TamableZombieGirlP6Model(context.bakeLayer(main)), new TamablePregnantZombieGirlModel.TamableZombieGirlP6Model(context.bakeLayer(inner)), new TamablePregnantZombieGirlModel.TamableZombieGirlP6Model(context.bakeLayer(outter)));
		}

		@Override
		public ResourceLocation getTextureLocation(TamablePregnantZombieGirl.TamableZombieGirlP6 entity) {
			if (entity.getTamableData().getBodyState() == PreggoMobBody.NAKED) {
				return ZOMBIE_GIRL_P6_LOCATION.getRight();
			}
			return ZOMBIE_GIRL_P6_LOCATION.getLeft(); 
		}
	}
	
	@OnlyIn(Dist.CLIENT) 
	public static class TamableZombieGirlP7Renderer extends AbstractTamablePregnantZombieGirlRenderer<TamablePregnantZombieGirl.TamableZombieGirlP7, TamablePregnantZombieGirlModel.TamableZombieGirlP7Model> {

		public TamableZombieGirlP7Renderer(EntityRendererProvider.Context context) {
			this(context, AbstractZombieGirlModel.LAYER_LOCATION_P7, AbstractZombieGirlModel.LAYER_INNER_ARMOR_LOCATION, AbstractZombieGirlModel.LAYER_OUTER_ARMOR_LOCATION);
		}
		
		public TamableZombieGirlP7Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter) {
			super(context, new TamablePregnantZombieGirlModel.TamableZombieGirlP7Model(context.bakeLayer(main)), new TamablePregnantZombieGirlModel.TamableZombieGirlP7Model(context.bakeLayer(inner)), new TamablePregnantZombieGirlModel.TamableZombieGirlP7Model(context.bakeLayer(outter)));
		}

		@Override
		public ResourceLocation getTextureLocation(TamablePregnantZombieGirl.TamableZombieGirlP7 entity) {
			if (entity.getTamableData().getBodyState() == PreggoMobBody.NAKED) {
				return ZOMBIE_GIRL_P7_LOCATION.getRight();
			}
			return ZOMBIE_GIRL_P7_LOCATION.getLeft(); 
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class TamableZombieGirlP8Renderer extends AbstractTamablePregnantZombieGirlRenderer<TamablePregnantZombieGirl.TamableZombieGirlP8, TamablePregnantZombieGirlModel.TamableZombieGirlP8Model> {
		public TamableZombieGirlP8Renderer(EntityRendererProvider.Context context) {
			this(context, AbstractZombieGirlModel.LAYER_LOCATION_P8, AbstractZombieGirlModel.LAYER_INNER_ARMOR_LOCATION, AbstractZombieGirlModel.LAYER_OUTER_ARMOR_LOCATION);
		}
		
		public TamableZombieGirlP8Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter) {
			super(context, new TamablePregnantZombieGirlModel.TamableZombieGirlP8Model(context.bakeLayer(main)), new TamablePregnantZombieGirlModel.TamableZombieGirlP8Model(context.bakeLayer(inner)), new TamablePregnantZombieGirlModel.TamableZombieGirlP8Model(context.bakeLayer(outter)));
		}

		@Override
		public ResourceLocation getTextureLocation(TamablePregnantZombieGirl.TamableZombieGirlP8 entity) {
			if (entity.getTamableData().getBodyState() == PreggoMobBody.NAKED) {
				return ZOMBIE_GIRL_P8_LOCATION.getRight();
			}
			return ZOMBIE_GIRL_P8_LOCATION.getLeft(); 
		}
	}
}
