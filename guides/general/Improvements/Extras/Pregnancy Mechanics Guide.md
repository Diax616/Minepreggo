# **Pregnancy Mechanics Guide**

This guide explains how the pregnancy system works in the Minepreggo mod v1.3.1.

_This system contains major changes since v1.3.0, hence the versions below such are abandoned for documentation._

## Overview

For both female players and preggo mobs, the pregnancy starts with a fertilization process, which is done with actions or potions following a condition, followed by a brief period (if natural fertilization), and the pregnancy starts afterwards. This is followed by a set of days, split into several phases depending on the number of babies, with pregnancy symptoms arising and amplifying, giving birth at the end. After giving birth, the mothers have a brief period that cannot be pregnant, and the cycle loops after.

Time used for these steps is in the configuration of the mod. Check the Configuration Guide for more.

## Fertility

The fertility of a female and male is calculated through several factors instead of just a mere timer.

### Female

Adapting the menstrual cycle of real female, both female players and PreggoMobs have a menstrual cycle, which is split into 4 parts; Menstruation, Follicular, Ovulation, and Luteal.

During Menstruation phase, a female cannot be fertilized and instead receiving the Menstruation effect. During Follicular phase, the female enters fertile window, and estrogen of the female builds up in preparation of ovulation. During Ovulation phase, the chance of fertilization is greatly increased after a sex event. During Luteal phase, progesterone builds up to prepare for a new cycle.

Depending on the fertility settings (check Configuration Guide), the length of the cycle differs along with fertilization rate and eggs count.

- In low settings, the fertilization rate is 10%, and the cycle varies between 21 days to 35 days. The fertile window is only 6 days, and only 1 egg will be available.
- In regular settings, the fertilization rate is 50%, and the cycle is consistent 28 days. The fertile window is 10 days, with the last day guaranteed on day 18, and 1-3 eggs will be available.
- In high settings, the fertilization rate is 75%, and the cycle is consistent 26 days. The fertile window is 12 days, with the last day guaranteed on day 18, and 1-2 eggs will be available.
- In very high settings, the fertilization rate is 90%, and the cycle is consistent 24 days. The fertile window is 12 days, with the last day guaranteed on day 18, and 3-4 eggs will be available.

In terms of duration of each phase, Menstruation takes up from day 1 to before fertile window starts, Follicular and Ovulation split evenly from the fertile window, and Luteal takes the rest.

### Male

Taking the real male's condition, both male players and villagers have two attributes; Concentration of sperm, and viability of sperm. Both are related to the fertilization rate and recover over time.

Depending on the fertility settings, the recovery rate and maximum value differ along with fertilization rate.

- In low settings, the fertilization rate is 10%, and the male recovers slowly from sex events or fapping. Sperm concentration caps at 15% while sperm viability caps at 30%.
- In normal settings, the fertilization rate is 50%, and the male recovers normally from sex events or fapping. Sperm concentration caps at 40% while sperm viability caps at 75%.
- In high settings, the fertilization rate is 75%, and the male recovers fast from sex events or fapping. Sperm concentration caps at 70% while sperm viability caps at 90%.
- In very high settings, the fertilization rate is 90%, and the male recovers extremely fast from sex events or fapping. Sperm concentration and sperm viability caps at 100%.

## Impregnation

There are several ways a female entity can become pregnant in this mod:

### Sexual Intercourse

Female players can initiate sex towards male players. Male players, meanwhile, can initiate sex towards any female entities. Both are followed by a short cutscene. Afterwards, players involved will be having a period of Weakness and Hunger II effect. Preggo mobs involved will be having a period of Weakness effect only.

Certain conditions must be met before having sex:

- No hostile mobs (this includes untamed preggo mobs) are in a 16-block range
- The targets must be less than 32 blocks away
- Extra beds must be present in 8 block range
- Either target is not giving birth or having sex
- Either target must have sexual appetite no less than 5

The female entity must also be in fertile window, and the male entity must also have Sperm Concentration at 4% or above.

The final fertilization rate calculated with the average fertilization rate on both, followed by the average health percentage on both, then by the sperm quality, which is the product of concentration and viability of sperm. If the female is in Ovulation phase, the rate is increased by 40% additively.

### Mob Attack

Female players can be impregnated by specific mobs, including zombies (includes husks, drowneds or zombie villagers), creepers, endermen and slimes. They have a 65% chance to be fertilized (guaranteed with Fertile effect) with 1-3 babies while their Fertility Score is above 0.5.

### Villager Sex

For female players, they can initiate sex with villagers, but it works differently; The player must throw food at a villager with a profession to initiate sex. Most other conditions are applied.

After a short cutscene, the player will be having a period of Weakness and Hunger II effect, like sex with players, while the villager will be having a period of Slowness effect instead.

For more information, check Other Species Pregnancy Guide.

### Potion Effects

Acquire Impregnation-type potion effects in any way (potions, arrows or commands) and have them expire will trigger pregnancy.

Unlike other methods, this does not need a brief period for fertilization.

## Fertilization

After any method above, if the process turned out to be successful, the female entities would experience a brief period for the calculation process, during which they will randomly have a brief period of Nausea effect.

This is also where the baby's species, types and genders are selected. For sex, the children have 6:8:3:3 ratio to be fully resembling the mother, fully resembling the father, a mix of mother's species with father's type, and a mix of father's species with mother's type. If either mix is invalid, it is disregarded. The invalid mixes are monster human babies, monster zombie babies, monster villager babies, humanoid Ender Dragon baby.

If the pregnancy is caused by mob attack, one baby is guaranteed to be fully resembling the father. As for the gender, it is split evenly between male and female.

When eggs are fertilized and the traits are selected, it has a 4% chance to split into twins, adding a baby of the exact same species, trait, and gender. The split eggs also have a 4% chance to further split, and so on. The split chance cannot be configured. For PreggoMobs, the chance is increased to 40% if they have the Fertile effect.

After this period, and if the entities do not die in between, they will be having Pregnancy (P0) effect, where the adventure really starts!

## Pregnancy

As pregnancy progresses, the entities will receive several side effects that develop over time.

### Hunger Increase

For players, the exhaustion from actions increases based on pregnancy progression, which in turn increases hunger faster unless idle (which the exhaustion is 0). For PreggoMobs, they have initial hunger, which can be configured, and the hunger increases based on pregnancy progression.

| Phase | Exhaustion (Players) | Increases (PreggoMobs) |
| ----- | -------------------- | ---------------------- |
| 0     | +0%                  | +0%                    |
| 1     | +5%                  | +18%                   |
| 2     | +10%                 | +25%                   |
| 3     | +20%                 | +33%                   |
| 4     | +30%                 | +43%                   |
| 5     | +40%                 | +54%                   |
| 6     | +50%                 | +67%                   |
| 7     | +60%                 | +81%                   |
| 8     | +70%                 | +100%                  |
| 9     | +80%                 | +122%                  |

### Immobility

As the belly grows bigger, the entities experience the weight of it, which in turn reduces their movement speed and attack speed, feeling more gravity and take less knockback. Both players and PreggoMobs are affected.

| Phase | Movement Speed | Attack Speed | Knockback Resistance | Gravity |
| ----- | -------------- | ------------ | -------------------- | ------- |
| 0     | \-0%           | \-0%         | +0%                  | +0%     |
| 1     | \-0%           | \-0%         | +0%                  | +0%     |
| 2     | \-15%          | \-0%         | +0%                  | +10%    |
| 3     | \-27%          | \-2.5%       | +0%                  | +12.5%  |
| 4     | \-30%          | \-10%        | +5%                  | +15%    |
| 5     | \-40%          | \-15%        | +10%                 | +17.5%  |
| 6     | \-45%          | \-20%        | +15%                 | +20%    |
| 7     | \-60%          | \-25%        | +20%                 | +22.5%  |
| 8     | \-75%          | \-30%        | +25%                 | +25%    |
| 9     | \-79%          | \-35%        | +30%                 | +27.5%  |

### Fatigue

Due to the belly's weight, the entities get fatigued after too much moving. The mobility available is reduced as pregnancy progresses.

| Phase | Jumps | Sprinting (s) | Sneaking (s) |
| ----- | ----- | ------------- | ------------ |
| 3     | 50    | 360           | \-           |
| 4     | 45    | 340           | 290          |
| 5     | 40    | 270           | 250          |
| 6     | 35    | 240           | 210          |
| 7     | 30    | 210           | 180          |
| 8     | 25    | 190           | 140          |
| 9     | 20    | 160           | 120          |

### Random Weakness

As the hormones are fluctuating, the players may feel weak randomly. Every interval, there's a 30% chance to get Weakness for a minute. The interval is as such:

| Phase | Interval (s) |
| ----- | ------------ |
| 3     | 205          |
| 4     | 200          |
| 5     | 195          |
| 6     | 190          |
| 7     | 185          |
| 8     | 180          |
| 9     | 175          |

### Equipments and Travel

The swelling of butt due to pregnancy causes leggings to no longer be usable, making knee braces the only protection for legs. The swelling of belly meanwhile renders normal chestplates unusable, making maternity chestplates the best option. It doesn't last long, though, as the belly on phase 5 onwards can't be fit into any chestplate, making belly shields the last option. Both knee braces and belly shields are subpar in protection, making the pregnancy riskier after.

Not only that, but elytra can also no longer be used at later phase, and the belly is too big to fit in a minecart or a boat and is too heavy to ride on mobs. All of these can be configured.

## Symptoms

As pregnancy progresses, the entities will experience symptoms that develop over time.

### Cravings

Experienced at phase 1 onwards, the craving meter will increase overtime, and once it reaches 20 (player) or 16 (preggo mobs), the flavor wanted will be shown, and the entities must eat food of said flavor to gain gratification and reduce the craving meter.

Attack is reduced by 20% on Craving effect triggers, lasting until the craving meter reaches 2 or below.

In v1.3.0, the nutrition of other foods is also reduced by 2 points and saturation of food is reduced by 10%, increased by the level of severity.

Severity is 0 on start, increases to 1 on phase 4, 3 on phase 5, and 4 on phase 6 onwards.

The symptom trigger increases as follows:

| Phase | Increases |
| ----- | --------- |
| 1     | 0%        |
| 2     | 11%       |
| 3     | 18%       |
| 4     | 25%       |
| 5     | 33%       |
| 6     | 43%       |
| 7     | 54%       |
| 8     | 67%       |
| 9     | 81%       |

For more information, check Cravings Guide.

### Lactation

Experienced at phase 2 onwards, the milking meter will increase overtime (which swells the breasts), and entities need to be milked with glass bottles, which reduce the meter by 5. When it reaches 13, Lactation effect triggers, where movement speed is reduced by 10%, lasting until it reaches 5 or below. Chestplates are not usable at all (except belly shields) during the Lactation effect due to the swollen breasts.

The symptom trigger increases as follows:

| Phase | Increases |
| ----- | --------- |
| 2     | 0%        |
| 3     | 33%       |
| 4     | 43%       |
| 5     | 54%       |
| 6     | 67%       |
| 7     | 81%       |
| 8     | 100%      |
| 9     | 122%      |

### Belly Rubs

Experienced at phase 3 onwards, the belly rub meter will increase overtime, and the entities need to rub their belly, which reduces the meter by 4. When it reaches 12, Belly Rubs effect triggers, where luck is reduced by 100%, lasting until it reaches 8 or below.

The symptom trigger increases as follows:

| Phase | Increases |
| ----- | --------- |
| 3     | 0%        |
| 4     | 43%       |
| 5     | 54%       |
| 6     | 67%       |
| 7     | 81%       |
| 8     | 100%      |
| 9     | 122%      |

### Horny

Experienced at phase 4 onwards, the horny meter will increase overtime, and once it reaches 17, Horny effect will trigger, which doubles the detection range of mobs. This is removed after sex, where horny meter also reduces to 0.1 on start, increases to 4 on phase 5, and to 5 on phase 6 onwards.

Severity is 0 on start, increases to 3 on phase 5, and 4 on phase 6 onwards.

The symptom trigger increases as follows:

| Phase | Increases |
| ----- | --------- |
| 4     | 0%        |
| 5     | 54%       |
| 6     | 67%       |
| 7     | 81%       |
| 8     | 100%      |
| 9     | 122%      |

## Pains

As pregnancy progresses, the entities will experience pregnancy pains randomly that develop over time.

### Severity

Just like pregnancy symptoms, pregnancy pains also have severity, which is calculated with phases.

Severity is 0 on phase 3, increased to 1 on phase 4, increased to 3 on phase 5, and increased to 4 on phase 6 onwards.

What is different, however, is that pregnancy pains are triggered either randomly or from specific acts (for fetal movement and contractions). The duration also differs, with a variation of 30% after calculations.

| Act                        | Severity Boost |
| -------------------------- | -------------- |
| None                       | 0%             |
| Eating spicy food          | 10%            |
| Exhausted (from movements) | 20%            |
| Sex event                  | 30%            |
| Being damaged              | 40%            |

Severity cannot be above 4. Each level of severity also increases duration of pregnancy pain by 25%.

### Morning Sickness

Experienced at phase 1 onwards, every tick, there's a chance for natural Morning Sickness effect to trigger, which is accompanied by a green lint effect of the same duration, like nausea. Attack speed is reduced by 25% during this period.

The base duration is 60 seconds, increased by 10%/20% on phase 1 and 2, respectively. The rate is as follows:

| Phase | Rate per tick |
| ----- | ------------- |
| 0     | 0.1%          |
| 1     | 0.2%          |
| 2     | 0.15%         |
| 3     | 0.1%          |
| 4     | 0.08%         |
| 5     | 0.07%         |
| 6     | 0.06%         |
| 7     | 0.05%         |
| 8     | 0.04%         |
| 9     | 0.03%         |

A significant duration reduction can be seen in phase 4 onwards.

### Fetal Movement

Experienced at phase 3 onwards, every tick, there's a chance for natural Fetal Movement effect to trigger, where the babies are rapidly moving in the belly. Carrying non-human babies also increases fetal movement rate. Movement speed and attack speed reduced by 10% while babies are moving, increased to 15% and 20% on severity 3 and 4, respectively.

| Phase | Rate per tick |
| ----- | ------------- |
| 3     | 0.15%         |
| 4     | 0.275%        |
| 5     | 0.3%          |
| 6     | 0.3125%       |
| 7     | 0.325%        |
| 8     | 0.3375%       |
| 9     | 0.35%         |

The chance is multiplied further by 5 when the pregnant entity is riding another entity in phase 3. Every phase onwards increases the multiplier by 1, up to 11 on phase 9. The chance is quintupled if the Eternal Pregnancy effect is active, and is multiplicative to the chance increase when riding.

The base duration is 60 seconds, increases by 10 seconds per phase, until phase 8 (110 seconds), where it remains consistent in phase 9, making the final base duration 110 seconds.

Eating spicy food has a 27.5% chance to trigger fetal movement for 60 seconds, with chance increased by 2.5% per phase onwards. Doing sex, meanwhile, is guaranteed to trigger fetal movement for 60 seconds.

### Contraction

Experienced at the last phase only, every tick, there's a 0.25% chance for natural Contraction effect may trigger, where the pregnant entities may experience random pain. Movement speed is and attack speed is reduced by 10% while the womb is contracting, increased to 15% and 20% on severity 3 and 4, respectively.

The chance is multiplied by 8 when riding in phase 4. Every phase increases the multiplier by 1, up to 13 on phase 9.

The base duration is 90 seconds, with no changes except variations, as with other pains.

Eating spicy food has a 40% chance to trigger contraction for 90 seconds, with chance increased by 5% per phase onwards. Doing sex, meanwhile, is guaranteed to trigger contraction for 80 seconds.

### Back Pain

Experienced at phase 4 onwards, Back Pain effect may trigger, where the pregnant entities may experience pain from weight. Movement speed is reduced by 25% and attack speed is reduced by 15% when experiencing back pain.

Back pain triggers after 260 seconds of moving, reduced by 25 seconds per phase onwards (17.5 seconds per phase for PreggoMobs). The base duration is 120 seconds, increased by 24 seconds per phase since phase 6.

Note that triggering back pain is considered from exhaustion and not natural reaction.

_Since v1.3.1, the timer is reset upon lying on bed._

### Belly Pressure

A side effect of other pregnancy pain, equipping a just fitting chestplate while experiencing fetal movement on phase 5 onwards will cause Belly Pressure effect to be active for 20 seconds, damaging the entity and movement speed and attack speed is further reduced by 30%.

## Outcome

### Birth

If the pregnant entity survived the entire pregnancy with pregnancy health always being above 1, and does not have an overloaded womb, they will experience water breaking for 60 seconds, where movement speed is reduced by 30% and attack is reduced by 20%. This is followed by a pre-birth phase where the womb contracts for preparation, where the chestplate and leggings that are currently wearing, as well as the items that are currently being held, will be dropped off. This lasts for 30 seconds for phase 4, 40 seconds for phase 5, and increased by 5 seconds for every phase onwards. After that, the birth process begins.

For PreggoMobs, the duration depends on the species of babies, with time reduced to 70% with a medical table around. A minimum birth time of 10 seconds is needed, however.

Each baby is calculated independently. If the baby is a monster variant, the time taken is increased by 5 seconds.

| Baby Species | Duration (s) |
| ------------ | ------------ |
| Human        | 25           |
| Zombie       | 25           |
| Creeper      | 20           |
| Ender        | 27.5         |
| Slime        | 20           |

For players, however, they must play a push minigame for giving birth. The player needs to press the designated key at least 3 times per second for a "push" to count. The number of pushes meanwhile depending on the species of the baby.

Each baby is calculated independently. If the baby is a monster variant, another 20% more pushes are needed.

| Baby Species | Pushes |
| ------------ | ------ |
| Human        | 14     |
| Zombie       | 14     |
| Villager     | 28     |
| Creeper      | 17     |
| Slime        | 18     |
| Ender        | 42     |
| Dragon       | 56     |

Afterwards, the entity enters a post birth period, which depends on babies born. Same as giving birth, each baby is calculated independently.

| Baby Species | Duration (s) |
| ------------ | ------------ |
| Human        | 7.5          |
| Zombie       | 7.5          |
| Villager     | 7.5          |
| Creeper      | 5            |
| Slime        | 5            |
| Ender        | 10           |
| Dragon       | 50           |

For the player, they gain the Rehabilitation effect afterwards, where they cannot be pregnant and need time to rest. Movement speed is reduced by 10% and health is reduced by 20% until the effect wears off. All symptoms during pregnancy are also removed, except for milking, which is identified with the Maternity effect (Lactation effect still triggers when condition is met). The player also gains 3 minutes of Weakness effect and 10 minutes of Luck effect.

### Miscarriage

At phase 1 onwards, the entities receive pregnancy damage when taking damage at health below 60%. Base pregnancy damage taken is 1-3 in phase 1, with the upper limit increases by a fixed 1 or 2 or 3 on each phase onwards. If the damage is caused by an explosion, it is increased to 11-16 in phase 1, with the same upper limit increase, and with another increase of 5 on phase 5.

If the pregnancy health reaches 0 at any point, they will experience miscarriage for 40 seconds instead, where attack speed is reduced by 50% and movement speed is reduced by 100%. Afterwards, the entities get their babies as dead fetuses, and gain effects identical to after giving birth, except for Luck effect which is being changed to Unluck effect.

### Early Birth

The womb can get irritated based on actions when the entity is in last phase of pregnancy, and when the days for the last phase are less than 60% remaining.

On an action trigger, 0.9-1.1 irritation will be added, which is multiplied based on the action:

| Action         | Multiplier |
| -------------- | ---------- |
| Spicy food     | 0.9        |
| Pressing belly | 1.1        |
| Slapping belly | 1.3        |
| Sex            | 1.4        |
| Ejaculation    | 1.6        |
| Attack         | 1.8        |

The irritation increases if womb is stretched:

| Stretch Level | Multiplier |
| ------------- | ---------- |
| None          | 0.4        |
| Slight        | 0.8        |
| Moderate      | 1.2        |
| Severe        | 1.4        |
| Dangerous     | 1.7        |

This is then calculated based on how close the entities are to giving birth.

When womb irritation reaches 20, there's a 40% for the entity will receive Irritated Womb effect for 400 seconds if eating spicy food, or 70% if doing sex. If any irritation is received afterwards, the entities will conduct birth earlier. The result will be identical to normal labor.

### Belly Burst

If the pregnant entity reaches the end of phase 9 when too many babies are being carried by one womb, a belly burst will trigger instead, where a small explosion triggers on spot, and the entity's belly pops. This instantly kills the entity (even if holding a Totem of Undying, which wastes it).

Due to natural pregnancies cannot result in a pregnancy with more than 10 babies, this is only achievable with misuse of Baby Duplication effect.