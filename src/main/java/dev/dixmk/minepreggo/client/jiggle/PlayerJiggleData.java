package dev.dixmk.minepreggo.client.jiggle;

import java.util.Optional;

import javax.annotation.Nullable;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * Wrapper class that holds all jiggle physics instances for a single player.
 * This class is stored per-player in the JigglePhysicsManager to ensure
 * each player has independent physics state in multiplayer.
 */
@OnlyIn(Dist.CLIENT)
public class PlayerJiggleData {
    private WrapperBoobsJiggle boobsJiggle;
    private Optional<BellyJigglePhysics> bellyJiggle;
    private Optional<WrapperButtJiggle> buttJiggle;
    
    public PlayerJiggleData(WrapperBoobsJiggle boobsJiggle, @Nullable BellyJigglePhysics bellyJiggle,  @Nullable WrapperButtJiggle buttJiggle) {
        this.boobsJiggle = boobsJiggle;
        this.bellyJiggle = Optional.ofNullable(bellyJiggle);
        this.buttJiggle = Optional.ofNullable(buttJiggle);
    }
    
    public WrapperBoobsJiggle getBoobsJiggle() {
        return boobsJiggle;
    }
    
    public void setBoobsJiggle(WrapperBoobsJiggle boobsJiggle) {
        this.boobsJiggle = boobsJiggle;
    }
    
    public Optional<BellyJigglePhysics> getBellyJiggle() {
        return bellyJiggle;
    }
    
    public void setBellyJiggle(BellyJigglePhysics bellyJiggle) {
        this.bellyJiggle = Optional.ofNullable(bellyJiggle);
    }
    
    public Optional<WrapperButtJiggle> getButtJiggle() {
        return buttJiggle;
    }
    
    public void setButtJiggle(WrapperButtJiggle buttJiggle) {
        this.buttJiggle = Optional.ofNullable(buttJiggle);
    }
    
    /**
     * Resets all physics state to initial values.
     * Useful when a player respawns or needs to reset their physics.
     */
    public void reset() {
        if (boobsJiggle != null) {
            boobsJiggle.reset();
        }
        bellyJiggle.ifPresent(BellyJigglePhysics::reset);
        buttJiggle.ifPresent(WrapperButtJiggle::reset);
    }
}
