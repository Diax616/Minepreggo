package dev.dixmk.minepreggo.world.entity.preggo;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;

public interface ITamablePreggoMobSystem {

	void onServerTick();
	
	InteractionResult onRightClick(Player source);
	
	void cinematicTick();
	
	boolean canOwnerAccessGUI(Player source);
	
    void setCinematicOwner(ServerPlayer player);
   
    void setCinematicEndTime(long time);
}
