package dev.dixmk.minepreggo.init;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.utils.MinepreggoHelper;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraftforge.registries.DeferredRegister;

public class MinepreggoModDamageSources {
	
	private MinepreggoModDamageSources() {}
	  
    public static final DeferredRegister<DamageType> REGISTRY = DeferredRegister.create(Registries.DAMAGE_TYPE, MinepreggoMod.MODID);

    public static final ResourceKey<DamageType> BELLY_BURST = ResourceKey.create(Registries.DAMAGE_TYPE, MinepreggoHelper.fromThisMod("belly_burst"));
    public static final ResourceKey<DamageType> PREGNANCY_PAIN = ResourceKey.create(Registries.DAMAGE_TYPE, MinepreggoHelper.fromThisMod("pregnancy_pain"));

}
