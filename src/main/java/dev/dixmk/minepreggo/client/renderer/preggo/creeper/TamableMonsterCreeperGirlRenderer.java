package dev.dixmk.minepreggo.client.renderer.preggo.creeper;

import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractMonsterCreeperGirlModel;
import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.TamableMonsterCreeperGirlModel;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableMonsterCreeperGirl;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TamableMonsterCreeperGirlRenderer extends AbstractTamableMonsterCreeperGirlRenderer<TamableMonsterCreeperGirl, TamableMonsterCreeperGirlModel> {
	
	public TamableMonsterCreeperGirlRenderer(EntityRendererProvider.Context context) {
		this(context, AbstractMonsterCreeperGirlModel.LAYER_LOCATION, AbstractMonsterCreeperGirlModel.LAYER_ENERGY_ARMOR_LOCATION, AbstractMonsterCreeperGirlModel.LAYER_OUTER_ARMOR_LOCATION);
	}
	
	protected TamableMonsterCreeperGirlRenderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation eneryArmor, ModelLayerLocation outterArmor) {
		super(context, new TamableMonsterCreeperGirlModel(context.bakeLayer(main)), new TamableMonsterCreeperGirlModel(context.bakeLayer(eneryArmor)), new TamableMonsterCreeperGirlModel(context.bakeLayer(outterArmor)));
	}	
	
	@Override
	public ResourceLocation getTextureLocation(TamableMonsterCreeperGirl p_115812_) {
		return MONSTER_CREEPER_GIRL_LOCATION;
	}
}
