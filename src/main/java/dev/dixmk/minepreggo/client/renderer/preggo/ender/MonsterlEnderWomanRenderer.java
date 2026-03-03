package dev.dixmk.minepreggo.client.renderer.preggo.ender;

import org.apache.commons.lang3.tuple.ImmutablePair;

import dev.dixmk.minepreggo.client.model.entity.preggo.ender.MonsterEnderWomanModel;
import dev.dixmk.minepreggo.client.model.geom.MinepreggoModelLayers;
import dev.dixmk.minepreggo.client.renderer.entity.layer.ExpressiveEyesLayer;
import dev.dixmk.minepreggo.client.renderer.entity.layer.ExpressiveFaceLayer;
import dev.dixmk.minepreggo.world.entity.preggo.ender.HostileMonsterEnderWoman;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MonsterlEnderWomanRenderer extends AbstractHostileMonsterEnderWomanRenderer<HostileMonsterEnderWoman, MonsterEnderWomanModel> {

	public MonsterlEnderWomanRenderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation inner, ModelLayerLocation outter) {
		super(context, new MonsterEnderWomanModel(context.bakeLayer(main)), new MonsterEnderWomanModel(context.bakeLayer(inner)), new MonsterEnderWomanModel(context.bakeLayer(outter)));
	}
	
	public MonsterlEnderWomanRenderer(EntityRendererProvider.Context context) {
		this(context, MinepreggoModelLayers.MONSTER_ENDER_WOMAN, MinepreggoModelLayers.MONSTER_ENDER_WOMAN_INNER_ARMOR, MinepreggoModelLayers.MONSTER_ENDER_WOMAN_OUTER_ARMOR);
	}

	@Override
	public ResourceLocation getTextureLocation(HostileMonsterEnderWoman entity) {
		return MONSTER_ENDER_GIRL_LOCATION;
	}

	@Override
	protected ImmutablePair<ExpressiveFaceLayer<HostileMonsterEnderWoman, MonsterEnderWomanModel>, ExpressiveEyesLayer<HostileMonsterEnderWoman, MonsterEnderWomanModel>> createExpressionLayers() {
		return null;
	}
}
