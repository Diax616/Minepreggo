# Release Notes: Version [1.3.6]
**Release Date:** 2026-07-17

## [Translation]
#### Vietnamese
- Credits to 'le9k'

## [Preggomob]
#### Spawn
- They can appear in caves; however, this also allows them to spawn on farms. To prevent “fetus farms”, pregnant preggomobs have a very low chance of spawning inside caves. The Slime Girl is a special case; they can't spawn in caves—it's completely blocked.
#### Taming Rework
- Previously, taming occurred randomly; that is, when feeding preggomobs, there was a 33% chance that the Preggo would be tamed. Now, specific conditions are applied to each Preggomob. After reaching the required affection points, there is a random chance—based on a certain probability—that the preggomob can be tamed each time it receives affection points.

|         | Required  Affection |   Points  By feeding |    Success  Chance (Cave) |     Success Chance (surface) |
|---------|---------------------|----------------------|---------------------------|------------------------------|
| Creeper | 7                   | 1                    | %50                       | %100                         |
| Zombie  | 5                   | 1                    | %50                       | %100                         |
| Ender   | 10                  | 1                    | %100                      | %100                         |
| Slime   | 12                  | 1                    | %100                      | %100                         |

* The Enderwoman has additional conditions: a 10% success rate if she is in the End; she can't be fed if she is aggressive; and ‘minepreggo:refined_chorus_shards’ grants 3 affection points.

* The creeper girl has additional conditions. She can't be fed if she's in a 'swelling' or 'igniting' state.

* The slime girl has additional conditions. She possesses a collective 'memory' of players who have killed a nearby slime girl (Grudge Keeper). This memory spreads to all nearby slime girls. A slime girl can't be tamed if she has this memory. The memory is erased over time or if the player dies near the slime girls that captured them.

## [Monster Creeper Girl]
#### Animations
- New animations for the Creeper, idle, walking, attack, morning sickness etc.

## [Enderwoman]
#### Spawn
- They can spawn naturally throughout the End.
#### Childbirth Animation
- The position of the fetus model was corrected during the childbirth animation.

## [Villager]
#### Conditions for courting
- They will no longer give themselves to just any player who throws bread at them. Using the vanilla player reputation system (gossip), a villager will only agree to have sex with a player if that player has 4 or more good reputation points. In other words, you'd have to purchase at least two full offers. Villagers will reject any player with more than 15 negative reputation points.
#### Discounts
- The reputation points earned after the events “have sex”, “confirm pregnancy”, and “give birth to a child” with villagers have been increased.
#### Trading
- They can no longer sell the potion of gradual pregnancy acceleration.


## [Scientific Illager]
#### Trading
- The number of babies that the Illager can trade has been increased (2 -> 5).
- The villager baby can now be traded for diamonds.
- The amount of emeralds obtained through the exchange of fetuses and breast milk was increased.
- The number of exchanges of enchanted books decreased.
- The time it takes for the Illager to restock was reduced by one minute. (10min -> 9min)
- The number of sold-out offers required to begin restocking the inventory was increased. (3 -> 5)

## [Pregnancy System]
#### Medical Table (Player/Preggomob)
- It reduces the time it takes to give birth by 40%. The player must right-click on the block to claim it. Preggomobs will try to claim it when labor begins.

## [Lying Down On Bed]
#### Preggomob
- Preggomobs can lie down on beds. Pregnant preggomobs with “back pain” can remove the effect in the same way the player does.

<img width="1366" height="768" alt="2026-07-18_10 45 36" src="https://github.com/user-attachments/assets/49c625e8-9439-42fe-a575-35153136474f" />

<img width="1366" height="768" alt="2026-07-18_10 46 26" src="https://github.com/user-attachments/assets/d3b76d49-237c-4348-a15d-adec3ca2aac2" />

#### Player
- Player can rub them belly while lying down

<img width="1366" height="705" alt="2026-07-18_10 48 21" src="https://github.com/user-attachments/assets/7609df3f-f21f-487e-8bbf-0cd1eb98d316" />

## [Bugs]
#### Belly Hitbox
- It forced chunks to load the moment its parent player teleported. Fixed

#### Birth Progress Bar (Player)
- Interoperability was corrected and progress was smoothed. Fixed

#### Childbirth Particle Position (Preggomob)
- The spawning of both the blood particles and the item itself was corrected, especially on the Enderwoman. Fixed.

#### Scientific Illager
- When the illager renews his offers, he randomly had to choose new a group offers (A, B, C). There was a possibility that he'd choose the same type of offers, creating the 'illusion' of always choosing the same offer. Now he'll always choose a different offer than before. Fixed
- When opening a GUI where the illager is the trader, there was a possibility that he would move. Fixed
  
#### Preggomobs AI
- Preggomobs can't switch to the nearest target that attacked them, causing them to always maintain the same target even if another entity is damaging them from a closer position. Now, they will switch to the nearest entity that attacked them. This change only applies to the following Preggomobs: Zombie Girl, Humanoid/Monster Creeper Girl, and Humanoid Slime Girl.

#### Reproduction Witch
- Placing her egg spawn inside a spawner causes a reduction in TPS on the client side. Fixed

