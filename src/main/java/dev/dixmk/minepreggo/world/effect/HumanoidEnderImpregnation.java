package dev.dixmk.minepreggo.world.effect;

import java.util.Optional;

import org.apache.commons.lang3.tuple.ImmutableTriple;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.world.entity.player.PlayerHelper;
import dev.dixmk.minepreggo.world.entity.preggo.Creature;
import dev.dixmk.minepreggo.world.entity.preggo.Species;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;

public class HumanoidEnderImpregnation extends Impregnantion {

	public HumanoidEnderImpregnation() {
		super(-13434829);
	}
	
	@Override
	public void applyImpregnationEffect(LivingEntity entity, int amplifier) {
		if (entity instanceof ServerPlayer serverPlayer) {			
			if (PlayerHelper.tryStartPregnancyByPotion(serverPlayer, ImmutableTriple.of(Optional.empty(), Species.ENDER, Creature.HUMANOID), amplifier)) {
				MinepreggoMod.LOGGER.info("Player {} has become pregnant.", serverPlayer.getName().getString());
			}
			else {
				MinepreggoMod.LOGGER.info("Player {} could not become pregnant.", serverPlayer.getName().getString());
			}							
		}
		else {
			entity.hurt(new DamageSource(entity.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.GENERIC)), 1);
		}
	}
}
