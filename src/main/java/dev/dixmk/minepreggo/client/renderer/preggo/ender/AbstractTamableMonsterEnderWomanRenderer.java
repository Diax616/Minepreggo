package dev.dixmk.minepreggo.client.renderer.preggo.ender;

import dev.dixmk.minepreggo.client.model.entity.preggo.ender.AbstractTamableMonsterEnderWomanModel;
import dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.ender.TamableMonsterEnderWomanExpressionLayer;
import dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.ender.TamableMonsterEnderWomanExpressiveEyesLayer;
import dev.dixmk.minepreggo.world.entity.preggo.ender.AbstractTamableEnderWoman;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractTamableMonsterEnderWomanRenderer 
	<E extends AbstractTamableEnderWoman, M extends AbstractTamableMonsterEnderWomanModel<E>> extends AbstractMonsterEnderWomanRenderer<E, M> {
			
	protected AbstractTamableMonsterEnderWomanRenderer(EntityRendererProvider.Context context, M main, M inner, M outter) {
		super(context, main, inner, outter);
		
		// Sort is important here, expression must be before eyes
		this.addLayer(this.createExpressiveFaceLayer());
		this.addLayer(this.createExpressiveEyesLayer());
	}
	
	protected TamableMonsterEnderWomanExpressionLayer<E, M> createExpressiveFaceLayer() {
		return new TamableMonsterEnderWomanExpressionLayer<>(this);
	}
	
	protected TamableMonsterEnderWomanExpressiveEyesLayer<E, M> createExpressiveEyesLayer() {
		return new TamableMonsterEnderWomanExpressiveEyesLayer<>(this);
	}
}
