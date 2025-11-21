package dev.dixmk.minepreggo.world.inventory.preggo;

import java.util.Optional;

import org.joml.Vector3i;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.init.MinepreggoModMenus;
import dev.dixmk.minepreggo.network.capability.IPregnancySystemHandler;
import dev.dixmk.minepreggo.world.entity.monster.ScientificIllager;
import dev.dixmk.minepreggo.world.entity.preggo.Baby;
import dev.dixmk.minepreggo.world.entity.preggo.IBreedable;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancyPhase;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Inventory;

public class PreggoMobMedicalCheckUpMenu extends AbstractMedicalCheckUpMenu<PreggoMob, ScientificIllager> {

	private final boolean valid;
	protected int totalNumOfBabies = -1;
	protected int daysToPassedNextPhase = -1;
	private final Optional<IPregnancySystemHandler> pregnancySystem;
	
	public PreggoMobMedicalCheckUpMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
		super(MinepreggoModMenus.PREGGO_MOB_MEDICAL_CHECKUP_MENU.get(), id, inv);	
		
		PreggoMob s = null;	
		IPregnancySystemHandler ps = null;
		ScientificIllager t = null;
		Vector3i p = null;
		
		if (extraData != null) {
			var pos = extraData.readBlockPos();			
			p = new Vector3i(pos.getX(), pos.getY(), pos.getZ());
		
			if (level.getEntity(extraData.readVarInt()) instanceof PreggoMob preggoMob)  {
				s = preggoMob;			
				if (preggoMob instanceof IPregnancySystemHandler pregSystem) {
					ps = pregSystem;
				}		
			}	
				
			if (level.getEntity(extraData.readVarInt()) instanceof ScientificIllager scientificIllager) {
				t = scientificIllager;
			}
		}		
		
		this.target = Optional.ofNullable(t);
		this.source = Optional.ofNullable(s);	
		this.pregnancySystem = Optional.ofNullable(ps);
		this.pos = Optional.ofNullable(p);
		this.numOfEmerald = 30;
		
		this.valid = this.source.isPresent() && this.pregnancySystem.isPresent() && this.target.isPresent();	
		
		if (!isValid()) {
			MinepreggoMod.LOGGER.error("Target={} or Source={} or PregnancySystem={} was null",
					this.source.isPresent(), this.target.isPresent(), this.pregnancySystem.isPresent());
		}
		else {
			totalNumOfBabies = IBreedable.getOffspringsByMaxPregnancyStage(this.pregnancySystem.get().getLastPregnancyStage());
			daysToPassedNextPhase = this.pregnancySystem.get().getDaysByStage() - this.pregnancySystem.get().getDaysPassed();
		}
	}
	
	@Override
	protected void onSuccessful() {	
		if (this.level.isClientSide && this.pos.isPresent()) {
			this.level.playLocalSound(this.pos.get().x, this.pos.get().y, this.pos.get().z, SoundEvents.VINDICATOR_CELEBRATE, SoundSource.AMBIENT, 1, 1, false);				
		}
	}

	@Override
	protected void onFailure() {
		if (this.level.isClientSide && this.pos.isPresent()) {
			this.level.playLocalSound(this.pos.get().x, this.pos.get().y, this.pos.get().z, SoundEvents.VINDICATOR_AMBIENT, SoundSource.AMBIENT, 1, 1, false);				
		}
	}

	@Override
	public String getName() {		
		return this.valid ? this.source.get().getSimpleName() : "null";
	}

	@Override
	public PregnancyPhase getCurrentPregnancyPhase() {
		return this.valid ? this.pregnancySystem.get().getCurrentPregnancyStage() :  PregnancyPhase.P0;
	}

	@Override
	public PregnancyPhase getLastPregnancyPhase() {
		return this.isValid() ? this.pregnancySystem.get().getLastPregnancyStage() : PregnancyPhase.P0;
	}
	
	@Override
	public int getPregnancyHealth() {
		return this.valid ? this.pregnancySystem.get().getPregnancyHealth() : -1;
	}

	@Override
	public int getTotalNumOfBabies() {
		return totalNumOfBabies;
	}

	@Override
	public Baby getTypeOfBaby() {
		return this.valid ? this.pregnancySystem.get().getDefaultTypeOfBaby() : null;
	}

	@Override
	public int getDaysToGiveBirth() {
		return this.valid ? this.pregnancySystem.get().getDaysToGiveBirth() : -1;
	}

	@Override
	public int getDaysPassed() {
		return this.valid ? this.pregnancySystem.get().getDaysPassed() : -1;
	}

	@Override
	public int getDaysToAdvanceNextPhase() {
		return daysToPassedNextPhase;
	}

	@Override
	public boolean isValid() {
		return this.valid;
	}
}