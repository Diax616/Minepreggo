package dev.dixmk.minepreggo.world.item;

import net.minecraft.world.item.Rarity;
import java.util.Optional;

import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.init.MinepreggoModItems;
import dev.dixmk.minepreggo.network.capability.IPlayerData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class CumSpecimenTubeItem extends Item {
	private static final String NBT_KEY = "malePlayerId";
	
	public CumSpecimenTubeItem() {
		super(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON));
	}
	
	/*
	 * TODO: NBT_KEY is an identifier for storing the UUID of the male player that provided the sample.
	 * It is expected that this UUID will be used later to link the specimen to the player for pregnancy mechanics like identify father.
	 * This mechanic is not yet implemented, so further development is needed to fully integrate this feature into the mod's pregnancy system.
	 * */

	public static Optional<ItemStack> createCumSpecimen(ServerPlayer source) {
		var result = source.getCapability(MinepreggoCapabilities.PLAYER_DATA).map(IPlayerData::isMale).orElse(null);

		if (result != null && result.booleanValue()) {
			var cum = new ItemStack(MinepreggoModItems.CUM_SPECIMEN_TUBE.get());
			cum.getOrCreateTag().putUUID(NBT_KEY, source.getUUID());	
			return Optional.of(cum);
		}
		
		return Optional.empty();
	}
	
	public static boolean tryTransferOwner(ItemStack input, ItemStack output) {
		if (input.is(MinepreggoModItems.CUM_SPECIMEN_TUBE.get())
				&& input.getOrCreateTag().hasUUID(NBT_KEY)) {
			output.getOrCreateTag().putUUID(NBT_KEY, input.getOrCreateTag().getUUID(NBT_KEY));	
			return true;
		}
		return false;
	}
}
