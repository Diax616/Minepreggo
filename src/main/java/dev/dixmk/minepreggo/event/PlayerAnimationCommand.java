package dev.dixmk.minepreggo.event;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.SuggestionProvider;

import dev.dixmk.minepreggo.common.animation.PlayerAnimationRegistry;
import dev.dixmk.minepreggo.server.ServerPlayerAnimationManager;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Collection;

@Mod.EventBusSubscriber
public class PlayerAnimationCommand {
    
	private PlayerAnimationCommand() {}
	
    private static final SuggestionProvider<CommandSourceStack> ANIMATION_SUGGESTIONS = (context, builder) -> {
    	PlayerAnimationRegistry.getInstance().getAllAnimationNames().forEach(builder::suggest);
        return builder.buildFuture();
    };
    
    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();
        
        dispatcher.register(
            Commands.literal("anim")
                .requires(source -> source.hasPermission(2)) // Op level 2
                .then(Commands.literal("play")
                    .then(Commands.argument("animation", StringArgumentType.string())
                        .suggests(ANIMATION_SUGGESTIONS)
                        .executes(context -> playAnimation(context, context.getSource().getPlayerOrException()))
                        .then(Commands.argument("target", EntityArgument.players())
                            .executes(context -> playAnimationOnTargets(context))
                        )
                    )
                )
                .then(Commands.literal("stop")
                    .executes(context -> stopAnimation(context, context.getSource().getPlayerOrException()))
                    .then(Commands.argument("target", EntityArgument.players())
                        .executes(context -> stopAnimationOnTargets(context))
                    )
                )
                .then(Commands.literal("list")
                    .executes(PlayerAnimationCommand::listAnimations)
                )
        );
    }
    
    private static int playAnimation(CommandContext<CommandSourceStack> context, ServerPlayer player) {
        String animationName = StringArgumentType.getString(context, "animation");
        
        if (PlayerAnimationRegistry.getInstance().getAnimation(animationName) == null) {
            context.getSource().sendFailure(Component.literal("Animation '" + animationName + "' not found!"));
            return 0;
        }
        
        ServerPlayerAnimationManager.getInstance().triggerAnimation(player, animationName);
        context.getSource().sendSuccess(
            () -> Component.literal("Playing animation '" + animationName + "' on " + player.getName().getString()),
            true
        );
        return 1;
    }
    
    private static int playAnimationOnTargets(CommandContext<CommandSourceStack> context) {
        String animationName = StringArgumentType.getString(context, "animation");
        
        if (PlayerAnimationRegistry.getInstance().getAnimation(animationName) == null) {
            context.getSource().sendFailure(Component.literal("Animation '" + animationName + "' not found!"));
            return 0;
        }
        
        try {
            Collection<ServerPlayer> targets = EntityArgument.getPlayers(context, "target");
            
            for (ServerPlayer player : targets) {
            	ServerPlayerAnimationManager.getInstance().triggerAnimation(player, animationName);
            }
            
            int count = targets.size();
            context.getSource().sendSuccess(
                () -> Component.literal("Playing animation '" + animationName + "' on " + count + " player(s)"),
                true
            );
            return count;
        } catch (Exception e) {
            context.getSource().sendFailure(Component.literal("Failed to get target players"));
            return 0;
        }
    }
    
    private static int stopAnimation(CommandContext<CommandSourceStack> context, ServerPlayer player) {
    	ServerPlayerAnimationManager.getInstance().stopAnimation(player);
        context.getSource().sendSuccess(
            () -> Component.literal("Stopped animation on " + player.getName().getString()),
            true
        );
        return 1;
    }
    
    private static int stopAnimationOnTargets(CommandContext<CommandSourceStack> context) {
        try {
            Collection<ServerPlayer> targets = EntityArgument.getPlayers(context, "target");
            
            for (ServerPlayer player : targets) {
            	ServerPlayerAnimationManager.getInstance().stopAnimation(player);
            }
            
            int count = targets.size();
            context.getSource().sendSuccess(
                () -> Component.literal("Stopped animations on " + count + " player(s)"),
                true
            );
            return count;
        } catch (Exception e) {
            context.getSource().sendFailure(Component.literal("Failed to get target players"));
            return 0;
        }
    }
    
    private static int listAnimations(CommandContext<CommandSourceStack> context) {
        Collection<String> animations = PlayerAnimationRegistry.getInstance().getAllAnimationNames();
        
        if (animations.isEmpty()) {
            context.getSource().sendSuccess(
                () -> Component.literal("No animations registered"),
                false
            );
            return 0;
        }
        
        context.getSource().sendSuccess(
            () -> Component.literal("Available animations (" + animations.size() + "):"),
            false
        );
        
        for (String animName : animations) {
            context.getSource().sendSuccess(
                () -> Component.literal("  - " + animName),
                false
            );
        }
        
        return animations.size();
    }
}