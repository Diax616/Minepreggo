package dev.dixmk.minepreggo.client.renderer.preggo.creeper;

import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractHumanoidCreeperGirlModel;
import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.MonsterCreeperGirlP3Model;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.MonsterCreeperGirlP3;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MonsterCreeperGirlP3Renderer extends AbstractMonsterPregnantCreeperGirlRenderer<MonsterCreeperGirlP3, MonsterCreeperGirlP3Model> {
	
	public MonsterCreeperGirlP3Renderer(EntityRendererProvider.Context context) {
		this(context, AbstractHumanoidCreeperGirlModel.LAYER_LOCATION_P3, AbstractHumanoidCreeperGirlModel.LAYER_INNER_ARMOR_LOCATION, AbstractHumanoidCreeperGirlModel.LAYER_OUTER_ARMOR_LOCATION, AbstractHumanoidCreeperGirlModel.LAYER_ENERGY_ARMOR_P3_LOCATION);
	}
	
	public MonsterCreeperGirlP3Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter, ModelLayerLocation armor) {
		super(context, new MonsterCreeperGirlP3Model(context.bakeLayer(main)), new MonsterCreeperGirlP3Model(context.bakeLayer(inner)), new MonsterCreeperGirlP3Model(context.bakeLayer(outter)), new MonsterCreeperGirlP3Model(context.bakeLayer(armor)));
	}

	@Override
	public ResourceLocation getTextureLocation(MonsterCreeperGirlP3 p_115812_) {
		return CREEPER_GIRL_P3_LOCATION;
	}
}