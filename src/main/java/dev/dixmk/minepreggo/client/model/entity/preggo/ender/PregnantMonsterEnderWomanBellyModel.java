package dev.dixmk.minepreggo.client.model.entity.preggo.ender;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class PregnantMonsterEnderWomanBellyModel /*extends AbstractPregnantBellyModel<AbstractEnderWoman>*/ {
	
	/*
	protected PregnantMonsterEnderWomanBellyModel(ModelPart root, @Nullable BellyInflation bellyInflation, boolean simpleBellyJiggle, PregnancyPhase pregnancyPhase) {
		super(root, bellyInflation, simpleBellyJiggle, pregnancyPhase);
	}

	@Override
	public void setupAnim(AbstractHostilPregnantEnderWoman enderWoman, float p_102619_, float p_102620_, float p_102621_, float p_102622_, float p_102623_) {
		if (enderWoman.getPregnancyData().getCurrentPregnancyPhase() != pregnancyPhase) {
			JigglePhysicsManager.getInstance().removeJigglePhysics(enderWoman);
		}

		EntityJiggleData jiggleData = JigglePhysicsManager.getInstance().getOrCreate(enderWoman, () -> EntityJiggleDataFactory.create(jiggleConfig, pregnancyPhase));
		jiggleData.getBoobsJiggle().setupAnim(enderWoman, boobs, leftBoob, rightBoob);			
		jiggleData.getBellyJiggle().ifPresent(jiggle -> jiggle.setupAnim(enderWoman, belly, simpleBellyJiggle));
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class BellyModelP0 extends PregnantMonsterEnderWomanBellyModel {
		public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "pregnant_monster_ender_woman_body_p0_model"), "main");
		public BellyModelP0(ModelPart root) {
			super(root, BellyInflation.LOW, true, PregnancyPhase.P0);
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class BellyModelP1 extends PregnantMonsterEnderWomanBellyModel {
		public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "pregnant_monster_ender_woman_body_p1_model"), "main");
		public BellyModelP1(ModelPart root) {
			super(root, BellyInflation.LOW, true, PregnancyPhase.P1);
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class BellyModelP2 extends PregnantMonsterEnderWomanBellyModel {
		public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "pregnant_monster_ender_woman_body_p2_model"), "main");
		public BellyModelP2(ModelPart root) {
			super(root, BellyInflation.MEDIUM, true, PregnancyPhase.P2);
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class BellyModelP3 extends PregnantMonsterEnderWomanBellyModel {
		public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "pregnant_monster_ender_woman_body_p3_model"), "main");
		public BellyModelP3(ModelPart root) {
			super(root, BellyInflation.MEDIUM, false, PregnancyPhase.P3);
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class BellyModelP4 extends PregnantMonsterEnderWomanBellyModel {
		public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "pregnant_monster_ender_woman_body_p4_model"), "main");
		public BellyModelP4(ModelPart root) {
			super(root, BellyInflation.MEDIUM, false, PregnancyPhase.P4);
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class BellyModelP5 extends PregnantMonsterEnderWomanBellyModel {
		public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "pregnant_monster_ender_woman_body_p5_model"), "main");
		public BellyModelP5(ModelPart root) {
			super(root, BellyInflation.MEDIUM, false, PregnancyPhase.P5);
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class BellyModelP6 extends PregnantMonsterEnderWomanBellyModel {
		public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "pregnant_monster_ender_woman_body_p6_model"), "main");
		public BellyModelP6(ModelPart root) {
			super(root, BellyInflation.HIGH, false, PregnancyPhase.P6);
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class BellyModelP7 extends PregnantMonsterEnderWomanBellyModel {
		public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "pregnant_monster_ender_woman_body_p7_model"), "main");
		public BellyModelP7(ModelPart root) {
			super(root, BellyInflation.HIGH, false, PregnancyPhase.P7);
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class BellyModelP8 extends PregnantMonsterEnderWomanBellyModel {
		public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "pregnant_monster_ender_woman_body_p8_model"), "main");
		public BellyModelP8(ModelPart root) {
			super(root, BellyInflation.HIGH, false, PregnancyPhase.P8);
		}
	}
	*/
}
