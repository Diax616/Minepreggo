# Predefined Player Textures — Minepreggo

> Non-technical documentation for the per-player custom texture system.  
> Version: Minecraft Forge 1.20.1

---

## Table of Contents

1. [Intro](#intro)
2. [How It Works](#how-it-works)
3. [Folder Structure](#folder-structure)
4. [Texture Catalog](#texture-catalog)
   - [Main Naked Texture](#main-naked-texture)
   - [Base State](#base-state)
   - [Pregnancy States](#pregnancy-states)
5. [Partial Support and Fallback](#partial-support-and-fallback)
6. [Step-by-Step Setup](#step-by-step-setup)
7. [Hot Reload](#hot-reload)
8. [Filename Reference Table](#filename-reference-table)

---

## Intro

The predefined texture system lets you assign a set of custom skins to specific players, identified by their **exact username**. These textures activate automatically when the game renders that player and replace their visual appearance based on their current state: not pregnant, in each pregnancy phase, dressed or undressed.

No mod jar editing needed. All textures go in the `config/` folder of your Minecraft install and load on the fly without restarting the game.

---

## How It Works

When the game starts, Minepreggo registers an **always-active internal resource pack** that exposes the `config/minepreggo/` folder to Minecraft's resource system. This means any PNG file placed there with the correct name is automatically picked up by the game's rendering engine.

When the game is about to render a player, it checks whether a texture set exists for that username. If it does, it uses those textures. If not, it falls back to the default texture set bundled with the mod (based on the **Alex** model).

The system caches the result of each lookup so it doesn't read the disk every frame. The first time the game sees a player with their own textures, it loads them and keeps them in memory until resources are reloaded.

---

## Folder Structure

All textures go inside the mod's config folder, organized by username:

```
.minecraft/
└── config/
    └── minepreggo/
        └── textures/
            └── player/
                └── predefined/
                    └── <username>/
                        ├── <username>_naked.png
                        ├── <username>.png
                        ├── <username>_extra.png
                        ├── <username>_extra_naked.png
                        ├── <username>_p1.png
                        ├── <username>_p1_extra.png
                        ├── <username>_p1_extra_naked.png
                        └── ... (one folder per user)
```

> The folder name and filenames must be **lowercase** and match the in-game username exactly. For example, for player `Steve`, the folder must be named `steve` and the files `steve.png`, `steve_naked.png`, etc.

The `predefined/` folder is created automatically the first time you launch the game with the mod installed.

---

## Texture Catalog

Each player has two visual models layered on top of each other:

| Model | Description |
|-------|-------------|
| **main** (base) | Character's base body |
| **extra** (overlay) | belly, boobs, butt and female body |

Each model has a dressed variant and a **naked** variant, which the mod uses in specific situations that call for it.

---

### Main Naked Texture

```
<username>_naked.png
```

This texture is **single and shared** across all character states (base and every pregnancy phase). It's the naked version of the main model. There's only one for the entire set.

---

### Base State

Textures used when the player **isn't pregnant**:

| File | Description |
|------|-------------|
| `<username>.png` | Normal dressed appearance |
| `<username>_extra.png` | Dressed overlay (second layer) |
| `<username>_extra_naked.png` | Naked overlay |

---

### Pregnancy States

Each pregnancy phase (P0 to P9) has its own set of three textures. The number after the `_p` suffix matches the phase number:

| File | Description |
|------|-------------|
| `<username>_p1.png` | Dressed appearance at phase P1 |
| `<username>_p1_extra.png` | Dressed overlay at phase P1 |
| `<username>_p1_extra_naked.png` | Naked overlay at phase P1 |
| `<username>_p2.png` | Dressed appearance at phase P2 |
| `<username>_p2_extra.png` | Dressed overlay at phase P2 |
| ... | *(continues up to P9)* |

A complete set for a single player is **34 PNG files** in total:
- 1 main naked
- 3 for the base state
- 30 for the 10 pregnancy phases (3 per phase)

---

## Partial Support and Fallback

You don't need to provide all 34 textures. The system is **fully modular**: any slot without its own file automatically inherits the equivalent texture from the default texture set (the Alex model bundled with the mod).

Examples:

- If you only provide `steve.png` and `steve_naked.png`, the game uses those two custom textures and Alex for everything else.
- If you provide textures for P1 through P5 but not P6 through P9, the phases without textures use Alex's automatically.
- You just need **at least one file** for the system to recognize the player as customized.

If the folder exists but is empty, or if the username has no folder at all, the player renders entirely with the default Alex set.

---

## Step-by-Step Setup
If your username contains uppercase letters, you must put them in lowercase (DixMK_MC123 -> dixmk_mc123) in both the folder name and the textures.

1. **Find the mod's config folder**:
   ```
   .minecraft/config/minepreggo/textures/player/predefined/
   ```
   If it doesn't exist, launch the game once with the mod installed — it gets created automatically.

2. **Create a subfolder** with the username in lowercase:
   ```
   predefined/
   └── myusername/
   ```

3. **Drop in the PNG files** with the correct names inside that folder. Start with at least the base texture:
   ```
   myusername/
   ├── myusername.png           ← base skin
   └── myusername_naked.png     ← naked version (optional but recommended)
   ```

4. **Reload resources** in-game (see section below). Textures show up immediately.

> Filenames are case-sensitive on Linux/Mac. On Windows it doesn't matter, but always using lowercase is recommended to keep things cross-platform compatible.

---

## Hot Reload

The mod supports hot-reloading textures. Press **F3 + T** in-game and the resource system restarts, the texture cache clears, and all files in the `predefined/` folder are re-read from disk.

This lets you:
- Add new textures without leaving the game
- Replace existing files and see the result right away
- Test different skin variants during the same session

---

## Filename Reference Table

The table below shows all possible filenames for an example player called `myusername`:

| File | State | Model | Variant |
|------|-------|-------|---------|
| `myusername_naked.png` | All | Main | Naked |
| `myusername.png` | Base | Main | Dressed |
| `myusername_extra.png` | Base | Overlay | Dressed |
| `myusername_extra_naked.png` | Base | Overlay | Naked |
| `myusername_p0.png` | Phase P0 | Main | Dressed |
| `myusername_p0_extra.png` | Phase P0 | Overlay | Dressed |
| `myusername_p0_extra_naked.png` | Phase P0 | Overlay | Naked |
| `myusername_p1.png` | Phase P1 | Main | Dressed |
| `myusername_p1_extra.png` | Phase P1 | Overlay | Dressed |
| `myusername_p1_extra_naked.png` | Phase P1 | Overlay | Naked |
| `myusername_p2.png` | Phase P2 | Main | Dressed |
| `myusername_p2_extra.png` | Phase P2 | Overlay | Dressed |
| `myusername_p2_extra_naked.png` | Phase P2 | Overlay | Naked |
| `myusername_p3.png` | Phase P3 | Main | Dressed |
| `myusername_p3_extra.png` | Phase P3 | Overlay | Dressed |
| `myusername_p3_extra_naked.png` | Phase P3 | Overlay | Naked |
| `myusername_p4.png` | Phase P4 | Main | Dressed |
| `myusername_p4_extra.png` | Phase P4 | Overlay | Dressed |
| `myusername_p4_extra_naked.png` | Phase P4 | Overlay | Naked |
| `myusername_p5.png` | Phase P5 | Main | Dressed |
| `myusername_p5_extra.png` | Phase P5 | Overlay | Dressed |
| `myusername_p5_extra_naked.png` | Phase P5 | Overlay | Naked |
| `myusername_p6.png` | Phase P6 | Main | Dressed |
| `myusername_p6_extra.png` | Phase P6 | Overlay | Dressed |
| `myusername_p6_extra_naked.png` | Phase P6 | Overlay | Naked |
| `myusername_p7.png` | Phase P7 | Main | Dressed |
| `myusername_p7_extra.png` | Phase P7 | Overlay | Dressed |
| `myusername_p7_extra_naked.png` | Phase P7 | Overlay | Naked |
| `myusername_p8.png` | Phase P8 | Main | Dressed |
| `myusername_p8_extra.png` | Phase P8 | Overlay | Dressed |
| `myusername_p8_extra_naked.png` | Phase P8 | Overlay | Naked |
| `myusername_p9.png` | Phase P9 | Main | Dressed |
| `myusername_p9_extra.png` | Phase P9 | Overlay | Dressed |
| `myusername_p9_extra_naked.png` | Phase P9 | Overlay | Naked |

---

*Documentation generated from analysis of the Minepreggo source code.*
