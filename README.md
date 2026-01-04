# Content Warning

## Explicit Pregnancy Content

This mod contains explicit pregnancy-related content and mechanics that are **intended only for people with a pregnancy fetish**.

**By installing and using this mod, you acknowledge that you have read this warning and understand the nature of the content. DixMK**

# Minepreggo

A Minecraft Forge mod for version 1.20.1.

<img width="1280" height="720" alt="minepreggo" src="https://github.com/user-attachments/assets/75133289-6370-4362-8da3-d4caec69c20f" />

## Technical Specifications

### Core Requirements
- **Minecraft Version:** 1.20.1
- **Forge Version:** 47.4.10 or higher
- **Java Version:** 17 or higher
- **License:** GNU GPLv3

### Implementation Details

**Mixins:**
- Uses SpongePowered Mixin framework (version 0.7.+)
- Mixin configuration: `mixins.minepreggo.json`
- Refmap: `mixins.minepreggo.refmap.json`

**Access Transformers:**
- Custom Access Transformer file: `src/main/resources/META-INF/accesstransformer.cfg`
- Enables modification of restricted member visibility during development

**Mappings:**
- Mapping Channel: Official (Mojang mappings)
- Mapping Version: 1.20.1

### Environment Support

**Client-Server Architecture:**
- The mod functions as both a client-side and server-side mod
- Client features include custom GUIs, animations, and renderers
- Server features include entity management, pregnancy systems, and trading mechanics

**Dedicated Server Compatibility:**
- The mod is apparently compatible with dedicated servers
- However, full testing on dedicated servers has not been completed
- Use with caution in production server environments

### Build System

- **Build Tool:** Gradle with MinecraftForge Gradle plugin (6.0+)
- **IDE Support:** Eclipse and IntelliJ IDEA compatible
- **Artifact Name:** minepreggo
- **Group ID:** dev.dixmk.minepreggo

### Project Structure

```
src/main/
├── java/dev/dixmk/minepreggo/
│   ├── world/          # Game world systems and entities
│   ├── client/         # Client-side rendering and GUIs
│   ├── network/        # Network synchronization
│   ├── init/           # Registry initialization
│   └── ...
├── resources/
│   ├── META-INF/accesstransformer.cfg
│   ├── data/           # Datapack resources
│   ├── assets/         # Client-side assets
│   └── mixins.minepreggo.json
```

## Development Notes

- Uses Mixin for non-invasive code modifications
- Access Transformers allow modification of protected/private fields in Minecraft classes
- Supports both single-player and multiplayer environments
