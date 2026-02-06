package dev.dixmk.minepreggo.client.renderer.preggo.creeper;

import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractHumanoidCreeperGirlModel;
import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.MonsterPregnantHumanoidCreeperGirlModel;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.MonsterPregnantHumanoidCreeperGirl;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MonsterPregnantHumanoidCreeperGirlRenderer {

	@OnlyIn(Dist.CLIENT)
	public static class MonsterHumanoidCreeperGirlP3Renderer extends AbstractMonsterHumanoidPregnantCreeperGirlRenderer<MonsterPregnantHumanoidCreeperGirl.MonsterHumanoidCreeperGirlP3, MonsterPregnantHumanoidCreeperGirlModel.MonsterHumanoidCreeperGirlP3Model> {
		
		public MonsterHumanoidCreeperGirlP3Renderer(EntityRendererProvider.Context context) {
			this(context, AbstractHumanoidCreeperGirlModel.LAYER_LOCATION_P3, AbstractHumanoidCreeperGirlModel.LAYER_INNER_ARMOR_LOCATION, AbstractHumanoidCreeperGirlModel.LAYER_OUTER_ARMOR_LOCATION, AbstractHumanoidCreeperGirlModel.LAYER_ENERGY_ARMOR_P3_LOCATION);
		}
		
		public MonsterHumanoidCreeperGirlP3Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter, ModelLayerLocation armor) {
			super(context, new MonsterPregnantHumanoidCreeperGirlModel.MonsterHumanoidCreeperGirlP3Model(context.bakeLayer(main)), new MonsterPregnantHumanoidCreeperGirlModel.MonsterHumanoidCreeperGirlP3Model(context.bakeLayer(inner)), new MonsterPregnantHumanoidCreeperGirlModel.MonsterHumanoidCreeperGirlP3Model(context.bakeLayer(outter)), new MonsterPregnantHumanoidCreeperGirlModel.MonsterHumanoidCreeperGirlP3Model(context.bakeLayer(armor)));
		}

		@Override
		public ResourceLocation getTextureLocation(MonsterPregnantHumanoidCreeperGirl.MonsterHumanoidCreeperGirlP3 p_115812_) {
			return CREEPER_GIRL_P3_LOCATION;
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class MonsterHumanoidCreeperGirlP5Renderer extends AbstractMonsterHumanoidPregnantCreeperGirlRenderer<MonsterPregnantHumanoidCreeperGirl.MonsterHumanoidCreeperGirlP5, MonsterPregnantHumanoidCreeperGirlModel.MonsterHumanoidCreeperGirlP5Model> {
		
		public MonsterHumanoidCreeperGirlP5Renderer(EntityRendererProvider.Context context) {
			this(context, AbstractHumanoidCreeperGirlModel.LAYER_LOCATION_P5, AbstractHumanoidCreeperGirlModel.LAYER_INNER_ARMOR_LOCATION, AbstractHumanoidCreeperGirlModel.LAYER_OUTER_ARMOR_LOCATION, AbstractHumanoidCreeperGirlModel.LAYER_ENERGY_ARMOR_P5_LOCATION);
		}
		
		public MonsterHumanoidCreeperGirlP5Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter, ModelLayerLocation armor) {
			super(context, new MonsterPregnantHumanoidCreeperGirlModel.MonsterHumanoidCreeperGirlP5Model(context.bakeLayer(main)), new MonsterPregnantHumanoidCreeperGirlModel.MonsterHumanoidCreeperGirlP5Model(context.bakeLayer(inner)), new MonsterPregnantHumanoidCreeperGirlModel.MonsterHumanoidCreeperGirlP5Model(context.bakeLayer(outter)), new MonsterPregnantHumanoidCreeperGirlModel.MonsterHumanoidCreeperGirlP5Model(context.bakeLayer(armor)));
		}

		@Override
		public ResourceLocation getTextureLocation(MonsterPregnantHumanoidCreeperGirl.MonsterHumanoidCreeperGirlP5 p_115812_) {
			return AbstractHumanoidCreeperGirlRenderer.CREEPER_GIRL_P5_LOCATION.getLeft();
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class MonsterHumanoidCreeperGirlP7Renderer extends AbstractMonsterHumanoidPregnantCreeperGirlRenderer<MonsterPregnantHumanoidCreeperGirl.MonsterHumanoidCreeperGirlP7, MonsterPregnantHumanoidCreeperGirlModel.MonsterHumanoidCreeperGirlP7Model> {
		
		public MonsterHumanoidCreeperGirlP7Renderer(EntityRendererProvider.Context context) {
			this(context, AbstractHumanoidCreeperGirlModel.LAYER_LOCATION_P7, AbstractHumanoidCreeperGirlModel.LAYER_INNER_ARMOR_LOCATION, AbstractHumanoidCreeperGirlModel.LAYER_OUTER_ARMOR_LOCATION, AbstractHumanoidCreeperGirlModel.LAYER_ENERGY_ARMOR_P7_LOCATION);
		}
		
		public MonsterHumanoidCreeperGirlP7Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter, ModelLayerLocation armor) {
			super(context, new MonsterPregnantHumanoidCreeperGirlModel.MonsterHumanoidCreeperGirlP7Model(context.bakeLayer(main)), new MonsterPregnantHumanoidCreeperGirlModel.MonsterHumanoidCreeperGirlP7Model(context.bakeLayer(inner)), new MonsterPregnantHumanoidCreeperGirlModel.MonsterHumanoidCreeperGirlP7Model(context.bakeLayer(outter)), new MonsterPregnantHumanoidCreeperGirlModel.MonsterHumanoidCreeperGirlP7Model(context.bakeLayer(armor)));
		}
	
		@Override
		public ResourceLocation getTextureLocation(MonsterPregnantHumanoidCreeperGirl.MonsterHumanoidCreeperGirlP7 p_115812_) {
			return AbstractHumanoidCreeperGirlRenderer.CREEPER_GIRL_P7_LOCATION.getLeft();
		}
	}
	
}
