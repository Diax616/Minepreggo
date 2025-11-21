package dev.dixmk.minepreggo.network.capability;

import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.Nullable;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.network.packet.SyncPregnancySystemS2CPacket;
import dev.dixmk.minepreggo.world.entity.preggo.Baby;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancyPain;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancyPhase;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancySymptom;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancySystemHelper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraftforge.network.PacketDistributor;

public class PlayerPregnancySystemImpl implements IPlayerPregnancySystemHandler {	
	private int daysToGiveBirth = 0;
	private int daysPassed = 0;
	private int pregnancyHealth = 0;
	private int pregnancyTimer = 0;
	private int pregnancyPainTimer = 0;
	private int numOfJumps = 0;
	private int sprintingTimer = 0;
	
	private Optional<PregnancyPhase> currentPregnancyPhase = Optional.empty();
	private Optional<PregnancyPhase> lastPregnancyPhase = Optional.empty();
		
	private EnumMap<Baby, @NonNull Integer> babiesInsideWomb = new EnumMap<>(Baby.class);

	private Optional<PregnancySymptom> currentPregnancySymptom = Optional.empty();
	private Optional<PregnancyPain> currentPregnancyPain = Optional.empty();
	
	private Map<PregnancyPhase, @NonNull Integer> daysByPregnancyPhase = new EnumMap<>(PregnancyPhase.class);


	@Override
	public int getDaysByStage() {	
		if (currentPregnancyPhase.isPresent() && daysByPregnancyPhase.containsKey(currentPregnancyPhase.get())) {
			return daysByPregnancyPhase.get(currentPregnancyPhase.get());
		}
		MinepreggoMod.LOGGER.error("Could not get total days by pregnancy phase, current pregnancy phase: {}, map: {}", 
				currentPregnancyPhase.isPresent(), daysByPregnancyPhase.isEmpty());
		return -1;
	}

	@Override
	public boolean setDaysByStage(int days, PregnancyPhase phase) {
		if (!this.daysByPregnancyPhase.isEmpty()) {	
			daysByPregnancyPhase.put(phase, days);	
			return true;
		}
		return false;
	}
	
	@Override
	public boolean setDaysByStage(Map<PregnancyPhase, Integer> map) {
		if (this.daysByPregnancyPhase.isEmpty()) {
			this.daysByPregnancyPhase = map;
			return true;
		}
		return false;
	}
	
	@Override
	public Map<PregnancyPhase, Integer> getDaysByStageMapping() {
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
		return this.lastPregnancyPhase.orElse(null);
	}

	@Override
	public void setLastPregnancyStage(@Nullable PregnancyPhase stage) {
		this.lastPregnancyPhase = Optional.ofNullable(stage);
	}

	@Override
	public @Nullable PregnancyPhase getCurrentPregnancyStage() {
		return this.currentPregnancyPhase.orElse(null);
	}

	@Override
	public void setCurrentPregnancyStage(@Nullable PregnancyPhase stage) {
		this.currentPregnancyPhase = Optional.ofNullable(stage);
	}

	@Override
	@Nullable
	public PregnancySymptom getPregnancySymptom() {
		return this.currentPregnancySymptom.orElse(null);
	}

	@Override
	public void setPregnancySymptom(@Nullable PregnancySymptom symptom) {
		this.currentPregnancySymptom = Optional.ofNullable(symptom);
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
	public void clearPregnancySymptom() {
		currentPregnancySymptom = Optional.empty();
	}

	@Override
	public void clearPregnancyPain() {
		currentPregnancyPain = Optional.empty();	
	}

	@Override
	public Baby getDefaultTypeOfBaby() {
		return Baby.HUMAN;
	}

	@Override
	public boolean isIncapacitated() {
		return currentPregnancyPain.isPresent();
	}	
	
	@Override
	public Set<Baby> getTypesOfBabies() {
		return this.babiesInsideWomb.keySet();
	}

	@Override
	public int getNumOfBabiesByType(Baby babyType) {
		return this.babiesInsideWomb.get(babyType);
	}

	@Override
	public int getTotalNumOfBabies() {	
		return this.babiesInsideWomb.values().stream().mapToInt(Integer::intValue).sum();
	}

	@Override
	public void addBaby(Baby babyType, int count) {	
		this.babiesInsideWomb.compute(babyType, (k, v) -> (v == null) ? count : v + count);	
	}
	
	
	
	@NonNull
	public Tag serializeNBT(@NonNull Tag tag) {
		CompoundTag nbt = (CompoundTag) tag;
		nbt.putInt("DataDaysToGiveBirth", daysToGiveBirth);
		nbt.putInt("DataDaysPassed", daysPassed);
		nbt.putInt("DataPregnancyHealth", pregnancyHealth);
		nbt.putInt("DataPregnancyTimer", pregnancyTimer);
		
		currentPregnancyPhase.ifPresent(phase -> nbt.putString(PregnancyPhase.CURRENT_PHASE_NBT_KEY, phase.name()));	
		lastPregnancyPhase.ifPresent(phase -> nbt.putString(PregnancyPhase.LAST_PHASE_NBT_KEY, phase.name()));

		currentPregnancySymptom.ifPresent(symptom -> nbt.putString(PregnancySymptom.NBT_KEY, symptom.name()));
		currentPregnancyPain.ifPresent(pain -> nbt.putString(PregnancyPain.NBT_KEY, pain.name()));
	
		if(!babiesInsideWomb.isEmpty()) {
			nbt.put("DataBabies", PregnancySystemHelper.serializeBabyMap(this.babiesInsideWomb));
		}
		
		if (!daysByPregnancyPhase.isEmpty()) {
			nbt.put("DaysByPhase", PregnancySystemHelper.serializePregnancyPhaseMap(this.daysByPregnancyPhase));
		}

		return nbt;
	}

	public void deserializeNBT(@NonNull Tag tag) {
		CompoundTag nbt = (CompoundTag) tag;
		daysToGiveBirth = nbt.getInt("DataDaysToGiveBirth");
		daysPassed = nbt.getInt("DataDaysPassed");
		pregnancyHealth = nbt.getInt("DataPregnancyHealth");
		pregnancyTimer = nbt.getInt("DataPregnancyTimer");
			
	    if (nbt.contains(PregnancyPhase.CURRENT_PHASE_NBT_KEY, Tag.TAG_STRING)) {
	        String name = nbt.getString(PregnancyPhase.CURRENT_PHASE_NBT_KEY);
	        setCurrentPregnancyStage(PregnancyPhase.valueOf(name));
	    }
	    if (nbt.contains(PregnancyPhase.LAST_PHASE_NBT_KEY, Tag.TAG_STRING)) {
	        String name = nbt.getString(PregnancyPhase.LAST_PHASE_NBT_KEY);
	        setLastPregnancyStage(PregnancyPhase.valueOf(name));
	    }
	    if (nbt.contains(PregnancyPain.NBT_KEY, Tag.TAG_STRING)) {
	        String name = nbt.getString(PregnancyPain.NBT_KEY);
	        PregnancyPain pain = PregnancyPain.valueOf(name);
	        setPregnancyPain(pain);
	    }    
	    if (nbt.contains(PregnancySymptom.NBT_KEY, Tag.TAG_STRING)) {
	        String name = nbt.getString(PregnancySymptom.NBT_KEY);
			PregnancySymptom symptom = PregnancySymptom.valueOf(name);
			setPregnancySymptom(symptom);
	    } 	  
	    if (nbt.contains("DataBabies")) {
	    	PregnancySystemHelper.deserializeBabyMap(nbt.getList("DataBabies", Tag.TAG_COMPOUND), babiesInsideWomb);
	    }  	
	    
	    if (nbt.contains("DaysByPhase")) {
	    	PregnancySystemHelper.deserializePregnancyPhaseMap(nbt.getList("DaysByPhase", Tag.TAG_COMPOUND), daysByPregnancyPhase);
	    }  
	}

	public void copyFrom(@NonNull PlayerPregnancySystemImpl newData) {		
		// this is not a COPY, it is a transference
		this.babiesInsideWomb = newData.babiesInsideWomb;
		this.daysByPregnancyPhase = newData.daysByPregnancyPhase;
		
		this.currentPregnancyPain = Optional.ofNullable(newData.currentPregnancyPain.orElse(null));
		this.currentPregnancyPhase = Optional.ofNullable(newData.currentPregnancyPhase.orElse(null));
		this.currentPregnancySymptom = Optional.ofNullable(newData.currentPregnancySymptom.orElse(null));
		this.lastPregnancyPhase = Optional.ofNullable(newData.lastPregnancyPhase.orElse(null));
		this.daysPassed = newData.daysPassed;
		this.daysToGiveBirth = newData.daysToGiveBirth;
		this.numOfJumps = newData.numOfJumps;
		this.pregnancyHealth = newData.pregnancyHealth;
		this.pregnancyPainTimer = newData.pregnancyPainTimer;
		this.pregnancyTimer = newData.pregnancyTimer;
		this.sprintingTimer = newData.sprintingTimer;
	}
	
	@NonNull
	public ScreenData createScreenData() {
		return new ScreenData(
				this.currentPregnancyPhase.orElse(PregnancyPhase.P0),
				this.lastPregnancyPhase.orElse(PregnancyPhase.P4),
				this.daysToGiveBirth,
				this.daysPassed,
				getDaysByStage(),
				this.pregnancyHealth,
				this.babiesInsideWomb);
	}
	
	@NonNull
	public ClientData createClientData() {
		return new ClientData(
				this.currentPregnancyPhase.orElse(null),
				this.currentPregnancyPain.orElse(null),
				this.currentPregnancySymptom.orElse(null));
	}
	
	
	public void sync(ServerPlayer serverPlayer) {
		MinepreggoModPacketHandler.INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> serverPlayer), 
				new SyncPregnancySystemS2CPacket(serverPlayer.getUUID(), createClientData()));
	}
	
	public static record ClientData(@Nullable PregnancyPhase currentPregnancyPhase, @Nullable PregnancyPain pregnancyPain, @Nullable PregnancySymptom pregnancySymptom) {
		public void encode(FriendlyByteBuf buffer) {
			buffer.writeBoolean(currentPregnancyPhase != null);
			if (currentPregnancyPhase != null) {
				buffer.writeEnum(currentPregnancyPhase);
			}	
			buffer.writeBoolean(pregnancyPain != null);
			if (pregnancyPain != null) {
				buffer.writeEnum(pregnancyPain);
			}	
			buffer.writeBoolean(pregnancySymptom != null);
			if (pregnancySymptom != null) {
				buffer.writeEnum(pregnancySymptom);
			}
		}	
		
		public static ClientData decode(FriendlyByteBuf buffer) {
			PregnancySymptom pregnancySymptom = null;
			PregnancyPain pregnancyPain = null;
			PregnancyPhase currentPregnancyPhase = null;		
			if (buffer.readBoolean()) {
				currentPregnancyPhase = buffer.readEnum(PregnancyPhase.class);
			}
			if (buffer.readBoolean()) {
				pregnancyPain = buffer.readEnum(PregnancyPain.class);
			}	
			if (buffer.readBoolean()) {
				pregnancySymptom = buffer.readEnum(PregnancySymptom.class);
			}
			return new ClientData(currentPregnancyPhase, pregnancyPain, pregnancySymptom);
		}
	}
	
	
	public static record ScreenData(
			PregnancyPhase currentPregnancyPhase,
			PregnancyPhase lastPregnancyPhase,
			int daysToGiveBirth,
			int daysPassed,
			int daysByCurrentPhase,
			int pregnancyHealth,
			Map<Baby, @NonNull Integer> babiesInsideWomb) {	
		
		public void encode(FriendlyByteBuf buffer) {
			buffer.writeEnum(this.currentPregnancyPhase);
			buffer.writeEnum(this.lastPregnancyPhase);
			buffer.writeInt(this.daysToGiveBirth);
			buffer.writeInt(this.daysPassed);
			buffer.writeInt(this.daysByCurrentPhase);
			buffer.writeInt(this.pregnancyHealth);
			var nbt = new CompoundTag();
			nbt.put("babiesInsideBelly", PregnancySystemHelper.serializeBabyMap(this.babiesInsideWomb));		
			buffer.writeNbt(nbt);
		}
			
		public static ScreenData decode(FriendlyByteBuf buffer) {
			PregnancyPhase currentPregnancyPhase = buffer.readEnum(PregnancyPhase.class);
			PregnancyPhase lastPregnancyPhase = buffer.readEnum(PregnancyPhase.class);
			int daysToGiveBirth = buffer.readInt();
			int daysPassed = buffer.readInt();
			int daysByCurrentStage = buffer.readInt();
			int pregnancyHealth = buffer.readInt();
			Map<Baby, @NonNull Integer> babiesInsideBelly = new EnumMap<>(Baby.class); 				
			CompoundTag nbt = buffer.readNbt(); 
			var list = nbt.getList("babiesInsideBelly", Tag.TAG_COMPOUND);
			PregnancySystemHelper.deserializeBabyMap(list, babiesInsideBelly);		
			return new ScreenData(currentPregnancyPhase, lastPregnancyPhase, daysToGiveBirth, daysPassed, daysByCurrentStage, pregnancyHealth, babiesInsideBelly);
		}
	}
}
