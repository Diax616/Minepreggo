package dev.dixmk.minepreggo.world.effect;

import java.util.Optional;

import org.apache.commons.lang3.tuple.ImmutableTriple;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.world.entity.player.PlayerHelper;
import dev.dixmk.minepreggo.world.entity.preggo.Creature;
import dev.dixmk.minepreggo.world.entity.preggo.Species;
import dev.dixmk.minepreggo.world.entity.preggo.ender.AbstractTamableEnderWoman;
import dev.dixmk.minepreggo.world.entity.preggo.ender.HostileMonsterEnderWoman;
import dev.dixmk.minepreggo.world.entity.preggo.ender.TamableMonsterEnderWoman;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;

public class EnderImpregnation extends Impregnantion {

	public EnderImpregnation() {
		super(-6750106);
	}
	
	@Override
	public void applyImpregnationEffect(LivingEntity entity, int amplifier) {
		if (entity instanceof ServerPlayer serverPlayer) {			
			if (PlayerHelper.tryStartPregnancyByPotion(serverPlayer, ImmutableTriple.of(Optional.empty(), Species.ENDER, Creature.MONSTER), amplifier)) {
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
			if (entity instanceof HostileMonsterEnderWoman enderWoman) {
				var nextStage = MinepreggoModEntities.TAMABLE_MONSTER_ENDER_WOMAN_P0.get().spawn(serverLevel, BlockPos.containing(x, y, z), MobSpawnType.CONVERSION);
				nextStage.setCarriedBlock(enderWoman.getCarriedBlock());
				AbstractTamableEnderWoman.syncBlockToInventory(nextStage);
				initPregnancy(enderWoman, nextStage, amplifier);
			}
			else if (entity instanceof TamableMonsterEnderWoman enderWoman && enderWoman.getGenderedData().getPostPregnancyData().isEmpty()) {
				var nextStage = MinepreggoModEntities.TAMABLE_MONSTER_ENDER_WOMAN_P0.get().spawn(serverLevel, BlockPos.containing(x, y, z), MobSpawnType.CONVERSION);
				nextStage.setCarriedBlock(enderWoman.getCarriedBlock());
				AbstractTamableEnderWoman.syncBlockToInventory(nextStage);
				initPregnancyInTamable(enderWoman, nextStage, amplifier);
			}
		}
		else {
			entity.hurt(new DamageSource(entity.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.GENERIC)), 1);
		}
	}
}

