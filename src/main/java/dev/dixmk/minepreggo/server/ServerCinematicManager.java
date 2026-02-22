package dev.dixmk.minepreggo.server;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Nullable;

import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.npc.Villager;

public class ServerCinematicManager {	
    private final Map<UUID, CinematicSession<?>> activeSessions;
    private final Set<UUID> playersInCinematic;
    private final Set<UUID> preggoMobsInCinematic;
    private final Set<UUID> villagerInCinematic;
	
	private ServerCinematicManager() {
	    activeSessions = new HashMap<>();
	    playersInCinematic = new HashSet<>();
	    preggoMobsInCinematic = new HashSet<>();
	    villagerInCinematic = new HashSet<>();
	}
	
    private static class Holder {
        private static final ServerCinematicManager INSTANCE = new ServerCinematicManager();
    }
       	    
    public static ServerCinematicManager getInstance() {
        return Holder.INSTANCE;
    }
    
    public void start(ServerPlayer playerA, ServerPlayer playerB, @Nullable Runnable onStart, @Nullable Runnable onEnd) {
        if (isInCinematic(playerA) || isInCinematic(playerB)) return;

        var session = new CinematicP2PSession(playerA, playerB, onStart, onEnd);
        activeSessions.put(playerA.getUUID(), session); // or use session ID
        playersInCinematic.add(playerA.getUUID());
        playersInCinematic.add(playerB.getUUID());
        session.start();
    }

    public void start(ServerPlayer owner, PreggoMob preggoMob, @Nullable Runnable onStart, @Nullable Runnable onEnd) {
        if (isInCinematic(owner) || isInCinematic(preggoMob)) return;

        var session = new CinematicP2MSession(owner, preggoMob, onStart, onEnd);
        activeSessions.put(owner.getUUID(), session);
        playersInCinematic.add(owner.getUUID());
        preggoMobsInCinematic.add(preggoMob.getUUID());
        session.start();
    }
    
    public void start(ServerPlayer owner, Villager villager, @Nullable Runnable onStart, @Nullable Runnable onEnd) {
        if (isInCinematic(owner) || isInCinematic(villager)) return;

        var session = new CinematicP2VSession(owner, villager, onStart, onEnd);
        activeSessions.put(owner.getUUID(), session);
        playersInCinematic.add(owner.getUUID());
        villagerInCinematic.add(villager.getUUID());
        session.start();
    }
      
    public void end(ServerPlayer player) {
        CinematicSession<?> session = null;
        for (var entry : activeSessions.entrySet()) {
            if (entry.getValue().involves(player)) {
                session = entry.getValue();
                activeSessions.remove(entry.getKey());
                break;
            }
        }
        
        if (session != null) {
            playersInCinematic.remove(session.source);
            if (session instanceof CinematicP2PSession) {
                playersInCinematic.remove(session.target);
            }
            else if (session instanceof CinematicP2MSession) {
                preggoMobsInCinematic.remove(session.target);
            }
            else {
                villagerInCinematic.remove(session.target);
            }
            session.end();
        }
    }
    
 
    public boolean isInCinematic(ServerPlayer player) {
        return playersInCinematic.contains(player.getUUID());
    }
    
    public boolean isInCinematic(PreggoMob preggoMob) {
        return preggoMobsInCinematic.contains(preggoMob.getUUID());
    }
    
    public boolean isInCinematic(Villager villager) {
        return villagerInCinematic.contains(villager.getUUID());
    }

    private class CinematicSession<E extends LivingEntity> {
        public final UUID source;
        public final UUID target;
        
        @Nullable private final Runnable onStart;
        @Nullable private final Runnable onEnd;
        
        protected CinematicSession(ServerPlayer a, E b, @Nullable Runnable onStart, @Nullable Runnable onEnd) {
            this.source = a.getUUID();
            this.target = b.getUUID();
            this.onStart = onStart;
            this.onEnd = onEnd;
        }
    
        public boolean involves(LivingEntity entity) {
            return source.equals(entity.getUUID()) || target.equals(entity.getUUID());
        }
        
        public void start() { 
        	if (onStart != null) {
        		onStart.run(); 
        	}
        }
        public void end() { 
        	if (onEnd != null) {
        		onEnd.run(); 
        	}
        }
    }
       
    private class CinematicP2PSession extends CinematicSession<ServerPlayer> {     
        public CinematicP2PSession(ServerPlayer a, ServerPlayer b, @Nullable Runnable onStart, @Nullable Runnable onEnd) {
        	super(a, b, onStart, onEnd);
        }
    }
    
    private class CinematicP2MSession extends CinematicSession<PreggoMob> {        
        public CinematicP2MSession(ServerPlayer a, PreggoMob b, @Nullable Runnable onStart, @Nullable Runnable onEnd) {
        	super(a, b, onStart, onEnd);
        }
    }
    
    private class CinematicP2VSession extends CinematicSession<Villager> {        
        public CinematicP2VSession(ServerPlayer a, Villager b, @Nullable Runnable onStart, @Nullable Runnable onEnd) {
        	super(a, b, onStart, onEnd);
        }
    }
}
