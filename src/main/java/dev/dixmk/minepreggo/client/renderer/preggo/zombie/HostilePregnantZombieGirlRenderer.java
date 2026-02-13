package dev.dixmk.minepreggo.client.renderer.preggo.zombie;

import dev.dixmk.minepreggo.client.model.entity.preggo.zombie.AbstractZombieGirlModel;
import dev.dixmk.minepreggo.client.model.entity.preggo.zombie.HostilePregnantZombieGirlModel;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.HostilePregnantZombieGirl;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HostilePregnantZombieGirlRenderer {

	@OnlyIn(Dist.CLIENT)
	public static class MonsterZombieGirlP3Renderer extends AbstractHostilePregnantZombieGirlRenderer<HostilePregnantZombieGirl.MonsterZombieGirlP3, HostilePregnantZombieGirlModel.MonsterZombieGirlP3Model> {
		public MonsterZombieGirlP3Renderer(EntityRendererProvider.Context context) {
			this(context, AbstractZombieGirlModel.LAYER_LOCATION_P3, AbstractZombieGirlModel.LAYER_INNER_ARMOR_LOCATION, AbstractZombieGirlModel.LAYER_OUTER_ARMOR_LOCATION);
		}
		
		public MonsterZombieGirlP3Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter) {
			super(context, new HostilePregnantZombieGirlModel.MonsterZombieGirlP3Model(context.bakeLayer(main)), new HostilePregnantZombieGirlModel.MonsterZombieGirlP3Model(context.bakeLayer(inner)), new HostilePregnantZombieGirlModel.MonsterZombieGirlP3Model(context.bakeLayer(outter)));
		}
		
		@Override
		public ResourceLocation getTextureLocation(HostilePregnantZombieGirl.MonsterZombieGirlP3 p_115812_) {
			return ZOMBIE_GIRL_P3_LOCATION;
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class MonsterZombieGirlP5Renderer extends AbstractHostilePregnantZombieGirlRenderer<HostilePregnantZombieGirl.MonsterZombieGirlP5, HostilePregnantZombieGirlModel.MonsterZombieGirlP5Model> {
		public MonsterZombieGirlP5Renderer(EntityRendererProvider.Context context) {
			this(context, AbstractZombieGirlModel.LAYER_LOCATION_P5, AbstractZombieGirlModel.LAYER_INNER_ARMOR_LOCATION, AbstractZombieGirlModel.LAYER_OUTER_ARMOR_LOCATION);
		}
		
		public MonsterZombieGirlP5Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter) {
			super(context, new HostilePregnantZombieGirlModel.MonsterZombieGirlP5Model(context.bakeLayer(main)), new HostilePregnantZombieGirlModel.MonsterZombieGirlP5Model(context.bakeLayer(inner)), new HostilePregnantZombieGirlModel.MonsterZombieGirlP5Model(context.bakeLayer(outter)));
		}
		
		@Override
		public ResourceLocation getTextureLocation(HostilePregnantZombieGirl.MonsterZombieGirlP5 entity) {
			return ZOMBIE_GIRL_P5_LOCATION.getLeft(); 
		}
	}


	@OnlyIn(Dist.CLIENT)
	public static class MonsterZombieGirlP7Renderer extends AbstractHostilePregnantZombieGirlRenderer<HostilePregnantZombieGirl.MonsterZombieGirlP7, HostilePregnantZombieGirlModel.MonsterZombieGirlP7Model> {
		public MonsterZombieGirlP7Renderer(EntityRendererProvider.Context context) {
			this(context, AbstractZombieGirlModel.LAYER_LOCATION_P7, AbstractZombieGirlModel.LAYER_INNER_ARMOR_LOCATION, AbstractZombieGirlModel.LAYER_OUTER_ARMOR_LOCATION);
		}
		
		public MonsterZombieGirlP7Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter) {
			super(context, new HostilePregnantZombieGirlModel.MonsterZombieGirlP7Model(context.bakeLayer(main)), new HostilePregnantZombieGirlModel.MonsterZombieGirlP7Model(context.bakeLayer(inner)), new HostilePregnantZombieGirlModel.MonsterZombieGirlP7Model(context.bakeLayer(outter)));
		}
		
		@Override
		public ResourceLocation getTextureLocation(HostilePregnantZombieGirl.MonsterZombieGirlP7 p_115812_) {
			return ZOMBIE_GIRL_P7_LOCATION.getLeft(); 
		}
	}
}
