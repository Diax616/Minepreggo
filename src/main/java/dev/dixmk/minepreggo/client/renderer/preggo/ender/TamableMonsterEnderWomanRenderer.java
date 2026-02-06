package dev.dixmk.minepreggo.client.renderer.preggo.ender;

import dev.dixmk.minepreggo.client.model.entity.preggo.ender.AbstractEnderWomanModel;
import dev.dixmk.minepreggo.client.model.entity.preggo.ender.TamableMonsterEnderWomanModel;
import dev.dixmk.minepreggo.world.entity.preggo.ender.TamableMonsterEnderWoman;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TamableMonsterEnderWomanRenderer extends AbstractTamableMonsterEnderWomanRenderer<TamableMonsterEnderWoman, TamableMonsterEnderWomanModel> {
	
	public TamableMonsterEnderWomanRenderer(EntityRendererProvider.Context context) {
		this(context, AbstractEnderWomanModel.LAYER_LOCATION, AbstractEnderWomanModel.LAYER_INNER_ARMOR_LOCATION, AbstractEnderWomanModel.LAYER_OUTER_ARMOR_LOCATION);
	}
	
	public TamableMonsterEnderWomanRenderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter) {
		super(context, new TamableMonsterEnderWomanModel(context.bakeLayer(main)), new TamableMonsterEnderWomanModel(context.bakeLayer(inner)), new TamableMonsterEnderWomanModel(context.bakeLayer(outter)));
	}
	
	@Override
	public ResourceLocation getTextureLocation(TamableMonsterEnderWoman p_115812_) {
		return MONSTER_ENDER_GIRL_LOCATION;
	}	
}
