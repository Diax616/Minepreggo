package dev.dixmk.minepreggo.common.animation;

import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import java.util.Collections;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;


/**
 * This file is now a stub to prevent server-side loading issues.
 * If server/common code needs animation metadata, use AnimationInfo instead.
 * */
public class CommonPlayerAnimationRegistry {
    private final Map<String, AnimationInfo> animations;
    private static final String RUBBING_BELLY_ANIM = "rubbing_belly_p";    
    private static final Pattern RUBBING_BELLY_PATTERN = Pattern.compile("^rubbing_belly_p\\d+$");

    private CommonPlayerAnimationRegistry() {
    	animations = new HashMap<>();
    }

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
    	return name != null && RUBBING_BELLY_PATTERN.matcher(name).matches() && containsAnimation(name);
    }

    public boolean isLaborAnimation(String name) {
    	return name != null && (name.equals("birth") || name.equals("miscarriage") || name.equals("water_breaking") || name.equals("prebirth"));
    }
    
    public boolean containsAnimation(String name) {
    	if (name == null) return false;
		return animations.containsKey(name);
	}
}