package dev.dixmk.minepreggo.client.model.entity.preggo.zombie;

import javax.annotation.Nonnull;

import dev.dixmk.minepreggo.client.jiggle.EntityJiggleDataFactory;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.AbstractHostilePregnantZombieGirl;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractHostilePregnantZombieGirlModel<E extends AbstractHostilePregnantZombieGirl> extends AbstractHostileZombieGirlModel<E> {

	protected AbstractHostilePregnantZombieGirlModel(ModelPart root, ZombieGirlAnimator<E> animate, @Nonnull PregnancyPhase phase, boolean simpleBellyJiggle) {
		super(root, animate, phase, simpleBellyJiggle);
		this.belly.visible = true;
	}
		
	@Override
	protected @Nonnull EntityJiggleDataFactory.JigglePositionConfig createJiggleConfig() {
		return EntityJiggleDataFactory.JigglePositionConfig.boobsAndBelly(this.boobs.y, this.belly.y);
	}
}
