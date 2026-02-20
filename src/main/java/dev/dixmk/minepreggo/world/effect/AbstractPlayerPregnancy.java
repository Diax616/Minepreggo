package dev.dixmk.minepreggo.world.effect;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.UUID;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.init.MinepreggoModMobEffects;
import dev.dixmk.minepreggo.world.entity.BellyPartManager;
import dev.dixmk.minepreggo.world.entity.player.PlayerHelper;
import dev.dixmk.minepreggo.world.entity.player.PlayerPregnancySystemP0;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

public abstract class AbstractPlayerPregnancy<S extends PlayerPregnancySystemP0> extends MobEffect  {
	static final UUID SPEED_MODIFIER_UUID = UUID.fromString("a0bf6ac9-4354-4977-86fc-5dea9108665d");
	static final UUID ATTACK_SPEED_MODIFIER_UUID = UUID.fromString("57a3938d-b55a-47b5-93ee-737724ba9d2e");
	
	/*
	 * Cache of pregnancy systems indexed by player UUID.
	 *
	 * Multiplayer behavior:
	 * In Minecraft Forge, MobEffect instances are singletons - there is only one instance
	 * of each effect class shared by all players on the server.
	 *
	 * This map allows multiple players to have the same pregnancy effect simultaneously:
	 *  - Player A in phase P0 -> {UUID_A -> PlayerPregnancySystemP0 for A}
	 *  - Player B in phase P0 -> {UUID_B -> PlayerPregnancySystemP0 for B}
	 *  - Player C in phase P5 -> Stored in PregnancyP5's separate map
	 *
	 * Why not static?
	 *  - Each pregnancy phase (P0-P8) is a different class with its own singleton instance.
	 *  - Each phase needs its own isolated map to store systems for players in that phase.
	 *  - If static, all phases would share the same map, causing conflicts when players are in different phases.
	 *
	 * Lifecycle:
	 *  - Add: When effect is applied via ensurePregnancySystemInitialized(ServerPlayer)
	 *  - Use: Retrieved every tick in applyEffectTick(LivingEntity, int)
	 *  - Remove: Cleaned up in removeAttributeModifiers(LivingEntity, AttributeMap, int)
	 */
	protected final Map<UUID, S> pregnancySystemsCache = new HashMap<>();
	
	protected AbstractPlayerPregnancy() {
		super(MobEffectCategory.NEUTRAL, -6750055);
	}

	@Override
	public void addAttributeModifiers(LivingEntity entity, AttributeMap p_19479_, int p_19480_) {
    	if (entity instanceof ServerPlayer serverPlayer && !serverPlayer.level().isClientSide) {
    		ensurePregnancySystemInitialized(serverPlayer); 		
    		serverPlayer.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap -> 
    			cap.getFemaleData().ifPresent(femaleData -> {
    				if (femaleData.isPregnant() && femaleData.isPregnancyDataInitialized()) {
    					var phase = femaleData.getPregnancyData().getCurrentPregnancyPhase();
    					PregnancySystemHelper.applyGravityModifier(entity, phase);
    					PregnancySystemHelper.applyKnockbackResistanceModifier(entity, phase);	
    							
    					PlayerHelper.addEnderDragonPregnancyEffects(serverPlayer);	
    					
    					if (serverPlayer.hasEffect(MinepreggoModMobEffects.ENDER_DRAGON_PREGNANCY.get())) {
    						MinepreggoMod.LOGGER.debug("Applying Ender Dragon pregnancy effects for player: {} in phase: {}", serverPlayer.getName().getString(), phase);
    						EnderDragonPregnancy.applyEffects(serverPlayer, phase);
    					}
    				}
    			})
    		); 	
    	}
	}
		
	/*If player logs back, pregnancySystem might not be null but have a stale player reference and packets does not reach new player
		So we need to ensure the player reference is valid			
		* Recreate if null OR if the player UUID doesn't match (shouldn't happen)
		* OR if we need to refresh the reference
		* Check if the stored player reference is stale
	*/
    protected abstract void ensurePregnancySystemInitialized(ServerPlayer serverPlayer);
	  
    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {  	
    	if (entity.level().isClientSide) {
    		return;
    	}
    	
    	if (entity instanceof ServerPlayer serverPlayer) {
            // Ensure the system is initialized before trying to use it
            ensurePregnancySystemInitialized(serverPlayer);
            
            // Get the pregnancy system for this specific player
            S pregnancySystem = pregnancySystemsCache.get(serverPlayer.getUUID());
            
            if (pregnancySystem != null) {
                pregnancySystem.onServerTick();
            }
            else {
    			// This should not happen, but log a warning if it does
    			MinepreggoMod.LOGGER.warn("Pregnancy system is not initialized for player: {}", serverPlayer.getName().getString());
    		}
    	}
    }
    
	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
	
    @Override
    public void removeAttributeModifiers(LivingEntity entity, AttributeMap p_19470_, int p_19471_) {
    	if (entity instanceof ServerPlayer serverPlayer && !serverPlayer.level().isClientSide) {
    		pregnancySystemsCache.remove(serverPlayer.getUUID());
    		
			PregnancySystemHelper.removeGravityModifier(entity);
			PregnancySystemHelper.removeKnockbackResistanceModifier(entity);
			
			if (MinepreggoModConfig.SERVER.isBellyColisionsForPlayersEnable()) {
				BellyPartManager.getInstance().remove(entity);
			}
    	}
    }
    
    abstract Optional<AttributeModifier> getSpeedModifier();
    abstract Optional<AttributeModifier> getAttackSpeedModifier();
    abstract OptionalInt getMovementSpeedNerfAmplifier();
}
