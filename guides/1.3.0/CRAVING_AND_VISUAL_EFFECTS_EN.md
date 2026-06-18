# Cravings and Visual Effects — Minepreggo

> Non-technical documentation for the pregnancy craving system and symptom visual effects.  
> Version: Minecraft Forge 1.20.1

---

## Table of Contents

1. [Craving System (CRAVING)](#craving-system-craving)
   - [When It Shows Up](#when-it-shows-up)
   - [Satisfying the Craving](#satisfying-the-craving)
   - [Ignoring the Craving](#ignoring-the-craving)
   - [Severity Penalty](#severity-penalty)
2. [Symptom Visual Effects](#symptom-visual-effects)
   - [Morning Sickness — Green Overlay](#morning-sickness--green-overlay)
   - [Horny — Red Overlay](#horny--red-overlay)
   - [Camera Distortion](#camera-distortion)

---

## Craving System (CRAVING)

### When It Shows Up

The `CRAVING` symptom can activate starting at **phase P1** of the pregnancy. While active, the game assigns a specific craving type tied to the species of the babies being carried. The symptom has an **internal counter** representing how much is left to satisfy — when it hits zero, the symptom goes away.

---

### Satisfying the Craving

To reduce the craving counter, the player needs to eat a food that the pregnancy recognizes as **valid**. After eating it, the game calculates how much the counter drops:

- If the food is **generic human** type → the item's full gratification is applied
- If the food is **species-specific** (non-human) → gratification is reduced by the item's own penalty

```
human food:         full gratification
species food:       gratification × (1 - item penalty)
```

When the counter hits zero after eating, the `CRAVING` symptom automatically deactivates and pregnancy symptoms are re-evaluated.

> Only items that implement the mod's craving system reduce the counter. Eating a valid vanilla food that isn't a recognized craving item has no effect on the counter.

---

### Ignoring the Craving

If the player **eats any invalid food** while `CRAVING` is active, the game **cancels normal Minecraft nutrition** and applies reduced values instead based on the symptom's **current severity**.

The food is still consumed, but it feeds you less than you'd expect.

---

### Severity Penalty

The higher the CRAVING symptom's severity, the bigger the nutrition and saturation reduction when eating foods that don't satisfy the craving:

| Severity | Nutrition reduction | Saturation reduction |
|:--------:|:-------------------:|:--------------------:|
| 0 | −2 points | −10% |
| 1 | −4 points | −20% |
| 2 | −6 points | −30% |
| 3 | −8 points | −40% |
| 4 | −10 points | −50% |

The resulting nutrition never drops below **1 point** even if the penalty exceeds the food's base value.

The player also gets a random chat message (one of 3 variants) letting them know the craving wasn't satisfied and reminding them what food they actually want.

> The higher the craving severity, the more important it is to satisfy it: ignoring it at high severity can make it so you don't recover hunger effectively even if you eat normally.

---

## Symptom Visual Effects

Pregnancy symptoms and special states have visual on-screen representations that fade in and out gradually. All overlays use smooth interpolation (±0.005 per tick) to avoid jarring pop-ins.

The **maximum intensity** of the overlay depends on the amplifier of the associated potion effect:

```
max intensity = min(0.4 + amplifier × 0.1, 2.0)
```

| Amplifier | Max intensity |
|:---------:|:-------------:|
| 0 | 0.4 |
| 1 | 0.5 |
| 2 | 0.6 |
| 4 | 0.8 |
| 16 or more | 2.0 *(ceiling)* |

---

### Morning Sickness — Green Overlay

Active while the player has the `MORNING_SICKNESS` effect.

**Appearance:** Minecraft's nausea texture with a **green tint** that gets more intense with the effect's severity. The texture starts zoomed in at 2× and shrinks to normal size as intensity rises.

**Behavior:** blended additively on top of the frame, adding green color to the screen without replacing it. At low intensities it's barely noticeable; at high intensities it clearly tints the image green.

---

### Horny — Red Overlay

Active while the player has the `HORNY` effect.

**Appearance:** mod-custom texture with a **red tint** applied with the same intensity logic as the green overlay. Same scaling and additive blending behavior.

---

### Camera Distortion

On top of the green overlay, the `MORNING_SICKNESS` symptom adds a **3D geometric distortion** to the world view. The screen sways and warps progressively.

The distortion has two simultaneous components:

**Sway:** the camera gently rotates around a diagonal axis, producing a rocking motion. Sway speed increases with the effect's amplifier:

| Amplifier | Approximate speed |
|:---------:|:-----------------:|
| 0 | slow (~2.7°/tick) |
| 1 | moderate (~3.2°/tick) |
| 2 | noticeable (~4.0°/tick) |
| 4 or more | max (~5.1°/tick) |

**Horizontal stretch:** the higher the intensity, the more the world image stretches horizontally. At low intensity it produces a mild dizzy effect; at high intensity it visibly warps the perspective.

Both effects fade out gradually when the symptom ends, the same way they faded in.

---

*Documentation generated from analysis of the Minepreggo source code.*
