package dev.dixmk.minepreggo.advancements.critereon;

import java.util.Optional;

import javax.annotation.Nullable;

import com.google.gson.JsonObject;

import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.network.capability.IPlayerData;
import dev.dixmk.minepreggo.utils.MinepreggoHelper;
import dev.dixmk.minepreggo.world.item.AbstractBaby;
import dev.dixmk.minepreggo.world.pregnancy.Gender;
import net.minecraft.advancements.critereon.AbstractCriterionTriggerInstance;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.DeserializationContext;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;

public class BabyTradeTrigger extends SimpleCriterionTrigger<BabyTradeTrigger.TriggerInstance> {

	private static final ResourceLocation ID = MinepreggoHelper.fromThisMod("baby_trade");

	@Override
	public ResourceLocation getId() {
		return ID;
	}

	@Override
	protected TriggerInstance createInstance(JsonObject json, ContextAwarePredicate predicate, DeserializationContext context) {
		ItemPredicate itemPredicate = ItemPredicate.fromJson(json.get("item"));
		Boolean verifyParent = null;
		
		if (json.has("baby") && json.get("baby").isJsonObject()) {
			JsonObject babyObj = json.getAsJsonObject("baby");
			if (babyObj.has("verify_parent")) {
				verifyParent = babyObj.get("verify_parent").getAsBoolean();
			}
		}

		return new TriggerInstance(predicate, itemPredicate, verifyParent);
	}

	public void trigger(ServerPlayer player, ItemStack stack, @Nullable Boolean verifyParent) {
		super.trigger(player, instance -> instance.matches(player, stack, Optional.ofNullable(verifyParent)));
	}

	public static class TriggerInstance extends AbstractCriterionTriggerInstance {
		private final @Nullable ItemPredicate itemPredicate;
		private final Optional<Boolean> verifyParent;

		public TriggerInstance(ContextAwarePredicate player, ItemPredicate itemPredicate, @Nullable Boolean verifyParent) {
			super(BabyTradeTrigger.ID, player);
			this.itemPredicate = itemPredicate;
			this.verifyParent = Optional.ofNullable(verifyParent);
		}

		public boolean matches(ServerPlayer player, ItemStack stack, Optional<Boolean> verifyParent) {	
			if (this.itemPredicate != null && !this.itemPredicate.matches(stack)) {
				return false;
			}

			Gender genderPlayer = player.getCapability(MinepreggoCapabilities.PLAYER_DATA).map(IPlayerData::getGender).orElse(Gender.UNKNOWN);

			if (this.verifyParent.isPresent() && (verifyParent.isEmpty() || !this.verifyParent.get().equals(verifyParent.get()))) {
				return false;
			}
		
			return (genderPlayer == Gender.MALE && AbstractBaby.isFatherOf(stack, player.getUUID())) ||
					(genderPlayer == Gender.FEMALE && AbstractBaby.isMotherOf(stack, player.getUUID()));
		}
	}
}

