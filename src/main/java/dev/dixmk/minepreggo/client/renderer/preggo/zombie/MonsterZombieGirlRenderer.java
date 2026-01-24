package dev.dixmk.minepreggo.client.renderer.preggo.zombie;

import dev.dixmk.minepreggo.client.model.entity.preggo.zombie.MonsterZombieGirlModel;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.MonsterZombieGirl;

import dev.dixmk.minepreggo.client.model.entity.preggo.zombie.AbstractZombieGirlModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
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
	public ResourceLocation getTextureLocation(MonsterZombieGirl p_115812_) {
		return ZOMBIE_GIRL_LOCATION;
	}
}
