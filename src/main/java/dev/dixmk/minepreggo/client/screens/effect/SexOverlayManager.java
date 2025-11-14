package dev.dixmk.minepreggo.client.screens.effect;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SexOverlayManager {
	
	private SexOverlayManager() {}

	private static class Holder {
		private static final  SexOverlayManager INSTANCE = new SexOverlayManager();
	}
	
	public static SexOverlayManager getInstance() {
		return Holder.INSTANCE;
	}
	
    private int overlayTimer = 0;    
    private int pauseTimer = 0; 
   
    private boolean isActive = false;
    private boolean isFirstLoop = true;
    private boolean isPause = false;
    
    public void tick() {
    	
    	if (!isActive()) return;
    	
    	
    	if (isFirstLoop) {
        	if (overlayTimer < 120) {
        		overlayTimer++;
        	}     
        	else {           		
        		isFirstLoop = false;
        		isPause = true;
        	}
    	}
    	else if (isPause) {
        	if (pauseTimer < 60) {
        		pauseTimer++;
        	}     
        	else {           		
        		isPause = false;
        	}
    	}
    	else {
        	if (overlayTimer > 0) {
        		overlayTimer--;
        	} 
        	else {
        		reset();
        	}
    	} 
    }
    
    public void trigger() {
    	isActive = true;
    }

    public float getAlpha() {
        if (overlayTimer <= 0) return 0.0F;
     
        float f =  overlayTimer /(float) 120;
        return Math.min(f, 1.0F);
    }

    public boolean isActive() {
        return isActive;
    }
    
    public void reset() {
        isActive = false;
        isPause = false;
        isFirstLoop = true;
        overlayTimer = 0;
        pauseTimer = 0;
    }
}
