package dev.dixmk.minepreggo.event;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.init.MinepreggoModItems;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MinepreggoMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents {

	private ClientModEvents() {}
	
    @SubscribeEvent
    public static void registerItemColors(RegisterColorHandlersEvent.Item event) {
        event.register((stack, tintIndex) -> tintIndex > 0 ? -1 : ((DyeableLeatherItem) stack.getItem()).getColor(stack),     		
        MinepreggoModItems.FEMALE_LEATHER_P0_CHESTPLATE.get(),
        MinepreggoModItems.MATERNITY_LEATHER_P1_CHESTPLATE.get(),
        MinepreggoModItems.MATERNITY_LEATHER_P2_CHESTPLATE.get(),
        MinepreggoModItems.MATERNITY_LEATHER_P3_CHESTPLATE.get(),
        MinepreggoModItems.MATERNITY_LEATHER_P4_CHESTPLATE.get(),
        MinepreggoModItems.LEATHER_KNEE_BRACE.get());    
    }
}


