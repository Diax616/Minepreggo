package dev.dixmk.minepreggo.client.renderer.preggo.ender;

import dev.dixmk.minepreggo.client.model.entity.preggo.ender.AbstractMonsterEnderWomanModel;
import dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.ender.MonsterEnderWomanEyesLayer;
import dev.dixmk.minepreggo.world.entity.preggo.ender.AbstractMonsterEnderWoman;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractMonsterEnderWomanRenderer
	<E extends AbstractMonsterEnderWoman, M extends AbstractMonsterEnderWomanModel<E>> extends AbstractEnderWomanRenderer<E, M> {
	
	protected AbstractMonsterEnderWomanRenderer(Context context, M main, M inner, M outter, RenderType eyesRenderType) {
		super(context, main, inner, outter);
		this.addLayer(new MonsterEnderWomanEyesLayer<>(this, eyesRenderType));
	}
	
	protected AbstractMonsterEnderWomanRenderer(Context context, M main, M inner, M outter) {
		super(context, main, inner, outter);
		this.addLayer(new MonsterEnderWomanEyesLayer<>(this));
	}
}
