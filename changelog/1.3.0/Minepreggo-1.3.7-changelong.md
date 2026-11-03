# Release Notes: Version [1.3.7]
**Release Date:** 2026-10-29

> **Note:** This changelog doesn't break down every change in detail, it just gives a general overview. For the full details, check the documentation. Also, I'm not listing every single change, I didn't keep track of everything I did, but I'm pretty sure this covers the vast majority of them.

## [Translations]
- Removed the following languages: *ru_su*, *vi_vn*, *zh_cn*.
- Added the French translation (*fr_fr*), credits to *PouletRoyal*.

## [Preggomob]
#### Taming
- Hostile pregnant preggomobs can now be tamed by the player using the food versions they use to reduce their cravings.
- You can give them **any** of their species' foods, no matter the flavor (salty, sweet, sour, or spicy) or what the preggomob is currently craving. These are the same foods used to satisfy their cravings once they're tamed (*CravingRegistry*).

| Hostile preggomob | Foods accepted for taming |
|---|---|
| Creeper girl (*monster*) and humanoid creeper girl (*humanoid*) | *minepreggo:activated_gunpowder_with_salt*, *minepreggo:activated_gunpowder_with_chocolate*, *minepreggo:sour_activated_gunpowder*, *minepreggo:activated_gunpowder_with_hot_sauce* |
| Zombie girl | *minepreggo:brain_with_salt*, *minepreggo:brain_with_chocolate*, *minepreggo:sour_brain*, *minepreggo:brain_with_hot_sauce* |
| Enderwoman (*monster*) | *minepreggo:ender_slime_jelly_with_salt*, *minepreggo:ender_slime_jelly_with_chocolate*, *minepreggo:sour_ender_slime_jelly*, *minepreggo:ender_slime_jelly_with_hot_sauce* |
| Slime girl (*monster*) and humanoid slime girl (*humanoid*) | *minepreggo:rotten_flesh_with_salt*, *minepreggo:rotten_flesh_with_chocolate*, *minepreggo:sour_rotten_flesh*, *minepreggo:rotten_flesh_with_hot_sauce*, *minepreggo:spider_eye_with_salt*, *minepreggo:spider_eye_with_chocolate*, *minepreggo:sour_spider_eye*, *minepreggo:spider_eye_with_hot_sauce* |

##### Creeper girls
- They can only be tamed while they're not ignited or in the middle of exploding.

##### Enderwoman
- An enderwoman won't let you tame her while she's aggressive.
- *minepreggo:refined_chorus_shards* also works for taming her.

## [Humans]
- The first humans, both female and male, are in! In the current versions, *1.3.7* - *1.3.10*, humans can only be obtained by growing up a baby (*minepreggo:human_baby*) in survival. In creative, you can get one with the */summon* command.

## [Impregmob]
- Added the first male mob whose job is to impregnate other entities: the male human (*minepreggo:tamable_male_human*). That said, the male human doesn't have the reproduction mechanic yet (*1.3.7* - *1.3.10*), so no impregmob can get a female player or a preggomob pregnant for now.

## [Pregnancy Phase]
- Added P10 phase.

## [Infertility Witch]

- Eternal pregnancy potion drop chance goes down from 25% to 15% per Looting level. Without Looting the chance is 0%.
- Added a chance to drop birth denial potions, 15% per Looting level. Without Looting the chance is 0%.
- Only one of the two potions can drop at a time.
- She always drops either a pregnancy delay potion (66.7%) or an infertility potion (33.3%).
- The chance to drop a pregnancy harming potion goes from ~12% to ~14.3%.

## [Fertility Witch]

- She always drops either a zombie impregnation potion (58.3%) or an impregnation potion (41.7%).
- She can also drop a pregnancy acceleration potion (42.9%), a pregnancy healing potion (28.6%), or nothing (28.6%).

## [Scientific Illager]

- Added the option to sell the birth denial potion.
- Baby trades now have a max of 8 uses, up from 5.
- The Ender Dragon baby trade no longer hands out the reward inside a shulker box. Now it comes in a *loot bundle* (*minepreggo:loot_bundle*). Right-click it and you get all the items, and the loot bundle is used up.


## [Villager]

- Removed some trades and fixed others for the farmer villager.
- Rebalanced prices and amounts for the farmer and butcher villagers so the villager's buy price is always lower than the sell price. No more making emeralds by buying and selling the same product back and forth.

#### Farmer
- Level 1: removed the chili pepper and lemon buy trades. Sell prices for chili pepper, lemon, and cucumber went up.
- Level 2: removed the cocoa beans sell trade. Prices and amounts for chili pepper, lemon, and cucumber were changed.
- Level 3: no longer sells chili pepper, cocoa beans, or lemon. Now buys chili pepper (3 per emerald), lemon (3 per emerald), and cucumber (4 per emerald).
- Level 4: removed. The cocoa beans and chocolate bar trades moved to level 5.
- Level 5: sells cocoa beans (5 emeralds for 32) and chocolate bars (12 emeralds for 2).

#### Butcher
- Level 1: removed the lemon ice cream. Hot sauce (7 emeralds for 2) and salt (1 emerald for 16) got new prices and amounts.
- Level 2: added chili poppers and changed the price and amount for pickles. Removed hot chicken and lemon ice cream.
- Level 3: only sells hot chicken. Removed the lemon ice pops and pickles.

#### Cleric
- Level 2: sells the *growth* potion (17 emeralds).
- Level 3: sells the splash version of the *growth* potion (20 emeralds).

#### Doctor
- Changed the XP given by some of his trades.


## [Mob Effects]

#### birth_denial
- Added the "birth denial" effect, which stops the water-breaking event from happening. In past versions, the eternal pregnancy effect did the same thing.

#### eternal_pregnancy
- This effect gets its old job back: it stops pregnancy time from advancing. It also keeps the 'pregnancy_delay' and 'pregnancy_acceleration' effects from doing anything.

#### full_of_creepers, full_of_zombies, full_of_enders, full_of_slimes

- All of these effects now use the amplifier, level 1 and level 2. Depending on the level, each effect makes the player react differently to certain events.

#### growth
- Added the instant "growth" effect (*minepreggo:growth*) and its potion (*minepreggo:growth*). It instantly completes the growth of the affected entity:
  - Mod babies: finish their growth timer.
  - Growing mod entities that haven't reached their max stage yet: finish their growth timer.
  - Vanilla mobs in their baby form (animals, villagers, etc.): turn into adults.
- If the entity grows, happy villager particles show up. If it can't grow any further, the effect does nothing.
- Keep in mind that mod babies can only grow up to the YOUNG stage.

## [Babies]
- Added the baby mechanic. Items that represent a baby can now be summoned into the world as a living entity or a block.
- Added these babies: human, zombie, creeper, humanoid creeper, ender, slime, humanoid slime. Babies have a gender, female or male, but only humans actually use it. Everything else is female.
- The ender dragon baby and the humanoid ender woman can't be turned into a living entity, they only exist in block form.
- All babies can only grow up to the YOUNG stage. No baby can reach the ADULT stage.

## [Equipment Menu]
- Added a new menu for equipping equippable items, kind of like armor. You can use it to equip things like the belly holder and babies. The menu will also be used for 'clothing' items, but in versions *1.3.7-1.3.10* the slots for that category are locked. Press *b* by default to open the menu.

## [Belly Holder]
- New item you equip through the mod's own 'equipment' menu, or by right-clicking if the conditions are met. While equipped, it reduces the speed penalty on pregnant entities and delays (triples) the time it takes to get back pain. It doesn't rule out getting back pain, though.

## [Baby Carrier]
- Baby items can be equipped in their block form. This doesn't give the player any effect or change, it's just for storing babies or for cosmetic use.

## [Monster Slime Girl]
- Inventory size increased from 6 slots to 8.

## [Belly Shield]
- Added the P10 phase version of this prenatal armor.
- Changed the equipping rules. The item can only be used if the player or preggomob is pregnant and their pregnancy phase is exactly the same as the belly shield's phase.
- Changed the recipe: it now uses *minepreggo:ropes* instead of *minecraft:string*.

## [Pregnancy Pain]
#### Fatigue
- Added a new prenatal pain: fatigue. It only kicks in from phase P3 onward. This new pain replaces the old penalty system that tracked how long the player ran, how many times they jumped, and how long they crouched. Fatigue is shown as a bar: the emptier it is, the higher the chance of getting the fatigue effect, and the worse the side effects can get, like slowness, weakness, blurry vision, and so on.
- The fatigue effect can only happen if more than 3 bars are empty.
- You can get rid of it by lying down on a bed, same as with back pain.

#### Multiple Pains
- Since the very first version of the mod, the player could only have one prenatal pain active at a time. Now several pains can stack at once. This only applies to 'common' pains: fetal_movement, contraction, back_pain, and fatigue.

## [Player Stats]
- New player stats related to pregnancy, preggomobs, and more: *total_pregnancies*, *total_impregnations*, *babies_birthed*, *impregnation_types*, *dimensions_birthed*, *miscarriage_suffered*, *death_while_pregnant*, *total_pregnancies*, *death_while_pregnant*, *checkups_done_myself*, *prenatal_checkups_on_preggomob*, *species_had_sex_with*, *sex_dimensions*, *breast_milk_species_drunk*, *fertility_witch_killed*, *infertility_witch_killed*, *own_babies_sold*, *select_female_gender*, *select_male_gender*.

## [Commands]
- Changed the */anim* and */preggo* commands. Now every command uses */minepreggo* as the root keyword, the old commands were migrated, *preggo* became *pregnancy*, and */preggo gender* was migrated.
- Added commands for: *pregnancy*, *anim*, *human*, *gender*, *entity*, *stats*, and *tamedlimit*.

## [Configuration]
- The server's *[Misc]* section was renamed to *[Mob]*. Its options didn't change (chance for a creeper or zombie baby to be female, preggomobs teleporting to the player, and natural spawning of hostile pregnant preggomobs). See *Migration*.

#### Mob
- *maxTamedBreedableMobLimit* (24, range 0-128): max number of tamed mobs per player. 0 turns the limit off. You can override it per player (UUID) in *<world>/minepreggo/config/tamed_mob_limits.json*.
- *enableRecruitableHumansRegistry* (false): turns on the registry of recruitable humans. It only counts humans that can use custom skins.

#### Baby (new section)
- *totalTicksPerMilkCycle* (4800, range 100-24000): ticks before a baby loses one milk point.
- *totalTicksPerGrowthDay* (12000, range 100-24000): ticks per baby growth day. Sleeping speeds up growth.
- *totalGrowthDays* (10, range 1-100): days a baby needs to fully grow up.
- *enableBabySpawning* (true): if false, babies can't be spawned in the world with spawn eggs or any other method.

#### Pregnancy
- *totalTicksMovingToStartBackPain*: the default goes up from 4800 to 6000. A config that's already been generated keeps its value.
- The max-phase options for using elytras, riding entities, and mounting vehicles now use P10 to turn the restriction off, instead of P9.

## [Advancements]
#### New
- **Bundle Of Joy** (*first_baby*): place one of your own biological babies in the world for the first time. Starts a new advancement branch.
- **Not Blood, But Family** (*adopt_baby*): earn an orphan baby's trust with breast milk and adopt it. Requires *Bundle Of Joy*.
- **They Grow Up So Fast** (*baby_grows_up*): watch one of your biological babies grow into a child. Requires *Bundle Of Joy*.
- **Full Nursery** (*full_nursery*): place your own biological babies of all 5 types: human, zombie, creeper, ender, and slime. Requires *They Grow Up So Fast*.
- **Womb At Full Capacity** (*womb_at_capacity*): reach phase P10 with 50 babies in the womb. Requires *fertile_mother*.
- **The Full Pregnancy Experience** (*all_the_pain*): suffer all 5 common pains at once (morning sickness, fetal movement, contractions, back pain, and fatigue) in the last stage of pregnancy. Requires *i_am_pregnant*.
- **Bad Mother III** (*bad_mother_iii*): while pregnant, suffer cravings, milking, belly rubs, and horniness all at the same time. Requires *Bad Mother II*.
- **Bad Father III** (*bad_father_iii*): same as *Bad Mother III*, but on a pregnant preggomob whose father is the player. Requires *Bad Father II*.
- **Undead Due Date** (*tame_pregnant_zombie_girl*): tame a pregnant zombie girl at phase P7 or later. Requires *Zombie Tamer*.
- **Ticking Time Bomb** (*tame_pregnant_monster_creeper_girl*): tame a pregnant monster creeper girl at phase P7 or later. Requires *Creeper Tamer*.
- **Hissing Mother-to-Be** (*tame_pregnant_humanoid_creeper_girl*): tame a pregnant humanoid creeper girl at phase P7 or later. Requires *Humanoid Creeper Tamer*.
- **Ender Mother** (*tame_pregnant_monster_ender_woman*): tame a pregnant monster enderwoman at phase P7 or later. Requires *Ender Tamer*.
- **Slime Mom Tamer** (*tame_pregnant_monster_slime_girl*): tame a pregnant monster slime girl at phase P7 or later. Requires *Slime Girl Tamer* (*tame_monster_slime_girl*).
- **Humanoid Slime Mom Tamer** (*tame_pregnant_humanoid_slime_girl*): tame a pregnant humanoid slime girl at phase P7 or later. Requires *Slime Girl Tamer* (*tame_humanoid_slime_girl*).

#### Changed
- **Scientific Illager chain**: the tree was reordered and the XP rewards changed. *Valued Customer* (*empty_one_stock*) -> *Questionable Sponsor* -> *Black Market Customer* -> *Scientist Killer*. Before, *Black Market Customer* and *Scientist Killer* depended directly on *Valued Customer*.
- **Sprint To Birth** (*sprint_to_birth*): the minimum distance to reach your partner drops from 200 to 100 blocks.
- **Bad Mother II** and **Bad Father II** (*bad_mother_ii*, *bad_father_ii*): slime and humanoid slime babies now count when sold too. The dragon baby is recognized by its new item (*minepreggo:baby_ender_dragon*).
- **Good Mother**, **Bad or Good Mother?**, **Know Your Body** (*fertility_checkup*), and **Good Father**: use the new checkup triggers (*self_checkup* and *preggomob_checkup*), which replace the old *medical_checkup*.
- **Serial Mother** (*serial_mother*) and **Stud** (*stud*): now based on the player's stats (*total_pregnancies* and *total_impregnations*) instead of the old triggers.
- Updated the icons for *Magic Pregnancy* (now the impregnation potion), *Select Gender* (*minepreggo_logo* banner pattern), and the dragon baby advancements (*brewing_an_ender_dragon_potion*, *give_birth_a_dragon*, *pregnant_with_a_dragon*), which use the new *minepreggo:baby_ender_dragon* item.

| Advancement | XP 1.3.6 | XP 1.3.7 |
|---|---|---|
| I'm Pregnant? (*i_am_pregnant*) | 30 | 25 |
| Two Lines (*pregnancy_test*) | 20 | 50 |
| Know Your Body (*fertility_checkup*) | 20 | 50 |
| First-Time Mother (*first_time_mother*) | 30 | 100 |
| Magic Pregnancy (*magic_pregnancy*) | 20 | 75 |
| Take Responsibility (*take_responsibility*) | 20 | 50 |
| Stud (*stud*) | 100 | 200 |
| Give Birth By All Species (*give_birth_by_all_species*) | 100 | 300 |
| Interested In The End (*interested_in_the_end*) | 30 | 50 |
| Brewing An Ender Dragon Potion (*brewing_an_ender_dragon_potion*) | 50 | 100 |
| Not In My Womb (*fertility_witch_hunter*) | 50 | 100 |
| Keep It Alive (*infertility_witch_hunter*) | 50 | 100 |
| Bad Mother I (*bad_mother_i*) | 30 | 50 |
| Bad Mother II (*bad_mother_ii*) | 50 | 100 |
| Bad Father I (*bad_father_i*) | 30 | 50 |
| Bad Father II (*bad_father_ii*) | 30 | 100 |
| Valued Customer (*empty_one_stock*) | 100 | 50 |
| Questionable Sponsor (*questionable_sponsor*) | 150 | 100 |
| Black Market Customer (*black_market_customer*) | 50 | 150 |
| Scientist Killer (*scientist_killer*) | 100 | 200 |
| Creeper Tamer (*tame_monster_creeper_girl*) | 30 | 75 |
| Humanoid Creeper Tamer (*tame_humanoid_creeper_girl*) | 30 | 75 |
| Zombie Tamer (*tame_zombie_girl*) | 30 | 75 |
| Ender Tamer (*tame_monster_ender_woman*) | 30 | 75 |
| Slime Girl Tamer (*tame_monster_slime_girl*) | 30 | 75 |
| Slime Girl Tamer (*tame_humanoid_slime_girl*) | 30 | 75 |


#### Removed
- **dimensional_traveler**: travel through the Overworld, the Nether, and the End with a preggomob.

## [Fixed Bugs]

- Knockback and collision between belly collisions only between players wasn't being applied.
- The belly collision size of a monster slime girl or humanoid slime girl didn't scale when the preggomob grew.
- Belly collisions used by a preggomob weren't removed from the world when it died, changed dimensions, or got unloaded with its chunk.
- The hitbox of zombie girl and humanoid creeper girl preggomobs in their baby forms was smaller than it should be.
- A tamed enderwoman teleports when she takes menstruation damage. The same thing happened with pregnancy pain (*pregnancy_pain*) on a tamed pregnant enderwoman.
- Preggomobs could spawn in the mushroom fields biome.
- The game crashes when *minepreggo:belly_burst* damage is applied. It usually happens when the *enableDeathByBellyBurst* config is on and the amount set in the *safeBabiesCountInWomb* config is exceeded.
- Pregnancy pains were getting overwritten when another pain started.
- The *Sprint To Birth* achievement doesn't correctly calculate the distance between the father and the mother, ultimately granting the achievement even when the distance is far less than what is required.

## [Migration]

> **Note:** Migrations will only be available up to version *1.3.10* (final). Starting with version *1.4.0*, migrations will no longer be applied, and any data that hasn't been migrated by then will be lost.

#### No Loss

##### Eternal pregnancy
- In 1.3.6, the *eternal_pregnancy* effect stopped the water-breaking event from happening. In 1.3.7 that mechanic moved to the new *birth_denial* effect, and *eternal_pregnancy* got its original job back (stopping pregnancy from advancing).
- To keep things working the same, the first time a player joins a 1.3.6 world on the new version, if they have the *eternal_pregnancy* effect active, it's automatically swapped for *birth_denial* (level 1, infinite duration). The migration only runs once per player.

##### Pregnancy slowness
- In earlier versions, the pregnancy speed penalty was applied with a permanent modifier on movement speed plus an infinite-duration vanilla *slowness* effect. In 1.3.7, that penalty is handled by its own effect (*pregnancy_slowness*), which would stack with the old leftovers instead of replacing them.
- The first time a player joins on the new version, both leftovers are removed before the pregnancy effect applies the penalty again using the new system. The migration only runs once per player.
- Vanilla *slowness* is only removed if it has an infinite duration. If it comes from a potion or another effect with a limited duration, it's kept. The attack speed modifier isn't touched.

##### Player textures
- Player texture names changed. They no longer repeat the username (the *<username>/* folder already identifies the player), the state word goes first, and the pregnancy phase always goes last (*_pN*).
- When the game starts (and when resources reload with F3+T), files with the old format are automatically renamed inside *config/minepreggo/textures/player/predefined/* and *config/minepreggo/textures/player/default/*:

| 1.3.6 | 1.3.7 |
|---|---|
| *username.png* | *dressed.png* |
| *username_pN.png* | *dressed_pN.png* |
| *username_extra.png* | *dressed_extra.png* |
| *username_pN_extra.png* | *dressed_extra_pN.png* |
| *username_extra_naked.png* | *naked_extra.png* |
| *username_pN_extra_naked.png* | *naked_extra_pN.png* |
| *username_naked.png* | *naked.png* (shared by all states) |

- Files are never overwritten: if the new name already exists, the old file is kept and a warning is logged. Files that already use the new names aren't touched.
- These files are not renamed: *username_pN_naked.png* (the old loader never read them, there's no per-phase naked texture), and any file where *N* isn't a real phase or has leading zeros (for example *_p03*).


#### Data Loss

##### Baby Ender Dragon (block)
A Baby Ender Dragon that was **placed as a block in a 1.3.6 world** has no mother or father in 1.3.7. When the block is broken, the item it drops has no parents.

- The block was renamed from `baby_ender_dragon_block` to `baby_ender_dragon` and is remapped automatically, so the block itself is kept.
- The Baby Ender Dragon has two forms: item and block. In 1.3.6 the item form stored the parents' data (`MotherUUID`), but the block form was a plain block, without a block entity, so that data had nowhere to be saved when the item was placed. Its loot table also dropped a plain item with no data, so breaking the block returned an item without the parents.
- The parents were already lost in 1.3.6 at the moment the item became a block, so there is nothing for 1.3.7 to recover.
- Baby Ender Dragon items that were never placed in 1.3.6 are not affected by this loss; they still carry their mother in the item (see *Baby items* below for the legacy item format).

##### Baby items
- In 1.3.6, baby items only stored the mother's UUID (*MotherUUID*) and, if there was one, the father's (*FatherUUID*) at the root of the item. In 1.3.7, baby items store their data in a versioned structure (*BabyData*, inside *BlockEntityTag*), which also includes gender, species, creature type, the baby's life state, and the parents' names.
- Baby items created in 1.3.6 **are not migrated**: they don't have that data, and support for reading the old format is being removed. These babies lose their mother and father info and act like a baby with no lineage.
- Babies created in 1.3.7 aren't affected.

##### Pregnancy data
- The internal structure of the pregnancy data (NBT) has been modified. Some data may be lost and replaced with generic data.

##### Server configuration
- The *[Misc]* section of the server config file is now called *[Mob]*. Values saved in *[Misc]* **are not migrated**, and these options go back to their defaults: *babyCreeperGirlProbability*, *babyZombieGirlProbability*, *enablePreggoMobsTeleportToPlayer*, and the five *enableSpawningHostilPregnant...* options.
- To keep your custom values, you'll need to copy them over to the *[Mob]* section by hand.

##### Commands
The command syntax changed, so command blocks and functions that use the 1.3.6 syntax stop working and **are not migrated**. You'll have to rewrite them by hand. Permissions (level 2) and the arguments of each subcommand didn't change, except for *gender*.

| 1.3.6 | 1.3.7 |
|---|---|
| `/preggo <subcommand> ...` | `/minepreggo pregnancy <subcommand> ...` |
| `/anim <play\|stop\|list> ...` | `/minepreggo animation <play\|stop\|list> ...` |
| `/preggo gender <target> <gender> [force]` | `/minepreggo gender change <gender> <target> [force]` |

- *pregnancy* subcommands that keep their arguments: `start`, `startat`, `dragon`, `end`, `birth`, `relievewomb`, `pain`, `clearpain`, and `symptom`.
- For *gender*, besides moving to its own command and adding the `change` literal, the argument order changed too: `<gender>` now comes before `<target>`.
- Examples: `/preggo start @p zombie humanoid 2` becomes `/minepreggo pregnancy start @p zombie humanoid 2`, and `/anim play <animation>` becomes `/minepreggo animation play <animation>`.
