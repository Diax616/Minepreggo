package dev.dixmk.minepreggo.world.entity.ai.goal;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

import dev.dixmk.minepreggo.world.entity.preggo.ITamablePreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;

public class BreakBlocksToFollowOwnerGoal<E extends PreggoMob & ITamablePreggoMob<?>> extends Goal {
    private final E tamable;
    private final float maxDistance;
    private final float minDistance;
    private int breakingTime;
    private BlockPos targetBlockPos;
    private BlockPos currentColumnBase; // Track which column we're working on
    private boolean columnCleared; // Track if vertical column is cleared
    private static final int BREAK_DURATION = 20; // Ticks to break a block
    private static final int HORIZONTAL_RANGE = 2; // How many blocks horizontally to check/break
    
    
    public BreakBlocksToFollowOwnerGoal(E tamable, float minDistance, float maxDistance) {
        this.tamable = tamable;
        this.minDistance = minDistance;
        this.maxDistance = maxDistance;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        if (!this.tamable.isTame() || !this.tamable.canBreakBlocks() || this.tamable.isWaiting() ||  this.tamable.getOwner() == null) {
            return false;
        }
             
        double distance = this.tamable.distanceToSqr(this.tamable.getOwner());
        
        if (distance < (this.minDistance * this.minDistance)) {
            return false;
        }
        
        return distance > (this.maxDistance * this.maxDistance);
    }

    @Override
    public boolean canContinueToUse() {
        if (this.tamable.getOwner() == null) {
            return false;
        }
        
        double distance = this.tamable.distanceToSqr(this.tamable.getOwner());
        return distance > (this.minDistance * this.minDistance);
    }

    @Override
    public void start() {
        this.breakingTime = 0;
        this.targetBlockPos = null;
        this.currentColumnBase = null;
        this.columnCleared = false;
    }

    @Override
    public void stop() {
        this.tamable.getNavigation().stop();
        this.breakingTime = 0;
        this.targetBlockPos = null;
        this.currentColumnBase = null;
        this.columnCleared = false;
    }

    @Override
    public void tick() {
        if (this.tamable.getOwner() == null) {
            return;
        }
        
        // Check if path is blocked
        if (this.tamable.getNavigation().isDone() || !this.tamable.getNavigation().isInProgress()) {
            BlockPos blockingPos = findNextBlockToBreak();
            
            if (blockingPos != null && isSoftBlock(blockingPos)) {
                // Start or continue breaking the block
                if (this.targetBlockPos == null || !this.targetBlockPos.equals(blockingPos)) {
                    // Clean up previous block animation
                    if (this.targetBlockPos != null) {
                        this.tamable.level().destroyBlockProgress(this.tamable.getId(), this.targetBlockPos, -1);
                    }
                    
                    this.targetBlockPos = blockingPos;
                    this.breakingTime = 0;
                }
                
                this.breakingTime++;
                
                // Show breaking animation
                int progress = (int)((float)this.breakingTime / BREAK_DURATION * 10.0F);
                this.tamable.level().destroyBlockProgress(this.tamable.getId(), this.targetBlockPos, progress);
                
                if (this.breakingTime >= BREAK_DURATION) {
                    // Break the block
                    this.tamable.level().destroyBlock(this.targetBlockPos, true, this.tamable);
                    this.tamable.level().destroyBlockProgress(this.tamable.getId(), this.targetBlockPos, -1);
                    this.breakingTime = 0;
                    this.targetBlockPos = null;
                    
                    // Check if we just finished a vertical column
                    if (this.currentColumnBase != null) {
                        boolean columnStillBlocked = isVerticalColumnBlocked(this.currentColumnBase);
                        if (!columnStillBlocked) {
                            this.columnCleared = true;
                        }
                    }
                }
            } else {
                // No more blocks to break, try to navigate to owner
                this.breakingTime = 0;
                if (this.targetBlockPos != null) {
                    this.tamable.level().destroyBlockProgress(this.tamable.getId(), this.targetBlockPos, -1);
                    this.targetBlockPos = null;
                }
                this.currentColumnBase = null;
                this.columnCleared = false;
            }
        }
    }

    private BlockPos findNextBlockToBreak() {
 
        Vec3 direction = this.tamable.getOwner().position().subtract(this.tamable.position()).normalize();
        int dirX = (int)Math.round(direction.x);
        int dirZ = (int)Math.round(direction.z);
        
        // If we're working on a column and it's not cleared yet, continue with that column
        if (this.currentColumnBase != null && !this.columnCleared) {
            BlockPos verticalBlock = findVerticalBlockInColumn(this.currentColumnBase);
            if (verticalBlock != null) {
                return verticalBlock;
            } else {
                // Column is now clear
                this.columnCleared = true;
            }
        }
        
        // Search for horizontal blocking positions
        BlockPos bestColumnBase = null;
        double closestDist = Double.MAX_VALUE;
        
        // Check in a horizontal range around the forward direction
        for (int xOffset = -HORIZONTAL_RANGE; xOffset <= HORIZONTAL_RANGE; xOffset++) {
            for (int zOffset = -HORIZONTAL_RANGE; zOffset <= HORIZONTAL_RANGE; zOffset++) {
                // Skip positions that aren't in the general direction of the owner
                if (dirX * xOffset + dirZ * zOffset < 0) {
                    continue;
                }
                
                BlockPos columnBase = this.tamable.blockPosition().offset(dirX + xOffset, 0, dirZ + zOffset);
                
                // Check if this column has blocking blocks
                if (isVerticalColumnBlocked(columnBase)) {
                    double dist = columnBase.distSqr(this.tamable.getOwner().blockPosition());
                    if (dist < closestDist) {
                        closestDist = dist;
                        bestColumnBase = columnBase;
                    }
                }
            }
        }
        
        // If we found a blocking column, set it as current and start breaking from bottom
        if (bestColumnBase != null) {
            this.currentColumnBase = bestColumnBase;
            this.columnCleared = false;
            return findVerticalBlockInColumn(bestColumnBase);
        }
        
        return null;
    }
    
    private boolean isVerticalColumnBlocked(BlockPos basePos) {
        // Check if there are any soft blocks in the vertical column (up to 3 blocks high)
        for (int y = 0; y <= 2; y++) {
            BlockPos checkPos = basePos.offset(0, y, 0);
            if (isSoftBlock(checkPos) && !this.tamable.level().isEmptyBlock(checkPos)) {
                return true;
            }
        }
        return false;
    }
    
    private BlockPos findVerticalBlockInColumn(BlockPos basePos) {
        // Break from bottom to top
        for (int y = 0; y <= 2; y++) {
            BlockPos checkPos = basePos.offset(0, y, 0);
            if (isSoftBlock(checkPos) && !this.tamable.level().isEmptyBlock(checkPos)) {
                return checkPos;
            }
        }
        return null;
    }
    
    private boolean isSoftBlock(BlockPos pos) {
        BlockState state = this.tamable.level().getBlockState(pos);
        var f = state.getDestroySpeed(this.tamable.level(), pos);
        return f <= 1.2F && f >= 0; // Any block with low hardness
    }
}
