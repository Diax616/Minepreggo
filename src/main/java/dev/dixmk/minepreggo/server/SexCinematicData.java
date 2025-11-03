package dev.dixmk.minepreggo.server;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SexCinematicData {
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
