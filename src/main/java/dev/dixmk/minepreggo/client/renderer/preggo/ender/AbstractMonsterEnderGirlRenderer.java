package dev.dixmk.minepreggo.client.renderer.preggo.ender;

import dev.dixmk.minepreggo.client.model.entity.preggo.ender.AbstractMonsterEnderGirlModel;
import dev.dixmk.minepreggo.world.entity.preggo.ender.AbstractMonsterEnderGirl;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractMonsterEnderGirlRenderer
	<E extends AbstractMonsterEnderGirl, M extends AbstractMonsterEnderGirlModel<E>> extends AbstractEnderGirlRenderer<E, M> {
	
	protected AbstractMonsterEnderGirlRenderer(Context context, M main) {
		super(context, main);
	}

	protected AbstractMonsterEnderGirlRenderer(Context context, M main, RenderType enderEyes) {
		super(context, main, enderEyes);
	}
}
