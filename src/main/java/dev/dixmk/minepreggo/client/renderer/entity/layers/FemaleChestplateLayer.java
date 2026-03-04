package dev.dixmk.minepreggo.client.renderer.entity.layers;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import dev.dixmk.minepreggo.client.model.armor.FemaleChestplateModel;
import dev.dixmk.minepreggo.client.model.armor.FemaleMaternityChestplateModel;
import dev.dixmk.minepreggo.client.model.geom.MinepreggoModelLayers;
import dev.dixmk.minepreggo.world.item.FemaleChestPlateItem;
import dev.dixmk.minepreggo.world.item.IMaternityArmor;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.armortrim.ArmorTrim;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class FemaleChestplateLayer<T extends LivingEntity, M extends HumanoidModel<T>> extends RenderLayer<T, M> {
    private static final Map<String, ResourceLocation> ARMOR_LOCATION_CACHE = Maps.newHashMap();
    private final FemaleChestplateModel<T> modelBase;
    private final FemaleMaternityChestplateModel<T> modelP1;
    private final FemaleMaternityChestplateModel<T> modelP2;
    private final FemaleMaternityChestplateModel<T> modelP3;
    private final FemaleMaternityChestplateModel<T> modelP4;
    private final TextureAtlas armorTrimAtlas;

    public FemaleChestplateLayer(RenderLayerParent<T, M> parent, ModelManager modelManager, EntityModelSet modelSet) {
        super(parent);
        this.modelBase = new FemaleChestplateModel<>(modelSet.bakeLayer(MinepreggoModelLayers.FEMALE_CHESTPLATE));
        this.modelP1 = new FemaleMaternityChestplateModel<>(modelSet.bakeLayer(MinepreggoModelLayers.FEMALE_MATERNITY_CHESTPLATE_P1));
        this.modelP2 = new FemaleMaternityChestplateModel<>(modelSet.bakeLayer(MinepreggoModelLayers.FEMALE_MATERNITY_CHESTPLATE_P2));
        this.modelP3 = new FemaleMaternityChestplateModel<>(modelSet.bakeLayer(MinepreggoModelLayers.FEMALE_MATERNITY_CHESTPLATE_P3));
        this.modelP4 = new FemaleMaternityChestplateModel<>(modelSet.bakeLayer(MinepreggoModelLayers.FEMALE_MATERNITY_CHESTPLATE_P4));
        this.armorTrimAtlas = modelManager.getAtlas(Sheets.ARMOR_TRIMS_SHEET);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTick, float ageInTicks, float netHeadYaw, float headPitch) {
        this.renderChestplate(poseStack, bufferSource, entity, packedLight);
    }

    private void renderChestplate(PoseStack poseStack, MultiBufferSource bufferSource, T entity, int packedLight) {
        ItemStack itemStack = entity.getItemBySlot(EquipmentSlot.CHEST);
        if (!(itemStack.getItem() instanceof ArmorItem armorItem)) return;
        if (armorItem.getEquipmentSlot() != EquipmentSlot.CHEST) return;

        FemaleChestplateModel<T> model = this.getModel(armorItem);
        String layerPath = armorItem.getArmorTexture(itemStack, entity, EquipmentSlot.CHEST, null);
  
        if (model == null || layerPath == null) return;
        
        M parentModel = this.getParentModel();

        model.body.copyFrom(parentModel.body);
        model.rightArm.copyFrom(parentModel.rightArm);
        model.leftArm.copyFrom(parentModel.leftArm);
        model.attackTime = parentModel.attackTime;
        model.riding = parentModel.riding;
        model.young = parentModel.young;

        if (armorItem instanceof DyeableLeatherItem dyeableItem) {
            int color = dyeableItem.getColor(itemStack);
            float r = (color >> 16 & 255) / 255.0F;
            float g = (color >> 8  & 255) / 255.0F;
            float b = (color & 255) / 255.0F;
            this.renderModel(poseStack, bufferSource, packedLight, armorItem, model, r, g, b, layerPath);
            this.renderModel(poseStack, bufferSource, packedLight, armorItem, model, 1.0F, 1.0F, 1.0F, armorItem.getArmorTexture(itemStack, entity, EquipmentSlot.CHEST, "overlay"));
        } else {
            this.renderModel(poseStack, bufferSource, packedLight, armorItem, model, 1.0F, 1.0F, 1.0F, layerPath);
        }

        ArmorTrim.getTrim(entity.level().registryAccess(), itemStack).ifPresent(trim ->
            this.renderTrim(armorItem.getMaterial(), poseStack, bufferSource, packedLight, trim, model)
        );

        if (itemStack.hasFoil()) {
            this.renderGlint(poseStack, bufferSource, packedLight, model);
        }
    }

    private @Nullable FemaleChestplateModel<T> getModel(ArmorItem armorItem) {
        if (armorItem instanceof FemaleChestPlateItem) {    	
        	if (armorItem instanceof IMaternityArmor maternityArmor) {
        		PregnancyPhase phase = maternityArmor.getCurrentPregnancyPhase();	
        		if (phase == PregnancyPhase.P1) {
					return modelP1;
				}
				else if (phase == PregnancyPhase.P2) {
					return modelP2;
				}
				else if (phase == PregnancyPhase.P3) {
					return modelP3;
				}
				else if (phase == PregnancyPhase.P4) {
					return modelP4;
				}
			} 
        	return modelBase;
        }
        
        return null;
    }
    
    private void renderModel(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, ArmorItem armorItem, FemaleChestplateModel<T> model, float r, float g, float b, String path) {
        ResourceLocation texture = getArmorLocation(armorItem, path);
        VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.armorCutoutNoCull(texture));
        model.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, r, g, b, 1.0F);
    }

    private void renderTrim(ArmorMaterial material, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, ArmorTrim trim, FemaleChestplateModel<T> model) {
        TextureAtlasSprite sprite = this.armorTrimAtlas.getSprite(trim.outerTexture(material));
        VertexConsumer vertexConsumer = sprite.wrap(bufferSource.getBuffer(Sheets.armorTrimsSheet()));
        model.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
    }

    private void renderGlint(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, FemaleChestplateModel<T> model) {
        model.renderToBuffer(poseStack, bufferSource.getBuffer(RenderType.armorEntityGlint()), packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
    }

    @SuppressWarnings("removal")
    private ResourceLocation getArmorLocation(ArmorItem armorItem, String path) {
        return ARMOR_LOCATION_CACHE.computeIfAbsent(path, ResourceLocation::new);
    }
}