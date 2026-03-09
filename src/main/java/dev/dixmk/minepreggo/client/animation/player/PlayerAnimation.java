package dev.dixmk.minepreggo.client.animation.player;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.ObjIntConsumer;

import dev.dixmk.minepreggo.common.animation.PlayerAnimationInfo;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PlayerAnimation {
	private final PlayerAnimationInfo info;
    private final Map<String, ObjIntConsumer<ModelPart>> timeBasedAnimationFunctions;
    
    private PlayerAnimation(PlayerAnimationInfo info) {
    	this.info = info;
    	this.timeBasedAnimationFunctions = new HashMap<>();
	}
    
    public static Builder builder(PlayerAnimationInfo info) {
    	return new Builder(info);
    }
    
    private void addPartAnimation(String partName, ObjIntConsumer<ModelPart> animFunction) {
        timeBasedAnimationFunctions.put(partName, animFunction);
    }

    public void applyAnimation(String partName, ModelPart part, int rawTick) {
        var timeBasedFunc = timeBasedAnimationFunctions.get(partName);
        if (timeBasedFunc != null) {
            timeBasedFunc.accept(part, rawTick);
        }
    }
    
    public PlayerAnimationInfo getInfo() {
		return info;
	}

    public Set<String> getAnimatedParts() {
        return timeBasedAnimationFunctions.keySet();
    }
    
    @OnlyIn(Dist.CLIENT)
    public static class Builder {
    	private PlayerAnimationInfo info;
        private final Map<String, ObjIntConsumer<ModelPart>> timeBasedAnimationFunctions = new HashMap<>();

    	public Builder(PlayerAnimationInfo info) {
			this.info = info;
		}
		
		public Builder addPartAnimation(String partName, ObjIntConsumer<ModelPart> animFunction) {
			timeBasedAnimationFunctions.put(partName, animFunction);
			return this;
		}
		
		public PlayerAnimation build() {
			var anim = new PlayerAnimation(info);
			timeBasedAnimationFunctions.forEach(anim::addPartAnimation);
			return anim;
		}
	}
}
