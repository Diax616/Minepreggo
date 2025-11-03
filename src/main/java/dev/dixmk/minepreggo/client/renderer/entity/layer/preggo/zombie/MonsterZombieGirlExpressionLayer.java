package dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.zombie;

import java.util.Optional;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.client.model.entity.preggo.zombie.AbstractMonsterZombieGirlModel;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.AbstractMonsterZombieGirl;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MonsterZombieGirlExpressionLayer 
	<E extends AbstractMonsterZombieGirl, M extends AbstractMonsterZombieGirlModel<E>> extends AbstractZombieGirlExpressionFacialLayer<E, M> {

	protected static final RenderType HOSTIL = RenderType.entityCutoutNoCull(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/zombie/expressions/zombie_girl_face_hostil.png"));
	protected static final RenderType SURPRISED = RenderType.entityCutoutNoCull(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/zombie/expressions/zombie_girl_face_hostil_surprised.png"));

	public MonsterZombieGirlExpressionLayer(RenderLayerParent<E, M> p_117346_) {
		super(p_117346_);
	}
	
	@Override
	public Optional<RenderType> renderType(E creeperGirl) {	
		if (creeperGirl.isOnFire()) {
			return Optional.of(SURPRISED);
		}
		return Optional.of(HOSTIL);
	}
}
