package dev.dixmk.minepreggo.client.renderer.preggo.zombie;

import dev.dixmk.minepreggo.client.model.entity.preggo.zombie.HostilePregnantZombieGirlModel;
import dev.dixmk.minepreggo.client.model.geom.MinepreggoModelLayers;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.HostilePregnantZombieGirl;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HostilePregnantZombieGirlRenderer {

	private HostilePregnantZombieGirlRenderer() {}
	
	@OnlyIn(Dist.CLIENT)
	public static class MonsterZombieGirlP3Renderer extends AbstractHostilePregnantZombieGirlRenderer<HostilePregnantZombieGirl.MonsterZombieGirlP3, HostilePregnantZombieGirlModel.MonsterZombieGirlP3Model> {
		public MonsterZombieGirlP3Renderer(EntityRendererProvider.Context context) {
			this(context, MinepreggoModelLayers.PREGNANT_ZOMBIE_GIRL_P3, MinepreggoModelLayers.ZOMBIE_GIRL_INNER_ARMOR, MinepreggoModelLayers.ZOMBIE_GIRL_OUTER_ARMOR);
		}
		
		public MonsterZombieGirlP3Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter) {
			super(context, new HostilePregnantZombieGirlModel.MonsterZombieGirlP3Model(context.bakeLayer(main)), new HostilePregnantZombieGirlModel.MonsterZombieGirlP3Model(context.bakeLayer(inner)), new HostilePregnantZombieGirlModel.MonsterZombieGirlP3Model(context.bakeLayer(outter)));
		}
		
		@Override
		public ResourceLocation getTextureLocation(HostilePregnantZombieGirl.MonsterZombieGirlP3 entity) {
			return ZOMBIE_GIRL_P3_LOCATION;
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class MonsterZombieGirlP5Renderer extends AbstractHostilePregnantZombieGirlRenderer<HostilePregnantZombieGirl.MonsterZombieGirlP5, HostilePregnantZombieGirlModel.MonsterZombieGirlP5Model> {
		public MonsterZombieGirlP5Renderer(EntityRendererProvider.Context context) {
			this(context, MinepreggoModelLayers.PREGNANT_ZOMBIE_GIRL_P5, MinepreggoModelLayers.ZOMBIE_GIRL_INNER_ARMOR, MinepreggoModelLayers.ZOMBIE_GIRL_OUTER_ARMOR);
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
			this(context, MinepreggoModelLayers.PREGNANT_ZOMBIE_GIRL_P7, MinepreggoModelLayers.ZOMBIE_GIRL_INNER_ARMOR, MinepreggoModelLayers.ZOMBIE_GIRL_OUTER_ARMOR);
		}
		
		public MonsterZombieGirlP7Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter) {
			super(context, new HostilePregnantZombieGirlModel.MonsterZombieGirlP7Model(context.bakeLayer(main)), new HostilePregnantZombieGirlModel.MonsterZombieGirlP7Model(context.bakeLayer(inner)), new HostilePregnantZombieGirlModel.MonsterZombieGirlP7Model(context.bakeLayer(outter)));
		}
		
		@Override
		public ResourceLocation getTextureLocation(HostilePregnantZombieGirl.MonsterZombieGirlP7 entity) {
			return ZOMBIE_GIRL_P7_LOCATION.getLeft(); 
		}
	}
}
