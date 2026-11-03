# Humans

Humans are the grown-up form of a Baby Human. They come in two kinds — **Female Human** and
**Male Human** — and they're the only mobs in the mod you can't get any other way.

They're companions, not livestock. You raise one, you keep it, and that's the whole loop.

---

## Where they come from

**Only from growing up a Baby Human. That's it.**

A Baby Human runs its growth timer, and when it fills it's replaced by a Female or Male
Human depending on the baby's gender. There is no other route. Specifically:

- **They don't spawn naturally.** Not in any biome, not at any light level, not at any time
  of day. Nothing in the mod registers them for world generation or natural spawning, so
  you will never stumble across one.
- **There's no creative spawn egg for them.** Unlike babies, which have a placeable item,
  humans have no item form at all. You can't carry one in your inventory or stash it in a
  chest.
- **They don't breed.** Two humans standing next to each other will never produce anything,
  with any item, ever. See [No reproduction](#no-reproduction) below.

So the only supply is the baby pipeline: get a Baby Human, raise it, and you have a human.
Lose it and you raise another one from scratch.

Operators can summon one directly with the mod's entity command, which is the escape hatch
for testing or fixing a save. That's an operator tool, not a gameplay route.

### Growing up further

Arriving as an adult isn't guaranteed. A human that grew from a baby starts at the **child**
stage and keeps growing on its own:

**child → young → adult**

Each step runs on its own timer, and the total time is split across the two transitions so
the whole childhood matches the configured growth duration rather than doubling it. The
growth bar in its menu shows progress through the current step.

Its **size and health scale with the stage** — a child is visibly smaller and has a lower
maximum health than an adult. It grows into its full body as it matures.

---

## Taming and keeping one

A human that grew from a baby **arrives already tamed and already yours.** You don't tame
it — it inherits everything from the baby it was:

- Same parents on its lineage
- Same custodian
- Same follow / wait / wander setting
- Same entity UUID

The tamed mob slot carries over too. Growing up is a handover, not a new mob, so it doesn't
cost you an extra slot. See [Tamed Mob Limits](tamed-mob-limits.md).

### The menu

Right-click a human you're a parent of to open its menu. From there you get the same kind of
controls a baby has — movement mode, inventory, renaming — plus whatever its own screen
offers.

Humans also carry a **full equipment inventory**. They wear armor, hold weapons, and can use
a bow if you give them arrows. That's a real inventory with slots, not a single held item
like a baby's mouth.

The menu also has a **copy button** that gives you the Human's UUID — normally by printing it
to chat, where clicking the message copies it. On a server with the human registry enabled it
copies a block of web-sync data instead.

### Giving one its own skin

Each Human can be reskinned individually, by dropping textures into a folder named after its
UUID — which is what that button is for. See [Custom Human Skins](human-skins.md).

---

## Custody

Humans are the only grown mobs where custody is a live question, for the same reason baby
humans are: they're the only ones whose lineage can hold **two players**.

The rules are identical to a baby's, and they carry over unchanged when the baby grows up:

- Both parents can open the menu; only the custodian can change what it's doing.
- A **following** human is protected while its custodian is within 16 blocks of it.
- A **waiting or wandering** human can be claimed by the other parent at any time.
- The **Take Custody** button appears in the menu when there's something to claim.

Full details in [Custody](custody.md).

---

## Female Humans

A Female Human has a **menstrual cycle** and can **lactate**, both of which only start once
she reaches the **adult** stage. A child or young human does neither.

When she is lactating, right-clicking her collects **human breast milk** — which is exactly
what you need to raise the next baby. Each collection drops her lactation level, and the
lactation eventually runs out on its own.

Here's the catch, and it's the important one:

**Nothing in the mod starts her lactation.** Lactation normally follows a pregnancy, and a
tamed Female Human has no pregnancy mechanic wired up at all. She has the cycle, she has the
milk-collection interaction, and she has the post-pregnancy state — but no path that puts her
into it.

In practice that means the milking interaction exists but never has anything to give. If you
right-click her expecting milk, nothing happens. Treat her as a companion, not a milk source.

---

## Male Humans

A Male Human has **fertility** in the sense that the data is tracked on him, and that's
where it ends.

He's the straightforward one: a tamed humanoid companion that grows up, wears gear, follows
you around and fights for you.

---

## Behavior and Actions

Humans are the most capable companions in the mod. What one actually does at any moment
depends on the order you gave it — **follow**, **wait** or **wander** — because each order
unlocks a different set of actions.

Combat overrides all three. A human with something to fight stops whatever it was doing.

### What they can always do

No matter the order, a human will:

- **Open and close doors** on its way through
- **Defend itself** when attacked
- **Avenge you** — if something hurts you, or you hurt something, it picks up that target

The revenge part has one exception: a human told to **wait** won't leave its post to fight
your battles. It still defends itself, it just won't walk off after someone who hit you
somewhere else.

### Following

| Action | Detail |
|---|---|
| **Follow you** | Starts moving at 6 blocks, stops at 2 |
| **Dig through terrain** | Only in this mode, and only if you allow it in its menu |
| **Eat** | From its food slot — see [Food](#food) |

Digging is the standout, and it gets its own section below.

### Digging through terrain

If walls or terrain stop a human from reaching you, it tunnels through. This only happens
while **following**, and only if you've allowed block breaking in its menu. A human told to
wait or wander never breaks a block — the wandering one is deliberately kept from tunnelling
out of the area you pinned it to.

**Walking is always tried first.** Digging only starts once the human is more than 5 blocks
away *and* its pathfinding has genuinely failed to find a route. Once it starts, it keeps
going until it's back within 2 blocks of you — that gap is what stops it flickering on and
off halfway through a tunnel.

It digs in **vertical columns** rather than one block at a time: it picks the column in your
direction, clears it top to bottom so it has headroom to walk through, then moves to the next.
If you're directly above or below, it digs straight up or down its own column instead. It
won't reach further than about 2 blocks to break something, so it tunnels rather than sniping
distant blocks.

#### What it can break depends on what it's holding

This is the part that decides whether digging is useful at all:

| What it's holding | What it can break |
|---|---|
| **The right tool** for the block | **Anything**, no hardness limit |
| Wrong tool, or empty-handed | Only soft blocks — hardness **1.2 or less** |

That threshold matters more than it sounds. Dirt, sand, gravel, leaves and wool are soft
enough to claw through bare-handed. **Stone is 1.5, and wood is 2.0** — so an empty-handed
human can't get through a stone wall, a cobblestone tunnel or anything you actually built.

Give it a **pickaxe** and stone, cobblestone, ore and obsidian all become fair game. An **axe**
opens up wood. A **shovel** speeds up dirt and gravel.

**Both hands count.** It checks the main hand first, then the off hand — so a human with a
sword in its main hand and a pickaxe in the off hand still digs stone. Watch out though: the
off hand is also where the shield goes, so you're choosing between digging and blocking.

**The tool sets the speed too**, not just what's possible. Mining follows the same rules a
player's would, **Efficiency included** — a Diamond Pickaxe with Efficiency V chews through
stone far faster than a wooden one.

**Bedrock is never breakable**, tool or not.

> **Note:** this applies to every mod mob that can hold items and dig, not just humans.
> **Humanoid Creeper Girls, Humanoid Slime Girls and Zombie Girls** use the exact same
> digging system, so the same rule holds for them: hand one a pickaxe and it stops being
> stopped by stone. The only difference is how far away they'll start digging — some begin
> at 7 blocks rather than 5.

### Wandering

| Action | Detail |
|---|---|
| **Roam around its anchor** | Stays within 18 blocks of where you set it |
| **Walk back** | If it drifts too far, it returns on its own |
| **Eat** | Yes |

### Waiting

| Action | Detail |
|---|---|
| **Stand still** | That's the whole job |
| **Look around** | The only idle behavior left |
| ~~Eat~~ | **No** — a human told to hold a post won't eat through your rations |

**Waiting doesn't mean helpless.** If something attacks it, it fights back and will chase its
attacker anywhere — but on a **10-second clock**. When that runs out it drops the target and
walks back to its post. So it defends itself and then returns, rather than getting lured away
permanently.

### Untamed

A human with no owner just wanders freely with no radius limit, eats, pauses now and then,
and looks around. In practice you'll rarely see this, since humans arrive already owned.

---

## Food

A human feeds itself, but only from a **dedicated food slot** in its inventory. Not from any
spare slot, not off the ground — that one slot is the whole pantry. Leave it empty and the
human never eats at all.

### What they'll eat

Pretty much anything a sensible person would: **bread, cooked meats, vegetables, fruit, cake,
stew, cookies, golden apples**, and edible items from other mods too.

The rule is a blacklist, not a whitelist. If it's edible and isn't on the banned list below,
a human will eat it.

### What they won't touch

| Category | Examples |
|---|---|
| **Anything raw** | Raw beef, pork, chicken, mutton, rabbit, salmon, cod |
| **Anything toxic** | Pufferfish, spider eye, rotten flesh, poisonous potato, suspicious stew |
| **Creeper food** | Activated gunpowder and all its variants |
| **Ender food** | Chorus fruit and its variants, ender slime jelly, refined chorus shards |
| **Slime food** | Rotten flesh and spider eye variants, stuffed mushroom |
| **Zombie food** | Brains of any kind — villager, illager, witch — and their variants |

The pattern is consistent: **nothing raw, nothing poisonous, and nothing that's another
species' specialty food.** Put raw steak in the food slot and it may as well be empty — cook
it first.

### Feeding one by hand

You don't have to wait for a human to feed itself. **Hold food in your main hand and
right-click it** and it eats straight from your hand, no food slot involved.

This is the faster route, and it's the one to use when you need a human topped up right now —
before a fight, or after one.

A few rules:

- **Only whoever owns it can feed it.** Being a parent isn't enough here; you have to be the
  one currently holding custody. The other parent has to take custody first.
- **The same food list applies.** Anything in the banned table above is refused.
- **A full human won't take it.** At 20 fullness the click does nothing and the food isn't
  consumed, so you can't waste it.
- **You get hearts** when it works.

The difference from self-feeding is what it costs: hand-feeding takes the whole item in one
go, with no chewing time and no off-hand juggling. It's instant.

### Hunger over time

Fullness drains on its own, and **moving burns it faster**:

| What it's doing | Drain rate |
|---|---|
| Standing still | Normal |
| Walking or running | Double |
| Swimming | Quadruple |

A human you march across the world gets hungry noticeably faster than one parked at home.

### When they eat

Two separate triggers, and either one is enough:

| Trigger | Condition | Eats up to |
|---|---|---|
| **Hungry** | Fullness below 10 (out of 20) | — |
| **Hurt** | Health below 60% **and** fullness below 18 | 18 |

The second one is what makes food matter. A wounded human keeps topping itself up well past
the point plain hunger would have stopped it, and that's how it recovers health — indirectly,
since fullness is what drives its regeneration (see [What starving does](#what-starving-does)).

**They don't eat during a fight.** Eating is an idle activity, so a human with a target keeps
its hands on its weapon. It eats afterwards, once things calm down.

**They don't eat while told to wait, either.** A human holding a post won't work through your
rations unattended. If you want it to heal up, put it back on follow or wander.

So a hurt human parked in a corner stays hurt. That's deliberate, but it catches people out.

### The meal itself

It stops, holds the food in its **off hand** (never the main hand, so it never looks disarmed),
chews for about a second with the usual sounds and particles, and burps when done.

It needs that off hand free. Whatever was in it gets parked in its private inventory and
handed back afterwards — but if that inventory is full with nowhere to park, the human simply
won't start eating.

**An interrupted meal wastes the food.** The mouthful is taken out of the stack the moment
eating starts, so if something cuts it short — a fight breaking out, an order change — that
item is gone and no nutrition is gained. Not a big loss on a single bite, but it's why a human
under constant harassment never manages to feed itself.

### What starving does

Letting fullness hit bottom has real consequences, and they arrive in stages:

| Fullness | What happens |
|---|---|
| 14 or more | Regenerates 1 health per second, spending a point of fullness each time |
| Below 14 | **Healing stops entirely** |
| 4 or below | **Gets angry.** You get a message, and it may eventually turn on you |
| 0 | **Turns savage.** No longer obeys you at all |

Going savage is the one that hurts. A savage human ignores your orders, drops its leash, gets
off any mount and wakes up if asleep. It stops being a companion.

The same applies to your other tamed mobs, and it has a knock-on effect: a savage preggomob
starts treating your humans as prey. See
[Your own preggomobs](#your-own-preggomobs).

**You can bring it back**, but only by feeding it up to **12 fullness or more**. Once there it
calms down, returns to following you, and stops targeting you. Hand-feeding is the practical
way to do this, since a savage human won't help itself.

Since healing stops below 14, the useful floor is higher than you'd think: a human hovering
around 10 fullness looks fine but will never recover from a scratch.

---

## Combat

### Melee

The default. Swings once per second with whatever is in its main hand.

### Bow

Give it a **bow and arrows** and it becomes an archer instead:

- Plants itself at **15 blocks** and shoots rather than closing in
- Needs line of sight to start, but keeps its aim for **3 seconds** after losing sight before
  giving up
- **No arrows means no archer.** It falls straight through to melee instead of miming a draw

Its accuracy is fixed and doesn't get worse on lower difficulties, unlike a vanilla skeleton.

### Shield

Put a shield in its **off hand** and it blocks — but only under specific conditions.

**It only raises the shield after being hit.** This is reactive, not precautionary, which
means **the first blow always lands in full**. Vanilla needs the shield up for 5 ticks before
it counts, and the trigger is the hit itself.

**A bow cancels it out.** A human holding a usable bow never blocks — it's an archer instead.
Interestingly, a bow with **no arrows** stops counting as usable, so an archer who runs dry
switches to hiding behind the shield.

**It can swing while blocking.** That's correct vanilla behavior: blocking limits movement
speed, not attacking. Sword plus shield is the combination that gets you both.

**The off hand is contested.** A shield there means no pickaxe there, so a human kitted out
for blocking can only dig with whatever is in its main hand — see
[Digging through terrain](#digging-through-terrain).

Once up, the shield stays up for **2.5 seconds**, and **every new hit resets that timer**. A
sustained beating keeps it raised indefinitely; it only drops once the blows actually stop.

#### Two ways damage still gets through

**From behind.** Only blows arriving from the front are blocked. The human turns to face
whoever is actually hitting it — not necessarily what it's fighting — but it can only face
one direction. **Two attackers will always land hits**, because one of them is behind it.

**A worn-out shield leaks.** This is the mod's own rule, not vanilla's. Once a shield drops
to **25% durability or below**, it stops being a perfect block and lets **25% of the damage
through**. A vanilla shield has 336 durability, so that's the last 84 points.

| Shield condition | What happens |
|---|---|
| Above 25% durability | Blocks completely |
| At or below 25% | Blocks 75%, the rest gets through |
| Broken | No protection at all |

The leaked damage still goes through armor and enchantments normally, so a well-armored human
feels it less. Unbreakable shields never leak — they have no durability to run down.

Two side effects worth knowing. A leaking shield means the human **is** taking damage, so it
starts fighting back where a perfect block would have left it passive. And the leak is
noticeable: it's your cue that the shield is nearly gone and needs replacing, which won't
happen on its own.

### Shield durability

Shields wear out like a player's. Each blocked hit of **3.0 damage or more** costs
`1 + floor(damage)` durability; weaker chip damage is free. Against 7-damage hits that's
roughly 42 blocks before it breaks, with the last stretch leaking as described above.

When it breaks you'll hear it, and the slot empties. Give it a new one — it won't re-equip
by itself.

---

## What hunts humans

Humans are a target in their own right. Plenty of things in the world will go after one on
sight, and that's worth knowing before you send yours somewhere alone.

### Vanilla hostile mobs

**Anything that already hunts players will also hunt humans.** Zombies, skeletons, spiders,
endermen, hostile piglins — the lot. The mod doesn't keep a list; it simply adds humans as
prey to any mob it finds already hunting players, which means mobs from *other* mods get the
same treatment automatically.

They need **line of sight**, and a human sits at the same priority as you do in their eyes.
Whichever of you is closer gets chased.

This is a real step up in danger from the baby stage. A Baby Human is hunted only by zombies
and skeletons; the grown version is hunted by everything. The trade-off is that an adult
wears armor and fights back.

> **Note:** humans are the **only** mod mob treated this way. No other creature in the mod is
> prey to the world at large — your Zombie Girls, Creeper Girls, Slime Girls and Ender Women
> are all ignored by vanilla hostiles, and so are the babies of those species. The handful of
> other rules that exist are narrow and specific: iron and snow golems attack preggomobs only
> once they've gone **savage**, and zombies and skeletons hunt **Baby Humans and Baby
> Villagers** alone.
>
> In other words, being human makes a mob universally hunted in a way nothing else in the mod
> is. Plan escorts accordingly — a Zombie Girl can cross hostile territory unbothered where a
> human cannot.

### The mod's own hostile mobs

Several of the mod's mobs hunt humans too:

| Mob | Hunts humans? |
|---|---|
| **Zombie Girl** | Yes |
| **Creeper Girl** (monster and humanoid) | Yes |
| **Slime Girl** (monster and humanoid) | Yes |
| **Ill** | Yes |
| **Reproduction Witch** | Yes |
| **Ender Woman** | **No** — she only goes after endermites |

These hunt from **20 blocks** and need line of sight. They rank humans *below* players, so if
both are in range they come for you first.

### Your own preggomobs

The tamable versions of those same species carry the same hunting instinct, but it's gated:

> **A tamed preggomob will not attack your humans.** The goal only ever activates while she's
> **untamed** or **savage**.

So under normal conditions your Zombie Girl and your Male Human coexist fine. The moment she
goes savage from starvation, though, she stops being safe to leave around them — she'll treat
your human as prey like any wild one would. Feeding her back up to 12 fullness ends that.

A couple of variants add extra conditions on top: a pregnant one won't hunt while
incapacitated, and zombie girls won't while on fire.

---

## No reproduction

Neither human has any breeding mechanic. Concretely:

- **You can't breed two humans.** There's no pairing, no love mode, no baby from two adults.
- **A Female Human can't get pregnant.** The pregnancy hook exists on her but is
  deliberately empty — nothing calls it, so no pregnancy ever begins.
- **A Male Human can't impregnate anything**, player or mob.
- **They can't be bred with anything else either.** No cross-species pairing of any kind.

---

## Odds and ends

**Both parents get told when a human dies**, wherever they are, as long as death messages
are on in the gamerules. Same as babies.

**They can be given away by handing over the baby, not the adult.** Once a Baby Human has
grown, the villager handover is no longer available — that only works on the item form,
which adults don't have.

**A human with a broken custodian fixes itself.** If one loads from the save with an owner
who isn't on its lineage, that owner is dropped and either real parent can claim it.

**Growth config changes apply immediately.** If the server changes how long growing takes,
a human already part-way through picks up the new duration on its next load rather than
staying pinned to the old one.
