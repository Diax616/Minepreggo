package dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.zombie;

import javax.annotation.Nullable;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.client.model.entity.preggo.zombie.AbstractTamablePregnantZombieGirlModel;
import dev.dixmk.minepreggo.utils.MinepreggoHelper;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.AbstractTamablePregnantZombieGirl;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPain;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TamablePregnantZombieGirlExpressionLayer 
	<E extends AbstractTamablePregnantZombieGirl, M extends AbstractTamablePregnantZombieGirlModel<E>> extends TamableZombieGirlExpressionLayer<E, M> {

	protected static final RenderType HORNY2 = RenderType.entityCutoutNoCull(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/zombie/expressions/zombie_girl_face_horny2.png"));
	protected static final RenderType PAIN1 = RenderType.entityCutoutNoCull(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/zombie/expressions/zombie_girl_face_pain1.png"));
	protected static final RenderType PAIN3 = RenderType.entityCutoutNoCull(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/zombie/expressions/zombie_girl_face_pain3.png"));
	protected static final RenderType PAIN2 = RenderType.entityCutoutNoCull(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/zombie/expressions/zombie_girl_face_pain2.png"));
	protected static final RenderType SAD1 = RenderType.entityCutoutNoCull(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/zombie/expressions/zombie_girl_face_sad1.png"));
	protected static final RenderType SURPRISED1 = RenderType.entityCutoutNoCull(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/zombie/expressions/zombie_girl_face_surprised1.png"));
	
	public TamablePregnantZombieGirlExpressionLayer(RenderLayerParent<E, M> p_117346_) {
		super(p_117346_);
	}

	@Override
	public @Nullable RenderType renderType(E zombieGirl) {			
		final var pregnancyData = zombieGirl.getPregnancyData();
		final var pain = pregnancyData.getPregnancyPain();
		
		if (zombieGirl.isOnFire() || pain == PregnancyPain.WATER_BREAKING) {
			return SURPRISED2;
		}
				
		if (pain != null) {			
			switch (pain) {
			case MORNING_SICKNESS: {		
				return PAIN4;
			}
			case FETAL_MOVEMENT, BIRTH: {		
				return PAIN1;
			}
			case CONTRACTION: {		
				return PAIN2;
			}
			case MISCARRIAGE: {		
				return SURPRISED1;
			}
			case PREBIRTH: {		
				return PAIN3;
			}
			default:
				break;
			}
		}

		if (!pregnancyData.getSyncedPregnancySymptoms().isEmpty()) {
			return SAD1;
		}

		return super.renderType(zombieGirl);
	}
}

