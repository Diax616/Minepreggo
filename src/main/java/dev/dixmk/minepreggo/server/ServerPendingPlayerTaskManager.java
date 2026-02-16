package dev.dixmk.minepreggo.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.BiConsumer;

import dev.dixmk.minepreggo.MinepreggoMod;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.common.util.INBTSerializable;

public class ServerPendingPlayerTaskManager implements INBTSerializable<CompoundTag> {
	    
    private final Map<UUID, List<SimplePendingTask>> pendingTasks;
    private final Map<String, BiConsumer<ServerPlayer, CompoundTag>> actionRegistry;
    private ServerLevel level;
    
    private ServerPendingPlayerTaskManager() {
        this.pendingTasks = new HashMap<>();
        this.actionRegistry = new HashMap<>();
    }	
    
    private static class Holder {
        private static final ServerPendingPlayerTaskManager INSTANCE = new ServerPendingPlayerTaskManager();
    }
       	    
    public static ServerPendingPlayerTaskManager getInstance() {
        return Holder.INSTANCE;
    }
       
    public void registerAction(String actionId, BiConsumer<ServerPlayer, CompoundTag> action) {
        actionRegistry.put(actionId, action);
    }

    public void addTask(UUID playerUUID, String actionId, CompoundTag data) {
        List<SimplePendingTask> tasks = pendingTasks.computeIfAbsent(playerUUID, k -> new ArrayList<>());
        
        if (tasks.size() >= 50) {
        	MinepreggoMod.LOGGER.warn("Player {} has reached the maximum pending task limit. New task '{}' will be ignored.", playerUUID, actionId);
            return;
        }
        
        tasks.add(new SimplePendingTask(actionId, data));
        markDirty();
    }
     
    public void addTask(UUID playerUUID, String actionId) {
        addTask(playerUUID, actionId, new CompoundTag());
    }
    
    public void executePendingTasks(ServerPlayer player) {
        UUID uuid = player.getUUID();
        List<SimplePendingTask> tasks = pendingTasks.remove(uuid);
        
        if (tasks != null && !tasks.isEmpty()) {
        	List<SimplePendingTask> tasksCopy = new ArrayList<>(tasks);
            for (SimplePendingTask task : tasksCopy) {
                try {
                    BiConsumer<ServerPlayer, CompoundTag> action = actionRegistry.get(task.actionId);
                    if (action != null) {
                        action.accept(player, task.data);
                    } 
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            
            markDirty();
        }
    }
    
    public int getPendingTaskCount(UUID playerUUID) {
        List<SimplePendingTask> tasks = pendingTasks.get(playerUUID);
        return tasks != null ? tasks.size() : 0;
    }
    
    public void clearTasks(UUID playerUUID) {
        if (pendingTasks.remove(playerUUID) != null) {
            markDirty();
        }
    }
    
    public void setLevel(ServerLevel level) {
        this.level = level;
    }
    
    private void markDirty() {
        if (level == null) {
            throw new IllegalStateException("Cannot mark pending tasks as dirty without a valid server level reference");
        }
        PendingTasksSavedData.get(level).setDirty();
    }
    
	@Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        ListTag playersList = new ListTag();
        
        pendingTasks.forEach((uuid, tasks) -> {
            if (tasks.isEmpty()) return;
            
            CompoundTag playerEntry = new CompoundTag();
            playerEntry.putUUID("player", uuid);
            
            ListTag tasksList = new ListTag();
            tasks.forEach(task -> tasksList.add(task.save()));
            
            playerEntry.put("tasks", tasksList);
            playersList.add(playerEntry);
        });
        
        tag.put("players", playersList);
        return tag;
    }
    
	@Override
    public void deserializeNBT(CompoundTag tag) {
        pendingTasks.clear();
        
        if (!tag.contains("players")) return;
        
        ListTag playersListTag = tag.getList("players", Tag.TAG_COMPOUND);
        
        playersListTag.forEach(playerTag -> {
            try {       	
            	CompoundTag playerEntry = (CompoundTag) playerTag;
                
                if (!playerEntry.hasUUID("player")) {
                	MinepreggoMod.LOGGER.warn("Skipping pending task entry without valid player UUID");
                    return;
                }               
                UUID playerUUID = playerEntry.getUUID("player");
                ListTag tasksList = playerEntry.getList("tasks", Tag.TAG_COMPOUND);
                List<SimplePendingTask> tasks = new ArrayList<>(tasksList.size());
                               
                for (int j = 0; j < tasksList.size(); j++) {
                    tasks.add(SimplePendingTask.load(tasksList.getCompound(j)));
                }
                
                if (!tasks.isEmpty()) {
                    pendingTasks.put(playerUUID, tasks);
                }
                
            } catch (Exception e) {
            	e.printStackTrace();
            }
        });
        
        
        MinepreggoMod.LOGGER.info("Pending tasks loaded: {}", pendingTasks.size());
    }
    
    public static class SimplePendingTask {
        private final String actionId;
        private final CompoundTag data;
        
        public SimplePendingTask(String actionId, CompoundTag data) {
            this.actionId = actionId;
            this.data = data;
        }
        
        public CompoundTag save() {
            CompoundTag tag = new CompoundTag();
            tag.putString("action", actionId);
            tag.put("data", data.copy());
            return tag;
        }
        
        public static SimplePendingTask load(CompoundTag tag) {
            if (!tag.contains("action", Tag.TAG_STRING)) {
                throw new IllegalArgumentException("Missing 'action' field in pending task data");
            }
            
            String actionId = tag.getString("action");
            if (actionId.isEmpty()) {
                throw new IllegalArgumentException("Action ID cannot be empty in pending task data");
            }
            
            return new SimplePendingTask(
                actionId,
                tag.getCompound("data")
            );
        }
    }   
}
    