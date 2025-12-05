package dev.dixmk.minepreggo.client.renderer.preggo.ender;

import dev.dixmk.minepreggo.client.model.entity.preggo.ender.AbstractEnderGirlModel;
import dev.dixmk.minepreggo.client.model.entity.preggo.ender.MonsterEnderGirlModel;
import dev.dixmk.minepreggo.world.entity.preggo.ender.MonsterEnderGirl;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MonsterlEnderGirlRenderer extends AbstractMonsterEnderGirlRenderer<MonsterEnderGirl, MonsterEnderGirlModel> {

	public MonsterlEnderGirlRenderer(EntityRendererProvider.Context context, ModelLayerLocation main) {
		super(context, new MonsterEnderGirlModel(context.bakeLayer(main)));
	}
	
	public MonsterlEnderGirlRenderer(EntityRendererProvider.Context context) {
		this(context, AbstractEnderGirlModel.LAYER_LOCATION);
	}

	@Override
	public ResourceLocation getTextureLocation(MonsterEnderGirl p_115812_) {
		return ENDER_GIRL_LOCATION;
	}
}
