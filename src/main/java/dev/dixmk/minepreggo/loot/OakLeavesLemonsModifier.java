package dev.dixmk.minepreggo.loot;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import dev.dixmk.minepreggo.init.MinepreggoModItems;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;

public class OakLeavesLemonsModifier extends LootModifier {
    
    public static final Codec<OakLeavesLemonsModifier> CODEC = RecordCodecBuilder.create(
            instance -> codecStart(instance).apply(instance, OakLeavesLemonsModifier::new)
        );

        public OakLeavesLemonsModifier(LootItemCondition[] conditionsIn) {
            super(conditionsIn);
        }

        @Override
        protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
            if (context.hasParam(LootContextParams.BLOCK_STATE)) {
            	var block = context.getParam(LootContextParams.BLOCK_STATE);
            	if (block.is(Blocks.OAK_LEAVES) || block.is(Blocks.DARK_OAK_LEAVES)) {
                    float chance = 0.075f;
                    ItemStack tool = context.getParamOrNull(LootContextParams.TOOL);
                    if (tool != null) {
                        int fortuneLevel = EnchantmentHelper.getTagEnchantmentLevel(
                                Enchantments.BLOCK_FORTUNE, tool);
                        if (fortuneLevel > 0) {
                            chance *= (fortuneLevel * 1.25f);
                        }
                    }        
                    if (context.getRandom().nextFloat() < chance) {
                        generatedLoot.add(new ItemStack(MinepreggoModItems.LEMON.get(), 2 + context.getRandom().nextInt(4)));
                    }   		
            	}	
            }
            return generatedLoot;
        }

        @Override
        public Codec<? extends IGlobalLootModifier> codec() {
            return CODEC;
        }
}