package dev.dixmk.minepreggo.client.renderer.entity.layers.preggo.creeper;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractMonsterCreeperGirlModel;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractCreeperGirl;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MonsterCreeperGirlHeldItemLayer
	<E extends AbstractCreeperGirl, M extends AbstractMonsterCreeperGirlModel<E>> extends RenderLayer<E, M> {
	private final ItemInHandRenderer itemInHandRenderer;
	
	public MonsterCreeperGirlHeldItemLayer(RenderLayerParent<E, M> renderer, ItemInHandRenderer itemInHandRenderer) {
		super(renderer);
		this.itemInHandRenderer = itemInHandRenderer;
	}

	@Override
	public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, E entity, float limbSwing, float limbSwingAmount, float partialTick, float ageInTicks, float netHeadYaw, float headPitch) {	
		poseStack.pushPose();
		var parentModel = this.getParentModel();
		poseStack.translate(parentModel.head.x / 16.0F, parentModel.head.y / 16.0F, parentModel.head.z / 16.0F);
		poseStack.mulPose(Axis.YP.rotationDegrees(netHeadYaw));
		poseStack.mulPose(Axis.XP.rotationDegrees(headPitch));
		poseStack.translate(0, -0.05F, -0.25F);
		poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
		poseStack.mulPose(Axis.ZP.rotationDegrees(200.0F));	
		ItemStack itemstack = entity.getItemBySlot(EquipmentSlot.MAINHAND);
		this.itemInHandRenderer.renderItem(entity, itemstack, ItemDisplayContext.GROUND, false, poseStack, bufferSource, packedLight);
		poseStack.popPose();
	}
}
