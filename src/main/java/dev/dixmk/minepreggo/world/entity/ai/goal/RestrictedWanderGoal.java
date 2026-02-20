package dev.dixmk.minepreggo.world.entity.ai.goal;

import javax.annotation.Nullable;

import dev.dixmk.minepreggo.world.entity.preggo.ITamablePreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.phys.Vec3;

public class RestrictedWanderGoal<T extends PreggoMob & ITamablePreggoMob<?>> extends WaterAvoidingRandomStrollGoal {

    private static final int MAX_ATTEMPTS = 10;
    protected final double maxRadius;
    protected final T preggoMob;
    private final double maxRadiusSq;
    
    public RestrictedWanderGoal(T mob, double speedModifier, double maxRadius) {
        this(mob, speedModifier, maxRadius, WaterAvoidingRandomStrollGoal.PROBABILITY);
    }

    public RestrictedWanderGoal(T mob, double speedModifier, double maxRadius, float waterAvoidProbability) {
        super(mob, speedModifier, waterAvoidProbability);
        this.maxRadius   = maxRadius;
        this.preggoMob   = mob;
        this.maxRadiusSq = maxRadius * maxRadius;
    }

    @Nullable
    @Override
    protected Vec3 getPosition() {
        BlockPos center = this.preggoMob.getTamableData().getCentralPositionWhenWandering();

        if (center == null) {
            return super.getPosition();
        }

        if (isInsideRadius(this.mob.position(), center)) {
            for (int attempt = 0; attempt < MAX_ATTEMPTS; attempt++) {
                Vec3 candidate = super.getPosition();
                if (candidate != null && isInsideRadius(candidate, center)) {
                    return candidate;
                }
            }
            return null;
        } else {
            return buildReturnPosition(center);
        }
    }

    protected boolean isInsideRadius(Vec3 pos, BlockPos center) {
        double dx = pos.x - center.getX();
        double dz = pos.z - center.getZ();
        return (dx * dx + dz * dz) <= this.maxRadiusSq;
    }

    protected Vec3 buildReturnPosition(BlockPos center) {
        Vec3 mobPos    = this.mob.position();
        Vec3 centerVec = Vec3.atBottomCenterOf(center);
        double t       = 0.25 + this.mob.getRandom().nextDouble() * 0.5;

        return new Vec3(
            mobPos.x + (centerVec.x - mobPos.x) * t,
            mobPos.y + (centerVec.y - mobPos.y) * t,
            mobPos.z + (centerVec.z - mobPos.z) * t
        );
    }
}