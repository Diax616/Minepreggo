package dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.ender;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.client.model.entity.preggo.ender.AbstractEnderGirlModel;
import dev.dixmk.minepreggo.world.entity.preggo.ender.AbstractEnderGirl;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class EnderGirlEyesLayer 
	<E extends AbstractEnderGirl, M extends AbstractEnderGirlModel<E>> extends EyesLayer<E, M> {

	private static final RenderType ENDER_EYES = RenderType.eyes(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/ender/ender_girl_eyes.png"));
	private final RenderType enderEyes;
	
	public EnderGirlEyesLayer(RenderLayerParent<E, M> p_116981_, RenderType enderEyes) {
		super(p_116981_);
		this.enderEyes = enderEyes;
	}
	
	public EnderGirlEyesLayer(RenderLayerParent<E, M> p_116981_) {
		super(p_116981_);
		this.enderEyes = ENDER_EYES;
	}
	
	@Override
	public RenderType renderType() {
		return this.enderEyes;
	}
}
