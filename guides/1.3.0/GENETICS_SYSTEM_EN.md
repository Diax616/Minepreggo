# Baby Genetics System — Minepreggo

> Non-technical documentation for the mod's genetic inheritance system.  
> Version: Minecraft Forge 1.20.1

---

## Table of Contents

1. [Intro](#intro)
2. [When Genetics Apply](#when-genetics-apply)
3. [Main Class: LitterFactory](#main-class-litterfactory)
4. [The Process Step by Step](#the-process-step-by-step)
   - [1. Candidate Generation](#1-candidate-generation)
   - [2. Filtering and Weight Normalization](#2-filtering-and-weight-normalization)
   - [3. Mendelian Sampling](#3-mendelian-sampling)
   - [4. Forced Slots (Slot Conditions)](#4-forced-slots-slot-conditions)
   - [5. Gender Assignment](#5-gender-assignment)
5. [Valid Baby Combinations](#valid-baby-combinations)
6. [Special Case: Pregnancy by Mob Attack](#special-case-pregnancy-by-mob-attack)
7. [Component Glossary](#component-glossary)

---

## Intro

Each baby in a litter has two inheritable traits: its **species** (Human, Zombie, Creeper, Ender, Slime, Villager, Dragon) and its **creature type** (HUMANOID or MONSTER). The genetics system decides which combination each baby gets by mixing the mother's and father's traits using a probability model inspired by Mendelian inheritance.

The result is that a litter **isn't uniform**: babies of different species and types can be born from the same pregnancy, each one calculated independently.

---

## When Genetics Apply

> **Important:** this system **only applies when pregnancy happens through sex** (player-player, player-villager, or player-PreggoMob).

In those cases there's a real father with their own species and type, so mixing both parents' genetics makes sense.

For every other pregnancy method, **genetic mixing isn't used**: the litter is generated directly with the indicated "father's" species, no crossbreeding. This applies to:

- **Pregnancy potions** — species is fixed by the potion.
- **Ender Dragon pregnancy** — always Dragon/Monster.
- **Admin commands** (`/preggo start`, `startat`, etc.) — the operator picks the species.

[Pregnancy by Mob Attack](#special-case-pregnancy-by-mob-attack) is an in-between case (see below).

---

## Main Class: LitterFactory

`LitterFactory` is the **central class** of the `genetics` package. It's the only one called from outside; all other classes are pieces it coordinates internally.

It has two ways to generate a litter:

| Method | Used for | Applies genetics |
|--------|----------|:----------------:|
| `generate(InheritanceContext, …)` | Pregnancy through **sex** | ✅ Yes — mixes mother + father |
| `generate(Species, Creature, …)` | **Direct** pregnancy (potion, dragon, commands) | ❌ No — uses a single fixed species |

The rest of this document describes the **genetics version** (the `InheritanceContext` one), which is what matters for sex-based pregnancies.

---

## The Process Step by Step

For each sex-based pregnancy, `LitterFactory` runs this sequence once to set up the probabilities, then rolls each baby individually.

### 1. Candidate Generation

`CandidateGenerator` builds the possible species+type combinations by crossing both parents, each with a base weight:

| Combination | Base weight | Meaning |
|-------------|:-----------:|---------|
| **Mother's** species and type | 0.30 | Baby comes out like the mother |
| **Father's** species and type | 0.40 | Baby comes out like the father (highest weight) |
| Mother's species + father's type | 0.15 | Mixed cross |
| Father's species + mother's type | 0.15 | Mixed cross |

> The father has the highest individual weight (0.40), so pure paternal inheritance is the most likely of the four combinations. If a combination repeats (e.g. both parents are identical), their weights are **added together**.

---

### 2. Filtering and Weight Normalization

`WeightAssigner` cleans up the candidate list:

1. **Discards impossible combinations** — only valid species+type pairs survive (see [combinations table](#valid-baby-combinations)).
2. **Discards any that don't meet the pregnancy's conditions** (optional inheritance rules, if any).
3. **Normalizes** the remaining weights so they add up to 1.0 (100%).

If no valid candidates remain after filtering, `LitterFactory` falls back to a **fallback plan**: uses the mother's species and type at 100% probability.

---

### 3. Mendelian Sampling

`MendelianSampler` picks a combination for **each baby separately**, rolling against the normalized probabilities (weighted roulette selection). Since each baby is rolled independently, a multi-baby litter can have different compositions.

---

### 4. Forced Slots (Slot Conditions)

Some pregnancies require that **certain litter slots** get a guaranteed combination, bypassing the random roll. After the probability roll, `LitterFactory` replaces random litter positions with these forced combinations (as long as they're valid).

Available forced slot rules:

| Rule | Guaranteed result |
|------|-------------------|
| `FATHER_PAIR` | Father's species and type |
| `MOTHER_PAIR` | Mother's species and type |
| `MOTHER_SPECIES_FATHER_CREATURE` | Mother's species + father's type |
| `FATHER_SPECIES_MOTHER_CREATURE` | Father's species + mother's type |

> If there are more forced slots than babies, the extras are ignored. Non-forced slots keep their probabilistic result.

---

### 5. Gender Assignment

Each baby's gender is decided by a **gender strategy**. The active strategy is `UNIFORM`: every baby has a **50% chance of being male and 50% chance of being female**, regardless of species, type, or the other babies.

---

## Valid Baby Combinations

Only these species+type pairs can exist. Any candidate outside this list is automatically discarded:

| Species | HUMANOID | MONSTER |
|---------|:--------:|:-------:|
| Human | ✅ | ❌ |
| Zombie | ✅ | ❌ |
| Villager | ✅ | ❌ |
| Creeper | ✅ | ✅ |
| Ender | ✅ | ✅ |
| Slime | ✅ | ✅ |
| Dragon | ❌ | ✅ |

> Human, Zombie, and Villager only exist as HUMANOID. Dragon only exists as MONSTER. Creeper, Ender, and Slime support both types, so they're the ones that produce the most genetic variety in a litter.

---

## Special Case: Pregnancy by Mob Attack

Mob attack pregnancies **do use the genetics system** (the `InheritanceContext` version), but with a catch: since there's no father with a real identity, the system automatically adds the **`FATHER_PAIR`** forced slot rule.

This guarantees that **at least one baby in the litter** has the species and type of the attacking mob. The remaining slots are calculated with the normal probability mix between the mother (Human/Humanoid) and the attacking mob.

---

## Component Glossary

| Class | Role |
|-------|------|
| **LitterFactory** | Main class. Orchestrates the entire litter generation process. |
| **InheritanceContext** | Input data container: mother, father, number of babies, conditions, and forced slots. |
| **ParentProfile** | A parent's profile: identity, species, and type. |
| **CandidateGenerator** | Generates the 4 candidate combinations with their base weights. |
| **CandidatePair** | A specific species + type combination. |
| **WeightAssigner** | Filters invalid combinations and normalizes weights to 100%. |
| **MendelianSampler** | Rolls a combination per baby based on probabilities. |
| **SlotCondition** | Rule that forces a combination into a specific litter slot. |
| **InheritanceCondition** | Rule that excludes combinations (e.g. "can't be this species"). |
| **InheritanceConditionRegistry** | Catalog of predefined conditions and forced slots. |
| **GenderStrategy / GenderStrategies** | Defines how gender is assigned (currently UNIFORM 50/50). |
| **BabyGeneticsConstants** | List of valid species+type combinations. |
| **BabyDataResult** | Result for a generated baby (gender, combination, and parent data). |

---

*Documentation generated from analysis of the Minepreggo source code.*
