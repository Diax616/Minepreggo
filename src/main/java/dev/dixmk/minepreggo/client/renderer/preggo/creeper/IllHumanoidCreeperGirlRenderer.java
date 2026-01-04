package dev.dixmk.minepreggo.client.renderer.preggo.creeper;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractHumanoidCreeperGirlModel;
import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.IllHumanoidCreeperGirlModel;
import dev.dixmk.minepreggo.utils.MinepreggoHelper;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.IllHumanoidCreeperGirl;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class IllHumanoidCreeperGirlRenderer extends AbstractHumanoidMonsterCreeperGirlRenderer<IllHumanoidCreeperGirl, IllHumanoidCreeperGirlModel> {
	
	private static final ResourceLocation ILL_CREEPER_GIRL = MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/ill_humanoid_creeper_girl.png");

	public IllHumanoidCreeperGirlRenderer(EntityRendererProvider.Context context) {
		this(context, AbstractHumanoidCreeperGirlModel.LAYER_LOCATION_P1, AbstractHumanoidCreeperGirlModel.LAYER_INNER_ARMOR_LOCATION, AbstractHumanoidCreeperGirlModel.LAYER_OUTER_ARMOR_LOCATION, AbstractHumanoidCreeperGirlModel.LAYER_ENERGY_ARMOR_P0_LOCATION);
	}
	
	public IllHumanoidCreeperGirlRenderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter, ModelLayerLocation armor) {
		super(context, new IllHumanoidCreeperGirlModel(context.bakeLayer(main)), new IllHumanoidCreeperGirlModel(context.bakeLayer(inner)), new IllHumanoidCreeperGirlModel(context.bakeLayer(outter)), new IllHumanoidCreeperGirlModel(context.bakeLayer(armor)), false);
	}

	@Override
	public ResourceLocation getTextureLocation(IllHumanoidCreeperGirl p_115812_) {
		return ILL_CREEPER_GIRL;
	}
}
