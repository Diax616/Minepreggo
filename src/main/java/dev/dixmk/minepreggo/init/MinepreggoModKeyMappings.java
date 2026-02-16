package dev.dixmk.minepreggo.init;

import org.lwjgl.glfw.GLFW;

import com.mojang.blaze3d.platform.InputConstants;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.settings.KeyConflictContext;

import net.minecraft.client.KeyMapping;
import net.minecraft.network.chat.Component;

@OnlyIn(Dist.CLIENT)
public class MinepreggoModKeyMappings {
	
	private MinepreggoModKeyMappings() {}
	
    public static final String KEY_CATEGORY = Component.translatable("key.categories.minepreggo").getString();
    
    public static final KeyMapping RUB_BELLY_KEY = new KeyMapping(
    	Component.translatable("key.minepreggo.rub_belly").getString(),
    	KeyConflictContext.IN_GAME,
        InputConstants.Type.KEYSYM,
        GLFW.GLFW_KEY_R,
        KEY_CATEGORY
    	);
    
    public static final KeyMapping TELEPORT_WITH_ENDER_WOMAN = new KeyMapping(
    	Component.translatable("key.minepreggo.teleport_with_ender_woman").getString(),
    	KeyConflictContext.IN_GAME,
    	InputConstants.Type.KEYSYM,
    	GLFW.GLFW_KEY_U,
    	KEY_CATEGORY
		);
    
    public static final KeyMapping TELEPORT_USING_ENDER_POWER = new KeyMapping(
        	Component.translatable("key.minepreggo.teleport_using_ender_power").getString(),
        	KeyConflictContext.IN_GAME,
        	InputConstants.Type.KEYSYM,
        	GLFW.GLFW_KEY_I,
        	KEY_CATEGORY
    		);
    
    public static final KeyMapping SHOOT_DRAGON_FIRE_BALL = new KeyMapping(
        	Component.translatable("key.minepreggo.shoot_dragon_fire_ball").getString(),
        	KeyConflictContext.IN_GAME,
        	InputConstants.Type.KEYSYM,
        	GLFW.GLFW_KEY_O,
        	KEY_CATEGORY
    		);
}


