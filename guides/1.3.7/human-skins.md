# Custom Human Skins

Every Human in your world can have its own skin. Not "every Human of that type" — *every
individual Human*, picked out by its own entity ID.

This doc is for anyone who wants to reskin one. It's more fiddly than dropping a resource
pack in, but it lets you give each companion a distinct face.

---

## Where the files go

The mod creates the folders for you the first time it runs. Look under your `config` folder:

```
config/minepreggo/textures/entity/preggomob/human/      ← female Humans
config/minepreggo/textures/entity/impregmob/human/      ← male Humans
```

Inside either one, make a folder **named after the Human's UUID** and put the textures there.

```
config/minepreggo/textures/entity/impregmob/human/
└── 069a79f4-44e9-4726-a5be-fca90e38aaf5/
    ├── dressed.png
    └── naked.png
```

### Finding a Human's UUID

Open the Human's menu and look for the **copy button** down the left-hand side. What it does
depends on a server setting, so you'll see one of two things:

**"Check UUID"** — the normal case. The UUID gets printed to your chat in green, and
**clicking that message copies it to your clipboard**. Hover over it and you'll see the
prompt. Paste it in as the folder name and you're done.

**"Copy web-sync data to clipboard"** — you'll only see this if your server has the human
registry turned on. It copies a whole block of data instead, and the UUID is inside it. That
block also holds a secret key, which has no use yet but shouldn't be shared — see
[Preggo Skin Syncer](#preggo-skin-syncer) below.

Either way, no commands and no operator permissions needed. If you'd rather use a command
anyway:

```
/data get entity @e[type=minepreggo:tamable_male_human,limit=1,sort=nearest] UUID
```

The folder name is that UUID with its dashes, exactly as it's written.

---

## The files

Each Human needs up to two textures per state:

| File | When it's used |
|---|---|
| `dressed.png` | Normal — the Human is clothed |
| `naked.png` | When its body state is set to naked |

They're ordinary player-format skins, 64×64. A female Human uses the slim (Alex) proportions
and a male uses the classic (Steve) ones, so start from the matching template.

**You don't have to supply both.** Each file is resolved on its own: give it only
`dressed.png` and the naked state falls back to the built-in skin. Nothing breaks, and nothing
shows up as a missing-texture checkerboard.

### Female pregnancy files

A female Human's texture set also has room for **11 pregnancy phases**, named `dressed_p0.png`
through `dressed_p10.png` and the same for `naked_`.

**In 1.3.7 these are never used.** Female Humans have no pregnancy mechanic — see
[Humans](humans.md#no-reproduction) — so the game always falls back to plain `dressed.png` /
`naked.png`. The slots exist and the built-in skin ships art for all of them, but nothing in
the game can currently put a Human into a pregnancy phase.

You can supply them if you want to be ready for a future version. They just won't do anything
yet.

---

## Sharing a skin with tags

Got a bunch of Humans that should all look the same — a squad of guards in matching uniforms,
say? Instead of copying the same PNGs into a dozen UUID folders, use a **tag**: one folder of
textures, plus a list of which Humans wear it.

### The tags file

Each gender folder has a `tags.json`. The mod creates it for you the first time it runs:

```
config/minepreggo/textures/entity/preggomob/human/tags.json     ← female tags
config/minepreggo/textures/entity/impregmob/human/tags.json     ← male tags
```

Out of the box it just contains `{}`, which means "no tags". Fill it in like this:

```json
{
  "guards": [
    "069a79f4-44e9-4726-a5be-fca90e38aaf5",
    "3f2a9c1e-1b2c-4d5e-8f90-123456789abc"
  ],
  "farmers": [
    "a1b2c3d4-5e6f-4a1b-9c2d-0123456789ab"
  ]
}
```

Each name on the left is a tag, and its list holds the UUIDs of the Humans in it. You get the
UUIDs exactly like you would for a regular folder — see
[Finding a Human's UUID](#finding-a-humans-uuid).

Deleted the file by accident? No big deal — the mod puts an empty one back next time you
launch. It never touches a file that's already there, though, so your tags are safe.

### The tag folder

Next, make a folder **with the same name as the tag**, right next to `tags.json`, and drop the
usual files in it:

```
config/minepreggo/textures/entity/impregmob/human/
├── tags.json
└── guards/
    ├── dressed.png
    └── naked.png
```

Same file names and same rules as in [The files](#the-files): everything is optional, and
anything you leave out falls back to the built-in skin (`alex` or `steve`).

### Which folder wins

A Human only ever reads **one** folder — the two never mix:

1. If it has its **own UUID folder** with at least one file in it, it uses that, and its tag
   is ignored.
2. Otherwise, if it's in a tag, it uses the **tag folder**.
3. Otherwise, the built-in skin.

That makes a UUID folder the way to give one member of a tag a one-off look. Just remember
there's no mixing: if that UUID folder only has `dressed.png`, the naked state falls back to the
*built-in* skin, not to the tag's `naked.png`.

### Rules for tags

- **Females and males are kept apart.** A female's UUID goes in the `preggomob` file, a male's
  in the `impregmob` file. Put one in the wrong file and it's simply ignored — no warning.
- **Tag names: lower-case letters, numbers, `_`, `-` and `.`** No spaces, no slashes, no
  accented letters, and not just dots. Capital letters technically get through, but the folder
  is looked up in lower case — so keep everything lower case and save yourself the headache.
- **A Human can only be in one tag.** List the same UUID under two tags and the first one in
  the file wins.
- **Tag folders aren't discovered on their own.** A folder that isn't named in `tags.json` is
  never looked at, no matter what's inside it.
- **Changes need a reload.** `tags.json` is read once and cached, just like the textures. Press
  **F3 + T** after editing it — see [Reloading](#reloading).

### When something's wrong

A typo in `tags.json` never crashes the game. The mod skips whatever it can't make sense of
and writes a warning to your log (`logs/latest.log`) saying what it skipped and why:

| Mistake | What gets skipped |
|---|---|
| Broken JSON — missing comma, unclosed bracket | The whole file: no tags for that gender |
| A tag whose value isn't a `[ ]` list | That tag |
| A tag with a bad name (see the rules above) | That tag |
| Something in a list that isn't a valid UUID | Just that entry |
| The same UUID in two tags | Everything but the first tag |

If a tagged Human still shows the built-in skin, check the log first. If the log is quiet, the
usual suspects are a capital letter in the folder name, or the UUID sitting in the other
gender's file.

---

## What happens if you supply nothing

Every Human always renders *something*. If it has no folder of its own and isn't in a
[tag](#sharing-a-skin-with-tags), it uses one of the skins bundled in the mod, picked from its
UUID.

Right now there's exactly **one bundled skin per gender** — `alex` for females and `steve` for
males — so every Human without a custom folder looks the same. That's the baseline you're
overriding.

---

## Reloading

Changed a file? Press **F3 + T** to reload resources. The mod clears its texture cache on
every resource reload, so your edit shows up without restarting the game. That goes for
`tags.json` too.

That cache matters: without it the game would re-check all 24 possible female texture paths
for every Human on screen, every frame. The trade-off is that new files aren't noticed until
you reload.

---

## Gotchas

**Folder names must be lower case.** UUIDs are normally written in lower case anyway, so this
mostly bites people copying a bundled skin folder. A capital letter anywhere in the path
produces a texture that silently never loads — you get the purple-and-black checkerboard with
no error message.

**One folder per Human, not per type.** Two Female Humans need two folders — or one
[tag](#sharing-a-skin-with-tags) listing both. There's still no way to reskin "all Female
Humans" from here; that's what an ordinary resource pack is for.

**The UUID has to match exactly.** Copying it from the button avoids any typos, which is why
that's the recommended route.

**A Human keeps the UUID it had as a baby.** Growing up doesn't change it, so a folder you set
up for the baby carries straight over to the adult — usually what you want.

**Folders are matched, not files.** If the folder name doesn't correspond to a real Human, the
files are simply never looked at. Nothing warns you.

---

## For resource pack authors

The config folder isn't the only route. The same paths work from an ordinary resource pack,
under `assets/minepreggo/`, because the config directory is mounted as a pack on the same
namespace:

```
assets/minepreggo/textures/entity/preggomob/human/<name>/dressed.png
assets/minepreggo/textures/entity/impregmob/human/<name>/dressed.png
```

The difference is what the folder name means. In the config directory it's a **UUID** and
targets one specific Human. In a resource pack it's a **skin name** and replaces a bundled
skin — overriding `alex` or `steve` changes the default every unskinned Human uses.

The config pack sits **on top** of other packs, so a per-Human folder always wins over a pack
that replaces the bundled skin.

---

## When a Human dies

Nothing about its skin folder changes. The mod doesn't delete it, doesn't warn you, and
doesn't reuse it — **the folder just sits there on disk forever**, pointing at a UUID no
living Human has any more. Clean it up yourself if you care about tidiness.

A replacement Human always gets a brand new UUID, so an old folder never accidentally
applies to something else.

Same goes for tags: if the Human was in one, its UUID just stays in `tags.json`. It's
harmless — take it out whenever you feel like tidying up.

What *does* happen on the server side:

- **Its registry entry is deleted for good.** If the human registry is on, that Human's key
  stops existing the moment it dies. There's no recovery.
- **A line is appended to a death log**, at
  `<world>/minepreggo/data/dead_humans.jsonl`.

That log is one JSON object per line, recording the UUID, the time, the gender, the dimension
and how it was removed:

```json
{"uuid":"069a79f4-...","diedAt":"2026-09-28T14:02:11Z","gender":"FEMALE","dimension":"minecraft:overworld","reason":"KILLED"}
```

It's write-only — the mod never reads it back — and it contains **no keys**, so unlike the
[web-sync data](#web-sync-data) it's safe to share or hand to a tool. It's there so something
outside the game — such as the planned [Preggo Skin Syncer](#preggo-skin-syncer) — can tell
which Humans no longer exist.

One quirk if you ever parse it: the same UUID can appear more than once. If the server
crashes before its next save, the Human comes back alive while its line is already written.
Treat the **last** line for a UUID as the truth.

This log is written whether or not the registry is enabled.

---

## Preggo Skin Syncer

> **Note: Preggo Skin Syncer is not available.** It's a planned external mod, together with
> the web service it talks to, and neither has been released. That has a direct consequence
> for the web-sync keys (`mpk-XXXXX-XXXXX-XXXXX-XXXXX`): **right now they have no use.**
> Nothing outside your server reads them and no service accepts them yet.
>
> Everything else in this doc — local skins, the copy button, the UUID — works fully without
> it. You don't need a key for anything you can do today.
>
> Keep any key you copy private anyway. A key issued now is intended to be the one that proves
> ownership once the syncer exists.

### What it is

Preggo Skin Syncer is two pieces working together:

- **A companion mod** installed alongside Minepreggo, which fetches each Human's skin from the
  web instead of from your config folder.
- **A web service** that stores those skins and decides who is allowed to change them.

How they're meant to work:

1. The service stores skins indexed by **Human UUID** — the same UUID you'd use for a local
   folder.
2. Your client asks for that UUID's skin and downloads it. No key needed just to *see* a skin.
3. **Uploading or changing** a skin requires the ownership key. The service only keeps a
   **hash** of it, so the key itself never leaves your hands — and the service can verify you
   without ever knowing it.
4. That means only the Human's owner or parents can change how it looks, and nobody can
   overwrite someone else's Human.

Local skins aren't going anywhere. The config folder will keep working exactly as it does
now; the syncer is meant as an addition for servers where people want to share Humans, not a
replacement.

What follows is the part of the system that already exists inside Minepreggo: the key
registry, the data you can copy, and the commands to manage it. It's all in place, waiting
for a service to consume it.

### Web-sync data

This only applies if your server has **`enableRecruitableHumansRegistry`** turned on. It's
**off by default**, so most players will never see any of this.

With it on, the copy button hands you a block of JSON instead of a plain UUID:

```json
{"uuid":"069a79f4-...","key":"mpk-XXXXX-XXXXX-XXXXX-XXXXX","createdAt":"2026-09-28T...","gender":"FEMALE"}
```

> **The `key` is a secret.** It's what proves you own this particular Human. Don't paste this
> block into chat, Discord, a screenshot or a bug report.

If a key does leak, it can be replaced — but only by a server operator, with
`/minepreggo human regenkey`. See [Operator commands](#operator-commands).

A few things to know:

- **It goes straight to your clipboard**, never to chat — which also means it **overwrites
  whatever you had copied**. Paste it somewhere before you copy anything else.
- **The menu closes** when you press the button.
- **Only the Human's owner or either of its recorded parents can ask for it.** Someone who
  can open the menu but isn't one of those gets refused, and isn't even told whether the
  Human is registered.
- If the Human was never registered, you'll get told that instead.

Humans enter the registry when you **recruit one directly** or when a **Baby Human grows up**
while owned. A baby that grows up with no owner isn't registered until somebody tames it.

### If your key gets replaced

An operator can issue a Human a new key, which makes the old one useless. When that happens
you'll get a chat message naming the Human and telling you to open its menu and copy its data
again. Do that — whatever you saved before is now dead.

Only the Human's parents get told. If you tamed a wild Human directly, it has no recorded
parents, so nobody is notified and you'd have to be told out of band.

### Turning the registry on

For server operators. The toggle lives in the `[Mob]` section of the server config:

```toml
enableRecruitableHumansRegistry = false
```

Leave it off and the copy button stays in "Check UUID" mode, which is all local skins need.
Turn it on and the mod starts issuing an ownership key per Human.

There's no reason to turn it on just for local skins. Its only purpose is to have keys ready
for the syncer — and since that doesn't exist yet, enabling it today only builds up a registry
for later. If you do, your Humans will already carry everything the service would need: UUID,
key, creation time and gender.

### Operator commands

All of these need **permission level 3**, and none of them are self-service — players can't
run them on their own Humans.

| Command | What it does |
|---|---|
| `/minepreggo human count` | How many Humans are registered |
| `/minepreggo human export` | Writes the registry to a file |
| `/minepreggo human regenkey <uuid>` | Issues a new key for one Human |
| `/minepreggo human add <entity>` | Registers a Human that's missing |

Everything except `count` is refused while the registry is disabled.

**`export`** writes `<world>/minepreggo/data/humans.json`, one entry per Human with its
creation time, gender and the **hash** of its key — never the key itself. That's the file the
syncer's service would ingest: the hash is enough to verify a key someone submits, without the
server ever handing out the real thing. It replaces any previous export.

**`regenkey`** is the fix for a leaked key. It swaps in a fresh one, which immediately
invalidates the old — anything holding the old key stops being able to prove ownership.

Three things to know about it:

- **The Human has to be loaded.** Its owner and parents are only readable off the live
  entity, so an unloaded one is refused rather than given a key nobody would learn about.
- **The new key is never shown.** Not in the command output, not in the logs. Its parents get
  a chat message telling them to open the menu and copy the data again — that's the only way
  to get it.
- **A Human with no parents notifies nobody.** A directly-tamed wild Human has an owner but no
  recorded lineage, so the key changes silently and its owner has to know to re-copy it. The
  owner is deliberately not used as a fallback recipient.

**`add`** covers Humans that predate the registry, or whose entry was somehow lost. Note that
the recorded creation time becomes *the moment you run the command* — the real one isn't
stored anywhere else, so it can't be recovered.
