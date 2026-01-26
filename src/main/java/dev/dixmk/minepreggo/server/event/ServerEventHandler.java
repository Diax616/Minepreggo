package dev.dixmk.minepreggo.server.event;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.server.ServerParticleHelper;
import dev.dixmk.minepreggo.server.ServerPlayerAnimationManager;
import dev.dixmk.minepreggo.server.ServerTaskQueueManager;
import dev.dixmk.minepreggo.world.entity.BellyPartManager;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.level.LevelEvent;
import net.minecraftforge.event.server.ServerStoppingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MinepreggoMod.MODID)
public class ServerEventHandler {
	
	private ServerEventHandler() {}
	
    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
            
        ServerPlayerAnimationManager.getInstance().onServerTick();
        ServerTaskQueueManager.getInstance().processTasks();
        ServerParticleHelper.tickBloodRains();
    }
    
    @SubscribeEvent
    public static void onServerStopping(ServerStoppingEvent event) {
    	if (MinepreggoModConfig.SERVER.isBellyColisionsForPlayersEnable() || MinepreggoModConfig.SERVER.isBellyColisionsForPreggoMobsEnable()) {
        	BellyPartManager.getInstance().clear();
		}	
    }
    
    @SubscribeEvent
    public static void onWorldUnload(LevelEvent.Unload event) {
    	if (MinepreggoModConfig.SERVER.isBellyColisionsForPlayersEnable() || MinepreggoModConfig.SERVER.isBellyColisionsForPreggoMobsEnable()) {
        	BellyPartManager.getInstance().clear();
		}	
    }
}
