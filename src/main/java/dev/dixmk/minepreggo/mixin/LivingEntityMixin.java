package dev.dixmk.minepreggo.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import dev.dixmk.minepreggo.init.MinepreggoDamageSources;

import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Inject(method = "hurtArmor", at = @At("HEAD"), cancellable = true)
	private void preventArmorDamageFromCustomType(DamageSource source, float damage, CallbackInfo ci) {
    	/**
    	 * It does not work when the living entity is a player, but it does work for mobs.
    	 * Currently, it injects also in Player.hurtArmor in PlayerMixin, it works.
    	 * */
    	if (source.is(MinepreggoDamageSources.PREGNANCY_PAIN)) {
    		ci.cancel();
    	}
	}
}
