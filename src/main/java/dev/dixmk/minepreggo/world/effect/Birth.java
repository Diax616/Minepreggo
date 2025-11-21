package dev.dixmk.minepreggo.world.effect;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.registries.ForgeRegistries;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.init.MinepreggoModMobEffects;
import dev.dixmk.minepreggo.network.chat.MessageHelper;
import dev.dixmk.minepreggo.world.entity.player.PlayerHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;

public class Birth extends AbstractPlayerPregnancyPain {
	private static final AttributeModifier ATTACK_SPEED_MODIFIER = new AttributeModifier(ATTACK_SPEED_MODIFIER_UUID, "birth attack speed nerf", -1D, AttributeModifier.Operation.MULTIPLY_TOTAL);
	private static final AttributeModifier SPEED_MODIFIER = new AttributeModifier(SPEED_MODIFIER_UUID, "birth speed nerf", -1D, AttributeModifier.Operation.MULTIPLY_TOTAL);

	public Birth() {
		super(-6750208);
	}
	
	@Override
	public void addAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {		
		if (!PlayerHelper.isPlayerValid(entity)) return;
		
		if (!entity.level().isClientSide) {
			entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, -1, 3, false, false));	
			
			AttributeInstance speedAttr = entity.getAttribute(Attributes.MOVEMENT_SPEED);
			AttributeInstance attackSpeedAttr = entity.getAttribute(Attributes.ATTACK_SPEED);

			if (speedAttr != null && speedAttr.getModifier(SPEED_MODIFIER_UUID) == null) {
			    speedAttr.addTransientModifier(SPEED_MODIFIER);
			}			
			if (attackSpeedAttr != null && attackSpeedAttr.getModifier(ATTACK_SPEED_MODIFIER_UUID) == null) {
			    attackSpeedAttr.addTransientModifier(ATTACK_SPEED_MODIFIER);
			}
			
			entity.level().playSound(null, BlockPos.containing(entity.getX(), entity.getY(), entity.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "birth")), SoundSource.PLAYERS, 1, 1);
			
			MessageHelper.sendMessageToPlayer((Player) entity, Component.translatable("chat.minepreggo.player.birth.message.labor"));	
		}	
	}
	
	@Override
	public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {	
		if (!PlayerHelper.isPlayerValid(entity)) return;
	
		if (!entity.level().isClientSide) {	
			AttributeInstance speedAttr = entity.getAttribute(Attributes.MOVEMENT_SPEED);
			AttributeInstance attackSpeedAttr = entity.getAttribute(Attributes.ATTACK_SPEED);
			
			if (speedAttr != null && speedAttr.getModifier(SPEED_MODIFIER_UUID) != null) {
				speedAttr.removeModifier(SPEED_MODIFIER);
			}	
			if (attackSpeedAttr != null && attackSpeedAttr.getModifier(ATTACK_SPEED_MODIFIER_UUID) != null) {
				attackSpeedAttr.removeModifier(ATTACK_SPEED_MODIFIER);
			}
			
			entity.removeEffect(MobEffects.WEAKNESS);	
			entity.removeEffect(MinepreggoModMobEffects.FULL_OF_CREEPERS.get());
			entity.removeEffect(MinepreggoModMobEffects.FULL_OF_ZOMBIES.get());
			entity.refreshDimensions();
			
			entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 3600, 0));	
			entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 3600, 0));
			entity.addEffect(new MobEffectInstance(MobEffects.LUCK, 4800, 0));	
		
			/*
			if (entity instanceof ServerPlayer serverPlayer) {
				serverPlayer.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap -> 
				MessageHelper.sendMessageToPlayer(serverPlayer, Component.translatable("chat.minepreggo.player.birth.message.successful_birth", cap.getFemalePlayerData().getTotalNumOfBabies())));
			}
			*/
		}
	}
}
