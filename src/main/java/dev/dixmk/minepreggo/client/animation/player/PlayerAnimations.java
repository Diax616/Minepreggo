package dev.dixmk.minepreggo.client.animation.player;

import dev.dixmk.minepreggo.common.animation.PlayerAnimationInfos;
import dev.dixmk.minepreggo.utils.MathHelper;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PlayerAnimations {

	private PlayerAnimations() {}

	public static final PlayerAnimation BIRTH = PlayerAnimation.builder(PlayerAnimationInfos.BIRTH)
			.addPartAnimation("body", (part, continuousAnimationTick) -> {
				part.yRot = MathHelper.animateBetweenAnglesMth(-1, 1, continuousAnimationTick, 0.065F);
				part.xRot = -Mth.HALF_PI;
				part.y = 22;
				part.z = -8;
			})
			.addPartAnimation("head", (part, continuousAnimationTick) -> {
				part.xRot = -Mth.HALF_PI;
				part.y = 22;
				part.z = -8;
			})
			.addPartAnimation("right_arm", (part, continuousAnimationTick) -> {		
				part.zRot = MathHelper.animateBetweenAnglesMth(-2.5F, -5F, continuousAnimationTick, 0.075F);
				part.yRot = MathHelper.animateBetweenAnglesMth(25F, 35F, continuousAnimationTick, 0.075F);
				part.xRot = -Mth.HALF_PI;
				part.y = 22;
				part.z = -8;
			})
			.addPartAnimation("left_arm", (part, continuousAnimationTick) -> {			
				part.zRot = MathHelper.animateBetweenAnglesMth(2.5F, 5F, continuousAnimationTick, 0.075F);
				part.yRot = MathHelper.animateBetweenAnglesMth(-25F, -35F, continuousAnimationTick, 0.075F);
				part.xRot = -Mth.HALF_PI;
				part.y = 22;
				part.z = -8;
			})
			.addPartAnimation("right_leg", (part, continuousAnimationTick) -> {
				part.zRot = MathHelper.animateBetweenAnglesMth(-2.5F, -5F, continuousAnimationTick, 0.065F);
				part.yRot = MathHelper.animateBetweenAnglesMth(30F, 40F, continuousAnimationTick, 0.065F);
				part.xRot = -Mth.HALF_PI;
				part.y = 22;
				part.z = -20;
			})
			.addPartAnimation("left_leg", (part, continuousAnimationTick) -> {
				part.zRot = MathHelper.animateBetweenAnglesMth(2.5F, 5F, continuousAnimationTick, 0.065F);
				part.yRot = MathHelper.animateBetweenAnglesMth(-30F, -40F, continuousAnimationTick, 0.065F);
				part.xRot = -Mth.HALF_PI;
				part.y = 22;
				part.z = -20;
			})
			.build();

	public static final PlayerAnimation MISCARRIAGE = PlayerAnimation.builder(PlayerAnimationInfos.MISCARRIAGE)
			.addPartAnimation("body", (part, continuousAnimationTick) -> 
				part.yRot = MathHelper.animateBetweenAnglesMth(-2.5F, 2.5F, continuousAnimationTick, 0.085F)
			)
			.addPartAnimation("head", (part, continuousAnimationTick) -> 
				part.xRot = MathHelper.animateBetweenAnglesMth(17.5F, 20, continuousAnimationTick, 0.085F)
			)
			.addPartAnimation("right_arm", (part, continuousAnimationTick) -> {		
				part.zRot = MathHelper.animateBetweenAnglesMth(17.5F, 20, continuousAnimationTick, 0.092F);
				part.yRot = MathHelper.animateBetweenAnglesMth(15, 17.5F, continuousAnimationTick, 0.092F);
			})
			.addPartAnimation("left_arm", (part, continuousAnimationTick) -> {			
				part.zRot = MathHelper.animateBetweenAnglesMth(-17.5F, -20, continuousAnimationTick, 0.092F);
				part.yRot = MathHelper.animateBetweenAnglesMth(-15, -17.5F, continuousAnimationTick, 0.092F);
			})
			.addPartAnimation("right_leg", (part, continuousAnimationTick) -> {
				part.zRot = MathHelper.animateBetweenAnglesMth(15, 17.5F, continuousAnimationTick, 0.085F);
				part.yRot = MathHelper.animateBetweenAnglesMth(30, 32.5F, continuousAnimationTick, 0.085F);	
			})
			.addPartAnimation("left_leg", (part, continuousAnimationTick) -> {
				part.zRot = MathHelper.animateBetweenAnglesMth(-15, -17.5F, continuousAnimationTick, 0.085F);
				part.yRot = MathHelper.animateBetweenAnglesMth(-30, -32.5F, continuousAnimationTick, 0.085F);
		
			})
			.build();

	public static final PlayerAnimation WATER_BREAKING = PlayerAnimation.builder(PlayerAnimationInfos.WATER_BREAKING)	
		.addPartAnimation("body", (part, continuousAnimationTick) -> {
			part.z = -3;
			part.xRot += MathHelper.animateBetweenAnglesMth(15, 16.5F, continuousAnimationTick, 0.085F);
		})
		.addPartAnimation("head", (part, continuousAnimationTick) -> 
			part.z = -3
		)
		.addPartAnimation("right_arm", (part, continuousAnimationTick) -> {		
			part.z = -3;
			part.zRot += MathHelper.animateBetweenAnglesMth(17.5F, 20, continuousAnimationTick, 0.092F);
			part.yRot += MathHelper.animateBetweenAnglesMth(15, 17.5F, continuousAnimationTick, 0.092F);
		})
		.addPartAnimation("left_arm", (part, continuousAnimationTick) -> {			
			part.z = -3;
			part.zRot += MathHelper.animateBetweenAnglesMth(-17.5F, -20, continuousAnimationTick, 0.092F);
			part.yRot += MathHelper.animateBetweenAnglesMth(-15, -17.5F, continuousAnimationTick, 0.092F);
		})
		.addPartAnimation("right_leg", (part, continuousAnimationTick) -> {
			part.zRot += MathHelper.animateBetweenAnglesMth(5, 6.5F, continuousAnimationTick, 0.085F);
			part.yRot += MathHelper.animateBetweenAnglesMth(10, 12.5F, continuousAnimationTick, 0.085F);
	
		})
		.addPartAnimation("left_leg", (part, continuousAnimationTick) -> {
			part.zRot += MathHelper.animateBetweenAnglesMth(-5, -6.5F, continuousAnimationTick, 0.085F);
			part.yRot += MathHelper.animateBetweenAnglesMth(-10, -12.5F, continuousAnimationTick, 0.085F);
	
		})
		.build();
	
	public static final PlayerAnimation PREBIRTH = PlayerAnimation.builder(PlayerAnimationInfos.PREBIRTH)
		.addPartAnimation("body", (part, continuousAnimationTick) -> {
			part.z = -3;
			part.xRot = MathHelper.animateBetweenAnglesMth(15, 16.5F, continuousAnimationTick, 0.085F);
		})
		.addPartAnimation("head", (part, continuousAnimationTick) -> {
			part.z = -3;
			part.xRot = MathHelper.animateBetweenAnglesMth(-27.5F, 29, continuousAnimationTick, 0.085F);
		})	
		.addPartAnimation("right_arm", (part, continuousAnimationTick) -> {		
			part.z = -3;
			part.zRot = MathHelper.animateBetweenAnglesMth(17.5F, 20, continuousAnimationTick, 0.02F);
			part.yRot = MathHelper.animateBetweenAnglesMth(15, 17.5F, continuousAnimationTick, 0.02F);
		})
		.addPartAnimation("left_arm", (part, continuousAnimationTick) -> {			
			part.z = -3;
			part.zRot = MathHelper.animateBetweenAnglesMth(-17.5F, -20, continuousAnimationTick, 0.02F);
			part.yRot = MathHelper.animateBetweenAnglesMth(-15, -17.5F, continuousAnimationTick, 0.02F);
		})
		.addPartAnimation("right_leg", (part, continuousAnimationTick) -> {
			part.zRot = MathHelper.animateBetweenAnglesMth(15, 17.5F, continuousAnimationTick, 0.085F);
			part.yRot = MathHelper.animateBetweenAnglesMth(30, 32.5F, continuousAnimationTick, 0.085F);
	
		})
		.addPartAnimation("left_leg", (part, continuousAnimationTick) -> {
			part.zRot = MathHelper.animateBetweenAnglesMth(-15, -17.5F, continuousAnimationTick, 0.085F);
			part.yRot = MathHelper.animateBetweenAnglesMth(-30, -32.5F, continuousAnimationTick, 0.085F);
	
		})
		.build();
	
	public static final PlayerAnimation RUBBING_BELLY_P0 = PlayerAnimation.builder(PlayerAnimationInfos.RUBBING_BELLY_P0)
		.addPartAnimation("right_arm", (part, continuousAnimationTick) -> {		
			part.xRot = MathHelper.animateBetweenAnglesMth(-28.9360F, -31.2482F, continuousAnimationTick, 0.085F);
			part.yRot = MathHelper.animateBetweenAnglesMth(12.8595F, 13.8965F, continuousAnimationTick, 0.085F);
			part.zRot = MathHelper.animateBetweenAnglesMth(-16.6430F, -29.4254F, continuousAnimationTick, 0.085F);	
		})
		.build();
	
	public static final PlayerAnimation RUBBING_BELLY_P1 = PlayerAnimation.builder(PlayerAnimationInfos.RUBBING_BELLY_P1)
			.addPartAnimation("right_arm", (part, continuousAnimationTick) -> {		
				part.xRot = MathHelper.animateBetweenAnglesMth(-30.9360F, -33.2482F, continuousAnimationTick, 0.085F);
				part.yRot = MathHelper.animateBetweenAnglesMth(12.8595F, 13.8965F, continuousAnimationTick, 0.085F);
				part.zRot = MathHelper.animateBetweenAnglesMth(-18.6430F, -31.4254F, continuousAnimationTick, 0.085F);
			})
			.build();

	public static final PlayerAnimation RUBBING_BELLY_P2 = PlayerAnimation.builder(PlayerAnimationInfos.RUBBING_BELLY_P2)
			.addPartAnimation("right_arm", (part, continuousAnimationTick) -> {		
				part.xRot = MathHelper.animateBetweenAnglesMth(-72.1763F, -76.0995F, continuousAnimationTick, 0.085F);
				part.yRot = MathHelper.animateBetweenAnglesMth(30.2325F, 41.8932F, continuousAnimationTick, 0.085F);
				part.zRot = MathHelper.animateBetweenAnglesMth(-65.6523F, -71.1692F, continuousAnimationTick, 0.085F);
			})	
			.addPartAnimation("left_arm", (part, continuousAnimationTick) -> {			
				part.xRot = MathHelper.animateBetweenAnglesMth(-60.3841F, -69.4102F, continuousAnimationTick, 0.085F);
				part.yRot = MathHelper.animateBetweenAnglesMth(-46.077F, -32.2461F, continuousAnimationTick, 0.085F);
				part.zRot = MathHelper.animateBetweenAnglesMth(56.3194F, 71.6639F, continuousAnimationTick, 0.085F);
			})
			.build();
	
	public static final PlayerAnimation RUBBING_BELLY_P3 = PlayerAnimation.builder(PlayerAnimationInfos.RUBBING_BELLY_P3)
			.addPartAnimation("right_arm", (part, continuousAnimationTick) -> {		
				part.xRot = MathHelper.animateBetweenAnglesMth(-39.6041F, -60.8900F, continuousAnimationTick, 0.085F);
				part.yRot = MathHelper.animateBetweenAnglesMth(-9.5874F, 0.7699F, continuousAnimationTick, 0.085F);
				part.zRot = MathHelper.animateBetweenAnglesMth(9.2054F, 0.5902F, continuousAnimationTick, 0.085F);
			})
			.addPartAnimation("left_arm", (part, continuousAnimationTick) -> {			
				part.xRot = MathHelper.animateBetweenAnglesMth(-60.9562F, -45.1742F, continuousAnimationTick, 0.085F);
				part.yRot = MathHelper.animateBetweenAnglesMth(9.1256F, 2.1333F, continuousAnimationTick, 0.085F);
				part.zRot = MathHelper.animateBetweenAnglesMth(-14.6035F, -3.8908F, continuousAnimationTick, 0.085F);
			})
			.build();
	
	public static final PlayerAnimation RUBBING_BELLY_P4 = PlayerAnimation.builder(PlayerAnimationInfos.RUBBING_BELLY_P4)
			.addPartAnimation("right_arm", (part, continuousAnimationTick) -> {		
				part.xRot = MathHelper.animateBetweenAnglesMth(-39.6041F, -60.8900F, continuousAnimationTick, 0.085F);
				part.yRot = MathHelper.animateBetweenAnglesMth(-9.5874F, 0.7699F, continuousAnimationTick, 0.085F);
				part.zRot = MathHelper.animateBetweenAnglesMth(12.5054F, 5.5902F, continuousAnimationTick, 0.085F);
			})
			.addPartAnimation("left_arm", (part, continuousAnimationTick) -> {			
				part.xRot = MathHelper.animateBetweenAnglesMth(-60.9562F, -45.1742F, continuousAnimationTick, 0.085F);
				part.yRot = MathHelper.animateBetweenAnglesMth(9.1256F, 2.1333F, continuousAnimationTick, 0.085F);
				part.zRot = MathHelper.animateBetweenAnglesMth(-17.6035F, -7.8908F, continuousAnimationTick, 0.085F);
			})
			.build();
	
	public static final PlayerAnimation RUBBING_BELLY_P5 = PlayerAnimation.builder(PlayerAnimationInfos.RUBBING_BELLY_P5)
			.addPartAnimation("right_arm", (part, continuousAnimationTick) -> {		
				part.xRot = MathHelper.animateBetweenAnglesMth(-40.1427F, -60.7642F, continuousAnimationTick, 0.085F);
				part.yRot = MathHelper.animateBetweenAnglesMth(-6.3807F, 5.1363F, continuousAnimationTick, 0.085F);
				part.zRot = MathHelper.animateBetweenAnglesMth(16.3743F, 7.4398F, continuousAnimationTick, 0.085F);
			})	
			.addPartAnimation("left_arm", (part, continuousAnimationTick) -> {			
				part.xRot = MathHelper.animateBetweenAnglesMth(-61.2504F, -45.1963F, continuousAnimationTick, 0.085F);
				part.yRot = MathHelper.animateBetweenAnglesMth(4.7474F, -1.4147F, continuousAnimationTick, 0.085F);
				part.zRot = MathHelper.animateBetweenAnglesMth(-20.0369F, -11.4144F, continuousAnimationTick, 0.085F);
			})
			.build();
	
	public static final PlayerAnimation RUBBING_BELLY_P6 = PlayerAnimation.builder(PlayerAnimationInfos.RUBBING_BELLY_P6)
			.addPartAnimation("right_arm", (part, continuousAnimationTick) -> {		
				part.xRot = MathHelper.animateBetweenAnglesMth(-40.1427F, -60.7642F, continuousAnimationTick, 0.085F);
				part.yRot = MathHelper.animateBetweenAnglesMth(-6.3807F, 5.1363F, continuousAnimationTick, 0.085F);
				part.zRot = MathHelper.animateBetweenAnglesMth(21.3743F, 12.4398F, continuousAnimationTick, 0.085F);
			})
			.addPartAnimation("left_arm", (part, continuousAnimationTick) -> {			
				part.xRot = MathHelper.animateBetweenAnglesMth(-61.2504F, -45.1963F, continuousAnimationTick, 0.085F);
				part.yRot = MathHelper.animateBetweenAnglesMth(4.7474F, -1.4147F, continuousAnimationTick, 0.085F);
				part.zRot = MathHelper.animateBetweenAnglesMth(-25.0369F, -17.4144F, continuousAnimationTick, 0.085F);
			})
			.build();
	
	public static final PlayerAnimation RUBBING_BELLY_P7 = PlayerAnimation.builder(PlayerAnimationInfos.RUBBING_BELLY_P7)
			.addPartAnimation("right_arm", (part, continuousAnimationTick) -> {		
				part.xRot = MathHelper.animateBetweenAnglesMth(-40.4606F, -60.4489F, continuousAnimationTick, 0.085F);
				part.yRot = MathHelper.animateBetweenAnglesMth(-3.1454F, 9.4933F, continuousAnimationTick, 0.085F);
				part.zRot = MathHelper.animateBetweenAnglesMth(29.2003F, 9.9134F, continuousAnimationTick, 0.085F);
			})
			.addPartAnimation("left_arm", (part, continuousAnimationTick) -> {			
				part.xRot = MathHelper.animateBetweenAnglesMth(-61.3575F, -44.9997F, continuousAnimationTick, 0.085F);
				part.yRot = MathHelper.animateBetweenAnglesMth(0.3609F, -4.9574F, continuousAnimationTick, 0.085F);
				part.zRot = MathHelper.animateBetweenAnglesMth(-27.4395F, -15.9488F, continuousAnimationTick, 0.085F);
			})
			.build();
	
	public static final PlayerAnimation RUBBING_BELLY_P8 = PlayerAnimation.builder(PlayerAnimationInfos.RUBBING_BELLY_P8)
			.addPartAnimation("right_arm", (part, continuousAnimationTick) -> {		
				part.xRot = MathHelper.animateBetweenAnglesMth(-40.4606F, -60.4489F, continuousAnimationTick, 0.085F);
				part.yRot = MathHelper.animateBetweenAnglesMth(-3.1454F, 9.4933F, continuousAnimationTick, 0.085F);
				part.zRot = MathHelper.animateBetweenAnglesMth(33.2003F, 13.9134F, continuousAnimationTick, 0.085F);
			})		
			.addPartAnimation("left_arm", (part, continuousAnimationTick) -> {			
				part.xRot = MathHelper.animateBetweenAnglesMth(-61.3575F, -44.9997F, continuousAnimationTick, 0.085F);
				part.yRot = MathHelper.animateBetweenAnglesMth(0.3609F, -4.9574F, continuousAnimationTick, 0.085F);
				part.zRot = MathHelper.animateBetweenAnglesMth(-32.4395F, -20.9488F, continuousAnimationTick, 0.085F);
			})
			.build();
}
