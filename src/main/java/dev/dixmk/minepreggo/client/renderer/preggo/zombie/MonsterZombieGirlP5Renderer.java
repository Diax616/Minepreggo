package dev.dixmk.minepreggo.client.renderer.preggo.zombie;

import dev.dixmk.minepreggo.client.model.entity.preggo.zombie.AbstractZombieGirlModel;
import dev.dixmk.minepreggo.client.model.entity.preggo.zombie.MonsterZombieGirlP5Model;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.MonsterZombieGirlP5;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MonsterZombieGirlP5Renderer extends AbstractMonsterPregnantZombieGirlRenderer<MonsterZombieGirlP5, MonsterZombieGirlP5Model> {
	public MonsterZombieGirlP5Renderer(EntityRendererProvider.Context context) {
		this(context, AbstractZombieGirlModel.LAYER_LOCATION_P5, AbstractZombieGirlModel.LAYER_INNER_ARMOR_LOCATION, AbstractZombieGirlModel.LAYER_OUTER_ARMOR_LOCATION);
	}
	
	public MonsterZombieGirlP5Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter) {
		super(context, new MonsterZombieGirlP5Model(context.bakeLayer(main)), new MonsterZombieGirlP5Model(context.bakeLayer(inner)), new MonsterZombieGirlP5Model(context.bakeLayer(outter)));
	}
	
	@Override
	public ResourceLocation getTextureLocation(MonsterZombieGirlP5 entity) {
		return ZOMBIE_GIRL_P5_LOCATION.getLeft(); 
	}
}
