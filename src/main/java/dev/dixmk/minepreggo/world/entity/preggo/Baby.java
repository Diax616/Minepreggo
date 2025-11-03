package dev.dixmk.minepreggo.world.entity.preggo;

import com.google.common.collect.ImmutableMap;

import dev.dixmk.minepreggo.init.MinepreggoModItems;
import net.minecraft.world.item.Item;

public enum Baby {
    ZOMBIE,
    HUMANOID_CREEPER,
    CREEPER,
    HUMAN,
	ENDER;
    
	protected static final ImmutableMap<Baby, Item> ALIVE_BABIES = ImmutableMap.of(
			Baby.HUMAN, MinepreggoModItems.BABY_HUMAN.get(), 
			Baby.ZOMBIE, MinepreggoModItems.BABY_ZOMBIE.get(), 
			Baby.HUMANOID_CREEPER, MinepreggoModItems.BABY_HUMANOID_CREEPER.get(),
			Baby.CREEPER, MinepreggoModItems.BABY_QUADRUPED_CREEPER.get());

	protected static final ImmutableMap<Baby, Item> DEAD_BABIES = ImmutableMap.of(
			Baby.HUMAN, MinepreggoModItems.DEAD_HUMAN_FETUS.get(), 
			Baby.ZOMBIE, MinepreggoModItems.DEAD_ZOMBIE_FETUS.get(), 
			Baby.HUMANOID_CREEPER, MinepreggoModItems.DEAD_HUMANOID_CREEPER_FETUS.get(),
			Baby.CREEPER, MinepreggoModItems.DEAD_QUADRUPED_CREEPER_FETUS.get());

	public static Item getDeadBabyItem(Baby babyType) {
		return DEAD_BABIES.get(babyType);
	}
	
	public static Item getAliveBabyItem(Baby babyType) {
		return ALIVE_BABIES.get(babyType);
	}
}
