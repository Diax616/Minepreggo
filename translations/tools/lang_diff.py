#!/usr/bin/env python3
"""Utilities for comparing Minecraft lang files.

Files must follow the Minecraft lang format: a flat JSON object at the root
whose keys and values are all strings. Any other shape (list, nested values,
numbers, null, ...) is rejected.

Commands:
    missing  keys present in the source but not in the target
    prune    the target without the keys the source no longer has

Usage:
    python lang_diff.py missing en_us.json es_mx.json missing.json
    python lang_diff.py missing en_us.json es_mx.json missing.json --empty-values
    python lang_diff.py prune en_us.json es_mx.json es_mx_pruned.json
    python lang_diff.py prune en_us.json es_mx.json --in-place
"""

import argparse
import json
import sys


def load_lang(path):
    """Load a lang JSON and validate it is a flat string-to-string map."""
    try:
        with open(path, encoding="utf-8") as f:
            data = json.load(f)
    except FileNotFoundError:
        raise SystemExit(f"error: file not found: {path}")
    except json.JSONDecodeError as e:
        raise SystemExit(f"error: {path} is not valid JSON: {e}")

    if not isinstance(data, dict):
        raise SystemExit(
            f"error: {path} is not a lang file: root is "
            f"{type(data).__name__}, expected an object"
        )

    for key, value in data.items():
        if not isinstance(value, str):
            raise SystemExit(
                f"error: {path} is not a lang file: key "
                f"'{key}' holds a {type(value).__name__}, "
                f"expected a string"
            )

    return data


def write_lang(path, data):
    """Write a lang file in the same style as the mod's own: tabs and UTF-8."""
    with open(path, "w", encoding="utf-8") as f:
        json.dump(data, f, ensure_ascii=False, indent="\t")
        f.write("\n")


def cmd_missing(args):
    """Write the source keys the target does not have."""
    source = load_lang(args.source)
    target = load_lang(args.target)

    missing = {k: ("" if args.empty_values else v)
               for k, v in source.items() if k not in target}

    write_lang(args.output, missing)

    print(f"source: {len(source)} keys")
    print(f"target: {len(target)} keys")
    print(f"missing: {len(missing)} -> {args.output}")


def cmd_prune(args):
    """Write the target keeping only the keys that exist in the source.

    Values come from the target, so existing translations are preserved and
    only stale keys are dropped. With --source-order the output follows the
    source order instead of the target's original one.
    """
    source = load_lang(args.source)
    target = load_lang(args.target)

    if args.source_order:
        kept = {k: target[k] for k in source if k in target}
    else:
        kept = {k: v for k, v in target.items() if k in source}

    output = args.target if args.in_place else args.output
    write_lang(output, kept)

    print(f"source: {len(source)} keys")
    print(f"target: {len(target)} keys")
    print(f"kept: {len(kept)}, dropped: {len(target) - len(kept)} -> {output}")


def main():
    parser = argparse.ArgumentParser(
        description="Compare Minecraft lang files."
    )
    sub = parser.add_subparsers(dest="command", required=True)

    p_missing = sub.add_parser(
        "missing",
        help="write the keys the target does not have from the source",
    )
    p_missing.add_argument("source", help="reference JSON (e.g. en_us.json)")
    p_missing.add_argument("target", help="JSON to compare (e.g. es_mx.json)")
    p_missing.add_argument("output", help="output JSON with the missing keys")
    p_missing.add_argument(
        "--empty-values",
        action="store_true",
        help="write empty strings instead of the source values",
    )
    p_missing.set_defaults(func=cmd_missing)

    p_prune = sub.add_parser(
        "prune",
        help="write the target without the keys the source no longer has",
    )
    p_prune.add_argument("source", help="reference JSON (e.g. en_us.json)")
    p_prune.add_argument("target", help="JSON to prune (e.g. es_mx.json)")
    p_prune.add_argument(
        "output",
        nargs="?",
        help="output JSON; omit it when using --in-place",
    )
    p_prune.add_argument(
        "--in-place",
        action="store_true",
        help="overwrite the target instead of writing a new file",
    )
    p_prune.add_argument(
        "--source-order",
        action="store_true",
        help="order the output by the source instead of the target order",
    )
    p_prune.set_defaults(func=cmd_prune)

    args = parser.parse_args()

    if args.command == "prune":
        if args.in_place and args.output:
            parser.error("--in-place takes no output file")
        if not args.in_place and not args.output:
            parser.error("missing output file (or use --in-place)")

    args.func(args)
    return 0


if __name__ == "__main__":
    sys.exit(main())
