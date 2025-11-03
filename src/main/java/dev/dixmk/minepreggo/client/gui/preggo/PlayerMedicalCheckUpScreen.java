package dev.dixmk.minepreggo.client.gui.preggo;

import dev.dixmk.minepreggo.world.entity.monster.ScientificIllager;
import dev.dixmk.minepreggo.world.inventory.preggo.PlayerMedicalCheckUpMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;

public abstract class PlayerMedicalCheckUpScreen
	<T extends Mob, C extends PlayerMedicalCheckUpMenu<T>> extends AbstractMedicalCheckUpScreen<Player, T, C> {

	protected PlayerMedicalCheckUpScreen(C container, Inventory inventory, Component text) {
		super(container, inventory, text);
	}

	public static class DoctorVillager extends PlayerMedicalCheckUpScreen<Villager, PlayerMedicalCheckUpMenu.DoctorVillager> {
		public DoctorVillager(PlayerMedicalCheckUpMenu.DoctorVillager container, Inventory inventory, Component text) {
			super(container, inventory, text);
		}		
	}
	
	public static class Illager extends PlayerMedicalCheckUpScreen<ScientificIllager, PlayerMedicalCheckUpMenu.Illager> {

		public Illager(PlayerMedicalCheckUpMenu.Illager container, Inventory inventory, Component text) {
			super(container, inventory, text);
		}		
	}
}
