package dev.dixmk.minepreggo.client.renderer.preggo.creeper;

import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.quadruped.AbstractMonsterCreeperGirlModel;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractMonsterQuadrupedCreeperGirl;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractMonsterCreeperGirlRenderer 
	<E extends AbstractMonsterQuadrupedCreeperGirl, M extends AbstractMonsterCreeperGirlModel<E>> extends AbstractCreeperGirlRenderer<E, M> {

	protected AbstractMonsterCreeperGirlRenderer(Context context, M main, M armor) {
		super(context, main, armor);
	}
}