package dev.dixmk.minepreggo.world.entity.npc;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.annotation.CheckForNull;

import org.checkerframework.checker.nullness.qual.NonNull;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

import dev.dixmk.minepreggo.init.MinepreggoModItems;
import dev.dixmk.minepreggo.init.MinepreggoModPotions;
import dev.dixmk.minepreggo.init.MinepreggoModVillagerProfessions;
import dev.dixmk.minepreggo.world.entity.preggo.Species;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.Util;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.network.chat.Component;
import net.minecraft.util.RandomSource;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.npc.VillagerTrades.ItemListing;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.ForgeRegistries;

public class Trades {

	private Trades() {}
	
	public static class Villager {
		
		private Villager() {}
		
		protected static final Map<VillagerProfession, Int2ObjectMap<VillagerTrades.ItemListing[]>> TRADES = Util.make(Maps.newHashMap(), (p_35633_) -> {	   
			p_35633_.put(VillagerProfession.FARMER, new Int2ObjectOpenHashMap<>(ImmutableMap.of(
					1, new VillagerTrades.ItemListing[]
					{ new VillagerTrades.ItemsForEmeralds(MinepreggoModItems.CHILI_PEPPER.get(), 2, 10, 10),
					new VillagerTrades.ItemsForEmeralds(MinepreggoModItems.LEMON.get(), 2, 12, 10),
					new VillagerTrades.ItemsForEmeralds(MinepreggoModItems.CUCUMBER.get(), 4, 10, 10), 
					new VillagerTrades.EmeraldForItems(MinepreggoModItems.CHILI_PEPPER.get(), 13, 10, 10),
					new VillagerTrades.EmeraldForItems(MinepreggoModItems.LEMON.get(), 15, 10, 10)},
					2, new VillagerTrades.ItemListing[]
					{ new VillagerTrades.ItemsForEmeralds(MinepreggoModItems.CHILI_PEPPER.get(), 4, 25, 10),
					new VillagerTrades.ItemsForEmeralds(MinepreggoModItems.LEMON.get(), 4, 25, 10),
					new VillagerTrades.ItemsForEmeralds(Items.COCOA_BEANS, 10, 5, 10),
					new VillagerTrades.ItemsForEmeralds(MinepreggoModItems.CUCUMBER.get(), 8, 25, 10)},
					3, new VillagerTrades.ItemListing[] { 
					new VillagerTrades.ItemsForEmeralds(MinepreggoModItems.CHILI_PEPPER.get(), 6, 41, 10),
					new VillagerTrades.ItemsForEmeralds(Items.COCOA_BEANS, 15, 10, 10),
					new VillagerTrades.ItemsForEmeralds(MinepreggoModItems.LEMON.get(), 6, 50, 10),
					new VillagerTrades.EmeraldForItems(MinepreggoModItems.CUCUMBER.get(), 10, 10, 10)},
					4, new VillagerTrades.ItemListing[] { 
					new VillagerTrades.ItemsForEmeralds(Items.COCOA_BEANS, 17, 20, 15),
					new VillagerTrades.ItemsForEmeralds(MinepreggoModItems.CHOCOLATE_BAR.get(), 9, 3, 20)})));	
				
			p_35633_.put(VillagerProfession.BUTCHER, new Int2ObjectOpenHashMap<>(ImmutableMap.of(
					1, new VillagerTrades.ItemListing[] 
					{ new VillagerTrades.ItemsForEmeralds(MinepreggoModItems.LEMON_ICE_CREAM.get(), 4, 8, 10),
						new VillagerTrades.ItemsForEmeralds(MinepreggoModItems.HOT_SAUCE.get(), 7, 16, 10),
						new VillagerTrades.ItemsForEmeralds(MinepreggoModItems.SALT.get(), 3, 20, 10)},
					2, new VillagerTrades.ItemListing[]
					{ new VillagerTrades.ItemsForEmeralds(MinepreggoModItems.PICKLE.get(), 8, 20, 10),
						new VillagerTrades.ItemsForEmeralds(MinepreggoModItems.HOT_CHICKEN.get(), 10, 16, 10),
						new VillagerTrades.ItemsForEmeralds(MinepreggoModItems.LEMON_ICE_CREAM.get(), 7, 18, 10),
						new VillagerTrades.EmeraldForItems(MinepreggoModItems.PICKLE.get(), 10, 10, 10)},
					3, new VillagerTrades.ItemListing[]
					{ new VillagerTrades.ItemsForEmeralds(MinepreggoModItems.LEMON_ICE_POPSICLES.get(), 10, 24, 10),
						new VillagerTrades.ItemsForEmeralds(MinepreggoModItems.HOT_CHICKEN.get(), 17, 17, 10),
						new VillagerTrades.ItemsForEmeralds(MinepreggoModItems.PICKLE.get(), 13, 20, 10),
						new VillagerTrades.ItemsForEmeralds(MinepreggoModItems.CHOCOLATE_BAR.get(), 10, 5, 20)})));
			
			p_35633_.put(MinepreggoModVillagerProfessions.VILLAGER_DOCTOR.get(), new Int2ObjectOpenHashMap<>(ImmutableMap.of(
					1, new VillagerTrades.ItemListing[]
					{ new PotionForEmeralds(Potions.HEALING, PotionForEmeralds.PotionType.REGULAR, 15, 20, 15),
					  new PotionForEmeralds(MinepreggoModPotions.FERTILITY.get(), PotionForEmeralds.PotionType.REGULAR, 15, 20, 15)},
					2, new VillagerTrades.ItemListing[]
					{ new PotionForEmeralds(Potions.STRONG_HEALING, PotionForEmeralds.PotionType.SPLASH, 17, 20, 17),
						new PotionForEmeralds(Potions.HEALING, PotionForEmeralds.PotionType.SPLASH, 16, 20, 17)},
					3, new VillagerTrades.ItemListing[]
					{ new PotionForEmeralds(MinepreggoModPotions.ZERO_GRAVITY_BELLY.get(), PotionForEmeralds.PotionType.REGULAR, 15, 20, 15)},
					4, new VillagerTrades.ItemListing[]
					{ new PotionForEmeralds(MinepreggoModPotions.PREGNANCY_HEALING.get(), PotionForEmeralds.PotionType.REGULAR, 20, 20, 20)},
					5, new VillagerTrades.ItemListing[]
					{ new PotionForEmeralds(MinepreggoModPotions.PREGNANCY_RESISTANCE.get(), PotionForEmeralds.PotionType.SPLASH, 25, 20, 25)}))); 
		});
		
		@CheckForNull
		public static Int2ObjectMap<ItemListing[]> getLevels(VillagerProfession key) {			
			return TRADES.get(key);	
		}		
		
		@CheckForNull
		public static ItemListing[] getTrades(VillagerProfession key, int level) {	
			final var levels = getLevels(key);		
			if (levels != null) {
				return levels.get(level);		
			}		
			return null;	
		}	
	}
	
	public static class Illager {
		
		private Illager() {}
		
		protected static final List<VillagerTrades.ItemListing[]> TRADES = List.of(
				new VillagerTrades.ItemListing[]{ 
						new ItemsForItems(MinepreggoModItems.BABY_HUMAN.get(), 1, Items.DIAMOND, 24, 20),
						new ItemsForItems(MinepreggoModItems.BABY_HUMANOID_CREEPER.get(), 1, Items.DIAMOND, 32, 10),
						new ItemsForItems(MinepreggoModItems.BABY_HUMANOID_ENDER.get(), 1, Items.DIAMOND, 36, 10),
						new ItemsForItems(MinepreggoModItems.BABY_ENDER.get(), 1, Items.DIAMOND, 40, 20),
						new ItemsForItems(MinepreggoModItems.BABY_ZOMBIE.get(), 1, Items.DIAMOND, 20, 15),
						new ItemsForItems(MinepreggoModItems.BABY_CREEPER.get(), 1, Items.DIAMOND, 16, 10),
						new ItemsForItems(MinepreggoModItems.HUMAN_BREAST_MILK_BOTTLE.get(), 12, Items.DIAMOND, 16, 10),
						new ItemsForItems(MinepreggoModItems.CREEPER_BREAST_MILK_BOTTLE.get(), 12, Items.DIAMOND, 12, 10),
						new ItemsForItems(MinepreggoModItems.ZOMBIE_BREAST_MILK_BOTTLE.get(), 12, Items.GOLD_INGOT, 16, 10),
						new EnchantBookForBaby(Species.HUMAN),
						new EnchantBookForBaby(Species.CREEPER),
						new EnchantBookForBaby(Species.ZOMBIE),
						new EnchantBookForBaby(Species.ENDER),
						new ItemstackForItemstack(MinepreggoModItems.BABY_ENDER_DRAGON_BLOCK.get().getDefaultInstance(), createLootForBabyEnderDragon(2, 16, 8, 1, 32), 1, 10, 0.5f),
						new PotionForEmeralds(MinepreggoModPotions.PREGNANCY_ACCELERATION_0.get(), PotionForEmeralds.PotionType.REGULAR, 25, 10, 0),
						new PotionForEmeralds(MinepreggoModPotions.IMPREGNATION_POTION_0.get(), PotionForEmeralds.PotionType.REGULAR, 20, 10, 0),
						new PotionForEmeralds(MinepreggoModPotions.IMPREGNATION_POTION_1.get(), PotionForEmeralds.PotionType.REGULAR, 30, 10, 0),
						new PotionForEmeralds(MinepreggoModPotions.ZOMBIE_IMPREGNATION_0.get(), PotionForEmeralds.PotionType.REGULAR, 15, 10, 0),
						new PotionForEmeralds(MinepreggoModPotions.PREGNANCY_RESISTANCE.get(), PotionForEmeralds.PotionType.REGULAR, 27, 10, 0),
						new PotionForEmeralds(MinepreggoModPotions.PREGNANCY_HEALING.get(), PotionForEmeralds.PotionType.REGULAR, 27, 10, 0),
						new PotionForEmeralds(MinepreggoModPotions.ZERO_GRAVITY_BELLY.get(), PotionForEmeralds.PotionType.REGULAR, 20, 10, 0),
						new VillagerTrades.ItemsForEmeralds(MinepreggoModItems.VILLAGER_BRAIN.get(), 12, 24, 10, 0),
						new VillagerTrades.EmeraldForItems(MinepreggoModItems.DEAD_HUMAN_FETUS.get(), 2, 30, 10),
						new VillagerTrades.EmeraldForItems(MinepreggoModItems.DEAD_ZOMBIE_FETUS.get(), 3, 30, 10),
						new VillagerTrades.EmeraldForItems(MinepreggoModItems.DEAD_HUMANOID_CREEPER_FETUS.get(), 1, 30, 10),
						new VillagerTrades.EmeraldForItems(MinepreggoModItems.DEAD_CREEPER_FETUS.get(), 2, 30, 10),
						new ItemsForItems(MinepreggoModItems.BABY_VILLAGER.get(), 1, MinepreggoModItems.ENDER_LIFE_SUBSTANCE.get(), 1, 5)},	
				new VillagerTrades.ItemListing[]{
						new ItemsForItems(MinepreggoModItems.HUMAN_BREAST_MILK_BOTTLE.get(), 12, Items.DIAMOND, 16, 10),
						new ItemsForItems(MinepreggoModItems.CREEPER_BREAST_MILK_BOTTLE.get(), 12, Items.DIAMOND, 12, 10),
						new ItemsForItems(MinepreggoModItems.ZOMBIE_BREAST_MILK_BOTTLE.get(), 12, Items.GOLD_INGOT, 16, 10),
						new ItemsForItems(MinepreggoModItems.BABY_HUMAN.get(), 1, Items.DIAMOND, 24, 20),
						new ItemsForItems(MinepreggoModItems.BABY_HUMANOID_CREEPER.get(), 1, Items.DIAMOND, 32, 10),
						new ItemsForItems(MinepreggoModItems.BABY_HUMANOID_ENDER.get(), 1, Items.DIAMOND, 36, 10),
						new ItemsForItems(MinepreggoModItems.BABY_ENDER.get(), 1, Items.DIAMOND, 40, 20),
						new ItemsForItems(MinepreggoModItems.BABY_ZOMBIE.get(), 1, Items.DIAMOND, 20, 15),
						new ItemsForItems(MinepreggoModItems.BABY_CREEPER.get(), 1, Items.DIAMOND, 16, 10),
						new PotionForEmeralds(MinepreggoModPotions.IMPREGNATION_POTION_1.get(), PotionForEmeralds.PotionType.REGULAR, 30, 10, 0),
						new PotionForEmeralds(MinepreggoModPotions.ZOMBIE_IMPREGNATION_2.get(), PotionForEmeralds.PotionType.REGULAR, 33, 10, 0),
						new PotionForEmeralds(MinepreggoModPotions.CREEPER_IMPREGNATION_1.get(), PotionForEmeralds.PotionType.REGULAR, 35, 10, 0),
						new PotionForEmeralds(MinepreggoModPotions.PREGNANCY_ACCELERATION_0.get(), PotionForEmeralds.PotionType.REGULAR, 27, 10, 0),
						new PotionForEmeralds(MinepreggoModPotions.PREGNANCY_HEALING.get(), PotionForEmeralds.PotionType.REGULAR, 27, 10, 0),
						new PotionForEmeralds(MinepreggoModPotions.ZERO_GRAVITY_BELLY.get(), PotionForEmeralds.PotionType.REGULAR, 20, 10, 0),
						new EnchantBookForBaby(Species.HUMAN),
						new EnchantBookForBaby(Species.CREEPER),
						new EnchantBookForBaby(Species.ZOMBIE),
						new EnchantBookForBaby(Species.ENDER),
						new ItemstackForItemstack(MinepreggoModItems.BABY_ENDER_DRAGON_BLOCK.get().getDefaultInstance(), createLootForBabyEnderDragon(1, 24, 4, 1, 32), 1, 10, 0.5f),
						new VillagerTrades.ItemsForEmeralds(MinepreggoModItems.VILLAGER_BRAIN.get(), 12, 24, 10, 0),
						new VillagerTrades.EmeraldForItems(MinepreggoModItems.DEAD_HUMAN_FETUS.get(), 2, 30, 10),
						new VillagerTrades.EmeraldForItems(MinepreggoModItems.DEAD_ZOMBIE_FETUS.get(), 3, 30, 10),
						new VillagerTrades.EmeraldForItems(MinepreggoModItems.DEAD_HUMANOID_CREEPER_FETUS.get(), 1, 30, 10),
						new VillagerTrades.EmeraldForItems(MinepreggoModItems.DEAD_CREEPER_FETUS.get(), 2, 30, 10),
						new ItemsForItems(MinepreggoModItems.BABY_VILLAGER.get(), 1, MinepreggoModItems.CREEPER_LIFE_SUBSTANCE.get(), 1, 5)},
				new VillagerTrades.ItemListing[]{
						new ItemsForItems(MinepreggoModItems.HUMAN_BREAST_MILK_BOTTLE.get(), 12, Items.DIAMOND, 16, 10),
						new ItemsForItems(MinepreggoModItems.CREEPER_BREAST_MILK_BOTTLE.get(), 12, Items.DIAMOND, 12, 10),
						new ItemsForItems(MinepreggoModItems.ZOMBIE_BREAST_MILK_BOTTLE.get(), 12, Items.GOLD_INGOT, 16, 10),
						new ItemsForItems(MinepreggoModItems.BABY_HUMAN.get(), 1, Items.DIAMOND, 24, 20),
						new ItemsForItems(MinepreggoModItems.BABY_HUMANOID_CREEPER.get(), 1, Items.DIAMOND, 32, 10),
						new ItemsForItems(MinepreggoModItems.BABY_HUMANOID_ENDER.get(), 1, Items.DIAMOND, 36, 10),
						new ItemsForItems(MinepreggoModItems.BABY_ENDER.get(), 1, Items.DIAMOND, 40, 20),
						new ItemsForItems(MinepreggoModItems.BABY_ZOMBIE.get(), 1, Items.DIAMOND, 20, 15),
						new ItemsForItems(MinepreggoModItems.BABY_CREEPER.get(), 1, Items.DIAMOND, 16, 10),	
						new PotionForEmeralds(MinepreggoModPotions.ZOMBIE_IMPREGNATION_3.get(), PotionForEmeralds.PotionType.REGULAR, 15, 15, 0),
						new PotionForEmeralds(MinepreggoModPotions.IMPREGNATION_POTION_2.get(), PotionForEmeralds.PotionType.REGULAR, 36, 15, 0),
						new PotionForEmeralds(MinepreggoModPotions.LONG_METABOLISM_CONTROL.get(), PotionForEmeralds.PotionType.REGULAR, 20, 20, 0),
						new PotionForEmeralds(MinepreggoModPotions.PREGNANCY_RESISTANCE.get(), PotionForEmeralds.PotionType.REGULAR, 27, 25, 0),
						new PotionForEmeralds(MinepreggoModPotions.ZERO_GRAVITY_BELLY.get(), PotionForEmeralds.PotionType.REGULAR, 20, 10, 0),
						new EnchantBookForBaby(Species.HUMAN),
						new EnchantBookForBaby(Species.CREEPER),
						new EnchantBookForBaby(Species.ZOMBIE),
						new EnchantBookForBaby(Species.ENDER),
						new ItemstackForItemstack(MinepreggoModItems.BABY_ENDER_DRAGON_BLOCK.get().getDefaultInstance(), createLootForBabyEnderDragon(3, 16, 4, 1, 4), 1, 10, 0.5f),
						new VillagerTrades.ItemsForEmeralds(MinepreggoModItems.VILLAGER_BRAIN.get(), 12, 24, 10, 0),
						new VillagerTrades.EmeraldForItems(MinepreggoModItems.DEAD_HUMAN_FETUS.get(), 2, 30, 10),
						new VillagerTrades.EmeraldForItems(MinepreggoModItems.DEAD_ZOMBIE_FETUS.get(), 3, 30, 10),
						new VillagerTrades.EmeraldForItems(MinepreggoModItems.DEAD_HUMANOID_CREEPER_FETUS.get(), 1, 30, 10),
						new VillagerTrades.EmeraldForItems(MinepreggoModItems.DEAD_CREEPER_FETUS.get(), 2, 30, 10),
						new ItemsForItems(MinepreggoModItems.BABY_VILLAGER.get(), 1, MinepreggoModItems.ZOMBIE_LIFE_SUBSTANCE.get(), 1, 5)});
		
		@NonNull
		public static VillagerTrades.ItemListing[] getRandomTrades(RandomSource random) {		
			return TRADES.get(random.nextInt(0, TRADES.size()));	
		}

		private static ItemStack createLootForBabyEnderDragon(int mendingBooks, int netheriteIngots, int goldenApples, int enchantedGoldenApples, int diamonds) {
		       ItemStack shulkerBox = new ItemStack(Blocks.SHULKER_BOX);		        
		       NonNullList<ItemStack> inventory = createLoot(27, mendingBooks, netheriteIngots, goldenApples, enchantedGoldenApples, diamonds);
		       Collections.shuffle(inventory);
		        
		       for (int index = inventory.size() - 1; index >= 0; index--) {
		    	   if (inventory.get(index).isEmpty()) {
		    		   inventory.set(index, createMinepreggoBook());
		    		   break;
		    	   }
		       }
			      
		       CompoundTag beTag = new CompoundTag();
		       ContainerHelper.saveAllItems(beTag, inventory);
		       shulkerBox.getOrCreateTag().put("BlockEntityTag", beTag);     
		       return shulkerBox;
		}	
		
		private static NonNullList<ItemStack> createLoot(int size, int mendingBooks, int netheriteIngots, int goldenApples, int enchantedGoldenApples, int diamonds) {
		    NonNullList<ItemStack> inventory = NonNullList.withSize(size, ItemStack.EMPTY);
		    int currentSlot = 0;
		    		    
		    while (mendingBooks > 0 && currentSlot < size) {
		        ItemStack enchantedBook = EnchantedBookItem.createForEnchantment(new EnchantmentInstance(Enchantments.MENDING, 1));
		        int stackSize = Math.min(mendingBooks, enchantedBook.getMaxStackSize());
		        enchantedBook.setCount(stackSize);
		        inventory.set(currentSlot++, enchantedBook);
		        mendingBooks -= stackSize;
		    }
		    
		    while (netheriteIngots > 0 && currentSlot < size) {
		        int stackSize = Math.min(netheriteIngots, Items.NETHERITE_INGOT.getDefaultInstance().getMaxStackSize());
		        inventory.set(currentSlot++, new ItemStack(Items.NETHERITE_INGOT, stackSize));
		        netheriteIngots -= stackSize;
		    }
		    
		    while (goldenApples > 0 && currentSlot < size) {
		        int stackSize = Math.min(goldenApples, Items.GOLDEN_APPLE.getDefaultInstance().getMaxStackSize());
		        inventory.set(currentSlot++, new ItemStack(Items.GOLDEN_APPLE, stackSize));
		        goldenApples -= stackSize;
		    }
		    
		    while (enchantedGoldenApples > 0 && currentSlot < size) {
		        int stackSize = Math.min(enchantedGoldenApples, Items.ENCHANTED_GOLDEN_APPLE.getDefaultInstance().getMaxStackSize());
		        inventory.set(currentSlot++, new ItemStack(Items.ENCHANTED_GOLDEN_APPLE, stackSize));
		        enchantedGoldenApples -= stackSize;
		    }
		    
		    while (diamonds > 0 && currentSlot < size) {
		        int stackSize = Math.min(diamonds, Items.DIAMOND.getDefaultInstance().getMaxStackSize());
		        inventory.set(currentSlot++, new ItemStack(Items.DIAMOND, stackSize));
		        diamonds -= stackSize;
		    }
		         
		    return inventory;
		}
		
		private static ItemStack createMinepreggoBook() {
	        ItemStack book = new ItemStack(Items.WRITTEN_BOOK);
	        CompoundTag nbt = book.getOrCreateTag();
	        nbt.putString("title", Component.translatable("book.minepreggo.end.title").getString());       
	        nbt.putString("author", "DixMK");
	        ListTag pages = new ListTag();
	        
	        String pageContent = "[{\"text\":\"⠀\",\"color\":\"black\"},{\"text\":\"⢀\",\"color\":\"gray\"},{\"text\":\"⣠\",\"color\":\"dark_gray\"},{\"text\":\"⣤\",\"color\":\"black\"},{\"text\":\"⣾⣿⣿⣿⣿⣿\",\"color\":\"dark_gray\"},{\"text\":\"⣧⡄\",\"color\":\"gray\"},{\"text\":\"⠀⠀⠀⠀\",\"color\":\"black\"},{\"text\":\"⢸⣿\",\"color\":\"gray\"},{\"text\":\"⣿⣿⣿⣿\",\"color\":\"gold\"},{\"text\":\"⣿⣿\",\"color\":\"dark_gray\"},{\"text\":\"⣤\",\"color\":\"black\"},{\"text\":\"⣄\",\"color\":\"dark_gray\"},{\"text\":\"⡀\",\"color\":\"gray\"},{\"text\":\"⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\\n⠀\",\"color\":\"black\"},{\"text\":\"⣾⣿\",\"color\":\"dark_gray\"},{\"text\":\"⣿⣿⣿⣿⣿⣿⣿⣿⣿⣶⣶⣶⣶⣿⣿⣿⣿\",\"color\":\"gray\"},{\"text\":\"⣿⣿\",\"color\":\"gold\"},{\"text\":\"⣿⣿⣿\",\"color\":\"gray\"},{\"text\":\"⣿⣷\",\"color\":\"dark_gray\"},{\"text\":\"⡆\",\"color\":\"gray\"},{\"text\":\"⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\\n⠀⣿\",\"color\":\"black\"},{\"text\":\"⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\",\"color\":\"gray\"},{\"text\":\"⣿\",\"color\":\"gold\"},{\"text\":\"⣿⣿⣿⣿⣿\",\"color\":\"gray\"},{\"text\":\"⣿\",\"color\":\"black\"},{\"text\":\"⡇\",\"color\":\"gray\"},{\"text\":\"⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\\n⠀⣿\",\"color\":\"black\"},{\"text\":\"⣿⣿⣿⣿⣿⣿⣿\",\"color\":\"gray\"},{\"text\":\"⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\",\"color\":\"dark_gray\"},{\"text\":\"⣿⣿⣿⣿⣿⣿⣿\",\"color\":\"gray\"},{\"text\":\"⣿\",\"color\":\"dark_gray\"},{\"text\":\"⡇\",\"color\":\"gray\"},{\"text\":\"⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\\n⠀\",\"color\":\"black\"},{\"text\":\"⠘\",\"color\":\"gray\"},{\"text\":\"⢻⣿⣿⣿⣿⣿\",\"color\":\"dark_gray\"},{\"text\":\"⣿⣿\",\"color\":\"gray\"},{\"text\":\"⣿⣿⣿⣿⣿⣿⣿⣿\",\"color\":\"dark_gray\"},{\"text\":\"⣿⣿\",\"color\":\"gray\"},{\"text\":\"⣿⣿⣿⣿⣿⡟\",\"color\":\"dark_gray\"},{\"text\":\"⠃\",\"color\":\"gray\"},{\"text\":\"⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\\n⠀⠀⠀⠈⠉\",\"color\":\"black\"},{\"text\":\"⣿⣿⣿⣿⣿\",\"color\":\"dark_gray\"},{\"text\":\"⣿⣿⣿⣿⣿⣿⣿⣿\",\"color\":\"gray\"},{\"text\":\"⣿⣿⣿⣿\",\"color\":\"dark_gray\"},{\"text\":\"⣿⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\\n⠀⠀⠀⠀⢸\",\"color\":\"black\"},{\"text\":\"⣿⣿⣿\",\"color\":\"dark_gray\"},{\"text\":\"⣿⣿⡟⠛⠁\",\"color\":\"gray\"},{\"text\":\"⠀⠀\",\"color\":\"black\"},{\"text\":\"⠈⠛⢻⣿⣿⣿\",\"color\":\"gray\"},{\"text\":\"⣿⣿⣷\",\"color\":\"dark_gray\"},{\"text\":\"⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\\n⠀⠀⠀⢸\",\"color\":\"black\"},{\"text\":\"⣿\",\"color\":\"dark_gray\"},{\"text\":\"⣿⡿⠿⠃\",\"color\":\"gray\"},{\"text\":\"⠀⠀⠀\",\"color\":\"black\"},{\"text\":\"⢀⣀⣀⡀\",\"color\":\"gray\"},{\"text\":\"⠀⠀⠀\",\"color\":\"black\"},{\"text\":\"⠘⠿⢿⣿\",\"color\":\"gray\"},{\"text\":\"⣿\",\"color\":\"dark_gray\"},{\"text\":\"⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\\n⠀⠀⠀⢸\",\"color\":\"black\"},{\"text\":\"⡿\",\"color\":\"dark_gray\"},{\"text\":\"⠋\",\"color\":\"gray\"},{\"text\":\"⠀⠀⠀\",\"color\":\"black\"},{\"text\":\"⢀⣰⣾⣿⣿⣿⣿⣷⣆\",\"color\":\"gray\"},{\"text\":\"⠀⠀⠀⠀\",\"color\":\"black\"},{\"text\":\"⠙\",\"color\":\"gray\"},{\"text\":\"⢿\",\"color\":\"dark_gray\"},{\"text\":\"⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\\n⠀⠀⠀⢸⡇⠀⠀⠀⠀\",\"color\":\"black\"},{\"text\":\"⢸⣿⣿⣿⣿⣿⣿⣿⣿⡆\",\"color\":\"gray\"},{\"text\":\"⠀⠀⠀⠀\",\"color\":\"black\"},{\"text\":\"⢸\",\"color\":\"dark_gray\"},{\"text\":\"⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\\n⠀⠀⠀\",\"color\":\"black\"},{\"text\":\"⠈\",\"color\":\"gray\"},{\"text\":\"⢹⣇\",\"color\":\"dark_gray\"},{\"text\":\"⠀⠀⠀\",\"color\":\"black\"},{\"text\":\"⢸⣿⣿⣿\",\"color\":\"gray\"},{\"text\":\"⣿⣿\",\"color\":\"dark_gray\"},{\"text\":\"⣿⣿⣿⡇\",\"color\":\"gray\"},{\"text\":\"⠀⠀⠀\",\"color\":\"black\"},{\"text\":\"⣸\",\"color\":\"gray\"},{\"text\":\"⣿\",\"color\":\"dark_gray\"},{\"text\":\"⠁\",\"color\":\"gray\"},{\"text\":\"⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\\n⠀⠀⠀⠀⠀\",\"color\":\"black\"},{\"text\":\"⢿\",\"color\":\"dark_gray\"},{\"text\":\"⣧⣤⡀\",\"color\":\"gray\"},{\"text\":\"⠀\",\"color\":\"black\"},{\"text\":\"⠹⢿⣿⣿⣿⣿⣿⠟⠁⢀⣤⣼\",\"color\":\"gray\"},{\"text\":\"⡿\",\"color\":\"dark_gray\"},{\"text\":\"⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\\n⠀⠀⠀⠀⠀⠀⠉\",\"color\":\"black\"},{\"text\":\"⠿\",\"color\":\"dark_gray\"},{\"text\":\"⣷⣆⣀⡈⠉⠉⠉⠉⢁⣀⣰⣾\",\"color\":\"gray\"},{\"text\":\"⡿\",\"color\":\"dark_gray\"},{\"text\":\"⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\\n⠀⠀⠀⠀⠀⠀⠀⠀\",\"color\":\"black\"},{\"text\":\"⠙⠿⢿⣷⣶⣶⣶⣶⣾⡿⠿⠋\",\"color\":\"dark_gray\"},{\"text\":\"⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\\n\",\"color\":\"black\"}]";
	        pages.add(StringTag.valueOf(pageContent));
	        nbt.put("pages", pages);
	        return book;
		}
	}
	
	static class ItemstackForItemstack implements VillagerTrades.ItemListing {
	      protected final ItemStack sourceItemStack;
	      protected final ItemStack targetItemStack;         
	      protected final int maxUses;
	      protected final int villagerXp;
	      protected final float priceMultiplier;

	      public ItemstackForItemstack(ItemStack sourceItemStack, ItemStack targetItemStack, int maxUses, int villagerXp, float priceMultiplier) {
	         this.sourceItemStack = sourceItemStack;
	         this.targetItemStack = targetItemStack;
	         this.maxUses = maxUses;
	         this.villagerXp = villagerXp;
	         this.priceMultiplier = priceMultiplier;
	      }
	      
	      @Override
		  public MerchantOffer getOffer(Entity p_219699_, RandomSource p_219700_) {
	         return new MerchantOffer(
	        		 sourceItemStack.copy(), targetItemStack.copy(),
	        		 this.maxUses, this.villagerXp, this.priceMultiplier);
	      }
	}
	
	
	static class ItemsForItems implements VillagerTrades.ItemListing {
	      protected final ItemStack sourceItemStack;
	      protected final ItemStack targetItemStack;     
	      protected final int numberOfTargetItems;
	      protected final int numberOfSourceItems;      
	      protected final int maxUses;
	      protected final int villagerXp;
	      protected final float priceMultiplier;

	      public ItemsForItems(ItemStack sourceItemStack, int numberOfSourceItems, ItemStack targetItemStack, int numberOfTargetItems, int maxUses, int villagerXp, float priceMultiplier) {
	         this.sourceItemStack = sourceItemStack;
	         this.targetItemStack = targetItemStack;
	         this.numberOfTargetItems = numberOfTargetItems;
	         this.numberOfSourceItems = numberOfSourceItems;
	         this.maxUses = maxUses;
	         this.villagerXp = villagerXp;
	         this.priceMultiplier = priceMultiplier;
	      }

	      public ItemsForItems(Item sourceItem, int numberOfSourceItems, Item targetItem, int numberOfTargetItems, int maxUses, int villagerXp) {
	    	  this(new ItemStack(sourceItem), numberOfSourceItems, new ItemStack(targetItem), numberOfTargetItems, maxUses, villagerXp, 0.05F);
	      }
	      
	      public ItemsForItems(Item sourceItem, int numberOfSourceItems, Item targetItem, int numberOfTargetItems, int maxUses) {
	    	  this(new ItemStack(sourceItem), numberOfSourceItems, new ItemStack(targetItem), numberOfTargetItems, maxUses, 0, 0.05F);
	      }
	      
	      public ItemsForItems(Item sourceItem, int numberOfSourceItems, Item targetItem, int numberOfTargetItems) {
	    	  this(new ItemStack(sourceItem), numberOfSourceItems, new ItemStack(targetItem), numberOfTargetItems, 12, 0, 0.05F);
	      }
	      
	      @Override
		  public MerchantOffer getOffer(Entity p_219699_, RandomSource p_219700_) {
	         return new MerchantOffer(
	        		 new ItemStack(sourceItemStack.getItem(), numberOfSourceItems),
	        		 new ItemStack(targetItemStack.getItem(), numberOfTargetItems),
	        		 this.maxUses, this.villagerXp, this.priceMultiplier);
	      }
	  }
	

	static class PotionForEmeralds extends VillagerTrades.ItemsForEmeralds {
		public PotionForEmeralds(Potion potion, PotionType potionType, int p_35766_, int p_35768_, int p_35769_) {
			super(createPotionStack(potion, potionType), p_35766_, 1, p_35768_, p_35769_);
		}
		
		@Override
		public MerchantOffer getOffer(Entity p_219699_, RandomSource p_219700_) {
			return new MerchantOffer(new ItemStack(Items.EMERALD, this.emeraldCost), this.itemStack.copy(), this.maxUses, this.villagerXp, this.priceMultiplier);
		}
		
		private enum PotionType {
			REGULAR,
			SPLASH,
			LINGERING;
		}
		
		private static ItemStack createPotionStack(Potion potion, PotionType type) throws IllegalArgumentException {
			switch (type) {
				case REGULAR -> {
					return PotionUtils.setPotion(new ItemStack(Items.POTION, 1), potion);
				}
				case SPLASH -> {
					return PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION, 1), potion);
				}
				case LINGERING -> {
					return PotionUtils.setPotion(new ItemStack(Items.LINGERING_POTION, 1), potion);
				}
				default -> throw new IllegalArgumentException("Invalid potion type");
			}
		}
	}

	
	static class EnchantBookForBaby implements VillagerTrades.ItemListing {
		private Species species;
		
		public EnchantBookForBaby(Species species) {		
			this.species = species;   		
		}

		public MerchantOffer getOffer(Entity p_219688_, RandomSource random) {
			Enchantment enchantment;
			Item babyItem;
			int level;
			
			if (species == Species.ENDER) {
				enchantment = Enchantments.MENDING;
				level = Enchantments.MENDING.getMaxLevel();
				babyItem = random.nextBoolean() ? MinepreggoModItems.BABY_ENDER.get() : MinepreggoModItems.BABY_HUMANOID_ENDER.get();
			}
			else {			
				Enchantment.Rarity rarity;
				
				if (species == Species.CREEPER) {
					rarity = Enchantment.Rarity.VERY_RARE;
					babyItem = random.nextBoolean() ? MinepreggoModItems.BABY_CREEPER.get() : MinepreggoModItems.BABY_HUMANOID_CREEPER.get();
				}
				else if (species == Species.HUMAN) {
					rarity = Enchantment.Rarity.UNCOMMON;
					babyItem = MinepreggoModItems.BABY_HUMAN.get();
				}
				else {
					rarity = Enchantment.Rarity.COMMON;
					babyItem = MinepreggoModItems.BABY_ZOMBIE.get();
				}
				
				List<Enchantment> list = ForgeRegistries.ENCHANTMENTS.getValues().stream()
						.filter(e -> e.isTradeable() && !e.isCurse() && e.getRarity() == rarity)
						.toList();
				
				enchantment = list.get(random.nextInt(list.size()));
				level = enchantment.getMaxLevel();
			}
				 
			ItemStack itemstack = EnchantedBookItem.createForEnchantment(new EnchantmentInstance(enchantment, level));	       
			return new MerchantOffer(new ItemStack(babyItem), new ItemStack(Items.BOOK), itemstack, 12, 0, 0.2F);
		}
	}
}
