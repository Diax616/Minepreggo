package dev.dixmk.minepreggo.world.effect;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.world.entity.player.PlayerHelper;
import dev.dixmk.minepreggo.world.entity.preggo.Baby;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;

public class EnderImpregnation extends Impregnantion {

	public EnderImpregnation() {
		super(-13434829);
	}
	
	@Override
	public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
		if (entity instanceof ServerPlayer serverPlayer) {			
			if (PlayerHelper.tryStartPregnancyByPotion(serverPlayer, Baby.ENDER, amplifier)) {
				MinepreggoMod.LOGGER.info("Player {} has become pregnant.", serverPlayer.getName().getString());
			}
			else {
				MinepreggoMod.LOGGER.info("Player {} could not become pregnant.", serverPlayer.getName().getString());
			}							
		}
	}
}

