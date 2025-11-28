package dev.dixmk.minepreggo.client.gui.component;

import java.util.Optional;

import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ObjectSelectionList;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PreggoMobScrollList extends ObjectSelectionList<PreggoMobScrollList.PreggoMobEntry> {
	
    public PreggoMobScrollList(Minecraft mc, int width, int height, int top, int bottom, int itemHeight) {
        super(mc, width, height, top, bottom, itemHeight);   
        this.setRenderBackground(false);
        this.setRenderTopAndBottom(false);
    }
    
    public void addEntry(String entityName, ResourceLocation icon, int textureWidth, int textureHeight) {
        this.addEntry(new PreggoMobEntry(entityName, icon, textureWidth, textureHeight));
    }
    
    public void addEntry(String entityName, ResourceLocation icon, int textureWidth, int textureHeight, Optional<Runnable> onClick) {
        this.addEntry(new PreggoMobEntry(entityName, icon, textureWidth, textureHeight, onClick));
    } 
    
    @OnlyIn(Dist.CLIENT)
    class PreggoMobEntry extends ObjectSelectionList.Entry<PreggoMobEntry> {
        private final String entityName;    
        private final Optional<Runnable> onClick;   
        private final int textureWidth;
        private final int textureHeight;
        private final ResourceLocation icon;
        
        public PreggoMobEntry(String entityName, ResourceLocation icon, int textureWidth, int textureHeight, Optional<Runnable> onClick) {
            this.entityName = entityName;
            this.icon = icon;
			this.textureHeight = textureHeight;
			this.textureWidth = textureWidth;
			this.onClick = onClick;
        }

        public PreggoMobEntry(String entityName, ResourceLocation icon, int textureWidth, int textureHeight) {
        	this(entityName, icon, textureWidth, textureHeight, Optional.empty());
        }
           
        @Override
        public void render(GuiGraphics guiGraphics, int index, int top, int left, int width, int height,
                           int mouseX, int mouseY, boolean hovered, float partialTick) {
    		RenderSystem.setShaderColor(1, 1, 1, 1);
    		RenderSystem.enableBlend();
    		RenderSystem.defaultBlendFunc();      
    		guiGraphics.drawString(minecraft.font, this.entityName, left + 25, top + 3, 0xFFFFFF);     
            guiGraphics.blit(icon, left, top, 16, 16, 8, 8, 8, 8, textureWidth, textureHeight);      	          
            
    		if (mouseX > left && mouseX < left + 16 && mouseY > top && mouseY < top + 16)
    			guiGraphics.renderTooltip(minecraft.font, Component.translatable("gui.minepreggo.scientific_illager.tooltip_make_medical_checkup"), mouseX, mouseY);

            
            RenderSystem.disableBlend();  
        }
        
        @Override
        public boolean isMouseOver(double mouseX, double mouseY) {
            return mouseX >= x0 && mouseX <= x0 + width &&
                   mouseY >= y0 && mouseY <= y0 + height;
        }
        
        @Override
        public boolean mouseClicked(double mouseX, double mouseY, int button) {   	
        	 if (button == 0) {
        		 minecraft.level.playLocalSound(minecraft.player.getX(), minecraft.player.getY(), minecraft.player.getZ(), SoundEvents.UI_BUTTON_CLICK.get(), SoundSource.NEUTRAL, 1F, 1F, false);
        		 this.onClick.ifPresent(e -> e.run());
        		 return true;
        	 }   
        	 return false;   	
        }

		@Override
		public Component getNarration() {
			return Component.literal("Searching PreggoMob");
		}
    }
}
