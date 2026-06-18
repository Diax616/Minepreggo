# Miscellaneous Mechanics — Minepreggo

> Non-technical documentation for the mod's secondary mechanics.  
> Version: Minecraft Forge 1.20.1

---

## Table of Contents

1. [Pregnancy Damage](#pregnancy-damage)
   - [When It Happens](#when-it-happens)
   - [Damage Amount](#damage-amount)
   - [Enchantment: Pregnancy Protection](#enchantment-pregnancy-protection)
   - [Final Consequence](#final-consequence)
2. [Irritated Womb and Sex-Triggered Labor](#irritated-womb-and-sex-triggered-labor)
   - [How Irritation Builds Up](#how-irritation-builds-up)
   - [The Two Stages](#the-two-stages)
   - [Post-Sex Effects](#post-sex-effects)

---

## Pregnancy Damage

The pregnancy has its own internal health bar, independent from the player's health. Certain events can reduce this health. If it hits zero, a **miscarriage (MISCARRIAGE)** triggers.

### When It Happens

The system ignores pregnancy damage in the following cases — **no consequences for the pregnancy**:

| Situation | Reason |
|-----------|--------|
| Player in creative or invincible mode | General immunity |
| Pregnancy at phase P0 | No embryo formed yet |
| Damage comes from the pregnancy pain itself | Avoids feedback loop |
| Entity has the `PREGNANCY_RESISTANCE` effect | Active protection |

Past those filters, pregnancy damage only happens in **two specific situations**:

1. **Explosion** (TNT, creeper, cannon fire, etc.) — pregnancy damage is applied automatically, no extra condition needed.
2. **Any other damage source** — only applies if the entity's health is **below 60% of maximum** at the moment of impact.

> A regular hit while the player is healthy **doesn't damage the pregnancy**. Vulnerability only kicks in when they're already weakened.

### Damage Amount

The base damage depends on the pregnancy phase. The higher the phase, the wider the possible range:

| Source | Range at P1 | Range at P5 | Range at P9 |
|--------|:-----------:|:-----------:|:-----------:|
| Non-explosion (health < 60%) | 1–2 | 1–6 | 1–10 |
| Explosion | 11–12 | 11–16 | 11–20 |

This base value is then multiplied by a **scaling factor** based on proximity to birth and progress within the current phase. In advanced phases with a well-developed pregnancy, the damage can be significantly higher than the base.

The final result is never less than **1 damage point**.

### Enchantment: Pregnancy Protection

The `PREGNANCY_PROTECTION` enchantment applied to the **chestplate** reduces the damage the pregnancy takes. **Doesn't protect against fall damage.** Can only be applied to maternity armor. Its **max level is IV**.

| Level | Reduction |
|-------|:---------:|
| I | 15% |
| II | 30% |
| III | 45% |
| IV | 60% *(max level)* |

Reduction is 15% per level, with an internal cap of 75% that the max level (IV = 60%) doesn't reach.

Every time the enchantment absorbs a hit, the chestplate takes **extra durability damage** (between 1 and 3 points per hit). The enchantment protects the pregnancy at the cost of wearing down your gear.

### Final Consequence

If the pregnancy health reaches **zero**, the system triggers a **MISCARRIAGE**. To avoid it:
- Keep health above 60% of maximum in combat situations
- Avoid explosions at any health level
- Use a chestplate with `PREGNANCY_PROTECTION` in risky situations
- Keep the `PREGNANCY_RESISTANCE` effect active whenever possible

---

## Irritated Womb and Sex-Triggered Labor

Sex with a pregnant entity builds up **uterine irritation**. If this irritation maxes out at the wrong moment, it can trigger labor earlier than expected.

### How Irritation Builds Up

During sex, each **orgasm** from the male participant (requires sperm concentration above 4) has a **60% chance** of irritating the womb. The irritation cause is **PENETRATION**, which applies a ×1.6 multiplier to the base irritation.

Irritation accumulates between sexual encounters — it doesn't automatically reset when the act ends. A womb already heavily irritated from previous acts can hit the limit with fewer encounters.

### The Two Stages

When the sexual act ends, the system evaluates the womb state. If irritation reached **100%**, one of two things happens depending on whether the effect was already active:

---

**Stage 1 — First time at 100%: Warning**

The `IRRITATED_WOMB` status effect is applied. The womb is at its limit but labor **doesn't start yet**. It's a clear signal that another sexual encounter in this state can trigger labor.

---

**Stage 2 — Second time at 100% with effect active: Labor Starts**

If the womb reaches 100% again while the `IRRITATED_WOMB` effect is already active, the system **forces labor to start**: it advances the current phase's internal progress and immediately activates the **WATER_BREAKING** phase, regardless of how far off natural birth was.

---

**Summary flow:**

```
Sex with pregnant entity
  └─ Per orgasm:
       └─ 60% → PENETRATION irritation ×1.6

When act ends:
  ├─ Womb at 100%, no effect active
  │    └─ Applies IRRITATED_WOMB (warning)
  ├─ Womb at 100%, effect already active
  │    └─ Advances phase days +1 → WATER_BREAKING
  └─ Womb below 100%
       └─ No uterine consequences
```

> If the player and the PreggoMob or the female player already have the `IRRITATED_WOMB` effect active, **the next sexual encounter that pushes the womb to 100% will trigger labor**, regardless of the current pregnancy phase.

The **CONTRACTION** or **FETAL_MOVEMENT** pain can also activate during the act if the pregnancy is far enough along, before the labor start actually happens.

### Post-Sex Effects

When the act ends, both parties receive effects regardless of the womb state:

| Participant | Effects received |
|-------------|-----------------|
| Active player | Weakness (60s) + Hunger II (60s) |
| Passive player / PreggoMob | Weakness (60s) |
| Villager | Slowness (60s) |
| Both participants | Sexual appetite reduction (−5) |

Also, if either party had the **HORNY** symptom active, it's removed when the act ends.

---

*Documentation generated from analysis of the Minepreggo source code.*
