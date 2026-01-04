package dev.dixmk.minepreggo.mixin;

import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;

import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Set;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

@Mixin(ServerPlayer.class)
public class ServerPlayerMixin {

	private static final Set<Item> VILLAGER_FOODS = Set.of(Items.BREAD, Items.POTATO, Items.CARROT, Items.BEETROOT);
	
    @Inject(method = "drop", at = @At("HEAD"))
    private void onDrop(ItemStack stack, boolean dropAround, boolean traceItem, CallbackInfoReturnable<ItemEntity> cir) {
        ServerPlayer player = ServerPlayer.class.cast(this);
        player.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap -> 
        	cap.getFemaleData().ifPresent(femaleData -> {	
        		if (!femaleData.isPregnant()
        				&& femaleData.getPostPregnancyData().isEmpty()
        				&& VILLAGER_FOODS.contains(stack.getItem())) {
        	        stack.getOrCreateTag().putUUID("femalePlayerUUID", player.getUUID());
        		}
        		else if (femaleData.isPregnant() && femaleData.isPregnancySystemInitialized()) {
        			var pregnancySystem = femaleData.getPregnancySystem();
        			var phase = pregnancySystem.getCurrentPregnancyStage();
        			
        			if (phase.compareTo(PregnancyPhase.P4) >= 0) {
            	        stack.getOrCreateTag().putUUID("pregnantFemalePlayerUUID", player.getUUID());
        			}   			
        		}
        	})
        );
    }
}
