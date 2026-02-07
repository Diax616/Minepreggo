package dev.dixmk.minepreggo.client.model.entity.preggo.ender;

import org.apache.commons.lang3.tuple.ImmutablePair;

import dev.dixmk.minepreggo.client.jiggle.EntityJiggleDataFactory;
import dev.dixmk.minepreggo.client.jiggle.EntityJiggleDataFactory.JigglePositionConfig;
import dev.dixmk.minepreggo.world.entity.preggo.ender.AbstractTamablePregnantEnderWoman;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySymptom;
import net.minecraft.client.model.geom.ModelPart;

public abstract class AbstractTamablePregnantMonsterEnderWomanModel
	<E extends AbstractTamablePregnantEnderWoman> extends AbstractTamableMonsterEnderWomanModel<E> {
	protected float milkingBoobsXScale = 1.15F;
	protected float milkingBoobsYScale = 1.05F;
	protected float milkingBoobsZScale = 1.25F;
	protected float milkingBoobsYPos = -0.36F;
	
	protected AbstractTamablePregnantMonsterEnderWomanModel(ModelPart root, MonsterEnderWomanAnimator<E> enderWomanAnimator,ImmutablePair<PregnancyPhase, Boolean> pregnancyPhaseAndSimpleBellyJiggle) {
		super(root, enderWomanAnimator, pregnancyPhaseAndSimpleBellyJiggle);
		this.belly.visible = true;
	}

	protected AbstractTamablePregnantMonsterEnderWomanModel(ModelPart root, MonsterEnderWomanAnimator<E> enderWomanAnimator, PregnancyPhase pregnancyPhase, boolean simpleBellyJiggle) {
		this(root, enderWomanAnimator, ImmutablePair.of(pregnancyPhase, simpleBellyJiggle));
	}

	@Override
	protected JigglePositionConfig createJiggleConfig() {
		return EntityJiggleDataFactory.JigglePositionConfig.boobsAndBelly(this.boobs.y, this.belly.y);
	}
	
	@Override
	public void setupAnim(E entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);	
		if (entity.getPregnancyData().getSyncedPregnancySymptoms().containsPregnancySymptom(PregnancySymptom.MILKING)) {
			this.boobs.y += milkingBoobsYPos;
			this.boobs.xScale = milkingBoobsXScale;
			this.boobs.yScale = milkingBoobsYScale;		
			this.boobs.zScale = milkingBoobsZScale;	
		} 
	}
}
