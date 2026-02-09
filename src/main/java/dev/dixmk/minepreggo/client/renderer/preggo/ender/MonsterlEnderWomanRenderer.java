package dev.dixmk.minepreggo.client.renderer.preggo.ender;

import org.apache.commons.lang3.tuple.ImmutablePair;

import dev.dixmk.minepreggo.client.model.entity.preggo.ender.AbstractMonsterEnderWomanModel;
import dev.dixmk.minepreggo.client.model.entity.preggo.ender.MonsterEnderWomanModel;
import dev.dixmk.minepreggo.client.renderer.entity.layer.ExpressiveEyesLayer;
import dev.dixmk.minepreggo.client.renderer.entity.layer.ExpressiveFaceLayer;
import dev.dixmk.minepreggo.world.entity.preggo.ender.HostilMonsterEnderWoman;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MonsterlEnderWomanRenderer extends AbstractHostilMonsterEnderWomanRenderer<HostilMonsterEnderWoman, MonsterEnderWomanModel> {

	public MonsterlEnderWomanRenderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter) {
		super(context, new MonsterEnderWomanModel(context.bakeLayer(main)), new MonsterEnderWomanModel(context.bakeLayer(inner)), new MonsterEnderWomanModel(context.bakeLayer(outter)));
	}
	
	public MonsterlEnderWomanRenderer(EntityRendererProvider.Context context) {
		this(context, AbstractMonsterEnderWomanModel.LAYER_LOCATION, AbstractMonsterEnderWomanModel.LAYER_INNER_ARMOR_LOCATION, AbstractMonsterEnderWomanModel.LAYER_OUTER_ARMOR_LOCATION);
	}

	@Override
	public ResourceLocation getTextureLocation(HostilMonsterEnderWoman p_115812_) {
		return MONSTER_ENDER_GIRL_LOCATION;
	}

	@Override
	protected ImmutablePair<ExpressiveFaceLayer<HostilMonsterEnderWoman, MonsterEnderWomanModel>, ExpressiveEyesLayer<HostilMonsterEnderWoman, MonsterEnderWomanModel>> createExpressionLayers() {
		return null;
	}
}
