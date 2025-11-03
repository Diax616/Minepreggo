package dev.dixmk.minepreggo.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;

import dev.dixmk.minepreggo.MinepreggoMod;

public class MinepreggoModSounds {
	
	private MinepreggoModSounds() {}
	
	
	public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, MinepreggoMod.MODID);
	public static final RegistryObject<SoundEvent> BELLY_TOUCH = REGISTRY.register("belly_touch", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "belly_touch")));
	public static final RegistryObject<SoundEvent> PREGGO_DEATH = REGISTRY.register("preggo_death", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "preggo_death")));
	public static final RegistryObject<SoundEvent> PREGNANCY_PAIN1 = REGISTRY.register("pregnancy_pain1", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "pregnancy_pain1")));
	public static final RegistryObject<SoundEvent> PREGNANCY_PAIN2 = REGISTRY.register("pregnancy_pain2", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "pregnancy_pain2")));
	public static final RegistryObject<SoundEvent> KICKING1 = REGISTRY.register("kicking1", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "kicking1")));
	public static final RegistryObject<SoundEvent> KICKING2 = REGISTRY.register("kicking2", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "kicking2")));
	public static final RegistryObject<SoundEvent> BIRTH = REGISTRY.register("birth", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "birth")));
	public static final RegistryObject<SoundEvent> CONTRACTION1 = REGISTRY.register("contraction1", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "contraction1")));
	public static final RegistryObject<SoundEvent> MISCARRIAGE = REGISTRY.register("miscarriage", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "miscarriage")));
}
