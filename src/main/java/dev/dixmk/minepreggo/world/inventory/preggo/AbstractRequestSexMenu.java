package dev.dixmk.minepreggo.world.inventory.preggo;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.network.FriendlyByteBuf;

import java.util.Optional;

import org.apache.commons.lang3.tuple.ImmutablePair;


public abstract class AbstractRequestSexMenu<S extends LivingEntity, T extends LivingEntity> extends AbstractContainerMenu {
	public final Level level;
	public final Player player;
	public final Optional<T> target;
	public final Optional<S> source;
	
	protected AbstractRequestSexMenu(MenuType<?> menuType, int id, Inventory inv, FriendlyByteBuf extraData) {
		super(menuType, id);
		this.player = inv.player;
		this.level = inv.player.level();
		var pair = getSourceAndTarget(extraData);
		this.source = Optional.ofNullable(pair.getLeft());
		this.target = Optional.ofNullable(pair.getRight());	
	}

	protected abstract ImmutablePair<S, T> getSourceAndTarget(FriendlyByteBuf extraData);
	
	@Override
	public boolean stillValid(Player player) {
		return this.target.isPresent() && this.target.get().isAlive();
	}

	@Override
	public ItemStack quickMoveStack(Player playerIn, int index) {
		return ItemStack.EMPTY;
	}
}
