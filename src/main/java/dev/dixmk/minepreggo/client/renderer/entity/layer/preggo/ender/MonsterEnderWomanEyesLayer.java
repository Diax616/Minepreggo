package dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.ender;

import dev.dixmk.minepreggo.client.model.entity.preggo.ender.AbstractEnderWomanModel;
import dev.dixmk.minepreggo.world.entity.preggo.ender.AbstractEnderWoman;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MonsterEnderWomanEyesLayer 
	<E extends AbstractEnderWoman, M extends AbstractEnderWomanModel<E>> extends EyesLayer<E, M> {

	private final RenderType enderEyes;
	
	public MonsterEnderWomanEyesLayer(RenderLayerParent<E, M> p_116981_, RenderType enderEyes) {
		super(p_116981_);
		this.enderEyes = enderEyes;
	}
	
	public MonsterEnderWomanEyesLayer(RenderLayerParent<E, M> p_116981_) {
		super(p_116981_);
		this.enderEyes = MonsterEnderWomanClientHelper.DEFAULT_ENDER_EYES;
	}
	
	@Override
	public RenderType renderType() {
		return this.enderEyes;
	}
}
