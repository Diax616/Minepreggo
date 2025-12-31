package dev.dixmk.minepreggo.client.animation.player;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.ObjIntConsumer;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PlayerAnimation {
    private final String name;
    private final int duration;
    private final boolean looping;
    private final boolean overrideVanilla;
    private final Map<String, ObjIntConsumer<ModelPart>> timeBasedAnimationFunctions;

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

    public void addPartAnimation(String partName, ObjIntConsumer<ModelPart> animFunction) {
        timeBasedAnimationFunctions.put(partName, animFunction);
    }

    public void applyAnimation(String partName, ModelPart part, int rawTick) {
        var timeBasedFunc = timeBasedAnimationFunctions.get(partName);
        if (timeBasedFunc != null) {
            timeBasedFunc.accept(part, rawTick);
        }
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
        return timeBasedAnimationFunctions.keySet();
    }
}
