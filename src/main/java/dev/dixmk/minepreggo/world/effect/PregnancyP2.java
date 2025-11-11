package dev.dixmk.minepreggo.world.effect;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.world.entity.player.PlayerPregnancySystemP2;
import net.minecraft.server.level.ServerPlayer;

public class PregnancyP2 extends AbstractPregnancy<PlayerPregnancySystemP2> {
	
	public PregnancyP2() {
		super();
	}

	@Override
    protected void ensurePregnancySystemInitialized(ServerPlayer serverPlayer) {
        if (pregnancySystem == null) {
            this.pregnancySystem = new PlayerPregnancySystemP2(serverPlayer);
            MinepreggoMod.LOGGER.info("Initialized PlayerPregnancySystemP2 for player: {}", serverPlayer.getName().getString());
        }
        else if (!pregnancySystem.isPlayerValid(serverPlayer)) {
            MinepreggoMod.LOGGER.info("Player reference is stale, reinitializing PlayerPregnancySystemP2");
            this.pregnancySystem = new PlayerPregnancySystemP2(serverPlayer);
        }
    } 

}
