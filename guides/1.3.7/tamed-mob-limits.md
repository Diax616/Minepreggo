# Tamed Mob Limits

Minepreggo caps how many breedable mobs a single player can keep tamed at once. This doc
covers how that cap works day to day and what commands you've got for poking at it.

Everything here is server-side. Players can't *change* their own cap and nothing in this
system lives on the client, but any player can check where they stand with
`/minepreggo tamedlimit query`.

---

## Manager

### The basics

Every player has a **count** (how many mobs they currently own) and a **limit** (how many
they're allowed). When they try to tame something, the mod checks the count against the
limit. If they're at the cap, the taming is refused and they get a message on the action
bar showing where they stand.

The limit comes from one of two places:

1. **The global config** — `maxTamedBreedableMobLimit` in the `[Mob]` section of the
   server config. Default is 24.
2. **A per-player override** — an entry in `<world>/serverconfig/minepreggo/tamed_mob_limits.json`.
   If a player has one, it replaces the global value entirely for them.

**A limit of `0` means unlimited.** Not "zero mobs allowed" — unlimited. That applies to
the global setting and to overrides alike. It's the same convention the mod's other "max"
settings use, so `0` consistently reads as "this feature is off."

### The override file

Lives at `<world>/serverconfig/minepreggo/tamed_mob_limits.json`, next to the mod's server
config, so each world (and each dedicated server) has its own. If it's not there when the server
starts, the mod creates it with `{}` inside so you can find it where you'd expect.

```json
{
  "069a79f4-44e9-4726-a5be-fca90e38aaf5": 50,
  "853c80ef-3c37-49fd-aa49-938b674adae6": 0,
  "61699b2e-d327-4a01-9f1e-0ea8c3f06bc6": 5
}
```

That's three players: one capped at 50, one with no cap at all, one capped at 5. Anyone
not listed falls back to the global config value.

Keys are player UUIDs with dashes. Values are whole numbers, zero or higher.

**What gets rejected:**

| Value | What happens |
|---|---|
| `0` | Accepted — unlimited for that player |
| `24` | Accepted |
| `-5` | Skipped, warning in the log |
| `1.5` | Skipped, warning in the log |
| `3000000000` | Skipped (too big for a 32-bit int), warning in the log |
| `"24"` | Skipped (it's a string, not a number), warning in the log |
| Bad UUID key | Skipped, warning naming the bad key |

Validation is **per entry**. One typo costs you that one line, not the whole file — the
rest still loads. If the file itself is broken (missing brace, root isn't an object), the
server logs a warning, keeps whatever overrides were already loaded, and starts normally.
A busted overrides file will never stop your server from booting.

### The file is the only source of truth

This is worth being clear about: **overrides are never saved into your world.** They live
in memory, read fresh from the JSON. The mod reads that file and never writes to it, with
the single exception of creating an empty `{}` when it's missing.

Practical upshot — delete a player's line, run the reload command, and they're back on the
global limit immediately. Nothing lingering in the save file is going to bring the old cap
back.

The **counts**, on the other hand, *are* world data and do persist. That leads to one
situation worth knowing about: if a player has an override of 50 and has tamed 40 mobs,
and you then delete their override, their count stays at 40 but their cap drops to 24.
They're over the limit until they release some mobs. Nothing breaks — they just can't tame
anything new until they're back under. That's working as intended, not a bug.

### When the file gets read

- Every server start
- Whenever you run `/minepreggo tamedlimit overrides reload`

That's it. Editing the file does nothing on its own; you need one of those two.

### Where a slot gets charged

Four places take a slot off a player. All of them check the cap **before** charging,
except the last one.

**1. Taming a breedable mob with food.** Feed the mob its taming food until affection
fills up, then there's a success roll. The slot is charged on success only. The cap is
checked before any food is consumed, so a player who's maxed out doesn't waste items on a
taming that could never land.

**2. Adopting a baby with milk.** Same shape as above — feed milk, fill affection, success
roll, slot charged on success. Cap checked before any milk is spent.

**3. Spawning a baby from a spawn egg.** The cap is checked before the baby appears. Note
that not *every* spawn egg use charges a slot — it depends on the baby's lineage as stored
on the item. If a particular spawn wouldn't be billed to the player, they're not blocked by
their cap either. The check and the charge always agree.

**4. A baby growing into its next stage.** When a baby grows up, the baby is removed (which
refunds its slot) and the successor mob immediately takes a slot for the same player. It's
a handover, not a net gain — the player doesn't pay twice, and the bill follows whoever
originally paid rather than whoever happens to hold the mob now.

That last one **does not check the cap**, deliberately. Growing up isn't a new acquisition,
so it can't push anyone over a limit they weren't already at.

### Where a slot gets released

**Mob removed from the world.** Covers death, despawn, and anything else that destroys the
entity for good. Only fires if the mob was actually tamed.

**Baby removed from the world.** Same deal on the baby side.

**Baby grows up.** The baby's slot is released as part of the growing-up handover described
above, then immediately re-taken by the successor.

**Tameable humans.** Female and male tameable humans credit the player who *paid* for them,
not whoever owns them right now. A human has two parents and custody moves between them, so
the current owner isn't necessarily the one who was charged. If nobody was recorded as
having paid, the ordinary release path applies.

**Admin commands.** `set` and `reset` overwrite a player's count directly. See below.

### Offline players

If a player's mob dies while they're logged off, their refund doesn't just vanish — it gets
queued and applied the moment they log back in. Multiple losses while offline get folded
into a single pending entry rather than piling up, so a long absence won't silently hand
out free slots.

Charges work the other way: if a mob somehow becomes an offline player's while they're
away, the charge is written straight to their stored count and it's waiting for them at
next login.

### Counts are per world

The mod wipes all counts and overrides when a server stops. That matters for singleplayer:
without it, the counts from one world would leak into the next one you open in the same
game session. Overrides get re-read from the JSON on the next start, so nothing is lost.

---

## Commands

All of these live under `/minepreggo tamedlimit`. Most need **permission level 2** (op), but
checking your own quota is open to everyone:

| Command | Who can run it |
|---|---|
| `query` (no target) | Anyone |
| `query <targets>` | Op only |
| `set` | Op only |
| `reset` | Op only |
| `overrides reload` | Op only |
| `overrides list` | Op only |

Players who aren't op won't see the gated branches in their autocomplete at all.

### `query`

```
/minepreggo tamedlimit query
/minepreggo tamedlimit query <targets>
```

Shows how many breedable mobs a player has tamed and what their cap is. With no target, it
reports on you — and that form is open to every player, so anyone can check where they
stand without bugging an admin.

Adding a target requires op. That gate is deliberate and sits on the target argument
specifically: Minecraft only restricts entity selectors like `@a` by default, not plain
player names, so without it anyone could read a teammate's quota just by typing their name.

Running the no-target form from the console or a command block gets you a "player only"
error, since there's no player to report on.

The message tells you whether the cap is the global config value or a personal override,
so you're never guessing which one is in force. If the effective limit is `0`, it says the
limit is disabled instead of quoting a number.

Read-only. Nothing is broadcast to other ops.

### `set`

```
/minepreggo tamedlimit set <targets> <count>
```

Overwrites a player's **count** — not their limit. Use this when the tally has drifted from
reality, like mobs lost in chunks that never got loaded.

Negative values get clamped to zero. This is broadcast to other ops and the server log,
since it's an administrative change to someone's quota.

### `reset`

```
/minepreggo tamedlimit reset <targets>
```

Sets a player's count to zero. Same as `set <targets> 0`, just quicker to type. Also
broadcast.

### `overrides reload`

```
/minepreggo tamedlimit overrides reload
```

Re-reads `tamed_mob_limits.json` and tells you how many overrides are in force afterward.
Run this after editing the file — the changes won't apply otherwise.

Nothing is written back to the file, and nothing touches your world save. If the file is
corrupt, you keep the overrides you already had and get a warning in the log. Broadcast to
other ops, since it changes which caps are active.

### `overrides list`

```
/minepreggo tamedlimit overrides list
```

Dumps every loaded override, one line per UUID, with a header showing the total. Says so
plainly if there are none.

Read-only. The listing order isn't stable between reloads, so don't count on it staying put.

---

## Quick reference

| I want to... | Do this |
|---|---|
| Change the cap for everyone | Edit `maxTamedBreedableMobLimit` in the server config |
| Give one player a different cap | Add their UUID to `tamed_mob_limits.json`, then `overrides reload` |
| Give one player no cap at all | Set their value to `0` |
| Turn the whole feature off | Set `maxTamedBreedableMobLimit` to `0` |
| Put a player back on the global cap | Delete their line, then `overrides reload` |
| Fix a count that drifted | `/minepreggo tamedlimit set <player> <count>` |
| See who has an override | `/minepreggo tamedlimit overrides list` |
| Check another player's standing | `/minepreggo tamedlimit query <player>` |
| Let players check their own standing | Nothing — `/minepreggo tamedlimit query` is already open to all |

---

## Troubleshooting

**Edited the JSON, nothing changed.** Run `overrides reload`. The file isn't watched.

**An override isn't taking effect.** Check the server log for a skip warning naming that
UUID — a malformed UUID, a decimal, a negative, or a quoted number all get rejected
individually and silently as far as in-game behavior goes. Also double-check the UUID
itself is right; a valid-but-wrong UUID loads fine and just applies to nobody.

**A player is over their limit.** Most likely their override was lowered or removed while
they were holding mobs. Counts persist, caps don't. They'll be able to tame again once
they're back under.

**Someone can tame infinitely.** Check whether their override is `0`, or whether the global
`maxTamedBreedableMobLimit` is `0`. Both mean unlimited.

**The file keeps coming back after I delete it.** That's intended — an empty `{}` is
recreated at server start so it's always where the docs say it is. An empty file means no
overrides, which is the same as no file.
