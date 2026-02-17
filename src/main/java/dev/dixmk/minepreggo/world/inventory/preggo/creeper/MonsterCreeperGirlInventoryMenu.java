package dev.dixmk.minepreggo.world.inventory.preggo.creeper;

import dev.dixmk.minepreggo.init.MinepreggoModMenus;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableMonsterCreeperGirl;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;

public class MonsterCreeperGirlInventoryMenu extends AbstractMonsterCreeperGirlInventoryMenu<TamableMonsterCreeperGirl> {
	public MonsterCreeperGirlInventoryMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
		super(MinepreggoModMenus.MONSTER_CREEPER_GIRL_INVENTORY_MENU.get(), id, inv, extraData, TamableMonsterCreeperGirl.class);
	}
}
