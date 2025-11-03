package dev.dixmk.minepreggo.client.renderer.preggo.zombie;

import dev.dixmk.minepreggo.client.model.entity.preggo.zombie.AbstractTamablePregnantZombieGirlModel;
import dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.zombie.TamablePregnantZombieGirlExpressionLayer;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.AbstractTamablePregnantZombieGirl;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractTamablePregnantZombieGirlRenderer
	<E extends AbstractTamablePregnantZombieGirl<?,?>, M extends AbstractTamablePregnantZombieGirlModel<E>> extends AbstractTamableZombieGirlRenderer<E, M> {

	protected AbstractTamablePregnantZombieGirlRenderer(Context context, M main, M inner, M outter) {
		super(context, main, inner, outter);
	}
	
	@Override
	protected void addFacialExpresions() {
		this.addLayer(new TamablePregnantZombieGirlExpressionLayer<>(this));
	}
}