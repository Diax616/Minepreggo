package dev.dixmk.minepreggo.mixin;

import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.mojang.blaze3d.vertex.PoseStack;

import dev.dixmk.minepreggo.client.animation.player.PlayerAnimationManager;



@Mixin(PlayerRenderer.class)
public class PlayerRendererMixin {
   
    @Inject(
    		method = "render(Lnet/minecraft/client/player/AbstractClientPlayer;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V",
            at = @At("HEAD")
    	)
    private void onRender(AbstractClientPlayer player, float entityYaw, float partialTicks, 
                         PoseStack poseStack, MultiBufferSource buffer, int packedLight, 
                         CallbackInfo ci) {
    
        var manager = PlayerAnimationManager.getInstance().get(player);
        manager.tick();

    }
}
