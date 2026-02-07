package dev.dixmk.minepreggo.world.entity.preggo.creeper;

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


public class TamablePregnantMonsterCreeperGirl {
	public static class TamableMonsterCreeperGirlP0 extends AbstractTamablePregnantMonsterCreeperGirl {
		
		public TamableMonsterCreeperGirlP0(PlayMessages.SpawnEntity packet, Level world) {
			this(MinepreggoModEntities.TAMABLE_MONSTER_CREEPER_GIRL_P0.get(), world);
		}

		public TamableMonsterCreeperGirlP0(EntityType<TamableMonsterCreeperGirlP0> type, Level world) {
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
			return new PreggoMobPregnancySystemP0<TamableMonsterCreeperGirlP0>(this) {
				@Override
				protected void advanceToNextPregnancyPhase() {
					if (pregnantEntity.level() instanceof ServerLevel serverLevel) {
						var creeperGirl = MinepreggoModEntities.TAMABLE_MONSTER_CREEPER_GIRL_P1.get().spawn(serverLevel, BlockPos.containing(pregnantEntity.getX(), pregnantEntity.getY(), pregnantEntity.getZ()), MobSpawnType.CONVERSION);
						PreggoMobHelper.copyAllData(pregnantEntity, creeperGirl);
					}
				}
			};
		}

		public static AttributeSupplier.Builder createAttributes() {
			return MonsterCreeperHelper.createBasicAttributes(0.24);
		}
	}

	public static class TamableMonsterCreeperGirlP1 extends AbstractTamablePregnantMonsterCreeperGirl {
		
		public TamableMonsterCreeperGirlP1(PlayMessages.SpawnEntity packet, Level world) {
			this(MinepreggoModEntities.TAMABLE_MONSTER_CREEPER_GIRL_P1.get(), world);
		}

		public TamableMonsterCreeperGirlP1(EntityType<TamableMonsterCreeperGirlP1> type, Level world) {
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
			return new PreggoMobPregnancySystemP1<TamableMonsterCreeperGirlP1>(this) {
				@Override
				protected void advanceToNextPregnancyPhase() {
					if (pregnantEntity.level() instanceof ServerLevel serverLevel) {
						var creeperGirl = MinepreggoModEntities.TAMABLE_MONSTER_CREEPER_GIRL_P2.get().spawn(serverLevel, BlockPos.containing(pregnantEntity.getX(), pregnantEntity.getY(), pregnantEntity.getZ()), MobSpawnType.CONVERSION);
						PreggoMobHelper.copyAllData(pregnantEntity, creeperGirl);
					}
				}
				
				@Override
				protected void initPostMiscarriage() {
					TamableMonsterCreeperGirl.onPostMiscarriage(pregnantEntity);
				}
			};
		}

		public static AttributeSupplier.Builder createAttributes() {
			return MonsterCreeperHelper.createBasicAttributes(0.24);
		}
	}
	
	public static class TamableMonsterCreeperGirlP2 extends AbstractTamablePregnantMonsterCreeperGirl {
		
		public TamableMonsterCreeperGirlP2(PlayMessages.SpawnEntity packet, Level world) {
			this(MinepreggoModEntities.TAMABLE_MONSTER_CREEPER_GIRL_P2.get(), world);
		}

		public TamableMonsterCreeperGirlP2(EntityType<TamableMonsterCreeperGirlP2> type, Level world) {
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
			return new PreggoMobPregnancySystemP2<TamableMonsterCreeperGirlP2>(this) {
				@Override
				protected void advanceToNextPregnancyPhase() {
					if (pregnantEntity.level() instanceof ServerLevel serverLevel) {
						var creeperGirl = MinepreggoModEntities.TAMABLE_MONSTER_CREEPER_GIRL_P3.get().spawn(serverLevel, BlockPos.containing(pregnantEntity.getX(), pregnantEntity.getY(), pregnantEntity.getZ()), MobSpawnType.CONVERSION);
						PreggoMobHelper.copyAllData(pregnantEntity, creeperGirl);
					}
				}
				
				@Override
				protected void initPostMiscarriage() {
					TamableMonsterCreeperGirl.onPostMiscarriage(pregnantEntity);
				}
			};
		}

		public static AttributeSupplier.Builder createAttributes() {
			return MonsterCreeperHelper.createBasicAttributes(0.235);
		}
	}
	
	public static class TamableMonsterCreeperGirlP3 extends AbstractTamablePregnantMonsterCreeperGirl {
		
		public TamableMonsterCreeperGirlP3(PlayMessages.SpawnEntity packet, Level world) {
			this(MinepreggoModEntities.TAMABLE_MONSTER_CREEPER_GIRL_P3.get(), world);
		}

		public TamableMonsterCreeperGirlP3(EntityType<TamableMonsterCreeperGirlP3> type, Level world) {
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
			return new PreggoMobPregnancySystemP3<TamableMonsterCreeperGirlP3>(this) {
				@Override
				protected void advanceToNextPregnancyPhase() {
					if (pregnantEntity.level() instanceof ServerLevel serverLevel) {
						var creeperGirl = MinepreggoModEntities.TAMABLE_MONSTER_CREEPER_GIRL_P4.get().spawn(serverLevel, BlockPos.containing(pregnantEntity.getX(), pregnantEntity.getY(), pregnantEntity.getZ()), MobSpawnType.CONVERSION);
						PreggoMobHelper.copyAllData(pregnantEntity, creeperGirl);
					}
				}
				
				@Override
				protected void initPostMiscarriage() {
					TamableMonsterCreeperGirl.onPostMiscarriage(pregnantEntity);
				}
			};
		}

		public static AttributeSupplier.Builder createAttributes() {
			return MonsterCreeperHelper.createBasicAttributes(0.23);
		}
	}

	public static class TamableMonsterCreeperGirlP4 extends AbstractTamablePregnantMonsterCreeperGirl {
		
		public TamableMonsterCreeperGirlP4(PlayMessages.SpawnEntity packet, Level world) {
			this(MinepreggoModEntities.TAMABLE_MONSTER_CREEPER_GIRL_P4.get(), world);
		}

		public TamableMonsterCreeperGirlP4(EntityType<TamableMonsterCreeperGirlP4> type, Level world) {
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
						var creeperGirl = MinepreggoModEntities.TAMABLE_MONSTER_CREEPER_GIRL_P5.get().spawn(serverLevel, BlockPos.containing(pregnantEntity.getX(), pregnantEntity.getY(), pregnantEntity.getZ()), MobSpawnType.CONVERSION);
						PreggoMobHelper.copyAllData(pregnantEntity, creeperGirl);
					}
				}
				
				@Override
				protected void initPostMiscarriage() {
					TamableMonsterCreeperGirl.onPostMiscarriage(pregnantEntity);
				}
				
				@Override
				protected void initPostPartum() {
					TamableMonsterCreeperGirl.onPostPartum(pregnantEntity);
				}
			};
		}
		
		public static AttributeSupplier.Builder createAttributes() {
			return MonsterCreeperHelper.createBasicAttributes(0.22);
		}
	}
	
	public static class TamableMonsterCreeperGirlP5 extends AbstractTamablePregnantMonsterCreeperGirl {
		
		public TamableMonsterCreeperGirlP5(PlayMessages.SpawnEntity packet, Level world) {
			this(MinepreggoModEntities.TAMABLE_MONSTER_CREEPER_GIRL_P5.get(), world);
		}

		public TamableMonsterCreeperGirlP5(EntityType<TamableMonsterCreeperGirlP5> type, Level world) {
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
						var creeperGirl = MinepreggoModEntities.TAMABLE_MONSTER_CREEPER_GIRL_P6.get().spawn(serverLevel, BlockPos.containing(pregnantEntity.getX(), pregnantEntity.getY(), pregnantEntity.getZ()), MobSpawnType.CONVERSION);
						PreggoMobHelper.copyAllData(pregnantEntity, creeperGirl);
					}
				}
				
				@Override
				protected void initPostMiscarriage() {
					TamableMonsterCreeperGirl.onPostMiscarriage(pregnantEntity);
				}
				
				@Override
				protected void initPostPartum() {
					TamableMonsterCreeperGirl.onPostPartum(pregnantEntity);
				}
			};
		}

		public static AttributeSupplier.Builder createAttributes() {
			return MonsterCreeperHelper.createBasicAttributes(0.215);
		}
	}

	public static class TamableMonsterCreeperGirlP6 extends AbstractTamablePregnantMonsterCreeperGirl {
		
		public TamableMonsterCreeperGirlP6(PlayMessages.SpawnEntity packet, Level world) {
			this(MinepreggoModEntities.TAMABLE_MONSTER_CREEPER_GIRL_P6.get(), world);
		}

		public TamableMonsterCreeperGirlP6(EntityType<TamableMonsterCreeperGirlP6> type, Level world) {
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
						var creeperGirl = MinepreggoModEntities.TAMABLE_MONSTER_CREEPER_GIRL_P7.get().spawn(serverLevel, BlockPos.containing(pregnantEntity.getX(), pregnantEntity.getY(), pregnantEntity.getZ()), MobSpawnType.CONVERSION);
						PreggoMobHelper.copyAllData(pregnantEntity, creeperGirl);
					}
				}
				
				@Override
				protected void initPostMiscarriage() {
					TamableMonsterCreeperGirl.onPostMiscarriage(pregnantEntity);
				}
				
				@Override
				protected void initPostPartum() {
					TamableMonsterCreeperGirl.onPostPartum(pregnantEntity);
				}
			};
		}
		
		public static AttributeSupplier.Builder createAttributes() {
			return MonsterCreeperHelper.createBasicAttributes(0.21);
		}
	}
	
	public static class TamableMonsterCreeperGirlP7 extends AbstractTamablePregnantMonsterCreeperGirl {
		
		public TamableMonsterCreeperGirlP7(PlayMessages.SpawnEntity packet, Level world) {
			this(MinepreggoModEntities.TAMABLE_MONSTER_CREEPER_GIRL_P7.get(), world);
		}

		public TamableMonsterCreeperGirlP7(EntityType<TamableMonsterCreeperGirlP7> type, Level world) {
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
						var creeperGirl = MinepreggoModEntities.TAMABLE_MONSTER_CREEPER_GIRL_P8.get().spawn(serverLevel, BlockPos.containing(pregnantEntity.getX(), pregnantEntity.getY(), pregnantEntity.getZ()), MobSpawnType.CONVERSION);
						PreggoMobHelper.copyAllData(pregnantEntity, creeperGirl);
					}
				}
				
				@Override
				protected void initPostMiscarriage() {
					TamableMonsterCreeperGirl.onPostMiscarriage(pregnantEntity);
				}
				
				@Override
				protected void initPostPartum() {
					TamableMonsterCreeperGirl.onPostPartum(pregnantEntity);
				}
			};
		}
		
		public static AttributeSupplier.Builder createAttributes() {
			return MonsterCreeperHelper.createBasicAttributes(0.20);
		}
	}
	
	public static class TamableMonsterCreeperGirlP8 extends AbstractTamablePregnantMonsterCreeperGirl {
		
		public TamableMonsterCreeperGirlP8(PlayMessages.SpawnEntity packet, Level world) {
			this(MinepreggoModEntities.TAMABLE_MONSTER_CREEPER_GIRL_P8.get(), world);
		}

		public TamableMonsterCreeperGirlP8(EntityType<TamableMonsterCreeperGirlP8> type, Level world) {
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
					TamableMonsterCreeperGirl.onPostMiscarriage(pregnantEntity);
				}
				
				@Override
				protected void initPostPartum() {
					TamableMonsterCreeperGirl.onPostPartum(pregnantEntity);
				}
			};
		}

		public static AttributeSupplier.Builder createAttributes() {
			return MonsterCreeperHelper.createBasicAttributes(0.19);
		}
	}
}
