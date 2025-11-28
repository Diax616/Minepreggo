package dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.zombie;

import java.util.Optional;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.client.model.entity.preggo.zombie.AbstractTamablePregnantZombieGirlModel;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobState;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.AbstractTamablePregnantZombieGirl;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TamablePregnantZombieGirlExpressionLayer 
	<E extends AbstractTamablePregnantZombieGirl<?,?>, M extends AbstractTamablePregnantZombieGirlModel<E>> extends TamableZombieGirlExpressionLayer<E, M> {

	protected static final RenderType ANGRY1 = RenderType.entityCutoutNoCull(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/zombie/expressions/zombie_girl_face_angry1.png"));
	protected static final RenderType HORNY2 = RenderType.entityCutoutNoCull(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/zombie/expressions/zombie_girl_face_horny2.png"));
	protected static final RenderType PAIN1 = RenderType.entityCutoutNoCull(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/zombie/expressions/zombie_girl_face_pain1.png"));
	protected static final RenderType PAIN3 = RenderType.entityCutoutNoCull(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/zombie/expressions/zombie_girl_face_pain3.png"));
	protected static final RenderType PAIN2 = RenderType.entityCutoutNoCull(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/zombie/expressions/zombie_girl_face_pain2.png"));
	protected static final RenderType SAD1 = RenderType.entityCutoutNoCull(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/zombie/expressions/zombie_girl_face_sad1.png"));
	protected static final RenderType SURPRISED1 = RenderType.entityCutoutNoCull(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/zombie/expressions/zombie_girl_face_surprised1.png"));
	
	public TamablePregnantZombieGirlExpressionLayer(RenderLayerParent<E, M> p_117346_) {
		super(p_117346_);
	}

	@Override
	public Optional<RenderType> renderType(E zombieGirl) {		
		
		if (zombieGirl.isOnFire()) {
			return Optional.of(SURPRISED2);
		}
			
		final var pain = zombieGirl.getPregnancyPain();
		
		if (pain != null) {			
			switch (pain) {
			case MORNING_SICKNESS: {		
				return Optional.of(PAIN4);
			}
			case FETAL_MOVEMENT: {		
				return Optional.of(PAIN1);
			}
			case CONTRACTION: {		
				return Optional.of(PAIN2);
			}
			case MISCARRIAGE: {		
				return Optional.of(SURPRISED1);
			}
			case PREBIRTH: {		
				return Optional.of(PAIN3);
			}
			case BIRTH: {		
				return Optional.of(PAIN1);
			}
			default:
				break;
			}
		}
		
		if (!zombieGirl.getPregnancySymptoms().isEmpty()) {
			return Optional.of(SAD1);
		}
		else if (zombieGirl.getState() == PreggoMobState.BLUSHED) {
			return Optional.of(HORNY2);
		}
		else if (zombieGirl.isWaiting()) {
			return Optional.of(SAD2);
		}
		else if (zombieGirl.isSavage()) {
			return Optional.of(SAD3);
		}

		return Optional.empty();
	}
}

