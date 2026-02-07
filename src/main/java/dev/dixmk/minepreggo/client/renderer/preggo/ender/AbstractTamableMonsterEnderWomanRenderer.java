package dev.dixmk.minepreggo.client.renderer.preggo.ender;

import dev.dixmk.minepreggo.client.model.entity.preggo.ender.AbstractTamableMonsterEnderWomanModel;
import dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.ender.MonsterEnderWomanExpressionLayer;
import dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.ender.MonsterEnderWomanExpressiveEyesLayer;
import dev.dixmk.minepreggo.world.entity.preggo.ender.AbstractTamableEnderWoman;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractTamableMonsterEnderWomanRenderer 
	<E extends AbstractTamableEnderWoman, M extends AbstractTamableMonsterEnderWomanModel<E>> extends AbstractEnderWomanRenderer<E, M> {
			
	protected AbstractTamableMonsterEnderWomanRenderer(EntityRendererProvider.Context context, M main, M inner, M outter) {
		super(context, main, inner, outter);
		
		// Sort is important here, expression must be before eyes
		this.addLayer(this.createExpressiveFaceLayer());
		this.addLayer(this.createExpressiveEyesLayer());
	}
	
	protected MonsterEnderWomanExpressionLayer<E, M> createExpressiveFaceLayer() {
		return new MonsterEnderWomanExpressionLayer<>(this);
	}
	
	protected MonsterEnderWomanExpressiveEyesLayer<E, M> createExpressiveEyesLayer() {
		return new MonsterEnderWomanExpressiveEyesLayer<>(this);
	}
}
