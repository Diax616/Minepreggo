package dev.dixmk.minepreggo.event;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.StreamSupport;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.network.capability.IPregnancySystemHandler;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.player.PlayerWakeUpEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MinepreggoMod.MODID)
public class WorldEventHandler {
    private static final ConcurrentHashMap<ServerLevel, Long> lastProcessedDay = new ConcurrentHashMap<>();

	private WorldEventHandler() {}
	
    @SubscribeEvent
    public static void onPlayerWakeUp(PlayerWakeUpEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            ServerLevel serverLevel = player.serverLevel();
            final long currentDay = serverLevel.getDayTime() / 24000;       
            
            // Only process once per in-game day
            if (lastProcessedDay.getOrDefault(serverLevel, -1L) < currentDay) {
                lastProcessedDay.put(serverLevel, currentDay);
                                        
                List<IPregnancySystemHandler> entities = StreamSupport.stream(serverLevel.getAllEntities().spliterator(), false)
                		.filter(IPregnancySystemHandler.class::isInstance)
                		.map(IPregnancySystemHandler.class::cast)
                		.toList();
                                  
                entities.forEach(preggoMob -> {
                	final var tickResult = preggoMob.getPregnancyTimer() + (int) (24000L - currentDay);
    				final var numOfDays = Math.min(tickResult / MinepreggoModConfig.getTotalTicksByPregnancyDay(), preggoMob.getDaysByStage() - preggoMob.getDaysPassed());
    				final var remainingTicks = tickResult % MinepreggoModConfig.getTotalTicksByPregnancyDay();
                   				
    				if (numOfDays > 0) {
    					preggoMob.setPregnancyTimer(remainingTicks);
    					preggoMob.setDaysPassed(Math.min(preggoMob.getDaysByStage(), preggoMob.getDaysPassed() + numOfDays));
    					preggoMob.setDaysToGiveBirth(Math.max(0, preggoMob.getDaysToGiveBirth() - numOfDays));
    				} else {
    					preggoMob.setPregnancyTimer(tickResult);
    				}
    				
    				MinepreggoMod.LOGGER.debug("TIME SKIP EVENT: currentPregnanctStage={}, tickResult={}, numOfDaysPassed={}, remainingTicks={}",
    						preggoMob.getCurrentPregnancyStage(), tickResult, numOfDays, remainingTicks);
                });
            }
        }
    }
}
