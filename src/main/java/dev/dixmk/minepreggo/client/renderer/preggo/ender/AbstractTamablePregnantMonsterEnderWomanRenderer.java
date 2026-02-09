package dev.dixmk.minepreggo.client.renderer.preggo.ender;

import javax.annotation.Nonnull;

import dev.dixmk.minepreggo.client.model.entity.preggo.ender.AbstractTamablePregnantMonsterEnderWomanModel;
import dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.ender.TamableMonsterEnderWomanExpressionLayer;
import dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.ender.TamableMonsterEnderWomanExpressiveEyesLayer;
import dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.ender.TamableMonsterPregnantEnderWomanExpressionLayer;
import dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.ender.TamableMonsterPregnantEnderWomanExpressiveEyesLayer;
import dev.dixmk.minepreggo.world.entity.preggo.ender.AbstractTamablePregnantEnderWoman;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractTamablePregnantMonsterEnderWomanRenderer 
	<E extends AbstractTamablePregnantEnderWoman, M extends AbstractTamablePregnantMonsterEnderWomanModel<E>> extends AbstractTamableMonsterEnderWomanRenderer<E, M> {

	protected AbstractTamablePregnantMonsterEnderWomanRenderer(Context context, M main, M inner, M outter) {
		super(context, main, inner, outter);	
	}

	@Override
	protected @Nonnull TamableMonsterEnderWomanExpressionLayer<E, M> createExpressiveFaceLayer() {
		return new TamableMonsterPregnantEnderWomanExpressionLayer<>(this);
	}
	
	@Override
	protected @Nonnull TamableMonsterEnderWomanExpressiveEyesLayer<E, M> createExpressiveEyesLayer() {
		return new TamableMonsterPregnantEnderWomanExpressiveEyesLayer<>(this);
	}
}
