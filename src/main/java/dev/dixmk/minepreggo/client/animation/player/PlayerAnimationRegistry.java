package dev.dixmk.minepreggo.client.animation.player;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.CheckForNull;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PlayerAnimationRegistry {
    private final Map<String, PlayerAnimation> animations;
    
	private PlayerAnimationRegistry() {
    	animations = new HashMap<>();
    }

    private static class Holder {
        private static final PlayerAnimationRegistry INSTANCE = new PlayerAnimationRegistry();
    }

    public static PlayerAnimationRegistry getInstance() {
        return Holder.INSTANCE;
    }

    public void register(PlayerAnimation animation) {
        animations.put(animation.getInfo().name(), animation);
    }

    @CheckForNull
    public PlayerAnimation getAnimation(String name) {
        return animations.get(name);
    }
}
