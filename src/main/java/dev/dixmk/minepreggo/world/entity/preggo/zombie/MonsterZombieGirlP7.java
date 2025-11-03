package dev.dixmk.minepreggo.world.entity.preggo.zombie;

import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancyStage;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PlayMessages;

public class MonsterZombieGirlP7 extends AbstractMonsterPregnantZombieGirl {

	
	public MonsterZombieGirlP7(PlayMessages.SpawnEntity packet, Level world) {
		this(MinepreggoModEntities.MONSTER_ZOMBIE_GIRL_P7.get(), world);
	}

	public MonsterZombieGirlP7(EntityType<MonsterZombieGirlP7> type, Level world) {
		super(type, world, PregnancyStage.P7, PregnancyStage.P7);
		xpReward = 10;
		setNoAi(false);
		setMaxUpStep(0.6f);
	}

	public static AttributeSupplier.Builder createAttributes() {
		return getBasicAttributes(0.19);
	}
}
