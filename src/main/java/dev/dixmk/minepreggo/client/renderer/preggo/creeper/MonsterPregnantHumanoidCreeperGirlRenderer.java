package dev.dixmk.minepreggo.client.renderer.preggo.creeper;

import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractHumanoidCreeperGirlModel;
import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.HostilePregnantHumanoidCreeperGirlModel;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.HostilePregnantHumanoidCreeperGirl;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MonsterPregnantHumanoidCreeperGirlRenderer {

	@OnlyIn(Dist.CLIENT)
	public static class MonsterHumanoidCreeperGirlP3Renderer extends AbstractHostilePregnantHumanoidCreeperGirlRenderer<HostilePregnantHumanoidCreeperGirl.MonsterHumanoidCreeperGirlP3, HostilePregnantHumanoidCreeperGirlModel.MonsterHumanoidCreeperGirlP3Model> {
		
		public MonsterHumanoidCreeperGirlP3Renderer(EntityRendererProvider.Context context) {
			this(context, AbstractHumanoidCreeperGirlModel.LAYER_LOCATION_P3, AbstractHumanoidCreeperGirlModel.LAYER_INNER_ARMOR_LOCATION, AbstractHumanoidCreeperGirlModel.LAYER_OUTER_ARMOR_LOCATION, AbstractHumanoidCreeperGirlModel.LAYER_ENERGY_ARMOR_P3_LOCATION);
		}
		
		public MonsterHumanoidCreeperGirlP3Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter, ModelLayerLocation armor) {
			super(context, new HostilePregnantHumanoidCreeperGirlModel.MonsterHumanoidCreeperGirlP3Model(context.bakeLayer(main)), new HostilePregnantHumanoidCreeperGirlModel.MonsterHumanoidCreeperGirlP3Model(context.bakeLayer(inner)), new HostilePregnantHumanoidCreeperGirlModel.MonsterHumanoidCreeperGirlP3Model(context.bakeLayer(outter)), new HostilePregnantHumanoidCreeperGirlModel.MonsterHumanoidCreeperGirlP3Model(context.bakeLayer(armor)));
		}

		@Override
		public ResourceLocation getTextureLocation(HostilePregnantHumanoidCreeperGirl.MonsterHumanoidCreeperGirlP3 p_115812_) {
			return CREEPER_GIRL_P3_LOCATION;
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class MonsterHumanoidCreeperGirlP5Renderer extends AbstractHostilePregnantHumanoidCreeperGirlRenderer<HostilePregnantHumanoidCreeperGirl.MonsterHumanoidCreeperGirlP5, HostilePregnantHumanoidCreeperGirlModel.MonsterHumanoidCreeperGirlP5Model> {
		
		public MonsterHumanoidCreeperGirlP5Renderer(EntityRendererProvider.Context context) {
			this(context, AbstractHumanoidCreeperGirlModel.LAYER_LOCATION_P5, AbstractHumanoidCreeperGirlModel.LAYER_INNER_ARMOR_LOCATION, AbstractHumanoidCreeperGirlModel.LAYER_OUTER_ARMOR_LOCATION, AbstractHumanoidCreeperGirlModel.LAYER_ENERGY_ARMOR_P5_LOCATION);
		}
		
		public MonsterHumanoidCreeperGirlP5Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter, ModelLayerLocation armor) {
			super(context, new HostilePregnantHumanoidCreeperGirlModel.MonsterHumanoidCreeperGirlP5Model(context.bakeLayer(main)), new HostilePregnantHumanoidCreeperGirlModel.MonsterHumanoidCreeperGirlP5Model(context.bakeLayer(inner)), new HostilePregnantHumanoidCreeperGirlModel.MonsterHumanoidCreeperGirlP5Model(context.bakeLayer(outter)), new HostilePregnantHumanoidCreeperGirlModel.MonsterHumanoidCreeperGirlP5Model(context.bakeLayer(armor)));
		}

		@Override
		public ResourceLocation getTextureLocation(HostilePregnantHumanoidCreeperGirl.MonsterHumanoidCreeperGirlP5 p_115812_) {
			return AbstractHumanoidCreeperGirlRenderer.CREEPER_GIRL_P5_LOCATION.getLeft();
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class MonsterHumanoidCreeperGirlP7Renderer extends AbstractHostilePregnantHumanoidCreeperGirlRenderer<HostilePregnantHumanoidCreeperGirl.MonsterHumanoidCreeperGirlP7, HostilePregnantHumanoidCreeperGirlModel.MonsterHumanoidCreeperGirlP7Model> {
		
		public MonsterHumanoidCreeperGirlP7Renderer(EntityRendererProvider.Context context) {
			this(context, AbstractHumanoidCreeperGirlModel.LAYER_LOCATION_P7, AbstractHumanoidCreeperGirlModel.LAYER_INNER_ARMOR_LOCATION, AbstractHumanoidCreeperGirlModel.LAYER_OUTER_ARMOR_LOCATION, AbstractHumanoidCreeperGirlModel.LAYER_ENERGY_ARMOR_P7_LOCATION);
		}
		
		public MonsterHumanoidCreeperGirlP7Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter, ModelLayerLocation armor) {
			super(context, new HostilePregnantHumanoidCreeperGirlModel.MonsterHumanoidCreeperGirlP7Model(context.bakeLayer(main)), new HostilePregnantHumanoidCreeperGirlModel.MonsterHumanoidCreeperGirlP7Model(context.bakeLayer(inner)), new HostilePregnantHumanoidCreeperGirlModel.MonsterHumanoidCreeperGirlP7Model(context.bakeLayer(outter)), new HostilePregnantHumanoidCreeperGirlModel.MonsterHumanoidCreeperGirlP7Model(context.bakeLayer(armor)));
		}
	
		@Override
		public ResourceLocation getTextureLocation(HostilePregnantHumanoidCreeperGirl.MonsterHumanoidCreeperGirlP7 p_115812_) {
			return AbstractHumanoidCreeperGirlRenderer.CREEPER_GIRL_P7_LOCATION.getLeft();
		}
	}
	
}
