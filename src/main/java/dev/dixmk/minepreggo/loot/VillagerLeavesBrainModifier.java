package dev.dixmk.minepreggo.loot;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;

import org.jetbrains.annotations.NotNull;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import dev.dixmk.minepreggo.init.MinepreggoModItems;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;

public class VillagerLeavesBrainModifier extends LootModifier {

    public static final Codec<VillagerLeavesBrainModifier> CODEC = RecordCodecBuilder.create(
            instance -> codecStart(instance).apply(instance, VillagerLeavesBrainModifier::new)
        );

	public VillagerLeavesBrainModifier(LootItemCondition[] conditionsIn) {
		super(conditionsIn);
	}

	
	@Override
	protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {            
		if (context.getParamOrNull(LootContextParams.THIS_ENTITY) instanceof AbstractVillager) {
			generatedLoot.add(new ItemStack(MinepreggoModItems.VILLAGER_BRAIN.get()));
		}        
		return generatedLoot;
	}

	@Override
	public Codec<? extends IGlobalLootModifier> codec() {
		return CODEC;
	}
}
