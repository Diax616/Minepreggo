package dev.dixmk.minepreggo.world.entity.preggo.ender;

import dev.dixmk.minepreggo.world.entity.preggo.Creature;
import dev.dixmk.minepreggo.world.entity.preggo.Inventory;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public abstract class AbstractTamablePregnantMonsterEnderWoman extends AbstractTamablePregnantEnderWoman {

	protected AbstractTamablePregnantMonsterEnderWoman(EntityType<? extends AbstractTamableEnderWoman> p_32485_, Level p_32486_, PregnancyPhase currentPregnancyPhase) {
		super(p_32485_, p_32486_, Creature.MONSTER, currentPregnancyPhase);
	}

	@Override
	public double getMyRidingOffset() {
		return MonsterEnderWomanHelper.getMyRidingOffset();
	}
	
	@Override
	public void travel(Vec3 dir) {
		MonsterEnderWomanHelper.travelBeingPregnant(this, dir, super::travel);
	}
	
	@Override
	public boolean canTeleportWithOwner() {
		return MonsterEnderWomanHelper.canTeleportWithOwner(this);
	}
	
	@Override
	public boolean hurt(DamageSource damagesource, float amount) {		
		return MonsterEnderWomanHelper.hurt(this, damagesource, amount, super::hurt);
	}
	
	@Override
	protected Inventory createInventory() {
		return MonsterEnderWomanHelper.createInventory();
	}
	
	@Override
	public String getSimpleName() {
		return MonsterEnderWomanHelper.SIMPLE_NAME;
	}
}
