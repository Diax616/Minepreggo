package dev.dixmk.minepreggo.world.inventory.preggo.creeper;

import org.checkerframework.checker.nullness.qual.NonNull;

import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractTamableCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableHumanoidCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableHumanoidCreeperGirlP0;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableHumanoidCreeperGirlP1;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableHumanoidCreeperGirlP2;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableHumanoidCreeperGirlP3;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableHumanoidCreeperGirlP4;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableHumanoidCreeperGirlP5;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableHumanoidCreeperGirlP6;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableHumanoidCreeperGirlP7;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableHumanoidCreeperGirlP8;
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
	
	public static<E extends AbstractTamableCreeperGirl> void showInventoryMenu(@NonNull ServerPlayer serverPlayer, @NonNull E creeperGirl) {			
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
                
                if (creeperGirlClass == TamableHumanoidCreeperGirl.class) {
                	return new CreeperGirlInventoryMenu(id, inventory, packetBuffer);
                }
                else if (creeperGirlClass == TamableHumanoidCreeperGirlP0.class) {
                	return new CreeperGirlP0InventoryMenu(id, inventory, packetBuffer);
                }
                else if (creeperGirlClass == TamableHumanoidCreeperGirlP1.class) {
                	return new CreeperGirlP1InventoryMenu(id, inventory, packetBuffer);
                }
                else if (creeperGirlClass == TamableHumanoidCreeperGirlP2.class) {
                	return new CreeperGirlP2InventoryMenu(id, inventory, packetBuffer);
                }
                else if (creeperGirlClass == TamableHumanoidCreeperGirlP3.class) {
                	return new CreeperGirlP3InventoryMenu(id, inventory, packetBuffer);
                }
                else if (creeperGirlClass == TamableHumanoidCreeperGirlP4.class) {
                	return new CreeperGirlP4InventoryMenu(id, inventory, packetBuffer);
                }
                else if (creeperGirlClass == TamableHumanoidCreeperGirlP5.class) {
                	return new CreeperGirlP5InventoryMenu(id, inventory, packetBuffer);
                }
                else if (creeperGirlClass == TamableHumanoidCreeperGirlP6.class) {
                	return new CreeperGirlP6InventoryMenu(id, inventory, packetBuffer);
                }
                else if (creeperGirlClass == TamableHumanoidCreeperGirlP7.class) {
                	return new CreeperGirlP7InventoryMenu(id, inventory, packetBuffer);
                }
                else if (creeperGirlClass == TamableHumanoidCreeperGirlP8.class) {
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
	
	
	public static<E extends AbstractTamableCreeperGirl> void showMainMenu(@NonNull ServerPlayer serverPlayer, @NonNull E creeperGirl) {			
		final var blockPos = serverPlayer.blockPosition();	
		final var creeperGirlId = creeperGirl.getId();
		final var canPickUpLoot = creeperGirl.canPickUpLoot();
		final var canBreakBlocks = creeperGirl.canBreakBlocks();
		final var combatMode = creeperGirl.getCombatMode();
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
                packetBuffer.writeBoolean(canPickUpLoot);
                packetBuffer.writeBoolean(canBreakBlocks);
                packetBuffer.writeEnum(combatMode);
                
                if (creeperGirlClass == TamableHumanoidCreeperGirl.class) {
                	packetBuffer.writeBoolean(creeperGirl.getGenderedData().isPregnant());
                	return new CreeperGirlMainMenu(id, inventory, packetBuffer);
                }
                else if (creeperGirlClass == TamableHumanoidCreeperGirlP0.class) {
                	return new CreeperGirlP0MainMenu(id, inventory, packetBuffer);
                }
                else if (creeperGirlClass == TamableHumanoidCreeperGirlP1.class) {
                	return new CreeperGirlP1MainMenu(id, inventory, packetBuffer);
                }
                else if (creeperGirlClass == TamableHumanoidCreeperGirlP2.class) {
                	return new CreeperGirlP2MainMenu(id, inventory, packetBuffer);
                }
                else if (creeperGirlClass == TamableHumanoidCreeperGirlP3.class) {
                	return new CreeperGirlP3MainMenu(id, inventory, packetBuffer);
                }
                else if (creeperGirlClass == TamableHumanoidCreeperGirlP4.class) {
                	return new CreeperGirlP4MainMenu(id, inventory, packetBuffer);
                }
                else if (creeperGirlClass == TamableHumanoidCreeperGirlP5.class) {
                	return new CreeperGirlP5MainMenu(id, inventory, packetBuffer);
                }
                else if (creeperGirlClass == TamableHumanoidCreeperGirlP6.class) {
                	return new CreeperGirlP6MainMenu(id, inventory, packetBuffer);
                }
                else if (creeperGirlClass == TamableHumanoidCreeperGirlP7.class) {
                	return new CreeperGirlP7MainMenu(id, inventory, packetBuffer);
                }
                else if (creeperGirlClass == TamableHumanoidCreeperGirlP8.class) {
                	return new CreeperGirlP8MainMenu(id, inventory, packetBuffer);
                }
                else {
                    throw new IllegalArgumentException("Unsupported creeper girl menu: " + creeperGirlClass.getSimpleName());
                } 
            }
        } , buf -> {		  	
			buf.writeBlockPos(blockPos);
		    buf.writeVarInt(creeperGirlId);
		    buf.writeBoolean(canPickUpLoot);
		    buf.writeBoolean(canBreakBlocks);
		    buf.writeEnum(combatMode);
            
		    if (creeperGirlClass == TamableHumanoidCreeperGirl.class) {
				buf.writeBoolean(creeperGirl.getGenderedData().isPregnant());
		    }
		});		
		
	}
	
	
	
}
