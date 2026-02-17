package dev.dixmk.minepreggo.mixin;

import java.util.UUID;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.init.MinepreggoModAdvancements;
import dev.dixmk.minepreggo.init.MinepreggoModItems;
import dev.dixmk.minepreggo.init.MinepreggoModVillagerProfessions;
import dev.dixmk.minepreggo.network.chat.MessageHelper;
import dev.dixmk.minepreggo.server.ServerCinematicManager;
import dev.dixmk.minepreggo.world.entity.player.PlayerHelper;
import dev.dixmk.minepreggo.world.inventory.preggo.PlayerPrenatalCheckUpMenu;
import dev.dixmk.minepreggo.world.item.BabyItem;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPain;
import dev.dixmk.minepreggo.world.pregnancy.SexHelper;

import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.gossip.GossipType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

@Mixin(Villager.class)
public class VillagerMixin {
	
    @Inject(method = "pickUpItem", at = @At("HEAD"))
    private void onPickUpItem(ItemEntity itemEntity, CallbackInfo ci) {
		Villager villager = Villager.class.cast(this);
    	ItemStack stack = itemEntity.getItem();     
    	if (!villager.level().isClientSide && PlayerHelper.containsAnyFemalePlayerIdTag(stack)) {
        	boolean isPregnant = false;
            final UUID playerId;
            UUID playerIdTemp;

    		if ((playerIdTemp = PlayerHelper.getPregnantFemalePlayerIdTag(stack)) != null) {
    			isPregnant = true;
    			playerId = playerIdTemp;
    		}	
    		else if ((playerIdTemp = PlayerHelper.getFemalePlayerIdTag(stack)) != null) {
        		playerId = playerIdTemp;
    		}
    		else {
    			MinepreggoMod.LOGGER.warn("Villager {} picked up item {} with a female player id tag but no player id was found in the tag, this should not happen", villager.getName().getString(), stack.getItem().getName(stack).getString());
				playerId = null;
			}

    		if (playerId != null) {
        		Player target = villager.level().getNearestPlayer(villager.getX(), villager.getY(), villager.getZ(), 25, p -> p.getUUID().equals(playerId));  		
        		if (target instanceof ServerPlayer serverPlayer &&
        				villager.getVillagerData().getProfession() != VillagerProfession.NONE &&
        				SexHelper.hasEnoughBedsForBreeding(villager, 1, 8) &&
        				villager.canBreed()) {       			
        			villager.level().playSound(null, villager.blockPosition(), SoundEvents.VILLAGER_YES, villager.getSoundSource(), 1.0F, 1.0F);
            		villager.getNavigation().moveTo(serverPlayer, 1.05); 
            		SexHelper.initSex(serverPlayer, villager);       
            		MinepreggoMod.LOGGER.debug("Villager {} picked up item {} with player id {}, isPregnant: {}, target player found: {}", villager.getName().getString(), stack.getItem().getName(stack).getString(), playerId, isPregnant, target != null);
        		}
            	PlayerHelper.removeAnyFemalePlayerIdTag(stack);
			}   	
		}
    }

    @Inject(method = "mobInteract", at = @At("HEAD"), cancellable = true)
	private void onMobInteract(Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {
    	if (player.level().isClientSide) {
    		return;
    	}
       	Villager villager = Villager.class.cast(this);	
       	ItemStack itemInHand = player.getItemInHand(hand);
       	
    	if (ServerCinematicManager.getInstance().isInCinematic(villager)) {
            cir.setReturnValue(InteractionResult.FAIL);
            return;
        }    
    	else if ((itemInHand.is(MinepreggoModItems.BABY_VILLAGER.get()) || itemInHand.is(MinepreggoModItems.BABY_HUMAN.get()))
    			&& !villager.isBaby()
    			&& player.level() instanceof ServerLevel serverLevel
    			&& player instanceof ServerPlayer serverPlayer) {  
			
    		if (itemInHand.is(MinepreggoModItems.BABY_VILLAGER.get()) ) {
        		Villager babyVillager = EntityType.VILLAGER.spawn(serverLevel, BlockPos.containing(villager.getX(), villager.getY(), villager.getZ()), MobSpawnType.BREEDING);
    			babyVillager.setBaby(true);
    		}
    		
			// It considers that the villager is always male (father).
			if (BabyItem.isFatherOf(itemInHand, villager.getUUID()) && BabyItem.isMotherOf(itemInHand, player.getUUID())) {
				if (!villager.isSilent()) {
					serverLevel.playSound(null, villager.blockPosition(), SoundEvents.VILLAGER_CELEBRATE, villager.getSoundSource(), 1.0F, 1.0F);
				}
				
				villager.getCapability(MinepreggoCapabilities.VILLAGER_DATA).ifPresent(cap -> {
					UUID playerId = player.getUUID();
					cap.doesVillagerKnowPlayerGivenBirthToChildFromThem(playerId).ifPresent(knowsPlayerGivenBirthToChildFromVillager -> {
						if (!knowsPlayerGivenBirthToChildFromVillager) {
							cap.villagerKnowsPlayerGivenBirthToChildFromThem(playerId);
							villager.getGossips().add(playerId, GossipType.MAJOR_POSITIVE, 30);			
							MinepreggoMod.LOGGER.debug("Villager {} now knows that player {} has given birth to a child from them", villager.getName().getString(), player.getName().getString());
						}
					});
				});				
			}
			else if (!villager.isSilent()) {
				serverLevel.playSound(null, villager.blockPosition(), SoundEvents.VILLAGER_NO, villager.getSoundSource(), 1.0F, 1.0F);
				MinepreggoMod.LOGGER.debug("Player {} tried to give a baby villager item to villager {} but the player is not the mother or the father of the baby, this should not happen", player.getName().getString(), villager.getName().getString());
			}
			
            MinepreggoModAdvancements.CHECK_PARENT_TRIGGER.trigger(serverPlayer, villager, itemInHand);
            
            itemInHand.shrink(1);
            if (itemInHand.isEmpty()) {
            	player.setItemInHand(hand, ItemStack.EMPTY);
            }        
            player.getInventory().setChanged();	
            
            cir.setReturnValue(InteractionResult.SUCCESS);
            return;
    	}
    	else if (player instanceof ServerPlayer serverPlayer) {
    		if (serverPlayer.isCrouching() && villager.getVillagerData().getProfession() == MinepreggoModVillagerProfessions.VILLAGER_DOCTOR.get()) {  			
    			Integer result = serverPlayer.getCapability(MinepreggoCapabilities.PLAYER_DATA)
    					.resolve()
    					.flatMap(playerCap -> playerCap.getFemaleData().resolve()
    					.map(femaleData -> {
    						if (femaleData.isPregnant() && femaleData.isPregnancyDataInitialized()) {
    							if (PregnancyPain.isLaborPain(femaleData.getPregnancyData().getPregnancyPain())) {
    								return 0;
    							}
    							// TODO: Villager does not keep standing facing the player when the menu is open, the method setTradingPlayer from Merchant interface does work for this case
		            			villager.setTradingPlayer(serverPlayer);      			
		            			PlayerPrenatalCheckUpMenu.VillagerMenu.showPrenatalCheckUpMenu(serverPlayer, villager);
		                		cir.setReturnValue(InteractionResult.SUCCESS);
		                		return 1;
    						} 	
    						return 2;
    					})).orElse(Integer.valueOf(-1));
    			
    			if (result == 2) {
    				MessageHelper.sendTo(serverPlayer, Component.translatable("chat.minepreggo.player.message.prenatal_checkup.not_pregnant"), true);
    				if (!villager.isSilent()) {
    					villager.level().playSound(null, villager.blockPosition(), SoundEvents.VILLAGER_NO, villager.getSoundSource(), 1.0F, 1.0F);
    				}
    				cir.setReturnValue(InteractionResult.FAIL);
            		return;
    			}	
    		}
    		else {
        		villager.getCapability(MinepreggoCapabilities.VILLAGER_DATA).ifPresent(villagerCap -> 
                	serverPlayer.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(playerCap -> 
                		playerCap.getFemaleData().ifPresent(femaleData -> { 			
                			if (femaleData.isPregnant() && femaleData.isPregnancyDataInitialized()) {			
                				villagerCap.doesVillagerKnowsPlayerIsPregnantFromThem(serverPlayer.getUUID()).ifPresent(knowsPlayerIsPregnantFromVillager -> {
									if (!knowsPlayerIsPregnantFromVillager) {
					        			villager.level().playSound(null, villager.blockPosition(), SoundEvents.VILLAGER_CELEBRATE, villager.getSoundSource(), 1.0F, 1.0F);
										villagerCap.addPlayerThatIsPregnantFromVillager(serverPlayer.getUUID());
										villagerCap.villagerKnowsPlayerIsPregnantFromThem(serverPlayer.getUUID());
										villager.getGossips().add(serverPlayer.getUUID(), GossipType.MAJOR_POSITIVE, 20);
										MinepreggoMod.LOGGER.debug("Villager {} now knows that player {} is pregnant from them", villager.getName().getString(), serverPlayer.getName().getString());
									}
                				});
                			}
                		})
                	)
                );
    		}
    	}
	}  
}
