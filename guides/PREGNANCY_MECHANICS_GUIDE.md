# Pregnancy Mechanics Guide (AI-generated content)

## Overview
This guide explains how the pregnancy system works in the Minepreggo mod in simple terms. The system has three main phases that players will experience.
---

## How Players Become Pregnant

There are several ways a female player can become pregnant in this mod:

### 1. **Player-to-Player Interaction**
- Right-click on another player while standing near beds
- Both players must meet certain conditions (fertility, availability, etc.)
- A menu will appear to request consent
- If accepted, pregnancy may occur based on both players' fertility rates

### 2. **Attack by Hostile Mobs**
- Female players with the "Fertile" status effect can become pregnant when attacked by certain mobs:
  - **Zombies** - can cause Zombie species pregnancy
  - **Creepers** - can cause Creeper species pregnancy
  - **Endermen** - can cause Ender species pregnancy
- There's a 50% chance this will trigger when attacked
- The pregnancy will be with that species of mob

### 3. **Using Potions**
- Special pregnancy potions can be used to trigger pregnancy
- The potion's strength (amplifier) determines how many babies you'll have

### 4. **Breeding with Villagers**
- Female players can interact with villagers for breeding (if configured)
- Results in human-villager hybrid pregnancy

---

## The Three Phases of Pregnancy

### **Phase 1: Pre-Pregnancy (Conception Period)**

**Duration:** Configurable (default is a set amount of minutes after becoming pregnant)

**What Happens:**
- After conception, there's a waiting period before the pregnancy system fully activates
- During this time, the player may experience:
  - Random nausea and confusion effects (dizziness)
  - Visual distortion from morning sickness
- This is like an early pregnancy period where your body is adjusting

**Game Mechanics:**
- Fertility rate is at work during conception
- Number of babies is determined by both parents' fertility rates
- Species type is locked in
- Player receives notification of pregnancy

---

### **Phase 2: Pregnancy (The Main Journey)**

**Duration:** Configurable total pregnancy length (divided into 9 stages)

**Pregnancy Stages:** The pregnancy progresses through 9 stages (P0 through P8). The final stage reached depends on how many babies you're carrying:
- **P0**: Early pregnancy - minimal effects
- **P1**: Early pregnancy - cravings begin
- **P2**: Lactation starts, milking becomes possible
- **P3**: Noticeable belly growth, movement restrictions begin
- **P4**: Minimum for giving birth to **1 baby** - significant belly, more restrictions on armor and movement
- **P5**: Minimum for giving birth to **2 babies** - large belly, increased symptoms
- **P6**: Minimum for giving birth to **3 babies** - very large belly, severe restrictions
- **P7**: Minimum for giving birth to **4 babies** - near full term, major restrictions
- **P8**: Minimum for giving birth to **5+ babies** - full term, maximum belly size

*Note: Your pregnancy will progress to the stage appropriate for the number of babies you're carrying. For example, if you're having twins, you'll reach P5 before giving birth.*

**What Happens During Pregnancy:**

1. **Belly Growth**
   - Your character's belly grows larger with each stage
   - The belly has a breathing/moving animation
   - Size affects what armor you can wear

2. **Movement Effects (Stage 3+)**
   - **Jumping:** Too much jumping causes nausea and confusion
   - **Sprinting (Stage 3+):** Extended sprinting causes dizziness after a certain time
   - **Sneaking (Stage 4+):** Staying crouched too long causes discomfort

3. **Food Cravings (Stage 1+)**
   - You'll develop specific food cravings that build up over time
   - Eating the craved food satisfies the craving and provides relief
   - Ignoring cravings may cause discomfort
   - Different species pregnancies have different cravings

4. **Lactation (Stage 2+)**
   - Your character begins producing breast milk
   - Use a glass bottle on yourself to collect milk (creates "Human Breast Milk Bottle")
   - Milk production increases over time and needs to be relieved
   - Certain chest armor cannot be worn while lactating
   - If milk builds up too much, you'll get a "Lactation" status effect

5. **Belly Rubs (Stage 3+)**
   - Other players can right-click you to rub your belly
   - You can be calmed by pressing the "R" key once to rub your belly; the need will decrease every 6 seconds.
   - Sound effects play during belly rubs

6. **Horny (Stage 4+)**
   - Hostile mobs detect you from a greater distance
   - This can be calmed by initiating the sex scene with any valid entity regardless of gender; preggomobs, player, villager.

7. **Armor Restrictions**
   - **Chestplates:** Some become unwearable as belly grows; only special maternity armor works
   - **Leggings:** Cannot be worn from Stage 3 onwards (they don't fit!)
   - Attempting to wear incompatible armor gives a warning message

8. **Pregnancy Health**
   - The pregnancy itself has a health value
   - Taking damage while pregnant can harm the pregnancy
   - If pregnancy health drops to 0, miscarriage occurs
   - More dangerous at higher stages

9. **Special Effects for Creeper Pregnancy**
   - If pregnant with Creeper babies and at stage 4+, dying causes an explosion!
   - The explosion power depends on how far along you are

**End of Pregnancy:**
- When the timer completes, labor begins
- Birth process occurs (with pain system)
- Babies are born into the world
- Or, if pregnancy health was depleted, miscarriage occurs (40 seconds duration)

---

### **Phase 3: Post-Pregnancy (Recovery Period)**

**Duration:** Configurable (set amount of time after pregnancy ends)

The post-pregnancy phase has **two different types** depending on how the pregnancy ended:

---

#### **Type 1: Post Partum (After Successful Birth)**

After giving birth successfully, you enter a recovery period with maternal effects:

**Status Effects:**
- **Maternity:** Special status showing you're recovering from childbirth
- **Lactation:** Active milk production that needs relief
- **Luck:** Temporary positive effect (lasts 10 minutes)
- **Weakness:** Temporary weakness from the birthing process (lasts 3 minutes)

**Continued Lactation:**
- Milk production continues and gradually builds up over time
- You can milk yourself with glass bottles to collect "Human Breast Milk Bottle"
- Lactation level increases periodically and needs to be relieved
- Once lactation drops below a certain threshold, the lactation effect is removed
- Cannot wear certain chest armor while actively lactating

**Recovery Process:**
- This phase lasts for a configured amount of time
- During this period, your fertility rate is recovering
- You cannot become pregnant again while in this phase
- After the phase expires, all maternal effects are removed
- Fertility begins regenerating normally (takes 4 minutes to fully recover)

**Babies:**
- Newborn babies are given to you as items (in your hands or inventory)
- You receive the babies you gave birth to

---

#### **Type 2: Post Miscarriage (After Pregnancy Loss)**

If pregnancy health drops to zero from taking damage, a miscarriage occurs:

**What Happens:**
- **Miscarriage effect** activates (lasts 40 seconds)
- Pregnancy is immediately lost
- You experience the miscarriage process with associated pain sounds
- At stage P3 or higher, fetuses may spawn in the world
- Below stage P3, only fetal matter is lost

**Status Effects:**
- **Depression:** Sad mood effect from the pregnancy loss

**Recovery Process:**
- Shorter recovery period than Post Partum
- No lactation effects (milk production stops)
- Fertility recovery begins after this phase ends
- All effects are cleared when the phase expires

---

**End of Post-Pregnancy (Both Types):**
- All maternal and recovery effects are cleared
- Character returns to normal non-pregnant state
- Can become pregnant again once fertility is restored (4 minutes to full fertility)

---

## Important Time Conversions

For reference, here are the time conversions used in the mod:
- **20 ticks = 1 second**
- **2,400 ticks = 2 minutes** (sexual appetite regeneration)
- **4,800 ticks = 4 minutes** (fertility rate regeneration)
- **12,000 ticks = 10 minutes** (fertile effect duration)
- **800 ticks = 40 seconds** (miscarriage duration)

---

## Additional Systems

### **Fertility System**
- Both male and female players have a fertility rate (0.0 to 1.0)
- Fertility increases over time (every 4 minutes)
- Higher fertility = higher chance of multiple babies
- When fertility reaches maximum, females get "Fertile" status effect (lasts 10 minutes)

### **Male Players**
- Can collect "specimens" using Specimen Tubes
- Have a "FAP" meter that regenerates over time (every 2 minutes)
- Specimens are used in breeding mechanics

### **Sexual Appetite**
- Both genders have a sexual appetite meter
- Regenerates over time (every 2 minutes)
- Affects breeding availability

---

## Tips for Players

1. **Keep Food Ready:** Stock up on foods that might satisfy cravings
2. **Prepare Maternity Armor:** Get special armor that works during pregnancy
3. **Glass Bottles:** Keep these handy for milking yourself
4. **Avoid Danger:** Pregnancy can be lost from taking too much damage
5. **Find Safe Spaces:** Later pregnancy stages limit movement significantly
6. **Plan Ahead:** Know where you'll give birth safely

---

<img width="1366" height="768" alt="Minecraft_ 1 20 1 1_3_2026 4_46_54 PM" src="https://github.com/user-attachments/assets/a8f2c24b-8bce-4ffb-ba37-ea5f1c8b9959" />

<img width="1366" height="768" alt="Minecraft_ 1 20 1 1_3_2026 4_47_21 PM" src="https://github.com/user-attachments/assets/b87d6383-701c-4eb6-b1c0-72b1615d7046" />

<img width="1366" height="768" alt="Minecraft_ 1 20 1 1_3_2026 4_47_48 PM" src="https://github.com/user-attachments/assets/2bc1e58b-0c66-4dfb-b3f8-e2a5a165ef5d" />

<img width="1366" height="768" alt="Minecraft_ 1 20 1 1_3_2026 4_48_24 PM" src="https://github.com/user-attachments/assets/734a32e9-8bd5-4af9-845b-510569f1f2e4" />

<img width="1366" height="768" alt="Minecraft_ 1 20 1 1_3_2026 4_48_59 PM" src="https://github.com/user-attachments/assets/b62240cf-b8be-4b25-ab56-69699ee0bf37" />

<img width="1366" height="768" alt="Minecraft_ 1 20 1 1_3_2026 4_49_35 PM" src="https://github.com/user-attachments/assets/d5cc767a-f220-4347-aecb-6f6b0cf4cc5f" />

<img width="1366" height="768" alt="Minecraft_ 1 20 1 1_3_2026 4_50_13 PM" src="https://github.com/user-attachments/assets/0445c975-aee8-427f-9162-af7d5c151315" />

<img width="1366" height="768" alt="Minecraft_ 1 20 1 1_3_2026 4_50_49 PM" src="https://github.com/user-attachments/assets/56f32b64-2c49-44e6-ad14-8c195d6e4199" />

<img width="1366" height="768" alt="Minecraft_ 1 20 1 1_3_2026 4_51_08 PM" src="https://github.com/user-attachments/assets/8b5d24c4-df7b-4d79-8d81-75b9d704e386" />

<img width="1366" height="768" alt="Minecraft_ 1 20 1 1_3_2026 4_52_06 PM" src="https://github.com/user-attachments/assets/b02a56a6-87f7-40a5-8de4-03e82babaaa9" />

<img width="1366" height="768" alt="Minecraft_ 1 20 1 1_3_2026 4_53_18 PM" src="https://github.com/user-attachments/assets/c825347e-ac3d-46b0-840b-5747627a06ec" />

<img width="1366" height="768" alt="Minecraft_ 1 20 1 1_3_2026 4_55_19 PM" src="https://github.com/user-attachments/assets/7c01a819-401a-42c6-b3c8-7855fe7dfb19" />

<img width="1366" height="768" alt="Minecraft_ 1 20 1 1_3_2026 4_55_30 PM" src="https://github.com/user-attachments/assets/fa43235d-6886-4f04-8917-3bb2eae23981" />

