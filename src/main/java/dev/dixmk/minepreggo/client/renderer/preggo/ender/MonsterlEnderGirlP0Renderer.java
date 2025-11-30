package dev.dixmk.minepreggo.client.renderer.preggo.ender;

import dev.dixmk.minepreggo.client.model.entity.preggo.ender.AbstractEnderGirlModel;
import dev.dixmk.minepreggo.client.model.entity.preggo.ender.MonsterEnderGirlModel;
import dev.dixmk.minepreggo.world.entity.preggo.ender.MonsterEnderGirlP0;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MonsterlEnderGirlP0Renderer extends AbstractMonsterEnderGirlRenderer<MonsterEnderGirlP0, MonsterEnderGirlModel> {

	public MonsterlEnderGirlP0Renderer(EntityRendererProvider.Context context, ModelLayerLocation main) {
		super(context, new MonsterEnderGirlModel(context.bakeLayer(main)));
	}
	
	public MonsterlEnderGirlP0Renderer(EntityRendererProvider.Context context) {
		this(context, AbstractEnderGirlModel.LAYER_LOCATION);
	}

	@Override
	public ResourceLocation getTextureLocation(MonsterEnderGirlP0 p_115812_) {
		return ENDER_GIRL_LOCATION;
	}
}
