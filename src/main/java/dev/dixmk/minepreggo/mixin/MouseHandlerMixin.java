package dev.dixmk.minepreggo.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.MouseHandler;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import dev.dixmk.minepreggo.client.CinematicManager;

@Mixin(MouseHandler.class)
public class MouseHandlerMixin {
	
    @Inject(method = "turnPlayer", at = @At("HEAD"), cancellable = true)
    private void onTurnPlayer(CallbackInfo ci) {
        var mc = Minecraft.getInstance();    
        if (mc.player == null) return;
        
    	if (CinematicManager.getInstance().isInCinematic()) {
    		mc.player.setYRot(CinematicManager.getInstance().getStoredYaw());
    		mc.player.setXRot(CinematicManager.getInstance().getStoredPitch());
    		mc.player.yRotO = CinematicManager.getInstance().getStoredYaw();
    		mc.player.xRotO = CinematicManager.getInstance().getStoredPitch();
            ci.cancel();
        }
    }
}
