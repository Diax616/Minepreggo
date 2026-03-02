package dev.dixmk.minepreggo.world.entity.preggo.ender;

import dev.dixmk.minepreggo.world.entity.preggo.Creature;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public abstract class AbstractHostileMonsterEnderWoman extends AbstractHostileEnderWoman {

	protected AbstractHostileMonsterEnderWoman(EntityType<? extends AbstractHostileEnderWoman> entityType, Level level) {
		super(entityType, level, Creature.MONSTER);
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
	public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob mate) {
		return null;
	}
}
