# Custody

When two players are both parents of the same mob, only one of them can actually be holding
it at any moment. That's **custody**: which parent the mob follows and takes orders from.

This doc is about how custody moves between two players. If you just want to know how babies
work generally, that's [Babies](babies.md).

---

## When this matters

Almost never, honestly. Custody only becomes a real thing when **both parents are players**,
and that only happens in one situation: one player got another player pregnant.

That's exclusively a **human** pregnancy, so the mobs where custody is a live question are:

- **Baby Humans**
- **Female Humans and Male Humans** that grew up from a Baby Human

Everything else has a mob somewhere on its lineage. A mob parent doesn't open menus, doesn't
press buttons and doesn't compete — so whichever player is on the lineage just holds the mob
permanently and never thinks about any of this.

The rules below are identical for a baby and for the grown human it turns into. Growing up
doesn't reset custody or change who's eligible.

---

## Parent vs. custodian

These are two different things and it's worth keeping them apart.

**Being a parent** is permanent. It's recorded on the mob's lineage and never changes. Both
parents can always:

- Open the mob's menu
- See its stats, its lineage and its bars
- Press the **Take Custody** button when it's available

**Holding custody** is temporary. Only one parent has it at a time. The custodian is the only
one who can:

- Change what the mob is doing — follow, wait, wander
- Have the mob actually follow them around

If you open the menu without custody, everything is visible but the movement controls are
greyed out. You're a spectator until you take custody.

---

## Taking custody

Open the mob's menu and look for the **Take Custody** button. It only appears when there's
something to take — if you already hold custody, it's hidden.

Whether you're allowed depends on what the mob is currently doing:

| Mob's state | Can the other parent take it? |
|---|---|
| **Following** the current custodian | Only if that custodian is more than 16 blocks from the mob |
| **Waiting** | Yes, always |
| **Wandering** | Yes, always |

So a mob that's actively following someone is **protected**, and a mob that's been parked
isn't. If you want to keep a mob from being taken, keep it on follow and stay near it.

### The 16-block rule

The distance is measured **from the mob to the current custodian** — not from you. Where
you're standing doesn't matter at all; you could be across the world.

A custodian who can't be found at all counts as out of range, so the mob is claimable if the
other parent is:

- Offline
- In another dimension
- In unloaded chunks

Which means in practice: if the other parent logs off, their mob becomes claimable the moment
it's more than 16 blocks from where they left it, or immediately if it was parked.

### A catch with the button

The button's availability is decided **once, when you open the menu**, and it isn't
re-checked while the screen is open. If the other parent walks away while you're staring at
the screen, the button won't suddenly appear — close the menu and reopen it.

It works the other way too: if they walk *back* into range after you opened the menu, the
button is still showing but the claim will be rejected when you press it. Nothing breaks, the
custody just doesn't change. Reopen and you'll see the current answer.

The server always validates the claim for real when you press the button, so the button is
only ever a hint — it can't be used to steal a mob that isn't claimable.

---

## What custody doesn't affect

**The tamed mob slot.** The mob counts against whoever originally *paid* for it, and custody
changes never move that. If you tamed or placed the mob, you keep being billed even after
handing custody to the other parent, and you're the one who gets the slot back when it dies.
See [Tamed Mob Limits](tamed-mob-limits.md).

**Growing up.** A baby that grows into a human keeps the same custodian, the same lineage and
the same movement setting. The slot stays with the original payer too.

**Being a parent.** Losing custody doesn't remove you from the lineage. You can always take
it back later under the same rules.

---

## Losing custody permanently

Custody normally bounces between the two parents forever. There's one way to leave the
lineage for good:

**Handing a baby to its villager father** erases you from the lineage entirely. No menu, no
custody, no claim, ever again. That one's covered in
[Babies](babies.md#handing-a-baby-to-its-villager-father).

There's also a quiet safety mechanism: if a mob loads from the save with a custodian who
isn't actually one of its parents — which shouldn't happen, but can after a bad edit or a
mod change — that custodian is dropped and the mob ends up unowned. Either parent can then
claim it normally.

---

## Quick reference

- Custody only matters for **Baby Humans and grown Humans** with two player parents.
- **Both parents** always get the menu. **One parent** at a time gets the controls.
- Take custody with the **Take Custody** button in the mob's menu.
- A **following** mob is protected while its custodian is within **16 blocks of the mob**.
- A **waiting or wandering** mob can be claimed by the other parent at any time.
- An offline or far-away custodian can't defend anything.
- The button is a **snapshot from when you opened the menu** — reopen it if things changed.
- Custody never moves the tamed mob slot; that stays with whoever paid.
