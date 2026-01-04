package dev.dixmk.minepreggo.network.capability;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.Nullable;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.network.packet.SyncPregnancySystemS2CPacket;
import dev.dixmk.minepreggo.world.entity.preggo.Species;
import dev.dixmk.minepreggo.world.pregnancy.MapPregnancyPhase;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPain;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySymptom;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import dev.dixmk.minepreggo.world.pregnancy.Womb;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.AnimationState;
import net.minecraftforge.network.PacketDistributor;

public class PlayerPregnancySystemImpl implements IPlayerPregnancySystemHandler {	
	
	public static final String NBT_KEY = "DataPlayerPregnancySystemImpl";
	
	private int daysToGiveBirth = 0;
	private int daysPassed = 0;
	private int pregnancyHealth = 0;
	private int pregnancyTimer = 0;
	private int pregnancyPainTimer = 0;

	private PregnancyPhase currentPregnancyPhase = PregnancyPhase.P0;
	private PregnancyPhase lastPregnancyPhase = PregnancyPhase.P4;
	
	private Optional<PregnancyPain> currentPregnancyPain = Optional.empty();
	private MapPregnancyPhase daysByPregnancyPhase = new MapPregnancyPhase(MinepreggoModConfig.getTotalPregnancyDays(), lastPregnancyPhase);
	private Womb babiesInsideWomb = Womb.empty();
	private byte pregnancySymptomsBitMask = (byte) 0;
	
	private Set<PregnancySymptom> cachePregnancySymptoms = null;
	
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
	public Set<PregnancySymptom> getPregnancySymptoms() {
		if (cachePregnancySymptoms == null) {
			cachePregnancySymptoms = PregnancySymptom.fromBitMask(this.pregnancySymptomsBitMask);
		}	
		return cachePregnancySymptoms;
	}

	@Override
	public boolean addPregnancySymptom(PregnancySymptom symptom) {	
		if ((this.pregnancySymptomsBitMask & symptom.flag) != 0) {
			return false;
		}	
			
		this.pregnancySymptomsBitMask |= symptom.flag;
		this.cachePregnancySymptoms = null;
		return true;
	}

	@Override
	public void setPregnancySymptoms(Set<PregnancySymptom> symptoms) {
		this.pregnancySymptomsBitMask = PregnancySymptom.toBitMask(symptoms);
		this.cachePregnancySymptoms = null;
	}

	@Override
	public boolean removePregnancySymptom(PregnancySymptom symptom) {	
		if ((this.pregnancySymptomsBitMask & symptom.flag) != 0) {
			this.pregnancySymptomsBitMask &= ~symptom.flag;
			this.cachePregnancySymptoms = null;
			return true;
		}	
		return false;
	}

	@Override
	public void clearPregnancySymptoms() {
		this.pregnancySymptomsBitMask = (byte) 0;
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

		if (this.pregnancySymptomsBitMask != (byte) 0) {
			nbt.putByte(PregnancySymptom.NBT_KEY, this.pregnancySymptomsBitMask);
		}
	
		currentPregnancyPain.ifPresent(pain -> nbt.putString(PregnancyPain.NBT_KEY, pain.name()));
	
		if(!babiesInsideWomb.isEmpty()) {
			nbt.put("DataBabies", this.babiesInsideWomb.toNBT());
		}
		if (!daysByPregnancyPhase.isEmpty()) {
			nbt.put("DaysByPhase", this.daysByPregnancyPhase.toNBT());
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
	    
	    if (nbt.contains(PregnancySymptom.NBT_KEY, Tag.TAG_BYTE)) {
	    	this.pregnancySymptomsBitMask = nbt.getByte(PregnancySymptom.NBT_KEY);
	    } 	 
	    
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
	}

	@NonNull
	public ClientData createClientData() {
		return new ClientData(
				this.currentPregnancyPhase,
				this.currentPregnancyPain.orElse(null),
				this.pregnancySymptomsBitMask);
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
	
	public void sync(ServerPlayer serverPlayer) {
		MinepreggoModPacketHandler.INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> serverPlayer), 
				new SyncPregnancySystemS2CPacket(serverPlayer.getUUID(), createClientData()));
	}
	
	public static record ClientData(PregnancyPhase currentPregnancyPhase, @Nullable PregnancyPain pregnancyPain, byte pregnancySymptoms) {
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
		
		public static ClientData decode(FriendlyByteBuf buffer) {
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
			return new ClientData(currentPregnancyPhase, pregnancyPain, pregnancySymptom);
		}
	}
}
