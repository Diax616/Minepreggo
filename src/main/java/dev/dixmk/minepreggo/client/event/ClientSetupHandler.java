package dev.dixmk.minepreggo.client.event;

import java.util.function.Consumer;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.client.model.armor.BellyShieldModel;
import dev.dixmk.minepreggo.client.model.armor.FemaleChestplateModel;
import dev.dixmk.minepreggo.client.model.armor.KneeBracesModel;
import dev.dixmk.minepreggo.client.model.armor.FemaleMaternityChestplateModel;
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
import dev.dixmk.minepreggo.client.model.entity.player.CustomPregnantBodyP9Model;
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
import dev.dixmk.minepreggo.client.model.entity.player.PredefinedPregnantBodyP9Model;
import dev.dixmk.minepreggo.client.model.entity.preggo.PregnantFemaleHumanoidModel;
import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractHumanoidCreeperGirlModel;
import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.AbstractMonsterCreeperGirlModel;
import dev.dixmk.minepreggo.client.model.entity.preggo.ender.AbstractMonsterEnderWomanModel;
import dev.dixmk.minepreggo.client.model.entity.preggo.zombie.AbstractZombieGirlModel;
import dev.dixmk.minepreggo.client.model.geom.MinepreggoModelLayers;
import dev.dixmk.minepreggo.client.renderer.entity.FertilityWitchRenderer;
import dev.dixmk.minepreggo.client.renderer.entity.ScientificIllagerRenderer;
import dev.dixmk.minepreggo.client.renderer.entity.layers.BellyShieldLayer;
import dev.dixmk.minepreggo.client.renderer.entity.layers.FemaleChestplateLayer;
import dev.dixmk.minepreggo.client.renderer.entity.layers.player.CustomPregnantBodyLayer;
import dev.dixmk.minepreggo.client.renderer.entity.layers.player.PredefinedPregnantBodyLayer;
import dev.dixmk.minepreggo.client.renderer.preggo.creeper.IllMonsterCreeperGirlRenderer;
import dev.dixmk.minepreggo.client.renderer.preggo.creeper.HostilePregnantMonsterCreeperGirlRenderer;
import dev.dixmk.minepreggo.client.renderer.preggo.creeper.IllHumanoidCreeperGirlRenderer;
import dev.dixmk.minepreggo.client.renderer.preggo.creeper.MonsterCreeperGirlRenderer;
import dev.dixmk.minepreggo.client.renderer.preggo.creeper.MonsterHumanoidCreeperGirlRenderer;
import dev.dixmk.minepreggo.client.renderer.preggo.creeper.MonsterPregnantHumanoidCreeperGirlRenderer;
import dev.dixmk.minepreggo.client.renderer.preggo.creeper.TamableHumanoidCreeperGirlRenderer;
import dev.dixmk.minepreggo.client.renderer.preggo.creeper.TamableMonsterCreeperGirlRenderer;
import dev.dixmk.minepreggo.client.renderer.preggo.creeper.TamablePregnantHumanoidCreeperGirlRenderer;
import dev.dixmk.minepreggo.client.renderer.preggo.creeper.TamablePregnantMonsterCreeperGirlRenderer;
import dev.dixmk.minepreggo.client.renderer.preggo.ender.HostilePregnantMonsterEnderWomanRenderer;
import dev.dixmk.minepreggo.client.renderer.preggo.ender.IllEnderWomanRenderer;
import dev.dixmk.minepreggo.client.renderer.preggo.ender.MonsterlEnderWomanRenderer;
import dev.dixmk.minepreggo.client.renderer.preggo.ender.TamableMonsterEnderWomanRenderer;
import dev.dixmk.minepreggo.client.renderer.preggo.ender.TamablePregnantMonsterEnderWomanRenderer;
import dev.dixmk.minepreggo.client.renderer.preggo.zombie.IllZombieGirlRenderer;
import dev.dixmk.minepreggo.client.renderer.preggo.zombie.HostilePregnantZombieGirlRenderer;
import dev.dixmk.minepreggo.client.renderer.preggo.zombie.HostileZombieGirlRenderer;
import dev.dixmk.minepreggo.client.renderer.preggo.zombie.TamablePregnantZombieGirlRenderer;
import dev.dixmk.minepreggo.client.renderer.preggo.zombie.TamableZombieGirlRenderer;
import dev.dixmk.minepreggo.init.MinepreggoEntities;
import dev.dixmk.minepreggo.init.MinepreggoItems;
import dev.dixmk.minepreggo.init.MinepreggoKeyMappings;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.NoopRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = MinepreggoMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetupHandler {

	private ClientSetupHandler() {}
	
    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.AddLayers event) {	
    	Consumer<String> registerPlayerLayers = skinName -> {
            if (event.getPlayerSkin(skinName) instanceof PlayerRenderer playerRenderer) {
                playerRenderer.addLayer(new CustomPregnantBodyLayer(playerRenderer, event.getEntityModels()));
                playerRenderer.addLayer(new PredefinedPregnantBodyLayer(playerRenderer, event.getEntityModels()));
                playerRenderer.addLayer(new FemaleChestplateLayer<>(playerRenderer, event.getContext().getModelManager(), event.getEntityModels()));
                playerRenderer.addLayer(new BellyShieldLayer<>(playerRenderer, event.getContext().getModelManager(), event.getEntityModels()));
            }
		};
		registerPlayerLayers.accept("default");
		registerPlayerLayers.accept("slim");

	    for (EntityType<?> type : ForgeRegistries.ENTITY_TYPES.getValues()) {;
	        if (event.getEntityRenderer(type) instanceof LivingEntityRenderer<?,?> living && living.getModel() instanceof HumanoidModel<?>) {	 
	        	@SuppressWarnings("unchecked")
	            LivingEntityRenderer<LivingEntity, HumanoidModel<LivingEntity>> castedLiving = (LivingEntityRenderer<LivingEntity, HumanoidModel<LivingEntity>>) living;
	        	castedLiving.addLayer(new FemaleChestplateLayer<>(castedLiving, event.getContext().getModelManager(), event.getEntityModels()));
	        	castedLiving.addLayer(new BellyShieldLayer<>(castedLiving, event.getContext().getModelManager(), event.getEntityModels()));
	        }
	    }	
    }

    @SubscribeEvent
    public static void registerItemColors(RegisterColorHandlersEvent.Item event) {
        event.register((stack, tintIndex) -> tintIndex > 0 ? -1 : ((DyeableLeatherItem) stack.getItem()).getColor(stack),     		
        MinepreggoItems.FEMALE_LEATHER_CHESTPLATE.get(),
        MinepreggoItems.MATERNITY_LEATHER_P1_CHESTPLATE.get(),
        MinepreggoItems.MATERNITY_LEATHER_P2_CHESTPLATE.get(),
        MinepreggoItems.MATERNITY_LEATHER_P3_CHESTPLATE.get(),
        MinepreggoItems.MATERNITY_LEATHER_P4_CHESTPLATE.get(),
        MinepreggoItems.LEATHER_KNEE_BRACES.get());    
    }
   	
	@SubscribeEvent
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(MinepreggoEntities.HOSTILE_ZOMBIE_GIRL.get(), HostileZombieGirlRenderer::new);
		event.registerEntityRenderer(MinepreggoEntities.HOSTILE_ZOMBIE_GIRL_P3.get(), HostilePregnantZombieGirlRenderer.MonsterZombieGirlP3Renderer::new);
		event.registerEntityRenderer(MinepreggoEntities.HOSTILE_ZOMBIE_GIRL_P5.get(), HostilePregnantZombieGirlRenderer.MonsterZombieGirlP5Renderer::new);
		event.registerEntityRenderer(MinepreggoEntities.HOSTILE_ZOMBIE_GIRL_P7.get(), HostilePregnantZombieGirlRenderer.MonsterZombieGirlP7Renderer::new);	
		event.registerEntityRenderer(MinepreggoEntities.TAMABLE_ZOMBIE_GIRL.get(), TamableZombieGirlRenderer::new);
		event.registerEntityRenderer(MinepreggoEntities.TAMABLE_ZOMBIE_GIRL_P0.get(), TamablePregnantZombieGirlRenderer.TamableZombieGirlP0Renderer::new);
		event.registerEntityRenderer(MinepreggoEntities.TAMABLE_ZOMBIE_GIRL_P1.get(), TamablePregnantZombieGirlRenderer.TamableZombieGirlP1Renderer::new);
		event.registerEntityRenderer(MinepreggoEntities.TAMABLE_ZOMBIE_GIRL_P2.get(), TamablePregnantZombieGirlRenderer.TamableZombieGirlP2Renderer::new);
		event.registerEntityRenderer(MinepreggoEntities.TAMABLE_ZOMBIE_GIRL_P3.get(), TamablePregnantZombieGirlRenderer.TamableZombieGirlP3Renderer::new);
		event.registerEntityRenderer(MinepreggoEntities.TAMABLE_ZOMBIE_GIRL_P4.get(), TamablePregnantZombieGirlRenderer.TamableZombieGirlP4Renderer::new);
		event.registerEntityRenderer(MinepreggoEntities.TAMABLE_ZOMBIE_GIRL_P5.get(), TamablePregnantZombieGirlRenderer.TamableZombieGirlP5Renderer::new);
		event.registerEntityRenderer(MinepreggoEntities.TAMABLE_ZOMBIE_GIRL_P6.get(), TamablePregnantZombieGirlRenderer.TamableZombieGirlP6Renderer::new);
		event.registerEntityRenderer(MinepreggoEntities.TAMABLE_ZOMBIE_GIRL_P7.get(), TamablePregnantZombieGirlRenderer.TamableZombieGirlP7Renderer::new);
		event.registerEntityRenderer(MinepreggoEntities.TAMABLE_ZOMBIE_GIRL_P8.get(), TamablePregnantZombieGirlRenderer.TamableZombieGirlP8Renderer::new);	
		event.registerEntityRenderer(MinepreggoEntities.HOSTILE_HUMANOID_CREEPER_GIRL.get(), MonsterHumanoidCreeperGirlRenderer::new);
		event.registerEntityRenderer(MinepreggoEntities.HOSTILE_HUMANOID_CREEPER_GIRL_P3.get(), MonsterPregnantHumanoidCreeperGirlRenderer.MonsterHumanoidCreeperGirlP3Renderer::new);
		event.registerEntityRenderer(MinepreggoEntities.HOSTILE_HUMANOID_CREEPER_GIRL_P5.get(), MonsterPregnantHumanoidCreeperGirlRenderer.MonsterHumanoidCreeperGirlP5Renderer::new);
		event.registerEntityRenderer(MinepreggoEntities.HOSTILE_HUMANOID_CREEPER_GIRL_P7.get(), MonsterPregnantHumanoidCreeperGirlRenderer.MonsterHumanoidCreeperGirlP7Renderer::new);
		event.registerEntityRenderer(MinepreggoEntities.TAMABLE_HUMANOID_CREEPER_GIRL.get(), TamableHumanoidCreeperGirlRenderer::new);
		event.registerEntityRenderer(MinepreggoEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P0.get(), TamablePregnantHumanoidCreeperGirlRenderer.TamableHumanoidCreeperGirlP0Renderer::new);
		event.registerEntityRenderer(MinepreggoEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P1.get(), TamablePregnantHumanoidCreeperGirlRenderer.TamableHumanoidCreeperGirlP1Renderer::new);
		event.registerEntityRenderer(MinepreggoEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P2.get(), TamablePregnantHumanoidCreeperGirlRenderer.TamableHumanoidCreeperGirlP2Renderer::new);
		event.registerEntityRenderer(MinepreggoEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P3.get(), TamablePregnantHumanoidCreeperGirlRenderer.TamableHumanoidCreeperGirlP3Renderer::new);
		event.registerEntityRenderer(MinepreggoEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P4.get(), TamablePregnantHumanoidCreeperGirlRenderer.TamableHumanoidCreeperGirlP4Renderer::new);
		event.registerEntityRenderer(MinepreggoEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P5.get(), TamablePregnantHumanoidCreeperGirlRenderer.TamableHumanoidCreeperGirlP5Renderer::new);
		event.registerEntityRenderer(MinepreggoEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P6.get(), TamablePregnantHumanoidCreeperGirlRenderer.TamableHumanoidCreeperGirlP6Renderer::new);
		event.registerEntityRenderer(MinepreggoEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P7.get(), TamablePregnantHumanoidCreeperGirlRenderer.TamableHumanoidCreeperGirlP7Renderer::new);
		event.registerEntityRenderer(MinepreggoEntities.TAMABLE_HUMANOID_CREEPER_GIRL_P8.get(), TamablePregnantHumanoidCreeperGirlRenderer.TamableHumanoidCreeperGirlP8Renderer::new);
		event.registerEntityRenderer(MinepreggoEntities.ILL_HUMANOID_CREEPER_GIRL.get(), IllHumanoidCreeperGirlRenderer::new);
		event.registerEntityRenderer(MinepreggoEntities.ILL_ZOMBIE_GIRL.get(), IllZombieGirlRenderer::new);
		event.registerEntityRenderer(MinepreggoEntities.ILL_ENDER_WOMAN.get(), IllEnderWomanRenderer::new);
		event.registerEntityRenderer(MinepreggoEntities.ILL_CREEPER_GIRL.get(), IllMonsterCreeperGirlRenderer::new);
		event.registerEntityRenderer(MinepreggoEntities.SCIENTIFIC_ILLAGER.get(), ScientificIllagerRenderer::new);
		event.registerEntityRenderer(MinepreggoEntities.FERTILITY_WITCH.get(), FertilityWitchRenderer::new);
		event.registerEntityRenderer(MinepreggoEntities.HOSTILE_MONSTER_ENDER_WOMAN.get(), MonsterlEnderWomanRenderer::new);	
		event.registerEntityRenderer(MinepreggoEntities.HOSTILE_PREGNANT_MONSTER_ENDER_WOMAN_P3.get(), HostilePregnantMonsterEnderWomanRenderer.MonsteEnderWomanP3::new);	
		event.registerEntityRenderer(MinepreggoEntities.HOSTILE_PREGNANT_MONSTER_ENDER_WOMAN_P5.get(), HostilePregnantMonsterEnderWomanRenderer.MonsteEnderWomanP5::new);	
		event.registerEntityRenderer(MinepreggoEntities.HOSTILE_PREGNANT_MONSTER_ENDER_WOMAN_P7.get(), HostilePregnantMonsterEnderWomanRenderer.MonsteEnderWomanP7::new);	
		event.registerEntityRenderer(MinepreggoEntities.HOSTILE_MONSTER_ENDER_WOMAN.get(), MonsterlEnderWomanRenderer::new);	
		event.registerEntityRenderer(MinepreggoEntities.HOSTILE_MONSTER_ENDER_WOMAN.get(), MonsterlEnderWomanRenderer::new);	
		event.registerEntityRenderer(MinepreggoEntities.TAMABLE_MONSTER_ENDER_WOMAN.get(), TamableMonsterEnderWomanRenderer::new);
		event.registerEntityRenderer(MinepreggoEntities.TAMABLE_MONSTER_ENDER_WOMAN_P0.get(), TamablePregnantMonsterEnderWomanRenderer.TamableMonsterEnderWomanP0Renderer::new);
		event.registerEntityRenderer(MinepreggoEntities.TAMABLE_MONSTER_ENDER_WOMAN_P1.get(), TamablePregnantMonsterEnderWomanRenderer.TamableMonsterEnderWomanP1Renderer::new);
		event.registerEntityRenderer(MinepreggoEntities.TAMABLE_MONSTER_ENDER_WOMAN_P2.get(), TamablePregnantMonsterEnderWomanRenderer.TamableMonsterEnderWomanP2Renderer::new);
		event.registerEntityRenderer(MinepreggoEntities.TAMABLE_MONSTER_ENDER_WOMAN_P3.get(), TamablePregnantMonsterEnderWomanRenderer.TamableMonsterEnderWomanP3Renderer::new);
		event.registerEntityRenderer(MinepreggoEntities.TAMABLE_MONSTER_ENDER_WOMAN_P4.get(), TamablePregnantMonsterEnderWomanRenderer.TamableMonsterEnderWomanP4Renderer::new);
		event.registerEntityRenderer(MinepreggoEntities.TAMABLE_MONSTER_ENDER_WOMAN_P5.get(), TamablePregnantMonsterEnderWomanRenderer.TamableMonsterEnderWomanP5Renderer::new);
		event.registerEntityRenderer(MinepreggoEntities.TAMABLE_MONSTER_ENDER_WOMAN_P6.get(), TamablePregnantMonsterEnderWomanRenderer.TamableMonsterEnderWomanP6Renderer::new);
		event.registerEntityRenderer(MinepreggoEntities.TAMABLE_MONSTER_ENDER_WOMAN_P7.get(), TamablePregnantMonsterEnderWomanRenderer.TamableMonsterEnderWomanP7Renderer::new);
		event.registerEntityRenderer(MinepreggoEntities.TAMABLE_MONSTER_ENDER_WOMAN_P8.get(), TamablePregnantMonsterEnderWomanRenderer.TamableMonsterEnderWomanP8Renderer::new);
		event.registerEntityRenderer(MinepreggoEntities.HOSTILE_MONSTER_CREEPER_GIRL.get(), MonsterCreeperGirlRenderer::new);
		event.registerEntityRenderer(MinepreggoEntities.HOSTILE_PREGNANT_MONSTER_CREEPER_GIRL_P3.get(), HostilePregnantMonsterCreeperGirlRenderer.MonsterCreeperGirlP3::new);
		event.registerEntityRenderer(MinepreggoEntities.HOSTILE_PREGNANT_MONSTER_CREEPER_GIRL_P5.get(), HostilePregnantMonsterCreeperGirlRenderer.MonsterCreeperGirlP5::new);
		event.registerEntityRenderer(MinepreggoEntities.HOSTILE_PREGNANT_MONSTER_CREEPER_GIRL_P7.get(), HostilePregnantMonsterCreeperGirlRenderer.MonsterCreeperGirlP7::new);
		event.registerEntityRenderer(MinepreggoEntities.TAMABLE_MONSTER_CREEPER_GIRL.get(), TamableMonsterCreeperGirlRenderer::new);
		event.registerEntityRenderer(MinepreggoEntities.TAMABLE_MONSTER_CREEPER_GIRL_P0.get(), TamablePregnantMonsterCreeperGirlRenderer.TamablePregnantCreeperGirlP0Renderer::new);
		event.registerEntityRenderer(MinepreggoEntities.TAMABLE_MONSTER_CREEPER_GIRL_P1.get(), TamablePregnantMonsterCreeperGirlRenderer.TamablePregnantCreeperGirlP1Renderer::new);
		event.registerEntityRenderer(MinepreggoEntities.TAMABLE_MONSTER_CREEPER_GIRL_P2.get(), TamablePregnantMonsterCreeperGirlRenderer.TamablePregnantCreeperGirlP2Renderer::new);
		event.registerEntityRenderer(MinepreggoEntities.TAMABLE_MONSTER_CREEPER_GIRL_P3.get(), TamablePregnantMonsterCreeperGirlRenderer.TamablePregnantCreeperGirlP3Renderer::new);
		event.registerEntityRenderer(MinepreggoEntities.TAMABLE_MONSTER_CREEPER_GIRL_P4.get(), TamablePregnantMonsterCreeperGirlRenderer.TamablePregnantCreeperGirlP4Renderer::new);
		event.registerEntityRenderer(MinepreggoEntities.TAMABLE_MONSTER_CREEPER_GIRL_P5.get(), TamablePregnantMonsterCreeperGirlRenderer.TamablePregnantCreeperGirlP5Renderer::new);
		event.registerEntityRenderer(MinepreggoEntities.TAMABLE_MONSTER_CREEPER_GIRL_P6.get(), TamablePregnantMonsterCreeperGirlRenderer.TamablePregnantCreeperGirlP6Renderer::new);
		event.registerEntityRenderer(MinepreggoEntities.TAMABLE_MONSTER_CREEPER_GIRL_P7.get(), TamablePregnantMonsterCreeperGirlRenderer.TamablePregnantCreeperGirlP7Renderer::new);
		event.registerEntityRenderer(MinepreggoEntities.TAMABLE_MONSTER_CREEPER_GIRL_P8.get(), TamablePregnantMonsterCreeperGirlRenderer.TamablePregnantCreeperGirlP8Renderer::new);
		event.registerEntityRenderer(MinepreggoEntities.BELLY_PART.get(), NoopRenderer::new);	
		event.registerEntityRenderer(MinepreggoEntities.EXPLOSIVE_DRAGON_FIREBALL.get(), ThrownItemRenderer::new);
	}
	
	@SubscribeEvent
	public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(MinepreggoModelLayers.ZOMBIE_GIRL, AbstractZombieGirlModel::createBodyLayer);
		event.registerLayerDefinition(MinepreggoModelLayers.PREGNANT_ZOMBIE_GIRL_P0, AbstractZombieGirlModel::createP0BodyLayer);
		event.registerLayerDefinition(MinepreggoModelLayers.PREGNANT_ZOMBIE_GIRL_P1, AbstractZombieGirlModel::createP1BodyLayer);
		event.registerLayerDefinition(MinepreggoModelLayers.PREGNANT_ZOMBIE_GIRL_P2, AbstractZombieGirlModel::createP2BodyLayer);
		event.registerLayerDefinition(MinepreggoModelLayers.PREGNANT_ZOMBIE_GIRL_P3, AbstractZombieGirlModel::createP3BodyLayer);
		event.registerLayerDefinition(MinepreggoModelLayers.PREGNANT_ZOMBIE_GIRL_P4, AbstractZombieGirlModel::createP4BodyLayer);
		event.registerLayerDefinition(MinepreggoModelLayers.PREGNANT_ZOMBIE_GIRL_P5, AbstractZombieGirlModel::createP5BodyLayer);
		event.registerLayerDefinition(MinepreggoModelLayers.PREGNANT_ZOMBIE_GIRL_P6, AbstractZombieGirlModel::createP6BodyLayer);
		event.registerLayerDefinition(MinepreggoModelLayers.PREGNANT_ZOMBIE_GIRL_P7, AbstractZombieGirlModel::createP7BodyLayer);
		event.registerLayerDefinition(MinepreggoModelLayers.PREGNANT_ZOMBIE_GIRL_P8, AbstractZombieGirlModel::createP8BodyLayer);
		event.registerLayerDefinition(MinepreggoModelLayers.ZOMBIE_GIRL_INNER_ARMOR, PregnantFemaleHumanoidModel::createInnerLayer);
		event.registerLayerDefinition(MinepreggoModelLayers.ZOMBIE_GIRL_OUTER_ARMOR, PregnantFemaleHumanoidModel::createOuterLayer);
		event.registerLayerDefinition(MinepreggoModelLayers.HUMANOID_CREEPER_GIRL, AbstractHumanoidCreeperGirlModel::createBodyLayer);		
		event.registerLayerDefinition(MinepreggoModelLayers.PREGNANT_HUMANOID_CREEPER_GIRL_P0, AbstractHumanoidCreeperGirlModel::createP0BodyLayer);		
		event.registerLayerDefinition(MinepreggoModelLayers.PREGNANT_HUMANOID_CREEPER_GIRL_P1, AbstractHumanoidCreeperGirlModel::createP1BodyLayer);		
		event.registerLayerDefinition(MinepreggoModelLayers.PREGNANT_HUMANOID_CREEPER_GIRL_P2, AbstractHumanoidCreeperGirlModel::createP2BodyLayer);		
		event.registerLayerDefinition(MinepreggoModelLayers.PREGNANT_HUMANOID_CREEPER_GIRL_P3, AbstractHumanoidCreeperGirlModel::createP3BodyLayer);		
		event.registerLayerDefinition(MinepreggoModelLayers.PREGNANT_HUMANOID_CREEPER_GIRL_P4, AbstractHumanoidCreeperGirlModel::createP4BodyLayer);		
		event.registerLayerDefinition(MinepreggoModelLayers.PREGNANT_HUMANOID_CREEPER_GIRL_P5, AbstractHumanoidCreeperGirlModel::createP5BodyLayer);		
		event.registerLayerDefinition(MinepreggoModelLayers.PREGNANT_HUMANOID_CREEPER_GIRL_P6, AbstractHumanoidCreeperGirlModel::createP6BodyLayer);		
		event.registerLayerDefinition(MinepreggoModelLayers.PREGNANT_HUMANOID_CREEPER_GIRL_P7, AbstractHumanoidCreeperGirlModel::createP7BodyLayer);		
		event.registerLayerDefinition(MinepreggoModelLayers.PREGNANT_HUMANOID_CREEPER_GIRL_P8, AbstractHumanoidCreeperGirlModel::createP8BodyLayer);		
		event.registerLayerDefinition(MinepreggoModelLayers.HUMANOID_CREEPER_GIRL_INNER_ARMOR, PregnantFemaleHumanoidModel::createInnerLayer);
		event.registerLayerDefinition(MinepreggoModelLayers.HUMANOID_CREEPER_GIRL_OUTER_ARMOR, PregnantFemaleHumanoidModel::createOuterLayer);
		event.registerLayerDefinition(MinepreggoModelLayers.HUMANOID_CREEPER_GIRL_ENERGY_ARMOR, AbstractHumanoidCreeperGirlModel::createBodyLayer);		
		event.registerLayerDefinition(MinepreggoModelLayers.PREGNANT_HUMANOID_CREEPER_GIRL_ENERGY_ARMOR_P0, AbstractHumanoidCreeperGirlModel::createP0BodyLayer);		
		event.registerLayerDefinition(MinepreggoModelLayers.PREGNANT_HUMANOID_CREEPER_GIRL_ENERGY_ARMOR_P1, AbstractHumanoidCreeperGirlModel::createP1BodyLayer);		
		event.registerLayerDefinition(MinepreggoModelLayers.PREGNANT_HUMANOID_CREEPER_GIRL_ENERGY_ARMOR_P2, AbstractHumanoidCreeperGirlModel::createP2BodyLayer);		
		event.registerLayerDefinition(MinepreggoModelLayers.PREGNANT_HUMANOID_CREEPER_GIRL_ENERGY_ARMOR_P3, AbstractHumanoidCreeperGirlModel::createP3BodyLayer);		
		event.registerLayerDefinition(MinepreggoModelLayers.PREGNANT_HUMANOID_CREEPER_GIRL_ENERGY_ARMOR_P4, AbstractHumanoidCreeperGirlModel::createP4BodyLayer);		
		event.registerLayerDefinition(MinepreggoModelLayers.PREGNANT_HUMANOID_CREEPER_GIRL_ENERGY_ARMOR_P5, AbstractHumanoidCreeperGirlModel::createP5BodyLayer);		
		event.registerLayerDefinition(MinepreggoModelLayers.PREGNANT_HUMANOID_CREEPER_GIRL_ENERGY_ARMOR_P6, AbstractHumanoidCreeperGirlModel::createP6BodyLayer);		
		event.registerLayerDefinition(MinepreggoModelLayers.PREGNANT_HUMANOID_CREEPER_GIRL_ENERGY_ARMOR_P7, AbstractHumanoidCreeperGirlModel::createP7BodyLayer);		
		event.registerLayerDefinition(MinepreggoModelLayers.PREGNANT_HUMANOID_CREEPER_GIRL_ENERGY_ARMOR_P8, AbstractHumanoidCreeperGirlModel::createP8BodyLayer);
		event.registerLayerDefinition(MinepreggoModelLayers.MONSTER_CREEPER_GIRL, AbstractMonsterCreeperGirlModel::createBasicBodyLayer);		
		event.registerLayerDefinition(MinepreggoModelLayers.PREGNANT_MONSTER_CREEPER_GIRL_P0, AbstractMonsterCreeperGirlModel::createP0BodyLayer);		
		event.registerLayerDefinition(MinepreggoModelLayers.PREGNANT_MONSTER_CREEPER_GIRL_P1, AbstractMonsterCreeperGirlModel::createP1BodyLayer);		
		event.registerLayerDefinition(MinepreggoModelLayers.PREGNANT_MONSTER_CREEPER_GIRL_P2, AbstractMonsterCreeperGirlModel::createP2BodyLayer);		
		event.registerLayerDefinition(MinepreggoModelLayers.PREGNANT_MONSTER_CREEPER_GIRL_P3, AbstractMonsterCreeperGirlModel::createP3BodyLayer);		
		event.registerLayerDefinition(MinepreggoModelLayers.PREGNANT_MONSTER_CREEPER_GIRL_P4, AbstractMonsterCreeperGirlModel::createP4BodyLayer);		
		event.registerLayerDefinition(MinepreggoModelLayers.PREGNANT_MONSTER_CREEPER_GIRL_P5, AbstractMonsterCreeperGirlModel::createP5BodyLayer);		
		event.registerLayerDefinition(MinepreggoModelLayers.PREGNANT_MONSTER_CREEPER_GIRL_P6, AbstractMonsterCreeperGirlModel::createP6BodyLayer);		
		event.registerLayerDefinition(MinepreggoModelLayers.PREGNANT_MONSTER_CREEPER_GIRL_P7, AbstractMonsterCreeperGirlModel::createP7BodyLayer);		
		event.registerLayerDefinition(MinepreggoModelLayers.PREGNANT_MONSTER_CREEPER_GIRL_P8, AbstractMonsterCreeperGirlModel::createP8BodyLayer);		
		event.registerLayerDefinition(MinepreggoModelLayers.MONSTER_CREEPER_GIRL_OUTER_ARMOR, AbstractMonsterCreeperGirlModel::createOuterLayer);
		event.registerLayerDefinition(MinepreggoModelLayers.MONSTER_CREEPER_GIRL_ENERGY_ARMOR, AbstractMonsterCreeperGirlModel::createBasicBodyLayer);		
		event.registerLayerDefinition(MinepreggoModelLayers.PREGNANT_MONSTER_CREEPER_GIRL_ENERGY_ARMOR_P0, AbstractMonsterCreeperGirlModel::createP0BodyLayer);		
		event.registerLayerDefinition(MinepreggoModelLayers.PREGNANT_MONSTER_CREEPER_GIRL_ENERGY_ARMOR_P1, AbstractMonsterCreeperGirlModel::createP1BodyLayer);		
		event.registerLayerDefinition(MinepreggoModelLayers.PREGNANT_MONSTER_CREEPER_GIRL_ENERGY_ARMOR_P2, AbstractMonsterCreeperGirlModel::createP2BodyLayer);		
		event.registerLayerDefinition(MinepreggoModelLayers.PREGNANT_MONSTER_CREEPER_GIRL_ENERGY_ARMOR_P3, AbstractMonsterCreeperGirlModel::createP3BodyLayer);		
		event.registerLayerDefinition(MinepreggoModelLayers.PREGNANT_MONSTER_CREEPER_GIRL_ENERGY_ARMOR_P4, AbstractMonsterCreeperGirlModel::createP4BodyLayer);		
		event.registerLayerDefinition(MinepreggoModelLayers.PREGNANT_MONSTER_CREEPER_GIRL_ENERGY_ARMOR_P5, AbstractMonsterCreeperGirlModel::createP5BodyLayer);		
		event.registerLayerDefinition(MinepreggoModelLayers.PREGNANT_MONSTER_CREEPER_GIRL_ENERGY_ARMOR_P6, AbstractMonsterCreeperGirlModel::createP6BodyLayer);		
		event.registerLayerDefinition(MinepreggoModelLayers.PREGNANT_MONSTER_CREEPER_GIRL_ENERGY_ARMOR_P7, AbstractMonsterCreeperGirlModel::createP7BodyLayer);		
		event.registerLayerDefinition(MinepreggoModelLayers.PREGNANT_MONSTER_CREEPER_GIRL_ENERGY_ARMOR_P8, AbstractMonsterCreeperGirlModel::createP8BodyLayer);		
		event.registerLayerDefinition(MinepreggoModelLayers.MONSTER_ENDER_WOMAN, AbstractMonsterEnderWomanModel::createBasicBodyLayer);
		event.registerLayerDefinition(MinepreggoModelLayers.PREGNANT_MONSTER_ENDER_WOMAN_P0, AbstractMonsterEnderWomanModel::createP0BodyLayer);
		event.registerLayerDefinition(MinepreggoModelLayers.PREGNANT_MONSTER_ENDER_WOMAN_P1, AbstractMonsterEnderWomanModel::createP1BodyLayer);
		event.registerLayerDefinition(MinepreggoModelLayers.PREGNANT_MONSTER_ENDER_WOMAN_P2, AbstractMonsterEnderWomanModel::createP2BodyLayer);
		event.registerLayerDefinition(MinepreggoModelLayers.PREGNANT_MONSTER_ENDER_WOMAN_P3, AbstractMonsterEnderWomanModel::createP3BodyLayer);
		event.registerLayerDefinition(MinepreggoModelLayers.PREGNANT_MONSTER_ENDER_WOMAN_P4, AbstractMonsterEnderWomanModel::createP4BodyLayer);
		event.registerLayerDefinition(MinepreggoModelLayers.PREGNANT_MONSTER_ENDER_WOMAN_P5, AbstractMonsterEnderWomanModel::createP5BodyLayer);
		event.registerLayerDefinition(MinepreggoModelLayers.PREGNANT_MONSTER_ENDER_WOMAN_P6, AbstractMonsterEnderWomanModel::createP6BodyLayer);
		event.registerLayerDefinition(MinepreggoModelLayers.PREGNANT_MONSTER_ENDER_WOMAN_P7, AbstractMonsterEnderWomanModel::createP7BodyLayer);
		event.registerLayerDefinition(MinepreggoModelLayers.PREGNANT_MONSTER_ENDER_WOMAN_P8, AbstractMonsterEnderWomanModel::createP8BodyLayer);	
		event.registerLayerDefinition(MinepreggoModelLayers.MONSTER_ENDER_WOMAN_INNER_ARMOR, AbstractMonsterEnderWomanModel::createInnerLayer);
		event.registerLayerDefinition(MinepreggoModelLayers.MONSTER_ENDER_WOMAN_OUTER_ARMOR, AbstractMonsterEnderWomanModel::createOuterLayer);
		event.registerLayerDefinition(MinepreggoModelLayers.BELLY_SHIELD_P5, BellyShieldModel::createBellyShieldP5Layer);
		event.registerLayerDefinition(MinepreggoModelLayers.BELLY_SHIELD_P6, BellyShieldModel::createBellyShieldP6Layer);
		event.registerLayerDefinition(MinepreggoModelLayers.BELLY_SHIELD_P7, BellyShieldModel::createBellyShieldP7Layer);
		event.registerLayerDefinition(MinepreggoModelLayers.BELLY_SHIELD_P8, BellyShieldModel::createBellyShieldP8Layer);
		event.registerLayerDefinition(MinepreggoModelLayers.FEMALE_CHESTPLATE, FemaleChestplateModel::createFemaleChesplateLayer);
		event.registerLayerDefinition(MinepreggoModelLayers.FEMALE_MATERNITY_CHESTPLATE_P1, FemaleMaternityChestplateModel::createMaternityChesplateP1Layer);
		event.registerLayerDefinition(MinepreggoModelLayers.FEMALE_MATERNITY_CHESTPLATE_P2, FemaleMaternityChestplateModel::createMaternityChesplateP2Layer);
		event.registerLayerDefinition(MinepreggoModelLayers.FEMALE_MATERNITY_CHESTPLATE_P3, FemaleMaternityChestplateModel::createMaternityChesplateP3Layer);
		event.registerLayerDefinition(MinepreggoModelLayers.FEMALE_MATERNITY_CHESTPLATE_P4, FemaleMaternityChestplateModel::createMaternityChesplateP4Layer);
		event.registerLayerDefinition(MinepreggoModelLayers.KNEE_BRACES, KneeBracesModel::createBodyLayer);
		event.registerLayerDefinition(MinepreggoModelLayers.CUSTOM_BOOBS, CustomBoobsModel::createBodyLayer);
		event.registerLayerDefinition(MinepreggoModelLayers.PREDEFINED_BOOBS, PredefinedBoobsModel::createBodyLayer);
		event.registerLayerDefinition(MinepreggoModelLayers.CUSTOM_PREGNANT_BODY_P0, CustomPregnantBodyP0Model::createBodyLayer);
		event.registerLayerDefinition(MinepreggoModelLayers.CUSTOM_PREGNANT_BODY_P1, CustomPregnantBodyP1Model::createBodyLayer);
		event.registerLayerDefinition(MinepreggoModelLayers.CUSTOM_PREGNANT_BODY_P2, CustomPregnantBodyP2Model::createBodyLayer);
		event.registerLayerDefinition(MinepreggoModelLayers.CUSTOM_PREGNANT_BODY_P3, CustomPregnantBodyP3Model::createBodyLayer);
		event.registerLayerDefinition(MinepreggoModelLayers.CUSTOM_PREGNANT_BODY_P4, CustomPregnantBodyP4Model::createBodyLayer);
		event.registerLayerDefinition(MinepreggoModelLayers.CUSTOM_PREGNANT_BODY_P5, CustomPregnantBodyP5Model::createBodyLayer);
		event.registerLayerDefinition(MinepreggoModelLayers.CUSTOM_PREGNANT_BODY_P6, CustomPregnantBodyP6Model::createBodyLayer);
		event.registerLayerDefinition(MinepreggoModelLayers.CUSTOM_PREGNANT_BODY_P7, CustomPregnantBodyP7Model::createBodyLayer);
		event.registerLayerDefinition(MinepreggoModelLayers.CUSTOM_PREGNANT_BODY_P8, CustomPregnantBodyP8Model::createBodyLayer);
		event.registerLayerDefinition(MinepreggoModelLayers.CUSTOM_PREGNANT_BODY_P9, CustomPregnantBodyP9Model::createBodyLayer);
		event.registerLayerDefinition(MinepreggoModelLayers.PREDEFINED_PREGNANT_BODY_P0, PredefinedPregnantBodyP0Model::createBodyLayer);
		event.registerLayerDefinition(MinepreggoModelLayers.PREDEFINED_PREGNANT_BODY_P1, PredefinedPregnantBodyP1Model::createBodyLayer);
		event.registerLayerDefinition(MinepreggoModelLayers.PREDEFINED_PREGNANT_BODY_P2, PredefinedPregnantBodyP2Model::createBodyLayer);
		event.registerLayerDefinition(MinepreggoModelLayers.PREDEFINED_PREGNANT_BODY_P3, PredefinedPregnantBodyP3Model::createBodyLayer);
		event.registerLayerDefinition(MinepreggoModelLayers.PREDEFINED_PREGNANT_BODY_P4, PredefinedPregnantBodyP4Model::createBodyLayer);
		event.registerLayerDefinition(MinepreggoModelLayers.PREDEFINED_PREGNANT_BODY_P5, PredefinedPregnantBodyP5Model::createBodyLayer);
		event.registerLayerDefinition(MinepreggoModelLayers.PREDEFINED_PREGNANT_BODY_P6, PredefinedPregnantBodyP6Model::createBodyLayer);
		event.registerLayerDefinition(MinepreggoModelLayers.PREDEFINED_PREGNANT_BODY_P7, PredefinedPregnantBodyP7Model::createBodyLayer);
		event.registerLayerDefinition(MinepreggoModelLayers.PREDEFINED_PREGNANT_BODY_P8, PredefinedPregnantBodyP8Model::createBodyLayer);
		event.registerLayerDefinition(MinepreggoModelLayers.PREDEFINED_PREGNANT_BODY_P9, PredefinedPregnantBodyP9Model::createBodyLayer);
	}
	
    @SubscribeEvent
    public static void registerKeyBindings(RegisterKeyMappingsEvent event) {
        event.register(MinepreggoKeyMappings.RUB_BELLY_KEY);
        event.register(MinepreggoKeyMappings.TELEPORT_WITH_ENDER_WOMAN);
        event.register(MinepreggoKeyMappings.TELEPORT_USING_ENDER_POWER);
        event.register(MinepreggoKeyMappings.SHOOT_DRAGON_FIRE_BALL);
    }
}