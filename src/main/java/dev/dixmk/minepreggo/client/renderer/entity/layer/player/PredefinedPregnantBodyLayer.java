package dev.dixmk.minepreggo.client.renderer.entity.layer.player;

import java.util.function.Consumer;

import org.apache.commons.lang3.tuple.ImmutablePair;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.client.model.entity.player.AbstractHeavyPregnantBodyModel;
import dev.dixmk.minepreggo.client.model.entity.player.AbstractPregnantBodyModel;
import dev.dixmk.minepreggo.client.model.entity.player.PredefinedBoobsModel;
import dev.dixmk.minepreggo.client.model.entity.player.PredefinedPregnantBodyP0Model;
import dev.dixmk.minepreggo.client.model.entity.player.PredefinedPregnantBodyP1Model;
import dev.dixmk.minepreggo.client.model.entity.player.PredefinedPregnantBodyP2Model;
import dev.dixmk.minepreggo.client.model.entity.player.PredefinedPregnantBodyP3Model;
import dev.dixmk.minepreggo.client.model.entity.player.PredefinedPregnantBodyP4Model;
import dev.dixmk.minepreggo.client.model.entity.player.PredefinedPregnantBodyP5Model;
import dev.dixmk.minepreggo.client.model.entity.player.PredefinedPregnantBodyP6Model;
import dev.dixmk.minepreggo.client.model.entity.player.PredefinedPregnantBodyP7Model;
import dev.dixmk.minepreggo.client.model.entity.player.PredefinedPregnantBodyP8Model;
import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.world.entity.player.SkinType;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


@OnlyIn(Dist.CLIENT)
public class PredefinedPregnantBodyLayer extends AbstractPregnantBodyLayer {	
	public final PredefinedBoobsModel boobsModel;
	public final PredefinedPregnantBodyP0Model pregnantBodyP0Model;
	public final PredefinedPregnantBodyP1Model pregnantBodyP1Model;
	public final PredefinedPregnantBodyP2Model pregnantBodyP2Model;
	public final PredefinedPregnantBodyP3Model pregnantBodyP3Model;
	public final PredefinedPregnantBodyP4Model pregnantBodyP4Model;
	public final PredefinedPregnantBodyP5Model pregnantBodyP5Model;
	public final PredefinedPregnantBodyP6Model pregnantBodyP6Model;
	public final PredefinedPregnantBodyP7Model pregnantBodyP7Model;
	public final PredefinedPregnantBodyP8Model pregnantBodyP8Model;
	
	public PredefinedPregnantBodyLayer(RenderLayerParent<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> parent, EntityModelSet modelSet) {
		super(parent);
        this.boobsModel = new PredefinedBoobsModel(modelSet.bakeLayer(PredefinedBoobsModel.LAYER_LOCATION));
        this.pregnantBodyP0Model = new PredefinedPregnantBodyP0Model(modelSet.bakeLayer(PredefinedPregnantBodyP0Model.LAYER_LOCATION));
        this.pregnantBodyP1Model = new PredefinedPregnantBodyP1Model(modelSet.bakeLayer(PredefinedPregnantBodyP1Model.LAYER_LOCATION));
        this.pregnantBodyP2Model = new PredefinedPregnantBodyP2Model(modelSet.bakeLayer(PredefinedPregnantBodyP2Model.LAYER_LOCATION));
        this.pregnantBodyP3Model = new PredefinedPregnantBodyP3Model(modelSet.bakeLayer(PredefinedPregnantBodyP3Model.LAYER_LOCATION));
        this.pregnantBodyP4Model = new PredefinedPregnantBodyP4Model(modelSet.bakeLayer(PredefinedPregnantBodyP4Model.LAYER_LOCATION));
        this.pregnantBodyP5Model = new PredefinedPregnantBodyP5Model(modelSet.bakeLayer(PredefinedPregnantBodyP5Model.LAYER_LOCATION));
        this.pregnantBodyP6Model = new PredefinedPregnantBodyP6Model(modelSet.bakeLayer(PredefinedPregnantBodyP6Model.LAYER_LOCATION));
        this.pregnantBodyP7Model = new PredefinedPregnantBodyP7Model(modelSet.bakeLayer(PredefinedPregnantBodyP7Model.LAYER_LOCATION));
        this.pregnantBodyP8Model = new PredefinedPregnantBodyP8Model(modelSet.bakeLayer(PredefinedPregnantBodyP8Model.LAYER_LOCATION));
	}

	@Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, 
    		AbstractClientPlayer player, float limbSwing, float limbSwingAmount, float partialTick, 
                      float ageInTicks, float netHeadYaw, float headPitch) {
    	  	
    	player.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap -> {   		
    		if (cap.getSkinType() == SkinType.CUSTOM) {
    			return;
    		}
    	
    		cap.getFemaleData().ifPresent(femaleData -> {	
    		
    	        final ImmutablePair<ResourceLocation, ResourceLocation> textures;
    	        PlayerModel<AbstractClientPlayer> playerModel = this.getParentModel(); 
    	        
    			if (femaleData.isPregnant() && femaleData.isPregnancyDataInitialized()) {
        	        final var pregnancyPhase = femaleData.getPregnancyData().getCurrentPregnancyStage();	        
    				textures = ClientPlayerHelper.getPredefinedPlayerTextures("player1", pregnancyPhase);
    			
        	        if (textures == null) {
        	        	MinepreggoMod.LOGGER.error("{} in pregnancy phase {} does not have a valid texture", player.getDisplayName().getString(), pregnancyPhase);
        	        	return;
        	        }
    				
        	        final var bellySkin = textures.getRight();
        	        final VertexConsumer bellyAndBoobsVertexConsumer = buffer.getBuffer(RenderType.entityCutoutNoCull(bellySkin));           	          	        
     
        	        Consumer<AbstractPregnantBodyModel> renderBellyAndBoobs = model -> {
        	        	model.setupAnim(player, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        	        	model.body.copyFrom(playerModel.body);
        	        	model.renderToBuffer(poseStack, bellyAndBoobsVertexConsumer, packedLight, LivingEntityRenderer.getOverlayCoords(player, 0.0F), 1.0F, 1.0F, 1.0F, 1.0F);
        	        };    	        
        	        Consumer<AbstractHeavyPregnantBodyModel> renderBellyAndBoobsAndButt = model -> {  	
        	        	model.setupAnim(player, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        	        	model.body.copyFrom(playerModel.body);
        	        	model.leftLeg.copyFrom(playerModel.leftLeg);
        	        	model.rightLeg.copyFrom(playerModel.rightLeg);
        	        	model.renderToBuffer(poseStack, bellyAndBoobsVertexConsumer, packedLight, LivingEntityRenderer.getOverlayCoords(player, 0.0F), 1.0F, 1.0F, 1.0F, 1.0F);
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
    					MinepreggoMod.LOGGER.error("Unsupported pregnancy phase {} for {}", pregnancyPhase, player.getDisplayName().getString());
    					return;
        	        } 	
    			}
    			else {
    				textures = ClientPlayerHelper.getPredefinedPlayerTextures("player1");
        	        			
        	        if (textures == null) {
        	        	MinepreggoMod.LOGGER.error("{} does not have a valid texture", player.getDisplayName().getString());
        	        	return;
        	        }
    				
    				final VertexConsumer boobsVertexConsumer = buffer.getBuffer(RenderType.entityCutoutNoCull(textures.getRight()));           	          	        
				
    	        	boobsModel.setupAnim(player, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
    	        	boobsModel.body.copyFrom(playerModel.body);
    	        	boobsModel.renderToBuffer(poseStack, boobsVertexConsumer, packedLight, LivingEntityRenderer.getOverlayCoords(player, 0.0F), 1.0F, 1.0F, 1.0F, 1.0F);
    			}
    		});	
    	}); 	
    }	
}
