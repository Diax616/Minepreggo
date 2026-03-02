package dev.dixmk.minepreggo.advancements.critereon;

import java.util.Optional;

import javax.annotation.Nullable;

import com.google.gson.JsonObject;

import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.utils.MinepreggoHelper;
import dev.dixmk.minepreggo.world.item.BabyItem;
import dev.dixmk.minepreggo.world.pregnancy.Gender;
import net.minecraft.advancements.critereon.AbstractCriterionTriggerInstance;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.DeserializationContext;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class CheckParentTrigger extends SimpleCriterionTrigger<CheckParentTrigger.TriggerInstance> {
	private static final ResourceLocation ID = MinepreggoHelper.fromThisNamespaceAndPath("check_parent");

    @Override
    public ResourceLocation getId() {
        return ID;
    }

    @Override
    protected TriggerInstance createInstance(JsonObject json, ContextAwarePredicate playerPredicate, DeserializationContext context) {
        EntityPredicate entityPredicate = EntityPredicate.fromJson(json.get("entity"));        
		ItemPredicate itemPredicate = ItemPredicate.fromJson(json.get("item"));
		Boolean verifyFather = null;
		Boolean verifyMother = null;
		
		if (json.has("baby") && json.get("baby").isJsonObject()) {
			JsonObject babyObj = json.getAsJsonObject("baby");
			if (babyObj.has("verify_father")) {
				verifyFather = babyObj.get("verify_father").getAsBoolean();
			}
			if (babyObj.has("verify_mother")) {
				verifyMother = babyObj.get("verify_mother").getAsBoolean();
			}
		}
		     
        return new TriggerInstance(playerPredicate, entityPredicate, itemPredicate, verifyFather, verifyMother);
    }

    public void trigger(ServerPlayer player, LivingEntity target, ItemStack itemstack) {
        super.trigger(player, instance -> instance.matches(player, target, itemstack));
    }
    
    public static class TriggerInstance extends AbstractCriterionTriggerInstance {
        private final @Nullable EntityPredicate entityPredicate;
        private final @Nullable ItemPredicate itemPredicate;
        private final Optional<Boolean> verifyFather;
        private final Optional<Boolean> verifyMother;
        
        public TriggerInstance(ContextAwarePredicate player, EntityPredicate entityPredicate, ItemPredicate itemPredicate, @Nullable Boolean verifyFather, @Nullable Boolean verifyMother) {
            super(ID, player);
            this.entityPredicate = entityPredicate;
            this.itemPredicate = itemPredicate;
            this.verifyFather = Optional.ofNullable(verifyFather);
            this.verifyMother = Optional.ofNullable(verifyMother);
        }

        public boolean matches(ServerPlayer player, LivingEntity target, ItemStack itemStack) {                           
            if (this.entityPredicate != null && !this.entityPredicate.matches(player, target)) {
                return false;
            }
            if (this.itemPredicate != null && !this.itemPredicate.matches(itemStack)) {
				return false;
			}           
            if (this.verifyFather.isEmpty() && this.verifyMother.isEmpty()) {
				return true;
			}
            
            
            if (!BabyItem.hasParentData(itemStack)) {
				return false;
			}
            
            return player.getCapability(MinepreggoCapabilities.PLAYER_DATA)
                    .map(cap -> {
                    	Gender playerGender = cap.getGender();
                    	
                    	if (playerGender == Gender.MALE) {
                    		if (this.verifyFather.orElse(Boolean.FALSE) && !BabyItem.isFatherOf(itemStack, player.getUUID())) {
								return false;
							}
                    		if (this.verifyMother.orElse(Boolean.FALSE) && !BabyItem.isMotherOf(itemStack, target.getUUID())) {
                    			return false;
                    		}           		
                    		return true;
                    	}
                    	else if (playerGender == Gender.FEMALE) {
                    		if (this.verifyFather.orElse(Boolean.FALSE) && !BabyItem.isFatherOf(itemStack, target.getUUID())) {
								return false;
							}
                    		if (this.verifyMother.orElse(Boolean.FALSE) && !BabyItem.isMotherOf(itemStack, player.getUUID())) {
                    			return false;
                    		}           		
                    		return true;
                    	}      	
                    	return false;
                    }).orElse(Boolean.FALSE);
        }
    }
}
