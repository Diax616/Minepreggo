package dev.dixmk.minepreggo.client.gui.preggo;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.world.entity.monster.ScientificIllager;
import dev.dixmk.minepreggo.world.inventory.preggo.PlayerPrenatalCheckUpMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class PlayerPrenatalCheckUpScreen 
	<T extends Mob, C extends PlayerPrenatalCheckUpMenu<T>> extends AbstractPrenatalCheckUpScreen<Player, T, C> {

	protected PlayerPrenatalCheckUpScreen(C container, Inventory inventory, Component text) {
		super(container, inventory, text);
		MinepreggoMod.LOGGER.debug("Opened PlayerPrenatalCheckUpScreen for player {}", container.player.getName().getString());
	}

	public static class VillagerScreen extends PlayerPrenatalCheckUpScreen<Villager, PlayerPrenatalCheckUpMenu.VillagerMenu> {
		public VillagerScreen(PlayerPrenatalCheckUpMenu.VillagerMenu container, Inventory inventory, Component text) {
			super(container, inventory, text);
		}		
	}
	
	public static class IllagerScreen extends PlayerPrenatalCheckUpScreen<ScientificIllager, PlayerPrenatalCheckUpMenu.IllagerMenu> {

		public IllagerScreen(PlayerPrenatalCheckUpMenu.IllagerMenu container, Inventory inventory, Component text) {
			super(container, inventory, text);
		}		
	}
}
