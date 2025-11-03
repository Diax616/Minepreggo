package dev.dixmk.minepreggo.client;

import net.minecraft.client.player.LocalPlayer;

public class SexCinematicManager {
	
	private SexCinematicManager() {}
	
    private static boolean isInCinematic = false;
    private static int activeCinematicMobId = -1;
    private static float storedYaw = 0.0f;
    private static float storedPitch = 0.0f;

    public static void startCinematic(LocalPlayer player, int mobEntityId) {
        isInCinematic = true;
        activeCinematicMobId = mobEntityId;
        storedYaw = player.getYRot();
        storedPitch = player.getXRot();
    }

    public static void endCinematic() {
        isInCinematic = false;
        activeCinematicMobId = -1;
    }

    public static boolean isInCinematic() {
        return isInCinematic;
    }

    public static int getActiveMobId() {
        return activeCinematicMobId;
    }

    public static float getStoredYaw() { return storedYaw; }
    public static float getStoredPitch() { return storedPitch; }
	
}
