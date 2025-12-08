package dev.dixmk.minepreggo.client.renderer.preggo.creeper;

import com.mojang.blaze3d.vertex.PoseStack;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.quadruped.AbstractCreeperGirlModel;
import dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.creeper.CreeperGirlPowerLayer;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractCreeperGirl;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraft.client.renderer.entity.MobRenderer;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractCreeperGirlRenderer
	<E extends AbstractCreeperGirl, M extends AbstractCreeperGirlModel<E>> extends MobRenderer<E, M>{

	protected static final ResourceLocation CREEPER_GIRL_LOCATION = ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/creeper_girl.png");

	protected AbstractCreeperGirlRenderer(EntityRendererProvider.Context context, M main, M armor) {
		super(context, main, 0.5F);
		this.addLayer(new CreeperGirlPowerLayer<>(this, context.getModelSet(), armor));
	}

	@Override
	protected void scale(E creeperGirl, PoseStack p_114047_, float p_114048_) {
		float f = creeperGirl.getSwelling(p_114048_);
		float f1 = 1.0F + Mth.sin(f * 100.0F) * f * 0.01F;
		f = Mth.clamp(f, 0.0F, 1.0F);
		f *= f;
		f *= f;
		float f2 = (1.0F + f * 0.4F) * f1;
		float f3 = (1.0F + f * 0.1F) / f1;
		p_114047_.scale(f2, f3, f2);
	}
	
	@Override
	protected float getWhiteOverlayProgress(E creeperGirl, float p_114044_) {
		float f = creeperGirl.getSwelling(p_114044_);
		return (int)(f * 10.0F) % 2 == 0 ? 0.0F : Mth.clamp(f, 0.5F, 1.0F);
	}
}
