package dev.dixmk.minepreggo.mixin;

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

	@Shadow public ModelPart leftSleeve;
	@Shadow public ModelPart rightSleeve;
	@Shadow public ModelPart leftPants;
	@Shadow public ModelPart rightPants;
	@Shadow public ModelPart jacket;
    
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
		cache.registerModelPart("head", this.head);
		cache.registerModelPart("body", this.body);
		cache.registerModelPart("right_arm", this.rightArm);
		cache.registerModelPart("left_arm", this.leftArm);
		cache.registerModelPart("right_leg", this.rightLeg);
		cache.registerModelPart("left_leg", this.leftLeg);
        			
		cache.registerModelPart("hat", this.head);
		cache.registerModelPart("jacket", this.body);
		cache.registerModelPart("rightSleeve", this.rightArm);
        cache.registerModelPart("leftSleeve", this.leftArm);
        cache.registerModelPart("rightPants", this.rightLeg);
        cache.registerModelPart("leftPants", this.leftLeg);
    }
    
    private void applyAnimation(PlayerAnimationCache cache) {
    	register(cache);
		cache.applyContinuousAnimation();
		copy();   	
    }
    
    private void copy() {
        copyTransform(this.head, this.hat);
        copyTransform(this.body, this.jacket);
        copyTransform(this.rightArm, this.rightSleeve);
        copyTransform(this.leftArm, this.leftSleeve);
        copyTransform(this.rightLeg, this.rightPants);
        copyTransform(this.leftLeg, this.leftPants);	
    }
}
