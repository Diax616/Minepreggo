package dev.dixmk.minepreggo.world.effect;

import java.util.UUID;

import com.google.common.collect.ImmutableMap;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.init.MinepreggoModMobEffects;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntMaps;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.ForgeMod;

public class EnderDragonPregnancy extends MobEffect {
	private static final UUID MAX_HEALTH_MODIFIER_UUID = UUID.fromString("dcba27df-80b9-403b-b90f-fa4d212d13a3");
	private static final AttributeModifier MAX_HEALTH_MODIFIER = new AttributeModifier(MAX_HEALTH_MODIFIER_UUID, "Max health boost", 0.3D, AttributeModifier.Operation.MULTIPLY_BASE);
	private static final UUID ARMOR_MODIFIER_UUID = UUID.fromString("1eb705bf-86e1-4fec-a5ac-56c56d84f37a");
	private static final AttributeModifier ARMOR_MODIFIER = new AttributeModifier(ARMOR_MODIFIER_UUID, "Armor boost", 0.2D, AttributeModifier.Operation.MULTIPLY_BASE);
	private static final UUID ATTACK_KNOCKBACK_MODIFIER_UUID = UUID.fromString("1460fcf6-1ded-4654-9f55-a8b93eba6cc6");
	private static final AttributeModifier ATTACK_KNOCKBACK_MODIFIER = new AttributeModifier(ATTACK_KNOCKBACK_MODIFIER_UUID, "Attack knockback boost", 0.1D, AttributeModifier.Operation.ADDITION);
	private static final UUID GRAVITY_MODIFIER_UUID = UUID.fromString("c6a3ca0b-9dbb-4572-a064-f452572394cd");
	private static final AttributeModifier GRAVITY_MODIFIER = new AttributeModifier(GRAVITY_MODIFIER_UUID, "Gravity boost", 0.15D, AttributeModifier.Operation.MULTIPLY_BASE);
	
	private static final UUID MOVEMENT_SPEED_MODIFIER_UUID = UUID.fromString("f15966b6-8474-4821-bb43-b0a6650c4a6f");
	private static final AttributeModifier MOVEMENT_SPEED_MODIFIER = new AttributeModifier(MOVEMENT_SPEED_MODIFIER_UUID, "Movement speed nerf", -0.1D, AttributeModifier.Operation.MULTIPLY_BASE);
	private static final UUID KNOCKBACK_RESISTANCE_MODIFIER_UUID = UUID.fromString("685b396f-7eca-4a8c-8ce9-2c56641819e5");
	private static final AttributeModifier KNOCKBACK_RESISTANCE_MODIFIER_1 = new AttributeModifier(KNOCKBACK_RESISTANCE_MODIFIER_UUID, "Knockback resistance nerf level 1", 0.2D, AttributeModifier.Operation.ADDITION);
	private static final AttributeModifier KNOCKBACK_RESISTANCE_MODIFIER_2 = new AttributeModifier(KNOCKBACK_RESISTANCE_MODIFIER_UUID, "Knockback resistance nerf level 2", 0.3D, AttributeModifier.Operation.ADDITION);
	private static final AttributeModifier KNOCKBACK_RESISTANCE_MODIFIER_3 = new AttributeModifier(KNOCKBACK_RESISTANCE_MODIFIER_UUID, "Knockback resistance nerf level 3", 0.4D, AttributeModifier.Operation.ADDITION);

	private static final UUID ATTACK_SPEED_MODIFIER_UUID = UUID.fromString("eeaa13eb-dacd-43ba-8423-1b18327adea9");
	private static final AttributeModifier ATTACK_SPEED_MODIFIER = new AttributeModifier(ATTACK_SPEED_MODIFIER_UUID, "Movement speed nerf", -0.1D, AttributeModifier.Operation.MULTIPLY_BASE);
	
	private static final int INACTIVE_TICKS = 2200;
	private static final Object2IntMap<PregnancyPhase> PARTICLE_CYCLE_DURATION = Object2IntMaps.unmodifiable(new Object2IntOpenHashMap<>(ImmutableMap.of(
			PregnancyPhase.P0, 2400,
			PregnancyPhase.P1, 2700,
			PregnancyPhase.P2, 3100,
			PregnancyPhase.P3, 3600,
			PregnancyPhase.P4, 4200,
			PregnancyPhase.P5, 4900,
			PregnancyPhase.P6, 5700,
			PregnancyPhase.P7, 6600,
			PregnancyPhase.P8, 7600
			)));
				
	private final Object2IntMap<UUID> particleTimer = new Object2IntOpenHashMap<>();
	
	public EnderDragonPregnancy() {
		super(MobEffectCategory.BENEFICIAL, -10092493);
	}
	
	@Override
	public void applyEffectTick(LivingEntity entity, int amplifier) {
	    if (!entity.level().isClientSide) {
	        return;
	    }
	    
	    entity.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(playerData -> {
	    	playerData.getFemaleData().ifPresent(femaleData -> {
	    		if (femaleData.isPregnant() && femaleData.isPregnancyDataInitialized()) {
	    			UUID entityUUID = entity.getUUID();
	    		    particleTimer.computeInt(entityUUID, (uuid, timer) -> { 	
	    		    	int cycleParticleDuration = PARTICLE_CYCLE_DURATION.getInt(femaleData.getPregnancyData().getCurrentPregnancyPhase());
	    		    	
	    		        if (timer == null) {
	    		            return cycleParticleDuration - 1;
	    		        }
	    		        
	    		        int currentTimer = timer;
	    		        if (currentTimer > INACTIVE_TICKS) {
	    		            spawnParticles(entity);
	    		        }
	    		        
	    		        currentTimer--;
	    		        if (currentTimer <= 0) {
	    		            currentTimer = cycleParticleDuration;
	    		        } 
	    		        return currentTimer;
	    		    });		
	    		}
	    	});
	    });
	}

	private void spawnParticles(LivingEntity entity) {
        var random = entity.getRandom();
	    for(int i = 0; i < 2; ++i) {
	        entity.level().addParticle(
	            ParticleTypes.PORTAL, 
	            entity.getRandomX(0.5D), 
	            entity.getRandomY(), 
	            entity.getRandomZ(0.5D), 
	            (random.nextDouble() - 0.5D) * 2.0D, 
	            -random.nextDouble(), 
	            (random.nextDouble() - 0.5D) * 2.0D
	        );
	    } 
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
	
    @Override
    public void removeAttributeModifiers(LivingEntity entity, AttributeMap p_19470_, int p_19471_) {	
    	 particleTimer.removeInt(entity.getUUID());
    	if (entity instanceof Player player) {
			removeEffects(player);
		}
    }
	
	static void applyEffects(Player player, PregnancyPhase current) {
		switch (current) {
			case P0 -> applyEffectsForP0(player);
			case P1 -> applyEffectsForP1(player);
			case P2 -> applyEffectsForP2(player);
			case P3 -> applyEffectsForP3(player);
			case P4 -> applyEffectsForP4(player);
			case P5 -> applyEffectsForP5(player);
			case P6 -> applyEffectsForP6(player);
			case P7 -> applyEffectsForP7(player);
			case P8 -> applyEffectsForP8(player);
		}
	}
	
	private static void removeEffects(Player player) {
		AttributeInstance healthAttribute = player.getAttribute(Attributes.MAX_HEALTH);
		if (healthAttribute != null && healthAttribute.getModifier(MAX_HEALTH_MODIFIER_UUID) != null) {
			healthAttribute.removeModifier(MAX_HEALTH_MODIFIER_UUID);
		}
		AttributeInstance armorAttribute = player.getAttribute(Attributes.ARMOR);
		if (armorAttribute != null && armorAttribute.getModifier(ARMOR_MODIFIER_UUID) != null) {
			armorAttribute.removeModifier(ARMOR_MODIFIER_UUID);
		}
		AttributeInstance knockbackAttribute = player.getAttribute(Attributes.ATTACK_KNOCKBACK);
		if (knockbackAttribute != null && knockbackAttribute.getModifier(ATTACK_KNOCKBACK_MODIFIER_UUID) != null) {
			knockbackAttribute.removeModifier(ATTACK_KNOCKBACK_MODIFIER_UUID);
		}
		AttributeInstance movementSpeedAttribute = player.getAttribute(Attributes.MOVEMENT_SPEED);
		if (movementSpeedAttribute != null && movementSpeedAttribute.getModifier(MOVEMENT_SPEED_MODIFIER_UUID) != null) {
			movementSpeedAttribute.removeModifier(MOVEMENT_SPEED_MODIFIER_UUID);
		}
		AttributeInstance knockbackResistanceAttribute = player.getAttribute(Attributes.KNOCKBACK_RESISTANCE);
		if (knockbackResistanceAttribute != null && knockbackResistanceAttribute.getModifier(KNOCKBACK_RESISTANCE_MODIFIER_UUID) != null) {
			knockbackResistanceAttribute.removeModifier(KNOCKBACK_RESISTANCE_MODIFIER_UUID);
		}
		AttributeInstance attackSpeedAttribute = player.getAttribute(Attributes.ATTACK_SPEED);
		if (attackSpeedAttribute != null && attackSpeedAttribute.getModifier(ATTACK_SPEED_MODIFIER_UUID) != null) {
			attackSpeedAttribute.removeModifier(ATTACK_SPEED_MODIFIER_UUID);
		}
		AttributeInstance gravityAttribute = player.getAttribute(ForgeMod.ENTITY_GRAVITY.get());
		if (gravityAttribute != null && gravityAttribute.getModifier(GRAVITY_MODIFIER_UUID) != null) {
			gravityAttribute.removeModifier(GRAVITY_MODIFIER_UUID);
		}
		
		player.removeEffect(MobEffects.NIGHT_VISION);
		player.removeEffect(MinepreggoModMobEffects.PREGNANCY_RESISTANCE.get());
		player.removeEffect(MobEffects.FIRE_RESISTANCE);
		player.removeEffect(MinepreggoModMobEffects.POISON_IMMUNITY.get());
		player.removeEffect(MinepreggoModMobEffects.WIHER_IMMUNITY.get());
		player.removeEffect(MobEffects.DAMAGE_BOOST);
		player.removeEffect(MobEffects.DAMAGE_RESISTANCE);
		player.removeEffect(MobEffects.REGENERATION);	
		player.removeEffect(MinepreggoModMobEffects.ENDER_ESSENCE.get());
		player.removeEffect(MinepreggoModMobEffects.ENDER_DRAGON_RECOGNITION.get());	
		player.removeEffect(MinepreggoModMobEffects.ENDER_DRAGON_ESSENCE.get());
	}
	
    private static void applyEffectsForP0(LivingEntity entity) {
    	AttributeInstance attributeMap = entity.getAttribute(Attributes.MAX_HEALTH);	
		if (attributeMap != null && attributeMap.getModifier(MAX_HEALTH_MODIFIER_UUID) == null) {
			attributeMap.addPermanentModifier(MAX_HEALTH_MODIFIER);
		}	
    }
    
    private static void applyEffectsForP1(LivingEntity entity) {
    	applyEffectsForP0(entity);
    	AttributeInstance attributeMap = entity.getAttribute(Attributes.ARMOR);
    	if (attributeMap != null && attributeMap.getModifier(ARMOR_MODIFIER_UUID) == null) {
    		attributeMap.addPermanentModifier(ARMOR_MODIFIER);
    	}
    }
	
    private static void applyEffectsForP2(LivingEntity entity) {
    	applyEffectsForP1(entity);
    	AttributeInstance attributeMap = entity.getAttribute(Attributes.ATTACK_KNOCKBACK);
    	if (attributeMap != null && attributeMap.getModifier(ATTACK_KNOCKBACK_MODIFIER_UUID) == null) {
			attributeMap.addPermanentModifier(ATTACK_KNOCKBACK_MODIFIER);
		}
    }
    
    private static void applyEffectsForP3(LivingEntity entity) {
    	applyEffectsForP2(entity);
    	entity.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, -1, 0, false, false, false));
    	entity.addEffect(new MobEffectInstance(MinepreggoModMobEffects.PREGNANCY_RESISTANCE.get(), -1, 0, false, false, false));
    	entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, -1, 0, false, false, false));
    }
    
    private static void applyEffectsForP4(LivingEntity entity) {
    	applyEffectsForP3(entity);
    	entity.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, -1, 0, false, false, false));
    	entity.addEffect(new MobEffectInstance(MinepreggoModMobEffects.POISON_IMMUNITY.get(), -1, 0, false, false, false));
    	entity.addEffect(new MobEffectInstance(MinepreggoModMobEffects.WIHER_IMMUNITY.get(), -1, 0, false, false, false));
   
    	AttributeInstance movementSpeedAttribute = entity.getAttribute(Attributes.MOVEMENT_SPEED);
    	if (movementSpeedAttribute != null && movementSpeedAttribute.getModifier(MOVEMENT_SPEED_MODIFIER_UUID) == null) {
    		movementSpeedAttribute.addPermanentModifier(MOVEMENT_SPEED_MODIFIER);
    	}
    	AttributeInstance attackSpeedAttribute = entity.getAttribute(Attributes.ATTACK_SPEED);
		if (attackSpeedAttribute != null && attackSpeedAttribute.getModifier(ATTACK_SPEED_MODIFIER_UUID) == null) {
			attackSpeedAttribute.addPermanentModifier(ATTACK_SPEED_MODIFIER);
		}
		addKnockBack(1, entity);
    }
    
    private static void applyEffectsForP5(LivingEntity entity) {
    	applyEffectsForP4(entity);
    	entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, -1, 0, false, false, false));
    	AttributeInstance gravityAttribute = entity.getAttribute(ForgeMod.ENTITY_GRAVITY.get());
    	if (gravityAttribute != null && gravityAttribute.getModifier(GRAVITY_MODIFIER_UUID) == null) {
			gravityAttribute.addPermanentModifier(GRAVITY_MODIFIER);
		}
    }

    private static void applyEffectsForP6(LivingEntity entity) {
    	applyEffectsForP5(entity);
    	entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, -1, 1, false, false, false));
		addKnockBack(2, entity);
    	entity.addEffect(new MobEffectInstance(MinepreggoModMobEffects.ENDER_ESSENCE.get(), -1, 0, false, false, true));
    }
    
    private static void applyEffectsForP7(LivingEntity entity) {
    	applyEffectsForP6(entity);
    	entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, -1, 0, false, false, false));
    	entity.addEffect(new MobEffectInstance(MinepreggoModMobEffects.ENDER_DRAGON_RECOGNITION.get(), -1, 0, false, false, true));
    }
    
    private static void applyEffectsForP8(LivingEntity entity) {
    	applyEffectsForP7(entity);
		addKnockBack(3, entity);
    	entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, -1, 1, false, false, false));
    	entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, -1, 1, false, false, false));
    	entity.addEffect(new MobEffectInstance(MinepreggoModMobEffects.ENDER_DRAGON_ESSENCE.get(), -1, 0, false, false, true));
    }
    
    private static void addKnockBack(int level, LivingEntity entity) {
		AttributeInstance knockbackResistanceAttribute = entity.getAttribute(Attributes.KNOCKBACK_RESISTANCE);
		if (knockbackResistanceAttribute != null) {
			if (knockbackResistanceAttribute.getModifier(KNOCKBACK_RESISTANCE_MODIFIER_UUID) != null) {
				knockbackResistanceAttribute.removeModifier(KNOCKBACK_RESISTANCE_MODIFIER_UUID);
			}
			switch (level) {
				case 1 -> knockbackResistanceAttribute.addPermanentModifier(KNOCKBACK_RESISTANCE_MODIFIER_1);
				case 2 -> knockbackResistanceAttribute.addPermanentModifier(KNOCKBACK_RESISTANCE_MODIFIER_2);
				case 3 -> knockbackResistanceAttribute.addPermanentModifier(KNOCKBACK_RESISTANCE_MODIFIER_3);
				default -> MinepreggoMod.LOGGER.warn("Invalid knockback resistance level: {}", level);	
			}
		}  	
    }
    
    public static boolean isSecondaryEffect(MobEffect effect, PregnancyPhase phase) {
    	return switch (phase) {
			case P3 -> isEffectInPhaseP3(effect);
			case P4 -> isEffectInPhaseP3(effect) || isEffectInPhaseP4(effect);
			case P5 -> isEffectInPhaseP3(effect) || isEffectInPhaseP4(effect) || isEffectInPhaseP5(effect);
			case P6 -> isEffectInPhaseP3(effect) || isEffectInPhaseP4(effect) || isEffectInPhaseP5(effect) || isEffectInPhaseP6(effect);
			case P7 -> isEffectInPhaseP3(effect) || isEffectInPhaseP4(effect) || isEffectInPhaseP5(effect) || isEffectInPhaseP6(effect) || isEffectInPhaseP7(effect);
			case P8 -> isEffectInPhaseP3(effect) || isEffectInPhaseP4(effect) || isEffectInPhaseP5(effect) || isEffectInPhaseP6(effect) || isEffectInPhaseP7(effect) || isEffectInPhaseP8(effect);
			default -> false;
    	};
	}
    
    private static boolean isEffectInPhaseP3(MobEffect effect) {
		return effect == MobEffects.NIGHT_VISION || effect == MinepreggoModMobEffects.PREGNANCY_RESISTANCE.get() || effect == MobEffects.DAMAGE_RESISTANCE;
	}
    
    private static boolean isEffectInPhaseP4(MobEffect effect) {
    	return effect == MobEffects.FIRE_RESISTANCE || effect == MinepreggoModMobEffects.POISON_IMMUNITY.get() || effect == MinepreggoModMobEffects.WIHER_IMMUNITY.get();
    }
    
    private static boolean isEffectInPhaseP5(MobEffect effect) {
		return effect == MobEffects.DAMAGE_BOOST;
	}
    
    private static boolean isEffectInPhaseP6(MobEffect effect) {
    	return effect == MobEffects.DAMAGE_RESISTANCE || effect == MinepreggoModMobEffects.ENDER_ESSENCE.get();
    }
    
    private static boolean isEffectInPhaseP7(MobEffect effect) {
		return effect == MobEffects.REGENERATION || effect == MinepreggoModMobEffects.ENDER_DRAGON_RECOGNITION.get();
	}
    
    private static boolean isEffectInPhaseP8(MobEffect effect) {
		return effect == MobEffects.DAMAGE_BOOST || effect == MinepreggoModMobEffects.ENDER_DRAGON_ESSENCE.get() || effect == MobEffects.REGENERATION;
	}
}