package dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.ender;

import dev.dixmk.minepreggo.client.model.entity.preggo.ender.AbstractHostilePregnantMonsterEnderWomanModel;
import dev.dixmk.minepreggo.client.renderer.entity.layer.ExpressiveEyesLayer;
import dev.dixmk.minepreggo.utils.MinepreggoHelper;
import dev.dixmk.minepreggo.world.entity.preggo.ender.AbstractHostilePregnantEnderWoman;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HostilePregnantMonsterEnderWomanEyesLayer 
	<E extends AbstractHostilePregnantEnderWoman, M extends AbstractHostilePregnantMonsterEnderWomanModel<E>> extends ExpressiveEyesLayer<E, M> {

	protected static final RenderType SURPRISED_ENDER_EYES = RenderType.eyes(MinepreggoHelper.fromThisNamespaceAndPath("textures/entity/preggo/ender/monster/expressions/monster_ender_woman_eye_surprised.png"));
	
	public HostilePregnantMonsterEnderWomanEyesLayer(RenderLayerParent<E, M> renderer) {
		super(renderer);
	}

	@Override
	protected RenderType renderType(E enderWoman) {
		if (enderWoman.getPregnancyData().isIncapacitated()) {
			return SURPRISED_ENDER_EYES;
		}
		return MonsterEnderWomanClientHelper.DEFAULT_ENDER_EYES;
	}
}
