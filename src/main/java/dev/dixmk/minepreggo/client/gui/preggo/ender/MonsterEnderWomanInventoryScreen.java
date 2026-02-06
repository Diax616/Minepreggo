package dev.dixmk.minepreggo.client.gui.preggo.ender;

import dev.dixmk.minepreggo.world.entity.preggo.ender.TamableMonsterEnderWoman;
import dev.dixmk.minepreggo.world.inventory.preggo.ender.MonsterEnderWomanInventoryMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MonsterEnderWomanInventoryScreen extends AbstractEnderWomanInventaryScreen<TamableMonsterEnderWoman, MonsterEnderWomanInventoryMenu> {
	public MonsterEnderWomanInventoryScreen(MonsterEnderWomanInventoryMenu container, Inventory inv, Component label) {
		super(container, inv, label);
	}
}
