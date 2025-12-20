package dev.dixmk.minepreggo.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.MouseHandler;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import dev.dixmk.minepreggo.client.SexCinematicManager;

@Mixin(MouseHandler.class)
public class MouseHandlerMixin {
	
    @Inject(method = "turnPlayer", at = @At("HEAD"), cancellable = true)
    private void onTurnPlayer(CallbackInfo ci) {
        var mc = Minecraft.getInstance();    
        if (mc.player == null) return;
        
    	if (SexCinematicManager.getInstance().isInCinematic()) {
    		mc.player.setYRot(SexCinematicManager.getInstance().getStoredYaw());
    		mc.player.setXRot(SexCinematicManager.getInstance().getStoredPitch());
    		mc.player.yRotO = SexCinematicManager.getInstance().getStoredYaw();
    		mc.player.xRotO = SexCinematicManager.getInstance().getStoredPitch();
            ci.cancel();
        }
    }
}
