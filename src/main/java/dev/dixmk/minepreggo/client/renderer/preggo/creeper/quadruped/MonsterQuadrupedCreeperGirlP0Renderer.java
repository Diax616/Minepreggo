package dev.dixmk.minepreggo.client.renderer.preggo.creeper.quadruped;

import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.quadruped.AbstractQueadrupedCreeperGirlModel;
import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.quadruped.MonsterQuadrupedCreeperGirlP0Model;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.MonsterQuadrupedCreeperGirlP0;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MonsterQuadrupedCreeperGirlP0Renderer extends AbstractMonsterQuadrupedCreeperGirlRenderer<MonsterQuadrupedCreeperGirlP0, MonsterQuadrupedCreeperGirlP0Model> {
	
	public MonsterQuadrupedCreeperGirlP0Renderer(EntityRendererProvider.Context context) {
		this(context, AbstractQueadrupedCreeperGirlModel.LAYER_LOCATION_P0, AbstractQueadrupedCreeperGirlModel.LAYER_ENERGY_ARMOR_P0_LOCATION);
	}
	
	public MonsterQuadrupedCreeperGirlP0Renderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation armor) {
		super(context, new MonsterQuadrupedCreeperGirlP0Model(context.bakeLayer(main)), new MonsterQuadrupedCreeperGirlP0Model(context.bakeLayer(armor)));
	}
	
	@Override
	public ResourceLocation getTextureLocation(MonsterQuadrupedCreeperGirlP0 p_115812_) {
		return QUADRUPED_CREEPER_GIRL_P0_LOCATION;
	}
}
