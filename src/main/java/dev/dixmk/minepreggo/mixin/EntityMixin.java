package dev.dixmk.minepreggo.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.world.entity.BellyPart;
import dev.dixmk.minepreggo.world.entity.BellyPartManager;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

@Mixin(Entity.class)
public class EntityMixin {

    @Inject(method = "canCollideWith", at = @At("HEAD"), cancellable = true)
    private void onCanCollideWith(Entity other, CallbackInfoReturnable<Boolean> cir) {
    	if (Entity.class.cast(this) instanceof LivingEntity livingEntity
    			&& !livingEntity.level().isClientSide
    			&& MinepreggoModConfig.isBellyColisionsEnable()) {
            BellyPart myBelly = BellyPartManager.getInstance().get(livingEntity);
            if (myBelly != null && other.getUUID().equals(myBelly.getUUID())) {
            	cir.setReturnValue(false);
            }
		}
    }
}
