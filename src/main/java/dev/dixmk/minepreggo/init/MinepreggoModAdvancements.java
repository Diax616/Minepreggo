package dev.dixmk.minepreggo.init;

import dev.dixmk.minepreggo.advancements.critereon.BabyTradeTrigger;
import dev.dixmk.minepreggo.advancements.critereon.GetPregnantTrigger;
import dev.dixmk.minepreggo.advancements.critereon.GiveBirthTrigger;
import dev.dixmk.minepreggo.advancements.critereon.ImpregnateEntityTrigger;
import dev.dixmk.minepreggo.advancements.critereon.PrenantalCheckUpTrigger;
import net.minecraft.advancements.CriteriaTriggers;

public class MinepreggoModAdvancements {

	private MinepreggoModAdvancements() {}
	
	public static final BabyTradeTrigger BABY_TRADE_TRIGGER = new BabyTradeTrigger();
	public static final GetPregnantTrigger GET_PREGNANT_TRIGGER = new GetPregnantTrigger();
	public static final GiveBirthTrigger GIVE_BIRTH_TRIGGER = new GiveBirthTrigger();
	public static final ImpregnateEntityTrigger IMPREGNATE_ENTITY_TRIGGER = new ImpregnateEntityTrigger();
	public static final PrenantalCheckUpTrigger PRENATAL_CHECKUP_TRIGGER = new PrenantalCheckUpTrigger();
	
	public static void register() {
		CriteriaTriggers.register(BABY_TRADE_TRIGGER);
		CriteriaTriggers.register(GET_PREGNANT_TRIGGER);
		CriteriaTriggers.register(GIVE_BIRTH_TRIGGER);
		CriteriaTriggers.register(IMPREGNATE_ENTITY_TRIGGER);
		CriteriaTriggers.register(PRENATAL_CHECKUP_TRIGGER);
	}
}
