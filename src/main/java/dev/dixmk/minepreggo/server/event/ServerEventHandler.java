package dev.dixmk.minepreggo.server.event;

import java.util.Optional;
import java.util.UUID;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.init.MinepreggoModAdvancements;
import dev.dixmk.minepreggo.server.PendingTasksSavedData;
import dev.dixmk.minepreggo.server.ServerParticleHelper;
import dev.dixmk.minepreggo.server.ServerPendingPlayerTaskManager;
import dev.dixmk.minepreggo.server.ServerPlayerAnimationManager;
import dev.dixmk.minepreggo.server.ServerTaskQueueManager;
import dev.dixmk.minepreggo.world.entity.BellyPartManager;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.level.LevelEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
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
    public static void onServerStarting(ServerStartingEvent event) {
        ServerLevel overworld = event.getServer().overworld();
        
        ServerPendingPlayerTaskManager.getInstance().setLevel(overworld);

        ServerPendingPlayerTaskManager.getInstance().registerAction("impregnate_player", (source, data) -> {
            UUID targetUUID = data.getUUID("target");
            
            Optional<ServerPlayer> targetOpt = source.getServer()
                .getPlayerList().getPlayers().stream()
                .filter(p -> p.getUUID().equals(targetUUID))
                .findFirst();
            
            if (targetOpt.isPresent()) {
                MinepreggoModAdvancements.IMPREGNATE_ENTITY_TRIGGER.trigger(source, targetOpt.get());
            } 
        });
        
        PendingTasksSavedData.get(overworld);

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
