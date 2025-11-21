package dev.dixmk.minepreggo.world.entity.preggo;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnegative;

import org.apache.commons.lang3.tuple.Pair;
import org.checkerframework.checker.nullness.qual.NonNull;

import com.google.common.collect.ImmutableMap;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.common.utils.ParticleHelper;
import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.init.MinepreggoModItems;
import dev.dixmk.minepreggo.network.capability.IPregnancySystemHandler;
import dev.dixmk.minepreggo.network.packet.RemoveMobEffectPacket;
import dev.dixmk.minepreggo.network.packet.SyncMobEffectPacket;
import dev.dixmk.minepreggo.utils.TagHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
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
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.registries.ForgeRegistries;

public class PregnancySystemHelper {
	
	private PregnancySystemHelper() {}
	
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
	public static final int DESACTIVATE_FULL_BELLY_RUBS_STAGE = 4;
	
	public static final int ACTIVATE_HORNY_SYMPTOM = 17;
		
	public static final int NATURAL_PREGNANCY_INITIALIZER_TIME = 10000;
	
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
	
	
	public static Craving getRandomCraving(RandomSource randomSource) {		
		final var p = randomSource.nextFloat();	
		if (p < 0.05F) {
			return Craving.SWEET;
		}
		else if (p < 0.35F) {
			return Craving.SALTY;
		}
		else if (p < 0.65F) {
			return Craving.SPICY;
		}
		else {
			return Craving.SOUR;
		}
	}
	
	protected static final ImmutableMap<Baby, Item> ALIVE_BABIES = ImmutableMap.of(
			Baby.HUMAN, MinepreggoModItems.BABY_HUMAN.get(), 
			Baby.ZOMBIE, MinepreggoModItems.BABY_ZOMBIE.get(), 
			Baby.HUMANOID_CREEPER, MinepreggoModItems.BABY_HUMANOID_CREEPER.get(),
			Baby.CREEPER, MinepreggoModItems.BABY_QUADRUPED_CREEPER.get());

	protected static final ImmutableMap<Baby, Item> DEAD_BABIES = ImmutableMap.of(
			Baby.HUMAN, MinepreggoModItems.DEAD_HUMAN_FETUS.get(), 
			Baby.ZOMBIE, MinepreggoModItems.DEAD_ZOMBIE_FETUS.get(), 
			Baby.HUMANOID_CREEPER, MinepreggoModItems.DEAD_HUMANOID_CREEPER_FETUS.get(),
			Baby.CREEPER, MinepreggoModItems.DEAD_QUADRUPED_CREEPER_FETUS.get());

	@CheckForNull
	public static Item getDeadBabyItem(Baby babyType) {
		if (babyType == null) {
			return null;
		}		
		return DEAD_BABIES.get(babyType);
	}
	
	@CheckForNull
	public static Item getAliveBabyItem(Baby babyType) {
		if (babyType == null) {
			return null;
		}	
		return ALIVE_BABIES.get(babyType);
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
	
	protected static final ImmutableMap<PregnancyPhase, List<Pair<PregnancyPhase, Float>>> PREGNANCY_PHASES_WEIGHTS = ImmutableMap.of(
			PregnancyPhase.P4, List.of(
					Pair.of(PregnancyPhase.P0, 0.05F),
					Pair.of(PregnancyPhase.P1, 0.2F),
					Pair.of(PregnancyPhase.P2, 0.2F),
					Pair.of(PregnancyPhase.P3, 0.25F),
					Pair.of(PregnancyPhase.P4, 0.3F)),		
			PregnancyPhase.P5, List.of(
					Pair.of(PregnancyPhase.P0, 0.05F),
					Pair.of(PregnancyPhase.P1, 0.1F),
					Pair.of(PregnancyPhase.P2, 0.2F),
					Pair.of(PregnancyPhase.P3, 0.2F),
					Pair.of(PregnancyPhase.P4, 0.2F),
					Pair.of(PregnancyPhase.P5, 0.25F)),	
			PregnancyPhase.P6, List.of(
					Pair.of(PregnancyPhase.P0, 0.05F),
					Pair.of(PregnancyPhase.P1, 0.1F),
					Pair.of(PregnancyPhase.P2, 0.1F),
					Pair.of(PregnancyPhase.P3, 0.1F),
					Pair.of(PregnancyPhase.P4, 0.2F),
					Pair.of(PregnancyPhase.P5, 0.2F),
					Pair.of(PregnancyPhase.P6, 0.25F)),	
			PregnancyPhase.P7, List.of(
					Pair.of(PregnancyPhase.P0, 0.05F),
					Pair.of(PregnancyPhase.P1, 0.1F),
					Pair.of(PregnancyPhase.P2, 0.1F),
					Pair.of(PregnancyPhase.P3, 0.1F),
					Pair.of(PregnancyPhase.P4, 0.15F),
					Pair.of(PregnancyPhase.P5, 0.15F),
					Pair.of(PregnancyPhase.P6, 0.15F),
					Pair.of(PregnancyPhase.P7, 0.2F)),
			PregnancyPhase.P8, List.of(
					Pair.of(PregnancyPhase.P0, 0.05F),
					Pair.of(PregnancyPhase.P1, 0.1F),
					Pair.of(PregnancyPhase.P2, 0.1F),
					Pair.of(PregnancyPhase.P3, 0.1F),
					Pair.of(PregnancyPhase.P4, 0.1F),
					Pair.of(PregnancyPhase.P5, 0.1F),
					Pair.of(PregnancyPhase.P6, 0.15F),
					Pair.of(PregnancyPhase.P7, 0.15F),
					Pair.of(PregnancyPhase.P8, 0.15F))
			);
	
	
	public static Map<PregnancyPhase, @NonNull Integer> createDaysByPregnancyPhase(@Nonnegative int totalDays, PregnancyPhase lastPregnancyPhase) {
	
		PregnancyPhase last = lastPregnancyPhase;
		
		if (last.ordinal() < 4) {
			last = PregnancyPhase.P4;
		}
		
		final var weights = PREGNANCY_PHASES_WEIGHTS.get(last);
		
		Map<PregnancyPhase, @NonNull Integer> daysByPregnancyPhase = new EnumMap<>(PregnancyPhase.class);
		int total = 0;
		for (final var pair : weights) {		
			int floor = Math.round(totalDays * pair.getRight());		
			daysByPregnancyPhase.put(pair.getLeft(), floor);
			total += floor;
		}
		final int rest = totalDays - total;
				
		if (rest > 0) {
			daysByPregnancyPhase.computeIfPresent(last, (key, value) -> value + rest);
		}
		
		return daysByPregnancyPhase;
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
	
	public static ListTag serializePregnancyPhaseMap(Map<PregnancyPhase, Integer> map) {
		ListTag list = new ListTag();
		map.forEach((key, value) -> {		
			CompoundTag pair = new CompoundTag();
		    pair.putString("pregnancyPhase", key.name());
		    pair.putInt("days", value);
			list.add(pair);
		});
		return list;
	}
	
	public static void deserializePregnancyPhaseMap(ListTag list, Map<PregnancyPhase, Integer> map) {
	    for (var t : list) {
	        CompoundTag pair = (CompoundTag) t;
	        PregnancyPhase key = PregnancyPhase.valueOf(pair.getString("pregnancyPhase"));
	        int value = pair.getInt("days");
	        map.put(key, value);
	    }
	}
	
	public static ListTag serializeBabyMap(Map<Baby, Integer> map) {
		ListTag list = new ListTag();
		map.forEach((key, value) -> {		
			CompoundTag pair = new CompoundTag();
		    pair.putString("typeOfBaby", key.name());
		    pair.putInt("size", value);
			list.add(pair);
		});
		return list;
	}
	
	public static void deserializeBabyMap(ListTag list, Map<Baby, Integer> map) {
	    for (var t : list) {
	        CompoundTag pair = (CompoundTag) t;
	        Baby key = Baby.valueOf(pair.getString("typeOfBaby"));
	        int value = pair.getInt("size");
	        map.put(key, value);
	    }
	}
	

}
