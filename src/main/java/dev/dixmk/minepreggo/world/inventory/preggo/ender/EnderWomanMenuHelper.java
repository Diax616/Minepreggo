package dev.dixmk.minepreggo.world.inventory.preggo.ender;

import org.checkerframework.checker.nullness.qual.NonNull;

import dev.dixmk.minepreggo.world.entity.preggo.ender.AbstractTamableEnderWoman;
import dev.dixmk.minepreggo.world.entity.preggo.ender.TamableMonsterEnderWoman;
import io.netty.buffer.Unpooled;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraftforge.network.NetworkHooks;

public class EnderWomanMenuHelper {

	private EnderWomanMenuHelper() {}
	
	public static<E extends AbstractTamableEnderWoman> void showInventoryMenu(@NonNull ServerPlayer serverPlayer, @NonNull E enderWoman) {			
		final var enderWomanId = enderWoman.getId();
		final var enderWomanClass = enderWoman.getClass();
 		final var blockPos = serverPlayer.blockPosition();
		
		NetworkHooks.openScreen(serverPlayer, new MenuProvider() {
            @Override
            public Component getDisplayName() {
                return Component.literal("CreeperGirlMainGUI");
            }

            @Override
            public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
                FriendlyByteBuf packetBuffer = new FriendlyByteBuf(Unpooled.buffer());
                packetBuffer.writeBlockPos(blockPos);
                packetBuffer.writeVarInt(enderWomanId);
                
                if (enderWomanClass == TamableMonsterEnderWoman.class) {
                	return new MonsterEnderWomanInventoryMenu(id, inventory, packetBuffer);
                }
                else {
                    throw new IllegalArgumentException("Unsupported creeper girl menu: " + enderWoman.getSimpleNameOrCustom());
                } 
            }
		}, buf -> {
				buf.writeBlockPos(blockPos);
			    buf.writeVarInt(enderWomanId); 
		});	
	}
	
	public static<E extends AbstractTamableEnderWoman> void showMainMenu(@NonNull ServerPlayer serverPlayer, @NonNull E enderWoman) {			
		final var blockPos = serverPlayer.blockPosition();	
		final var enderWomanId = enderWoman.getId();
		final var canPickUpLoot = enderWoman.canPickUpLoot();
		final var canBreakBlocks = enderWoman.canBreakBlocks();
		final var enderWomanClass = enderWoman.getClass();
		
		NetworkHooks.openScreen(serverPlayer,new MenuProvider() {
            @Override
            public Component getDisplayName() {
                return Component.literal("CreeperGirlMainGUI");
            }

            @Override
            public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
                FriendlyByteBuf packetBuffer = new FriendlyByteBuf(Unpooled.buffer());
                packetBuffer.writeBlockPos(blockPos);
                packetBuffer.writeVarInt(enderWomanId);
                packetBuffer.writeBoolean(canPickUpLoot);
                packetBuffer.writeBoolean(canBreakBlocks);
                
                if (enderWomanClass == TamableMonsterEnderWoman.class) {
                	packetBuffer.writeBoolean(enderWoman.getGenderedData().isPregnant());
                	return new MonsterEnderWomanMainMenu(id, inventory, packetBuffer);
                }
                else {
                    throw new IllegalArgumentException("Unsupported creeper girl menu: " + enderWoman.getSimpleNameOrCustom());
                } 
            }
        } , buf -> {		  	
			buf.writeBlockPos(blockPos);
		    buf.writeVarInt(enderWomanId);
		    buf.writeBoolean(canPickUpLoot);
		    buf.writeBoolean(canBreakBlocks);
            
		    if (enderWomanClass == TamableMonsterEnderWoman.class) {
				buf.writeBoolean(enderWoman.getGenderedData().isPregnant());
		    }
		});		
		
	}
}
