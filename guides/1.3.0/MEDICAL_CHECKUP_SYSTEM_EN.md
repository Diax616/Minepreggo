# Medical Checkup System — Minepreggo

> Non-technical documentation for the mod's medical checkup system.  
> Version: Minecraft Forge 1.20.1

---

## Table of Contents

1. [Intro](#intro)
2. [Medical Providers](#medical-providers)
3. [Checkup Types](#checkup-types)
4. [Service Catalog](#service-catalog)
5. [Fertility Checkups](#fertility-checkups)
   - [Female Fertility Status](#female-fertility-status)
   - [Male Fertility Status](#male-fertility-status)
   - [Combined Fertility](#combined-fertility)
   - [Pregnancy Test](#pregnancy-test)
6. [Prenatal Checkups](#prenatal-checkups)
   - [Regular Prenatal Checkup](#regular-prenatal-checkup)
   - [Ultrasound](#ultrasound)
   - [Paternity Test](#paternity-test)
   - [Baby Reduction](#baby-reduction)
7. [Checkups for PreggoMobs](#checkups-for-preggomobs)
8. [Costs and Requirements](#costs-and-requirements)
9. [Provider Comparison Table](#provider-comparison-table)

---

## Intro

The medical checkup system lets players get detailed info about their fertility and pregnancy status. Each checkup produces a **written book** signed by the provider, with the consultation date and the price paid. Books can be saved, shared, or re-read at any time.

There are two checkup categories: **Fertility** (always available) and **Prenatal** (pregnancy only). Each category has its own services, access requirements, and emerald costs.

---

## Medical Providers

There are two types of entities that offer medical services. Not all services are available with both providers.

### Villager Doctor

A villager with medical specialization. Offers the most common fertility and prenatal services. Plays the characteristic village sound (`VILLAGER_YES`) when a consultation is completed.

**Services offered:**
- Fertility checkup (both genders)
- Pregnancy test (females only)
- Regular prenatal checkup
- Ultrasound
- Paternity test

### Scientific Illager

A scientist illager with advanced knowledge. Offers everything the Villager Doctor does plus one exclusive service: **baby reduction** when the womb is overloaded. Plays the illager celebration sound (`VINDICATOR_CELEBRATE`) when a consultation is completed.

**Services offered:**
- Everything the Villager Doctor offers
- Excess baby reduction *(exclusive)*

> The Scientific Illager is the **only provider** that can serve PreggoMobs. The Villager Doctor only serves players.

---

## Checkup Types

| Type | When it's available | Who can receive it |
|------|--------------------|--------------------|
| **FERTILITY** | Always | Any player (male or female) and PreggoMobs |
| **PRENATAL** | Only during pregnancy | Pregnant players and pregnant PreggoMobs |

---

## Service Catalog

### Fertility Services

| Service | Description | Access restriction |
|---------|-------------|-------------------|
| `FERTILITY_STATE` | Full fertility analysis | No gender restriction |
| `PREGNANCY_TEST` | Pregnancy test | Female entities only |

### Prenatal Services

| Service | Description | Minimum phase required |
|---------|-------------|----------------------|
| `PREGNANCY_STATE` | Regular pregnancy checkup | P1 |
| `ULTRASOUND_SCAN` | Womb and baby ultrasound | P2 |
| `PATERNITY_TEST` | Paternity test | P2 |
| `REMOVAL_OF_EXCESS_BABIES` | Baby reduction for overloaded womb | P3 *(Illager only)* |

If the pregnancy hasn't reached the service's minimum phase, it shows up **grayed out** in the menu with a warning message. It can't be purchased.

---

## Fertility Checkups

### Female Fertility Status

The resulting book has four pages with full info on the player's menstrual cycle and reproductive status.

---

**Cover**
- Patient name
- Service price (in emeralds)
- Consultation date

---

**Page 2 — General Status**
- **Fertility score** — percentage representing overall fertility right now
- **Reproductive health** — reproductive system health percentage
- **Cycle regularity** — assigned cycle type (REGULAR, IRREGULAR, HYPER_OVULATION)
- **In fertile window?** — Yes or No

---

**Page 3 — Cycle and Ovulation**
- **Current cycle phase** — MENSTRUATION, FOLLICULAR, OVULATION, or LUTEAL
- **Ovulating right now?** — Yes or No
- **Fertile window start day** — cycle day the window opens
- **Fertile window end day** — cycle day the window closes
- **Number of available eggs** — how many eggs are available this cycle

---

**Page 4 — Time Remaining**
- **Days until ovulation** — how many cycle days until the peak
- **Days until window closes** — how much fertile window time is left
- **Days until next cycle** — when the cycle resets from the start
- **Total cycle length** — in cycle days

---

### Male Fertility Status

The book has three pages with the player's sperm status and reproductive capacity.

---

**Cover**
- Patient name, price, and date

---

**Page 2 — General Status**
- **Fertility score** — overall fertility percentage
- **Reproductive health** — reproductive system health percentage
- **Fertile?** — Yes or No (the active fertility threshold is a score above 25%)

---

**Page 3 — Sperm Quality**
- **Sperm quality** — combination of concentration and viability as a percentage
- **Viability** — how functional the sperm are (%)
- **Current concentration** — percentage relative to the base concentration for the assigned fertility level

> If concentration has dropped a lot due to recent sexual activity, this percentage will reflect that. Concentration must exceed the threshold of 4 for fertilization to be attempted.

---

### Combined Fertility

A special book that combines both parties' data and calculates the **real pregnancy probability at the time of the consultation**. Only available when both parties get the checkup together.

---

**Cover**
- Patient name, price, and date

---

**Page 2 — Female Summary**
- Fertility score
- Reproductive health
- Current cycle phase
- In fertile window? → Yes / No
- Days until ovulation
- Number of available eggs

---

**Page 3 — Male Summary**
- Fertility score
- Reproductive health
- Sperm quality (%)
- Sperm viability (%)
- Current concentration (%)

---

**Page 4 — Real-Time Probability**
- **Fertilization probability** — calculated at the exact moment of purchase, combining all factors from both parties
- **Ovulation bonus active?** — Yes or No (+40% to probability when active)
- **In fertile window right now?** — Yes or No

> This is the only way to see the real pregnancy probability. It reflects the state at the exact moment of the consultation; if either party moves away or ovulation ends, the percentage would change.

---

### Pregnancy Test

A simple test available for female entities only. Tells you whether a pregnancy is in progress.

---

**Cover**
- Patient name, price, and date

---

**Page 2 — Result**
- **POSITIVE** — the player is fertilized / pregnant
- **NEGATIVE** — no pregnancy in progress

> The book stores the result in its internal data (NBT). This lets it be used in other mod mechanics beyond just being text. The result is tied to the patient's UUID.

---

## Prenatal Checkups

### Regular Prenatal Checkup

Available from **phase P1**. Gives a summary of pregnancy progress and estimated timings.

---

**Cover**
- Mother's name, price, and date

---

**Page 2 — Pregnancy Status**
- **Current phase** — pregnancy phase it's currently at (P1–P9)
- **Projected final phase** — the max phase it will reach based on the number of babies being carried
- **Pregnancy health** — numerical value representing the overall state

---

**Page 3 — Timing**
- **Days elapsed** — since the pregnancy started (P0)
- **Days until next phase** — how long until advancing to the next one
- **Days until birth** — estimate of when labor will start

---

### Ultrasound

Available from **phase P2**. The ultrasound shows the womb's internal state and info on each baby separately.

---

**Cover**
- Mother's name, price, and date

---

**Page 2 — Womb Status**
- **Number of babies** — how many babies are in the womb right now
- **Stretch level** — NONE, SLIGHT, MODERATE, SEVERE, or DANGEROUS
- **Uterine irritation level** — accumulated irritation percentage (0% to 100%)
- **Womb fully irritated?** — Yes or No. If at 100% and the `IRRITATED_WOMB` effect is active, labor can trigger
- **Womb state** — HEALTHY (0–3 babies), STRESSED (4–10), OVERLOADED (more than 10)

---

**One page per baby:**
- Baby's **gender**
- **Species** (HUMAN, ZOMBIE, CREEPER, ENDER, SLIME)
- **Creature type** (HUMANOID or MONSTER)

> A pregnancy with 6 babies generates an ultrasound with **8 pages total**: cover + womb summary + 6 individual pages.

---

### Paternity Test

Available from **phase P2**. Compares the father registered in the pregnancy against all male players connected to the server at the time of the consultation.

---

**Cover**
- Mother's name, price, and date

---

**Page 2 — Summary**
- **Number of candidates analyzed** — all male players online at that moment
- **Overall result** — "father known" if any candidate is positive, "father unknown" if no one matches

---

**One page per connected male candidate:**
- Candidate's UUID
- Candidate's name
- Result: **POSITIVE** or **NEGATIVE**

> If the father isn't connected to the server at the time of the consultation, they'll show up as "father unknown" even if the pregnancy has a registered father. The father must be online to appear in the list.

---

### Baby Reduction

**Scientific Illager exclusive.** Available from **phase P3**, only when the womb is in **OVERLOADED** state (more than 10 babies). This procedure removes the excess babies and brings the womb back to STRESSED state.

---

**Dynamic cost based on pregnancy phase:**

| Phase | Additional emeralds |
|-------|:-------------------:|
| P3 | 20 |
| P4 | 25 |
| P5 | 30 |
| P6 | 35 |
| P7 | 40 |
| P8 | 45 |
| P9 | 50 |

The further along the pregnancy, the higher the procedure cost.

---

**Cover**
- Mother's name, total price, and date

---

**Page 2 — Procedure Report**
- **Womb state before** — always OVERLOADED
- **Womb state after** — always STRESSED
- **Babies removed** — how many were taken out
- **Babies remaining** — how many are left after the procedure

**Procedure effects:**
- The womb returns to STRESSED state
- The pregnancy **rolls back a phase** to match the new baby count
- A random birth sound plays

> This is the only way out of an overloaded womb without suffering a tear. Without this procedure, when the pregnancy tries to advance to the birth phase with an OVERLOADED womb, the result is lethal.

---

## Checkups for PreggoMobs

PreggoMobs (tamed female mobs) have access to the exact same checkups as players, with the following differences:

| Aspect | Player | PreggoMob |
|--------|--------|-----------|
| Available providers | Villager + Illager | **Scientific Illager only** |
| Who interacts with the provider | The player themselves | The mob's **owner** |
| Books delivered to | Player | Mob's owner |
| Advancement unlocked on completion | `MEDICAL_CHECKUP_TRIGGER` | None |
| Sound on completion | Depends on provider | Always `VINDICATOR_CELEBRATE` |

Books have the same content as for players. The paternity test compares against connected male players the same way.

---

## Costs and Requirements

Service costs are **configurable per individual provider entity** — two Villagers in the same world can have different prices. Costs are stored in the entity and passed to the menu when the session opens.

**Cost range:** 1 to 64 emeralds per service.

**Additional items:** Some services may require a second item on top of emeralds. Currently only baby reduction uses this mechanic, asking for extra emeralds as a second item.

**Conditions for a service to be available:**

| Condition | Affected services |
|-----------|------------------|
| Minimum pregnancy phase not reached | PREGNANCY_STATE, ULTRASOUND_SCAN, PATERNITY_TEST, REMOVAL_OF_EXCESS_BABIES |
| Womb not overloaded | REMOVAL_OF_EXCESS_BABIES (grayed out if no overload) |
| Wrong gender | PREGNANCY_TEST (disabled for male entities) |
| Wrong provider | REMOVAL_OF_EXCESS_BABIES (not available with Villager) |

---

## Provider Comparison Table

| Service | Player + Villager | Player + Illager | PreggoMob + Illager |
|---------|:-----------------:|:----------------:|:-------------------:|
| FERTILITY_STATE | ✅ | ✅ | ✅ |
| PREGNANCY_TEST | ✅ (♀ only) | ✅ (♀ only) | ✅ (♀ only) |
| PREGNANCY_STATE | ✅ from P1 | ✅ from P1 | ✅ from P1 |
| ULTRASOUND_SCAN | ✅ from P2 | ✅ from P2 | ✅ from P2 |
| PATERNITY_TEST | ✅ from P2 | ✅ from P2 | ✅ from P2 |
| REMOVAL_OF_EXCESS_BABIES | ❌ | ✅ from P3 | ✅ from P3 |

---

*Documentation generated from analysis of the Minepreggo source code.*
