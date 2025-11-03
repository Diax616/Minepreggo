package dev.dixmk.minepreggo.init;

import java.util.List;

import dev.dixmk.minepreggo.world.entity.npc.Trades;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades.ItemListing;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class MinepreggoModTrades {

	private MinepreggoModTrades() {}

	@SubscribeEvent
	public static void registerTrades(VillagerTradesEvent event) {		
		var profession = event.getType();
		
		if (profession == MinepreggoModVillagerProfessions.VILLAGER_DOCTOR.get()
				|| profession == VillagerProfession.FARMER
				|| profession == VillagerProfession.BUTCHER) {			
			addTrades(profession, event.getTrades());
		}		
	}
	
	private static void addTrades(VillagerProfession profession, Int2ObjectMap<List<ItemListing>> currentTrades) {
		final var newTrades = Trades.Villager.getLevels(profession);		
		
		if (newTrades == null) return;
		
		final var levels = newTrades.keySet().toIntArray();
		
		for (var level : levels) {			
			for (var trade : Trades.Villager.getTrades(profession, level)) {
				currentTrades.get(level).add(trade);
			}
		}
	}
}
