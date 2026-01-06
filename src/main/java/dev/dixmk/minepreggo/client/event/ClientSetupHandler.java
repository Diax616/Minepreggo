package dev.dixmk.minepreggo.client.event;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.client.renderer.entity.layer.player.CustomPregnantBodyLayer;
import dev.dixmk.minepreggo.client.renderer.entity.layer.player.PredefinedPregnantBodyLayer;
import dev.dixmk.minepreggo.init.MinepreggoModItems;
import net.minecraft.client.renderer.entity.EntityRenderer;
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
        addBoobsLayerToSkin(event, "default");
        addBoobsLayerToSkin(event, "slim");
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
    
    
	private static void addBoobsLayerToSkin(EntityRenderersEvent.AddLayers event, String skinName) {
        EntityRenderer<? extends Player> renderer = event.getPlayerSkin(skinName);
        if (renderer instanceof PlayerRenderer playerRenderer) {
            playerRenderer.addLayer(new CustomPregnantBodyLayer(playerRenderer, event.getEntityModels()));
            playerRenderer.addLayer(new PredefinedPregnantBodyLayer(playerRenderer, event.getEntityModels()));
        }
    }	
}
