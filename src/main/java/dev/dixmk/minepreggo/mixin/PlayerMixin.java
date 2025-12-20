package dev.dixmk.minepreggo.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.At;

import dev.dixmk.minepreggo.client.animation.player.BellyAnimationManager;
import dev.dixmk.minepreggo.client.animation.preggo.BellyAnimation;
import net.minecraft.world.entity.player.Player;

@Mixin(Player.class)
public abstract class PlayerMixin {
    
    @Inject(method = "handleEntityEvent", at = @At("HEAD"), cancellable = true)
    private void onHandleEntityEvent(byte id, CallbackInfo ci) {
        var animation = BellyAnimation.QUICK_ANIMATIONS.get(id);  
        
        if (animation != null) {
            Player self = (Player) (Object) this;
            
            if (self.level().isClientSide()) {
            	BellyAnimationManager.getInstance().startAnimation(self, animation);
            }
            
            ci.cancel();
        }
    } 
}
