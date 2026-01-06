package dev.dixmk.minepreggo.mixin;

import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.resources.ResourceLocation;

import org.apache.commons.lang3.tuple.Pair;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.client.renderer.entity.layer.player.ClientPlayerHelper;
import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.world.entity.player.SkinType;

@Mixin(AbstractClientPlayer.class)
public class AbstractClientPlayerMixin {
        
    @Inject(method = "getSkinTextureLocation", at = @At("HEAD"), cancellable = true)
    private void changeSkin(CallbackInfoReturnable<ResourceLocation> cir) {
        AbstractClientPlayer player = (AbstractClientPlayer) (Object) this; 
    	player.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap -> {
    		if (cap.getSkinType() == SkinType.CUSTOM) {
    			return;
    		}
    		
    		cap.getFemaleData().ifPresent(femaleData -> { 	
    			final Pair<ResourceLocation, ResourceLocation> textures;	
    			if (femaleData.isPregnant() && femaleData.isPregnancySystemInitialized()) {
        			final var pregnancyPhase =  femaleData.getPregnancySystem().getCurrentPregnancyStage();
    				textures = ClientPlayerHelper.getPredefinedPlayerTextures("player1", pregnancyPhase);
        			if (textures == null) {
        	        	MinepreggoMod.LOGGER.error("{} in pregnancy phase {} does not have a valid texture", player.getDisplayName().getString(), pregnancyPhase);
        	        	return;
        			}
    			}   	
    			else {
    				textures = ClientPlayerHelper.getPredefinedPlayerTextures("player1");
    			}	
    			cir.setReturnValue(textures.getLeft());	
    		});		
    	}); 
    }
}
