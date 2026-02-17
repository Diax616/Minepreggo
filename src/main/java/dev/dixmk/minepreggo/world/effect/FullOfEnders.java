package dev.dixmk.minepreggo.world.effect;

import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.init.MinepreggoModSounds;
import dev.dixmk.minepreggo.world.entity.BellyPartManager;
import dev.dixmk.minepreggo.world.entity.LivingEntityHelper;
import dev.dixmk.minepreggo.world.entity.preggo.Species;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import dev.dixmk.minepreggo.world.pregnancy.Womb;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class FullOfEnders extends MobEffect {

	public FullOfEnders() {
		super(MobEffectCategory.HARMFUL, -16751053);
	}
	
	@Override
	public void applyEffectTick(LivingEntity entity, int amplifier) {	
		if (entity instanceof ServerPlayer serverPlayer) {		
			serverPlayer.getCapability(MinepreggoCapabilities.PLAYER_DATA).ifPresent(cap -> 			
				cap.getFemaleData().ifPresent(femaleData -> {
					if (femaleData.isPregnant() && femaleData.isPregnancyDataInitialized()) {
						var pregnancySystem = femaleData.getPregnancyData();
						if (serverPlayer.getRandom().nextFloat() < calculateProbabilityToRandomTeleport(pregnancySystem.getCurrentPregnancyPhase(), pregnancySystem.getLastPregnancyStage(), pregnancySystem.getWomb())) {
							double extraBlocks = serverPlayer.getRandom().nextInt(femaleData.getPregnancyData().getCurrentPregnancyPhase().ordinal());
							LivingEntityHelper.randomTeleport(entity, SoundEvents.ENDERMAN_TELEPORT, 8, 16 + extraBlocks);
							if (MinepreggoModConfig.SERVER.isBellyColisionsForPlayersEnable()) {
								var bellyPart = BellyPartManager.getInstance().get(entity);
								if (bellyPart != null) {
									bellyPart.teleportTo(entity.getX(), entity.getY(), entity.getZ());
								}
							}	
							LivingEntityHelper.playSoundNearTo(entity, MinepreggoModSounds.getRandomStomachGrowls(entity.getRandom()));
						}					
					}
				})
			);
		}
	}
	
	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return duration % 2400 == 0;
	}
	
	private static float calculateProbabilityToRandomTeleport(PregnancyPhase current, PregnancyPhase last, Womb womb) throws IllegalArgumentException {
		if (current.compareTo(last) >= 1) {
			throw new IllegalArgumentException("Current pregnancy phase must be before the last phase.");
		}
		
		int numOfBabiesEnder = womb.calculateNumOfBabiesBySpecies(Species.ENDER);
		float phaseProgress = current.ordinal() / (float) last.ordinal();
		float enderFactor = numOfBabiesEnder / (float) Womb.getMaxNumOfBabies();
		float prob = phaseProgress * enderFactor;
		return Mth.clamp(prob, 0.0f, 1.0f);
	}
}