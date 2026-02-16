# Tamed Preggo Mob Behavior & Emotions Guide (AI-generated content)

This guide explains how tamed pregnant mobs (Creeper Girls, Ender Women, Zombie Girls, etc.) behave and their emotional states. Understanding these behaviors is essential for maintaining a happy, obedient companion.

---

## Overview

Tamed pregnant mobs are intelligent creatures with emotional needs. They experience hunger, which directly affects their mood and loyalty. Poor care leads to behavioral problems ranging from mild grumpiness to outright hostility.

### Key Emotional States

1. **Neutral (Happy):** Well-fed and content
2. **Angry:** Hungry and irritable
3. **Savage:** Starving and hostile

---

## Fullness System

All tamed pregnant mobs have a **fullness level** that tracks how well-fed they are.

### Fullness Mechanics

- **Maximum Fullness:** 20
- **Drains from:** Walking, swimming, and other activities
- **Restored by:** Feeding the mob food it likes

### Hunger Drain Rate

Fullness decreases at different speeds based on activity:
- **Standing still:** 1 fullness per timer cycle
- **Walking/Running:** 2 fullness per timer cycle
- **Swimming:** Additional 2 fullness drain on top of movement

This means swimming pregnant mobs lose food VERY quickly. Keep them out of water during long periods to conserve their food supplies!

---

## The Angry State

When a tamed pregnancy mob becomes **hungry**, it enters an **Angry** emotional state.

### Angry Threshold & Triggers

- **Triggers when:** Fullness drops to 4 or below (80% hungry)
- **Message sent:** "[Mob Name] is angry"
- **Visible indicator:** Hostile particles spawn around the mob

### Angry Behavior

**Passive Behavior:**
- Mob follows owner normally
- Will defend you if you're attacked
- Responds to commands
- Still obeys your orders

**Aggressive Behavior:**
- After 100 game ticks (5 seconds) of being angry, the mob might randomly attack
- **Attack probability:** Varies by mob type (Creeper Girls are more prone to random aggression)
- **Important:** The mob will attack the owner **ONCE** and then immediately stop
- After hitting the owner, the mob's target resets to null (calms down from that attack)
- **Distance:** Attacks can trigger from up to 12 blocks away
- **Other conditions:** Won't attack if owner has creative mode/invincibility effects, or if the mob already has a valid target

### Exiting Angry State

The angry state automatically ends when:
- Fullness exceeds 4 (mob is fed to 5+ fullness)
- This happens immediately upon reaching the threshold
- Message sent: "[Mob Name] is no longer angry"
- Mob will stop targeting the owner and return to normal behavior

### Angry = Warning System

**Think of the angry state as a warning:** Your mob is telling you it's hungry. Feed it NOW before it becomes savage!

---

## The Savage State

When a tamed pregnant mob becomes **completely starving**, it enters the **Savage** state - the most dangerous emotional state.

### Savage Threshold & Triggers

- **Triggers when:** Fullness drops to 0 (completely empty)
- **Message sent:** "[Mob Name] is savage"
- **Immediate effects:** 
  - If leashed, the leash automatically drops
  - Mob becomes uncontrollable
  - Cannot open GUI menus or interact with it

### Savage Behavior

**Combat State:**
- Mob becomes **defensive**, not aggressive
- **Will NOT attack the owner unless the owner attacks it first**
- If the owner hits the mob, the mob will retaliate
- Shows angry particles constantly
- Cannot be commanded or controlled (will not follow, attack targets for you, or open inventory)
- Will not respond to orders

**Key Difference:** Savage mobs are not actively hostile—they're just refusing to take orders. They only fight back if attacked.

**Recovery from Savage:**
- **Feeding:** The ONLY way to calm a savage mob
- When fullness reaches 12 or higher, the savage state ends
- Once the mob is fed to 12+ fullness: Message "[Mob Name] is no longer savage"
- Mob immediately returns to taking orders
- Leash can be reattached
- Normal tamable behavior resumes

### Recovery Cost

Feeding a savage mob is actually safe and straightforward:
- The mob will NOT attack you unless you attack it first
- Simply right-click them with food to feed them
- They won't retaliate if you approach peacefully
- Each food item adds nutrition value (varies by food type)
- Feed them multiple times until reaching 12+ fullness
- Once fed, they immediately obey you again

**High-nutrition foods are essential for recovering from savage!** Use foods like:
- Steak (8 nutrition) or Cooked Porkchop (8 nutrition)
- Golden Carrots (6 nutrition + Saturation)
- Cooked Salmon (6 nutrition)

Avoid low-nutrition foods (mushroom stew = 6, bread = 5) as you'll need too many.

---



## Prevention: How to Keep Your Mob Happy

### Regular Feeding Schedule

**Minimum feeding:** At least once per in-game day
- More frequently if mob is active/swimming
- Swimming mobs need feeding every 20-30 minutes (real time)

**Optimal nutrition:**
- Keep fullness at 12+ for stable behavior
- Aim for 15+ to never trigger angry state
- Above 20 = maximum happiness

### Activity Management

**Reduce hunger drain:**
- Keep mob on land (swimming drains food fast!)
- Use leads/leashes to reduce wandering
- Station mob at a safe location when leaving

**Monitor during activities:**
- Long expeditions require extra food
- Combat drains more energy
- Swimming deserves regular feeding breaks

### Emergency Prevention

**Set a safe zone:**
- Fence off an area where the mob can't wander far
- Place food stations nearby
- Check regularly for fullness

**Inventory preparation:**
- Always carry extra food when with tamed mobs
- Stock different nutrition sources
- Plan feeding routes before long trips

---

## Comparison Table: Emotional States

| Aspect | Neutral (Happy) | Angry | Savage |
|--------|-----------------|-------|---------|
| **Fullness Level** | 5+ | 1-4 | 0 |
| **Mood Indicator** | Content | Angry particles | Aggressive particles |
| **Can be Leashed** | Yes | Yes | No |
| **Can Access GUI/Inventory** | Yes | Yes | No |
| **Follows Owner** | Yes | Yes | No |
| **Defends Owner** | Yes | Yes | No |
| **Takes Orders** | Yes | Yes | No |
| **Attacks Owner?** | No | Possibly once after 100 ticks, then resets | Only if owner attacks it first |
| **Can Be Fed** | Yes | Yes | Yes |
| **How to Exit** | Stay fed | Feed to 5+ | Feed to 12+ |
| **Messages** | None | "is angry" | "is savage" |
| **Recovery Danger** | N/A | Low (one hit maximum) | Safe (only attacks if provoked) |

---

## Recovery Protocol: Getting Your Mob Back to Normal

### If Your Mob is Angry

1. **Do NOT panic.** It's just hungry, not dangerous yet.
2. **Feed immediately to prevent escalation**
3. **Gather high-nutrition food** (steak, cooked salmon, golden carrots)
4. **Approach and right-click to feed**
5. **Feed** until fullness reaches 5+
6. **Important:** If you haven't fed it in 100+ ticks, it might make ONE attack attempt. If it does, it will immediately reset its target
7. **Keep distance** during the angry period if you want to be extra safe
8. **Prevention:** Feed regularly to prevent this state entirely

### If Your Mob is Savage

1. **Important:** The mob will NOT attack you unprovoked
2. **Approach cautiously** but calmly - don't hit it
3. **Gather high-nutrition food** to speed up recovery
4. **Right-click to feed** until fullness reaches 12+
5. **Simple and safe** - no combat needed
6. **Key rule:** As long as you don't attack it, it won't retaliate
7. **Once at 12+ fullness:** Mob immediately returns to obeying commands
8. **Why it's safe:** Savage mobs are defensive, not offensive

#### What NOT to Do

Do NOT:
- Hit the mob (it will only retaliate if you attack first)
- Try combat when feeding
- Panic - there's no threat unless you create one
- Use distance feeding or defensive prep - unnecessary and slow

#### Species-Specific Notes

Savage mobs behave the same across all species:
- **Creeper Girls:** Won't explode unless you hit them first
- **Ender Women:** Won't attack unless provoked
- **Zombie Girls:** Same defensive behavior

There's no special advantage needed - just feed them peacefully and they'll return to normal.

### Prevention is Easier Than Recovery

Feeding takes ~5 seconds. Recovery from savage takes several minutes and high risk. **Always maintain fullness above 5!**

---

## Frequently Asked Questions

**Q: Can my angry mob still defend me?**  
A: Yes! Angry mobs still follow you and will defend you from attacks. They're just irritable and might attack once after 100 ticks, but they're still obedient. Feed them to prevent that attack.

**Q: What happens if my angry mob hits me?**  
A: It will attack once and immediately reset its target. The anger attack is a single strike, not a sustained assault. After that, it will stop being aggressive until you feed it.

**Q: If my mob is savage, will it attack me?**  
A: No, not unless you attack it first! Savage mobs are defensive—they refuse to take orders, but they won't start a fight. Just approach peacefully and feed them.

**Q: But what if I accidentally hit my savage mob while trying to feed it?**  
A: If you do hit it, it will retaliate. Be careful with your clicks. Right-click (feed) instead of left-click (attack).

**Q: Why did my tamed mob suddenly become savage overnight?**  
A: It starved while you were away/AFK. Mobs drain fullness even when inactive. Remember to feed before logging off!

**Q: Can I prevent my mob from ever becoming angry?**  
A: Yes! Keep fullness above 4 at all times. Check fullness regularly and feed preventatively. With proper care, your mob stays happy forever.

**Q: What's the best food for tamed mobs?**  
A: Steak and cooked salmon (8 and 6 nutrition). Golden carrots are excellent too (6 nutrition + saturation benefits). Avoid bread and mushroom stew.

**Q: My Creeper Girl is savage. Will she explode?**  
A: Not unless you attack her first. Once she's savage, she's peaceful (unless provoked). Just feed her and she'll calm down.

**Q: How do I get a savage mob back to normal?**  
A: Simple: right-click them with food until fullness reaches 12+. The instant you hit 12+ fullness, they stop being savage and take orders again. No combat required.

**Q: How often should I feed my active mob?**  
A: Check every 5-10 minutes during active play. Swimming mobs need feeding every 20-30 minutes IRL. Inactive mobs can go longer but check before logging off.

**Q: Can I check my mob's fullness?**  
A: Open the mob's GUI menu (right-click) to see fullness level. If you can't open the GUI, it's either savage or waiting in another state.

**Q: Does my mob still age/progress pregnancy while starving?**  
A: No. Preggo mobs with fullness at 0 (savage state) pause their other timers. Feeding them resumes normal progression.

**Q: What happens if savage state triggers while I'm riding the mob?**  
A: The mob will dismount you and may attack if you hit them. Avoid mounting hungry mobs!

---

## Summary

Tamed pregnant mobs are rewarding companions with emotional depth:

- **Keep them fed** (fullness 5+) = loyal, happy, protective
- **Let them get hungry** (fullness 1-4) = angry, unpredictable, dangerous
- **Starve them** (fullness 0) = savage, hostile, uncontrollable

The key to a successful tamed mob relationship is **consistent feeding and monitoring**. Develop a feeding routine, carry food, and your mobs will be the most powerful allies in your world.

Remember: **A fed mob is a happy mob. A happy mob is a helpful mob.**

---

*This guide covers tamed preggo mob behavior mechanics for Minecraft Forge 1.20.1. Specific emotional state timings and probabilities may vary based on mob species and mod configuration.*
