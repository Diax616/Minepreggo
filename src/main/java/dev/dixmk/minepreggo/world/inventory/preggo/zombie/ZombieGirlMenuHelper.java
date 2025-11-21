package dev.dixmk.minepreggo.world.inventory.preggo.zombie;

import org.checkerframework.checker.nullness.qual.NonNull;

import dev.dixmk.minepreggo.world.entity.preggo.zombie.AbstractTamableZombieGirl;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirl;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirlP0;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirlP1;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirlP2;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirlP3;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirlP4;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirlP5;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirlP6;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirlP7;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirlP8;
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
	
	public static<E extends AbstractTamableZombieGirl<?>> void showMainMenu(@NonNull ServerPlayer serverPlayer, @NonNull E zombieGirl) {
		final var zombieGirlId = zombieGirl.getId();
		final var zombieGirlClass = zombieGirl.getClass();
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
                
                if (zombieGirlClass == TamableZombieGirl.class) {
                	return new ZombieGirlMainMenu(id, inventory, packetBuffer);
                }
                else if (zombieGirlClass == TamableZombieGirlP0.class) {
                	return new ZombieGirlP0MainMenu(id, inventory, packetBuffer);
                }
                else if (zombieGirlClass == TamableZombieGirlP1.class) {
                	return new ZombieGirlP1MainMenu(id, inventory, packetBuffer);
                }
                else if (zombieGirlClass == TamableZombieGirlP2.class) {
                	return new ZombieGirlP2MainMenu(id, inventory, packetBuffer);
                }
                else if (zombieGirlClass == TamableZombieGirlP3.class) {
                	return new ZombieGirlP3MainMenu(id, inventory, packetBuffer);
                }
                else if (zombieGirlClass == TamableZombieGirlP4.class) {
                	return new ZombieGirlP4MainMenu(id, inventory, packetBuffer);
                }
                else if (zombieGirlClass == TamableZombieGirlP5.class) {
                	return new ZombieGirlP5MainMenu(id, inventory, packetBuffer);
                }
                else if (zombieGirlClass == TamableZombieGirlP6.class) {
                	return new ZombieGirlP6MainMenu(id, inventory, packetBuffer);
                }
                else if (zombieGirlClass == TamableZombieGirlP7.class) {
                	return new ZombieGirlP7MainMenu(id, inventory, packetBuffer);
                }
                else if (zombieGirlClass == TamableZombieGirlP8.class) {
                	return new ZombieGirlP8MainMenu(id, inventory, packetBuffer);
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
	
	public static<E extends AbstractTamableZombieGirl<?>> void showInventoryMenu(@NonNull ServerPlayer serverPlayer, @NonNull E zombieGirl) {
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
                else if (zombieGirlClass == TamableZombieGirlP0.class) {
                	return new ZombieGirlP0InventoryMenu(id, inventory, packetBuffer);
                }
                else if (zombieGirlClass == TamableZombieGirlP1.class) {
                	return new ZombieGirlP1InventoryMenu(id, inventory, packetBuffer);
                }
                else if (zombieGirlClass == TamableZombieGirlP2.class) {
                	return new ZombieGirlP2InventoryMenu(id, inventory, packetBuffer);
                }
                else if (zombieGirlClass == TamableZombieGirlP3.class) {
                	return new ZombieGirlP3InventoryMenu(id, inventory, packetBuffer);
                }
                else if (zombieGirlClass == TamableZombieGirlP4.class) {
                	return new ZombieGirlP4InventoryMenu(id, inventory, packetBuffer);
                }
                else if (zombieGirlClass == TamableZombieGirlP5.class) {
                	return new ZombieGirlP5InventoryMenu(id, inventory, packetBuffer);
                }
                else if (zombieGirlClass == TamableZombieGirlP6.class) {
                	return new ZombieGirlP6InventoryMenu(id, inventory, packetBuffer);
                }
                else if (zombieGirlClass == TamableZombieGirlP7.class) {
                	return new ZombieGirlP7InventoryMenu(id, inventory, packetBuffer);
                }
                else if (zombieGirlClass == TamableZombieGirlP8.class) {
                	return new ZombieGirlP8InventoryMenu(id, inventory, packetBuffer);
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
