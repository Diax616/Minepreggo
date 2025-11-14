package dev.dixmk.minepreggo.world.effect;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;

public class BellyRubs extends AbstractPlayerPregnancySymptom {
	private static final AttributeModifier LUCK_MODIFIER = new AttributeModifier(LUCK_MODIFIER_UUID, "lactation luck nerf", -100, AttributeModifier.Operation.ADDITION);

	public BellyRubs() {
		super(-39322);
	}

	@Override
	public void addAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {	
		if (!(entity instanceof Player)) return;
		
		if (!entity.level().isClientSide) {
			AttributeInstance luckAttr = entity.getAttribute(Attributes.LUCK);
			if (luckAttr != null && luckAttr.getModifier(LUCK_MODIFIER_UUID) == null) {
				luckAttr.addPermanentModifier(LUCK_MODIFIER);
			}
		}
	}
	
	@Override
	public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
		if (!(entity instanceof Player)) return;
		
		if (!entity.level().isClientSide) {		
			AttributeInstance luckAttr = entity.getAttribute(Attributes.LUCK);
			if (luckAttr != null && luckAttr.getModifier(LUCK_MODIFIER_UUID) != null) {
				luckAttr.removeModifier(LUCK_MODIFIER);
			}
		}
	}
}
