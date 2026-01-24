package dev.dixmk.minepreggo.world.entity.preggo;

import net.minecraft.world.entity.EquipmentSlot;
import javax.annotation.Nonnegative;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

public class InventoryInfo {
    private final Set<EquipmentSlot> validVanillaSlots;
    private final int extraInventorySlots;

    /**
     * @param validVanillaSlots Set of valid vanilla EquipmentSlots for this entity
     * @param extraInventorySlots Number of extra custom slots (non-vanilla)
     */
    public InventoryInfo(Set<EquipmentSlot> validVanillaSlots, @Nonnegative int extraInventorySlots) {
        this.validVanillaSlots = Collections.unmodifiableSet(EnumSet.copyOf(validVanillaSlots));
        this.extraInventorySlots = extraInventorySlots;
    }

    /**
     * Returns the set of valid vanilla EquipmentSlots for this entity.
     */
    public Set<EquipmentSlot> getValidVanillaSlots() {
        return validVanillaSlots;
    }

    /**
     * Returns true if the given EquipmentSlot is valid for this entity.
     */
    public boolean isValidVanillaSlot(EquipmentSlot slot) {
        return validVanillaSlots.contains(slot);
    }

    /**
     * Returns the number of extra custom inventory slots for this entity.
     */
    public int getExtraInventorySlots() {
        return extraInventorySlots;
    }
}