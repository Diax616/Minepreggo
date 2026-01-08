package dev.dixmk.minepreggo.mixin;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import dev.dixmk.minepreggo.client.animation.player.PlayerAnimationManager;
import dev.dixmk.minepreggo.client.animation.player.PlayerAnimationManager.PlayerAnimationCache;
import dev.dixmk.minepreggo.common.animation.CommonPlayerAnimationRegistry;

import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

@Mixin(PlayerModel.class)
public class PlayerModelMixin<T extends LivingEntity> extends HumanoidModel<T> {

	@Shadow @Final public ModelPart leftSleeve;
	@Shadow @Final public ModelPart rightSleeve;
	@Shadow @Final public ModelPart leftPants;
	@Shadow @Final public ModelPart rightPants;
	@Shadow @Final public ModelPart jacket;
	
    public PlayerModelMixin(ModelPart p_170677_) {
		super(p_170677_);
	}
   
    @Inject(
            method = "setupAnim(Lnet/minecraft/world/entity/LivingEntity;FFFFF)V",
            at = @At("TAIL")
        )
    private void afterSetupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, CallbackInfo ci) {
       
    	/*
         * VISUAL BUG:
         * When a player triggers a labor animation (such as 'birth', 'prebirth', or 'water_breaking'),
         * the body and head of other players are visually displaced along the Z axis, even though those players are not running the animation.
         * This is visible to all clients: non-animated players appear to have their body and head shifted forward during the animation,
         * and return to normal when the animation ends. This only happens for ModelParts 'body', 'jacket', 'head', and 'hat'.
         *
         * BUG EXPLANATION:
         * In Minecraft/Forge, ModelPart instances (such as 'body' and 'head') are sometimes shared between player models during rendering.
         * When a custom animation (e.g., 'birth', 'prebirth', 'water_breaking') modifies the Z position of these ModelParts for one player,
         * the change can visually affect all other players that share the same ModelPart instance, even if they are not running the animation.
         * This results in the 'body' and 'head' of non-animated players being displaced in the Z axis when another player runs a labor animation.
         *
         * TEMPORARY SOLUTION:
         * As a workaround, after applying any labor animation, we forcibly reset the Z position of 'body', 'jacket', 'head', and 'hat' to 0
         * for all players who are NOT currently running a labor animation. This ensures that only the intended player is visually affected
         * by the animation, and all other players' models remain in their vanilla pose, even if ModelPart instances are shared internally.
         *
         * This workaround is necessary due to the internal model sharing in Minecraft/Forge and should be removed if/when the engine
         * guarantees unique ModelPart instances per player model.
         */
  	
        if (!entity.level().isClientSide) return;
              
        boolean isAnyLaborAnimActive = false;
        
        for (Player player : entity.level().players()) {
            PlayerAnimationCache cache = PlayerAnimationManager.getInstance().get(player);
            if (cache.hasActiveAnimation()) {
                String animationName = cache.getCurrentAnimationName();
                if (animationName != null && CommonPlayerAnimationRegistry.getInstance().isLaborAnimation(animationName)) {
                    isAnyLaborAnimActive = true;
                    break;
                }
            }
        }
        
        boolean isCurrentLaborAnim = false;
        if (entity instanceof Player player) {
            PlayerAnimationCache cache = PlayerAnimationManager.getInstance().get(player);
            if (cache.hasActiveAnimation()) {
                applyAnimation(cache);
                String animationName = cache.getCurrentAnimationName();
                if (animationName != null && CommonPlayerAnimationRegistry.getInstance().isLaborAnimation(animationName)) {
                    isCurrentLaborAnim = true;
                }
            }
            
            if (isAnyLaborAnimActive && !isCurrentLaborAnim) {
                body.z = 0;
                jacket.z = 0;
                head.z = 0;
                hat.z = 0;
            }
        }
    }
    
    private void copyTransform(ModelPart source, ModelPart target) {
        target.xRot = source.xRot;
        target.yRot = source.yRot;
        target.zRot = source.zRot;
        target.x = source.x;
        target.y = source.y;
        target.z = source.z;
    }
    
    private void register(PlayerAnimationCache cache) {
		cache.registerModelPart("head", head);
		cache.registerModelPart("body", body);
		cache.registerModelPart("right_arm", rightArm);
		cache.registerModelPart("left_arm", leftArm);
		cache.registerModelPart("right_leg", rightLeg);
		cache.registerModelPart("left_leg", leftLeg);
        			
		cache.registerModelPart("hat", head);
		cache.registerModelPart("jacket", body);
		cache.registerModelPart("rightSleeve", rightArm);
        cache.registerModelPart("leftSleeve", leftArm);
        cache.registerModelPart("rightPants", rightLeg);
        cache.registerModelPart("leftPants", leftLeg);
    }
    
    private void applyAnimation(PlayerAnimationCache cache) {
    	register(cache);
		cache.applyContinuousAnimation();
		copy();   	
    }
    
    private void copy() {
        copyTransform(head, hat);
        copyTransform(body, jacket);
        copyTransform(rightArm, rightSleeve);
        copyTransform(leftArm, leftSleeve);
        copyTransform(rightLeg, rightPants);
        copyTransform(leftLeg, leftPants);	
    }
}