package dev.dixmk.minepreggo.client.renderer.preggo.zombie;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.client.model.entity.preggo.zombie.AbstractZombieGirlModel;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.AbstractZombieGirl;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractZombieGirlRenderer<E extends AbstractZombieGirl, M extends AbstractZombieGirlModel<E>> extends HumanoidMobRenderer<E, M> {
	
	protected static final ResourceLocation  ZOMBIE_GIRL_P0_LOCATION = ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/zombie/zombie_girl_p0.png");
	protected static final ResourceLocation  ZOMBIE_GIRL_P1_LOCATION = ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/zombie/zombie_girl_p1.png");
	protected static final ResourceLocation  ZOMBIE_GIRL_P2_LOCATION = ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/zombie/zombie_girl_p2.png");
	protected static final ResourceLocation  ZOMBIE_GIRL_P3_LOCATION = ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/zombie/zombie_girl_p3.png");
	protected static final ResourceLocation  ZOMBIE_GIRL_P4_LOCATION = ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/zombie/zombie_girl_p4.png");
	protected static final ResourceLocation  ZOMBIE_GIRL_P5_LOCATION = ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/zombie/zombie_girl_p5.png");
	protected static final ResourceLocation  ZOMBIE_GIRL_P6_LOCATION = ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/zombie/zombie_girl_p6.png");
	protected static final ResourceLocation  ZOMBIE_GIRL_P7_LOCATION = ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/zombie/zombie_girl_p7.png");
	protected static final ResourceLocation  ZOMBIE_GIRL_P8_LOCATION = ResourceLocation.fromNamespaceAndPath(MinepreggoMod.MODID, "textures/entity/preggo/zombie/zombie_girl_p8.png");
	
	protected AbstractZombieGirlRenderer(EntityRendererProvider.Context context, M main, M inner, M outter) {
		super(context, main, 0.5F);
		this.addLayer(new HumanoidArmorLayer<>(this, inner, outter, context.getModelManager()));
	}
}
