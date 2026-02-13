package dev.dixmk.minepreggo.client.renderer.preggo.creeper;

import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractMonsterCreeperGirlModel;
import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.HostilePregnantMonsterCreeperGirlModel;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.HostilePregnantMonsterCreeperGirl;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HostilePregnantMonsterCreeperGirlRenderer {
	
	@OnlyIn(Dist.CLIENT)
	public static class MonsterCreeperGirlP3 extends AbstractHostilePregnantMonsterCreeperGirlRenderer<HostilePregnantMonsterCreeperGirl.MonsterCreeperGirlP3, HostilePregnantMonsterCreeperGirlModel.MonsterCreeperGirlP3> {
		public MonsterCreeperGirlP3(EntityRendererProvider.Context context) {
			this(context, AbstractMonsterCreeperGirlModel.LAYER_LOCATION_P3, AbstractMonsterCreeperGirlModel.LAYER_ENERGY_ARMOR_P3_LOCATION);
		}	
		public MonsterCreeperGirlP3(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation eneryArmor) {
			super(context, new HostilePregnantMonsterCreeperGirlModel.MonsterCreeperGirlP3(context.bakeLayer(main)), new HostilePregnantMonsterCreeperGirlModel.MonsterCreeperGirlP3(context.bakeLayer(eneryArmor)));
		}	
		@Override
		public ResourceLocation getTextureLocation(HostilePregnantMonsterCreeperGirl.MonsterCreeperGirlP3 p_115812_) {
			return MONSTER_CREEPER_GIRL_P3_LOCATION;
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class MonsterCreeperGirlP5 extends AbstractHostilePregnantMonsterCreeperGirlRenderer<HostilePregnantMonsterCreeperGirl.MonsterCreeperGirlP5, HostilePregnantMonsterCreeperGirlModel.MonsterCreeperGirlP5> {
		public MonsterCreeperGirlP5(EntityRendererProvider.Context context) {
			this(context, AbstractMonsterCreeperGirlModel.LAYER_LOCATION_P5, AbstractMonsterCreeperGirlModel.LAYER_ENERGY_ARMOR_P5_LOCATION);
		}	
		public MonsterCreeperGirlP5(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation eneryArmor) {
			super(context, new HostilePregnantMonsterCreeperGirlModel.MonsterCreeperGirlP5(context.bakeLayer(main)), new HostilePregnantMonsterCreeperGirlModel.MonsterCreeperGirlP5(context.bakeLayer(eneryArmor)));
		}	
		@Override
		public ResourceLocation getTextureLocation(HostilePregnantMonsterCreeperGirl.MonsterCreeperGirlP5 p_115812_) {
			return MONSTER_CREEPER_GIRL_P5_LOCATION;
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class MonsterCreeperGirlP7 extends AbstractHostilePregnantMonsterCreeperGirlRenderer<HostilePregnantMonsterCreeperGirl.MonsterCreeperGirlP7, HostilePregnantMonsterCreeperGirlModel.MonsterCreeperGirlP7> {
		public MonsterCreeperGirlP7(EntityRendererProvider.Context context) {
			this(context, AbstractMonsterCreeperGirlModel.LAYER_LOCATION_P7, AbstractMonsterCreeperGirlModel.LAYER_ENERGY_ARMOR_P7_LOCATION);
		}	
		public MonsterCreeperGirlP7(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation eneryArmor) {
			super(context, new HostilePregnantMonsterCreeperGirlModel.MonsterCreeperGirlP7(context.bakeLayer(main)), new HostilePregnantMonsterCreeperGirlModel.MonsterCreeperGirlP7(context.bakeLayer(eneryArmor)));
		}	
		@Override
		public ResourceLocation getTextureLocation(HostilePregnantMonsterCreeperGirl.MonsterCreeperGirlP7 p_115812_) {
			return MONSTER_CREEPER_GIRL_P7_LOCATION;
		}
	}
}
