package dev.dixmk.minepreggo.client.renderer.entity.layer.player;

import java.util.function.Consumer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import dev.dixmk.minepreggo.client.model.entity.player.AbstractHeavyPregnantBodyModel;
import dev.dixmk.minepreggo.client.model.entity.player.AbstractPregnantBodyModel;
import dev.dixmk.minepreggo.client.model.entity.player.CustomBoobsModel;
import dev.dixmk.minepreggo.client.model.entity.player.CustomPregnantBodyP0Model;
import dev.dixmk.minepreggo.client.model.entity.player.CustomPregnantBodyP1Model;
import dev.dixmk.minepreggo.client.model.entity.player.CustomPregnantBodyP2Model;
import dev.dixmk.minepreggo.client.model.entity.player.CustomPregnantBodyP3Model;
import dev.dixmk.minepreggo.client.model.entity.player.CustomPregnantBodyP4Model;
import dev.dixmk.minepreggo.client.model.entity.player.CustomPregnantBodyP5Model;
import dev.dixmk.minepreggo.client.model.entity.player.CustomPregnantBodyP6Model;
import dev.dixmk.minepreggo.client.model.entity.player.CustomPregnantBodyP7Model;
import dev.dixmk.minepreggo.client.model.entity.player.CustomPregnantBodyP8Model;
import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.world.pregnancy.PostPregnancy;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CustomPregnantBodyLayer extends AbstractPregnantBodyLayer {
	private final CustomBoobsModel boobsModel;
	private final CustomPregnantBodyP0Model pregnantBodyP0Model;
	private final CustomPregnantBodyP1Model pregnantBodyP1Model;
	private final CustomPregnantBodyP2Model pregnantBodyP2Model;
	private final CustomPregnantBodyP3Model pregnantBodyP3Model;
	private final CustomPregnantBodyP4Model pregnantBodyP4Model;
	private final CustomPregnantBodyP5Model pregnantBodyP5Model;
	private final CustomPregnantBodyP6Model pregnantBodyP6Model;
	private final CustomPregnantBodyP7Model pregnantBodyP7Model;
	private final CustomPregnantBodyP8Model pregnantBodyP8Model;

	public CustomPregnantBodyLayer(RenderLayerParent<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> parent, EntityModelSet modelSet) {
		super(parent);
        this.boobsModel = new CustomBoobsModel(modelSet.bakeLayer(CustomBoobsModel.LAYER_LOCATION));
        this.pregnantBodyP0Model = new CustomPregnantBodyP0Model(modelSet.bakeLayer(CustomPregnantBodyP0Model.LAYER_LOCATION));
        this.pregnantBodyP1Model = new CustomPregnantBodyP1Model(modelSet.bakeLayer(CustomPregnantBodyP1Model.LAYER_LOCATION));
        this.pregnantBodyP2Model = new CustomPregnantBodyP2Model(modelSet.bakeLayer(CustomPregnantBodyP2Model.LAYER_LOCATION));
        this.pregnantBodyP3Model = new CustomPregnantBodyP3Model(modelSet.bakeLayer(CustomPregnantBodyP3Model.LAYER_LOCATION));
        this.pregnantBodyP4Model = new CustomPregnantBodyP4Model(modelSet.bakeLayer(CustomPregnantBodyP4Model.LAYER_LOCATION));
        this.pregnantBodyP5Model = new CustomPregnantBodyP5Model(modelSet.bakeLayer(CustomPregnantBodyP5Model.LAYER_LOCATION));
        this.pregnantBodyP6Model = new CustomPregnantBodyP6Model(modelSet.bakeLayer(CustomPregnantBodyP6Model.LAYER_LOCATION));
        this.pregnantBodyP7Model = new CustomPregnantBodyP7Model(modelSet.bakeLayer(CustomPregnantBodyP7Model.LAYER_LOCATION));
        this.pregnantBodyP8Model = new CustomPregnantBodyP8Model(modelSet.bakeLayer(CustomPregnantBodyP8Model.LAYER_LOCATION));
	}

	@Override
	public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, 
			AbstractClientPlayer player, float limbSwing, float limbSwingAmount, float partialTick, 
			float ageInTicks, float netHeadYaw, float headPitch) {
	    	 
	
    	player.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap -> {   		
    		if (!cap.isUsingCustomSkin()) {
    			return;
    		}
    	
    		cap.getFemaleData().ifPresent(femaleData -> {				
    	        PlayerModel<AbstractClientPlayer> playerModel = this.getParentModel();       
    	        final VertexConsumer playerVertexConsumer = buffer.getBuffer(RenderType.entityCutoutNoCull(player.getSkinTextureLocation()));       
    	       
    	        if (femaleData.isPregnant()) {
        	        final var pregnancyPhase = femaleData.getPregnancySystem().getCurrentPregnancyStage();	        
  	      	
        	        Consumer<AbstractPregnantBodyModel> renderBellyAndBoobs = model -> {
        	        	model.setupAnim(player, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        	        	model.body.copyFrom(playerModel.body);
        	        	model.renderToBuffer(poseStack, playerVertexConsumer, packedLight, LivingEntityRenderer.getOverlayCoords(player, 0.0F), 1.0F, 1.0F, 1.0F, 1.0F);
        	        };     
        	        Consumer<AbstractHeavyPregnantBodyModel> renderBellyAndBoobsAndButt = model -> {  	
        	        	model.setupAnim(player, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        	        	model.body.copyFrom(playerModel.body);
        	        	model.leftLeg.copyFrom(playerModel.leftLeg);
        	        	model.rightLeg.copyFrom(playerModel.rightLeg);
        	        	model.renderToBuffer(poseStack, playerVertexConsumer, packedLight, LivingEntityRenderer.getOverlayCoords(player, 0.0F), 1.0F, 1.0F, 1.0F, 1.0F);
        	        	renderBellyAndBoobs.accept(model);
        	        };
       
        	        switch (pregnancyPhase) {
    				case P0: {			
        	        	renderBellyAndBoobs.accept(pregnantBodyP0Model);
    					return;
    				}
    				case P1: {
        	        	renderBellyAndBoobs.accept(pregnantBodyP1Model);
    					return;
    				}
    				case P2: {
        	        	renderBellyAndBoobs.accept(pregnantBodyP2Model);
    					return;
    				}
    				case P3: {
        	        	renderBellyAndBoobsAndButt.accept(pregnantBodyP3Model);
    					return;
    				}
    				case P4: {
        	        	renderBellyAndBoobsAndButt.accept(pregnantBodyP4Model);
    					return;
    				}
    				case P5: {
        	        	renderBellyAndBoobsAndButt.accept(pregnantBodyP5Model);
    					return;
    				}
    				case P6: {
        	        	renderBellyAndBoobsAndButt.accept(pregnantBodyP6Model);
    					return;
    				}
    				case P7: {
        	        	renderBellyAndBoobsAndButt.accept(pregnantBodyP7Model);
    					return;
    				}
    				case P8: {
        	        	renderBellyAndBoobsAndButt.accept(pregnantBodyP8Model);
    					return;
    				}
    				default:
    					throw new IllegalArgumentException("Unexpected value: " + pregnancyPhase);
    				}  
    	        } 	        
    	        else {	      	
					femaleData.getPostPregnancyData().ifPresent(post -> {
						if (post.getPostPregnancy() == PostPregnancy.PARTUM && post.getPostPartumLactation() >= PregnancySystemHelper.ACTIVATE_MILKING_SYMPTOM) {
							boobsModel.boobs.y -= 0.42F;		
							boobsModel.boobs.xScale = 1.4F;
							boobsModel.boobs.yScale = 1.2F;
							boobsModel.boobs.zScale = 1.3F;	
						}
					});
    	        						
    	        	boobsModel.setupAnim(player, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
    	        	boobsModel.body.copyFrom(playerModel.body);
    	        	boobsModel.renderToBuffer(poseStack, playerVertexConsumer, packedLight, LivingEntityRenderer.getOverlayCoords(player, 0.0F), 1.0F, 1.0F, 1.0F, 1.0F);
    	        }        
    		});	
    	});
	}
}
