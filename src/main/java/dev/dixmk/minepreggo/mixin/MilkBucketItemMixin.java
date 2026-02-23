package dev.dixmk.minepreggo.mixin;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.MilkBucketItem;
import net.minecraft.world.level.Level;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.init.MinepreggoModMobEffects;
import dev.dixmk.minepreggo.world.entity.LivingEntityHelper;
import dev.dixmk.minepreggo.world.entity.player.PlayerHelper;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;

@Mixin(MilkBucketItem.class)
public class MilkBucketItemMixin {

    // Inject at HEAD to prevent the original method from executing when we handle milk
    @Inject(method = "finishUsingItem", at = @At("HEAD"), cancellable = true)
    private void preventEffectRemoval(ItemStack stack, Level level, LivingEntity entity, CallbackInfoReturnable<ItemStack> cir) {
          	
    	if (!level.isClientSide && entity instanceof ServerPlayer player) {
            player.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap ->   
	        	cap.getFemaleData().ifPresent(femaleData -> {                      		                	
	            	boolean flag = false;
	            	List<MobEffect> effectsToRemove = null;
	            	if (femaleData.isPregnant() && femaleData.isPregnancyDataInitialized()) {        
	                	// TODO: Refactor the logic to avoid removing secondary pregnancy effects	
	            		if (player.hasEffect(MinepreggoModMobEffects.ENDER_DRAGON_PREGNANCY.get())) {
		            		effectsToRemove = PlayerHelper.removeEffectsBeingPregnantOfEnderDragon(player, femaleData.getPregnancyData().getCurrentPregnancyPhase());
	            		}
	            		else {
		            		effectsToRemove = PlayerHelper.removeEffectsBeingPregnant(player, femaleData.getPregnancyData().getCurrentPregnancyPhase());
	            		}	 
	                	flag = true;
	                }
	                else {         
	                	effectsToRemove = LivingEntityHelper.removeEffects(entity, effect -> !PregnancySystemHelper.isFemaleEffect(effect));
	                    flag = true;
	                }
	                
	                
	                if (flag && effectsToRemove != null) {
	                    // Remove the effects safely (they were already collected into a separate list)
	                	effectsToRemove.forEach(player::removeEffect);
	                    
	                    // Handle item consumption and return the appropriate ItemStack (bucket)
	                    if (!player.getAbilities().instabuild) {
	                        stack.shrink(1);
	                    }
	
	                    // Set the return value and cancel further execution so the original
	                    cir.setReturnValue(stack.isEmpty() ? new ItemStack(Items.BUCKET) : stack);
	                    cir.cancel();
	                }
	            })         
            );
        }
    }
}