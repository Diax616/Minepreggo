package dev.dixmk.minepreggo.client.renderer.preggo.ender;

import dev.dixmk.minepreggo.client.model.entity.preggo.ender.AbstractEnderWomanModel;
import dev.dixmk.minepreggo.client.model.entity.preggo.ender.MonsterEnderWomanModel;
import dev.dixmk.minepreggo.world.entity.preggo.ender.MonsterEnderWoman;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MonsterlEnderWomanRenderer extends AbstractMonsterEnderWomanRenderer<MonsterEnderWoman, MonsterEnderWomanModel> {

	public MonsterlEnderWomanRenderer(EntityRendererProvider.Context context, ModelLayerLocation main) {
		super(context, new MonsterEnderWomanModel(context.bakeLayer(main)));
	}
	
	public MonsterlEnderWomanRenderer(EntityRendererProvider.Context context) {
		this(context, AbstractEnderWomanModel.LAYER_LOCATION);
	}

	@Override
	public ResourceLocation getTextureLocation(MonsterEnderWoman p_115812_) {
		return ENDER_GIRL_LOCATION;
	}
}
