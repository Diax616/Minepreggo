# Milk and Potion Effect Mechanics Guide (AI-generated content)

This guide explains how milk works in the Minepreggo mod, which effects cannot be removed by milk consumption, and the special properties of breast milk items.

---

## Overview of Milk

Milk is a consumable item that can remove unwanted potion effects from the player. In Minecraft, milk is typically used to cure potion effects. The Minepreggo mod enhances milk mechanics with pregnancy-aware effect removal systems.

### Types of Milk

The mod includes several varieties of milk:

1. **Standard Milk Bucket** - Regular Minecraft milk bucket (enhanced by the mod)
2. **Human Breast Milk Bottle** - Special milk produced during pregnancy and post-partum lactation
3. **Zombie Breast Milk Bottle** - Special milk from pregnant zombie creatures
4. **Creeper Breast Milk Bottle** - Special milk from pregnant creeper creatures

### Basic Milk Mechanics

When milk is consumed:
- Most potion effects are removed from the player
- The item leaves behind an empty bottle (or empty bucket for milk buckets)
- Health status and hunger are not affected
- The effect removal happens instantly

---

## What Milk CAN Remove

### Non-Pregnant Players

When a non-pregnant player drinks milk, **all female-related effects** are removed EXCEPT:
- Any effect marked as a "female effect" (pregnancy-related effects)

This means standard potion effects like Poison, Weakness, Nausea, etc., are all removed normally.

### Pregnant Players - Phase 1

When a pregnant player in Phase 1 drinks milk, the following effects **ARE removed**:
- Poison
- Weakness
- Nausea
- Speed
- Slowness
- Strength
- Haste
- Mining Fatigue
- Resistance
- Fire Resistance
- Water Breathing
- Invisibility
- Blindness
- Night Vision
- Hunger
- Saturation
- Glowing
- Levitation
- Luck
- Bad Luck
- Slow Falling
- Wind Charged
- Weaving

In other words: any effect that is **NOT** a pregnancy-related effect.

### Pregnant Players - Phase 2 and Later

When a pregnant player in Phase 2, 3, or later drinks milk, even more restrictions apply:
- The same non-pregnancy effects listed above are removed (primary pregnancy effects protected)
- Additionally, **secondary pregnancy effects are also protected** from removal
- Only general status effects are removed

---

## What Milk CANNOT Remove

### Protected Pregnancy Effects

Milk **cannot remove** the following categories of effects:

#### Primary Pregnancy Effects

These are core pregnancy-related effects that milk cannot remove under any circumstances:

- **Birth Effect** - Indicates imminent birth/delivery
- **Pre-Birth Effect** - Early stage of labor
- **Contraction Effect** - Labor contractions
- **Miscarriage Effect** - Indicates miscarriage in progress
- **Fertility Effect** - Fertility/breeding status
- **Pregnant Status Effects** - The visible pregnancy indicators

#### Secondary Pregnancy Effects (Phase 2+)

When pregnancy reaches Phase 2 or beyond, these additional effects cannot be removed:

- **Morning Sickness** - Nausea during pregnancy
- **Craving** - Food cravings specific to pregnancy
- **Lactation** - Milk production during pregnancy and post-partum
- **Maternity** - Post-pregnancy maternity status
- **Depression** - Postpartum depression
- **Fetal Movement** - Baby movement sensations
- **Belly Rubs** - Response to physical belly interaction
- **Horny** - Reproductive drive
- **Fertile** - Enhanced fertility period

### Female-Specific Effects

The mod tracks certain effects as "female effects":
- Any effect specifically tied to female characters
- Pregnancy-related effects (cannot be removed by any type of milk)
- Post-pregnancy/postpartum effects

---

## How Effect Removal Works

### Decision Logic

When milk is consumed, the system follows this logic:

1. **Check if player is pregnant and has active pregnancy system**
   - YES: Use pregnancy-phase-based removal rules
   - NO: Use standard female-effect-based removal rules

2. **If pregnant:**
   - Check current pregnancy phase
   - If Phase 1: Remove only non-pregnancy effects
   - If Phase 2+: Remove non-pregnancy and non-secondary-pregnancy effects

3. **If not pregnant:**
   - Remove all non-female effects
   - Keep all female-related effects intact

### Comparison to Standard Milk Bucket

Standard Minecraft milk bucket behavior is modified:
- **Original:** Removes all potion effects indiscriminately
- **Modified:** Respects pregnancy-related effect protections
- **Additional:** Works with special breast milk bottles with bonus effects

---

## Special Breast Milk Properties

### Human Breast Milk Bottle

The breast milk bottles have special properties beyond standard milk:

**Special Effect:** Adds Regeneration Potion effect temporarily
- Duration: 60 ticks (3 seconds)
- Level: 1 (basic regeneration)
- This occurs before the standard effect removal

**Additional Properties:**
- Provides slight nutrition when consumed (3 nutrition points)
- Uncommon rarity
- Can be stacked up to 16 bottles
- Uses drinking animation (like potions)
- Leaves behind empty glass bottle after use

### Zombie and Creeper Breast Milk

These variants have similar properties to human milk:
- Provide nutrition when consumed
- Uncommon rarity
- Can be stacked
- Use drinking animation
- Leave empty glass bottles after use

---

## Obtaining Breast Milk

### Human Breast Milk

**During Pregnancy:**
- Cannot be obtained during pregnancy itself
- The mother does not produce milk while pregnant

**Post-Partum (After Birth):**
- Mothers enter a post-partum phase with lactation
- Use a glass bottle to collect human breast milk
- Requires specific lactation level to be reached
- Each collection reduces available lactation

**Collection Process:**
- Right-click with glass bottle in hand
- Must have sufficient lactation available
- Produces a human breast milk bottle
- Lactation decreases with each collection

### Creature Breast Milk

- Obtained from pregnant zombie and creeper mobs
- Available during their post-partum lactation phase
- Similar collection mechanics to human milk

---

## Practical Usage

### Removing Unwanted Effects

**Scenario 1: Non-Pregnant Player**
- Drank poison potion
- Other permanent effects active (e.g., strength)
- Drink milk → Removes poison and strength
- Pregnancy effects (if any) remain untouched

**Scenario 2: Pregnant Player (Phase 1)**
- Has weakness effect from mob attack
- Has pregnancy effects from current phase
- Drink milk → Removes weakness
- Pregnancy effects remain (they are protected)

**Scenario 3: Pregnant Player (Phase 2+)**
- Has morning sickness (secondary pregnancy effect)
- Has regular poisons from mobs
- Drink milk → Removes poison only
- Morning sickness remains (secondary pregnancy effects are protected in Phase 2+)

### Strategic Considerations

1. **During Pregnancy:** Milk mainly removes non-pregnancy effects, useful for combat scenarios
2. **Post-Pregnancy:** Milk still protects lactation and maternity effects from removal
3. **Health Management:** Milk doesn't restore health, only removes effects
4. **Transition Periods:** When moving from one pregnancy phase to another, protection changes

---

## Why Effects Are Protected

The pregnancy effect protection system exists to:

1. **Maintain Immersion** - Pregnancy effects cannot be casually dismissed
2. **Game Balance** - Prevents trivialization of pregnancy progression
3. **Important Status** - Ensures players remain aware of critical pregnancy phases
4. **Lactation Mechanics** - Preserves the post-partum lactation system
5. **Symptom Tracking** - Maintains important pregnancy symptoms like morning sickness

---

## Effect Categories Summary

### Always Removable (Non-Pregnant)
- Poison
- Weakness
- Slowness
- Speed
- Blindness
- Hunger
- Nausea
- And other standard potion effects

### Always Protected (All Players)
- Birth-related effects
- Contraction effects
- Miscarriage effects
- Fertility effects
- Core pregnancy status effects

### Conditionally Protected (Pregnant Phase 2+)
- Morning Sickness
- Craving
- Lactation
- Maternity
- Depression
- Fetal Movement
- Belly Rubs
- Horny
- Fertile

---

## Technical Details

### Effect Classification

Effects are classified by tags in the mod:
- `minecraft:minepreggo:pregnancy_effects` - Core pregnancy effects
- `minecraft:minepreggo:secondary_pregnancy_effects` - Secondary Phase 2+ effects
- `minecraft:minepreggo:female_effects` - Female-related effects

### Milk Processing

When milk is consumed:
1. Check if player has pregnancy capability
2. Get current pregnancy status and phase
3. Determine which effects should be removed based on phase
4. Apply removal to appropriate effects
5. Drop glass bottle in inventory or on ground

---

## Tips for Players

1. **During Pregnancy:** Save milk for emergency situations when you really need to cure effects
2. **Phase Awareness:** Remember that Phase 2+ protects more effects - don't expect to remove everything
3. **Breast Milk Benefits:** Collect breast milk during post-partum for the regeneration bonus
4. **Stock Up:** Keep milk on hand during combat to manage potion effects
5. **Plan Ahead:** Know which effects will persist so you can prepare accordingly

---
