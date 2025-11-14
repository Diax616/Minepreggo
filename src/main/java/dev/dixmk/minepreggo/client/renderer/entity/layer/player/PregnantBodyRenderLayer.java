package dev.dixmk.minepreggo.client.renderer.entity.layer.player;

import java.util.function.Consumer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import dev.dixmk.minepreggo.client.model.entity.player.AbstractHeavyPregnantBodyModel;
import dev.dixmk.minepreggo.client.model.entity.player.AbstractPregnantBodyModel;
import dev.dixmk.minepreggo.client.model.entity.player.BoobsModel;
import dev.dixmk.minepreggo.client.model.entity.player.PregnantBodyP1Model;
import dev.dixmk.minepreggo.client.model.entity.player.PregnantBodyP2Model;
import dev.dixmk.minepreggo.client.model.entity.player.PregnantBodyP3Model;
import dev.dixmk.minepreggo.client.model.entity.player.PregnantBodyP4Model;
import dev.dixmk.minepreggo.client.model.entity.player.PregnantBodyP5Model;
import dev.dixmk.minepreggo.client.model.entity.player.PregnantBodyP6Model;
import dev.dixmk.minepreggo.client.model.entity.player.PregnantBodyP7Model;
import dev.dixmk.minepreggo.client.model.entity.player.PregnantBodyP8Model;
import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.init.MinepreggoModMobEffects;
import dev.dixmk.minepreggo.network.capability.Gender;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PregnantBodyRenderLayer extends RenderLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {

	private final BoobsModel boobsModel;
	private final PregnantBodyP1Model pregnantBodyP1Model;
	private final PregnantBodyP2Model pregnantBodyP2Model;
	private final PregnantBodyP3Model pregnantBodyP3Model;
	private final PregnantBodyP4Model pregnantBodyP4Model;
	private final PregnantBodyP5Model pregnantBodyP5Model;
	private final PregnantBodyP6Model pregnantBodyP6Model;
	private final PregnantBodyP7Model pregnantBodyP7Model;
	private final PregnantBodyP8Model pregnantBodyP8Model;
	
	
	public PregnantBodyRenderLayer(RenderLayerParent<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> parent, EntityModelSet modelSet) {
		super(parent);
        this.boobsModel = new BoobsModel(modelSet.bakeLayer(BoobsModel.LAYER_LOCATION));
        this.pregnantBodyP1Model = new PregnantBodyP1Model(modelSet.bakeLayer(PregnantBodyP1Model.LAYER_LOCATION));
        this.pregnantBodyP2Model = new PregnantBodyP2Model(modelSet.bakeLayer(PregnantBodyP2Model.LAYER_LOCATION));
        this.pregnantBodyP3Model = new PregnantBodyP3Model(modelSet.bakeLayer(PregnantBodyP3Model.LAYER_LOCATION));
        this.pregnantBodyP4Model = new PregnantBodyP4Model(modelSet.bakeLayer(PregnantBodyP4Model.LAYER_LOCATION));
        this.pregnantBodyP5Model = new PregnantBodyP5Model(modelSet.bakeLayer(PregnantBodyP5Model.LAYER_LOCATION));
        this.pregnantBodyP6Model = new PregnantBodyP6Model(modelSet.bakeLayer(PregnantBodyP6Model.LAYER_LOCATION));
        this.pregnantBodyP7Model = new PregnantBodyP7Model(modelSet.bakeLayer(PregnantBodyP7Model.LAYER_LOCATION));
        this.pregnantBodyP8Model = new PregnantBodyP8Model(modelSet.bakeLayer(PregnantBodyP8Model.LAYER_LOCATION));
	}

    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, 
    		AbstractClientPlayer player, float limbSwing, float limbSwingAmount, float partialTick, 
                      float ageInTicks, float netHeadYaw, float headPitch) {
        
        if (!shouldRender(player)) {
            return;
        }             
        
        PlayerModel<AbstractClientPlayer> playerModel = this.getParentModel();
        VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.entityCutoutNoCull(player.getSkinTextureLocation()));

        Consumer<AbstractPregnantBodyModel> pregnantBody = model -> {
            model.body.copyFrom(playerModel.body);
            model.setupAnim(player, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
            model.renderToBuffer(poseStack, vertexConsumer, packedLight, LivingEntityRenderer.getOverlayCoords(player, 0.0F), 1.0F, 1.0F, 1.0F, 1.0F);
        };
        
        Consumer<AbstractHeavyPregnantBodyModel> heavyPregnantBody = model -> {
        	model.leftLeg.copyFrom(playerModel.leftLeg);
        	model.leftLeg.copyFrom(playerModel.rightLeg);
        	pregnantBody.accept(model);
        };
        
        if (player.hasEffect(MinepreggoModMobEffects.PREGNANCY_P1.get())) {
        	pregnantBody.accept(pregnantBodyP1Model);
        }
        else if (player.hasEffect(MinepreggoModMobEffects.PREGNANCY_P2.get())) {
        	pregnantBody.accept(pregnantBodyP2Model);
        }
        else {
            this.boobsModel.body.copyFrom(playerModel.body);
            this.boobsModel.setupAnim(player, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
            this.boobsModel.renderToBuffer(poseStack, vertexConsumer, packedLight, LivingEntityRenderer.getOverlayCoords(player, 0.0F), 1.0F, 1.0F, 1.0F, 1.0F);
        }
    }
	

    
    private boolean shouldRender(Player player) {
    	var cap = player.getCapability(MinepreggoCapabilities.PLAYER_DATA).resolve(); 	
    	if (cap.isPresent()) {
    		return cap.get().getGender() == Gender.FEMALE;
    	} 
        return false; 
    }
}
