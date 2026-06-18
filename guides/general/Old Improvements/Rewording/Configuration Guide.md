# <a id="_dlxqhw8foq8p"></a>__Configuration Guide__

This guide explains all the configurable settings in the Minepreggo mod\. These settings control how long various pregnancy\-related effects last and can be customized in the mod's configuration file\. Players can use the "Configured" mod to modify the \.toml files in the game, or modify it in the files, but it has to close and reopen the game for the changes to take effect, no matter which method is used\.

Quick reminder that Minecraft calculates time in ticks\. Hence, unless stated, all timers used for this guide are in ticks\.

## <a id="_28h4kbf04prq"></a>Fertility Settings

### <a id="_31gz6wlsva6a"></a>Female Fertility

This determines the default fertility of female entities on spawn, including players and other preggo mods\.

There are four levels: Very Low, Low, Normal, Very High\. Default set as Normal\.

### <a id="_7xcq9u1ld3b0"></a>Male Fertility

This determines the default fertility of male entities on spawn, including players and villagers\.

There are four levels: Very Low, Low, Normal, Very High\. Default set as Normal\.

### <a id="_xg0v7modhcs3"></a>Total Ticks Per Menstrual Cycle Days for Female Mobs

This determines the time taken per menstrual cycle day for preggo mobs\.

The value accepted is between 600 to 24000\. Default set as 12000\. This is recommended to be set equally as Total Tick by Days \(see below\)\.

### <a id="_x7ro3henjrnp"></a>Total Ticks Per Menstrual Cycle Days for Player

This determines the time taken per menstrual cycle day for female players\.

The value accepted is between 600 to 24000\. Default set as 12000\. This is recommended to be set equally as Total Tick by Days \(see below\)\.

## <a id="_9vlmd11xb1sh"></a>Misc Settings

### <a id="_9qokbvflgxvh"></a>Baby Creeper Girl Probability

This determines the chance for a baby creeper girl to spawn in place of a creeper girl\.

The value accepted is between 0\.0 to 1\.0, resembling 0% to 100%\. Default set as 0\.1\.

### <a id="_8m9w59xgb0c"></a>Baby Zombie Girl Probability

This determines the chance for a baby zombie girl to spawn in place of a zombie girl\.

The value accepted is between 0\.0 to 1\.0, resembling 0% to 100%\. Default set as 0\.15

### <a id="_wol5hykbdgh0"></a>Enable Preggo Mobs Teleport to Player

This determines if tamed preggo mobs are allowed to teleport to players like other tamable animals\.

The value accepted is simply true and false\. Default set to false\.

### <a id="_9wa5w9ourznr"></a>Enable Spawning Hostile Pregnant Humanoid Creeper Girls

This determines if hostile humanoid creeper girls can spawn being pregnant\. This does not affect normal hostile humanoid creeper girls spawning\.

The value accepted is simply true and false\. Default set to true\.

### <a id="_1t068b18jnai"></a>Enable Spawning Hostile Pregnant Monster Creeper Girls

This determines if hostile monster creeper girls can spawn being pregnant\. This does not affect normal hostile monster creeper girls spawning\.

The value accepted is simply true and false\. Default set to true\.

### <a id="_25l8so180dez"></a>Enable Spawning Hostile Pregnant Monster Ender Girls

This determines if hostile ender girls can spawn being pregnant\. This does not affect normal hostile ender girls spawning\.

The value accepted is simply true and false\. Default set to true\.

### <a id="_3rkzsqsuiasy"></a>Enable Spawning Hostile Pregnant Zombie Girls

This determines if hostile zombie girls can spawn being pregnant\. This does not affect normal hostile zombie girls spawning\.

The value accepted is simply true and false\. Default set to true\.

## <a id="_6790ehnrh0wd"></a>Pregnancy Settings

### <a id="_fl89e28y2r2p"></a>Enable Belly Collisions for Player

This determines if the hitbox of belly is enabled for player models\.

The value accepted is simply true and false\. Default set to false\.

__Warning:__ Setting it to true may cause obstacles such as accidental self\-attacking or unable to fit between narrow spots, but it gives a much more immersive experience\. Enable at your own risks\.

### <a id="_ap6i1fehyflz"></a>Enable Belly Collisions for Preggo Mob

This determines if the hitbox of belly is enabled for preggo mobs’ models\.

The value accepted is simply true and false\. Default set to false\.

### <a id="_pmaim57svdna"></a>Enable Mounting Entities in Later Pregnancy Phases

This determines if the player is allowed to mount and ride on entities when in pregnancy phase 6 onwards\. This includes minecarts\.

The value accepted is simply true and false\. Default set to true\.

### <a id="_4rlb4pd08vo9"></a>Enable Pregnancy by Mob Attack

This determines if the female entities can be impregnated by attacks of zombies, creepers, or endermen\.

The value accepted is simply true and false\. Default set to false\.

### <a id="_ebgvh88kbftg"></a>Max Pregnancy Phase to Use Elytras

This determines on the last pregnancy phase that the player is allowed to use elytra when pregnant\. If the player reaches beyond this phase, elytras can still be put on, but they cannot be used at all\.

The value accepted is between p0 to p8\. Default set to p6\. For players that wish to disable the feature, simply turn it to p8 \(as there’s no phase 9 yet\)\.

### <a id="_w4ztc7qd8rua"></a>Post Birth Lactation Cooldown

This determines the time taken for the milking meter to increase by 1 after giving birth\.

The value accepted is any integer above 100\. Default set as 1200\.

### <a id="_te5v68zax2bu"></a>Pregnancy Healing Amount

This determines the amount of pregnancy health recovered after receiving Pregnancy Healing effect from either potions, natural recovery, or from sleeping\. Every pregnancy phase reduces the actual healing received by 5%\.

The value accepted is between 0 to 100\. Default set as 25\.

### <a id="_5d5hn0hwekge"></a>Pregnancy Hunger Cooldown Config

This determines the cooldown, or to be exact, time taken to add extra hunger during pregnancy\. This simply determines the frequency of extra hunger, not the exact amount of extra hunger, which is dependent on pregnancy phases instead\.

The value accepted is between 10 to 200\. Default set as 20\.

### <a id="_sxmoofezwu9d"></a>Total Pregnancy Days

This determines the total time taken of the entire pregnancy, from start to just before water breaking\.

The value accepted is any integer above 20\. Default set as 50\.

For an immersive experience, change this to 280, which is the average pregnancy period in real time\.

### <a id="_7f60wv4hmgb5"></a>Total Tick by Days

This determines the time taken of one pregnancy day\. Along with Total Pregnancy Days, this set the total pregnancy length\.

The value accepted is between 100 to 24000\. Default set as 24000\.

For an immersive experience, if Total Pregnancy Days is changed for it and all settings down below are as default, change this to 4285\.

### <a id="_i8d0dnnbfgx4"></a>Total Ticks of Belly Rub

This determines the time taken for the belly rub meter to increase by 1 at pregnancy phase 3\. Further phases have a multiplier to shorten the time depending on this phase’s time\.

The value accepted is between 100 to 24000\. Default set as 3600\.

### <a id="_a4mp72rc1r4x"></a>Total Ticks of Cravings

This determines the time taken for the craving meter to increase by 1 at pregnancy phase 1\. Further phases have a multiplier to shorten the time depending on this phase’s time\.

The value accepted is between 100 to 24000\. Default set as 4800\.

### <a id="_d4xc7kbrga13"></a>Total Ticks of Horny

This determines the time taken for the horny meter to increase by 1 at pregnancy phase 4\. Further phases have a multiplier to shorten the time depending on this phase’s time\.

The value accepted is between 100 to 24000\. Default set as 6000\.

### <a id="_h445o46k8fts"></a>Total Ticks of Hungry

This determines the time taken for hunger for preggo mobs before pregnancy\. Pregnancy phases have a multiplier to shorten the time depending on this phase’s time\.

The value accepted is between 100 to 24000\. Default set as 8400\.

### <a id="_gpmeum5rv0gf"></a>Total Ticks of Milking

This determines the time taken for the milking meter to increase by 1 at pregnancy phase 2\. Further phases have a multiplier to shorten the time depending on this phase’s time\.

The value accepted is between 100 to 24000\. Default set as 3600\.

### <a id="_a0cu8lcpchbs"></a>Total Ticks of Post Pregnancy Phase

This determines the cooldown of pregnancy, or to be exact, the duration of Rehabiliation effect\. Entities cannot get pregnant during this period\.

The value accepted is any integer above 100\. Default set as 12000\.

### <a id="_q1umm0mz8vie"></a>Total Ticks of Pregnancy Healing

This determines the time taken for the natural Pregnancy Healing effect\.

The value accepted is between 100 to 24000\. Default set as 6000\.

This is removed since 1\.3\.0\.

### <a id="_2j22g7qejxta"></a>Total Tick of Post Partum Lactation

This determines the duration of Maternity effect after giving birth\.

The value accepted is any integer above 100\. Default set as 24000\.

### <a id="_rqkw7xz4t6mw"></a>Total Ticks to Start Pregnancy

This determines the time taken of fertilisation, or to be exact, time taken for pregnancy to start after a successful sex or mob attack\. This does not affect pregnancy through potions, which start after the effect wore off naturally\.

The value accepted is between 100 to 24000\. Default set as 4800\.
