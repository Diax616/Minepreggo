package dev.dixmk.minepreggo.mixin;

import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.world.entity.player.PlayerHelper;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;

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
        		if (!femaleData.isPregnant()
        				&& femaleData.getPostPregnancyData().isEmpty()
        				&& Villager.FOOD_POINTS.containsKey(stack.getItem())) {
        			PlayerHelper.addFemalePlayerIdTag(stack, player.getUUID());
        		}
        		else if (femaleData.isPregnant()
        				&& femaleData.isPregnancySystemInitialized()
        				&& Villager.FOOD_POINTS.containsKey(stack.getItem())) {
        			
        			var pregnancySystem = femaleData.getPregnancySystem();
        			var phase = pregnancySystem.getCurrentPregnancyStage();
        			
        			if (phase.compareTo(PregnancyPhase.P4) >= 0) {
        				PlayerHelper.addPregnantFemalePlayerIdTag(stack, player.getUUID());
        			}   			
        		}
        	})
        );
    }
}
