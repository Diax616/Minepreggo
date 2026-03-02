package dev.dixmk.minepreggo.world.entity.preggo.zombie;

import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraftforge.network.PlayMessages;

public class HostilePregnantZombieGirl {

	private HostilePregnantZombieGirl() {}
	
	public static boolean checkSpawnRules(EntityType<? extends AbstractHostilePregnantZombieGirl> entityType, ServerLevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
		return MinepreggoModConfig.SERVER.isSpawningHostilPregnantZombieGirlsEnable() && AbstractHostileZombieGirl.checkSpawnRules(entityType, level, spawnType, pos, random);
	}
	
	public static class MonsterZombieGirlP3 extends AbstractHostilePregnantZombieGirl {

		public MonsterZombieGirlP3(PlayMessages.SpawnEntity packet, Level world) {
			this(MinepreggoModEntities.HOSTILE_ZOMBIE_GIRL_P3.get(), world);
		}

		public MonsterZombieGirlP3(EntityType<MonsterZombieGirlP3> type, Level world) {
			super(type, world, PregnancyPhase.P3);
			xpReward = 10;
			setNoAi(false);
			setMaxUpStep(0.6f);
		}

		public static AttributeSupplier.Builder createAttributes() {
			return getBasicAttributes(0.225);
		}
	}
	
	public static class MonsterZombieGirlP5 extends AbstractHostilePregnantZombieGirl {
	
		
		public MonsterZombieGirlP5(PlayMessages.SpawnEntity packet, Level world) {
			this(MinepreggoModEntities.HOSTILE_ZOMBIE_GIRL_P5.get(), world);
		}
	
		public MonsterZombieGirlP5(EntityType<MonsterZombieGirlP5> type, Level world) {
			super(type, world, PregnancyPhase.P5);
			xpReward = 10;
			setNoAi(false);
			setMaxUpStep(0.6f);
		}
	
		public static AttributeSupplier.Builder createAttributes() {
			return getBasicAttributes(0.215);
		}
	}

	public static class MonsterZombieGirlP7 extends AbstractHostilePregnantZombieGirl {
		
		public MonsterZombieGirlP7(PlayMessages.SpawnEntity packet, Level world) {
			this(MinepreggoModEntities.HOSTILE_ZOMBIE_GIRL_P7.get(), world);
		}
	
		public MonsterZombieGirlP7(EntityType<MonsterZombieGirlP7> type, Level world) {
			super(type, world, PregnancyPhase.P7);
			xpReward = 10;
			setNoAi(false);
			setMaxUpStep(0.6f);
		}
	
		public static AttributeSupplier.Builder createAttributes() {
			return getBasicAttributes(0.19);
		}
	}
}
