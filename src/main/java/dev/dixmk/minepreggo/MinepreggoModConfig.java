package dev.dixmk.minepreggo;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

import org.apache.commons.lang3.tuple.Pair;

@Mod.EventBusSubscriber(modid = MinepreggoMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MinepreggoModConfig {
	
	private MinepreggoModConfig() {}
	
    public static final ForgeConfigSpec COMMON_SPEC;
    public static final ForgeConfigSpec CLIENT_SPEC;
    public static final ForgeConfigSpec SERVER_SPEC;
 
    static final Common COMMON;
    static final Client CLIENT;
    static final Server SERVER;
    
    private static int totalTickByPregnancyDays;
    private static int totalPregnancyDays;
    private static int ticksToStartPregnancy;
    private static int totalTicksOfMaternityLactation;
    private static int totalTicksOfPostPregnancyPhase;
    
    private static int totalTicksOfHungryP0;
    private static int totalTicksOfHungryP1;
    private static int totalTicksOfHungryP2;
    private static int totalTicksOfHungryP3;
    private static int totalTicksOfHungryP4;
    private static int totalTicksOfHungryP5;
    private static int totalTicksOfHungryP6;
    private static int totalTicksOfHungryP7;
    private static int totalTicksOfHungryP8;

    private static int totalTicksOfCravingP1;
    private static int totalTicksOfCravingP2;
    private static int totalTicksOfCravingP3;
    private static int totalTicksOfCravingP4;
    private static int totalTicksOfCravingP5;
    private static int totalTicksOfCravingP6;
    private static int totalTicksOfCravingP7;
    private static int totalTicksOfCravingP8;
    
    private static int totalTicksOfMilkingP2;
    private static int totalTicksOfMilkingP3;
    private static int totalTicksOfMilkingP4;
    private static int totalTicksOfMilkingP5;
    private static int totalTicksOfMilkingP6;
    private static int totalTicksOfMilkingP7;
    private static int totalTicksOfMilkingP8;
    
    private static int totalTicksOfBellyRubsP3;
    private static int totalTicksOfBellyRubsP4;
    private static int totalTicksOfBellyRubsP5;
    private static int totalTicksOfBellyRubsP6;
    private static int totalTicksOfBellyRubsP7;
    private static int totalTicksOfBellyRubsP8;
    
    private static int totalTicksOfHornyP4;
    private static int totalTicksOfHornyP5;
    private static int totalTicksOfHornyP6;
    private static int totalTicksOfHornyP7;
    private static int totalTicksOfHornyP8;
    
    private static float babyCreeperGirlProbability;
    private static float babyZombieGirlProbability;

    private static boolean enablePreggoMobsMoans;  
    private static boolean enablePlayerMoans;
    private static boolean enableBellySounds;
    
    public static int getTotalPregnancyDays() {
    	return totalPregnancyDays;
    }
     
    public static int getTotalTicksByPregnancyDay() {
    	return totalTickByPregnancyDays;
    }
    
    public static int getTicksToStartPregnancy() {
    	return ticksToStartPregnancy;
    }
    
    public static int getTotalTicksOfMaternityLactation() {
		return totalTicksOfMaternityLactation;
	}
    
    public static int getTotalTicksOfPostPregnancyPhase() {
    	return totalTicksOfPostPregnancyPhase;
    }
    
    public static int getTotalTicksOfHungryP0() {
    	return totalTicksOfHungryP0;
    }

    public static int getTotalTicksOfHungryP1() {
    	return totalTicksOfHungryP1;
    }
    
    public static int getTotalTicksOfHungryP2() {
    	return totalTicksOfHungryP2;
    }
    
    public static int getTotalTicksOfHungryP3() {
    	return totalTicksOfHungryP3;
    }
    
    public static int getTotalTicksOfHungryP4() {
    	return totalTicksOfHungryP4;
    }
    
    public static int getTotalTicksOfHungryP5() {
    	return totalTicksOfHungryP5;
    }
    
    public static int getTotalTicksOfHungryP6() {
    	return totalTicksOfHungryP6;
    }
    
    public static int getTotalTicksOfHungryP7() {
    	return totalTicksOfHungryP7;
    }
    
    public static int getTotalTicksOfHungryP8() {
    	return totalTicksOfHungryP8;
    }
    
    public static int getTotalTicksOfMilkingP2() {
    	return totalTicksOfMilkingP2;
    }
    
    public static int getTotalTicksOfMilkingP3() {
    	return totalTicksOfMilkingP3;
    }
    
    public static int getTotalTicksOfMilkingP4() {
    	return totalTicksOfMilkingP4;
    }
    
    public static int getTotalTicksOfMilkingP5() {
    	return totalTicksOfMilkingP5;
    }
    
    public static int getTotalTicksOfMilkingP6() {
    	return totalTicksOfMilkingP6;
    }
    
    public static int getTotalTicksOfMilkingP7() {
    	return totalTicksOfMilkingP7;
    }
    
    public static int getTotalTicksOfMilkingP8() {
    	return totalTicksOfMilkingP8;
    }
    
    public static int getTotalTicksOfBellyRubsP3() {
    	return totalTicksOfBellyRubsP3;
    }
    
    public static int getTotalTicksOfBellyRubsP4() {
    	return totalTicksOfBellyRubsP4;
    }
    
    public static int getTotalTicksOfBellyRubsP5() {
    	return totalTicksOfBellyRubsP5;
    }
    
    public static int getTotalTicksOfBellyRubsP6() {
    	return totalTicksOfBellyRubsP6;
    }
    
    public static int getTotalTicksOfBellyRubsP7() {
    	return totalTicksOfBellyRubsP7;
    }
    
    public static int getTotalTicksOfBellyRubsP8() {
    	return totalTicksOfBellyRubsP8;
    }
    
    public static int getTotalTicksOfCravingP1() {
    	return totalTicksOfCravingP1;
    }
    
    public static int getTotalTicksOfCravingP2() {
    	return totalTicksOfCravingP2;
    }
    
    public static int getTotalTicksOfCravingP3() {
    	return totalTicksOfCravingP3;
    }
    
    public static int getTotalTicksOfCravingP4() {
    	return totalTicksOfCravingP4;
    }
    
    public static int getTotalTicksOfCravingP5() {
    	return totalTicksOfCravingP5;
    }
    
    public static int getTotalTicksOfCravingP6() {
    	return totalTicksOfCravingP6;
    }
    
    public static int getTotalTicksOfCravingP7() {
    	return totalTicksOfCravingP7;
    }
    
    public static int getTotalTicksOfCravingP8() {
    	return totalTicksOfCravingP8;
    }
    
    public static int getTotalTicksOfHornyP4() {
    	return totalTicksOfHornyP4;
    }
    
    public static int getTotalTicksOfHornyP5() {
    	return totalTicksOfHornyP5;
    }
    
    public static int getTotalTicksOfHornyP6() {
    	return totalTicksOfHornyP6;
    }
    
    public static int getTotalTicksOfHornyP7() {
    	return totalTicksOfHornyP7;
    }
    
    public static int getTotalTicksOfHornyP8() {
    	return totalTicksOfHornyP8;
    }
    
    public static float getBabyCreeperGirlProbability() {
    	return babyCreeperGirlProbability;
    }
    
    public static float getBabyZombieGirlProbability() {
    	return babyZombieGirlProbability;
    }
    
    public static boolean isPlayerMoansEnable() {
    	return enablePlayerMoans;
    }
    
    public static boolean isPreggoMobsMoansEnable() {
    	return enablePreggoMobsMoans;
    }
    
    public static boolean isBellySoundsEnable() {
		return enableBellySounds;
	}
    
    static {
    	Pair<Client, ForgeConfigSpec> client = 
                new ForgeConfigSpec.Builder().configure(Client::new);     
        CLIENT_SPEC = client.getRight();
        CLIENT = client.getLeft();
    	
        final Pair<Common, ForgeConfigSpec> common = 
            new ForgeConfigSpec.Builder().configure(Common::new);
        COMMON_SPEC = common.getRight();
        COMMON = common.getLeft();
        
        
        final Pair<Server, ForgeConfigSpec> server = 
                new ForgeConfigSpec.Builder().configure(Server::new);
        SERVER_SPEC = server.getRight();
        SERVER = server.getLeft();
    }
    

    @SubscribeEvent
    static void onLoad(final ModConfigEvent.Loading event) {  	
    	  	
        if (event.getConfig().getSpec() == COMMON_SPEC) {
        	totalTickByPregnancyDays = COMMON.totalTickByPregnancyDays.get();
        	totalPregnancyDays = COMMON.totalPregnancyDays.get();
        	ticksToStartPregnancy = COMMON.ticksToStartPregnancy.get();    
        	totalTicksOfMaternityLactation = COMMON.totalTicksOfMaternityLactation.get();
        	totalTicksOfPostPregnancyPhase = COMMON.totalTicksOfPostPregnancyPhase.get();
        	totalTicksOfHungryP0 = COMMON.totalTicksOfHungry.get();
        	totalTicksOfCravingP1 = COMMON.totalTicksOfCraving.get();
        	totalTicksOfMilkingP2 = COMMON.totalTicksOfMilking.get();
        	totalTicksOfBellyRubsP3 = COMMON.totalTicksOfBellyRubs.get();
        	totalTicksOfHornyP4 = COMMON.totalTicksOfHorny.get();
        	
        	calculateHungryValues();
        	calculateCravingValues();
        	calculateMilkingValues();
        	calculateBellyRubsValues();
        	calculateHornyValues();      	
        }
        else if (event.getConfig().getSpec() == CLIENT_SPEC) {
        	enablePlayerMoans = CLIENT.enablePlayerMoans.get();
        	enablePreggoMobsMoans = CLIENT.enablePreggoMobsMoans.get();
        	enableBellySounds = CLIENT.enableBellySounds.get();
        }
        else if (event.getConfig().getSpec() == SERVER_SPEC) {
        	babyCreeperGirlProbability = (float) SERVER.babyCreeperGirlProbability.get().doubleValue();
        	babyZombieGirlProbability = (float) SERVER.babyZombieGirlProbability.get().doubleValue();    
        }
    }
    
    static class Common {  	
        final ForgeConfigSpec.IntValue totalPregnancyDays;
        final ForgeConfigSpec.IntValue ticksToStartPregnancy;
        final ForgeConfigSpec.IntValue totalTickByPregnancyDays;
        final ForgeConfigSpec.IntValue totalTicksOfHungry;
        final ForgeConfigSpec.IntValue totalTicksOfCraving;
        final ForgeConfigSpec.IntValue totalTicksOfMilking;
        final ForgeConfigSpec.IntValue totalTicksOfBellyRubs;
        final ForgeConfigSpec.IntValue totalTicksOfHorny;
        final ForgeConfigSpec.IntValue totalTicksOfMaternityLactation;
        final ForgeConfigSpec.IntValue totalTicksOfPostPregnancyPhase;
        
        Common(ForgeConfigSpec.Builder builder) {
            builder.push("Common"); // category name

            totalPregnancyDays = builder
                    .comment("Total number of pregnancy days.")
                    .defineInRange("totalPregnancyDays", 70, 20, Integer.MAX_VALUE);
            
            totalTickByPregnancyDays = builder
                    .comment("Total ticks per pregnancy day.")
                    .defineInRange("totalTickByDays", 24000, 100, 24000);

            ticksToStartPregnancy = builder
                    .comment("Ticks to start pregnancy after mating.")
                    .defineInRange("ticksToStartPregnancy", 6000, 100, 24000);
            
            totalTicksOfMaternityLactation = builder
					.comment("Total ticks of maternity lactation for preggo mobs and player.")
					.defineInRange("totalTicksOfMaternityLactation", 24000, 100, Integer.MAX_VALUE);
                   
            totalTicksOfPostPregnancyPhase = builder
            		.comment("Total ticks of post pregnancy phase for preggo mobs and player.")
            		.defineInRange("totalTicksOfPostPregnancyPhase", 24000, 100, Integer.MAX_VALUE);
            
            totalTicksOfHungry = builder
                    .comment("Total ticks of hungry for preggo mobs.")
                    .defineInRange("totalTicksOfHungry", 6000, 100, 24000);
            
            totalTicksOfCraving = builder
                    .comment("Total ticks of craving for pregnant entities.")
                    .defineInRange("totalTicksOfCraving", 7200, 100, 24000);
            
            totalTicksOfMilking = builder
                    .comment("total ticks of milking for pregnant entities.")
                    .defineInRange("totalTicksOfMilking", 7200, 100, 24000);
            
            totalTicksOfBellyRubs = builder
                    .comment("total ticks of belly rubs for pregnant entities.")
                    .defineInRange("totalTicksOfBellyRubs", 7200, 100, 24000);
            
            totalTicksOfHorny = builder
                    .comment("total ticks of horny for pregnant entities.")
                    .defineInRange("totalTicksOfHorny", 7200, 100, 24000);
                  
            builder.pop();
        }
    }
    
    static class Client {
        final ForgeConfigSpec.BooleanValue enablePlayerMoans;
        final ForgeConfigSpec.BooleanValue enablePreggoMobsMoans;
        final ForgeConfigSpec.BooleanValue enableBellySounds;

        Client(ForgeConfigSpec.Builder builder) {
            builder.push("Client"); // category name

            enablePlayerMoans = builder
                    .comment("Enable or disable player moans. (NOT WORKING)")
                    .define("enablePlayerMoans", true);

            enablePreggoMobsMoans = builder
                    .comment("Enable or disable pregnant mobs moans. (NOT WORKING)")
                    .define("enablePreggoMobsMoans", true);

            enableBellySounds = builder
                    .comment("Enable or disable belly sounds. (NOT WORKING)")
                    .define("enableBellySounds", true);
            
            builder.pop();
        }
    }
        
    static class Server {
        final ForgeConfigSpec.DoubleValue babyCreeperGirlProbability;   
        final ForgeConfigSpec.DoubleValue babyZombieGirlProbability;
 
        Server(ForgeConfigSpec.Builder builder) {
            builder.push("Server");
            
            babyCreeperGirlProbability = builder
                    .comment("probability of spawning a girl baby creeper.")
                    .defineInRange("babyCreeperGirlProbability", 0.2, 0.0, 1.0);

            babyZombieGirlProbability = builder
                    .comment("probability of spawning a girl baby zombie.")
                    .defineInRange("babyZombieGirlProbability", 0.3, 0.0, 1.0);
            
            builder.pop();
        }
    }
    
    private static void calculateHungryValues() { 	
    	totalTicksOfHungryP1 = (int) Math.ceil(totalTicksOfHungryP0 * 0.85F);
    	totalTicksOfHungryP2 = (int) Math.ceil(totalTicksOfHungryP0 * 0.8F);
    	totalTicksOfHungryP3 = (int) Math.ceil(totalTicksOfHungryP0 * 0.75F);
    	totalTicksOfHungryP4 = (int) Math.ceil(totalTicksOfHungryP0 * 0.7F);
    	totalTicksOfHungryP5 = (int) Math.ceil(totalTicksOfHungryP0 * 0.65F);
    	totalTicksOfHungryP6 = (int) Math.ceil(totalTicksOfHungryP0  * 0.6F);
    	totalTicksOfHungryP7 = (int) Math.ceil(totalTicksOfHungryP0 * 0.55F);
    	totalTicksOfHungryP8 = (int) Math.ceil(totalTicksOfHungryP0 * 0.5);
    }
    
    private static void calculateCravingValues() { 	
    	totalTicksOfCravingP2 = (int) Math.ceil(totalTicksOfCravingP1 * 0.8F);
    	totalTicksOfCravingP3 = (int) Math.ceil(totalTicksOfCravingP1 * 0.75F);
    	totalTicksOfCravingP4 = (int) Math.ceil(totalTicksOfCravingP1 * 0.7F);
    	totalTicksOfCravingP5 = (int) Math.ceil(totalTicksOfCravingP1 * 0.65F);
    	totalTicksOfCravingP6 = (int) Math.ceil(totalTicksOfCravingP1 * 0.6F);
    	totalTicksOfCravingP7 = (int) Math.ceil(totalTicksOfCravingP1 * 0.55F);
    	totalTicksOfCravingP8 = (int) Math.ceil(totalTicksOfCravingP1 * 0.5F);
    }
    
    private static void calculateMilkingValues() { 	
    	totalTicksOfMilkingP3 = (int) Math.ceil(totalTicksOfMilkingP2 * 0.75F);
    	totalTicksOfMilkingP4 = (int) Math.ceil(totalTicksOfMilkingP2 * 0.7F);
    	totalTicksOfMilkingP5 = (int) Math.ceil(totalTicksOfMilkingP2 * 0.65F);
    	totalTicksOfMilkingP6 = (int) Math.ceil(totalTicksOfMilkingP2 * 0.6F);
    	totalTicksOfMilkingP7 = (int) Math.ceil(totalTicksOfMilkingP2 * 0.55F);
    	totalTicksOfMilkingP8 = (int) Math.ceil(totalTicksOfMilkingP2 * 0.5F);
    }
    
    private static void calculateBellyRubsValues() { 	
    	totalTicksOfBellyRubsP4 = (int) Math.ceil(totalTicksOfBellyRubsP3 * 0.7F);
    	totalTicksOfBellyRubsP5 = (int) Math.ceil(totalTicksOfBellyRubsP3 * 0.65F);
    	totalTicksOfBellyRubsP6 = (int) Math.ceil(totalTicksOfBellyRubsP3 * 0.6F);
    	totalTicksOfBellyRubsP7 = (int) Math.ceil(totalTicksOfBellyRubsP3 * 0.55F);
    	totalTicksOfBellyRubsP8 = (int) Math.ceil(totalTicksOfBellyRubsP3 * 0.5F);
    }
   
    private static void calculateHornyValues() { 	
    	totalTicksOfHornyP5 = (int) Math.ceil(totalTicksOfHornyP4 * 0.65F);
    	totalTicksOfHornyP6 = (int) Math.ceil(totalTicksOfHornyP4 * 0.6F);
    	totalTicksOfHornyP7 = (int) Math.ceil(totalTicksOfHornyP4 * 0.55F);
    	totalTicksOfHornyP8 = (int) Math.ceil(totalTicksOfHornyP4 * 0.5F);
    }    
}
