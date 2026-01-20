package dev.dixmk.minepreggo.network.capability;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.Nullable;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.network.packet.s2c.SyncPregnancyEffectsS2CPacket;
import dev.dixmk.minepreggo.network.packet.s2c.SyncPregnancySystemS2CPacket;
import dev.dixmk.minepreggo.world.entity.preggo.Species;
import dev.dixmk.minepreggo.world.pregnancy.Craving;
import dev.dixmk.minepreggo.world.pregnancy.MapPregnancyPhase;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPain;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySymptom;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import dev.dixmk.minepreggo.world.pregnancy.SetPregnancySymptom;
import dev.dixmk.minepreggo.world.pregnancy.Womb;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.item.Item;
import net.minecraftforge.network.PacketDistributor;

public class PlayerPregnancyDataImpl implements IPlayerPregnancyDataHandler {	
	
	public static final String NBT_KEY = "DataPlayerPregnancySystemImpl";
	
	private int daysToGiveBirth = 0;
	private int daysPassed = 0;
	private int pregnancyHealth = 0;
	private int pregnancyTimer = 0;
	private int pregnancyPainTimer = 0;

	private PregnancyPhase currentPregnancyPhase = PregnancyPhase.P0;
	private PregnancyPhase lastPregnancyPhase = PregnancyPhase.P4;
	
	private Optional<PregnancyPain> currentPregnancyPain = Optional.empty();
	private MapPregnancyPhase daysByPregnancyPhase = new MapPregnancyPhase(PregnancySystemHelper.DEFAULT_TOTAL_PREGNANCY_DAYS, lastPregnancyPhase);
	private Womb babiesInsideWomb = Womb.empty();
	private SetPregnancySymptom setPregnancySymptom = new SetPregnancySymptom();
	
	private int cravingTimer = 0;
	private int milkingTimer = 0;
	private int bellyRubsTimer = 0;
	private int hornyTimer = 0;
	
	private int craving = 0;
	private int milking = 0;
	private int bellyRubs = 0;
	private int horny = 0;
	
	// they do not need to be saved in a NBT
	private int numOfJumps = 0;
	private int sprintingTimer = 0;
	private int sneakingTimer = 0;
	
	private Optional<ImmutablePair<Craving, Species>> typeOfCraving = Optional.empty();
	
	public final AnimationState bellyAnimationState = new AnimationState();
	
	@Override
	public int getDaysByCurrentStage() {	
		if (daysByPregnancyPhase.containsPregnancyPhase(currentPregnancyPhase)) {
			return daysByPregnancyPhase.getDaysByPregnancyPhase(currentPregnancyPhase);
		}
		MinepreggoMod.LOGGER.error("Could not get total days by pregnancy phase, current pregnancy phase: {}, map: {}", 
				currentPregnancyPhase, daysByPregnancyPhase.isEmpty());	
		return 0;
	}

	@Override
	public boolean setDaysByStage(int days, PregnancyPhase phase) {
		return daysByPregnancyPhase.modifyDaysByPregnancyPhase(phase, days);
	}
	
	@Override
	public void setMapPregnancyPhase(MapPregnancyPhase map) {
		this.daysByPregnancyPhase = map;
	}
	
	@Override
	public MapPregnancyPhase getMapPregnancyPhase() {
		return this.daysByPregnancyPhase;
	}
	
	@Override
	public int getPregnancyHealth() {
		return this.pregnancyHealth;
	}

	@Override
	public void setPregnancyHealth(int health) {
		this.pregnancyHealth = Mth.clamp(health, 0, PregnancySystemHelper.MAX_PREGNANCY_HEALTH);
	}

	@Override
	public int getDaysPassed() {
		return this.daysPassed;
	}

	@Override
	public void setDaysPassed(int days) {
		this.daysPassed = Math.max(days, 0);
	}

	@Override
	public int getTotalDaysOfPregnancy() {
		return daysByPregnancyPhase.getDaysValues().stream()
				.mapToInt(Integer::intValue)
				.sum();
	}
	
	@Override
	public int getDaysToGiveBirth() {
		return this.daysToGiveBirth;
	}

	@Override
	public void setDaysToGiveBirth(int days) {
		this.daysToGiveBirth = Math.max(days, 0);
	}

	@Override
	public void incrementDaysPassed() {
		this.daysPassed++;
	}

	@Override
	public void reduceDaysToGiveBirth() {
		setDaysToGiveBirth(daysToGiveBirth - 1);
	}
	
	@Override
	public int getPregnancyTimer() {
		return this.pregnancyTimer;
	}

	@Override
	public void setPregnancyTimer(int ticks) {
		this.pregnancyTimer = Math.max(ticks, 0);
	}
	
	@Override
	public void incrementPregnancyTimer() {
		++this.pregnancyTimer;
	}
	
	@Override
	public @Nullable PregnancyPhase getLastPregnancyStage() {
		return this.lastPregnancyPhase;
	}

	@Override
	public void setLastPregnancyStage(PregnancyPhase stage) {
		this.lastPregnancyPhase = stage;
	}

	@Override
	public PregnancyPhase getCurrentPregnancyStage() {
		return this.currentPregnancyPhase;
	}

	@Override
	public void setCurrentPregnancyStage(PregnancyPhase stage) {
		this.currentPregnancyPhase = stage;
	}

	@Override
	@Nullable
	public PregnancyPain getPregnancyPain() {
		return this.currentPregnancyPain.orElse(null);
	}

	@Override
	public void setPregnancyPain(@Nullable PregnancyPain pain) {
		this.currentPregnancyPain = Optional.ofNullable(pain);
	}

	@Override
	public void resetPregnancyTimer() {
		this.pregnancyTimer = 0;	
	}

	@Override
	public int getPregnancyPainTimer() {
		return this.pregnancyPainTimer;
	}

	@Override
	public void setPregnancyPainTimer(@NonNegative int ticks) {
		this.pregnancyPainTimer = Math.max(ticks, 0);
	}

	@Override
	public void incrementPregnancyPainTimer() {
		++this.pregnancyPainTimer;
	}

	@Override
	public void resetPregnancyPainTimer() {
		this.pregnancyPainTimer = 0;
	}

	@Override
	public void resetDaysPassed() {
		this.daysPassed = 0;	
	}

	@Override
	public void reducePregnancyHealth(int amount) {
		setPregnancyHealth(pregnancyHealth - amount);
	}

	@Override
	public void resetPregnancyHealth() {
		pregnancyHealth = 0;
	}

	@Override
	public void clearPregnancyPain() {
		currentPregnancyPain = Optional.empty();	
	}

	@Override
	public boolean isIncapacitated() {
		return currentPregnancyPain.isPresent();
	}	

	@Override
	public int getNumOfBabiesBySpecies(Species babyType) {
		return (int) this.babiesInsideWomb.stream()
				.filter(babyData -> babyData.typeOfSpecies == babyType)
				.count();
	}
	
	@Override
	public Set<Species> getBabiesBySpecies() {
		return this.babiesInsideWomb.stream()
				.map(baby -> baby.typeOfSpecies)
				.collect(Collectors.toUnmodifiableSet());
	}
	
	@Override
	public void setWomb(@NonNull Womb babiesInsideWomb) {
		this.babiesInsideWomb = babiesInsideWomb;
	}
	
	@Override
	public Womb getWomb() {
		return this.babiesInsideWomb;
	}
	
	@Override
	public SetPregnancySymptom getPregnancySymptoms() {
		return setPregnancySymptom;
	}

	@Override
	public void setPregnancySymptoms(SetPregnancySymptom symptoms) {
		this.setPregnancySymptom = symptoms;
	}

	@Override
	@Nullable
	public Craving getTypeOfCraving() {	
		if (typeOfCraving.isPresent()) {
			return typeOfCraving.get().getKey();
		}	
		return null;
	}

	@Override
	public void setTypeOfCraving(@Nullable Craving craving) {	
		if (craving != null) {
			this.typeOfCraving = Optional.of(ImmutablePair.of(craving, Species.HUMAN));
		} else {
			this.typeOfCraving = Optional.empty();
		}
	}
	
	@Override
	public boolean isValidCraving(Item item) {

		if (typeOfCraving.isEmpty()) return false;
		
		final var tOfCraving = typeOfCraving.get().getLeft();
		final var tOfSpecies = typeOfCraving.get().getRight();
		final var items = PregnancySystemHelper.getCravingItems(tOfSpecies, tOfCraving);
			
		if (items == null) {
			MinepreggoMod.LOGGER.error("Type of craving: {} for species: {} has no items registered!", tOfCraving, tOfSpecies);
			return false;
		}
		
		MinepreggoMod.LOGGER.debug("Type of craving: {} for species: {} has items: {}", tOfCraving, tOfSpecies, items);
		
		for (final var i : items) {
			if (item == i) {
				MinepreggoMod.LOGGER.debug("Checking craving item: {} against item: {}", item, i);					
				return true;
			}	
		}
		
		return false;
	}
	
	@Override
	public int getCraving() {
		return this.craving;
	}
	
	@Override
	public void setCraving(@NonNegative int craving) {
		this.craving = Mth.clamp(craving, 0, PregnancySystemHelper.MAX_CRAVING_LEVEL);
	}
	
	@Override
	public void incrementCraving() {
		++this.craving;
	}
	
	@Override
	public int getCravingTimer() {
		return this.cravingTimer;
	}
	
	@Override
	public void setCravingTimer(int timer) {
		this.cravingTimer = Math.max(0, timer);
	}
	
	@Override
	public void incrementCravingTimer() {
		++this.cravingTimer;
	}
	
	@Override
	public int getMilking() {
		return this.milking;
	}
	
	@Override
	public void setMilking(@NonNegative int milking) {
		this.milking = Mth.clamp(milking, 0, PregnancySystemHelper.MAX_MILKING_LEVEL);
	}
	
	@Override
	public void incrementMilking() {
		++this.milking;		
	}
	
	@Override
	public int getMilkingTimer() {
		return this.milkingTimer;
	}
	
	@Override
	public void setMilkingTimer(int timer) {
		this.milkingTimer = Math.max(0, timer);
		
	}
	
	@Override
	public void incrementMilkingTimer() {
		++this.milkingTimer;
	}
	
	@Override
	public int getBellyRubs() {
		return this.bellyRubs;
	}
	
	@Override
	public void setBellyRubs(int bellyRubs) {
		this.bellyRubs = Mth.clamp(bellyRubs, 0, PregnancySystemHelper.MAX_BELLY_RUBBING_LEVEL);
	}
	
	@Override
	public void incrementBellyRubs() {
		++this.bellyRubs;
	}
	
	@Override
	public int getBellyRubsTimer() {
		return this.bellyRubsTimer;
	}
	
	@Override
	public void setBellyRubsTimer(int timer) {
		this.bellyRubsTimer = Math.max(0, timer);
	}
	
	@Override
	public void incrementBellyRubsTimer() {
		++this.bellyRubsTimer;
	}
	
	@Override
	public int getHorny() {
		return this.horny;
	}
	
	@Override
	public void setHorny(int horny) {
		this.horny = Mth.clamp(horny, 0, PregnancySystemHelper.MAX_HORNY_LEVEL);
		
	}
	
	@Override
	public void incrementHorny() {
		++this.horny;
	}
	
	@Override
	public int getHornyTimer() {
		return this.hornyTimer;
	}
	
	@Override
	public void setHornyTimer(int timer) {
		this.hornyTimer = Math.max(0, timer);
		
	}
	
	@Override
	public void incrementHornyTimer() {
		++this.hornyTimer;	
	}
	
	@Override
	public void clearTypeOfCraving() {
		this.clearTypeOfCravingBySpecies();
	}

	@Override
	public void decrementCraving(int amount) {
		setCraving(craving - amount);
	}

	@Override
	public void resetCravingTimer() {
		this.cravingTimer = 0;
	}

	@Override
	public void decrementMilking(int amount) {
		setMilking(this.milking - amount);
	}

	@Override
	public void resetMilkingTimer() {
		this.milkingTimer = 0;
	}

	@Override
	public void decrementBellyRubs(int amount) {
		setBellyRubs(this.bellyRubs - amount);
	}

	@Override
	public void resetBellyRubsTimer() {
		this.bellyRubsTimer = 0;
	}

	@Override
	public void resetHornyTimer() {
		this.hornyTimer = 0;
	}

	
	@Override
	public void clearTypeOfCravingBySpecies() {
		this.typeOfCraving = Optional.empty();	
	}
	
	@Override
	public void incrementSprintingTimer() {
		this.sprintingTimer++;
	}

	@Override
	public void resetSprintingTimer() {
		this.sprintingTimer = 0;
	}

	@Override
	public int getSprintingTimer() {
		return this.sprintingTimer;
	}

	@Override
	public void incrementNumOfJumps() {
		this.numOfJumps++;
	}

	@Override
	public void resetNumOfJumps() {
		this.numOfJumps = 0;
	}

	@Override
	public int getNumOfJumps() {
		return this.numOfJumps;
	}

	@Override
	public int getSneakingTimer() {
		return this.sneakingTimer;
	}

	@Override
	public void resetSneakingTimer() {
		this.sneakingTimer = 0;
	}

	@Override
	public void incrementSneakingTimer() {
		this.sneakingTimer++;
	}
	
	@Override
	public @Nullable ImmutablePair<Craving, Species> getTypeOfCravingBySpecies() {
		return this.typeOfCraving.orElse(null);
	}

	@Override
	public void setTypeOfCravingBySpecies(@Nullable ImmutablePair<Craving, Species> craving) {
		this.typeOfCraving = Optional.ofNullable(craving);
	}
	
	public CompoundTag serializeNBT() {
		CompoundTag wrapper = new CompoundTag();
		CompoundTag nbt = new CompoundTag();
		
		nbt.putInt("DataDaysToGiveBirth", daysToGiveBirth);
		nbt.putInt("DataDaysPassed", daysPassed);
		nbt.putInt("DataPregnancyHealth", pregnancyHealth);
		nbt.putInt("DataPregnancyTimer", pregnancyTimer);
		
		nbt.putString(PregnancyPhase.CURRENT_PHASE_NBT_KEY, currentPregnancyPhase.name());
		nbt.putString(PregnancyPhase.LAST_PHASE_NBT_KEY, lastPregnancyPhase.name());

		nbt.putByte(PregnancySymptom.NBT_KEY, this.setPregnancySymptom.getBitmask());
	
		currentPregnancyPain.ifPresent(pain -> nbt.putString(PregnancyPain.NBT_KEY, pain.name()));
	
		if(!babiesInsideWomb.isEmpty()) {
			nbt.put("DataBabies", this.babiesInsideWomb.toNBT());
		}
		if (!daysByPregnancyPhase.isEmpty()) {
			nbt.put("DaysByPhase", this.daysByPregnancyPhase.toNBT());
		}
		
		nbt.putInt("DataCraving", craving);
		nbt.putInt("DataCravingTimer", cravingTimer);
		nbt.putInt("DataMilking", milking);
		nbt.putInt("DataMilkingTimer", milkingTimer);
		nbt.putInt("DataBellyRubs", bellyRubs);
		nbt.putInt("DataBellyRubsTimer", bellyRubsTimer);
		nbt.putInt("DataHorny", horny);
		nbt.putInt("DataHornyTimer", hornyTimer);
		
		if (typeOfCraving.isPresent()) {
			nbt.putString(Craving.NBT_KEY, typeOfCraving.get().getLeft().name());
			nbt.putString(Species.NBT_KEY, typeOfCraving.get().getRight().name());
		}	
		
		wrapper.put(NBT_KEY, nbt);
		
		return wrapper;
	}

	public void deserializeNBT(@NonNull Tag tag) {
		CompoundTag wrapper = (CompoundTag) tag;	
		if (!wrapper.contains(NBT_KEY, Tag.TAG_COMPOUND)) {
			MinepreggoMod.LOGGER.error("{} is not present in nbt", NBT_KEY);
			return;
		}
		
		CompoundTag nbt = wrapper.getCompound(NBT_KEY);	
		
		daysToGiveBirth = nbt.getInt("DataDaysToGiveBirth");
		daysPassed = nbt.getInt("DataDaysPassed");
		pregnancyHealth = nbt.getInt("DataPregnancyHealth");
		pregnancyTimer = nbt.getInt("DataPregnancyTimer");
			
	    if (nbt.contains(PregnancyPhase.CURRENT_PHASE_NBT_KEY, Tag.TAG_STRING)) {
	        setCurrentPregnancyStage(PregnancyPhase.valueOf(nbt.getString(PregnancyPhase.CURRENT_PHASE_NBT_KEY)));
	    }
	    if (nbt.contains(PregnancyPhase.LAST_PHASE_NBT_KEY, Tag.TAG_STRING)) {
	        setLastPregnancyStage(PregnancyPhase.valueOf(nbt.getString(PregnancyPhase.LAST_PHASE_NBT_KEY)));
	    }
	    if (nbt.contains(PregnancyPain.NBT_KEY, Tag.TAG_STRING)) {
	        setPregnancyPain(PregnancyPain.valueOf(nbt.getString(PregnancyPain.NBT_KEY)));
	    }   
	    
	    this.setPregnancySymptom.setBitMask(nbt.getByte(PregnancySymptom.NBT_KEY));
	      
	    if (nbt.contains("DataBabies", Tag.TAG_COMPOUND)) {
	    	this.babiesInsideWomb = Womb.fromNBT(nbt.getCompound("DataBabies"));
	    	if (this.babiesInsideWomb == null) {
	    		MinepreggoMod.LOGGER.error("Could not deserialize babies inside womb from nbt DataBabies");
	    	}
	    }  	
	    
	    if (nbt.contains("DaysByPhase", Tag.TAG_COMPOUND)) {
	    	this.daysByPregnancyPhase = MapPregnancyPhase.fromNBT(nbt.getCompound("DaysByPhase"));
	    	if (this.daysByPregnancyPhase == null) {
	    		MinepreggoMod.LOGGER.error("Could not deserialize days by pregnancy phase from nbt DaysByPhase");
	    	}
	    }  
	    
		craving = nbt.getInt("DataCraving");
		cravingTimer = nbt.getInt("DataCravingTimer");
		milking = nbt.getInt("DataMilking");
		milkingTimer = nbt.getInt("DataMilkingTimer");
		bellyRubs = nbt.getInt("DataBellyRubs");
		bellyRubsTimer = nbt.getInt("DataBellyRubsTimer");
		horny = nbt.getInt("DataHorny");
		hornyTimer = nbt.getInt("DataHornyTimer");	

	    if (nbt.contains(Craving.NBT_KEY, Tag.TAG_STRING) && nbt.contains(Species.NBT_KEY, Tag.TAG_STRING)) {
	        String cravingName = nbt.getString(Craving.NBT_KEY);
	        String speciesName = nbt.getString(Species.NBT_KEY);        
        	Craving c = Craving.valueOf(cravingName);
        	Species e = Species.valueOf(speciesName);
        	setTypeOfCravingBySpecies(ImmutablePair.of(c, e));
	    } else {
	    	setTypeOfCravingBySpecies(null);
	    }
	}
	
	public PlayerPregnancyDataImpl.PlayerStateData createPlayerStateData() {
		return new PlayerStateData(
				this.currentPregnancyPhase,
				this.currentPregnancyPain.orElse(null),
				this.setPregnancySymptom.getBitmask());
	}
	
	public PlayerPregnancyDataImpl.PlayerEffectData createPlayerEffectData() {
		return new PlayerEffectData(
				this.craving,
				this.milking,
				this.bellyRubs,
				this.horny,
				this.getTypeOfCravingBySpecies());
	}
	
	public PregnancySystemHelper.PrenatalRegularCheckUpData createRegularCheckUpData() {
		return new PregnancySystemHelper.PrenatalRegularCheckUpData(
				this.currentPregnancyPhase,
				this.lastPregnancyPhase,
				this.pregnancyHealth,
				this.babiesInsideWomb.getNumOfBabies(),
				this.daysPassed,
				PregnancySystemHelper.calculateDaysToNextPhase(this),
				this.daysToGiveBirth);	
	}
	
	public PregnancySystemHelper.PrenatalUltrasoundScanData createUltrasoundScanData() {
		return new PregnancySystemHelper.PrenatalUltrasoundScanData(
				Species.HUMAN,
				this.babiesInsideWomb);	
	}
	
	public void syncState(ServerPlayer serverPlayer) {
		MinepreggoModPacketHandler.INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> serverPlayer), 
				new SyncPregnancySystemS2CPacket(serverPlayer.getUUID(), createPlayerStateData()));
	}
	
	public void syncEffect(ServerPlayer serverPlayer) {
		MinepreggoModPacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> serverPlayer), 
				new SyncPregnancyEffectsS2CPacket(serverPlayer.getUUID(), createPlayerEffectData()));	
	}
	
	public static record PlayerStateData(PregnancyPhase currentPregnancyPhase, @Nullable PregnancyPain pregnancyPain, byte pregnancySymptoms) {
		public void encode(FriendlyByteBuf buffer) {
			buffer.writeBoolean(currentPregnancyPhase != null);
			if (currentPregnancyPhase != null) {
				buffer.writeEnum(currentPregnancyPhase);
			}	
			buffer.writeBoolean(pregnancyPain != null);
			if (pregnancyPain != null) {
				buffer.writeEnum(pregnancyPain);
			}	
			buffer.writeByte(pregnancySymptoms);
		}	
		
		public static PlayerStateData decode(FriendlyByteBuf buffer) {
			byte pregnancySymptom;
			PregnancyPain pregnancyPain = null;
			PregnancyPhase currentPregnancyPhase = null;		
			if (buffer.readBoolean()) {
				currentPregnancyPhase = buffer.readEnum(PregnancyPhase.class);
			}
			if (buffer.readBoolean()) {
				pregnancyPain = buffer.readEnum(PregnancyPain.class);
			}	
			pregnancySymptom = buffer.readByte();
			return new PlayerStateData(currentPregnancyPhase, pregnancyPain, pregnancySymptom);
		}
	}
	
	public static record PlayerEffectData(int craving, int milking, int bellyRubs, int horny, @Nullable ImmutablePair<Craving, Species> typeOfCravingBySpecies) {	
		public static PlayerEffectData decode(FriendlyByteBuf buffer) {		
			int craving = buffer.readInt();
			int milking = buffer.readInt();	
			int bellyRubs = buffer.readInt();
			int horny = buffer.readInt();
			Craving typeOfCraving = null;
			Species typeOfSpecies = null;		
			if (buffer.readBoolean()) {
				typeOfCraving = buffer.readEnum(Craving.class);
				typeOfSpecies = buffer.readEnum(Species.class);
			}		
			return new PlayerEffectData(craving, milking, bellyRubs, horny, ImmutablePair.of(typeOfCraving, typeOfSpecies));
		}
		
		public void encode(FriendlyByteBuf buffer) {	
			buffer.writeInt(this.craving);
			buffer.writeInt(this.milking);
			buffer.writeInt(this.bellyRubs);
			buffer.writeInt(this.horny);	
			buffer.writeBoolean(this.typeOfCravingBySpecies != null);	
			if (this.typeOfCravingBySpecies != null) {
				buffer.writeEnum(this.typeOfCravingBySpecies.getLeft());
				buffer.writeEnum(this.typeOfCravingBySpecies.getRight());
			}		
		}
	}
}
