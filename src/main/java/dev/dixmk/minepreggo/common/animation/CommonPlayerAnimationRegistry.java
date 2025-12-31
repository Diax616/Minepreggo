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
        return "rubbing_belly_p" + phase.ordinal();
    }

    public boolean isBellyRubbingAnimation(String name) {
        return name != null && name.startsWith("rubbing_belly_p");
    }
}