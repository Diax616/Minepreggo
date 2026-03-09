package dev.dixmk.minepreggo.common.animation;

import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;

public class PlayerAnimationInfos {

	private PlayerAnimationInfos() {}
	
	public static final PlayerAnimationInfo BIRTH = new PlayerAnimationInfo("birth", 100, true, true);
	public static final PlayerAnimationInfo MISCARRIAGE = new PlayerAnimationInfo("prebirth", 180, true, true);
	public static final PlayerAnimationInfo WATER_BREAKING = new PlayerAnimationInfo("miscarriage", 240, true, true);
	public static final PlayerAnimationInfo PREBIRTH = new PlayerAnimationInfo("water_breaking", 180, true, false);
	public static final PlayerAnimationInfo RUBBING_BELLY_P0 = new PlayerAnimationInfo(CommonPlayerAnimationRegistry.getInstance().getBellyRubbingAnimationName(PregnancyPhase.P0), 240, true, true);
	public static final PlayerAnimationInfo RUBBING_BELLY_P1 = new PlayerAnimationInfo(CommonPlayerAnimationRegistry.getInstance().getBellyRubbingAnimationName(PregnancyPhase.P1), 240, true, true);
	public static final PlayerAnimationInfo RUBBING_BELLY_P2 = new PlayerAnimationInfo(CommonPlayerAnimationRegistry.getInstance().getBellyRubbingAnimationName(PregnancyPhase.P2), 240, true, true);
	public static final PlayerAnimationInfo RUBBING_BELLY_P3 = new PlayerAnimationInfo(CommonPlayerAnimationRegistry.getInstance().getBellyRubbingAnimationName(PregnancyPhase.P3), 240, true, true);
	public static final PlayerAnimationInfo RUBBING_BELLY_P4 = new PlayerAnimationInfo(CommonPlayerAnimationRegistry.getInstance().getBellyRubbingAnimationName(PregnancyPhase.P4), 240, true, true);
	public static final PlayerAnimationInfo RUBBING_BELLY_P5 = new PlayerAnimationInfo(CommonPlayerAnimationRegistry.getInstance().getBellyRubbingAnimationName(PregnancyPhase.P5), 240, true, true);
	public static final PlayerAnimationInfo RUBBING_BELLY_P6 = new PlayerAnimationInfo(CommonPlayerAnimationRegistry.getInstance().getBellyRubbingAnimationName(PregnancyPhase.P6), 240, true, true);
	public static final PlayerAnimationInfo RUBBING_BELLY_P7 = new PlayerAnimationInfo(CommonPlayerAnimationRegistry.getInstance().getBellyRubbingAnimationName(PregnancyPhase.P7), 240, true, true);
	public static final PlayerAnimationInfo RUBBING_BELLY_P8 = new PlayerAnimationInfo(CommonPlayerAnimationRegistry.getInstance().getBellyRubbingAnimationName(PregnancyPhase.P8), 240, true, true);
}
