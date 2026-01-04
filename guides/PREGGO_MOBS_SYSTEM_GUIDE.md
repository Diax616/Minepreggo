# PreggoMobs System - Complete Guide

## Overview

The PreggoMobs system adds special female creatures to the Minepreggo mod that can become pregnant and have unique interactions. The main creatures are **Zombie Girls** and **Humanoid Creeper Girls**. These mobs have two distinct modes of operation: their normal non-pregnant state and their multi-stage pregnancy system.

---

## Part 1: Base PreggoMob System (Non-Pregnant)

### General Characteristics

PreggoMobs are intelligent, tamable creatures that have complex AI and interaction systems:

- **Ownership:** Can be tamed by players (similar to wolves)
- **Inventory:** Have their own inventory system for storing items
- **Intelligence:** React to their environment and owner's actions
- **Interaction:** Can be right-clicked for various interactions based on situation

### Fullness & Hunger System

**Fullness Meter:**
- PreggoMobs have a fullness value (0-100)
- They need food to maintain fullness
- Fullness decreases over time

**Hunger Levels:**
- When fullness is low (≤ 4), the mob becomes **Angry**
- Angry mobs will:
  - Search for the nearest player every 5 seconds (100 ticks)
  - Target their owner if not in Creative/Spectator mode
  - Attack if hungry enough
  - Stop being angry when fed (fullness > 4)

**Feeding:**
- Players can feed PreggoMobs by giving them food
- Fullness increases when food is consumed
- Well-fed mobs are friendly and cooperative

### Owned vs. Wild Mobs

**Owned Mobs (Tamed):**
- Have a specific owner (player)
- Won't attack their owner
- Can be right-clicked for interactions
- More docile and cooperative
- Can carry items in inventory
- Respond to owner's actions

**Wild/Monster Mobs (Untamed):**
- Spawn naturally in the world
- Will attack players
- Can be tamed by the right interactions
- Convert to owned version upon successful taming

### Mob Variants

**Zombie Girls:**
- Appear as zombie humanoids
- Can transform between monster (untamed) and tamable (owned) forms
- Spawn naturally in dark areas
- Convert to tamable form when impregnated

**Humanoid Creeper Girls:**
- Appear as creeper-shaped humanoids
- Similar taming and transformation mechanics as Zombie Girls
- More rare spawning
- Convert to tamable form when impregnated

**Ender Girls:**
- Ender-based humanoid creatures
- Same mechanics as other PreggoMobs

---

## Part 2: Pregnancy System

When a PreggoMob becomes pregnant, it enters a detailed pregnancy progression system with 9 stages (P0 through P8).

### How Pregnancy Begins

**Three Ways to Become Pregnant:**

1. **Impregnation Potion**
   - Owner gives the mob an impregnation potion
   - Potion effect expires → pregnancy triggered
   - Mob converts to tamable form (if wild)
   - Immediately enters P0 pregnancy stage

2. **Natural Sex with Male Player**
   - Male player and female mob interact
   - Only if both are interested (sexual appetite/horny enough)
   - Can produce human babies or babies of the mother's species
   - Mob stays in her current species form

### Pregnancy Progression System

The pregnancy duration is **always the same** (configured number of days), regardless of how many babies the mother is carrying. What changes based on baby count is the **number of pregnancy stages the mother must go through** before giving birth.

**How It Works:**

The total pregnancy days are divided equally among the stages needed. More babies = more stages needed = shorter duration per stage.

**Examples:**

If total pregnancy is 40 days:

- **1 baby:** Ends at **P4** (4 stages: P1, P2, P3, P4) = ~10 days per stage
- **2 babies:** Ends at **P5** (5 stages: P1, P2, P3, P4, P5) = ~8 days per stage
- **3 babies:** Ends at **P6** (6 stages) = ~6.7 days per stage
- **4 babies:** Ends at **P7** (7 stages) = ~5.7 days per stage
- **5+ babies:** Ends at **P8** (8 stages: P1-P8) = ~5 days per stage

**Key Point:** A mother carrying 5 babies doesn't have a longer pregnancy than a mother with 1 baby—they both take 40 days. The difference is that the mother with 5 babies progresses through more pregnancy stages in the same timeframe, making each stage shorter and more compressed.

### Pregnancy Stages (P0 to P8)

#### **Stage P0: Early Pregnancy (Minimal Effects)**

**Duration:** Configurable (e.g., 5-10 days)

**What Happens:**
- Pregnancy is initialized
- No visible symptoms yet
- Mob can still perform normal activities
- No cravings or lactation
- Owner is notified pregnancy began
- Mob's body shows no changes

**Interactions:**
- Owner can still feed the mob
- Right-click only triggers belly rubs (always fails - belly too small)
- Mob continues normal behavior

**End Condition:**
- Days passed = configured days per stage
- Automatically advances to P1

---

#### **Stage P1: Early Pregnancy (Cravings Begin)**

**Duration:** Configurable days

**What Happens:**
- Craving system activates
- Morning sickness begins (random)
- Mob becomes uncomfortable
- Belly starts showing slight changes

**New Mechanics:**

1. **Cravings:**
   - Specific food items crave over time
   - Owner can feed special craving items to satisfy
   - Satisfied cravinggs reduce discomfort
   - If cravings not satisfied, mob gets angry

2. **Morning Sickness:**
   - Random nausea effects
   - Probability: Low (20% chance each tick when evaluating)
   - Lasts for 6 seconds when triggered
   - Cause discomfort

**Interactions:**
- Right-click with appropriate food → satisfy craving (SUCCESS)
- Right-click with wrong food → mob gets angry (ANGRY)
- Belly rubs still fail (belly not large enough)

**Mood:**
- More irritable than P0
- Gets angry if hungry
- Gets angry if cravings not satisfied

---

#### **Stage P2: Milk Production Begins**

**Duration:** Configurable days

**What Happens:**
- Lactation system activates
- Milk production begins
- Milking symptom develops
- Morning sickness continues but more frequent
- Belly becomes more noticeable

**New Mechanics:**

1. **Milking:**
   - Milk builds up over time (every ~30-60 seconds depending on config)
   - Reaches maximum level when "too full"
   - Owner can milk the mob with a glass bottle
   - Creates milk item (species-specific: Zombie milk, Creeper milk, etc.)
   - Reduces milk level when milked

2. **Lactation Symptom:**
   - When milk reaches activation threshold (50+ out of 100)
   - Mob shows discomfort from full milk
   - Cannot wear certain chest armor while lactating

**Interactions:**
- Right-click with glass bottle → collect milk (SUCCESS)
- Can satisfy cravings with appropriate food
- Belly rubs still fail

**Mood:**
- Gets angry if milking level is too high (≥ 80)
- Still gets angry if hungry or cravings unsatisfied
- More irritable overall

---

#### **Stage P3: Visible Pregnancy (Belly Shows)**

**Duration:** Configurable days

**What Happens:**
- Belly becomes very noticeable
- Fetal movements begin
- Belly rubbing available
- Morning sickness reaches medium intensity
- Mob moves slower
- Armor restrictions tighten

**New Mechanics:**

1. **Fetal Movements (Kicking):**
   - Random fetal movements
   - Probability: Low-Medium (15% chance)
   - Lasts ~8 seconds when triggered
   - Visible/audible effects

2. **Belly Rubs:**
   - Owner can right-click to rub belly
   - Provides comfort and reduces irritability
   - Builds belly rubs meter
   - Belly rubs decrease stress

3. **Audio Effects:**
   - Stomach growling sounds
   - Different sounds based on pregnancy pain

**Interactions:**
- Right-click with glass bottle → collect milk
- Right-click with food → satisfy cravings
- Right-click empty → rub belly and reduce stress (if belly rubs available)

**Armor Restrictions:**
- Chest armor becomes unwearable (belly too large)
- Automatically removed and dropped
- Some special maternity armor might work

**Mood:**
- Very irritable
- Gets angry if starving
- Gets angry if cravings peak or milk too full
- Gets angry if belly needs rubbing

---

#### **Stage P4: Late Pregnancy (Near Birth)**

**Duration:** Configurable days

**What Happens:**
- Very large belly
- Birth is imminent
- Horny/sexual appetite system activates
- Contractions begin
- Labor will start when stage 4 is complete

**New Mechanics:**

1. **Horny/Sexual Appetite:**
   - Sexual appetite builds up over time
   - When reaches peak (100), HORNY symptom activates
   - Mob wants to breed/have sex
   - Increases risk of requesting sex from owner

2. **Contractions:**
   - Random contractions as labor approaches
   - Higher probability than fetal movements
   - Stronger pain effects
   - Lasts ~12 seconds
   - Chest armor automatically dropped when contractions occur

3. **Sex Requests:**
   - When horny enough, mob periodically requests sex from owner
   - Request sent every ~3 minutes (3600 ticks)
   - Only if close to owner and not aggressive
   - Request appears as screen prompt

**Interactions:**
- Right-click with glass bottle → collect milk
- Right-click with food → satisfy cravings  
- Right-click empty → rub belly
- Mob may initiate sex request to owner

**Armor:**
- Chest and leg armor removed and dropped
- Cannot wear any protective gear

**Mood:**
- Extremely irritable
- Gets angry easily
- Gets angry if milk/cravings/belly rubs peak
- Gets angry if sexual needs not met

**End of Stage 4:**
- Water breaks automatically
- Labor begins

---

#### **Stages P5-P8: Full Term Pregnancy**

**Stages P5, P6, P7, P8** follow the same mechanics as P4 with increasing severity:

- More contractions
- More sex requests
- More discomfort
- All previous systems remain active
- Each stage has different configuration timers

**At End of P8 (or appropriate final stage for baby count):**
- Labor begins when stage is complete
- Water breaks
- Birth sequence starts

---

### Pregnancy Pain System

PreggoMobs experience different types of pregnancy pain:

| Pain Type | Stage | Duration | Effect |
|-----------|-------|----------|--------|
| Morning Sickness | P1+ | 6 seconds | Nausea visual effect |
| Fetal Movement | P3+ | 8-12 seconds | Kicking sensation, sound |
| Contraction | P4+ | 12+ seconds | Strong pain, dropped armor |
| Water Breaking | P4 end | ~10 seconds | Labor initiated |
| Pre-Birth | Labor | ~20 seconds | Extreme pain before birth |
| Birth | Labor | ~30 seconds | Giving birth sequence |

### Pregnancy Effects & Symptoms

**Available Pregnancy Symptoms:**
- **Craving** - P1+: Food cravings
- **Milking** - P2+: Milk production
- **Belly Rubs** - P3+: Needs comfort
- **Horny** - P4+: Sexual needs

**Pregnancy Health:**
- Mobs have pregnancy health (similar to players)
- Taking damage reduces pregnancy health
- If health reaches 0 → **Miscarriage**
- Miscarriage takes ~40 seconds to complete
- Dead fetuses are dropped as items
- Miscarriage triggers post-miscarriage recovery

---

### Birth & Post-Birth

#### **Water Breaking**
- Signals approaching birth
- Creates particle effects
- Takes ~10 seconds
- Automatically transitions to Pre-Birth

#### **Pre-Birth Phase**
- Labor intensifies
- Extreme pain effects
- Mob cannot move well
- Takes ~20 seconds
- Transitions to actual birth

#### **Birth Phase**
- Actual delivery occurring
- Takes ~30 seconds
- Babies generated and given to mob or dropped
- Post-partum immediately triggered after

#### **Post-Partum (Post-Pregnancy Phase)**
- Mob receives:
  - **Depression** effect (sad mood)
  - **Maternity** effect (recovery status)
  - **Weakness** effect (3 minutes - temporary)
  - Luck boost (10 minutes - positive effect)

**Duration:** Configured amount of time (e.g., 24 hours)

**During Post-Partum:**
- Lactation may continue
- Milk slowly decreases
- Mob recovers from pregnancy
- Fertility rebuilds
- Cannot become pregnant again
- All pregnancy effects removed when complete

---

### Right-Click Interaction System

All interactions with pregnant mobs happen through right-clicking with specific items:

**With Glass Bottle (P2+):**
- If milk level ≥ minimum threshold
- Result: SUCCESS - milk collected, milk level decreases
- Requires pregnant mob in valid state

**With Food (P1+):**
- If mob has active craving symptom
- If food matches current craving → SUCCESS (craving satisfied)
- If food doesn't match → ANGRY (mob gets mad)
- Requires correct food type

**Empty Hand (P3+):**
- Right-click with nothing in hand
- If belly rubs available → rub belly (SUCCESS)
- Reduces stress and belly rubs meter
- Updates mood

**Belly Rubs Fail (P0-P2):**
- Belly too small in early stages
- Right-click registers but accomplishes nothing
- Play sound effect only

---

## Part 3: Advanced Features

### Species-Specific Mechanics

**Zombie Girls:**
- Produce zombie milk
- Zombie babies from breeding
- Human babies from human partner
- Require zombie-compatible items for breeding

**Creeper Girls:**
- Produce creeper-based abilities
- Creeper babies from breeding
- Human babies from human partner
- May have creeper-specific abilities

**Ender Girls:**
- Produce ender-based abilities
- Ender babies from breeding
- May have teleportation-related mechanics

### Ownership System

**Getting a Tame:**
- Wild mobs can be tamed through interactions
- Feeding is often first step
- Successful interactions increase taming
- Full taming grants ownership

**Owned Mob Benefits:**
- Won't attack owner
- Follow owner's commands
- Can breed with owner
- Store items in inventory
- Respond to interactions

### Inventory Management

**Storage:**
- PreggoMobs have inventory slots
- Can carry items (food, milk, etc.)
- Items stored when picked up
- Can retrieve items later

**Food Storage:**
- Special food slots separate from inventory
- Automatically consume when hungry
- Owner can stock food for mob

---

## Configuration & Customization

Most timing values are configurable:

- **Pregnancy duration** (days total)
- **Days per stage** (calculated automatically)
- **Craving timer** (how often cravings increase)
- **Milking timer** (milk production rate)
- **Belly rubs timer** (comfort recharge rate)
- **Horny timer** (sexual appetite build rate)
- **Birth timer** (labor duration)
- **Post-partum duration** (recovery time)

All these can be customized in the mod config file.

---

## Summary of PreggoMob Lifecycle

1. **Wild Mob Spawns** → Monster form, not owned
2. **Taming/Ownership** → Can be tamed through feeding/interactions
3. **Impregnation** → Via potion or breeding
4. **Pregnancy P0-P3** → Growing, developing cravings/lactation
5. **Pregnancy P4** → Near birth, hormonal changes, sex requests
6. **Labor & Birth** → Water breaks, pre-birth, actual birth
7. **Post-Partum** → Recovery phase with temporary effects
8. **Ready Again** → Can breed again after recovery

---

## Practical Player Tips

1. **Keep Mobs Fed:** Always maintain food supply to prevent anger
2. **Satisfy Cravings:** Feed requested foods to keep mob happy
3. **Regular Milking:** Collect milk regularly to prevent discomfort
4. **Belly Rubs:** Rub bellies in late pregnancy to manage stress
5. **Prepare for Birth:** Clear area and ensure owner is nearby during labor
6. **Post-Birth Care:** Milk continues after birth, manage lactation
7. **Breeding Strategy:** Plan pregnancies based on desired babies
8. **Variety:** Use different potion types for different species babies

---
