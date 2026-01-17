package dev.dixmk.minepreggo.world.entity.preggo;

import java.util.Optional;
import java.util.OptionalInt;

import dev.dixmk.minepreggo.world.pregnancy.PostPregnancy;

public interface IPostPregnancyEntity {

	IFemalePreggoMob<?> getFemalePreggoMob();
	
	Optional<PostPregnancy> getSyncedPostPregnancy();
	
	OptionalInt getSyncedPostPartumLactation();
	
}
