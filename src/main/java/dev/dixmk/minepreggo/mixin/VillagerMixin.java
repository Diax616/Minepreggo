package dev.dixmk.minepreggo.mixin;

import java.util.Optional;
import java.util.UUID;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.init.MinepreggoModItems;
import dev.dixmk.minepreggo.init.MinepreggoModVillagerProfessions;
import dev.dixmk.minepreggo.network.packet.s2c.RenderSexOverlayS2CPacket;
import dev.dixmk.minepreggo.network.packet.s2c.SexCinematicControlP2MS2CPacket;
import dev.dixmk.minepreggo.server.ServerCinematicManager;
import dev.dixmk.minepreggo.server.ServerParticleHelper;
import dev.dixmk.minepreggo.server.ServerTaskQueueManager;
import dev.dixmk.minepreggo.world.entity.player.PlayerHelper;
import dev.dixmk.minepreggo.world.inventory.preggo.PlayerPrenatalCheckUpMenu;
import dev.dixmk.minepreggo.world.item.AbstractBaby;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPain;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;

import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.gossip.GossipType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.PacketDistributor;

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
        				PregnancySystemHelper.hasEnoughBedsForBreeding(villager, 1, 8) &&
        				villager.canBreed()) {       			
        			villager.level().playSound(null, villager.blockPosition(), SoundEvents.VILLAGER_YES, villager.getSoundSource(), 1.0F, 1.0F);
            		villager.getNavigation().moveTo(serverPlayer, 1.05); 
            		initSex(serverPlayer, villager);       
            		MinepreggoMod.LOGGER.debug("Villager {} picked up item {} with player id {}, isPregnant: {}, target player found: {}", villager.getName().getString(), stack.getItem().getName(stack).getString(), playerId, isPregnant, target != null);
        		}
            	PlayerHelper.removeAnyFemalePlayerIdTag(stack);
			}   	
		} 
    }


    @Inject(method = "mobInteract", at = @At("HEAD"), cancellable = true)
	private void onMobInteract(Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {
    	if (!player.level().isClientSide) {
        	Villager villager = Villager.class.cast(this);	
           	ItemStack itemInHand = player.getItemInHand(hand);

        	if (ServerCinematicManager.getInstance().isInCinematic(villager)) {
                cir.setReturnValue(InteractionResult.FAIL);
                return;
            }    
        	else if ((itemInHand.is(MinepreggoModItems.BABY_VILLAGER.get()) || itemInHand.is(MinepreggoModItems.BABY_HUMAN.get()))
        			&& !villager.isBaby()
        			&& player.level() instanceof ServerLevel serverLevel) {  
    			
        		if (itemInHand.is(MinepreggoModItems.BABY_VILLAGER.get()) ) {
            		Villager babyVillager = EntityType.VILLAGER.spawn(serverLevel, BlockPos.containing(villager.getX(), villager.getY(), villager.getZ()), MobSpawnType.BREEDING);
        			babyVillager.setBaby(true);
        		}
        		
    			// It considers that the villager is always male (father).
    			if (AbstractBaby.isFatherOf(itemInHand, villager.getUUID()) && AbstractBaby.isMotherOf(itemInHand, player.getUUID())) {
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
    			                
                itemInHand.shrink(1);
                if (itemInHand.isEmpty()) {
                	player.setItemInHand(hand, ItemStack.EMPTY);
                }        
                player.getInventory().setChanged();	
                
                cir.setReturnValue(InteractionResult.SUCCESS);
                return;
        	}

        	if (player instanceof ServerPlayer serverPlayer) {
        		if (serverPlayer.isCrouching()) {
            		Optional<Boolean> isPregnantAndNotInLabor = serverPlayer.getCapability(MinepreggoCapabilities.PLAYER_DATA)
            				.resolve()
            				.flatMap(cap ->	cap.getFemaleData().map(femaleData -> femaleData.isPregnant() && femaleData.isPregnancyDataInitialized() && !PregnancyPain.isLaborPain(femaleData.getPregnancyData().getPregnancyPain())));
            		
            		if (isPregnantAndNotInLabor.orElse(Boolean.FALSE) && villager.getVillagerData().getProfession() == MinepreggoModVillagerProfessions.VILLAGER_DOCTOR.get()) {
            			// TODO: Villager does not keep standing facing the player when the menu is open, the method setTradingPlayer from Merchant interface does work for this case
            			villager.setTradingPlayer(serverPlayer);
            			
            			PlayerPrenatalCheckUpMenu.VillagerMenu.showPrenatalCheckUpMenu(serverPlayer, villager);
                		cir.setReturnValue(InteractionResult.SUCCESS);
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
    
    private static void initSex(ServerPlayer femalePlayer, Villager villager) {
    	
		Runnable start = () -> {
			villager.setTradingPlayer(femalePlayer);
			ServerParticleHelper.spawnRandomlyFromServer(femalePlayer, ParticleTypes.HEART);
			ServerParticleHelper.spawnRandomlyFromServer(villager, ParticleTypes.HEART);
		};
		
		Runnable end = () -> {
			villager.setTradingPlayer(null);
			femalePlayer.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap -> 
				cap.getFemaleData().ifPresent(femaleData -> {				
					if (!femaleData.isPregnant() && femaleData.getPostPregnancyData().isEmpty()) {
						PlayerHelper.tryStartPregnancyBySex(femalePlayer, villager);
					}	
					else if (femaleData.isPregnant() && femaleData.isPregnancyDataInitialized()) {
						PlayerHelper.removeHorny(femalePlayer);
					}
					
					femaleData.resetFertilityRate();
					villager.eatAndDigestFood();
					villager.eatAndDigestFood();
									
					villager.getCapability(MinepreggoCapabilities.VILLAGER_DATA).ifPresent(capVillager -> {
						if (capVillager.addPlayerThatHadSexWithVillager(femalePlayer.getUUID())) {
							villager.getGossips().add(femalePlayer.getUUID(), GossipType.MAJOR_POSITIVE, 5);
							MinepreggoMod.LOGGER.debug("Villager {} now has player {} in the list of players that had sex with them", villager.getName().getString(), femalePlayer.getName().getString());
						}
					});	
							
					femalePlayer.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 600, 0, false, true, true));
					femalePlayer.addEffect(new MobEffectInstance(MobEffects.HUNGER, 1200, 0, false, true, true));
					villager.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 600, 0, false, true, true));
				})
			);	
		};
				
		var manager = ServerTaskQueueManager.getInstance();
		
		manager.queueTask(20, start);		
		manager.queueTask(40, start);
		
		ServerCinematicManager.getInstance().start(femalePlayer, villager, start, end);
		
		int overlayTicks = 120;
		int overlayPauseTicks = 20;
			                  
        MinepreggoModPacketHandler.INSTANCE.send(
    			PacketDistributor.PLAYER.with(() -> femalePlayer),
    			new RenderSexOverlayS2CPacket(overlayTicks, overlayPauseTicks)
    		);     		
        
        MinepreggoModPacketHandler.INSTANCE.send(
    			PacketDistributor.PLAYER.with(() -> femalePlayer),
    			new SexCinematicControlP2MS2CPacket(true, villager.getId())
    		);
        
        
        manager.queueTask(overlayTicks * 2 + overlayPauseTicks, () -> {
			ServerCinematicManager.getInstance().end(femalePlayer);
	        
			MinepreggoModPacketHandler.INSTANCE.send(
	    			PacketDistributor.PLAYER.with(() -> femalePlayer),
	    			new SexCinematicControlP2MS2CPacket(false, villager.getId())
	    		);
		});		
    }
}
