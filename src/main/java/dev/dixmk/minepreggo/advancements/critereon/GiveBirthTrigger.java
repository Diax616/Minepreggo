package dev.dixmk.minepreggo.advancements.critereon;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.Optional;
import java.util.Set;

import javax.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.utils.MinepreggoHelper;
import dev.dixmk.minepreggo.world.entity.preggo.Species;
import net.minecraft.advancements.critereon.AbstractCriterionTriggerInstance;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.DeserializationContext;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

public class GiveBirthTrigger extends SimpleCriterionTrigger<GiveBirthTrigger.TriggerInstance> {
	
	private static final ResourceLocation ID = MinepreggoHelper.fromThisNamespaceAndPath("give_birth");

	@Override
	public ResourceLocation getId() {
		return ID;
	}

	@Override
	protected TriggerInstance createInstance(JsonObject json, ContextAwarePredicate predicate, DeserializationContext context) {
		Set<Species> successfulBirths = null;
		if (json.has("successful_births")) {
        	JsonObject successfulBirthsJson = json.getAsJsonObject("successful_births");
        	String key = "species";
    		if (successfulBirthsJson.has(key) && successfulBirthsJson.get(key).isJsonArray()) {
    			successfulBirths = EnumSet.copyOf(Arrays.asList(new Gson().fromJson(successfulBirthsJson.get(key).getAsJsonArray(), Species[].class)));
    		}
		}
		return new TriggerInstance(predicate, successfulBirths);
	}

    public void trigger(ServerPlayer player) {
        super.trigger(player, instance -> instance.matches(player));
    }

	public static class TriggerInstance extends AbstractCriterionTriggerInstance {
		Optional<Set<Species>> successfulBirths;
		
		public TriggerInstance(ContextAwarePredicate player, @Nullable Set<Species> successfulBirths) {
			super(GiveBirthTrigger.ID, player);
			this.successfulBirths = Optional.ofNullable(successfulBirths);
		}

        public boolean matches(ServerPlayer player) {  	
        	return player.getCapability(MinepreggoCapabilities.PLAYER_DATA)
        			.resolve()
        			.flatMap(cap -> 
        	            cap.getFemaleData().map(femaleData -> {
        	            	if (femaleData.isPregnant() && femaleData.isPregnancyDataInitialized()) {
            					var pregnancyData = femaleData.getPregnancyData();
            					boolean flag = pregnancyData.getCurrentPregnancyPhase() == pregnancyData.getLastPregnancyStage()
            							&& pregnancyData.getDaysPassed() >= pregnancyData.getDaysByCurrentStage();
            					if (successfulBirths.isPresent()) {
    								flag = flag && cap.getPlayerStatistic().getSuccessfulBirths().containsAll(successfulBirths.get());
    							}
            					return flag;
    						} 
        	                return false;
        	            })
        			).orElse(Boolean.FALSE);
        }
	}
}
