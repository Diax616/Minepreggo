# Crafting System — Minepreggo

> Non-technical documentation for the mod's custom recipe types.  
> Version: Minecraft Forge 1.20.1

---

## Table of Contents

1. [Intro](#intro)
2. [Water Potion Recipe (Water Potion Shapeless)](#water-potion-recipe-water-potion-shapeless)
3. [Armor Transfer Recipe](#armor-transfer-recipe)
   - [Maternity Armor](#maternity-armor)
   - [Belly Shield](#belly-shield)
4. [Summary Table](#summary-table)

---

## Intro

Minepreggo adds **two custom recipe types** to the crafting table, on top of regular Minecraft recipes. Both solve limitations that the vanilla system can't handle:

- Requiring that a potion be **pure water** and not just any potion.
- Converting one armor piece into another **while keeping its durability**.

These recipe types work at the **regular crafting table**. The remaining **netherite** maternity armor pieces use the standard **smithing table**.

---

## Water Potion Recipe (Water Potion Shapeless)

A shapeless recipe that **requires the potion used as an ingredient to be a water bottle**, not a potion with an effect.

**Why it exists:** in regular Minecraft, a recipe asking for a "potion" would accept *any* potion (healing, poison, strength…) because the game only checks the item type and not its contents. That would let you accidentally waste valuable potions in recipes that only need water.

**Behavior:**

| Aspect | Detail |
|--------|--------|
| Validation | If there's a potion in the grid that **isn't a water bottle**, the recipe won't complete. |
| Display | In the recipe book and in JEI, the potion ingredient is shown explicitly as a **water bottle**. |

**Usage examples:**

| Result | Ingredients |
|--------|-------------|
| Saltwater Bottle | Salt + water bottle + chili |
| Medical Table | (includes a water bottle among its components) |

---

## Armor Transfer Recipe

A crafting recipe that **transforms one armor piece into another while preserving its durability**, consuming additional ingredients in the process.

**Why it exists:** it lets you "upgrade" an armor piece (from regular to maternity, or from one phase to the next) **without losing accumulated wear** or having to repair it from scratch.

**Behavior:**

| Aspect | Detail |
|--------|--------|
| Exact input | Requires **1 input armor piece** + the exact number of extra ingredients. No more, no fewer items in the grid. |
| No enchantments | The input armor piece **cannot be enchanted** — if it is, the recipe won't work (prevents destroying enchantments). |
| Durability | The result **inherits the exact damage** of the original piece. A heavily worn chestplate produces an equally worn chestplate. |
| Extra ingredients | Configurable list of additional materials (leather, iron, string, etc.). |

### Maternity Armor

Starting at phase **P1**, a pregnant player needs a maternity chestplate. These recipes convert a regular female chestplate into its maternity equivalent for each phase:

| Material | Available phases | Crafting method |
|----------|------------------|-----------------|
| Leather | P1 – P4 | Armor Transfer |
| Iron | P1 – P4 | Armor Transfer |
| Golden | P1 – P4 | Armor Transfer |
| Diamond | P1 – P4 | Armor Transfer |
| Netherite | P1 – P4 | **Smithing table** |

There are three ways to get each maternity chestplate:

1. **From scratch** — a shaped recipe that crafts it from raw materials (e.g. `maternity_leather_p1_chestplate`).
2. **From a female chestplate** — converts a regular female chestplate by adding material (`..._from_female`).
3. **From a previous phase** — upgrades the maternity chestplate from one phase to the next (`..._from_p1`, `..._from_p2`, etc.), taking advantage of the durability transfer.

> Thanks to durability inheritance, upgrading the same chestplate phase by phase (P1 → P2 → P3 → P4) preserves the piece's actual wear instead of handing you a brand-new one.

### Belly Shield

The Belly Shield is a piece that covers the advanced phases (**P5 – P8**). It uses the same Armor Transfer recipe type:

| Phase | How to get it |
|-------|---------------|
| P5 | Base craft |
| P6 | Base craft, or upgrade from P5 (+ string) |
| P7 | Base craft, or upgrade from P5 / P6 |
| P8 | Base craft, or upgrade from P5 / P6 / P7 |

> Just like maternity armor, upgrading the shield from one phase to another keeps its accumulated durability.

---

## Summary Table

| Recipe type | Station | What it does |
|-------------|---------|--------------|
| **Water Potion Shapeless** | Crafting table | Shapeless recipe that requires a **pure water bottle** as an ingredient. |
| **Armor Transfer** | Crafting table | Converts one armor piece into another **while keeping its durability**; rejects enchanted pieces. |

> See [MISC.md](MISC.md) for details on maternity armor and the Pregnancy Protection enchantment, and [PREGNANCY_SYSTEM.md](PREGNANCY_SYSTEM.md) for per-phase equipment restrictions.

---

*Documentation generated from analysis of the Minepreggo source code.*
