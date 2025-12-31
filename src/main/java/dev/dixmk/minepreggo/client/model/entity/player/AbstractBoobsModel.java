package dev.dixmk.minepreggo.client.model.entity.player;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import dev.dixmk.minepreggo.client.jiggle.WrapperBoobsJiggle;
import dev.dixmk.minepreggo.init.MinepreggoModMobEffects;
import dev.dixmk.minepreggo.world.item.IMaternityArmor;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractBoobsModel extends HierarchicalModel<AbstractClientPlayer> {
	private final ModelPart root;
	public final ModelPart body;
	public final ModelPart boobs;
	public final ModelPart rightBoob;
	public final ModelPart leftBoob;
	protected final WrapperBoobsJiggle boobsJiggle;
	
	protected float milkingBoobsXScale = 1.15F;
	protected float milkingBoobsYScale = 1.05F;
	protected float milkingBoobsZScale = 1.25F;
	protected float milkingBoobsYPos = -0.42F;
	
	protected AbstractBoobsModel(ModelPart root, WrapperBoobsJiggle boobsJiggle) {
		this.root = root;
		this.body = root.getChild("body");
		this.boobs = this.body.getChild("boobs");
		this.rightBoob = this.boobs.getChild("right_boob");
		this.leftBoob = this.boobs.getChild("left_boob");
		this.boobsJiggle = boobsJiggle;
	}
	
	@Override
	public ModelPart root() {
		return root;
	}
	
	@Override
	public void setupAnim(AbstractClientPlayer entity, float limbSwing, float limbSwingAmount, 
            float ageInTicks, float netHeadYaw, float headPitch) {	      
		
		this.root.getAllParts().forEach(ModelPart::resetPose);
			
		final var armor = entity.getItemBySlot(EquipmentSlot.CHEST);	
		if (armor.isEmpty()) {
			animBoobs(entity);
		}
		else { 		
			if (armor.getItem() instanceof IMaternityArmor maternityArmor && maternityArmor.areBoobsExposed()) {
				animBoobs(entity);
			}
			else if (boobs.visible) {
	    		boobs.visible = false;
	    	}
		}	
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		this.body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
	
	private void animBoobs(AbstractClientPlayer entity) {
		boobsJiggle.setupAnim(entity, boobs, leftBoob, rightBoob);
		
		if (entity.hasEffect(MinepreggoModMobEffects.LACTATION.get())) {
			boobs.y -= 0.42F;		
			boobs.xScale = 1.4F;
			boobs.yScale = 1.2F;
			boobs.zScale = 1.3F;	
		}
			
		if (!boobs.visible) {
    		boobs.visible = true;
    	}
	}
}

