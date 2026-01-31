package dev.dixmk.minepreggo.event;

import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.init.MinepreggoModMobEffects;
import dev.dixmk.minepreggo.world.entity.preggo.ITamablePregnantPreggoMob;
import dev.dixmk.minepreggo.world.pregnancy.IBreedable;
import dev.dixmk.minepreggo.world.pregnancy.IPregnancyData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.level.SleepFinishedTimeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MinepreggoMod.MODID)
public class WorldEventHandler {

	private WorldEventHandler() {}

    @SubscribeEvent
    public static void onSleepFinished(SleepFinishedTimeEvent event) {

        if (!(event.getLevel() instanceof ServerLevel level) || level.isClientSide) {
            return;
        }

        long currentWorldTime = level.getDayTime(); 
        long currentTick = currentWorldTime % 24000L; 
        long ticksSkipped = 24000L - currentTick;

        final List<IPregnancyData> pregnantEntities = StreamSupport.stream(level.getAllEntities().spliterator(), false)
                .flatMap(entity -> {
                    // If the entity itself implements the pregnancy handler, return it directly
                    if (entity instanceof LivingEntity livingEntity
                    		&& !livingEntity.hasEffect(MinepreggoModMobEffects.ETERNAL_PREGNANCY.get())
                    		&& entity instanceof ITamablePregnantPreggoMob handler) {
                        return Stream.of(IPregnancyData.class.cast(handler.getPregnancyData()));
                    }
                    
                    // If the entity is a player, try to obtain the player's pregnancy system from capability
                    if (entity instanceof ServerPlayer serverPlayer && !serverPlayer.hasEffect(MinepreggoModMobEffects.ETERNAL_PREGNANCY.get())) { 
                        var playerDataOpt = serverPlayer.getCapability(MinepreggoCapabilities.PLAYER_DATA).resolve();
                        if (playerDataOpt.isPresent()) {
                            var femaleOpt = playerDataOpt.get().getFemaleData().resolve();
                            if (femaleOpt.isPresent()) {
                                var femaleData = femaleOpt.get();
                                if (femaleData.isPregnant() && femaleData.isPregnancyDataInitialized()) {
                                    return Stream.of(IPregnancyData.class.cast(femaleData.getPregnancyData()));
                                }
                            }
                        }
                    }

                    return Stream.empty();
                })
                .toList();

        
        final List<IBreedable> breedableEntities = StreamSupport.stream(level.getAllEntities().spliterator(), false)
                .flatMap(entity -> {
                    if (entity instanceof IBreedable handler) {
                        return Stream.of(handler);
                    }
                    if (entity instanceof ServerPlayer serverPlayer) { 
                        var playerDataOpt = serverPlayer.getCapability(MinepreggoCapabilities.PLAYER_DATA).resolve();
                        if (playerDataOpt.isPresent()) {
                            var breedableOpt = playerDataOpt.get().getBreedableData();
                            if (breedableOpt.isPresent()) {
                                return Stream.of(breedableOpt.get());
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
            final var numOfDays = Math.min(tickResult / MinepreggoModConfig.SERVER.getTotalTicksByPregnancyDay(), pregnantEntity.getDaysByCurrentStage() - pregnantEntity.getDaysPassed());
            final var remainingTicks = tickResult % MinepreggoModConfig.SERVER.getTotalTicksByPregnancyDay();
                            
            if (numOfDays > 0) {
                pregnantEntity.setPregnancyTimer(remainingTicks);
                pregnantEntity.setDaysPassed(Math.min(pregnantEntity.getDaysByCurrentStage(), pregnantEntity.getDaysPassed() + numOfDays));
                pregnantEntity.setDaysToGiveBirth(Math.max(0, pregnantEntity.getDaysToGiveBirth() - numOfDays));
            } else {
                pregnantEntity.setPregnancyTimer(tickResult);
            }
            
            MinepreggoMod.LOGGER.debug("TIME SKIP EVENT: currentPregnanctStage={}, pregnancyTimer={}, ticksSkipped={}, tickResult={}, numOfDaysPassed={}, remainingTicks={}",
                    pregnantEntity.getCurrentPregnancyPhase(), pregnancyTimer, ticksSkipped, tickResult, numOfDays, remainingTicks);
        });
        
        breedableEntities.forEach(breedableEntity -> breedableEntity.incrementSexualAppetite(4));
    }
}