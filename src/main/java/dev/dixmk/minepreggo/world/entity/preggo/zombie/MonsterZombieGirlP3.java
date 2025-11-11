package dev.dixmk.minepreggo.world.entity.preggo.zombie;

import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancyStage;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PlayMessages;

public class MonsterZombieGirlP3 extends AbstractMonsterPregnantZombieGirl {

	
	public MonsterZombieGirlP3(PlayMessages.SpawnEntity packet, Level world) {
		this(MinepreggoModEntities.MONSTER_ZOMBIE_GIRL_P3.get(), world);
	}

	public MonsterZombieGirlP3(EntityType<MonsterZombieGirlP3> type, Level world) {
		super(type, world, PregnancyStage.P3);
		xpReward = 10;
		setNoAi(false);
		setMaxUpStep(0.6f);
	}

	public static AttributeSupplier.Builder createAttributes() {
		return getBasicAttributes(0.225);
	}
}
