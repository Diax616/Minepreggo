# Release Notes: Version [1.3.9]
**Release Date:** 2026-11-16

## [Custody]
- Upon taking custody of a mob, the mob automatically follows the player.

## [Babies]
#### Mob mother
- When a baby is spawned (from its item or with a command), it now looks for its mother if she's a mob. If she's loaded, alive, and within 16 blocks, the baby follows her. Otherwise, it wanders around with no custodian.
- If one of the baby's parents (a player) places it, that player still takes custody, even if the mob mother is nearby.
- While the baby is following its mob mother and she's within 16 blocks, the other parent can't claim custody.
- A baby placed from its item no longer keeps the *waiting* state it had when it was picked up. It now wanders, unless it follows its mob mother or the player who placed it.

## [Belly Rubbing]
- Both hands must be empty to start rubbing the belly (*r* by default). If the player picks up or switches to an item mid-animation, the belly rubbing stops.
- While rubbing the belly, left, right, and middle clicks are ignored.
- When the belly rubbing can't start, a message in the action bar explains why:

| Case | Message |
|---|---|
| The player is holding an item in the main hand or the off hand | *Your hands must be empty to rub your belly* |
| The player is in labor | *You're in labor, you can't rub your belly now!* |
| The player is suffering a miscarriage | *You feel too much pain to rub your belly now...* |

- The empty-hands rule is also checked by the server, which sends the same message if it rejects the belly rubbing.
- Players who aren't pregnant (or are male) don't get any message.

## [Fixed Bugs]
- When another player was rubbing their belly, the local player's belly-rubbing need could be relieved as if they were the one doing it.
- The head and both arms of the enderwoman move too far away from her body during the birthing and post-birth animations.
- The player can't tame a baby born from their tamed preggomobs.
- The gender of preggomobs (mobs whose only gender is female, with the exception of humans) could be male.
- Babies born to a player are marked as non-biological if the pregnancy wasn't the result of sex.