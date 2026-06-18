# Visual Effects — Minepreggo

> Non-technical documentation for the pregnancy craving system and symptom visual effects.  
> Version: Minecraft Forge 1.20.1

---

## Table of Contents

1. [Symptom Visual Effects](#symptom-visual-effects)
   - [Morning Sickness — Green Overlay](#morning-sickness--green-overlay)
   - [Horny — Red Overlay](#horny--red-overlay)
   - [Camera Distortion](#camera-distortion)

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
