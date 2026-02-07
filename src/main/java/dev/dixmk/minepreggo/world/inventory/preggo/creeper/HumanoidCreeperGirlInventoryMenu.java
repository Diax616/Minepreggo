package dev.dixmk.minepreggo.world.inventory.preggo.creeper;

import dev.dixmk.minepreggo.init.MinepreggoModMenus;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableHumanoidCreeperGirl;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;

public class HumanoidCreeperGirlInventoryMenu extends AbstractHumanoidCreeperGirlInventoryMenu<TamableHumanoidCreeperGirl> {
	public HumanoidCreeperGirlInventoryMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
		super(MinepreggoModMenus.CREEPER_GIRL_INVENTORY_MENU.get(), id, inv, extraData, TamableHumanoidCreeperGirl.class);
	}
}
