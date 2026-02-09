package dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.ender;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.client.model.entity.preggo.ender.AbstractHostilPregnantMonsterEnderWomanModel;
import dev.dixmk.minepreggo.client.renderer.entity.layer.ExpressiveEyesLayer;
import dev.dixmk.minepreggo.utils.MinepreggoHelper;
import dev.dixmk.minepreggo.world.entity.preggo.ender.AbstractHostilPregnantEnderWoman;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HostilPregnantMonsterEnderWomanEyesLayer 
	<E extends AbstractHostilPregnantEnderWoman, M extends AbstractHostilPregnantMonsterEnderWomanModel<E>> extends ExpressiveEyesLayer<E, M> {

	protected static final RenderType SURPRISED_ENDER_EYES = RenderType.eyes(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/ender/monster/expressions/monster_ender_woman_eye_surprised.png"));
	
	public HostilPregnantMonsterEnderWomanEyesLayer(RenderLayerParent<E, M> p_116981_) {
		super(p_116981_);
	}

	@Override
	protected RenderType renderType(E enderWoman) {
		if (enderWoman.getPregnancyData().isIncapacitated()) {
			return SURPRISED_ENDER_EYES;
		}
		return MonsterEnderWomanClientHelper.DEFAULT_ENDER_EYES;
	}
}
