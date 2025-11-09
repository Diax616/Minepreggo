package dev.dixmk.minepreggo.world.effect;

import java.util.UUID;

import dev.dixmk.minepreggo.network.capability.IPregnancySystemHandler;
import dev.dixmk.minepreggo.world.entity.player.PlayerHelper;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class PregnancyResistance extends MobEffect {
	private static final UUID ARMOR_MODIFIER_UUID = UUID.fromString("cffb22ba-bbb7-4c01-a758-b01e61d1dec6");
	private static final AttributeModifier ARMOR_MODIFIER = new AttributeModifier(ARMOR_MODIFIER_UUID, "armor buff", 0.1, AttributeModifier.Operation.MULTIPLY_BASE);

	
	public PregnancyResistance() {
		super(MobEffectCategory.BENEFICIAL, -6710785);
	}
	
	@Override
	public void addAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {		
		if (entity.level().isClientSide) {
			return;
		}
		
		if (canApplyEffect(entity)) {
			AttributeInstance armorAttr = entity.getAttribute(Attributes.ARMOR);
			if (armorAttr != null && armorAttr.getModifier(ARMOR_MODIFIER_UUID) == null) {
				armorAttr.addPermanentModifier(ARMOR_MODIFIER);
			}
		}
	}
	
	@Override
	public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
		if (entity.level().isClientSide) {
			return;
		}
		
		if (canApplyEffect(entity)) {
			AttributeInstance armorAttr = entity.getAttribute(Attributes.ARMOR);
			if (armorAttr != null && armorAttr.getModifier(ARMOR_MODIFIER_UUID) != null) {
				armorAttr.removeModifier(ARMOR_MODIFIER);
			}
		}	
	}
	
	private boolean canApplyEffect(LivingEntity entity) {
		return (entity instanceof ServerPlayer serverPlayer && PlayerHelper.isFemaleAndPregnant(serverPlayer))
		|| (entity instanceof PreggoMob p && p instanceof IPregnancySystemHandler);
	}
}
