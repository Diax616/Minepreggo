package dev.dixmk.minepreggo.client.renderer.preggo.ender;

import javax.annotation.Nonnull;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.client.model.entity.preggo.ender.AbstractTamablePregnantMonsterEnderWomanModel;
import dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.ender.MonsterEnderWomanExpressionLayer;
import dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.ender.MonsterEnderWomanExpressiveEyesLayer;
import dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.ender.MonsterPregnantEnderWomanExpressionLayer;
import dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.ender.MonsterPregnantEnderWomanExpressiveEyesLayer;
import dev.dixmk.minepreggo.utils.MinepreggoHelper;
import dev.dixmk.minepreggo.world.entity.preggo.ender.AbstractTamablePregnantEnderWoman;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractTamablePregnantMonsterEnderWomanRenderer 
	<E extends AbstractTamablePregnantEnderWoman, M extends AbstractTamablePregnantMonsterEnderWomanModel<E>> extends AbstractTamableMonsterEnderWomanRenderer<E, M> {

	protected static final ResourceLocation MONSTER_PREGNANT_ENDER_WOMAN_P0_TEXTURE = MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/ender/monster/ender_woman_p0.png");
	protected static final ResourceLocation MONSTER_PREGNANT_ENDER_WOMAN_P1_TEXTURE = MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/ender/monster/ender_woman_p1.png");
	protected static final ResourceLocation MONSTER_PREGNANT_ENDER_WOMAN_P2_TEXTURE = MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/ender/monster/ender_woman_p2.png");
	protected static final ResourceLocation MONSTER_PREGNANT_ENDER_WOMAN_P3_TEXTURE = MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/ender/monster/ender_woman_p3.png");
	protected static final ResourceLocation MONSTER_PREGNANT_ENDER_WOMAN_P4_TEXTURE = MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/ender/monster/ender_woman_p4.png");
	protected static final ResourceLocation MONSTER_PREGNANT_ENDER_WOMAN_P5_TEXTURE = MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/ender/monster/ender_woman_p5.png");
	protected static final ResourceLocation MONSTER_PREGNANT_ENDER_WOMAN_P6_TEXTURE = MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/ender/monster/ender_woman_p6.png");
	protected static final ResourceLocation MONSTER_PREGNANT_ENDER_WOMAN_P7_TEXTURE = MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/ender/monster/ender_woman_p7.png");
	protected static final ResourceLocation MONSTER_PREGNANT_ENDER_WOMAN_P8_TEXTURE = MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/ender/monster/ender_woman_p8.png");

	protected AbstractTamablePregnantMonsterEnderWomanRenderer(Context context, M main, M inner, M outter) {
		super(context, main, inner, outter);	
	}

	@Override
	protected @Nonnull MonsterEnderWomanExpressionLayer<E, M> createExpressiveFaceLayer() {
		return new MonsterPregnantEnderWomanExpressionLayer<>(this);
	}
	
	@Override
	protected @Nonnull MonsterEnderWomanExpressiveEyesLayer<E, M> createExpressiveEyesLayer() {
		return new MonsterPregnantEnderWomanExpressiveEyesLayer<>(this);
	}
}
