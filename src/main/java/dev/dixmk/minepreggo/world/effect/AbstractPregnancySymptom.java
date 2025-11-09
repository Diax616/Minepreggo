package dev.dixmk.minepreggo.world.effect;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.item.ItemStack;

public abstract class AbstractPregnancySymptom extends MobEffect {
	protected static final UUID SPEED_MODIFIER_UUID = UUID.fromString("2c79a160-298d-4450-ab99-715e49eb764a");
	protected static final UUID ATTACK_SPEED_MODIFIER_UUID = UUID.fromString("95f72d4f-220b-4168-8db5-67844a3e4712");
	
	protected AbstractPregnancySymptom(int color) {
		super(MobEffectCategory.NEUTRAL, color);

	}
	
	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
	
	@Override
	public List<ItemStack> getCurativeItems() {
		return new ArrayList<>();
	}
}
