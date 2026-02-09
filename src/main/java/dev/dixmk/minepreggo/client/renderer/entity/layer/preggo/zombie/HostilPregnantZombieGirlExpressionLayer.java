package dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.zombie;

import javax.annotation.Nullable;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.client.model.entity.preggo.zombie.AbstractHostilPregnantZombieGirlModel;
import dev.dixmk.minepreggo.utils.MinepreggoHelper;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.AbstractHostilPregnantZombieGirl;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HostilPregnantZombieGirlExpressionLayer 
	<E extends AbstractHostilPregnantZombieGirl, M extends AbstractHostilPregnantZombieGirlModel<E>> extends HostilZombieGirlExpressionLayer<E, M> {

	protected static final RenderType HOSTIL_PAIN = RenderType.entityCutoutNoCull(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/zombie/expressions/zombie_girl_face_hostil_pain.png"));
	
	public HostilPregnantZombieGirlExpressionLayer(RenderLayerParent<E, M> p_117346_) {
		super(p_117346_);
	}

	@Override
	public @Nullable RenderType renderType(E zombieGirl) {		
		if (zombieGirl.getPregnancyData().isIncapacitated()) {
			return HOSTIL_PAIN;
		}	
		return super.renderType(zombieGirl);
	}
}
