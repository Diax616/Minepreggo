# Stats

Minepreggo keeps track of what you get up to. Births, checkups, witches you've killed,
dimensions you've had sex in — there's a counter behind all of it, and you can pull the
numbers up whenever you want.

Your stats are yours. They're saved with your player data, they survive death, and they
follow you between worlds on the same save. Nobody else's numbers get mixed into yours.

---

## Seeing your stats

Two places.

### The vanilla stats screen

Most Minepreggo stats show up right in the normal Minecraft stats screen — Esc → Statistics
→ the **Custom** tab, mixed in with "Times Jumped" and "Distance Walked." Scroll and you'll
find them.

This is the easy way, but it flattens things. The stats screen only has room for one number
per row, so a stat that's really a *breakdown* gets summed into a single total. "Species
Given Birth To" shows `7` there — seven births — not which species they were.

For the breakdown, use the command.

### `/minepreggo stats`

This is the full picture. Three forms:

| Command | What it does |
|---|---|
| `/minepreggo stats query` | Your own stats, all of them |
| `/minepreggo stats get <stat>` | One of your stats |
| `/minepreggo stats list` | Every stat name that exists, with its display name |

Typing `get` and hitting tab will suggest every valid stat name, so you don't have to
memorize them.

Your own stats print only to you. Nobody else sees them in chat and nothing goes in the
server log.

Counters print on one line:

```
Miscarriages Suffered: 3
```

Breakdowns print as a heading with the entries underneath, sorted most-frequent first:

```
Species Given Birth To:
  zombie x4
  human x2
  creeper x1
```

And if something never happened, it says so straight out rather than leaving a blank:

```
Own Babies Sold: never
```

### Checking someone else

`/minepreggo stats query <player>` reads another player's stats, and
`/minepreggo stats query <player> <stat>` pulls one out. You can target multiple players at
once with selectors like `@a`.

This needs command permission, so on a normal server it's an operator thing.

---

## Why the two screens disagree

The mod keeps its own copy of every stat. That copy is the real one — it's what the command
reads, what advancements check, and what's saved with your player data.

Most of those stats *also* get published into the vanilla stats screen as a courtesy, so you
can find them without typing a command. But vanilla only understands "one stat, one number,"
which means anything richer has to be squashed on the way out. That squashing is where the
two screens stop agreeing.

**Breakdowns get summed.** The vanilla screen adds every entry together into one total. If
you've given birth to four zombies, two humans and a creeper, the stats screen says `7` for
"Species Given Birth To." The command shows you the actual three-way split.

**Except the dimension stats, which get counted instead.** "Dimensions Given Birth In,"
"Dimensions Had Sex In" and "Dimensions Visited With a Preg Preggomob" report *how many
different dimensions*, not how many times. Give birth fifty times in the Overworld and
you've still only got one dimension, so the vanilla number stays `1`. This is deliberate —
"how many dimensions" is the interesting question for those, and it's also what the matching
advancements care about.

**Counters match exactly.** Miscarriages, witch kills, babies sold, gender picks — there's
only one number to begin with, so both screens show the same thing.

**One stat is command-only.** `impregnation_types` was never published to vanilla, so it
doesn't appear in the stats screen at all. `/minepreggo stats get impregnation_types` is the
only way to see it.

So the rule of thumb: **the vanilla screen is a summary, the command is the truth.** If the
two ever look like they're contradicting each other, they're not — you're looking at a total
where the command is showing you the parts.

### A wrinkle worth knowing

The published vanilla copy only ever goes **up**. Nothing in the mod lowers it.

In normal play you'll never notice, since stats only climb anyway. Where it shows up is if
an operator edits your mod stats downward, or fiddles with your player data directly: the
command will immediately report the new lower number, while the stats screen keeps showing
the old high-water mark. The mod re-publishes its numbers every time you log in, but because
that re-publish also only raises values, relogging won't pull the vanilla screen back down.

Nothing's broken when this happens, and your real stats are fine — the vanilla screen is
just a display that got stuck high. Trust the command.

---

## The stats

Two kinds. **Counters** are a single running number. **Breakdowns** are a tally per
category — which species, which dimension, which kind of checkup — and they remember both
*what* happened and *how many times*.

### Pregnancy and birth

| Stat | Type | What counts |
|---|---|---|
| `total_pregnancies` | Breakdown by species | Every time you get pregnant, filed under the father's species |
| `total_impregnations` | Breakdown by species | Every time you get someone else pregnant, filed under *their* species. Another player counts as `human` |
| `species_birthed` | Breakdown by species | Each baby you successfully give birth to, by the baby's species |
| `impregnation_types` | Breakdown by type | *How* you got pregnant: `sex`, `potion`, `mob_attack`, `command`, or `spawn` |
| `dimensions_birthed` | Breakdown by dimension | Every dimension you've given birth in |
| `miscarriage_suffered` | Counter | Pregnancies you lost |
| `death_while_pregnant` | Breakdown by phase | Dying while pregnant, filed under which phase you were in (`p0` through `p10`) |

A couple of things worth knowing:

**`total_pregnancies` counts pregnancies, `species_birthed` counts babies.** One pregnancy
can produce more than one baby, so the second number can outrun the first. That's normal.

**`death_while_pregnant` files by phase, so it doubles as a record of how far along you
were.** `p0` is the very start, `p10` is full term. Dying at `p9` is a different entry from
dying at `p1`.

### Checkups

| Stat | Type | What counts |
|---|---|---|
| `checkups_done_myself` | Breakdown by checkup | Checkups you ran on yourself |
| `prenatal_checkups_on_preggomob` | Breakdown by checkup | Prenatal checkups you ran on a preggomob |

The checkup types are `pregnancy_state`, `ultrasound_scan`, `paternity_test`,
`removal_of_excess_babies`, `fertility_state`, and `pregnancy_test`.

`checkups_done_myself` covers both the prenatal machine and the fertility one, so all six
types can land in it. `prenatal_checkups_on_preggomob` only ever gets the prenatal four,
since that's all you can run on a mob.

### Sex

| Stat | Type | What counts |
|---|---|---|
| `species_had_sex_with` | Breakdown by species | Filed by partner species. Another player counts as `human` |
| `sex_dimensions` | Breakdown by dimension | Every dimension you've had sex in |

### Drinking milk

| Stat | Type | What counts |
|---|---|---|
| `breast_milk_species_drunk` | Breakdown by species | Each bottle you drink, filed by the species it came from |

Counted when you finish the bottle, not when you pick it up.

### Combat and trading

| Stat | Type | What counts |
|---|---|---|
| `fertility_witch_killed` | Counter | Fertility witches you've killed |
| `infertility_witch_killed` | Counter | Infertility witches you've killed |
| `own_babies_sold` | Counter | Babies you sold to a Scientific Illager |

**`own_babies_sold` means *your* babies.** The mod checks parentage on the baby you hand
over — if you're male it looks for you as the father, if you're female as the mother. Sell
somebody else's baby and the trade goes through fine, but this counter doesn't move.

### Travel

| Stat | Type | What counts |
|---|---|---|
| `dimensions_visited_with_pregnant_preggomob` | Breakdown by dimension | Dimensions you've entered with a full-term tamed preggomob nearby |

Three things have to be true when you change dimensions: the preggomob is **tamed by you**,
it's at its **last pregnancy phase**, and it's **within 16 blocks**. Leave it at home and
the dimension doesn't get recorded.

### Gender

| Stat | Type | What counts |
|---|---|---|
| `select_female_gender` | Counter | Times you've picked female |
| `select_male_gender` | Counter | Times you've picked male |

These only move on an actual *change*. Re-picking the gender you already are does nothing.

---

## Stats and advancements

A lot of Minepreggo advancements read straight off these stats, which is why breakdowns
track categories instead of just totaling everything up.

Most of those advancements ask "have you ever done this with *each* of these?" — every
species, every dimension in a list, every checkup type. For those, the count doesn't matter
at all. Drinking twelve bottles of zombie milk is the same as drinking one: zombie is on the
list either way. You need *variety*, not volume.

A few go the other way and want a raw total — total impregnations, total pregnancies, witch
kills. Those just want the number to get big enough.

So if an advancement is refusing to pop, run `/minepreggo stats get <stat>` and look at
which entries you've actually got. It's usually a species or a dimension you haven't hit yet.

---

## Odds and ends

**Nothing here is retroactive.** Stats started counting when the system went in. Anything
you did before that isn't in the numbers, and there's no way to backfill it.

**Zeroes aren't stored.** A stat you've never triggered takes up no space in your save —
that's why the command says `never` instead of `0`. It starts existing the first time it
happens.

**A corrupted stat won't break your save.** If one value gets mangled somehow, the mod skips
that single stat and loads the rest normally. You lose one number, not your player data.

**Partner breast milk has a stat name but isn't hooked up yet.** `partner_breast_milk_drunk`
shows in `/minepreggo stats list` and in the stats screen, but nothing currently increments
it, so it'll sit at zero no matter what you drink. Regular breast milk is tracked fine under
`breast_milk_species_drunk` — it's only the partner-specific counter that's idle.
