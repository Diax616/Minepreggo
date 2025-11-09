package dev.dixmk.minepreggo.world.effect;

import net.minecraft.world.entity.ai.attributes.AttributeModifier;

public class PregnancyP3 extends AbstractPregnancy {

	private static final AttributeModifier ATTACK_SPEED_NERF = new AttributeModifier(ATTACK_SPEED_MODIFIER_UUID, "pregnancy", -0.1, AttributeModifier.Operation.MULTIPLY_BASE);
	private static final AttributeModifier SPEED_NERF = new AttributeModifier(SPEED_MODIFIER_UUID, "pregnancy", -0.15, AttributeModifier.Operation.MULTIPLY_BASE);

	
	public PregnancyP3() {
		super();
	}
	
}
