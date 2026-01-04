# Configuration Timers Guide

This guide explains all the configurable timer settings in the Minepreggo mod. These settings control how long various pregnancy-related effects last and can be customized in the mod's configuration file.

---

## Understanding Ticks

All timers in this guide are measured in **Minecraft ticks**. One Minecraft day (day-night cycle) equals 24,000 ticks.

**Quick Reference:**
- 20 ticks = 1 second
- 1,200 ticks = 1 minute
- 24,000 ticks = 1 Minecraft day (20 minutes real-time)

---

## Core Pregnancy Timers

### Total Pregnancy Days
**Configuration Name:** `totalPregnancyDays`

**Default Value:** 50 days

**Range:** 20 to 2,147,483,647 days

**Description:**
This setting controls how many days a pregnancy lasts from conception to birth. This is the primary timer that determines pregnancy length.

**Calculation:**
- Total pregnancy duration = `totalPregnancyDays` × `totalTickByPregnancyDays` ticks
- With defaults (50 days × 24,000 ticks/day) = 1,200,000 ticks = 50 Minecraft days = ~16.7 real-world hours

**Impact:**
- Lower values = shorter pregnancies (faster progression through phases)
- Higher values = longer pregnancies (more time in each phase, more challenges)

**Recommended Adjustments:**
- Shorter gameplay: 20-30 days
- Standard gameplay: 40-60 days
- Extended roleplay: 70-100+ days

---

### Total Ticks Per Pregnancy Day
**Configuration Name:** `totalTickByPregnancyDays`

**Default Value:** 24,000 ticks

**Range:** 100 to 24,000 ticks

**Description:**
Controls how many in-game ticks constitute one "pregnancy day." At 24,000 ticks, one pregnancy day equals one Minecraft day (approximately 20 real-world minutes).

**Calculation:**
- One pregnancy day = `totalTickByPregnancyDays` ticks of in-game time

**Impact:**
- At 24,000: 1 pregnancy day = 1 Minecraft day
- At 12,000: 1 pregnancy day = 0.5 Minecraft days (pregnancy progresses 2x faster)
- At 6,000: 1 pregnancy day = 0.25 Minecraft days (pregnancy progresses 4x faster)

**Recommended Adjustments:**
- Faster progression: 12,000-18,000 ticks
- Standard progression: 24,000 ticks
- Slower progression: Not recommended (values below 24,000 accelerate time)

**Note:** This timer works in conjunction with `totalPregnancyDays`. Decreasing this value makes pregnancies progress faster in real-time.

---

### Ticks To Start Pregnancy
**Configuration Name:** `ticksToStartPregnancy`

**Default Value:** 4,800 ticks

**Range:** 100 to 24,000 ticks

**Conversion:** 
- Default (4,800 ticks) = 4 real-world minutes of in-game time

**Description:**
The delay between mating (impregnation) and when pregnancy actually begins. This represents a "conception period" where the pregnancy is established but not yet detected.

**Impact:**
- Lower values = immediate pregnancy detection
- Higher values = longer delay before pregnancy shows (pregnancy counter starts later)

**Recommended Adjustments:**
- Immediate: 100-500 ticks
- Short delay: 2,400-4,800 ticks (2-4 real-world minutes)
- Long delay: 6,000-12,000 ticks (5-10 real-world minutes)

**Gameplay Implications:**
- Shorter delays = pregnancies start faster after mating
- Longer delays = more time passes before pregnancy becomes apparent

---

### Maternity Lactation Duration
**Configuration Name:** `totalTicksOfMaternityLactation`

**Default Value:** 1,200 ticks

**Range:** 100 to 2,147,483,647 ticks

**Conversion:**
- Default (1,200 ticks) = 1 real-world minute of in-game time

**Description:**
The duration of the lactation/breast milk phase after pregnancy ends and babies are born. During this period, the pregnant entity (player or mob) can produce milk.

**Impact:**
- Lower values = shorter lactation period
- Higher values = longer lactation period (can produce milk longer)

**Recommended Adjustments:**
- Short lactation: 600-1,200 ticks
- Medium lactation: 2,400-6,000 ticks (2-5 real-world minutes)
- Extended lactation: 12,000+ ticks (10+ real-world minutes)

**Gameplay Implications:**
- Shorter periods = less time to farm milk
- Longer periods = more time to collect multiple bottles of milk

---

### Post Pregnancy Phase Duration
**Configuration Name:** `totalTicksOfPostPregnancyPhase`

**Default Value:** 24,000 ticks

**Range:** 100 to 2,147,483,647 ticks

**Conversion:**
- Default (24,000 ticks) = 1 Minecraft day (20 real-world minutes)

**Description:**
The recovery period after lactation ends. During this phase, the entity recovers from pregnancy before becoming able to become pregnant again.

**Impact:**
- Lower values = faster recovery (can become pregnant again sooner)
- Higher values = longer recovery (must wait longer for next pregnancy)

**Recommended Adjustments:**
- Short recovery: 6,000-12,000 ticks (5-10 real-world minutes)
- Standard recovery: 24,000 ticks (1 Minecraft day)
- Extended recovery: 48,000+ ticks (40+ real-world minutes)

**Gameplay Implications:**
- Shorter periods = continuous rapid pregnancies
- Longer periods = enforces spacing between pregnancies

---

## Pregnancy Symptom Timers

These timers control how often various pregnancy symptoms activate during different pregnancy phases. The system calculates values for each phase based on a base value, decreasing as pregnancy progresses.

### Hunger Timer (Base Value)
**Configuration Name:** `totalTicksOfHungry`

**Default Value:** 4,800 ticks

**Range:** 100 to 24,000 ticks

**Conversion:**
- Default (4,800 ticks) = 4 real-world minutes of in-game time

**Description:**
The base timer for how often the "Hungry" effect activates during Phase 0 (P0). This effect causes increased hunger during early pregnancy.

**Phase Calculations:**
The mod automatically calculates hunger timers for each phase as percentages of P0:
- P0: 100% (4,800 ticks) - baseline
- P1: 85% (4,080 ticks) - more frequent hunger
- P2: 80% (3,840 ticks)
- P3: 75% (3,600 ticks)
- P4: 70% (3,360 ticks)
- P5: 65% (3,120 ticks)
- P6: 60% (2,880 ticks)
- P7: 55% (2,640 ticks)
- P8: 50% (2,400 ticks) - most frequent hunger

**Impact:**
- Lower base value = more frequent hunger (pregnant entities need food more often)
- Higher base value = less frequent hunger

**Recommended Adjustments:**
- Harsh hunger: 2,400-3,600 ticks
- Standard hunger: 4,800 ticks
- Reduced hunger: 6,000-8,000 ticks

---

### Craving Timer (Base Value)
**Configuration Name:** `totalTicksOfCraving`

**Default Value:** 4,800 ticks

**Range:** 100 to 24,000 ticks

**Conversion:**
- Default (4,800 ticks) = 4 real-world minutes of in-game time

**Description:**
The base timer for how often cravings activate during Phase 1 (P1). This controls the frequency of food cravings during pregnancy.

**Phase Calculations:**
The mod automatically calculates craving timers for each phase as percentages of P1:
- P1: 100% (4,800 ticks) - baseline
- P2: 80% (3,840 ticks)
- P3: 75% (3,600 ticks)
- P4: 70% (3,360 ticks)
- P5: 65% (3,120 ticks)
- P6: 60% (2,880 ticks)
- P7: 55% (2,640 ticks)
- P8: 50% (2,400 ticks) - most frequent cravings

**Impact:**
- Lower base value = more frequent cravings (need to satisfy cravings more often)
- Higher base value = less frequent cravings

**Recommended Adjustments:**
- Frequent cravings: 2,400-3,600 ticks (challenging gameplay)
- Standard cravings: 4,800 ticks
- Rare cravings: 6,000-12,000 ticks (relaxed gameplay)

**Gameplay Implications:**
- Shorter intervals = more engagement with craving system
- Longer intervals = less constant management needed

---

### Milking Timer (Base Value)
**Configuration Name:** `totalTicksOfMilking`

**Default Value:** 4,800 ticks

**Range:** 100 to 24,000 ticks

**Conversion:**
- Default (4,800 ticks) = 4 real-world minutes of in-game time

**Description:**
The base timer for how often the "Milking" effect activates during Phase 2 (P2). This controls the frequency of milk production during lactation.

**Phase Calculations:**
The mod automatically calculates milking timers for each phase as percentages of P2:
- P2: 100% (4,800 ticks) - baseline
- P3: 75% (3,600 ticks)
- P4: 70% (3,360 ticks)
- P5: 65% (3,120 ticks)
- P6: 60% (2,880 ticks)
- P7: 55% (2,640 ticks)
- P8: 50% (2,400 ticks) - most frequent milking

**Impact:**
- Lower base value = more frequent milking (produce more milk)
- Higher base value = less frequent milking

**Recommended Adjustments:**
- High milk production: 2,400-3,600 ticks
- Standard production: 4,800 ticks
- Low production: 6,000-12,000 ticks

---

### Belly Rubs Timer (Base Value)
**Configuration Name:** `totalTicksOfBellyRubs`

**Default Value:** 4,800 ticks

**Range:** 100 to 24,000 ticks

**Conversion:**
- Default (4,800 ticks) = 4 real-world minutes of in-game time

**Description:**
The base timer for how often the "Belly Rubs" effect activates during Phase 3 (P3). This controls the frequency of needing/receiving belly rubs during pregnancy.

**Phase Calculations:**
The mod automatically calculates belly rubs timers for each phase as percentages of P3:
- P3: 100% (4,800 ticks) - baseline
- P4: 70% (3,360 ticks)
- P5: 65% (3,120 ticks)
- P6: 60% (2,880 ticks)
- P7: 55% (2,640 ticks)
- P8: 50% (2,400 ticks) - most frequent

**Impact:**
- Lower base value = more frequent need for belly rubs
- Higher base value = less frequent need

**Recommended Adjustments:**
- Frequent need: 2,400-3,600 ticks
- Standard need: 4,800 ticks
- Occasional need: 6,000-12,000 ticks

---

### Horniness Timer (Base Value)
**Configuration Name:** `totalTicksOfHorny`

**Default Value:** 6,000 ticks

**Range:** 100 to 24,000 ticks

**Conversion:**
- Default (6,000 ticks) = 5 real-world minutes of in-game time

**Description:**
The base timer for how often the "Horny" effect activates during Phase 4 (P4). This controls the frequency of sexual attraction during later pregnancy phases.

**Phase Calculations:**
The mod automatically calculates horniness timers for each phase as percentages of P4:
- P4: 100% (6,000 ticks) - baseline
- P5: 65% (3,900 ticks)
- P6: 60% (3,600 ticks)
- P7: 55% (3,300 ticks)
- P8: 50% (3,000 ticks) - most frequent

**Impact:**
- Lower base value = more frequent horniness
- Higher base value = less frequent horniness

**Recommended Adjustments:**
- Frequent horniness: 3,000-4,800 ticks
- Standard: 6,000 ticks
- Reduced: 8,000-12,000 ticks

---

## Symptom Progression Table

This table shows how all symptoms progress through pregnancy phases with default settings:

| Phase | Hunger (ticks) | Craving (ticks) | Milking (ticks) | Belly Rubs (ticks) | Horny (ticks) |
|-------|---|---|---|---|---|
| P0 | 4,800 | - | - | - | - |
| P1 | 4,080 | 4,800 | - | - | - |
| P2 | 3,840 | 3,840 | 4,800 | - | - |
| P3 | 3,600 | 3,600 | 3,600 | 4,800 | - |
| P4 | 3,360 | 3,360 | 3,360 | 3,360 | 6,000 |
| P5 | 3,120 | 3,120 | 3,120 | 3,120 | 3,900 |
| P6 | 2,880 | 2,880 | 2,880 | 2,880 | 3,600 |
| P7 | 2,640 | 2,640 | 2,640 | 2,640 | 3,300 |
| P8 | 2,400 | 2,400 | 2,400 | 2,400 | 3,000 |

---

## How to Edit Configuration

### Finding the Configuration File

The configuration file is typically located at:
```
.minecraft/config/minepreggo-common.toml
```

Or in a modpack:
```
/config/minepreggo-common.toml
```

### Editing Values

Open the configuration file in a text editor and find the "Common" section:

```toml
[Common]
    # Total number of pregnancy days.
    # Range: 20 ~ 2147483647
    totalPregnancyDays = 50
    
    # Total ticks per pregnancy day.
    # Range: 100 ~ 24000
    totalTickByDays = 24000
    
    # Ticks to start pregnancy after mating.
    # Range: 100 ~ 24000
    ticksToStartPregnancy = 4800
    
    # Total ticks of craving for pregnant entities.
    # Range: 100 ~ 24000
    totalTicksOfCraving = 4800
    
    # [Additional values...]
```

### Applying Changes

1. Edit the values as desired
2. Save the file
3. Restart Minecraft for changes to take effect (or reload config if supported)

---

## Configuration Presets

Here are recommended configuration combinations for different playstyles:

### Casual Gameplay (Shorter Pregnancies)
```toml
totalPregnancyDays = 30
totalTickByPregnancyDays = 24000
ticksToStartPregnancy = 2400
totalTicksOfHungry = 6000
totalTicksOfCraving = 6000
totalTicksOfMilking = 6000
totalTicksOfBellyRubs = 6000
totalTicksOfHorny = 8000
totalTicksOfMaternityLactation = 600
totalTicksOfPostPregnancyPhase = 12000
```

### Standard Gameplay (Balanced)
```toml
totalPregnancyDays = 50
totalTickByPregnancyDays = 24000
ticksToStartPregnancy = 4800
totalTicksOfHungry = 4800
totalTicksOfCraving = 4800
totalTicksOfMilking = 4800
totalTicksOfBellyRubs = 4800
totalTicksOfHorny = 6000
totalTicksOfMaternityLactation = 1200
totalTicksOfPostPregnancyPhase = 24000
```

### Roleplay/Extended Gameplay (Longer Pregnancies)
```toml
totalPregnancyDays = 80
totalTickByPregnancyDays = 24000
ticksToStartPregnancy = 6000
totalTicksOfHungry = 3600
totalTicksOfCraving = 3600
totalTicksOfMilking = 3600
totalTicksOfBellyRubs = 3600
totalTicksOfHorny = 5400
totalTicksOfMaternityLactation = 2400
totalTicksOfPostPregnancyPhase = 48000
```

### Challenge Mode (Very Demanding)
```toml
totalPregnancyDays = 100
totalTickByPregnancyDays = 24000
ticksToStartPregnancy = 4800
totalTicksOfHungry = 2400
totalTicksOfCraving = 2400
totalTicksOfMilking = 2400
totalTicksOfBellyRubs = 2400
totalTicksOfHorny = 3600
totalTicksOfMaternity Lactation = 600
totalTicksOfPostPregnancyPhase = 36000
```

---

## Tips for Configuration

1. **Start with Defaults:** Begin with default settings and adjust based on your experience
2. **Make Small Changes:** Change one value at a time to understand its effect
3. **Document Changes:** Keep track of what you modify for troubleshooting
4. **Balance Challenges:** Don't make all symptoms too frequent or rare
5. **Consider Playtime:** Align pregnancy length with how much you play
6. **Test in Creative:** Try changes in creative mode first
7. **Reload Config:** Some servers support `/reload` commands without restarting

---

## Common Configuration Questions

**Q: Can I have very long pregnancies?**
A: Yes! Set `totalPregnancyDays` to 100+ and reduce `totalTickByPregnancyDays` if needed.

**Q: How do I make pregnancy pass quickly?**
A: Lower `totalPregnancyDays` (20-30) and reduce symptom timers to 3,000-4,000 ticks.

**Q: Why are symptoms more frequent in later phases?**
A: The system automatically increases frequency as pregnancy progresses. This is by design.

**Q: Can I disable symptoms?**
A: Set symptom timers to very high values (20,000+) to make them extremely rare.

**Q: What happens if I set invalid values?**
A: The mod will use the nearest valid value or fall back to defaults.

