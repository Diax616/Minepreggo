package dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.ender;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HostilPregnantEnderWomanBellyLayer /*<E extends AbstractEnderWoman, M extends AbstractMonsterEnderWomanModel<E>> extends RenderLayer<E, M>*/ {
	/*
	private final PregnantMonsterEnderWomanBellyModel.BellyModelP0 bellyModelP0;
	private final PregnantMonsterEnderWomanBellyModel.BellyModelP1 bellyModelP1;
	private final PregnantMonsterEnderWomanBellyModel.BellyModelP2 bellyModelP2;
	private final PregnantMonsterEnderWomanBellyModel.BellyModelP3 bellyModelP3;
	private final PregnantMonsterEnderWomanBellyModel.BellyModelP4 bellyModelP4;
	private final PregnantMonsterEnderWomanBellyModel.BellyModelP5 bellyModelP5;
	private final PregnantMonsterEnderWomanBellyModel.BellyModelP6 bellyModelP6;
	private final PregnantMonsterEnderWomanBellyModel.BellyModelP7 bellyModelP7;
	private final PregnantMonsterEnderWomanBellyModel.BellyModelP8 bellyModelP8;

	public HostilPregnantEnderWomanBellyLayer(RenderLayerParent<E, M> parent, EntityModelSet modelSet) {
		super(parent);

		bellyModelP0 = new PregnantMonsterEnderWomanBellyModel.BellyModelP0(modelSet.bakeLayer(PregnantMonsterEnderWomanBellyModel.BellyModelP0.LAYER_LOCATION));
		bellyModelP1 = new PregnantMonsterEnderWomanBellyModel.BellyModelP1(modelSet.bakeLayer(PregnantMonsterEnderWomanBellyModel.BellyModelP1.LAYER_LOCATION));
		bellyModelP2 = new PregnantMonsterEnderWomanBellyModel.BellyModelP2(modelSet.bakeLayer(PregnantMonsterEnderWomanBellyModel.BellyModelP2.LAYER_LOCATION));
		bellyModelP3 = new PregnantMonsterEnderWomanBellyModel.BellyModelP3(modelSet.bakeLayer(PregnantMonsterEnderWomanBellyModel.BellyModelP3.LAYER_LOCATION));
		bellyModelP4 = new PregnantMonsterEnderWomanBellyModel.BellyModelP4(modelSet.bakeLayer(PregnantMonsterEnderWomanBellyModel.BellyModelP4.LAYER_LOCATION));
		bellyModelP5 = new PregnantMonsterEnderWomanBellyModel.BellyModelP5(modelSet.bakeLayer(PregnantMonsterEnderWomanBellyModel.BellyModelP5.LAYER_LOCATION));
		bellyModelP6 = new PregnantMonsterEnderWomanBellyModel.BellyModelP6(modelSet.bakeLayer(PregnantMonsterEnderWomanBellyModel.BellyModelP6.LAYER_LOCATION));
		bellyModelP7 = new PregnantMonsterEnderWomanBellyModel.BellyModelP7(modelSet.bakeLayer(PregnantMonsterEnderWomanBellyModel.BellyModelP7.LAYER_LOCATION));
		bellyModelP8 = new PregnantMonsterEnderWomanBellyModel.BellyModelP8(modelSet.bakeLayer(PregnantMonsterEnderWomanBellyModel.BellyModelP8.LAYER_LOCATION));
	}

	@Override
	public void render(PoseStack p_117349_, MultiBufferSource p_117350_, int p_117351_,
			E p_117352_, float p_117353_, float p_117354_, float p_117355_,
			float p_117356_, float p_117357_, float p_117358_) {
		
		PregnancyPhase pregnancyPhase = p_117352_.getPregnancyData().getCurrentPregnancyPhase();

		switch (pregnancyPhase) {
			case P0: {
				renderBelly(p_117352_, bellyModelP0, p_117349_, p_117350_, p_117351_, p_117353_, p_117354_, p_117355_, p_117356_, p_117357_);
				break;
			}
			case P1: {
				renderBelly(p_117352_, bellyModelP1, p_117349_, p_117350_, p_117351_, p_117353_, p_117354_, p_117355_, p_117356_, p_117357_);
				break;
			}
			case P2: {
				renderBelly(p_117352_, bellyModelP2, p_117349_, p_117350_, p_117351_, p_117353_, p_117354_, p_117355_, p_117356_, p_117357_);
				break;
			}
			case P3: {
				renderBelly(p_117352_, bellyModelP3, p_117349_, p_117350_, p_117351_, p_117353_, p_117354_, p_117355_, p_117356_, p_117357_);
				break;
			}
			case P4: {
				renderBelly(p_117352_, bellyModelP4, p_117349_, p_117350_, p_117351_, p_117353_, p_117354_, p_117355_, p_117356_, p_117357_);
				break;
			}
			case P5: {
				renderBelly(p_117352_, bellyModelP5, p_117349_, p_117350_, p_117351_, p_117353_, p_117354_, p_117355_, p_117356_, p_117357_);
				break;
			}
			case P6: {
				renderBelly(p_117352_, bellyModelP6, p_117349_, p_117350_, p_117351_, p_117353_, p_117354_, p_117355_, p_117356_, p_117357_);
				break;
			}
			case P7: {
				renderBelly(p_117352_, bellyModelP7, p_117349_, p_117350_, p_117351_, p_117353_, p_117354_, p_117355_, p_117356_, p_117357_);
				break;
			}
			case P8: {
				renderBelly(p_117352_, bellyModelP8, p_117349_, p_117350_, p_117351_, p_117353_, p_117354_, p_117355_, p_117356_, p_117357_);
				break;
			}
			default: {
				throw new IllegalStateException("Unexpected pregnancy phase: " + pregnancyPhase);
			}
		}  
	}
	
	private void renderBelly(E enderWoman, PregnantMonsterEnderWomanBellyModel bellyModel, PoseStack poseStack, MultiBufferSource buffer, int packedLight, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		bellyModel.setupAnim(enderWoman, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		bellyModel.body.copyFrom(this.getParentModel().body);
		bellyModel.renderToBuffer(poseStack, buffer.getBuffer(RenderType.entityCutoutNoCull(this.getTextureLocation(enderWoman))), packedLight, LivingEntityRenderer.getOverlayCoords(enderWoman, 0.0F), 1.0F, 1.0F, 1.0F, 1.0F);		
	}
	*/
}
