package dev.dixmk.minepreggo.world.entity.npc;

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
import dev.dixmk.minepreggo.world.item.alchemy.PotionItemFactory;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.Util;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.npc.VillagerTrades.ItemListing;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.item.enchantment.Enchantment.Rarity;
import net.minecraft.world.item.trading.MerchantOffer;
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
					new VillagerTrades.ItemsForEmeralds(MinepreggoModItems.CUCUMBER.get(), 8, 25, 10)},
					3, new VillagerTrades.ItemListing[] { 
					new VillagerTrades.ItemsForEmeralds(Items.COCOA_BEANS, 15, 10, 10),
					new VillagerTrades.EmeraldForItems(MinepreggoModItems.CUCUMBER.get(), 10, 10, 10)},
					4, new VillagerTrades.ItemListing[] { 
					new VillagerTrades.ItemsForEmeralds(Items.COCOA_BEANS, 17, 20, 15)})));	
				
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
						new VillagerTrades.ItemsForEmeralds(MinepreggoModItems.CHOCOLATE_BAR.get(), 10, 5, 10)})));
			
			p_35633_.put(MinepreggoModVillagerProfessions.VILLAGER_DOCTOR.get(), new Int2ObjectOpenHashMap<>(ImmutableMap.of(
					1, new VillagerTrades.ItemListing[]
					{ new PotionsForEmeralds(PotionItemFactory.createPotion(Potions.HEALING), 15, 1, 20, 15)},
					2, new VillagerTrades.ItemListing[]
					{ new PotionsForEmeralds(PotionItemFactory.createPotion(Potions.STRONG_HEALING), 17, 1, 20, 17),
						new PotionsForEmeralds(PotionItemFactory.createSplashPotion(Potions.HEALING), 16, 1, 20, 17)},
					3, new VillagerTrades.ItemListing[]
					{ new PotionsForEmeralds(PotionItemFactory.createSplashPotion(MinepreggoModPotions.IMPREGNATION_POTION_0.get()), 30, 1, 20, 20)},
					4, new VillagerTrades.ItemListing[]
					{ new PotionsForEmeralds(PotionItemFactory.createSplashPotion(MinepreggoModPotions.PREGNANCY_HEALING.get()), 35, 1, 20, 20)},
					5, new VillagerTrades.ItemListing[]
					{ new PotionsForEmeralds(PotionItemFactory.createSplashPotion(MinepreggoModPotions.PREGNANCY_RESISTANCE.get()), 40, 1, 20, 25)}))); 
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
						new ItemsForItems(MinepreggoModItems.BABY_HUMAN.get(), 1, Items.GOLD_INGOT, 36, 20),
						new ItemsForItems(MinepreggoModItems.BABY_HUMAN.get(), 1, Items.IRON_INGOT, 48, 20),
						new ItemsForItems(MinepreggoModItems.BABY_HUMANOID_CREEPER.get(), 1, Items.DIAMOND, 20, 10),
						new ItemsForItems(MinepreggoModItems.BABY_HUMAN.get(), 1, Items.EMERALD, 64, 20),
						new ItemsForItems(MinepreggoModItems.BABY_ZOMBIE.get(), 1, Items.GOLD_INGOT, 48, 15),
						new ItemsForItems(MinepreggoModItems.BABY_ZOMBIE.get(), 1, Items.IRON_BLOCK, 9, 15),
						new ItemsForItems(MinepreggoModItems.BABY_QUADRUPED_CREEPER.get(), 1, Items.DIAMOND, 8, 10),
						new EnchantBookForBaby(Species.HUMAN),
						new EnchantBookForBaby(Species.CREEPER),
						new EnchantBookForBaby(Species.ZOMBIE),
						new EnchantBookForBaby(Species.ENDER),
						new PotionsForEmeralds(PotionItemFactory.createPotion(MinepreggoModPotions.PREGNANCY_ACCELERATION.get()), 25, 1, 10, 0),
						new PotionsForEmeralds(PotionItemFactory.createPotion(MinepreggoModPotions.IMPREGNATION_POTION_0.get()), 20, 1, 10, 0),
						new PotionsForEmeralds(PotionItemFactory.createPotion(MinepreggoModPotions.IMPREGNATION_POTION_1.get()), 30, 1, 10, 0),
						new PotionsForEmeralds(PotionItemFactory.createPotion(MinepreggoModPotions.ZOMBIE_IMPREGNATION_0.get()), 15, 1, 10, 0),
						new PotionsForEmeralds(PotionItemFactory.createPotion(MinepreggoModPotions.PREGNANCY_RESISTANCE.get()), 27, 1, 10, 0),
						new PotionsForEmeralds(PotionItemFactory.createPotion(MinepreggoModPotions.PREGNANCY_HEALING.get()), 27, 1, 10, 0),
						new VillagerTrades.ItemsForEmeralds(MinepreggoModItems.VILLAGER_BRAIN.get(), 12, 24, 10, 0),
						new VillagerTrades.EmeraldForItems(MinepreggoModItems.DEAD_HUMAN_FETUS.get(), 2, 30, 10),
						new VillagerTrades.EmeraldForItems(MinepreggoModItems.DEAD_ZOMBIE_FETUS.get(), 3, 30, 10),
						new VillagerTrades.EmeraldForItems(MinepreggoModItems.DEAD_HUMANOID_CREEPER_FETUS.get(), 1, 30, 10),
						new VillagerTrades.EmeraldForItems(MinepreggoModItems.DEAD_QUADRUPED_CREEPER_FETUS.get(), 2, 30, 10)},	
				new VillagerTrades.ItemListing[]{
						new ItemsForItems(MinepreggoModItems.BABY_HUMAN.get(), 1, Items.GOLD_INGOT, 36, 20),
						new ItemsForItems(MinepreggoModItems.HUMAN_BREAST_MILK_BOTTLE.get(), 12, Items.GOLD_INGOT, 20, 10),
						new ItemsForItems(MinepreggoModItems.CREEPER_BREAST_MILK_BOTTLE.get(), 12, Items.DIAMOND, 14, 10),
						new ItemsForItems(MinepreggoModItems.ZOMBIE_BREAST_MILK_BOTTLE.get(), 12, Items.GOLD_INGOT, 6, 10),
						new ItemsForItems(MinepreggoModItems.BABY_HUMAN.get(), 1, Items.EMERALD, 32, 20),
						new ItemsForItems(MinepreggoModItems.BABY_ZOMBIE.get(), 1, Items.EMERALD, 24, 15),
						new ItemsForItems(MinepreggoModItems.BABY_QUADRUPED_CREEPER.get(), 1, Items.DIAMOND, 8, 15),
						new ItemsForItems(MinepreggoModItems.BABY_HUMANOID_CREEPER.get(), 1, Items.DIAMOND, 20, 10),
						new PotionsForEmeralds(PotionItemFactory.createPotion(MinepreggoModPotions.IMPREGNATION_POTION_1.get()), 30, 1, 10, 0),
						new PotionsForEmeralds(PotionItemFactory.createPotion(MinepreggoModPotions.IMPREGNATION_POTION_2.get()), 33, 1, 10, 0),
						new PotionsForEmeralds(PotionItemFactory.createPotion(MinepreggoModPotions.CREEPER_IMPREGNATION_0.get()), 35, 1, 10, 0),
						new PotionsForEmeralds(PotionItemFactory.createPotion(MinepreggoModPotions.PREGNANCY_ACCELERATION.get()), 27, 1, 10, 0),
						new PotionsForEmeralds(PotionItemFactory.createPotion(MinepreggoModPotions.PREGNANCY_HEALING.get()), 27, 1, 10, 0),
						new EnchantBookForBaby(Species.HUMAN),
						new EnchantBookForBaby(Species.CREEPER),
						new EnchantBookForBaby(Species.ZOMBIE),
						new EnchantBookForBaby(Species.ENDER),
						new VillagerTrades.ItemsForEmeralds(MinepreggoModItems.VILLAGER_BRAIN.get(), 12, 24, 10, 0),
						new VillagerTrades.EmeraldForItems(MinepreggoModItems.DEAD_HUMAN_FETUS.get(), 2, 30, 10),
						new VillagerTrades.EmeraldForItems(MinepreggoModItems.DEAD_ZOMBIE_FETUS.get(), 3, 30, 10),
						new VillagerTrades.EmeraldForItems(MinepreggoModItems.DEAD_HUMANOID_CREEPER_FETUS.get(), 1, 30, 10),
						new VillagerTrades.EmeraldForItems(MinepreggoModItems.DEAD_QUADRUPED_CREEPER_FETUS.get(), 2, 30, 10)},
				new VillagerTrades.ItemListing[]{
						new ItemsForItems(MinepreggoModItems.HUMAN_BREAST_MILK_BOTTLE.get(), 12, Items.GOLD_INGOT, 20, 10),
						new ItemsForItems(MinepreggoModItems.CREEPER_BREAST_MILK_BOTTLE.get(), 12, Items.DIAMOND, 14, 10),
						new ItemsForItems(MinepreggoModItems.ZOMBIE_BREAST_MILK_BOTTLE.get(), 12, Items.GOLD_INGOT, 6, 10),
						new ItemsForItems(MinepreggoModItems.BABY_ZOMBIE.get(), 1, Items.GOLD_INGOT, 24, 15),
						new ItemsForItems(MinepreggoModItems.BABY_QUADRUPED_CREEPER.get(), 1, Items.DIAMOND, 8, 15),
						new ItemsForItems(MinepreggoModItems.BABY_ZOMBIE.get(), 1, Items.IRON_BLOCK, 4, 15),
						new ItemsForItems(MinepreggoModItems.BABY_HUMANOID_CREEPER.get(), 1, Items.GOLD_INGOT, 48, 10),
						new ItemsForItems(MinepreggoModItems.BABY_HUMANOID_CREEPER.get(), 1, Items.DIAMOND, 20, 10),		
						new PotionsForEmeralds(PotionItemFactory.createPotion(MinepreggoModPotions.ZOMBIE_IMPREGNATION_2.get()), 15, 1, 15, 0),
						new PotionsForEmeralds(PotionItemFactory.createPotion(MinepreggoModPotions.IMPREGNATION_POTION_2.get()), 36, 1, 15, 0),
						new PotionsForEmeralds(PotionItemFactory.createPotion(MinepreggoModPotions.PREGNANCY_ACCELERATION.get()), 27, 1, 20, 0),
						new PotionsForEmeralds(PotionItemFactory.createPotion(MinepreggoModPotions.PREGNANCY_RESISTANCE.get()), 27, 1, 25, 0),
						new EnchantBookForBaby(Species.HUMAN),
						new EnchantBookForBaby(Species.CREEPER),
						new EnchantBookForBaby(Species.ZOMBIE),
						new EnchantBookForBaby(Species.ENDER),
						new VillagerTrades.ItemsForEmeralds(MinepreggoModItems.VILLAGER_BRAIN.get(), 12, 24, 10, 0),
						new VillagerTrades.EmeraldForItems(MinepreggoModItems.DEAD_HUMAN_FETUS.get(), 2, 30, 10),
						new VillagerTrades.EmeraldForItems(MinepreggoModItems.DEAD_ZOMBIE_FETUS.get(), 3, 30, 10),
						new VillagerTrades.EmeraldForItems(MinepreggoModItems.DEAD_HUMANOID_CREEPER_FETUS.get(), 1, 30, 10),
						new VillagerTrades.EmeraldForItems(MinepreggoModItems.DEAD_QUADRUPED_CREEPER_FETUS.get(), 2, 30, 10)});
		
		@NonNull
		public static VillagerTrades.ItemListing[] getRandomTrades(RandomSource random) {		
			return TRADES.get(random.nextInt(0, TRADES.size()));	
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
	       
		  public MerchantOffer getOffer(Entity p_219699_, RandomSource p_219700_) {
	         return new MerchantOffer(
	        		 new ItemStack(sourceItemStack.getItem(), numberOfSourceItems),
	        		 new ItemStack(targetItemStack.getItem(), numberOfTargetItems),
	        		 this.maxUses, this.villagerXp, this.priceMultiplier);
	      }
	  }
	

	static class PotionsForEmeralds extends VillagerTrades.ItemsForEmeralds {
		public PotionsForEmeralds(ItemStack p_35765_, int p_35766_, int p_35767_, int p_35768_, int p_35769_) {
			super(p_35765_, p_35766_, p_35767_, p_35768_, p_35769_);
		}
		
		@Override
		public MerchantOffer getOffer(Entity p_219699_, RandomSource p_219700_) {
			ItemStack sellingStack = this.itemStack.copy();
			sellingStack.setCount(1);
			return new MerchantOffer(new ItemStack(Items.EMERALD, this.emeraldCost), sellingStack, this.maxUses, this.villagerXp, this.priceMultiplier);
		}
	}

	
	static class EnchantBookForBaby implements VillagerTrades.ItemListing {
		private Item babyItem;
		private Enchantment.Rarity rarity;
		private float enchantmentMaxLevelProbability;
		
		public EnchantBookForBaby(Species species) {		    	
			switch (species) {	
			case CREEPER: {
				babyItem = MinepreggoModItems.BABY_QUADRUPED_CREEPER.get();
				rarity = Rarity.VERY_RARE;
				enchantmentMaxLevelProbability = 0.8F;
				break;
			}
			case ENDER: {
				babyItem = MinepreggoModItems.BABY_HUMANOID_CREEPER.get();
				rarity = Rarity.VERY_RARE;
				enchantmentMaxLevelProbability = 0.95F;
				break;
			}
			case ZOMBIE: {
				babyItem = MinepreggoModItems.BABY_ZOMBIE.get();
				rarity = Rarity.RARE;
				enchantmentMaxLevelProbability = 0.6F;
				break;
			}
			default:
				babyItem = MinepreggoModItems.BABY_HUMAN.get();
				rarity = Rarity.RARE;
				enchantmentMaxLevelProbability = 0.7F;
			}    	
		}


		public MerchantOffer getOffer(Entity p_219688_, RandomSource p_219689_) {
			List<Enchantment> list = ForgeRegistries.ENCHANTMENTS.getValues().stream()
					.filter(e -> e.isTradeable() && !e.isCurse() && e.getRarity() == rarity)
					.toList();
			
			Enchantment enchantment = list.get(p_219689_.nextInt(list.size()));
			int i;	
			
			if (p_219689_.nextFloat() < enchantmentMaxLevelProbability) {
				i = enchantment.getMaxLevel();
			}
			else {
				i = p_219689_.nextInt(enchantment.getMinLevel(), enchantment.getMaxLevel() + 1);
			}
	 
			ItemStack itemstack = EnchantedBookItem.createForEnchantment(new EnchantmentInstance(enchantment, i));	       
			return new MerchantOffer(new ItemStack(babyItem), new ItemStack(Items.BOOK), itemstack, 12, 0, 0.2F);
		}
	}
}
