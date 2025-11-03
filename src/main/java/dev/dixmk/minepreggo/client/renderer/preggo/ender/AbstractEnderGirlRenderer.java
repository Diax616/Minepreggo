package dev.dixmk.minepreggo.client.renderer.preggo.ender;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.client.model.entity.preggo.ender.AbstractEnderGirlModel;
import dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.ender.EnderGirlEyesLayer;
import dev.dixmk.minepreggo.world.entity.preggo.ender.AbstractEnderGirl;
import dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.ender.CarriedBlockLayer;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


@OnlyIn(Dist.CLIENT)
public abstract class AbstractEnderGirlRenderer
	<E extends AbstractEnderGirl, M extends AbstractEnderGirlModel<E>> extends HumanoidMobRenderer<E, M> {
	
	protected static final ResourceLocation ENDER_GIRL_P0_LOCATION = ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/ender/ender_girl_p0.png");
	private final RandomSource random = RandomSource.create();
	
	protected AbstractEnderGirlRenderer(EntityRendererProvider.Context context, M main, RenderType enderEyes) {
		super(context, main, 0.5F);
		this.addLayer(new CarriedBlockLayer<>(this, context.getBlockRenderDispatcher()));
		this.addLayer(new EnderGirlEyesLayer<>(this, enderEyes));
	}
	
	protected AbstractEnderGirlRenderer(EntityRendererProvider.Context context, M main) {
		super(context, main, 0.5F);
		this.addLayer(new CarriedBlockLayer<>(this, context.getBlockRenderDispatcher()));
		this.addLayer(new EnderGirlEyesLayer<>(this));
	}

	@Override
	public Vec3 getRenderOffset(E p_114336_, float p_114337_) {
		if (p_114336_.isCreepy()) {
			return new Vec3(this.random.nextGaussian() * 0.02D, 0.0D, this.random.nextGaussian() * 0.02D);
		} else {
			return super.getRenderOffset(p_114336_, p_114337_);
		}
	}
}