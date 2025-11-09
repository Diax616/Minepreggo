package dev.dixmk.minepreggo.client.renderer.entity.layer.player;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import dev.dixmk.minepreggo.client.model.entity.player.BoobsModel;
import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.network.capability.Gender;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BoobsRenderLayer extends RenderLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {

	private final BoobsModel boobsModel;
	    
    public BoobsRenderLayer(RenderLayerParent<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> parent, EntityModelSet modelSet) {
        super(parent);
        this.boobsModel = new BoobsModel(modelSet.bakeLayer(BoobsModel.LAYER_LOCATION));
    }
    
    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, 
    		AbstractClientPlayer player, float limbSwing, float limbSwingAmount, float partialTick, 
                      float ageInTicks, float netHeadYaw, float headPitch) {
        
        if (!shouldRenderBoobs(player)) {
            return;
        }             
        PlayerModel<AbstractClientPlayer> playerModel = this.getParentModel();
        
        this.boobsModel.body.copyFrom(playerModel.body);
        
        this.boobsModel.setupAnim(player, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
  
        VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.entityCutoutNoCull(player.getSkinTextureLocation()));
        this.boobsModel.renderToBuffer(poseStack, vertexConsumer, packedLight, LivingEntityRenderer.getOverlayCoords(player, 0.0F), 1.0F, 1.0F, 1.0F, 1.0F);
    }
    
    private boolean shouldRenderBoobs(Player player) {
    	var cap = player.getCapability(MinepreggoCapabilities.PLAYER_DATA).resolve(); 	
    	if (cap.isPresent()) {
    		return player.getItemBySlot(EquipmentSlot.CHEST).isEmpty() && cap.get().getGender() == Gender.FEMALE;
    	} 
        return false; 
    }
}
