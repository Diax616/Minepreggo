package dev.dixmk.minepreggo.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import dev.dixmk.minepreggo.event.ClientEventHandler;
import net.minecraft.client.Camera;
import net.minecraft.world.phys.Vec3;

/**
 * Camera Mixin - Applies persistent camera offsets to prevent server sync reset
 * 
 * This mixin intercepts camera position setup and applies the offsets stored
 * in ClientEventHandler without modifying the actual player position.
 * 
 * This solves the issue where camera position changes would reset each tick
 * because the server would resync the player's actual position.
 */
@Mixin(Camera.class)
public abstract class CameraMixin {
    
    @Shadow private Vec3 position;
    
    /**
     * Inject into Camera.setup() to apply persistent camera offsets
     * This is called every frame during camera setup
     */
    @Inject(method = "setup", at = @At("TAIL"))
    private void applyPersistentCameraOffset(CallbackInfo ci) {
        // Get the offset values (they persist across ticks)
        double offsetX = ClientEventHandler.getCameraOffsetX();
        double offsetY = ClientEventHandler.getCameraOffsetY();
        double offsetZ = ClientEventHandler.getCameraOffsetZ();
        
        // Only apply if there's an actual offset
        if (offsetX != 0.0 || offsetY != 0.0 || offsetZ != 0.0) {
            // Apply the offset to the camera position
            this.position = this.position.add(offsetX, offsetY, offsetZ);
        }
    }
}

