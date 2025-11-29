package dev.dixmk.minepreggo.world.pregnancy;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
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
import dev.dixmk.minepreggo.network.packet.RemoveMobEffectPacket;
import dev.dixmk.minepreggo.network.packet.SyncMobEffectPacket;
import dev.dixmk.minepreggo.utils.TagHelper;
import dev.dixmk.minepreggo.world.entity.preggo.Creature;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.Species;
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
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.registries.ForgeRegistries;

public class PregnancySystemHelper {
	
	private PregnancySystemHelper() {}
	
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
	public static final int TOTAL_TICKS_MISCARRIAGE = 400;
	public static final int TOTAL_TICKS_MORNING_SICKNESS = 200;
	public static final int TOTAL_TICKS_WATER_BREAKING = 1200;
	
	public static final int TOTAL_TICKS_PREBIRTH_P4 = 300;
	public static final int TOTAL_TICKS_PREBIRTH_P5 = 400;
	public static final int TOTAL_TICKS_PREBIRTH_P6 = 500;
	public static final int TOTAL_TICKS_PREBIRTH_P7 = 600;
	public static final int TOTAL_TICKS_PREBIRTH_P8 = 700;
	
	public static final int TOTAL_TICKS_BIRTH_P4 = 900;
	public static final int TOTAL_TICKS_BIRTH_P5 = 1000;
	public static final int TOTAL_TICKS_BIRTH_P6 = 1100;
	public static final int TOTAL_TICKS_BIRTH_P7 = 1200;
	public static final int TOTAL_TICKS_BIRTH_P8 = 1300;
	
	public static final int TOTAL_TICKS_KICKING_P3 = 400;
	public static final int TOTAL_TICKS_KICKING_P4 = 500;
	public static final int TOTAL_TICKS_KICKING_P5 = 600;
	public static final int TOTAL_TICKS_KICKING_P6 = 700;
	public static final int TOTAL_TICKS_KICKING_P7 = 800;
	public static final int TOTAL_TICKS_KICKING_P8 = 900;
	
	public static final int TOTAL_TICKS_CONTRACTION_P4 = 600;
	public static final int TOTAL_TICKS_CONTRACTION_P5 = 700;
	public static final int TOTAL_TICKS_CONTRACTION_P6 = 800;
	public static final int TOTAL_TICKS_CONTRACTION_P7 = 900;
	public static final int TOTAL_TICKS_CONTRACTION_P8 = 1000;
	
	public static final int TOTAL_TICKS_FERTILITY_RATE = 6000;
	
	// Probabilities
	public static final float LOW_PREGNANCY_PAIN_PROBABILITY = 0.000075F;
	public static final float MEDIUM_PREGNANCY_PAIN_PROBABILITY = 0.000125F;
	public static final float HIGH_PREGNANCY_PAIN_PROBABILITY = 0.000175F;
	
	public static final float LOW_MORNING_SICKNESS_PROBABILITY = 0.0001F;
	public static final float MEDIUM_MORNING_SICKNESS_PROBABILITY = 0.00015F;
	public static final float HIGH_MORNING_SICKNESS_PROBABILITY = 0.0002F;
	
	public static final float LOW_ANGER_PROBABILITY = 0.005F;
	public static final float MEDIUM_ANGER_PROBABILITY = 0.0075F;
	public static final float HIGH_ANGER_PROBABILITY = 0.01F;
	
	// Pregnancy Symptoms
	public static final int CRAVING_VALUE = 4;
	public static final int ACTIVATE_CRAVING_SYMPTOM = 16;
	public static final int DESACTIVATE_CRAVING_SYMPTOM = 2;
	
	public static final int MILKING_VALUE = 5;
	public static final int ACTIVATE_MILKING_SYMPTOM = 16;
	public static final int DESACTIVATE_MILKING_SYMPTOM = 8;
	
	public static final int BELLY_RUBBING_VALUE = 3;
	public static final int ACTIVATE_BELLY_RUBS_SYMPTOM = 12;
	public static final int DESACTIVATEL_BELLY_RUBS_SYMPTOM = 4;
	
	public static final int ACTIVATE_HORNY_SYMPTOM = 17;
		
	public static final int NATURAL_PREGNANCY_INITIALIZER_TIME = 10000;
	
	static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	
	public static final RandomSource RANDOM_SOURCE = RandomSource.create();
	
	protected static final ImmutableMap<Species, Item> MILK_ITEM = ImmutableMap.of(
			Species.CREEPER, MinepreggoModItems.CREEPER_BREAST_MILK_BOTTLE.get(),
			Species.ZOMBIE, MinepreggoModItems.ZOMBIE_BREAST_MILK_BOTTLE.get(),
			Species.HUMAN, MinepreggoModItems.HUMAN_BREAST_MILK_BOTTLE.get()			
	);
	
	
	protected static final ImmutableMap<Species, ImmutableMap<Craving, List<Item>>> CRAVING_ITEM = ImmutableMap.of(
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
					Craving.SALTY, List.of(MinepreggoModItems.PICKLE.get()), 
					Craving.SWEET, List.of(MinepreggoModItems.CHOCOLATE_BAR.get()), 
					Craving.SOUR, List.of(MinepreggoModItems.LEMON_ICE_POPSICLES.get(), MinepreggoModItems.LEMON_ICE_CREAM.get()),
					Craving.SPICY, List.of(MinepreggoModItems.HOT_CHICKEN.get()))	
	);
	
	protected static final Table<Species, Creature, Item> ALIVE_BABIES = ImmutableTable.<Species, Creature, Item>builder()
			.put(Species.HUMAN, Creature.HUMANOID, MinepreggoModItems.BABY_HUMAN.get())
			.put(Species.ZOMBIE, Creature.HUMANOID, MinepreggoModItems.BABY_ZOMBIE.get())
			.put(Species.CREEPER, Creature.HUMANOID, MinepreggoModItems.BABY_HUMANOID_CREEPER.get())
			.put(Species.CREEPER, Creature.MONSTER, MinepreggoModItems.BABY_QUADRUPED_CREEPER.get())
			.build();

	protected static final Table<Species, Creature, Item> DEAD_BABIES = ImmutableTable.<Species, Creature, Item>builder()
			.put(Species.HUMAN, Creature.HUMANOID, MinepreggoModItems.DEAD_HUMAN_FETUS.get())
			.put(Species.ZOMBIE, Creature.HUMANOID, MinepreggoModItems.DEAD_ZOMBIE_FETUS.get())
			.put(Species.CREEPER, Creature.HUMANOID, MinepreggoModItems.DEAD_HUMANOID_CREEPER_FETUS.get())
			.put(Species.CREEPER, Creature.MONSTER, MinepreggoModItems.DEAD_QUADRUPED_CREEPER_FETUS.get())
			.build();

	protected static final ImmutableMap<Craving, Float> CRAVING_WEIGHTS = ImmutableMap.of(		
			Craving.SWEET, 0.1F,
			Craving.SOUR, 0.25F,
			Craving.SPICY, 0.3F,
			Craving.SALTY, 0.35F
			);
	
	@CheckForNull
	public static ImmutableMap<Craving, List<Item>> getCravingMap(Species species) {
		if (species == null) {
			return null;
		}
		return CRAVING_ITEM.get(species);
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

	public static boolean isPregnantEntityValid(LivingEntity entity) {	
		boolean flag = false;
		if (entity instanceof ServerPlayer serverPlayer) {				
			var cap = serverPlayer.getCapability(MinepreggoCapabilities.PLAYER_DATA).resolve();		
			if (cap.isPresent()) {			
				var data = cap.get().getFemaleData().resolve();			
				if (data.isPresent()) {
					flag = data.get().isPregnant();
				}	
			}		
		}

		return flag || entity instanceof PreggoMob p && p instanceof IPregnancySystemHandler;
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
		final var cap = target.getCapability(MinepreggoCapabilities.PLAYER_DATA).resolve();
		
		if (cap.isEmpty() || cap.get().isMale()) {
			return false;
		}
		
		final var femaleData = cap.get().getFemaleData().resolve();
		
		if (femaleData.isEmpty() || !femaleData.get().isPregnant()) {
			return false;
		}
		
		final var pregnancyStage = femaleData.get().getPregnancySystem().getCurrentPregnancyStage();
			
		if (pregnancyStage == null || !canTouchBelly(source, target)) {
			return false;
		}

		if (!level.isClientSide) {			
			final var isFather = femaleData.get().getFather() != null && femaleData.get().getFather().equals(source.getUUID());
			
			ParticleOptions particle = isFather ? ParticleTypes.HEART : ParticleTypes.SMOKE;


			target.level().broadcastEntityEvent(target, (byte) 100);
			
			
			if (pregnancyStage.ordinal() > 2) {
				level.playSound(null, BlockPos.containing(target.getX(), target.getY(), target.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "belly_touch")), SoundSource.NEUTRAL, 0.75F, 1);			
				
				if (femaleData.get().getPregnancyEffects().getBellyRubs() >= BELLY_RUBBING_VALUE) {
					femaleData.get().getPregnancyEffects().decrementBellyRubs(isFather ? BELLY_RUBBING_VALUE : 1);
					femaleData.get().getPregnancyEffects().sync(target);			
				}
				else {
					particle = ParticleTypes.ANGRY_VILLAGER;
				}
			}
		
			ParticleHelper.spawnRandomlyFromServer(target, particle);
		}
		
		return true;
	}
	
    public static boolean isPregnancyEffect(MobEffect effect) { 	
    	Optional<Holder<MobEffect>> holder = ForgeRegistries.MOB_EFFECTS.getHolder(effect);
        return holder.isPresent() && holder.get().is(TagHelper.PREGNANCY_EFFECTS);
    }
    
	public static void syncNewMobEffect(LivingEntity entity, MobEffectInstance effect) {
		MinepreggoModPacketHandler.INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> entity),
				new SyncMobEffectPacket(entity.getId(), effect));
	}
	
	public static void syncRemovedMobEffect(LivingEntity entity, MobEffect effect) {
		MinepreggoModPacketHandler.INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> entity),
				new RemoveMobEffectPacket(entity.getId(), effect));
	}
	
	public static boolean canUseChestplate(Item armor, PregnancyPhase pregnancyPhase) {
		return canUseChestplate(armor, pregnancyPhase, true);
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
	
	public static boolean canUseChestplate(LivingEntity target, Item armor, PregnancyPhase pregnancyPhase, boolean considerBoobs) {	
		return canUseChestplate(armor, pregnancyPhase, considerBoobs) && !target.hasEffect(MinepreggoModMobEffects.LACTATION.get());
	}
	
	public static boolean canUseChestplate(LivingEntity target, Item armor, PregnancyPhase pregnancyPhase) {	
		return canUseChestplate(armor, pregnancyPhase, true) && !target.hasEffect(MinepreggoModMobEffects.LACTATION.get());
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
    	var map = h.getDaysByStageMapping();
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
    

    
}
