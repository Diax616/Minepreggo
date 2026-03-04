package dev.dixmk.minepreggo.client.renderer.preggo.creeper;

import javax.annotation.Nullable;

import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.IllMonsterCreeperGirlModel;
import dev.dixmk.minepreggo.client.model.geom.MinepreggoModelLayers;
import dev.dixmk.minepreggo.client.renderer.entity.layers.ExpressiveFaceLayer;
import dev.dixmk.minepreggo.utils.MinepreggoHelper;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.IllMonsterCreeperGirl;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class IllMonsterCreeperGirlRenderer extends AbstractHostileMonsterCreeperGirlRenderer<IllMonsterCreeperGirl, IllMonsterCreeperGirlModel> {

	protected static final ResourceLocation ILL_CREEPER_GIRL = MinepreggoHelper.fromThisNamespaceAndPath("textures/entity/preggo/creeper/monster/ill_creeper_girl.png");
	
	public IllMonsterCreeperGirlRenderer(EntityRendererProvider.Context context) {
		this(context, MinepreggoModelLayers.MONSTER_CREEPER_GIRL, MinepreggoModelLayers.MONSTER_CREEPER_GIRL_ENERGY_ARMOR);
	}
	
	public IllMonsterCreeperGirlRenderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation armor) {
		super(context, new IllMonsterCreeperGirlModel(context.bakeLayer(main)), new IllMonsterCreeperGirlModel(context.bakeLayer(armor)));
	}
	
	@Override
	public ResourceLocation getTextureLocation(IllMonsterCreeperGirl entity) {
		return ILL_CREEPER_GIRL;
	}
	
	@Override
	protected @Nullable ExpressiveFaceLayer<IllMonsterCreeperGirl, IllMonsterCreeperGirlModel> createExpressiveFaceLayer() {
		return null;
	}
}
