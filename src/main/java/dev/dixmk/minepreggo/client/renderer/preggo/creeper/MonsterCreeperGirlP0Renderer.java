package dev.dixmk.minepreggo.client.renderer.preggo.creeper;

import com.mojang.blaze3d.vertex.PoseStack;

import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractHumanoidCreeperGirlModel;
import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.MonsterCreeperGirlP0Model;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.MonsterCreeperGirlP0;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MonsterCreeperGirlP0Renderer extends AbstractMonsterCreeperGirlRenderer<MonsterCreeperGirlP0, MonsterCreeperGirlP0Model> {
	
	public MonsterCreeperGirlP0Renderer(EntityRendererProvider.Context context) {
		this(context, AbstractHumanoidCreeperGirlModel.LAYER_LOCATION_P1, AbstractHumanoidCreeperGirlModel.LAYER_INNER_ARMOR_LOCATION, AbstractHumanoidCreeperGirlModel.LAYER_OUTER_ARMOR_LOCATION, AbstractHumanoidCreeperGirlModel.LAYER_ENERGY_ARMOR_P0_LOCATION);
	}
	
	public MonsterCreeperGirlP0Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter, ModelLayerLocation armor) {
		super(context, new MonsterCreeperGirlP0Model(context.bakeLayer(main)), new MonsterCreeperGirlP0Model(context.bakeLayer(inner)), new MonsterCreeperGirlP0Model(context.bakeLayer(outter)), new MonsterCreeperGirlP0Model(context.bakeLayer(armor)));
	}
	
	@Override
	public void render(MonsterCreeperGirlP0 p_115455_, float p_115456_, float p_115457_, PoseStack p_115458_, MultiBufferSource p_115459_, int p_115460_) {
		if (p_115455_.isBaby()) {
			p_115458_.scale(0.575F, 0.575F, 0.575F);
		}				
		super.render(p_115455_, p_115456_, p_115457_, p_115458_, p_115459_, p_115460_);
	}

	@Override
	public ResourceLocation getTextureLocation(MonsterCreeperGirlP0 p_115812_) {
		return CREEPER_GIRL_P0_LOCATION;
	}
}
