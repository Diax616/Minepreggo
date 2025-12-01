package dev.dixmk.minepreggo.client.renderer.preggo.creeper;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractHumanoidCreeperGirlModel;
import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.IllCreeperGirlP0Model;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.IllCreeperGirl;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class IllCreeperGirlRenderer extends AbstractMonsterCreeperGirlRenderer<IllCreeperGirl, IllCreeperGirlP0Model> {
	
	private static final ResourceLocation ILL_CREEPER_GIRL = ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/ill_creeper_girl.png");

	public IllCreeperGirlRenderer(EntityRendererProvider.Context context) {
		this(context, AbstractHumanoidCreeperGirlModel.LAYER_LOCATION_P1, AbstractHumanoidCreeperGirlModel.LAYER_INNER_ARMOR_LOCATION, AbstractHumanoidCreeperGirlModel.LAYER_OUTER_ARMOR_LOCATION, AbstractHumanoidCreeperGirlModel.LAYER_ENERGY_ARMOR_P0_LOCATION);
	}
	
	public IllCreeperGirlRenderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter, ModelLayerLocation armor) {
		super(context, new IllCreeperGirlP0Model(context.bakeLayer(main)), new IllCreeperGirlP0Model(context.bakeLayer(inner)), new IllCreeperGirlP0Model(context.bakeLayer(outter)), new IllCreeperGirlP0Model(context.bakeLayer(armor)), false);
	}

	@Override
	public ResourceLocation getTextureLocation(IllCreeperGirl p_115812_) {
		return ILL_CREEPER_GIRL;
	}
}
