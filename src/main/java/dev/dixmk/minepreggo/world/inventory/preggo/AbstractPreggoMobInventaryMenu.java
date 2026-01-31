package dev.dixmk.minepreggo.world.inventory.preggo;

import java.util.Optional;

import org.joml.Vector3i;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.network.chat.MessageHelper;
import dev.dixmk.minepreggo.world.entity.preggo.ITamablePreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.ITamablePregnantPreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.InventorySlot;
import dev.dixmk.minepreggo.world.entity.preggo.InventorySlotMapper;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;
import dev.dixmk.minepreggo.world.item.ItemHelper;
import dev.dixmk.minepreggo.world.pregnancy.IFemaleEntity;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

@Mod.EventBusSubscriber
public abstract class AbstractPreggoMobInventaryMenu
	<E extends PreggoMob & ITamablePreggoMob<?>> extends AbstractContainerMenu {

	public final Level level;
	public final Player player;
	public final Class<E> preggoMobClass;
	protected Optional<Vector3i> pos;
	protected IItemHandler internal;
	protected final Optional<E> preggoMob;
	protected int invetorySize;
	
	protected AbstractPreggoMobInventaryMenu(MenuType<?> container, int id, Inventory inv, FriendlyByteBuf extraData, Class<E> preggoMobClass) {
		super(container, id);		
		this.player = inv.player;
		this.level = inv.player.level();
		this.preggoMobClass = preggoMobClass;		
		this.preggoMob = this.readBuffer(extraData);
		this.preggoMob.ifPresent(mob -> {
			var inventory = mob.getInventory();
			this.internal = inventory.getHandler();
			this.invetorySize = inventory.getSlotMapper().getTotalSlots();		
		});	
		this.createInventory(inv);	
	}

	protected abstract void createInventory(Inventory inv);
	
	
	protected void createDefaultHumanoidInventory() throws IllegalStateException {
		this.preggoMob.ifPresent(mob -> {	
			InventorySlotMapper inventoryMapper = mob.getInventory().getSlotMapper();
			
			if (!InventorySlotMapper.isHumanoidDefault(inventoryMapper)) {
				throw new IllegalStateException("Mob " + mob.getSimpleName() + " does not have humanoid default inventory");
			}
			
			MinepreggoMod.LOGGER.debug(inventoryMapper.getDebugMapping());
			
			var slotMapper = mob.getInventory().getSlotMapper();

			this.addSlot(new SlotItemHandler(internal, slotMapper.getSlotIndex(InventorySlot.HEAD), 8, 8) {
				@Override
				public boolean mayPlace(ItemStack itemstack) {
					return ItemHelper.isHelmet(itemstack);
				}
			});
	
			this.addSlot(new SlotItemHandler(internal, slotMapper.getSlotIndex(InventorySlot.CHEST), 8, 26) {
				@Override
				public boolean mayPlace(ItemStack itemstack) {	
					var armor = itemstack.getItem();
					boolean flag = ItemHelper.isChest(armor);			
					
					if (!flag) {
						return false;
					}
					
					if (mob instanceof ITamablePregnantPreggoMob tamablePregnantPreggoMob) {
						final var pregnancyData = tamablePregnantPreggoMob.getPregnancyData();
						final var pregnancyPhase = pregnancyData.getCurrentPregnancyPhase();					
						if (!PregnancySystemHelper.canUseChestplate(armor, pregnancyPhase, false)) {
							MessageHelper.warnFittedArmor((Player) mob.getOwner(), mob, pregnancyPhase);
			                flag = false;
						}	
						else if (!PreggoMobHelper.canUseChestPlateInLactation(tamablePregnantPreggoMob.getGenderedData(), armor)) {
							MessageHelper.sendTo(MessageHelper.asServerPlayer((Player) mob.getOwner()), Component.translatable("chat.minepreggo.preggo_mob.armor.message.lactating", mob.getSimpleName()));
			                flag = false;
						}
					}		
					else {                      							
						if (mob.getGenderedData() instanceof IFemaleEntity femaleData && !PreggoMobHelper.canUseChestPlateInLactation(femaleData, armor)) {
							MessageHelper.sendTo(MessageHelper.asServerPlayer((Player) mob.getOwner()), Component.translatable("chat.minepreggo.preggo_mob.armor.message.lactating", mob.getSimpleName()));
							flag = false;
						}
					}
			
					return flag;			
				}
			});

			this.addSlot(new SlotItemHandler(internal, slotMapper.getSlotIndex(InventorySlot.LEGS), 8, 44) {
				@Override
				public boolean mayPlace(ItemStack itemstack) {
					var armor = itemstack.getItem();
					if (!ItemHelper.isLegging(armor)) {
						return false;
					}
					
					if (mob instanceof ITamablePregnantPreggoMob tamablePregnantPreggoMob) {
						final var pregnancyData = tamablePregnantPreggoMob.getPregnancyData();
						final var pregnancyPhase = pregnancyData.getCurrentPregnancyPhase();					
						if (!PregnancySystemHelper.canUseLegging(armor, pregnancyPhase)) {
							MessageHelper.sendTo(MessageHelper.asServerPlayer((Player) mob.getOwner()), Component.translatable("chat.minepreggo.preggo_mob.armor.message.leggings_does_not_fit", mob.getSimpleName()));
			                return false;
						}			
					}												
					return true;			
				}
			});

			this.addSlot(new SlotItemHandler(internal, slotMapper.getSlotIndex(InventorySlot.FEET), 8, 62) {
				@Override
				public boolean mayPlace(ItemStack itemstack) {
					return ItemHelper.isBoot(itemstack);
				}
			});
			
			
			this.addSlot(new SlotItemHandler(internal, slotMapper.getSlotIndex(InventorySlot.MAINHAND), 77, 62));
			
			this.addSlot(new SlotItemHandler(internal, slotMapper.getSlotIndex(InventorySlot.OFFHAND), 95, 62));

			this.addSlot(new SlotItemHandler(internal, slotMapper.getSlotIndex(InventorySlot.FOOD), 113, 62) {
				@Override
				public boolean mayPlace(ItemStack itemstack) {
					return mob.isFood(itemstack);
				}
			});		
		});
		
	}
	
	protected Optional<E> readBuffer(FriendlyByteBuf extraData) {		
		E mob = null;
		Vector3i p = null;
		if (extraData != null) {
			
			var bpos = extraData.readBlockPos();
			p = new Vector3i(bpos.getX(), bpos.getY(), bpos.getZ());
			
			var e = level.getEntity(extraData.readVarInt());
			
			if (e != null && preggoMobClass.isInstance(e))  {
				mob = preggoMobClass.cast(e);
			}	
		}		
		
		this.pos = Optional.ofNullable(p);
		
		return Optional.ofNullable(mob);
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
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.slots.get(index);
		if (slot.hasItem()) {
			ItemStack itemstack1 = slot.getItem();
			itemstack = itemstack1.copy();
			if (index < invetorySize) {
				if (!this.moveItemStackTo(itemstack1, invetorySize, this.slots.size(), true))
					return ItemStack.EMPTY;
				slot.onQuickCraft(itemstack1, itemstack);
			} else if (!this.moveItemStackTo(itemstack1, 0, invetorySize, false)) {
				if (index < invetorySize + 27) {
					if (!this.moveItemStackTo(itemstack1, invetorySize + 27, this.slots.size(), true))
						return ItemStack.EMPTY;
				} else {
					if (!this.moveItemStackTo(itemstack1, invetorySize, invetorySize + 27, false))
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
	
	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END && event.player.containerMenu instanceof AbstractPreggoMobInventaryMenu<?> container) {						
			container.getPreggoMob().ifPresent(PreggoMobHelper::syncFromInventoryToEquipmentSlot);
		}
	}
}
