package dev.dixmk.minepreggo.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.google.common.collect.ImmutableMap;

import org.spongepowered.asm.mixin.injection.At;

import dev.dixmk.minepreggo.client.animation.player.BellyAnimationManager;
import dev.dixmk.minepreggo.client.animation.preggo.BellyAnimation;
import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.init.MinepreggoModMobEffects;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;

@Mixin(Player.class)
public abstract class PlayerMixin {
 
	private static final ImmutableMap<PregnancyPhase, EntityDimensions> DIMENSIONS = ImmutableMap.of(
			PregnancyPhase.P3, EntityDimensions.scalable(0.6F, 1.8F),
			PregnancyPhase.P4, EntityDimensions.scalable(0.65F, 1.8F),
			PregnancyPhase.P5, EntityDimensions.scalable(0.7F, 1.8F),
			PregnancyPhase.P6, EntityDimensions.scalable(0.75F, 1.8F),
			PregnancyPhase.P7, EntityDimensions.scalable(0.85F, 1.8F),
			PregnancyPhase.P8, EntityDimensions.scalable(0.9F, 1.8F)
	);
	
    @Inject(method = "handleEntityEvent", at = @At("HEAD"), cancellable = true)
    private void onHandleEntityEvent(byte id, CallbackInfo ci) {   		
        Player self = (Player) (Object) this;       
        if (self.level().isClientSide) {
        	var animation = BellyAnimation.BELLY_SLAP_ANIMATION.get(id); 
            if (animation != null) {
            	BellyAnimationManager.getInstance().startAnimation(self, animation);
            	ci.cancel();
            }
        }
    } 
    
    @Inject(method = "jumpFromGround", at = @At("HEAD"), cancellable = true)
    private void cancelJump(CallbackInfo ci) {
    	Player player = Player.class.cast(this);
		player.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap -> 
			cap.getFemaleData().ifPresent(femaleData -> {
				if (femaleData.isPregnant() && femaleData.isPregnancySystemInitialized()) {			
					if (player.hasEffect(MinepreggoModMobEffects.MISCARRIAGE.get()) ||
							player.hasEffect(MinepreggoModMobEffects.PREBIRTH.get()) || 
							player.hasEffect(MinepreggoModMobEffects.BIRTH.get())) {
						ci.cancel();
					}
				}
			})
		); 
    }
    
    @Inject(method = "getDimensions", at = @At("RETURN"), cancellable = true)
    private void modificarDimensions(Pose pose, CallbackInfoReturnable<EntityDimensions> cir) {  
    	Player player = (Player) (Object) this;

    	player.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap -> 
    		cap.getFemaleData().ifPresent(femaleData -> {
    			if (femaleData.isPregnant() && femaleData.isPregnancySystemInitialized()) {
    				EntityDimensions dimensions = DIMENSIONS.get(femaleData.getPregnancySystem().getCurrentPregnancyStage());
    		        if (dimensions != null) {		            
    		            cir.setReturnValue(dimensions);  
    		        }
    			}
    		})
    	);
    }
}
