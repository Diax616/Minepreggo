package dev.dixmk.minepreggo.client.renderer.preggo.creeper;

import javax.annotation.Nullable;

import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractHostileMonsterCreeperGirlModel;
import dev.dixmk.minepreggo.client.renderer.entity.layer.ExpressiveFaceLayer;
import dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.creeper.HostileMonsterCreeperGirlExpressiveFaceLayer;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractHostileCreeperGirl;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractHostileMonsterCreeperGirlRenderer 
	<E extends AbstractHostileCreeperGirl, M extends AbstractHostileMonsterCreeperGirlModel<E>> extends AbstractMonsterCreeperGirlRenderer<E, M> {

	protected AbstractHostileMonsterCreeperGirlRenderer(Context context, M main, M armor) {
		super(context, main, armor);
	}
	
	@Override
	protected @Nullable ExpressiveFaceLayer<E, M> createExpressiveFaceLayer() {
		return new HostileMonsterCreeperGirlExpressiveFaceLayer<>(this);
	}
}