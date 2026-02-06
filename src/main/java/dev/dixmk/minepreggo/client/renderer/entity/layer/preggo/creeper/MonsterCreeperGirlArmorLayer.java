package dev.dixmk.minepreggo.client.renderer.entity.layer.preggo.creeper;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import java.util.Locale;
import java.util.Map;
import javax.annotation.Nullable;

import dev.dixmk.minepreggo.client.model.entity.preggo.creeper.quadruped.AbstractCreeperGirlModel;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractCreeperGirl;
import net.minecraft.client.model.geom.ModelPart;
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
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.armortrim.ArmorTrim;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MonsterCreeperGirlArmorLayer<E extends AbstractCreeperGirl, M extends AbstractCreeperGirlModel<E>> extends RenderLayer<E, M> {
    
    private static final Map<String, ResourceLocation> ARMOR_LOCATION_CACHE = Maps.newHashMap();
    private final M armorModel;
    private final TextureAtlas armorTrimAtlas;

    public MonsterCreeperGirlArmorLayer(RenderLayerParent<E, M> renderer, M armorModel, ModelManager modelManager) {
        super(renderer);
        this.armorModel = armorModel;
        this.armorTrimAtlas = modelManager.getAtlas(Sheets.ARMOR_TRIMS_SHEET);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, 
                       E entity, float limbSwing, float limbSwingAmount, float partialTick, 
                       float ageInTicks, float netHeadYaw, float headPitch) {
        ItemStack itemstack = entity.getItemBySlot(EquipmentSlot.HEAD);
        if (!(itemstack.getItem() instanceof ArmorItem armorItem)) return;
        if (armorItem.getEquipmentSlot() != EquipmentSlot.HEAD) return;

        // Copy pose from parent model to armor model using getHead()
        ModelPart parentHead = this.getParentModel().getHead();
        ModelPart armorHead = this.armorModel.getHead();
        armorHead.copyFrom(parentHead);

        // Render armor
        if (armorItem instanceof DyeableLeatherItem dyeable) {
            int color = dyeable.getColor(itemstack);
            float r = (color >> 16 & 255) / 255.0F;
            float g = (color >> 8 & 255) / 255.0F;
            float b = (color & 255) / 255.0F;
            this.renderHead(poseStack, bufferSource, packedLight, armorHead, r, g, b, 
                this.getArmorResource(entity, itemstack, EquipmentSlot.HEAD, null));
            this.renderHead(poseStack, bufferSource, packedLight, armorHead, 1.0F, 1.0F, 1.0F, 
                this.getArmorResource(entity, itemstack, EquipmentSlot.HEAD, "overlay"));
        } else {
            this.renderHead(poseStack, bufferSource, packedLight, armorHead, 1.0F, 1.0F, 1.0F, 
                this.getArmorResource(entity, itemstack, EquipmentSlot.HEAD, null));
        }

        // Render armor trim
        ArmorTrim.getTrim(entity.level().registryAccess(), itemstack).ifPresent(trim -> {
            this.renderTrim(armorItem.getMaterial(), poseStack, bufferSource, packedLight, trim, armorHead);
        });

        // Render enchantment glint
        if (itemstack.hasFoil()) {
            this.renderGlint(poseStack, bufferSource, packedLight, armorHead);
        }
    }

    private void renderHead(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, 
                            ModelPart head, float r, float g, float b, ResourceLocation armorResource) {
        VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.armorCutoutNoCull(armorResource));
        head.render(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, r, g, b, 1.0F);
    }

    private void renderTrim(ArmorMaterial material, PoseStack poseStack, 
                            MultiBufferSource bufferSource, int packedLight, ArmorTrim trim, ModelPart head) {
        TextureAtlasSprite sprite = this.armorTrimAtlas.getSprite(trim.outerTexture(material));
        VertexConsumer vertexConsumer = sprite.wrap(bufferSource.getBuffer(Sheets.armorTrimsSheet()));
        head.render(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
    }

    private void renderGlint(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, ModelPart head) {
        head.render(poseStack, bufferSource.getBuffer(RenderType.armorEntityGlint()), 
            packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
    }

    @SuppressWarnings("removal")
	private ResourceLocation getArmorResource(E entity, ItemStack stack, EquipmentSlot slot, @Nullable String type) {
        ArmorItem item = (ArmorItem) stack.getItem();
        String texture = item.getMaterial().getName();
        String domain = "minecraft";
        int idx = texture.indexOf(':');
        if (idx != -1) {
            domain = texture.substring(0, idx);
            texture = texture.substring(idx + 1);
        }
        String path = String.format(Locale.ROOT, "%s:textures/models/armor/%s_layer_1%s.png", 
            domain, texture, type == null ? "" : "_" + type);
        
        return ARMOR_LOCATION_CACHE.computeIfAbsent(path, ResourceLocation::new);
    }
}
