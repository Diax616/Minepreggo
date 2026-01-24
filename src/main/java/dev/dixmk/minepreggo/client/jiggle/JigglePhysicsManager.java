package dev.dixmk.minepreggo.client.jiggle;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;

import javax.annotation.CheckForNull;
import javax.annotation.Nullable;

import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Table;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.world.entity.player.SkinType;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * Singleton manager that maintains separate jiggle physics instances for each player.
 * This ensures that in multiplayer environments, each player has their own independent
 * physics state that doesn't interfere with other players.
 */
@OnlyIn(Dist.CLIENT)
public class JigglePhysicsManager {
    
    private final Map<UUID, EntityJiggleData> entitiesPhysicsCache;
    
    private final Map<String, EntityJiggleDataFactory.JigglePositionConfig> configCache;

	private static final Table<SkinType, Optional<PregnancyPhase>, String> PLAYER_KEY_TABLE = ImmutableTable.<SkinType, Optional<PregnancyPhase>, String> builder()
			.put(SkinType.PREDEFINED, Optional.empty(), "pr_np")
			.put(SkinType.PREDEFINED, Optional.of(PregnancyPhase.P0), "pr_p0")
			.put(SkinType.PREDEFINED, Optional.of(PregnancyPhase.P1), "pr_p1")
			.put(SkinType.PREDEFINED, Optional.of(PregnancyPhase.P2), "pr_p2")
			.put(SkinType.PREDEFINED, Optional.of(PregnancyPhase.P3), "pr_p3")
			.put(SkinType.PREDEFINED, Optional.of(PregnancyPhase.P4), "pr_p4")
			.put(SkinType.PREDEFINED, Optional.of(PregnancyPhase.P5), "pr_p5")
			.put(SkinType.PREDEFINED, Optional.of(PregnancyPhase.P6), "pr_p6")
			.put(SkinType.PREDEFINED, Optional.of(PregnancyPhase.P7), "pr_p7")
			.put(SkinType.PREDEFINED, Optional.of(PregnancyPhase.P8), "pr_p8")
			.put(SkinType.CUSTOM, Optional.empty(), "cu_np")
			.put(SkinType.CUSTOM, Optional.of(PregnancyPhase.P0), "cu_p0")
			.put(SkinType.CUSTOM, Optional.of(PregnancyPhase.P1), "cu_p1")
			.put(SkinType.CUSTOM, Optional.of(PregnancyPhase.P2), "cu_p2")
			.put(SkinType.CUSTOM, Optional.of(PregnancyPhase.P3), "cu_p3")
			.put(SkinType.CUSTOM, Optional.of(PregnancyPhase.P4), "cu_p4")
			.put(SkinType.CUSTOM, Optional.of(PregnancyPhase.P5), "cu_p5")	
			.put(SkinType.CUSTOM, Optional.of(PregnancyPhase.P6), "cu_p6")
			.put(SkinType.CUSTOM, Optional.of(PregnancyPhase.P7), "cu_p7")
			.put(SkinType.CUSTOM, Optional.of(PregnancyPhase.P8), "cu_p8")
			.build();
	
    private static class Holder {
        private static final JigglePhysicsManager INSTANCE = new JigglePhysicsManager();
    }
    
    private JigglePhysicsManager() {
        this.entitiesPhysicsCache = new HashMap<>();
        this.configCache = new HashMap<>();   
    }
    
    public static JigglePhysicsManager getInstance() {
        return Holder.INSTANCE;
    }
    
    private EntityJiggleData getOrCreate(LivingEntity entity, Supplier<EntityJiggleData> factory) {
        return entitiesPhysicsCache.computeIfAbsent(entity.getUUID(), id -> factory.get());
    }
    
    public EntityJiggleData getOrCreate(Player player, Supplier<EntityJiggleData> factory) {
    	return getOrCreate((LivingEntity) player, factory);
    }
    
    public EntityJiggleData getOrCreate(PreggoMob preggoMob, Supplier<EntityJiggleData> factory) {
    	return getOrCreate((LivingEntity) preggoMob, factory);
    }
    
    @CheckForNull
    public EntityJiggleData get(Player playerId) {
        return entitiesPhysicsCache.get(playerId.getUUID());
    }
    
    public void set(Player playerId, EntityJiggleData data) {
        entitiesPhysicsCache.put(playerId.getUUID(), data);
    }
    
    private void removeJigglePhysics(LivingEntity entity) {
    	MinepreggoMod.LOGGER.debug("Removing jiggle physics for entity UUID={}", entity.getUUID());
        entitiesPhysicsCache.remove(entity.getUUID());
    }
    
    public void removeJigglePhysics(Player entity) {
    	removeJigglePhysics((LivingEntity) entity);
    }
    
    public void removeJigglePhysics(PreggoMob entity) {
    	removeJigglePhysics((LivingEntity) entity);
    }
    
    public void resetJigglePhysics(Player playerId) {
        EntityJiggleData data = entitiesPhysicsCache.get(playerId.getUUID());
        if (data != null) {
            data.reset();
        }
    }
    
    public void clearJigglePhysics() {
        entitiesPhysicsCache.clear();
    }

    public void updateJigglePhysics(Player entity, SkinType skinType, @Nullable PregnancyPhase phase) {
    	var uuid = entity.getUUID();
		if (entitiesPhysicsCache.containsKey(uuid)) {
			var config = getConfig(skinType, phase);
			if (config == null) {
				MinepreggoMod.LOGGER.warn("No jiggle physics config found for player UUID={}, skinType={}, pregnancyPhase={}", uuid, skinType, phase);
				return;
			}	
			MinepreggoMod.LOGGER.debug("Updating jiggle physics for player UUID={}, skinType={}, pregnancyPhase={}", uuid, skinType, phase);
			entitiesPhysicsCache.put(uuid, EntityJiggleDataFactory.create(config, phase));
		}
		else {
			MinepreggoMod.LOGGER.debug("No existing jiggle physics to update for player UUID={}, skinType={}, pregnancyPhase={}", uuid, skinType, phase);
		}
	}
    
    @CheckForNull
    public String getConfigKey(SkinType player, @Nullable PregnancyPhase phase) {
    	return PLAYER_KEY_TABLE.get(player, Optional.ofNullable(phase));
	}
    
    @CheckForNull
    public EntityJiggleDataFactory.JigglePositionConfig getConfig(SkinType player, @Nullable PregnancyPhase phase) {	
		String key = getConfigKey(player, phase);
		if (key == null) {
			return null;
		}
		return configCache.get(key);
    }
    
    public void putConfig(String key, EntityJiggleDataFactory.JigglePositionConfig config) {
		this.configCache.put(key, config);
	}
    
    public boolean hasConfig(String key) {
        return configCache.containsKey(key);
    }
}