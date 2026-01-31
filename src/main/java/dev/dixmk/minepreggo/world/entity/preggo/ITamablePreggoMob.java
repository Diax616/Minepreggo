package dev.dixmk.minepreggo.world.entity.preggo;

import dev.dixmk.minepreggo.world.pregnancy.IBreedable;
import net.minecraft.server.level.ServerPlayer;

public interface ITamablePreggoMob<G extends IBreedable> {

	static final int MAX_FULLNESS = 20;
	
	ITamablePreggoMobData getTamableData();
	
    G getGenderedData();
    
    Inventory getInventory();
    
    boolean hasCustomHeadAnimation();
    
	void setCinematicOwner(ServerPlayer player);
    
    void setCinematicEndTime(long time);

    void setBreakBlocks(boolean breakBlocks);
    boolean canBreakBlocks();
}