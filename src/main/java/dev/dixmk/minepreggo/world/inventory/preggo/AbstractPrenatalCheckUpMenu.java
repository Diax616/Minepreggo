package dev.dixmk.minepreggo.world.inventory.preggo;

import java.util.Optional;

import org.joml.Vector3i;

import dev.dixmk.minepreggo.network.chat.MessageHelper;
import dev.dixmk.minepreggo.world.item.checkup.PrenatalCheckups;
import dev.dixmk.minepreggo.world.item.checkup.PrenatalCheckups.PrenatalCheckup;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public abstract class AbstractPrenatalCheckUpMenu 
	<S extends LivingEntity, T extends Mob> extends AbstractContainerMenu {
	
	private static final int SIZE = 6;
	
	protected PregnancyPhase motherPregnancyPhase = PregnancyPhase.P0;
	public final Level level;
	public final Player player;
	protected Optional<T> target;
	protected Optional<S> source;
	protected Optional<Vector3i> pos;
	protected int emeraldForRegularCheckUp = 0;
	protected int emeraldForUltrasoundScan = 0;
	protected int emeraldForPaternityTest = 0;
	protected boolean valid = false; 
    private PrenatalCheckups checkUps = null;
    private boolean warningShown = false;
    
    private final SimpleContainer container = new SimpleContainer(SIZE); // 0-2: input, 3-5: output
    
    private final Int2ObjectMap<Boolean> tradeCompleted = new Int2ObjectOpenHashMap<>();
    
    private static final int EXTRA_DISPLACEMENT_X = -66;
    
	protected AbstractPrenatalCheckUpMenu(MenuType<?> p_38851_, int id, Inventory inv, FriendlyByteBuf buffer) {
		super(p_38851_, id);	
		this.player = inv.player;
		this.level = inv.player.level();
 	
		this.addSlot(new TradeInputSlot(container, 0, 3, PrenatalCheckup.REGULAR, 111 + EXTRA_DISPLACEMENT_X, 18));
		this.addSlot(new TradeInputSlot(container, 1, 4, PrenatalCheckup.ULTRASOUND_SCAN, 111 + EXTRA_DISPLACEMENT_X, 38));
		this.addSlot(new TradeInputSlot(container, 2, 5, PrenatalCheckup.PATERNITY_TEST, 111 + EXTRA_DISPLACEMENT_X, 58));
		this.addSlot(new TradeOutputSlot(container, 3, 0, PrenatalCheckup.REGULAR, 184 + EXTRA_DISPLACEMENT_X, 18));
		this.addSlot(new TradeOutputSlot(container, 4, 1, PrenatalCheckup.ULTRASOUND_SCAN, 184 + EXTRA_DISPLACEMENT_X, 38));
		this.addSlot(new TradeOutputSlot(container, 5, 2, PrenatalCheckup.PATERNITY_TEST, 184 + EXTRA_DISPLACEMENT_X, 58));

		tradeCompleted.put(0, Boolean.FALSE);
		tradeCompleted.put(1, Boolean.FALSE);
		tradeCompleted.put(2, Boolean.FALSE);
		
		for (int si = 0; si < 3; ++si)
			for (int sj = 0; sj < 9; ++sj)
				this.addSlot(new Slot(inv, sj + (si + 1) * 9, 68 + 8 + sj * 18 + EXTRA_DISPLACEMENT_X, 0 + 84 + si * 18));
		for (int si = 0; si < 9; ++si)
			this.addSlot(new Slot(inv, si, 68 + 8 + si * 18 + EXTRA_DISPLACEMENT_X, 0 + 142));
	
		this.readBuffer(buffer);
		
		this.initializeTrades();
	}

	protected void initializeTrades() {		
        // Generate trades once on server
        if (!player.level().isClientSide) {
            this.checkUps = createTradesForThisSession();
        }     
	}
	
	// Reads necessary data from the buffer, mainly the target entity and source entity
	protected abstract void readBuffer(FriendlyByteBuf buffer);
	
	// Creates the trades for this session, only called on server side, target and source have to be present here
	protected abstract PrenatalCheckups createTradesForThisSession();
	
	// Called when all trades have been successfully completed, only on server side
	protected abstract void onSuccessful();
	
	
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
			if (index < SIZE) {
				if (!this.moveItemStackTo(itemstack1, SIZE, this.slots.size(), true))
					return ItemStack.EMPTY;
				slot.onQuickCraft(itemstack1, itemstack);
			} else if (!this.moveItemStackTo(itemstack1, 0, SIZE, false)) {
				if (index < SIZE + 27) {
					if (!this.moveItemStackTo(itemstack1, SIZE + 27, this.slots.size(), true))
						return ItemStack.EMPTY;
				} else {
					if (!this.moveItemStackTo(itemstack1, SIZE, SIZE + 27, false))
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
		if (this.target.isPresent() && playerIn instanceof ServerPlayer serverPlayer) {		
			if (!serverPlayer.isAlive() || serverPlayer.hasDisconnected()) {			
				for (int i = 0; i < 3; ++i) {
					playerIn.drop(container.removeItem(i, container.getMaxStackSize()), false, false);
				}
			} 
			else {
				for (int i = 0; i < 3; ++i) {
					playerIn.getInventory().placeItemBackInInventory(container.removeItem(i, container.getMaxStackSize()), false);
				}
			}	
		}
	}
	
	public Optional<S> getSource() {
		return this.source;
	}
	
	public Optional<T> getTarget() {
		return this.target;
	}
	
	public int getEmeraldForRegularCheckUp() {
		return this.emeraldForRegularCheckUp;
	}
	
	public int getEmeraldForUltrasoundScan() {
		return this.emeraldForUltrasoundScan;
	}
	
	public int getEmeraldForPaternityTest() {
		return this.emeraldForPaternityTest;
	}
	
	private class TradeInputSlot extends Slot {
	    private final PrenatalCheckup prenatalCheckup;
	    private final int outputSlotIndex;
	    private TradeInputSlot(Container container, int slotIndex, int outputSlotIndex, PrenatalCheckup prenatalCheckup, int x, int y) {
	        super(container, slotIndex, x, y);
	        this.outputSlotIndex = outputSlotIndex;
	        this.prenatalCheckup = prenatalCheckup;
	    }

	    @Override
	    public void setChanged() {	        
	        if (player.level().isClientSide || checkUps == null) {
	        	return;
	        }

	        ItemStack input = container.getItem(this.getSlotIndex());
	        ItemStack currentOutput = container.getItem(outputSlotIndex);

	        if (tradeCompleted.getOrDefault(this.getSlotIndex(), Boolean.FALSE).booleanValue()) {
	            if (!currentOutput.isEmpty()) {
	                container.setItem(outputSlotIndex, ItemStack.EMPTY);
	            }
	            warningShown = false;
	        }
	        else if (!input.isEmpty() && checkUps.get(prenatalCheckup).canTrade(input)) {
	            if (motherPregnancyPhase.compareTo(prenatalCheckup.minRequiredPhase) <= -1) {
	                if (!warningShown) { // Only send once
	                    MessageHelper.sendTo(MessageHelper.asServerPlayer(player), 
	                        Component.translatable("chat.minepreggo.prenatal_checkup.message.warning", 
	                        prenatalCheckup.minRequiredPhase.toString()));
	                    warningShown = true;
	                }
	            }
	            else if(currentOutput.isEmpty())  {
	                container.setItem(outputSlotIndex, checkUps.get(prenatalCheckup).createOutput());
	            }
	        } else {
	            container.setItem(outputSlotIndex, ItemStack.EMPTY);
	            warningShown = false;
	        }
	        
	        super.setChanged();	        
	    }	   
	}
	
	
	private class TradeOutputSlot extends Slot {
	    private final int inputIndex;
	    private final PrenatalCheckup prenatalCheckup;
	    private TradeOutputSlot(Container container, int slotIndex, int inputIndex, PrenatalCheckup prenatalCheckup, int x, int y) {
	        super(container, slotIndex, x, y);
	        this.inputIndex = inputIndex;
	        this.prenatalCheckup = prenatalCheckup;
	    }
	    
	    @Override
	    public boolean mayPlace(ItemStack stack) {
	        return false;
	    }

	    @Override
	    public boolean mayPickup(Player player) {
	        return !this.getItem().isEmpty();
	    }
	    
		@Override
		public void onTake(Player entity, ItemStack stack) {
        	if (player.level().isClientSide || checkUps == null) {
                return;
        	}
    		
            var trade = checkUps.get(prenatalCheckup);  
            ItemStack inputStack = container.getItem(inputIndex);

            if (!tradeCompleted.getOrDefault(inputIndex, Boolean.TRUE).booleanValue() && trade.canTrade(inputStack)) {
                inputStack.shrink(trade.emeraldCount);
                tradeCompleted.computeIfPresent(inputIndex, (key, value) -> value = true);                
                container.setItem(this.getSlotIndex(), ItemStack.EMPTY);  
            }
            
            super.onTake(player, stack);
		}
	}
}
