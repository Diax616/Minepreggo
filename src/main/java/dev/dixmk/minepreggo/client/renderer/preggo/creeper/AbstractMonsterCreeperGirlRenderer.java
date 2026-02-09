package dev.dixmk.minepreggo.client.renderer.preggo.creeper;

import javax.annotation.Nullable;

import com.mojang.blaze3d.vertex.PoseStack;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractMonsterCreeperGirlModel;
import dev.dixmk.minepreggo.client.renderer.entity.layer.ExpressiveFaceLayer;
import dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.creeper.MonsterCreeperGirlPowerLayer;
import dev.dixmk.minepreggo.utils.MinepreggoHelper;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractCreeperGirl;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraft.client.renderer.entity.MobRenderer;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractMonsterCreeperGirlRenderer
	<E extends AbstractCreeperGirl, M extends AbstractMonsterCreeperGirlModel<E>> extends MobRenderer<E, M>{

	protected static final ResourceLocation MONSTER_CREEPER_GIRL_LOCATION = MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/monster/monster_creeper_girl.png");

	protected static final ResourceLocation MONSTER_CREEPER_GIRL_P0_LOCATION = MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/monster/monster_creeper_girl_p0.png");
	protected static final ResourceLocation MONSTER_CREEPER_GIRL_P1_LOCATION = MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/monster/monster_creeper_girl_p1.png");
	protected static final ResourceLocation MONSTER_CREEPER_GIRL_P2_LOCATION = MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/monster/monster_creeper_girl_p2.png");
	protected static final ResourceLocation MONSTER_CREEPER_GIRL_P3_LOCATION = MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/monster/monster_creeper_girl_p3.png");
	protected static final ResourceLocation MONSTER_CREEPER_GIRL_P4_LOCATION = MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/monster/monster_creeper_girl_p4.png");
	protected static final ResourceLocation MONSTER_CREEPER_GIRL_P5_LOCATION = MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/monster/monster_creeper_girl_p5.png");
	protected static final ResourceLocation MONSTER_CREEPER_GIRL_P6_LOCATION = MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/monster/monster_creeper_girl_p6.png");
	protected static final ResourceLocation MONSTER_CREEPER_GIRL_P7_LOCATION = MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/monster/monster_creeper_girl_p7.png");
	protected static final ResourceLocation MONSTER_CREEPER_GIRL_P8_LOCATION = MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/creeper/monster/monster_creeper_girl_p8.png");

	protected AbstractMonsterCreeperGirlRenderer(EntityRendererProvider.Context context, M main, M armor) {
		super(context, main, 0.5F);
		var expressiveFaceLayer = createExpressiveFaceLayer();
		if (expressiveFaceLayer != null) {
			this.addLayer(expressiveFaceLayer);
		}
		this.addLayer(new MonsterCreeperGirlPowerLayer<>(this, context.getModelSet(), armor));
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
