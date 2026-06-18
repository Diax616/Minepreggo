# Admin Commands — Minepreggo

> Non-technical documentation for the mod's operator commands.  
> Version: Minecraft Forge 1.20.1

---

## Table of Contents

1. [Intro](#intro)
2. [Pregnancy Commands](#pregnancy-commands)
   - [start — Start a pregnancy](#start--start-a-pregnancy)
   - [startat — Start at a specific phase](#startat--start-at-a-specific-phase)
   - [dragon — Dragon pregnancy](#dragon--dragon-pregnancy)
   - [end — End a pregnancy](#end--end-a-pregnancy)
   - [birth — Force labor](#birth--force-labor)
   - [relievewomb — Relieve an overloaded womb](#relievewomb--relieve-an-overloaded-womb)
3. [Gender Commands](#gender-commands)
   - [gender — Change gender](#gender--change-gender)
4. [Pain and Symptom Commands](#pain-and-symptom-commands)
   - [pain — Trigger pain](#pain--trigger-pain)
   - [clearpain — Clear pain](#clearpain--clear-pain)
   - [symptom — Trigger a symptom](#symptom--trigger-a-symptom)
5. [Summary Table](#summary-table)

---

## Intro

Minepreggo registers the root command `/preggo` with all its subcommands. It requires **permission level 2** (operator) for both players and console.

Arguments that accept enums (species, creature, phase, symptom, etc.) have **autocomplete** when typing the command in chat.

> All commands operate on server-side game state. Changes are immediate and automatically synced to the client.

---

## Pregnancy Commands

### `start` — Start a pregnancy

```
/preggo start <target> <species> <creature> [babies]
```

Starts a pregnancy on the specified player or players.

| Argument | Description |
|----------|-------------|
| `target` | Player or player selector (`@a`, `@p`, name, etc.) |
| `species` | Pregnancy species (autocomplete available) |
| `creature` | Specific creature within the species (autocomplete available) |
| `babies` | *(Optional)* Number of babies. Default: **1** |

**Conditions:**
- The target must be **female**. With multiple targets, non-female ones are skipped with an individual error message.
- The number of babies has a mod-defined maximum.

**Example:**
```
/preggo start Player1 zombie creeper_girl 2
```

---

### `startat` — Start at a specific phase

```
/preggo startat <target> <species> <creature> <startPhase> <finalPhase>
```

Same as `start`, but lets you control what phase the pregnancy starts at and what the final phase will be. The final phase determines the **number of babies** that will be born.

| Argument | Description |
|----------|-------------|
| `startPhase` | Starting phase of the pregnancy (P0–P9) |
| `finalPhase` | Phase the pregnancy will end at (minimum **P4**) |

**Restrictions:**
- `finalPhase` must be **P4 or higher** — lower phases don't have a litter size defined.
- `startPhase` must be **equal to or earlier than** `finalPhase`.
- **Not available for the DRAGON species** — use `/preggo dragon` instead.

**Example:**
```
/preggo startat Player1 human villager p2 p6
```
This starts the pregnancy at phase P2, with P6 as the final phase.

---

### `dragon` — Dragon pregnancy

```
/preggo dragon <target>
```

Starts an Ender Dragon pregnancy on the target player. No species or creature needed — it uses the dragon species' exclusive internal process.

**Conditions:**
- The target must be **female**.

---

### `end` — End a pregnancy

```
/preggo end <target>
```

Immediately ends the target player's active pregnancy **without birth**. No babies are produced and no birth or miscarriage sequence is triggered.

**Conditions:**
- The target must have an active pregnancy. If they don't, the command fails with an error message.

---

### `birth` — Force labor

```
/preggo birth <target>
```

Forces labor to start on the target player. The system internally validates whether birth conditions are met.

If labor can't start, the command tells you the exact reason:

| Cause | Description |
|-------|-------------|
| Not at final phase | The pregnancy hasn't reached its end phase yet |
| Not enough days passed | The current phase hasn't lasted the minimum required time |
| Womb overloaded | There are more babies than the womb can handle for birth |
| Already in progress | The player is already in labor or a miscarriage process |

> To force birth while bypassing day conditions or an overloaded womb, use `/preggo relievewomb` first if applicable, or wait for the phase to advance.

---

### `relievewomb` — Relieve an overloaded womb

```
/preggo relievewomb <target>
```

Removes excess babies when the womb is **overloaded** (more babies than the maximum size allows). The system determines how many are removed.

- If the womb isn't overloaded, the command fails and notifies the operator.
- The command reports how many babies were removed per player and the running total if there are multiple targets.

> Useful as a step before `/preggo birth` when birth fails due to womb overload.

---

## Gender Commands

### `gender` — Change gender

```
/preggo gender <target> <gender>
/preggo gender <target> <gender> force
```

Changes the target player's gender.

**Without `force`:**
- Blocked if the player is **pregnant**. Gender can't be changed while there's an active pregnancy.
- Fails if the player already has the specified gender.

**With `force`:**
- Removes the active pregnancy if there is one and **forces the gender change** anyway.
- Still fails if the player already has that gender (even with force).

> Use `force` carefully: it ends the active pregnancy without triggering any birth or miscarriage sequence.

---

## Pain and Symptom Commands

> The `pain` and `symptom` commands apply to **the player running the command**, not an external target. They must be used from in-game, not from the server console.

### `pain` — Trigger pain

```
/preggo pain <pain>
```

Triggers a pregnancy pain on **the player running the command**.

**Restrictions:**
- Only accepts **COMMON** type pains. Pains tied to labor (`BIRTH`) or miscarriage (`MISCARRIAGE`) can't be triggered by command — they're handled exclusively by the pregnancy's internal state.
- Requires the executor to have an **active pregnancy**.

---

### `clearpain` — Clear pain

```
/preggo clearpain <target>
```

Removes the target player's active pregnancy pain, as long as it's of type **COMMON**.

- Doesn't affect labor or miscarriage pain — those only go away when the corresponding process finishes.
- Fails if the player doesn't have an active pregnancy or doesn't have any active common pain.

---

### `symptom` — Trigger a symptom

```
/preggo symptom <symptom>
```

Activates a pregnancy symptom on **the player running the command**. Accepts any symptom in the system (autocomplete available).

**Restrictions:**
- Requires the executor to have an **active pregnancy**.
- If the symptom can't be applied in the pregnancy's current state, the command fails with an error message.

> See [CRAVING_AND_VISUAL_EFFECTS.md](CRAVING_AND_VISUAL_EFFECTS.md) for details on the `CRAVING` symptom and its associated visual effects.

---

## Summary Table

| Command | Target | Requires pregnancy | Key notes |
|---------|:------:|:-----------------:|-----------|
| `/preggo start` | Other players | No | Target must be female |
| `/preggo startat` | Other players | No | finalPhase ≥ P4; DRAGON species not supported |
| `/preggo dragon` | Other players | No | Target must be female |
| `/preggo end` | Other players | Yes | Ends with no birth or miscarriage |
| `/preggo birth` | Other players | Yes | Validates conditions; reports reason if it fails |
| `/preggo relievewomb` | Other players | Yes | Only acts if the womb is overloaded |
| `/preggo gender` | Other players | — | With `force`, removes the active pregnancy |
| `/preggo pain` | Executor | Yes | COMMON type only; labor pain not supported |
| `/preggo clearpain` | Other players | Yes | Only clears COMMON type |
| `/preggo symptom` | Executor | Yes | Any valid symptom |

---

*Documentation generated from analysis of the Minepreggo source code.*
