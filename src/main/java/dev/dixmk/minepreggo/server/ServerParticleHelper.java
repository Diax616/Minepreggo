package dev.dixmk.minepreggo.server;

import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.game.ClientboundLevelParticlesPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.PacketDistributor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Nonnegative;
import org.joml.Vector3f;

public class ServerParticleHelper {
    private static final List<BloodRainInstance> ACTIVATE_RAINS = new ArrayList<>();
    private static final BlockParticleOption RED_BLOCK_PARTICLE = new BlockParticleOption(ParticleTypes.BLOCK, Blocks.RED_CONCRETE.defaultBlockState());

	private ServerParticleHelper() {}
    
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
	
    public static void startBloodRain(ServerLevel level, Entity entity, Vec3 pos) {
        startBloodRain(level, entity, pos, 20, 5);
    }

    public static void startBloodRainAtEntity(ServerLevel level, Entity entity) {
        Vec3 pos = entity.position().add(0, entity.getBbHeight() / 2, 0);
        startBloodRain(level, entity, pos, 40, 10);
    }

    public static void startBloodRainAtEntity(ServerLevel level, Entity entity, int durationTicks) {
        Vec3 pos = entity.position().add(0, entity.getBbHeight() / 2, 0);
        startBloodRain(level, entity, pos, durationTicks, 5);
    }

    public static void startBloodRain(ServerLevel level, Entity entity, Vec3 pos, int durationTicks, int particlesPerTick) {
    	ACTIVATE_RAINS.add(new BloodRainInstance(level, entity, pos, durationTicks, particlesPerTick));
    }
    
    public static void tickBloodRains() {
		Iterator<BloodRainInstance> iterator = ACTIVATE_RAINS.iterator();
		while (iterator.hasNext()) {
			BloodRainInstance rain = iterator.next();
			
			if (rain.isFinished()) {
				iterator.remove();
			} else {
				rain.tick();
			}
		}
	}
    
    private static class BloodRainInstance {    	
        private final ServerLevel level;
        private final Entity entity;
        private final Vec3 position;
        private final int duration;
        private final int particlesPerTick;
        private int ticksElapsed = 0;
        
        public BloodRainInstance(ServerLevel level, Entity entity, Vec3 position, int duration, int particlesPerTick) {
            this.level = level;
            this.entity = entity;
            this.position = position;
            this.duration = duration;
            this.particlesPerTick = particlesPerTick;
        }
        
        public void tick() {
            if (entity.isRemoved()) {
                ticksElapsed = duration;
                return;
            }
            
            spawnParticles();
            ticksElapsed++;
        }
        
        public boolean isFinished() {
            return ticksElapsed >= duration;
        }
        
        private void spawnParticles() {
            double x = position.x;
            double y = position.y;
            double z = position.z;
            
            for (int i = 0; i < particlesPerTick; i++) {
                double offsetX = level.random.nextDouble() * 0.9;
                double offsetZ = level.random.nextDouble() * 0.9;
                double offsetY = level.random.nextDouble() * 0.3;
                double velocityX = (level.random.nextDouble() - 0.5) * 0.1;
                double velocityY = -0.3 - level.random.nextDouble() * 0.2;
                double velocityZ = (level.random.nextDouble() - 0.5) * 0.1;
                
                ClientboundLevelParticlesPacket packet = new ClientboundLevelParticlesPacket(
                	RED_BLOCK_PARTICLE,
                    false,
                    x + offsetX,
                    y + offsetY,
                    z + offsetZ,
                    (float) velocityX,
                    (float) velocityY,
                    (float) velocityZ,
                    0.2f,
                    1
                );
                
                PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> entity).send(packet);
            }
        }
    }
}
