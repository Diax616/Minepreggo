package dev.dixmk.minepreggo.world.inventory.preggo.zombie;

import dev.dixmk.minepreggo.init.MinepreggoModMenus;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirl;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;

public class ZombieGirlMainMenu extends AbstractZombieGirlMainMenu<TamableZombieGirl> {
	public ZombieGirlMainMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
		super(MinepreggoModMenus.ZOMBIE_GIRL_MAIN_MENU.get(), id, inv, extraData, TamableZombieGirl.class);
	}
}
