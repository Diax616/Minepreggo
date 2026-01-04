package dev.dixmk.minepreggo.world.entity.monster;

import net.minecraftforge.network.PlayMessages;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.monster.Witch;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;

import java.util.List;
import java.util.Optional;

import dev.dixmk.minepreggo.MinepreggoMod;
import dev.dixmk.minepreggo.init.MinepreggoCapabilities;
import dev.dixmk.minepreggo.init.MinepreggoModEntities;
import dev.dixmk.minepreggo.init.MinepreggoModPotions;
import dev.dixmk.minepreggo.utils.MinepreggoHelper;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMob;
import dev.dixmk.minepreggo.world.entity.preggo.Species;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractTamablePregnantCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.TamableHumanoidCreeperGirl;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.AbstractTamablePregnantZombieGirl;
import dev.dixmk.minepreggo.world.entity.preggo.zombie.TamableZombieGirl;



/**
 * 
 * Fertility Witch spawn rules have issues, she can spawn in mushroom fields which does no sense, other entities like MonsterZombieGirl, MonsterCreeperGirl even its father class, Witch, 
 * cannot spawn in mushroom fields. I've deggugged and tested it, but I couldn't find the reason why Fertility Witch can spawn in mushroom fields.
 * For now, I've created a special tag "hostil.json" in data/minepreggo/tags/worldgen/biome/ which includes all biomes except mushroom fields, and added this tag to the spawn rules of Fertility Witch in 
 * data/minepreggo/forge/biome_modifier/fertility_witch_biome_modifier.json, so she won't spawn in mushroom fields anymore. But it's not the ideal solution.
 * 
 * @author DixMK
 * 
 * */


public class FertilityWitch extends Witch {
	
	private static final List<Species> HARMFUL_SPECIES = List.of(Species.ZOMBIE, Species.CREEPER, Species.ENDER);
	
	private HarmfulPregnancyPotion harmfulPregnancyPotion = null;
	
	public FertilityWitch(PlayMessages.SpawnEntity packet, Level world) {
		this(MinepreggoModEntities.FERTILITY_WITCH.get(), world);
	}

	public FertilityWitch(EntityType<FertilityWitch> type, Level world) {
		super(type, world);
		setMaxUpStep(0.6f);
		xpReward = 0;
		setNoAi(false);
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		if (this.harmfulPregnancyPotion != null) {
			compound.putString("harmfulPregnancyPotion", harmfulPregnancyPotion.name());
		}
	}
	
	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);	
		if (compound.contains("harmfulPregnancyPotion", Tag.TAG_STRING)) {
			harmfulPregnancyPotion = HarmfulPregnancyPotion.valueOf(compound.getString("harmfulPregnancyPotion"));
		}
	}
	
	@Override
	public boolean canJoinRaid() {
		return false;
	}

	@Override
	protected ResourceLocation getDefaultLootTable() {
	    return MinepreggoHelper.fromNamespaceAndPath(MinepreggoMod.MODID, "entities/fertility_witch_loot");
	}
	
	public void chooseRandomHarmfulPregnancyPotion() {
		this.harmfulPregnancyPotion = this.random.nextBoolean() ? HarmfulPregnancyPotion.DELAY : HarmfulPregnancyPotion.ACCELERATION;
	}
	
	private Potion getRandomHarmfulPregnancyPotion() {
		if (this.random.nextFloat() < 0.1) {
			return MinepreggoModPotions.getRandomBabyDuplicationPotion(random);
		}
		
		if (this.harmfulPregnancyPotion == HarmfulPregnancyPotion.ACCELERATION) {
			return MinepreggoModPotions.getRandomPregnancyAccelerationPotion(random);
		}
		return MinepreggoModPotions.getRandomPregnancyDelayPotion(random);
	}
	
	private Potion getRandomHarmfulImpregnationPotion() {
		switch (HARMFUL_SPECIES.get(random.nextInt(HARMFUL_SPECIES.size()))) {
		case ENDER: {
			return random.nextBoolean() ? MinepreggoModPotions.getRandomEnderImpregnationPotion(random) : MinepreggoModPotions.getRandomHumanoidEnderImpregnationPotion(random);		
		}
		case CREEPER: {
			return random.nextBoolean() ? MinepreggoModPotions.getRandomCreeperImpregnationPotion(random) : MinepreggoModPotions.getRandomHumanoidCreeperImpregnationPotion(random);		
		}
		default:
			return MinepreggoModPotions.getRandomZombieImpregnationPotion(random);
		}
	}

	@Override
	public void performRangedAttack(LivingEntity p_34143_, float p_34144_) {
		if (this.isDrinkingPotion()) return;
			
		if (this.harmfulPregnancyPotion == null) 
			chooseRandomHarmfulPregnancyPotion();
		
		Vec3 vec3 = p_34143_.getDeltaMovement();
		double d0 = p_34143_.getX() + vec3.x - this.getX();
		double d1 = p_34143_.getEyeY() - 1.1 - this.getY();
		double d2 = p_34143_.getZ() + vec3.z - this.getZ();
		double d3 = Math.sqrt(d0 * d0 + d2 * d2);
		Potion potion = Potions.HARMING;
		
		if (p_34143_ instanceof Raider) {
			if (p_34143_.getHealth() <= 6.0F) {
				potion = Potions.HEALING;
			}
			else {
				potion = Potions.REGENERATION;
			}
			this.setTarget(null);
		}
		else if (d3 >= 6D && p_34143_ instanceof Player player && this.random.nextBoolean()) {		
			Optional<Boolean> result = player.getCapability(MinepreggoCapabilities.PLAYER_DATA).map(cap -> 
				cap.getFemaleData().map(femaleData -> femaleData.isPregnant() && femaleData.isPregnancySystemInitialized())
			).orElse(Optional.empty());
			
			if (result.isPresent()) {			
				if (result.get().booleanValue()) {
					potion = getRandomHarmfulPregnancyPotion();
				}
				else {
					potion = getRandomHarmfulImpregnationPotion();
				}
			}
		}		
		else if (d3 >= 6D && p_34143_ instanceof PreggoMob preggoMob && this.random.nextBoolean()) {
			Potion result = null;
			if (preggoMob instanceof TamableZombieGirl || preggoMob instanceof TamableHumanoidCreeperGirl) {
				result = MinepreggoModPotions.getRandomImpregnationPotion(random);
			}
			else if (preggoMob instanceof AbstractTamablePregnantZombieGirl<?,?> || preggoMob instanceof AbstractTamablePregnantCreeperGirl<?,?>) {
				result = getRandomHarmfulPregnancyPotion();
			}
			
			if (result != null) {
				potion = result;
			}
		}	
		else if (p_34143_.getHealth() >= 8.0F && !p_34143_.hasEffect(MobEffects.POISON)) {
			potion = Potions.POISON;
		}
		else if (d3 <= 3.0D && !p_34143_.hasEffect(MobEffects.WEAKNESS) && this.random.nextFloat() < 0.25F) {
			potion = Potions.WEAKNESS;
		}

		ThrownPotion thrownpotion = new ThrownPotion(this.level(), this);
		thrownpotion.setItem(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), potion));
		thrownpotion.setXRot(thrownpotion.getXRot() - -20.0F);
		thrownpotion.shoot(d0, d1 + d3 * 0.2D, d2, 0.75F, 8.0F);
		if (!this.isSilent()) {
			this.level().playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.WITCH_THROW, this.getSoundSource(), 1.0F, 0.8F + this.random.nextFloat() * 0.4F);
		}

		this.level().addFreshEntity(thrownpotion);
	}
	
	private enum HarmfulPregnancyPotion {
		DELAY,
		ACCELERATION
	}
}
