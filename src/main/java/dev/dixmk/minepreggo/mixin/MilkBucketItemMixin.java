package dev.dixmk.minepreggo.mixin;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MilkBucketItem;
import net.minecraft.world.level.Level;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;

@Mixin(MilkBucketItem.class)
public class MilkBucketItemMixin {

    @Inject(
    		method = "finishUsingItem",
    		at = @At(
    		value = "INVOKE",
    		target = "Lnet/minecraft/world/entity/LivingEntity;removeAllEffects()Z"
            ),
            cancellable = true
        )
	private void onDrinkMilk(ItemStack stack, Level level, LivingEntity entity, CallbackInfoReturnable<ItemStack> cir) {
    	if (entity instanceof Player player) {
    		player.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap -> {     		
    			if (cap.getFemaleData().isPresent()) {
        			player.getActiveEffectsMap().entrySet().removeIf(entry -> {
            			MobEffectInstance effect = entry.getValue();
            			return !PregnancySystemHelper.isPregnancyEffect(effect.getEffect());
            		});
            		cir.setReturnValue(stack);
    			}	
    		});
    	}
    	// If not a Player, do nothing -> vanilla behavior (removeAllEffects) proceeds as normal
	}
}
