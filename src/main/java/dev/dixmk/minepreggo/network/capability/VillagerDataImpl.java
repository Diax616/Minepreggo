package dev.dixmk.minepreggo.network.capability;

import java.util.Optional;
import java.util.UUID;

import org.checkerframework.checker.nullness.qual.NonNull;

import dev.dixmk.minepreggo.world.pregnancy.IObstetrician;
import dev.dixmk.minepreggo.world.pregnancy.PrenatalCheckupCostHolder;
import dev.dixmk.minepreggo.world.pregnancy.PrenatalCheckupCostHolder.PrenatalCheckupCost;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;

public class VillagerDataImpl implements IVillagerData, IObstetrician {
    private PrenatalCheckupCostHolder prenatalCheckUpCosts = new PrenatalCheckupCostHolder(3, 8);    
    private Optional<UUID> motherPlayer = Optional.empty();
    
	@Override
	public Optional<UUID> getMotherPlayerId() {		
		return motherPlayer;
	}
   
    @Override
    public PrenatalCheckupCost getPrenatalCheckupCosts() {
    	return this.prenatalCheckUpCosts.getValue();
    }
    
	public @NonNull Tag serializeNBT() {
		CompoundTag nbt = new CompoundTag();		
		motherPlayer.ifPresent(id -> nbt.putUUID("motherPlayer", id));	
		if (prenatalCheckUpCosts.isInitialized()) {
			nbt.put("prenatalCheckUpCosts", prenatalCheckUpCosts.serializeNBT());
		}
		return nbt;
	}
	
	public void deserializeNBT(@NonNull Tag tag) {
		CompoundTag nbt = (CompoundTag) tag;		
		if (nbt.contains("motherPlayer")) {
			motherPlayer = Optional.ofNullable(nbt.getUUID("motherPlayer"));
		}
		
		if (nbt.contains("prenatalCheckUpCosts", Tag.TAG_COMPOUND)) {
			prenatalCheckUpCosts.deserializeNBT(nbt.getCompound("prenatalCheckUpCosts"));
		}
	}
}
