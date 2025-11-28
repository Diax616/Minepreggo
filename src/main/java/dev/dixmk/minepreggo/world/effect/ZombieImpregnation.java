package dev.dixmk.minepreggo.world.effect;

import java.util.Optional;

import org.apache.commons.lang3.tuple.ImmutableTriple;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.world.entity.player.PlayerHelper;
import dev.dixmk.minepreggo.world.entity.preggo.Creature;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;
import dev.dixmk.minepreggo.world.entity.preggo.Species;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.MonsterZombieGirlP0;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirl;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeMap;

public class ZombieImpregnation extends Impregnantion {
	public ZombieImpregnation() {
		super(-13408768);
	}

	@Override
	public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
		if (entity instanceof ServerPlayer serverPlayer) {			
			if (PlayerHelper.tryStartPregnancyByPotion(serverPlayer, ImmutableTriple.of(Optional.empty(), Species.ZOMBIE, Creature.HUMANOID), amplifier)) {
				MinepreggoMod.LOGGER.info("Player {} has become pregnant.", serverPlayer.getName().getString());
			}
			else {
				MinepreggoMod.LOGGER.info("Player {} could not become pregnant.", serverPlayer.getName().getString());
			}							
		}
		else if (entity.level() instanceof ServerLevel serverLevel) {		
			final double x = entity.getX();
			final double y = entity.getY();	
			final double z = entity.getZ();
			
			if (entity instanceof MonsterZombieGirlP0 zombieGirl && !zombieGirl.isBaby()) {
				var nextStage = MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P0.get().spawn(serverLevel, BlockPos.containing(x, y, z), MobSpawnType.CONVERSION);
				initPregnancy(zombieGirl, nextStage, amplifier);
			}
			else if (entity instanceof TamableZombieGirl zombieGirl) {
				var nextStage = MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P0.get().spawn(serverLevel, BlockPos.containing(x, y, z), MobSpawnType.CONVERSION);
				initPregnancy(zombieGirl, nextStage, amplifier);
				PreggoMobHelper.copyOwner(zombieGirl, nextStage);
			}
			else {
				entity.hurt(new DamageSource(entity.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.GENERIC)), 1);
			}
		}		
	}
}
