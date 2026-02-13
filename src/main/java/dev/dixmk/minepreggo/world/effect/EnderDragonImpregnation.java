package dev.dixmk.minepreggo.world.effect;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.world.entity.player.PlayerHelper;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;

public class EnderDragonImpregnation extends Impregnantion {
	
	public EnderDragonImpregnation() {
		super(-6750106);
	}

	@Override
	public void applyImpregnationEffect(LivingEntity entity, int amplifier) {
		if (entity instanceof ServerPlayer serverPlayer) {			
			if (PlayerHelper.tryStartEnderDragonPregnancy(serverPlayer)) {
				MinepreggoMod.LOGGER.info("Player {} has become pregnant.", serverPlayer.getName().getString());
			}
			else {
				MinepreggoMod.LOGGER.info("Player {} could not become pregnant.", serverPlayer.getName().getString());
			}							
		}
		else {
			entity.hurt(new DamageSource(entity.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.MAGIC)), 1);
		}
	}
}
