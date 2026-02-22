package dev.dixmk.minepreggo.world.entity.ai.goal;

import java.util.Iterator;
import java.util.Set;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.GoalSelector;

public class GoalHelper {
	
	private GoalHelper() {
		throw new UnsupportedOperationException("GoalHelper is a utility class and cannot be instantiated.");
	}
	
    public static void addGoalWithReplacement(Mob entity, int priority, Goal goal) {
        removeGoalByClass(entity.goalSelector, goal.getClass());
        entity.goalSelector.addGoal(priority, goal);
    }

    public static void removeGoalByClass(GoalSelector goalSelector, Class<? extends Goal> goalClass) {
    	goalSelector.getAvailableGoals().removeIf(wrappedGoal ->  {
    		Goal goal = wrappedGoal.getGoal();
    		if (goalClass.isAssignableFrom(goal.getClass())) {
    			if (wrappedGoal.isRunning()) {
    				goal.stop();
				}
    			return true;
    		}
    		return false;
    	});
    }
    
    public static void removeGoalByClass(GoalSelector goalSelector, Set<Class<? extends Goal>> goalClasses) {
    	goalSelector.getAvailableGoals().removeIf(wrappedGoal ->  {
			Goal goal = wrappedGoal.getGoal();
			Iterator<Class<? extends Goal>> iter = goalClasses.iterator();
			while (iter.hasNext()) {
				if (iter.next().isAssignableFrom(goal.getClass())) {
					if (wrappedGoal.isRunning()) {
						goal.stop();
					}
					return true;
				}
			}
			return false;
		});
	}
        
    public static void addGoalIfNotPresent(GoalSelector selector, int priority, Goal goal) {
		if (!hasGoalOfClass(selector, goal.getClass())) {
			selector.addGoal(priority, goal);
		}
	}
    
    public static void addGoalWithReplacement(Mob entity, int priority, Goal goal, boolean isTargetSelector) {
        GoalSelector selector = isTargetSelector ? entity.targetSelector : entity.goalSelector;
        removeGoalByClass(selector, goal.getClass());
        selector.addGoal(priority, goal);
    }

    public static boolean hasGoalOfClass(GoalSelector goalSelector, Class<? extends Goal> goalClass) {
        return goalSelector.getAvailableGoals().stream()
            .anyMatch(wrappedGoal -> goalClass.isAssignableFrom(wrappedGoal.getGoal().getClass()));
    }
}
