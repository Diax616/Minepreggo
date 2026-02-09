package dev.dixmk.minepreggo.client.renderer.preggo.creeper;

import javax.annotation.Nullable;

import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractHostilPregnantMonsterCreeperGirlModel;
import dev.dixmk.minepreggo.client.renderer.entity.layer.ExpressiveFaceLayer;
import dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.creeper.HostilPregnantMonsterCreeperGirlExpressiveFaceLayer;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractHostilPregnantCreeperGirl;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractHostilPregnantMonsterCreeperGirlRenderer 
	<E extends AbstractHostilPregnantCreeperGirl, M extends AbstractHostilPregnantMonsterCreeperGirlModel<E>> extends AbstractHostilMonsterCreeperGirlRenderer<E, M> {

	protected AbstractHostilPregnantMonsterCreeperGirlRenderer(Context context, M main, M armor) {
		super(context, main, armor);
	}
	
	@Override
	protected @Nullable ExpressiveFaceLayer<E, M> createExpressiveFaceLayer() {
		return new HostilPregnantMonsterCreeperGirlExpressiveFaceLayer<>(this);
	}
}
