package dev.dixmk.minepreggo.world.pregnancy;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;
import java.util.UUID;
import java.util.stream.StreamSupport;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnegative;
import javax.annotation.Nullable;

import org.checkerframework.checker.nullness.qual.NonNull;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Table;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.client.particle.ParticleHelper;
import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.init.MinepreggoModItems;
import dev.dixmk.minepreggo.init.MinepreggoModMobEffects;
import dev.dixmk.minepreggo.init.MinepreggoModSounds;
import dev.dixmk.minepreggo.network.packet.RemoveMobEffectPacket;
import dev.dixmk.minepreggo.network.packet.SyncMobEffectPacket;
import dev.dixmk.minepreggo.utils.MathHelper;
import dev.dixmk.minepreggo.utils.TagHelper;
import dev.dixmk.minepreggo.world.entity.player.PlayerHelper;
import dev.dixmk.minepreggo.world.entity.preggo.Creature;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.Species;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractTamablePregnantCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.AbstractTamablePregnantZombieGirl;
import dev.dixmk.minepreggo.world.item.IFemaleArmor;
import dev.dixmk.minepreggo.world.item.IMaternityArmor;
import dev.dixmk.minepreggo.world.item.ItemHelper;
import dev.dixmk.minepreggo.world.item.KneeBraceItem;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.registries.ForgeRegistries;

public class PregnancySystemHelper {	
	/*
	 * Helper class for pregnancy system constants and methods
	 * WARNING: Some fields from player and preggomob are only available in server side. This class does not check that. It will fixed in future updates.
	 * 
	 * */

	private PregnancySystemHelper() {}
	
	// Post-Pregnancy Nerf
	private static final UUID SPEED_MODIFIER_TIRENESS_UUID = UUID.fromString("fa6a4626-c325-4835-8259-69577a99c9c8");
	private static final AttributeModifier SPEED_MODIFIER_TIRENESS = new AttributeModifier(SPEED_MODIFIER_TIRENESS_UUID, "Tireness speed boost", -0.1, AttributeModifier.Operation.MULTIPLY_BASE);
	private static final UUID ATTACK_SPEED_MODIFIER_TIRENESS_UUID = UUID.fromString("94d78c8b-0983-4ae4-af65-8e477ee52f2e");
	private static final AttributeModifier ATTACK_SPEED_MODIFIER_TIRENESS = new AttributeModifier(ATTACK_SPEED_MODIFIER_TIRENESS_UUID, "Tireness attack speed", -0.2, AttributeModifier.Operation.MULTIPLY_BASE);
	
	// Max Levels
	public static final int MAX_PREGNANCY_HEALTH = 100;
	public static final int MAX_PREGNANCY_FERTILITY = 100;
	public static final int MAX_CRAVING_LEVEL = 20;
	public static final int MAX_MILKING_LEVEL = 20;
	public static final int MAX_BELLY_RUBBING_LEVEL = 20;
	public static final int MAX_HORNY_LEVEL = 20;
	
	// Days
	public static final int TOTAL_PREGNANCY_DAYS = 70;
	
	// Ticks
	public static final int TOTAL_TICKS_MISCARRIAGE = 1200;
	public static final int TOTAL_TICKS_MORNING_SICKNESS = 300;
	public static final int TOTAL_TICKS_WATER_BREAKING = 1200;	
	
	public static final int TOTAL_TICKS_PREBIRTH_P4 = 600;
	public static final int TOTAL_TICKS_PREBIRTH_P5 = 800;
	public static final int TOTAL_TICKS_PREBIRTH_P6 = 900;
	public static final int TOTAL_TICKS_PREBIRTH_P7 = 1000;
	public static final int TOTAL_TICKS_PREBIRTH_P8 = 1100;
	
	public static final int TOTAL_TICKS_BIRTH_P4 = 1200;
	public static final int TOTAL_TICKS_BIRTH_P5 = 1300;
	public static final int TOTAL_TICKS_BIRTH_P6 = 1400;
	public static final int TOTAL_TICKS_BIRTH_P7 = 1500;
	public static final int TOTAL_TICKS_BIRTH_P8 = 1600;
	
	public static final int TOTAL_TICKS_KICKING_P3 = 1200;
	public static final int TOTAL_TICKS_KICKING_P4 = 1400;
	public static final int TOTAL_TICKS_KICKING_P5 = 1600;
	public static final int TOTAL_TICKS_KICKING_P6 = 1800;
	public static final int TOTAL_TICKS_KICKING_P7 = 2000;
	public static final int TOTAL_TICKS_KICKING_P8 = 2200;
	
	public static final int TOTAL_TICKS_CONTRACTION_P4 = 800;
	public static final int TOTAL_TICKS_CONTRACTION_P5 = 1000;
	public static final int TOTAL_TICKS_CONTRACTION_P6 = 1200;
	public static final int TOTAL_TICKS_CONTRACTION_P7 = 1400;
	public static final int TOTAL_TICKS_CONTRACTION_P8 = 1600;
	
	public static final int TOTAL_TICKS_FERTILITY_RATE = 6000;
	
	public static final int TOTAL_TICKS_SEXUAL_APPETITE_P0 = 4000;
	public static final int TOTAL_TICKS_SEXUAL_APPETITE_P1 = 3800;
	public static final int TOTAL_TICKS_SEXUAL_APPETITE_P2 = 3600;
	public static final int TOTAL_TICKS_SEXUAL_APPETITE_P3 = 3400;
	public static final int TOTAL_TICKS_SEXUAL_APPETITE_P4 = 3200;
	public static final int TOTAL_TICKS_SEXUAL_APPETITE_P5 = 3000;
	public static final int TOTAL_TICKS_SEXUAL_APPETITE_P6 = 2800;
	public static final int TOTAL_TICKS_SEXUAL_APPETITE_P7 = 2600;
	public static final int TOTAL_TICKS_SEXUAL_APPETITE_P8 = 2400;
	
	
	public static final int TOTAL_TICKS_CALM_BELLY_RUGGING_DOWN = 120;
	
	// Probabilities
	public static final float LOW_PREGNANCY_PAIN_PROBABILITY = 0.001F;
	public static final float MEDIUM_PREGNANCY_PAIN_PROBABILITY = 0.00125F;
	public static final float HIGH_PREGNANCY_PAIN_PROBABILITY = 0.0015F;
	
	public static final float LOW_MORNING_SICKNESS_PROBABILITY = 0.001F;
	public static final float MEDIUM_MORNING_SICKNESS_PROBABILITY = 0.0015F;
	public static final float HIGH_MORNING_SICKNESS_PROBABILITY = 0.002F;
	
	public static final float LOW_ANGER_PROBABILITY = 0.005F;
	public static final float MEDIUM_ANGER_PROBABILITY = 0.0075F;
	public static final float HIGH_ANGER_PROBABILITY = 0.01F;
	
	// Pregnancy Symptoms
	public static final int CRAVING_VALUE = 4;
	public static final int ACTIVATE_CRAVING_SYMPTOM = 16;
	public static final int DESACTIVATE_CRAVING_SYMPTOM = 2;
	
	public static final int MILKING_VALUE = 5;
	public static final int ACTIVATE_MILKING_SYMPTOM = 12;
	public static final int DESACTIVATE_MILKING_SYMPTOM = 8;
	
	public static final int BELLY_RUBBING_VALUE = 3;
	public static final int ACTIVATE_BELLY_RUBS_SYMPTOM = 12;
	public static final int DESACTIVATEL_BELLY_RUBS_SYMPTOM = 8;
	
	public static final int ACTIVATE_HORNY_SYMPTOM = 17;
		
	public static final int NATURAL_PREGNANCY_INITIALIZER_TIME = 10000;
	
	static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	
	public static final RandomSource RANDOM_SOURCE = RandomSource.create();
	
	public static void applyPostPregnancyNerf(LivingEntity entity) {
		AttributeInstance speed = entity.getAttribute(Attributes.MOVEMENT_SPEED);
		AttributeInstance attackSpeed = entity.getAttribute(Attributes.ATTACK_SPEED);	
		speed.addTransientModifier(SPEED_MODIFIER_TIRENESS);	
		attackSpeed.addTransientModifier(ATTACK_SPEED_MODIFIER_TIRENESS);	
	}
	
	public static void removePostPregnancyNeft(LivingEntity entity) {
		AttributeInstance speed = entity.getAttribute(Attributes.MOVEMENT_SPEED);
		AttributeInstance attackSpeed = entity.getAttribute(Attributes.ATTACK_SPEED);	
		speed.removeModifier(SPEED_MODIFIER_TIRENESS);	
		attackSpeed.removeModifier(ATTACK_SPEED_MODIFIER_TIRENESS);	
	}
	
	private static final ImmutableMap<Species, Item> MILK_ITEM = ImmutableMap.of(
			Species.CREEPER, MinepreggoModItems.CREEPER_BREAST_MILK_BOTTLE.get(),
			Species.ZOMBIE, MinepreggoModItems.ZOMBIE_BREAST_MILK_BOTTLE.get(),
			Species.HUMAN, MinepreggoModItems.HUMAN_BREAST_MILK_BOTTLE.get()			
	);
	
	
	private static final ImmutableMap<Species, ImmutableMap<Craving, List<Item>>> CRAVING_ITEM = ImmutableMap.of(
			Species.CREEPER, ImmutableMap.of(
					Craving.SALTY, List.of(MinepreggoModItems.ACTIVATED_GUNPOWDER_WITH_SALT.get()), 
					Craving.SWEET, List.of(MinepreggoModItems.ACTIVATED_GUNPOWDER_WITH_CHOCOLATE.get()), 
					Craving.SOUR, List.of(MinepreggoModItems.SOUR_ACTIVATED_GUNPOWDER.get()),
					Craving.SPICY, List.of(MinepreggoModItems.ACTIVATED_GUNPOWDER_WITH_HOT_SAUCE.get())),	
			Species.ZOMBIE, ImmutableMap.of(
					Craving.SALTY, List.of(MinepreggoModItems.BRAIN_WITH_SALT.get()), 
					Craving.SWEET, List.of(MinepreggoModItems.BRAIN_WITH_CHOCOLATE.get()), 
					Craving.SOUR, List.of(MinepreggoModItems.SOUR_BRAIN.get()),
					Craving.SPICY, List.of(MinepreggoModItems.BRAIN_WITH_HOT_SAUCE.get())),
			Species.HUMAN,	ImmutableMap.of(
					Craving.SALTY, List.of(MinepreggoModItems.PICKLE.get(), MinepreggoModItems.FRENCH_FRIES.get()), 
					Craving.SWEET, List.of(MinepreggoModItems.CHOCOLATE_BAR.get(), MinepreggoModItems.CANDY_APPLE.get()), 
					Craving.SOUR, List.of(MinepreggoModItems.LEMON_ICE_POPSICLES.get(), MinepreggoModItems.LEMON_ICE_CREAM.get(), MinepreggoModItems.LEMON_DROPS.get()),
					Craving.SPICY, List.of(MinepreggoModItems.HOT_CHICKEN.get(), MinepreggoModItems.CHILI_POPPERS.get()))	
	);
	
	private static final Table<Species, Creature, Item> ALIVE_BABIES = ImmutableTable.<Species, Creature, Item>builder()
			.put(Species.HUMAN, Creature.HUMANOID, MinepreggoModItems.BABY_HUMAN.get())
			.put(Species.ZOMBIE, Creature.HUMANOID, MinepreggoModItems.BABY_ZOMBIE.get())
			.put(Species.CREEPER, Creature.HUMANOID, MinepreggoModItems.BABY_HUMANOID_CREEPER.get())
			.put(Species.CREEPER, Creature.MONSTER, MinepreggoModItems.BABY_CREEPER.get())
			.put(Species.ENDER, Creature.MONSTER, MinepreggoModItems.BABY_ENDER.get())
			.put(Species.ENDER, Creature.HUMANOID, MinepreggoModItems.BABY_HUMANOID_ENDER.get())
			.put(Species.VILLAGER, Creature.HUMANOID, MinepreggoModItems.BABY_VILLAGER.get())
			.build();

	private static final Table<Species, Creature, Item> DEAD_BABIES = ImmutableTable.<Species, Creature, Item>builder()
			.put(Species.HUMAN, Creature.HUMANOID, MinepreggoModItems.DEAD_HUMAN_FETUS.get())
			.put(Species.ZOMBIE, Creature.HUMANOID, MinepreggoModItems.DEAD_ZOMBIE_FETUS.get())
			.put(Species.CREEPER, Creature.HUMANOID, MinepreggoModItems.DEAD_HUMANOID_CREEPER_FETUS.get())
			.put(Species.CREEPER, Creature.MONSTER, MinepreggoModItems.DEAD_CREEPER_FETUS.get())
			.put(Species.ENDER, Creature.MONSTER, MinepreggoModItems.DEAD_ENDER_FETUS.get())
			.put(Species.ENDER, Creature.HUMANOID, MinepreggoModItems.DEAD_HUMANOID_ENDER_FETUS.get())
			.put(Species.VILLAGER, Creature.HUMANOID, MinepreggoModItems.DEAD_VILLAGER_FETUS.get())
			.build();

	private static final ImmutableMap<Craving, Float> CRAVING_WEIGHTS = ImmutableMap.of(		
			Craving.SWEET, 0.075F,
			Craving.SOUR, 0.275F,
			Craving.SPICY, 0.3F,
			Craving.SALTY, 0.35F
			);
	
	@CheckForNull
	public static ImmutableMap<Craving, List<Item>> getCravingMap(Species species) {
		return species != null ? CRAVING_ITEM.get(species) : null;
	}
	
	@CheckForNull
	public static List<Item> getCravingItems(Species species, Craving craving) {
		if (species == null || craving == null) {
			return null;
		}
		final var cravingMap = CRAVING_ITEM.get(species);
		return cravingMap != null ? cravingMap.get(craving) : null;
	}
	
	
	@CheckForNull
	public static Item getMilkItem(Species species) {
		if (species == null) {
			return null;
		}
		return MILK_ITEM.get(species);
	}

	@CheckForNull
	public static Item getDeadBabyItem(Species species, Creature creature) {
		if (species == null || creature == null) {
			return null;
		}		
		return DEAD_BABIES.get(species, creature);
	}
	
	@CheckForNull
	public static Item getAliveBabyItem(Species species, Creature creature) {
		if (species == null || creature == null) {
			return null;
		}	
		return ALIVE_BABIES.get(species, creature);
	}

	public static List<ItemStack> getAliveBabies(@NonNull Womb womb) {
		return womb.stream()
				.map(babyData -> {
				var babyItem = getAliveBabyItem(babyData.typeOfSpecies, babyData.typeOfCreature);
				if (babyItem != null) {
					return new ItemStack(babyItem);
				}
				return ItemStack.EMPTY;
				})
				.filter(i -> !i.isEmpty())
				.toList();
	}

	public static List<ItemStack> getDeadBabies(@NonNull Womb womb) {
		return womb.stream()
				.map(babyData -> {
				var babyItem = getDeadBabyItem(babyData.typeOfSpecies, babyData.typeOfCreature);
				if (babyItem != null) {
					return new ItemStack(babyItem);
				}
				return ItemStack.EMPTY;
				})
				.filter(i -> !i.isEmpty())
				.toList();
	}
	
	public static boolean isPregnantEntityValid(LivingEntity entity) {	
		if (entity instanceof ServerPlayer serverPlayer) {			
			Optional<Boolean> result = serverPlayer.getCapability(MinepreggoCapabilities.PLAYER_DATA).map(cap ->
											cap.getFemaleData().map(IFemaleEntity::isPregnant)
										).orElse(Optional.empty());		
			if (result.isPresent()) {
				return result.get().booleanValue();
			}		
		}

		return entity instanceof PreggoMob p && p instanceof IPregnancySystemHandler;
	}
	
	
	public static boolean isInPostPregnancyPhase(LivingEntity entity) {	
		
		if (entity instanceof ServerPlayer serverPlayer) {	
			Optional<Boolean> result = serverPlayer.getCapability(MinepreggoCapabilities.PLAYER_DATA).map(cap ->
											cap.getFemaleData().map(femaleData -> femaleData.getPostPregnancyData().isPresent())
										).orElse(Optional.empty());		
			if (result.isPresent()) {
				return result.get().booleanValue();
			}
		}
		else if (entity instanceof IFemaleEntity femaleEntity) {
			return femaleEntity.getPostPregnancyData().isPresent();
		}

		return false;
	}
	
	
	
	
	public static boolean hasEnoughBedsForBreeding(LivingEntity source, @Nonnegative int minBedsRequired, @Nonnegative int range) {
	    Level level = source.level();
	    AABB searchArea = new AABB(source.blockPosition()).inflate(range);
	    Set<BlockPos> checkedBeds = new HashSet<>();
	    int availableBeds = 0;
 
        for (final var pos : BlockPos.betweenClosed(
        	    (int)searchArea.minX, (int)searchArea.minY, (int)searchArea.minZ,
        	    (int)searchArea.maxX, (int)searchArea.maxY, (int)searchArea.maxZ)) {   	        
	    	
        	if (checkedBeds.contains(pos)) {
                continue;
            }	
        	
	    	BlockState state = level.getBlockState(pos);      
	        if (state.getBlock() instanceof BedBlock && !state.getValue(BedBlock.OCCUPIED).booleanValue()) {
	        	availableBeds++;
                
                // Mark both parts as checked
                checkedBeds.add(pos);
                checkedBeds.add(pos.relative(BedBlock.getConnectedDirection(state)));               	
	        }
	    }	        

	    return availableBeds >= minBedsRequired;
	}
	
	public static boolean canTouchBelly(Player source, LivingEntity target) {
		return source.isShiftKeyDown()
				&& source.getMainHandItem().isEmpty() 
				&& source.getDirection() == target.getDirection().getOpposite()
				&& target.getItemBySlot(EquipmentSlot.CHEST).isEmpty();
	}
	
	
	public static boolean tryRubBelly(ServerPlayer source, ServerPlayer target, Level level) {
		Optional<Boolean> result = target.getCapability(MinepreggoCapabilities.PLAYER_DATA).map(cap -> 
			cap.getFemaleData().map(femaleData -> {
				if (femaleData.isPregnant() && femaleData.isPregnancySystemInitialized() && canTouchBelly(source, target)) {
					
					if (!level.isClientSide) {
										
						PlayerHelper.playSoundNearTo(target, MinepreggoModSounds.BELLY_TOUCH.get(), 0.6f);
						
						final var pregnancySystem = femaleData.getPregnancySystem();
						final var isFather = femaleData.getFather() != null && femaleData.getFather().equals(source.getUUID());
						ParticleOptions particle = isFather ? ParticleTypes.HEART : ParticleTypes.SMOKE;

						playSlappingBellyAnimation(source, target);
								
						if (pregnancySystem.getCurrentPregnancyStage().compareTo(PregnancyPhase.P3) >= 0) {				
							if (femaleData.getPregnancyEffects().getBellyRubs() >= BELLY_RUBBING_VALUE) {
								femaleData.getPregnancyEffects().decrementBellyRubs(isFather ? BELLY_RUBBING_VALUE : 1);
								femaleData.getPregnancyEffects().sync(target);			
							}
							else {
								particle = ParticleTypes.ANGRY_VILLAGER;
							}
						}
					
						ParticleHelper.spawnRandomlyFromServer(target, particle);
					}
					return true;			
				}
				return false;
			})
		).orElse(Optional.empty());
		
		return result.isPresent() && result.get().booleanValue();
	}
	
    public static boolean isPregnancyEffect(MobEffect effect) { 	
    	Optional<Holder<MobEffect>> holder = ForgeRegistries.MOB_EFFECTS.getHolder(effect);
        return holder.isPresent() && holder.get().is(TagHelper.PREGNANCY_EFFECTS);
    }
    
    public static boolean isSecondaryPregnancyEffect(MobEffect effect) { 	
    	Optional<Holder<MobEffect>> holder = ForgeRegistries.MOB_EFFECTS.getHolder(effect);
    	return holder.isPresent() && holder.get().is(TagHelper.SECONDARY_PREGNANCY_EFFECTS);
    }
    
    public static boolean isFemaleEffect(MobEffect effect) { 	
    	Optional<Holder<MobEffect>> holder = ForgeRegistries.MOB_EFFECTS.getHolder(effect);
    	return holder.isPresent() && holder.get().is(TagHelper.FEMALE_EFFECTS);
    }
    
	public static void syncNewMobEffect(LivingEntity entity, MobEffectInstance effect) {
		MinepreggoModPacketHandler.INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> entity),
				new SyncMobEffectPacket(entity.getId(), effect));
	}
	
	public static void syncRemovedMobEffect(LivingEntity entity, MobEffect effect) {
		MinepreggoModPacketHandler.INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> entity),
				new RemoveMobEffectPacket(entity.getId(), effect));
	}
	
	/**
	 * Syncs the removal of expired pregnancy effects to a specific player.
	 * This method ensures that when a tracker player starts tracking another player,
	 * all expired pregnancy effects are properly communicated.
	 * 
	 * @param entity the living entity whose expired effects should be synced
	 * @param trackerPlayer the player who is tracking this entity
	 */
	public static void syncExpiredMobEffectsToTracker(LivingEntity entity, ServerPlayer trackerPlayer) {
		if (!(entity instanceof ServerPlayer trackedPlayer)) {
			return;
		}
		
		// Get all known pregnancy effects from the registry
		StreamSupport.stream(ForgeRegistries.MOB_EFFECTS.spliterator(), false)
			.filter(effect -> isPregnancyEffect(effect))
			.forEach(effect -> {
				// If the tracked player doesn't have this effect, sync its removal
				if (!trackedPlayer.hasEffect(effect)) {
					MinepreggoModPacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> trackerPlayer),
							new RemoveMobEffectPacket(trackedPlayer.getId(), effect));
				}
			});
	}
	
	public static boolean canUseChestplate(Item armor, PregnancyPhase pregnancyPhase) {
		return canUseChestplate(armor, pregnancyPhase, false);
	}
	
	public static boolean canUseChestplate(Item armor, PregnancyPhase pregnancyPhase, boolean considerBoobs) {
		if (!ItemHelper.isChest(armor)) {
			return false;
		}		
		else if (pregnancyPhase == PregnancyPhase.P0) {		
			if (considerBoobs) {
				return armor instanceof IFemaleArmor;
			}		
			return true;
		}		
		return armor instanceof IMaternityArmor maternityArmor && pregnancyPhase.compareTo(maternityArmor.getMinPregnancyPhaseAllowed()) <= 0;
	}

	public static boolean canUseLegging(Item armor, @Nullable PregnancyPhase pregnancyPhase) {	
		if (!ItemHelper.isLegging(armor)) {
			return false;
		}		
		if (pregnancyPhase == null) {
			return true;
		}		
		return armor instanceof KneeBraceItem;
	}
	
	public static Craving getRandomCraving(RandomSource randomSource) {
		final float p = randomSource.nextFloat();
		float a = 0F;
		
		for (final var entry : CRAVING_WEIGHTS.entrySet()) {
			a += entry.getValue();
			if (a >= p) {
				return entry.getKey();
			}
		}

		MinepreggoMod.LOGGER.warn("PregnancySystemHelper.getRandomCraving: Weights do not sum to 1. Returning random craving.");	
		return Craving.values()[randomSource.nextInt(0, Craving.values().length)];
	}
	
	public static ItemStack createPrenatalCheckUpResult(PrenatalCheckUpInfo info, PrenatalRegularCheckUpData data, String autor) {
		ListTag pages = new ListTag();
		List<Component> components = new ArrayList<>();

		components.add(Component.literal("")
	    		.append(Component.translatable("book.minepreggo.prenatal_checkup.regular.template.page.1.intro").withStyle(ChatFormatting.BOLD, ChatFormatting.ITALIC))
	    		.append(Component.translatable("book.minepreggo.prenatal_checkup.default.template.page.1.mother", info.motherName))
	    		.append(Component.translatable("book.minepreggo.prenatal_checkup.default.template.page.1.price", Integer.toString(info.price)))
	    		.append(Component.translatable("book.minepreggo.prenatal_checkup.default.template.page.1.date", info.date.format(DATE_FORMAT)))
	    		);		
		components.add((Component.translatable("book.minepreggo.prenatal_checkup.regular.template.page.2.intro").withStyle(ChatFormatting.BOLD, ChatFormatting.ITALIC))
		    	.append(Component.translatable("book.minepreggo.prenatal_checkup.regular.template.page.2.current_phase", data.currentPregnancyPhase.toString()))
		    	.append(Component.translatable("book.minepreggo.prenatal_checkup.regular.template.page.2.last_phase", data.lastPregnancyPhase.toString()))
		    	.append(Component.translatable("book.minepreggo.prenatal_checkup.regular.template.page.2.health", Integer.toString(data.pregnancyHealth)))
		    	.append(Component.translatable("book.minepreggo.prenatal_checkup.regular.template.page.2.num_of_babies", Integer.toString(data.numOfBabies)))
		    	);
		components.add((Component.translatable("book.minepreggo.prenatal_checkup.regular.template.page.3.intro").withStyle(ChatFormatting.BOLD, ChatFormatting.ITALIC))
		    	.append(Component.translatable("book.minepreggo.prenatal_checkup.regular.template.page.3.days_elapsed", Integer.toString(data.daysPassed)))
		    	.append(Component.translatable("book.minepreggo.prenatal_checkup.regular.template.page.3.days_next_phase", Integer.toString(data.daysToNextPhase)))
		    	.append(Component.translatable("book.minepreggo.prenatal_checkup.regular.template.page.3.days_birth", Integer.toString(data.daysToGiveBirth)))
		    	);	
		
	
		
		components.forEach(c -> pages.add(StringTag.valueOf(Component.Serializer.toJson(c))));				
	    ItemStack book = new ItemStack(Items.WRITTEN_BOOK);
	    CompoundTag tag = book.getOrCreateTag();
	    tag.put("pages", pages);
	    tag.putString("title", Component.translatable("book.minepreggo.prenatal_checkup.regular.template.title").getString());
	    tag.putString("author", autor); 
		return book;
	}
	
	public static ItemStack createPrenatalCheckUpResult(PrenatalCheckUpInfo info, PrenatalUltrasoundScanData data, String autor) {
		ListTag pages = new ListTag();
		List<Component> components = new ArrayList<>();
		
		components.add(Component.literal("")
	    		.append(Component.translatable("book.minepreggo.prenatal_checkup.ultrasound_scan.template.page.1.intro").withStyle(ChatFormatting.BOLD, ChatFormatting.ITALIC))
	    		.append(Component.translatable("book.minepreggo.prenatal_checkup.default.template.page.1.mother", info.motherName))
	    		.append(Component.translatable("book.minepreggo.prenatal_checkup.default.template.page.1.price", Integer.toString(info.price)))
	    		.append(Component.translatable("book.minepreggo.prenatal_checkup.default.template.page.1.date", info.date.format(DATE_FORMAT)))
				.append(Component.translatable("book.minepreggo.prenatal_checkup.ultrasound_scan.template.page.1.babies_inside_belly", Integer.toString(data.babiesInsideWomb.getNumOfBabies())))
				);

		data.babiesInsideWomb().forEach(babyData -> 
			components.add((Component.translatable("book.minepreggo.prenatal_checkup.ultrasound_scan.template.page.default.title").withStyle(ChatFormatting.BOLD, ChatFormatting.ITALIC))
			    	.append(Component.translatable("book.minepreggo.prenatal_checkup.ultrasound_scan.template.page.gender", babyData.gender.toString()))
			    	.append(Component.translatable("book.minepreggo.prenatal_checkup.ultrasound_scan.template.page.type_of_species", babyData.typeOfSpecies.toString()))
			    	.append(Component.translatable("book.minepreggo.prenatal_checkup.ultrasound_scan.template.page.type_of_creature", babyData.typeOfCreature.toString())))
		);
		

		components.forEach(c -> pages.add(StringTag.valueOf(Component.Serializer.toJson(c))));
		
	    ItemStack book = new ItemStack(Items.WRITTEN_BOOK);
	    CompoundTag tag = book.getOrCreateTag();
	    tag.put("pages", pages);
	    tag.putString("title", Component.translatable("book.minepreggo.prenatal_checkup.ultrasound_scan.template.title").getString());
	    tag.putString("author", autor); 
		return book;
	}
	
	public static ItemStack createPrenatalCheckUpResult(PrenatalCheckUpInfo info, List<PrenatalPaternityTestData> data, String autor) {
		ListTag pages = new ListTag();
		List<Component> components = new ArrayList<>();
		
		long result = data.stream()
			.filter(d -> d.result)
			.count();
			
		components.add(Component.literal("")
	    		.append(Component.translatable("book.minepreggo.prenatal_checkup.paternity_test.template.page.1.intro").withStyle(ChatFormatting.BOLD, ChatFormatting.ITALIC))
	    		.append(Component.translatable("book.minepreggo.prenatal_checkup.default.template.page.1.mother", info.motherName))
	    		.append(Component.translatable("book.minepreggo.prenatal_checkup.default.template.page.1.price", Integer.toString(info.price)))
	    		.append(Component.translatable("book.minepreggo.prenatal_checkup.default.template.page.1.date", info.date.format(DATE_FORMAT)))
	    		.append(Component.translatable("book.minepreggo.prenatal_checkup.paternity_test.template.page.1.number_of_target", Integer.toString(data.size())))
	    		.append(Component.translatable("book.minepreggo.prenatal_checkup.paternity_test.template.page.1.result",
	    				result != 0 ? Component.translatable("book.minepreggo.prenatal_checkup.paternity_test.template.page.1.result.known").getString()
	    						: Component.translatable("book.minepreggo.prenatal_checkup.paternity_test.template.page.1.result.unknown").getString())));
			
		data.forEach(d ->
			components.add((Component.translatable("book.minepreggo.prenatal_checkup.paternity_test.template.page.default.intro").withStyle(ChatFormatting.BOLD, ChatFormatting.ITALIC))
		    		.append(Component.translatable("book.minepreggo.prenatal_checkup.paternity_test.template.page.default.id", Integer.toString(d.id)))
		    		.append(Component.translatable("book.minepreggo.prenatal_checkup.paternity_test.template.page.default.name", d.name))
		    		.append(Component.translatable("book.minepreggo.prenatal_checkup.paternity_test.template.page.default.result",
		    				d.result ? Component.translatable("book.minepreggo.prenatal_checkup.paternity_test.template.page.default.result.positive").getString()
		    						: Component.translatable("book.minepreggo.prenatal_checkup.paternity_test.template.page.default.result.negative").getString())))
		);

		components.forEach(c -> pages.add(StringTag.valueOf(Component.Serializer.toJson(c))));	
	    ItemStack book = new ItemStack(Items.WRITTEN_BOOK);
	    CompoundTag tag = book.getOrCreateTag();
	    tag.put("pages", pages);
	    tag.putString("title", Component.translatable("book.minepreggo.prenatal_checkup.paternity_test.template.title").getString());
	    tag.putString("author", autor); 
		return book;
	}

    public static record PrenatalCheckUpInfo(
    		String motherName,
    		int price,
    		LocalDateTime date) {}
    
    public static record PrenatalRegularCheckUpData(
			PregnancyPhase currentPregnancyPhase,
			PregnancyPhase lastPregnancyPhase,
			int pregnancyHealth,
			int numOfBabies,
			int daysPassed,
			int daysToNextPhase,
			int daysToGiveBirth) {}
    
	public static record PrenatalUltrasoundScanData(
			Species motherSpecies,
			Womb babiesInsideWomb) {}
    
	public static record PrenatalPaternityTestData(
			int id,
			String name,
			boolean result) {}
	
	// Pregnancy Calculates	START	
	public static PregnancyPhase calculateMinPhaseToGiveBirth(PregnancyPhase currentPregnancyStage) {
		if (currentPregnancyStage.compareTo(PregnancyPhase.P4) >= 1) {
			return PregnancyPhase.values()[Math.min(currentPregnancyStage.ordinal() + 1, PregnancyPhase.values().length - 1)];
		}	
		return PregnancyPhase.P4;
	}
	
	public static PregnancyPhase calculateRandomMinPhaseToGiveBirthFrom(PregnancyPhase currentPregnancyStage, RandomSource randomSource) {		
		int c = currentPregnancyStage.ordinal();
		if (c < 4) {
			c = 4;
		}	
		return PregnancyPhase.values()[randomSource.nextInt(c, PregnancyPhase.values().length)];	
    }
	
	
    public static int calculateDaysToGiveBirth(@NonNull IPregnancySystemHandler h) {
        return h.getTotalDaysOfPregnancy() - calculateTotalDaysPassedFromPhaseP0(h);
    }
    
    public static int calculateTotalDaysPassedFromPhaseP0(@NonNull IPregnancySystemHandler h) {   	
    	var map = h.getMapPregnancyPhase();
    	final PregnancyPhase currentPhase = h.getCurrentPregnancyStage();
    	
    	final var totalDaysPassed = StreamSupport.stream(Arrays.spliterator(PregnancyPhase.values()), false)
    			.filter(phase -> phase.compareTo(currentPhase) <= -1)
    			.mapToInt(phase -> {
    				final var days = map.getDaysByPregnancyPhase(phase);
    				if (days == 0) {
    					MinepreggoMod.LOGGER.warn("PregnancySystemHelper.calculateTotalDaysPassedFromPhaseP0: Missing days for pregnancy phase {}", phase.name());
    					return 0;
    				}	
    				return days;
    			})
    			.sum();
    	
    	return totalDaysPassed + h.getDaysPassed();
    } 
    
    public static int calculateDaysToNextPhase(@NonNull IPregnancySystemHandler h) {
		return h.getDaysByCurrentStage() - h.getDaysPassed();
    }
   
	// Pregnancy Calculates	END	

	public static boolean shouldBoobsBeHidden(Item armor) {
		return !(armor instanceof IMaternityArmor maternityArmor && maternityArmor.areBoobsExposed());
	}
    
    
	
	// TODO: Rework this method to evaluate only hostile mobs that can target the source entities, ITamablePreggoMob are hostile by default
    public static boolean areHostileMobsNearby(LivingEntity source1, LivingEntity source2, @Nonnegative double detectionRadius) {
        Level level = source1.level();

        if (level.isClientSide) {
            return false;
        }

        double radiusSquared = detectionRadius * detectionRadius;

        return level.getEntitiesOfClass(Mob.class,
        		source1.getBoundingBox().inflate(detectionRadius))
                .stream()
                .anyMatch(mob -> {
                    if (mob.isDeadOrDying()) return false;
                    if (mob.distanceToSqr(source1) > radiusSquared || mob.distanceToSqr(source2) > radiusSquared) return false;
                    
                    var target = mob.getTarget();
                    if (target == null) return false;
                    return target.getId() == source1.getId() || target.getId() == source2.getId();
                });
    }
    
    public enum HorizontalPosition {
        LEFT, RIGHT, CENTER
    }

    public static HorizontalPosition getHorizontalPosition(LivingEntity reference, LivingEntity target, double tolerance) {
        // Get horizontal look vector (ignoring pitch)
        Vec3 look = reference.getLookAngle();
        Vec3 flatLook = new Vec3(look.x, 0, look.z).normalize();

        // Compute relative offset from reference to target
        Vec3 offset = new Vec3(
            target.getX() - reference.getX(),
            0,
            target.getZ() - reference.getZ()
        ).normalize();

        // Avoid NaNs if offset is zero-length
        if (offset.lengthSqr() < 1e-6) {
            return HorizontalPosition.CENTER;
        }

        // Cross product in 2D: (a.x * b.z - a.z * b.x)
        // This gives signed magnitude: >0 = left, <0 = right
        double cross = flatLook.x * offset.z - flatLook.z * offset.x;

        if (cross > tolerance) {
            return HorizontalPosition.LEFT;
        } else if (cross < -tolerance) {
            return HorizontalPosition.RIGHT;
        } else {
            return HorizontalPosition.CENTER;
        }
    }
    
    
    public static OptionalInt getPregnancyDamage(LivingEntity pregnantEntity, PregnancyPhase phase, DamageSource damagesource) {
    	if (phase == PregnancyPhase.P0) {
    		return OptionalInt.empty();
    	}
    	
    	RandomSource randomSource = pregnantEntity.getRandom();

		if ((pregnantEntity.hasEffect(MinepreggoModMobEffects.PREGNANCY_RESISTANCE.get()) && randomSource.nextFloat() < 0.9F)
				|| (!damagesource.is(DamageTypes.FALL) && !pregnantEntity.getItemBySlot(EquipmentSlot.CHEST).isEmpty() && randomSource.nextFloat() < 0.5)) {
			return OptionalInt.empty();
		}
		int damage = 0;
		
		if (pregnantEntity.getHealth() < pregnantEntity.getMaxHealth() * 0.5f) {
			damage = phase.ordinal() + randomSource.nextInt(3);
		} 	
		
		if (damagesource.is(DamageTypes.EXPLOSION) || damagesource.is(DamageTypes.PLAYER_EXPLOSION) || damagesource.is(DamageTypes.FALL)) {
			damage = damage == 0 ? 7 : damage * 2;
		}	
				
		return OptionalInt.of(damage);
    }
    
    public static void playSlappingBellyAnimation(LivingEntity source, LivingEntity target) {
		final HorizontalPosition position = getHorizontalPosition(source, target, 0.1);
		byte id;	
		// TODO: Use contant values for ids, try to link with animation system later
		if (position == HorizontalPosition.LEFT) {
			id = 102;
		}
		else if (position == HorizontalPosition.CENTER) {
			id = 101;
		}
		else {
			id = 100;
		}		
		target.level().broadcastEntityEvent(target, id);
    }
    
    public static void playSlappingBellyAnimation(Player source, Player target) {
    	playSlappingBellyAnimation((LivingEntity) source, (LivingEntity) target);
    }
    
    public static void playSlappingBellyAnimation(Player source, AbstractTamablePregnantZombieGirl<?,?> target) {
    	playSlappingBellyAnimation((LivingEntity) source, (LivingEntity) target);
    }
    
    public static void playSlappingBellyAnimation(Player source, AbstractTamablePregnantCreeperGirl<?,?> target) {
    	playSlappingBellyAnimation((LivingEntity) source, (LivingEntity) target);
    }
    
    public static float getSpawnProbabilityBasedPregnancy(IPregnancySystemHandler handler, float t0, float k, float pMin, float pMax) {
		final int totalDays = handler.getTotalDaysOfPregnancy();
		final int totalDaysPassed = PregnancySystemHelper.calculateTotalDaysPassedFromPhaseP0(handler);
		
		final float t = Mth.clamp(totalDaysPassed / (float) totalDays, 0, 1);	
		final float p = MathHelper.sigmoid(pMin, pMax, k, t, t0);
		
		MinepreggoMod.LOGGER.debug("SPAWN PROBABILITY BASED IN PREGNANCY: class={}, totalDays={}, totalDaysPassed={}, t={}, p={}",
				handler.getClass().getSimpleName(), totalDays, totalDaysPassed, t, p);
		
		return p;
    }
}
