package dev.dixmk.minepreggo.world.effect;

import javax.annotation.Nullable;

import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.init.MinepreggoModMobEffects;
import dev.dixmk.minepreggo.init.MinepreggoModSounds;
import dev.dixmk.minepreggo.world.entity.preggo.ITamablePregnantPreggoMob;
import dev.dixmk.minepreggo.world.pregnancy.IPregnancyData;
import dev.dixmk.minepreggo.world.pregnancy.MapPregnancyPhase;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhaseHelper;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class PregnancyDelay extends MobEffect {

    private static final float[][] PERCETANGES_RANGES = {
            {0.25f, 0.3f},
            {0.3f, 0.35f},
            {0.35f, 0.4f},
            {0.4f, 0.45f},
            {0.45f, 0.5f}
		};
	
	public PregnancyDelay() {
		super(MobEffectCategory.HARMFUL, -39220);
	}
	
	@Override
	public boolean isInstantenous() {
		return true;
	}
	
	@Override
	public void applyInstantenousEffect(@Nullable Entity source, @Nullable Entity indirectSource, LivingEntity target, int amplifier, double effectiveness) {
		if (target.level().isClientSide || target.hasEffect(MinepreggoModMobEffects.ETERNAL_PREGNANCY.get())) return;

		if (target instanceof ITamablePregnantPreggoMob handler) {
			apply(handler.getPregnancyData(), getDaysByAmplifier(target.getRandom(), amplifier));
        	PregnancySystemHelper.playSoundNearTo(target, MinepreggoModSounds.getRandomStomachGrowls(target.getRandom()));
		} else if (target instanceof ServerPlayer serverPlayer) {
			serverPlayer.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap ->
				cap.getFemaleData().ifPresent(femaleData -> {
					if (femaleData.isPregnant() && femaleData.isPregnancyDataInitialized()) {
						apply(femaleData.getPregnancyData(), getDaysByAmplifier(target.getRandom(), amplifier));
			        	PregnancySystemHelper.playSoundNearTo(target, MinepreggoModSounds.getRandomStomachGrowls(target.getRandom()));
					}
				})		
			);
		}
	}
	
    private static void apply(IPregnancyData handler, int extraDays) {
		MapPregnancyPhase map = handler.getMapPregnancyPhase();
		PregnancyPhase current = handler.getCurrentPregnancyStage();

		int added = PregnancyPhaseHelper.addDaysToPhase(map, current, extraDays);
		if (added > 0) {
			// Increase overall days to give birth by the added delay
			int currentDaysToBirth = handler.getDaysToGiveBirth();
			handler.setDaysToGiveBirth(currentDaysToBirth + added);
		}
		handler.setMapPregnancyPhase(map);
    }
    
	// Returns a number of days proportional to the total pregnancy days, based on amplifier
	private static int getDaysByAmplifier(RandomSource random, int amplifier) {
		int totalPregnancyDays = MinepreggoModConfig.getTotalPregnancyDays();

		int idx = Math.min(amplifier, PERCETANGES_RANGES.length - 1);
		double minPercent = PERCETANGES_RANGES[idx][0];
		double maxPercent = PERCETANGES_RANGES[idx][1];

		int minDays = Math.max(1, (int)Math.round(totalPregnancyDays * minPercent));
		int maxDays = Math.max(minDays, (int)Math.round(totalPregnancyDays * maxPercent));
		maxDays = Math.min(maxDays, totalPregnancyDays); // Don't exceed total days

		return random.nextInt(minDays, maxDays + 1); // upper bound is exclusive, so +1
	}
}