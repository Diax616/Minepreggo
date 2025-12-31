package dev.dixmk.minepreggo.network.capability;

import java.util.Optional;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.Nullable;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.MinepreggoModPacketHandler;
import dev.dixmk.minepreggo.network.packet.SyncPregnancyEffectsS2CPacket;
import dev.dixmk.minepreggo.world.entity.preggo.Species;
import dev.dixmk.minepreggo.world.item.IItemCraving;
import dev.dixmk.minepreggo.world.pregnancy.Craving;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.item.Item;

import net.minecraftforge.network.PacketDistributor;

public class PlayerPregnancyEffectsImpl implements IPlayerPregnancyEffectsHandler {
	
	public static final String NBT_KEY = "DataPlayerPregnancyEffectsImpl";
	
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
			
		if (!(items instanceof IItemCraving)) return false;
		
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
	public @Nullable ImmutablePair<Craving, Species> getTypeOfCravingBySpecies() {
		return this.typeOfCraving.orElse(null);
	}

	@Override
	public void setTypeOfCravingBySpecies(@Nullable ImmutablePair<Craving, Species> craving) {
		this.typeOfCraving = Optional.ofNullable(craving);
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
		
	public CompoundTag serializeNBT() {
		CompoundTag wrapper = new CompoundTag();
		CompoundTag nbt = new CompoundTag();
		
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
			return;
		}
		
		CompoundTag nbt = wrapper.getCompound(NBT_KEY);
		
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
	
	public @NonNull ClientData createClientData() {
		return new ClientData(
				this.craving,
				this.milking,
				this.bellyRubs,
				this.horny,
				this.getTypeOfCravingBySpecies());
	}
	
	
	public void sync(ServerPlayer serverPlayer) {
	    serverPlayer.getServer().execute(() -> 
	        MinepreggoModPacketHandler.INSTANCE.send(
	            PacketDistributor.PLAYER.with(() -> serverPlayer), 
	            new SyncPregnancyEffectsS2CPacket(serverPlayer.getUUID(), createClientData()))
	    );
	}

	public static record ClientData(int craving, int milking, int bellyRubs, int horny, @Nullable ImmutablePair<Craving, Species> typeOfCravingBySpecies) {	
		public static ClientData decode(FriendlyByteBuf buffer) {		
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
			return new ClientData(craving, milking, bellyRubs, horny, ImmutablePair.of(typeOfCraving, typeOfSpecies));
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
