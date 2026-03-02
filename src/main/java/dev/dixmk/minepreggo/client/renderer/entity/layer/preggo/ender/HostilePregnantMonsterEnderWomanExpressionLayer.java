package dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.ender;

import javax.annotation.Nullable;

import dev.dixmk.minepreggo.client.model.entity.preggo.ender.AbstractHostilePregnantMonsterEnderWomanModel;
import dev.dixmk.minepreggo.client.renderer.entity.layer.ExpressiveFaceLayer;
import dev.dixmk.minepreggo.utils.MinepreggoHelper;
import dev.dixmk.minepreggo.world.entity.preggo.ender.AbstractHostilePregnantEnderWoman;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HostilePregnantMonsterEnderWomanExpressionLayer
	<E extends AbstractHostilePregnantEnderWoman, M extends AbstractHostilePregnantMonsterEnderWomanModel<E>> extends ExpressiveFaceLayer<E, M> {

	protected static final RenderType SUPRISED_MASK = RenderType.entityCutoutNoCull(MinepreggoHelper.fromThisNamespaceAndPath("textures/entity/preggo/ender/monster/expressions/monster_ender_woman_face_surprised.png"));

	public HostilePregnantMonsterEnderWomanExpressionLayer(RenderLayerParent<E, M> renderer) {
		super(renderer);
	}

	@Override
	public @Nullable RenderType renderType(E entity) {
		if (entity.getPregnancyData().isIncapacitated()) {
			return SUPRISED_MASK;
		}	
		return null;
	}
}
