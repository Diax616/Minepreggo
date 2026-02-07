package dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.creeper;

import com.mojang.blaze3d.vertex.PoseStack;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractMonsterCreeperGirlModel;
import dev.dixmk.minepreggo.utils.MinepreggoHelper;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractCreeperGirl;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EnergySwirlLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MonsterCreeperGirlPowerLayer
	<E extends AbstractCreeperGirl, M extends AbstractMonsterCreeperGirlModel<E>> extends EnergySwirlLayer<E, M> {

	private static final ResourceLocation POWER_LOCATION = MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/armor.png");
	private final M model;
	
	public MonsterCreeperGirlPowerLayer(RenderLayerParent<E, M> p_174471_, EntityModelSet p_174472_, M main) {
		super(p_174471_);
		this.model = main;
	}
	
	@Override
	public void render(PoseStack p_116970_, MultiBufferSource p_116971_, int p_116972_, E p_116973_, float p_116974_, float p_116975_, float p_116976_, float p_116977_, float p_116978_, float p_116979_) {
		if (p_116973_.isPowered()) {
			p_116970_.pushPose();
			p_116970_.scale(1.05F, 1.05F, 1.05F);
			p_116970_.translate(0.0D, -0.0175D, 0.0D);
			super.render(p_116970_, p_116971_, p_116972_, p_116973_, p_116974_, p_116975_, p_116976_, p_116977_, p_116978_, p_116979_);
			p_116970_.popPose();
		}
	}
	
	@Override
	protected float xOffset(float p_116683_) {
		return p_116683_ * 0.01F;
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
