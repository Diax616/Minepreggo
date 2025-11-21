package dev.dixmk.minepreggo.init;

import dev.dixmk.minepreggo.network.capability.PlayerDataImpl;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;

public class MinepreggoCapabilities {

	private MinepreggoCapabilities() {}
	
	public static final Capability<PlayerDataImpl> PLAYER_DATA = CapabilityManager.get(new CapabilityToken<PlayerDataImpl>() {});
}
