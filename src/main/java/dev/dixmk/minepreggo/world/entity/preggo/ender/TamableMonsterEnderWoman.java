package dev.dixmk.minepreggo.world.entity.preggo.ender;

import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.world.entity.preggo.ITamablePreggoMobSystem;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobSystem;
import dev.dixmk.minepreggo.world.pregnancy.FemaleEntityImpl;
import dev.dixmk.minepreggo.world.pregnancy.IFemaleEntity;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class TamableMonsterEnderWoman extends AbstractTamableMonsterEnderWoman {

	public TamableMonsterEnderWoman(EntityType<TamableMonsterEnderWoman> p_32485_, Level p_32486_) {
		super(p_32485_, p_32486_);
	}

	/*
	public TamableMonsterEnderWoman(PlayMessages.SpawnEntity packet, Level world) {
		this(MinepreggoModEntities.TAMABLE_MONSTER_ENDER_WOMAN.get(), world);
	}
	*/
	
	@Override
	protected ITamablePreggoMobSystem createTamableSystem() {
		return new PreggoMobSystem<>(this, MinepreggoModConfig.SERVER.getTotalTicksOfHungryP0(), PregnancySystemHelper.TOTAL_TICKS_SEXUAL_APPETITE_P0);
	}

	@Override
	protected IFemaleEntity createFemaleEntity() {
		return new FemaleEntityImpl();
	}
}
