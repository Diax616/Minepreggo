package dev.dixmk.minepreggo.client.event;

import java.util.function.Consumer;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.client.model.armor.BellyShieldP5Model;
import dev.dixmk.minepreggo.client.model.armor.BellyShieldP6Model;
import dev.dixmk.minepreggo.client.model.armor.BellyShieldP7Model;
import dev.dixmk.minepreggo.client.model.armor.BellyShieldP8Model;
import dev.dixmk.minepreggo.client.model.armor.FemaleChestPlateP0Model;
import dev.dixmk.minepreggo.client.model.armor.KneeBraceModel;
import dev.dixmk.minepreggo.client.model.armor.MaternityChestPlateP1Model;
import dev.dixmk.minepreggo.client.model.armor.MaternityChestPlateP2Model;
import dev.dixmk.minepreggo.client.model.armor.MaternityChestPlateP3Model;
import dev.dixmk.minepreggo.client.model.armor.MaternityChestPlateP4Model;
import dev.dixmk.minepreggo.client.model.entity.player.CustomBoobsModel;
import dev.dixmk.minepreggo.client.model.entity.player.CustomPregnantBodyP0Model;
import dev.dixmk.minepreggo.client.model.entity.player.CustomPregnantBodyP1Model;
import dev.dixmk.minepreggo.client.model.entity.player.CustomPregnantBodyP2Model;
import dev.dixmk.minepreggo.client.model.entity.player.CustomPregnantBodyP3Model;
import dev.dixmk.minepreggo.client.model.entity.player.CustomPregnantBodyP4Model;
import dev.dixmk.minepreggo.client.model.entity.player.CustomPregnantBodyP5Model;
import dev.dixmk.minepreggo.client.model.entity.player.CustomPregnantBodyP6Model;
import dev.dixmk.minepreggo.client.model.entity.player.CustomPregnantBodyP7Model;
import dev.dixmk.minepreggo.client.model.entity.player.CustomPregnantBodyP8Model;
import dev.dixmk.minepreggo.client.model.entity.player.PredefinedBoobsModel;
import dev.dixmk.minepreggo.client.model.entity.player.PredefinedPregnantBodyP0Model;
import dev.dixmk.minepreggo.client.model.entity.player.PredefinedPregnantBodyP1Model;
import dev.dixmk.minepreggo.client.model.entity.player.PredefinedPregnantBodyP2Model;
import dev.dixmk.minepreggo.client.model.entity.player.PredefinedPregnantBodyP3Model;
import dev.dixmk.minepreggo.client.model.entity.player.PredefinedPregnantBodyP4Model;
import dev.dixmk.minepreggo.client.model.entity.player.PredefinedPregnantBodyP5Model;
import dev.dixmk.minepreggo.client.model.entity.player.PredefinedPregnantBodyP6Model;
import dev.dixmk.minepreggo.client.model.entity.player.PredefinedPregnantBodyP7Model;
import dev.dixmk.minepreggo.client.model.entity.player.PredefinedPregnantBodyP8Model;
import dev.dixmk.minepreggo.client.model.entity.preggo.PregnantFemaleHumanoidModel;
import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractHumanoidCreeperGirlModel;
import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.quadruped.AbstractCreeperGirlModel;
import dev.dixmk.minepreggo.client.model.entity.preggo.ender.AbstractEnderWomanModel;
import dev.dixmk.minepreggo.client.model.entity.preggo.zombie.AbstractZombieGirlModel;
import dev.dixmk.minepreggo.client.renderer.entity.FertilityWitchRenderer;
import dev.dixmk.minepreggo.client.renderer.entity.ScientificIllagerRenderer;
import dev.dixmk.minepreggo.client.renderer.entity.layer.player.CustomPregnantBodyLayer;
import dev.dixmk.minepreggo.client.renderer.entity.layer.player.PredefinedPregnantBodyLayer;
import dev.dixmk.minepreggo.client.renderer.preggo.creeper.IllCreeperGirlRenderer;
import dev.dixmk.minepreggo.client.renderer.preggo.creeper.IllHumanoidCreeperGirlRenderer;
import dev.dixmk.minepreggo.client.renderer.preggo.creeper.MonsterCreeperGirlRenderer;
import dev.dixmk.minepreggo.client.renderer.preggo.creeper.MonsterHumanoidCreeperGirlP3Renderer;
import dev.dixmk.minepreggo.client.renderer.preggo.creeper.MonsterHumanoidCreeperGirlP5Renderer;
import dev.dixmk.minepreggo.client.renderer.preggo.creeper.MonsterHumanoidCreeperGirlP7Renderer;
import dev.dixmk.minepreggo.client.renderer.preggo.creeper.MonsterHumanoidCreeperGirlRenderer;
import dev.dixmk.minepreggo.client.renderer.preggo.creeper.TamableHumanoidCreeperGirlP0Renderer;
import dev.dixmk.minepreggo.client.renderer.preggo.creeper.TamableHumanoidCreeperGirlP1Renderer;
import dev.dixmk.minepreggo.client.renderer.preggo.creeper.TamableHumanoidCreeperGirlP2Renderer;
import dev.dixmk.minepreggo.client.renderer.preggo.creeper.TamableHumanoidCreeperGirlP3Renderer;
import dev.dixmk.minepreggo.client.renderer.preggo.creeper.TamableHumanoidCreeperGirlP4Renderer;
import dev.dixmk.minepreggo.client.renderer.preggo.creeper.TamableHumanoidCreeperGirlP5Renderer;
import dev.dixmk.minepreggo.client.renderer.preggo.creeper.TamableHumanoidCreeperGirlP6Renderer;
import dev.dixmk.minepreggo.client.renderer.preggo.creeper.TamableHumanoidCreeperGirlP7Renderer;
import dev.dixmk.minepreggo.client.renderer.preggo.creeper.TamableHumanoidCreeperGirlP8Renderer;
import dev.dixmk.minepreggo.client.renderer.preggo.creeper.TamableHumanoidCreeperGirlRenderer;
import dev.dixmk.minepreggo.client.renderer.preggo.ender.IllEnderWomanRenderer;
import dev.dixmk.minepreggo.client.renderer.preggo.ender.MonsterlEnderWomanRenderer;
import dev.dixmk.minepreggo.client.renderer.preggo.zombie.IllZombieGirlRenderer;
import dev.dixmk.minepreggo.client.renderer.preggo.zombie.MonsterZombieGirlP3Renderer;
import dev.dixmk.minepreggo.client.renderer.preggo.zombie.MonsterZombieGirlP5Renderer;
import dev.dixmk.minepreggo.client.renderer.preggo.zombie.MonsterZombieGirlP7Renderer;
import dev.dixmk.minepreggo.client.renderer.preggo.zombie.MonsterZombieGirlRenderer;
import dev.dixmk.minepreggo.client.renderer.preggo.zombie.TamableZombieGirlP0Renderer;
import dev.dixmk.minepreggo.client.renderer.preggo.zombie.TamableZombieGirlP1Renderer;
import dev.dixmk.minepreggo.client.renderer.preggo.zombie.TamableZombieGirlP2Renderer;
import dev.dixmk.minepreggo.client.renderer.preggo.zombie.TamableZombieGirlP3Renderer;
import dev.dixmk.minepreggo.client.renderer.preggo.zombie.TamableZombieGirlP4Renderer;
import dev.dixmk.minepreggo.client.renderer.preggo.zombie.TamableZombieGirlP5Renderer;
import dev.dixmk.minepreggo.client.renderer.preggo.zombie.TamableZombieGirlP6Renderer;
import dev.dixmk.minepreggo.client.renderer.preggo.zombie.TamableZombieGirlP7Renderer;
import dev.dixmk.minepreggo.client.renderer.preggo.zombie.TamableZombieGirlP8Renderer;
import dev.dixmk.minepreggo.client.renderer.preggo.zombie.TamableZombieGirlRenderer;
import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.init.MinepreggoModItems;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.NoopRenderer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MinepreggoMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetupHandler {

	private ClientSetupHandler() {}
	
    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.AddLayers event) {	
    	Consumer<String> registerBoobsLayer = skinName -> {
            EntityRenderer<? extends Player> renderer = event.getPlayerSkin(skinName);
            if (renderer instanceof PlayerRenderer playerRenderer) {
                playerRenderer.addLayer(new CustomPregnantBodyLayer(playerRenderer, event.getEntityModels()));
                playerRenderer.addLayer(new PredefinedPregnantBodyLayer(playerRenderer, event.getEntityModels()));
            }
		};
    	
		registerBoobsLayer.accept("default");
		registerBoobsLayer.accept("slim");
    }

    @SubscribeEvent
    public static void registerItemColors(RegisterColorHandlersEvent.Item event) {
        event.register((stack, tintIndex) -> tintIndex > 0 ? -1 : ((DyeableLeatherItem) stack.getItem()).getColor(stack),     		
        MinepreggoModItems.FEMALE_LEATHER_P0_CHESTPLATE.get(),
        MinepreggoModItems.MATERNITY_LEATHER_P1_CHESTPLATE.get(),
        MinepreggoModItems.MATERNITY_LEATHER_P2_CHESTPLATE.get(),
        MinepreggoModItems.MATERNITY_LEATHER_P3_CHESTPLATE.get(),
        MinepreggoModItems.MATERNITY_LEATHER_P4_CHESTPLATE.get(),
        MinepreggoModItems.LEATHER_KNEE_BRACE.get());    
    }
   
	
	@SubscribeEvent
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(MinepreggoModEntities.MONSTER_ZOMBIE_GIRL.get(), MonsterZombieGirlRenderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.MONSTER_ZOMBIE_GIRL_P3.get(), MonsterZombieGirlP3Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.MONSTER_ZOMBIE_GIRL_P5.get(), MonsterZombieGirlP5Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.MONSTER_ZOMBIE_GIRL_P7.get(), MonsterZombieGirlP7Renderer::new);	
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL.get(), TamableZombieGirlRenderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P0.get(), TamableZombieGirlP0Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P1.get(), TamableZombieGirlP1Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P2.get(), TamableZombieGirlP2Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P3.get(), TamableZombieGirlP3Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P4.get(), TamableZombieGirlP4Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P5.get(), TamableZombieGirlP5Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P6.get(), TamableZombieGirlP6Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P7.get(), TamableZombieGirlP7Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P8.get(), TamableZombieGirlP8Renderer::new);
		
		event.registerEntityRenderer(MinepreggoModEntities.MONSTER_HUMANOID_CREEPER_GIRL.get(), MonsterHumanoidCreeperGirlRenderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.MONSTER_HUMANOID_CREEPER_GIRL_P3.get(), MonsterHumanoidCreeperGirlP3Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.MONSTER_HUMANOID_CREEPER_GIRL_P5.get(), MonsterHumanoidCreeperGirlP5Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.MONSTER_HUMANOID_CREEPER_GIRL_P7.get(), MonsterHumanoidCreeperGirlP7Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL.get(), TamableHumanoidCreeperGirlRenderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P0.get(), TamableHumanoidCreeperGirlP0Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P1.get(), TamableHumanoidCreeperGirlP1Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P2.get(), TamableHumanoidCreeperGirlP2Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P3.get(), TamableHumanoidCreeperGirlP3Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P4.get(), TamableHumanoidCreeperGirlP4Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P5.get(), TamableHumanoidCreeperGirlP5Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P6.get(), TamableHumanoidCreeperGirlP6Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P7.get(), TamableHumanoidCreeperGirlP7Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P8.get(), TamableHumanoidCreeperGirlP8Renderer::new);
				
		event.registerEntityRenderer(MinepreggoModEntities.ILL_HUMANOID_CREEPER_GIRL.get(), IllHumanoidCreeperGirlRenderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.ILL_ZOMBIE_GIRL.get(), IllZombieGirlRenderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.ILL_ENDER_WOMAN.get(), IllEnderWomanRenderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.ILL_CREEPER_GIRL.get(), IllCreeperGirlRenderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.SCIENTIFIC_ILLAGER.get(), ScientificIllagerRenderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.FERTILITY_WITCH.get(), FertilityWitchRenderer::new);

		event.registerEntityRenderer(MinepreggoModEntities.MONSTER_ENDER_WOMAN.get(), MonsterlEnderWomanRenderer::new);	
		event.registerEntityRenderer(MinepreggoModEntities.MONSTER_CREEPER_GIRL.get(), MonsterCreeperGirlRenderer::new);
	
		event.registerEntityRenderer(MinepreggoModEntities.BELLY_PART.get(), NoopRenderer::new);
	}
	
	@SubscribeEvent
	public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(AbstractZombieGirlModel.LAYER_LOCATION, AbstractZombieGirlModel::createBodyLayer);
		event.registerLayerDefinition(AbstractZombieGirlModel.LAYER_LOCATION_P0, AbstractZombieGirlModel::createP0BodyLayer);
		event.registerLayerDefinition(AbstractZombieGirlModel.LAYER_LOCATION_P1, AbstractZombieGirlModel::createP1BodyLayer);
		event.registerLayerDefinition(AbstractZombieGirlModel.LAYER_LOCATION_P2, AbstractZombieGirlModel::createP2BodyLayer);
		event.registerLayerDefinition(AbstractZombieGirlModel.LAYER_LOCATION_P3, AbstractZombieGirlModel::createP3BodyLayer);
		event.registerLayerDefinition(AbstractZombieGirlModel.LAYER_LOCATION_P4, AbstractZombieGirlModel::createP4BodyLayer);
		event.registerLayerDefinition(AbstractZombieGirlModel.LAYER_LOCATION_P5, AbstractZombieGirlModel::createP5BodyLayer);
		event.registerLayerDefinition(AbstractZombieGirlModel.LAYER_LOCATION_P6, AbstractZombieGirlModel::createP6BodyLayer);
		event.registerLayerDefinition(AbstractZombieGirlModel.LAYER_LOCATION_P7, AbstractZombieGirlModel::createP7BodyLayer);
		event.registerLayerDefinition(AbstractZombieGirlModel.LAYER_LOCATION_P8, AbstractZombieGirlModel::createP8BodyLayer);
		event.registerLayerDefinition(AbstractZombieGirlModel.LAYER_INNER_ARMOR_LOCATION, PregnantFemaleHumanoidModel::createInnerLayer);
		event.registerLayerDefinition(AbstractZombieGirlModel.LAYER_OUTER_ARMOR_LOCATION, PregnantFemaleHumanoidModel::createOuterLayer);
		
		event.registerLayerDefinition(AbstractHumanoidCreeperGirlModel.LAYER_LOCATION, AbstractHumanoidCreeperGirlModel::createBodyLayer);
		event.registerLayerDefinition(AbstractHumanoidCreeperGirlModel.LAYER_LOCATION_P0, AbstractHumanoidCreeperGirlModel::createP0BodyLayer);
		event.registerLayerDefinition(AbstractHumanoidCreeperGirlModel.LAYER_LOCATION_P1, AbstractHumanoidCreeperGirlModel::createP1BodyLayer);
		event.registerLayerDefinition(AbstractHumanoidCreeperGirlModel.LAYER_LOCATION_P2, AbstractHumanoidCreeperGirlModel::createP2BodyLayer);
		event.registerLayerDefinition(AbstractHumanoidCreeperGirlModel.LAYER_LOCATION_P3, AbstractHumanoidCreeperGirlModel::createP3BodyLayer);
		event.registerLayerDefinition(AbstractHumanoidCreeperGirlModel.LAYER_LOCATION_P4, AbstractHumanoidCreeperGirlModel::createP4BodyLayer);
		event.registerLayerDefinition(AbstractHumanoidCreeperGirlModel.LAYER_LOCATION_P5, AbstractHumanoidCreeperGirlModel::createP5BodyLayer);
		event.registerLayerDefinition(AbstractHumanoidCreeperGirlModel.LAYER_LOCATION_P6, AbstractHumanoidCreeperGirlModel::createP6BodyLayer);
		event.registerLayerDefinition(AbstractHumanoidCreeperGirlModel.LAYER_LOCATION_P7, AbstractHumanoidCreeperGirlModel::createP7BodyLayer);
		event.registerLayerDefinition(AbstractHumanoidCreeperGirlModel.LAYER_LOCATION_P8, AbstractHumanoidCreeperGirlModel::createP8BodyLayer);
		event.registerLayerDefinition(AbstractHumanoidCreeperGirlModel.LAYER_INNER_ARMOR_LOCATION, PregnantFemaleHumanoidModel::createInnerLayer);
		event.registerLayerDefinition(AbstractHumanoidCreeperGirlModel.LAYER_OUTER_ARMOR_LOCATION, PregnantFemaleHumanoidModel::createOuterLayer);
		event.registerLayerDefinition(AbstractHumanoidCreeperGirlModel.LAYER_ENERGY_ARMOR_LOCATION, AbstractHumanoidCreeperGirlModel::createBodyLayer);
		event.registerLayerDefinition(AbstractHumanoidCreeperGirlModel.LAYER_ENERGY_ARMOR_P0_LOCATION, AbstractHumanoidCreeperGirlModel::createP0BodyLayer);
		event.registerLayerDefinition(AbstractHumanoidCreeperGirlModel.LAYER_ENERGY_ARMOR_P1_LOCATION, AbstractHumanoidCreeperGirlModel::createP1BodyLayer);
		event.registerLayerDefinition(AbstractHumanoidCreeperGirlModel.LAYER_ENERGY_ARMOR_P2_LOCATION, AbstractHumanoidCreeperGirlModel::createP2BodyLayer);
		event.registerLayerDefinition(AbstractHumanoidCreeperGirlModel.LAYER_ENERGY_ARMOR_P3_LOCATION, AbstractHumanoidCreeperGirlModel::createP3BodyLayer);
		event.registerLayerDefinition(AbstractHumanoidCreeperGirlModel.LAYER_ENERGY_ARMOR_P4_LOCATION, AbstractHumanoidCreeperGirlModel::createP4BodyLayer);
		event.registerLayerDefinition(AbstractHumanoidCreeperGirlModel.LAYER_ENERGY_ARMOR_P5_LOCATION, AbstractHumanoidCreeperGirlModel::createP5BodyLayer);
		event.registerLayerDefinition(AbstractHumanoidCreeperGirlModel.LAYER_ENERGY_ARMOR_P6_LOCATION, AbstractHumanoidCreeperGirlModel::createP6BodyLayer);
		event.registerLayerDefinition(AbstractHumanoidCreeperGirlModel.LAYER_ENERGY_ARMOR_P7_LOCATION, AbstractHumanoidCreeperGirlModel::createP7BodyLayer);
		event.registerLayerDefinition(AbstractHumanoidCreeperGirlModel.LAYER_ENERGY_ARMOR_P8_LOCATION, AbstractHumanoidCreeperGirlModel::createP8BodyLayer);
				
		event.registerLayerDefinition(AbstractCreeperGirlModel.LAYER_LOCATION, AbstractCreeperGirlModel::createP0BodyLayer);		
		event.registerLayerDefinition(AbstractCreeperGirlModel.LAYER_OUTER_ARMOR_LOCATION, AbstractCreeperGirlModel::createOuterLayer);
		event.registerLayerDefinition(AbstractCreeperGirlModel.LAYER_ENERGY_ARMOR_LOCATION, AbstractCreeperGirlModel::createP0BodyLayer);		
		
		event.registerLayerDefinition(AbstractEnderWomanModel.LAYER_LOCATION, AbstractEnderWomanModel::createP0BodyLayer);
		event.registerLayerDefinition(AbstractEnderWomanModel.LAYER_OUTER_ARMOR_LOCATION, AbstractEnderWomanModel::createOuterLayer);
		event.registerLayerDefinition(AbstractEnderWomanModel.LAYER_INNER_ARMOR_LOCATION, AbstractEnderWomanModel::createInnerLayer);
		
		event.registerLayerDefinition(BellyShieldP5Model.LAYER_LOCATION, BellyShieldP5Model::createBodyLayer);
		event.registerLayerDefinition(BellyShieldP6Model.LAYER_LOCATION, BellyShieldP6Model::createBodyLayer);
		event.registerLayerDefinition(BellyShieldP7Model.LAYER_LOCATION, BellyShieldP7Model::createBodyLayer);
		event.registerLayerDefinition(BellyShieldP8Model.LAYER_LOCATION, BellyShieldP8Model::createBodyLayer);
		event.registerLayerDefinition(FemaleChestPlateP0Model.LAYER_LOCATION, FemaleChestPlateP0Model::createBodyLayer);
		event.registerLayerDefinition(MaternityChestPlateP1Model.LAYER_LOCATION, MaternityChestPlateP1Model::createBodyLayer);
		event.registerLayerDefinition(MaternityChestPlateP2Model.LAYER_LOCATION, MaternityChestPlateP2Model::createBodyLayer);
		event.registerLayerDefinition(MaternityChestPlateP3Model.LAYER_LOCATION, MaternityChestPlateP3Model::createBodyLayer);
		event.registerLayerDefinition(MaternityChestPlateP4Model.LAYER_LOCATION, MaternityChestPlateP4Model::createBodyLayer);
		event.registerLayerDefinition(KneeBraceModel.LAYER_LOCATION, KneeBraceModel::createBodyLayer);
	
		event.registerLayerDefinition(CustomBoobsModel.LAYER_LOCATION, CustomBoobsModel::createBodyLayer);
		event.registerLayerDefinition(PredefinedBoobsModel.LAYER_LOCATION, PredefinedBoobsModel::createBodyLayer);

		event.registerLayerDefinition(CustomPregnantBodyP0Model.LAYER_LOCATION, CustomPregnantBodyP0Model::createBodyLayer);
		event.registerLayerDefinition(CustomPregnantBodyP1Model.LAYER_LOCATION, CustomPregnantBodyP1Model::createBodyLayer);
		event.registerLayerDefinition(CustomPregnantBodyP2Model.LAYER_LOCATION, CustomPregnantBodyP2Model::createBodyLayer);
		event.registerLayerDefinition(CustomPregnantBodyP3Model.LAYER_LOCATION, CustomPregnantBodyP3Model::createBodyLayer);
		event.registerLayerDefinition(CustomPregnantBodyP4Model.LAYER_LOCATION, CustomPregnantBodyP4Model::createBodyLayer);
		event.registerLayerDefinition(CustomPregnantBodyP5Model.LAYER_LOCATION, CustomPregnantBodyP5Model::createBodyLayer);
		event.registerLayerDefinition(CustomPregnantBodyP6Model.LAYER_LOCATION, CustomPregnantBodyP6Model::createBodyLayer);
		event.registerLayerDefinition(CustomPregnantBodyP7Model.LAYER_LOCATION, CustomPregnantBodyP7Model::createBodyLayer);
		event.registerLayerDefinition(CustomPregnantBodyP8Model.LAYER_LOCATION, CustomPregnantBodyP8Model::createBodyLayer);
	
		event.registerLayerDefinition(PredefinedPregnantBodyP0Model.LAYER_LOCATION, PredefinedPregnantBodyP0Model::createBodyLayer);
		event.registerLayerDefinition(PredefinedPregnantBodyP1Model.LAYER_LOCATION, PredefinedPregnantBodyP1Model::createBodyLayer);
		event.registerLayerDefinition(PredefinedPregnantBodyP2Model.LAYER_LOCATION, PredefinedPregnantBodyP2Model::createBodyLayer);
		event.registerLayerDefinition(PredefinedPregnantBodyP3Model.LAYER_LOCATION, PredefinedPregnantBodyP3Model::createBodyLayer);
		event.registerLayerDefinition(PredefinedPregnantBodyP4Model.LAYER_LOCATION, PredefinedPregnantBodyP4Model::createBodyLayer);
		event.registerLayerDefinition(PredefinedPregnantBodyP5Model.LAYER_LOCATION, PredefinedPregnantBodyP5Model::createBodyLayer);
		event.registerLayerDefinition(PredefinedPregnantBodyP6Model.LAYER_LOCATION, PredefinedPregnantBodyP6Model::createBodyLayer);
		event.registerLayerDefinition(PredefinedPregnantBodyP7Model.LAYER_LOCATION, PredefinedPregnantBodyP7Model::createBodyLayer);
		event.registerLayerDefinition(PredefinedPregnantBodyP8Model.LAYER_LOCATION, PredefinedPregnantBodyP8Model::createBodyLayer);
	}
}