package dev.dixmk.minepreggo.world.inventory.preggo.creeper;

import org.checkerframework.checker.nullness.qual.NonNull;

import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractTamableCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableHumanoidCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableMonsterCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamablePregnantHumanoidCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamablePregnantMonsterCreeperGirl;
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
	
	public static<E extends AbstractTamableCreeperGirl> void showInventoryMenuForHumanoid(@NonNull ServerPlayer serverPlayer, @NonNull E creeperGirl) {			
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
                	return new HumanoidCreeperGirlInventoryMenu(id, inventory, packetBuffer);
                }
                else if (creeperGirlClass == TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP0.class) {
                	return new PregnantHumanoidCreeperGirlInventoryMenu.CreeperGirlP0InventoryMenu(id, inventory, packetBuffer);
                }
                else if (creeperGirlClass == TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP1.class) {
                	return new PregnantHumanoidCreeperGirlInventoryMenu.CreeperGirlP1InventoryMenu(id, inventory, packetBuffer);
                }
                else if (creeperGirlClass == TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP2.class) {
                	return new PregnantHumanoidCreeperGirlInventoryMenu.CreeperGirlP2InventoryMenu(id, inventory, packetBuffer);
                }
                else if (creeperGirlClass == TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP3.class) {
                	return new PregnantHumanoidCreeperGirlInventoryMenu.CreeperGirlP3InventoryMenu(id, inventory, packetBuffer);
                }
                else if (creeperGirlClass == TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP4.class) {
                	return new PregnantHumanoidCreeperGirlInventoryMenu.CreeperGirlP4InventoryMenu(id, inventory, packetBuffer);
                }
                else if (creeperGirlClass == TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP5.class) {
                	return new PregnantHumanoidCreeperGirlInventoryMenu.CreeperGirlP5InventoryMenu(id, inventory, packetBuffer);
                }
                else if (creeperGirlClass == TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP6.class) {
                	return new PregnantHumanoidCreeperGirlInventoryMenu.CreeperGirlP6InventoryMenu(id, inventory, packetBuffer);
                }
                else if (creeperGirlClass == TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP7.class) {
                	return new PregnantHumanoidCreeperGirlInventoryMenu.CreeperGirlP7InventoryMenu(id, inventory, packetBuffer);
                }
                else if (creeperGirlClass == TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP8.class) {
                	return new PregnantHumanoidCreeperGirlInventoryMenu.CreeperGirlP8InventoryMenu(id, inventory, packetBuffer);
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
	
	
	public static<E extends AbstractTamableCreeperGirl> void showMainMenuForHumanoid(@NonNull ServerPlayer serverPlayer, @NonNull E creeperGirl) {			
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
                	return new HumanoidCreeperGirlMainMenu(id, inventory, packetBuffer);
                }
                else if (creeperGirlClass == TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP0.class) {
                	return new PregnantHumanoidCreeperGirlMainMenu.CreeperGirlP0MainMenu(id, inventory, packetBuffer);
                }
                else if (creeperGirlClass == TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP1.class) {
                	return new PregnantHumanoidCreeperGirlMainMenu.CreeperGirlP1MainMenu(id, inventory, packetBuffer);
                }
                else if (creeperGirlClass == TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP2.class) {
                	return new PregnantHumanoidCreeperGirlMainMenu.CreeperGirlP2MainMenu(id, inventory, packetBuffer);
                }
                else if (creeperGirlClass == TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP3.class) {
                	return new PregnantHumanoidCreeperGirlMainMenu.CreeperGirlP3MainMenu(id, inventory, packetBuffer);
                }
                else if (creeperGirlClass == TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP4.class) {
                	return new PregnantHumanoidCreeperGirlMainMenu.CreeperGirlP4MainMenu(id, inventory, packetBuffer);
                }
                else if (creeperGirlClass == TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP5.class) {
                	return new PregnantHumanoidCreeperGirlMainMenu.CreeperGirlP5MainMenu(id, inventory, packetBuffer);
                }
                else if (creeperGirlClass == TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP6.class) {
                	return new PregnantHumanoidCreeperGirlMainMenu.CreeperGirlP6MainMenu(id, inventory, packetBuffer);
                }
                else if (creeperGirlClass == TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP7.class) {
                	return new PregnantHumanoidCreeperGirlMainMenu.CreeperGirlP7MainMenu(id, inventory, packetBuffer);
                }
                else if (creeperGirlClass == TamablePregnantHumanoidCreeperGirl.TamableHumanoidCreeperGirlP8.class) {
                	return new PregnantHumanoidCreeperGirlMainMenu.CreeperGirlP8MainMenu(id, inventory, packetBuffer);
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
	
	
	public static<E extends AbstractTamableCreeperGirl> void showInventoryMenuForMonster(@NonNull ServerPlayer serverPlayer, @NonNull E creeperGirl) {			
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
                
                if (creeperGirlClass == TamableMonsterCreeperGirl.class) {
                	return new MonsterCreeperGirlInventoryMenu(id, inventory, packetBuffer);
                }
                else if (creeperGirlClass == TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP0.class) {
                	return new PregnantMonsterCreeperGirlInventoryMenu.MonsterCreeperGirlP0InventoryMenu(id, inventory, packetBuffer);
                }
                else if (creeperGirlClass == TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP1.class) {
                	return new PregnantMonsterCreeperGirlInventoryMenu.MonsterCreeperGirlP1InventoryMenu(id, inventory, packetBuffer);
                }
                else if (creeperGirlClass == TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP2.class) {
                	return new PregnantMonsterCreeperGirlInventoryMenu.MonsterCreeperGirlP2InventoryMenu(id, inventory, packetBuffer);
                }
                else if (creeperGirlClass == TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP3.class) {
                	return new PregnantMonsterCreeperGirlInventoryMenu.MonsterCreeperGirlP3InventoryMenu(id, inventory, packetBuffer);
                }
                else if (creeperGirlClass == TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP4.class) {
                	return new PregnantMonsterCreeperGirlInventoryMenu.MonsterCreeperGirlP4InventoryMenu(id, inventory, packetBuffer);
                }
                else if (creeperGirlClass == TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP5.class) {
                	return new PregnantMonsterCreeperGirlInventoryMenu.MonsterCreeperGirlP5InventoryMenu(id, inventory, packetBuffer);
                }
                else if (creeperGirlClass == TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP6.class) {
                	return new PregnantMonsterCreeperGirlInventoryMenu.MonsterCreeperGirlP6InventoryMenu(id, inventory, packetBuffer);
                }
                else if (creeperGirlClass == TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP7.class) {
                	return new PregnantMonsterCreeperGirlInventoryMenu.MonsterCreeperGirlP7InventoryMenu(id, inventory, packetBuffer);
                }
                else if (creeperGirlClass == TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP8.class) {
                	return new PregnantMonsterCreeperGirlInventoryMenu.MonsterCreeperGirlP8InventoryMenu(id, inventory, packetBuffer);
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
	
	public static<E extends AbstractTamableCreeperGirl> void showMainMenuForMonster(@NonNull ServerPlayer serverPlayer, @NonNull E creeperGirl) {			
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
                
                if (creeperGirlClass == TamableMonsterCreeperGirl.class) {
                	packetBuffer.writeBoolean(creeperGirl.getGenderedData().isPregnant());
                	return new MonsterCreeperGirlMainMenu(id, inventory, packetBuffer);
                }
                else if (creeperGirlClass == TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP0.class) {
                	return new PregnantMonsterCreeperGirlMainMenu.MonsterCreeperGirlP0MainMenu(id, inventory, packetBuffer);
                }
                else if (creeperGirlClass == TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP1.class) {
                	return new PregnantMonsterCreeperGirlMainMenu.MonsterCreeperGirlP1MainMenu(id, inventory, packetBuffer);
                }
                else if (creeperGirlClass == TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP2.class) {
                	return new PregnantMonsterCreeperGirlMainMenu.MonsterCreeperGirlP2MainMenu(id, inventory, packetBuffer);
                }
                else if (creeperGirlClass == TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP3.class) {
                	return new PregnantMonsterCreeperGirlMainMenu.MonsterCreeperGirlP3MainMenu(id, inventory, packetBuffer);
                }
                else if (creeperGirlClass == TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP4.class) {
                	return new PregnantMonsterCreeperGirlMainMenu.MonsterCreeperGirlP4MainMenu(id, inventory, packetBuffer);
                }
                else if (creeperGirlClass == TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP5.class) {
                	return new PregnantMonsterCreeperGirlMainMenu.MonsterCreeperGirlP5MainMenu(id, inventory, packetBuffer);
                }
                else if (creeperGirlClass == TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP6.class) {
                	return new PregnantMonsterCreeperGirlMainMenu.MonsterCreeperGirlP6MainMenu(id, inventory, packetBuffer);
                }
                else if (creeperGirlClass == TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP7.class) {
                	return new PregnantMonsterCreeperGirlMainMenu.MonsterCreeperGirlP7MainMenu(id, inventory, packetBuffer);
                }
                else if (creeperGirlClass == TamablePregnantMonsterCreeperGirl.TamableMonsterCreeperGirlP8.class) {
                	return new PregnantMonsterCreeperGirlMainMenu.MonsterCreeperGirlP8MainMenu(id, inventory, packetBuffer);
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
            
		    if (creeperGirlClass == TamableMonsterCreeperGirl.class) {
				buf.writeBoolean(creeperGirl.getGenderedData().isPregnant());
		    }
		});		
		
	}
}
