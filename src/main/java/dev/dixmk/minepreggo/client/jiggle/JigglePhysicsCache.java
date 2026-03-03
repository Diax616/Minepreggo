package dev.dixmk.minepreggo.client.jiggle;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
abstract class JigglePhysicsCache {
	private boolean update = true;
	private int tickCounter = 0;
	private int ticksForUpdate = 0;

	protected JigglePhysicsCache(int ticksForUpdate) {
		this.ticksForUpdate = ticksForUpdate;	
	}
	
	public boolean shouldUpdate() {
		return update;
	}
	
	public void tick() {
		tickCounter++;
		if (tickCounter >= ticksForUpdate) {
			update = true;
			tickCounter = 0;
		} else if (update) {
			update = false;
		}
	}
	
	public abstract void refreshTrigCache();
}
