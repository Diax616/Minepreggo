package dev.dixmk.minepreggo.event;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.client.animation.player.ArmAnimationManager;
import dev.dixmk.minepreggo.client.screens.effect.SexOverlayManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiEvent;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MinepreggoMod.MODID, value = Dist.CLIENT)
public class RenderEventHandler {

	private RenderEventHandler() {}
	
    @SubscribeEvent
    public static void onRenderGui(RenderGuiEvent.Post event) {
        if (SexOverlayManager.getInstance().isActive()) {
            final float alpha = SexOverlayManager.getInstance().getAlpha();    
            
            if (alpha <= 0.0F) return;

            GuiGraphics guiGraphics = event.getGuiGraphics();
            int width = event.getWindow().getGuiScaledWidth();
            int height = event.getWindow().getGuiScaledHeight();

            // Pack alpha into ARGB color (black with alpha)
            int color = ((int)(alpha * 255) << 24) | 0x000000;

            // Draw solid black overlay
            guiGraphics.fill(0, 0, width, height, color);
        }
    }
    
    @SubscribeEvent
    public static void onRenderHand(RenderHandEvent event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return;
        
        AbstractClientPlayer player = mc.player;
             
        // Check if player has active animation
        if (ArmAnimationManager.getInstance().isAnimating(player.getUUID())) {
           
            MinepreggoMod.LOGGER.debug("Applying arm animation for hand: {}", event.getHand());
            
            // DON'T use pushPose/popPose - the transformations have to be persist
            if (event.getHand() == InteractionHand.MAIN_HAND) {
            	ArmAnimationManager.getInstance().applyAnimation(
                    player.getUUID(),
                    player.getMainArm(),
                    event.getPoseStack(),
                    event.getPartialTick()
                );
            } 
        }
    }
}
