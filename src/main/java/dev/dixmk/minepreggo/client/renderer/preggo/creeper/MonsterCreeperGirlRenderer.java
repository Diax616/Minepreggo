package dev.dixmk.minepreggo.client.renderer.preggo.creeper;

import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractMonsterCreeperGirlModel;
import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.HostilMonsterCreeperGirlModel;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.HostilMonsterCreeperGirl;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MonsterCreeperGirlRenderer extends AbstractHostilMonsterCreeperGirlRenderer<HostilMonsterCreeperGirl, HostilMonsterCreeperGirlModel> {
	
	public MonsterCreeperGirlRenderer(EntityRendererProvider.Context context) {
		this(context, AbstractMonsterCreeperGirlModel.LAYER_LOCATION, AbstractMonsterCreeperGirlModel.LAYER_ENERGY_ARMOR_LOCATION);
	}
	
	public MonsterCreeperGirlRenderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation armor) {
		super(context, new HostilMonsterCreeperGirlModel(context.bakeLayer(main)), new HostilMonsterCreeperGirlModel(context.bakeLayer(armor)));
	}
	
	@Override
	public ResourceLocation getTextureLocation(HostilMonsterCreeperGirl p_115812_) {
		return MONSTER_CREEPER_GIRL_LOCATION;
	}
}
