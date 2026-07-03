# **Commands Guide**

This guide explains all the commands in the Minepreggo mod v1.3.1.

## Pregnancy Progress

### /preggo start &lt;target&gt; &lt;species&gt; &lt;creature&gt; \[babies\]

This command starts a pregnancy at the target.

&lt;target&gt; - The entities selected. It must be a target selector (@p, @s, etc) or a specific target, and only female targets will be affected.

&lt;species&gt; - The species selected. It can be human, zombie, villager, creeper, ender, slime and dragon.

&lt;creature&gt; - The type selected, which is humanoid or monster. If it is invalid (e.g. monster zombie), then it will be defaulted to the only valid one based on species.

\[babies\] - Number of babies for this pregnancy. If no number is entered, default to 1 baby.

### /preggo startat &lt;target&gt; &lt;species&gt; &lt;creature&gt; &lt;startPhase&gt; &lt;finalPhase&gt;

This command starts a pregnancy and select the initial phase and the ending phase at the target. The final phase determines the number of babies.

&lt;target&gt; - The entities selected. It must be a target selector (@p, @s, etc) or a specific target, and only female targets will be affected.

&lt;species&gt; - The species selected. It can be human, zombie, villager, creeper, ender, slime and dragon.

&lt;creature&gt; - The type selected, which is humanoid or monster. If it is invalid (e.g. monster zombie), then it will be defaulted to the only valid one based on species.

&lt;startPhase&gt; - The starting phase of this pregnancy. Must be chosen between p0 to p9.

&lt;finalPhase&gt; - The ending phase of this pregnancy. Must be chosen between p4 to p9.

### /preggo dragon &lt;target&gt;

This command starts an ender dragon pregnancy. Works identically to /preggo start.

&lt;target&gt; - The entities selected. It must be a target selector (@p, @s, etc) or a specific target, and only female targets will be affected.

### /preggo end &lt;target&gt;

This command cancel the current pregnancy. Does not trigger the ending events (labor, miscarriage, belly burst).

&lt;target&gt; - The entities selected. It must be a target selector (@p, @s, etc) or a specific target, and only female targets will be affected.

### /preggo birth &lt;target&gt;

This command forces labor to start, similar to an early birth from irritated womb. If the womb is overloaded, or the condition of early birth is not met, or when the target is already in labor, the command will not affect said targets.

&lt;target&gt; - The entities selected. It must be a target selector (@p, @s, etc) or a specific target, and only female targets will be affected.

### /preggo relievewomb &lt;target&gt;

This command removes excess babies in a similar manner to excess baby reduction from Scientific Illager.

&lt;target&gt; - The entities selected. It must be a target selector (@p, @s, etc) or a specific target, and only female targets will be affected.

## Pregnancy Pains

### /preggo pain &lt;pain&gt;

This command triggers a specified pregnancy pain on the user. The user must be pregnant or it will not be triggered.

&lt;pain&gt; - Pregnancy pain chosen, includes morning sickness, fetal movement, contraction and back pain.

### /preggo clearpain &lt;target&gt;

This command cancels all the current pregnancy pain on the target. The user must be pregnant and have one pregnancy pain active.

&lt;target&gt; - The entities selected. It must be a target selector (@p, @s, etc) or a specific target, and only female targets will be affected.

### /preggo symptom &lt;symptom&gt;

This command triggers a pregnancy symptom on the user. The user must be pregnant or it will not be triggered.

&lt;symptom&gt; - Pregnancy symptom chosen, includes craving, milking, belly rubs and horny.

## Miscellaneous

### /preggo gender &lt;target&gt; &lt;gender&gt;

This command changes the gender of the targets. Only the players with the different gender from the command and are not pregnant will be affected.

If the user insists, add a force behind the command, which forces all players of the different gender to change, cancelling the pregnancy in a similar manner of /preggo end.

&lt;target&gt; - The entities selected. It must be a target selector (@p, @s, etc) or a specific target, and only female targets will be affected. For this command, only players are accepted.

&lt;gender&gt; - Gender chosen, such as male or female.