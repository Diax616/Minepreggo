package dev.dixmk.minepreggo.world.entity.preggo.ender;

import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PlayMessages;

public class MonsterEnderWoman extends AbstractMonsterEnderWoman {
	
	public MonsterEnderWoman(PlayMessages.SpawnEntity packet, Level world) {
		this(MinepreggoModEntities.MONSTER_ENDER_WOMAN.get(), world);
	}

	public MonsterEnderWoman(EntityType<MonsterEnderWoman> type, Level world) {
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
