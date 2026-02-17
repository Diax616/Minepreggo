package dev.dixmk.minepreggo.client.renderer.preggo.ender;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.client.model.entity.preggo.ender.AbstractMonsterEnderWomanModel;
import dev.dixmk.minepreggo.utils.MinepreggoHelper;
import dev.dixmk.minepreggo.world.entity.preggo.ender.AbstractEnderWoman;
import dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.ender.CarriedBlockLayer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractMonsterEnderWomanRenderer
	<E extends AbstractEnderWoman, M extends AbstractMonsterEnderWomanModel<E>> extends HumanoidMobRenderer<E, M> {
		
	protected static final ResourceLocation MONSTER_ENDER_GIRL_LOCATION = MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/ender/monster/ender_woman.png");
	protected static final ResourceLocation MONSTER_PREGNANT_ENDER_WOMAN_P0_TEXTURE = MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/ender/monster/ender_woman_p0.png");
	protected static final ResourceLocation MONSTER_PREGNANT_ENDER_WOMAN_P1_TEXTURE = MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/ender/monster/ender_woman_p1.png");
	protected static final ResourceLocation MONSTER_PREGNANT_ENDER_WOMAN_P2_TEXTURE = MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/ender/monster/ender_woman_p2.png");
	protected static final ResourceLocation MONSTER_PREGNANT_ENDER_WOMAN_P3_TEXTURE = MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/ender/monster/ender_woman_p3.png");
	protected static final ResourceLocation MONSTER_PREGNANT_ENDER_WOMAN_P4_TEXTURE = MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/ender/monster/ender_woman_p4.png");
	protected static final ResourceLocation MONSTER_PREGNANT_ENDER_WOMAN_P5_TEXTURE = MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/ender/monster/ender_woman_p5.png");
	protected static final ResourceLocation MONSTER_PREGNANT_ENDER_WOMAN_P6_TEXTURE = MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/ender/monster/ender_woman_p6.png");
	protected static final ResourceLocation MONSTER_PREGNANT_ENDER_WOMAN_P7_TEXTURE = MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/ender/monster/ender_woman_p7.png");
	protected static final ResourceLocation MONSTER_PREGNANT_ENDER_WOMAN_P8_TEXTURE = MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/ender/monster/ender_woman_p8.png");

	protected AbstractMonsterEnderWomanRenderer(EntityRendererProvider.Context context, M main, M inner, M outter) {
		super(context, main, 0.5F);
		this.addLayer(new CarriedBlockLayer<>(this, context.getBlockRenderDispatcher()));
		this.addLayer(new HumanoidArmorLayer<>(this, inner, outter, context.getModelManager()));
	}

	@Override
	public Vec3 getRenderOffset(E p_114336_, float p_114337_) {
		if (p_114336_.isCreepy()) {
			return new Vec3(p_114336_.getRandom().nextGaussian() * 0.02D, 0.0D, p_114336_.getRandom().nextGaussian() * 0.02D);
		} else {
			return super.getRenderOffset(p_114336_, p_114337_);
		}
	}
}