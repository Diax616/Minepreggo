# Babies

Babies are what you get out of a pregnancy. They crawl around, they drink milk, they grow up
into something bigger, and they remember who their parents are — which turns out to matter a
lot more than you'd think.

This doc covers looking after one from the moment it exists to the moment it isn't a baby
anymore.

---

## Forms

A baby exists in two forms, and you'll switch between them constantly.

**As an entity**, it's a live mob in the world: it crawls around, drinks, grows, picks things
up, and can be hurt or killed.

**As an item**, it's a stack in your inventory — how you carry one around, stash it in a
chest, or trade it. It stacks to 1, so one slot per baby.

The item keeps everything that matters: lineage, gender, milk level, growth progress and
parent names. Put it back down and you get the same baby, with **the same entity UUID**,
picking up exactly where it left off. Nothing is lost in the round trip.

Two things don't survive:

- **Anything it was carrying.** Whatever was in its mouth or on its head is dropped on the
  ground as it becomes an item.
- **Parent names get refreshed, not preserved.** Each time it changes form, the mod re-reads
  the names of any parent still reachable. Rename yourself and the baby catches up.

Babies also fit in the **baby carrier** maternity slot.

### The block form

The baby item is also a **block item**, so it can be placed as a decorative block instead of
spawned as a mob. Same item, two behaviours, picked by how you click — see
[Spawning](#spawning) below.

---

## Lineage

Every baby carries a **mother** and a **father**, recorded permanently the moment it's born.
That lineage isn't cosmetic — it's the thing that decides what you're allowed to do with the
baby, and most of this doc hangs off it.

Being a parent gets you exactly two things:

- **You can open its menu.** Right-click a baby you're a parent of and you get its GUI, where
  you set what it's doing. Anyone else right-clicking gets nothing.
- **You can take custody of it** — see [Custody](#custody).

A parent can be a **player** or a **mob**. The mother is always whoever actually gave birth.
The father is whoever did the impregnating, which may well be a mob.

**A baby whose parents are both unknown is an orphan.** Nobody can open its menu and nobody
owns it, until somebody adopts it.

### Adopting an orphan

You adopt an orphan by feeding it breast milk until it decides it likes you.

Hold the milk, right-click repeatedly. Hearts mean progress, smoke means not yet. Once it's
had enough it rolls the dice, and on a success you become its parent — permanently. Which
parent slot you fill depends on your character's gender: female players become the mother,
everyone else becomes the father.

**Some milk is better than others.** Milk from the baby's own species counts for three times
as much as generic milk, so a zombie baby adopts fastest on zombie milk. Plain human breast
milk always works on any baby, just slower.

**A tamed baby can never be re-tamed.** Once it has a parent, that's settled forever. You
can't steal somebody else's baby by feeding it.

---

## Custody

Custody is **which single parent the baby actually follows around.** Both parents keep the
menu regardless — custody is only about who it's trailing.

For most babies this never comes up, because only one parent is a player and the other is a
mob. A mob parent doesn't compete for custody, so whichever player is on the lineage just
holds it.

**Custody only becomes a real question with baby humans.** A human baby is the only kind that
can come from one player impregnating another, which is the only way both lineage slots end
up holding players. When that happens, the two of them share the baby, and the rules below
decide who has it at any moment. The same rules carry over to the grown human it becomes —
see [Custody](custody.md) for the full picture.

### Taking custody from the other parent

You have to be able to reach the baby, and the rule depends on what it's currently doing.

If the baby is **following** the other parent, it's protected: you can't take it while that
parent is within 16 blocks **of the baby**. Further away than that — or offline, or in
another dimension — and it's fair game.

If the baby is **waiting or wandering**, it isn't protected at all. Either parent can claim
it whenever.

### Handing a baby to its villager father

Right-click a villager while holding a baby item and you can give it to them — but only with
**Baby Humans and Baby Villagers.** Those are the only two babies that do anything when
clicked on a villager. Every other baby item ignores villagers completely; the click just
does nothing.

That makes sense once you see where these babies come from. **Villagers are always male** —
they can only ever father a baby, never carry one — so the mother is always the player. What
changes is the species the baby inherits: sometimes it comes out human, sometimes villager.
Those two are the only babies that can have a villager father, so they're the only ones with
anyone to hand over to.

**Two things have to be true**, and both are checked against the item you're holding:

1. That specific villager is recorded as the baby's **father**.
2. You are recorded as the baby's **mother**.

Get both right and the villager celebrates, takes the baby, and the baby spawns next to them
already tamed and following its new father.

**The villager may also gossip about you.** If it already knew you were pregnant, handing it
the baby confirms the birth and earns you a large positive gossip — which is what improves
its trade prices. This only fires once per villager, and only if it knew about the pregnancy
beforehand.

Get either check wrong and the villager gets visibly unhappy, plays its "no" sound, and tells
you it doesn't recognize the baby. The baby stays in your hand.

**This is a permanent handover, not shared custody.** Once the villager has the baby, you're
erased from its lineage entirely: no menu, no custody, no claim. You can't take it back, and
it stops counting against your tamed mob cap.

Three ways it gets refused:

- **It's not the right villager, or you're not the mother.** The unhappy reaction above.
- **The villager already has more than 3 mobs of its own** within 16 blocks. You get told
  it has too many babies to look after. Note this counts *everything* the villager owns
  nearby, not just babies.
- **It's a baby villager.** They don't take babies, and nothing happens at all.

---

## Spawning

The baby item does two different things depending on how you click, because it's both a spawn
egg and a block item.

| What you do | What you get |
|---|---|
| **Right-click** a block | The live baby mob |
| **Sneak + right-click** a block | The decorative block |
| **Right-click a villager** | Hands the baby over — Baby Humans and Baby Villagers only |

**Sneaking always places the block.** It never falls through to spawning the mob, even if the
block can't be placed there.

**Clicking a villager only does something with a Baby Human or a Baby Villager.** Any other
baby item treats a villager like empty air — see
[Handing a baby to its villager father](#handing-a-baby-to-its-villager-father).

Where the mob appears follows the normal spawn-egg rules: inside the clicked block if it's
something you can stand in like tall grass, otherwise against the face you clicked. Clicking
a villager spawns the baby in a free space next to them instead.

In creative the item isn't consumed; otherwise it is.

### Where babies come from

Besides placing an item you already have, babies enter the world by being **born** — the
normal route — or from a **creative spawn egg** out of the creative menu.

A creative egg carries no lineage, so what it spawns is an **orphan**: no parents, no owner,
nobody can open its menu. It's adoptable like any other orphan.

### What a baby grows into

Every baby is on a growth timer (see [Growing up](#growing-up)). When it fills, the baby is
replaced by its grown-up version:

| Baby | Grows into |
|---|---|
| Baby Human | Female or Male Human, depending on the baby's gender |
| Baby Zombie | Zombie Girl |
| Baby Creeper / Humanoid Creeper | Creeper Girl (monster or humanoid) |
| Baby Ender | Ender Woman |
| Baby Slime / Humanoid Slime | Slime Girl (monster or humanoid) |
| Baby Villager | A regular vanilla Villager |

The grown mob keeps everything: same lineage, same parents, same owner, same
follow/wait/wander setting, and the same entity UUID. It also stays tame if the baby was.

It arrives at the **child** stage, not adult — from there it keeps growing child → young →
adult as a regular mob.

---

## Spawn limits

Two separate things can stop a baby from being placed.

### The server switch

If the server has **`enableBabySpawning`** turned off, placing a baby is refused outright with
a message on your action bar. Nothing gets around it.

### The tamed mob cap

Babies count against the same limit as every other tamed mob you own — 24 by default. See
[Tamed Mob Limits](tamed-mob-limits.md) for the full rules.

What's specific to babies is **who gets billed**:

- **Placing your own baby** from an item charges you a slot. Placing somebody *else's* baby
  doesn't, since it'll never be yours.
- **Adopting an orphan** charges you. You're checked *before* you spend any milk, so a player
  at the cap won't waste bottles on an adoption that could never finish.
- **A creative egg with no lineage** charges nobody. It spawns orphaned, and only costs a
  slot if somebody later adopts it.
- **Handing a baby to its villager father** charges nobody, and refunds you if you were
  paying.

**The slot follows whoever paid, not whoever's holding it.** Hand custody to the other parent
and the original payer keeps being billed. When the baby dies or grows up, that same person
gets the slot back — growing up is a handover, not a free extra mob.

If you're at the cap, you get a message quoting your current count and your actual limit,
including any per-player override set for you.

---

## Feeding

Babies run on breast milk. Right-click with a bottle to feed.

- Each bottle gives **+5 milk**, out of a maximum of **20**.
- Milk drains **one point every 4 minutes** by default (4800 ticks — server-configurable).
  So a full baby lasts about 80 minutes of real time.
- Draining is purely time-based. Running around, swimming, whatever — it doesn't burn milk
  any faster. A full bar always lasts the same wall-clock time.
- Feeding a baby that's already full does nothing and **doesn't waste the bottle.** You get
  a message saying so.

You get your empty bottle back, and your bucket back if you fed it a milk bucket.

### Milk as healing

A hurt baby heals itself, but only while it's **well-fed — 18 milk or more.** At that point
it regenerates 1 health per second, spending a point of milk each time.

Drop below 18 and healing stops entirely. So if a baby's been hurt, top it right up: a
half-full baby won't recover on its own.

### Potions

You can also right-click a baby with a **potion** to apply its effects. Works with anything,
including instant effects like healing and harming. You get the glass bottle back.

Potions aren't food — feeding a potion doesn't touch the milk bar or the taming progress.

---

## Growing up

Every baby is on a timer from the moment it exists. The default is **10 growth days at 12000
ticks each** — 120,000 ticks, or about **100 minutes of real time.** The GUI shows this as a
20-segment bar.

**Feeding speeds it up.** Every bottle adds 200 ticks (10 seconds) to the clock on top of the
milk it restores. Not a huge jump on its own, but it adds up over a baby's life.

**Nothing else advances it.** Sleeping through the night does *not* skip a baby's growth, in
spite of what the config file comment says. The time-skip only moves pregnancy and fertility
along; the baby's growth timer runs purely on real ticks whether you're asleep or not.

---

## Telling a baby what to do

Three movement modes, set from the baby's menu:

- **Following** — trails whoever holds custody. This is the mode a freshly adopted baby
  starts in, and the only one where custody is protected from the other parent.
- **Wandering** — pins the baby to wherever it was standing when you set it and lets it roam
  within 16 blocks. It'll also wander toward familiar faces: a baby zombie drifts toward
  zombies and players, a baby villager toward villagers and iron golems, and so on by
  species.
- **Waiting** — stays put.

**Putting a baby to sleep forces it into waiting.** When it wakes it goes back to following
if a parent's nearby, or wandering if not.

### Panic

Hurt a waiting baby and it panics for 10 seconds, so it doesn't sit there playing its idle
animation while something's attacking it. Hit it again and the timer *stacks* rather than
restarting.

Anything that deals damage from a living creature counts. A parent opening the baby's menu
calms it instantly.

Panic only exists for waiting babies — switch it to following or wandering and it clears.

---

## What hunts babies

Babies can't defend themselves at all — they deal no damage and never fight back — so
anything that decides to attack one is a straight threat. Two vanilla mobs are specifically
taught to hunt them.

| Mob | Goes after | Range |
|---|---|---|
| **Zombies** (and variants) | Baby Humans and Baby Villagers | 20 blocks |
| **Skeletons** (and variants) | Baby Humans only | Default (~16 blocks) |

"Variants" is literal here: husks and drowned count as zombies, and strays and wither
skeletons count as skeletons, so the rule follows the whole family.

A few things worth knowing:

**Zombies hunt from a long way off.** 20 blocks is further than their normal player-spotting
range, and they don't need line of sight to lock on. A baby left outdoors at night will
attract zombies you never see coming.

**Skeletons need line of sight**, so walls actually stop them. Zombies don't care.

**Only human and villager babies are hunted.** Baby zombies, creepers, enders and slimes are
ignored by both — nothing in the mod teaches a vanilla mob to attack them. In practice human
and villager babies are the fragile ones to babysit.

**Being tamed doesn't help.** Having parents, being followed, holding custody — none of it
changes whether a zombie will come for your baby. The only real protection is not leaving it
somewhere reachable.

Everything else that can hurt a baby hurts it the ordinary way: drowning, falling, fire, mob
damage in passing. Those aren't targeted hunts, they're just the world being the world.

**Iron golems and snow golems won't touch babies.** They're taught to attack savage
preggomobs, not babies of any kind.

Since a baby heals itself only while it's well-fed (see [Feeding](#feeding)), a baby that
survives an attack on a low milk bar just stays hurt. Top it up afterwards.

---

## Carrying things

Babies pick items up off the ground and hold them in their mouth.

- One item stack at a time, and it takes the **whole stack**.
- It won't pick up blocks — only items.
- Once its mouth is full it ignores everything else on the ground.
- Scare it and it'll **spit the item out**, throwing it forward.
- Anything it's carrying drops when it dies, and when you pick it up as an item.

**This needs the `mobGriefing` gamerule on.** With `mobGriefing` off, babies silently never
pick anything up. That's a vanilla quirk the mod inherits, not a bug in the mod.

---

## Odds and ends

**Babies can't fight.** They deal no damage and never attack anything, including in defence
of a parent. They also get knocked back harder than a normal mob — see
[What hunts babies](#what-hunts-babies).

**A tamed baby never despawns.** Untamed orphans do, like any wild mob.

**Both parents get told when a baby dies**, wherever they are — as long as death messages are
on in the gamerules.

**Only one parent can have the menu open at a time.** If the other parent already has it
open, your right-click does nothing until they close it.

**A baby with no valid data loads with a default instead of breaking.** If a baby's lineage is
somehow missing, the mod rebuilds what it can from whoever owned it and logs a warning. You
lose the lineage detail, not the baby.
