package dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.creeper;

import java.util.Optional;

import javax.annotation.Nonnull;

import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractMonsterCreeperGirlModel;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractMonsterHumanoidCreeperGirl;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HumanoidMonsterCreeperGirlExpressionLayer 
	<E extends AbstractMonsterHumanoidCreeperGirl, M extends AbstractMonsterCreeperGirlModel<E>> extends AbstractHumanoidCreeperGirlExpressionFacialLayer<E, M> {

	public HumanoidMonsterCreeperGirlExpressionLayer(RenderLayerParent<E, M> p_117346_) {
		super(p_117346_);
	}

	
	@Nonnull
	public Optional<RenderType> renderType(E creeperGirl) {	
		return Optional.of(HOSTIL);
	}
}

