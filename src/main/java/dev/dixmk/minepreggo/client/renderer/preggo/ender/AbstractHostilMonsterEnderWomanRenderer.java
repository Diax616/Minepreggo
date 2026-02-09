package dev.dixmk.minepreggo.client.renderer.preggo.ender;

import javax.annotation.Nullable;

import org.apache.commons.lang3.tuple.ImmutablePair;

import dev.dixmk.minepreggo.client.model.entity.preggo.ender.AbstractHostilMonsterEnderWomanModel;
import dev.dixmk.minepreggo.client.renderer.entity.layer.ExpressiveFaceLayer;
import dev.dixmk.minepreggo.client.renderer.entity.layer.ExpressiveEyesLayer;
import dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.ender.MonsterEnderWomanEyesLayer;
import dev.dixmk.minepreggo.world.entity.preggo.ender.AbstractHostilEnderWoman;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractHostilMonsterEnderWomanRenderer
	<E extends AbstractHostilEnderWoman, M extends AbstractHostilMonsterEnderWomanModel<E>> extends AbstractMonsterEnderWomanRenderer<E, M> {
	
	protected AbstractHostilMonsterEnderWomanRenderer(Context context, M main, M inner, M outter, RenderType eyesRenderType) {
		super(context, main, inner, outter);
		this.addLayer(new MonsterEnderWomanEyesLayer<>(this, eyesRenderType));
	}
	
	protected AbstractHostilMonsterEnderWomanRenderer(Context context, M main, M inner, M outter) {
		this(context, main, inner, outter, true);
	}
	
	protected AbstractHostilMonsterEnderWomanRenderer(Context context, M main, M inner, M outter, boolean useDefaultEyesLayer) {
		super(context, main, inner, outter);	
		if (useDefaultEyesLayer) {
			this.addLayer(new MonsterEnderWomanEyesLayer<>(this));
		}
		else {
			var expressionLayers = this.createExpressionLayers();
			if (expressionLayers == null) {
				throw new IllegalStateException("default expression layers was disabled but createExpressionLayers returned null");
			}
			this.addLayer(expressionLayers.left);
			this.addLayer(expressionLayers.right);
		}
	}

	protected abstract @Nullable ImmutablePair<ExpressiveFaceLayer<E, M>, ExpressiveEyesLayer<E, M>> createExpressionLayers();
}
