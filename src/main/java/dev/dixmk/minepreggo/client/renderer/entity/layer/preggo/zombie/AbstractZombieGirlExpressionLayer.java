package dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.zombie;

import javax.annotation.Nullable;

import com.mojang.blaze3d.vertex.PoseStack;

import dev.dixmk.minepreggo.client.model.entity.preggo.zombie.AbstractZombieGirlModel;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.AbstractZombieGirl;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractZombieGirlExpressionLayer 
	<E extends AbstractZombieGirl, M extends AbstractZombieGirlModel<E>> extends RenderLayer<E, M> {

	
	protected AbstractZombieGirlExpressionLayer(RenderLayerParent<E, M> p_117346_) {
		super(p_117346_);
	}
	
	@Override
	public void render(PoseStack poseStack, MultiBufferSource p_117350_, int p_117351_, E p_117352_, float p_117353_, float p_117354_, float p_117355_, float p_117356_, float p_117357_, float p_117358_) {		
		if (p_117352_.isInvisible())
			return;
		final var face = renderType(p_117352_);
		if (face != null) {	
			var model = this.getParentModel();	
			if (model.young) {
				model.setAllInvisibleLessHead();				
				model.renderToBuffer(
						poseStack, 
						p_117350_.getBuffer(face), 
						p_117351_, 
						LivingEntityRenderer.getOverlayCoords(p_117352_, 0.0F),
						1.0F, 1.0F, 1.0F, 1.0F
					);
				model.setAllVisible(true);
			}
			else {
				model.head.render(
						poseStack, 
						p_117350_.getBuffer(face), 
						p_117351_, 
						LivingEntityRenderer.getOverlayCoords(p_117352_, 0.0F),
						1.0F, 1.0F, 1.0F, 1.0F
					);
			}	
		}
	} 
	
	public abstract @Nullable RenderType renderType(E creeperGirl);
}
