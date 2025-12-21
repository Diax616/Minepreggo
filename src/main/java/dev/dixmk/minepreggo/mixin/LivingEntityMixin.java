package dev.dixmk.minepreggo.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import dev.dixmk.minepreggo.client.SexCinematicManager;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
	
    @Inject(
    		method = "travel",
    		at = @At("HEAD"),
    		cancellable = true
    		)
    private void onTravel(Vec3 movement, CallbackInfo ci) {
        if ((LivingEntity.class.cast(this)) instanceof Player player && player == Minecraft.getInstance().player && SexCinematicManager.getInstance().isInCinematic()) {
        	ci.cancel();
        }
    }
}
