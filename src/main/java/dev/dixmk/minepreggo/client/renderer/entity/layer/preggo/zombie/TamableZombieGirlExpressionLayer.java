package dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.zombie;

import java.util.Optional;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.client.model.entity.preggo.zombie.AbstractTamableZombieGirlModel;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobState;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.AbstractTamableZombieGirl;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TamableZombieGirlExpressionLayer 
	<E extends AbstractTamableZombieGirl<?>, M extends AbstractTamableZombieGirlModel<E>> extends AbstractZombieGirlExpressionFacialLayer<E, M> {

	protected static final RenderType SAD2 = RenderType.entityCutoutNoCull(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/zombie/expressions/zombie_girl_face_sad2.png"));
	protected static final RenderType SAD3 = RenderType.entityCutoutNoCull(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/zombie/expressions/zombie_girl_face_sad3.png"));
	protected static final RenderType PAIN4 = RenderType.entityCutoutNoCull(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/zombie/expressions/zombie_girl_face_pain4.png"));
	protected static final RenderType SURPRISED2 = RenderType.entityCutoutNoCull(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/zombie/expressions/zombie_girl_face_surprised2.png"));
	protected static final RenderType HORNY2 = RenderType.entityCutoutNoCull(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/zombie/expressions/zombie_girl_face_horny2.png"));

	public TamableZombieGirlExpressionLayer(RenderLayerParent<E, M> p_117346_) {
		super(p_117346_);
	}
	
	@Override
	public Optional<RenderType> renderType(E zombieGirl) {	
		
		if (zombieGirl.isOnFire()) {
			return Optional.of(SURPRISED2);
		}		
		else if (zombieGirl.hasEffect(MobEffects.CONFUSION)) {
			return Optional.of(PAIN4);
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
		else {
			return Optional.empty();
		}
	}
}
