package dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.ender;

import javax.annotation.Nullable;

import dev.dixmk.minepreggo.client.model.entity.preggo.ender.AbstractTamablePregnantMonsterEnderWomanModel;
import dev.dixmk.minepreggo.world.entity.preggo.ender.AbstractTamablePregnantEnderWoman;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TamableMonsterPregnantEnderWomanExpressionLayer 
	<E extends AbstractTamablePregnantEnderWoman, M extends AbstractTamablePregnantMonsterEnderWomanModel<E>> extends TamableMonsterEnderWomanExpressionLayer<E, M> {

	public TamableMonsterPregnantEnderWomanExpressionLayer(RenderLayerParent<E, M> p_117346_) {
		super(p_117346_);
	}

	@Override
	public @Nullable RenderType renderType(E enderWoman) {
		final var pregnancyData = enderWoman.getPregnancyData();
		final var pain = pregnancyData.getPregnancyPain();

		if (pain != null) {	
			RenderType face = switch (pain) {
				case MORNING_SICKNESS, FETAL_MOVEMENT, PREBIRTH, BIRTH -> SAD_MASK;
				case CONTRACTION, MISCARRIAGE, WATER_BREAKING -> SUPRISED_MASK;
				default -> null;
			};
			if (face != null)
				return face;
		}
		
		if (!pregnancyData.getSyncedPregnancySymptoms().isEmpty()) {
			return SAD_MASK;
		}
	
		return super.renderType(enderWoman);
	}
}
