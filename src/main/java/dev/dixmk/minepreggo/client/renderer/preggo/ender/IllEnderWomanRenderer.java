package dev.dixmk.minepreggo.client.renderer.preggo.ender;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.client.model.entity.preggo.ender.AbstractEnderWomanModel;
import dev.dixmk.minepreggo.client.model.entity.preggo.ender.IllMonsterEnderWomanModel;
import dev.dixmk.minepreggo.utils.MinepreggoHelper;
import dev.dixmk.minepreggo.world.entity.preggo.ender.IllMonsterEnderWoman;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class IllEnderWomanRenderer extends AbstractMonsterEnderWomanRenderer<IllMonsterEnderWoman, IllMonsterEnderWomanModel> {

	private static final RenderType ILL_ENDER_EYES = RenderType.eyes(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/ender/monster/eye/ill_ender_woman_eyes.png"));
	
	public IllEnderWomanRenderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation outter, ModelLayerLocation inner) {
		super(context, new IllMonsterEnderWomanModel(context.bakeLayer(main)), new IllMonsterEnderWomanModel(context.bakeLayer(inner)), new IllMonsterEnderWomanModel(context.bakeLayer(outter)), ILL_ENDER_EYES);
	}
	
	public IllEnderWomanRenderer(EntityRendererProvider.Context context) {
		this(context, AbstractEnderWomanModel.LAYER_LOCATION, AbstractEnderWomanModel.LAYER_INNER_ARMOR_LOCATION, AbstractEnderWomanModel.LAYER_OUTER_ARMOR_LOCATION);
	}

	@Override
	public ResourceLocation getTextureLocation(IllMonsterEnderWoman p_115812_) {
		return MONSTER_ENDER_GIRL_LOCATION;
	}
}
