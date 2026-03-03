package dev.dixmk.minepreggo.client.renderer.preggo.creeper;

import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.HostileMonsterCreeperGirlModel;
import dev.dixmk.minepreggo.client.model.geom.MinepreggoModelLayers;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.HostileMonsterCreeperGirl;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MonsterCreeperGirlRenderer extends AbstractHostileMonsterCreeperGirlRenderer<HostileMonsterCreeperGirl, HostileMonsterCreeperGirlModel> {
	
	public MonsterCreeperGirlRenderer(EntityRendererProvider.Context context) {
		this(context, MinepreggoModelLayers.MONSTER_CREEPER_GIRL, MinepreggoModelLayers.MONSTER_CREEPER_GIRL_ENERGY_ARMOR);
	}
	
	public MonsterCreeperGirlRenderer(EntityRendererProvider.Context context, ModelLayerLocation main, ModelLayerLocation armor) {
		super(context, new HostileMonsterCreeperGirlModel(context.bakeLayer(main)), new HostileMonsterCreeperGirlModel(context.bakeLayer(armor)));
	}
	
	@Override
	public ResourceLocation getTextureLocation(HostileMonsterCreeperGirl entity) {
		return MONSTER_CREEPER_GIRL_LOCATION;
	}
}
