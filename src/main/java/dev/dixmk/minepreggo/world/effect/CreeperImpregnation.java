package dev.dixmk.minepreggo.world.effect;

import java.util.Optional;

import org.apache.commons.lang3.tuple.ImmutableTriple;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.world.entity.player.PlayerHelper;
import dev.dixmk.minepreggo.world.entity.preggo.Creature;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;
import dev.dixmk.minepreggo.world.entity.preggo.Species;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.MonsterCreeperGirlP0;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableCreeperGirl;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeMap;

public class CreeperImpregnation extends Impregnantion {

	public CreeperImpregnation() {
		super(-13369549);
	}

	@Override
	public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
		if (entity instanceof ServerPlayer serverPlayer) {			
			if (PlayerHelper.tryStartPregnancyByPotion(serverPlayer, ImmutableTriple.of(Optional.empty(), Species.CREEPER, Creature.MONSTER), amplifier)) {
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
			if (entity instanceof MonsterCreeperGirlP0 creeperGirl && !creeperGirl.isBaby()) {
				var nextStage = MinepreggoModEntities.TAMABLE_CREEPER_GIRL_P0.get().spawn(serverLevel, BlockPos.containing(x, y, z), MobSpawnType.CONVERSION);
				initPregnancy(creeperGirl, nextStage, amplifier);
			}		
			else if (entity instanceof TamableCreeperGirl creeperGirl) {
				var nextStage = MinepreggoModEntities.TAMABLE_CREEPER_GIRL_P0.get().spawn(serverLevel, BlockPos.containing(x, y, z), MobSpawnType.CONVERSION);
				initPregnancy(creeperGirl, nextStage, amplifier);
				PreggoMobHelper.copyOwner(creeperGirl, nextStage);
			}
			else {
				entity.hurt(new DamageSource(entity.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.GENERIC)), 1);
			}
		}
	}
}
