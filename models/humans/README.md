# Humans

Models for the human mobs.

## Female human

The female human uses **the same model as the predefined player in `slim`**:

- Model: [`player/predefined/FullBody/slim`](../player/predefined/FullBody/slim)
- Textures: the **64x192** textures of that same folder.

Unlike the player, the female human does not need the template cut in two: the whole 64x192 texture (vanilla part on top, extra part below) is used as a **single file**. See [`player/predefined`](../player/predefined) for how the texture is organized.

There is no separate female model in this folder because it is the one above.

## Male human

| File | Description |
|---|---|
| [`male/MaleHuman.bbmodel`](male/MaleHuman.bbmodel) | Male human model. |

The male human uses the **vanilla base model with `wide` arms (4 px)** and a regular **64x64** skin. He has no extra parts, so any standard Minecraft skin template works.

## Textures

`_naked` textures are the variant without clothes. They only change the texture, not the model.
