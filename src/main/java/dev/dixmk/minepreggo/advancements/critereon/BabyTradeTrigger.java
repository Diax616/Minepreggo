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
		Gender gender = null;
		
		if (json.has("source") && json.get("source").isJsonObject()) {
		    JsonObject sourceObj = json.getAsJsonObject("source");
		    if (sourceObj.has("gender")) {
		    	gender = Gender.valueOf(sourceObj.get("gender").getAsString().toUpperCase());
		    }
		    if (sourceObj.has("verify_parent")) {
		        verifyParent = sourceObj.get("verify_parent").getAsBoolean();
		    }
		}

		return new TriggerInstance(predicate, itemPredicate, verifyParent, gender);
	}

	public void trigger(ServerPlayer player, ItemStack stack) {
		super.trigger(player, instance -> instance.matches(player, stack));
	}

	public static class TriggerInstance extends AbstractCriterionTriggerInstance {
		private final @Nullable ItemPredicate itemPredicate;
		private final Optional<Boolean> verifyParent;
		private final Optional<Gender> gender;

		public TriggerInstance(ContextAwarePredicate player, ItemPredicate itemPredicate, @Nullable Boolean verifyParent, @Nullable Gender gender) {
			super(BabyTradeTrigger.ID, player);
			this.itemPredicate = itemPredicate;
			this.verifyParent = Optional.ofNullable(verifyParent);
			this.gender = Optional.ofNullable(gender);
		}

		public boolean matches(ServerPlayer player, ItemStack stack) {    
		    if (this.itemPredicate != null && !this.itemPredicate.matches(stack)) {
		        return false;
		    }

		    if (this.verifyParent.isEmpty() && this.gender.isEmpty()) {
		        return true;
		    }
		    
		    Gender genderPlayer = player.getCapability(MinepreggoCapabilities.PLAYER_DATA)
			        .map(IPlayerData::getGender)
			        .orElse(Gender.UNKNOWN);
		    
		    if ((this.verifyParent.isEmpty() || !this.verifyParent.get()) && this.gender.isPresent()) {
		        return this.gender.get() == genderPlayer;
		    }
		     
		    if (this.verifyParent.isPresent() && this.gender.isPresent()) {
			    return (this.gender.get() == Gender.MALE &&	genderPlayer == Gender.MALE && AbstractBaby.isFatherOf(stack, player.getUUID())) ||
				           (this.gender.get() == Gender.FEMALE && genderPlayer == Gender.FEMALE && AbstractBaby.isMotherOf(stack, player.getUUID()));
		    }
		    
		    return false;
		}
	}
}

