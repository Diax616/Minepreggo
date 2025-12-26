package dev.dixmk.minepreggo.world.pregnancy;

import javax.annotation.CheckForNull;

public final class PregnancyPhaseHelper {

    private PregnancyPhaseHelper() {}

    /**
     * Returns the next pregnancy phase after the provided one, or null if none.
     */
    @CheckForNull
    public static PregnancyPhase getNextPhase(PregnancyPhase current) {
        PregnancyPhase[] phases = PregnancyPhase.values();
        int idx = current.ordinal();
        if (idx + 1 < phases.length) {
            return phases[idx + 1];
        }
        return null;
    }

    /**
     * Consume up to `days` from the provided map starting at `start` phase and moving forward.
     * Modifies the provided map in place and returns the number of days that were actually applied (consumed).
     */
    public static int consumeDaysFromMap(MapPregnancyPhase map, PregnancyPhase start, int days) {
        int daysLeft = days;
        PregnancyPhase phase = start;
        while (daysLeft > 0 && phase != null && map.containsPregnancyPhase(phase)) {
            int phaseDays = map.getDaysByPregnancyPhase(phase);
            int reduce = Math.min(daysLeft, phaseDays);
            map.modifyDaysByPregnancyPhase(phase, Math.max(0, phaseDays - reduce));
            daysLeft -= reduce;
            if (daysLeft > 0) {
                phase = getNextPhase(phase);
            } else {
                break;
            }
        }
        return days - daysLeft; // applied
    }

    /**
     * Add `days` to the given `phase` inside `map`.
     * If the map doesn't contain the phase this is a no-op and returns 0.
     * Returns the number of days actually added (either `days` or 0).
     */
    public static int addDaysToPhase(MapPregnancyPhase map, PregnancyPhase phase, int days) {
        if (days <= 0) return 0;
        if (!map.containsPregnancyPhase(phase)) return 0;
        int current = map.getDaysByPregnancyPhase(phase);
        map.modifyDaysByPregnancyPhase(phase, current + days);
        return days;
    }
}