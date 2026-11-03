# Only Extra Model

Blockbench files with **only the parts added by the mod** (breasts, belly, buttocks, female body), without the vanilla player body. The textures here are already split the way the mod reads them, so **no cutting is needed**.

If you want to see the complete player while making a texture, use [`FullBody`](../FullBody) instead. Blockbench cannot assign two textures to one model, so `FullBody` uses a single combined template that must be cut afterwards. This folder avoids that, but you only see the extra parts.

## Download (Mediafire)

 - [Only-Body](https://www.mediafire.com/file/itb74ae2va8fqak/only-body.zip/file)

## Textures

The mod uses **two separate texture files**, because it does not modify the player's base model. Both are included here:

| File | Size | Contents |
|---|---|---|
| `alex.png`, `alex_pN.png` | 64x64 | Main vanilla body texture. |
| `alex_extra.png`, `alex_pN_extra.png` | 64x128 | Extra parts (belly, chest, buttocks, female body). |

`N` is the pregnancy phase, `0` to `10`. Files without it are the non-pregnant body.

Every texture has a `_naked` variant, which is the same texture without clothes (for example `alex_p5_extra_naked.png`). It only changes the texture, not the model.

## Using it

The `.bbmodel` files (`PredefinedPlayerExtra.bbmodel` and `PredefinedPlayerExtraP0` to `P10`) use the `_extra` textures. Paint on them and use the result in game as the extra file, together with the 64x64 main body texture.

If you started from the combined 64x192 template of [`FullBody`](../FullBody), you do not need this folder: cut that template as explained in its README.
