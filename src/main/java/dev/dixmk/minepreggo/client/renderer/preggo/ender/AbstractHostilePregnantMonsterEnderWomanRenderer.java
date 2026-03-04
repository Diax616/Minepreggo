package dev.dixmk.minepreggo.client.renderer.preggo.ender;

import org.apache.commons.lang3.tuple.ImmutablePair;

import dev.dixmk.minepreggo.client.model.entity.preggo.ender.AbstractHostilePregnantMonsterEnderWomanModel;
import dev.dixmk.minepreggo.client.renderer.entity.layers.ExpressiveEyesLayer;
import dev.dixmk.minepreggo.client.renderer.entity.layers.ExpressiveFaceLayer;
import dev.dixmk.minepreggo.client.renderer.entity.layers.preggo.ender.HostilePregnantMonsterEnderWomanExpressionLayer;
import dev.dixmk.minepreggo.client.renderer.entity.layers.preggo.ender.HostilePregnantMonsterEnderWomanEyesLayer;
import dev.dixmk.minepreggo.world.entity.preggo.ender.AbstractHostilePregnantEnderWoman;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractHostilePregnantMonsterEnderWomanRenderer 
	<E extends AbstractHostilePregnantEnderWoman, M extends AbstractHostilePregnantMonsterEnderWomanModel<E>> extends AbstractHostileMonsterEnderWomanRenderer<E, M> {

	protected AbstractHostilePregnantMonsterEnderWomanRenderer(Context context, M main, M inner, M outter) {
		super(context, main, inner, outter, false);
	}
	
	@Override
	protected ImmutablePair<ExpressiveFaceLayer<E, M>, ExpressiveEyesLayer<E, M>> createExpressionLayers() {
		return ImmutablePair.of(new HostilePregnantMonsterEnderWomanExpressionLayer<>(this), new HostilePregnantMonsterEnderWomanEyesLayer<>(this));
	}
}
