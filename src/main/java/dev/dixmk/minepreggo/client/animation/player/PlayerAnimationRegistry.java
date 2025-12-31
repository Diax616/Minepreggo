package dev.dixmk.minepreggo.client.animation.player;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.CheckForNull;

import dev.dixmk.minepreggo.common.animation.AnimationInfo;
import dev.dixmk.minepreggo.common.animation.CommonPlayerAnimationRegistry;
import dev.dixmk.minepreggo.utils.MathHelper;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


@OnlyIn(Dist.CLIENT)
public class PlayerAnimationRegistry {
    private PlayerAnimationRegistry() {}

    private static class Holder {
        private static final PlayerAnimationRegistry INSTANCE = new PlayerAnimationRegistry();
    }

    public static PlayerAnimationRegistry getInstance() {
        return Holder.INSTANCE;
    }

    private final Map<String, PlayerAnimation> animations = new HashMap<>();

    private static final String RUBBING_BELLY_ANIM = "rubbing_belly_p";

    public void register(PlayerAnimation animation) {
        animations.put(animation.getName(), animation);
    }

    @CheckForNull
    public PlayerAnimation getAnimation(String name) {
        return animations.get(name);
    }

    public String getBellyRubbingAnimationName(PregnancyPhase phase) {
        return RUBBING_BELLY_ANIM + phase.ordinal();
    }

    public boolean isBellyRubbingAnimation(String name) {
        return name != null && name.startsWith(RUBBING_BELLY_ANIM);
    }

    public void init() {
		birthAnim();
		preBirth();
		miscarriageAnim();
		waterBreakingAnim();
		rubbingBelly();
    }
    
	private void birthAnim() {
		PlayerAnimation birth = new PlayerAnimation("birth", 360, true);
		final float extraZ = -20;
		
		birth.addPartAnimation("body", (part, continuousAnimationTick) -> {
				part.yRot = MathHelper.animateBetweenAnglesMth(-1, 1, continuousAnimationTick, 0.065F);
				part.xRot = -Mth.HALF_PI;
				part.y = 22;
				part.z = 12 + extraZ;
			});
		
		birth.addPartAnimation("head", (part, continuousAnimationTick) -> {
				part.xRot = -Mth.HALF_PI;
				part.y = 22;
				part.z = 12 + extraZ;
			});
		
		birth.addPartAnimation("right_arm", (part, continuousAnimationTick) -> {		
			part.zRot = MathHelper.animateBetweenAnglesMth(-2.5F, -5F, continuousAnimationTick, 0.075F);
			part.yRot = MathHelper.animateBetweenAnglesMth(25F, 35F, continuousAnimationTick, 0.075F);
			part.xRot = -Mth.HALF_PI;
			part.y = 22;
			part.z = 12 + extraZ;
		});
		
		birth.addPartAnimation("left_arm", (part, continuousAnimationTick) -> {			
			part.zRot = MathHelper.animateBetweenAnglesMth(2.5F, 5F, continuousAnimationTick, 0.075F);
			part.yRot = MathHelper.animateBetweenAnglesMth(-25F, -35F, continuousAnimationTick, 0.075F);
			part.xRot = -Mth.HALF_PI;
			part.y = 22;
			part.z = 12 + extraZ;
		});
	
		birth.addPartAnimation("right_leg", (part, continuousAnimationTick) -> {
			part.zRot = MathHelper.animateBetweenAnglesMth(-2.5F, -5F, continuousAnimationTick, 0.065F);
			part.yRot = MathHelper.animateBetweenAnglesMth(30F, 40F, continuousAnimationTick, 0.065F);
			part.xRot = -Mth.HALF_PI;
			part.y = 22;
			part.z = extraZ;
		});
		
		birth.addPartAnimation("left_leg", (part, continuousAnimationTick) -> {
			part.zRot = MathHelper.animateBetweenAnglesMth(2.5F, 5F, continuousAnimationTick, 0.065F);
			part.yRot = MathHelper.animateBetweenAnglesMth(-30F, -40F, continuousAnimationTick, 0.065F);
			part.xRot = -Mth.HALF_PI;
			part.y = 22;
			part.z = extraZ;
		});
		
		registerAndSync(birth);
	}
	
	private void miscarriageAnim() {
		PlayerAnimation miscarriage = new PlayerAnimation("miscarriage", 240, true);
		
		miscarriage.addPartAnimation("body", (part, continuousAnimationTick) -> 
			part.yRot = MathHelper.animateBetweenAnglesMth(-2.5F, 2.5F, continuousAnimationTick, 0.085F)
		);
		
		miscarriage.addPartAnimation("head", (part, continuousAnimationTick) -> 
			part.xRot = MathHelper.animateBetweenAnglesMth(17.5F, 20, continuousAnimationTick, 0.085F)
		);
		
		miscarriage.addPartAnimation("right_arm", (part, continuousAnimationTick) -> {		
			part.zRot = MathHelper.animateBetweenAnglesMth(17.5F, 20, continuousAnimationTick, 0.092F);
			part.yRot = MathHelper.animateBetweenAnglesMth(15, 17.5F, continuousAnimationTick, 0.092F);
		});
		
		miscarriage.addPartAnimation("left_arm", (part, continuousAnimationTick) -> {			
			part.zRot = MathHelper.animateBetweenAnglesMth(-17.5F, -20, continuousAnimationTick, 0.092F);
			part.yRot = MathHelper.animateBetweenAnglesMth(-15, -17.5F, continuousAnimationTick, 0.092F);
		});
	
		miscarriage.addPartAnimation("right_leg", (part, continuousAnimationTick) -> {
			part.zRot = MathHelper.animateBetweenAnglesMth(15, 17.5F, continuousAnimationTick, 0.085F);
			part.yRot = MathHelper.animateBetweenAnglesMth(30, 32.5F, continuousAnimationTick, 0.085F);
	
		});
		
		miscarriage.addPartAnimation("left_leg", (part, continuousAnimationTick) -> {
			part.zRot = MathHelper.animateBetweenAnglesMth(-15, -17.5F, continuousAnimationTick, 0.085F);
			part.yRot = MathHelper.animateBetweenAnglesMth(-30, -32.5F, continuousAnimationTick, 0.085F);
	
		});
				
		registerAndSync(miscarriage);
	}
	
	private void waterBreakingAnim() {
		PlayerAnimation waterBreaking = new PlayerAnimation("water_breaking", 180, true, false);
		
		waterBreaking.addPartAnimation("body", (part, continuousAnimationTick) -> {
			part.z = -3;
			part.xRot += MathHelper.animateBetweenAnglesMth(15, 16.5F, continuousAnimationTick, 0.085F);
		});
		
		waterBreaking.addPartAnimation("head", (part, continuousAnimationTick) -> 
			part.z = -3
		);
		
		waterBreaking.addPartAnimation("right_arm", (part, continuousAnimationTick) -> {		
			part.z = -3;
			part.zRot += MathHelper.animateBetweenAnglesMth(17.5F, 20, continuousAnimationTick, 0.092F);
			part.yRot += MathHelper.animateBetweenAnglesMth(15, 17.5F, continuousAnimationTick, 0.092F);
		});
		
		waterBreaking.addPartAnimation("left_arm", (part, continuousAnimationTick) -> {			
			part.z = -3;
			part.zRot += MathHelper.animateBetweenAnglesMth(-17.5F, -20, continuousAnimationTick, 0.092F);
			part.yRot += MathHelper.animateBetweenAnglesMth(-15, -17.5F, continuousAnimationTick, 0.092F);
		});
		
		waterBreaking.addPartAnimation("right_leg", (part, continuousAnimationTick) -> {
			part.zRot += MathHelper.animateBetweenAnglesMth(5, 6.5F, continuousAnimationTick, 0.085F);
			part.yRot += MathHelper.animateBetweenAnglesMth(10, 12.5F, continuousAnimationTick, 0.085F);
	
		});
		
		waterBreaking.addPartAnimation("left_leg", (part, continuousAnimationTick) -> {
			part.zRot += MathHelper.animateBetweenAnglesMth(-5, -6.5F, continuousAnimationTick, 0.085F);
			part.yRot += MathHelper.animateBetweenAnglesMth(-10, -12.5F, continuousAnimationTick, 0.085F);
	
		});
		registerAndSync(waterBreaking);	
	}
	
	private void preBirth() {
		PlayerAnimation preBirth = new PlayerAnimation("prebirth", 180, true);
		
		preBirth.addPartAnimation("body", (part, continuousAnimationTick) -> {
			part.z = -3;
			part.xRot = MathHelper.animateBetweenAnglesMth(15, 16.5F, continuousAnimationTick, 0.085F);
		});
		
		preBirth.addPartAnimation("head", (part, continuousAnimationTick) -> {
			part.z = -3;
			part.xRot = MathHelper.animateBetweenAnglesMth(-27.5F, 29, continuousAnimationTick, 0.085F);
		});
		
		preBirth.addPartAnimation("right_arm", (part, continuousAnimationTick) -> {		
			part.z = -3;
			part.zRot = MathHelper.animateBetweenAnglesMth(17.5F, 20, continuousAnimationTick, 0.02F);
			part.yRot = MathHelper.animateBetweenAnglesMth(15, 17.5F, continuousAnimationTick, 0.02F);
		});
		
		preBirth.addPartAnimation("left_arm", (part, continuousAnimationTick) -> {			
			part.z = -3;
			part.zRot = MathHelper.animateBetweenAnglesMth(-17.5F, -20, continuousAnimationTick, 0.02F);
			part.yRot = MathHelper.animateBetweenAnglesMth(-15, -17.5F, continuousAnimationTick, 0.02F);
		});
	
		preBirth.addPartAnimation("right_leg", (part, continuousAnimationTick) -> {
			part.zRot = MathHelper.animateBetweenAnglesMth(15, 17.5F, continuousAnimationTick, 0.085F);
			part.yRot = MathHelper.animateBetweenAnglesMth(30, 32.5F, continuousAnimationTick, 0.085F);
	
		});
		
		preBirth.addPartAnimation("left_leg", (part, continuousAnimationTick) -> {
			part.zRot = MathHelper.animateBetweenAnglesMth(-15, -17.5F, continuousAnimationTick, 0.085F);
			part.yRot = MathHelper.animateBetweenAnglesMth(-30, -32.5F, continuousAnimationTick, 0.085F);
	
		});
	
		registerAndSync(preBirth);	
	}
	
	private void rubbingBelly() {
		PlayerAnimation rubbingBellyP0 = new PlayerAnimation(getBellyRubbingAnimationName(PregnancyPhase.P0), 240, true);
		
		rubbingBellyP0.addPartAnimation("right_arm", (part, continuousAnimationTick) -> {		
			part.xRot = MathHelper.animateBetweenAnglesMth(-28.9360F, -31.2482F, continuousAnimationTick, 0.085F);
			part.yRot = MathHelper.animateBetweenAnglesMth(12.8595F, 13.8965F, continuousAnimationTick, 0.085F);
			part.zRot = MathHelper.animateBetweenAnglesMth(-16.6430F, -29.4254F, continuousAnimationTick, 0.085F);	
		});
		registerAndSync(rubbingBellyP0);
		
		PlayerAnimation rubbingBellyP1 = new PlayerAnimation(getBellyRubbingAnimationName(PregnancyPhase.P1), 240, true);
		
		rubbingBellyP1.addPartAnimation("right_arm", (part, continuousAnimationTick) -> {		
			part.xRot = MathHelper.animateBetweenAnglesMth(-30.9360F, -33.2482F, continuousAnimationTick, 0.085F);
			part.yRot = MathHelper.animateBetweenAnglesMth(12.8595F, 13.8965F, continuousAnimationTick, 0.085F);
			part.zRot = MathHelper.animateBetweenAnglesMth(-18.6430F, -31.4254F, continuousAnimationTick, 0.085F);
		});
		registerAndSync(rubbingBellyP1);
		
		PlayerAnimation rubbingBellyP2 = new PlayerAnimation(getBellyRubbingAnimationName(PregnancyPhase.P2), 240, true);
		
		rubbingBellyP2.addPartAnimation("right_arm", (part, continuousAnimationTick) -> {		
			part.xRot = MathHelper.animateBetweenAnglesMth(-72.1763F, -76.0995F, continuousAnimationTick, 0.085F);
			part.yRot = MathHelper.animateBetweenAnglesMth(30.2325F, 41.8932F, continuousAnimationTick, 0.085F);
			part.zRot = MathHelper.animateBetweenAnglesMth(-65.6523F, -71.1692F, continuousAnimationTick, 0.085F);
		});
		
		rubbingBellyP2.addPartAnimation("left_arm", (part, continuousAnimationTick) -> {			
			part.xRot = MathHelper.animateBetweenAnglesMth(-60.3841F, -69.4102F, continuousAnimationTick, 0.085F);
			part.yRot = MathHelper.animateBetweenAnglesMth(-46.077F, -32.2461F, continuousAnimationTick, 0.085F);
			part.zRot = MathHelper.animateBetweenAnglesMth(56.3194F, 71.6639F, continuousAnimationTick, 0.085F);
		});
		
		registerAndSync(rubbingBellyP2);
			
		PlayerAnimation rubbingBellyP3 = new PlayerAnimation(getBellyRubbingAnimationName(PregnancyPhase.P3), 240, true);
		
		rubbingBellyP3.addPartAnimation("right_arm", (part, continuousAnimationTick) -> {		
			part.xRot = MathHelper.animateBetweenAnglesMth(-39.6041F, -60.8900F, continuousAnimationTick, 0.085F);
			part.yRot = MathHelper.animateBetweenAnglesMth(-9.5874F, 0.7699F, continuousAnimationTick, 0.085F);
			part.zRot = MathHelper.animateBetweenAnglesMth(9.2054F, 0.5902F, continuousAnimationTick, 0.085F);
		});
		
		rubbingBellyP3.addPartAnimation("left_arm", (part, continuousAnimationTick) -> {			
			part.xRot = MathHelper.animateBetweenAnglesMth(-60.9562F, -45.1742F, continuousAnimationTick, 0.085F);
			part.yRot = MathHelper.animateBetweenAnglesMth(9.1256F, 2.1333F, continuousAnimationTick, 0.085F);
			part.zRot = MathHelper.animateBetweenAnglesMth(-14.6035F, -3.8908F, continuousAnimationTick, 0.085F);
		});
		
		registerAndSync(rubbingBellyP3);	
		
		PlayerAnimation rubbingBellyP4 = new PlayerAnimation(getBellyRubbingAnimationName(PregnancyPhase.P4), 240, true);
		
		rubbingBellyP4.addPartAnimation("right_arm", (part, continuousAnimationTick) -> {		
			part.xRot = MathHelper.animateBetweenAnglesMth(-39.6041F, -60.8900F, continuousAnimationTick, 0.085F);
			part.yRot = MathHelper.animateBetweenAnglesMth(-9.5874F, 0.7699F, continuousAnimationTick, 0.085F);
			part.zRot = MathHelper.animateBetweenAnglesMth(12.5054F, 5.5902F, continuousAnimationTick, 0.085F);
		});
		
		rubbingBellyP4.addPartAnimation("left_arm", (part, continuousAnimationTick) -> {			
			part.xRot = MathHelper.animateBetweenAnglesMth(-60.9562F, -45.1742F, continuousAnimationTick, 0.085F);
			part.yRot = MathHelper.animateBetweenAnglesMth(9.1256F, 2.1333F, continuousAnimationTick, 0.085F);
			part.zRot = MathHelper.animateBetweenAnglesMth(-17.6035F, -7.8908F, continuousAnimationTick, 0.085F);
		});
		
		registerAndSync(rubbingBellyP4);	
		
		PlayerAnimation rubbingBellyP5 = new PlayerAnimation(getBellyRubbingAnimationName(PregnancyPhase.P5), 240, true);
		
		rubbingBellyP5.addPartAnimation("right_arm", (part, continuousAnimationTick) -> {		
			part.xRot = MathHelper.animateBetweenAnglesMth(-40.1427F, -60.7642F, continuousAnimationTick, 0.085F);
			part.yRot = MathHelper.animateBetweenAnglesMth(-6.3807F, 5.1363F, continuousAnimationTick, 0.085F);
			part.zRot = MathHelper.animateBetweenAnglesMth(16.3743F, 7.4398F, continuousAnimationTick, 0.085F);
		});
		
		rubbingBellyP5.addPartAnimation("left_arm", (part, continuousAnimationTick) -> {			
			part.xRot = MathHelper.animateBetweenAnglesMth(-61.2504F, -45.1963F, continuousAnimationTick, 0.085F);
			part.yRot = MathHelper.animateBetweenAnglesMth(4.7474F, -1.4147F, continuousAnimationTick, 0.085F);
			part.zRot = MathHelper.animateBetweenAnglesMth(-20.0369F, -11.4144F, continuousAnimationTick, 0.085F);
		});
		
		registerAndSync(rubbingBellyP5);
		
		
		PlayerAnimation rubbingBellyP6 = new PlayerAnimation(getBellyRubbingAnimationName(PregnancyPhase.P6), 240, true);
		
		rubbingBellyP6.addPartAnimation("right_arm", (part, continuousAnimationTick) -> {		
			part.xRot = MathHelper.animateBetweenAnglesMth(-40.1427F, -60.7642F, continuousAnimationTick, 0.085F);
			part.yRot = MathHelper.animateBetweenAnglesMth(-6.3807F, 5.1363F, continuousAnimationTick, 0.085F);
			part.zRot = MathHelper.animateBetweenAnglesMth(21.3743F, 12.4398F, continuousAnimationTick, 0.085F);
		});
		
		rubbingBellyP6.addPartAnimation("left_arm", (part, continuousAnimationTick) -> {			
			part.xRot = MathHelper.animateBetweenAnglesMth(-61.2504F, -45.1963F, continuousAnimationTick, 0.085F);
			part.yRot = MathHelper.animateBetweenAnglesMth(4.7474F, -1.4147F, continuousAnimationTick, 0.085F);
			part.zRot = MathHelper.animateBetweenAnglesMth(-25.0369F, -17.4144F, continuousAnimationTick, 0.085F);
		});
		
		registerAndSync(rubbingBellyP6);
		
	
		PlayerAnimation rubbingBellyP7 = new PlayerAnimation(getBellyRubbingAnimationName(PregnancyPhase.P7), 240, true);
		
		rubbingBellyP7.addPartAnimation("right_arm", (part, continuousAnimationTick) -> {		
			part.xRot = MathHelper.animateBetweenAnglesMth(-40.4606F, -60.4489F, continuousAnimationTick, 0.085F);
			part.yRot = MathHelper.animateBetweenAnglesMth(-3.1454F, 9.4933F, continuousAnimationTick, 0.085F);
			part.zRot = MathHelper.animateBetweenAnglesMth(29.2003F, 9.9134F, continuousAnimationTick, 0.085F);
		});
		
		rubbingBellyP7.addPartAnimation("left_arm", (part, continuousAnimationTick) -> {			
			part.xRot = MathHelper.animateBetweenAnglesMth(-61.3575F, -44.9997F, continuousAnimationTick, 0.085F);
			part.yRot = MathHelper.animateBetweenAnglesMth(0.3609F, -4.9574F, continuousAnimationTick, 0.085F);
			part.zRot = MathHelper.animateBetweenAnglesMth(-27.4395F, -15.9488F, continuousAnimationTick, 0.085F);
		});
		
		registerAndSync(rubbingBellyP7);

		
		PlayerAnimation rubbingBellyP8 = new PlayerAnimation(getBellyRubbingAnimationName(PregnancyPhase.P8), 240, true);
		
		rubbingBellyP8.addPartAnimation("right_arm", (part, continuousAnimationTick) -> {		
			part.xRot = MathHelper.animateBetweenAnglesMth(-40.4606F, -60.4489F, continuousAnimationTick, 0.085F);
			part.yRot = MathHelper.animateBetweenAnglesMth(-3.1454F, 9.4933F, continuousAnimationTick, 0.085F);
			part.zRot = MathHelper.animateBetweenAnglesMth(33.2003F, 13.9134F, continuousAnimationTick, 0.085F);
		});
		
		rubbingBellyP8.addPartAnimation("left_arm", (part, continuousAnimationTick) -> {			
			part.xRot = MathHelper.animateBetweenAnglesMth(-61.3575F, -44.9997F, continuousAnimationTick, 0.085F);
			part.yRot = MathHelper.animateBetweenAnglesMth(0.3609F, -4.9574F, continuousAnimationTick, 0.085F);
			part.zRot = MathHelper.animateBetweenAnglesMth(-32.4395F, -20.9488F, continuousAnimationTick, 0.085F);
		});
		
		registerAndSync(rubbingBellyP8);
	}
	
	private void registerAndSync(PlayerAnimation anim) {
        register(anim);
        CommonPlayerAnimationRegistry.getInstance().register(
            new AnimationInfo(anim.getName(), anim.getDuration(), anim.isLooping(), anim.shouldOverrideVanilla())
        );
    }
}
