package dev.dixmk.minepreggo.client;

import net.minecraft.client.player.LocalPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CinematicManager {
	
	private CinematicManager() {}
	
    private static class Holder {
        private static final CinematicManager INSTANCE = new CinematicManager();
    }
       	    
    public static CinematicManager getInstance() {
        return Holder.INSTANCE;
    }
	
    private boolean isInCinematic = false;
    private int activeCinematicMobId = -1;
    private float storedYaw = 0.0f;
    private float storedPitch = 0.0f;

    public void startCinematic(LocalPlayer player, int mobEntityId) {
        isInCinematic = true;
        activeCinematicMobId = mobEntityId;
        storedYaw = player.getYRot();
        storedPitch = player.getXRot();
    }

    public void startCinematicWithPlayer(LocalPlayer player) {
        isInCinematic = true;
        storedYaw = player.getYRot();
        storedPitch = player.getXRot();
    }
    
    public void endCinematic() {
        isInCinematic = false;
        activeCinematicMobId = -1;
    }
    
    public void endCinematicWithPlayer() {
        isInCinematic = false;
    }

    public boolean isInCinematic() {
        return isInCinematic;
    }

    public int getActiveMobId() {
        return activeCinematicMobId;
    }

    public float getStoredYaw() { 
    	return storedYaw; 
    }
    
    public float getStoredPitch() { 
    	return storedPitch; 
    }
}
