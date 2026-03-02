package dev.dixmk.minepreggo.client.renderer.preggo.creeper;

import com.mojang.blaze3d.vertex.PoseStack;

import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractCreeperGirl;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CreeperGirlClientHelper {
	private CreeperGirlClientHelper() {}
	
	public static<E extends AbstractCreeperGirl> void scale(E creeperGirl, PoseStack poseStack, float partialTick) {
		float f = creeperGirl.getSwelling(partialTick);
		float f1 = 1.0F + Mth.sin(f * 100.0F) * f * 0.01F;
		f = Mth.clamp(f, 0.0F, 1.0F);
		f *= f;
		f *= f;
		float f2 = (0.9375F + f * 0.4F) * f1;
		float f3 = (0.9375F + f * 0.1F) / f1;				
		poseStack.scale(f2, f3, f2);
	}
	
	public static<E extends AbstractCreeperGirl> float getWhiteOverlayProgress(E creeperGirl, float partialTick) {
		float f = creeperGirl.getSwelling(partialTick);
		return (int)(f * 10.0F) % 2 == 0 ? 0.0F : Mth.clamp(f, 0.5F, 1.0F);
	}
}
