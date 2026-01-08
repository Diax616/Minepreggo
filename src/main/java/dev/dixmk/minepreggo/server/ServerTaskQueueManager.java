package dev.dixmk.minepreggo.server;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import net.minecraftforge.fml.util.thread.SidedThreadGroups;

public class ServerTaskQueueManager {
    private final Collection<AbstractMap.SimpleEntry<Runnable, Integer>> workQueue = new ConcurrentLinkedQueue<>();

    private ServerTaskQueueManager() {}

    private static class Holder {
        private static final ServerTaskQueueManager INSTANCE = new ServerTaskQueueManager();
    }
    
    public static ServerTaskQueueManager getInstance() {
        return Holder.INSTANCE;
    }

    public void queueTask(int tick, Runnable action) {
		if (Thread.currentThread().getThreadGroup() == SidedThreadGroups.SERVER) {
			workQueue.add(new AbstractMap.SimpleEntry<>(action, tick));
		}
    }

    public void processTasks() {
        List<AbstractMap.SimpleEntry<Runnable, Integer>> actions = new ArrayList<>();
        workQueue.forEach(work -> {
            work.setValue(work.getValue() - 1);
            if (work.getValue() == 0)
                actions.add(work);
        });
        actions.forEach(e -> e.getKey().run());
        workQueue.removeAll(actions);
    }
}
