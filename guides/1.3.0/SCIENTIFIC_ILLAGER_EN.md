# Scientific Illager — Minepreggo

> Non-technical documentation for the ScientificIllager entity.  
> Version: Minecraft Forge 1.20.1

---

## Table of Contents

1. [Intro](#intro)
2. [General Behavior](#general-behavior)
3. [Mobs Associated with the Spawn](#mobs-associated-with-the-spawn)
4. [Accessing Services](#accessing-services)
5. [Service: Item Trades](#service-item-trades)
   - [Base Trades](#base-trades)
   - [Extra Trades](#extra-trades)
   - [Rare Trade](#rare-trade)
   - [Trade Restock](#trade-restock)
6. [Service: Buying PreggoMobs](#service-buying-preggomobs)
7. [Service: Medical Checkups](#service-medical-checkups)
8. [Killing the Illager](#killing-the-illager)

---

## Intro

The Scientific Illager is an illager-family entity with advanced medical and reproductive knowledge. Unlike other illagers, he **never despawns** and can't join raids. His purpose is to offer exclusive services to the player: pregnancy-related item trades, sales of tamed PreggoMobs, and fertility and prenatal medical checkups.

Drops **20 experience points** on death.

---

## General Behavior

The Scientific Illager is permanent in the world — he doesn't disappear by distance or on Peaceful difficulty.

**Combat:**
- At **≤4 blocks**: switches to an iron axe with **Sharpness** (level varies by difficulty).
- At **>4 blocks**: uses a crossbow with **Quick Charge** and a chance of **Multishot** or **Piercing**.
- His arrows **don't hit his own Ill mobs** — he won't accidentally shoot his own guards.
- If one of his own Creeper Girls ignites, he **runs away from her** within 8 blocks.
- If he's being attacked while a player has a service menu open, he **automatically closes the menu**.

---

## Mobs Associated with the Spawn

When he first spawns, the Scientific Illager generates a group of **Ill mobs** that act as guards. These mobs have boosted stats compared to their normal versions:

| Ill guardian mob buffs | Value |
|------------------------|:-----:|
| Bonus attack damage | +3 |
| Bonus armor | +2 |
| Bonus max health | +35% |
| Knockback (Slime Girl and Ender Woman only) | +0.5 |

**Group composition:**

| Mob | Chance |
|-----|:------:|
| Ill Zombie Girl | Always |
| Ill Humanoid Creeper Girl | Always |
| Ill Creeper Girl **or** Ill Humanoid Slime Girl | Always (60% / 40%) |
| Ill Ender Woman | Always |
| Vendible PreggoMob (see purchase section) | 70% *(30% chance of 2)* |

> The buffs belong to the Ill guardian mobs, **not** to the PreggoMobs you buy. When a PreggoMob is sold, the Ill mob disappears and a new tamable entity is generated with no extra bonuses.

---

## Accessing Services

The Scientific Illager offers three different services accessible by **right-clicking**:

| Interaction | Service that opens |
|-------------|-------------------|
| Normal right-click | Item trade menu |
| Right-click (with PreggoMobs available) | PreggoMob purchase option |
| **Medical checkup key** + right-click | Medical checkup menu |

**Blocking conditions for medical checkups:**
- He must have a **Medical Table** claimed within **16 blocks**. If he doesn't, he'll try to claim the nearest one; if none is available, he shows an error and doesn't open the menu.
- If the player is **in labor**, the menu is blocked with an error message.

---

## Service: Item Trades

The trade catalog is built each time the Illager generates new offers. It combines three groups: **BASE** (always present) + **EXTRA** (one random group) + **RARE** (one at random, 1 use).

---

### Base Trades

Always available from any Scientific Illager:

**Babies for diamonds:**

| Item | Cost | Uses |
|------|:----:|:----:|
| Baby Human | 3 diamonds | 2 |
| Baby Zombie | 2 diamonds | 2 |
| Baby Creeper Monster | 4 diamonds | 2 |
| Baby Creeper Humanoid | 6 diamonds | 2 |
| Baby Slime Monster | 3 diamonds | 2 |
| Baby Slime Humanoid | 5 diamonds | 2 |
| Baby Ender Monster | 5 diamonds | 2 |
| Baby Ender Humanoid | 7 diamonds | 2 |

**Breast milk for emeralds:**

| Item | Cost | Uses |
|------|:----:|:----:|
| Human breast milk | 3 emeralds | 6 |
| Zombie breast milk | 2 emeralds | 6 |
| Creeper breast milk | 5 emeralds | 6 |
| Slime breast milk | 4 emeralds | 6 |
| Ender breast milk | 8 emeralds | 6 |

**Other:**

| Item | Cost | Uses |
|------|:----:|:----:|
| Enchanted book for baby (Human Humanoid) | Variable | — |
| Human sperm tube ×1 | 12 dead human fetuses | 3 |
| Impregnation potion (level 0) | 20 emeralds | 5 |

---

### Extra Trades

When spawning, the Illager picks **one of three groups** at random. The group determines which extra items are available for that instance.

#### Group A — Creeper and Slime Focus

| Item | Cost | Uses |
|------|:----:|:----:|
| Enchanted book for Creeper baby (Humanoid and Monster) | Variable | — |
| Enchanted book for Slime baby (Humanoid and Monster) | Variable | — |
| Dead Creeper and Slime fetuses | Emeralds or sperm tubes | 3 |
| Pregnancy acceleration potion level 0 | 25 emeralds | 7 |
| Pregnancy delay potion level 0 | 25 emeralds | 7 |
| Gradual acceleration potion level 0 | 20 emeralds | 7 |
| **Pregnancy Protection** book (random level I–IV) | Emeralds (variable price, ×2 as treasure) | 2 |
| Villager Brain | 15 emeralds | 3 |
| Ender Dragon Baby Shulker Box *(see note)* | Baby Ender Dragon Block | 1 |

> **Shulker Box — Group A contents:** 1 Mending book, 8 golden apples, 1 enchanted golden apple, 6 diamonds, 1 Totem of Undying + Minepreggo book + 2 mod banner patterns.

---

#### Group B — Zombie and Villager Focus

| Item | Cost | Uses |
|------|:----:|:----:|
| Enchanted book for Zombie Humanoid baby | Variable | — |
| Enchanted book for Villager Humanoid baby | Variable | — |
| Dead Zombie and Villager fetuses | Emeralds or sperm tubes | 3–5 |
| Metabolism Control potion | 15 emeralds | 7 |
| Pregnancy Healing potion | 10 emeralds | 7 |
| **Pregnancy Knockback Resistance** book (single level I) | Emeralds (variable price, ×2 as treasure) | 2 |
| Activated Gunpowder | 7 emeralds | 3 |
| Ender Dragon Baby Shulker Box *(see note)* | Baby Ender Dragon Block | 1 |

> **Shulker Box — Group B contents:** 1 Mending book, 2 Netherite ingots, 4 golden apples, 1 enchanted golden apple, 3 diamonds, 2 Totems of Undying + Minepreggo book + 2 mod banner patterns.

---

#### Group C — Ender Focus

| Item | Cost | Uses |
|------|:----:|:----:|
| Enchanted book for Ender baby (Monster and Humanoid) | Variable | — |
| Dead Ender and Human fetuses | Emeralds or sperm tubes | 3 |
| Pregnancy Resistance potion | 20 emeralds | 7 |
| Zero Gravity Belly potion | 25 emeralds | 7 |
| **Fetal Bond** book (single level I) | Emeralds (variable price, ×2 as treasure) | 2 |
| Ender Slime Jelly | 20 emeralds | 3 |
| Ender Dragon Baby Shulker Box *(see note)* | Baby Ender Dragon Block | 1 |

> **Shulker Box — Group C contents:** 2 Mending books, 1 Netherite ingot, 1 golden apple, 1 enchanted golden apple, 8 diamonds, 1 Totem of Undying + Minepreggo book + 2 mod banner patterns.

---

### Rare Trade

Each Illager has **exactly one** of these trades, chosen at random. It has only **1 use** and is the most expensive item in the catalog:

| Rare item | Price |
|-----------|:-----:|
| Pregnancy acceleration potion level 4 | 40 emeralds |
| Pregnancy delay potion level 4 | 40 emeralds |
| Gradual pregnancy acceleration potion level 4 | 40 emeralds |
| Human impregnation potion level 5 | 45 emeralds |
| Zombie impregnation potion level 5 | 42 emeralds |
| Creeper impregnation potion level 5 | 43 emeralds |
| Slime impregnation potion level 5 | 47 emeralds |
| Ender impregnation potion level 5 | 50 emeralds |
| Villager impregnation potion level 5 | 41 emeralds |
| Baby duplication potion level 4 | 60 emeralds |
| Eternal pregnancy potion | 55 emeralds |
| Pregnancy damage potion | 50 emeralds |

---

### Trade Restock

Trades run out with use. For the Illager to restock his offers, **all** of these conditions must be true at the same time:

| Condition | Detail |
|-----------|--------|
| ≥3 depleted offers | Enough slots are out of stock |
| 12,000 ticks elapsed | ~10 minutes since the last restock |
| Within or near his structure | The `SCIENTIFIC_ILLAGER_HIDEOUT` structure must be within ~3 chunks |
| No player in an active menu | He can't restock while someone is shopping |

> If the Illager moves away from his home structure, the **timer resets to 0** and doesn't advance until he returns. Moving him far from his hideout prevents his trades from restocking.

---

## Service: Buying PreggoMobs

With a 70% chance, the Illager generates **1 vendible PreggoMob** on spawn (30% chance of 2). Each available PreggoMob has a cost in emerald blocks plus a second item:

| PreggoMob | Emerald blocks | Second item |
|-----------|:--------------:|-------------|
| Humanoid Slime Girl | ~6 | ~20 slimeballs |
| Zombie Girl | ~4 | ~30 rotten flesh |
| Humanoid Creeper Girl | ~5 | ~20 gunpowder |
| Monster Creeper Girl | ~6 | ~20 gunpowder |
| Monster Ender Woman | ~8 | ~10 Ender pearls |

> Exact costs are random within a defined range — two Scientific Illagers in the same world can ask different prices for the same PreggoMob.

**How the purchase works:**
1. The player pays the cost to the Illager.
2. The vendible Ill mob **disappears** from the world.
3. A new tamable entity of the corresponding variant is spawned, already tamed to the buying player.
4. The new PreggoMob has her **own base stats** — she doesn't inherit the buffs the guardian Ill mob had.

---

## Service: Medical Checkups

The Scientific Illager is the **only provider** capable of serving PreggoMobs. He also serves players with all of the Villager Doctor's services plus one exclusive one:

| Service | Availability | Illager exclusive |
|---------|:------------:|:-----------------:|
| Fertility status | Always | No |
| Pregnancy test | Always (♀ only) | No |
| Regular prenatal checkup | From P1 | No |
| Ultrasound | From P2 | No |
| Paternity test | From P2 | No |
| **Excess baby reduction** | From P3, OVERLOADED womb | **Yes** |

> See [MEDICAL_CHECKUP_SYSTEM.md](MEDICAL_CHECKUP_SYSTEM_EN.md) for the full breakdown of each service and its costs.

---

## Killing the Illager

| Event | Condition |
|-------|-----------|
| **BAD_OMEN** applied to the killer | Only if the Illager **still has at least one living Ill mob** registered as his and there's no active raid nearby |
| Medical Table freed | Always — the table becomes available for another Illager |
| Ill mobs unlinked | Surviving Ill mobs lose their link to the Illager |

> Buying or removing all Ill mobs **before** killing the Illager avoids getting the Bad Omen effect.

---

*Documentation generated from analysis of the Minepreggo source code.*
