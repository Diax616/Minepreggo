package dev.dixmk.minepreggo.world.entity.preggo.creeper;

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

public class HostilePregnantMonsterCreeperGirl {

	public static boolean checkSpawnRules(EntityType<? extends AbstractHostilePregnantMonsterCreeperGirl> entityType, ServerLevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
		return MinepreggoModConfig.SERVER.isSpawningHostilPregnantMonsterCreeperGirlsEnable() && AbstractHostileCreeperGirl.checkSpawnRules(entityType, level, spawnType, pos, random);
	}
	
	public static class MonsterCreeperGirlP3 extends AbstractHostilePregnantMonsterCreeperGirl {

		public MonsterCreeperGirlP3(PlayMessages.SpawnEntity packet, Level world) {
			this(MinepreggoModEntities.HOSTILE_PREGNANT_MONSTER_CREEPER_GIRL_P3.get(), world);
		}

		public MonsterCreeperGirlP3(EntityType<MonsterCreeperGirlP3> type, Level world) {
			super(type, world, PregnancyPhase.P3);
			xpReward = 10;
			setNoAi(false);
			setMaxUpStep(0.6f);	
		}
		
		public static AttributeSupplier.Builder createAttributes() {
			return MonsterCreeperHelper.createBasicAttributes(0.26);
		}
	}
	
	public static class MonsterCreeperGirlP5 extends AbstractHostilePregnantMonsterCreeperGirl {

		public MonsterCreeperGirlP5(PlayMessages.SpawnEntity packet, Level world) {
			this(MinepreggoModEntities.HOSTILE_PREGNANT_MONSTER_CREEPER_GIRL_P5.get(), world);
		}

		public MonsterCreeperGirlP5(EntityType<MonsterCreeperGirlP5> type, Level world) {
			super(type, world, PregnancyPhase.P5);
			xpReward = 10;
			setNoAi(false);
			setMaxUpStep(0.6f);	
		}
		
		public static AttributeSupplier.Builder createAttributes() {
			return MonsterCreeperHelper.createBasicAttributes(0.24);
		}
	}
	
	public static class MonsterCreeperGirlP7 extends AbstractHostilePregnantMonsterCreeperGirl {

		public MonsterCreeperGirlP7(PlayMessages.SpawnEntity packet, Level world) {
			this(MinepreggoModEntities.HOSTILE_PREGNANT_MONSTER_CREEPER_GIRL_P7.get(), world);
		}

		public MonsterCreeperGirlP7(EntityType<MonsterCreeperGirlP7> type, Level world) {
			super(type, world, PregnancyPhase.P7);
			xpReward = 10;
			setNoAi(false);
			setMaxUpStep(0.6f);	
		}
		
		public static AttributeSupplier.Builder createAttributes() {
			return MonsterCreeperHelper.createBasicAttributes(0.22);
		}
	}
	
}
