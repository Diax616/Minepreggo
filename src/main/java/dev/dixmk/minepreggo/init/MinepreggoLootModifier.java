package dev.dixmk.minepreggo.init;

import com.mojang.serialization.Codec;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.loot.OakLeavesLemonsModifier;
import net.minecraftforge.common.loot.IGlobalLootModifier;

import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MinepreggoLootModifier {

	private MinepreggoLootModifier() {}
	
    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> GLM = 
            DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, MinepreggoMod.MODID);

    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> OAK_SUGAR = 
            GLM.register("oak_lemons", () -> OakLeavesLemonsModifier.CODEC);
}