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
import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractMonsterCreeperGirlModel;
import dev.dixmk.minepreggo.client.model.entity.preggo.ender.AbstractMonsterEnderWomanModel;
import dev.dixmk.minepreggo.client.model.entity.preggo.zombie.AbstractZombieGirlModel;
import dev.dixmk.minepreggo.client.renderer.entity.FertilityWitchRenderer;
import dev.dixmk.minepreggo.client.renderer.entity.ScientificIllagerRenderer;
import dev.dixmk.minepreggo.client.renderer.entity.layer.player.CustomPregnantBodyLayer;
import dev.dixmk.minepreggo.client.renderer.entity.layer.player.PredefinedPregnantBodyLayer;
import dev.dixmk.minepreggo.client.renderer.preggo.creeper.IllMonsterCreeperGirlRenderer;
import dev.dixmk.minepreggo.client.renderer.preggo.creeper.HostilPregnantMonsterCreeperGirlRenderer;
import dev.dixmk.minepreggo.client.renderer.preggo.creeper.IllHumanoidCreeperGirlRenderer;
import dev.dixmk.minepreggo.client.renderer.preggo.creeper.MonsterCreeperGirlRenderer;
import dev.dixmk.minepreggo.client.renderer.preggo.creeper.MonsterHumanoidCreeperGirlRenderer;
import dev.dixmk.minepreggo.client.renderer.preggo.creeper.MonsterPregnantHumanoidCreeperGirlRenderer;
import dev.dixmk.minepreggo.client.renderer.preggo.creeper.TamableHumanoidCreeperGirlRenderer;
import dev.dixmk.minepreggo.client.renderer.preggo.creeper.TamableMonsterCreeperGirlRenderer;
import dev.dixmk.minepreggo.client.renderer.preggo.creeper.TamablePregnantHumanoidCreeperGirlRenderer;
import dev.dixmk.minepreggo.client.renderer.preggo.creeper.TamablePregnantMonsterCreeperGirlRenderer;
import dev.dixmk.minepreggo.client.renderer.preggo.ender.HostilPregnantMonsterEnderWomanRenderer;
import dev.dixmk.minepreggo.client.renderer.preggo.ender.IllEnderWomanRenderer;
import dev.dixmk.minepreggo.client.renderer.preggo.ender.MonsterlEnderWomanRenderer;
import dev.dixmk.minepreggo.client.renderer.preggo.ender.TamableMonsterEnderWomanRenderer;
import dev.dixmk.minepreggo.client.renderer.preggo.ender.TamablePregnantMonsterEnderWomanRenderer;
import dev.dixmk.minepreggo.client.renderer.preggo.zombie.IllZombieGirlRenderer;
import dev.dixmk.minepreggo.client.renderer.preggo.zombie.HostilPregnantZombieGirlRenderer;
import dev.dixmk.minepreggo.client.renderer.preggo.zombie.HostilZombieGirlRenderer;
import dev.dixmk.minepreggo.client.renderer.preggo.zombie.TamablePregnantZombieGirlRenderer;
import dev.dixmk.minepreggo.client.renderer.preggo.zombie.TamableZombieGirlRenderer;
import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.init.MinepreggoModItems;
import dev.dixmk.minepreggo.init.MinepreggoModKeyMappings;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.NoopRenderer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
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
		event.registerEntityRenderer(MinepreggoModEntities.HOSTILE_ZOMBIE_GIRL.get(), HostilZombieGirlRenderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.HOSTILE_ZOMBIE_GIRL_P3.get(), HostilPregnantZombieGirlRenderer.MonsterZombieGirlP3Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.HOSTILE_ZOMBIE_GIRL_P5.get(), HostilPregnantZombieGirlRenderer.MonsterZombieGirlP5Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.HOSTILE_ZOMBIE_GIRL_P7.get(), HostilPregnantZombieGirlRenderer.MonsterZombieGirlP7Renderer::new);	
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL.get(), TamableZombieGirlRenderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P0.get(), TamablePregnantZombieGirlRenderer.TamableZombieGirlP0Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P1.get(), TamablePregnantZombieGirlRenderer.TamableZombieGirlP1Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P2.get(), TamablePregnantZombieGirlRenderer.TamableZombieGirlP2Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P3.get(), TamablePregnantZombieGirlRenderer.TamableZombieGirlP3Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P4.get(), TamablePregnantZombieGirlRenderer.TamableZombieGirlP4Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P5.get(), TamablePregnantZombieGirlRenderer.TamableZombieGirlP5Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P6.get(), TamablePregnantZombieGirlRenderer.TamableZombieGirlP6Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P7.get(), TamablePregnantZombieGirlRenderer.TamableZombieGirlP7Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P8.get(), TamablePregnantZombieGirlRenderer.TamableZombieGirlP8Renderer::new);
		
		event.registerEntityRenderer(MinepreggoModEntities.HOSTILE_HUMANOID_CREEPER_GIRL.get(), MonsterHumanoidCreeperGirlRenderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.HOSTILE_HUMANOID_CREEPER_GIRL_P3.get(), MonsterPregnantHumanoidCreeperGirlRenderer.MonsterHumanoidCreeperGirlP3Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.HOSTILE_HUMANOID_CREEPER_GIRL_P5.get(), MonsterPregnantHumanoidCreeperGirlRenderer.MonsterHumanoidCreeperGirlP5Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.HOSTILE_HUMANOID_CREEPER_GIRL_P7.get(), MonsterPregnantHumanoidCreeperGirlRenderer.MonsterHumanoidCreeperGirlP7Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL.get(), TamableHumanoidCreeperGirlRenderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P0.get(), TamablePregnantHumanoidCreeperGirlRenderer.TamableHumanoidCreeperGirlP0Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P1.get(), TamablePregnantHumanoidCreeperGirlRenderer.TamableHumanoidCreeperGirlP1Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P2.get(), TamablePregnantHumanoidCreeperGirlRenderer.TamableHumanoidCreeperGirlP2Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P3.get(), TamablePregnantHumanoidCreeperGirlRenderer.TamableHumanoidCreeperGirlP3Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P4.get(), TamablePregnantHumanoidCreeperGirlRenderer.TamableHumanoidCreeperGirlP4Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P5.get(), TamablePregnantHumanoidCreeperGirlRenderer.TamableHumanoidCreeperGirlP5Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P6.get(), TamablePregnantHumanoidCreeperGirlRenderer.TamableHumanoidCreeperGirlP6Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P7.get(), TamablePregnantHumanoidCreeperGirlRenderer.TamableHumanoidCreeperGirlP7Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P8.get(), TamablePregnantHumanoidCreeperGirlRenderer.TamableHumanoidCreeperGirlP8Renderer::new);
				
		event.registerEntityRenderer(MinepreggoModEntities.ILL_HUMANOID_CREEPER_GIRL.get(), IllHumanoidCreeperGirlRenderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.ILL_ZOMBIE_GIRL.get(), IllZombieGirlRenderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.ILL_ENDER_WOMAN.get(), IllEnderWomanRenderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.ILL_CREEPER_GIRL.get(), IllMonsterCreeperGirlRenderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.SCIENTIFIC_ILLAGER.get(), ScientificIllagerRenderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.FERTILITY_WITCH.get(), FertilityWitchRenderer::new);

		event.registerEntityRenderer(MinepreggoModEntities.HOSTILE_MONSTER_ENDER_WOMAN.get(), MonsterlEnderWomanRenderer::new);	
		event.registerEntityRenderer(MinepreggoModEntities.HOSTILE_PREGNANT_MONSTER_ENDER_WOMAN_P3.get(), HostilPregnantMonsterEnderWomanRenderer.MonsteEnderWomanP3::new);	
		event.registerEntityRenderer(MinepreggoModEntities.HOSTILE_PREGNANT_MONSTER_ENDER_WOMAN_P5.get(), HostilPregnantMonsterEnderWomanRenderer.MonsteEnderWomanP5::new);	
		event.registerEntityRenderer(MinepreggoModEntities.HOSTILE_PREGNANT_MONSTER_ENDER_WOMAN_P7.get(), HostilPregnantMonsterEnderWomanRenderer.MonsteEnderWomanP7::new);	
		event.registerEntityRenderer(MinepreggoModEntities.HOSTILE_MONSTER_ENDER_WOMAN.get(), MonsterlEnderWomanRenderer::new);	
		event.registerEntityRenderer(MinepreggoModEntities.HOSTILE_MONSTER_ENDER_WOMAN.get(), MonsterlEnderWomanRenderer::new);	
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_MONSTER_ENDER_WOMAN.get(), TamableMonsterEnderWomanRenderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_MONSTER_ENDER_WOMAN_P0.get(), TamablePregnantMonsterEnderWomanRenderer.TamableMonsterEnderWomanP0Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_MONSTER_ENDER_WOMAN_P1.get(), TamablePregnantMonsterEnderWomanRenderer.TamableMonsterEnderWomanP1Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_MONSTER_ENDER_WOMAN_P2.get(), TamablePregnantMonsterEnderWomanRenderer.TamableMonsterEnderWomanP2Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_MONSTER_ENDER_WOMAN_P3.get(), TamablePregnantMonsterEnderWomanRenderer.TamableMonsterEnderWomanP3Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_MONSTER_ENDER_WOMAN_P4.get(), TamablePregnantMonsterEnderWomanRenderer.TamableMonsterEnderWomanP4Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_MONSTER_ENDER_WOMAN_P5.get(), TamablePregnantMonsterEnderWomanRenderer.TamableMonsterEnderWomanP5Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_MONSTER_ENDER_WOMAN_P6.get(), TamablePregnantMonsterEnderWomanRenderer.TamableMonsterEnderWomanP6Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_MONSTER_ENDER_WOMAN_P7.get(), TamablePregnantMonsterEnderWomanRenderer.TamableMonsterEnderWomanP7Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_MONSTER_ENDER_WOMAN_P8.get(), TamablePregnantMonsterEnderWomanRenderer.TamableMonsterEnderWomanP8Renderer::new);

		event.registerEntityRenderer(MinepreggoModEntities.HOSTILE_MONSTER_CREEPER_GIRL.get(), MonsterCreeperGirlRenderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.HOSTILE_PREGNANT_MONSTER_CREEPER_GIRL_P3.get(), HostilPregnantMonsterCreeperGirlRenderer.MonsterCreeperGirlP3::new);
		event.registerEntityRenderer(MinepreggoModEntities.HOSTILE_PREGNANT_MONSTER_CREEPER_GIRL_P5.get(), HostilPregnantMonsterCreeperGirlRenderer.MonsterCreeperGirlP5::new);
		event.registerEntityRenderer(MinepreggoModEntities.HOSTILE_PREGNANT_MONSTER_CREEPER_GIRL_P7.get(), HostilPregnantMonsterCreeperGirlRenderer.MonsterCreeperGirlP7::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_MONSTER_CREEPER_GIRL.get(), TamableMonsterCreeperGirlRenderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_MONSTER_CREEPER_GIRL_P0.get(), TamablePregnantMonsterCreeperGirlRenderer.TamablePregnantCreeperGirlP0Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_MONSTER_CREEPER_GIRL_P1.get(), TamablePregnantMonsterCreeperGirlRenderer.TamablePregnantCreeperGirlP1Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_MONSTER_CREEPER_GIRL_P2.get(), TamablePregnantMonsterCreeperGirlRenderer.TamablePregnantCreeperGirlP2Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_MONSTER_CREEPER_GIRL_P3.get(), TamablePregnantMonsterCreeperGirlRenderer.TamablePregnantCreeperGirlP3Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_MONSTER_CREEPER_GIRL_P4.get(), TamablePregnantMonsterCreeperGirlRenderer.TamablePregnantCreeperGirlP4Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_MONSTER_CREEPER_GIRL_P5.get(), TamablePregnantMonsterCreeperGirlRenderer.TamablePregnantCreeperGirlP5Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_MONSTER_CREEPER_GIRL_P6.get(), TamablePregnantMonsterCreeperGirlRenderer.TamablePregnantCreeperGirlP6Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_MONSTER_CREEPER_GIRL_P7.get(), TamablePregnantMonsterCreeperGirlRenderer.TamablePregnantCreeperGirlP7Renderer::new);
		event.registerEntityRenderer(MinepreggoModEntities.TAMABLE_MONSTER_CREEPER_GIRL_P8.get(), TamablePregnantMonsterCreeperGirlRenderer.TamablePregnantCreeperGirlP8Renderer::new);

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
				
		event.registerLayerDefinition(AbstractMonsterCreeperGirlModel.LAYER_LOCATION, AbstractMonsterCreeperGirlModel::createBasicBodyLayer);		
		event.registerLayerDefinition(AbstractMonsterCreeperGirlModel.LAYER_LOCATION_P0, AbstractMonsterCreeperGirlModel::createP0BodyLayer);		
		event.registerLayerDefinition(AbstractMonsterCreeperGirlModel.LAYER_LOCATION_P1, AbstractMonsterCreeperGirlModel::createP1BodyLayer);		
		event.registerLayerDefinition(AbstractMonsterCreeperGirlModel.LAYER_LOCATION_P2, AbstractMonsterCreeperGirlModel::createP2BodyLayer);		
		event.registerLayerDefinition(AbstractMonsterCreeperGirlModel.LAYER_LOCATION_P3, AbstractMonsterCreeperGirlModel::createP3BodyLayer);		
		event.registerLayerDefinition(AbstractMonsterCreeperGirlModel.LAYER_LOCATION_P4, AbstractMonsterCreeperGirlModel::createP4BodyLayer);		
		event.registerLayerDefinition(AbstractMonsterCreeperGirlModel.LAYER_LOCATION_P5, AbstractMonsterCreeperGirlModel::createP5BodyLayer);		
		event.registerLayerDefinition(AbstractMonsterCreeperGirlModel.LAYER_LOCATION_P6, AbstractMonsterCreeperGirlModel::createP6BodyLayer);		
		event.registerLayerDefinition(AbstractMonsterCreeperGirlModel.LAYER_LOCATION_P7, AbstractMonsterCreeperGirlModel::createP7BodyLayer);		
		event.registerLayerDefinition(AbstractMonsterCreeperGirlModel.LAYER_LOCATION_P8, AbstractMonsterCreeperGirlModel::createP8BodyLayer);		
		event.registerLayerDefinition(AbstractMonsterCreeperGirlModel.LAYER_OUTER_ARMOR_LOCATION, AbstractMonsterCreeperGirlModel::createOuterLayer);
		event.registerLayerDefinition(AbstractMonsterCreeperGirlModel.LAYER_ENERGY_ARMOR_LOCATION, AbstractMonsterCreeperGirlModel::createBasicBodyLayer);		
		event.registerLayerDefinition(AbstractMonsterCreeperGirlModel.LAYER_ENERGY_ARMOR_P0_LOCATION, AbstractMonsterCreeperGirlModel::createP0BodyLayer);		
		event.registerLayerDefinition(AbstractMonsterCreeperGirlModel.LAYER_ENERGY_ARMOR_P1_LOCATION, AbstractMonsterCreeperGirlModel::createP1BodyLayer);		
		event.registerLayerDefinition(AbstractMonsterCreeperGirlModel.LAYER_ENERGY_ARMOR_P2_LOCATION, AbstractMonsterCreeperGirlModel::createP2BodyLayer);		
		event.registerLayerDefinition(AbstractMonsterCreeperGirlModel.LAYER_ENERGY_ARMOR_P3_LOCATION, AbstractMonsterCreeperGirlModel::createP3BodyLayer);		
		event.registerLayerDefinition(AbstractMonsterCreeperGirlModel.LAYER_ENERGY_ARMOR_P4_LOCATION, AbstractMonsterCreeperGirlModel::createP4BodyLayer);		
		event.registerLayerDefinition(AbstractMonsterCreeperGirlModel.LAYER_ENERGY_ARMOR_P5_LOCATION, AbstractMonsterCreeperGirlModel::createP5BodyLayer);		
		event.registerLayerDefinition(AbstractMonsterCreeperGirlModel.LAYER_ENERGY_ARMOR_P6_LOCATION, AbstractMonsterCreeperGirlModel::createP6BodyLayer);		
		event.registerLayerDefinition(AbstractMonsterCreeperGirlModel.LAYER_ENERGY_ARMOR_P7_LOCATION, AbstractMonsterCreeperGirlModel::createP7BodyLayer);		
		event.registerLayerDefinition(AbstractMonsterCreeperGirlModel.LAYER_ENERGY_ARMOR_P8_LOCATION, AbstractMonsterCreeperGirlModel::createP8BodyLayer);		

		event.registerLayerDefinition(AbstractMonsterEnderWomanModel.LAYER_LOCATION, AbstractMonsterEnderWomanModel::createBasicBodyLayer);
		event.registerLayerDefinition(AbstractMonsterEnderWomanModel.LAYER_LOCATION_P0, AbstractMonsterEnderWomanModel::createP0BodyLayer);
		event.registerLayerDefinition(AbstractMonsterEnderWomanModel.LAYER_LOCATION_P1, AbstractMonsterEnderWomanModel::createP1BodyLayer);
		event.registerLayerDefinition(AbstractMonsterEnderWomanModel.LAYER_LOCATION_P2, AbstractMonsterEnderWomanModel::createP2BodyLayer);
		event.registerLayerDefinition(AbstractMonsterEnderWomanModel.LAYER_LOCATION_P3, AbstractMonsterEnderWomanModel::createP3BodyLayer);
		event.registerLayerDefinition(AbstractMonsterEnderWomanModel.LAYER_LOCATION_P4, AbstractMonsterEnderWomanModel::createP4BodyLayer);
		event.registerLayerDefinition(AbstractMonsterEnderWomanModel.LAYER_LOCATION_P5, AbstractMonsterEnderWomanModel::createP5BodyLayer);
		event.registerLayerDefinition(AbstractMonsterEnderWomanModel.LAYER_LOCATION_P6, AbstractMonsterEnderWomanModel::createP6BodyLayer);
		event.registerLayerDefinition(AbstractMonsterEnderWomanModel.LAYER_LOCATION_P7, AbstractMonsterEnderWomanModel::createP7BodyLayer);
		event.registerLayerDefinition(AbstractMonsterEnderWomanModel.LAYER_LOCATION_P8, AbstractMonsterEnderWomanModel::createP8BodyLayer);	
		event.registerLayerDefinition(AbstractMonsterEnderWomanModel.LAYER_OUTER_ARMOR_LOCATION, AbstractMonsterEnderWomanModel::createOuterLayer);
		event.registerLayerDefinition(AbstractMonsterEnderWomanModel.LAYER_INNER_ARMOR_LOCATION, AbstractMonsterEnderWomanModel::createInnerLayer);
		
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
	
    @SubscribeEvent
    public static void registerKeyBindings(RegisterKeyMappingsEvent event) {
        event.register(MinepreggoModKeyMappings.RUB_BELLY_KEY);
        event.register(MinepreggoModKeyMappings.TELEPORT_WITH_ENDER_WOMAN);
    }
}