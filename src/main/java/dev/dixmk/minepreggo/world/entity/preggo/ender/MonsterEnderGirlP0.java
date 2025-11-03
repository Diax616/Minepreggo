package dev.dixmk.minepreggo.world.entity.preggo.ender;

import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PlayMessages;

public class MonsterEnderGirlP0 extends AbstractMonsterEnderGirl {
	
	public MonsterEnderGirlP0(PlayMessages.SpawnEntity packet, Level world) {
		this(MinepreggoModEntities.MONSTER_ENDER_GIRL_P0.get(), world);
	}

	public MonsterEnderGirlP0(EntityType<MonsterEnderGirlP0> type, Level world) {
		super(type, world);
		xpReward = 12;
		setNoAi(false);
		setMaxUpStep(0.6f);
		setPersistenceRequired();
	}

	@Override
	public boolean isFoodToTame(ItemStack stack) {
		return false;
	}

	@Override
	public boolean hasCustomHeadAnimation() {
		return false;
	}
}
