package dev.dixmk.minepreggo.world.inventory.preggo.zombie;

import org.checkerframework.checker.nullness.qual.NonNull;

import dev.dixmk.minepreggo.world.entity.preggo.zombie.AbstractTamableZombieGirl;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamablePregnantZombieGirl;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirl;
import io.netty.buffer.Unpooled;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraftforge.network.NetworkHooks;

public class ZombieGirlMenuHelper {

	private ZombieGirlMenuHelper() {}
	
	public static<E extends AbstractTamableZombieGirl> void showMainMenu(@NonNull ServerPlayer serverPlayer, @NonNull E zombieGirl) {
		final var zombieGirlId = zombieGirl.getId();
		final var zombieGirlClass = zombieGirl.getClass();
		final var canPickUpLoot = zombieGirl.canPickUpLoot();
		final var canBreakBlocks = zombieGirl.canBreakBlocks();
		final var bPos = serverPlayer.blockPosition();
			
		NetworkHooks.openScreen(serverPlayer,new MenuProvider() {
            @Override
            public Component getDisplayName() {
                return Component.literal("ZombieGirlMainGUI");
            }

            @Override
            public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
                FriendlyByteBuf packetBuffer = new FriendlyByteBuf(Unpooled.buffer());
                packetBuffer.writeBlockPos(bPos);
                packetBuffer.writeVarInt(zombieGirlId);
                packetBuffer.writeBoolean(canPickUpLoot);
                packetBuffer.writeBoolean(canBreakBlocks);
                
                if (zombieGirlClass == TamableZombieGirl.class) {
                	packetBuffer.writeBoolean(zombieGirl.getGenderedData().isPregnant());
                	return new ZombieGirlMainMenu(id, inventory, packetBuffer);
                }
                else if (zombieGirlClass == TamablePregnantZombieGirl.TamableZombieGirlP0.class) {
                	return new TamablePregnantZombieGirlMainMenu.ZombieGirlP0MainMenu(id, inventory, packetBuffer);
                }
                else if (zombieGirlClass == TamablePregnantZombieGirl.TamableZombieGirlP1.class) {
                	return new TamablePregnantZombieGirlMainMenu.ZombieGirlP1MainMenu(id, inventory, packetBuffer);
                }
                else if (zombieGirlClass == TamablePregnantZombieGirl.TamableZombieGirlP2.class) {
                	return new TamablePregnantZombieGirlMainMenu.ZombieGirlP2MainMenu(id, inventory, packetBuffer);
                }
                else if (zombieGirlClass == TamablePregnantZombieGirl.TamableZombieGirlP3.class) {
                	return new TamablePregnantZombieGirlMainMenu.ZombieGirlP3MainMenu(id, inventory, packetBuffer);
                }
                else if (zombieGirlClass == TamablePregnantZombieGirl.TamableZombieGirlP4.class) {
                	return new TamablePregnantZombieGirlMainMenu.ZombieGirlP4MainMenu(id, inventory, packetBuffer);
                }
                else if (zombieGirlClass == TamablePregnantZombieGirl.TamableZombieGirlP5.class) {
                	return new TamablePregnantZombieGirlMainMenu.ZombieGirlP5MainMenu(id, inventory, packetBuffer);
                }
                else if (zombieGirlClass == TamablePregnantZombieGirl.TamableZombieGirlP6.class) {
                	return new TamablePregnantZombieGirlMainMenu.ZombieGirlP6MainMenu(id, inventory, packetBuffer);
                }
                else if (zombieGirlClass == TamablePregnantZombieGirl.TamableZombieGirlP7.class) {
                	return new TamablePregnantZombieGirlMainMenu.ZombieGirlP7MainMenu(id, inventory, packetBuffer);
                }
                else if (zombieGirlClass == TamablePregnantZombieGirl.TamableZombieGirlP8.class) {
                	return new TamablePregnantZombieGirlMainMenu.ZombieGirlP8MainMenu(id, inventory, packetBuffer);
                }
                else {
                    throw new IllegalArgumentException("Unsupported zombie girl menu: " + zombieGirlClass.getSimpleName());
                }       
            }
        }, buf -> {    
			buf.writeBlockPos(bPos);
		    buf.writeVarInt(zombieGirlId);
		    buf.writeBoolean(canPickUpLoot);
		    buf.writeBoolean(canBreakBlocks);
		    
		    if (zombieGirlClass == TamableZombieGirl.class) {
		    	buf.writeBoolean(zombieGirl.getGenderedData().isPregnant());
		    }
		});
	}
	
	public static<E extends AbstractTamableZombieGirl> void showInventoryMenu(@NonNull ServerPlayer serverPlayer, @NonNull E zombieGirl) {
		final var zombieGirlId = zombieGirl.getId();
		final var zombieGirlClass = zombieGirl.getClass();
		final var bPos = serverPlayer.blockPosition();
		
		NetworkHooks.openScreen(serverPlayer,new MenuProvider() {
            @Override
            public Component getDisplayName() {
                return Component.literal("ZombieGirlInventoryGUI");
            }

            @Override
            public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
                FriendlyByteBuf packetBuffer = new FriendlyByteBuf(Unpooled.buffer());
                packetBuffer.writeBlockPos(bPos);
                packetBuffer.writeVarInt(zombieGirlId);

                if (zombieGirlClass == TamableZombieGirl.class) {
                	return new ZombieGirlInventoryMenu(id, inventory, packetBuffer);
                }
                else if (zombieGirlClass == TamablePregnantZombieGirl.TamableZombieGirlP0.class) {
                	return new TamablePregnantZombieGirlInventoryMenu.ZombieGirlP0InventoryMenu(id, inventory, packetBuffer);
                }
                else if (zombieGirlClass == TamablePregnantZombieGirl.TamableZombieGirlP1.class) {
                	return new TamablePregnantZombieGirlInventoryMenu.ZombieGirlP1InventoryMenu(id, inventory, packetBuffer);
                }
                else if (zombieGirlClass == TamablePregnantZombieGirl.TamableZombieGirlP2.class) {
                	return new TamablePregnantZombieGirlInventoryMenu.ZombieGirlP2InventoryMenu(id, inventory, packetBuffer);
                }
                else if (zombieGirlClass == TamablePregnantZombieGirl.TamableZombieGirlP3.class) {
                	return new TamablePregnantZombieGirlInventoryMenu.ZombieGirlP3InventoryMenu(id, inventory, packetBuffer);
                }
                else if (zombieGirlClass == TamablePregnantZombieGirl.TamableZombieGirlP4.class) {
                	return new TamablePregnantZombieGirlInventoryMenu.ZombieGirlP4InventoryMenu(id, inventory, packetBuffer);
                }
                else if (zombieGirlClass == TamablePregnantZombieGirl.TamableZombieGirlP5.class) {
                	return new TamablePregnantZombieGirlInventoryMenu.ZombieGirlP5InventoryMenu(id, inventory, packetBuffer);
                }
                else if (zombieGirlClass == TamablePregnantZombieGirl.TamableZombieGirlP6.class) {
                	return new TamablePregnantZombieGirlInventoryMenu.ZombieGirlP6InventoryMenu(id, inventory, packetBuffer);
                }
                else if (zombieGirlClass == TamablePregnantZombieGirl.TamableZombieGirlP7.class) {
                	return new TamablePregnantZombieGirlInventoryMenu.ZombieGirlP7InventoryMenu(id, inventory, packetBuffer);
                }
                else if (zombieGirlClass == TamablePregnantZombieGirl.TamableZombieGirlP8.class) {
                	return new TamablePregnantZombieGirlInventoryMenu.ZombieGirlP8InventoryMenu(id, inventory, packetBuffer);
                }
                else {
                    throw new IllegalArgumentException("Unsupported zombie girl menu: " + zombieGirlClass.getSimpleName());
                }    
            }
        }, buf -> {
		    buf.writeBlockPos(bPos);
		    buf.writeVarInt(zombieGirlId);
		});
	}
}
