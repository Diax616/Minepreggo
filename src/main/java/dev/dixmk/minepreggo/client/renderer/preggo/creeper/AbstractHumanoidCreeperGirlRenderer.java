package dev.dixmk.minepreggo.client.renderer.preggo.creeper;

import javax.annotation.Nullable;

import org.apache.commons.lang3.tuple.ImmutablePair;

import com.mojang.blaze3d.vertex.PoseStack;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractHumanoidCreeperGirlModel;
import dev.dixmk.minepreggo.client.renderer.entity.layer.ExpressiveFaceLayer;
import dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.creeper.HumanoidCreeperGirlPowerLayer;
import dev.dixmk.minepreggo.utils.MinepreggoHelper;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractCreeperGirl;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractHumanoidCreeperGirlRenderer<E extends AbstractCreeperGirl, M extends AbstractHumanoidCreeperGirlModel<E>> extends HumanoidMobRenderer<E, M> {
	protected static final ResourceLocation  CREEPER_GIRL_LOCATION = MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/humanoid/humanoid_creeper_girl.png");
	protected static final ResourceLocation  CREEPER_GIRL_P0_LOCATION = MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/humanoid/humanoid_creeper_girl_p0.png");
	protected static final ResourceLocation  CREEPER_GIRL_P1_LOCATION = MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/humanoid/humanoid_creeper_girl_p1.png");
	protected static final ResourceLocation  CREEPER_GIRL_P2_LOCATION = MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/humanoid/humanoid_creeper_girl_p2.png");
	protected static final ResourceLocation  CREEPER_GIRL_P3_LOCATION = MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/humanoid/humanoid_creeper_girl_p3.png");
	
	protected static final ImmutablePair<ResourceLocation, ResourceLocation> CREEPER_GIRL_P4_LOCATION = ImmutablePair.of(
			MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/humanoid/humanoid_creeper_girl_p4.png"),
			MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/humanoid/humanoid_creeper_girl_p4_nude.png"));	
	
	protected static final ImmutablePair<ResourceLocation, ResourceLocation> CREEPER_GIRL_P5_LOCATION = ImmutablePair.of(
			MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/humanoid/humanoid_creeper_girl_p5.png"),
			MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/humanoid/humanoid_creeper_girl_p5_nude.png"));	
	
	protected static final ImmutablePair<ResourceLocation, ResourceLocation> CREEPER_GIRL_P6_LOCATION = ImmutablePair.of(
			MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/humanoid/humanoid_creeper_girl_p6.png"),
			MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/humanoid/humanoid_creeper_girl_p6_nude.png"));	

	protected static final ImmutablePair<ResourceLocation, ResourceLocation> CREEPER_GIRL_P7_LOCATION = ImmutablePair.of(
			MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/humanoid/humanoid_creeper_girl_p7.png"),
			MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/humanoid/humanoid_creeper_girl_p7_nude.png"));	
	
	protected static final ImmutablePair<ResourceLocation, ResourceLocation> CREEPER_GIRL_P8_LOCATION = ImmutablePair.of(
			MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/humanoid/humanoid_creeper_girl_p8.png"),
			MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/humanoid/humanoid_creeper_girl_p8_nude.png"));	
	
	protected AbstractHumanoidCreeperGirlRenderer(EntityRendererProvider.Context context, M main, M inner, M outter, M armor) {
		super(context, main, 0.5F);
		this.addLayer(new HumanoidArmorLayer<>(this, inner, outter, context.getModelManager()));
		var expressiveFaceLayer = this.createExpressiveFaceLayer();
		if (expressiveFaceLayer != null) {
			this.addLayer(expressiveFaceLayer);
		}
		this.addLayer(new HumanoidCreeperGirlPowerLayer<>(this, context.getModelSet(), armor));
	}
	
	@Override
	protected void scale(E creeperGirl, PoseStack p_114047_, float p_114048_) {
		CreeperGirlClientHelper.scale(creeperGirl, p_114047_, p_114048_);
	}
	
	@Override
	protected float getWhiteOverlayProgress(E creeperGirl, float p_114044_) {
		return CreeperGirlClientHelper.getWhiteOverlayProgress(creeperGirl, p_114044_);
	}
	
	protected @Nullable ExpressiveFaceLayer<E, M> createExpressiveFaceLayer() {
		return null;
	}
}
