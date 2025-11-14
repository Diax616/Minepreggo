package dev.dixmk.minepreggo.world.effect;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.item.ItemStack;

public abstract class AbstractPlayerPregnancyPain extends MobEffect {
	protected static final UUID SPEED_MODIFIER_UUID = UUID.fromString("b6d112f5-f5ec-41e9-a7af-1ff574bc28ce");
	protected static final UUID ATTACK_SPEED_MODIFIER_UUID = UUID.fromString("61f81610-5816-49f5-b1c5-ebc1c000981e");
	protected static final UUID ATTACK_DAMAGE_MODIFIER_UUID = UUID.fromString("c2a50193-ba13-4d7a-85bc-06f7c475faa2");

	protected AbstractPlayerPregnancyPain(int color) {
		super(MobEffectCategory.HARMFUL, color);
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
