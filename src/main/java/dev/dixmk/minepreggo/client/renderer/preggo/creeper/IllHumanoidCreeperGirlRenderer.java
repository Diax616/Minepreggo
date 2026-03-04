package dev.dixmk.minepreggo.client.renderer.preggo.creeper;

import javax.annotation.Nullable;

import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.IllHumanoidCreeperGirlModel;
import dev.dixmk.minepreggo.client.model.geom.MinepreggoModelLayers;
import dev.dixmk.minepreggo.client.renderer.entity.layers.ExpressiveFaceLayer;
import dev.dixmk.minepreggo.utils.MinepreggoHelper;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.IllHumanoidCreeperGirl;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class IllHumanoidCreeperGirlRenderer extends AbstractHostileHumanoidCreeperGirlRenderer<IllHumanoidCreeperGirl, IllHumanoidCreeperGirlModel> {
	
	private static final ResourceLocation ILL_HUMANOID_CREEPER_GIRL = MinepreggoHelper.fromThisNamespaceAndPath("textures/entity/preggo/creeper/humanoid/ill_humanoid_creeper_girl.png");

	public IllHumanoidCreeperGirlRenderer(EntityRendererProvider.Context context) {
		this(context, MinepreggoModelLayers.HUMANOID_CREEPER_GIRL, MinepreggoModelLayers.HUMANOID_CREEPER_GIRL_INNER_ARMOR, MinepreggoModelLayers.HUMANOID_CREEPER_GIRL_OUTER_ARMOR, MinepreggoModelLayers.HUMANOID_CREEPER_GIRL_ENERGY_ARMOR);
	}
	
	public IllHumanoidCreeperGirlRenderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter, ModelLayerLocation armor) {
		super(context, new IllHumanoidCreeperGirlModel(context.bakeLayer(main)), new IllHumanoidCreeperGirlModel(context.bakeLayer(inner)), new IllHumanoidCreeperGirlModel(context.bakeLayer(outter)), new IllHumanoidCreeperGirlModel(context.bakeLayer(armor)));
	}

	@Override
	public ResourceLocation getTextureLocation(IllHumanoidCreeperGirl entity) {
		return ILL_HUMANOID_CREEPER_GIRL;
	}
	
	@Override
	protected @Nullable ExpressiveFaceLayer<IllHumanoidCreeperGirl, IllHumanoidCreeperGirlModel> createExpressiveFaceLayer() {
		return null;
	}
}
