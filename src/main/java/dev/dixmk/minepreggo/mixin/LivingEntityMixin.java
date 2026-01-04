package dev.dixmk.minepreggo.mixin;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.world.entity.player.PlayerHelper;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    
    @Inject(method = "getJumpPower", at = @At("RETURN"), cancellable = true)
    private void modifyJumpPower(CallbackInfoReturnable<Float> cir) {    
    	if ((LivingEntity.class.cast(this)) instanceof Player player) {
    		player.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap -> 
	    		cap.getFemaleData().ifPresent(femaleData -> {
	    			if (femaleData.isPregnant() && femaleData.isPregnancySystemInitialized()) {
	    				var phase = femaleData.getPregnancySystem().getCurrentPregnancyStage();
	    				if (phase.compareTo(PregnancyPhase.P3) >= 0) {  
	    					cir.setReturnValue(cir.getReturnValue() * PlayerHelper.maxJumpStrength(phase));
	    				}
	    			}
	    		})
        	);  
    	}
    }
}