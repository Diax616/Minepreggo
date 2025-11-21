package dev.dixmk.minepreggo.world.entity.preggo.creeper;

import dev.dixmk.minepreggo.world.entity.preggo.Creature;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancyPhase;
import dev.dixmk.minepreggo.world.item.ItemHelper;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public abstract class AbstractMonsterPregnantHumanoidCreeperGirl extends AbstractMonsterPregnantCreeperGirl {

	protected AbstractMonsterPregnantHumanoidCreeperGirl(EntityType<? extends PreggoMob> p_21803_, Level p_21804_, PregnancyPhase currentPregnancyStage) {
		super(p_21803_, p_21804_, currentPregnancyStage);
		this.typeOfCreature = Creature.HUMANOID;
	}
	
	@Override
	protected boolean canReplaceArmorBasedInPregnancyStage(ItemStack armor) {
		return (ItemHelper.isChest(armor) && PreggoMobHelper.canUseChestplate(armor, this.getCurrentPregnancyStage()))
				|| (ItemHelper.isLegging(armor) && PreggoMobHelper.canUseLegging(armor, this.getCurrentPregnancyStage()));
	}
}
