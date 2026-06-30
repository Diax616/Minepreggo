# Release Notes: Version [1.3.3]
**Release Date:** 2026-06-29

## [Naked Default/Vanilla Skin]
### Player Textures
- **[Config]**
    - it can assign a texture for naked state to the player when using Minecraft's default textures (64x64). It uses the same pattern as the predefined textures but changes the folder from 'predefined' to 'default' -> 'textures/player/default/username/username_naked.png'. ONLY one texture.

## [Custom Boobs Size]
### Run Time Synced Config
- **[Player Settings]**
    - Boobs size can be set to one of three sizes (SMALL, MEDIUM, LARGE), the scale can be interpolated to a limited extent between 0.8 and 1.4. It can set when selecting the gender and skin type, or in the player settings menu (press the ‘j’ key), however this last mechanic (key 'j') will be modified and the size can only be changed by a doctor villager or illager.
    
    
## [Config]  
### Pains
- **[Back Pain]**
    - 'totalTicksMovingToStartBackPain' defines the ticks count that the player or preggomob must move (not consecutively) to trigger back pain. 'backPainFactorDueToMovingPerPhase' defines a factor that reduces the value of ‘totalTicksMovingToStartBackPain’ for each stage of pregnancy, starting from P4. It reduces the amount of time the player must move to trigger back pain for each stage of pregnancy.
### Symptoms
- **[Morning Sickness]**
    - The client-side setting ‘enableMorningSicknessDistortion’ blocks the screen distortion caused by morning sickness.

### [Cinematics]
### Sex
- **[Have fun with preggomobs]**
    - All interactions the player can perform in the world are blocked while a cinematic is active.
     
## [Slime Girl]
### Humanoid/Monster
- **[Attribute Armor]**
    - The armor attribute she gains for each level of growth has been increased. 

### [Scientific Illager]
### Services
- **[Bad Omen]**
    - If the player has the 'bad omen' effect, the illager will refuse to provide services.

### [Translation]
### Chinese
- **[zn_ch]**
    - Credits to gd

## [Item]
### BabyItem
- **[Villager]**
    - It can spawn a baby villager by right-clicking. It works similarly to vanilla spawn eggs.

## [Recipe]
### Belly Shield
- **[BellyShield Downgrade]**
    - By using a pair of scissors and a belly shield, you can obtain a smaller version of the item (P5<-P6<-P7<-P8<-P9). The scissors take damage, and the belly shield's damage and enchantments are preserved.

## [Monster/Humanoid Creeper Girl]
### Both
- **[Combat Mode Being Pregnant]**
    - Pregnant creepers will always have the "fight and then explode" combat mode upon spawning.
### Monster
- **[Spawn]**
    - Her spawn probability was increased by 20%.
### Humanoid
- **[Spawn]**
    - She has the possibility of spawning with a sword or hoe iron

## [Bugs]
### Misc
- **[Tamable Humanoid Slime Girl]**
    - If she's not tamed, she can't move on her own. Fixed
- **[Pregnant Zombie Girl]**
    - When She died, she could spawn armored baby zombies or chicken jockey or armored baby zombie girl. Fixed
- **[Baby Villager Item Spawn]**
    - Right-clicking on a villager with the item didn't spawn the baby villager.  Fixed
- **[Pregnany initiation fails - CRITICAL]**
    - The pregnancy didn't initiate correctly when 'totalTicksToStartPregnancy' reached very high values. This could only occur when the pregnancy was induced by sex (villager/player). Fixed
- **[Cinematic freezes - CRITICAL]**
    - The cinematics freeze the player's screen, usually when interacting with Preggomobs. I never verified this and never saw the bug myself. The code was completely rebuilt, and all forms of player interaction were blocked. I don't know if it's fixed.
- **[Player Birth]**
    - The player model's rotation when childbirth is activated has been corrected. It now rotates correctly towards the point where the particles and the baby item spawn.
- **[Es-Mx translation]**
    - The medical checkup books don't generate text if your language is set to Spanish-Mexico. The error was only verified to occur in regular prenatal checkups.  Fixed
- **[Scientific Illager Provokes Raids]**
    - If a scientist illager uses a 'medical table' block and the player has a bad oven, a raid occurs at the scientist illager's position. - Fixed
- **[Female Armor Doesnt Scale]**
    - The armor doesn't scale correctly with the size of baby entities. -Fixed
- **[Preggomobs can't jump blocks when belly collision is activated]**
    - Preggomobs can jump over blocks normally even when using belly collision. -Fixed
- **[Belly collision escalate infinitely]**
    - Collisions could escalate infinitely even if it wasn't in the final pregnancy stage (P9) by having more than 10 babies. Now they can only escalate if it's in the final stage.

## [Misc]
### External
- **[DixMK-Lib-1.0.0]**
    - Creation and integration of the dixmk's library, a collection of common and generic code for MinePreggo and any mods I might create. The library won't be a required dependency for now; it's currently "merged" into minepreggo.
