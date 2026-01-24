package dev.dixmk.minepreggo.client.model.entity.preggo;

import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class PregnantPreggoMobAnimator<E extends PreggoMob> extends HierarchicalModel<E> {

	private final ModelPart root;
	
    protected PregnantPreggoMobAnimator(ModelPart root) {
		this.root = root;	
    }
    
	@Override
	public ModelPart root() {
		return root;
	}
    
	@Override
	public void setupAnim(E preggoMob, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        resetPose();
        animateBelly(preggoMob, ageInTicks);
        animateAttack(preggoMob, ageInTicks);
        animateMovement(preggoMob, limbSwing, limbSwingAmount, ageInTicks);
        animatePregnancyPain(preggoMob, ageInTicks);
        animateLoopState(preggoMob, ageInTicks);
	}	
    
    protected void resetPose() {
        this.root().getAllParts().forEach(ModelPart::resetPose);
    }
    
    protected abstract void animateBelly(E zombieGirl, float ageInTicks);
    
    protected abstract void animateAttack(E zombieGirl, float ageInTicks);
    
    protected abstract void animateMovement(E zombieGirl, float limbSwing, float limbSwingAmount, float ageInTicks);
        
    protected abstract void animateLoopState(E zombieGirl, float ageInTicks);
    
    protected abstract void animatePregnancyPain(E zombieGirl, float ageInTicks);

}
