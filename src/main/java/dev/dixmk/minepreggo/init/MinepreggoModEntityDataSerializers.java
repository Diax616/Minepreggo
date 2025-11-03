package dev.dixmk.minepreggo.init;

import dev.dixmk.minepreggo.world.entity.preggo.Craving;
import dev.dixmk.minepreggo.world.entity.preggo.PostPregnancy;
import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobState;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancyPain;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancyStage;
import dev.dixmk.minepreggo.world.entity.preggo.PregnancySymptom;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractCreeperGirl.CombatMode;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;

public class MinepreggoModEntityDataSerializers {

	private MinepreggoModEntityDataSerializers() {}
	
	public static final EntityDataSerializer<PregnancySymptom> PREGNANCY_SYMPTOM = EntityDataSerializer.simpleEnum(PregnancySymptom.class);	
	public static final EntityDataSerializer<PregnancyStage> PREGNANCY_STAGE = EntityDataSerializer.simpleEnum(PregnancyStage.class);
	public static final EntityDataSerializer<PregnancyPain> PREGNANCY_PAIN = EntityDataSerializer.simpleEnum(PregnancyPain.class);
	
	public static final EntityDataSerializer<CombatMode> COMBAT_MODE = EntityDataSerializer.simpleEnum(CombatMode.class);
	public static final EntityDataSerializer<PreggoMobState> STATE = EntityDataSerializer.simpleEnum(PreggoMobState.class);
	public static final EntityDataSerializer<Craving> CRAVING = EntityDataSerializer.simpleEnum(Craving.class);
	
	public static final EntityDataSerializer<PostPregnancy> POST_PREGNANCY = EntityDataSerializer.simpleEnum(PostPregnancy.class);	
	
    public static void register() {
        EntityDataSerializers.registerSerializer(PREGNANCY_SYMPTOM);
        EntityDataSerializers.registerSerializer(PREGNANCY_STAGE);
        EntityDataSerializers.registerSerializer(PREGNANCY_PAIN);
        EntityDataSerializers.registerSerializer(COMBAT_MODE);
        EntityDataSerializers.registerSerializer(STATE);
        EntityDataSerializers.registerSerializer(CRAVING);
        EntityDataSerializers.registerSerializer(POST_PREGNANCY);
    }
}
