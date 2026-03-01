package dev.dixmk.minepreggo.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import dev.dixmk.minepreggo.init.MinepreggoModDamageSources;

import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Inject(method = "hurtArmor", at = @At("HEAD"), cancellable = true)
	private void preventArmorDamageFromCustomType(DamageSource source, float damage, CallbackInfo ci) {
    	if (source.is(MinepreggoModDamageSources.PREGNANCY_PAIN)) {
    		ci.cancel();
    	}
	}
}
