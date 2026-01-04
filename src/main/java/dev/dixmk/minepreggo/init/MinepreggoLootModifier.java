package dev.dixmk.minepreggo.init;

import com.mojang.serialization.Codec;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.loot.IllagerLeavesBrainModifier;
import dev.dixmk.minepreggo.loot.OakLeavesLemonsModifier;
import dev.dixmk.minepreggo.loot.VillagerLeavesBrainModifier;
import dev.dixmk.minepreggo.loot.WitchLeavesBrainModifier;
import net.minecraftforge.common.loot.IGlobalLootModifier;

import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MinepreggoLootModifier {

	private MinepreggoLootModifier() {}
	
    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> REGISTRY = 
            DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, MinepreggoMod.MODID);

    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> OAK_LEMON = 
            REGISTRY.register("oak_lemons", () -> OakLeavesLemonsModifier.CODEC);

    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> VILLAGER_BRAIN = 
            REGISTRY.register("villager_brain_loot", () -> VillagerLeavesBrainModifier.CODEC);

    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> ILLAGER_BRAIN = 
            REGISTRY.register("illager_brain_loot", () -> IllagerLeavesBrainModifier.CODEC);

    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> WITCH_BRAIN = 
            REGISTRY.register("witch_brain_loot", () -> WitchLeavesBrainModifier.CODEC);

}