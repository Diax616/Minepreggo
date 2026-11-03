# Release Notes: Version [1.3.0]
**Release Date:** 2026-05-13

There were a lot of changes, many of them from DeviantArt and GitHub, however, I didn't keep a record of which ones I fixed. These are the ones I can remember. My apologies, I'll now keep a record of all the changes.

## [Humanoid/Monster Creeper Girl]

### Explosion
- **[Power]**
    - The explosion force was reduced in pregnant versions.
- **[Time]**
    - The time it takes for a pregnant creeper to explode was increased.
### Head Rotation
 - **[Monster Creeper Girl]**
    - A synchronization bug between the head rotation and the item the creeper is holding in its mouth has been fixed.

## [Miscarriage]
### Preggomobs
 - **[Crash]**
    - A bug that caused crashes when the miscarriage event ended in the preggombs has been fixed.

## [Reproduction Witch]

### Looting
- **[Rework]**
    - The loot will be modified; the chance to obtain the following potions from the Reproduction Witch will be removed: Pregnancy Healing.
    - The chance to obtain a potion as a drop will be increased.
    - Using the looting enchantment with a 10% multiplier for each additional level of looting, the Fertility Witch will have a 40% chance of dropping the Baby Duplication Potion, the Infertility Witch will have the same chance of dropping the Eternal Pregnancy Potion.
### AI and Behaviour
- **[Attack against male players]**  
    - The infertility witch will first try using infertility potions; if the player is under the infertility effect, the witch will always choose to throw explosive charges.
    - The Fertility Witch will always choose to throw explosive charges at players.
### Attacks
- **[Explosive Charges]**  
    - The speed at which the witch throws explosive charges will be increased.  
    - It can now adjust its trajectory toward the target only once after being thrown.
### Spawning
- **[Chance]**  
    - Both witches (Fertility and Infertility) now have the same spawn chance to spawn in plains, but they'll only spawn during a full moon.
    - Fertility witches are now more likely to spawn in sunflower plains, while infertility witches have a higher spawn rate in swamps.
### Team
- **[Allies]**
    - Witches are no longer allied with Illagers and can even turn hostile toward them. The only exception is the Scientist Illager and their pet Preggomobs.
### Bug Fixes
- **[Raid]**
    - Both witches will no longer appear in raids.
---

## [Scientific Illager]

### Trading
- **[Fetuses]**
    - Nerfed the fetus cost for cum trades.
    - Cheaper emerald-to-fetus exchange rate.
    - Capped the number of fetus trades.
- **[Babies]**
    - Nerfed the diamond cost per baby.
    - Capped the baby trade uses.
    - The trading rewards for the Baby Ender Dragon have been reduced.
- **[Misc]** 
    - Breast milk trades are getting removed.
    - It now needs at least two exhausted trades before he can reset his offers.
    - Villager Brain trades are getting removed.
### AI and Behaviour
- **[Range Attack]**  
    - The Scientist Illager now has two attack styles: ranged (crossbow) and melee (axe).
- **[Escape]**
    - If Monster Creeper Girls or Humanoid Creeper Girls are about to explode, their owner (the Scientist Illager) will try to move away from the blast.
### Pets PreggoMobs
- **[Attributes]**  
    - Scientist Illager's Preggomobs are getting a HP and armor buff. Enderwoman also gets extra knockback resistance.
- **[Equipment]**  
    - Scientist Illager's Preggomobs (Zombie Girl and Enderwoman) have a chance to spawn with enchanted armor.
- **[AI and Behaviour]**  
    - Monster Creeper Girls and Humanoid Creeper Girls will now choose to explode if there are two or fewer Preggomobs protecting the Scientist Illager.
    - All of the Scientist Illager's Preggomobs get an "Enrage" buff (Strength, Speed) if their owner dies, causing them to erraticly attack players,  villagers or iron golems.

### Structure (Scientific Illager Hideout)
- **[Looting]**  
    - Loot in the Scientist Illager's Hideout chests is getting nerfed.
### Bug Fixes
- **[Medical Checkups]**  
    - Fixed a bug that let players get free medical checkups.
---

## [Hostil Preggo Mobs]

### Spawning
- **[Misc]**  
    - Natural spawn rates for Hostile Preggo Mobs are getting nerfed, especially Enderwomen and Monster/Humanoid Creeper Girls. 
---

## [Boobs Model]

### Bug Fixes
- **[Rendering]**  
    - Fixed a bug where female boobs wouldn't render after unequipping chest armor while pregnant (doesn't apply to belly shield armor).
---

## [Female Player Model]

### Bug Fixes
- **[Rendering]**  
    - Fixed a bug where the female torso kept rendering while invisible.
---

## [Belly Model]

### Bug Fixes
- **[Rendering]**  
    - Fixed a bug where the pregnant belly kept rendering while invisible.
---

## [Belly HitBox]
### Bug Fixes
- **[Position]**  
    - The hitbox position has been shifted, so players can no longer place blocks underneath themselves.
---

## [Potions]

### Fertility
- **[Female Player and PreggoMobs]**  
    - It will now force the fertility cycle into the ovulation phase.
    - It will increase the number of eggs, adding one extra for each time the effect is applied.
- **[Recipe]**  
    - Its crafting has been removed; it can now only be obtained through trading or looting.
### Infertility
- **[Female Player and PreggoMobs]**
    - It will remove all present eggs.
- **[Recipe]**  
    - Its crafting has been removed; it can now only be obtained through trading or looting.

### Potion of Harmful Pregnancy
- **[Powerful]**  
    - The pregnancy health damage has been increased.

### Misc
- **[Humanoid Potions and Mob Effects]** 
    - The humanoid ender impregnation potion and effect are getting yeeted from the mod.
    - The humanoid creeper impregnation potion and effect are getting yeeted from the mod.
    
---

## [Fertility System]

### Menstruation (MobEffect)
- **[Bleeding]**  
    - Blood particle spawn time's getting nerfed to 200 ticks per 600-tick cycle.
- **[Damage]**
    - Damage only applies if you've got level 2 (amp 1 in code) and the entity's HP is above half, plus there's an extra RNG check for it to actually trigger.

### Fertile (MobEffect)
- **[Female Players/Preggo Mobs]**  
    - It will now increase the chance of monozygotic splits.
- **[Male Players/Villagers]**
    - It will now increase the chance of an egg being fertilized.
### Infertile (MobEffect)
 - **[Players]**
    - It will now make colors appear more opaque and grayish. (Client)
---

## [Sex Event]

### Bug Fixes
- **[Sound]**  
    - During the sex event between a male entity (player or villager) and a pregnant female player, two moaning sounds are playing at once. Fixed it by removing the duplicate sound.
---


## [Items]

### Food
- **[Lemon]**  
    - The drop rate and quantity of lemons from leaf blocks have been reduced.
---

## [Recipes]

### Crafting
- **[Knee Braces]**  
    - It now requires double the materials.

## [Enchantments]

### Fetus Bond
- **[Effect]**  
    - The random chance for extra damage has been removed.
    - For each higher pregnancy phase (P0 → P9), the additional damage will increase, regardless of the number of babies.

### Pregnancy Knockback resistance
- **[Effect]**  
    - Additional knockback resistance is now applied from the start, and the amount of resistance increases with the pregnancy phase.

## [Pregnancy Health]

### Healing
- **[By Time]**  
    - Pregnancy healing over time has been removed.
- **[By Sleep]**  
    - Pregnancy healing from sleeping has been increased.
### Damage
- **[Any]**  
    - General damage to pregnancy health has been increased.

## [Configs]

### Overloaded Womb
- **[Enable Overloaded Womb]**  
    - it prevents the "Overloaded Uterus" state, stopping players from having more babies than the allowed limit.
### Fertility System
- **[Force Fertilization]**
    - it allows all eggs to be forcibly fertilized. This only works if the male player or villager isn't infertile and has enough cum.