package dev.dixmk.minepreggo.advancements.critereon;

import java.util.Optional;
import java.util.OptionalInt;

import javax.annotation.Nullable;

import com.google.gson.JsonObject;

import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.utils.MinepreggoHelper;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyType;
import net.minecraft.advancements.critereon.AbstractCriterionTriggerInstance;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.DeserializationContext;
import net.minecraft.advancements.critereon.MobEffectsPredicate;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

public class GetPregnantTrigger extends SimpleCriterionTrigger<GetPregnantTrigger.TriggerInstance> {

	private static final ResourceLocation ID = MinepreggoHelper.fromThisMod("get_pregnant");

	@Override
	public ResourceLocation getId() {
		return ID;
	}

	@Override
	protected TriggerInstance createInstance(JsonObject json, ContextAwarePredicate predicate, DeserializationContext context) {
		MobEffectsPredicate effectsPredicate = MobEffectsPredicate.fromJson(json.get("effects"));
		Integer babyCount = null;
		PregnancyType pregnancyType = null;
		
		if (json.has("babies") && json.get("babies").isJsonObject()) {
			JsonObject babiesObj = json.getAsJsonObject("babies");
			if (babiesObj.has("count")) {
				babyCount = babiesObj.get("count").getAsInt();
			}
		}	
		if (json.has("pregnancy") && json.get("pregnancy").isJsonObject()) {
			JsonObject pregObj = json.getAsJsonObject("pregnancy");
			if (pregObj.has("type")) {
				String typeStr = pregObj.get("type").getAsString();
				pregnancyType = PregnancyType.valueOf(typeStr);
			}
		}
		
		return new TriggerInstance(predicate, effectsPredicate, babyCount, pregnancyType);
	}

    public void trigger(ServerPlayer player) {
        super.trigger(player, instance -> instance.matches(player));
    }

	public static class TriggerInstance extends AbstractCriterionTriggerInstance {
        private final @Nullable MobEffectsPredicate effectsPredicate;
        private final OptionalInt babyCount;
        private final Optional<PregnancyType> pregnancyType;

		public TriggerInstance(ContextAwarePredicate player, MobEffectsPredicate effectsPredicate, @Nullable Integer babyCount, @Nullable PregnancyType pregnancyType) {
			super(GetPregnantTrigger.ID, player);
			this.effectsPredicate = effectsPredicate;
			this.babyCount = babyCount != null ? OptionalInt.of(babyCount.intValue()) : OptionalInt.empty();
			this.pregnancyType = Optional.ofNullable(pregnancyType);
		}

        public boolean matches(ServerPlayer player) {
        	if (this.effectsPredicate != null && !this.effectsPredicate.matches(player)) {
        		return false;
        	}
        	     	
        	return player.getCapability(MinepreggoCapabilities.PLAYER_DATA)
        			.resolve()
        			.flatMap(cap -> cap.getFemaleData().resolve())
        			.map(femaleCap -> {
        				if (femaleCap.isPregnant() && femaleCap.isPregnancyDataInitialized()) {
        					if (this.babyCount.isPresent() && this.babyCount.getAsInt() != femaleCap.getPregnancyData().getWomb().getNumOfBabies()) {
								return false;
							}
        					if (this.pregnancyType.isPresent() && this.pregnancyType.get() != femaleCap.getPrePregnancyData().map(data -> data.pregnancyType()).orElse(null)) {
        						return false;
        					}       						
        					return true;
        				}
        				return false;
        			}).orElse(Boolean.FALSE);
    	
        }
	}
}