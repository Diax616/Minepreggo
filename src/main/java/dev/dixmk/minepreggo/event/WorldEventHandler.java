package dev.dixmk.minepreggo.event;

import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.apache.commons.lang3.tuple.ImmutablePair;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.init.MinepreggoModMobEffects;
import dev.dixmk.minepreggo.server.ServerParticleHelper;
import dev.dixmk.minepreggo.server.ServerTaskQueueManager;
import dev.dixmk.minepreggo.world.entity.preggo.ITamablePregnantPreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
import dev.dixmk.minepreggo.world.pregnancy.IBreedable;
import dev.dixmk.minepreggo.world.pregnancy.IPregnancyData;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import net.minecraft.core.particles.ParticleTypes;
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

        final List<ImmutablePair<LivingEntity, IPregnancyData>> pregnantEntities = StreamSupport
                .stream(level.getAllEntities().spliterator(), false)
                .flatMap(entity -> {
                    // If the entity itself implements the pregnancy handler, return it directly
                    if (entity instanceof PreggoMob preggoMob && preggoMob instanceof ITamablePregnantPreggoMob handler) {
                        return Stream.of(ImmutablePair.of((LivingEntity) preggoMob,
                                IPregnancyData.class.cast(handler.getPregnancyData())));
                    }

                    // If the entity is a player, try to obtain the player's pregnancy system from capability
                    if (entity instanceof ServerPlayer serverPlayer) {
                        var playerDataOpt = serverPlayer.getCapability(MinepreggoCapabilities.PLAYER_DATA).resolve();
                        if (playerDataOpt.isPresent()) {
                            var femaleOpt = playerDataOpt.get().getFemaleData().resolve();
                            if (femaleOpt.isPresent()) {
                                var femaleData = femaleOpt.get();
                                if (femaleData.isPregnant() && femaleData.isPregnancyDataInitialized()) {
                                    return Stream.of(ImmutablePair.of((LivingEntity) serverPlayer,
                                            IPregnancyData.class.cast(femaleData.getPregnancyData())));
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
           
        pregnantEntities.forEach(pair -> {
            final LivingEntity entity = pair.getLeft();
            final IPregnancyData pregnancyData = pair.getRight();

            if (pregnancyData.getPregnancyHealth() < PregnancySystemHelper.MAX_PREGNANCY_HEALTH) {
            	pregnancyData.incrementPregnancyHealth(33 - Math.max(0, pregnancyData.getCurrentPregnancyPhase().ordinal() * 2));
				ServerTaskQueueManager.getInstance().queueTask(20, () -> ServerParticleHelper.spawnParticlesAroundSelf(entity, ParticleTypes.HAPPY_VILLAGER, 10));
            }
            
            if (entity.hasEffect(MinepreggoModMobEffects.ETERNAL_PREGNANCY.get())) {
				return; 
			}
            
            // Add the skipped ticks to the pregnancy timer.
            final var pregnancyTimer = pregnancyData.getPregnancyTimer();
            final var tickResult = pregnancyTimer + (int) ticksSkipped;
            final var numOfDays = Math.min(tickResult / MinepreggoModConfig.SERVER.getTotalTicksByPregnancyDay(), pregnancyData.getDaysByCurrentStage() - pregnancyData.getDaysPassed());
            final var remainingTicks = tickResult % MinepreggoModConfig.SERVER.getTotalTicksByPregnancyDay();

            if (numOfDays > 0) {
            	pregnancyData.setPregnancyTimer(remainingTicks);
            	pregnancyData.setDaysPassed(Math.min(pregnancyData.getDaysByCurrentStage(), pregnancyData.getDaysPassed() + numOfDays));
            	pregnancyData.setDaysToGiveBirth(Math.max(0, pregnancyData.getDaysToGiveBirth() - numOfDays));
            } else {
            	pregnancyData.setPregnancyTimer(tickResult);
            }

            MinepreggoMod.LOGGER.debug(
                    "TIME SKIP EVENT: entity={}, currentPregnancyStage={}, pregnancyTimer={}, ticksSkipped={}, tickResult={}, numOfDaysPassed={}, remainingTicks={}",
                    entity.getName().getString(), pregnancyData.getCurrentPregnancyPhase(),
                    pregnancyTimer, ticksSkipped, tickResult, numOfDays, remainingTicks);
        });
        
        breedableEntities.forEach(breedableEntity -> breedableEntity.incrementSexualAppetite(4));
    }
}