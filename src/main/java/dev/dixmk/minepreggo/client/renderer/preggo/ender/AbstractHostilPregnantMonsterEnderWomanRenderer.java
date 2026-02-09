package dev.dixmk.minepreggo.client.renderer.preggo.ender;

import org.apache.commons.lang3.tuple.ImmutablePair;

import dev.dixmk.minepreggo.client.model.entity.preggo.ender.AbstractHostilPregnantMonsterEnderWomanModel;
import dev.dixmk.minepreggo.client.renderer.entity.layer.ExpressiveEyesLayer;
import dev.dixmk.minepreggo.client.renderer.entity.layer.ExpressiveFaceLayer;
import dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.ender.HostilPregnantMonsterEnderWomanExpressionLayer;
import dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.ender.HostilPregnantMonsterEnderWomanEyesLayer;
import dev.dixmk.minepreggo.world.entity.preggo.ender.AbstractHostilPregnantEnderWoman;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractHostilPregnantMonsterEnderWomanRenderer 
	<E extends AbstractHostilPregnantEnderWoman, M extends AbstractHostilPregnantMonsterEnderWomanModel<E>> extends AbstractHostilMonsterEnderWomanRenderer<E, M> {

	protected AbstractHostilPregnantMonsterEnderWomanRenderer(Context context, M main, M inner, M outter) {
		super(context, main, inner, outter, false);
	}
	
	@Override
	protected ImmutablePair<ExpressiveFaceLayer<E, M>, ExpressiveEyesLayer<E, M>> createExpressionLayers() {
		return ImmutablePair.of(new HostilPregnantMonsterEnderWomanExpressionLayer<>(this), new HostilPregnantMonsterEnderWomanEyesLayer<>(this));
	}
}
