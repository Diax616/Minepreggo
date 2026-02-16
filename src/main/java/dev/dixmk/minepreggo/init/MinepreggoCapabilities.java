package dev.dixmk.minepreggo.init;

import dev.dixmk.minepreggo.network.capability.EnderPowerDataImpl;
import dev.dixmk.minepreggo.network.capability.PlayerDataImpl;
import dev.dixmk.minepreggo.network.capability.VillagerDataImpl;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;

public class MinepreggoCapabilities {

	private MinepreggoCapabilities() {}
	
	public static final Capability<PlayerDataImpl> PLAYER_DATA = CapabilityManager.get(new CapabilityToken<PlayerDataImpl>() {});
	public static final Capability<VillagerDataImpl> VILLAGER_DATA = CapabilityManager.get(new CapabilityToken<VillagerDataImpl>() {});
	public static final Capability<EnderPowerDataImpl> ENDER_POWER_DATA = CapabilityManager.get(new CapabilityToken<EnderPowerDataImpl>() {});

}
