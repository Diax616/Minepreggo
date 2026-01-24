package dev.dixmk.minepreggo.world.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Supplier;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.world.entity.BellyPartFactory.BellyPartConfig;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import net.minecraft.world.entity.LivingEntity;

public class BellyPartManager {
    private final Map<UUID, BellyPart> bellyParts = new HashMap<>();

    private BellyPartManager() {}

    private static class Holder {
        private static final BellyPartManager INSTANCE = new BellyPartManager();
    }

    public static BellyPartManager getInstance() {
        return Holder.INSTANCE;
    }

    public void onServerTick(@Nonnull LivingEntity entity, @Nonnull Supplier<BellyPart> bellyPartSupplier) {
        if (entity.level().isClientSide) {
            return;
        }
    	BellyPart part = bellyParts.get(entity.getUUID());
    	
        if (!entity.isAlive() || entity.isRemoved()) {	
        	if (part != null && !part.isRemoved()) {
        		part.discard();
        	}	
        	bellyParts.remove(entity.getUUID());
        	MinepreggoMod.LOGGER.debug("Removed BellyPart for invalid entity {}", entity.getUUID());
        }    
        else if (part == null || part.isRemoved()) {
            // TODO: BellyPart creates unnecessary when player change dimension. Fix it.
        	
        	if (part != null && !part.isRemoved()) {
                part.discard();
            }    
            BellyPart newBellyPart = bellyPartSupplier.get();
            bellyParts.put(entity.getUUID(), newBellyPart); 
            entity.level().addFreshEntity(newBellyPart);
            MinepreggoMod.LOGGER.debug("Created BellyPart for entity {}", entity.getUUID());
        }
    }
    
    @CheckForNull
    public BellyPart get(LivingEntity entity) {
        return bellyParts.get(entity.getUUID());
    }
    
    public boolean remove(LivingEntity entity) {
		BellyPart part = bellyParts.remove(entity.getUUID());
		if (part != null && !part.isRemoved()) {
			part.discard();
			return true;
		}
		return false;
	}

    public BellyPart create(@Nonnull LivingEntity entity, @Nonnull BellyPartConfig bellyPartConfig) {
    	UUID entityId = entity.getUUID();
    	BellyPart currentBellyPart = bellyParts.get(entityId);
    	if (currentBellyPart != null && !currentBellyPart.isRemoved()) {
			currentBellyPart.discard();
		}
    	
		BellyPart newBellyPart = BellyPartFactory.createBellyPart(entity, bellyPartConfig);
		bellyParts.put(entityId, newBellyPart);
		entity.level().addFreshEntity(newBellyPart);
		return newBellyPart;
    }
    
    public BellyPart create(@Nonnull LivingEntity entity, @Nonnull PregnancyPhase phase) throws IllegalArgumentException {
    	BellyPartConfig config = BellyPartFactory.getHumanoidBellyConfig(phase);
		if (config == null) {
			throw new IllegalArgumentException("No BellyPartConfig found for PregnancyPhase: " + phase);
		}
		return create(entity, config);
	}

    public void clear() {
        for (BellyPart part : bellyParts.values()) {
            if (!part.isRemoved()) {
                part.discard();
            }
        }
        bellyParts.clear();
    }
}