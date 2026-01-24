package dev.dixmk.minepreggo.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.google.common.collect.ImmutableMap;

import org.spongepowered.asm.mixin.injection.At;

import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.init.MinepreggoModMobEffects;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import it.unimi.dsi.fastutil.objects.Object2FloatMap;
import it.unimi.dsi.fastutil.objects.Object2FloatMaps;
import it.unimi.dsi.fastutil.objects.Object2FloatOpenHashMap;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;

@Mixin(Player.class)
public abstract class PlayerMixin extends Entity {
 
	// Dummy constructor required for extending Entity
	protected PlayerMixin() {
		super(null, null);
	}

	@Unique
	private PregnancyPhase minepreggo$lastPregnancyPhase = null;
	
	private static final Object2FloatMap<PregnancyPhase> WIDTH = Object2FloatMaps.unmodifiable(new Object2FloatOpenHashMap<>(ImmutableMap.of(
			PregnancyPhase.P3, 0.625F,
			PregnancyPhase.P4, 0.625F,
			PregnancyPhase.P5, 0.65F,
			PregnancyPhase.P6, 0.65F,
			PregnancyPhase.P7, 0.7F,
			PregnancyPhase.P8, 0.7F
	)));
    
    @Inject(method = "jumpFromGround", at = @At("HEAD"), cancellable = true)
    private void cancelJump(CallbackInfo ci) {
    	Player player = Player.class.cast(this);
		player.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap -> 
			cap.getFemaleData().ifPresent(femaleData -> {
				if (femaleData.isPregnant() && femaleData.isPregnancyDataInitialized()) {			
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
    private void modifyDimension(Pose pose, CallbackInfoReturnable<EntityDimensions> cir) {  
    	Player player = (Player) (Object) this;

    	player.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap -> 
    		cap.getFemaleData().ifPresent(femaleData -> {
    			if (femaleData.isPregnant() && femaleData.isPregnancyDataInitialized()) {
    				EntityDimensions vanillaDimensions = cir.getReturnValue();
    		        float newWidth = WIDTH.getFloat(femaleData.getPregnancyData().getCurrentPregnancyPhase());

    				if (newWidth != 0) {		            
    		            cir.setReturnValue(EntityDimensions.scalable(newWidth, vanillaDimensions.height));  
    		        }
    			}
    		})
    	);
    }

    @Inject(method = "tick", at = @At("TAIL"))
    private void onTick(CallbackInfo ci) {
        Player player = (Player) (Object) this;
        
        player.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap -> 
            cap.getFemaleData().ifPresent(femaleData -> {
                if (femaleData.isPregnant() && femaleData.isPregnancyDataInitialized()) {
                    PregnancyPhase currentPhase = femaleData.getPregnancyData().getCurrentPregnancyPhase();
                    
                    // Check if pregnancy phase has changed
                    if (minepreggo$lastPregnancyPhase != currentPhase) {
                        minepreggo$lastPregnancyPhase = currentPhase;
                        // Force dimension recalculation by refreshing dimensions
                        player.refreshDimensions();
                    }
                } else if (minepreggo$lastPregnancyPhase != null) {
                    // Player is no longer pregnant, reset dimensions
                    minepreggo$lastPregnancyPhase = null;
                    player.refreshDimensions();
                }
            })
        );
    }
}
