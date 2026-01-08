package dev.dixmk.minepreggo.common.animation;

import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import java.util.Collections;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

//This file is now a stub to prevent server-side loading issues.
//If server/common code needs animation metadata, use AnimationInfo instead.

public class CommonPlayerAnimationRegistry {
	
    private final Map<String, AnimationInfo> animations = new HashMap<>();

    private static final String RUBBING_BELLY_ANIM = "rubbing_belly_p";    
    
    private CommonPlayerAnimationRegistry() {}

    private static class Holder {
        private static final CommonPlayerAnimationRegistry INSTANCE = new CommonPlayerAnimationRegistry();
    }

    public static CommonPlayerAnimationRegistry getInstance() {
        return Holder.INSTANCE;
    }

    public void register(AnimationInfo animation) {
        animations.put(animation.name(), animation);
    }

    public AnimationInfo getAnimation(String name) {
        return animations.get(name);
    }

    public Collection<String> getAllAnimationNames() {
        return Collections.unmodifiableSet(animations.keySet());
    }

    public String getBellyRubbingAnimationName(PregnancyPhase phase) {
        return RUBBING_BELLY_ANIM + phase.ordinal();
    }

    public boolean isBellyRubbingAnimation(String name) {
        return name != null && name.startsWith(RUBBING_BELLY_ANIM);
    }

    public boolean isLaborAnimation(String name) {
    	return name != null && (name.equals("birth") || name.equals("miscarriage") || name.equals("water_breaking") || name.equals("prebirth"));
    }
}