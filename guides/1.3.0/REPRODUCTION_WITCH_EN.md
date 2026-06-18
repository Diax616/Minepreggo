# Reproduction Witch — Minepreggo

> Non-technical documentation for the ReproductionWitch entity.  
> Version: Minecraft Forge 1.20.1

---

## Table of Contents

1. [Intro](#intro)
2. [Variants](#variants)
3. [Where She Spawns](#where-she-spawns)
4. [Combat Behavior](#combat-behavior)
   - [Melee Attack](#melee-attack)
   - [Potion Volley](#potion-volley)
   - [Explosive Spell](#explosive-spell)
   - [Self-Management](#self-management)
5. [Attacks by Target Type](#attacks-by-target-type)
   - [Male Player](#male-player)
   - [Non-Pregnant Female Player](#non-pregnant-female-player)
   - [Pregnant Female Player](#pregnant-female-player)
   - [PreggoMob](#preggomob)
6. [Drops on Death](#drops-on-death)
7. [Attack Summary Table](#attack-summary-table)

---

## Intro

The Reproduction Witch is a hostile entity that attacks players and PreggoMobs with splash potions specifically designed to mess with fertility and pregnancy. Unlike the vanilla witch, her attacks aren't just about dealing damage — depending on her variant and the target's state, she'll try to impregnate, infertilize, accelerate, or directly damage an ongoing pregnancy.

Drops **25 experience points** on death.

---

## Variants

There are two Reproduction Witch variants with completely opposite goals:

### FERTILITY (Fertility Witch)

Her goal is to **cause and accelerate pregnancies**. She throws impregnation potions at non-pregnant females and acceleration or baby duplication potions at pregnant ones. Against male players she has no useful potions, so she falls back to an explosive spell.

### INFERTILITY (Infertility Witch)

Her goal is to **destroy reproductive capacity**. She throws infertility potions at any non-pregnant target and, against pregnant ones, tries to delay or directly damage the pregnancy.

---

## Where She Spawns

The Reproduction Witch only spawns in three biomes. She can spawn in groups of **1 to 2 witches** per spawn event.

| Biome | Frequency | Most common variant | Time restriction |
|-------|:---------:|---------------------|:---------------:|
| **Plains** | Normal | 50% FERTILITY / 50% INFERTILITY | **Full moon only** |
| **Sunflower Plains** | High | 65% FERTILITY / 35% INFERTILITY | Any night |
| **Swamp** | High | 35% FERTILITY / 65% INFERTILITY | Any night |

> In regular plains, the witch **only spawns during a full moon**. In Sunflower Plains and Swamp she has no such restriction and can spawn any dark night.

The most dangerous variant for **pregnant players** (FERTILITY) dominates in Sunflower Plains. The most dangerous for **fertility in general** (INFERTILITY) dominates in the Swamp.

---

## Combat Behavior

The witch has a detection range of **32 blocks** and switches attack modes based on distance to the target.

### Melee Attack

When the target is **within 2 blocks**, she hits directly. Automatically deactivates if she's drinking a potion or charging a spell.

---

### Potion Volley

When the target is **more than 2 blocks away**, she throws **two splash potions simultaneously** with a slight angular spread between them to increase hit probability. The first potion is the main effect for that target (see attacks section). The second is always a **slowness** or **weakness** potion.

If the witch doesn't have a specific potion for the target (for example, against a male player as the FERTILITY variant), the volley becomes generic **damage + poison**.

---

### Explosive Spell

The witch fires a **guided explosive projectile** (`ExplosiveChemiball`) that corrects its trajectory toward the target. It activates in two situations:

- The target is **more than 16 blocks away**
- The target is a **male player** and the witch is the **FERTILITY** variant (at any distance greater than 2 blocks)

While charging the spell, the witch enters a casting state with her own animation and witch particles. If she loses her target and stays passive for **5 seconds**, she automatically cancels the spell.

---

### Self-Management

When she doesn't have a nearby target, the witch can drink potions to keep herself going:

| Situation | Potion she drinks |
|-----------|------------------|
| Underwater without water breathing | Water Breathing |
| On fire without fire resistance | Fire Resistance |
| Health below maximum | Healing |
| Target is far away (>11 blocks) and she doesn't have speed | Speed |

While drinking, she gets a small temporary speed bonus. If her health drops below **60% of maximum** while drinking, she flees from the target until she's far enough away.

---

## Attacks by Target Type

### Male Player

| Variant | Attack |
|---------|--------|
| **FERTILITY** | No specific potion → fires **guided explosive spell** + damage/poison volley |
| **INFERTILITY** | Throws **infertility** potion |

The FERTILITY witch considers male players a secondary target and prioritizes the explosive spell over potions.

---

### Non-Pregnant Female Player

| Variant | Condition | Attack |
|---------|-----------|--------|
| **FERTILITY** | No active impregnation effect | Random impregnation potion (**Zombie** or **Creeper**, 50/50) |
| **FERTILITY** | Already has active impregnation effect | **Slowness** potion (to stop her from escaping) |
| **INFERTILITY** | — | **Infertility** potion |

The FERTILITY witch tries to impregnate the player with a random species. If the impregnation effect is already in progress, she switches to slowing the player down to let the process complete.

---

### Pregnant Female Player

This logic only applies if the pregnancy **isn't in labor or miscarriage**. In those cases, the witch has no specific potions and uses the generic damage and poison volley.

| Variant | Condition | Attack | Probability |
|---------|-----------|--------|:-----------:|
| **FERTILITY** | No active gradual acceleration | **Gradual acceleration** potion | 100% |
| **FERTILITY** | Gradual acceleration already active | **Acceleration** potion | 75% |
| **FERTILITY** | Gradual acceleration already active | **Baby duplication** potion | 25% |
| **INFERTILITY** | — | Pregnancy **delay** potion | 90% |
| **INFERTILITY** | — | Pregnancy **damage** potion | 10% |

> The FERTILITY witch first activates gradual acceleration (an ongoing process) and then can add direct accelerations or try to duplicate the number of babies in the womb. The INFERTILITY witch mainly slows down pregnancy progress, with a small chance of causing direct damage.

---

### PreggoMob

PreggoMobs receive the same effects as players, with one difference in the impregnation potions:

| State | Variant | Attack |
|-------|---------|--------|
| Not pregnant | **FERTILITY** | Random impregnation potion (**any species**) |
| Not pregnant | **INFERTILITY** | **Infertility** potion |
| Pregnant | **FERTILITY** | Same as pregnant player (acceleration / duplication) |
| Pregnant | **INFERTILITY** | Same as pregnant player (delay / damage) |

The difference from players is that non-pregnant PreggoMobs can receive impregnations of **any species** available in the mod, not just Zombie or Creeper.

---

## Drops on Death

Both variants always drop **1 Witch Brain** when killed by a player. The potions they can drop vary by variant and are calculated in **2 independent rolls** per death (each roll may or may not produce an item).

### Fertility Witch

| Item | Probability per roll | Condition |
|------|:-------------------:|-----------|
| Zombie impregnation potion | 35% | Always |
| Impregnation potion | 25% | Always |
| Pregnancy healing potion | 30% | Always |
| Pregnancy acceleration potion | 15% | Always |
| Baby duplication potion | +25% per Looting level | Killed by player with Looting |
| Witch Brain | 1 guaranteed | Killed by player |

> With **Looting III**, the chance to get the baby duplication potion is 75%.

### Infertility Witch

| Item | Probability per roll | Condition |
|------|:-------------------:|-----------|
| Infertility potion | 50% | Always |
| Pregnancy delay potion | 35% | Always |
| Pregnancy damage potion | 10% | Always |
| Eternal pregnancy potion | +25% per Looting level | Killed by player with Looting |
| Witch Brain | 1 guaranteed | Killed by player |

> With **Looting III**, the chance to get the eternal pregnancy potion is 75%.

---

## Attack Summary Table

| Target | FERTILITY variant | INFERTILITY variant |
|--------|------------------|---------------------|
| Male player | Guided explosive spell + damage/poison | Infertility |
| Non-pregnant female player | Impregnation (Zombie or Creeper) / Slowness if already impregnated | Infertility |
| Pregnant female player (normal) | Speeds up pregnancy / duplicates babies | Delays / damages pregnancy |
| Female player in labor / miscarriage | Damage + poison (generic) | Damage + poison (generic) |
| Non-pregnant PreggoMob | Impregnation (any species) | Infertility |
| Pregnant PreggoMob | Speeds up pregnancy / duplicates babies | Delays / damages pregnancy |

---

*Documentation generated from analysis of the Minepreggo source code.*
