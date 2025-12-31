package dev.dixmk.minepreggo.event.entity.living;

import dev.dixmk.minepreggo.world.entity.preggo.ender.AbstractEnderWoman;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.Cancelable;

@Cancelable
public class EnderWomanAngerEvent extends LivingEvent {
    private final Player player;

    public EnderWomanAngerEvent(AbstractEnderWoman abstractEnderGirl, Player player) {
        super(abstractEnderGirl);
        this.player = player;
    }

    /**
     * The player that is being checked.
     */
    public Player getPlayer() {
        return player;
    }

    @Override
    public AbstractEnderWoman getEntity() {
        return (AbstractEnderWoman) super.getEntity();
    }
}
