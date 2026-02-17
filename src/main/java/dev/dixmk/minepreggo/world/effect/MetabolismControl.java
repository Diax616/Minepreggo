package dev.dixmk.minepreggo.world.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;

public class MetabolismControl extends MobEffect {
      
    public MetabolismControl() {
        super(MobEffectCategory.BENEFICIAL, -39424);
    }
    
    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity.level().isClientSide && entity instanceof Player player) {
            FoodData foodData = player.getFoodData();
            float currentSaturation = foodData.getSaturationLevel();
            
            if (currentSaturation > 0) {
                float bonus = currentSaturation * 0.25F * (amplifier + 1);
                float newSaturation = Math.min(currentSaturation + bonus, foodData.getFoodLevel());
                foodData.setSaturation(newSaturation);
            }
        }
    }
    
    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration % 10 == 0;
    }
}
