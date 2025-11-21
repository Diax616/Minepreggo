package dev.dixmk.minepreggo.world.inventory.preggo;

import org.apache.commons.lang3.tuple.Pair;

import dev.dixmk.minepreggo.init.MinepreggoModMenus;
import io.netty.buffer.Unpooled;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraftforge.network.NetworkHooks;

public class RequestSexP2PMenu extends AbstractRequestSexMenu<Player, Player> {

	public RequestSexP2PMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
		super(MinepreggoModMenus.REQUEST_SEX_P2P_MENU.get(), id, inv, extraData);
	}

	@Override
	protected Pair<Player, Player> getSourceAndTarget(FriendlyByteBuf extraData) {
		Player source = null;
		Player target = null;	
		if (extraData != null) {
			if (level.getEntity(extraData.readVarInt()) instanceof Player s)  {
				source = s;
			}
			if (level.getEntity(extraData.readVarInt()) instanceof Player t) {
				target = t;
			}
		}
		return Pair.of(source, target);
	}
	
	public static void create(ServerPlayer target, ServerPlayer source) {
		NetworkHooks.openScreen(target, new MenuProvider() {
            @Override
            public Component getDisplayName() {
                return Component.literal("CreeperGirlInventoryGUI");
            }
            
            @Override
            public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
                FriendlyByteBuf packetBuffer = new FriendlyByteBuf(Unpooled.buffer());
                packetBuffer.writeVarInt(source.getId());
                packetBuffer.writeVarInt(target.getId());              
                return new RequestSexP2PMenu(id, inventory, packetBuffer);
            }
		}
					
		,buf -> {
		    buf.writeVarInt(source.getId());
		    buf.writeVarInt(target.getId()); 
		});
	}
}
