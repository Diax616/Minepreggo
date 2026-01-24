package dev.dixmk.minepreggo.world.effect;

import java.util.Optional;
import java.util.OptionalInt;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.world.entity.player.PlayerPregnancySystemP0;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

public class PregnancyP0 extends AbstractPlayerPregnancy<PlayerPregnancySystemP0> {

	@Override
    protected void ensurePregnancySystemInitialized(ServerPlayer serverPlayer) {
        PlayerPregnancySystemP0 pregnancySystem = pregnancySystemsCache.get(serverPlayer.getUUID());
        
        if (pregnancySystem == null) {
            pregnancySystem = new PlayerPregnancySystemP0(serverPlayer);
            pregnancySystemsCache.put(serverPlayer.getUUID(), pregnancySystem);
            MinepreggoMod.LOGGER.info("Initialized PlayerPregnancySystemP0 for player: {}", serverPlayer.getName().getString());
        }
        else if (serverPlayer.isAlive() && !pregnancySystem.isPlayerValid(serverPlayer)) {
            MinepreggoMod.LOGGER.info("Player {} reference is stale, reinitializing PlayerPregnancySystemP0", serverPlayer.getGameProfile().getName());
            pregnancySystem = new PlayerPregnancySystemP0(serverPlayer);
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
