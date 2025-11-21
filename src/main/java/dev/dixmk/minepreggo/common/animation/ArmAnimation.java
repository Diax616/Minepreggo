package dev.dixmk.minepreggo.common.animation;

import com.mojang.blaze3d.vertex.PoseStack;

import dev.dixmk.minepreggo.common.utils.TriConsumer;
import net.minecraft.world.entity.HumanoidArm;

public class ArmAnimation {
	
    private final String name;
    private final long duration;
    private final TriConsumer<PoseStack, HumanoidArm, Float> anim;
    
    public ArmAnimation(String name, long duration, TriConsumer<PoseStack, HumanoidArm, Float> anim) {
        this.name = name;
        this.duration = duration;
		this.anim = anim;
    }
    
    public String getName() {
        return name;
    }
    
    public void apply(PoseStack poseStack, HumanoidArm arm, float partialTicks) {
        anim.accept(poseStack, arm, partialTicks);
    }
    
    public long getDuration() {
        return duration;
    }
}
