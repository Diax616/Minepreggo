package dev.dixmk.minepreggo.event;

import java.util.Optional;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.init.MinepreggoModDamageSources;
import dev.dixmk.minepreggo.init.MinepreggoModItems;
import dev.dixmk.minepreggo.init.MinepreggoModMobEffects;
import dev.dixmk.minepreggo.init.MinepreggoModSounds;
import dev.dixmk.minepreggo.network.capability.FemalePlayerImpl;
import dev.dixmk.minepreggo.network.capability.IMalePlayer;
import dev.dixmk.minepreggo.network.capability.MalePlayerImpl;
import dev.dixmk.minepreggo.network.chat.MessageHelper;
import dev.dixmk.minepreggo.network.packet.s2c.SyncFemalePlayerDataS2CPacket;
import dev.dixmk.minepreggo.network.packet.s2c.SyncMobEffectS2CPacket;
import dev.dixmk.minepreggo.network.packet.s2c.SyncPlayerDataS2CPacket;
import dev.dixmk.minepreggo.network.packet.s2c.SyncPregnancySystemS2CPacket;
import dev.dixmk.minepreggo.server.ServerCinematicManager;
import dev.dixmk.minepreggo.server.ServerPlayerAnimationManager;
import dev.dixmk.minepreggo.world.entity.BellyPartFactory;
import dev.dixmk.minepreggo.world.entity.BellyPartManager;
import dev.dixmk.minepreggo.world.entity.player.PlayerHelper;
import dev.dixmk.minepreggo.world.entity.preggo.Species;
import dev.dixmk.minepreggo.world.inventory.preggo.PlayerJoinsWorldMenu;
import dev.dixmk.minepreggo.world.inventory.preggo.RequestSexP2PMenu;
import dev.dixmk.minepreggo.world.item.CumSpecimenTubeItem;
import dev.dixmk.minepreggo.world.item.ICravingItem;
import dev.dixmk.minepreggo.world.pregnancy.Gender;
import dev.dixmk.minepreggo.world.pregnancy.IBreedable;
import dev.dixmk.minepreggo.world.pregnancy.PostPregnancy;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPain;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import io.netty.buffer.Unpooled;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Position;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level.ExplosionInteraction;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.EntityMountEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.ItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PacketDistributor;

@Mod.EventBusSubscriber(modid = MinepreggoMod.MODID)
public class PlayerEventHandler {

	private PlayerEventHandler() {}
	
	@SubscribeEvent
	public static void onPlayerLoggedInSync(PlayerEvent.PlayerLoggedInEvent event) {
		if (event.getEntity() instanceof ServerPlayer serverPlayer && !serverPlayer.level().isClientSide) {
			ServerPlayerAnimationManager.getInstance().stopAnimation(serverPlayer);
			serverPlayer.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(c -> {
				c.syncAllClientData(serverPlayer);
				if (c.canShowMainMenu()) {
					showPlayerMainMenu(serverPlayer);
				}
			});
		}
	}

	// TODO: If player has an animation playing when disconnecting, the animation won't play next time they log in. Capability data does not save the animation state.
	@SubscribeEvent
	public static void onPlayerQuit(PlayerEvent.PlayerLoggedOutEvent event) {
	    if (event.getEntity() instanceof ServerPlayer player) {
	    	ServerCinematicManager.getInstance().end(player);
	    	ServerPlayerAnimationManager.getInstance().stopAnimation(player);
	    	PlayerHelper.removeJigglePhysics(player);
	    	
	        if (MinepreggoModConfig.isBellyColisionsForPlayersEnable()) {
	        	BellyPartManager.getInstance().remove(event.getEntity());
	        }
	    }
	}
		
	@SubscribeEvent
	public static void onPlayerJoinWorld(EntityJoinLevelEvent event) {	
		if (event.getEntity() instanceof ServerPlayer serverPlayer && !serverPlayer.level().isClientSide) {	
			serverPlayer.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(c -> {
				c.syncAllClientData(serverPlayer);
				if (c.canShowMainMenu()) {
					showPlayerMainMenu(serverPlayer);
				}
			});				
		}
	}

	@SubscribeEvent
	public static void onPlayerRespawnedSync(PlayerEvent.PlayerRespawnEvent event) {
		if (event.getEntity() instanceof ServerPlayer serverPlayer && !serverPlayer.level().isClientSide) {	
	
	        if (MinepreggoModConfig.isBellyColisionsForPlayersEnable()) {
	        	BellyPartManager.getInstance().remove(event.getEntity());
	        }

			serverPlayer.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap -> {
				cap.syncAllClientData(serverPlayer);			
				if (cap.canShowMainMenu()) {
					showPlayerMainMenu(serverPlayer);
				}
				
				// Handle returning from The End after defeating the Ender Dragon
				// This event is triggered when using the End portal, not PlayerChangedDimensionEvent
				if (!event.isEndConquered()) {
					return;
				}
				
				MinepreggoMod.LOGGER.debug("Player {} returned from The End conquest, re-applying pregnancy effects", serverPlayer.getName().getString());			
				cap.getFemaleData().ifPresent(femaleData -> {
					if (femaleData.isPregnant() && femaleData.isPregnancyDataInitialized()) {
						var phase = femaleData.getPregnancyData().getCurrentPregnancyPhase();
						var effect = PlayerHelper.getPregnancyEffects(phase);
						if (!serverPlayer.hasEffect(effect)) {
							serverPlayer.addEffect(new MobEffectInstance(effect, -1, 0, false, false, true));
							MinepreggoMod.LOGGER.debug("Re-applied pregnancy effect {} to player {}", effect, serverPlayer.getName().getString());
						}
					}
				});
			});
		}
	}

	@SubscribeEvent
	public static void onPlayerChangedDimensionSync(PlayerEvent.PlayerChangedDimensionEvent event) {
		if (event.getEntity() instanceof ServerPlayer serverPlayer && !serverPlayer.level().isClientSide) {	
			serverPlayer.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap -> {
				cap.syncAllClientData(serverPlayer);			
				if (cap.canShowMainMenu()) {
					showPlayerMainMenu(serverPlayer);
				}	
			});
		}
	}
	
	@SubscribeEvent
	public static void clonePlayer(PlayerEvent.Clone event) {	
        final var originalPlayer = event.getOriginal();

        if (!event.isWasDeath()) {
            originalPlayer.revive();
        }

        final var newPlayer = event.getEntity();
        
        var origialPlayerData = originalPlayer.getCapability(MinepreggoCapabilities.PLAYER_DATA).resolve();
        var newPlayerData = newPlayer.getCapability(MinepreggoCapabilities.PLAYER_DATA).resolve();
        
        if (origialPlayerData.isEmpty() || newPlayerData.isEmpty()) return;

        if (event.isWasDeath()) {
            newPlayerData.get().resetToDefault();
        } else {
            newPlayerData.get().deserializeNBT(origialPlayerData.get().serializeNBT());
        }

        if (newPlayer instanceof ServerPlayer serverPlayer && !serverPlayer.level().isClientSide) {
            newPlayerData.ifPresent(c -> {
                c.syncAllClientData(serverPlayer);
                if (c.canShowMainMenu()) {
                    showPlayerMainMenu(serverPlayer);
                }
            });
        }     
	}
	
	@SubscribeEvent
	public static void onPlayerTracking(PlayerEvent.StartTracking event) {
	    if (event.getTarget() instanceof ServerPlayer trackedPlayer
	    		&& event.getEntity() instanceof ServerPlayer trackerPlayer) {    	
	    	
	    	trackedPlayer.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap -> {	    		
	            MinepreggoModPacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> trackerPlayer),
		                new SyncPlayerDataS2CPacket(trackedPlayer.getUUID(), cap.getGender(), cap.getSkinType()));
	           
	            cap.getFemaleData().ifPresent(femaleData -> {
		            MinepreggoModPacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> trackerPlayer),
			                new SyncFemalePlayerDataS2CPacket(trackedPlayer.getUUID(), femaleData.createClientData()));
              
			        MinepreggoModPacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> trackerPlayer),
			        		new SyncPregnancySystemS2CPacket(trackedPlayer.getUUID(), femaleData.getPregnancyData().createPlayerStateData()));
	            });
	            
	            // Sync active pregnancy effects
	            final var effects = trackedPlayer.getActiveEffects().stream()
	            		.filter(effect -> PregnancySystemHelper.isPregnancyEffect(effect.getEffect()))
	            		.toList();
	            
	            effects.forEach(effect -> 
	            	MinepreggoModPacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> trackerPlayer),
	            			new SyncMobEffectS2CPacket(trackedPlayer.getId(), effect))
	            );
	            
	            // Sync expired/removed pregnancy effects to ensure proper state synchronization
	            PregnancySystemHelper.syncExpiredMobEffectsToTracker(trackedPlayer, trackerPlayer);
	    	});
	    }
	}	

	
	// PREGNANCY EFFECTS IN PLAYER HANDLING START
	@SubscribeEvent
	public static void onEntityJump(LivingEvent.LivingJumpEvent event) {
		if (!(event.getEntity() instanceof ServerPlayer serverPlayer)) return;
		
		if (serverPlayer.level().isClientSide) {
			return;
		}
		
		serverPlayer.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap -> 
			cap.getFemaleData().ifPresent(femaleData -> {			
				if (femaleData.isPregnant() && femaleData.isPregnancyDataInitialized()) {
					var pregnancyData = femaleData.getPregnancyData();
					var phase = pregnancyData.getCurrentPregnancyPhase();			
					if (phase.compareTo(PregnancyPhase.P3) >= 0) {	
						pregnancyData.incrementNumOfJumps();			
						if (pregnancyData.getNumOfJumps() >= PlayerHelper.maxJumps(phase)) {
							serverPlayer.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 260, 0, false, true, true));
							pregnancyData.resetNumOfJumps();
						}					
					}
				}			
			})
		);
	}
	
	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {		
		if (event.phase != TickEvent.Phase.END) return;
	
		if (event.player.level().isClientSide) {		
			event.player.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap ->
				cap.getFemaleData().ifPresent(femaleData -> {			
					if (femaleData.isPregnant() && femaleData.isPregnancyDataInitialized()) {
						var system = femaleData.getPregnancyData();
						if (!system.bellyAnimationState.isStarted()) {
							system.bellyAnimationState.start(event.player.tickCount);
						}
					}
				})
			);			
			return;
		}
		
		if (!(event.player instanceof ServerPlayer serverPlayer)) return;
				
		serverPlayer.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap -> {			
			cap.getBreedableData().ifPresent(breedableData -> {
				if (breedableData.getSexualAppetite() < IBreedable.MAX_SEXUAL_APPETIVE) {				
					if (breedableData.getSexualAppetiteTimer() > 2400) {
						breedableData.setSexualAppetiteTimer(0);
						breedableData.incrementSexualAppetite(1);
						MinepreggoMod.LOGGER.debug("Player {} sexual appetite increased to {}", serverPlayer.getName().getString(), breedableData.getSexualAppetite());
					}	
					else {
						breedableData.incrementSexualAppetiteTimer();
					}		
				}	
			});
					
			if (cap.isFemale()) {
				cap.getFemaleData().ifPresent(femaleData -> evalualeFemalePlayerOnTick(serverPlayer, femaleData)) ;				
			}
			else {
				cap.getMaleData().ifPresent(maleData -> evalualeMalePlayerOnTick(serverPlayer, maleData));				
			}	
		});		
	}
	
	private static void evalualeMalePlayerOnTick(ServerPlayer serverPlayer, MalePlayerImpl maleData) {
		if (maleData.getFertilityRate() < IBreedable.MAX_FERTILITY_RATE) {						
			if (maleData.getFertilityRateTimer() > PregnancySystemHelper.TOTAL_TICKS_FERTILITY_RATE) {
				maleData.incrementFertilityRate(0.1F);
				maleData.resetFertilityRateTimer();
			}					
			else {
				maleData.incrementFertilityRateTimer();
			}
		}
		if (maleData.getFap() < IMalePlayer.MAX_FAP) {
			if (maleData.getFapTimer() > 2400) {
				maleData.incrementFap(1);
				maleData.resetFapTimer();
			}
			else {
				maleData.incrementFapTimer();
			}
		}
	}

	private static void evalualeFemalePlayerOnTick(ServerPlayer serverPlayer, FemalePlayerImpl femaleData) {
		if (femaleData.isPregnant()) {	
			if (!femaleData.isPregnancyDataInitialized()) {						
				
				if (femaleData.getPregnancyInitializerTimer() > MinepreggoModConfig.getTicksToStartPregnancy()) {		
					
					if (!PlayerHelper.tryToStartPregnancy(serverPlayer, false)) {					
						throw new IllegalStateException("Failed to initialize pregnancy system for player " + serverPlayer.getName().getString());	
					}
					
					femaleData.setPregnancyInitializerTimer(0);
					
					MinepreggoMod.LOGGER.debug("Player {} pregnancy system initialized", serverPlayer.getName().getString());
				}					
				else {
					
					MinepreggoMod.LOGGER.debug("Player {} pregnancy system not initialized, pregnancy initializer timer: {}", serverPlayer.getName().getString(), femaleData.getPregnancyInitializerTimer());
					
					femaleData.incrementPregnancyInitializerTimer();
					
					if (!serverPlayer.hasEffect(MobEffects.CONFUSION)) {
						if (femaleData.getDiscomfortCooldown() < 20) {
							femaleData.incrementDiscomfortCooldown();
						}	
						else {
							if(serverPlayer.getRandom().nextFloat() < 0.01F) {				
								serverPlayer.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 260, 0, false, true, true));			
							}	
							femaleData.resetDiscomfortCooldown();
						}
					}
				}
			}		
			else {	
							
				var pregnancyData = femaleData.getPregnancyData();
				var phase =	pregnancyData.getCurrentPregnancyPhase();

				if (phase.compareTo(PregnancyPhase.P3) >= 0 && serverPlayer.isSprinting()) {
					if (pregnancyData.getSprintingTimer() > PlayerHelper.sprintingTimer(phase)) {
						pregnancyData.resetSprintingTimer();
						serverPlayer.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 260, 0, false, true, true));
					}
					else {
						pregnancyData.incrementSprintingTimer();
					}
				}
				else if (phase.compareTo(PregnancyPhase.P4) >= 0 && serverPlayer.isShiftKeyDown()) {
					if (pregnancyData.getSneakingTimer() > PlayerHelper.sneakingTimer(phase)) {
						pregnancyData.resetSneakingTimer();
						serverPlayer.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 260, 0, false, true, true));
					}
					else {
						pregnancyData.incrementSneakingTimer();
					}
				}
				
				if (MinepreggoModConfig.isBellyColisionsForPlayersEnable() && phase.compareTo(PregnancyPhase.P4) >= 0) {
					BellyPartManager.getInstance().onServerTick(serverPlayer, () -> BellyPartFactory.createHumanoidBellyPart(serverPlayer, phase));
				}
			}	
		}
		else {
			if (femaleData.getPostPregnancyData().isEmpty() && femaleData.getFertilityRate() < IBreedable.MAX_FERTILITY_RATE) {						
				if (femaleData.getFertilityRateTimer() > PregnancySystemHelper.TOTAL_TICKS_FERTILITY_RATE) {
					femaleData.incrementFertilityRate(0.1F);
					femaleData.resetFertilityRateTimer();
				}					
				else {
					femaleData.incrementFertilityRateTimer();
				}
			}
			else if (!serverPlayer.hasEffect(MinepreggoModMobEffects.FERTILE.get()) && femaleData.getPostPregnancyData().isEmpty() && femaleData.getFertilityRate() >= IBreedable.MAX_FERTILITY_RATE) {
				serverPlayer.addEffect(new MobEffectInstance(MinepreggoModMobEffects.FERTILE.get(), 12000, 0, false, true, true));
			}
			else {
				evaluatePostPartum(serverPlayer);
			}
		}
	}
	
	private static void evaluatePostPartum(ServerPlayer serverPlayer) {			
		serverPlayer.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap -> 
			cap.getFemaleData().ifPresent(femaleData -> 				
				femaleData.getPostPregnancyData().ifPresent(post -> {				
					if (post.getPostPregnancyTimer() > MinepreggoModConfig.getTotalTicksOfPostPregnancyPhase()) {	
						if (!femaleData.tryRemovePostPregnancyPhase()) {
							MinepreggoMod.LOGGER.error("Failed to remove post pregnancy phase for player {}", serverPlayer.getName().getString());
						}
						else {
							serverPlayer.removeEffect(MinepreggoModMobEffects.DEPRESSION.get());
							serverPlayer.removeEffect(MinepreggoModMobEffects.MATERNITY.get());						
							serverPlayer.removeEffect(MinepreggoModMobEffects.LACTATION.get());						
							femaleData.sync(serverPlayer);	
							PregnancySystemHelper.removePostPregnancyNeft(serverPlayer);
							MinepreggoMod.LOGGER.debug("Player {} post pregnancy data removed", serverPlayer.getName().getString());
						}										
					}
					else {
						post.incrementPostPregnancyTimer();
					}
					
					if (post.getPostPregnancy() == PostPregnancy.PARTUM) {
						
						if (post.getPostPartumLactationTimer() > MinepreggoModConfig.getTotalTicksOfMaternityLactation()) {
							post.resetPostPartumLactationTimer();
							post.incrementPostPartumLactation();
							
							if (post.getPostPartumLactation() >= PregnancySystemHelper.ACTIVATE_MILKING_SYMPTOM
									&& !serverPlayer.hasEffect(MinepreggoModMobEffects.LACTATION.get())) {
								serverPlayer.addEffect(new MobEffectInstance(MinepreggoModMobEffects.LACTATION.get(), -1, 0, false, false, true));
							}
								
							femaleData.syncLactation(serverPlayer);
							MinepreggoMod.LOGGER.debug("Player {} lactation level increased to {}", serverPlayer.getName().getString(), post.getPostPartumLactation());
						}
						else {
							post.incrementPostPartumLactationTimer();
						}
					}						
				})
			)	
		);			
	}

	// Craving gratification handling
	@SubscribeEvent
	public static void onUseItemFinish(LivingEntityUseItemEvent.Finish event) {	
	
		if (!(event.getEntity() instanceof ServerPlayer player) || player.level().isClientSide) return;		
			
		player.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap -> 		
			cap.getFemaleData().ifPresent(femaleData -> {			
				final var mainHandItem = event.getItem().getItem();
	
				if (femaleData.isPregnant() && femaleData.isPregnancyDataInitialized()) {
					var pregnancyData = femaleData.getPregnancyData();
					
					if (pregnancyData.getCurrentPregnancyPhase().compareTo(PregnancyPhase.P1) >= 0 && pregnancyData.isValidCraving(mainHandItem)) {
						if (!(mainHandItem instanceof ICravingItem itemCraving)) {
							MinepreggoMod.LOGGER.debug("Item used is not an IItemCraving: {}", mainHandItem);
							return;
						}
						
						int gratification = itemCraving.getSpeciesType() != Species.HUMAN ? (int) (itemCraving.getGratification() * itemCraving.getPenalty()) : itemCraving.getGratification();
						MinepreggoMod.LOGGER.debug("Player {} satisfied craving with item: {} by {}", player.getName().getString(), mainHandItem, gratification);
						pregnancyData.decrementCraving(gratification);
						pregnancyData.syncEffect(player);
						
						if (PregnancyPain.isLaborPain(femaleData.getPregnancyData().getPregnancyPain())) {
							/*
							 * Force re-evaluation of pregnancy symptoms after craving gratification, 
							 * the currect pregnancy system does not evaluate symptoms if player is in birth, prebirt, waterbreaking, etc,
							 * so this method ensures that craving symptom is removed when its condition is no longer met.
							 * */
							femaleData.evaluatePregnancySymptom(player);
						}
					}
				}
			})
		);		
	}

	// Milking handling
	@SubscribeEvent
	public static void onRightClickItem(PlayerInteractEvent.RightClickItem event) {
		if (!(event.getEntity() instanceof ServerPlayer serverPlayer)) return;
		
		if (serverPlayer.level().isClientSide) {
			return;
		}
		
		serverPlayer.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap -> {	
			var itemstack = event.getItemStack();
			if (cap.getGender() == Gender.FEMALE) {
				cap.getFemaleData().ifPresent(femaleData -> {
					if (femaleData.isPregnant() && femaleData.isPregnancyDataInitialized()) {	
						var pregnancySystem = femaleData.getPregnancyData();	
						var pain = pregnancySystem.getPregnancyPain();
						if (pain != null && (pain == PregnancyPain.BIRTH || pain == PregnancyPain.MISCARRIAGE)) {
							event.setCanceled(true);
							return;
						}
						
						if (itemstack.is(Items.GLASS_BOTTLE) 
								&& pregnancySystem.getCurrentPregnancyPhase().compareTo(PregnancyPhase.P2) >= 0
								&& pregnancySystem.getMilking() >= PregnancySystemHelper.MILKING_VALUE) {
							
							PlayerHelper.playSoundNearTo(serverPlayer, SoundEvents.COW_MILK, 0.7f);
							
							pregnancySystem.decrementMilking(PregnancySystemHelper.MILKING_VALUE);
							pregnancySystem.syncEffect(serverPlayer);
							
							itemstack.shrink(1);
							
				            if (itemstack.isEmpty()) {
				            	serverPlayer.setItemInHand(event.getHand(), ItemStack.EMPTY);
				            }        
				            serverPlayer.getInventory().setChanged();
												
							ItemHandlerHelper.giveItemToPlayer(serverPlayer, new ItemStack(MinepreggoModItems.HUMAN_BREAST_MILK_BOTTLE.get()));								
							MinepreggoMod.LOGGER.debug("Player {} milked. Current milking value: {}", serverPlayer.getName().getString(), pregnancySystem.getMilking());	
						
							if (PregnancyPain.isLaborPain(femaleData.getPregnancyData().getPregnancyPain())) {
								/*
								 * Force re-evaluation of pregnancy symptoms after craving gratification, 
								 * the currect pregnancy system does not evaluate symptoms if player is in birth, prebirt, waterbreaking, etc,
								 * so this method ensures that craving symptom is removed when its condition is no longer met.
								 * */
								femaleData.evaluatePregnancySymptom(serverPlayer);	
							}
						}
					}
					else  {
						femaleData.getPostPregnancyData().ifPresent(post -> {
							if (post.getPostPregnancy() == PostPregnancy.PARTUM
									&& itemstack.is(Items.GLASS_BOTTLE)
									&& post.getPostPartumLactation() >= PregnancySystemHelper.MILKING_VALUE) {						
								PlayerHelper.playSoundNearTo(serverPlayer, SoundEvents.COW_MILK, 0.7f);					
								itemstack.shrink(1);	
					            if (itemstack.isEmpty()) {
					            	serverPlayer.setItemInHand(event.getHand(), ItemStack.EMPTY);
					            }        
					            serverPlayer.getInventory().setChanged();	
								ItemHandlerHelper.giveItemToPlayer(serverPlayer, new ItemStack(MinepreggoModItems.HUMAN_BREAST_MILK_BOTTLE.get()));								
								post.decrementPostPartumLactation(PregnancySystemHelper.MILKING_VALUE);
									
								if (post.getPostPartumLactation() <= PregnancySystemHelper.DESACTIVATE_MILKING_SYMPTOM) {
									serverPlayer.removeEffect(MinepreggoModMobEffects.LACTATION.get());
								}
										
								femaleData.syncLactation(serverPlayer);
								MinepreggoMod.LOGGER.debug("Player {} milked. Current milking value: {}", serverPlayer.getName().getString(), post.getPostPartumLactation());								
							}
						});
					}
				});
			}
			else if (cap.getGender() == Gender.MALE && itemstack.is(MinepreggoModItems.SPECIMEN_TUBE.get())) {		
				cap.getMaleData().ifPresent(maleData -> {	
					if (maleData.canFap()) {
						
						PlayerHelper.playSoundNearTo(serverPlayer, SoundEvents.BOTTLE_FILL, 0.7f);
						
						itemstack.shrink(1);					
			            if (itemstack.isEmpty()) {
			            	serverPlayer.setItemInHand(event.getHand(), ItemStack.EMPTY);
			            }  	
			            serverPlayer.getInventory().setChanged();
			           
			            maleData.decrementFap(5);
							
						// Cum sample should not be empty here, as canFap() checks for enough fap points and getGender() == MALE guarantees that CumSpecimenTubeItem.createCumSpecimen will succeed.
						var cum = CumSpecimenTubeItem.createCumSpecimen(serverPlayer).orElse(new ItemStack(MinepreggoModItems.CUM_SPECIMEN_TUBE.get()));		
						
						ItemHandlerHelper.giveItemToPlayer(serverPlayer, cum);					
					}
					else {
						MessageHelper.sendTo(serverPlayer, Component.translatable("chat.minepreggo.player.not_enough_fap.message"), true);
					}
				});		
			}
		});	
	}
	// PREGNANCY EFFECTS IN PLAYER HANDLING END	
	
	@SubscribeEvent
	public static void onHurt(LivingHurtEvent event) {
		if (!(event.getEntity() instanceof ServerPlayer serverPlayer)) return;	
			
		serverPlayer.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap -> 
			cap.getFemaleData().ifPresent(femaleData -> {		
				if (femaleData.isPregnant() && femaleData.isPregnancyDataInitialized()) {
					var pregnancySystem = femaleData.getPregnancyData();
					if (pregnancySystem.getPregnancyPain() != PregnancyPain.MISCARRIAGE) {
											
						PregnancySystemHelper.calculatePregnancyDamage(serverPlayer, pregnancySystem.getCurrentPregnancyPhase(), event.getSource()).ifPresent(damage -> {
														
							pregnancySystem.reducePregnancyHealth(damage);
							final var health = pregnancySystem.getPregnancyHealth();
							if (health <= 0) {
								PlayerHelper.playSoundNearTo(serverPlayer, MinepreggoModSounds.PLAYER_MISCARRIAGE.get(), 0.7f);
								MessageHelper.sendTo(serverPlayer, Component.translatable("chat.minepreggo.player.miscarriage.message.start", serverPlayer.getDisplayName().getString()));
								pregnancySystem.resetPregnancyPainTimer();
								pregnancySystem.setPregnancyPain(PregnancyPain.MISCARRIAGE);
								serverPlayer.addEffect(new MobEffectInstance(MinepreggoModMobEffects.MISCARRIAGE.get(), PregnancySystemHelper.TOTAL_TICKS_MISCARRIAGE, 0, false, false, true));
								pregnancySystem.syncState(serverPlayer);
								MinepreggoMod.LOGGER.debug("Miscarriage just started");										
							}
							else if (health < 40 && serverPlayer.getRandom().nextBoolean()) {
								MessageHelper.sendTo(serverPlayer, Component.translatable("chat.minepreggo.player.miscarriage.message.warning"), true);
							}	
						});
					}
				}	
				else if(!femaleData.isPregnant() 
						&& femaleData.getPostPregnancyData().isEmpty()
						&& serverPlayer.hasEffect(MinepreggoModMobEffects.FERTILE.get())
						&& event.getSource().getEntity() instanceof Mob source
						&& serverPlayer.getRandom().nextFloat() < 0.65f) {
					
					Species species = null;
					boolean flag = false;
					
					if (source instanceof Zombie && !source.isBaby()) {
						species = Species.ZOMBIE;
						flag = femaleData.tryImpregnateByHurting(species);
					}
					else if (source instanceof Creeper) {
						species = Species.CREEPER;
						flag = femaleData.tryImpregnateByHurting(species);
					}
					else if (source instanceof EnderMan) {
						species = Species.ENDER;
						flag = femaleData.tryImpregnateByHurting(species);
					}		
					
					if (flag && !PlayerHelper.tryStartPregnancyByMobAttack(serverPlayer, species)) {
						MinepreggoMod.LOGGER.error("Failed to start pregnancy by mob attack for player {}", serverPlayer.getName().getString());
					}				
				}
			})					
		);
	}
	
	// TODO: It does not completely prevent a pregnant player from using valid armor, Search a solution using Mixin, setItemSlot from LivingEntity does not work, it crashes the game.
    @SubscribeEvent
    public static void onEquipmentChange(LivingEquipmentChangeEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;
      
        if (player.level().isClientSide) {
        	return;
        }

        player.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap -> 
        	cap.getFemaleData().ifPresent(femaleData -> {    
                ItemStack newArmor = event.getTo();
    	        if (newArmor.getItem() instanceof ArmorItem) {	
	               	var item = newArmor.getItem();
	                var slot = event.getSlot(); 
	                     
        			if (femaleData.isPregnant() && femaleData.isPregnancyDataInitialized()) {
        				if (slot == EquipmentSlot.CHEST) {
        					if (!PregnancySystemHelper.canUseChestplate(item, femaleData.getPregnancyData().getCurrentPregnancyPhase())) {               					
            					MessageHelper.warnFittedArmor(player, femaleData.getPregnancyData().getCurrentPregnancyPhase());
            					removeArmorAndDamagePregnantPlayer(player, slot, newArmor);
        					}
        					else if (!PlayerHelper.canUseChestPlateInLactation(player, item)) {
                       			MessageHelper.sendTo(player, Component.translatable("chat.minepreggo.player.armor.message.lactating"), true);
                       			removeArmorAndDamagePregnantPlayer(player, slot, newArmor);
        					}
        				}
        				else if (slot == EquipmentSlot.LEGS && !PregnancySystemHelper.canUseLegging(item, femaleData.getPregnancyData().getCurrentPregnancyPhase())) {
                   			MessageHelper.sendTo(player, Component.translatable("chat.minepreggo.player.armor.message.leggings_does_not_fit"), true);
                   			removeArmorAndDamagePregnantPlayer(player, slot, newArmor);
        				}
	               	}		
        			else if (slot == EquipmentSlot.CHEST && !PlayerHelper.canUseChestPlateInLactation(player, item)) {
               			MessageHelper.sendTo(player, Component.translatable("chat.minepreggo.player.armor.message.lactating"), true);
               			removeArmorAndDamagePregnantPlayer(player, slot, newArmor);
        			}		
    	        }
        	})
        );
    }
  
	@SubscribeEvent
	public static void onRightClickEntity(PlayerInteractEvent.EntityInteract event) {	
		if (event.getHand() != InteractionHand.MAIN_HAND || event.getLevel().isClientSide) return;

		if (event.getEntity() instanceof ServerPlayer sourcePlayer) {	
			Optional<Boolean> isInLabor = sourcePlayer.getCapability(MinepreggoCapabilities.PLAYER_DATA).map(cap -> 
				cap.getFemaleData().map(femaleData -> 
					femaleData.isPregnant()
					&& femaleData.isPregnancyDataInitialized() 
					&& PregnancyPain.isLaborPain(femaleData.getPregnancyData().getPregnancyPain())
				)
			).orElse(Optional.empty());
			
			if (isInLabor.isPresent() && isInLabor.get().booleanValue()) {
				MessageHelper.sendTo(sourcePlayer, Component.translatable("chat.minepreggo.player.pregnancy.message.cannot_in_labor"), true);
		        event.setCancellationResult(InteractionResult.SUCCESS);
		        sourcePlayer.swing(InteractionHand.MAIN_HAND);
		        event.setCanceled(true);
			}				
			else if (event.getTarget() instanceof ServerPlayer targetPlayer) {
				if (PregnancySystemHelper.tryRubBelly(sourcePlayer, targetPlayer, event.getLevel())) {
					MinepreggoMod.LOGGER.debug("Player {} slapped the pregnant belly of player {}", sourcePlayer.getName().getString(), targetPlayer.getName().getString());
			        event.setCancellationResult(InteractionResult.SUCCESS);
			        sourcePlayer.swing(InteractionHand.MAIN_HAND);
			        event.setCanceled(true);
				}
				else if (PregnancySystemHelper.hasEnoughBedsForBreeding(targetPlayer, 1, 12) && PregnancySystemHelper.canFuck(sourcePlayer, targetPlayer)) {		        		        				
					MessageHelper.sendTo(sourcePlayer, Component.translatable("chat.minepreggo.player.sex.request_sent", targetPlayer.getDisplayName().getString()), true);
					RequestSexP2PMenu.create(targetPlayer, sourcePlayer);
			        event.setCancellationResult(InteractionResult.SUCCESS);
			        sourcePlayer.swing(InteractionHand.MAIN_HAND);
			        event.setCanceled(true);
				}
			}
		}
	}
	
    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {
    	var entity = event.getEntity();
    	
    	if (entity.level().isClientSide) {
			return;
		}
    	
    	if (entity instanceof ServerPlayer player) {
        	player.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap ->
        		cap.getFemaleData().ifPresent(femaleData -> {
        			if (femaleData.isPregnant() && femaleData.isPregnancyDataInitialized()) {
                    	ServerPlayerAnimationManager.getInstance().stopAnimation(player);             	
                    	PlayerHelper.removeJigglePhysics(player);
                    	boolean explode = false;
                    	Position deathPos = entity.position();
                    	
            			if (player.level() instanceof ServerLevel serverLevel) {	
                        	if (event.getSource().is(MinepreggoModDamageSources.BELLY_BURST)) {
                        		PregnancySystemHelper.deathByBellyBurst(entity, serverLevel);
                        		PlayerHelper.playSoundNearTo(player, MinepreggoModSounds.PREGNANT_PREGGO_MOB_DEATH.get(), 0.8f);
                        		explode = true;
                        	}

            				var pregnancySystem = femaleData.getPregnancyData();
                			var phase = pregnancySystem.getCurrentPregnancyPhase();  		
            				if (player.hasEffect(MinepreggoModMobEffects.FULL_OF_CREEPERS.get()) && phase.compareTo(PregnancyPhase.P3) >= 0 && !explode) {
            					serverLevel.explode(player, deathPos.x(), deathPos.y(), deathPos.z(), PlayerHelper.calculateExplosionLevelByPregnancyPhase(phase), ExplosionInteraction.MOB);
            				}
                			if (phase.compareTo(PregnancyPhase.P3) >= 0) {
                    			float alive = PregnancySystemHelper.getSpawnProbabilityBasedPregnancy(pregnancySystem, 0.6F, 0.3F, 0.1F, 0.3F);
                    			PregnancySystemHelper.spawnBabiesOrFetuses(serverLevel, deathPos, alive, 0.35f, pregnancySystem.getWomb(), player.getRandom());
                			}
                			else {
                				PregnancySystemHelper.spawnFetuses(serverLevel, deathPos, 0.85f, pregnancySystem.getWomb(), player.getRandom());
                			}
            			}
        			}
        		})
        	);
        }
    }   
	
    @SubscribeEvent
    public static void onItemPickup(ItemPickupEvent event) {
        if (event.getEntity().level().isClientSide) {
        	return;
        }
 
        ItemStack stack = event.getStack();
        if (Villager.FOOD_POINTS.containsKey(stack.getItem())) {
            PlayerHelper.removeAnyFemalePlayerIdTag(stack);
        }
    }
	
	private static void showPlayerMainMenu(ServerPlayer serverPlayer) {
		BlockPos bpos = BlockPos.containing(serverPlayer.getX(), serverPlayer.getY(), serverPlayer.getZ());	
		NetworkHooks.openScreen(serverPlayer, new MenuProvider() {
			@Override
			public Component getDisplayName() {
				return Component.literal("PlayerMainGUI");
			}
			@Override
			public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
				return new PlayerJoinsWorldMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(bpos));
			}
		}, bpos);
	}
	
	private static void removeArmorAndDamagePregnantPlayer(ServerPlayer player, EquipmentSlot slot, ItemStack newArmor) {
    	player.setItemSlot(slot, ItemStack.EMPTY);   		
        if (!player.getInventory().add(newArmor)) {
            player.drop(newArmor, false);
        }
        player.hurt(new DamageSource(player.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.GENERIC)), 1);
	}
	
    @SubscribeEvent
	public static void onMountEntity(EntityMountEvent event) {		
		// TODO: Pregnant preggomobs are not being handled here, only players.
		
		if (!event.getLevel().isClientSide
				&& event.getEntityBeingMounted() != null
				&& event.getEntityMounting() instanceof ServerPlayer serverPlayer) {	

			Optional<Boolean> canRiding = serverPlayer.getCapability(MinepreggoCapabilities.PLAYER_DATA).map(cap -> 
				cap.getFemaleData().map(femaleData -> {
					if (femaleData.isPregnant() && femaleData.isPregnancyDataInitialized()) {
						return PregnancySystemHelper.canMountEntity(femaleData.getPregnancyData().getCurrentPregnancyPhase());
					}
					return false;
				})
			).orElse(Optional.empty());
			
			if (canRiding.isPresent() && !canRiding.get().booleanValue()) {
				var message = serverPlayer.getRandom().nextBoolean() 
						? Component.translatable("chat.minepreggo.player.pregnancy.message.cannot_ride.message.1") 
						: Component.translatable("chat.minepreggo.player.pregnancy.message.cannot_ride.message.2");
				MessageHelper.sendTo(serverPlayer, message, true);
		        event.setCanceled(true);
			}
		}
	}
    
    @SubscribeEvent
    public static void onFall(LivingFallEvent event) {
    	if (!(event.getEntity() instanceof ServerPlayer serverPlayer)) return;	
		
    	serverPlayer.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap ->
    		cap.getFemaleData().ifPresent(femaleData -> {
    			if (femaleData.isPregnant() && femaleData.isPregnancyDataInitialized()) {
    				var phase = femaleData.getPregnancyData().getCurrentPregnancyPhase();	
    				if (phase.compareTo(PregnancyPhase.P3) >= 0) {
    			        event.setDamageMultiplier(event.getDamageMultiplier() * PregnancySystemHelper.calculateExtraFallDamageMultiplier(phase));
    				}
    			}
    		})
    	);
    } 
}