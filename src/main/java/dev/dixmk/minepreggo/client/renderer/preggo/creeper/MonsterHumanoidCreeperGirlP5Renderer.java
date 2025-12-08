package dev.dixmk.minepreggo.client.renderer.preggo.creeper;

import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractHumanoidCreeperGirlModel;
import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.MonsterHumanoidCreeperGirlP5Model;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.MonsterHumanoidCreeperGirlP5;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MonsterHumanoidCreeperGirlP5Renderer extends AbstractMonsterHumanoidPregnantCreeperGirlRenderer<MonsterHumanoidCreeperGirlP5, MonsterHumanoidCreeperGirlP5Model> {
	
	public MonsterHumanoidCreeperGirlP5Renderer(EntityRendererProvider.Context context) {
		this(context, AbstractHumanoidCreeperGirlModel.LAYER_LOCATION_P5, AbstractHumanoidCreeperGirlModel.LAYER_INNER_ARMOR_LOCATION, AbstractHumanoidCreeperGirlModel.LAYER_OUTER_ARMOR_LOCATION, AbstractHumanoidCreeperGirlModel.LAYER_ENERGY_ARMOR_P5_LOCATION);
	}
	
	public MonsterHumanoidCreeperGirlP5Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter, ModelLayerLocation armor) {
		super(context, new MonsterHumanoidCreeperGirlP5Model(context.bakeLayer(main)), new MonsterHumanoidCreeperGirlP5Model(context.bakeLayer(inner)), new MonsterHumanoidCreeperGirlP5Model(context.bakeLayer(outter)), new MonsterHumanoidCreeperGirlP5Model(context.bakeLayer(armor)));
	}

	@Override
	public ResourceLocation getTextureLocation(MonsterHumanoidCreeperGirlP5 p_115812_) {
		return AbstractHumanoidCreeperGirlRenderer.CREEPER_GIRL_P5_LOCATION.getLeft();
	}
}
