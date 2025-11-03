package dev.dixmk.minepreggo.client.renderer.preggo.zombie;

import dev.dixmk.minepreggo.client.model.entity.preggo.zombie.AbstractZombieGirlModel;
import dev.dixmk.minepreggo.client.model.entity.preggo.zombie.MonsterZombieGirlP3Model;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.MonsterZombieGirlP3;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MonsterZombieGirlP3Renderer extends AbstractMonsterPregnantZombieGirlRenderer<MonsterZombieGirlP3, MonsterZombieGirlP3Model> {
	public MonsterZombieGirlP3Renderer(EntityRendererProvider.Context context) {
		this(context, AbstractZombieGirlModel.LAYER_LOCATION_P3, AbstractZombieGirlModel.LAYER_INNER_ARMOR_LOCATION, AbstractZombieGirlModel.LAYER_OUTER_ARMOR_LOCATION);
	}
	
	public MonsterZombieGirlP3Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter) {
		super(context, new MonsterZombieGirlP3Model(context.bakeLayer(main)), new MonsterZombieGirlP3Model(context.bakeLayer(inner)), new MonsterZombieGirlP3Model(context.bakeLayer(outter)));
	}
	
	@Override
	public ResourceLocation getTextureLocation(MonsterZombieGirlP3 p_115812_) {
		return ZOMBIE_GIRL_P3_LOCATION;
	}
}
