package dev.dixmk.minepreggo.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import dev.dixmk.minepreggo.world.entity.preggo.zombie.AbstractHostileZombieGirl;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.AbstractTamableZombieGirl;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.AbstractZombieGirl;

import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.sensing.VillagerHostilesSensor;

@Mixin(VillagerHostilesSensor.class)
public class VillagerHostilesSensorMixin {
    @Inject(method = "isHostile", at = @At("HEAD"), cancellable = true)
    private void isHostile(LivingEntity entity, CallbackInfoReturnable<Boolean> cir) {
		if (entity instanceof AbstractHostileZombieGirl || (entity instanceof AbstractTamableZombieGirl zombieGirl && zombieGirl.getTamableData().isSavage())) {
			cir.setReturnValue(true);
			return;
		}	
    }
    
    @Inject(method = "isClose", at = @At("HEAD"), cancellable = true)
    private void isClose(LivingEntity villager, LivingEntity target, CallbackInfoReturnable<Boolean> cir) {
		if (target instanceof AbstractZombieGirl) {
			cir.setReturnValue(target.distanceToSqr(villager) < 8.0D);
			return;
		}	
    }
}

