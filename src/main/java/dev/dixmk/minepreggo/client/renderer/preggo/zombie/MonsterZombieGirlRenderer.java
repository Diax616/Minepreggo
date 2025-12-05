package dev.dixmk.minepreggo.client.renderer.preggo.zombie;

import dev.dixmk.minepreggo.client.model.entity.preggo.zombie.MonsterZombieGirlModel;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.MonsterZombieGirl;

import com.mojang.blaze3d.vertex.PoseStack;

import dev.dixmk.minepreggo.client.model.entity.preggo.zombie.AbstractZombieGirlModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MonsterZombieGirlRenderer extends AbstractMonsterZombieGirlRenderer<MonsterZombieGirl, MonsterZombieGirlModel> {

	public MonsterZombieGirlRenderer(EntityRendererProvider.Context context) {
		this(context, AbstractZombieGirlModel.LAYER_LOCATION, AbstractZombieGirlModel.LAYER_INNER_ARMOR_LOCATION, AbstractZombieGirlModel.LAYER_OUTER_ARMOR_LOCATION);
	}
	
	public MonsterZombieGirlRenderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter) {
		super(context, new MonsterZombieGirlModel(context.bakeLayer(main)), new MonsterZombieGirlModel(context.bakeLayer(inner)), new MonsterZombieGirlModel(context.bakeLayer(outter)));
	}
	
	@Override
	public void render(MonsterZombieGirl p_115455_, float p_115456_, float p_115457_, PoseStack p_115458_, MultiBufferSource p_115459_, int p_115460_) {
		if (p_115455_.isBaby()) {
			p_115458_.scale(0.575F, 0.575F, 0.575F);
		}			
		super.render(p_115455_, p_115456_, p_115457_, p_115458_, p_115459_, p_115460_);
	}

	@Override
	public ResourceLocation getTextureLocation(MonsterZombieGirl p_115812_) {
		return ZOMBIE_GIRL_LOCATION;
	}
}
