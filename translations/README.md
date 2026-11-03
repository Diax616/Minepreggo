# Translations

This folder contains the original (English) language file of Minepreggo, so anyone can use it as the base to translate the mod into another language.

## Files

The files are organized in folders by version series (`1.3.x`, `1.4.x`, ...). Each folder holds the original `en_us` file for the latest release of that series, named `en_us_<version>.json`.

**Always use the file from the most recent folder**, since older ones may be missing keys.

## How to translate

1. Copy the latest `en_us_<version>.json` and rename it to your language code, for example `es_mx.json`
   ([list of Minecraft language codes](https://minecraft.wiki/w/Language#Languages)).
2. Translate **only the values**, never the keys.
3. Keep placeholders such as `%s`, `%d` or `%1$s` and formatting codes (`§a`, `\n`) exactly as they are.
4. Place the file in the mod/resource pack at `assets/minepreggo/lang/`.

## Tool: Minecraft Language Tools

Comparing language files by hand is slow when the mod updates. For that I use
**[Minecraft Language Tools](https://github.com/Diax616/Minecraft-Language-Tools)**, which lets you:

- **`missing`**: list the keys that exist in the original file but not in your translation (what you still have to translate).
- **`prune`**: remove from your translation the keys that no longer exist in the original, keeping the ones you already translated.

It has a graphical window (Windows) and a command line (any system with Java 21+).
See the [tool's README](https://github.com/Diax616/Minecraft-Language-Tools#readme) for usage.

Typical workflow when a new version is released (`source` is the latest original file, `target` is your translation):

```
java -jar MCLanguageTools-<version>.jar missing <source> <target> missing.json
java -jar MCLanguageTools-<version>.jar prune   <source> <target> <target>_clean.json
```
