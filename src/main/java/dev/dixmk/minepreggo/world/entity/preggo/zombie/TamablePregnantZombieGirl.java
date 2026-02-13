package dev.dixmk.minepreggo.world.entity.preggo.zombie;

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
import dev.dixmk.minepreggo.world.entity.preggo.PregnantPreggoMobSystemP3;
import dev.dixmk.minepreggo.world.entity.preggo.PregnantPreggoMobSystemP4;
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

public class TamablePregnantZombieGirl {

	public static class TamableZombieGirlP0 extends AbstractTamablePregnantZombieGirl {
		
		public TamableZombieGirlP0(PlayMessages.SpawnEntity packet, Level world) {
			this(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P0.get(), world);
		}

		public TamableZombieGirlP0(EntityType<TamableZombieGirlP0> type, Level world) {
			super(type, world, PregnancyPhase.P0);
			xpReward = 10;
			setNoAi(false);
			setMaxUpStep(0.6f);
		}
		
		@Override
		protected ITamablePreggoMobSystem createTamablePreggoMobSystem() {
			return new PregnantPreggoMobSystemP0<>(this, MinepreggoModConfig.SERVER.getTotalTicksOfHungryP0(), PregnancySystemHelper.TOTAL_TICKS_SEXUAL_APPETITE_P0);
		}
		
		@Override
		protected IFemaleEntity createFemaleEntityData() {
			return new FemaleEntityImpl();
		}
		
		@Override
		protected IPreggoMobPregnancySystem createPregnancySystem() {
			return new PreggoMobPregnancySystemP0<>(this) {
				@Override
				protected void advanceToNextPregnancyPhase() {
					if (pregnantEntity.level() instanceof ServerLevel serverLevel) {
						var zombieGirl = MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P1.get().spawn(serverLevel, BlockPos.containing(pregnantEntity.getX(), pregnantEntity.getY(), pregnantEntity.getZ()), MobSpawnType.CONVERSION);		
						PreggoMobHelper.copyAllData(pregnantEntity, zombieGirl);			
					}			
				}
			};
		}
		
		public static AttributeSupplier.Builder createAttributes() {
			return getBasicAttributes(0.235);
		}
	}
	
	public static class TamableZombieGirlP1 extends AbstractTamablePregnantZombieGirl {
		
		public TamableZombieGirlP1(PlayMessages.SpawnEntity packet, Level world) {
			this(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P1.get(), world);
		}

		public TamableZombieGirlP1(EntityType<TamableZombieGirlP1> type, Level world) {
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
			return new PreggoMobPregnancySystemP1<>(this) {
				@Override
				protected void advanceToNextPregnancyPhase() {
					if (pregnantEntity.level() instanceof ServerLevel serverLevel) {
						var zombieGirl = MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P2.get().spawn(serverLevel, BlockPos.containing(pregnantEntity.getX(), pregnantEntity.getY(), pregnantEntity.getZ()), MobSpawnType.CONVERSION);		
						PreggoMobHelper.copyAllData(pregnantEntity, zombieGirl);			
					}
				}
				
				@Override
				protected void initPostMiscarriage() {
					TamableZombieGirl.onPostMiscarriage(pregnantEntity);
				}
			};
		}

		public static AttributeSupplier.Builder createAttributes() {
			return getBasicAttributes(0.235);
		}
	}
	
	public static class TamableZombieGirlP2 extends AbstractTamablePregnantZombieGirl {
		
		public TamableZombieGirlP2(PlayMessages.SpawnEntity packet, Level world) {
			this(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P2.get(), world);
		}

		public TamableZombieGirlP2(EntityType<TamableZombieGirlP2> type, Level world) {
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
			return new PreggoMobPregnancySystemP2<>(this) {
				@Override
				protected void advanceToNextPregnancyPhase() {
					if (pregnantEntity.level() instanceof ServerLevel serverLevel) {
						var zombieGirl = MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P3.get().spawn(serverLevel, BlockPos.containing(pregnantEntity.getX(), pregnantEntity.getY(), pregnantEntity.getZ()), MobSpawnType.CONVERSION);		
						PreggoMobHelper.copyAllData(pregnantEntity, zombieGirl);			
					}
				}
				
				@Override
				protected void initPostMiscarriage() {
					TamableZombieGirl.onPostMiscarriage(pregnantEntity);
				}
			};
		}
		
		public static AttributeSupplier.Builder createAttributes() {
			return getBasicAttributes(0.23);
		}
	}

	public static class TamableZombieGirlP3 extends AbstractTamablePregnantZombieGirl {
		
		public TamableZombieGirlP3(PlayMessages.SpawnEntity packet, Level world) {
			this(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P3.get(), world);
		}

		public TamableZombieGirlP3(EntityType<TamableZombieGirlP3> type, Level world) {
			super(type, world, PregnancyPhase.P3);
			xpReward = 10;
			setNoAi(false);
			setMaxUpStep(0.6f);
		}

		@Override
		protected ITamablePreggoMobSystem createTamablePreggoMobSystem() {
			return new PregnantPreggoMobSystemP3<>(this, MinepreggoModConfig.SERVER.getTotalTicksOfHungryP3(), PregnancySystemHelper.TOTAL_TICKS_SEXUAL_APPETITE_P3);
		}
		
		@Override
		protected IFemaleEntity createFemaleEntityData() {
			return new FemaleEntityImpl();
		}
		
		@Override
		protected IPreggoMobPregnancySystem createPregnancySystem() {
			return new PreggoMobPregnancySystemP3<>(this) {
				@Override
				protected void advanceToNextPregnancyPhase() {
					if (pregnantEntity.level() instanceof ServerLevel serverLevel) {
						var zombieGirl = MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P4.get().spawn(serverLevel, BlockPos.containing(pregnantEntity.getX(), pregnantEntity.getY(), pregnantEntity.getZ()), MobSpawnType.CONVERSION);		
						PreggoMobHelper.copyAllData(pregnantEntity, zombieGirl);			
					}
				}
				
				@Override
				protected void initPostMiscarriage() {
					TamableZombieGirl.onPostMiscarriage(pregnantEntity);
				}
			};
		}

		public static AttributeSupplier.Builder createAttributes() {
			return getBasicAttributes(0.225);
		}
	}

	public static class TamableZombieGirlP4 extends AbstractTamablePregnantZombieGirl {
		
		public TamableZombieGirlP4(PlayMessages.SpawnEntity packet, Level world) {
			this(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P4.get(), world);
		}

		public TamableZombieGirlP4(EntityType<TamableZombieGirlP4> type, Level world) {
			super(type, world, PregnancyPhase.P4);
			xpReward = 10;
			setNoAi(false);
			setMaxUpStep(0.6f);
		}

		@Override
		protected ITamablePreggoMobSystem createTamablePreggoMobSystem() {
			return new PregnantPreggoMobSystemP4<>(this, MinepreggoModConfig.SERVER.getTotalTicksOfHungryP4(), PregnancySystemHelper.TOTAL_TICKS_SEXUAL_APPETITE_P4);
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
						var zombieGirl = MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P5.get().spawn(serverLevel, BlockPos.containing(pregnantEntity.getX(), pregnantEntity.getY(), pregnantEntity.getZ()), MobSpawnType.CONVERSION);		
						PreggoMobHelper.copyAllData(pregnantEntity, zombieGirl);			
					}
				}
				
				@Override
				protected void initPostMiscarriage() {
					TamableZombieGirl.onPostMiscarriage(pregnantEntity);
				}

				@Override
				protected void initPostPartum() {
					TamableZombieGirl.onPostPartum(pregnantEntity);
				}
			};
		}

		public static AttributeSupplier.Builder createAttributes() {
			return getBasicAttributes(0.22);
		}
	}
	
	public static class TamableZombieGirlP5 extends AbstractTamablePregnantZombieGirl {
		
		public TamableZombieGirlP5(PlayMessages.SpawnEntity packet, Level world) {
			this(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P5.get(), world);
		}

		public TamableZombieGirlP5(EntityType<TamableZombieGirlP5> type, Level world) {
			super(type, world, PregnancyPhase.P5);
			xpReward = 10;
			setNoAi(false);
			setMaxUpStep(0.6f);
		}

		@Override
		protected ITamablePreggoMobSystem createTamablePreggoMobSystem() {
			return new PregnantPreggoMobSystemP4<>(this, MinepreggoModConfig.SERVER.getTotalTicksOfHungryP5(), PregnancySystemHelper.TOTAL_TICKS_SEXUAL_APPETITE_P5);
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
						var zombieGirl = MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P6.get().spawn(serverLevel, BlockPos.containing(pregnantEntity.getX(), pregnantEntity.getY(), pregnantEntity.getZ()), MobSpawnType.CONVERSION);		
						PreggoMobHelper.copyAllData(pregnantEntity, zombieGirl);
					}
				}
				
				@Override
				protected void initPostMiscarriage() {
					TamableZombieGirl.onPostMiscarriage(pregnantEntity);
				}

				@Override
				protected void initPostPartum() {
					TamableZombieGirl.onPostPartum(pregnantEntity);
				}
			};
		}
		
		public static AttributeSupplier.Builder createAttributes() {
			return getBasicAttributes(0.215);
		}
	}

	public static class TamableZombieGirlP6 extends AbstractTamablePregnantZombieGirl {
		
		public TamableZombieGirlP6(PlayMessages.SpawnEntity packet, Level world) {
			this(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P6.get(), world);
		}

		public TamableZombieGirlP6(EntityType<TamableZombieGirlP6> type, Level world) {
			super(type, world, PregnancyPhase.P6);
			xpReward = 10;
			setNoAi(false);
			setMaxUpStep(0.6f);
		}

		@Override
		protected ITamablePreggoMobSystem createTamablePreggoMobSystem() {
			return new PregnantPreggoMobSystemP4<>(this, MinepreggoModConfig.SERVER.getTotalTicksOfHungryP6(), PregnancySystemHelper.TOTAL_TICKS_SEXUAL_APPETITE_P6);
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
						var zombieGirl = MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P7.get().spawn(serverLevel, BlockPos.containing(pregnantEntity.getX(), pregnantEntity.getY(), pregnantEntity.getZ()), MobSpawnType.CONVERSION);		
						PreggoMobHelper.copyAllData(pregnantEntity, zombieGirl);			
					}
				}
				
				@Override
				protected void initPostMiscarriage() {
					TamableZombieGirl.onPostMiscarriage(pregnantEntity);
				}

				@Override
				protected void initPostPartum() {
					TamableZombieGirl.onPostPartum(pregnantEntity);
				}
			};
		}
		
		public static AttributeSupplier.Builder createAttributes() {
			return getBasicAttributes(0.20);
		}
	}

	public static class TamableZombieGirlP7 extends AbstractTamablePregnantZombieGirl {
		
		public TamableZombieGirlP7(PlayMessages.SpawnEntity packet, Level world) {
			this(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P7.get(), world);
		}

		public TamableZombieGirlP7(EntityType<TamableZombieGirlP7> type, Level world) {
			super(type, world, PregnancyPhase.P7);
			xpReward = 10;
			setNoAi(false);
			setMaxUpStep(0.6f);
		}
		
		@Override
		protected ITamablePreggoMobSystem createTamablePreggoMobSystem() {
			return new PregnantPreggoMobSystemP4<>(this, MinepreggoModConfig.SERVER.getTotalTicksOfHungryP7(), PregnancySystemHelper.TOTAL_TICKS_SEXUAL_APPETITE_P7);
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
						var zombieGirl = MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P8.get().spawn(serverLevel, BlockPos.containing(pregnantEntity.getX(), pregnantEntity.getY(), pregnantEntity.getZ()), MobSpawnType.CONVERSION);		
						PreggoMobHelper.copyAllData(pregnantEntity, zombieGirl);			
					}
				}
					
				@Override
				protected void initPostMiscarriage() {
					TamableZombieGirl.onPostMiscarriage(pregnantEntity);
				}
				
				@Override
				protected void initPostPartum() {
					TamableZombieGirl.onPostPartum(pregnantEntity);
				}		
			};
		}

		public static AttributeSupplier.Builder createAttributes() {
			return getBasicAttributes(0.19);
		}
	}

	public static class TamableZombieGirlP8 extends AbstractTamablePregnantZombieGirl {
		
		public TamableZombieGirlP8(PlayMessages.SpawnEntity packet, Level world) {
			this(MinepreggoModEntities.TAMABLE_ZOMBIE_GIRL_P8.get(), world);
		}
		
		public TamableZombieGirlP8(EntityType<TamableZombieGirlP8> type, Level world) {
			super(type, world, PregnancyPhase.P8);
			xpReward = 10;
			setNoAi(false);
			setMaxUpStep(0.6f);
		}
		
		@Override
		protected ITamablePreggoMobSystem createTamablePreggoMobSystem() {
			return new PregnantPreggoMobSystemP4<>(this, MinepreggoModConfig.SERVER.getTotalTicksOfHungryP8(), PregnancySystemHelper.TOTAL_TICKS_SEXUAL_APPETITE_P8);
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
					// P8 is the last pregnancy stage
				}
				
				@Override
				protected void initPostMiscarriage() {
					TamableZombieGirl.onPostMiscarriage(pregnantEntity);
				}
				
				@Override
				protected void initPostPartum() {
					TamableZombieGirl.onPostPartum(pregnantEntity);
				}		
			};
		}
		
		public static AttributeSupplier.Builder createAttributes() {
			return getBasicAttributes(0.19);
		}
	}
}
