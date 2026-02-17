package dev.dixmk.minepreggo.world.effect;

import java.util.Optional;
import java.util.OptionalInt;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.world.entity.player.PlayerPregnancySystemP1;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

public class PregnancyP1 extends AbstractPlayerPregnancy<PlayerPregnancySystemP1> {

	@Override
    protected void ensurePregnancySystemInitialized(ServerPlayer serverPlayer) {
        PlayerPregnancySystemP1 pregnancySystem = pregnancySystemsCache.get(serverPlayer.getUUID());
        
        if (pregnancySystem == null) {
            pregnancySystem = new PlayerPregnancySystemP1(serverPlayer);
            pregnancySystemsCache.put(serverPlayer.getUUID(), pregnancySystem);
            MinepreggoMod.LOGGER.info("Initialized PlayerPregnancySystemP1 for player: {}", serverPlayer.getName().getString());
        }
        else if (serverPlayer.isAlive() && !pregnancySystem.isPlayerValid(serverPlayer)) {
            MinepreggoMod.LOGGER.info("Player {} reference is stale, reinitializing PlayerPregnancySystemP1", serverPlayer.getGameProfile().getName());
            pregnancySystem = new PlayerPregnancySystemP1(serverPlayer);
            pregnancySystemsCache.put(serverPlayer.getUUID(), pregnancySystem);
        }
    }
	
	@Override
	Optional<AttributeModifier> getSpeedModifier() {
		return Optional.empty();
	}

	@Override
	Optional<AttributeModifier> getAttackSpeedModifier() {
		return Optional.empty();
	}

	@Override
	OptionalInt getMovementSpeedNerfAmplifier() {
		return OptionalInt.empty();
	} 
}
