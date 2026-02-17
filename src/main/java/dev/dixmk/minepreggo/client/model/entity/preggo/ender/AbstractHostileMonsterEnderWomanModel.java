package dev.dixmk.minepreggo.client.model.entity.preggo.ender;

import javax.annotation.Nullable;

import org.apache.commons.lang3.tuple.ImmutablePair;

import dev.dixmk.minepreggo.client.jiggle.EntityJiggleDataFactory;
import dev.dixmk.minepreggo.client.jiggle.EntityJiggleDataFactory.JigglePositionConfig;
import dev.dixmk.minepreggo.world.entity.preggo.ender.AbstractHostileEnderWoman;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractHostileMonsterEnderWomanModel
	<E extends AbstractHostileEnderWoman> extends AbstractMonsterEnderWomanModel<E> {
	
	protected AbstractHostileMonsterEnderWomanModel(ModelPart root, MonsterEnderWomanAnimator<E> animator, @Nullable ImmutablePair<PregnancyPhase, Boolean> pregnancyPhaseAndSimpleBellyJiggle) {
		super(root, animator, pregnancyPhaseAndSimpleBellyJiggle);
		this.belly.visible = false;
	}
	
	protected AbstractHostileMonsterEnderWomanModel(ModelPart root) {
		this(root, new MonsterEnderWomanAnimator.BasicEnderWomanAnimator<>(root), null);
	}
	
	@Override
	protected JigglePositionConfig createJiggleConfig() {
		return EntityJiggleDataFactory.JigglePositionConfig.boobs(this.boobs.y);
	}
}

