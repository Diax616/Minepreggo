package dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.zombie;

import javax.annotation.Nullable;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.client.model.entity.preggo.zombie.AbstractHostilePregnantZombieGirlModel;
import dev.dixmk.minepreggo.utils.MinepreggoHelper;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.AbstractHostilePregnantZombieGirl;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HostilePregnantZombieGirlExpressionLayer 
	<E extends AbstractHostilePregnantZombieGirl, M extends AbstractHostilePregnantZombieGirlModel<E>> extends HostileZombieGirlExpressionLayer<E, M> {

	protected static final RenderType HOSTIL_PAIN = RenderType.entityCutoutNoCull(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/zombie/expressions/zombie_girl_face_hostil_pain.png"));
	
	public HostilePregnantZombieGirlExpressionLayer(RenderLayerParent<E, M> p_117346_) {
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
