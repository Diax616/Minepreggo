package dev.dixmk.minepreggo.world.effect;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.world.entity.player.PlayerPregnancySystemP1;
import net.minecraft.server.level.ServerPlayer;

public class PregnancyP1 extends AbstractPlayerPregnancy<PlayerPregnancySystemP1> {

	@Override
    protected void ensurePregnancySystemInitialized(ServerPlayer serverPlayer) {
        if (pregnancySystem == null) {
            this.pregnancySystem = new PlayerPregnancySystemP1(serverPlayer);
            MinepreggoMod.LOGGER.info("Initialized PlayerPregnancySystemP1 for player: {}", serverPlayer.getName().getString());
        }
        else if (!pregnancySystem.isPlayerValid(serverPlayer)) {
            MinepreggoMod.LOGGER.info("Player reference is stale, reinitializing PlayerPregnancySystemP1");
            this.pregnancySystem = new PlayerPregnancySystemP1(serverPlayer);
        }
    } 
}
