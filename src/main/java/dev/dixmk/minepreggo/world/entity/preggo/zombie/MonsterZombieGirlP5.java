package dev.dixmk.minepreggo.world.entity.preggo.zombie;

import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PlayMessages;

public class MonsterZombieGirlP5 extends AbstractMonsterPregnantZombieGirl {

	
	public MonsterZombieGirlP5(PlayMessages.SpawnEntity packet, Level world) {
		this(MinepreggoModEntities.MONSTER_ZOMBIE_GIRL_P5.get(), world);
	}

	public MonsterZombieGirlP5(EntityType<MonsterZombieGirlP5> type, Level world) {
		super(type, world, PregnancyPhase.P5);
		xpReward = 10;
		setNoAi(false);
		setMaxUpStep(0.6f);
	}

	public static AttributeSupplier.Builder createAttributes() {
		return getBasicAttributes(0.215);
	}
}
