package dev.dixmk.minepreggo.client.renderer.preggo.creeper;

import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.quadruped.AbstractTamableMonsterCreeperGirlModel;
import dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.creeper.TamableMonsterCreeperGirlExpressionLayer;
import dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.creeper.MonsterCreeperGirlArmorLayer;
import dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.creeper.MonsterCreeperGirlHeldItemLayer;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractTamableMonsterCreeperGirl;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractTamableMonsterCreeperGirlRenderer 
	<E extends AbstractTamableMonsterCreeperGirl, M extends AbstractTamableMonsterCreeperGirlModel<E>> extends AbstractCreeperGirlRenderer<E, M> {

	protected AbstractTamableMonsterCreeperGirlRenderer(Context context, M main, M eneryArmor, M outterArmor) {
		super(context, main, eneryArmor);
		this.addLayer(new MonsterCreeperGirlHeldItemLayer<>(this, context.getItemInHandRenderer()));
		this.addLayer(new CustomHeadLayer<>(this, context.getModelSet(), context.getItemInHandRenderer()));
		this.addLayer(new MonsterCreeperGirlArmorLayer<>(this, outterArmor, context.getModelManager()));
		this.addLayer(new TamableMonsterCreeperGirlExpressionLayer<>(this));
	}
}
