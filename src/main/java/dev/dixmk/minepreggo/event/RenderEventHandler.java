package dev.dixmk.minepreggo.event;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.client.screen.effect.SexOverlayManager;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MinepreggoMod.MODID, value = Dist.CLIENT)
public class RenderEventHandler {

	private RenderEventHandler() {}
	
    @SubscribeEvent
    public static void onRenderGui(RenderGuiEvent.Post event) {
        if (SexOverlayManager.isActive()) {
            final float alpha = SexOverlayManager.getAlpha();    
            
            if (alpha <= 0.0F) return;

            GuiGraphics guiGraphics = event.getGuiGraphics();
            int width = event.getWindow().getGuiScaledWidth();
            int height = event.getWindow().getGuiScaledHeight();

            // Pack alpha into ARGB color (black with alpha)
            int color = ((int)(alpha * 255) << 24) | 0x000000;

            // Draw solid black overlay — exactly like vanilla sleep
            guiGraphics.fill(0, 0, width, height, color);
        }
    }
}
