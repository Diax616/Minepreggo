package dev.dixmk.minepreggo.server;

import java.util.HashMap;
import java.util.Map;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class ServerSexCinematicManager {
	
	private ServerSexCinematicManager() {}
	
    private static final Set<UUID> playersInCinematic = new HashSet<>();

    public static void start(ServerPlayer player) {	
        playersInCinematic.add(player.getUUID());
        
        double speed = player.getAttributeBaseValue(Attributes.MOVEMENT_SPEED);
        SexCinematicData.setSpeed(player.getUUID(), speed);

        player.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.0);
        player.getAbilities().flying = false;
    }

    public static void end(ServerPlayer player) {
        playersInCinematic.remove(player.getUUID());
        
        Double original = SexCinematicData.getSpeed(player.getUUID());
        if (original != null) {
            player.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(original);
            SexCinematicData.clear(player.getUUID());
        }
    }

    public static boolean isInCinematic(ServerPlayer player) {
        return playersInCinematic.contains(player.getUUID());          
    }
    
    public static class SexCinematicData {
        private static final Map<UUID, Double> SPEED_MAP = new HashMap<>();

        private SexCinematicData() {}
        
        public static void setSpeed(UUID uuid, double speed) {
            SPEED_MAP.put(uuid, speed);
        }

        public static Double getSpeed(UUID uuid) {
            return SPEED_MAP.get(uuid);
        }

        public static void clear(UUID uuid) {
            SPEED_MAP.remove(uuid);
        }
    }
}

