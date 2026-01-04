package dev.dixmk.minepreggo.world.effect;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.world.entity.player.PlayerPregnancySystemP0;
import net.minecraft.server.level.ServerPlayer;

public class PregnancyP0 extends AbstractPlayerPregnancy<PlayerPregnancySystemP0> {

	@Override
    protected void ensurePregnancySystemInitialized(ServerPlayer serverPlayer) {
        PlayerPregnancySystemP0 pregnancySystem = pregnancySystemsCache.get(serverPlayer.getUUID());
        
        if (pregnancySystem == null) {
            pregnancySystem = new PlayerPregnancySystemP0(serverPlayer);
            pregnancySystemsCache.put(serverPlayer.getUUID(), pregnancySystem);
            MinepreggoMod.LOGGER.info("Initialized PlayerPregnancySystemP0 for player: {}", serverPlayer.getName().getString());
        }
        else if (!pregnancySystem.isPlayerValid(serverPlayer)) {
            MinepreggoMod.LOGGER.info("Player {} reference is stale, reinitializing PlayerPregnancySystemP0", serverPlayer.getGameProfile().getName());
            pregnancySystem = new PlayerPregnancySystemP0(serverPlayer);
            pregnancySystemsCache.put(serverPlayer.getUUID(), pregnancySystem);
        }
    } 
}
