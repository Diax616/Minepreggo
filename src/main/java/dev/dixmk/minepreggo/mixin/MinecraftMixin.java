package dev.dixmk.minepreggo.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.Input;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import dev.dixmk.minepreggo.client.SexCinematicManager;

@Mixin(Minecraft.class)
public class MinecraftMixin {
    @Inject(method = "tick", at = @At("HEAD"))
    private void onTick(CallbackInfo ci) {
        if (SexCinematicManager.getInstance().isInCinematic()) {
            Minecraft mc = Minecraft.getInstance();
            if (mc.player != null && mc.player.input != null) {
                Input input = mc.player.input;
                input.jumping = false;
                input.shiftKeyDown = false;
            }
        }
    }
}