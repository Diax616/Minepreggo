package dev.dixmk.minepreggo.mixin;

import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.util.Mth;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;

import dev.dixmk.minepreggo.client.model.entity.player.cinematic.CinematicMalePlayerModel;
import dev.dixmk.minepreggo.client.model.entity.player.cinematic.CinematicPredefinedPlayerModelP6;
import dev.dixmk.minepreggo.init.MinepreggoCapabilities;

@Mixin(PlayerRenderer.class)
public abstract class PlayerRendererMixin extends LivingEntityRenderer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {

    private CinematicMalePlayerModel cinematicMaleModel;
    private CinematicPredefinedPlayerModelP6 cinematicFemaleModel;
	
    protected PlayerRendererMixin(EntityRendererProvider.Context context, 
                              PlayerModel<AbstractClientPlayer> model, float shadowRadius) {
        super(context, model, shadowRadius);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    private void onInit(EntityRendererProvider.Context context, boolean useSlimModel, CallbackInfo ci) {
        // Store the original model     
        EntityModelSet modelSet = context.getModelSet();
        this.cinematicMaleModel = new CinematicMalePlayerModel(modelSet.bakeLayer(CinematicMalePlayerModel.LAYER_LOCATION));
        this.cinematicFemaleModel = new CinematicPredefinedPlayerModelP6(modelSet.bakeLayer(CinematicPredefinedPlayerModelP6.LAYER_LOCATION));
    }

    @Inject(method = "render(Lnet/minecraft/client/player/AbstractClientPlayer;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V", 
            at = @At("HEAD"),
            cancellable = true)
    private void onRender(AbstractClientPlayer player, float partialTicks, float yaw, PoseStack poseStack, 
                         MultiBufferSource buffer, int packedLight, CallbackInfo ci) {
        
    	player.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap -> {
    		if (cap.isCinamatic()) {   			
    			if (cap.isFemale()) {
    				renderCinematicFemaleModel(player, partialTicks, yaw, poseStack, buffer, packedLight);
    			}
    			else {
    				renderCinematicMaleModel(player, partialTicks, yaw, poseStack, buffer, packedLight);
    			}
                ci.cancel();
    		}
    	});
    }
    
    private void renderCinematicFemaleModel(AbstractClientPlayer player, float partialTicks, float yaw, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {       
    	poseStack.pushPose();
    	
        float f = Mth.rotLerp(yaw, player.yBodyRotO, player.yBodyRot);
        float f1 = Mth.rotLerp(yaw, player.yHeadRotO, player.yHeadRot);
        float f2 = f1 - f;
    	
        // Copy the exact transformation sequence from PlayerRenderer
        this.setupRotations(player, poseStack, this.getBob(player, yaw), f, f2); 
        this.scale(player, poseStack, partialTicks);
    	
        poseStack.scale(-1.0F, -1.0F, 1.0F);
        poseStack.translate(0.0D, -1.501D, 0.0D);
                
        this.cinematicFemaleModel.prepareMobModel(player, partialTicks, yaw, packedLight);
		this.cinematicFemaleModel.setupAnim(player, partialTicks, 1.0F, player.tickCount + partialTicks, yaw, 0);
		
		VertexConsumer vertexconsumer = buffer.getBuffer(RenderType.entityTranslucent(CinematicPredefinedPlayerModelP6.TEXTURE_LOCATION));
		this.cinematicFemaleModel.renderToBuffer(poseStack, vertexconsumer, packedLight, LivingEntityRenderer.getOverlayCoords(player, 0.0F), 1.0F, 1.0F, 1.0F, 1.0F);
        
        poseStack.popPose();	
	}
    
    
    private void renderCinematicMaleModel(AbstractClientPlayer player, float partialTicks, float yaw, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
    	poseStack.pushPose();

        float f = Mth.rotLerp(yaw, player.yBodyRotO, player.yBodyRot);
        float f1 = Mth.rotLerp(yaw, player.yHeadRotO, player.yHeadRot);
        float f2 = f1 - f;
        
        // Copy the exact transformation sequence from PlayerRenderer
        this.setupRotations(player, poseStack, this.getBob(player, yaw), f, f2); 
        this.scale(player, poseStack, partialTicks);
    	
        poseStack.scale(-1.0F, -1.0F, 1.0F);
        poseStack.translate(0.0D, -1.501D, 0.0D);
        poseStack.mulPose(Axis.XP.rotationDegrees(-90)); // This rotates 180 degrees around Z-axis if needed
            
        this.cinematicMaleModel.prepareMobModel(player, partialTicks, yaw, packedLight);
		this.cinematicMaleModel.setupAnim(player, partialTicks, 0, player.tickCount + partialTicks, yaw, 0);

		VertexConsumer vertexconsumer = buffer.getBuffer(RenderType.entityTranslucent(CinematicMalePlayerModel.TEXTURE_LOCATION));
		this.cinematicMaleModel.renderToBuffer(poseStack, vertexconsumer, packedLight, LivingEntityRenderer.getOverlayCoords(player, 0.0F), 1.0F, 1.0F, 1.0F, 1.0F);
        
        poseStack.popPose();	
	}
}
