package dev.dixmk.minepreggo.world.entity.preggo.ender;

import dev.dixmk.minepreggo.world.entity.preggo.Creature;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public abstract class AbstractTamableMonsterEnderWoman extends AbstractTamableEnderWoman {

	protected AbstractTamableMonsterEnderWoman(EntityType<? extends AbstractTamableEnderWoman> p_32485_, Level p_32486_) {
		super(p_32485_, p_32486_, Creature.MONSTER);
	}

}
