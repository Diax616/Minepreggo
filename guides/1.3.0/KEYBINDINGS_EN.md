# Registered Keybindings — Minepreggo

> Non-technical documentation for the mod's custom keybindings.  
> Version: Minecraft Forge 1.20.1

---

## Table of Contents

1. [Intro](#intro)
2. [Pregnancy Keys](#pregnancy-keys)
   - [Rub Belly](#rub-belly)
   - [Push During Labor](#push-during-labor)
3. [Ender Keys](#ender-keys)
   - [Teleport with Ender Woman](#teleport-with-ender-woman)
   - [Teleport with Ender Power](#teleport-with-ender-power)
   - [Fire Dragon Fireball](#fire-dragon-fireball)
4. [Interaction Keys](#interaction-keys)
   - [Request Medical Checkup](#request-medical-checkup)

---

## Intro

Minepreggo registers several custom keys in Minecraft's controls menu. All of them are rebindable from **Options → Controls → Key Binds**. Keys only work when no menu is open.

---

## Pregnancy Keys

### Rub Belly

**Condition:** the player must be pregnant at any phase. Not available during **labor** or **miscarriage**.

Pressing the key toggles the belly rubbing animation:

- If the animation **isn't active** → starts the animation for the current pregnancy phase
- If the animation **is already active** → stops it

The animation varies by pregnancy phase; later phases have their own animations that reflect the belly size.

Starting at **phase P3**, continuously rubbing the belly sends periodic updates to the server that affect the pregnancy state.

---

### Push During Labor

**Condition:** only active during labor pain (`BIRTH`). Outside that state the key does nothing.

The push system uses a **press tracker** that requires **3 presses in under 1 second** to register a valid push:

| Result | Effect |
|--------|--------|
| 3 presses in < 1 second | Push completed → adds to birth progress |
| Presses too slow | Push failed → subtracts from the accumulated level |

The push level also decreases automatically over time if the player stops pressing. Keeping a fast, consistent rhythm is key to advancing the birth.

> See [PREGNANCY_SYSTEM.md](PREGNANCY_SYSTEM_EN.md) for more details on the labor system and the number of pushes required per species.

---

## Ender Keys

All three Ender keys are tied to advanced mechanics related to Ender species pregnancies and Dragon power.

### Teleport with Ender Woman

Aim at a visible block within **100 blocks** and press the key to teleport to that position along with your tamed Ender Woman.

The destination is calculated via a raycast from the player's eyes in the look direction. If there's no valid block along that line, the key does nothing.

---

### Teleport with Ender Power

Works the same as the Ender Woman teleport, but uses the **player's own Ender power** instead of the companion. Requires having Ender power charged.

The destination is calculated the same way: 100-block raycast in the look direction.

---

### Fire Dragon Fireball

Fires an explosive fireball in the **exact direction the player is looking**. This ability is tied to the Ender Dragon power.

---

## Interaction Keys

### Request Medical Checkup

**How to use it:** hold this key while **right-clicking** a **Scientific Illager**.

- Without the key → opens the illager's normal item trade menu
- With the key → opens the **medical checkup menu** (fertility or prenatal depending on current state)

If the Scientific Illager doesn't have a valid Medical Table claimed within 16 blocks, they'll show an error message and won't open the checkup menu.

> See [MEDICAL_CHECKUP_SYSTEM.md](MEDICAL_CHECKUP_SYSTEM_EN.md) for more details on available services and their requirements.

---

## Key Summary

| Key | When it works | Effect |
|-----|:------------:|--------|
| **Rub belly** | Pregnant, no active labor | Toggles rubbing animation (varies by phase) |
| **Push during labor** | During BIRTH pain | 3 presses < 1s = valid push |
| **Teleport with Ender Woman** | Always | Teleports to targeted block (<100 blocks) with Ender Woman |
| **Teleport with Ender Power** | With Ender power charged | Teleports to targeted block (<100 blocks) |
| **Fire Fireball** | With Dragon power | Fires explosive projectile in look direction |
| **Request Medical Checkup** | When interacting with Scientific Illager | Opens checkup menu instead of trade menu |

---

*Documentation generated from analysis of the Minepreggo source code.*
