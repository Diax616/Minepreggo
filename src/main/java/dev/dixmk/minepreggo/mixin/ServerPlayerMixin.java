package dev.dixmk.minepreggo.mixin;

import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.world.entity.player.PlayerHelper;

import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.item.ItemStack;

@Mixin(ServerPlayer.class)
public class ServerPlayerMixin {
	
    @Inject(method = "drop", at = @At("HEAD"))
    private void onDrop(ItemStack stack, boolean dropAround, boolean traceItem, CallbackInfoReturnable<ItemEntity> cir) {
        ServerPlayer player = ServerPlayer.class.cast(this);
        player.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap -> 
        	cap.getFemaleData().ifPresent(femaleData -> {	       		
        		if (Villager.FOOD_POINTS.containsKey(stack.getItem())) {
            		if (femaleData.isPregnant() && femaleData.isPregnancyDataInitialized()) {      			
        				PlayerHelper.addPregnantFemalePlayerIdTag(stack, player.getUUID());  			
            		}
            		else {
            			PlayerHelper.addFemalePlayerIdTag(stack, player.getUUID());
            		}
        		}
        	})
        );
    }
}
