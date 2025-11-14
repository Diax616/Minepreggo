package dev.dixmk.minepreggo.init;

import org.lwjgl.glfw.GLFW;

import com.mojang.blaze3d.platform.InputConstants;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.KeyMapping;


@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class MinepreggoModKeyMappings {
	
	private MinepreggoModKeyMappings() {}
	
    public static final String KEY_CATEGORY = "key.categories.minepreggo";
    
    public static final KeyMapping ANIMATION_KEY = new KeyMapping(
        "key.minepreggo.gameplay",
        KeyConflictContext.IN_GAME,
        InputConstants.Type.KEYSYM,
        GLFW.GLFW_KEY_R,
        KEY_CATEGORY
    );
    
    @SubscribeEvent
    public static void registerKeyBindings(RegisterKeyMappingsEvent event) {
        event.register(ANIMATION_KEY);
    }
}


