package dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.ender;

import javax.annotation.Nonnull;

import dev.dixmk.minepreggo.client.model.entity.preggo.ender.AbstractTamablePregnantMonsterEnderWomanModel;
import dev.dixmk.minepreggo.world.entity.preggo.ender.AbstractTamablePregnantEnderWoman;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MonsterPregnantEnderWomanExpressiveEyesLayer 
	<E extends AbstractTamablePregnantEnderWoman, M extends AbstractTamablePregnantMonsterEnderWomanModel<E>> extends MonsterEnderWomanExpressiveEyesLayer<E, M> {

	public MonsterPregnantEnderWomanExpressiveEyesLayer(RenderLayerParent<E, M> p_116981_) {
		super(p_116981_);
	}

	@Override
	protected @Nonnull RenderType renderType(E enderWoman) {
		final var pregnancyData = enderWoman.getPregnancyData();
		final var pain = pregnancyData.getPregnancyPain();	

		if (pain != null) {	
			RenderType face = switch (pain) {
				case MORNING_SICKNESS, FETAL_MOVEMENT, PREBIRTH, BIRTH -> SAD_ENDER_EYES_1;
				case CONTRACTION -> PAIN_ENDER_EYES;
				case MISCARRIAGE, WATER_BREAKING -> SURPRISED_ENDER_EYES;
				default -> null;
			};
			if (face != null)
				return face;
		}
		
		if (!pregnancyData.getSyncedPregnancySymptoms().isEmpty()) {
			return SAD_ENDER_EYES_1;
		}
			
		return super.renderType(enderWoman);
	}
}
