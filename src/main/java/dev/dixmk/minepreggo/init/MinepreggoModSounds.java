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

	public static final RegistryObject<SoundEvent> BELLY_TOUCH = REGISTRY.register("belly_touch", () -> SoundEvent.createVariableRangeEvent(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "belly_touch")));
	public static final RegistryObject<SoundEvent> PREGNANT_PREGGO_MOB_DEATH = REGISTRY.register("pregnant_preggo_mob_death", () -> SoundEvent.createVariableRangeEvent(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "pregnant_preggo_mob_death")));
	
	public static final RegistryObject<SoundEvent> PREGNANCY_PAIN1 = REGISTRY.register("pregnancy_pain1", () -> SoundEvent.createVariableRangeEvent(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "pregnancy_pain1")));
	public static final RegistryObject<SoundEvent> PREGNANCY_PAIN2 = REGISTRY.register("pregnancy_pain2", () -> SoundEvent.createVariableRangeEvent(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "pregnancy_pain2")));
	public static final RegistryObject<SoundEvent> PREGNANCY_PAIN3 = REGISTRY.register("pregnancy_pain3", () -> SoundEvent.createVariableRangeEvent(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "pregnancy_pain3")));
	public static final RegistryObject<SoundEvent> PREGNANCY_PAIN4 = REGISTRY.register("pregnancy_pain4", () -> SoundEvent.createVariableRangeEvent(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "pregnancy_pain4")));
		
	public static final RegistryObject<SoundEvent> PLAYER_BIRTH = REGISTRY.register("player_birth", () -> SoundEvent.createVariableRangeEvent(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "player_birth")));
	public static final RegistryObject<SoundEvent> PLAYER_CONTRACTION = REGISTRY.register("player_contraction", () -> SoundEvent.createVariableRangeEvent(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "player_contraction")));
	public static final RegistryObject<SoundEvent> PLAYER_MISCARRIAGE = REGISTRY.register("player_miscarriage", () -> SoundEvent.createVariableRangeEvent(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "player_miscarriage")));
	
	public static final RegistryObject<SoundEvent> STOMACH_GROWLS_1 = REGISTRY.register("stomach_growls_1", () -> SoundEvent.createVariableRangeEvent(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "stomach_growls_1")));
	public static final RegistryObject<SoundEvent> STOMACH_GROWLS_2 = REGISTRY.register("stomach_growls_2", () -> SoundEvent.createVariableRangeEvent(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "stomach_growls_2")));
	public static final RegistryObject<SoundEvent> STOMACH_GROWLS_3 = REGISTRY.register("stomach_growls_3", () -> SoundEvent.createVariableRangeEvent(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "stomach_growls_3")));
	public static final RegistryObject<SoundEvent> STOMACH_GROWLS_4 = REGISTRY.register("stomach_growls_4", () -> SoundEvent.createVariableRangeEvent(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "stomach_growls_4")));
	public static final RegistryObject<SoundEvent> STOMACH_GROWLS_5 = REGISTRY.register("stomach_growls_5", () -> SoundEvent.createVariableRangeEvent(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "stomach_growls_5")));
	public static final RegistryObject<SoundEvent> STOMACH_GROWLS_6 = REGISTRY.register("stomach_growls_6", () -> SoundEvent.createVariableRangeEvent(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "stomach_growls_6")));

	public static final RegistryObject<SoundEvent> PLAYER_PUSH_1 = REGISTRY.register("player_push_1", () -> SoundEvent.createVariableRangeEvent(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "player_push_1")));
	public static final RegistryObject<SoundEvent> PLAYER_PUSH_2 = REGISTRY.register("player_push_2", () -> SoundEvent.createVariableRangeEvent(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "player_push_2")));
	public static final RegistryObject<SoundEvent> PLAYER_PUSH_3 = REGISTRY.register("player_push_3", () -> SoundEvent.createVariableRangeEvent(MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "player_push_3")));

	private static SoundEvent[] pregnancyPains = null;
	
	private static SoundEvent[] stomachGrols = null;
	
	private static SoundEvent[] playerPuches = null;

	public static final SoundEvent getRandomPregnancyPain(RandomSource random) {	
		if (pregnancyPains == null) {
			pregnancyPains = new SoundEvent[] {
					PREGNANCY_PAIN1.get(),
					PREGNANCY_PAIN2.get(),
					PREGNANCY_PAIN3.get(),
					PREGNANCY_PAIN4.get()
				};
		}
		return pregnancyPains[random.nextInt(pregnancyPains.length)];
	}
	
	public static final SoundEvent getRandomStomachGrowls(RandomSource random) {
		if (stomachGrols == null) {
			stomachGrols = new SoundEvent[] {
					STOMACH_GROWLS_1.get(),
					STOMACH_GROWLS_2.get(),
					STOMACH_GROWLS_3.get(),
					STOMACH_GROWLS_4.get(),
					STOMACH_GROWLS_5.get(),
					STOMACH_GROWLS_6.get()
				};
		}
		return stomachGrols[random.nextInt(stomachGrols.length)];
	}
	
	public static final SoundEvent getRandomPlayerPush(RandomSource random) {
		if (playerPuches == null) {
			playerPuches = new SoundEvent[] {
					PLAYER_PUSH_1.get(),
					PLAYER_PUSH_2.get(),
					PLAYER_PUSH_3.get()
				};
		}
		return playerPuches[random.nextInt(playerPuches.length)];
	}
}