package dev.dixmk.minepreggo.client.renderer.preggo.creeper.quadruped;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.quadruped.AbstractQueadrupedCreeperGirlModel;
import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.quadruped.IllQuadrupedCreeperGirlModel;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.IllQuadrupedCreeperGirl;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class IllQuadrupedCreeperGirlRenderer extends AbstractQuadrupedCreeperGirlRenderer<IllQuadrupedCreeperGirl, IllQuadrupedCreeperGirlModel> {

	protected static final ResourceLocation ILL_QUADRUPED_CREEPER_GIRL = ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/ill_quadruped_creeper_girl_p0.png");
	
	public IllQuadrupedCreeperGirlRenderer(EntityRendererProvider.Context context) {
		this(context, AbstractQueadrupedCreeperGirlModel.LAYER_LOCATION_P0, AbstractQueadrupedCreeperGirlModel.LAYER_ENERGY_ARMOR_P0_LOCATION);
	}
	
	public IllQuadrupedCreeperGirlRenderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation armor) {
		super(context, new IllQuadrupedCreeperGirlModel(context.bakeLayer(main)), new IllQuadrupedCreeperGirlModel(context.bakeLayer(armor)));
	}
	
	@Override
	public ResourceLocation getTextureLocation(IllQuadrupedCreeperGirl p_115812_) {
		return ILL_QUADRUPED_CREEPER_GIRL;
	}
}
