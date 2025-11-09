package dev.dixmk.minepreggo.world.entity.preggo.creeper;

import dev.dixmk.minepreggo.world.entity.preggo.Creature;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancyStage;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancySystemP1;
import dev.dixmk.minepreggo.world.entity.preggo.PregnantPreggoMobSystem;
import dev.dixmk.minepreggo.world.item.ItemHelper;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public abstract class AbstractTamablePregnantHumanoidCreeperGirl<S extends PregnantPreggoMobSystem<?>, P extends PregnancySystemP1<?>> extends AbstractTamablePregnantCreeperGirl<S, P> {

	protected AbstractTamablePregnantHumanoidCreeperGirl(EntityType<? extends AbstractTamableCreeperGirl<?>> p_21803_,
			Level p_21804_, PregnancyStage pStage) {
		super(p_21803_, p_21804_, pStage);
		this.typeOfCreature = Creature.HUMANOID;
	}
	
	@Override
	protected boolean canReplaceArmorBasedInPregnancyStage(ItemStack armor) {
		return (ItemHelper.isChest(armor) && PreggoMobHelper.canUseChestplate(armor, this.getCurrentPregnancyStage()))
				|| (ItemHelper.isLegging(armor) && PreggoMobHelper.canUseLegging(armor, this.getCurrentPregnancyStage()));
	}
}
