package dev.dixmk.minepreggo.world.effect;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public abstract class AbstractPregnancyPain extends MobEffect {
	protected static final UUID SPEED_MODIFIER_UUID = UUID.fromString("b6d112f5-f5ec-41e9-a7af-1ff574bc28ce");
	protected static final UUID ATTACK_SPEED_MODIFIER_UUID = UUID.fromString("61f81610-5816-49f5-b1c5-ebc1c000981e");
	
	protected AbstractPregnancyPain(int color) {
		super(MobEffectCategory.HARMFUL, color);
	}
	
	protected final boolean canApplyEffect(LivingEntity entity) {
		return entity instanceof Player;
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
