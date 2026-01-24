package dev.dixmk.minepreggo.world.entity.player;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnegative;
import javax.annotation.Nullable;

import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.checkerframework.checker.nullness.qual.NonNull;

import com.google.common.collect.ImmutableMap;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.init.MinepreggoModMobEffects;
import dev.dixmk.minepreggo.network.capability.FemalePlayerImpl;
import dev.dixmk.minepreggo.network.packet.s2c.RemovePlayerJigglePhysicsS2CPacket;
import dev.dixmk.minepreggo.network.packet.s2c.SyncJigglePhysicsS2CPacket;
import dev.dixmk.minepreggo.world.entity.preggo.Creature;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobHelper;
import dev.dixmk.minepreggo.world.entity.preggo.Species;
import dev.dixmk.minepreggo.world.item.IMaternityArmor;
import dev.dixmk.minepreggo.world.pregnancy.MapPregnancyPhase;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySymptom;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import dev.dixmk.minepreggo.world.pregnancy.Womb;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntMaps;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.PacketDistributor;

public class PlayerHelper {

	private PlayerHelper() {}

	private static final Object2IntMap<PregnancyPhase> MAX_JUMPS = Object2IntMaps.unmodifiable(new Object2IntOpenHashMap<>(ImmutableMap.of(
			PregnancyPhase.P3, 45,
			PregnancyPhase.P4, 40,
			PregnancyPhase.P5, 35,
			PregnancyPhase.P6, 30,
			PregnancyPhase.P7, 25,
			PregnancyPhase.P8, 20
			)));
	
	private static final Object2IntMap<PregnancyPhase> SPRINTING_TIMERS = Object2IntMaps.unmodifiable(new Object2IntOpenHashMap<>(ImmutableMap.of(
			PregnancyPhase.P3, 4000,
			PregnancyPhase.P4, 3800,
			PregnancyPhase.P5, 3600,
			PregnancyPhase.P6, 3400,
			PregnancyPhase.P7, 3200,
			PregnancyPhase.P8, 3000
			)));
	
	private static final Object2IntMap<PregnancyPhase> SNEAKING_TIMERS = Object2IntMaps.unmodifiable(new Object2IntOpenHashMap<>(ImmutableMap.of(
			PregnancyPhase.P4, 3000,
			PregnancyPhase.P5, 2800,
			PregnancyPhase.P6, 2600,
			PregnancyPhase.P7, 2400,
			PregnancyPhase.P8, 2200
			)));
	
	private static final ImmutableMap<PregnancyPhase, MobEffect> PREGNANCY_EFFECTS = ImmutableMap.of(
			PregnancyPhase.P0, MinepreggoModMobEffects.PREGNANCY_P0.get(),
			PregnancyPhase.P1, MinepreggoModMobEffects.PREGNANCY_P1.get(),
			PregnancyPhase.P2, MinepreggoModMobEffects.PREGNANCY_P2.get(),
			PregnancyPhase.P3, MinepreggoModMobEffects.PREGNANCY_P3.get(),
			PregnancyPhase.P4, MinepreggoModMobEffects.PREGNANCY_P4.get(),
			PregnancyPhase.P5, MinepreggoModMobEffects.PREGNANCY_P5.get(),
			PregnancyPhase.P6, MinepreggoModMobEffects.PREGNANCY_P6.get(),
			PregnancyPhase.P7, MinepreggoModMobEffects.PREGNANCY_P7.get(),
			PregnancyPhase.P8, MinepreggoModMobEffects.PREGNANCY_P8.get()
			);

	private static final String FEMALE_PLAYER_ID = "femalePlayerUUID";
	private static final String PREGNANT_FEMALE_PLAYER_ID = "pregnantFemalePlayerUUID";
	
	public static boolean containsAnyFemalePlayerIdTag(ItemStack stack) {
		if (stack.isEmpty()) {
			return false;
		}
		var tag = stack.getTag();
		return tag != null && (tag.hasUUID(FEMALE_PLAYER_ID) || tag.hasUUID(PREGNANT_FEMALE_PLAYER_ID));
	}
	
	@Nullable
	public static UUID getFemalePlayerIdTag(ItemStack stack) {
		if (stack.isEmpty()) {
			return null;
		}
		var tag = stack.getTag();
		return tag != null && tag.hasUUID(FEMALE_PLAYER_ID) ? tag.getUUID(FEMALE_PLAYER_ID) : null;
	}

	@Nullable
	public static UUID getPregnantFemalePlayerIdTag(ItemStack stack) {
		if (stack.isEmpty()) {
			return null;
		}
		var tag = stack.getTag();
		return tag != null && tag.hasUUID(PREGNANT_FEMALE_PLAYER_ID) ? tag.getUUID(PREGNANT_FEMALE_PLAYER_ID) : null;
	}	
	
	public static boolean removeAnyFemalePlayerIdTag(ItemStack stack) {
		if (stack.isEmpty()) {
			return false;
		}
		var tag = stack.getTag();
		if (tag != null) {
			boolean removed = false;
			if (tag.hasUUID(FEMALE_PLAYER_ID)) {
				tag.remove(FEMALE_PLAYER_ID);
				removed = true;
			}
			if (tag.hasUUID(PREGNANT_FEMALE_PLAYER_ID)) {
				tag.remove(PREGNANT_FEMALE_PLAYER_ID);
				removed = true;
			}
			return removed;
		}
		
		return false;
	}
	
	public static void addFemalePlayerIdTag(ItemStack stack, UUID femalePlayerId) {
		if (stack.isEmpty()) {
			return;
		}
		var tag = stack.getOrCreateTag();
		tag.putUUID(FEMALE_PLAYER_ID, femalePlayerId);
	}
	
	public static void addPregnantFemalePlayerIdTag(ItemStack stack, UUID pregnantFemalePlayerId) {
		if (stack.isEmpty()) {
			return;
		}
		var tag = stack.getOrCreateTag();
		tag.putUUID(PREGNANT_FEMALE_PLAYER_ID, pregnantFemalePlayerId);
	}

	public static int maxJumps(PregnancyPhase phase) {
		return MAX_JUMPS.getInt(phase.compareTo(PregnancyPhase.P3) <= -1 ? PregnancyPhase.P3 : phase);
	}
	
	public static int sprintingTimer(PregnancyPhase phase) {
		return SPRINTING_TIMERS.getInt(phase.compareTo(PregnancyPhase.P3) <= -1 ? PregnancyPhase.P3 : phase);
	}
	
	public static int sneakingTimer(PregnancyPhase phase) {
		return SNEAKING_TIMERS.getInt(phase.compareTo(PregnancyPhase.P4) <= -1 ? PregnancyPhase.P4 : phase);
	}
	
	public static MobEffect getPregnancyEffects(PregnancyPhase phase) {
		return PREGNANCY_EFFECTS.get(phase);
	}
	
	public static boolean tryToStartPregnancy(ServerPlayer player, boolean forceFather) {
		var playerDataCap = player.getCapability(MinepreggoCapabilities.PLAYER_DATA).resolve();		

		if (playerDataCap.isEmpty()) {
			return false;
		}

		final var femaleDataOpt = playerDataCap.get().getFemaleData().resolve();
		boolean result = femaleDataOpt.isPresent() && femaleDataOpt.get().isPregnant() && femaleDataOpt.get().getPrePregnancyData().isPresent();

		femaleDataOpt.ifPresent(femaleData -> {	
			if (!femaleData.isPregnant()) return;	
			
			femaleData.getPrePregnancyData().ifPresent(prePregnancyData -> {
				final var lastPregnancyStage = PregnancySystemHelper.calculateMaxPregnancyPhaseByTotalNumOfBabies(prePregnancyData.fertilizedEggs());
				final var totalDays = MinepreggoModConfig.getTotalPregnancyDays();
				final var daysByStage = new MapPregnancyPhase(totalDays, lastPregnancyStage);
				final var pregnancySystem = femaleData.getPregnancyData();
				
				final Womb womb;
								
				if (forceFather) {
					womb = new Womb(
							player.getUUID(),
							ImmutableTriple.of(Optional.ofNullable(prePregnancyData.fatherId()), prePregnancyData.typeOfSpeciesOfFather(), prePregnancyData.typeOfCreatureOfFather()),
							player.getRandom(),
							prePregnancyData.fertilizedEggs());				
				}
				else {
					womb = new Womb(
							ImmutableTriple.of(player.getUUID(), Species.HUMAN, Creature.HUMANOID),
							ImmutableTriple.of(Optional.ofNullable(prePregnancyData.fatherId()), prePregnancyData.typeOfSpeciesOfFather(), prePregnancyData.typeOfCreatureOfFather()),
							player.getRandom(),
							prePregnancyData.fertilizedEggs());
				}
				
				pregnancySystem.setMapPregnancyPhase(daysByStage);
				pregnancySystem.setPregnancyHealth(PregnancySystemHelper.MAX_PREGNANCY_HEALTH);
				pregnancySystem.setLastPregnancyStage(lastPregnancyStage);
				pregnancySystem.setDaysToGiveBirth(totalDays);			
				pregnancySystem.setWomb(womb);	
				pregnancySystem.setCurrentPregnancyPhase(PregnancyPhase.P0);	
							
				player.addEffect(new MobEffectInstance(MinepreggoModMobEffects.PREGNANCY_P0.get(), -1, 0, false, false, true));				
				
				PlayerHelper.updateJigglePhysics(player, playerDataCap.get().getSkinType(), PregnancyPhase.P0);
				
				femaleData.sync(player);
				pregnancySystem.syncState(player);	
							
				MinepreggoMod.LOGGER.debug("Player {} has become pregnant with {} babies, total days to give birth: {}, pregnancy phases days: {}, womb: {}", 
						player.getName().getString(), 
						prePregnancyData.fertilizedEggs(), 
						totalDays,
						daysByStage,
						womb);
			});
		});
		
		return result;
	}
	
	private static boolean tryToStartPrePregnancy(ServerPlayer player, @NonNull ImmutableTriple<Optional<UUID>, Species, Creature> father, @Nonnegative int numOfBabies) {
		var playerDataCap = player.getCapability(MinepreggoCapabilities.PLAYER_DATA).resolve();		

		if (playerDataCap.isEmpty()) {
			MinepreggoMod.LOGGER.error("Player {} has no PLAYER_DATA={} capability.", player.getName().getString(), playerDataCap.isEmpty());
			return false;
		}

		final var femaleData = playerDataCap.get().getFemaleData().resolve();
	
		if (femaleData.isPresent()
				&& !femaleData.get().isPregnant() 
				&& femaleData.get().getPostPregnancyData().isEmpty()) {	
			
			player.removeEffect(MinepreggoModMobEffects.FERTILE.get());

			return femaleData.get().tryImpregnate(numOfBabies, father);
		}
		else {
			MinepreggoMod.LOGGER.warn("Player {} is already pregnant or has no FEMALE_DATA={} capability.", player.getName().getString(), femaleData.isEmpty());
		}
		
		return false;
	}
	
	public static boolean tryStartPregnancyBySex(ServerPlayer female, ServerPlayer male) {
		var femalePlayerCap = female.getCapability(MinepreggoCapabilities.PLAYER_DATA).resolve();
		var malePlayerCap = male.getCapability(MinepreggoCapabilities.PLAYER_DATA).resolve();

		if (femalePlayerCap.isEmpty() || malePlayerCap.isEmpty()) {
			return false;
		}
		
		final var femalePlayerData = femalePlayerCap.get().getFemaleData().resolve();
		final var malePlayerData = malePlayerCap.get().getMaleData().resolve();
		
		if (femalePlayerData.isEmpty() || malePlayerData.isEmpty()) {
			return false;
		}
		
		final var numOfBabies = PregnancySystemHelper.calculateNumOfBabiesByFertility(malePlayerData.get().getFertilityRate(), femalePlayerData.get().getFertilityRate());
		
		if (numOfBabies == 0) {
			return false;
		}
			
		return tryToStartPrePregnancy(female, ImmutableTriple.of(Optional.of(male.getUUID()), Species.HUMAN, Creature.HUMANOID), numOfBabies);
	}
	
	
	public static boolean tryStartPregnancyBySex(ServerPlayer female, Villager villager) {		
		var femalePlayerCap = female.getCapability(MinepreggoCapabilities.PLAYER_DATA).resolve();

		if (femalePlayerCap.isEmpty()) {
			return false;
		}
		
		final var femalePlayerData = femalePlayerCap.get().getFemaleData().resolve();
		
		if (femalePlayerData.isEmpty()) {
			return false;
		}
		
		final var numOfBabies = PregnancySystemHelper.calculateNumOfBabiesByFertility(villager.getRandom().nextFloat(), femalePlayerData.get().getFertilityRate());
		
		if (numOfBabies == 0) {
			return false;
		}
			
		return tryToStartPrePregnancy(female, ImmutableTriple.of(Optional.of(villager.getUUID()), Species.VILLAGER, Creature.HUMANOID), numOfBabies);
	}
	
	public static boolean tryStartPregnancyByPotion(ServerPlayer player, @NonNull ImmutableTriple<Optional<UUID>, Species, Creature> father, int amplifier) {			
		final var numOfbabies = PregnancySystemHelper.calculateNumOfBabiesByPotion(amplifier);
		if (tryToStartPrePregnancy(player, father, numOfbabies)) {
			return tryToStartPregnancy(player, true);
		}
		return false;
	}
		
	public static boolean tryStartPregnancyByMobAttack(ServerPlayer female, Species species) {
		return tryToStartPrePregnancy(female, ImmutableTriple.of(Optional.empty(), species, Creature.MONSTER), female.getRandom().nextInt(1, PregnancySystemHelper.MAX_NUMBER_OF_BABIES + 1));
	}

	public static boolean isPlayerValid(LivingEntity entity) {
		if (entity instanceof Player player) {
			Optional<Boolean> result = player.getCapability(MinepreggoCapabilities.PLAYER_DATA).map(cap -> cap.getFemaleData().isPresent());
			return result.isPresent() && result.get().booleanValue();
		}
		return false;
	}
	
	public static boolean canUseChestPlateInLactation(LivingEntity target, Item armor) {	
		if (!target.hasEffect(MinepreggoModMobEffects.LACTATION.get())) {
			return true;
		}
		return armor instanceof IMaternityArmor maternityArmor && maternityArmor.areBoobsExposed();
	}
	
	public static void removeAndDropItemStackFromEquipmentSlot(Player player, EquipmentSlot slotId) {
		if (PreggoMobHelper.dropItemStack(player, player.getItemBySlot(slotId))) {
			player.setItemSlot(slotId, ItemStack.EMPTY);
		}
 	}
	
	public static void replaceAndDropItemstackInHand(ServerPlayer player, InteractionHand hand, ItemStack itemStack) {			
		var current = player.getItemInHand(hand);		
		if (!current.isEmpty()) {
			PreggoMobHelper.dropItemStack(player, current);		
		}		
		player.setItemInHand(hand, itemStack);
	}
	
	public static boolean isValidCravingBySpecies(Species species) {
		return species == Species.HUMAN || species == Species.ZOMBIE || species == Species.CREEPER;
	}
	
	@CheckForNull
	static Species addInterspeciesPregnancy(ServerPlayer serverPlayer) {	
		var result = serverPlayer.getCapability(MinepreggoCapabilities.PLAYER_DATA).map(cap -> cap.getFemaleData().map(FemalePlayerImpl::getPrePregnancyData)).orElse(Optional.empty()).orElse(Optional.empty());
			
		if (result.isPresent()) {
			var pre = result.get();	
			switch (pre.typeOfSpeciesOfFather()){
				case ZOMBIE: {			
					if (!serverPlayer.hasEffect(MinepreggoModMobEffects.FULL_OF_ZOMBIES.get())) {
						serverPlayer.addEffect(new MobEffectInstance(MinepreggoModMobEffects.FULL_OF_ZOMBIES.get(), -1, 0, false, false, true));
						return Species.ZOMBIE;
					}
					break;
				}
				case CREEPER: {		
					if (!serverPlayer.hasEffect(MinepreggoModMobEffects.FULL_OF_CREEPERS.get())) {
						serverPlayer.addEffect(new MobEffectInstance(MinepreggoModMobEffects.FULL_OF_CREEPERS.get(), -1, 0, false, false, true));
						return Species.CREEPER;
					}
					break;
				}
				case ENDER: {	
					if (!serverPlayer.hasEffect(MinepreggoModMobEffects.FULL_OF_ENDERS.get())) {
						serverPlayer.addEffect(new MobEffectInstance(MinepreggoModMobEffects.FULL_OF_ENDERS.get(), -1, 0, false, false, true));
						return Species.ENDER;
					}
					break;
				}
			default:
				break;
			}
		}
		
		return null;
	}
	
	public static List<MobEffect> removeEffects(LivingEntity entity, Predicate<MobEffect> predicate) {
		List<MobEffect> effectsToRemove = new ArrayList<>();
    	for (MobEffectInstance effectInstance : entity.getActiveEffects()) {
            MobEffect effect = effectInstance.getEffect();
            if (predicate.test(effect)) {
                effectsToRemove.add(effect);
            }
        }
    	return effectsToRemove;
	}
	
	public static List<MobEffect> removeEffectsByPregnancyPhase(Player player, PregnancyPhase phase) {
		Predicate<MobEffect> predicate = phase.compareTo(PregnancyPhase.P2) >= 0
				? effect -> !PregnancySystemHelper.isPregnancyEffect(effect) && !PregnancySystemHelper.isSecondaryPregnancyEffect(effect)
				: effect -> !PregnancySystemHelper.isPregnancyEffect(effect);
					
		return removeEffects(player, predicate);
	}
	
	public static @Nonnegative int calculateExplosionLevelByPregnancyPhase(PregnancyPhase phase) {
		int ordinal = phase.ordinal();
		int maxOrdinal = PregnancyPhase.values().length - 1;
		int level = (int) Math.round(ordinal * 6.0 / maxOrdinal);
		return Math.min(level, 6);
	}
	
	public static void playSoundNearTo(LivingEntity entity, SoundEvent sound) {
		playSoundNearTo(entity, sound, 0.5f);
	}
	
	public static void playSoundNearTo(LivingEntity entity, SoundEvent soundEvent, float volume) {
	    if (entity.level() instanceof ServerLevel serverLevel && !serverLevel.isClientSide) {
	        serverLevel.playSound(null, entity.getX(), entity.getY(), entity.getZ(), 
	                             soundEvent, entity.getSoundSource(), volume, 
	                             0.9F + entity.getRandom().nextFloat() * 0.3F);
	    }
	}
	
	public static void removeHorny(ServerPlayer source) {
		source.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap -> 
			cap.getFemaleData().ifPresent(femaleData -> {
				if (femaleData.isPregnant() && femaleData.isPregnancyDataInitialized()) {								
					var pregnancyData = femaleData.getPregnancyData();					
					if (pregnancyData.getCurrentPregnancyPhase().compareTo(PregnancyPhase.P4) >= 0) {
						pregnancyData.setHorny(0);
						pregnancyData.resetHornyTimer();
						pregnancyData.getPregnancySymptoms().removePregnancySymptom(PregnancySymptom.HORNY);
						source.removeEffect(MinepreggoModMobEffects.HORNY.get());
						pregnancyData.syncState(source);
						pregnancyData.syncEffect(source);
					}
				}
			})			
		);
	}
	
	public static void removeJigglePhysics(ServerPlayer player) {
		MinepreggoModPacketHandler.INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> player), 
				new RemovePlayerJigglePhysicsS2CPacket(player.getUUID()));
	}
	
	public static void updateJigglePhysics(ServerPlayer player, SkinType skinType, @Nullable PregnancyPhase phase) {		
		MinepreggoModPacketHandler.INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> player), 
				new SyncJigglePhysicsS2CPacket(player.getUUID(), skinType, phase));
	}
}
