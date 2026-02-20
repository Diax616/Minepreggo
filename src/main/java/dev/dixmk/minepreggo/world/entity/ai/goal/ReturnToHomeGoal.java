package dev.dixmk.minepreggo.world.entity.ai.goal;

import dev.dixmk.minepreggo.world.entity.preggo.ITamablePreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;


public class ReturnToHomeGoal<E extends PreggoMob & ITamablePreggoMob<?>> extends Goal {
    private static final double INNER_MARGIN = 4.0;
    protected final E mob;
    protected final double speedModifier;
    protected final double maxRadius;
    private final double maxRadiusSq;
    private final double innerRadiusSq;

    public ReturnToHomeGoal(E mob, double speedModifier, double maxRadius) {
        this.mob          = mob;
        this.speedModifier = speedModifier;
        this.maxRadius    = maxRadius;
        this.maxRadiusSq  = maxRadius * maxRadius;
        double innerRadius = Math.max(0, maxRadius - INNER_MARGIN);
        this.innerRadiusSq = innerRadius * innerRadius;

        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        BlockPos center = this.mob.getTamableData().getCentralPositionWhenWandering();
        if (center == null || this.mob.isVehicle() || isInsideRadius(this.mob.position(), center, this.maxRadiusSq)) return false;
        
        this.clearTarget();
        return true;
    }

    @Override
    public boolean canContinueToUse() {
        BlockPos center = this.mob.getTamableData().getCentralPositionWhenWandering();
        if (center == null || this.mob.isVehicle() || this.mob.getNavigation().isDone()) return false;
        return !isInsideRadius(this.mob.position(), center, this.innerRadiusSq);
    }

    @Override
    public void start() {
        BlockPos center = this.mob.getTamableData().getCentralPositionWhenWandering();
        if (center == null) return;

        this.mob.getNavigation().moveTo(
            center.getX() + 0.5,
            center.getY(),
            center.getZ() + 0.5,
            this.speedModifier
        );
    }

    @Override
    public void stop() {
        this.mob.getNavigation().stop();
        super.stop();
    }

    @Override
    public void tick() {
        if (this.mob.getTarget() != null) {
            this.clearTarget();
        }
    }

    private void clearTarget() {
        this.mob.setTarget(null);
    }

    private static boolean isInsideRadius(Vec3 pos, BlockPos center, double radiusSq) {
        double dx = pos.x - center.getX();
        double dz = pos.z - center.getZ();
        return (dx * dx + dz * dz) <= radiusSq;
    }
}
