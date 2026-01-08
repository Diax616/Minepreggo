package dev.dixmk.minepreggo;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

import dev.dixmk.minepreggo.utils.MinepreggoHelper;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class MinepreggoModPacketHandler {

	private MinepreggoModPacketHandler() {}

	private static final String PROTOCOL_VERSION = "1";
	public static final SimpleChannel INSTANCE  = NetworkRegistry.newSimpleChannel(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "main"), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);
	private static int messageID = 0;

	public static <T> void addNetworkMessage(Class<T> messageType, BiConsumer<T, FriendlyByteBuf> encoder, Function<FriendlyByteBuf, T> decoder, BiConsumer<T, Supplier<NetworkEvent.Context>> messageConsumer) {
		INSTANCE.registerMessage(messageID, messageType, encoder, decoder, messageConsumer);
		messageID++;
	}
}
