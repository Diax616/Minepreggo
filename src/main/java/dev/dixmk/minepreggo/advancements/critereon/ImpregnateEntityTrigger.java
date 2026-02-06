package dev.dixmk.minepreggo.advancements.critereon;

import java.util.Optional;
import java.util.Set;

import javax.annotation.Nullable;

import com.google.gson.JsonObject;

import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.utils.MinepreggoHelper;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.Species;
import net.minecraft.advancements.critereon.AbstractCriterionTriggerInstance;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.DeserializationContext;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class ImpregnateEntityTrigger extends SimpleCriterionTrigger<ImpregnateEntityTrigger.TriggerInstance> {
	private static final ResourceLocation ID = MinepreggoHelper.fromThisMod("impregnate_entity");

    @Override
    public ResourceLocation getId() {
        return ID;
    }

    @Override
    protected TriggerInstance createInstance(JsonObject json, ContextAwarePredicate playerPredicate, DeserializationContext context) {
        EntityPredicate entityPredicate = EntityPredicate.fromJson(json.get("entity"));        
        Set<Species> entityGottenPregnant = null;
        if (json.has("entity_gotten_pregnant")) {
            entityGottenPregnant = JsonHelper.parseSpeciesSet(json.getAsJsonObject("entity_gotten_pregnant"), "species");
        }
        
        return new TriggerInstance(playerPredicate, entityPredicate, entityGottenPregnant);
    }

    public void trigger(ServerPlayer malePlayer, LivingEntity impregnableEntity) {
        super.trigger(malePlayer, instance -> instance.matches(malePlayer, impregnableEntity));
    }
    
    public static class TriggerInstance extends AbstractCriterionTriggerInstance {
        private final @Nullable EntityPredicate entityPredicate;
        private final Optional<Set<Species>> entityGottenPregnant;
        
        public TriggerInstance(ContextAwarePredicate player, @Nullable EntityPredicate entityPredicate, @Nullable Set<Species> entityGottenPregnant) {
            super(ID, player);
            this.entityPredicate = entityPredicate;
            this.entityGottenPregnant = Optional.ofNullable(entityGottenPregnant);
        }

        public boolean matches(ServerPlayer malePlayer, LivingEntity impregnableEntity) {                           
            if (this.entityPredicate != null && !this.entityPredicate.matches(malePlayer, impregnableEntity)) {
                return false;
            }

            return malePlayer.getCapability(MinepreggoCapabilities.PLAYER_DATA)
                    .map(cap -> {
                        var playerStadistics = cap.getPlayerStatistic();
                        if (impregnableEntity instanceof Player) {
                            playerStadistics.addEntityGottenPregnant(Species.HUMAN);
                        } else if (impregnableEntity instanceof PreggoMob preggoMob) {
                            playerStadistics.addEntityGottenPregnant(preggoMob.getTypeOfSpecies());
                        }
                        
                        if (this.entityGottenPregnant.isEmpty()) {
                            return true;
                        }
                        
                        return playerStadistics.getEntitiesGottenPregnant().containsAll(this.entityGottenPregnant.get());
                    }).orElse(Boolean.FALSE);
        }
    }
}
