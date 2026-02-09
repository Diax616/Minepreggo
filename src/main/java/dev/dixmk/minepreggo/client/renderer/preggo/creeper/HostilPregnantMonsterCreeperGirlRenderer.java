package dev.dixmk.minepreggo.client.renderer.preggo.creeper;

import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractMonsterCreeperGirlModel;
import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.HostilPregnantMonsterCreeperGirlModel;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.HostilPregnantMonsterCreeperGirl;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HostilPregnantMonsterCreeperGirlRenderer {
	
	@OnlyIn(Dist.CLIENT)
	public static class MonsterCreeperGirlP3 extends AbstractHostilPregnantMonsterCreeperGirlRenderer<HostilPregnantMonsterCreeperGirl.MonsterCreeperGirlP3, HostilPregnantMonsterCreeperGirlModel.MonsterCreeperGirlP3> {
		public MonsterCreeperGirlP3(EntityRendererProvider.Context context) {
			this(context, AbstractMonsterCreeperGirlModel.LAYER_LOCATION_P3, AbstractMonsterCreeperGirlModel.LAYER_ENERGY_ARMOR_P3_LOCATION);
		}	
		public MonsterCreeperGirlP3(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation eneryArmor) {
			super(context, new HostilPregnantMonsterCreeperGirlModel.MonsterCreeperGirlP3(context.bakeLayer(main)), new HostilPregnantMonsterCreeperGirlModel.MonsterCreeperGirlP3(context.bakeLayer(eneryArmor)));
		}	
		@Override
		public ResourceLocation getTextureLocation(HostilPregnantMonsterCreeperGirl.MonsterCreeperGirlP3 p_115812_) {
			return MONSTER_CREEPER_GIRL_P3_LOCATION;
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class MonsterCreeperGirlP5 extends AbstractHostilPregnantMonsterCreeperGirlRenderer<HostilPregnantMonsterCreeperGirl.MonsterCreeperGirlP5, HostilPregnantMonsterCreeperGirlModel.MonsterCreeperGirlP5> {
		public MonsterCreeperGirlP5(EntityRendererProvider.Context context) {
			this(context, AbstractMonsterCreeperGirlModel.LAYER_LOCATION_P5, AbstractMonsterCreeperGirlModel.LAYER_ENERGY_ARMOR_P5_LOCATION);
		}	
		public MonsterCreeperGirlP5(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation eneryArmor) {
			super(context, new HostilPregnantMonsterCreeperGirlModel.MonsterCreeperGirlP5(context.bakeLayer(main)), new HostilPregnantMonsterCreeperGirlModel.MonsterCreeperGirlP5(context.bakeLayer(eneryArmor)));
		}	
		@Override
		public ResourceLocation getTextureLocation(HostilPregnantMonsterCreeperGirl.MonsterCreeperGirlP5 p_115812_) {
			return MONSTER_CREEPER_GIRL_P5_LOCATION;
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class MonsterCreeperGirlP7 extends AbstractHostilPregnantMonsterCreeperGirlRenderer<HostilPregnantMonsterCreeperGirl.MonsterCreeperGirlP7, HostilPregnantMonsterCreeperGirlModel.MonsterCreeperGirlP7> {
		public MonsterCreeperGirlP7(EntityRendererProvider.Context context) {
			this(context, AbstractMonsterCreeperGirlModel.LAYER_LOCATION_P7, AbstractMonsterCreeperGirlModel.LAYER_ENERGY_ARMOR_P7_LOCATION);
		}	
		public MonsterCreeperGirlP7(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation eneryArmor) {
			super(context, new HostilPregnantMonsterCreeperGirlModel.MonsterCreeperGirlP7(context.bakeLayer(main)), new HostilPregnantMonsterCreeperGirlModel.MonsterCreeperGirlP7(context.bakeLayer(eneryArmor)));
		}	
		@Override
		public ResourceLocation getTextureLocation(HostilPregnantMonsterCreeperGirl.MonsterCreeperGirlP7 p_115812_) {
			return MONSTER_CREEPER_GIRL_P7_LOCATION;
		}
	}
}
