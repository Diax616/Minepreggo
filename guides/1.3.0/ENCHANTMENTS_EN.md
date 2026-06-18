# Enchantments — Minepreggo

> Non-technical documentation for the mod's enchantments.  
> Version: Minecraft Forge 1.20.1

---

## Table of Contents

1. [Intro](#intro)
2. [Pregnancy Protection](#pregnancy-protection)
3. [Pregnancy Knockback Resistance](#pregnancy-knockback-resistance)
4. [Fetal Bond](#fetal-bond)
5. [Summary Table](#summary-table)
6. [How to Get Them](#how-to-get-them)

---

## Intro

Minepreggo adds three pregnancy-exclusive enchantments. All three are **treasure enchantments** (`treasure-only`): **they don't show up at the enchanting table** and can only be obtained as enchanted books (looting, trading with the Scientific Illager, etc.).

Two of them protect the pregnant player; the third turns the pregnancy into an offensive advantage.

---

## Pregnancy Protection

Reduces the damage dealt to the **pregnancy's internal health**, keeping a fight from triggering a miscarriage.

| Property | Value |
|----------|-------|
| Category | Armor (chestplate) |
| Compatible with | **Maternity armor** only |
| Rarity | UNCOMMON |
| Max level | **IV** |
| Type | Treasure (not at enchanting table) |

**Effect:**

| Level | Pregnancy damage reduction |
|-------|:--------------------------:|
| I | 15% |
| II | 30% |
| III | 45% |
| IV | 60% *(max level)* |

- Reduction is **15% per level**. There's an internal cap of 75%, but the max level (IV) doesn't reach it.
- **Doesn't protect against fall damage.**
- Every time it absorbs a hit, the chestplate takes **1 to 3 extra durability damage**. It protects the pregnancy at the cost of your gear.

> See [MISC.md](MISC.md) for the full breakdown of the pregnancy damage system.

---

## Pregnancy Knockback Resistance

Grants knockback resistance that **scales with the pregnancy phase**.

| Property | Value |
|----------|-------|
| Category | Armor (chestplate) |
| Compatible with | **Maternity armor** only |
| Rarity | UNCOMMON |
| Max level | **I** |
| Type | Treasure (not at enchanting table) |

**Effect:** while wearing the enchanted chestplate, a knockback resistance bonus is applied based on the current pregnancy phase:

| Phase | Resistance bonus |
|:-----:|:----------------:|
| P0 | +0.1 |
| P1 | +0.2 |
| P2 | +0.3 |
| P3 | +0.4 |
| P4 | +0.5 |
| P5 | +0.6 |
| P6 | +0.7 |
| P7 | +0.8 |
| P8 | +0.9 |
| P9 | +1.0 *(full knockback immunity)* |

- The bonus is **applied on equip** and **removed on unequip**.
- Unlike the knockback resistance the pregnancy already gives naturally (see [PREGNANCY_SYSTEM.md](PREGNANCY_SYSTEM.md)), this enchantment is an **additional** and much larger bonus: at P9 it grants full immunity.

---

## Fetal Bond

Turns the pregnancy into an **offensive advantage**: the pregnant wearer deals bonus damage when attacking.

| Property | Value |
|----------|-------|
| Category | Weapon · also applicable to **axes** |
| Slot | Main hand |
| Rarity | RARE |
| Max level | **I** |
| Type | Treasure (not at enchanting table) |
| Incompatibility | **Doesn't combine with damage enchantments** (Sharpness, Smite, Bane of Arthropods) |

**Effect:** only works if the attacker is **pregnant** (player or PreggoMob). The bonus damage is calculated as:

```
bonus damage = number of babies × (1 + phase × 0.15)
```

In other words, damage scales with **how many babies** are being carried and **how far along** the pregnancy is.

**Bonus damage examples:**

| Babies | At P1 (×1.15) | At P5 (×1.75) | At P9 (×2.35) |
|:------:|:-------------:|:-------------:|:-------------:|
| 1 | +1.15 | +1.75 | +2.35 |
| 5 | +5.75 | +8.75 | +11.75 |
| 10 | +11.5 | +17.5 | +23.5 |

> A large, advanced pregnancy can more than double the damage per hit. Since Fetal Bond is incompatible with regular damage enchantments, the choice is strategic: commit to the pregnancy or stick with traditional Sharpness.

---

## Summary Table

| Enchantment | Applies to | Max level | Main effect |
|-------------|-----------|:---------:|-------------|
| **Pregnancy Protection** | Maternity chestplate | IV | −15% pregnancy damage per level (max 60%) |
| **Pregnancy Knockback Resistance** | Maternity chestplate | I | Knockback resistance scaled by phase (P9 = full immunity) |
| **Fetal Bond** | Weapon / axe | I | Bonus attack damage based on babies and phase |

---

## How to Get Them

Since these are treasure enchantments, they can't be applied at the enchanting table. The main source is enchanted books sold by the **Scientific Illager**, where they appear in their trade groups:

| Book | Illager group | Book level |
|------|---------------|------------|
| Pregnancy Protection | Group A (Creeper + Slime) | Random (I–IV) |
| Pregnancy Knockback Resistance | Group B (Zombie + Villager) | Always I (single level) |
| Fetal Bond | Group C (Ender) | Always I (single level) |

The book level is rolled randomly between the enchantment's min and max (that's why Protection can come out at I–IV while the other two only have one level). The **emerald price is random** and, being a treasure enchantment, it's **doubled** compared to a regular book (up to a max of 64 emeralds).

> See [SCIENTIFIC_ILLAGER.md](SCIENTIFIC_ILLAGER.md) for the full trade catalog.

---

*Documentation generated from analysis of the Minepreggo source code.*
