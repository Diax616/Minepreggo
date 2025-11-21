package dev.dixmk.minepreggo.world.inventory.preggo.creeper;

import org.checkerframework.checker.nullness.qual.NonNull;

import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractTamableCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableCreeperGirlP0;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableCreeperGirlP1;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableCreeperGirlP2;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableCreeperGirlP3;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableCreeperGirlP4;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableCreeperGirlP5;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableCreeperGirlP6;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableCreeperGirlP7;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableCreeperGirlP8;
import io.netty.buffer.Unpooled;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraftforge.network.NetworkHooks;

public class CreeperGirlMenuHelper {

	private CreeperGirlMenuHelper() {}
	
	public static<E extends AbstractTamableCreeperGirl<?>> void showInventoryMenu(@NonNull ServerPlayer serverPlayer, @NonNull E creeperGirl) {			
		final var creeperGirlId = creeperGirl.getId();
		final var creeperGirlClass = creeperGirl.getClass();
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
                packetBuffer.writeVarInt(creeperGirlId);
                
                if (creeperGirlClass == TamableCreeperGirl.class) {
                	return new CreeperGirlInventoryMenu(id, inventory, packetBuffer);
                }
                else if (creeperGirlClass == TamableCreeperGirlP0.class) {
                	return new CreeperGirlP0InventoryMenu(id, inventory, packetBuffer);
                }
                else if (creeperGirlClass == TamableCreeperGirlP1.class) {
                	return new CreeperGirlP1InventoryMenu(id, inventory, packetBuffer);
                }
                else if (creeperGirlClass == TamableCreeperGirlP2.class) {
                	return new CreeperGirlP2InventoryMenu(id, inventory, packetBuffer);
                }
                else if (creeperGirlClass == TamableCreeperGirlP3.class) {
                	return new CreeperGirlP3InventoryMenu(id, inventory, packetBuffer);
                }
                else if (creeperGirlClass == TamableCreeperGirlP4.class) {
                	return new CreeperGirlP4InventoryMenu(id, inventory, packetBuffer);
                }
                else if (creeperGirlClass == TamableCreeperGirlP5.class) {
                	return new CreeperGirlP5InventoryMenu(id, inventory, packetBuffer);
                }
                else if (creeperGirlClass == TamableCreeperGirlP6.class) {
                	return new CreeperGirlP6InventoryMenu(id, inventory, packetBuffer);
                }
                else if (creeperGirlClass == TamableCreeperGirlP7.class) {
                	return new CreeperGirlP7InventoryMenu(id, inventory, packetBuffer);
                }
                else if (creeperGirlClass == TamableCreeperGirlP8.class) {
                	return new CreeperGirlP8InventoryMenu(id, inventory, packetBuffer);
                }
                else {
                    throw new IllegalArgumentException("Unsupported creeper girl menu: " + creeperGirlClass.getSimpleName());
                } 
            }
		}, buf -> {
				buf.writeBlockPos(blockPos);
			    buf.writeVarInt(creeperGirlId); 
		});	
	}
	
	
	public static<E extends AbstractTamableCreeperGirl<?>> void showMainMenu(@NonNull ServerPlayer serverPlayer, @NonNull E creeperGirl) {			
		final var blockPos = serverPlayer.blockPosition();	
		final var creeperGirlId = creeperGirl.getId();
		final var creeperGirlClass = creeperGirl.getClass();
		
		NetworkHooks.openScreen(serverPlayer,new MenuProvider() {
            @Override
            public Component getDisplayName() {
                return Component.literal("CreeperGirlMainGUI");
            }

            @Override
            public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
                FriendlyByteBuf packetBuffer = new FriendlyByteBuf(Unpooled.buffer());
                packetBuffer.writeBlockPos(blockPos);
                packetBuffer.writeVarInt(creeperGirlId);
                
                if (creeperGirlClass == TamableCreeperGirl.class) {
                	return new CreeperGirlMainMenu(id, inventory, packetBuffer);
                }
                else if (creeperGirlClass == TamableCreeperGirlP0.class) {
                	return new CreeperGirlP0MainMenu(id, inventory, packetBuffer);
                }
                else if (creeperGirlClass == TamableCreeperGirlP1.class) {
                	return new CreeperGirlP1MainMenu(id, inventory, packetBuffer);
                }
                else if (creeperGirlClass == TamableCreeperGirlP2.class) {
                	return new CreeperGirlP2MainMenu(id, inventory, packetBuffer);
                }
                else if (creeperGirlClass == TamableCreeperGirlP3.class) {
                	return new CreeperGirlP3MainMenu(id, inventory, packetBuffer);
                }
                else if (creeperGirlClass == TamableCreeperGirlP4.class) {
                	return new CreeperGirlP4MainMenu(id, inventory, packetBuffer);
                }
                else if (creeperGirlClass == TamableCreeperGirlP5.class) {
                	return new CreeperGirlP5MainMenu(id, inventory, packetBuffer);
                }
                else if (creeperGirlClass == TamableCreeperGirlP6.class) {
                	return new CreeperGirlP6MainMenu(id, inventory, packetBuffer);
                }
                else if (creeperGirlClass == TamableCreeperGirlP7.class) {
                	return new CreeperGirlP7MainMenu(id, inventory, packetBuffer);
                }
                else if (creeperGirlClass == TamableCreeperGirlP8.class) {
                	return new CreeperGirlP8MainMenu(id, inventory, packetBuffer);
                }
                else {
                    throw new IllegalArgumentException("Unsupported creeper girl menu: " + creeperGirlClass.getSimpleName());
                } 
            }
        } , buf -> {		  	
			buf.writeBlockPos(blockPos);
		    buf.writeVarInt(creeperGirlId);
		});		
	}
	
	
	
}
