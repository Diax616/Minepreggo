# Jiggle Physics System (AI-generated content)

This guide explains the jiggle physics system for players in the Minepreggo mod.

---

## Overview

The **jiggle physics system** adds dynamic bouncing and movement animations to specific body parts during gameplay. These animations respond realistically to player movement, jumping, and falling.

### Affected Body Parts

- **Breasts** - Bounce during movement and jumping
- **Buttocks** - Bounce during movement and jumping
- **Belly** - Bounces and moves during all pregnancy phases

---

## Important Note

**These jiggle physics are NOT configurable.** The animations and bounce characteristics are built-in and cannot be customized through configuration files or settings. Also, the physical jiggle cannot be disabled and they are not available in the preggo mobs.

---

## Breast Physics

### How They Work

Breasts have realistic jiggling that responds to:
- **Walking and Running** - Continuous bouncing
- **Jumping** - Enhanced bounce with air time
- **Landing** - Impact-based reaction
- **Movement Direction** - Asymmetrical movement based on direction

### Intensity Levels

The mod includes three preset intensity levels, determined by pregnancy progression:

| Level | Characteristics | When Used |
|-------|-----------------|-----------|
| Lightweight | More movement, less restricted | Early pregnancies |
| Normal | Balanced movement and stability | Standard pregnancies |
| Heavyweight | Less movement, more stable | Advanced pregnancies |

### Movement Axes

Breast physics can move along different axes:
- **Y-axis** (vertical) - Primary bouncing
- **X-axis** (horizontal) - Side-to-side sway
- **Z-axis** (front-to-back) - Forward-backward tilt

**Note:** Axis movement is also fixed and not configurable.

---

## Buttocks Physics

### How They Work

The buttocks have independent jiggling that responds to:
- **Walking and Running** - Natural bouncing motion
- **Jumping** - Upward impulse and bounce
- **Landing** - Impact response
- **Player Velocity** - Speed-based intensity

### Intensity Levels

Similar to breasts, buttocks have preset intensity levels:

| Level | Characteristics |
|-------|-----------------|
| Lightweight | Maximum displacement and movement |
| Heavyweight | Reduced displacement for stability |

---

## Belly Physics

### How They Work

The belly bounces and moves in response to:
- **Walking and Running** - Jiggling motion
- **Jumping** - Upward and downward bounce
- **Player Movement** - Direction-based swaying
- **Pregnancy Phase** - Increases with each phase

### Phase-Based Variations

The belly physics change as pregnancy progresses:

| Phase | Movement Intensity | Rotation | Max Displacement |
|-------|-------------------|----------|------------------|
| P0-P1 | Low | Minimal | 0.7 |
| P2 | Low-Moderate | Minimal | 0.7 |
| P3-P8 | Moderate-High | Increased | 0.7 |

As pregnancy advances, the belly becomes more responsive to movement with increased rotation effects.

---

## Physics Behavior

### Movement Response

All jiggle physics respond to:

1. **Player Velocity** - How fast the player is moving
2. **Vertical Movement** - Jumping and falling
3. **Direction Changes** - Turning and sidestepping
4. **Landing Impact** - Impact when hitting ground

### Spring-Like Animation

The jiggle physics use spring-like behavior:
- **Bounce Back** - Automatic return to neutral position
- **Damping** - Gradual reduction of movement over time
- **Oscillation** - Natural swinging motion

---

## Visual Effects

### What You'll See

- **During Walking** - Gentle, rhythmic bouncing
- **During Running** - More pronounced bouncing
- **During Jumping** - Exaggerated bounce with hang time
- **During Landing** - Quick impact followed by settling
- **While Standing Still** - Minimal movement

### Realism Features

- **Asymmetrical Movement** - Left and right sides move independently
- **Natural Bounce** - Realistic spring-like motion
- **Phase Progression** - Belly becomes more pronounced as pregnancy advances
- **Momentum** - Inertia-based continued movement

---

## Performance Considerations

The jiggle physics system:
- Only affects player character rendering
- Runs on client-side (no server impact)
- Minimal performance impact on most systems
- Designed to work smoothly during gameplay
