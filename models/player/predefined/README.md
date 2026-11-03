# Predefined player

Models and textures for the predefined player skin. Each model exists for the non-pregnant body and for every pregnancy phase, `P0` to `P10`.

## Folders

| Folder | Contents |
|---|---|
| [`FullBody`](FullBody) | The complete player in Blockbench (vanilla body plus the mod's parts), so you can see the whole model while painting. Available as `slim` and `wide`. |
| [`OnlyExtraModel`](OnlyExtraModel) | Only the parts the mod adds. |

## How the textures are built

The templates in `FullBody` are **64x192** images made of two pieces:

| Area (Y axis) | Size | Contents |
|---|---|---|
| 0 - 63 | 64x64 | Vanilla texture: head, body, arms, legs, etc. |
| 64 - 191 | 64x128 | **Extra**: breasts, belly, buttocks and the female body. |

The player's base model is not modified by the mod, so for the **player** these two pieces are used as **two separate files**: the vanilla skin (64x64) and the extra (64x128). That is why the template must be cut before using it in game. See the README in [`FullBody`](FullBody) for the exact pieces.

Female humans do not have this restriction. They use the **whole 64x192 texture as a single file**, see [`humans`](../../humans).

## `slim` or `wide`

- `slim`: Alex arms (3 px wide).
- `wide`: Steve arms (4 px wide).

Pick the one that matches the skin you are making. The `slim` model is also the one used by the female human, see [`humans`](../../humans).
