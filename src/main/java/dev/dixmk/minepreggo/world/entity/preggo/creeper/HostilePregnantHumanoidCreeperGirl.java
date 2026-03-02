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

public class HostilePregnantHumanoidCreeperGirl {
	
	private HostilePregnantHumanoidCreeperGirl() {}
	
	public static boolean checkSpawnRules(EntityType<? extends AbstractHostilePregnantHumanoidCreeperGirl> entityType, ServerLevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
		return MinepreggoModConfig.SERVER.isSpawningHostilPregnantHumanoidCreeperGirlsEnable() && AbstractHostileCreeperGirl.checkSpawnRules(entityType, level, spawnType, pos, random);
	}
		
	public static class MonsterHumanoidCreeperGirlP3 extends AbstractHostilePregnantHumanoidCreeperGirl {

		public MonsterHumanoidCreeperGirlP3(PlayMessages.SpawnEntity packet, Level world) {
			this(MinepreggoModEntities.HOSTILE_HUMANOID_CREEPER_GIRL_P3.get(), world);
		}

		public MonsterHumanoidCreeperGirlP3(EntityType<MonsterHumanoidCreeperGirlP3> type, Level world) {
			super(type, world, PregnancyPhase.P3);
			xpReward = 10;
			setNoAi(false);
			setMaxUpStep(0.6f);	
		}
		
		public static AttributeSupplier.Builder createAttributes() {
			return HumanoidCreeperHelper.createBasicAttributes(0.24);
		}
	}


	public static class MonsterHumanoidCreeperGirlP5 extends AbstractHostilePregnantHumanoidCreeperGirl {
	
		public MonsterHumanoidCreeperGirlP5(PlayMessages.SpawnEntity packet, Level world) {
			this(MinepreggoModEntities.HOSTILE_HUMANOID_CREEPER_GIRL_P5.get(), world);
		}
	
		public MonsterHumanoidCreeperGirlP5(EntityType<MonsterHumanoidCreeperGirlP5> type, Level world) {
			super(type, world, PregnancyPhase.P5);
			xpReward = 10;
			setNoAi(false);
			setMaxUpStep(0.6f);	
		}
		
		public static AttributeSupplier.Builder createAttributes() {
			return HumanoidCreeperHelper.createBasicAttributes(0.215);
		}
	}
	
	public static class MonsterHumanoidCreeperGirlP7 extends AbstractHostilePregnantHumanoidCreeperGirl {

		public MonsterHumanoidCreeperGirlP7(PlayMessages.SpawnEntity packet, Level world) {
			this(MinepreggoModEntities.HOSTILE_HUMANOID_CREEPER_GIRL_P7.get(), world);
		}

		public MonsterHumanoidCreeperGirlP7(EntityType<MonsterHumanoidCreeperGirlP7> type, Level world) {
			super(type, world, PregnancyPhase.P7);
			xpReward = 10;
			setNoAi(false);
			setMaxUpStep(0.6f);	
		}
		
		public static AttributeSupplier.Builder createAttributes() {
			return HumanoidCreeperHelper.createBasicAttributes(0.19);
		}
	}
}
