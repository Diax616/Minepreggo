package dev.dixmk.minepreggo.event;

import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.world.pregnancy.IPregnancySystemHandler;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.level.SleepFinishedTimeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MinepreggoMod.MODID)
public class WorldEventHandler {

	private WorldEventHandler() {}

    @SubscribeEvent
    public static void onSleepFinished(SleepFinishedTimeEvent event) {
       ServerLevel level = (ServerLevel) event.getLevel();
        
        if (level.isClientSide()) {
            return;
        }

        long currentWorldTime = level.getDayTime(); 
        long currentTick = currentWorldTime % 24000L; 
        long ticksSkipped = 24000L - currentTick;

        final List<IPregnancySystemHandler> pregnantEntities = StreamSupport.stream(level.getAllEntities().spliterator(), false)
                .flatMap(entity -> {
                    // If the entity itself implements the pregnancy handler, return it directly
                    if (entity instanceof IPregnancySystemHandler handler) {
                        return Stream.of(handler);
                    }

                    // If the entity is a player, try to obtain the player's pregnancy system from capability
                    if (entity instanceof ServerPlayer serverPlayer) { 
                        var playerDataOpt = serverPlayer.getCapability(MinepreggoCapabilities.PLAYER_DATA).resolve();
                        if (playerDataOpt.isPresent()) {
                            var femaleOpt = playerDataOpt.get().getFemaleData().resolve();
                            if (femaleOpt.isPresent()) {
                                var femaleData = femaleOpt.get();
                                if (femaleData.isPregnant() && femaleData.isPregnancySystemInitialized()) {
                                    return Stream.of(IPregnancySystemHandler.class.cast(femaleData.getPregnancySystem()));
                                }
                            }
                        }
                    }

                    return Stream.empty();
                })
                .toList();

        pregnantEntities.forEach(pregnantEntity -> {
            // Add the skipped ticks to the pregnancy timer.
        	final var pregnancyTimer = pregnantEntity.getPregnancyTimer();
            final var tickResult = pregnancyTimer + (int) ticksSkipped;
            final var numOfDays = Math.min(tickResult / MinepreggoModConfig.getTotalTicksByPregnancyDay(), pregnantEntity.getDaysByCurrentStage() - pregnantEntity.getDaysPassed());
            final var remainingTicks = tickResult % MinepreggoModConfig.getTotalTicksByPregnancyDay();
                            
            if (numOfDays > 0) {
                pregnantEntity.setPregnancyTimer(remainingTicks);
                pregnantEntity.setDaysPassed(Math.min(pregnantEntity.getDaysByCurrentStage(), pregnantEntity.getDaysPassed() + numOfDays));
                pregnantEntity.setDaysToGiveBirth(Math.max(0, pregnantEntity.getDaysToGiveBirth() - numOfDays));
            } else {
                pregnantEntity.setPregnancyTimer(tickResult);
            }
            
            MinepreggoMod.LOGGER.debug("TIME SKIP EVENT: currentPregnanctStage={}, pregnancyTimer={}, ticksSkipped={}, tickResult={}, numOfDaysPassed={}, remainingTicks={}",
                    pregnantEntity.getCurrentPregnancyStage(), pregnancyTimer, ticksSkipped, tickResult, numOfDays, remainingTicks);
        });
    }
}