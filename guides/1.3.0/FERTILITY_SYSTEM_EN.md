# Fertility System — Minepreggo

> Non-technical documentation for the mod's player fertility system.  
> Version: Minecraft Forge 1.20.1

---

## Table of Contents

1. [Intro](#intro)
2. [Female Fertility](#female-fertility)
   - [Menstrual Cycle](#menstrual-cycle)
   - [Cycle Phases](#cycle-phases)
   - [Regularity Types](#regularity-types)
   - [Female Fertility Levels](#female-fertility-levels)
   - [Hormones](#hormones)
3. [Male Fertility](#male-fertility)
   - [Sperm Concentration and Viability](#sperm-concentration-and-viability)
   - [Male Fertility Levels](#male-fertility-levels)
4. [The Fertilization Event](#the-fertilization-event)
   - [Probability Formula](#probability-formula)
   - [Required Conditions](#required-conditions)
   - [FERTILE Effect](#fertile-effect)
5. [Twins and Identical Twins](#twins-and-identical-twins)
6. [Server Configuration](#server-configuration)
7. [Summary Tables](#summary-tables)

---

## Intro

Minepreggo's fertility system simulates a full biological cycle for the mod's female entities. The chance of pregnancy isn't fixed — it depends on multiple simultaneous factors: the female's menstrual cycle phase, the male's sperm quality, both parties' health, and the fertility level assigned to each one.

Both players and compatible mobs (PreggoMobs) are part of this system.

---

## Female Fertility

### Menstrual Cycle

The menstrual cycle is the core of female fertility. It's divided into **cycle days**, and each day equals a configurable number of game ticks (default **12,000 ticks ≈ 10 real minutes**).

The total cycle length varies based on the fertility level and regularity type assigned. Throughout the cycle, the female entity goes through 4 phases in sequence, looping repeatedly.

### Cycle Phases

| Phase | Name | Description |
|-------|------|-------------|
| **MENSTRUATION** | Menstruation | Start of the cycle. Low fertilization chance. Renewal period. |
| **FOLLICULAR** | Follicular | The body prepares for ovulation. Estrogen levels start rising. |
| **OVULATION** | Ovulation | Fertile window. Fertilization chance is at its peak. Eggs are available. |
| **LUTEAL** | Luteal | Post-ovulation phase. If no fertilization happens, the cycle resets. |

> Fertilization **can only happen during the OVULATION phase**. Outside this window, a sexual encounter won't result in pregnancy.

### Regularity Types

Regularity determines how predictable the menstrual cycle is and how many eggs are produced during ovulation.

| Type | Cycle Length | Behavior | Who has it |
|------|-------------|----------|------------|
| **REGULAR** | 28 days (stable) | Predictable cycle, no variation | NORMAL fertility |
| **IRREGULAR** | Variable ±7 days | Cycle can shorten or lengthen each time | LOW fertility |
| **HYPER_OVULATION** | 24 days | Short cycle; produces more eggs per cycle | VERY_HIGH fertility |
| **ANOVULATORY** | Variable | Frequent cycles without ovulation; very reduced fertility | Pathological extremes |

### Female Fertility Levels

The female fertility level determines the full cycle configuration: length, base probability, number of eggs, and how wide the ovulation window is.

---

#### LOW (Low Fertility)

- **Cycle type:** IRREGULAR (±7 day variation)
- **Base fertilization probability:** 10%
- **Eggs per cycle:** 1
- **Ovulation window:** Narrow
- **Player description:** The cycle is unpredictable. Fertilization is hard even during the fertile window. You need the exact timing and good health.

---

#### NORMAL (Normal Fertility)

- **Cycle type:** REGULAR (fixed 28 days)
- **Base fertilization probability:** 50%
- **Eggs per cycle:** 1–3
- **Ovulation window:** Moderate
- **Player description:** The cycle is predictable. Reasonable chance of pregnancy during ovulation. Can produce twins under favorable circumstances.

---

#### HIGH (High Fertility)

- **Cycle type:** REGULAR (26 days)
- **Base fertilization probability:** 75%
- **Eggs per cycle:** 1–2
- **Ovulation window:** Wide
- **Player description:** High chance of getting pregnant. The fertile window lasts longer, giving more room for error. Pregnancies happen more often and faster.

---

#### VERY_HIGH (Very High Fertility)

- **Cycle type:** HYPER_OVULATION (24 days)
- **Base fertilization probability:** 90%
- **Eggs per cycle:** 3–4
- **Ovulation window:** Very wide
- **Player description:** Almost any sexual encounter during the right phase results in pregnancy. Cycles are short and ovulation lasts a good while. Very prone to multiple pregnancies.

---

### Hormones

The system internally tracks two hormones that change throughout the cycle:

| Hormone | Range | Role |
|---------|-------|------|
| **Estrogen** | 0–3 | Rises during the follicular phase. Peaks at ovulation. |
| **Progesterone** | 0–3 | Rises during the luteal phase. Drops at the start of the next cycle. |

These hormones **don't have a direct visible gameplay effect** for the player, but they're part of the internal fertile state calculation.

---

## Male Fertility

### Sperm Concentration and Viability

Male fertility is based on two independent values that together determine **sperm quality**:

| Value | Description |
|-------|-------------|
| **Concentration** | Number of available sperm. Consumed with each ejaculation. |
| **Viability** | How "alive" and functional the sperm are. Directly affects probability. |

Both values recover over time (rest). Recovery speed varies by fertility level.

> **Important note:** For fertilization to be possible, sperm concentration must be **above 4** at the time of the act. If it's below this threshold, ejaculation won't attempt fertilization.

#### Sperm Quality

The final quality is calculated by combining concentration and viability into a single score used in the fertilization formula. Higher quality means a bigger male contribution to the pregnancy probability.

### Male Fertility Levels

Just like female fertility, there are 4 levels that determine the base values and recovery speed.

---

#### LOW (Low Fertility)

- **Base concentration:** Low
- **Base viability:** Low
- **Recovery speed:** Slow
- **Base probability:** 10%
- **Player description:** Low sperm count and quality. Recovery after the act is slow. Contributes little to the fertilization probability.

---

#### NORMAL (Normal Fertility)

- **Base concentration:** Moderate
- **Base viability:** Moderate
- **Recovery speed:** Normal
- **Base probability:** 50%
- **Player description:** Standard contribution. Fertilization is possible with a partner in their fertile window.

---

#### HIGH (High Fertility)

- **Base concentration:** High
- **Base viability:** High
- **Recovery speed:** Fast
- **Base probability:** 75%
- **Player description:** High sperm quality. Recovers quickly between encounters. Significantly raises the odds.

---

#### VERY_HIGH (Very High Fertility)

- **Base concentration:** Very high
- **Base viability:** Very high
- **Recovery speed:** Very fast
- **Base probability:** 90%
- **Player description:** Maximum reproductive capacity. Recovery is almost immediate. Together with a highly fertile female, success is nearly guaranteed.

---

## The Fertilization Event

Every time the right conditions are met (sexual act completed, active ovulation), the game runs a **fertilization event** that calculates whether the encounter results in pregnancy.

### Probability Formula

```
final_probability =
    average(female_base_probability, male_base_probability)
  × average(female_health, male_health)        ← penalized if either is low on health
  × sperm_quality                               ← concentration × male viability
  + ovulation_bonus (+40%)                      ← added if at ovulation peak
```

The result is the final probability that **an egg gets fertilized** at that moment. If the result beats a random number, an embryo is created.

> The ovulation bonus (+40%) is the most significant of all factors. Being exactly at the ovulation peak can turn a low-probability situation into a high one.

### Required Conditions

For the fertilization event to run, **all** of these must be true:

| Condition | Detail |
|-----------|--------|
| The female isn't already pregnant | Can't start a new pregnancy while already carrying one |
| The female is in her fertile window | Must be in the OVULATION phase or close to it |
| Neither party is infertile | If either has zero fertility, no fertilization happens |
| Male concentration is > 4 | Minimum sperm threshold to attempt fertilization |

### FERTILE Effect

The `FERTILE` potion effect significantly changes the fertilization event's behavior:

| Who has it | Effect |
|------------|--------|
| **Female** | Multiplies the cell division factor by ×5 (higher chance of identical twins) |
| **Male** | Multiplies the egg fertilization factor by ×5 (more eggs fertilized at once) |

> For PreggoMobs, the `FERTILE` effect on the mob multiplies the division factor by **×10**, making it even more potent than on players.

---

## Twins and Identical Twins

When multiple eggs are fertilized (possible with HIGH/VERY_HIGH fertility or the FERTILE effect), each embryo also has a chance to split and create **monozygotic (identical) twins**.

### How It Works

1. Each fertilized egg has a **base 4% chance** to split.
2. If it splits, the resulting embryo can split again, but with a **decreasing** probability each generation.
3. This repeats until the probability is too low to continue.

### Baby Cap

The maximum number of babies a womb can hold is **10**. Going above that number puts the womb into an **OVERLOADED** state, which has serious consequences during birth.

### Gestation Types by Number of Babies

| No. of babies | Phase they appear at |
|---------------|----------------------|
| 1 | P4 |
| 2–3 | P5 |
| 4–5 | P6 |
| 6–7 | P7 |
| 8–9 | P8 |
| 10 | P9 |

---

## Server Configuration

Server admins can adjust all fertility system parameters in the server config file. The main configurable values are:

### Cycle Length

```
totalTicksPerMenstrualCycleDayForPlayer = 12000  (default)
```

This value defines how many game ticks equal **one menstrual cycle day**. At 20 ticks per second:

| Ticks per day | Real time (approx.) |
|---------------|---------------------|
| 12,000 | ~10 minutes |
| 24,000 | ~20 minutes |
| 6,000 | ~5 minutes |

Lowering this value speeds up cycles. Raising it makes them slower and more realistic.

### Configurable Fertility Levels

Admins can independently adjust the parameters for each level (LOW, NORMAL, HIGH, VERY_HIGH) for both female and male entities:

**For female fertility:**
- Total cycle length (in days)
- Regularity type (REGULAR, IRREGULAR, HYPER_OVULATION)
- Base fertilization probability
- Min and max number of eggs
- Ovulation window width

**For male fertility:**
- Base sperm concentration
- Base sperm viability
- Daily recovery speed
- Base fertilization probability

### Special Pregnancy Settings

| Option | Description |
|--------|-------------|
| `enableOverloadedWomb` | Allows or prevents the womb from entering an overloaded state |
| `enablePregnancyByMobAttack` | Enables pregnancy from compatible mob attacks |
| `maxPregnancyPhaseToUseElytras` | Max phase after which elytra can't be used (default P6) |

---

## Summary Tables

### Female Fertility Level Comparison

| Level | Cycle Type | Length | Base Prob. | Eggs | Fertile Window |
|-------|-----------|--------|------------|------|----------------|
| LOW | IRREGULAR | ~28 days ±7 | 10% | 1 | Narrow |
| NORMAL | REGULAR | 28 days | 50% | 1–3 | Moderate |
| HIGH | REGULAR | 26 days | 75% | 1–2 | Wide |
| VERY_HIGH | HYPER_OVULATION | 24 days | 90% | 3–4 | Very wide |

### Male Fertility Level Comparison

| Level | Concentration | Viability | Recovery | Base Prob. |
|-------|--------------|-----------|----------|------------|
| LOW | Low | Low | Slow | 10% |
| NORMAL | Moderate | Moderate | Normal | 50% |
| HIGH | High | High | Fast | 75% |
| VERY_HIGH | Very high | Very high | Very fast | 90% |

### Fertilization Event Modifiers

| Factor | Value | Effect |
|--------|-------|--------|
| Ovulation bonus | +40% | Added when at ovulation peak |
| FERTILE effect (female) | ×5 | Cell division multiplier (identical twins) |
| FERTILE effect (male) | ×5 | Egg fertilization multiplier |
| FERTILE effect (PreggoMob) | ×10 | Cell division multiplier on mobs |
| Low health | Penalizes | The average health of both parties reduces the prob. if either is weak |
| Concentration ≤ 4 | Blocks | Fertilization isn't attempted if the minimum threshold isn't met |

---

*Documentation generated from analysis of the Minepreggo source code.*
