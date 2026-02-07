package dev.dixmk.minepreggo.world.entity.preggo.ender;

import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.world.entity.preggo.IPreggoMobPregnancySystem;
import dev.dixmk.minepreggo.world.entity.preggo.ITamablePreggoMobSystem;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobPregnancySystemP0;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobPregnancySystemP1;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobPregnancySystemP2;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobPregnancySystemP3;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobPregnancySystemP4;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobPregnancySystemP5;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobPregnancySystemP6;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobPregnancySystemP7;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobPregnancySystemP8;
import dev.dixmk.minepreggo.world.entity.preggo.PregnantPreggoMobSystemP0;
import dev.dixmk.minepreggo.world.entity.preggo.PregnantPreggoMobSystemP1;
import dev.dixmk.minepreggo.world.entity.preggo.PregnantPreggoMobSystemP2;
import dev.dixmk.minepreggo.world.pregnancy.FemaleEntityImpl;
import dev.dixmk.minepreggo.world.pregnancy.IFemaleEntity;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PlayMessages;

public class TamablePregnantMonsterEnderWoman {

	public static class TamableMonsterEnderWomanP0 extends AbstractTamablePregnantMonsterEnderWoman {
		
		public TamableMonsterEnderWomanP0(PlayMessages.SpawnEntity packet, Level world) {
			this(MinepreggoModEntities.TAMABLE_MONSTER_ENDER_WOMAN_P0.get(), world);
		}

		public TamableMonsterEnderWomanP0(EntityType<TamableMonsterEnderWomanP0> type, Level world) {
			super(type, world, PregnancyPhase.P0);
			xpReward = 10;
			setNoAi(false);
			setMaxUpStep(0.6f);
		}
		
		@Override
		protected ITamablePreggoMobSystem createTamablePreggoMobSystem() {
			return new PregnantPreggoMobSystemP0<>(this, MinepreggoModConfig.SERVER.getTotalTicksOfHungryP1(), PregnancySystemHelper.TOTAL_TICKS_SEXUAL_APPETITE_P0);
		}
		
		@Override
		protected IFemaleEntity createFemaleEntityData() {
			return new FemaleEntityImpl();
		}
		
		@Override
		protected IPreggoMobPregnancySystem createPregnancySystem() {
			return new PreggoMobPregnancySystemP0<TamableMonsterEnderWomanP0>(this) {
				@Override
				protected void advanceToNextPregnancyPhase() {
					if (pregnantEntity.level() instanceof ServerLevel serverLevel) {
						var enderWoman = MinepreggoModEntities.TAMABLE_MONSTER_ENDER_WOMAN_P1.get().spawn(serverLevel, BlockPos.containing(pregnantEntity.getX(), pregnantEntity.getY(), pregnantEntity.getZ()), MobSpawnType.CONVERSION);
						PreggoMobHelper.copyAllData(pregnantEntity, enderWoman);
					}
				}
			};
		}

		public static AttributeSupplier.Builder createAttributes() {
			return MonsterEnderWomanHelper.createTamableAttributes(0.3);
		}
	}

	public static class TamableMonsterEnderWomanP1 extends AbstractTamablePregnantMonsterEnderWoman {
		
		public TamableMonsterEnderWomanP1(PlayMessages.SpawnEntity packet, Level world) {
			this(MinepreggoModEntities.TAMABLE_MONSTER_ENDER_WOMAN_P1.get(), world);
		}

		public TamableMonsterEnderWomanP1(EntityType<TamableMonsterEnderWomanP1> type, Level world) {
			super(type, world, PregnancyPhase.P1);
			xpReward = 10;
			setNoAi(false);
			setMaxUpStep(0.6f);
		}
		
		@Override
		protected ITamablePreggoMobSystem createTamablePreggoMobSystem() {
			return new PregnantPreggoMobSystemP1<>(this, MinepreggoModConfig.SERVER.getTotalTicksOfHungryP1(), PregnancySystemHelper.TOTAL_TICKS_SEXUAL_APPETITE_P1);
		}
		
		@Override
		protected IFemaleEntity createFemaleEntityData() {
			return new FemaleEntityImpl();
		}
		
		@Override
		protected IPreggoMobPregnancySystem createPregnancySystem() {
			return new PreggoMobPregnancySystemP1<TamableMonsterEnderWomanP1>(this) {
				@Override
				protected void advanceToNextPregnancyPhase() {
					if (pregnantEntity.level() instanceof ServerLevel serverLevel) {
						var enderWoman = MinepreggoModEntities.TAMABLE_MONSTER_ENDER_WOMAN_P2.get().spawn(serverLevel, BlockPos.containing(pregnantEntity.getX(), pregnantEntity.getY(), pregnantEntity.getZ()), MobSpawnType.CONVERSION);
						PreggoMobHelper.copyAllData(pregnantEntity, enderWoman);
					}
				}
				
				@Override
				protected void initPostMiscarriage() {
					TamableMonsterEnderWoman.onPostMiscarriage(pregnantEntity);
				}
			};
		}

		public static AttributeSupplier.Builder createAttributes() {
			return MonsterEnderWomanHelper.createTamableAttributes(0.3);
		}
	}
	
	public static class TamableMonsterEnderWomanP2 extends AbstractTamablePregnantMonsterEnderWoman {
		
		public TamableMonsterEnderWomanP2(PlayMessages.SpawnEntity packet, Level world) {
			this(MinepreggoModEntities.TAMABLE_MONSTER_ENDER_WOMAN_P2.get(), world);
		}

		public TamableMonsterEnderWomanP2(EntityType<TamableMonsterEnderWomanP2> type, Level world) {
			super(type, world, PregnancyPhase.P2);
			xpReward = 10;
			setNoAi(false);
			setMaxUpStep(0.6f);
		}
		
		@Override
		protected ITamablePreggoMobSystem createTamablePreggoMobSystem() {
			return new PregnantPreggoMobSystemP2<>(this, MinepreggoModConfig.SERVER.getTotalTicksOfHungryP2(), PregnancySystemHelper.TOTAL_TICKS_SEXUAL_APPETITE_P2);
		}
		
		@Override
		protected IFemaleEntity createFemaleEntityData() {
			return new FemaleEntityImpl();
		}
		
		@Override
		protected IPreggoMobPregnancySystem createPregnancySystem() {
			return new PreggoMobPregnancySystemP2<TamableMonsterEnderWomanP2>(this) {
				@Override
				protected void advanceToNextPregnancyPhase() {
					if (pregnantEntity.level() instanceof ServerLevel serverLevel) {
						var enderWoman = MinepreggoModEntities.TAMABLE_MONSTER_ENDER_WOMAN_P3.get().spawn(serverLevel, BlockPos.containing(pregnantEntity.getX(), pregnantEntity.getY(), pregnantEntity.getZ()), MobSpawnType.CONVERSION);
						PreggoMobHelper.copyAllData(pregnantEntity, enderWoman);
					}
				}
				
				@Override
				protected void initPostMiscarriage() {
					TamableMonsterEnderWoman.onPostMiscarriage(pregnantEntity);
				}
			};
		}

		public static AttributeSupplier.Builder createAttributes() {
			return MonsterEnderWomanHelper.createTamableAttributes(0.29);
		}
	}
	
	public static class TamableMonsterEnderWomanP3 extends AbstractTamablePregnantMonsterEnderWoman {
		
		public TamableMonsterEnderWomanP3(PlayMessages.SpawnEntity packet, Level world) {
			this(MinepreggoModEntities.TAMABLE_MONSTER_ENDER_WOMAN_P3.get(), world);
		}

		public TamableMonsterEnderWomanP3(EntityType<TamableMonsterEnderWomanP3> type, Level world) {
			super(type, world, PregnancyPhase.P3);
			xpReward = 10;
			setNoAi(false);
			setMaxUpStep(0.6f);
		}
		
		@Override
		protected ITamablePreggoMobSystem createTamablePreggoMobSystem() {
			return new PregnantPreggoMobSystemP2<>(this, MinepreggoModConfig.SERVER.getTotalTicksOfHungryP3(), PregnancySystemHelper.TOTAL_TICKS_SEXUAL_APPETITE_P3);
		}
		
		@Override
		protected IFemaleEntity createFemaleEntityData() {
			return new FemaleEntityImpl();
		}
		
		@Override
		protected IPreggoMobPregnancySystem createPregnancySystem() {
			return new PreggoMobPregnancySystemP3<TamableMonsterEnderWomanP3>(this) {
				@Override
				protected void advanceToNextPregnancyPhase() {
					if (pregnantEntity.level() instanceof ServerLevel serverLevel) {
						var enderWoman = MinepreggoModEntities.TAMABLE_MONSTER_ENDER_WOMAN_P4.get().spawn(serverLevel, BlockPos.containing(pregnantEntity.getX(), pregnantEntity.getY(), pregnantEntity.getZ()), MobSpawnType.CONVERSION);
						PreggoMobHelper.copyAllData(pregnantEntity, enderWoman);
					}
				}
				
				@Override
				protected void initPostMiscarriage() {
					TamableMonsterEnderWoman.onPostMiscarriage(pregnantEntity);
				}
			};
		}

		public static AttributeSupplier.Builder createAttributes() {
			return MonsterEnderWomanHelper.createTamableAttributes(0.23);
		}
	}

	public static class TamableMonsterEnderWomanP4 extends AbstractTamablePregnantMonsterEnderWoman {
		
		public TamableMonsterEnderWomanP4(PlayMessages.SpawnEntity packet, Level world) {
			this(MinepreggoModEntities.TAMABLE_MONSTER_ENDER_WOMAN_P4.get(), world);
		}

		public TamableMonsterEnderWomanP4(EntityType<TamableMonsterEnderWomanP4> type, Level world) {
			super(type, world, PregnancyPhase.P4);
			xpReward = 10;
			setNoAi(false);
			setMaxUpStep(0.6f);
		}
		
		@Override
		protected ITamablePreggoMobSystem createTamablePreggoMobSystem() {
			return new PregnantPreggoMobSystemP2<>(this, MinepreggoModConfig.SERVER.getTotalTicksOfHungryP4(), PregnancySystemHelper.TOTAL_TICKS_SEXUAL_APPETITE_P4);
		}
		
		@Override
		protected IFemaleEntity createFemaleEntityData() {
			return new FemaleEntityImpl();
		}
		
		@Override
		protected IPreggoMobPregnancySystem createPregnancySystem() {
			return new PreggoMobPregnancySystemP4<>(this) {
				@Override
				protected void advanceToNextPregnancyPhase() {
					if (pregnantEntity.level() instanceof ServerLevel serverLevel) {
						var enderWoman = MinepreggoModEntities.TAMABLE_MONSTER_ENDER_WOMAN_P5.get().spawn(serverLevel, BlockPos.containing(pregnantEntity.getX(), pregnantEntity.getY(), pregnantEntity.getZ()), MobSpawnType.CONVERSION);
						PreggoMobHelper.copyAllData(pregnantEntity, enderWoman);
					}
				}
				
				@Override
				protected void initPostMiscarriage() {
					TamableMonsterEnderWoman.onPostMiscarriage(pregnantEntity);
				}
				
				@Override
				protected void initPostPartum() {
					TamableMonsterEnderWoman.onPostPartum(pregnantEntity);
				}
			};
		}
		
		public static AttributeSupplier.Builder createAttributes() {
			return MonsterEnderWomanHelper.createTamableAttributes(0.28);
		}
	}
	
	public static class TamableMonsterEnderWomanP5 extends AbstractTamablePregnantMonsterEnderWoman {
		
		public TamableMonsterEnderWomanP5(PlayMessages.SpawnEntity packet, Level world) {
			this(MinepreggoModEntities.TAMABLE_MONSTER_ENDER_WOMAN_P5.get(), world);
		}

		public TamableMonsterEnderWomanP5(EntityType<TamableMonsterEnderWomanP5> type, Level world) {
			super(type, world, PregnancyPhase.P5);
			xpReward = 10;
			setNoAi(false);
			setMaxUpStep(0.6f);
		}

		@Override
		protected ITamablePreggoMobSystem createTamablePreggoMobSystem() {
			return new PregnantPreggoMobSystemP2<>(this, MinepreggoModConfig.SERVER.getTotalTicksOfHungryP5(), PregnancySystemHelper.TOTAL_TICKS_SEXUAL_APPETITE_P5);
		}
		
		@Override
		protected IFemaleEntity createFemaleEntityData() {
			return new FemaleEntityImpl();
		}
		
		@Override
		protected IPreggoMobPregnancySystem createPregnancySystem() {
			return new PreggoMobPregnancySystemP5<>(this) {
				@Override
				protected void advanceToNextPregnancyPhase() {
					if (pregnantEntity.level() instanceof ServerLevel serverLevel) {
						var enderWoman = MinepreggoModEntities.TAMABLE_MONSTER_ENDER_WOMAN_P6.get().spawn(serverLevel, BlockPos.containing(pregnantEntity.getX(), pregnantEntity.getY(), pregnantEntity.getZ()), MobSpawnType.CONVERSION);
						PreggoMobHelper.copyAllData(pregnantEntity, enderWoman);
					}
				}
				
				@Override
				protected void initPostMiscarriage() {
					TamableMonsterEnderWoman.onPostMiscarriage(pregnantEntity);
				}
				
				@Override
				protected void initPostPartum() {
					TamableMonsterEnderWoman.onPostPartum(pregnantEntity);
				}
			};
		}

		public static AttributeSupplier.Builder createAttributes() {
			return MonsterEnderWomanHelper.createTamableAttributes(0.27);
		}
	}

	public static class TamableMonsterEnderWomanP6 extends AbstractTamablePregnantMonsterEnderWoman {
		
		public TamableMonsterEnderWomanP6(PlayMessages.SpawnEntity packet, Level world) {
			this(MinepreggoModEntities.TAMABLE_MONSTER_ENDER_WOMAN_P6.get(), world);
		}

		public TamableMonsterEnderWomanP6(EntityType<TamableMonsterEnderWomanP6> type, Level world) {
			super(type, world, PregnancyPhase.P6);
			xpReward = 10;
			setNoAi(false);
			setMaxUpStep(0.6f);
		}

		@Override
		protected ITamablePreggoMobSystem createTamablePreggoMobSystem() {
			return new PregnantPreggoMobSystemP2<>(this, MinepreggoModConfig.SERVER.getTotalTicksOfHungryP6(), PregnancySystemHelper.TOTAL_TICKS_SEXUAL_APPETITE_P6);
		}
		
		@Override
		protected IFemaleEntity createFemaleEntityData() {
			return new FemaleEntityImpl();
		}
		
		@Override
		protected IPreggoMobPregnancySystem createPregnancySystem() {
			return new PreggoMobPregnancySystemP6<>(this) {
				@Override
				protected void advanceToNextPregnancyPhase() {
					if (pregnantEntity.level() instanceof ServerLevel serverLevel) {
						var enderWoman = MinepreggoModEntities.TAMABLE_MONSTER_ENDER_WOMAN_P7.get().spawn(serverLevel, BlockPos.containing(pregnantEntity.getX(), pregnantEntity.getY(), pregnantEntity.getZ()), MobSpawnType.CONVERSION);
						PreggoMobHelper.copyAllData(pregnantEntity, enderWoman);
					}
				}
				
				@Override
				protected void initPostMiscarriage() {
					TamableMonsterEnderWoman.onPostMiscarriage(pregnantEntity);
				}
				
				@Override
				protected void initPostPartum() {
					TamableMonsterEnderWoman.onPostPartum(pregnantEntity);
				}
			};
		}
		
		public static AttributeSupplier.Builder createAttributes() {
			return MonsterEnderWomanHelper.createTamableAttributes(0.21);
		}
	}
	
	public static class TamableMonsterEnderWomanP7 extends AbstractTamablePregnantMonsterEnderWoman {
		
		public TamableMonsterEnderWomanP7(PlayMessages.SpawnEntity packet, Level world) {
			this(MinepreggoModEntities.TAMABLE_MONSTER_ENDER_WOMAN_P7.get(), world);
		}

		public TamableMonsterEnderWomanP7(EntityType<TamableMonsterEnderWomanP7> type, Level world) {
			super(type, world, PregnancyPhase.P7);
			xpReward = 10;
			setNoAi(false);
			setMaxUpStep(0.6f);
		}
		
		@Override
		protected ITamablePreggoMobSystem createTamablePreggoMobSystem() {
			return new PregnantPreggoMobSystemP2<>(this, MinepreggoModConfig.SERVER.getTotalTicksOfHungryP7(), PregnancySystemHelper.TOTAL_TICKS_SEXUAL_APPETITE_P7);
		}
		
		@Override
		protected IFemaleEntity createFemaleEntityData() {
			return new FemaleEntityImpl();
		}
		
		@Override
		protected IPreggoMobPregnancySystem createPregnancySystem() {
			return new PreggoMobPregnancySystemP7<>(this) {		
				@Override
				protected void advanceToNextPregnancyPhase() {
					if (pregnantEntity.level() instanceof ServerLevel serverLevel) {
						var enderWoman = MinepreggoModEntities.TAMABLE_MONSTER_ENDER_WOMAN_P8.get().spawn(serverLevel, BlockPos.containing(pregnantEntity.getX(), pregnantEntity.getY(), pregnantEntity.getZ()), MobSpawnType.CONVERSION);
						PreggoMobHelper.copyAllData(pregnantEntity, enderWoman);
					}
				}
				
				@Override
				protected void initPostMiscarriage() {
					TamableMonsterEnderWoman.onPostMiscarriage(pregnantEntity);
				}
				
				@Override
				protected void initPostPartum() {
					TamableMonsterEnderWoman.onPostPartum(pregnantEntity);
				}
			};
		}
		
		public static AttributeSupplier.Builder createAttributes() {
			return MonsterEnderWomanHelper.createTamableAttributes(0.26);
		}
	}
	
	public static class TamableMonsterEnderWomanP8 extends AbstractTamablePregnantMonsterEnderWoman {
		
		public TamableMonsterEnderWomanP8(PlayMessages.SpawnEntity packet, Level world) {
			this(MinepreggoModEntities.TAMABLE_MONSTER_ENDER_WOMAN_P8.get(), world);
		}

		public TamableMonsterEnderWomanP8(EntityType<TamableMonsterEnderWomanP8> type, Level world) {
			super(type, world, PregnancyPhase.P8);
			xpReward = 10;
			setNoAi(false);
			setMaxUpStep(0.6f);
		}
		
		@Override
		protected ITamablePreggoMobSystem createTamablePreggoMobSystem() {
			return new PregnantPreggoMobSystemP2<>(this, MinepreggoModConfig.SERVER.getTotalTicksOfHungryP8(), PregnancySystemHelper.TOTAL_TICKS_SEXUAL_APPETITE_P8);
		}
		
		@Override
		protected IFemaleEntity createFemaleEntityData() {
			return new FemaleEntityImpl();
		}
		
		@Override
		protected IPreggoMobPregnancySystem createPregnancySystem() {
			return new PreggoMobPregnancySystemP8<>(this) {	
				
				@Override
				protected void advanceToNextPregnancyPhase() {
					// P8 is last pregnancy stage
				}
				
				@Override
				protected void initPostMiscarriage() {
					TamableMonsterEnderWoman.onPostMiscarriage(pregnantEntity);
				}
				
				@Override
				protected void initPostPartum() {
					TamableMonsterEnderWoman.onPostPartum(pregnantEntity);
				}
			};
		}

		public static AttributeSupplier.Builder createAttributes() {
			return MonsterEnderWomanHelper.createTamableAttributes(0.25);
		}
	}

}