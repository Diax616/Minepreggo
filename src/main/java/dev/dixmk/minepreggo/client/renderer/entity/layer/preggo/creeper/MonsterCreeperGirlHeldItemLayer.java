package dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.creeper;

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
	
	public MonsterCreeperGirlHeldItemLayer(RenderLayerParent<E, M> p_117346_, ItemInHandRenderer p_234839_) {
		super(p_117346_);
		this.itemInHandRenderer = p_234839_;
	}

	@Override
	public void render(PoseStack p_117349_, MultiBufferSource p_117350_, int p_117351_, E p_117352_, float p_117353_, float p_117354_, float p_117355_, float p_117356_, float p_117357_, float p_117358_) {	
		p_117349_.pushPose();
		var parentModel = this.getParentModel();
		p_117349_.translate(parentModel.head.x / 16.0F, parentModel.head.y / 16.0F, parentModel.head.z / 16.0F);
		p_117349_.mulPose(Axis.YP.rotationDegrees(p_117357_));
		p_117349_.mulPose(Axis.XP.rotationDegrees(p_117358_));
		p_117349_.translate(0, -0.05F, -0.25F);
		p_117349_.mulPose(Axis.XP.rotationDegrees(90.0F));
		p_117349_.mulPose(Axis.ZP.rotationDegrees(200.0F));
		
		ItemStack itemstack = p_117352_.getItemBySlot(EquipmentSlot.MAINHAND);
		this.itemInHandRenderer.renderItem(p_117352_, itemstack, ItemDisplayContext.GROUND, false, p_117349_, p_117350_, p_117351_);
		p_117349_.popPose();
	}
}
