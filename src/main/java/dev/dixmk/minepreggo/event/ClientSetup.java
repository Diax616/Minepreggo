package dev.dixmk.minepreggo.event;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.client.renderer.entity.layer.player.PregnantBodyRenderLayer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MinepreggoMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup {
    
    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.AddLayers event) {
        // Add layer to all player renderer types
        addBoobsLayerToSkin(event, "default");  // Steve model
        addBoobsLayerToSkin(event, "slim");     // Alex model
    }
    
	private static void addBoobsLayerToSkin(EntityRenderersEvent.AddLayers event, String skinName) {
        EntityRenderer<? extends Player> renderer = event.getPlayerSkin(skinName);
        if (renderer instanceof PlayerRenderer playerRenderer) {
            playerRenderer.addLayer(new PregnantBodyRenderLayer(playerRenderer, event.getEntityModels()));
        }
    }
}