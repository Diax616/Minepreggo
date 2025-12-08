package dev.dixmk.minepreggo.world.effect;

import javax.annotation.Nullable;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.init.MinepreggoModMobEffects;
import dev.dixmk.minepreggo.world.pregnancy.IPregnancySystemHandler;
import dev.dixmk.minepreggo.world.pregnancy.MapPregnancyPhase;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class PregnancyDelay extends MobEffect {

	public PregnancyDelay() {
		super(MobEffectCategory.HARMFUL, -39220);
	}
	
	@Override
	public boolean isInstantenous() {
		return true;
	}

	// TODO: Extra days is not proporsional to total days of pregnancy, should be reworked later 
	private static int getDaysByAmplifier(RandomSource random, int amplifier) {
		switch (amplifier) {
		case 0: return random.nextInt(5, 16);
		case 1: return random.nextInt(15, 26);
		case 2: return random.nextInt(25, 36);
		case 3: return random.nextInt(35, 46);
		default: return random.nextInt(45, 56);
		}
	}
	
	@Override
	public void applyInstantenousEffect(@Nullable Entity source, @Nullable Entity indirectSource, LivingEntity target, int amplifier, double effectiveness) {
		if (target.level().isClientSide || target.hasEffect(MinepreggoModMobEffects.ETERNAL_PREGNANCY.get())) return;

		int extraDays = getDaysByAmplifier(target.getRandom(), amplifier);

		if (target instanceof IPregnancySystemHandler s) {
			MapPregnancyPhase map = s.getMapPregnancyPhase();
			PregnancyPhase current = s.getCurrentPregnancyStage();
			int currentDays = map.getDaysByPregnancyPhase(current);
			map.modifyDaysByPregnancyPhase(current, currentDays + extraDays);
			s.setMapPregnancyPhase(map);
			
			MinepreggoMod.LOGGER.debug("PregnancyDelay applied to entity {}, added {} days to pregnancy phase {}, New MapPregnancyPhase: {}", target.getDisplayName().getString(), extraDays, current.name(), map);
			
		} else if (target instanceof ServerPlayer serverPlayer) {
			serverPlayer.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap ->
				cap.getFemaleData().ifPresent(femaleData -> {
					if (!femaleData.isPregnant()) return;
					var pregnancySystem = femaleData.getPregnancySystem();
					MapPregnancyPhase map = pregnancySystem.getMapPregnancyPhase();
					PregnancyPhase current = pregnancySystem.getCurrentPregnancyStage();
					int currentDays = map.getDaysByPregnancyPhase(current);
					map.modifyDaysByPregnancyPhase(current, currentDays + extraDays);
					pregnancySystem.setMapPregnancyPhase(map);
					
					MinepreggoMod.LOGGER.debug("PregnancyDelay applied to player {}, added {} days to pregnancy phase {}, New MapPregnancyPhase: {}", target.getDisplayName().getString(), extraDays, current.name(), map);
				})		
			);
		}
	}
}