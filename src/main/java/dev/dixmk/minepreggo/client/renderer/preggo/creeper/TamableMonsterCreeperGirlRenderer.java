package dev.dixmk.minepreggo.client.renderer.preggo.creeper;

import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.TamableMonsterCreeperGirlModel;
import dev.dixmk.minepreggo.client.model.geom.MinepreggoModelLayers;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableMonsterCreeperGirl;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TamableMonsterCreeperGirlRenderer extends AbstractTamableMonsterCreeperGirlRenderer<TamableMonsterCreeperGirl, TamableMonsterCreeperGirlModel> {
	
	public TamableMonsterCreeperGirlRenderer(EntityRendererProvider.Context context) {
		this(context, MinepreggoModelLayers.MONSTER_CREEPER_GIRL, MinepreggoModelLayers.MONSTER_CREEPER_GIRL_ENERGY_ARMOR, MinepreggoModelLayers.MONSTER_CREEPER_GIRL_OUTER_ARMOR);
	}
	
	protected TamableMonsterCreeperGirlRenderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation eneryArmor, ModelLayerLocation outterArmor) {
		super(context, new TamableMonsterCreeperGirlModel(context.bakeLayer(main)), new TamableMonsterCreeperGirlModel(context.bakeLayer(eneryArmor)), new TamableMonsterCreeperGirlModel(context.bakeLayer(outterArmor)));
	}	
	
	@Override
	public ResourceLocation getTextureLocation(TamableMonsterCreeperGirl entity) {
		return MONSTER_CREEPER_GIRL_LOCATION;
	}
}
