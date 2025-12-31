package dev.dixmk.minepreggo.common.animation;

// This file is now a stub to prevent server-side loading issues.
// If server/common code needs animation metadata, use AnimationInfo instead.

public class CommonPlayerAnimation {
    private final String name;
    private final int duration;
    private final boolean looping;  
    private final boolean overrideVanilla;
   
    public CommonPlayerAnimation(String name, int duration, boolean looping, boolean overrideVanilla) {
        this.name = name;
        this.duration = duration;
        this.looping = looping;
		this.overrideVanilla = overrideVanilla;
    }
    
    public CommonPlayerAnimation(String name, int duration, boolean looping) {
        this(name, duration, looping, true);
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
}