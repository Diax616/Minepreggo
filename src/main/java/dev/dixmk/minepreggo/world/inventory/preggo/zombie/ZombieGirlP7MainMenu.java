package dev.dixmk.minepreggo.world.inventory.preggo.zombie;

import dev.dixmk.minepreggo.init.MinepreggoModMenus;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirlP7;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;

public class ZombieGirlP7MainMenu extends AbstractZombieGirlMainMenu<TamableZombieGirlP7> {
	public ZombieGirlP7MainMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
		super(MinepreggoModMenus.ZOMBIE_GIRL_P7_MAIN_MENU.get(), id, inv, extraData, TamableZombieGirlP7.class);
	}
}
