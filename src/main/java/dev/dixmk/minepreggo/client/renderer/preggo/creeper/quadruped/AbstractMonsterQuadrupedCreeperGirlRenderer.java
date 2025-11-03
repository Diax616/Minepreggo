package dev.dixmk.minepreggo.client.renderer.preggo.creeper.quadruped;

import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.quadruped.AbstractMonsterQuadrupedCreeperGirlModel;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractMonsterQuadrupedCreeperGirl;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractMonsterQuadrupedCreeperGirlRenderer 
	<E extends AbstractMonsterQuadrupedCreeperGirl, M extends AbstractMonsterQuadrupedCreeperGirlModel<E>> extends AbstractQuadrupedCreeperGirlRenderer<E, M> {

	protected AbstractMonsterQuadrupedCreeperGirlRenderer(Context context, M main, M armor) {
		super(context, main, armor);
	}
}