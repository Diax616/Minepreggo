# Belly Holder

A big belly is heavy. It slows you down, and walking around with it all day will wreck your
back. The Belly Holder is a leather support belt that takes some of that weight off. You
still move slower than normal, but it's a lot less, and back pain takes way longer to show up.

The catch: it has to fit. A belt made for a small belly won't close around a big one, so
you'll resize it as your pregnancy goes along.

> **Note:** The Belly Holder is **players only**. You can't put one on a tamed mob or any
> other creature, it only goes in your own maternity slot.

---

## Crafting

You start with the smallest size, **Belly Holder (P0)**:

```
[ Ropes  ] [        ] [ Ropes  ]
[Leather ] [Leather ] [Leather ]
[Blaze Rod] [        ] [Blaze Rod]
```

- 2 × **Ropes**
- 3 × **Leather**
- 2 × **Blaze Rod**

**Ropes** are cheap: 2 String in any shape gives you 1 Ropes. You'll want a stack of them,
since they're also what you use to let the belt out later.

---

## Sizes

There are 11 sizes, one for each pregnancy phase, **P0** through **P10**. The size is in the
name: *Belly Holder (P4)* fits a P4 belly.

The belt has to match your belly **exactly**:

| Your situation | What fits |
|---|---|
| Not pregnant | Only **P0** |
| Pregnant, phase P*n* | Only **P*n*** |

Too small won't close. Too big just hangs there and does nothing, so the game won't let you
put it on either way.

There's one hard limit on top of that. At **P10**, if you're carrying **more than 10 babies**,
your belly's past what any belt can hold, so even a P10 Belly Holder won't go on. That goes
for both ways of putting it on, right-click and the Equipment Menu. With 10 babies or fewer, a
P10 belt goes on like any other size.

### Resizing

You don't need a new belt every phase. Adjust the one you've got in a crafting grid (your 2×2
inventory grid works). Put in just the belt plus one tool and nothing else:

| Want to... | Belt + | Result | What happens to the tool |
|---|---|---|---|
| Go up a size | **Ropes** | Next size up (P3 → P4) | The Ropes get used up |
| Go down a size | **Shears** | Next size down (P4 → P3) | You get the shears back, 1 durability lower |

It goes **one step at a time**. To jump from P2 to P5 you do the Ropes craft three times.

Resizing is re-tailoring the belt you already have, not making a new one. It keeps its
**damage and enchantments**, so you can't use it as a free repair.

P0 can't go smaller and P10 can't go bigger. The recipe just won't show up.

> JEI users: both recipes are listed as **Belly Holder Enlarge** and **Belly Holder Downgrade**.

---

## Putting it on

The Belly Holder doesn't go in an armor slot. It has its own **maternity slot**. Two ways to
get it there:

- **Right-click** with the belt in your hand. If you're already wearing one, they swap and the
  old one ends up in your hand.
- Open the **Equipment Menu** (default key: **B**) and drop it into the belly holder slot.

If the size is wrong, you'll get one of these messages above your hotbar:

| Message | Meaning | Fix |
|---|---|---|
| *Your belly is too big to wear this belly holder* | The belt is smaller than your belly | Let it out with Ropes |
| *You can't wear this belly holder because it is not the same size as your belly* | The belt is bigger than your belly | Take it in with Shears |
| *You can't wear this belly holder because it is too large* | You're not pregnant and it isn't P0 | Take it all the way down to P0 |
| *Your belly is too big to wear any belly holder, you carry too many babies inside you* | You're at P10 with more than 10 babies | No fix: no belt fits until the birth |

On a server with some lag, a P10 belt you drop into the Equipment Menu might show up in the
slot for a split second and then pop back out. That's just the server saying no (too many
babies), not a glitch, and you'll see the message right after.

To take it off, open the Equipment Menu and pull it out of the slot.

---

## What it does for you

### Less slowness

Pregnancy slows you down more and more as you get bigger. With the belt on, the slowness
drops by **2 levels**. At the lighter phases that means it's gone completely.

| Phase | Without belt | With belt |
|---|---|---|
| P0–P1 | — | — |
| P2 | Slowness I | **none** |
| P3 | Slowness I | **none** |
| P4 | Slowness II | **none** |
| P5 | Slowness III | Slowness I |
| P6 | Slowness IV | Slowness II |
| P7 | Slowness V | Slowness III |
| P8 | Slowness VI | Slowness IV |
| P9 | Slowness VII | Slowness V |
| P10 | Slowness VIII | Slowness VI |

This doesn't stack with **Zero Gravity Belly**. That effect already takes care of the weight
on its own, so while it's active the belt doesn't change your speed.

### Back pain takes 3× longer

Starting at **P4**, walking around on foot for a long time gives you back pain, and the bigger
you get, the sooner it kicks in. With a Belly Holder on, you can walk **three times as long**
before it starts. It doesn't make you immune, it just buys you a lot more time.

### Looks

The belt shows up on your character, wrapped around your belly, and it matches the size you've
got on. It also holds everything in place, so your belly doesn't bounce while you move.

---

## Durability & care

Every size has **100 durability**.

### Wear

The belt takes damage every time you get hit by something your armor would block. It wears
**faster than armor**: at least 2 points per hit, more for big hits. Expect it to go through
durability quickly in a fight. Damage that ignores armor doesn't touch it.

When it breaks you'll hear it snap and see it fall apart, same as a broken armor piece.

### Repairing

- **Anvil + Leather**, just like leather armor.
- Two damaged belts combined in a crafting grid or anvil, the vanilla way.
- **Mending** (see below).

### Enchanting

At the enchanting table it only accepts **Unbreaking** and **Mending**. Its enchantability is
low (5, about the same as chainmail), so don't expect great rolls.

With **Mending**, XP orbs you pick up repair the belt while you're wearing it, at the same rate
as Mending armor. Your other Mending gear gets repaired first, and the belt uses whatever XP
is left over.

It can also pick up **Curse of Vanishing** from a book on an anvil, if that's something you
want.

### Dyeing

It's leather, so you can dye it. Throw it in a crafting grid with any dye, the same way you'd
dye leather armor. The color shows up on the belt you're wearing, too.

---

## When you die

Your belt drops on the ground with the rest of your stuff. With `keepInventory` on, you keep
it on respawn.

---

## Quick tips

- **Carry Ropes.** When your belly moves to the next phase, the belt you're wearing won't be
  the right size anymore. Let it out one notch and put it back on.
- **Keep shears around after the birth.** Take the belt back down to P0 and it's ready for next
  time.
- **Enchant it with Mending early.** It wears out fast, and Mending is the lowest-effort way to
  keep it alive.
- **It stacks with armor.** The belt has its own slot, so you can wear a full armor set and a
  Belly Holder at the same time.
