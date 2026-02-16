package dev.dixmk.minepreggo.world.item;

import java.util.List;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class ConcentratedEnderLifeSubstanceItem extends LifeSubstanceItem {
	public ConcentratedEnderLifeSubstanceItem() {
		super(Rarity.RARE);
	}

	@Override
	public void appendHoverText(ItemStack itemstack, Level level, List<Component> list, TooltipFlag flag) {
		list.add(Component.translatable("item.minepreggo.concentrated_ender_life_substance.tooltip"));
	}
	
	@Override
	public boolean isFoil(ItemStack p_41172_) {
		return true;
	}
}
