package dev.dixmk.minepreggo.world.effect;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.world.entity.player.PlayerPregnancySystemP0;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;

public abstract class AbstractPlayerPregnancy<S extends PlayerPregnancySystemP0> extends MobEffect  {
	protected static final UUID SPEED_MODIFIER_UUID = UUID.fromString("a0bf6ac9-4354-4977-86fc-5dea9108665d");
	protected static final UUID ATTACK_SPEED_MODIFIER_UUID = UUID.fromString("57a3938d-b55a-47b5-93ee-737724ba9d2e");
	
	/**
	 * Cache of pregnancy systems indexed by player UUID.
	 * 
	 * <h3>Multiplayer Behavior:</h3>
	 * In Minecraft Forge, MobEffect instances are <b>singletons</b> - there is only ONE instance
	 * of each effect class (PregnancyP0, PregnancyP1, etc.) shared by ALL players on the server.
	 * 
	 * <p>This Map allows multiple players to have the same pregnancy effect simultaneously:
	 * <ul>
	 *   <li>Player A in phase P0 → Map entry: {UUID_A → PlayerPregnancySystemP0 for A}</li>
	 *   <li>Player B in phase P0 → Map entry: {UUID_B → PlayerPregnancySystemP0 for B}</li>
	 *   <li>Player C in phase P5 → Stored in PregnancyP5's separate Map</li>
	 * </ul>
	 * 
	 * <h3>Why NOT static?</h3>
	 * This field is <b>intentionally NOT static</b> because:
	 * <ol>
	 *   <li>Each pregnancy phase (P0-P8) is a <b>different class</b> with its own singleton instance</li>
	 *   <li>Each phase needs its own isolated Map to store systems for players in that specific phase</li>
	 *   <li>If static, all phases would share the same Map, causing conflicts when players are in different phases</li>
	 * </ol>
	 * 
	 * <h3>Lifecycle:</h3>
	 * <ul>
	 *   <li><b>Add:</b> When effect is applied via {@link #ensurePregnancySystemInitialized(ServerPlayer)}</li>
	 *   <li><b>Use:</b> Retrieved every tick in {@link #applyEffectTick(LivingEntity, int)}</li>
	 *   <li><b>Remove:</b> Cleaned up in {@link #removeAttributeModifiers(LivingEntity, AttributeMap, int)}</li>
	 * </ul>
	 * 
	 * @see #ensurePregnancySystemInitialized(ServerPlayer)
	 * @see PlayerPregnancySystemP0#isPlayerValid(ServerPlayer)
	 */
	protected final Map<UUID, S> pregnancySystemsCache = new HashMap<>();
	
	protected AbstractPlayerPregnancy() {
		super(MobEffectCategory.NEUTRAL, -6750055);
	}

	@Override
	public void addAttributeModifiers(LivingEntity entity, AttributeMap p_19479_, int p_19480_) {
    	if (entity instanceof ServerPlayer serverPlayer) {
    		ensurePregnancySystemInitialized(serverPlayer);
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
    	if (entity instanceof ServerPlayer serverPlayer) {
    		// Remove the pregnancy system for this specific player from the Map
    		pregnancySystemsCache.remove(serverPlayer.getUUID());
    	}
    }
}
