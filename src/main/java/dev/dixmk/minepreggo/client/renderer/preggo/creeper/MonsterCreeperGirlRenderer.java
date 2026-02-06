package dev.dixmk.minepreggo.client.renderer.preggo.creeper;

import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.quadruped.AbstractCreeperGirlModel;
import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.quadruped.MonsterCreeperGirlModel;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.MonsterCreeperGirl;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MonsterCreeperGirlRenderer extends AbstractMonsterCreeperGirlRenderer<MonsterCreeperGirl, MonsterCreeperGirlModel> {
	
	public MonsterCreeperGirlRenderer(EntityRendererProvider.Context context) {
		this(context, AbstractCreeperGirlModel.LAYER_LOCATION, AbstractCreeperGirlModel.LAYER_ENERGY_ARMOR_LOCATION);
	}
	
	public MonsterCreeperGirlRenderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation armor) {
		super(context, new MonsterCreeperGirlModel(context.bakeLayer(main)), new MonsterCreeperGirlModel(context.bakeLayer(armor)));
	}
	
	@Override
	public ResourceLocation getTextureLocation(MonsterCreeperGirl p_115812_) {
		return MONSTER_CREEPER_GIRL_LOCATION;
	}
}
