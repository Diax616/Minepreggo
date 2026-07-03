# **Configuration Guide**

This guide explains all the configurable settings in the Minepreggo mod v1.3.1.

These settings control how long various pregnancy-related effects last and can be customized in the mod's configuration file. Players can use the "Configured" mod to modify the .toml files in the game, or modify it in the files, but it must close and reopen the game for the changes to take effect, no matter which method is used.

Quick reminder that Minecraft calculates time in ticks. Hence, unless stated, all timers used for this guide are in ticks.

## Client Specific Settings

These are enabled on the client side and does not affect other players.

### Enable Moans

_This is added since v1.3.0._

This determines if the player (if female) and PreggoMobs may have moan sounds during pregnancy pains or birth.

The value accepted is simply true and false. Default set to true.

### Enable Belly Growls

_This is added since v1.3.0._

This determines if the player (if female) and PreggoMobs may have growl sounds during pregnancy symptoms or potion consumptions.

The value accepted is simply true and false. Default set to true.

## Fertility Settings

### Female Fertility

This determines the default fertility of female players on spawn.

There are four levels: Very Low, Low, Normal, Very High. Default set as Normal.

### Female Mob Fertility

_This is added since v1.3.0._

This determines the default fertility of female PreggoMobs on spawn.

There are four levels: Very Low, Low, Normal, Very High. Default set as Normal.

### Male Fertility

This determines the default fertility of male players on spawn.

There are four levels: Very Low, Low, Normal, Very High. Default set as Normal.

### Male Mob Fertility

_This is added since v1.3.0._

This determines the default fertility of male villagers on spawn.

There are four levels: Very Low, Low, Normal, Very High. Default set as Normal.

### Total Ticks Per Menstrual Cycle Days for Female Mobs

This determines the time taken per menstrual cycle day for PreggoMobs.

The value accepted is between 600 to 24000. Default set as 12000. This is recommended to be set equally as Total Tick by Days (see below).

### Total Ticks Per Menstrual Cycle Days for Player

This determines the time taken per menstrual cycle day for female players.

The value accepted is between 600 to 24000. Default set as 12000. This is recommended to be set equally as Total Tick by Days (see below).

### Enable Force Fertilization Eggs

_This is added since v1.3.0._

This determines if the sex events always guarantee impregnation.

The value accepted is simply true and false. Default set to false.

## Misc Settings

### Baby Creeper Girl Probability

This determines the chance for a baby creeper girl to spawn in place of a creeper girl.

The value accepted is between 0.0 to 1.0, resembling 0% to 100%. Default set as 0.1.

### Baby Zombie Girl Probability

This determines the chance for a baby zombie girl to spawn in place of a zombie girl.

The value accepted is between 0.0 to 1.0, resembling 0% to 100%. Default set as 0.15

### Enable Preggo Mobs Teleport to Player

This determines if tamed PreggoMobs are allowed to teleport to players like other tamable animals.

The value accepted is simply true and false. Default set to false.

### Enable Spawning Hostile Pregnant Humanoid Creeper Girls

This determines if hostile humanoid creeper girls can spawn being pregnant. This does not affect normal hostile humanoid creeper girls spawning.

The value accepted is simply true and false. Default set to true.

### Enable Spawning Hostile Pregnant Monster Creeper Girls

This determines if hostile monster creeper girls can spawn being pregnant. This does not affect normal hostile monster creeper girls spawning.

The value accepted is simply true and false. Default set to true.

### Enable Spawning Hostile Pregnant Monster Ender Girls

This determines if hostile ender girls can spawn being pregnant. This does not affect normal hostile ender girls spawning.

The value accepted is simply true and false. Default set to true.

### Enable Spawning Hostile Pregnant Zombie Girls

This determines if hostile zombie girls can spawn being pregnant. This does not affect normal hostile zombie girls spawning.

The value accepted is simply true and false. Default set to true.

### Enable Spawning Hostile Pregnant Slime Girls

_This is added since v1.3.0._

This determines if hostile monster slime girls can spawn being pregnant. This does not affect normal hostile monster slime girls spawning.

The value accepted is simply true and false. Default set to true.

## Pregnancy Settings

### Enable Belly Collisions for Player

This determines if the hitbox of belly is enabled for player models.

The value accepted is simply true and false. Default set to false.

**Warning:** Setting it to true may cause obstacles such as accidental self-attacking or unable to fit between narrow spots, but it gives a much more immersive experience. Enable at your own risks.

### Enable Belly Collisions for Preggo Mob

This determines if the hitbox of belly is enabled for PreggoMobs' models.

The value accepted is simply true and false. Default set to false.

### Enable Riding Entities in Later Pregnancy Phases

_This is added since v1.3.0._

This determines on the last pregnancy phase that the player is allowed to mount and ride on entities such as horses and PreggoMobs.

The value accepted is between p0 to p9. Default set to p6. For players that wish to disable the feature, simply turn it to p9.

### Enable Mounting Entities in Later Pregnancy Phases

This determines on the last pregnancy phase that the player is allowed to mount and ride on minecarts and boats.

The value accepted is between p0 to p9. Default set to p6. For players that wish to disable the feature, simply turn it to p9.

### Enable Pregnancy by Mob Attack

This determines if the female entities can be impregnated by attacks of zombies, creepers, endermen or slimes.

The value accepted is simply true and false. Default set to true.

### Enable Death by Belly Burst

_This is added since v1.3.0, under the name Enable Overloaded Womb. Renamed since v1.3.1._

This determines if the female entities trigger belly burst if they carried more than what the womb allows.

The value accepted is simply true and false. Default set to false.

### Max Babies in Womb

_This is added since v1.3.1._

This determines the maximum amount of babies that can be carried, ignoring other conditions.

The value accepted is between 10 to 40. Default set to 10.

### Safe Babies Count in Womb

_This is added since v1.3.1._

This determines the maximum amount of babies that can be carried without risks of belly burst

The value accepted is between 10 to 40. Default set to 10.

### Max Pregnancy Phase to Use Elytras

This determines on the last pregnancy phase that the player is allowed to use elytra when pregnant. If the player reaches beyond this phase, elytras can still be put on, but they cannot be used at all.

The value accepted is between p0 to p9. Default set to p6. For players that wish to disable the feature, simply turn it to p9.

### Post Birth Lactation Cooldown

This determines the time taken for the milking meter to increase by 1 after giving birth.

The value accepted is any integer above 100. Default set as 1200.

### Pregnancy Healing Amount Through Time

This determines the amount of pregnancy health recovered after receiving Pregnancy Healing effect from either potions, natural recovery, or from sleeping.

The value accepted is between 0 to 100. Default set as 25. **Setting as** **0 will disable Pregnancy Healing completely.**

### Pregnancy Healing Reduction Per Phase

_This is added since v1.3.0._

This determines the amount of pregnancy healing reduced after every pregnancy phase.

The value accepted is between 0.0 to 1.0. Default set as 0.05. **Setting as** **1.0 will disable Pregnancy Healing completely.**

### Pregnancy Hunger Cooldown Config

This determines the cooldown, or to be exact, time taken to add extra hunger during pregnancy. This simply determines the frequency of extra hunger, not the exact amount of extra hunger, which is dependent on pregnancy phases instead.

The value accepted is between 10 to 200. Default set as 20.

### Total Pregnancy Days

This determines the total time taken of the entire pregnancy, from start to just before water breaking.

The value accepted is any integer above 20. Default set as 50.

For an immersive experience, change this to 280, which is the average pregnancy period in real time.

### Total Tick by Days

This determines the time taken of one pregnancy day. Along with Total Pregnancy Days, this set the total pregnancy length.

The value accepted is between 100 to 24000. Default set as 24000.

For an immersive experience, if Total Pregnancy Days is changed for it and all settings down below are as default, change this to 4285.

### Total Ticks of Belly Rub

This determines the time taken for the belly rub meter to increase by 1 at pregnancy phase 3. Further phases have a multiplier to shorten the time depending on this phase's time.

The value accepted is between 100 to 24000. Default set as 3600.

### Total Ticks of Cravings

This determines the time taken for the craving meter to increase by 1 at pregnancy phase 1. Further phases have a multiplier to shorten the time depending on this phase's time.

The value accepted is between 100 to 24000. Default set as 4800.

### Total Ticks of Horny

This determines the time taken for the horny meter to increase by 1 at pregnancy phase 4. Further phases have a multiplier to shorten the time depending on this phase's time.

The value accepted is between 100 to 24000. Default set as 6000.

### Total Ticks of Hungry

This determines the time taken for hunger for PreggoMobs before pregnancy. Pregnancy phases have a multiplier to shorten the time depending on this phase's time.

The value accepted is between 100 to 24000. Default set as 8400.

### Total Ticks of Milking

This determines the time taken for the milking meter to increase by 1 at pregnancy phase 2. Further phases have a multiplier to shorten the time depending on this phase's time.

The value accepted is between 100 to 24000. Default set as 3600.

### Total Ticks of Post Pregnancy Phase

This determines the cooldown of pregnancy, or to be exact, the duration of Rehabiliation effect. The player cannot get pregnant during this period.

The value accepted is any integer above 100. Default set as 12000.

### Total Ticks of Pregnancy Healing

This determines the time taken for the natural Pregnancy Healing effect.

The value accepted is between 100 to 24000. Default set as 6000.

### Total Tick of Post Partum Lactation

This determines the duration of Maternity effect after giving birth.

The value accepted is any integer above 100. Default set as 24000.

### Total Ticks to Start Pregnancy

This determines the time taken of fertilisation, or to be exact, time taken for pregnancy to start after a successful sex or mob attack. This does not affect pregnancy through potions, which start after the effect wore off naturally.

The value accepted is between 100 to 24000. Default set as 4800.