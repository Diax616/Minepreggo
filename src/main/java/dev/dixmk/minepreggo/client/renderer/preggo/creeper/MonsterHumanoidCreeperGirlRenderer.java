package dev.dixmk.minepreggo.client.renderer.preggo.creeper;

import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractHumanoidCreeperGirlModel;
import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.MonsterHumanoidCreeperGirlModel;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.MonsterHumanoidCreeperGirl;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MonsterHumanoidCreeperGirlRenderer extends AbstractHumanoidMonsterCreeperGirlRenderer<MonsterHumanoidCreeperGirl, MonsterHumanoidCreeperGirlModel> {
	
	public MonsterHumanoidCreeperGirlRenderer(EntityRendererProvider.Context context) {
		this(context, AbstractHumanoidCreeperGirlModel.LAYER_LOCATION, AbstractHumanoidCreeperGirlModel.LAYER_INNER_ARMOR_LOCATION, AbstractHumanoidCreeperGirlModel.LAYER_OUTER_ARMOR_LOCATION, AbstractHumanoidCreeperGirlModel.LAYER_ENERGY_ARMOR_LOCATION);
	}
	
	public MonsterHumanoidCreeperGirlRenderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter, ModelLayerLocation armor) {
		super(context, new MonsterHumanoidCreeperGirlModel(context.bakeLayer(main)), new MonsterHumanoidCreeperGirlModel(context.bakeLayer(inner)), new MonsterHumanoidCreeperGirlModel(context.bakeLayer(outter)), new MonsterHumanoidCreeperGirlModel(context.bakeLayer(armor)));
	}

	@Override
	public ResourceLocation getTextureLocation(MonsterHumanoidCreeperGirl p_115812_) {
		return CREEPER_GIRL_LOCATION;
	}
}
