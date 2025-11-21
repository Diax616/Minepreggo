package dev.dixmk.minepreggo.common.animation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import dev.dixmk.minepreggo.common.utils.TriConsumer;
import net.minecraft.client.model.geom.ModelPart;


public class PlayerAnimation {
    private final String name;
    private final int duration;
    private final boolean looping;  
    private final boolean overrideVanilla;
    private final Map<String, TriConsumer<ModelPart, Integer, Float>> timeBasedAnimationFunctions; 
   
    public PlayerAnimation(String name, int duration, boolean looping, boolean overrideVanilla) {
        this.name = name;
        this.duration = duration;
        this.looping = looping;
		this.overrideVanilla = overrideVanilla;
        this.timeBasedAnimationFunctions = new HashMap<>();
    }
    
    public PlayerAnimation(String name, int duration, boolean looping) {
        this(name, duration, looping, true);
    }
    
    public void addPartAnimation(String partName, TriConsumer<ModelPart, Integer, Float> animFunction) {
        timeBasedAnimationFunctions.put(partName, animFunction);
    }
     
    public void applyAnimation(String partName, ModelPart part, float animationProgress, int rawTick) {
        // Apply time-based animation if present
        var timeBasedFunc = timeBasedAnimationFunctions.get(partName);
        if (timeBasedFunc != null) {
        	timeBasedFunc.accept(part, rawTick, animationProgress);
        }
    }

    public void applyAnimation(String partName, ModelPart part, float animationProgress) {
        applyAnimation(partName, part, animationProgress, 0); // Default raw tick to 0
    }
    
    public String getName() {
        return name;
    }
    
    public int getDuration() {
        return duration;
    }
    
    public boolean isLooping() {
        return looping;
    }
    
    public boolean shouldOverrideVanilla() {
        return overrideVanilla;
    }
    
    public Set<String> getAnimatedParts() {
        Set<String> allParts = new HashSet<>();
        allParts.addAll(timeBasedAnimationFunctions.keySet());
        return allParts;
    }
}


