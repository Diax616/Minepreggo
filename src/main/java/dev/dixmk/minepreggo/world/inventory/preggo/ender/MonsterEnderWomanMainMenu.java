package dev.dixmk.minepreggo.world.inventory.preggo.ender;

import java.util.Optional;

import dev.dixmk.minepreggo.init.MinepreggoModMenus;
import dev.dixmk.minepreggo.world.entity.preggo.ender.TamableMonsterEnderWoman;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;

public class MonsterEnderWomanMainMenu extends AbstractEnderWomanMainMenu<TamableMonsterEnderWoman> {
	
	public final Optional<Boolean> pregnant;
	
	public MonsterEnderWomanMainMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
		super(MinepreggoModMenus.MONSTER_ENDER_WOMAN_MAIN_MENU.get(), id, inv, extraData, TamableMonsterEnderWoman.class);
	
		if (extraData != null) {
			this.pregnant = Optional.of(extraData.readBoolean());
		} else {
			this.pregnant = Optional.empty();
		}
	}
}
