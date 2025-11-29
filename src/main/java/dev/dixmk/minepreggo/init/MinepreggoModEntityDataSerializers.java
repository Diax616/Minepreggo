package dev.dixmk.minepreggo.init;

import java.util.Optional;

import dev.dixmk.minepreggo.world.entity.preggo.PreggoMobState;
import dev.dixmk.minepreggo.world.entity.preggo.creeper.AbstractCreeperGirl.CombatMode;
import dev.dixmk.minepreggo.world.pregnancy.Craving;
import dev.dixmk.minepreggo.world.pregnancy.PostPregnancy;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPain;
import dev.dixmk.minepreggo.world.pregnancy.PregnancyPhase;
import dev.dixmk.minepreggo.world.pregnancy.PregnancySymptom;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;

public class MinepreggoModEntityDataSerializers {

	private MinepreggoModEntityDataSerializers() {}
	
	public static final EntityDataSerializer<PregnancyPhase> PREGNANCY_STAGE = EntityDataSerializer.simpleEnum(PregnancyPhase.class);
	public static final EntityDataSerializer<CombatMode> COMBAT_MODE = EntityDataSerializer.simpleEnum(CombatMode.class);
	public static final EntityDataSerializer<PreggoMobState> STATE = EntityDataSerializer.simpleEnum(PreggoMobState.class);	

    public static final EntityDataSerializer<Optional<Craving>> OPTIONAL_CRAVING =
    		EntityDataSerializer.optional(
                // Writer: write enum name (safe)
                (buf, craving) -> buf.writeUtf(craving.name(), 32),
                // Reader: read enum name and parse
                buf -> Craving.valueOf(buf.readUtf(32))
            );
	
    public static final EntityDataSerializer<Optional<PregnancyPain>> OPTIONAL_PREGNANCY_PAIN =
    		EntityDataSerializer.optional(
                (buf, pain) -> buf.writeUtf(pain.name(), 32),
                buf -> PregnancyPain.valueOf(buf.readUtf(32))
            );
    
    public static final EntityDataSerializer<Optional<PregnancySymptom>> OPTIONAL_PREGNANCY_SYMPTOM =
    		EntityDataSerializer.optional(
                (buf, symptom) -> buf.writeUtf(symptom.name(), 32),
                buf -> PregnancySymptom.valueOf(buf.readUtf(32))
            );
    
    public static final EntityDataSerializer<Optional<PostPregnancy>> OPTIONAL_POST_PREGNANCY =
    		EntityDataSerializer.optional(
                (buf, post) -> buf.writeUtf(post.name(), 32),
                buf -> PostPregnancy.valueOf(buf.readUtf(32))
            );
    
	
	
	
	
    public static void register() {
        EntityDataSerializers.registerSerializer(OPTIONAL_PREGNANCY_SYMPTOM);
        EntityDataSerializers.registerSerializer(PREGNANCY_STAGE);
        EntityDataSerializers.registerSerializer(OPTIONAL_PREGNANCY_PAIN);
        EntityDataSerializers.registerSerializer(COMBAT_MODE);
        EntityDataSerializers.registerSerializer(STATE);
        EntityDataSerializers.registerSerializer(OPTIONAL_CRAVING);
        EntityDataSerializers.registerSerializer(OPTIONAL_POST_PREGNANCY);
    }
}
