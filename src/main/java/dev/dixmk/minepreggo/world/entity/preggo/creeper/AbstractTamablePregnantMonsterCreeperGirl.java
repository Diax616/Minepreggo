package dev.dixmk.minepreggo.world.entity.preggo.creeper;

import dev.dixmk.minepreggo.world.entity.preggo.Creature;
import dev.dixmk.minepreggo.world.entity.preggo.Inventory;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public abstract class AbstractTamablePregnantMonsterCreeperGirl extends AbstractTamablePregnantCreeperGirl {

	protected AbstractTamablePregnantMonsterCreeperGirl(EntityType<? extends AbstractTamablePregnantCreeperGirl> p_21803_, Level p_21804_, PregnancyPhase currentPregnancyStage) {
		super(p_21803_, p_21804_, Creature.MONSTER, currentPregnancyStage);
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
	
	@Override
	protected ExplosionData updateExplosionByPregnancyPhase(PregnancyPhase pregnancyPhase) {
		return MonsterCreeperHelper.getExplosionValuesByPregnancyPhase(pregnancyPhase);
	}
}
