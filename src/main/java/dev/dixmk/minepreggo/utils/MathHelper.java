package dev.dixmk.minepreggo.utils;

import net.minecraft.util.Mth;

public class MathHelper {
	
	private MathHelper() {}

	public static float sigmoid(float pMin, float pMax, float k, float t, float t0) {
		return (pMin + (pMax - pMin) / (float) (1 + Math.exp(-k * (t - t0))));
	}
	
	public static float animateBetweenAnglesMth(float minAngle, float maxAngle, float time, float speed) {
		return animateBetweenAnglesMth(minAngle, maxAngle, time, speed, 0F);
	}
	
	public static float animateBetweenAnglesMth(float minAngle, float maxAngle, float time, float speed, float offset) {
	    
		float t = time * speed + offset;
	
		float minRad = (float) Math.toRadians(minAngle);
	    float maxRad = (float) Math.toRadians(maxAngle);
	    
	    float sineValue = Mth.sin(t);
	    float normalized = (sineValue + 1.0f) / 2.0f;
	    
	    return minRad + (maxRad - minRad) * normalized;
	}
	
}
