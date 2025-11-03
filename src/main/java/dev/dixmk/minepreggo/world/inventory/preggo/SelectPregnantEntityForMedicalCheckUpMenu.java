package dev.dixmk.minepreggo.world.inventory.preggo;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.init.MinepreggoModMenus;
import dev.dixmk.minepreggo.world.entity.monster.ScientificIllager;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

public class SelectPregnantEntityForMedicalCheckUpMenu extends AbstractContainerMenu {
	public final Level level;
	public final Player player;
	protected final int x;
	protected final int y;
	protected final int z;
	private final Optional<ScientificIllager> target;
	private List<LivingEntity> pregnantLivingEntities = new ArrayList<>();
	
	public SelectPregnantEntityForMedicalCheckUpMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
		super(MinepreggoModMenus.SELECT_PREGNANT_ENTITY_FOR_MEDICAL_CHECKUP_MENU.get(), id);	
		this.player = inv.player;
		this.level = inv.player.level();

		ScientificIllager s = null;
		
		if (extraData != null) {
			BlockPos pos = extraData.readBlockPos();
			this.x = pos.getX();
			this.y = pos.getY();
			this.z = pos.getZ();						
			if (level.getEntity(extraData.readVarInt()) instanceof ScientificIllager scientificIllager) {
				s = scientificIllager;
			}			
			List<Integer> entityIds = extraData.readList(b -> b.readVarInt());				
			entityIds.forEach(entityId -> {
				if (this.level.getEntity(entityId) instanceof LivingEntity e) {
					pregnantLivingEntities.add(e);
				}
			});				
		} else {
			this.x = -1;
			this.y = -1;
			this.z = -1;
			MinepreggoMod.LOGGER.error("Buffer was null");
		}
		
		this.target = Optional.ofNullable(s);
	}

	@Override
	public boolean stillValid(Player player) {
		return this.target.isPresent() && this.target.get().isAlive();
	}
	
	public Optional<ScientificIllager> getScienticIllager() {
		return this.target;
	}
	
	public List<LivingEntity> getPregnantLivingEntities() {
		return this.pregnantLivingEntities;
	}
	
	@Override
	public ItemStack quickMoveStack(Player playerIn, int index) {
		return ItemStack.EMPTY;
	}
}
