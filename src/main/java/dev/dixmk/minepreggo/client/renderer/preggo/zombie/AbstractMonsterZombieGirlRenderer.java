package dev.dixmk.minepreggo.client.renderer.preggo.zombie;

import dev.dixmk.minepreggo.client.model.entity.preggo.zombie.AbstractMonsterZombieGirlModel;
import dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.zombie.MonsterZombieGirlExpressionLayer;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.AbstractMonsterZombieGirl;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


@OnlyIn(Dist.CLIENT)
public abstract class AbstractMonsterZombieGirlRenderer
	<E extends AbstractMonsterZombieGirl, M extends AbstractMonsterZombieGirlModel<E>> extends AbstractZombieGirlRenderer<E, M> {

	protected AbstractMonsterZombieGirlRenderer(Context context, M main, M inner, M outter, boolean facialExpression) {
		super(context, main, inner, outter);
		if (facialExpression) this.addFacialExpresions();
	}
	
	protected AbstractMonsterZombieGirlRenderer(Context context, M main, M inner, M outter) {
		this(context, main, inner, outter, true);
	}
	
	protected void addFacialExpresions() {
		this.addLayer(new MonsterZombieGirlExpressionLayer<>(this));
	}
}