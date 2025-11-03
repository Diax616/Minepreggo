package dev.dixmk.minepreggo.world.inventory.preggo;

import java.util.Optional;

import org.joml.Vector3i;

import dev.dixmk.minepreggo.init.MinepreggoModMenus;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class PlayerJoinsWorldMenu extends AbstractContainerMenu { 
	public final Level level;
	public final Player player;
	public final Optional<Vector3i> pos;

	public PlayerJoinsWorldMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
		super(MinepreggoModMenus.PLAYER_JOINS_WORLD_MENU.get(), id);			
		this.player = inv.player;
		this.level = inv.player.level();
		
		Vector3i p = null;
		
		if (extraData != null) {
			var blockPos = extraData.readBlockPos();
			p = new Vector3i(blockPos.getX(), blockPos.getY(), blockPos.getZ());
		}
		
		this.pos = Optional.ofNullable(p);
	}

	@Override
	public ItemStack quickMoveStack(Player p_38941_, int p_38942_) {
		return ItemStack.EMPTY;
	}

	@Override
	public boolean stillValid(Player p_38874_) {
		return player.isAlive();
	}
}
