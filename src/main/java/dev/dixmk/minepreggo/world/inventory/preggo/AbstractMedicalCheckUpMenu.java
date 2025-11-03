package dev.dixmk.minepreggo.world.inventory.preggo;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

import javax.annotation.Nullable;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.joml.Vector3i;

import dev.dixmk.minepreggo.world.entity.preggo.Baby;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancyStage;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.DataSlot;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

public abstract class AbstractMedicalCheckUpMenu
	<S extends LivingEntity, T extends Mob>	extends AbstractContainerMenu implements Supplier<Map<Integer, Slot>> {
	public final Level level;
	public final Player player;
	protected Optional<T> target;
	protected Optional<S> source;
	protected int numOfEmerald;
	protected Optional<Vector3i> pos;
	private IItemHandler internal = new MyItemHandler(1, this::updateSpecialFlag);
	private final Map<Integer, Slot> customSlots = new HashMap<>();
	private boolean pregnancyCheck = false;
		
	protected AbstractMedicalCheckUpMenu(MenuType<?> p_38851_, int id, Inventory inv) {
		super(p_38851_, id);
		this.player = inv.player;
		this.level = inv.player.level();
		
		this.customSlots.put(0, this.addSlot(new SlotItemHandler(internal, 0, 116, 53) {
			@Override
			public boolean mayPlace(ItemStack itemstack) {
				return !pregnancyCheck;
			}		
		}));

		this.addDataSlot(new DataSlot() {
	        @Override
	        public int get() {
	            return pregnancyCheck ? 1 : 0;
	        }

	        @Override
	        public void set(int value) {
	        	pregnancyCheck = value != 0;
	        }
	    });
				
		for (int si = 0; si < 3; ++si)
			for (int sj = 0; sj < 9; ++sj)
				this.addSlot(new Slot(inv, sj + (si + 1) * 9, 82 + 8 + sj * 18, 0 + 84 + si * 18));
		for (int si = 0; si < 9; ++si)
			this.addSlot(new Slot(inv, si, 82 + 8 + si * 18, 0 + 142));	
	}

	public boolean hasPreggoMobChecked() {
		return pregnancyCheck;
	}
	
	private void updateSpecialFlag() {	
		if (!pregnancyCheck) {		
			if (internal.getStackInSlot(0).is(Items.EMERALD)
				&& internal.getStackInSlot(0).getCount() >= numOfEmerald) {
				pregnancyCheck = true;	
				internal.extractItem(0, numOfEmerald, false);
				this.onSuccessful();
			}
			else {
				this.onFailure();
			}
		}
	}
	
	public abstract boolean isValid();
	
	protected abstract void onSuccessful();
	
	protected abstract void onFailure();
	
	@Nullable
	public abstract String getName();

	@Nullable
	public abstract PregnancyStage getCurrentStage();
	
	public abstract int getPregnancyHealth();
	
	public abstract int getNumberOfChildren();

	@Nullable
	public abstract Baby getBabyType();
	
	public abstract int getDaysToGiveBirth();
	
	public abstract int getDaysPassed();
	
	public abstract int getDaysToNextStage();
	
	
	@Override
	public boolean stillValid(Player player) {	
		return this.target.isPresent() && this.target.get().isAlive();
	}

	@Override
	public ItemStack quickMoveStack(Player playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.slots.get(index);
		
		if (slot.hasItem()) {
			
			ItemStack itemstack1 = slot.getItem();
			itemstack = itemstack1.copy();
			if (index < 1) {
				if (!this.moveItemStackTo(itemstack1, 1, this.slots.size(), true))
					return ItemStack.EMPTY;
				slot.onQuickCraft(itemstack1, itemstack);
			} else if (!this.moveItemStackTo(itemstack1, 0, 1, false)) {
				if (index < 1 + 27) {
					if (!this.moveItemStackTo(itemstack1, 1 + 27, this.slots.size(), true))
						return ItemStack.EMPTY;
				} else {
					if (!this.moveItemStackTo(itemstack1, 1, 1 + 27, false))
						return ItemStack.EMPTY;
				}
				return ItemStack.EMPTY;
			}
			if (itemstack1.getCount() == 0)
				slot.set(ItemStack.EMPTY);
			else
				slot.setChanged();
			if (itemstack1.getCount() == itemstack.getCount())
				return ItemStack.EMPTY;
				
			slot.onTake(playerIn, itemstack1);
		}
			
		return itemstack;
	}

	@Override
	protected boolean moveItemStackTo(ItemStack p_38904_, int p_38905_, int p_38906_, boolean p_38907_) {
		boolean flag = false;
		int i = p_38905_;
		if (p_38907_) {
			i = p_38906_ - 1;
		}
		if (p_38904_.isStackable()) {
			while (!p_38904_.isEmpty()) {
				if (p_38907_) {
					if (i < p_38905_) {
						break;
					}
				} else if (i >= p_38906_) {
					break;
				}
				Slot slot = this.slots.get(i);
				ItemStack itemstack = slot.getItem();
				if (slot.mayPlace(itemstack) && !itemstack.isEmpty() && ItemStack.isSameItemSameTags(p_38904_, itemstack)) {
					int j = itemstack.getCount() + p_38904_.getCount();
					int maxSize = Math.min(slot.getMaxStackSize(), p_38904_.getMaxStackSize());
					if (j <= maxSize) {
						p_38904_.setCount(0);
						itemstack.setCount(j);
						slot.set(itemstack);
						flag = true;
					} else if (itemstack.getCount() < maxSize) {
						p_38904_.shrink(maxSize - itemstack.getCount());
						itemstack.setCount(maxSize);
						slot.set(itemstack);
						flag = true;
					}
				}
				if (p_38907_) {
					--i;
				} else {
					++i;
				}
			}
		}
		if (!p_38904_.isEmpty()) {
			if (p_38907_) {
				i = p_38906_ - 1;
			} else {
				i = p_38905_;
			}
			while (true) {
				if (p_38907_) {
					if (i < p_38905_) {
						break;
					}
				} else if (i >= p_38906_) {
					break;
				}
				Slot slot1 = this.slots.get(i);
				ItemStack itemstack1 = slot1.getItem();
				if (itemstack1.isEmpty() && slot1.mayPlace(p_38904_)) {
					if (p_38904_.getCount() > slot1.getMaxStackSize()) {
						slot1.setByPlayer(p_38904_.split(slot1.getMaxStackSize()));
					} else {
						slot1.setByPlayer(p_38904_.split(p_38904_.getCount()));
					}
					slot1.setChanged();
					flag = true;
					break;
				}
				if (p_38907_) {
					--i;
				} else {
					++i;
				}
			}
		}
		return flag;
	}

	@Override
	public void removed(Player playerIn) {
		super.removed(playerIn);
		if (source.isEmpty() && playerIn instanceof ServerPlayer serverPlayer) {
			if (!serverPlayer.isAlive() || serverPlayer.hasDisconnected()) {
				for (int j = 0; j < internal.getSlots(); ++j) {
					playerIn.drop(internal.extractItem(j, internal.getStackInSlot(j).getCount(), false), false);
				}
			} else {
				for (int i = 0; i < internal.getSlots(); ++i) {
					playerIn.getInventory().placeItemBackInInventory(internal.extractItem(i, internal.getStackInSlot(i).getCount(), false));
				}
			}
		}
	}

	public Map<Integer, Slot> get() {
		return customSlots;
	}
	
	public Optional<S> getSource() {
		return this.source;
	}
	
	public Optional<T> getTarget() {
		return this.target;
	}
	
	public int getNumOfEmerald() {
		return this.numOfEmerald;
	}
	
	public Optional<Vector3i> getPos() {
		return this.pos;
	}
	
	private class MyItemHandler extends ItemStackHandler {
	    private final Runnable onChange;

	    public MyItemHandler(int slots, @NonNull Runnable onChange) {
	        super(slots);
	        this.onChange = onChange;
	    }

	    @Override
	    protected void onContentsChanged(int slot) {
	        super.onContentsChanged(slot);
            onChange.run();
	    }
	}
}
