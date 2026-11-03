# Full Body

Blockbench files of the **complete player model**: the vanilla body plus the parts added by the mod (breasts, belly, buttocks, female body). Most users pick these files over the ones in [`OnlyExtraModel`](../OnlyExtraModel) because they want to see the whole player while making a texture, not only the additional part.

## Download (Mediafire)

 - [Slim](https://www.mediafire.com/file/4b1jztml69heyqi/model-slim.zip/file)
 - [Wide](https://www.mediafire.com/file/9med09bnqz0gw64/model-wide.zip/file)

# Warning

The textures you make with these Blockbench files are **not** what the mod reads in game.

## Why the texture has to be cut

The mod does not modify the player's base model, so it uses **two separate texture files**: the vanilla skin and the extra parts.

Blockbench cannot assign two textures to one model. To let you see the full player, these files use a **single 64x192 template** that combines both:

| Area (Y axis) | Size | Contents |
|---|---|---|
| 0 - 63 | 64x64 | Main vanilla body texture (head, body, arms, legs, etc.). |
| 64 - 191 | 64x128 | Extra textures (belly, chest, buttocks, female body). |

That template is only meant to make texture creation easier. **To use it in the game, you must cut the final texture** into the following pieces:

## Main Body (64x64)
The top part of the template, rows 0 - 63.

<img width="64" height="64" alt="alex" src="https://github.com/user-attachments/assets/b196a717-0268-4bea-a8dd-ea609dd51089" />

## Extra (64x128)
The bottom part of the template, rows 64 - 191.

<img width="64" height="128" alt="alex_extra" src="https://github.com/user-attachments/assets/94ca79d7-d62a-49bd-92e5-c02f5d724903" />

## Naked Extra
The same cut for the version without clothes (`_naked`).

<img width="64" height="128" alt="alex_extra_naked" src="https://github.com/user-attachments/assets/1e60c75f-9584-4074-a05e-ca578a16dbe7" />

> Female humans do not need this cut: they use the whole 64x192 template as a single file. See [`humans`](../../../humans).
