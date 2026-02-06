package dev.dixmk.minepreggo.world.entity.preggo.creeper;

import dev.dixmk.minepreggo.world.entity.preggo.Creature;
import dev.dixmk.minepreggo.world.entity.preggo.Inventory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public abstract class AbstractTamableMonsterCreeperGirl extends AbstractTamableCreeperGirl {

	protected AbstractTamableMonsterCreeperGirl(EntityType<? extends AbstractTamableCreeperGirl> p_21803_, Level p_21804_) {
		super(p_21803_, p_21804_, Creature.MONSTER);
	}
	
	@Override
	protected boolean canReplaceArmorBasedInPregnancyPhase(ItemStack armor) {				
		return false;
	}
	
	@Override
	protected Inventory createInventory() {
		return MonsterCreeperHelper.createInventory();
	}
	
	@Override
	public String getSimpleName() {
		return MonsterCreeperHelper.SIMPLE_NAME;
	}
}
