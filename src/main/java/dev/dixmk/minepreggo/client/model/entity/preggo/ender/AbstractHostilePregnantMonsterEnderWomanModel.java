package dev.dixmk.minepreggo.client.model.entity.preggo.ender;

import javax.annotation.Nullable;

import org.apache.commons.lang3.tuple.ImmutablePair;

import dev.dixmk.minepreggo.client.animation.preggo.BellyInflation;
import dev.dixmk.minepreggo.client.animation.preggo.FetalMovementIntensity;
import dev.dixmk.minepreggo.client.jiggle.EntityJiggleDataFactory;
import dev.dixmk.minepreggo.client.jiggle.EntityJiggleDataFactory.JigglePositionConfig;
import dev.dixmk.minepreggo.world.entity.preggo.ender.AbstractHostilePregnantEnderWoman;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractHostilePregnantMonsterEnderWomanModel 
	<E extends AbstractHostilePregnantEnderWoman> extends AbstractHostileMonsterEnderWomanModel<E> {

	protected AbstractHostilePregnantMonsterEnderWomanModel(ModelPart root, PregnancyPhase pregnancyPhase, boolean simpleBellyJiggle, BellyInflation bellyInflation, @Nullable FetalMovementIntensity fetalMovementIntensity) {
		super(root, new MonsterEnderWomanAnimator.HostilPregnantEnderWomanAnimator<>(root, bellyInflation, fetalMovementIntensity), ImmutablePair.of(pregnancyPhase, simpleBellyJiggle));
		this.belly.visible = true;
	}
	
	@Override
	protected JigglePositionConfig createJiggleConfig() {
		return EntityJiggleDataFactory.JigglePositionConfig.boobsAndBelly(this.boobs.y, this.belly.y);
	}
}
