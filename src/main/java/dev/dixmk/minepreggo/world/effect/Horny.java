package dev.dixmk.minepreggo.world.effect;

import java.util.List;
import java.util.UUID;

import dev.dixmk.minepreggo.init.MinepreggoModMobEffects;
import dev.dixmk.minepreggo.world.entity.player.PlayerHelper;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.AbstractIllager;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class Horny extends AbstractPlayerPregnancySymptom {
    private static final UUID FOLLOW_RANGE_BOOST_ID = UUID.fromString("36baaadb-c244-4c77-a05f-a6dc26049675");
    private static final AttributeModifier FOLLOW_RANGE_MODIFIER = new AttributeModifier(FOLLOW_RANGE_BOOST_ID, "Alert aura range boost", 2, AttributeModifier.Operation.MULTIPLY_TOTAL);
    
    private int heartsTimer = 0;
    private int searchingTimer = 0;
    
	public Horny() {
		super(-65536);	
	}

	@Override
	public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
		if (!PlayerHelper.isPlayerValid(entity)) {
			return;
		}
		
        var level = entity.level();
        if (level.isClientSide) {
        	return;
        }
                 
        List<? extends Mob> mobs;
        final var center = new Vec3(entity.getX(), entity.getY(), entity.getZ());      
        final var aabb = new AABB(center, center);
        
        if (entity.hasEffect(MinepreggoModMobEffects.FULL_OF_CREEPERS.get())) {
        	mobs = level.getEntitiesOfClass(Creeper.class, aabb.inflate(64D));     	
        }
        else if (entity.hasEffect(MinepreggoModMobEffects.FULL_OF_ZOMBIES.get())) {
        	mobs = level.getEntitiesOfClass(Zombie.class, aabb.inflate(64D));
        }
        else {
        	mobs = level.getEntitiesOfClass(AbstractIllager.class, aabb.inflate(64D));
        }
        
        mobs.forEach(mob -> {
            AttributeInstance attr = mob.getAttribute(Attributes.FOLLOW_RANGE);
            if (attr != null && attr.getModifier(FOLLOW_RANGE_BOOST_ID) != null) {
                attr.removeModifier(FOLLOW_RANGE_MODIFIER);
            }
        }); 
	}
	 
	
	@Override
	public void applyEffectTick(LivingEntity entity, int amplifier) {	
        if (!PlayerHelper.isPlayerValid(entity)) {
        	return;
        }
        
        var level = entity.level();
        if (level.isClientSide) return;    
        trySpawnHeartParticules(level, entity);
        trySearchMobs(level, entity);
	}
	
	private boolean trySpawnHeartParticules(Level level, LivingEntity entity) {
        if (this.heartsTimer >= 400 && level instanceof ServerLevel serverLevel) {
        	this.heartsTimer = 0;    	
    		for (ServerPlayer player : serverLevel.getServer().getPlayerList().getPlayers()) {
    		    if (player.distanceToSqr(entity) <= 512.0) {
    				serverLevel.sendParticles(player, ParticleTypes.HEART, true, entity.getRandomX(1.0), entity.getRandomY() + 0.5, entity.getRandomZ(1.0),
    						7, serverLevel.random.nextGaussian() * 0.3, serverLevel.random.nextGaussian() * 0.5, serverLevel.random.nextGaussian() * 0.3, 0.02);
    		    }
    		}
    		return true;
        }
        else {
        	this.heartsTimer++;     	
        	return false;
        }
	}
	
	private boolean trySearchMobs(Level level, LivingEntity entity) {
        
		if (this.searchingTimer >= 700) {
			this.searchingTimer = 0;
			
			List<? extends Mob> mobs;
	        final var center = new Vec3(entity.getX(), entity.getY(), entity.getZ());      
	        final var aabb = new AABB(center, center);
	        
	        if (entity.hasEffect(MinepreggoModMobEffects.FULL_OF_CREEPERS.get())) {
	        	mobs = level.getEntitiesOfClass(Zombie.class, aabb.inflate(64D));
	        }      
	        else if (entity.hasEffect(MinepreggoModMobEffects.FULL_OF_ZOMBIES.get())) {
	        	mobs = level.getEntitiesOfClass(Creeper.class, aabb.inflate(64D));
	        }
	        else {
	        	mobs = level.getEntitiesOfClass(AbstractIllager.class, aabb.inflate(64D));
	        }
	        
	        mobs.forEach(mob -> {
	            AttributeInstance attr = mob.getAttribute(Attributes.FOLLOW_RANGE);     
	            if (attr != null && attr.getModifier(FOLLOW_RANGE_BOOST_ID) == null) {        	
	                attr.addTransientModifier(FOLLOW_RANGE_MODIFIER);
	            }      
	        }); 
	        
	        return true;
		}
		else {
			++this.searchingTimer;
			return false;
		}
	}
}
