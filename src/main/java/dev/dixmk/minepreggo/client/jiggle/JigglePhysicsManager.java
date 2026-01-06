package dev.dixmk.minepreggo.client.jiggle;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Supplier;

import javax.annotation.Nullable;

import dev.dixmk.minepreggo.world.entity.player.SkinType;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * Singleton manager that maintains separate jiggle physics instances for each player.
 * This ensures that in multiplayer environments, each player has their own independent
 * physics state that doesn't interfere with other players.
 */
@OnlyIn(Dist.CLIENT)
public class JigglePhysicsManager {
    
    private final Map<UUID, PlayerJiggleData> playerPhysicsCache;
    
    private static class Holder {
        private static final JigglePhysicsManager INSTANCE = new JigglePhysicsManager();
    }
    
    private JigglePhysicsManager() {
        this.playerPhysicsCache = new HashMap<>();
    }
    
    public static JigglePhysicsManager getInstance() {
        return Holder.INSTANCE;
    }
    
    public PlayerJiggleData getOrCreate(UUID playerId, Supplier<PlayerJiggleData> factory) {
        return playerPhysicsCache.computeIfAbsent(playerId, id -> factory.get());
    }
    
    @Nullable
    public PlayerJiggleData get(UUID playerId) {
        return playerPhysicsCache.get(playerId);
    }
    
    public void set(UUID playerId, PlayerJiggleData data) {
        playerPhysicsCache.put(playerId, data);
    }
    
    public void remove(UUID playerId) {
        playerPhysicsCache.remove(playerId);
    }
    
    public void reset(UUID playerId) {
        PlayerJiggleData data = playerPhysicsCache.get(playerId);
        if (data != null) {
            data.reset();
        }
    }
    
    public void clearAll() {
        playerPhysicsCache.clear();
    }
    
    public int size() {
        return playerPhysicsCache.size();
    }
    
    public boolean contains(UUID playerId) {
        return playerPhysicsCache.containsKey(playerId);
    }
    
    /**
     * Updates or creates the PlayerJiggleData for a player based on their current pregnancy phase and model type.
     * This should be called when a player advances to a new pregnancy phase.
     * 
     * @param playerId The UUID of the player
     * @param phase The new pregnancy phase
     * @param modelType The model type (CUSTOM or PREDEFINED)
     */
    public void updateForPhase(UUID playerId, PregnancyPhase phase, SkinType modelType) {
        playerPhysicsCache.put(playerId, PlayerJiggleDataFactory.create(phase, modelType));
    }
    
    public void updateForNonPregnancy(UUID playerId) {
        playerPhysicsCache.put(playerId, PlayerJiggleDataFactory.createNonPregnancy());
    }
}
