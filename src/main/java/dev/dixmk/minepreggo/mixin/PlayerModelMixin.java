package dev.dixmk.minepreggo.mixin;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import dev.dixmk.minepreggo.client.animation.player.PlayerAnimationManager;
import dev.dixmk.minepreggo.client.animation.player.PlayerAnimationManager.PlayerAnimationCache;

import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.world.entity.LivingEntity;

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
    	if (entity instanceof AbstractClientPlayer player) {
			PlayerAnimationCache cache = PlayerAnimationManager.getInstance().get(player);
			if (cache.hasActiveAnimation()) {
				applyAnimation(cache);
			}
		}
	}
    
    /*
	@Inject(
			method = "setupAnim(Lnet/minecraft/world/entity/LivingEntity;FFFFF)V",
			at = @At("HEAD"),
			cancellable = true
			)
		private void replaceSetupAnim(T entity, float limbSwing, float limbSwingAmount,
                                    float ageInTicks, float netHeadYaw, float headPitch, CallbackInfo ci) {
		if (entity instanceof AbstractClientPlayer player) {     		     
			PlayerAnimationCache cache = PlayerAnimationManager.getInstance().get(player);		
			if (cache.hasActiveAnimation() && !cache.getCurrentAnimationName().equals("water_breaking")) {
				ci.cancel();
				// Register all model parts (only stores original state if not already stored)
				applyAnimation(cache);			
			}
		}
	}
	*/
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
