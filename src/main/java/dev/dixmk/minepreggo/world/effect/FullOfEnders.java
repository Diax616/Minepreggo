package dev.dixmk.minepreggo.world.effect;

import dev.dixmk.minepreggo.MinepreggoModConfig;
import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.init.MinepreggoModSounds;
import dev.dixmk.minepreggo.world.entity.BellyPartManager;
import dev.dixmk.minepreggo.world.entity.LivingEntityHelper;
import dev.dixmk.minepreggo.world.entity.preggo.Species;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import dev.dixmk.minepreggo.world.pregnancy.Womb;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;

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
						if (serverPlayer.getRandom().nextFloat() < calculateProbabilityToRandomTeleport(pregnancySystem.getCurrentPregnancyPhase(), pregnancySystem.getWomb())) {
							randomTeleport(entity);
							LivingEntityHelper.playSoundNearTo(entity, MinepreggoModSounds.getRandomStomachGrowls(entity.getRandom()));
						}					
					}
				})
			);
		}
	}
	
	private void randomTeleport(LivingEntity entity) {	
		var level = entity.level();
		if (!entity.level().isClientSide) {
			double d0 = entity.getX();
			double d1 = entity.getY();
			double d2 = entity.getZ();

			for(int i = 0; i < 16; ++i) {
				double d3 = entity.getX() + (entity.getRandom().nextDouble() - 0.5D) * 16.0D;
				double d4 = Mth.clamp(entity.getY() + (entity.getRandom().nextInt(16) - 8), level.getMinBuildHeight(), (level.getMinBuildHeight() + ((ServerLevel)level).getLogicalHeight() - 1));
				double d5 = entity.getZ() + (entity.getRandom().nextDouble() - 0.5D) * 16.0D;
				if (entity.isPassenger()) {
					entity.stopRiding();
				}
				Vec3 vec3 = entity.position();
				level.gameEvent(GameEvent.TELEPORT, vec3, GameEvent.Context.of(entity));
				if (entity.randomTeleport(d3, d4, d5, true)) {
					level.playSound(null, d0, d1, d2, SoundEvents.ENDERMAN_TELEPORT, SoundSource.PLAYERS, 1.0F, 1.0F);
					break;
				}
			}
			
			if (MinepreggoModConfig.SERVER.isBellyColisionsForPlayersEnable()) {
				var bellyPart = BellyPartManager.getInstance().get(entity);
				if (bellyPart != null) {
					bellyPart.teleportTo(entity.getX(), entity.getY(), entity.getZ());
				}
			}	
		}
	}
	
	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return duration % 3600 == 0;
	}
	
	public static float calculateProbabilityToRandomTeleport(PregnancyPhase phase, Womb womb) {
		int numOfBabiesEnder = womb.calculateNumOfBabiesBySpecies(Species.ENDER);
		float phaseProgress = phase.ordinal() / (float) (PregnancyPhase.values().length - 1);
		float enderFactor = numOfBabiesEnder / (float) Womb.getMaxNumOfBabies();
		float prob = phaseProgress * enderFactor;
		return Mth.clamp(prob, 0.0f, 1.0f);
	}
}