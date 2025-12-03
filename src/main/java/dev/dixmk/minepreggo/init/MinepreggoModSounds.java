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
	public static final RegistryObject<SoundEvent> PREGNANT_PREGGO_MOB_DEATH = REGISTRY.register("pregnant_preggo_mob_death", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "pregnant_preggo_mob_death")));
	
	public static final RegistryObject<SoundEvent> PREGNANCY_PAIN1 = REGISTRY.register("pregnancy_pain1", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "pregnancy_pain1")));
	public static final RegistryObject<SoundEvent> PREGNANCY_PAIN2 = REGISTRY.register("pregnancy_pain2", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "pregnancy_pain2")));
	public static final RegistryObject<SoundEvent> PREGNANCY_PAIN3 = REGISTRY.register("pregnancy_pain3", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "pregnancy_pain3")));
	public static final RegistryObject<SoundEvent> PREGNANCY_PAIN4 = REGISTRY.register("pregnancy_pain4", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "pregnancy_pain4")));
		
	public static final RegistryObject<SoundEvent> PLAYER_BIRTH = REGISTRY.register("player_birth", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "player_birth")));
	public static final RegistryObject<SoundEvent> PLAYER_CONTRACTION = REGISTRY.register("player_contraction", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "player_contraction")));
	public static final RegistryObject<SoundEvent> PLAYER_MISCARRIAGE = REGISTRY.register("player_miscarriage", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "player_miscarriage")));
}
