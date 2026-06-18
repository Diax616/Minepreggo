# Pregnancy System — Minepreggo

> Non-technical documentation for the mod's pregnancy system.  
> Version: Minecraft Forge 1.20.1

---

## Table of Contents

1. [Ways to Start a Pregnancy](#ways-to-start-a-pregnancy)
   - [Sex Between Players](#1-sex-between-players)
   - [Sex with a Villager](#2-sex-with-a-villager)
   - [Sex with a PreggoMob](#3-sex-with-a-preggomob)
   - [Mob Attack](#4-mob-attack)
   - [Pregnancy Potion](#5-pregnancy-potion)
2. [Pregnancy Phases](#pregnancy-phases)
3. [Intensity System](#intensity-system)
   - [Combined Factor](#combined-factor)
   - [Pregnancy Pains](#pregnancy-pains)
   - [Pregnancy Symptoms](#pregnancy-symptoms)
4. [The Womb and Gestation State](#the-womb-and-gestation-state)
   - [Womb State](#womb-state)
   - [Uterine Irritation](#uterine-irritation)
5. [Physical Effects by Phase](#physical-effects-by-phase)
6. [Labor System — Player](#labor-system--player)
   - [Start Condition](#start-condition)
   - [Stage 1 — Water Breaking](#stage-1--water-breaking)
   - [Stage 2 — Pre-Labor](#stage-2--pre-labor)
   - [Stage 3 — Active Labor (Manual Pushing)](#stage-3--active-labor-manual-pushing)
   - [Stage 4 — Post-Labor and Recovery](#stage-4--post-labor-and-recovery)
7. [Labor System — PreggoMob](#labor-system--preggomob)
   - [Differences from the Player](#differences-from-the-player)
   - [Full Flow](#full-flow-preggomob)
8. [Player vs PreggoMob Comparison Table](#player-vs-preggomob-comparison-table)
9. [Craving](#cravings-and-its-nerfs)
10. [Server Configuration](#server-configuration)

---

## Ways to Start a Pregnancy

There are five different ways for a female entity to get pregnant. Each one has its own conditions and restrictions.

---

### 1. Sex Between Players

The main method. Requires both players to participate in the sex cinematic event.

**Required conditions:**
- The female player must be in her **ovulation window** (OVULATION phase of the menstrual cycle)
- The male player must have **sperm concentration above 4**
- Neither can be infertile
- The female player can't already be pregnant

**Act mechanics:**
- The event lasts **120 ticks** of animation + **60 ticks** of pause
- At the end, `tryCum()` is called, which checks the sperm threshold and triggers the fertilization calculation
- The male's concentration **drops by 4** after each ejaculation
- Both receive **Weakness** and **Hunger** for 1200 ticks after the act
- Both parties' sexual appetite drops by 5 points

**If the female is already pregnant:**
- There's a **60% chance** that sex irritates the womb (cause: PENETRATION)
- If the womb was already at max irritation and the player has the `IRRITATED_WOMB` effect, **labor triggers early**

---

### 2. Sex with a Villager

Works with the same mechanics as player-player sex, but the partner is a Villager. The villager contributes their own fertility values to the calculation.

**How it starts — Food Offering:**

Unlike player sex, courting a villager **isn't triggered by right-clicking**. It's done by offering food in two steps:

1. **The player drops a food item (drop key) that villagers eat** (carrot, potato, bread, wheat, beetroot…). At that point the item gets invisibly tagged with the player's identity, including whether they're pregnant or not.
2. **A villager picks up the tagged item.** If conditions are met, they locate the player, walk over, and initiate the act.

**Conditions the villager must meet when picking it up:**

| Condition | Detail |
|-----------|--------|
| Has a profession | Unemployed (`NONE`) villagers and young ones ignore the offering |
| ≥8 beds for breeding | There must be enough valid beds nearby |
| Can breed (`canBreed`) | Must not be in their breeding cooldown |
| Player within ≤25 blocks | Must locate the player who dropped the item within that radius |

If everything checks out, the villager plays the `VILLAGER_YES` sound, navigates to the player, and starts the encounter. The item tag is **always removed** after being picked up, whether the courtship succeeded or not — if the villager doesn't meet the conditions, the food is just consumed normally.

> The system tags both pregnant and non-pregnant players. This allows a villager to approach an already-pregnant player too (for example, for scenes or to irritate the womb via penetration), not just to start a new pregnancy.

---

### 3. Sex with a PreggoMob

When a PreggoMob's owner initiates the act with her:

- If the mob has the `FERTILE` effect, the cell division factor is multiplied by **×10** (very high chance of identical twins)
- Fertilization follows the same base calculation as between players
- The mob can **ask her owner for sex** automatically every 3600 ticks if she has the `HORNY` symptom active and her owner is within 5 blocks

---

### 4. Mob Attack

Certain hostile mobs can trigger a pregnancy by attacking a female player. This mechanic is **optional** and can be disabled in the server config.

**Compatible mobs:**
| Mob | Type |
|-----|------|
| Zombie | Adult |
| Creeper | Adult |
| Enderman | Adult |
| Slime | Adult |

**Conditions:**
- The player must have a fertility score (`fertilityScore`) **above 0.5**
- One of these two must be true:
  - The player has the `FERTILE` effect active, **or**
  - There's a **60% random chance** the attack goes through
- Result: **1 to 3 babies** of the attacking mob's type

> This method **ignores the menstrual cycle** and normal ovulation conditions.

---

### 5. Pregnancy Potion

The most direct method. Drinking the potion starts a pregnancy **without checking any fertility conditions**:

- Doesn't require being in ovulation
- Doesn't require a male partner
- Ignores both parties' fertility levels
- Pregnancy starts immediately

It's the most reliable way to start a pregnancy regardless of cycle state.

---

## Pregnancy Phases

Pregnancy is divided into **10 phases** (P0 to P9). Each phase determines belly size, available movements, active pains, and when birth can happen.

| Phase | Possible babies in womb | Gameplay changes |
|-------|------------------------|-----------------|
| P0 | — | Start. No visible restrictions yet. |
| P1 | — | Strong morning sickness begins. |
| P2 | — | Nausea continues. Extra weight starts (+10%). |
| P3 | — | First fetal movements. Nausea eases up. |
| P4 | 1 baby | **Birth possible.** Contractions. HORNY symptom active. |
| P5 | 2–3 babies | Longer contractions. More weight (+~15%). |
| P6 | 4–5 babies | **Back pain appears.** Elytra blocked (configurable). |
| P7 | 6–7 babies | Back pain more frequent and intense. |
| P8 | 8–9 babies | Max weight (+27.5%). High knockback resistance (+25%). |
| P9 | 10 babies | Final phase. Random weakness. Maximum movement restriction. |

---

## Intensity System

All pregnancy pains and symptoms are generated by factories that dynamically calculate their severity and duration. Intensity isn't purely random — it's **tied to pregnancy progress**.

### Combined Factor

Before generating any pain or symptom, the game calculates a **combined factor** (value from 0.0 to 1.0) representing how far along the pregnancy is at that moment:

```
Combined Factor =
    global_proximity  × 50%   ← how close to P9 the entity is in absolute terms
  + local_progress    × 30%   ← how far along within its own max phase
  + final_severity    × 20%   ← how many babies are being carried (more = heavier pregnancy)
```

This single factor feeds both the pain and symptom calculations. A single-baby pregnancy at P5 **doesn't produce the same intensity** as an 8-baby pregnancy at P5.

---

### Pregnancy Pains

Pains are temporary events that incapacitate or bother the entity. They have a **severity** (0–4) and a dynamically calculated **duration**.

#### Pain Types

| Pain | Incapacitates | Behavior throughout pregnancy |
|------|--------------|-------------------------------|
| **MORNING_SICKNESS** | Yes | **Inverse** — worst at P1–P3, gets better toward P9 |
| **FETAL_MOVEMENT** | No | Grows progressively (sigmoid curve) |
| **CONTRACTION** | Yes | Grows progressively (sigmoid curve) |
| **BACK_PAIN** | No | Grows gradually (softer sigmoid) |
| **WATER_BREAKING** | No | Fixed duration |
| **PREBIRTH** | Yes | Fixed duration per config |
| **BIRTH** | Yes | Variable duration per baby |
| **POSTBIRTH** | Yes | Calculated based on number of babies |
| **MISCARRIAGE** | Yes | Special event |

#### Causes and Their Multiplier

Each pain has a **cause** that amplifies its severity. Not all causes have the same effect:

| Cause | Severity Multiplier |
|-------|:-------------------:|
| NATURAL (spontaneous) | ×1.00 |
| FOOD (spicy food) | ×1.10 |
| TIRENESS (exhaustion) | ×1.15 |
| SEX | ×1.20 |
| DAMAGE (taking damage) | ×1.40 |

> Taking damage during pregnancy causes the most severe pains. Sex comes in second.

#### How Severity Is Calculated

**For all pains except morning sickness:**
```
severity = round(global_proximity × causeFactor × 4)    ← scale 0 to 4
```

**For morning sickness (inverted):**
```
severity = round((1 - global_proximity) × causeFactor × 4)
```
Morning sickness is the **only pain that gets better over time**. It's worst at the start (P1–P3) and almost disappears by the late phases.

#### How Duration Is Calculated

```
duration = base_duration × pain_curve × (1 + severity × 0.25) × random_variation(±30%)
```

- Each severity point adds **25% more duration**
- A severity 4 pain can last up to **2× longer** than a severity 0 one
- The final duration is capped between **10% and 130%** of the configured base duration

#### Duration Curves by Pain Type

| Pain | Curve Type | Effect |
|------|-----------|--------|
| MORNING_SICKNESS | Asymmetric bell (peak at P3) | Lasts longer in early phases |
| FETAL_MOVEMENT | Sigmoid (S) | Grows slowly at first, speeds up in mid phases |
| CONTRACTION | Sigmoid (S) | Similar to fetal movement |
| BACK_PAIN | Soft sigmoid | Grows more gradually |
| BIRTH / PREBIRTH | Fixed scale 1.0 | No variation by progress |

#### Contractions and Horses

If the player is **riding a saddled horse** during phase P4 or higher, the contraction chance is multiplied by `(phase_ordinal + 4)`. Riding a horse in late phases triggers contractions far more often.

---

### Pregnancy Symptoms

Symptoms are persistent effects with no set duration — they stay active as long as their conditions are met. There are 4 possible symptoms:

| Symptom | Description | Activation |
|---------|-------------|-----------|
| **CRAVING** | Food cravings | From phase P1 (appears first) |
| **MILKING** | Milk production | From phase P3 |
| **BELLY_RUBS** | Urge for belly rubs | From phase P4 |
| **HORNY** | Increased sexual appetite | From phase P5 (appears last) |

#### How a Symptom Activates

Each symptom has a **phase threshold** after which its sigmoid curve starts growing fast. Before the threshold, the symptom can exist but at a very low scale. After it, it grows quickly.

Symptom severity is calculated as:
```
severity = round(global_proximity × symptom_scale × 4)    ← scale 0 to 4
```

Unlike pains, symptoms **have no external cause** — they only depend on pregnancy progress.

#### Cravings — Desired Food Type

The craving type distribution is weighted:

| Type | Probability |
|------|------------|
| SALTY | 35% |
| SPICY | 30% |
| SOUR | 27.5% |
| SWEET | 7.5% |

> **Spicy** food is the second most craved, but it's also a cause of uterine irritation.

---

## The Womb and Gestation State

### Womb State

The womb has three states based on how many babies it holds:

| State | Baby Range | Consequences |
|-------|-----------|--------------|
| **HEALTHY** | 0–3 babies | No additional negative effects |
| **STRESSED** | 4–10 babies | More weight, more restricted movement |
| **OVERLOADED** | More than 10 babies | **Normal birth can't start. The womb tears.** |

The womb's stretch level (StretchedLevel) also increases with the number of babies: NONE → SLIGHT → MODERATE → SEVERE → DANGEROUS.

### Uterine Irritation

Irritation is an internal womb value that goes from **0 to 20**. If it maxes out while the player has the `IRRITATED_WOMB` effect, **labor triggers prematurely**.

#### Irritation Causes and Their Multiplier

| Cause | Multiplier |
|-------|:----------:|
| BLOW (direct hit) | ×1.8 |
| PENETRATION (sex during pregnancy) | ×1.6 |
| ORGASM | ×1.4 |
| SLAPPING | ×1.3 |
| PRESSING | ×1.1 |
| SPICY_FOOD | ×0.9 |

> Direct hits to the abdomen are the most irritating cause. Sex during pregnancy comes in second.

---

## Physical Effects by Phase

Pregnancy progressively changes the entity's physical capabilities.

### Gravity Modifiers (Extra Weight)

| Phase | Extra Weight |
|-------|:-----------:|
| P2 | +10% |
| P3 | +12.5% |
| P4 | +15% |
| P5 | +17.5% |
| P6 | +20% |
| P7 | +22.5% |
| P8 | +25% |
| P9 | +27.5% |

### Knockback Resistance

Starting at P4, the pregnant entity gains knockback resistance:

| Phase | Knockback Resistance |
|-------|:-------------------:|
| P4 | +5% |
| P5 | +10% |
| P6 | +15% |
| P7 | +20% |
| P8 | +25% |
| P9 | +30% |

### Movement Restrictions (Player)

Movement capabilities decrease with each phase:

| Phase | Max jumps | Sprint time (ticks) | Sneak time (ticks) |
|-------|:---------:|:-------------------:|:------------------:|
| P3 | 50 | 4000 | 2800 |
| P4 | 47 | 3800 | 2700 |
| P5 | 44 | 3600 | 2600 |
| P6 | 41 | 3400 | 2500 |
| P7 | 38 | 3200 | 2400 |
| P8 | 30 | 2800 | 2000 |
| P9 | 20 | 2400 | 1600 |

### Equipment Restrictions

- Starting at **P1**, **maternity armor** is required for the torso.
- **Regular leggings** are blocked for the entire pregnancy — only special knee pads are allowed.
- Starting at **P6** (configurable), **elytra** are blocked.

---

## Labor System — Player

Player labor is an **interactive** process split into 4 sequential stages. No stage can be skipped.

```
WATER BREAKING → PRE-LABOR → ACTIVE LABOR (baby by baby) → POST-LABOR → Recovery
```

### Start Condition

Before water breaks, the game checks the womb state:

| Situation | Result |
|-----------|--------|
| Normal womb (≤10 babies) | Water breaks normally |
| Overloaded womb (survival mode) | The womb **tears** instead of starting birth |
| Overloaded womb (creative mode) | Only a chat warning appears. Birth stays **blocked** until resolved. |

---

### Stage 1 — Water Breaking

**What happens:**
- The `WATER_BREAKING` effect is applied for a fixed duration
- **Water particles** appear around the player constantly
- A random pain sound plays
- The father gets a chat message and their presence advancements start being tracked
- If the father is in the same dimension, their distance is recorded (for the "sprint to birth" advancement)

When the water breaking timer expires, **Pre-Labor** starts automatically.

---

### Stage 2 — Pre-Labor (`PREBIRTH`)

**What happens:**
- The player takes light damage
- The `PREBIRTH` effect activates for a configured duration
- **Torso, legs, main hand, and offhand are automatically removed** and drop to the ground
- An alert message is sent to the player and the father
- Water particles continue

> If there's a **Medical Table** claimed nearby, the number of pushes required in the next stage is reduced to **70%** (minimum 5 pushes).

When the `PREBIRTH` timer expires, **Active Labor** begins.

---

### Stage 3 — Active Labor (Manual Pushing)

This stage requires the **player's active participation**. Each baby must be "pushed out" by pressing a key a set number of times.

**Mechanics:**
1. The first baby is taken from the womb and the number of presses needed is calculated
2. An on-screen indicator shows the key to press and the current push level (refreshes every 5 seconds)
3. The player presses the key until reaching the required number
4. When pushes are complete:
   - Light damage is applied to the mother
   - **50 birth particles** appear
   - Birth and water sounds play
   - The baby appears as an **item on the ground** in front of the player
   - The next baby in queue starts its own push cycle
5. When no babies are left, the stage ends and moves to Post-Labor

**Pushes required by species:**

| Species | Base Multiplier | MONSTER Type |
|---------|:--------------:|:------------:|
| Human / Zombie | ×2 | ×1.2 additional if MONSTER |
| Creeper | ×1.2 | ×1.2 additional if MONSTER |
| Slime | ×1.3 | ×1.2 additional if MONSTER |
| Enderman | ×3 | ×1.2 additional if MONSTER |
| Dragon | ×4 | ×1.2 additional if MONSTER |

*(Base: 14 pushes × species multiplier)*

---

### Stage 4 — Post-Labor and Recovery

After delivering the last baby, **post-labor** (`POSTBIRTH`) begins, with a duration calculated based on the total number of babies.

**When post-labor expires, all of the following happen:**

| Action | Detail |
|--------|--------|
| Clothing restored | The player returns to the `DRESSED` state |
| Pregnancy removed | All pregnancy data is reset |
| `REHABILITATION` effect | Server-configured duration. Physical recovery phase. |
| `MATERNITY` effect | Activates the post-birth lactation period. |
| `LUCK` effect | 12000 ticks (visible). Birth bonus. |
| `WEAKNESS` effect | 3600 ticks. Post-birth weakness. |
| Additional light damage | `tryHurt` is called one more time. |
| Medical table released | If one was claimed, it's freed. |
| Jiggle physics reset | The belly physics system returns to its base state. |
| Advancements unlocked | `GIVE_BIRTH`, birth in specific dimension. |
| Message to father | Notified of how many babies were born. |

---

## Labor System — PreggoMob

PreggoMob labor follows the **same 4-stage flow**, but with key differences: it's **fully automatic** (no player input needed) and the mob **disappears** when it's done.

### Differences from the Player

| Aspect | Player | PreggoMob |
|--------|--------|-----------|
| Active labor | Manual push by key | **Automatic by timer** |
| Birth particles | 50 per baby | 30 per baby |
| Birth sounds | Multiple (random + water) | Water sound only |
| Clothing removed | Yes (torso, legs, hands) | Yes (torso, legs, hands) |
| Post-labor fate | Stays alive in recovery | **Disappears from the world** (`discard()`) |
| Post-labor effects | 4 effects (rehab, nursing, luck, weakness) | None (mob doesn't persist) |
| Creative mode | Protects from tearing | No protection |
| Event message | To herself and the father | To the owner only |
| Ask for sex | Not automatic | Yes (if HORNY symptom is active) |
| Pick up items during labor | Yes | **Blocked** when labor starts |
| Break blocks during labor | Yes | **Blocked** when labor starts |

### Full Flow PreggoMob

```
WATER BREAKING
    ↓ (fixed timer)
PRE-LABOR (PREBIRTH)
    ↓ (fixed timer, clothing drops to the ground)
ACTIVE LABOR (BIRTH)
    ↓ Per baby: individual timer calculated by species
    ↓ With Medical Table: duration reduced to 70% (minimum 200 ticks)
POST-LABOR (POSTBIRTH)
    ↓ (timer calculated by number of babies)
DISCARD — the mob disappears from the world
```

> PreggoMob babies appear as items on the ground in front of the mob, exactly like with the player. The owner has to pick them up from the ground.

---

## Player vs PreggoMob Comparison Table

| Feature | Player | PreggoMob |
|---------|--------|-----------|
| Start by sex | Yes (between players, with villager) | Yes (with owner) |
| Start by mob attack | Yes (configurable) | No |
| Start by potion | Yes | No |
| Asks owner for sex | No | Yes (if HORNY) |
| Labor requires input | Yes (manual pushes) | No (automatic) |
| Survives birth | Yes | No (disappears) |
| Has nursing period | Yes (MATERNITY effect) | No |
| Has rehabilitation period | Yes | No |
| Overloaded womb | Blocked in creative, tears in survival | Tears with no exception |
| Notifications | To herself + father | To owner only |

---

## Server Configuration

Server admins can modify pregnancy system parameters:

### Pain Probability per Tick

| Intensity | Probability per tick |
|-----------|:-------------------:|
| LOW | 0.00175 |
| MEDIUM | 0.00225 |
| HIGH | 0.00275 |

### Contraction Duration by Phase (base ticks)

| Phase | Ticks |
|-------|:-----:|
| P4 | 800 |
| P5 | 1000 |
| P6 | 1200 |
| P7 | 1400 |
| P8–P9 | 1600 |

### Fetal Movement Duration (base ticks)

| Phase | Ticks |
|-------|:-----:|
| P3 | 1200 |
| P4 | 1400 |
| P5 | 1600 |
| P6 | 1800 |
| P7 | 2000 |
| P8 | 2100 |
| P9 | 2200 |

### General Options

| Option | Description |
|--------|-------------|
| `enableOverloadedWomb` | Enables or disables the overloaded womb state |
| `enablePregnancyByMobAttack` | Allows or prevents pregnancy from mob attacks |
| `maxPregnancyPhaseToUseElytras` | Max phase before elytra are blocked (default P6) |
| `totalTicksOfPostPregnancyPhase` | Duration of post-birth rehabilitation |
| `totalTicksPostPartumLactation` | Duration of the post-birth nursing period |

---
## Craving System (CRAVING)

### When It Shows Up

The `CRAVING` symptom can activate starting at **phase P1** of the pregnancy. While active, the game assigns a specific craving type tied to the species of the babies being carried. The symptom has an **internal counter** representing how much is left to satisfy — when it hits zero, the symptom goes away.

---

### Satisfying the Craving

To reduce the craving counter, the player needs to eat a food that the pregnancy recognizes as **valid**. After eating it, the game calculates how much the counter drops:

- If the food is **generic human** type → the item's full gratification is applied
- If the food is **species-specific** (non-human) → gratification is reduced by the item's own penalty

```
human food:         full gratification
species food:       gratification × (1 - item penalty)
```

When the counter hits zero after eating, the `CRAVING` symptom automatically deactivates and pregnancy symptoms are re-evaluated.

> Only items that implement the mod's craving system reduce the counter. Eating a valid vanilla food that isn't a recognized craving item has no effect on the counter.

---

### Ignoring the Craving

If the player **eats any invalid food** while `CRAVING` is active, the game **cancels normal Minecraft nutrition** and applies reduced values instead based on the symptom's **current severity**.

The food is still consumed, but it feeds you less than you'd expect.

---

### Severity Penalty

The higher the CRAVING symptom's severity, the bigger the nutrition and saturation reduction when eating foods that don't satisfy the craving:

| Severity | Nutrition reduction | Saturation reduction |
|:--------:|:-------------------:|:--------------------:|
| 0 | −2 points | −10% |
| 1 | −4 points | −20% |
| 2 | −6 points | −30% |
| 3 | −8 points | −40% |
| 4 | −10 points | −50% |

The resulting nutrition never drops below **1 point** even if the penalty exceeds the food's base value.

The player also gets a random chat message (one of 3 variants) letting them know the craving wasn't satisfied and reminding them what food they actually want.

> The higher the craving severity, the more important it is to satisfy it: ignoring it at high severity can make it so you don't recover hunger effectively even if you eat normally.

---


*Documentation generated from analysis of the Minepreggo source code.*
