package dev.dixmk.minepreggo.client.renderer.preggo.creeper;

import javax.annotation.Nullable;

import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractHostilMonsterCreeperGirlModel;
import dev.dixmk.minepreggo.client.renderer.entity.layer.ExpressiveFaceLayer;
import dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.creeper.HostilMonsterCreeperGirlExpressiveFaceLayer;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractHostilCreeperGirl;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractHostilMonsterCreeperGirlRenderer 
	<E extends AbstractHostilCreeperGirl, M extends AbstractHostilMonsterCreeperGirlModel<E>> extends AbstractMonsterCreeperGirlRenderer<E, M> {

	protected AbstractHostilMonsterCreeperGirlRenderer(Context context, M main, M armor) {
		super(context, main, armor);
	}
	
	@Override
	protected @Nullable ExpressiveFaceLayer<E, M> createExpressiveFaceLayer() {
		return new HostilMonsterCreeperGirlExpressiveFaceLayer<>(this);
	}
}