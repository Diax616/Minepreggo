package dev.dixmk.minepreggo.utils;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.world.entity.preggo.ITamablePreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractTamableCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractTamablePregnantCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableHumanoidCreeperGirlP1;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableHumanoidCreeperGirlP2;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableHumanoidCreeperGirlP3;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableHumanoidCreeperGirlP4;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableHumanoidCreeperGirlP5;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableHumanoidCreeperGirlP6;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableHumanoidCreeperGirlP7;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.AbstractTamablePregnantZombieGirl;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.AbstractTamableZombieGirl;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirlP1;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirlP2;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirlP3;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirlP4;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirlP5;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirlP6;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirlP7;
import dev.dixmk.minepreggo.world.pregnancy.IPregnancyEffectsHandler;
import dev.dixmk.minepreggo.world.pregnancy.IPregnancySystemHandler;

public class DebugHelper {

	private DebugHelper() {}
	
	public static<E extends PreggoMob & ITamablePreggoMob<?>> void showBasicInfo(E preggoMob) {
		MinepreggoMod.LOGGER.debug("BASIC INFO: id={} class={}, hungry={}, hungryTimer={}, isAngry={}",
				preggoMob.getId(), preggoMob.getClass().getSimpleName(), preggoMob.getFullness(), preggoMob.getHungryTimer(), 
				preggoMob.isAngry());		
	}
	
	public static<E extends PreggoMob & ITamablePreggoMob<?> & IPregnancySystemHandler> void showPregnancyInfo(E preggoMob) {
		MinepreggoMod.LOGGER.debug("BASIC PREGNANCY INFO: pregnancyTimer={}, daysByStage={}, daysByStage={}, daysToGiveBirth={}, pregnancyPain={}, "
				+ "pregnancyPainTimer={}, pregnanctSymptom={}, isIncapacitated={}",
				preggoMob.getPregnancyTimer(), preggoMob.getDaysByCurrentStage(), preggoMob.getDaysByCurrentStage(),
				preggoMob.getDaysToGiveBirth(), preggoMob.getPregnancyPain(), preggoMob.getPregnancyPainTimer(),
				preggoMob.getPregnancySymptoms(), preggoMob.isIncapacitated());
	}
	
	public static<E extends PreggoMob & ITamablePreggoMob<?> & IPregnancyEffectsHandler> void showPregnancyP1Info(E preggoMob) {
		MinepreggoMod.LOGGER.debug("PREGNANCY P1 INFO: craving={}, cravingTimer={}, cravingChosen={}",
				preggoMob.getCraving(), preggoMob.getCravingTimer(), preggoMob.getTypeOfCraving());
	}
	
	public static<E extends PreggoMob & ITamablePreggoMob<?> & IPregnancyEffectsHandler> void showPregnancyP2Info(E preggoMob) {
		MinepreggoMod.LOGGER.debug("PREGNANCY P2 INFO: milking={}, milkingTimer={}",
				preggoMob.getMilking(), preggoMob.getMilkingTimer());
	}
	
	public static<E extends PreggoMob & ITamablePreggoMob<?> & IPregnancyEffectsHandler> void showPregnancyP3Info(E preggoMob) {
		MinepreggoMod.LOGGER.debug("PREGNANCY P3 INFO: bellyRubs={}, bellyRubsTimer={}",
				preggoMob.getBellyRubs(), preggoMob.getBellyRubsTimer());
	}
	
	public static<E extends PreggoMob & ITamablePreggoMob<?> & IPregnancyEffectsHandler> void showPregnancyP4Info(E preggoMob) {
		MinepreggoMod.LOGGER.debug("PREGNANCY P4 INFO: horny={}, hornyTimer={}",
				preggoMob.getHorny(), preggoMob.getHornyTimer());
	}
	
	public static<E extends PreggoMob> void check(E entity) {
		
		if (entity.level().isClientSide()) {
			return;
		}
		
		if (entity instanceof AbstractTamablePregnantCreeperGirl<?,?> c) {
			DebugHelper.showBasicInfo(c);	
			DebugHelper.showPregnancyInfo(c);			
			if (c instanceof TamableHumanoidCreeperGirlP7 c7) {
				DebugHelper.showPregnancyP1Info(c7);
				DebugHelper.showPregnancyP3Info(c7);
				DebugHelper.showPregnancyP2Info(c7);
				DebugHelper.showPregnancyP4Info(c7);	
			}
			else if (c instanceof TamableHumanoidCreeperGirlP6 c6) {
				DebugHelper.showPregnancyP1Info(c6);
				DebugHelper.showPregnancyP3Info(c6);
				DebugHelper.showPregnancyP2Info(c6);
				DebugHelper.showPregnancyP4Info(c6);	
			}
			else if (c instanceof TamableHumanoidCreeperGirlP5 c5) {
				DebugHelper.showPregnancyP1Info(c5);
				DebugHelper.showPregnancyP2Info(c5);
				DebugHelper.showPregnancyP3Info(c5);
				DebugHelper.showPregnancyP4Info(c5);	
			}
			else if (c instanceof TamableHumanoidCreeperGirlP4 c4) {
				DebugHelper.showPregnancyP1Info(c4);
				DebugHelper.showPregnancyP2Info(c4);
				DebugHelper.showPregnancyP3Info(c4);
				DebugHelper.showPregnancyP4Info(c4);	
			}
			else if (c instanceof TamableHumanoidCreeperGirlP3 c3) {
				DebugHelper.showPregnancyP1Info(c3);
				DebugHelper.showPregnancyP2Info(c3);
				DebugHelper.showPregnancyP3Info(c3);	
			}
			else if (c instanceof TamableHumanoidCreeperGirlP2 c2) {
				DebugHelper.showPregnancyP1Info(c2);
				DebugHelper.showPregnancyP2Info(c2);	
			}
			else if (c instanceof TamableHumanoidCreeperGirlP1 c1) {
				DebugHelper.showPregnancyP1Info(c1);	
			}
		}
		else if (entity instanceof AbstractTamablePregnantZombieGirl<?,?> z) {
			DebugHelper.showBasicInfo(z);	
			DebugHelper.showPregnancyInfo(z);	
			
			if (z instanceof TamableZombieGirlP7 c7) {
				DebugHelper.showPregnancyP1Info(c7);
				DebugHelper.showPregnancyP2Info(c7);
				DebugHelper.showPregnancyP3Info(c7);
				DebugHelper.showPregnancyP4Info(c7);	
			}
			else if (z instanceof TamableZombieGirlP6 c6) {
				DebugHelper.showPregnancyP1Info(c6);
				DebugHelper.showPregnancyP2Info(c6);
				DebugHelper.showPregnancyP3Info(c6);
				DebugHelper.showPregnancyP4Info(c6);	
			}
			else if (z instanceof TamableZombieGirlP5 c5) {
				DebugHelper.showPregnancyP1Info(c5);
				DebugHelper.showPregnancyP2Info(c5);
				DebugHelper.showPregnancyP3Info(c5);
				DebugHelper.showPregnancyP4Info(c5);	
			}
			else if (z instanceof TamableZombieGirlP4 c4) {
				DebugHelper.showPregnancyP1Info(c4);
				DebugHelper.showPregnancyP2Info(c4);
				DebugHelper.showPregnancyP3Info(c4);
				DebugHelper.showPregnancyP4Info(c4);	
			}
			else if (z instanceof TamableZombieGirlP3 c3) {
				DebugHelper.showPregnancyP1Info(c3);
				DebugHelper.showPregnancyP2Info(c3);
				DebugHelper.showPregnancyP3Info(c3);	
			}
			else if (z instanceof TamableZombieGirlP2 c2) {
				DebugHelper.showPregnancyP1Info(c2);
				DebugHelper.showPregnancyP2Info(c2);	
			}
			else if (z instanceof TamableZombieGirlP1 c1) {
				DebugHelper.showPregnancyP1Info(c1);	
			}
		}			
		else if (entity instanceof AbstractTamableCreeperGirl<?> c) {
			DebugHelper.showBasicInfo(c);	
		}
		else if (entity instanceof AbstractTamableZombieGirl<?> z) {
			DebugHelper.showBasicInfo(z);	
		}	
	}
}
