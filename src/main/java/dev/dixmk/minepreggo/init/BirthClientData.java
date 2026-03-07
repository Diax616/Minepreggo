package dev.dixmk.minepreggo.init;

import dev.dixmk.minepreggo.world.entity.preggo.Creature;
import dev.dixmk.minepreggo.world.entity.preggo.Species;
import dev.dixmk.minepreggo.world.pregnancy.BabyData;
import net.minecraft.network.FriendlyByteBuf;

public class BirthClientData {
	private Species currentBabySpecies;
	private Creature currentBabyCreature;
	
	public BirthClientData(BabyData babyData) {
		this.currentBabySpecies = babyData.typeOfSpecies;
		this.currentBabyCreature = babyData.typeOfCreature;
	}
	
	public BirthClientData(FriendlyByteBuf buffer) throws IllegalStateException {
		this.read(buffer);
	}
	
	public Species getCurrentBabySpecies() {
		return currentBabySpecies;
	}
	
	public Creature getCurrentBabyCreature() {
		return currentBabyCreature;
	}
	
	public void write(FriendlyByteBuf buffer) {
		buffer.writeByte(2);
		buffer.writeUtf(currentBabySpecies.name(), 32);
		buffer.writeUtf(currentBabyCreature.name(), 32);
	}
	
	public void read(FriendlyByteBuf buffer) throws IllegalStateException {
		if (buffer.readByte() != 2) {
			throw new IllegalStateException("Invalid BirthClientData version");
		}
		this.currentBabySpecies = Species.valueOf(buffer.readUtf(32));
		this.currentBabyCreature = Creature.valueOf(buffer.readUtf(32));
	}
}
