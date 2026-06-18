# Configuration — Minepreggo

> Non-technical documentation for the mod's configuration options.  
> Version: Minecraft Forge 1.20.1

---

## Table of Contents

1. [Intro](#intro)
2. [Client Configuration](#client-configuration)
3. [Server Configuration](#server-configuration)
   - [Misc Section](#misc-section)
   - [Fertility Section](#fertility-section)
   - [Pregnancy Section](#pregnancy-section)
4. [Automatic Symptom Scaling by Phase](#automatic-symptom-scaling-by-phase)
5. [Time Reference](#time-reference)

---

## Intro

Minepreggo has **two separate config files**:

| File | Scope | Location |
|------|-------|----------|
| **Client** | Personal sound settings per player | `config/minepreggo-client.toml` |
| **Server** | Game rules shared across the whole world | `config/minepreggo-server.toml` |

The **client** file only affects the player who edits it (sounds). The **server** file defines the mechanics for everyone, and in multiplayer it's controlled by the host/operator.

Changes take effect when the config is reloaded. Most time values are expressed in **ticks** (20 ticks = 1 second; 24,000 ticks = 1 full Minecraft day).

---

## Client Configuration

**Sound** section. Personal audio settings.

| Option | Default | Description |
|--------|:-------:|-------------|
| `enableMoans` | `true` | Enables or disables moaning sounds during labor, contractions, etc. |
| `enableBellyGrowls` | `true` | Enables or disables belly growling sounds. |

---

## Server Configuration

### Misc Section

General mob spawn and behavior options.

| Option | Default | Range | Description |
|--------|:-------:|:-----:|-------------|
| `babyCreeperGirlProbability` | `0.1` | 0.0 – 1.0 | Chance that a baby creeper is a girl. |
| `babyZombieGirlProbability` | `0.15` | 0.0 – 1.0 | Chance that a baby zombie is a girl. |
| `enablePreggoMobsTeleportToPlayer` | `false` | — | PreggoMobs teleport to their owner if they get too far away. |
| `enableSpawningHostilPregnantZombieGirls` | `true` | — | Natural spawn of hostile pregnant Zombie Girls. |
| `enableSpawningHostilPregnantMonsterCreeperGirls` | `true` | — | Natural spawn of hostile pregnant Monster Creeper Girls. |
| `enableSpawningHostilPregnantHumanoidCreeperGirls` | `true` | — | Natural spawn of hostile pregnant Humanoid Creeper Girls. |
| `enableSpawningHostilPregnantMonsterEnderWomen` | `true` | — | Natural spawn of hostile pregnant Monster Ender Women. |
| `enableSpawningHostilPregnantMonsterSlimeGirls` | `true` | — | Natural spawn of hostile pregnant Monster Slime Girls. |

---

### Fertility Section

Fertility levels and menstrual cycle. The four available levels are **LOW**, **NORMAL**, **HIGH**, and **VERY_HIGH**.

| Option | Default | Range | Description |
|--------|:-------:|:-----:|-------------|
| `femaleFertility` | `NORMAL` | LOW–VERY_HIGH | Fertility level for female players. |
| `maleFertility` | `NORMAL` | LOW–VERY_HIGH | Fertility level for male players. |
| `femaleMobFertility` | `NORMAL` | LOW–VERY_HIGH | Fertility level for female mobs. |
| `maleMobFertility` | `NORMAL` | LOW–VERY_HIGH | Fertility level for male mobs. |
| `totalTicksPerMenstrualCycleDayForPlayer` | `8000` | 600 – 24000 | Ticks per menstrual cycle day (players). Sleeping advances the cycle. |
| `totalTicksPerMenstrualCycleDayForFemaleMobs` | `8000` | 600 – 24000 | Ticks per menstrual cycle day (female mobs). Sleeping advances the cycle. |
| `enableForceFertilizationEggs` | `false` | — | Allows force-fertilizing eggs during sexual interactions (bypasses part of the RNG). |

---

### Pregnancy Section

The biggest block: pregnancy duration, symptoms, movement restrictions, and birth rules.

#### Duration and General Timing

| Option | Default | Range | Description |
|--------|:-------:|:-----:|-------------|
| `totalPregnancyDays` | `50` | 20 – ∞ | Total number of days a full pregnancy lasts. |
| `totalTickByDays` | `24000` | 100 – 24000 | Ticks per pregnancy day. Sleeping advances the pregnancy. |
| `totalTicksToStartPregnancy` | `2400` | 100 – 24000 | Ticks before pregnancy data initializes after mating. |
| `totalTicksOfPostPregnancyPhase` | `12000` | 100 – ∞ | Duration of the post-birth rehabilitation phase (player and PreggoMob). |
| `totalTicksPostPartumLactation` | `24000` | 100 – ∞ | Duration of the post-birth lactation period. |
| `postBirthLactationCooldown` | `1200` | 100 – ∞ | Lactation cooldown, starts when the lactation period ends. |

#### Pregnancy Healing

| Option | Default | Range | Description |
|--------|:-------:|:-----:|-------------|
| `totalTicksOfPregnancyHealing` | `6000` | 100 – 24000 | How often (in ticks) pregnancy healing is applied. |
| `pregnancyHealingAmountThroughTime` | `25` | 0 – 100 | Pregnancy health points healed per cycle. **0 disables** time-based healing. |
| `pregnancyHealingReductionPerPhase` | `0.05` | 0.0 – 1.0 | Healing reduction per pregnancy phase (5% per phase). **1.0 disables** tiered healing. |

#### Symptoms — Base Values

These are the values for the **starting phase** of each symptom. Later phases are calculated automatically (see [Automatic Scaling](#automatic-symptom-scaling-by-phase)).

| Option | Default | Range | Symptom (base phase) |
|--------|:-------:|:-----:|----------------------|
| `totalTicksOfHungry` | `8400` | 100 – 24000 | Extra hunger for PreggoMobs (base P0). |
| `pregnancyHungryCooldownConfig` | `20` | 10 – 200 | Extra hunger cooldown for players (prevents it from applying every tick). |
| `totalTicksOfCraving` | `4800` | 100 – 24000 | Cravings (base P1). |
| `totalTicksOfMilking` | `3600` | 100 – 24000 | Milk production (base P2). |
| `totalTicksOfBellyRubs` | `3600` | 100 – 24000 | Belly rub urge (base P3). |
| `totalTicksOfHorny` | `6000` | 100 – 24000 | Sexual appetite (base P4). |

#### Movement Restrictions

All three use a pregnancy phase (P0–P9) as the threshold. Setting **P9** disables the restriction.

| Option | Default | Description |
|--------|:-------:|-------------|
| `maxPregnancyPhaseToUseElytras` | `P6` | Max phase for flying with elytra. Above this phase, elytra are blocked. |
| `maxPregnancyPhaseToRideLivingEntities` | `P6` | Max phase for riding living entities (horse, pig…). |
| `maxPregnancyPhaseToMountEntities` | `P8` | Max phase for riding vehicles (boat, minecart…). |

#### Birth and Pregnancy Rules

| Option | Default | Description |
|--------|:-------:|-------------|
| `enablePregnancyByMobAttack` | `true` | Allows getting pregnant when attacked by a mob with reproductive capability (players and PreggoMobs). |
| `enableOverloadedWomb` | `false` | If enabled, exceeding the womb's max capacity **instantly kills the mother**. |

#### Belly Collisions (Experimental)

| Option | Default | Description |
|--------|:-------:|-------------|
| `enableBellyColisionsForPlayer` | `false` | Physical belly collisions for players. **EXPERIMENTAL.** |
| `enableBellyColisionsForPreggoMob` | `false` | Physical belly collisions for PreggoMobs. **EXPERIMENTAL.** |

---

## Automatic Symptom Scaling by Phase

Only the **base phase value** of each symptom is configured. The game calculates the rest of the phases by progressively reducing the duration — symptoms get more frequent as the pregnancy advances.

| Symptom | Base phase | Reduction factor per phase (relative to base value) |
|---------|:----------:|-----------------------------------------------------|
| **Hungry** | P0 (×1.0) | P1 ×0.85 · P2 ×0.8 · P3 ×0.75 · P4 ×0.7 · P5 ×0.65 · P6 ×0.6 · P7 ×0.55 · P8 ×0.5 · P9 ×0.45 |
| **Craving** | P1 (×1.0) | P2 ×0.9 · P3 ×0.85 · P4 ×0.8 · P5 ×0.75 · P6 ×0.7 · P7 ×0.65 · P8 ×0.6 · P9 ×0.55 |
| **Milking** | P2 (×1.0) | P3 ×0.75 · P4 ×0.7 · P5 ×0.65 · P6 ×0.6 · P7 ×0.55 · P8 ×0.5 · P9 ×0.45 |
| **Belly Rubs** | P3 (×1.0) | P4 ×0.7 · P5 ×0.65 · P6 ×0.6 · P7 ×0.55 · P8 ×0.5 · P9 ×0.45 |
| **Horny** | P4 (×1.0) | P5 ×0.65 · P6 ×0.6 · P7 ×0.55 · P8 ×0.5 · P9 ×0.45 |

> A shorter duration means the symptom reactivates faster. That's why cravings, milking, and the rest feel increasingly persistent as the pregnancy progresses.

---

## Time Reference

| Ticks | Real time | Minecraft time |
|------:|-----------|----------------|
| 20 | 1 second | — |
| 1,200 | 1 minute | — |
| 24,000 | 20 minutes | 1 full day |

Keep in mind: **sleeping advances both the menstrual cycle and the pregnancy**, so the effective time can be much shorter than the tick values suggest if the server sleeps frequently.

---

*Documentation generated from analysis of the Minepreggo source code.*
