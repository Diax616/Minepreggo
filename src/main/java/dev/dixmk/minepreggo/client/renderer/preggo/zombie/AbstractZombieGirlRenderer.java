package dev.dixmk.minepreggo.client.renderer.preggo.zombie;

import javax.annotation.Nullable;

import org.apache.commons.lang3.tuple.ImmutablePair;

import com.mojang.blaze3d.vertex.PoseStack;

import dev.dixmk.minepreggo.client.model.entity.preggo.zombie.AbstractZombieGirlModel;
import dev.dixmk.minepreggo.client.renderer.entity.layer.ExpressiveFaceLayer;
import dev.dixmk.minepreggo.utils.MinepreggoHelper;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.AbstractZombieGirl;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractZombieGirlRenderer<E extends AbstractZombieGirl, M extends AbstractZombieGirlModel<E>> extends HumanoidMobRenderer<E, M> {
	
	protected static final ResourceLocation  ZOMBIE_GIRL_LOCATION = MinepreggoHelper.fromThisNamespaceAndPath("textures/entity/preggo/zombie/zombie_girl.png");
	protected static final ResourceLocation  ZOMBIE_GIRL_P0_LOCATION = MinepreggoHelper.fromThisNamespaceAndPath("textures/entity/preggo/zombie/zombie_girl_p0.png");
	protected static final ResourceLocation  ZOMBIE_GIRL_P1_LOCATION = MinepreggoHelper.fromThisNamespaceAndPath("textures/entity/preggo/zombie/zombie_girl_p1.png");
	protected static final ResourceLocation  ZOMBIE_GIRL_P2_LOCATION = MinepreggoHelper.fromThisNamespaceAndPath("textures/entity/preggo/zombie/zombie_girl_p2.png");
	protected static final ResourceLocation  ZOMBIE_GIRL_P3_LOCATION = MinepreggoHelper.fromThisNamespaceAndPath("textures/entity/preggo/zombie/zombie_girl_p3.png");

	protected static final ImmutablePair<ResourceLocation, ResourceLocation>  ZOMBIE_GIRL_P4_LOCATION = ImmutablePair.of(
			MinepreggoHelper.fromThisNamespaceAndPath("textures/entity/preggo/zombie/zombie_girl_p4.png"),
			MinepreggoHelper.fromThisNamespaceAndPath("textures/entity/preggo/zombie/zombie_girl_p4_nude.png"));
	
	protected static final ImmutablePair<ResourceLocation, ResourceLocation>  ZOMBIE_GIRL_P5_LOCATION = ImmutablePair.of(
			MinepreggoHelper.fromThisNamespaceAndPath("textures/entity/preggo/zombie/zombie_girl_p5.png"),
			MinepreggoHelper.fromThisNamespaceAndPath("textures/entity/preggo/zombie/zombie_girl_p5_nude.png"));
	
	protected static final ImmutablePair<ResourceLocation, ResourceLocation>  ZOMBIE_GIRL_P6_LOCATION = ImmutablePair.of(
			MinepreggoHelper.fromThisNamespaceAndPath("textures/entity/preggo/zombie/zombie_girl_p6.png"),
			MinepreggoHelper.fromThisNamespaceAndPath("textures/entity/preggo/zombie/zombie_girl_p6_nude.png"));
	
	protected static final ImmutablePair<ResourceLocation, ResourceLocation>  ZOMBIE_GIRL_P7_LOCATION = ImmutablePair.of(
			MinepreggoHelper.fromThisNamespaceAndPath("textures/entity/preggo/zombie/zombie_girl_p7.png"),
			MinepreggoHelper.fromThisNamespaceAndPath("textures/entity/preggo/zombie/zombie_girl_p7_nude.png"));
	
	protected static final ImmutablePair<ResourceLocation, ResourceLocation>  ZOMBIE_GIRL_P8_LOCATION = ImmutablePair.of(
			MinepreggoHelper.fromThisNamespaceAndPath("textures/entity/preggo/zombie/zombie_girl_p8.png"),
			MinepreggoHelper.fromThisNamespaceAndPath("textures/entity/preggo/zombie/zombie_girl_p8_nude.png"));
	
	protected AbstractZombieGirlRenderer(EntityRendererProvider.Context context, M main, M inner, M outter) {
		super(context, main, 0.5F);
		this.addLayer(new HumanoidArmorLayer<>(this, inner, outter, context.getModelManager()));	
		var expressionLayer = this.createExpressiveFaceLayer();
		if (expressionLayer != null) {
			this.addLayer(expressionLayer);
		}
	}
	
	protected @Nullable ExpressiveFaceLayer<E, M> createExpressiveFaceLayer() {
		return null;
	}
	
	@Override
	protected void scale(E entity, PoseStack poseStack, float partialTick) {
		poseStack.scale(0.9375F, 0.9375F, 0.9375F);
	}
}
