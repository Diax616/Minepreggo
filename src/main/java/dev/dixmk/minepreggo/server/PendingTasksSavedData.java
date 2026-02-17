package dev.dixmk.minepreggo.server;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;

public class PendingTasksSavedData extends SavedData {
    private static final String DATA_NAME = "pending_player_tasks";
    
    public static PendingTasksSavedData load(CompoundTag tag) {
        PendingTasksSavedData data = new PendingTasksSavedData();
        ServerPendingPlayerTaskManager.getInstance().deserializeNBT(tag);
        return data;
    }
    
    @Override
    public CompoundTag save(CompoundTag tag) {
        return ServerPendingPlayerTaskManager.getInstance().serializeNBT();
    }
    
    public static PendingTasksSavedData get(ServerLevel level) {
        return level.getServer().overworld().getDataStorage()
                .computeIfAbsent(
                    PendingTasksSavedData::load,
                    PendingTasksSavedData::new,
                    DATA_NAME
                );
    }
}
