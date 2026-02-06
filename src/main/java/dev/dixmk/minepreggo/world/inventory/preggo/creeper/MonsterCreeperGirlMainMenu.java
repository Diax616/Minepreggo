package dev.dixmk.minepreggo.world.inventory.preggo.creeper;

import java.util.Optional;

import dev.dixmk.minepreggo.init.MinepreggoModMenus;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableMonsterCreeperGirl;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;

public class MonsterCreeperGirlMainMenu extends AbstractCreeperGirlMainMenu<TamableMonsterCreeperGirl> {	
	
	public final Optional<Boolean> pregnant;
	
	public MonsterCreeperGirlMainMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
		super(MinepreggoModMenus.MONSTER_CREEPER_GIRL_MAIN_MENU.get(), id, inv, extraData, TamableMonsterCreeperGirl.class);
	
		if (extraData != null) {
			this.pregnant = Optional.of(extraData.readBoolean());
		} else {
			this.pregnant = Optional.empty();
		}
	}
}