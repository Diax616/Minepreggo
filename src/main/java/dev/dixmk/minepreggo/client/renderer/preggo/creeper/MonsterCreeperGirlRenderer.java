package dev.dixmk.minepreggo.client.renderer.preggo.creeper;

import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractMonsterCreeperGirlModel;
import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.HostileMonsterCreeperGirlModel;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.HostileMonsterCreeperGirl;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MonsterCreeperGirlRenderer extends AbstractHostileMonsterCreeperGirlRenderer<HostileMonsterCreeperGirl, HostileMonsterCreeperGirlModel> {
	
	public MonsterCreeperGirlRenderer(EntityRendererProvider.Context context) {
		this(context, AbstractMonsterCreeperGirlModel.LAYER_LOCATION, AbstractMonsterCreeperGirlModel.LAYER_ENERGY_ARMOR_LOCATION);
	}
	
	public MonsterCreeperGirlRenderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation armor) {
		super(context, new HostileMonsterCreeperGirlModel(context.bakeLayer(main)), new HostileMonsterCreeperGirlModel(context.bakeLayer(armor)));
	}
	
	@Override
	public ResourceLocation getTextureLocation(HostileMonsterCreeperGirl p_115812_) {
		return MONSTER_CREEPER_GIRL_LOCATION;
	}
}
