package dev.dixmk.minepreggo.client.renderer.preggo.ender;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.client.model.entity.preggo.ender.AbstractEnderWomanModel;
import dev.dixmk.minepreggo.utils.MinepreggoHelper;
import dev.dixmk.minepreggo.world.entity.preggo.ender.AbstractEnderWoman;
import dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.ender.CarriedBlockLayer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractEnderWomanRenderer
	<E extends AbstractEnderWoman, M extends AbstractEnderWomanModel<E>> extends HumanoidMobRenderer<E, M> {
		
	protected static final ResourceLocation MONSTER_ENDER_GIRL_LOCATION = MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/ender/monster/ender_woman.png");
	
	protected AbstractEnderWomanRenderer(EntityRendererProvider.Context context, M main, M inner, M outter) {
		super(context, main, 0.5F);
		this.addLayer(new CarriedBlockLayer<>(this, context.getBlockRenderDispatcher()));
		this.addLayer(new HumanoidArmorLayer<>(this, inner, outter, context.getModelManager()));
	}

	@Override
	public Vec3 getRenderOffset(E p_114336_, float p_114337_) {
		if (p_114336_.isCreepy()) {
			return new Vec3(p_114336_.getRandom().nextGaussian() * 0.02D, 0.0D, p_114336_.getRandom().nextGaussian() * 0.02D);
		} else {
			return super.getRenderOffset(p_114336_, p_114337_);
		}
	}
}