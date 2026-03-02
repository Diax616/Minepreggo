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
import dev.dixmk.minepreggo.world.item.checkup.PrenatalCheckups.PrenatalCheckup;
import net.minecraft.advancements.critereon.AbstractCriterionTriggerInstance;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.DeserializationContext;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

public class PrenantalCheckUpTrigger extends SimpleCriterionTrigger<PrenantalCheckUpTrigger.TriggerInstance> {
	private	static final ResourceLocation ID = MinepreggoHelper.fromThisNamespaceAndPath("prenantal_checkup");

	@Override
	public ResourceLocation getId() {
		return ID;
	}

	@Override
	protected TriggerInstance createInstance(JsonObject json, ContextAwarePredicate predicate, DeserializationContext context) {
		Set<PrenatalCheckup> prenatalCheckups = null;
		if (json.has("prenatal_checkup")) {
			JsonObject checkupsJson = json.getAsJsonObject("prenatal_checkup");
			String key = "checkups";
			if (checkupsJson.has(key) && checkupsJson.get(key).isJsonArray()) {
				prenatalCheckups = EnumSet.copyOf(Arrays.asList(new Gson().fromJson(checkupsJson.get(key).getAsJsonArray(), PrenatalCheckup[].class)));
			}
		}
		return new TriggerInstance(predicate, prenatalCheckups);
	}

	public void trigger(ServerPlayer player) {
		super.trigger(player, instance -> instance.matches(player));
	}

	public static class TriggerInstance extends AbstractCriterionTriggerInstance {
		private final Optional<Set<PrenatalCheckup>> prenatalCheckups;

		public TriggerInstance(ContextAwarePredicate player, @Nullable Set<PrenatalCheckup> prenatalCheckups) {
			super(PrenantalCheckUpTrigger.ID, player);
			this.prenatalCheckups = Optional.ofNullable(prenatalCheckups);
		}

		public boolean matches(ServerPlayer player) {	
			return player.getCapability(MinepreggoCapabilities.PLAYER_DATA)
					.resolve()
					.flatMap(cap -> 
						cap.getFemaleData().map(femaleData -> {
							if (this.prenatalCheckups.isPresent()) {
								return cap.getPlayerStatistic().getPrenatalCheckupsDoneMyself().containsAll(this.prenatalCheckups.get());
							}
							return true;
						})
					).orElse(Boolean.FALSE);
		}
	}
}
