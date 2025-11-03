package dev.dixmk.minepreggo.client.screen.effect;

public class SexOverlayManager {
	
	private SexOverlayManager() {}

    private static int overlayTimer = 0;    
    private static int pauseTimer = 0; 
    
    
    private static boolean isActive = false;
    private static boolean isFirstLoop = true;
    private static boolean isPause = false;
    
    public static void tick() {
    	
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
    
    public static void trigger() {
    	isActive = true;
    }

    public static float getAlpha() {
        if (overlayTimer <= 0) return 0.0F;
     
        float f =  overlayTimer /(float) 120;
        return Math.min(f, 1.0F);
    }

    public static boolean isActive() {
        return isActive;
    }
    
    public static void reset() {
        isActive = false;
        isPause = false;
        isFirstLoop = true;
        overlayTimer = 0;
        pauseTimer = 0;
    }
}
