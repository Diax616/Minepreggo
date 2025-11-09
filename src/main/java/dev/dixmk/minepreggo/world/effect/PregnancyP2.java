package dev.dixmk.minepreggo.world.effect;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.world.entity.player.PlayerPregnancySystemP2;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;

public class PregnancyP2 extends AbstractPregnancy<PlayerPregnancySystemP2> {
	public PregnancyP2() {
		super();
	}

	@Override
	public void addAttributeModifiers(LivingEntity p_19478_, AttributeMap p_19479_, int p_19480_) {
		if (pregnancySystem == null && p_19478_ instanceof ServerPlayer serverPlayer) {
			this.pregnancySystem = new PlayerPregnancySystemP2(serverPlayer);
			MinepreggoMod.LOGGER.debug("Initialized PregnancyP1 system for player: {}", serverPlayer.getName().getString());
		}
	}

}
