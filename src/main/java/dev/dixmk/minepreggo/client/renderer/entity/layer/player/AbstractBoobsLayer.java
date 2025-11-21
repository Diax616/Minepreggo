package dev.dixmk.minepreggo.client.renderer.entity.layer.player;

import org.checkerframework.checker.nullness.qual.NonNull;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import dev.dixmk.minepreggo.client.model.entity.player.AbstractBoobsModel;
import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractBoobsLayer extends RenderLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {

	protected AbstractBoobsLayer(RenderLayerParent<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> parent) {
		super(parent);
	}

    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, 
    		AbstractClientPlayer player, float limbSwing, float limbSwingAmount, float partialTick, 
                      float ageInTicks, float netHeadYaw, float headPitch) {
        	  	
    	player.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap -> 
    		cap.getFemaleData().ifPresent(femaleData -> {
    	        PlayerModel<AbstractClientPlayer> playerModel = this.getParentModel();
    	        VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.entityCutoutNoCull(player.getSkinTextureLocation()));
    	        
    	        final var model = getBoobsModel();
    	        
    	        model.body.copyFrom(playerModel.body);      
    	        model.setupAnim(player, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
    	        model.renderToBuffer(poseStack, vertexConsumer, packedLight, LivingEntityRenderer.getOverlayCoords(player, 0.0F), 1.0F, 1.0F, 1.0F, 1.0F);		
    		})
    	);
    }
    
    
    protected abstract @NonNull AbstractBoobsModel getBoobsModel();

}
