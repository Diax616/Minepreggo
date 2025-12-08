package dev.dixmk.minepreggo.client.renderer.preggo.creeper;

import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractHumanoidCreeperGirlModel;
import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.MonsterHumanoidCreeperGirlP7Model;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.MonsterHumanoidCreeperGirlP7;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MonsterHumanoidCreeperGirlP7Renderer extends AbstractMonsterHumanoidPregnantCreeperGirlRenderer<MonsterHumanoidCreeperGirlP7, MonsterHumanoidCreeperGirlP7Model> {
	
	public MonsterHumanoidCreeperGirlP7Renderer(EntityRendererProvider.Context context) {
		this(context, AbstractHumanoidCreeperGirlModel.LAYER_LOCATION_P7, AbstractHumanoidCreeperGirlModel.LAYER_INNER_ARMOR_LOCATION, AbstractHumanoidCreeperGirlModel.LAYER_OUTER_ARMOR_LOCATION, AbstractHumanoidCreeperGirlModel.LAYER_ENERGY_ARMOR_P7_LOCATION);
	}
	
	public MonsterHumanoidCreeperGirlP7Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter, ModelLayerLocation armor) {
		super(context, new MonsterHumanoidCreeperGirlP7Model(context.bakeLayer(main)), new MonsterHumanoidCreeperGirlP7Model(context.bakeLayer(inner)), new MonsterHumanoidCreeperGirlP7Model(context.bakeLayer(outter)), new MonsterHumanoidCreeperGirlP7Model(context.bakeLayer(armor)));
	}

	@Override
	public ResourceLocation getTextureLocation(MonsterHumanoidCreeperGirlP7 p_115812_) {
		return AbstractHumanoidCreeperGirlRenderer.CREEPER_GIRL_P7_LOCATION.getLeft();
	}
}