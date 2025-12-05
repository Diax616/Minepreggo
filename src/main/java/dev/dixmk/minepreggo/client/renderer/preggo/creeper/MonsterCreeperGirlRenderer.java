package dev.dixmk.minepreggo.client.renderer.preggo.creeper;

import com.mojang.blaze3d.vertex.PoseStack;

import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractHumanoidCreeperGirlModel;
import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.MonsterCreeperGirlModel;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.MonsterCreeperGirl;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MonsterCreeperGirlRenderer extends AbstractMonsterCreeperGirlRenderer<MonsterCreeperGirl, MonsterCreeperGirlModel> {
	
	public MonsterCreeperGirlRenderer(EntityRendererProvider.Context context) {
		this(context, AbstractHumanoidCreeperGirlModel.LAYER_LOCATION, AbstractHumanoidCreeperGirlModel.LAYER_INNER_ARMOR_LOCATION, AbstractHumanoidCreeperGirlModel.LAYER_OUTER_ARMOR_LOCATION, AbstractHumanoidCreeperGirlModel.LAYER_ENERGY_ARMOR_LOCATION);
	}
	
	public MonsterCreeperGirlRenderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter, ModelLayerLocation armor) {
		super(context, new MonsterCreeperGirlModel(context.bakeLayer(main)), new MonsterCreeperGirlModel(context.bakeLayer(inner)), new MonsterCreeperGirlModel(context.bakeLayer(outter)), new MonsterCreeperGirlModel(context.bakeLayer(armor)));
	}
	
	@Override
	public void render(MonsterCreeperGirl p_115455_, float p_115456_, float p_115457_, PoseStack p_115458_, MultiBufferSource p_115459_, int p_115460_) {
		if (p_115455_.isBaby()) {
			p_115458_.scale(0.575F, 0.575F, 0.575F);
		}				
		super.render(p_115455_, p_115456_, p_115457_, p_115458_, p_115459_, p_115460_);
	}

	@Override
	public ResourceLocation getTextureLocation(MonsterCreeperGirl p_115812_) {
		return CREEPER_GIRL_LOCATION;
	}
}
