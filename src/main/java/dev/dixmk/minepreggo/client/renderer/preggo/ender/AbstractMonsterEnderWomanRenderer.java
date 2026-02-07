package dev.dixmk.minepreggo.client.renderer.preggo.ender;

import dev.dixmk.minepreggo.client.model.entity.preggo.ender.AbstractHostilMonsterEnderWomanModel;
import dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.ender.MonsterEnderWomanEyesLayer;
import dev.dixmk.minepreggo.world.entity.preggo.ender.AbstractHostilMonsterEnderWoman;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractMonsterEnderWomanRenderer
	<E extends AbstractHostilMonsterEnderWoman, M extends AbstractHostilMonsterEnderWomanModel<E>> extends AbstractEnderWomanRenderer<E, M> {
	
	protected AbstractMonsterEnderWomanRenderer(Context context, M main, M inner, M outter, RenderType eyesRenderType) {
		super(context, main, inner, outter);
		this.addLayer(new MonsterEnderWomanEyesLayer<>(this, eyesRenderType));
	}
	
	protected AbstractMonsterEnderWomanRenderer(Context context, M main, M inner, M outter) {
		super(context, main, inner, outter);
		this.addLayer(new MonsterEnderWomanEyesLayer<>(this));
	}
}
