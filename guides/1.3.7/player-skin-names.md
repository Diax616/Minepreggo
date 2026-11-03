# Player Skin File Names: What Changed

Custom player skin files got new names. They don't start with your username anymore.

**You don't need to do anything.** The mod renames your old files to the new names by itself.
This doc explains what changed and what the mod does with your existing files.

---

## What changed

Your skin files still go in a folder named after your username:

```
config/minepreggo/textures/player/predefined/<username>/
config/minepreggo/textures/player/default/<username>/
```

The folder already says who the files are for, so the file names don't repeat your username
anymore. On top of that, two things moved around:

- **The phase goes at the end now.** `_p3_extra` became `extra_p3`.
- **`_extra_naked` became `naked_extra`.**

Here's the full mapping, using `steve123` as the username:

| Old name | New name |
|---|---|
| `steve123.png` | `dressed.png` |
| `steve123_naked.png` | `naked.png` |
| `steve123_extra.png` | `dressed_extra.png` |
| `steve123_extra_naked.png` | `naked_extra.png` |
| `steve123_p3.png` | `dressed_p3.png` |
| `steve123_p3_extra.png` | `dressed_extra_p3.png` |
| `steve123_p3_extra_naked.png` | `naked_extra_p3.png` |

Phases work the same way for every phase from `p0` to `p10`.

So a folder that used to look like this:

```
steve123/
├── steve123.png
├── steve123_naked.png
├── steve123_p3.png
└── steve123_p3_extra.png
```

ends up looking like this:

```
steve123/
├── dressed.png
├── naked.png
├── dressed_p3.png
└── dressed_extra_p3.png
```

---

## The mod renames your files for you

Every time resources load, the mod goes through your skin folders and renames any file that
still has an old name. That happens:

- **When the game starts.** Just launch it once and your folders are converted.
- **When you press F3 + T.** If you drop old files in while the game's running, press F3 + T
  and they get renamed and show up right away.

It only ever renames. It doesn't delete anything, and it never overwrites a file. Running it
again does nothing, because once a file has its new name there's nothing left to change.

Got skins from a friend or an old pack that still use the old names? Drop them in as they are.
The mod takes care of them.

---

## What it won't touch

**Files that already have a new name.** If it's already called `dressed.png`, it stays
`dressed.png`.

**Old files whose new name is already taken.** Say your folder has both `steve123.png` and
`dressed.png`. The mod keeps `dressed.png`, leaves `steve123.png` alone, and writes a warning
to the log. It won't pick a winner for you. Delete whichever one you don't want and you're
done.

**Files the old version never used.** These stay exactly as they are:

- Anything that doesn't start with the folder's name, like `bob.png` inside `steve123/`
- `steve123_p3_naked.png` and similar. There was never a per-phase naked file. The naked
  texture has always been a single `naked.png` for every phase.
- Phases past 10, or with extra zeros like `_p03`

None of these were showing up in game before either, so nothing you could see changes.

---

## Something didn't get renamed?

Check `logs/latest.log` in your Minecraft folder. Every file that got skipped because its new
name was taken, or that failed to rename, is listed there with the reason.

If the log says nothing about a file, it's one of the files from the list above. The mod didn't
recognize it as an old skin file.
