package dev.dixmk.minepreggo.world.pregnancy;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.annotation.Nonnegative;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.network.chat.MessageHelper;
import dev.dixmk.minepreggo.network.packet.s2c.RenderSexOverlayS2CPacket;
import dev.dixmk.minepreggo.network.packet.s2c.SexCinematicControlP2MS2CPacket;
import dev.dixmk.minepreggo.network.packet.s2c.SexCinematicControlP2PS2CPacket;
import dev.dixmk.minepreggo.server.ServerCinematicManager;
import dev.dixmk.minepreggo.server.ServerParticleHelper;
import dev.dixmk.minepreggo.server.ServerTaskQueueManager;
import dev.dixmk.minepreggo.world.entity.LivingEntityHelper;
import dev.dixmk.minepreggo.world.entity.player.PlayerHelper;
import dev.dixmk.minepreggo.world.entity.preggo.ITamablePreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.ITamablePregnantPreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobFace;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.gossip.GossipType;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.network.PacketDistributor;

public class SexHelper {
	
	private SexHelper() {}

	public static boolean canOwnerActivateSexEvent(Level level, ServerPlayer owner, PreggoMob target) {
		if (!(target instanceof ITamablePreggoMob<?> tamableTarget)) {
			return false;
		}
		
		Optional<Boolean> result = owner.getCapability(MinepreggoCapabilities.PLAYER_DATA).map(cap -> cap.getBreedableData().map(IBreedable::canFuck)).orElse(Optional.empty());
		
		if (LivingEntityHelper.areHostileMobsNearby(level, owner, target, 16D)) {
			MessageHelper.sendTo(owner, Component.translatable("chat.minepreggo.sex.message.mobs"));
			return false;
		}
		else if (!tamableTarget.getGenderedData().canFuck()) {
			MessageHelper.sendTo(owner, Component.translatable("chat.minepreggo.preggo_mob.sex.message.tiring", target.getSimpleNameOrCustom()));
			return false;
		}
		else if (result.isPresent() && !result.get().booleanValue()) {
			MessageHelper.sendTo(owner, Component.translatable("chat.minepreggo.player.sex.message.tiring"));
			return false;
		}
		else if (owner.distanceToSqr(target) >= 32) {
			MessageHelper.sendTo(owner, Component.translatable("chat.minepreggo.sex.message.distance", target.getSimpleNameOrCustom()));
			return false;
		}
		else if (!hasEnoughBedsForBreeding(target, 1, 8)) {
			MessageHelper.sendTo(owner, Component.translatable("chat.minepreggo.sex.message.enough_beds"));
			return false;
		}
		else if (ServerCinematicManager.getInstance().isInCinematic(target)) {
			MessageHelper.sendTo(owner, Component.translatable("chat.minepreggo.sex.message.active_cinematic", target.getSimpleNameOrCustom()));
			return false;
		}
		
		owner.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap -> {
			if (cap.getGender() == tamableTarget.getGenderedData().getGender()) {
				MessageHelper.sendTo(owner, Component.translatable("chat.minepreggo.preggo_mob.message.same_sex", target.getSimpleNameOrCustom()));
			}
		});
		
		return true;
	}
	
	public static boolean canActivateSexEvent(Level level, ServerPlayer owner, PreggoMob target) {		
		if (!(target instanceof ITamablePreggoMob<?>)) {
			return false;
		}
		
		if (LivingEntityHelper.areHostileMobsNearby(level, owner, target, 16D)) {
			MessageHelper.sendTo(owner, Component.translatable("chat.minepreggo.sex.message.mobs"));
		}
		else if (owner.distanceToSqr(target) >= 32) {
			MessageHelper.sendTo(owner, Component.translatable("chat.minepreggo.sex.message.distance", target.getSimpleNameOrCustom()));
		}
		else if (ServerCinematicManager.getInstance().isInCinematic(target)) {
			MessageHelper.sendTo(owner, Component.translatable("chat.minepreggo.sex.message.active_cinematic", target.getSimpleNameOrCustom()));
		}
					
		if (!hasEnoughBedsForBreeding(target, 1, 8)) {
			MessageHelper.sendTo(owner, Component.translatable("chat.minepreggo.pregnant.preggo_mob.message.without_beds", target.getSimpleNameOrCustom(), owner.getDisplayName().getString()));
		}
		else {
			return true;
		}

		return false;
	}	
	
	public static boolean hasEnoughBedsForBreeding(LivingEntity source, @Nonnegative int minBedsRequired, @Nonnegative int range) {
	    Level level = source.level();
	    AABB searchArea = new AABB(source.blockPosition()).inflate(range);
	    Set<BlockPos> checkedBeds = new HashSet<>();
	    int availableBeds = 0;
 
        for (final var pos : BlockPos.betweenClosed(
        	    (int)searchArea.minX, (int)searchArea.minY, (int)searchArea.minZ,
        	    (int)searchArea.maxX, (int)searchArea.maxY, (int)searchArea.maxZ)) {   	        
	    	
        	if (checkedBeds.contains(pos)) {
                continue;
            }	
        	
	    	BlockState state = level.getBlockState(pos);      
	        if (state.getBlock() instanceof BedBlock && !state.getValue(BedBlock.OCCUPIED).booleanValue()) {
	        	availableBeds++;
                
                // Mark both parts as checked
                checkedBeds.add(pos);
                checkedBeds.add(pos.relative(BedBlock.getConnectedDirection(state)));               	
	        }
	    }	        

	    return availableBeds >= minBedsRequired;
	}
	
    public static boolean canFuck(ServerPlayer sourcePlayer, ServerPlayer targetPlayer) {		
    	Optional<Boolean> sourceCanFuck = sourcePlayer.getCapability(MinepreggoCapabilities.PLAYER_DATA)
		    							.resolve()
		    							.flatMap(cap -> cap.getBreedableData())
		    							.map(b -> b.canFuck());

    	Optional<Boolean> targetCanFuck = targetPlayer.getCapability(MinepreggoCapabilities.PLAYER_DATA)
										.resolve()
										.flatMap(cap -> cap.getBreedableData())
										.map(b -> b.canFuck());
    	
    	if (sourceCanFuck.isPresent() && targetCanFuck.isPresent()) {
			if (!sourceCanFuck.get().booleanValue()) {
				MessageHelper.sendTo(sourcePlayer, Component.translatable("chat.minepreggo.player.sex.message.tiring"), true);
				MessageHelper.sendTo(targetPlayer, Component.translatable("chat.minepreggo.preggo_mob.sex.message.tiring", sourcePlayer.getGameProfile().getName()), true);
				return false;
			}
			else if (!targetCanFuck.get().booleanValue()) {
				MessageHelper.sendTo(sourcePlayer, Component.translatable("chat.minepreggo.preggo_mob.sex.message.tiring", targetPlayer.getGameProfile().getName()), true);
				MessageHelper.sendTo(targetPlayer, Component.translatable("chat.minepreggo.player.sex.message.tiring"), true);
				return false;
			}
			return true;
		}
		
		MinepreggoMod.LOGGER.error("Failed to retrieve breedable data for players {} and {}", sourcePlayer.getName().getString(), targetPlayer.getName().getString());
		return false;
    }
    
    public static<E extends PreggoMob & ITamablePreggoMob<?>> boolean canFuck(ServerPlayer sourcePlayer, E preggoMob) {
    	Optional<Boolean> sourceCanFuck = sourcePlayer.getCapability(MinepreggoCapabilities.PLAYER_DATA)
				.resolve()
				.flatMap(cap -> cap.getBreedableData())
				.map(b -> b.canFuck());
    	
    	
    	if (sourceCanFuck.isPresent()) {
			if (!sourceCanFuck.get().booleanValue()) {
				MessageHelper.sendTo(sourcePlayer, Component.translatable("chat.minepreggo.player.sex.message.tiring"), true);
				return false;
			}
			else if (!preggoMob.getGenderedData().canFuck()) {
				MessageHelper.sendTo(sourcePlayer, Component.translatable("chat.minepreggo.preggo_mob.sex.message.tiring", preggoMob.getSimpleNameOrCustom()), true);
				return false;
			}
			return true;
		}
		
		MinepreggoMod.LOGGER.error("Failed to retrieve breedable data for player {}", sourcePlayer.getName().getString());
		return false;
    }
    
    public static<E extends PreggoMob & ITamablePreggoMob<IFemaleEntity>> boolean initSex(Level level, ServerPlayer sourcePlayer, E targetMob) {
    	if (!canOwnerActivateSexEvent(level, sourcePlayer, targetMob)) {
			return false;
		}

		ServerCinematicManager.getInstance().start(
				sourcePlayer,
				targetMob,
				() -> {
					ServerParticleHelper.spawnRandomlyFromServer(targetMob, ParticleTypes.HEART);
					targetMob.getTamableData().setFaceState(PreggoMobFace.BLUSHED);  
				}, () -> {
					targetMob.getTamableData().cleanFaceState();
					if (!(targetMob instanceof ITamablePregnantPreggoMob)) {
						PreggoMobHelper.initPregnancyBySex(targetMob, sourcePlayer);
					}	
					else if (targetMob instanceof ITamablePregnantPreggoMob pregnancyHandler && pregnancyHandler.getPregnancyData().getCurrentPregnancyPhase().compareTo(PregnancyPhase.P4) >= 0) {
						var pregnancyData =	pregnancyHandler.getPregnancyData();
						pregnancyData.getSyncedPregnancySymptoms().removePregnancySymptom(PregnancySymptom.HORNY);
						pregnancyData.setHorny(0);		
						pregnancyData.setHornyTimer(0);
					}
					
					targetMob.getGenderedData().reduceSexualAppetite(5);
					sourcePlayer.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap -> cap.getBreedableData().ifPresent(breedableData -> breedableData.reduceSexualAppetite(5)));					
					PlayerHelper.removeHorny(sourcePlayer);
				});
		
		targetMob.setCinematicOwner(sourcePlayer);
		targetMob.setCinematicEndTime(sourcePlayer.level().getGameTime() + 120 * 2 + 60);
  
        sendControlPacket(sourcePlayer, targetMob, true);
		sendOverlayPacket(sourcePlayer, 120, 60);
		
		return true;
    }
    
    public static void initSex(ServerPlayer femalePlayer, Villager villager) {	
		Runnable start = () -> {
			villager.setTradingPlayer(femalePlayer);
			ServerParticleHelper.spawnRandomlyFromServer(femalePlayer, ParticleTypes.HEART);
			ServerParticleHelper.spawnRandomlyFromServer(villager, ParticleTypes.HEART);
		};
		
		Runnable end = () -> {
			villager.setTradingPlayer(null);
			femalePlayer.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap -> 
				cap.getFemaleData().ifPresent(femaleData -> {		
					if (!femaleData.isPregnant()) {
						PlayerHelper.tryStartPregnancyBySex(femalePlayer, villager);
					}	
					else {
						PlayerHelper.removeHorny(femalePlayer);
					}
					
					femaleData.resetFertilityRate();
					villager.eatAndDigestFood();
					villager.eatAndDigestFood();
					villager.getCapability(MinepreggoCapabilities.VILLAGER_DATA).ifPresent(capVillager -> {
						capVillager.setMotherPlayer(femalePlayer.getUUID());
						villager.getGossips().add(femalePlayer.getUUID(), GossipType.MAJOR_POSITIVE, 15);
					});	
				})
			);	
		};
				
		var manager = ServerTaskQueueManager.getInstance();
		
		manager.queueTask(20, start);		
		manager.queueTask(40, start);
		
		ServerCinematicManager.getInstance().start(femalePlayer, villager, start, end);
		
		int overlayTicks = 120;
		int overlayPauseTicks = 20;
			                  
        sendOverlayPacket(femalePlayer, overlayTicks, overlayPauseTicks);
        sendControlPacket(femalePlayer, villager, true);
                
        manager.queueTask(overlayTicks * 2 + overlayPauseTicks, () -> {
			ServerCinematicManager.getInstance().end(femalePlayer);
			sendControlPacket(femalePlayer, villager, false);
		});		
    }
      
    public static boolean canStartSex(ServerPlayer sourcePlayer, ServerPlayer targetPlayer, double minDistanceSqr) {
        return canFuck(sourcePlayer, targetPlayer) 
            && sourcePlayer.distanceToSqr(targetPlayer) <= minDistanceSqr 
            && !ServerCinematicManager.getInstance().isInCinematic(targetPlayer);
    }
    
    public static<E extends PreggoMob & ITamablePregnantPreggoMob> void acceptSexRequest(ServerPlayer target, E source) {
		var manager =  ServerTaskQueueManager.getInstance();
		
		manager.queueTask(20, () -> ServerParticleHelper.spawnRandomlyFromServer(target, ParticleTypes.HEART));
		manager.queueTask(40, () -> ServerParticleHelper.spawnRandomlyFromServer(target, ParticleTypes.HEART));
		manager.queueTask(60, () -> ServerParticleHelper.spawnRandomlyFromServer(target, ParticleTypes.HEART));			
			
		ServerCinematicManager.getInstance().start(
				target,
				source,
				() -> {
					ServerParticleHelper.spawnRandomlyFromServer(source, ParticleTypes.HEART);
					source.getTamableData().setFaceState(PreggoMobFace.BLUSHED);  
				}, () -> {
					source.getTamableData().cleanFaceState();
					var pregnancyData = source.getPregnancyData();
					pregnancyData.setHorny(0);
					pregnancyData.getSyncedPregnancySymptoms().removePregnancySymptom(PregnancySymptom.HORNY);
					pregnancyData.setHornyTimer(0);
					source.getGenderedData().setSexualAppetite(0);
					PlayerHelper.removeHorny(target);
				});
		
	 	final int totalOverlayTicks = 120;
	 	final int totalPauseTicks = 60;
		
		source.setCinematicOwner(target);
		source.setCinematicEndTime(target.level().getGameTime() + totalOverlayTicks * 2 + totalPauseTicks);
  
        sendControlPacket(target, source, true);
		sendOverlayPacket(target, totalOverlayTicks, totalPauseTicks);
	}
	
    public static void rejectSexRequest(ServerPlayer target, PreggoMob source) {
		MessageHelper.sendTo(target, Component.translatable("chat.minepreggo.pregnant.preggo_mob.message.rejection_by_its_owner", source.getSimpleNameOrCustom()));
		ServerParticleHelper.spawnRandomlyFromServer(target, ParticleTypes.SMOKE);
	}
    
    public static void acceptSexRequest(ServerPlayer source, ServerPlayer target) {
		Runnable task = () -> {
			ServerParticleHelper.spawnRandomlyFromServer(source, ParticleTypes.HEART);
			ServerParticleHelper.spawnRandomlyFromServer(target, ParticleTypes.HEART);
		};
			
		Runnable end = () -> {
			source.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(sourceCap ->
				target.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(targetCap -> {
					if (sourceCap.getGender() == Gender.FEMALE) {
						if (!PlayerHelper.tryStartPregnancyBySex(source, target)) {
							MinepreggoMod.LOGGER.warn("Failed to impregnate female player {} by player {}", source.getUUID(), target.getUUID());
						}
						PlayerHelper.removeHorny(source);
					}
					else if (targetCap.getGender() == Gender.FEMALE) {
						if (!PlayerHelper.tryStartPregnancyBySex(target, source)) {
							MinepreggoMod.LOGGER.warn("Failed to impregnate female player {} by player {}", target.getUUID(), source.getUUID());
						}
						PlayerHelper.removeHorny(target);
					}
					
					sourceCap.getBreedableData().ifPresent(breedableData -> {
						breedableData.setFertilityRate(0);
						breedableData.reduceSexualAppetite(5);
					});
						
					targetCap.getBreedableData().ifPresent(breedableData -> {
						breedableData.setFertilityRate(0);
						breedableData.reduceSexualAppetite(5);
					});			
				})
			);
		};
		
		var manager = ServerTaskQueueManager.getInstance();
			
		manager.queueTask(20, task);		
		manager.queueTask(40, task);
		
		ServerCinematicManager.getInstance().start(target, source, task, end);
	
		int overlayTicks = 120;
		int overlayPauseTicks = 60;
			
        sendControlPacket(source, true);
		sendControlPacket(target, true);

		sendOverlayPacket(source, overlayTicks, overlayPauseTicks);
		sendOverlayPacket(target, overlayTicks, overlayPauseTicks);
        
        manager.queueTask(overlayTicks * 2 + overlayPauseTicks, () -> {
			ServerCinematicManager.getInstance().end(source);
			ServerCinematicManager.getInstance().end(target);

	        sendControlPacket(source, false);
			sendControlPacket(target, false);
		});		
	}
	
    public static void rejectSexRequest(ServerPlayer source, ServerPlayer target) {
		MessageHelper.sendTo(source, Component.translatable("chat.minepreggo.player.sex.request_reject", target.getName().getString()), true);
		ServerParticleHelper.spawnRandomlyFromServer(target, ParticleTypes.SMOKE);			
	}
    
    private static<E extends PreggoMob & ITamablePreggoMob<IFemaleEntity>> void sendControlPacket(ServerPlayer player, E preggoMob, boolean start) {
    	MinepreggoModPacketHandler.INSTANCE.send(
            PacketDistributor.PLAYER.with(() -> player),
            new SexCinematicControlP2MS2CPacket(start, preggoMob.getId())
        );
    }
    
    private static void sendControlPacket(ServerPlayer player, Villager villager, boolean start) {
    	MinepreggoModPacketHandler.INSTANCE.send(
            PacketDistributor.PLAYER.with(() -> player),
            new SexCinematicControlP2MS2CPacket(start, villager.getId())
        );
    }
    
    private static void sendControlPacket(ServerPlayer player, boolean start) {
    	MinepreggoModPacketHandler.INSTANCE.send(
            PacketDistributor.PLAYER.with(() -> player),
            new SexCinematicControlP2PS2CPacket(start)
        );
    }
    
    private static void sendOverlayPacket(ServerPlayer player, int totalOverlayTicks, int totalPauseTicks) {
		MinepreggoModPacketHandler.INSTANCE.send(
			PacketDistributor.PLAYER.with(() -> player),
			new RenderSexOverlayS2CPacket(totalOverlayTicks, totalPauseTicks));   
    }
}
