package dev.dixmk.minepreggo.client.renderer.preggo.ender;

import dev.dixmk.minepreggo.client.model.entity.preggo.ender.AbstractMonsterEnderWomanModel;
import dev.dixmk.minepreggo.client.model.entity.preggo.ender.HostilPregnantMonsterEnderWomanModel;
import dev.dixmk.minepreggo.world.entity.preggo.ender.HostilPregnantMonsterEnderWoman;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HostilPregnantMonsterEnderWomanRenderer {

	@OnlyIn(Dist.CLIENT)
	public static class MonsteEnderWomanP3 extends AbstractHostilPregnantMonsterEnderWomanRenderer<HostilPregnantMonsterEnderWoman.MonsterEnderWomanP3, HostilPregnantMonsterEnderWomanModel.MonsterEnderWomanP3> {
		protected MonsteEnderWomanP3(Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter) {
			super(context, new HostilPregnantMonsterEnderWomanModel.MonsterEnderWomanP3(context.bakeLayer(main)), new HostilPregnantMonsterEnderWomanModel.MonsterEnderWomanP3(context.bakeLayer(inner)), new HostilPregnantMonsterEnderWomanModel.MonsterEnderWomanP3(context.bakeLayer(outter)));
		}

		public MonsteEnderWomanP3(EntityRendererProvider.Context context) {
			this(context, AbstractMonsterEnderWomanModel.LAYER_LOCATION_P3, AbstractMonsterEnderWomanModel.LAYER_INNER_ARMOR_LOCATION, AbstractMonsterEnderWomanModel.LAYER_OUTER_ARMOR_LOCATION);
		}
		
		@Override
		public ResourceLocation getTextureLocation(HostilPregnantMonsterEnderWoman.MonsterEnderWomanP3 p_115812_) {
			return MONSTER_PREGNANT_ENDER_WOMAN_P3_TEXTURE;
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class MonsteEnderWomanP5 extends AbstractHostilPregnantMonsterEnderWomanRenderer<HostilPregnantMonsterEnderWoman.MonsterEnderWomanP5, HostilPregnantMonsterEnderWomanModel.MonsterEnderWomanP5> {
		protected MonsteEnderWomanP5(Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter) {
			super(context, new HostilPregnantMonsterEnderWomanModel.MonsterEnderWomanP5(context.bakeLayer(main)), new HostilPregnantMonsterEnderWomanModel.MonsterEnderWomanP5(context.bakeLayer(inner)), new HostilPregnantMonsterEnderWomanModel.MonsterEnderWomanP5(context.bakeLayer(outter)));
		}

		public MonsteEnderWomanP5(EntityRendererProvider.Context context) {
			this(context, AbstractMonsterEnderWomanModel.LAYER_LOCATION_P5, AbstractMonsterEnderWomanModel.LAYER_INNER_ARMOR_LOCATION, AbstractMonsterEnderWomanModel.LAYER_OUTER_ARMOR_LOCATION);
		}

		@Override
		public ResourceLocation getTextureLocation(HostilPregnantMonsterEnderWoman.MonsterEnderWomanP5 p_115812_) {
			return MONSTER_PREGNANT_ENDER_WOMAN_P5_TEXTURE;
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class MonsteEnderWomanP7 extends AbstractHostilPregnantMonsterEnderWomanRenderer<HostilPregnantMonsterEnderWoman.MonsterEnderWomanP7, HostilPregnantMonsterEnderWomanModel.MonsterEnderWomanP7> {
		protected MonsteEnderWomanP7(Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter) {
			super(context, new HostilPregnantMonsterEnderWomanModel.MonsterEnderWomanP7(context.bakeLayer(main)), new HostilPregnantMonsterEnderWomanModel.MonsterEnderWomanP7(context.bakeLayer(inner)), new HostilPregnantMonsterEnderWomanModel.MonsterEnderWomanP7(context.bakeLayer(outter)));
		}

		public MonsteEnderWomanP7(EntityRendererProvider.Context context) {
			this(context, AbstractMonsterEnderWomanModel.LAYER_LOCATION_P7, AbstractMonsterEnderWomanModel.LAYER_INNER_ARMOR_LOCATION, AbstractMonsterEnderWomanModel.LAYER_OUTER_ARMOR_LOCATION);
		}
		
		@Override
		public ResourceLocation getTextureLocation(HostilPregnantMonsterEnderWoman.MonsterEnderWomanP7 p_115812_) {
			return MONSTER_PREGNANT_ENDER_WOMAN_P7_TEXTURE;
		}
	}
}
