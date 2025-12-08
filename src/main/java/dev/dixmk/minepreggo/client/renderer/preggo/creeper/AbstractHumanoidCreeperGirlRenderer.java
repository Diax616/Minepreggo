package dev.dixmk.minepreggo.client.renderer.preggo.creeper;

import org.apache.commons.lang3.tuple.ImmutablePair;

import com.mojang.blaze3d.vertex.PoseStack;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractHumanoidCreeperGirlModel;
import dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.creeper.HumanoidCreeperGirlPowerLayer;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractCreeperGirl;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractHumanoidCreeperGirlRenderer<E extends AbstractCreeperGirl, M extends AbstractHumanoidCreeperGirlModel<E>> extends HumanoidMobRenderer<E, M> {
	protected static final ResourceLocation  CREEPER_GIRL_LOCATION = ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/humanoid_creeper_girl.png");
	protected static final ResourceLocation  CREEPER_GIRL_P0_LOCATION = ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/humanoid_creeper_girl_p0.png");
	protected static final ResourceLocation  CREEPER_GIRL_P1_LOCATION = ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/humanoid_creeper_girl_p1.png");
	protected static final ResourceLocation  CREEPER_GIRL_P2_LOCATION = ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/humanoid_creeper_girl_p2.png");
	protected static final ResourceLocation  CREEPER_GIRL_P3_LOCATION = ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/humanoid_creeper_girl_p3.png");
	
	protected static final ImmutablePair<ResourceLocation, ResourceLocation> CREEPER_GIRL_P4_LOCATION = ImmutablePair.of(
			ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/humanoid_creeper_girl_p4.png"),
			ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/humanoid_creeper_girl_p4_nude.png"));	
	
	protected static final ImmutablePair<ResourceLocation, ResourceLocation> CREEPER_GIRL_P5_LOCATION = ImmutablePair.of(
			ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/humanoid_creeper_girl_p5.png"),
			ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/humanoid_creeper_girl_p5_nude.png"));	
	
	protected static final ImmutablePair<ResourceLocation, ResourceLocation> CREEPER_GIRL_P6_LOCATION = ImmutablePair.of(
			ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/humanoid_creeper_girl_p6.png"),
			ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/humanoid_creeper_girl_p6_nude.png"));	

	protected static final ImmutablePair<ResourceLocation, ResourceLocation> CREEPER_GIRL_P7_LOCATION = ImmutablePair.of(
			ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/humanoid_creeper_girl_p7.png"),
			ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/humanoid_creeper_girl_p7_nude.png"));	
	
	protected static final ImmutablePair<ResourceLocation, ResourceLocation> CREEPER_GIRL_P8_LOCATION = ImmutablePair.of(
			ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/humanoid_creeper_girl_p8.png"),
			ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/humanoid_creeper_girl_p8_nude.png"));	
	
	protected AbstractHumanoidCreeperGirlRenderer(EntityRendererProvider.Context context, M main, M inner, M outter, M armor) {
		super(context, main, 0.5F);
		this.addLayer(new HumanoidArmorLayer<>(this, inner, outter, context.getModelManager()));
		this.addLayer(new HumanoidCreeperGirlPowerLayer<>(this, context.getModelSet(), armor));
	}
	
	@Override
	protected void scale(E creeperGirl, PoseStack p_114047_, float p_114048_) {
		float f = creeperGirl.getSwelling(p_114048_);
		float f1 = 1.0F + Mth.sin(f * 100.0F) * f * 0.01F;
		f = Mth.clamp(f, 0.0F, 1.0F);
		f *= f;
		f *= f;
		float f2 = (1.0F + f * 0.4F) * f1;
		float f3 = (1.0F + f * 0.1F) / f1;
		p_114047_.scale(f2, f3, f2);
	}
	
	@Override
	protected float getWhiteOverlayProgress(E creeperGirl, float p_114044_) {
		float f = creeperGirl.getSwelling(p_114044_);
		return (int)(f * 10.0F) % 2 == 0 ? 0.0F : Mth.clamp(f, 0.5F, 1.0F);
	}
}
