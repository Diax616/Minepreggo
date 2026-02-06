package dev.dixmk.minepreggo.client.renderer.preggo.creeper;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.quadruped.AbstractCreeperGirlModel;
import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.quadruped.IllMonsterCreeperGirlModel;
import dev.dixmk.minepreggo.utils.MinepreggoHelper;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.IllCreeperGirl;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class IllCreeperGirlRenderer extends AbstractMonsterCreeperGirlRenderer<IllCreeperGirl, IllMonsterCreeperGirlModel> {

	protected static final ResourceLocation ILL_CREEPER_GIRL = MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/monster/ill_creeper_girl.png");
	
	public IllCreeperGirlRenderer(EntityRendererProvider.Context context) {
		this(context, AbstractCreeperGirlModel.LAYER_LOCATION, AbstractCreeperGirlModel.LAYER_ENERGY_ARMOR_LOCATION);
	}
	
	public IllCreeperGirlRenderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation armor) {
		super(context, new IllMonsterCreeperGirlModel(context.bakeLayer(main)), new IllMonsterCreeperGirlModel(context.bakeLayer(armor)));
	}
	
	@Override
	public ResourceLocation getTextureLocation(IllCreeperGirl p_115812_) {
		return ILL_CREEPER_GIRL;
	}
}
