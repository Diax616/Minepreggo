package dev.dixmk.minepreggo.client.renderer.preggo.ender;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.client.model.entity.preggo.ender.AbstractEnderGirlModel;
import dev.dixmk.minepreggo.client.model.entity.preggo.ender.IllEnderGirlModel;
import dev.dixmk.minepreggo.world.entity.preggo.ender.IllEnderGirl;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class IllEnderGirlRenderer extends AbstractMonsterEnderGirlRenderer<IllEnderGirl, IllEnderGirlModel> {

	private static final RenderType ILL_ENDER_EYES = RenderType.eyes(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/ender/ill_ender_girl_eyes.png"));
	
	public IllEnderGirlRenderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation outter, ModelLayerLocation inner) {
		super(context, new IllEnderGirlModel(context.bakeLayer(main)), ILL_ENDER_EYES);
		this.addLayer(new HumanoidArmorLayer<>(this, new IllEnderGirlModel(context.bakeLayer(inner)), new IllEnderGirlModel(context.bakeLayer(outter)), context.getModelManager()));
	}
	
	public IllEnderGirlRenderer(EntityRendererProvider.Context context) {
		this(context, AbstractEnderGirlModel.LAYER_LOCATION, AbstractEnderGirlModel.LAYER_INNER_ARMOR_LOCATION, AbstractEnderGirlModel.LAYER_OUTER_ARMOR_LOCATION);
	}

	@Override
	public ResourceLocation getTextureLocation(IllEnderGirl p_115812_) {
		return ENDER_GIRL_LOCATION;
	}
}
