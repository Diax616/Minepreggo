package dev.dixmk.minepreggo.world.entity.preggo.ender;

import dev.dixmk.minepreggo.world.entity.preggo.Creature;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public abstract class AbstractHostilMonsterEnderWoman extends AbstractHostilEnderWoman {

	protected AbstractHostilMonsterEnderWoman(EntityType<? extends AbstractHostilEnderWoman> p_32485_, Level p_32486_) {
		super(p_32485_, p_32486_, Creature.MONSTER);
	}
 
	@Override
	public boolean hasCustomHeadAnimation() {
		return false;
	}
	
	@Override
	public boolean canBeTamedByPlayer() {
		return false;
	}
	
	@Override
	public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
		return null;
	}
}
