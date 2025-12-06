package dev.dixmk.minepreggo.client.renderer.preggo.ender;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.client.model.entity.preggo.ender.AbstractEnderWomanModel;
import dev.dixmk.minepreggo.client.model.entity.preggo.ender.IllEnderWomanModel;
import dev.dixmk.minepreggo.world.entity.preggo.ender.IllEnderWoman;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class IllEnderWomanRenderer extends AbstractMonsterEnderWomanRenderer<IllEnderWoman, IllEnderWomanModel> {

	private static final RenderType ILL_ENDER_EYES = RenderType.eyes(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/ender/ill_ender_woman_eyes.png"));
	
	public IllEnderWomanRenderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation outter, ModelLayerLocation inner) {
		super(context, new IllEnderWomanModel(context.bakeLayer(main)), ILL_ENDER_EYES);
		this.addLayer(new HumanoidArmorLayer<>(this, new IllEnderWomanModel(context.bakeLayer(inner)), new IllEnderWomanModel(context.bakeLayer(outter)), context.getModelManager()));
	}
	
	public IllEnderWomanRenderer(EntityRendererProvider.Context context) {
		this(context, AbstractEnderWomanModel.LAYER_LOCATION, AbstractEnderWomanModel.LAYER_INNER_ARMOR_LOCATION, AbstractEnderWomanModel.LAYER_OUTER_ARMOR_LOCATION);
	}

	@Override
	public ResourceLocation getTextureLocation(IllEnderWoman p_115812_) {
		return ENDER_GIRL_LOCATION;
	}
}
