package dev.dixmk.minepreggo;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.event.config.ModConfigEvent;

import org.apache.commons.lang3.tuple.Pair;

import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySystemHelper;

public class MinepreggoModConfig {
	
	private MinepreggoModConfig() {}
	
    static final ForgeConfigSpec CLIENT_SPEC;
    static final ForgeConfigSpec SERVER_SPEC;
 
    public static final Client CLIENT;
    public static final Server SERVER;
   
    static {
    	Pair<Client, ForgeConfigSpec> client = 
                new ForgeConfigSpec.Builder().configure(Client::new);     
        CLIENT_SPEC = client.getRight();
        CLIENT = client.getLeft();
   
        final Pair<Server, ForgeConfigSpec> server = 
                new ForgeConfigSpec.Builder().configure(Server::new);
        SERVER_SPEC = server.getRight();
        SERVER = server.getLeft();
    }
    
    static void onLoad(ModConfigEvent.Loading event) {  		  	
    	if (event.getConfig().getSpec() == CLIENT_SPEC) {
        	CLIENT.enablePlayerMoans = CLIENT.enablePlayerMoansConfig.get();
        	CLIENT.enablePreggoMobsMoans = CLIENT.enablePreggoMobsMoansConfig.get();
        	CLIENT.enableBellySounds = CLIENT.enableBellySoundsConfig.get();
        }
        else if (event.getConfig().getSpec() == SERVER_SPEC) {
        	SERVER.babyCreeperGirlProbability = (float) SERVER.babyCreeperGirlProbabilityConfig.get().doubleValue();
        	SERVER.babyZombieGirlProbability = (float) SERVER.babyZombieGirlProbabilityConfig.get().doubleValue(); 
        	
        	SERVER.totalTickByPregnancyDays = SERVER.totalTickByPregnancyDaysConfig.get();
        	SERVER.totalPregnancyDays = SERVER.totalPregnancyDaysConfig.get();
        	SERVER.totalTicksToStartPregnancy = SERVER.totalTicksToStartPregnancyConfig.get();    
        	SERVER.totalTicksOfMaternityLactation = SERVER.totalTicksOfMaternityLactationConfig.get();
        	SERVER.totalTicksOfPostPregnancyPhase = SERVER.totalTicksOfPostPregnancyPhaseConfig.get();
        	SERVER.totalTicksOfHungryP0 = SERVER.totalTicksOfHungryConfig.get();
        	SERVER.totalTicksOfCravingP1 = SERVER.totalTicksOfCravingConfig.get();
        	SERVER.totalTicksOfMilkingP2 = SERVER.totalTicksOfMilkingConfig.get();
        	SERVER.totalTicksOfBellyRubsP3 = SERVER.totalTicksOfBellyRubsConfig.get();
        	SERVER.totalTicksOfHornyP4 = SERVER.totalTicksOfHornyConfig.get();
        	SERVER.enableBellyColisionsForPlayer = SERVER.enableBellyColisionsForPlayerConfig.get();
        	SERVER.enableBellyColisionsForPreggoMob = SERVER.enableBellyColisionsForPreggoMobConfig.get();
        	SERVER.enableMountingEntitiesInLaterPregnancyPhases = SERVER.enableMountingEntitiesInLaterPregnancyPhasesConfig.get();
        	SERVER.enablePreggoMobsTeleportToPlayer = SERVER.enablePreggoMobsTeleportToPlayerConfig.get();
        	SERVER.enableSpawningHostilPregnantZombieGirls = SERVER.enableSpawningHostilPregnantZombieGirlsConfig.get();
        	SERVER.enableSpawningHostilPregnantMonsterCreeperGirls = SERVER.enableSpawningHostilPregnantMonsterCreeperGirlsConfig.get();
        	SERVER.enableSpawningHostilPregnantHumanoidCreeperGirls = SERVER.enableSpawningHostilPregnantHumanoidCreeperGirlsConfig.get();
        	SERVER.enableSpawningHostilPregnantMonsterEnderWomen = SERVER.enableSpawningHostilPregnantMonsterEnderWomenConfig.get();
        	SERVER.totalTicksForPregnancyHealing = SERVER.totalTicksForPregnancyHealingConfig.get();
        	SERVER.pregnacyHealingAmount = SERVER.pregnacyHealingAmountConfig.get();
        	
        	SERVER.calculateHungryValues();
        	SERVER.calculateCravingValues();
        	SERVER.calculateMilkingValues();
        	SERVER.calculateBellyRubsValues();
        	SERVER.calculateHornyValues();
        }
    }
  
    public static class Client {
    	private final ForgeConfigSpec.BooleanValue enablePlayerMoansConfig;
    	private final ForgeConfigSpec.BooleanValue enablePreggoMobsMoansConfig;
    	private final ForgeConfigSpec.BooleanValue enableBellySoundsConfig;
	
        private boolean enablePreggoMobsMoans;  
        private boolean enablePlayerMoans;
        private boolean enableBellySounds;
    	
    	private Client(ForgeConfigSpec.Builder builder) {
            builder.push("Client");

            enablePlayerMoansConfig = builder
                    .comment("Enable or disable player moans. (NOT WORKING)")
                    .define("enablePlayerMoans", false);

            enablePreggoMobsMoansConfig = builder
                    .comment("Enable or disable pregnant mobs moans. (NOT WORKING)")
                    .define("enablePreggoMobsMoans", false);

            enableBellySoundsConfig = builder
                    .comment("Enable or disable belly sounds. (NOT WORKING)")
                    .define("enableBellySounds", false);
            
            builder.pop();
        }
    	 
        public boolean isPlayerMoansEnable() {
        	return enablePlayerMoans;
        }
        
        public boolean isPreggoMobsMoansEnable() {
        	return enablePreggoMobsMoans;
        }
        
        public boolean isBellySoundsEnable() {
    		return enableBellySounds;
    	}
    }
        
    public static class Server {
        private final ForgeConfigSpec.DoubleValue babyCreeperGirlProbabilityConfig;   
        private final ForgeConfigSpec.DoubleValue babyZombieGirlProbabilityConfig;
 
        private final ForgeConfigSpec.IntValue totalPregnancyDaysConfig;
        private final ForgeConfigSpec.IntValue totalTicksToStartPregnancyConfig;
        private final ForgeConfigSpec.IntValue totalTickByPregnancyDaysConfig;
        private final ForgeConfigSpec.IntValue totalTicksOfHungryConfig;
        private final ForgeConfigSpec.IntValue totalTicksOfCravingConfig;
        private final ForgeConfigSpec.IntValue totalTicksOfMilkingConfig;
        private final ForgeConfigSpec.IntValue totalTicksOfBellyRubsConfig;
        private final ForgeConfigSpec.IntValue totalTicksOfHornyConfig;
        private final ForgeConfigSpec.IntValue totalTicksOfMaternityLactationConfig;
        private final ForgeConfigSpec.IntValue totalTicksOfPostPregnancyPhaseConfig;
        
        private final ForgeConfigSpec.BooleanValue enableBellyColisionsForPlayerConfig;
        private final ForgeConfigSpec.BooleanValue enableBellyColisionsForPreggoMobConfig;
        private final ForgeConfigSpec.BooleanValue enableMountingEntitiesInLaterPregnancyPhasesConfig;
        private final ForgeConfigSpec.BooleanValue enablePreggoMobsTeleportToPlayerConfig;
        private final ForgeConfigSpec.BooleanValue enableSpawningHostilPregnantZombieGirlsConfig;
        private final ForgeConfigSpec.BooleanValue enableSpawningHostilPregnantMonsterCreeperGirlsConfig;
        private final ForgeConfigSpec.BooleanValue enableSpawningHostilPregnantHumanoidCreeperGirlsConfig;
        private final ForgeConfigSpec.BooleanValue enableSpawningHostilPregnantMonsterEnderWomenConfig;

        private final ForgeConfigSpec.IntValue totalTicksForPregnancyHealingConfig;
        private final ForgeConfigSpec.IntValue pregnacyHealingAmountConfig;

        private int totalTickByPregnancyDays;
        private int totalPregnancyDays;
        private int totalTicksToStartPregnancy;
        private int totalTicksOfMaternityLactation;
        private int totalTicksOfPostPregnancyPhase;
        
        private int totalTicksOfHungryP0;
        private int totalTicksOfHungryP1;
        private int totalTicksOfHungryP2;
        private int totalTicksOfHungryP3;
        private int totalTicksOfHungryP4;
        private int totalTicksOfHungryP5;
        private int totalTicksOfHungryP6;
        private int totalTicksOfHungryP7;
        private int totalTicksOfHungryP8;

        private int totalTicksOfCravingP1;
        private int totalTicksOfCravingP2;
        private int totalTicksOfCravingP3;
        private int totalTicksOfCravingP4;
        private int totalTicksOfCravingP5;
        private int totalTicksOfCravingP6;
        private int totalTicksOfCravingP7;
        private int totalTicksOfCravingP8;
        
        private int totalTicksOfMilkingP2;
        private int totalTicksOfMilkingP3;
        private int totalTicksOfMilkingP4;
        private int totalTicksOfMilkingP5;
        private int totalTicksOfMilkingP6;
        private int totalTicksOfMilkingP7;
        private int totalTicksOfMilkingP8;
        
        private int totalTicksOfBellyRubsP3;
        private int totalTicksOfBellyRubsP4;
        private int totalTicksOfBellyRubsP5;
        private int totalTicksOfBellyRubsP6;
        private int totalTicksOfBellyRubsP7;
        private int totalTicksOfBellyRubsP8;
        
        private int totalTicksOfHornyP4;
        private int totalTicksOfHornyP5;
        private int totalTicksOfHornyP6;
        private int totalTicksOfHornyP7;
        private int totalTicksOfHornyP8;
        
        private float babyCreeperGirlProbability;
        private float babyZombieGirlProbability;

        private boolean enableBellyColisionsForPlayer;
        private boolean enableBellyColisionsForPreggoMob;
        
        // It only applies in final phase of pregnancy from P5 to P8
        private boolean enableMountingEntitiesInLaterPregnancyPhases;
        private boolean enablePreggoMobsTeleportToPlayer;
        private boolean enableSpawningHostilPregnantZombieGirls;
        private boolean enableSpawningHostilPregnantMonsterCreeperGirls;
        private boolean enableSpawningHostilPregnantHumanoidCreeperGirls;
        private boolean enableSpawningHostilPregnantMonsterEnderWomen;
        
        private int totalTicksForPregnancyHealing;
        private int pregnacyHealingAmount;
            
        private Server(ForgeConfigSpec.Builder builder) {
            builder.push("Server");
            
            babyCreeperGirlProbabilityConfig = builder
                    .comment("probability of spawning a girl baby creeper.")
                    .defineInRange("babyCreeperGirlProbability", 0.1, 0.0, 1.0);

            babyZombieGirlProbabilityConfig = builder
                    .comment("probability of spawning a girl baby zombie.")
                    .defineInRange("babyZombieGirlProbability", 0.15, 0.0, 1.0);
            
            totalPregnancyDaysConfig = builder
                    .comment("Total number of pregnancy days.")
                    .defineInRange("totalPregnancyDays", 50, 20, Integer.MAX_VALUE);
            
            totalTickByPregnancyDaysConfig = builder
                    .comment("Total ticks per pregnancy day.")
                    .defineInRange("totalTickByDays", 24000, 100, 24000);

            totalTicksToStartPregnancyConfig = builder
                    .comment("Ticks to start pregnancy after mating.")
                    .defineInRange("totalTicksToStartPregnancy", 4800, 100, 24000);
            
            totalTicksOfMaternityLactationConfig = builder
					.comment("Total ticks of maternity lactation for preggo mobs and player.")
					.defineInRange("totalTicksOfMaternityLactation", 1200, 100, Integer.MAX_VALUE);
                   
            totalTicksOfPostPregnancyPhaseConfig = builder
            		.comment("Total ticks of post pregnancy phase for preggo mobs and player.")
            		.defineInRange("totalTicksOfPostPregnancyPhase", 24000, 100, Integer.MAX_VALUE);
            
            totalTicksOfHungryConfig = builder
                    .comment("Total ticks of hungry for preggo mobs.")
                    .defineInRange("totalTicksOfHungry", 8400, 100, 24000);
            
            totalTicksOfCravingConfig = builder
                    .comment("Total ticks of craving for pregnant entities.")
                    .defineInRange("totalTicksOfCraving", 4800, 100, 24000);
            
            totalTicksOfMilkingConfig = builder
                    .comment("total ticks of milking for pregnant entities.")
                    .defineInRange("totalTicksOfMilking", 3600, 100, 24000);
            
            totalTicksOfBellyRubsConfig = builder
                    .comment("total ticks of belly rubs for pregnant entities.")
                    .defineInRange("totalTicksOfBellyRubs", 3600, 100, 24000);
            
            totalTicksOfHornyConfig = builder
                    .comment("total ticks of horny for pregnant entities.")
                    .defineInRange("totalTicksOfHorny", 6000, 100, 24000);
            
            enableBellyColisionsForPlayerConfig = builder
            		.comment("Enable or disable belly colisions for player. EXPERIMENTAL.")
					.define("enableBellyColisionsForPlayer", false);
            
            enableBellyColisionsForPreggoMobConfig = builder
					.comment("Enable or disable belly colisions for preggo mobs. EXPERIMENTAL.")
					.define("enableBellyColisionsForPreggoMob", false);
            
            enableMountingEntitiesInLaterPregnancyPhasesConfig = builder
					.comment("Enable or disable the ability to mount entities in later pregnancy phases (P6 to P8).")
					.define("enableMountingEntitiesInLaterPregnancyPhases", true);
            
            enablePreggoMobsTeleportToPlayerConfig = builder
            		.comment("Enable or disable preggo mobs teleporting to player when the distance is too far.")
            		.define("enablePreggoMobsTeleportToPlayer", false);
            
            enableSpawningHostilPregnantZombieGirlsConfig = builder
					.comment("Enable or disable the natural spawning of hostile pregnant zombie girls.")
					.define("enableSpawningHostilPregnantZombieGirls", true);
            
            enableSpawningHostilPregnantMonsterCreeperGirlsConfig = builder
            		.comment("Enable or disable the natural spawning of hostile pregnant monster creeper girls.")
            		.define("enableSpawningHostilPregnantMonsterCreeperGirls", true);
            
            enableSpawningHostilPregnantHumanoidCreeperGirlsConfig = builder
            		.comment("Enable or disable the natural spawning of hostile pregnant humanoid creeper girls.")
            		.define("enableSpawningHostilPregnantHumanoidCreeperGirls", true);
            
            enableSpawningHostilPregnantMonsterEnderWomenConfig = builder
            		.comment("Enable or disable the natural spawning of hostile pregnant monster ender women.")
            		.define("enableSpawningHostilPregnantMonsterEnderWomen", true);
            
            totalTicksForPregnancyHealingConfig = builder
					.comment("Total ticks for pregnancy healing for pregnant entities.")
					.defineInRange("totalTicksOfPregnancyHealing", 6000, 100, 24000);
            
            pregnacyHealingAmountConfig = builder
            		.comment("Amount of health healed during pregnancy healing for pregnant entities. This amount is reduced 5% by each pregnancy phase.")
            		.defineInRange("pregnacyHealingAmount", 25, 0, PregnancySystemHelper.MAX_PREGNANCY_HEALTH);
            
            builder.pop();
        }
        
        public int getTotalPregnancyDays() {
        	return totalPregnancyDays;
        }
         
        public int getTotalTicksByPregnancyDay() {
        	return totalTickByPregnancyDays;
        }
        
        public int getTotalTicksToStartPregnancy() {
        	return totalTicksToStartPregnancy;
        }
        
        public int getTotalTicksOfMaternityLactation() {
    		return totalTicksOfMaternityLactation;
    	}
        
        public int getTotalTicksOfPostPregnancyPhase() {
        	return totalTicksOfPostPregnancyPhase;
        }
        
        public int getTotalTicksOfHungryP0() {
        	return totalTicksOfHungryP0;
        }

        public int getTotalTicksOfHungryP1() {
        	return totalTicksOfHungryP1;
        }
        
        public int getTotalTicksOfHungryP2() {
        	return totalTicksOfHungryP2;
        }
        
        public int getTotalTicksOfHungryP3() {
        	return totalTicksOfHungryP3;
        }
        
        public int getTotalTicksOfHungryP4() {
        	return totalTicksOfHungryP4;
        }
        
        public int getTotalTicksOfHungryP5() {
        	return totalTicksOfHungryP5;
        }
        
        public int getTotalTicksOfHungryP6() {
        	return totalTicksOfHungryP6;
        }
        
        public int getTotalTicksOfHungryP7() {
        	return totalTicksOfHungryP7;
        }
        
        public int getTotalTicksOfHungryP8() {
        	return totalTicksOfHungryP8;
        }
        
        public int getTotalTicksOfMilkingP2() {
        	return totalTicksOfMilkingP2;
        }
        
        public int getTotalTicksOfMilkingP3() {
        	return totalTicksOfMilkingP3;
        }
        
        public int getTotalTicksOfMilkingP4() {
        	return totalTicksOfMilkingP4;
        }
        
        public int getTotalTicksOfMilkingP5() {
        	return totalTicksOfMilkingP5;
        }
        
        public int getTotalTicksOfMilkingP6() {
        	return totalTicksOfMilkingP6;
        }
        
        public int getTotalTicksOfMilkingP7() {
        	return totalTicksOfMilkingP7;
        }
        
        public int getTotalTicksOfMilkingP8() {
        	return totalTicksOfMilkingP8;
        }
        
        public int getTotalTicksOfBellyRubsP3() {
        	return totalTicksOfBellyRubsP3;
        }
        
        public int getTotalTicksOfBellyRubsP4() {
        	return totalTicksOfBellyRubsP4;
        }
        
        public int getTotalTicksOfBellyRubsP5() {
        	return totalTicksOfBellyRubsP5;
        }
        
        public int getTotalTicksOfBellyRubsP6() {
        	return totalTicksOfBellyRubsP6;
        }
        
        public int getTotalTicksOfBellyRubsP7() {
        	return totalTicksOfBellyRubsP7;
        }
        
        public int getTotalTicksOfBellyRubsP8() {
        	return totalTicksOfBellyRubsP8;
        }
        
        public int getTotalTicksOfCravingP1() {
        	return totalTicksOfCravingP1;
        }
        
        public int getTotalTicksOfCravingP2() {
        	return totalTicksOfCravingP2;
        }
        
        public int getTotalTicksOfCravingP3() {
        	return totalTicksOfCravingP3;
        }
        
        public int getTotalTicksOfCravingP4() {
        	return totalTicksOfCravingP4;
        }
        
        public int getTotalTicksOfCravingP5() {
        	return totalTicksOfCravingP5;
        }
        
        public int getTotalTicksOfCravingP6() {
        	return totalTicksOfCravingP6;
        }
        
        public int getTotalTicksOfCravingP7() {
        	return totalTicksOfCravingP7;
        }
        
        public int getTotalTicksOfCravingP8() {
        	return totalTicksOfCravingP8;
        }
        
        public int getTotalTicksOfHornyP4() {
        	return totalTicksOfHornyP4;
        }
        
        public int getTotalTicksOfHornyP5() {
        	return totalTicksOfHornyP5;
        }
        
        public int getTotalTicksOfHornyP6() {
        	return totalTicksOfHornyP6;
        }
        
        public int getTotalTicksOfHornyP7() {
        	return totalTicksOfHornyP7;
        }
        
        public int getTotalTicksOfHornyP8() {
        	return totalTicksOfHornyP8;
        }
        
        public float getBabyCreeperGirlProbability() {
        	return babyCreeperGirlProbability;
        }
        
        public float getBabyZombieGirlProbability() {
        	return babyZombieGirlProbability;
        }
        
        public boolean isBellyColisionsForPlayersEnable() {
    		return enableBellyColisionsForPlayer;
    	}
        
        public boolean isBellyColisionsForPreggoMobsEnable() {
        	return enableBellyColisionsForPreggoMob;
        }
        
        public boolean isMountingEntitiesInLaterPregnancyPhasesEnable() {
			return enableMountingEntitiesInLaterPregnancyPhases;
		}
        
        public boolean isPreggoMobsTeleportToPlayerEnable() {
			return enablePreggoMobsTeleportToPlayer;
		}
        
        public boolean isSpawningHostilPregnantZombieGirlsEnable() {
        	return enableSpawningHostilPregnantZombieGirls;
        }
        
        public boolean isSpawningHostilPregnantMonsterCreeperGirlsEnable() {
			return enableSpawningHostilPregnantMonsterCreeperGirls;
		}
        
        public boolean isSpawningHostilPregnantHumanoidCreeperGirlsEnable() {
        	return enableSpawningHostilPregnantHumanoidCreeperGirls;
        }
        
        public boolean isSpawningHostilPregnantMonsterEnderWomenEnable() {
			return enableSpawningHostilPregnantMonsterEnderWomen;
		}
        
        public int getTotalTicksForPregnancyHealing() {
        	return totalTicksForPregnancyHealing;
        }
        
        public int getPregnacyHealingAmount() {
			return pregnacyHealingAmount;
		}
        
        public int getPregnacyHealingAmount(PregnancyPhase pregnancyPhase) {
        	float reductionPercentage = 0.05F * pregnancyPhase.ordinal();
        	return (int) Math.ceil(pregnacyHealingAmount * (1f - reductionPercentage));
        }
        
        private void calculateHungryValues() { 	
        	totalTicksOfHungryP1 = (int) Math.ceil(totalTicksOfHungryP0 * 0.85F);
        	totalTicksOfHungryP2 = (int) Math.ceil(totalTicksOfHungryP0 * 0.8F);
        	totalTicksOfHungryP3 = (int) Math.ceil(totalTicksOfHungryP0 * 0.75F);
        	totalTicksOfHungryP4 = (int) Math.ceil(totalTicksOfHungryP0 * 0.7F);
        	totalTicksOfHungryP5 = (int) Math.ceil(totalTicksOfHungryP0 * 0.65F);
        	totalTicksOfHungryP6 = (int) Math.ceil(totalTicksOfHungryP0  * 0.6F);
        	totalTicksOfHungryP7 = (int) Math.ceil(totalTicksOfHungryP0 * 0.55F);
        	totalTicksOfHungryP8 = (int) Math.ceil(totalTicksOfHungryP0 * 0.5);
        }
        
        private void calculateCravingValues() { 	
        	totalTicksOfCravingP2 = (int) Math.ceil(totalTicksOfCravingP1 * 0.9F);
        	totalTicksOfCravingP3 = (int) Math.ceil(totalTicksOfCravingP1 * 0.85F);
        	totalTicksOfCravingP4 = (int) Math.ceil(totalTicksOfCravingP1 * 0.8F);
        	totalTicksOfCravingP5 = (int) Math.ceil(totalTicksOfCravingP1 * 0.75F);
        	totalTicksOfCravingP6 = (int) Math.ceil(totalTicksOfCravingP1 * 0.7F);
        	totalTicksOfCravingP7 = (int) Math.ceil(totalTicksOfCravingP1 * 0.65F);
        	totalTicksOfCravingP8 = (int) Math.ceil(totalTicksOfCravingP1 * 0.6F);
        }
        
        private void calculateMilkingValues() { 	
        	totalTicksOfMilkingP3 = (int) Math.ceil(totalTicksOfMilkingP2 * 0.75F);
        	totalTicksOfMilkingP4 = (int) Math.ceil(totalTicksOfMilkingP2 * 0.7F);
        	totalTicksOfMilkingP5 = (int) Math.ceil(totalTicksOfMilkingP2 * 0.65F);
        	totalTicksOfMilkingP6 = (int) Math.ceil(totalTicksOfMilkingP2 * 0.6F);
        	totalTicksOfMilkingP7 = (int) Math.ceil(totalTicksOfMilkingP2 * 0.55F);
        	totalTicksOfMilkingP8 = (int) Math.ceil(totalTicksOfMilkingP2 * 0.5F);
        }
        
        private void calculateBellyRubsValues() { 	
        	totalTicksOfBellyRubsP4 = (int) Math.ceil(totalTicksOfBellyRubsP3 * 0.7F);
        	totalTicksOfBellyRubsP5 = (int) Math.ceil(totalTicksOfBellyRubsP3 * 0.65F);
        	totalTicksOfBellyRubsP6 = (int) Math.ceil(totalTicksOfBellyRubsP3 * 0.6F);
        	totalTicksOfBellyRubsP7 = (int) Math.ceil(totalTicksOfBellyRubsP3 * 0.55F);
        	totalTicksOfBellyRubsP8 = (int) Math.ceil(totalTicksOfBellyRubsP3 * 0.5F);
        }
       
        private void calculateHornyValues() { 	
        	totalTicksOfHornyP5 = (int) Math.ceil(totalTicksOfHornyP4 * 0.65F);
        	totalTicksOfHornyP6 = (int) Math.ceil(totalTicksOfHornyP4 * 0.6F);
        	totalTicksOfHornyP7 = (int) Math.ceil(totalTicksOfHornyP4 * 0.55F);
        	totalTicksOfHornyP8 = (int) Math.ceil(totalTicksOfHornyP4 * 0.5F);
        }
    }  
}
