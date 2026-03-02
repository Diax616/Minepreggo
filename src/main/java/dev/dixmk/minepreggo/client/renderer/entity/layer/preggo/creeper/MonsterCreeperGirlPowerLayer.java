package dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.creeper;

import com.mojang.blaze3d.vertex.PoseStack;

import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractMonsterCreeperGirlModel;
import dev.dixmk.minepreggo.utils.MinepreggoHelper;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractCreeperGirl;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EnergySwirlLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MonsterCreeperGirlPowerLayer
	<E extends AbstractCreeperGirl, M extends AbstractMonsterCreeperGirlModel<E>> extends EnergySwirlLayer<E, M> {

	private static final ResourceLocation POWER_LOCATION = MinepreggoHelper.fromThisNamespaceAndPath("textures/entity/preggo/creeper/armor.png");
	private final M model;
	
	public MonsterCreeperGirlPowerLayer(RenderLayerParent<E, M> renderer, M model) {
		super(renderer);
		this.model = model;
	}
	
	@Override
	public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, E entity, float limbSwing, float limbSwingAmount, float partialTick, float ageInTicks, float netHeadYaw, float headPitch) {
		if (entity.isPowered()) {
			poseStack.pushPose();
			poseStack.scale(1.05F, 1.05F, 1.05F);
			super.render(poseStack, bufferSource, packedLight, entity, limbSwing, limbSwingAmount, partialTick, ageInTicks, netHeadYaw, headPitch);
			poseStack.popPose();
		}
	}
	
	@Override
	protected float xOffset(float ageInTicks) {
		return ageInTicks * 0.01F;
	}

	@Override
	protected ResourceLocation getTextureLocation() {
		return POWER_LOCATION;
	}
	
	@Override
	protected EntityModel<E> model() {
		return this.model;
	}
}
