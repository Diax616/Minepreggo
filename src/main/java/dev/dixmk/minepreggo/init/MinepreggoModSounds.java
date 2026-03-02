package dev.dixmk.minepreggo.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.utils.MinepreggoHelper;

public class MinepreggoModSounds {
	
	private MinepreggoModSounds() {}

	public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, MinepreggoMod.MODID);

	public static final RegistryObject<SoundEvent> BELLY_TOUCH = REGISTRY.register("belly_touch", () -> SoundEvent.createVariableRangeEvent(MinepreggoHelper.fromThisNamespaceAndPath("belly_touch")));
	public static final RegistryObject<SoundEvent> PREGNANT_PREGGO_MOB_DEATH = REGISTRY.register("pregnant_preggo_mob_death", () -> SoundEvent.createVariableRangeEvent(MinepreggoHelper.fromThisNamespaceAndPath("pregnant_preggo_mob_death")));
	
	public static final RegistryObject<SoundEvent> PREGNANCY_PAIN1 = REGISTRY.register("pregnancy_pain1", () -> SoundEvent.createVariableRangeEvent(MinepreggoHelper.fromThisNamespaceAndPath("pregnancy_pain1")));
	public static final RegistryObject<SoundEvent> PREGNANCY_PAIN2 = REGISTRY.register("pregnancy_pain2", () -> SoundEvent.createVariableRangeEvent(MinepreggoHelper.fromThisNamespaceAndPath("pregnancy_pain2")));
	public static final RegistryObject<SoundEvent> PREGNANCY_PAIN3 = REGISTRY.register("pregnancy_pain3", () -> SoundEvent.createVariableRangeEvent(MinepreggoHelper.fromThisNamespaceAndPath("pregnancy_pain3")));
	public static final RegistryObject<SoundEvent> PREGNANCY_PAIN4 = REGISTRY.register("pregnancy_pain4", () -> SoundEvent.createVariableRangeEvent(MinepreggoHelper.fromThisNamespaceAndPath("pregnancy_pain4")));
		
	public static final RegistryObject<SoundEvent> PLAYER_BIRTH = REGISTRY.register("player_birth", () -> SoundEvent.createVariableRangeEvent(MinepreggoHelper.fromThisNamespaceAndPath("player_birth")));
	public static final RegistryObject<SoundEvent> PLAYER_CONTRACTION = REGISTRY.register("player_contraction", () -> SoundEvent.createVariableRangeEvent(MinepreggoHelper.fromThisNamespaceAndPath("player_contraction")));
	public static final RegistryObject<SoundEvent> PLAYER_MISCARRIAGE = REGISTRY.register("player_miscarriage", () -> SoundEvent.createVariableRangeEvent(MinepreggoHelper.fromThisNamespaceAndPath("player_miscarriage")));
	
	public static final RegistryObject<SoundEvent> STOMACH_GROWLS_1 = REGISTRY.register("stomach_growls_1", () -> SoundEvent.createVariableRangeEvent(MinepreggoHelper.fromThisNamespaceAndPath("stomach_growls_1")));
	public static final RegistryObject<SoundEvent> STOMACH_GROWLS_2 = REGISTRY.register("stomach_growls_2", () -> SoundEvent.createVariableRangeEvent(MinepreggoHelper.fromThisNamespaceAndPath("stomach_growls_2")));
	public static final RegistryObject<SoundEvent> STOMACH_GROWLS_3 = REGISTRY.register("stomach_growls_3", () -> SoundEvent.createVariableRangeEvent(MinepreggoHelper.fromThisNamespaceAndPath("stomach_growls_3")));
	public static final RegistryObject<SoundEvent> STOMACH_GROWLS_4 = REGISTRY.register("stomach_growls_4", () -> SoundEvent.createVariableRangeEvent(MinepreggoHelper.fromThisNamespaceAndPath("stomach_growls_4")));
	public static final RegistryObject<SoundEvent> STOMACH_GROWLS_5 = REGISTRY.register("stomach_growls_5", () -> SoundEvent.createVariableRangeEvent(MinepreggoHelper.fromThisNamespaceAndPath("stomach_growls_5")));
	public static final RegistryObject<SoundEvent> STOMACH_GROWLS_6 = REGISTRY.register("stomach_growls_6", () -> SoundEvent.createVariableRangeEvent(MinepreggoHelper.fromThisNamespaceAndPath("stomach_growls_6")));

	public static final RegistryObject<SoundEvent> PLAYER_PUSH_1 = REGISTRY.register("player_push_1", () -> SoundEvent.createVariableRangeEvent(MinepreggoHelper.fromThisNamespaceAndPath("player_push_1")));
	public static final RegistryObject<SoundEvent> PLAYER_PUSH_2 = REGISTRY.register("player_push_2", () -> SoundEvent.createVariableRangeEvent(MinepreggoHelper.fromThisNamespaceAndPath("player_push_2")));
	public static final RegistryObject<SoundEvent> PLAYER_PUSH_3 = REGISTRY.register("player_push_3", () -> SoundEvent.createVariableRangeEvent(MinepreggoHelper.fromThisNamespaceAndPath("player_push_3")));
	
	public static final SoundEvent getRandomPregnancyPain(RandomSource random) {	
		return switch (random.nextInt(4)) {
			case 0 -> PREGNANCY_PAIN1.get();
			case 1 -> PREGNANCY_PAIN2.get();
			case 2 -> PREGNANCY_PAIN3.get();
			default -> PREGNANCY_PAIN4.get();
		};
	}
	
	public static final SoundEvent getRandomStomachGrowls(RandomSource random) {
		return switch (random.nextInt(6)) {
			case 0 -> STOMACH_GROWLS_1.get();
			case 1 -> STOMACH_GROWLS_2.get();
			case 2 -> STOMACH_GROWLS_3.get();
			case 3 -> STOMACH_GROWLS_4.get();
			case 4 -> STOMACH_GROWLS_5.get();
			default -> STOMACH_GROWLS_6.get();
		};
	}
	
	public static final SoundEvent getRandomPlayerPush(RandomSource random) {
		return switch (random.nextInt(3)) {
			case 0 -> PLAYER_PUSH_1.get();
			case 1 -> PLAYER_PUSH_2.get();
			default -> PLAYER_PUSH_3.get();
		};
	}
}