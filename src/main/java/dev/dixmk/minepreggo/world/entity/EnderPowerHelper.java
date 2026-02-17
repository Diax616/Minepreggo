package dev.dixmk.minepreggo.world.entity;

import java.util.Optional;

import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.network.capability.IEnderPowerData;
import dev.dixmk.minepreggo.network.chat.MessageHelper;
import dev.dixmk.minepreggo.world.entity.projectile.ExplosiveDragonFireball;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;

public class EnderPowerHelper {
	
	private EnderPowerHelper() {}
	
	public static boolean tryTeleportTo(ServerPlayer player, BlockPos targetPos) { 
		Optional<Boolean> teleportResult = player.getCapability(MinepreggoCapabilities.ENDER_POWER_DATA)
				.resolve()
				.map(enderPowerData -> {									
					if (enderPowerData.getEnderPowerLevel() >= IEnderPowerData.EnderPower.TELEPORT.baseLevelCost) {						
						if (teleportTo(player, targetPos.getX() + 0.5, targetPos.getY() + 1d, targetPos.getZ() + 0.5)) {
							enderPowerData.decrementEnderPowerLevel(IEnderPowerData.EnderPower.TELEPORT.baseLevelCost);	
							enderPowerData.sync(player);
							return true;
						}
					}
					else {
						MessageHelper.sendTo(player, Component.translatable("chat.minepreggo.ender_power.message.without_ender_energy"), true);
					}
					return false;
				});
		return teleportResult.orElse(false);
	}
		
    public static boolean tryShootFireball(ServerPlayer player, Vec3 target) {
    	Optional<Boolean> shootResult = player.getCapability(MinepreggoCapabilities.ENDER_POWER_DATA)
    			.resolve()
    			.map(enderPowerData -> {			
					if (enderPowerData.getEnderPowerLevel() >= IEnderPowerData.EnderPower.FIREBALL.baseLevelCost) {
						shootFireball(player, target);
						enderPowerData.decrementEnderPowerLevel(IEnderPowerData.EnderPower.FIREBALL.baseLevelCost);
						enderPowerData.sync(player);
						return true;
					}
					else {
						MessageHelper.sendTo(player, Component.translatable("chat.minepreggo.ender_power.message.without_ender_energy"), true);
					}
					return false;
				});
    	return shootResult.orElse(false);
    }
	
	
	@SuppressWarnings("deprecation")
	private static boolean teleportTo(ServerPlayer player, double targetX, double targetY, double targetZ) {
	    if (player.level().isClientSide()) {
	        return false;
	    }
	    
	    Level level = player.level();
	    BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos(targetX, targetY, targetZ);
	    
	    while (mutablePos.getY() > level.getMinBuildHeight() && !level.getBlockState(mutablePos).blocksMotion()) {
	        mutablePos.move(Direction.DOWN);
	    }
	    
	    BlockState blockState = level.getBlockState(mutablePos);
	    boolean isSolid = blockState.blocksMotion();
	    boolean isWater = blockState.getFluidState().is(FluidTags.WATER);
	    
	    if (isSolid && !isWater) {
	        	        
	        Vec3 originalPos = player.position();
	        boolean success = player.randomTeleport(targetX, targetY, targetZ, true);
	        
	        if (success) {
	            level.gameEvent(GameEvent.TELEPORT, originalPos, GameEvent.Context.of(player));
	            
	            if (!player.isSilent()) {
	                level.playSound(null, originalPos.x, originalPos.y, originalPos.z, 
	                    SoundEvents.ENDERMAN_TELEPORT, player.getSoundSource(), 1.0F, 1.0F);
	                level.playSound(null, targetX, targetY, targetZ, 
		                    SoundEvents.ENDERMAN_TELEPORT, player.getSoundSource(), 1.0F, 1.0F);
	            }
	        }
	        
	        return success;
	    }
	    
	    return false;
	}
    
    private static void shootFireball(ServerPlayer player, Vec3 direction) {
        Vec3 eyePosition = player.getEyePosition();       
        double deltaX = direction.x * 3.5;
        double deltaY = direction.y * 3.5;
        double deltaZ = direction.z * 3.5;
        ExplosiveDragonFireball fireball = new ExplosiveDragonFireball(player.level(), player, deltaX, deltaY, deltaZ, 2);
        fireball.setPos(eyePosition.x, eyePosition.y, eyePosition.z);
        player.level().addFreshEntity(fireball);
        if (!player.isSilent()) {
            player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.GHAST_SHOOT, SoundSource.PLAYERS, 1.0F, 1.0F);
        }
    }
}
