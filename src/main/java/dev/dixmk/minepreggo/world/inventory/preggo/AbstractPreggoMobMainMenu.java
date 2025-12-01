package dev.dixmk.minepreggo.world.inventory.preggo;

import java.util.Optional;

import org.joml.Vector3i;

import dev.dixmk.minepreggo.world.entity.preggo.ITamablePreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public abstract class AbstractPreggoMobMainMenu
	<E extends PreggoMob & ITamablePreggoMob<?>> extends AbstractContainerMenu {

	public final Level level;
	public final Player player;
	public final Class<E> preggoMobClass;
	protected Optional<Vector3i> pos;
	protected final Optional<E> preggoMob;
	
	protected AbstractPreggoMobMainMenu(MenuType<?> p_38851_, int p_38852_, Inventory inv, FriendlyByteBuf extraData, Class<E> preggoMobClass) {
		super(p_38851_, p_38852_);
		this.player = inv.player;
		this.level = inv.player.level();
		this.preggoMobClass = preggoMobClass;	
		this.preggoMob = this.readBuffer(extraData);
	}

	protected Optional<E> readBuffer(FriendlyByteBuf extraData) {		
		E mob = null;
		Vector3i p = null;
		if (extraData != null) {
			var posBlock = extraData.readBlockPos();			
			p = new Vector3i(posBlock.getX(), posBlock.getY(), posBlock.getZ());
			
			var e = level.getEntity(extraData.readVarInt());		
			if (e != null && preggoMobClass.isInstance(e))  {
				mob = preggoMobClass.cast(e);
			}	
		}				
		this.pos = Optional.ofNullable(p);
		
		return Optional.ofNullable(mob);
	}
	
		
	public Optional<Vector3i> getPos() {
		return this.pos;
	}
	
	public Optional<E> getPreggoMob() {
		return this.preggoMob;
	}
	
	@Override
	public boolean stillValid(Player player) {
		return this.preggoMob.isPresent() && this.preggoMob.get().isAlive();
	}

	@Override
	public ItemStack quickMoveStack(Player playerIn, int index) {
		return ItemStack.EMPTY;
	}
}
