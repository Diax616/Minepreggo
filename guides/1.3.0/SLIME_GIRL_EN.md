# Slime Girl — Humanoid and Monster

> Non-technical documentation for the two Slime Girl variants.  
> Version: Minecraft Forge 1.20.1

---

## Table of Contents

1. [Intro](#intro)
2. [Size System](#size-system)
   - [Stages and Scale](#stages-and-scale)
   - [Attributes by Stage](#attributes-by-stage)
   - [Jump Power by Stage](#jump-power-by-stage)
3. [Natural Spawn](#natural-spawn)
4. [Taming](#taming)
   - [Grudge Mechanic](#grudge-mechanic)
5. [Humanoid Slime Girl](#humanoid-slime-girl)
   - [Base Attributes](#base-attributes-humanoid)
   - [Inventory](#inventory-humanoid)
   - [Riding](#riding-humanoid)
   - [AI Goals](#ai-goals-humanoid)
6. [Monster Slime Girl](#monster-slime-girl)
   - [Base Attributes](#base-attributes-monster)
   - [Inventory](#inventory-monster)
   - [Riding and Jump System](#riding-and-jump-system-monster)
   - [AI Goals](#ai-goals-monster)
7. [Shared Behavior](#shared-behavior)
   - [Jelly Physics](#jelly-physics)
   - [Block Breaking](#block-breaking)
   - [On Death](#on-death)
8. [Restrictions During Pregnancy](#restrictions-during-pregnancy)
9. [Final Comparison Table](#final-comparison-table)

---

## Intro

The Slime Girl is a PreggoMob that comes in two variants: **Humanoid** (bipedal, walks) and **Monster** (jumpy, rideable with a charge bar). Both share the same size system, the same grudge mechanic, and the same gelatinous base, but differ in combat, movement, inventory, and riding controls.

Unlike the vanilla Slime, the Slime Girl is tameable, has her own inventory, can get pregnant, and her size doesn't determine whether she splits on death — instead she spawns small vanilla Slimes.

---

## Size System

### Stages and Scale

There are four size stages. The hitbox and visual model always scale identically — there's no mismatch between collision and appearance.

| Stage | Visual and hitbox scale | Effect |
|-------|:-----------------------:|--------|
| **NORMAL** | ×1.0 | Base size. Can't be ridden. |
| **BIG** | ×1.35 | Can be ridden. First attribute bonuses. |
| **HUGE** | ×1.65 | Bigger bonuses. Noticeably larger hitbox. |
| **MASSIVE** | ×2.0 | Max size — exactly double NORMAL. Breaks soft blocks. |

> The Slime Girl can only be ridden when she's **BIG or larger**. At NORMAL size, riding isn't available regardless of who tries.

### Attributes by Stage

Each stage above NORMAL stacks modifiers multiplied by the stage number (BIG=1, HUGE=2, MASSIVE=3):

| Attribute | Modifier per stage | BIG (+1) | HUGE (+2) | MASSIVE (+3) |
|-----------|:-----------------:|:--------:|:---------:|:------------:|
| MAX_HEALTH | +30% MULTIPLY_BASE | +30% | +60% | +90% |
| ARMOR | +1.0 ADDITION | +1 | +2 | +3 |
| ATTACK_DAMAGE | +2.0 ADDITION | +2 | +4 | +6 |
| KNOCKBACK_RESISTANCE | +2.0 ADDITION | +2 | +4 | +6 |
| ATTACK_KNOCKBACK | +2.0 ADDITION | +2 | +4 | +6 |

> When shrinking a stage, if current health exceeds the new maximum it's automatically adjusted. Growing doesn't restore health — it only raises the ceiling.

### Jump Power by Stage

Each stage has two jump factors: **normal** (following/idle) and **strong** (chasing/mounted). Values are `(vertical_multiplier, horizontal_power)`:

| Stage | Normal Jump | Strong Jump |
|-------|:-----------:|:-----------:|
| NORMAL | ×1.0 vert / ×0.7 horiz | ×1.2 vert / ×0.8 horiz |
| BIG | ×1.1 vert / ×0.8 horiz | ×1.3 vert / ×0.9 horiz |
| HUGE | ×1.2 vert / ×0.9 horiz | ×1.4 vert / ×1.0 horiz |
| MASSIVE | ×1.3 vert / ×1.0 horiz | ×1.5 vert / ×1.1 horiz |

---

## Natural Spawn

| Variant | Natural spawn | How to get her |
|---------|:------------:|----------------|
| **Monster Slime Girl** | ✅ | Swamp and Mangrove Swamp (see table below) |
| **Humanoid Slime Girl** | ❌ | Purchase only from a **Scientific Illager** |

### Monster Slime Girl Spawn

Spawns exclusively in **Swamp** and **Mangrove Swamp** biomes under these conditions:

- Server difficulty is **not Peaceful**
- The location is dark enough
- There's an **additional probability proportional to moon brightness** — full moon makes spawning more likely, new moon makes it almost zero

Can appear in four variants with different weights and rarity:

| Variant | Weight | Group size | Extra rarity |
|---------|:------:|:----------:|:------------:|
| Monster Slime Girl (normal) | 60 | 1–3 | — |
| Pregnant Monster Slime Girl P3 | 30 | 1 | 1 in 50 |
| Pregnant Monster Slime Girl P5 | 30 | 1 | 1 in 65 |
| Pregnant Monster Slime Girl P7 | 30 | 1 | 1 in 85 |

> Pregnant versions get progressively rarer. A P7 is almost twice as hard to find as a P3.

**Untamed** Slime Girls **despawn** when the player moves far enough away. Tamed ones persist indefinitely.

---

## Food and Taming

The Slime Girl **only accepts items from the slime food group** — regular Minecraft food doesn't work for her. This group includes both vanilla items and mod-exclusive preparations:

| Item | Origin | Effect |
|------|--------|--------|
| Rotten flesh | Vanilla | Feeds |
| Spider eye | Vanilla | Feeds |
| Rotten flesh with chocolate | Mod | Feeds |
| Rotten flesh with salt | Mod | Feeds |
| Rotten flesh with hot sauce | Mod | Feeds |
| Sour rotten flesh | Mod | Feeds |
| Spider eye with chocolate | Mod | Feeds |
| Spider eye with salt | Mod | Feeds |
| Spider eye with hot sauce | Mod | Feeds |
| Sour spider eye | Mod | Feeds |
| **Stuffed Mushroom** | **Mod** | **Feeds and tames** |

Taming a Slime Girl requires exclusively the **Stuffed Mushroom**. All other items in the group only feed her (restore health or satisfy hunger) but don't start the taming process.

### Grudge Mechanic

The grudge system is a social protection layer that **prevents the killer of a Slime Girl from taming others** for a set period of time.

**How it works:**

1. A player kills any Slime Girl
2. At the moment of death, all Slime Girls within a **32-block radius** that witnessed the event register the killer's UUID
3. For **24,000 ticks (1 full in-game day)**, those witnesses reject any taming attempt by the killer
4. When the time expires, the grudge disappears automatically and taming becomes possible again

**System characteristics:**
- Grudges are **saved to the world** (NBT) — they survive server restarts
- They're automatically cleaned up every in-game second to prevent old entries from piling up
- Each Slime Girl maintains her own independent list of offenders with timestamps
- There's no way to speed up grudge expiration through gameplay

> Killing Slime Girls in a dense area can make dozens of them reject the killer simultaneously. The effect spreads to witnesses, not just the direct victim.

---

## Humanoid Slime Girl

The humanoid variant walks on two legs like a bipedal companion. She's frailer than the Monster but easier to control on foot.

### Base Attributes (Humanoid)

| Attribute | Untamed | Tamed |
|-----------|:-------:|:-----:|
| MAX_HEALTH | 30 | 36 |
| ATTACK_DAMAGE | 3 | 3 |
| ARMOR | 1 | 1 |
| ATTACK_KNOCKBACK | 1 | 1 |
| FOLLOW_RANGE | 35 blocks | 35 blocks |

> The Humanoid has no base KNOCKBACK_RESISTANCE — unlike the Monster, she can be knocked back by hits.

### Inventory (Humanoid)

The Humanoid has 5 equipment slots + 6 storage slots:

| Slot | Available |
|------|:---------:|
| Head (HEAD) | ✅ |
| Feet (FEET) | ✅ |
| Main hand (MAINHAND) | ✅ |
| Offhand (OFFHAND) | ✅ |
| Food (FOOD) | ✅ |
| Extra storage | 6 slots |

During pregnancy, the **chest and legs** equipment slots are blocked regardless of phase — she can only equip head, feet, and hands at all times.

### Riding (Humanoid)

**Conditions to ride her:**
- Be her owner
- She must be at BIG, HUGE, or MASSIVE stage (not NORMAL)
- She must not be in aggressive mode

**Mounted behavior:**
- The slime rotates her body to follow the rider's yaw
- Moves with **normal movement keys** (W/A/S/D) at **half her base speed**
- Can climb 1-block steps while being ridden
- The rider's position shifts slightly backward and upward, scaling with body size

**Auto-dismount:** if the Humanoid takes enough damage and her health drops **below 50% of maximum**, the rider is automatically thrown off.

### AI Goals (Humanoid)

**Untamed:** base AI inherited from PreggoMob. No active combat goals of her own.

**When tamed**, these are added:

| Priority | Goal | Description |
|----------|------|-------------|
| 4 | `BreakBlocksToFollowOwnerGoal` | Breaks soft blocks blocking the path to the owner (radius 2, speed 5) |
| 6 | `EatGoal` | Eats food items from the ground (speed 0.6, cooldown 20 ticks) |

**During pregnancy**, both goals are replaced by versions with adjusted parameters and an extra condition: **they automatically pause if the mob is incapacitated** by a pregnancy pain:

| Priority | Goal | Changes vs non-pregnant |
|----------|------|-------------------------|
| 4 | `BreakBlocksToFollowOwnerGoal` | Same radius, speed increased to **7** |
| 6 | `EatGoal` | Cooldown increased to **30** ticks |

---

## Monster Slime Girl

The monster variant jumps like the vanilla Slime but with her own mechanics. She's tougher, more dangerous in combat, and works as a jump mount with a horse-style charge bar.

### Base Attributes (Monster)

| Attribute | Untamed | Tamed |
|-----------|:-------:|:-----:|
| MAX_HEALTH | 40 | 46 |
| ATTACK_DAMAGE | 4 | 4 |
| ARMOR | 2 | 2 |
| KNOCKBACK_RESISTANCE | 1 | 1 |
| ATTACK_KNOCKBACK | 1 | 1 |
| FOLLOW_RANGE | 35 blocks | 35 blocks |

> The Monster has **10 more health points** than the Humanoid and base knockback resistance, making her harder to push around in combat.

### Inventory (Monster)

The Monster has 4 equipment slots + 6 storage slots:

| Slot | Available |
|------|:---------:|
| Head (HEAD) | ✅ |
| Feet (FEET) | ❌ |
| Main hand (MAINHAND) | ✅ |
| Offhand (OFFHAND) | ✅ |
| Food (FOOD) | ✅ |
| Extra storage | 6 slots |

She can't equip anything on her feet. Can only equip items on head and hands. Chest and legs are also blocked during pregnancy.

### Riding and Jump System (Monster)

**Conditions to ride her:**
- Be her owner
- She must be at BIG, HUGE, or MASSIVE stage (not NORMAL)

**Mounted behavior — Horse-style charge bar model:**

The Monster works like a jump horse: the rider sees a **charge bar** on screen. Horizontal movement comes exclusively from jumps — not from walking keys.

There are two movement modes:

---

**Mode 1 — Charged Jump (hold and release space):**

Jump power depends on how long the key was held:

| Charge accumulated | Jump scale | Result |
|:-----------------:|:----------:|--------|
| 0 (no charge) | No jump | Nothing happens |
| 1–89% | 0.40 → 0.99× | Jump proportional to charge |
| 90–100% | 1.00× (max) | Full jump with `StrongJumpFactor × 1.8` horiz / `× 2.2` vert |

---

**Mode 2 — Continuous Jumping (hold W without pressing space):**

Moving forward without charging, the Monster performs automatic jumps at roughly **3× the rate** of her idle bounce, keeping forward momentum without requiring player input.

---

In both modes, the body **faces the direction the rider is looking** before each jump. Each hop travels exactly in the rider's visual direction — no momentum carryover from the previous one.

**Auto-dismount:** if the Monster takes enough damage and her health drops **below 25% of maximum**, the rider is thrown off. The threshold is twice as resistant as the Humanoid's.

**Dynamic jump (no rider):**

When the Monster moves on her own, each jump's power adapts to the distance to her owner:

| Distance to owner | Scale applied | Effect |
|-------------------|:------------:|--------|
| Very close (≤5 blocks) | ×0.4 min | Short jump to avoid overshooting |
| Neutral distance (~8 blocks) | ×1.0 | No change |
| Far away (≥12 blocks) | ×1.5 max | Long jump to catch up |

Between these ranges the scale interpolates continuously. The result is that the Monster never overshoots her owner when close and can catch up when far away.

### AI Goals (Monster)

| Priority | Goal | Active when |
|----------|------|-------------|
| 1 | `SlimeGirlFloatGoal` | Always (floats in water) |
| 2 | `HurtByTargetGoal` | Always (defends herself when hit) |
| 4 | `BreakBlocksToFollowOwnerGoal` | Tamed only |
| 5 | `SlimeGirlAttackGoal` | Jumps toward target and damages on contact |
| 6 | `EatGoal` | Tamed only |
| 8 | `SlimeGirlTamableKeepOnJumpingGoal` | Constant bounce when no other priorities |
| 9 | `LookAtPlayerGoal` | When not mounted, not waiting, and no target |
| 10 | `RandomLookAroundGoal` | Wild or untamed only |
| 7 (target) | `NearestAttackableTargetGoal` | Wild or untamed only |

Once tamed and with no savage mode active, **she doesn't attack players**. The continuous bounce acts as her movement method for following her owner.

---

## Shared Behavior

### Jelly Physics

Both variants have an elastic body deformation animation that activates with each jump:

- **On takeoff:** the model **stretches vertically** (elongation)
- **On landing:** the model **squishes flat** (compression)
- The deformation smoothly returns to rest via interpolation each tick (×0.6 decay)
- Slime particles proportional to the size stage spawn on landing

### Block Breaking

Only available at **MASSIVE** stage. Breakable blocks are those with hardness ≤ 0.6: leaves, dirt, sand, gravel, and equivalents.

| Variant | When it breaks | Affected blocks |
|---------|---------------|-----------------|
| **Humanoid** | On lateral collision with a wall | Full body (excludes the floor) |
| **Monster** | In the air during the jump arc + on landing | Impact floor and first body layer |

Detection checks that the block is on the **outer perimeter** of the hitbox — it doesn't break blocks that are "inside" due to accidental overlap.

### On Death

When any Slime Girl dies, the following happens on the ServerLevel:

1. **Grudge system:** if a player killed her, all Slime Girls within 32 blocks register the killer
2. **Vanilla Slime spawn:** 1 to 3 size-1 vanilla Slimes spawn at her position

---

## Restrictions During Pregnancy

When the Slime Girl is pregnant, riding attempts are evaluated based on the active pain at that moment:

| Active pain | Can be ridden? | Message to owner |
|-------------|:--------------:|-----------------|
| No active pain | ✅ Yes | — |
| MORNING_SICKNESS | ❌ No | "She has morning sickness" |
| BACK_PAIN | ❌ No | "She has back pain" |
| CONTRACTION / MISCARRIAGE | ❌ No | "She has pregnancy pain" |
| WATER_BREAKING / PREBIRTH / BIRTH / POSTBIRTH | ❌ No | "She's in labor" |

In addition, the following and feeding goals **automatically pause** during any pain that incapacitates the mob. Once the pain ends, the goals resume without the owner doing anything.

---

## Final Comparison Table

| Feature | Humanoid Slime Girl | Monster Slime Girl |
|---------|--------------------|--------------------|
| Locomotion | Bipedal (walks) | Jumps (like vanilla Slime) |
| Base health / tamed | 30 / 36 | 40 / 46 |
| Base damage | 3 | 4 |
| Base armor | 1 | 2 |
| Knockback resistance | ❌ | ✅ (1) |
| Equipment slots | Head, feet, hands | Head, hands |
| Riding available | Yes (BIG+) | Yes (BIG+) |
| Mounted controls | WASD keys at half speed | Jump charge bar |
| Dismount threshold | <50% health | <25% health |
| Dynamic jump by distance | N/A | ✅ (scale ×0.4 to ×1.5) |
| Breaks blocks (MASSIVE) | Lateral collision | Landing + jump arc |
| Combat goals | Inherited (no own combat) | `SlimeGirlAttackGoal` |
| Grudge on death | ✅ (32-block radius, 1 day) | ✅ (32-block radius, 1 day) |
| Death spawn | 1–3 size-1 vanilla Slimes | 1–3 size-1 vanilla Slimes |
| Taming | Stuffed Mushroom | Stuffed Mushroom |
| Natural spawn | Darkness + moon phase | Darkness + moon phase |

---

*Documentation generated from analysis of the Minepreggo source code.*
