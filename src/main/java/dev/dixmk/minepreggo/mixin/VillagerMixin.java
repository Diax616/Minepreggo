package dev.dixmk.minepreggo.mixin;

import java.util.Optional;
import java.util.UUID;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.utils.ServerParticleUtil;
import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.init.MinepreggoModItems;
import dev.dixmk.minepreggo.init.MinepreggoModVillagerProfessions;
import dev.dixmk.minepreggo.network.packet.RenderSexOverlayS2CPacket;
import dev.dixmk.minepreggo.network.packet.SexCinematicControlP2MS2CPacket;
import dev.dixmk.minepreggo.server.ServerCinematicManager;
import dev.dixmk.minepreggo.server.ServerTaskQueueManager;
import dev.dixmk.minepreggo.world.entity.player.PlayerHelper;
import dev.dixmk.minepreggo.world.inventory.preggo.PlayerPrenatalCheckUpMenu;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;

import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
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
import net.minecraftforge.network.PacketDistributor;

@Mixin(Villager.class)
public class VillagerMixin {
	
    @Inject(method = "pickUpItem", at = @At("HEAD"))
    private void onPickUpItem(ItemEntity itemEntity, CallbackInfo ci) {
        ItemStack stack = itemEntity.getItem(); 
        UUID playerId;    
        
        if ((playerId = PlayerHelper.getFemalePlayerIdTag(stack)) != null) {
        	Villager villager = Villager.class.cast(this);
            if (villager.getVillagerData().getProfession() != VillagerProfession.NONE
            		&& villager.canBreed()
            		&& PregnancySystemHelper.hasEnoughBedsForBreeding(villager, 1, 8)
            		&& villager.level() instanceof ServerLevel serverLevel
            		&& serverLevel.getPlayerByUUID(playerId) instanceof ServerPlayer serverPlayer
            		&& serverPlayer.distanceToSqr(villager) < 25D) {        	       	      	
        		villager.getNavigation().moveTo(serverPlayer, 0.95); 
        		initSex(serverPlayer, villager);
            }    
        }
        else if ((playerId = PlayerHelper.getPregnantFemalePlayerIdTag(stack)) != null) {
    		Villager villager = Villager.class.cast(this);
    		if (villager.level() instanceof ServerLevel serverLevel
    				&& villager.canBreed()
    				&& PregnancySystemHelper.hasEnoughBedsForBreeding(villager, 1, 8)
    				&& serverLevel.getPlayerByUUID(playerId) instanceof ServerPlayer serverPlayer
        			&& serverPlayer.distanceToSqr(villager) < 25D) {       		      			       					
    			villager.getNavigation().moveTo(serverPlayer, 0.95); 
    			shake(serverPlayer, villager);			
    		}
        }     
    }


    @Inject(method = "mobInteract", at = @At("HEAD"), cancellable = true)
	private void onMobInteract(Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {
    	if (!player.level().isClientSide) {
        	Villager villager = Villager.class.cast(this);	
        	if (ServerCinematicManager.getInstance().isInCinematic(villager)) {
                cir.setReturnValue(InteractionResult.FAIL);
                return;
            }    
        	else if (player.getItemInHand(hand).is(MinepreggoModItems.BABY_VILLAGER.get())
        			&& !villager.isBaby()
        			&& player.level() instanceof ServerLevel serverLevel) {  
				Villager babyVillager = EntityType.VILLAGER.spawn(serverLevel, BlockPos.containing(villager.getX(), villager.getY(), villager.getZ()), MobSpawnType.BREEDING);
				babyVillager.setBaby(true);
				ItemStack itemstack = player.getItemInHand(hand);
				itemstack.shrink(1);
	            if (itemstack.isEmpty()) {
	            	player.setItemInHand(hand, ItemStack.EMPTY);
	            }        
	            player.getInventory().setChanged();	
        		cir.setReturnValue(InteractionResult.SUCCESS);
                return;
        	}

        	if (player instanceof ServerPlayer serverPlayer) {
        		if (serverPlayer.isCrouching()) {
	        		Optional<Boolean> result = serverPlayer.getCapability(MinepreggoCapabilities.PLAYER_DATA).map(cap -> 
			        				cap.getFemaleData().map(femaleData -> {
			        					return femaleData.isPregnant() && femaleData.isPregnancyDataInitialized();
			        				})
			        			).orElse(Optional.empty());
	        		
	        		if (result.isPresent() && result.get().booleanValue() && villager.getVillagerData().getProfession() == MinepreggoModVillagerProfessions.VILLAGER_DOCTOR.get()) {
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
	                		playerCap.getFemaleData().ifPresent(femaleData ->             			
	                			villagerCap.getMotherPlayerId().ifPresent(motherId -> {
	                    			if (!villagerCap.doesVillagerKnowPlayerIsPregnant()
	                    					&& motherId.equals(serverPlayer.getUUID())
	                    					&& femaleData.isPregnant()
	                    					&& femaleData.isPregnancyDataInitialized()) {
	        							villager.getGossips().add(serverPlayer.getUUID(), GossipType.MAJOR_POSITIVE, 50);
	        							villagerCap.setVillagerKnowIsPlayerIsPregnant(true);
	                    			}
	                			})
	                		)
	                	)
	                );
        		}
        	}
    	}
	}
    
    private static void initSex(ServerPlayer femalePlayer, Villager villager) {
    	
		Runnable start = () -> {
			villager.setTradingPlayer(femalePlayer);
			ServerParticleUtil.spawnRandomlyFromServer(femalePlayer, ParticleTypes.HEART);
			ServerParticleUtil.spawnRandomlyFromServer(villager, ParticleTypes.HEART);
		};
		
		Runnable end = () -> {
			villager.setTradingPlayer(null);
			femalePlayer.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap -> 
				cap.getFemaleData().ifPresent(femaleData -> {				
					if (!PlayerHelper.tryStartPregnancyBySex(femalePlayer, villager)) {
						MinepreggoMod.LOGGER.warn("Failed to impregnate female player {} by villager {}", femalePlayer.getUUID(), villager.getUUID());
					}
					else {
						femaleData.resetFertilityRate();
						villager.eatAndDigestFood();
						villager.eatAndDigestFood();
						villager.getCapability(MinepreggoCapabilities.VILLAGER_DATA).ifPresent(capVillager -> {
							capVillager.setMotherPlayer(femalePlayer.getUUID());
							villager.getGossips().add(femalePlayer.getUUID(), GossipType.MAJOR_POSITIVE, 15);
						});												
					}
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
    
    private static void shake(ServerPlayer femalePlayer, Villager villager) {
    	
		Runnable start = () -> {
			villager.setTradingPlayer(femalePlayer);
			ServerParticleUtil.spawnRandomlyFromServer(femalePlayer, ParticleTypes.HEART);
			ServerParticleUtil.spawnRandomlyFromServer(villager, ParticleTypes.HEART);
		};
		
		Runnable end = () -> {			
			villager.setTradingPlayer(null);	
			villager.eatAndDigestFood();
			villager.eatAndDigestFood();
			PlayerHelper.removeHorny(femalePlayer);		
		};
				
		var manager = ServerTaskQueueManager.getInstance();
		
		manager.queueTask(20, start);		
		manager.queueTask(40, start);
		
		ServerCinematicManager.getInstance().start(femalePlayer, villager, start, end);
		
		int overlayTicks = 140;
		int overlayPauseTicks = 80;
			                  
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
