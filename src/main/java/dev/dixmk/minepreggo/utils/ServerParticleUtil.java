package dev.dixmk.minepreggo.utils;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.network.protocol.game.ClientboundLevelParticlesPacket;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.network.PacketDistributor;

import javax.annotation.Nonnegative;
import org.joml.Vector3f;

public class ServerParticleUtil {

	private ServerParticleUtil() {}
	
	public static void spawnRandomlyFromServer(LivingEntity target, ParticleOptions particle) {		    
		spawnRandomlyFromServer(target, particle, 10);
	}
	
	public static void spawnRandomlyFromServer(LivingEntity target, ParticleOptions particle, @Nonnegative int count) {		    
		spawnRandomlyFromServer(target, particle, count, 0.02F, new Vector3f(0.75F), true);
	}
	
	public static void spawnRandomlyFromServer(LivingEntity target, ParticleOptions particle, @Nonnegative int count, @Nonnegative float maxSpeed, Vector3f dist, boolean overrideLimiter) {		    
		if (target.level().isClientSide) {
			return;
		}	
		ClientboundLevelParticlesPacket packet = new ClientboundLevelParticlesPacket(
	            particle, 
	            overrideLimiter, 
	            target.getRandomX(1.0D), target.getRandomY(), target.getRandomZ(1.0D), 
	            dist.x, dist.y, dist.z,
	            maxSpeed,
	            count
	        );    
	    PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> target).send(packet);
	}
}
