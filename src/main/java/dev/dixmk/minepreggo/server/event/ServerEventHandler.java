package dev.dixmk.minepreggo.server.event;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.server.ServerPlayerAnimationManager;
import dev.dixmk.minepreggo.server.ServerTaskQueueManager;
import net.minecraftforge.event.TickEvent;
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
    }
}
