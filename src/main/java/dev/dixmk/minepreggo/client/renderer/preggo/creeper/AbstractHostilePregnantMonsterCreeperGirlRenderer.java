package dev.dixmk.minepreggo.client.renderer.preggo.creeper;

import javax.annotation.Nullable;

import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractHostilePregnantMonsterCreeperGirlModel;
import dev.dixmk.minepreggo.client.renderer.entity.layer.ExpressiveFaceLayer;
import dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.creeper.HostilePregnantMonsterCreeperGirlExpressiveFaceLayer;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractHostilePregnantCreeperGirl;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractHostilePregnantMonsterCreeperGirlRenderer 
	<E extends AbstractHostilePregnantCreeperGirl, M extends AbstractHostilePregnantMonsterCreeperGirlModel<E>> extends AbstractHostileMonsterCreeperGirlRenderer<E, M> {

	protected AbstractHostilePregnantMonsterCreeperGirlRenderer(Context context, M main, M armor) {
		super(context, main, armor);
	}
	
	@Override
	protected @Nullable ExpressiveFaceLayer<E, M> createExpressiveFaceLayer() {
		return new HostilePregnantMonsterCreeperGirlExpressiveFaceLayer<>(this);
	}
}
