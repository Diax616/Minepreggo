package dev.dixmk.minepreggo.world.entity.preggo.ender;

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

public class HostilePregnantMonsterEnderWoman {

	public static boolean checkSpawnRules(EntityType<? extends AbstractHostilePregnantEnderWoman> p_219014_, ServerLevelAccessor p_219015_, MobSpawnType p_219016_, BlockPos p_219017_, RandomSource p_219018_) {	
		return MinepreggoModConfig.SERVER.isSpawningHostilPregnantMonsterEnderWomenEnable() && AbstractHostileEnderWoman.checkSpawnRules(p_219014_, p_219015_, p_219016_, p_219017_, p_219018_);
	}
	
	public static class MonsterEnderWomanP3 extends AbstractHostilePregnantEnderWoman {
		public MonsterEnderWomanP3(PlayMessages.SpawnEntity packet, Level world) {
			super(MinepreggoModEntities.HOSTILE_PREGNANT_MONSTER_ENDER_WOMAN_P3.get(), world, PregnancyPhase.P3);
		}

		public MonsterEnderWomanP3(EntityType<MonsterEnderWomanP3> type, Level world) {
			super(type, world, PregnancyPhase.P3);
			xpReward = 10;
			setNoAi(false);
			setMaxUpStep(0.6f);	
		}
		
		public static AttributeSupplier.Builder createAttributes() {
			return MonsterEnderWomanHelper.createBasicAttributes(0.29D);
		}
	}
	
	public static class MonsterEnderWomanP5 extends AbstractHostilePregnantEnderWoman {
		public MonsterEnderWomanP5(PlayMessages.SpawnEntity packet, Level world) {
			super(MinepreggoModEntities.HOSTILE_PREGNANT_MONSTER_ENDER_WOMAN_P5.get(), world, PregnancyPhase.P5);
		}

		public MonsterEnderWomanP5(EntityType<MonsterEnderWomanP5> type, Level world) {
			super(type, world, PregnancyPhase.P5);
			xpReward = 10;
			setNoAi(false);
			setMaxUpStep(0.6f);	
		}
		
		public static AttributeSupplier.Builder createAttributes() {
			return MonsterEnderWomanHelper.createBasicAttributes(0.27D);
		}
	}
	
	public static class MonsterEnderWomanP7 extends AbstractHostilePregnantEnderWoman {
		public MonsterEnderWomanP7(PlayMessages.SpawnEntity packet, Level world) {
			super(MinepreggoModEntities.HOSTILE_PREGNANT_MONSTER_ENDER_WOMAN_P7.get(), world, PregnancyPhase.P7);
		}

		public MonsterEnderWomanP7(EntityType<MonsterEnderWomanP7> type, Level world) {
			super(type, world, PregnancyPhase.P7);
			xpReward = 10;
			setNoAi(false);
			setMaxUpStep(0.6f);	
		}
		
		public static AttributeSupplier.Builder createAttributes() {
			return MonsterEnderWomanHelper.createBasicAttributes(0.25D);
		}
	}
	
	/*
	public HostilPregnantMonsterEnderWoman(EntityType<HostilPregnantMonsterEnderWoman> type, Level level) {
		super(type, level, PregnancyPhase.P0);
	}

	public HostilPregnantMonsterEnderWoman(EntityType<HostilPregnantMonsterEnderWoman> type, Level level, PregnancyPhase phase) {
		super(type, level, phase);
	}
	
	public HostilPregnantMonsterEnderWoman(PlayMessages.SpawnEntity packet, Level level) {
		this(MinepreggoModEntities.HOSTIL_PREGNANT_MONSTER_ENDER_WOMAN.get(), level, readPregnancyPhase(packet));
	}
	
	private static PregnancyPhase readPregnancyPhase(PlayMessages.SpawnEntity packet) {
        FriendlyByteBuf buf = packet.getAdditionalData();
        return buf != null ? buf.readEnum(PregnancyPhase.class) : PregnancyPhase.P0;
	}
	*/
}
