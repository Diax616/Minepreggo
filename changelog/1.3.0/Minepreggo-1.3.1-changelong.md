# Release Notes: Version [1.3.1]
**Release Date:** 2026-06-23

## [Monster Creeper Girl]
### Spawn Rate
- **[Pregnant]**
    - Her spawn probability has been reduced by 80%.
- **[Non Pregnant]**
    - Her spawn probability has been reduced by 20%. 
    
### Swell Time
- **[Pregnant]**
    - For each additional phase starting from P3, an additional 20% swelling will be required for her to explode for each additional phase. 
      
### Explosion
- **[Pregnant]**
    - Her explosive power was increased. Since the pregnant versions can no longer spawn as often, I restored a fraction of her explosive power.

## [Monster Slime Girl]
### Riding
- **[Owner takes fall damage when she falls to the ground]**
    - The player no longer takes fall damage.

## [Translations]
### Russian - Русский язык (ru_ru) (by DanMj)
### Korean - 한국어 (ko_kr) (by ahoon12)

## [Dynamic Bellies]
### Activation
- **[Womb Overloaded]**
    - They are activated only when the player has more babies than the “safe” number (10). For each additional baby—and depending on the current stage of pregnancy—the belly model and the belly hitbox increase proportionally for each additional baby.

## [Babies and Belly busrt]
### Config
- **[maxBabiesInWomb]**
    - It determines the max number of babies the uterus can carry.
 - **[safeBabiesCountInWomb]**
    - It defines the number of babies that the player can safely carry without risking death from a belly burst.
- **[enableDeathByBellyBurst]**
    - In case 'currentBabiesCountInWomb' > 'safeBabiesCountInWomb', death by belly burst will occur when the player reaches the maximum stage of pregnancy (P9).

## [Player]
### Synced Settings
- **[Activation]**
    - Pressing the default key “j” will open a GUI displaying the player's own options; these options are synchronized with all clients
- **[Undress]**
    - The player can change their skin from “clothed” to “naked.”
### Predefined Skin for Male
- **[Support]**
    - Male players can use predefined skins (clothed/naked)

## [Potions]
### Baby Duplication
- **[Rework]**
    - The mechanics of the baby duplication potions have been modified; they now primarily take into account the “growth/size” of the babies to be duplicated. The fact that the potion is applied and you don't advance to the next stage of pregnancy doesn't mean it didn't work. If the total duration of the pregnancy was short, the babies being duplicated haven't grown, so you don't advance to the next stages immediately.
### Eternal Pregnancy
- **[Rework]**
	- Its effect has been modified. Previously, it would freeze the current phase of the player/preggo mob; now, it allows time to pass but prevents the entity from going into labor.
## [Animations]
### EnderWoman
- **[Pregnant]**
    - The animation played for the Enderwoman during labor was reworked.
### Belly
- **[Belly Rubs Effect/Symptom]**
    - Animations were added to the belly when the entity exhibits the “belly rubs” symptom.

## [Models]
### Boobs
- **[Maternity]**
    - The initial size of the boobs model is partially increased when an entity enters the postpartum phase. (Player/Preggomobs)

## [Compatibility]
### Waystone
- **[Pregnancy/Female MobEffects]**
    - The effects of pregnancy are no longer eliminated when using a waystone between two dimensions.
- **[Teleport PreggoMobs]**
    - Tamed preggomobs that follow the player can also teleport. Only the 4 preggomobs closest to the player in a range of 8 block can teleport alongside the player.
    
    
## [Womb Irritation Triggers]
### Spicy Food
- **[Chance]**
    - Eating foods categorized as 'spicy' can cause contractions or fetal movement. It can also cause an 'irritated womb' effect if the player is in the final stage of pregnancy and more than half the days have passed in the current phase. It can't cause the water to break.

## [Lying on Bed]
A feature was implemented that allows players to lie down on beds without triggering the sleeping event. 
### Activation
- **[Player]**
    - Hold down the default “Left Alt” key and right-click on the bed to trigger the event. It works the same as in vanilla, but allows the player to turn their head up to 180. Jump to wake up.
- **[BackPain]**
    - If the player remains lying on the bed, the 'back pain' effect will be removed.
- **[PreggoMobs]**
    - They can't use the beds. This feature isn't currently available for them.
## [Back Pain]
### Triggers
- **[Walking/Sprinting]**
    - This pain used to occur randomly. Now it only occurs when the player/preggo mob walks or runs a specific distance. The distance required for it to occur gradually decreases with each stage of pregnancy.
- **[Activation]**
    - It can only be activated starting since phase P4

## [Bugs Fixed]
### Animation
- **[Cloak]**
    - All animations that weren't properly synchronized with the player cloak (water_breaking, prebirth, birth, postbirth, back_pain) have been fixed.
    - The issue that caused the cloak to become static after an animation was applied has been fixed.

### Pregnancy System
- **[Birth]**
    - Birth phase crashes the game if the player got pregnant by sex (other player/villager) has been fixed.
