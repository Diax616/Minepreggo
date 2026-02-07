package dev.dixmk.minepreggo.client.renderer.preggo.creeper;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractMonsterCreeperGirlModel;
import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.IllMonsterCreeperGirlModel;
import dev.dixmk.minepreggo.utils.MinepreggoHelper;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.IllMonsterCreeperGirl;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class IllMonsterCreeperGirlRenderer extends AbstractHostilMonsterCreeperGirlRenderer<IllMonsterCreeperGirl, IllMonsterCreeperGirlModel> {

	protected static final ResourceLocation ILL_CREEPER_GIRL = MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/monster/ill_creeper_girl.png");
	
	public IllMonsterCreeperGirlRenderer(EntityRendererProvider.Context context) {
		this(context, AbstractMonsterCreeperGirlModel.LAYER_LOCATION, AbstractMonsterCreeperGirlModel.LAYER_ENERGY_ARMOR_LOCATION);
	}
	
	public IllMonsterCreeperGirlRenderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation armor) {
		super(context, new IllMonsterCreeperGirlModel(context.bakeLayer(main)), new IllMonsterCreeperGirlModel(context.bakeLayer(armor)));
	}
	
	@Override
	public ResourceLocation getTextureLocation(IllMonsterCreeperGirl p_115812_) {
		return ILL_CREEPER_GIRL;
	}
}
