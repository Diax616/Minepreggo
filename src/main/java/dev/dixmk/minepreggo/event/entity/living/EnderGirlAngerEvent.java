package dev.dixmk.minepreggo.event.entity.living;

import dev.dixmk.minepreggo.world.entity.preggo.ender.AbstractEnderGirl;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.Cancelable;

@Cancelable
public class EnderGirlAngerEvent extends LivingEvent {
    private final Player player;

    public EnderGirlAngerEvent(AbstractEnderGirl abstractEnderGirl, Player player) {
        super(abstractEnderGirl);
        this.player = player;
    }

    /**
     * The player that is being checked.
     */
    public Player getPlayer()
    {
        return player;
    }

    @Override
    public AbstractEnderGirl getEntity()
    {
        return (AbstractEnderGirl) super.getEntity();
    }
}
