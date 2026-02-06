package dev.dixmk.minepreggo.world.inventory.preggo.ender;

import dev.dixmk.minepreggo.init.MinepreggoModMenus;
import dev.dixmk.minepreggo.world.entity.preggo.ender.TamableMonsterEnderWoman;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;

public class MonsterEnderWomanInventoryMenu extends AbstractEnderWomanInventoryMenu<TamableMonsterEnderWoman> {
	public MonsterEnderWomanInventoryMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
		super(MinepreggoModMenus.MONSTER_ENDER_WOMAN_INVENTORY_MENU.get(), id, inv, extraData, TamableMonsterEnderWoman.class);
	}
}
