package dev.dixmk.minepreggo.client.model.entity;

import javax.annotation.Nullable;

import dev.dixmk.minepreggo.client.animation.preggo.BellyInflation;
import dev.dixmk.minepreggo.client.jiggle.EntityJiggleDataFactory;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractPregnantBellyModel<E extends LivingEntity> extends HierarchicalModel<E> {
	private final ModelPart root;
	public final ModelPart body;
	public final ModelPart boobs;
	public final ModelPart belly;
	public final ModelPart rightBoob;
	public final ModelPart leftBoob;
	protected final @Nullable BellyInflation bellyInflation;
	protected final boolean simpleBellyJiggle;
	protected final PregnancyPhase pregnancyPhase;
	protected final EntityJiggleDataFactory.JigglePositionConfig jiggleConfig;
	
	protected AbstractPregnantBellyModel(ModelPart root, @Nullable BellyInflation bellyInflation, boolean simpleBellyJiggle, PregnancyPhase pregnancyPhase) {
		this.root = root;
		this.body = root.getChild("body");
		this.boobs = this.body.getChild("boobs");
		this.rightBoob = this.boobs.getChild("right_boob");
		this.leftBoob = this.boobs.getChild("left_boob");
		this.belly = this.body.getChild("belly");
		this.bellyInflation = bellyInflation;
		this.simpleBellyJiggle = simpleBellyJiggle;
		this.pregnancyPhase = pregnancyPhase;
		this.jiggleConfig = EntityJiggleDataFactory.JigglePositionConfig.boobsAndBelly(this.boobs.y, this.belly.y);
	}
	
	@Override
	public ModelPart root() {
		return this.root;
	}
}
